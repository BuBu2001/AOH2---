# Архитектура Age of Civilizations II (декомпиляция 1.4)

Этот документ — карта кода. Его задача: за 2 минуты понять, где что лежит и куда идти,
чтобы что-то поправить. Полный список всех 1552 файлов — в [`FILES.md`](FILES.md),
машинночитаемые метаданные — в [`files.json`](files.json).

---

## 1. Что это за репозиторий

- Исходники — **декомпиляция Age of Civilizations II 1.4** (Steam-сборка; строки `Addon+ Developement Team`).
- Декомпилятор — **CFR 0.152** (шапка `Decompiled with CFR 0.152.` стоит в 608 файлах;
  остальные — результат другого прохода, там отступ в 3 пробела вместо 4).
- 1552 `.java` в **одном пакете** `age.of.civilizations2.jakowski.lukasz` (+2 файла в `desktop`).
  Внутренние классы не вынесены в отдельные файлы — они inline (анонимные классы и `$`-классы).
- `liv/` — **распакованные библиотеки** (`.class` + нативные `.dll/.so/.dylib`), а не код игры.
- **Чего нет:** ассетов (`UI/`, `game/`, `map/`, `languages/`) и файлов сборки.
  Без ресурсов из установленной игры проект не запустится, даже если скомпилируется.

## 2. Стек

| Компонент | Версия | Примечание |
|---|---|---|
| Java | 8+ | лямбды в 29 файлах, diamond, switch по строке |
| libGDX | 1.9.6 | `gdx`, `gdx-backend-lwjgl` (LWJGL **2**), `gdx-freetype` |
| steamworks4j | 1.8.0 | Steam API, UGC/мастерская |
| jlayer | 1.0.1-gdx | mp3 |
| jorbis / jogg / jutils | 0.0.17 / — / 1.0.0 | ogg |

Точка входа desktop: `desktop/DesktopLauncher` → `LwjglApplication(new AoCGame(...))`.
Мобильные/иные лаунчеры в репозиторий не попали (`IOSLauncher` ищется рефлексией в `AoCGame`).

## 3. Структура каталогов данных

Пути заданы константами в `CFG`:

```
config.ini                 разрешение/фуллскрин/AA/vsync/UI scale
settings, settings_map     сериализованные настройки (Java serialization)
UI/                        интерфейс: кнопки, линии, шрифты, game_logo.png
game/                      civilizations/, flags/, flagsH/, leaders/, lines/,
                           pallets_of_civs_colors/, alliance_names/, shader/
map/                       карты; map/data/continents/{packges,packges_data},
                           map/data/regions/{packges,packges_data},
                           map/backgrounds/, map/update/
languages/                 Bundle*.properties (основной, civilizations, loading)
saves/games/               сохранения партий (JSON)
saves/stats/civ/           статистика цивилизаций
```

## 4. Поток запуска

```
DesktopLauncher.main
 └─ config.ini → LwjglApplicationConfiguration → SteamAPI.init
 └─ new LwjglApplication(new AoCGame(linkHandler), config)

AoCGame.create()
 ├─ ConfigINI.readConfig(); CFG.LANDSCAPE = ...
 ├─ камера OrthographicCamera + FitViewport, CFG.updateRender(true)
 ├─ CFG.loadSettings(); расчёт DENSITY / XHDPI..XXXXHDPI / GUI_SCALE / PADDING
 ├─ ImageManager.addImage(...) для UI-иконок → Images.* (ID)
 ├─ CFG.menuManager = new MenuManager()
 ├─ Game_Render.updateRenderer(); CFG.soundsManager = new SoundsManager()
 ├─ new InitGame()            // в отдельном Thread → postRunnable
 │    ├─ CFG.langManager, шрифты, CFG.editorManager, CFG.game = new Game()
 │    ├─ CFG.eventsManager, CFG.map = new Map(), загрузка активной карты
 │    └─ CFG.menuManager.initExtraMenus()
 └─ загрузка шейдеров (game/shader/*.glsl), создание SpriteBatch
```

