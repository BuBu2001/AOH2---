# Ассеты: что нужно игре и где их взять

## Коротко

В репозитории **ассетов нет** — только код (`age/`) и библиотеки (`liv/`).
Игра читает ресурсы из **рабочей папки** (working directory), а не из jar.
Поэтому сборка запускается так: `java -jar game.jar` из папки установленной игры
(см. гайд Red Border Studio: Output Directory = папка игры, Working directory = папка игры).

## Обязательная структура рабочей папки

Пути заданы константами в `CFG`:

```
config.ini                     разрешение/фуллскрин/AA/vsync/UI scale
settings, settings_map, status Java-serialization настроек
UI/                            интерфейс
  H/ XH/ XXH/ XXXH/ XXXXH/     варианты под плотность экрана (CFG.getRescouresPath())
    buttons/ icons/ top/ bottom/ menus/ boxes/ flags/ crowns/ ...
  lines/                       линии приказов, границ (line_11.png, line_32_off1.png, ...)
  picker/                      палитра цвета (hue.png, sv.png, edge.png, pos.png)
  fonts/                       шрифты (characters_main)
game/                          данные игры
  civilizations/               Age_of_Civilizations.json, civilizations_editor/
  flags/ flagsH/ flags_editor/ leaders/ lines/ pallets_of_civs_colors/
  alliance_names/ unions/ shader/ (flag_fragment.glsl и др.)
  Ages.json Governments.json ...
map/                           карты
  data/continents/{packges,packges_data}/
  data/regions/{packges,packges_data}/
  backgrounds/ scenarios/ update/
languages/                     Bundle*.properties, civilizations/, loading/
saves/                         games/ (JSON-сейвы), stats/civ/
```

## Где взять

1. **Установленная Age of Civilizations II v1.4** (Steam, appid `603850`) — эталон.
   Нужна именно 1.4: схема сейвов `Save_GameData_11`, набор UI-файлов и шейдеры
   привязаны к версии.
2. Папка игры от мододелов (Addon+ и т.п.) — тот же набор + моды.
3. Age of History III (`Documents/aoc3`) **не подходит**: это другая игра.
   Проверка совместимости на этой машине — 22 из 265 путей (8 %):
   совпали только `game/shader/*.glsl`, `UI/lines/*`, `UI/picker/*`,
   `game/Ages.json`, `game/Governments.json`; `UI/buttons/*`, `UI/icons/*`,
   `UI/top/*` и `game/flags/*` называются иначе.

## Проверка полноты

```powershell
python tools/check_assets.py "C:\путь\к\папке\игры"
```

Скрипт вытаскивает из кода все пути `ImageManager.addImage(...)` /
`Gdx.files.internal(...)`, разворачивает `CFG.getRescouresPath()` в H/XH/XXH/XXXH/XXXXH
и показывает, чего не хватает (с учётом регистра и с маппингом `UI/ → ui/interface/`).

## Сборка (по гайду)

1. IntelliJ: папка проекта = sources root, `liv/` = библиотека.
2. Artifacts → jar, manifest — `liv/META-INF/MANIFEST.MF`
   (Main-Class `age.of.civilizations2.jakowski.lukasz.desktop.DesktopLauncher`).
3. Output Directory — папка игры; запуск из неё же.