`AoCGame.render()` → `update()` (логика/UI) + `updateMoveMap()` (скролл карты),
затем отрисовка. Рендер «по требованию»: флаг `CFG.RENDER`, включается через `CFG.setRender_3(true)`;
`settingsManager.CONTINUOUS_RENDERING` отключает экономию. FPS ограничен `CFG.FPS_LIMIT = 60`.

## 5. Глобальное состояние: `CFG`

`CFG` — это «синглтон на статиках»: все подсистемы лежат в статических полях.

```
CFG.game            Game        модель партии
CFG.map             Map         карта и её части
CFG.menuManager     MenuManager UI
CFG.settingsManager SettingsManager
CFG.langManager     LanguageManager
CFG.soundsManager   SoundsManager
CFG.viewsManager    ViewsManager  режимы карты
CFG.oAI             AI
CFG.editorManager   EditorManager
CFG.eventsManager / historyManager / plagueManager / unionsManager / terrainTypesManager
CFG.flagManager / timelapseManager / tutorialManager / serviceRibbon_Manager / toast
```

Там же: размеры (`GAME_WIDTH/HEIGHT`, `BUTTON_HEIGHT/WIDTH`, `PADDING`, `GUI_SCALE`, `TEXT_HEIGHT`),
цвета (`COLOR_*`), шрифты, флаги `LOGS/DEBUG_MODE`, `isDesktop()/isAndroid()/isIOS()`,
утилиты отрисовки текста и `setRender_*`.

> Практика: почти любой код обращается к `CFG.xxx` напрямую. Это удобно, но связность максимальная —
> при правках легко зацепить инициализацию (порядок в `InitGame.Init()` важен).

## 6. UI: три уровня

```
MenuElement            базовый элемент: pos/size, visible, clickable, hover, draw()
├─ Button / Button_*   кнопки (текст, иконка, чекбокс, состояния)
├─ Text / Text_*       текстовые элементы
├─ Slider / Slider_*   слайдеры
├─ Graph*, Image, Line, Space, Drag, ...
└─ MenuElement_Hover_v2*  составные всплывающие подсказки

SliderMenu             контейнер экрана: заголовок + List<MenuElement>, скролл X/Y (ScissorStack),
│                      back-кнопка, closeable, visible
└─ Menu_*              565 классов: каждый экран/попап (Menu_InGame_*, Menu_CreateScenario_*, ...)

MenuManager            реестр: List<List<SliderMenu>> menus + List<List<Integer>> orderOfMenu;
                       addMenu(), addNextMenuToView(), setVisible(), hover, отрисовка стека
Menu                   enum всех экранов (eMAINMENU, eINGAME_*, eEDITOR_*, ...)
```

Особенности:
- координаты — **абсолютные пиксели** от `CFG.GAME_WIDTH/HEIGHT`, а не layout-менеджер;
- каждый `Menu_*` в конструкторе собирает `ArrayList<MenuElement>` и вызывает `initMenu(...)`;
- тексты берутся из `CFG.langManager.get("Key")` в `updateLanguage()`;
- анимации/перерисовка — вручную через `CFG.setRender_3(true)`;
- hover строится в `buildElementHover()`.

## 7. Модель игры

| Класс | Ответственность |
|---|---|
| `Game` | корень состояния: `lProvinces`, `lRegions`, `lCivs`, `lPlayers`, `lCities`, `lAlliances`, `lWars`, `lPeaceTreaties`, выделение, линии приказов, чудеса, активная провинция |
| `Province` | владелец/оккупант, население, экономика, развитие, армии по цивилизациям, соседи, границы, полигон, города, стабильность, опасность, потенциал |
| `Civilization` | провинции, армии, бюджет/доходы/расходы, счастье, стабильность, идеология, регионы, отношения, флаг, ИИ-стиль |
| `Player` | civID, туман войны, статистика, открытые панели, камера |
| `Region` / `Continent` | группировки провинций (бонусы, ИИ, пакеты карт) |
| `City`, `Mountain`, `Army` | объекты на карте |
| `Save_*` | сериализуемые срезы (`Save_Provinces_GameData`, `Save_Civ_GameData`, ...) |

## 8. Ход и правила

- `Game_Action` — «мозг» хода: приказы, бой (`diceAggressors`/`diceDefenders`, `DICE_ROLL_BONUS`),
  набор/роспуск, восстания, смена владельца, стоимость действий, переход хода.
  Состояния: `INPUT_ORDERS → LOAD_AI_RTO → TURN_ACTIONS → LOADING_NEXT_TURN →
  START_NEXT_TURN → SAVE_THE_GAME → RESULTS_STANDINGS → END_OF_THE_GAME`.
- `Turn_NewTurn` (**наследник `Thread`**) — тяжёлый пересчёт нового хода: экономика, население,
  счастье, оккупации, голод.
- `Game_NextTurnUpdate`, `Turn_Actions`, `Game_Calendar` (`TURN_ID`, год/месяц/день),
  `RandomTurnOrder` (RTO), `SaveManager` (автосейв).
- Балансные константы — прямо в `Game_Action` (`RECRUITABLE_ARMY_OWN_POP`, `UPRISE_*`,
  `NOT_SUPPLIED_*`, `MOVE_CAPITAL_*`). Менять их — самый быстрый способ подкрутить баланс.

## 9. ИИ

```
AI                    каркас: NUM_OF_CIVS_IN_THE_GAME, lFrontLines, expandNeutral,
                      REBUILD_PERSONALITY, статус-кво, lAI_Styles
AI_Style              базовый стиль (веса агрессии/дипломатии/экономики/армии)
├─ AI_Style_Communism / _Horde / _Fascism / _CityState / _Tribal / _Rebels
AI_Frontline          фронт (список провинций)
CivArmyMission*       миссии армий (атака/оборона/подкрепление/колонизация)
CivPersonality, CivPlans  личность и планы цивилизации
AI_* (55 файлов)      утилиты: соседи, опасность, экспансия, дипломатия, бюджет
```

## 10. Рендер карты и режимы

- `Game_Render` — заливка провинций, границы, имена цивилизаций, города, армии, анимации.
- `ViewsManager` + `View_Type` — ~35 режимов карты (политика, население, экономика, дипломатия,
  армия, снабжение, стабильность, идеологии, болезни, ...). Каждый режим задаёт цвет и hover.
- `Map`, `Map_BG`, `Map_Scale`, `Map_Scroll`, `Map_Coordinates`, `Map_TouchManager` — карта.
- `Minimap`, `MinimapInfo` — мини-карта.

## 11. Сохранения

- Формат — **JSON** (libGDX `Json`), файлы в `saves/games/`.
- Схема версионирована: `Save_GameData_1` … `Save_GameData_11`; `SaveManager` читает старые
  и достраивает недостающие поля (`buildData()`). **Новое поле в сейве = новая версия + шаг миграции.**
- Настройки — отдельно, Java serialization (`settings`, `settings_map`).

## 12. Редактор

`EditorManager` (активный инструмент, кисть, палитра) + `Menu_MapEditor_*`, `Menu_CreateScenario_*`,
`Menu_Editor_*`, `Undo*` (undo-стек: рельеф, континенты, регионы, рост населения),
`WastelandMap`, `FormableCivs`, `Scenario_*`, `PreDefined`.

## 13. Steam

`Steam_Game` — user, stats/achievements, remote storage, UGC (мастерская), leaderboards, friends.
Инициализация — в `DesktopLauncher` (`SteamAPI.loadLibraries()` / `SteamAPI.init()`), вызовы
размазаны по коду через `CFG.steamGame`. Достижения — `Steam_Achievements`, `Achievement`.

## 14. Особенности декомпилированного кода

Это важно, чтобы не «чинить» нормальное:

- почти все классы **package-private** (`class Game`, а не `public class`);
- отступы 3 или 4 пробела в зависимости от прохода декомпилятора;
- повсеместно `this.` и развёрнутые `new ArrayList<X>()`;
- исключения вместо проверок: `catch (IndexOutOfBoundsException)` / `NullPointerException`
  там, где ожидается «нормальный» выход;
- анонимные классы inline (кнопки с переопределённым `getColor`, потоки, колбэки);
- много полей-дублей и get/set-обёрток — это не мусор, а следы оптимизации/инлайна;
- статические мутабельные поля — норма (см. `Turn_NewTurn`, `Game_Action`).

## 15. Куда идти, если…

| Задача | Файлы |
|---|---|
| Поменять баланс (армия, восстания, стоимость) | `Game_Action` (константы в начале), `Turn_NewTurn` |
| Поправить экономику/налоги/доход | `Turn_NewTurn`, `Civilization` (`iIncome*`, `iBudget`), `Menu_InGame_FlagAction_Budget` |
| Добавить/изменить экран | новый `Menu_*` (наследник `SliderMenu`) + регистрация в `MenuManager` + `Menu` enum |
| Добавить кнопку на экран | `Menu_*.java` в конструкторе + `Button_*` при необходимости |
| Изменить подсказку (hover) | `buildElementHover()` в нужном `Menu_*`/`Text_*`, `MenuElement_Hover_v2*` |
| Новый режим карты | `ViewsManager` (`addViewToTheGame`) + `View_Type` |
| Логика ИИ | `AI`, `AI_Style*`, `AI_*`, `CivArmyMission*` |
| Дипломатия/войны/союзы | `DiplomacyManager`, `War_*`, `PeaceTreaty_*`, `Alliance*`, `Union*`, `Vassal` |
| Сохранение: новое поле | `Save_GameData_11` → `Save_GameData_12` + миграция в `SaveManager` |
| События и условия | `Event*`, `EventsManager`, `Menu_CreateScenario_Events_*`, `Menu_InGame_Event` |
| Технологии | `TechnologyManager`, `Menu_InGame_Technology`, `Menu_CreateScenario_Technology*` |
| Строения и чудеса | `BuildingsManager`, `Buildings`, `Construction*`, `Wonders_Manager`, `Wonder` |
| Религия/болезни | `ReligionManager`, `PlagueManager`, `Plague*` |
| Флаги и цвета | `FlagManager`, `PalletOfCivsColors`, `ColorPicker_AoC`, `Image`/`Images` |
| Локализация | `LanguageManager`, ключи `CFG.langManager.get("...")` |
| Steam/достижения | `Steam_Game`, `Steam_Achievements`, `ServiceRibbon*` |
| Редактор карт | `EditorManager`, `Menu_MapEditor_*`, `Undo*` |

## 16. Сборка (чего не хватает)

В репозитории нет `build.gradle`/`pom.xml` и ассетов. Минимальный путь:

1. Создать Gradle-проект (Java 8) с `sourceSets.main.java.srcDirs = ['.']` и
   `exclude 'liv/**'` — пакет `age.of...` уже соответствует пути `age/of/...` от корня.
2. Зависимости: `gdx 1.9.6`, `gdx-backend-lwjgl 1.9.6`, `gdx-freetype 1.9.6`,
   `steamworks4j 1.8.0`, `jlayer 1.0.1-gdx`, `jorbis 0.0.17`, `jutils 1.0.0`
   (либо подключить классы из `liv/` как `implementation files('liv')` — но там же нативные либы).
3. Положить ассеты из установленной игры рядом с рабочей директорией (`UI/`, `game/`, `map/`,
   `languages/`, `config.ini`).
4. Запуск: `age.of.civilizations2.jakowski.lukasz.desktop.DesktopLauncher`.

---

_Каталог обновляется командой:_ `python tools/gen_file_index.py`
