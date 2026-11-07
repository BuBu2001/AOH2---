#!/usr/bin/env python3
"""Строит каталог всех .java файлов декомпилированного Age of Civilizations II.

Выход:
  docs/files.json - машинночитаемые метаданные по каждому файлу
  docs/FILES.md   - человекочитаемый каталог, сгруппированный по подсистемам

Запуск из корня репозитория:  python tools/gen_file_index.py
"""

import json
import os
import re
import sys

SRC_ROOT = os.path.join("age", "of", "civilizations2", "jakowski", "lukasz")
DESKTOP_ROOT = os.path.join(SRC_ROOT, "desktop")
DOCS = "docs"

# ---------------------------------------------------------------- parsing ----

TYPE_RE = re.compile(
    r"^[ \t]*(?:public[ \t]+|final[ \t]+|abstract[ \t]+|strictfp[ \t]+)*"
    r"(class|interface|enum)[ \t]+(\w+)",
    re.MULTILINE,
)
EXTENDS_RE = re.compile(r"\bextends[ \t]+([\w.<>, ]+?)(?=[ \t]*(?:implements|\{))")
IMPLEMENTS_RE = re.compile(r"\bimplements[ \t]+([\w.<>, ]+?)(?=[ \t]*\{)")
FIELD_RE = re.compile(
    r"^ {3,4}(?!return|if|for|while|else|try|catch|switch|case|break|continue|do\b|throw|new\b|this\.|super\.)"
    r"((?:protected|public|private|static|final|volatile|transient)[\w \t<>\[\],.?]*?)"
    r"[ \t]+(\w+)[ \t]*(?:=[^;]*)?;",
    re.MULTILINE,
)
METHOD_RE = re.compile(
    r"^ {3,4}(?:@\w+[ \t]*\n[ \t]*)*(?:protected|public|private)[ \t]+"
    r"((?:static[ \t]+|final[ \t]+|synchronized[ \t]+|abstract[ \t]+|native[ \t]+)*)"
    r"[\w<>\[\],.? \t]+?[ \t]+(\w+)[ \t]*\(",
    re.MULTILINE,
)
PACKAGE_RE = re.compile(r"^package[ \t]+([\w.]+);", re.MULTILINE)

# ------------------------------------------------------- подсистемы ----------

# Порядок важен: срабатывает первое совпадение.
FAMILIES = [
    ("Ядро и точка входа", r"^(AoCGame|DesktopLauncher|DesktopLinkHandler|LinkHandler|Touch|Keyboard|ConfigINI|InitGame)$"),
    ("Глобальное состояние", r"^(CFG|SettingsManager)$"),
    ("Модель: провинции", r"^Province"),
    ("Модель: цивилизации", r"^Civilization"),
    ("Модель: регионы и континенты", r"^(Region|Continent|Map_Continents|Map_Regions)"),
    ("Модель: армия и приказы", r"^(CivArmyMission|MoveUnits|Move_Units|RegroupArmy|RecruitArmy|Commands|Selected|Army|Mountain)"),
    ("Модель: экономика и города", r"^(City|Buildings?|Construction|Loan|Trade|CivInvest|CivBonus|CivFestival|Civ_Gift)"),
    ("Модель: дипломатия и войны", r"^(Diplomacy|War|PeaceTreaty|Alliance|Alliances|Union|Vassal|OpenBorders|Ultimatum|SupportRebels|Assimilate|CreateVassal|HolyRomanEmpire|TradeRequest|WarReparations|WarCiv|Ideolog)"),
    ("Модель: события", r"^Event"),
    ("Модель: история", r"^History"),
    ("Модель: задачи и планы ИИ", r"^(Civ_Task|CivPlans|CivPersonality|Civ_Mission)"),
    ("Модель: лидеры", r"^Leader"),
    ("Модель: прочее состояние", r"^(Player|Turn|Age|Difficulty|Skills|ServiceRibbon|RandomTurnOrder|PopulationGrowth|Achievement|Slide|Vicotry|Wonder|Terrain)"),
    ("Сохранения и данные", r"^(Save|Package)"),
    ("Игровой цикл и рендер", r"^(Game|Map$|Map_|ViewsManager|View|Graph|Minimap|Flag|Image|Images|Color|Line|Lines|Point|Space|Toast|Report|Statistics|Timelapse|RTS|Start)"),
    ("ИИ", r"^AI"),
    ("Редактор карт и сценариев", r"^(Editor|Editors|Scenario|Undo|Wasteland|Pallet|PreDefined|RandomGame|FormableCivs|UnionFlagsToGenerate)"),
    ("Steam и платформа", r"^Steam"),
    ("UI: экраны", r"^Menu"),
    ("UI: кнопки", r"^(Button|Buton)"),
    ("UI: элементы", r"^(Text|Slider|MenuElement|Message|Dialog|Tutorial|Drag|Menu$)"),
]

# --------------------------------------------------- описания ключевых файлов

DESCRIPTIONS = {
    "AoCGame": "Главный класс приложения (libGDX ApplicationAdapter + InputProcessor). Читает config.ini, создаёт камеру/viewport/шейдеры, гоняет update+render, обрабатывает ввод и скролл карты. Точка сборки подсистем через CFG.",
    "DesktopLauncher": "Точка входа desktop-сборки: парсит config.ini, настраивает LwjglApplicationConfiguration, грузит SteamAPI, запускает AoCGame.",
    "DesktopLinkHandler": "Desktop-реализация LinkHandler — открывает URL в браузере через Gdx.net.openURI.",
    "LinkHandler": "Интерфейс открытия внешних ссылок (отдельные реализации под desktop/mobile).",
    "Touch": "Абстракция ввода: позиция мыши/тача, кнопки, скролл-дельты, эмуляция тача на desktop.",
    "Keyboard": "Экранная клавиатура/ввод текста (наследует SliderMenu, т.к. это всплывающая панель).",
    "ConfigINI": "Чтение/запись config.ini: WIDTH/HEIGHT/FULLSCREEN/ANTIALIASING/VSYNC/LANDSCAPE/UISCALE.",
    "CFG": "Глобальный контекст игры: статические ссылки на все подсистемы (game, map, menuManager, settingsManager, langManager, soundsManager, viewsManager, oAI, менеджеры), пути к ассетам, шрифты/цвета, утилиты рендера текста, isDesktop/isAndroid/isIOS, setRender_*.",
    "SettingsManager": "Сериализуемые настройки игрока: язык, размеры шрифтов, громкости, альфы провинций, границы, автозащита, цвета имён цивилизаций, режимы рендера.",
    "Game": "Модель партии: провинции, регионы, цивилизации, игроки, города, горы, альянсы, войны, мирные договоры, выделение, линии приказов, чудеса. Здесь же сериализация состояния и запросы (getProvince/getCiv/...).",
    "Game_Action": "Правила хода и боя: обработка приказов, dice roll, набор/роспуск армии, восстания, смена владельца, движение войск, стоимости действий, переход хода.",
    "Game_Render": "Отрисовка карты: заливка провинций по режимам, границы, названия цивилизаций, города, армии, анимации, подписи регионов.",
    "Game_Calendar": "Календарь: год/месяц/день, TURN_ID, скорость хода, форматирование дат.",
    "Game_NextTurnUpdate": "Межходовые обновления: экономика, население, счастье, ИИ-шаги.",
    "Game_Scenarios": "Загрузка/сохранение сценариев и стартовых условий.",
    "Map": "Данные карты: список карт (теги, масштаб, фон, пакеты континентов/регионов), фон, координаты, зум, скролл, тач-менеджер, бассейны.",
    "Map_TouchManager": "Логика мыши/тача по карте: выбор провинции, drag, зум, клики по армиям.",
    "Map_BG": "Фон карты (текстура/тайлинг/параллакс).",
    "Map_Coordinates": "Преобразование экранных координат в провинции и обратно.",
    "Map_Scroll": "Скролл карты (инерция, границы).",
    "Map_Scale": "Масштаб карты (зум, ограничения, шаги).",
    "Map_Continents": "Пакеты континентов (загрузка/хранение).",
    "Map_Regions": "Пакеты регионов (загрузка/хранение).",
    "ViewsManager": "Режимы отображения карты (политический, население, экономика, дипломатия, армия, ~35 режимов) + легенды и hover-подсказки для каждого.",
    "MenuManager": "Менеджер UI: List<List<SliderMenu>> со всеми экранами, порядок наложения, переключение eMAINMENU/eINGAME_*/eEDITOR_*, hover, попапы, позиции элементов, отрисовка.",
    "Menu": "enum всех экранов и состояний UI (eMAINMENU, eINGAME_*, eEDITOR_*, ...).",
    "MenuElement": "Базовый UI-элемент: позиция, размер, видимость, кликабельность, hover, отрисовка.",
    "SliderMenu": "Контейнер экрана: заголовок, список MenuElement, скролл по X/Y со ScissorStack, back-кнопка, закрытие.",
    "SliderMenuTitle": "Заголовок SliderMenu (текст, кнопки, иконки).",
    "Button": "Базовый класс кнопки: текст, позиция текста, чекбокс, состояния (обычное/hover/нажато/выключено).",
    "Slider": "Базовый слайдер: значение, диапазон, перетаскивание, колбэк.",
    "Text": "Базовый текстовый элемент (шрифт, цвет, выравнивание, перенос).",
    "Space": "Пустой UI-элемент-отступ.",
    "Image": "Обёртка текстуры (фильтры, wrap, регион).",
    "Images": "ID всех изображений UI (константы).",
    "ImageManager": "Реестр текстур по ID: addImage/getImage, фильтры и wrap.",
    "Province": "Провинция: владелец и оккупант, население/экономика/развитие, армии по цивилизациям, соседи (суша/море), участки границ, полигон (точки), города, горы, чудеса, стабильность, опасность, потенциал, рост населения.",
    "Province_Border": "Участок границы провинции для отрисовки (land-by-land / land-by-sea / sea-by-sea).",
    "Province_Animation2": "Анимация подсветки провинции.",
    "Civilization": "Цивилизация: провинции и армии, бюджет/доходы/расходы, счастье, стабильность, развитие, идеология, регионы, отношения, флаг, теги, ИИ-стиль, счётчики строений.",
    "Civilization_Region": "Регион внутри цивилизации (группировка провинций для бонусов/ИИ).",
    "Player": "Игрок: civID, туман войны, статистика, состояние открытых панелей, камера, «нет приказов».",
    "AI": "Каркас ИИ: стили (AI_Style), фронты, экспансия на нейтралов, пересборка личности, константы терпения/статус-кво.",
    "AI_Style": "Базовый стиль ИИ: веса агрессии/дипломатии/экономики/армии и правила принятия решений.",
    "Turn_NewTurn": "Поток расчёта нового хода: экономика, население, счастье, оккупации, восстания, голод.",
    "Turn_Actions": "Очередь действий внутри хода.",
    "SaveManager": "Сохранение/загрузка партии (JSON), автосейв, миграция версий Save_GameData_1..11, статистика, сейвы в saves/games/.",
    "Save_GameData": "Базовая структура сейва партии.",
    "Save_GameData_11": "Актуальная (11-я) версия схемы сейва; добавляет Events_GameData. Более старые версии — шаги миграции.",
    "Save_Provinces_GameData": "Сериализуемая часть провинции.",
    "Save_Civ_GameData": "Сериализуемая часть цивилизации.",
    "Save_Player_GameData": "Сериализуемая часть игрока.",
    "LanguageManager": "I18N-бандлы (основной, цивилизации, экраны загрузки), выбор локали, режим вывода ключей.",
    "SoundsManager": "Музыка и звуки: плейлист, громкости, ID эффектов, console/start/defeat музыка.",
    "Steam_Game": "Интеграция Steam: user, stats/achievements, remote storage, UGC (моды), leaderboards, friends, utils.",
    "Steam_Achievements": "Список и разблокировка достижений Steam.",
    "EditorManager": "Состояние редактора: активный инструмент, кисть, undo-стек, палитры, режим редактора.",
    "DiplomacyManager": "Дипломатические действия и проверки: войны, союзы, проход войск, мир, вассалы, унии.",
    "TechnologyManager": "Дерево технологий: уровни, стоимость, эффекты.",
    "BuildingsManager": "Строения: типы, стоимость, эффекты, лимиты и подсчёт по цивилизации.",
    "ReligionManager": "Религии: список, распространение, эффекты.",
    "PlagueManager": "Болезни: распространение, смертность, затухание.",
    "TutorialManager": "Пошаговый туториал и его шаги.",
    "HistoryManager": "История партии: события, данные для графиков.",
    "TimelapseManager": "Таймлапс: снимки карты по ходам и их проигрывание.",
    "FlagManager": "Генерация и кэш флагов цивилизаций/союзов/уний.",
    "Minimap": "Мини-карта: текстура, клики, рамка текущего вида.",
    "Graph": "Графики статистики с осями, подписями и легендой.",
    "Toast": "Всплывающие уведомления внизу/вверху экрана.",
    "Commands": "Приказы игрока (движение, набор, стройка, дипломатия) с валидацией.",
    "Selected_Provinces": "Выделенные провинции и групповые операции.",
    "CivArmyMission": "Миссия армии ИИ: цель, маршрут, тип (атака/оборона/подкрепление).",
    "MoveUnits_Line": "Линия перемещения армии (логика + отрисовка).",
    "RegroupArmy_Data": "Состояние операции перегруппировки.",
    "PeaceTreaty_Data": "Мирный договор: условия, участники, статус.",
    "Alliance": "Альянс: участники, лидер, правила.",
    "War_GameData": "Война: участники, счёт, оккупации.",
    "Ideologies": "Менеджер идеологий.",
    "Ideology": "Идеология: модификаторы, цвета, доступность.",
    "Wonders_Manager": "Чудеса света: постройка, эффекты, размещение.",
    "Wonder": "Чудо света в провинции.",
    "Religion": "Данные религии.",
    "Plague": "Данные болезни.",
    "Region": "Регион карты (группа провинций) для бонусов и ИИ.",
    "Continent": "Континент карты.",
    "City": "Город: уровень, население, отрисовка, бонусы.",
    "Mountain": "Горный пик на карте.",
    "Army": "Армия/юнит в провинции.",
    "Loan": "Кредиты: взятие, проценты, выплата.",
    "Buildings": "Типы и данные строений.",
    "Construction": "Очередь строительства.",
    "TradeRoute": "Торговый маршрут.",
    "TradeZone": "Торговая зона.",
    "Vassal": "Вассалитет: сюзерен/вассал, обязанности.",
    "Union": "Уния/объединение государств.",
    "Ultimatum": "Ультиматум в дипломатии.",
    "SupportRebels": "Поддержка повстанцев.",
    "Assimilate": "Ассимиляция провинций.",
    "OpenBorders": "Договор об открытых границах.",
    "Statistics": "Статистика партии/цивилизации.",
    "Report": "Отчёт о сражении/ходе.",
    "Timelapse": "Снимок карты для таймлапса.",
    "Line": "Линия UI.",
    "Color": "Цвет (данные/утилиты).",
    "ColorPicker_AoC": "Палитра выбора цвета (флаги, цвета цивилизаций).",
    "Point": "Точка 2D.",
    "Drag": "Базовый drag-элемент.",
    "Drag_Civilization": "Перетаскивание цивилизации (редактор).",
    "RTS": "Режим реального времени: скорость, пауза, шаг хода.",
    "Start": "Стартовый экран/загрузка.",
    "View": "Базовый режим отображения карты.",
    "View_Type": "Базовый класс режима отображения карты (заливка, легенда, hover).",
    "MinimapInfo": "Данные мини-карты.",
    "WastelandMap": "Необитаемые (wasteland) провинции.",
    "Undo": "Базовый undo-стек редактора.",
    "Editors": "Перечисление редакторов.",
    "Editor": "Базовый класс редактора.",
    "Scenario": "Сценарий: стартовые данные карты.",
    "Pallet": "Палитра цветов/провинций.",
    "FormableCivs": "Формируемые цивилизации (условия и результат).",
    "RandomGame": "Генерация случайной партии.",
    "PreDefined": "Предопределённые данные карт/сценариев.",
    "ServiceRibbon": "Лента сервисов (Steam-статистика, достижения).",
    "Achievement": "Достижение.",
    "Difficulty": "Сложность и её модификаторы.",
    "Skills": "Навыки/перки.",
    "Age": "Эпоха и её модификаторы.",
    "CivBonus": "Бонус цивилизации.",
    "CivFestival": "Праздник цивилизации.",
    "CivInvest": "Инвестиции.",
    "CivPersonality": "Личность ИИ-цивилизации.",
    "CivPlans": "Планы ИИ.",
    "Leader": "Лидер цивилизации.",
    "LeaderOfCiv": "Лидер конкретной цивилизации.",
    "HolyRomanEmpire": "Священная Римская империя: состав, курфюрсты, император.",
    "TradeRequest": "Запрос торговли.",
    "TradeZones": "Менеджер торговых зон.",
    "Plagues": "Менеджер болезней.",
    "PlagueProvince": "Болезнь в провинции.",
    "PopulationGrowth": "Расчёт роста населения.",
    "RandomTurnOrder": "Случайный порядок хода.",
    "DiplomacyColors": "Цвета дипломатических отношений.",
    "TutorialBox": "UI-бокс туториала.",
    "MessageBox": "Очередь сообщений игроку.",
    "Message": "Базовое сообщение игроку.",
    "Dialog": "Диалоговое окно.",
    "TextData": "Данные текста (текст, шрифт, цвет).",
    "TextSlider": "Слайдер с текстом.",
    "MenuElement_Hover": "Всплывающая подсказка.",
    "MenuElement_Hover_v2": "Составная подсказка v2.",
    "MenuElement_Hover_v2_Element2": "Строка подсказки v2.",
    "MenuElement_Hover_v2_Element_Type": "Тип элемента подсказки.",
    "UndoTerrain": "Undo изменения рельефа.",
    "UndoContinent": "Undo изменения континента.",
    "UndoGrowthRate": "Undo изменения роста населения.",
    "UndoOptimizationRegions": "Undo оптимизации регионов.",
    "UnionFlagsToGenerate": "Очередь генерации флагов уний.",
    "PalletOfCivsColors": "Палитра цветов цивилизаций.",
    "LinesManager": "Менеджер линий (движение армий, границы).",
    "GraphData": "Данные графика.",
    "GraphLine": "Линия графика.",
    "Graph_Vertical": "Вертикальный график.",
    "Graph_Circle": "Круговой график.",
    "HistoryLog": "Запись в лог истории.",
    "Event": "Событие (условия + эффекты).",
    "Events": "Список событий.",
    "EventsManager": "Менеджер событий.",
    "War": "Война (данные/UI).",
    "WarCiv": "Участник войны.",
    "WarReparations": "Репарации.",
    "Alliances": "Менеджер альянсов.",
    "Unions": "Менеджер уний.",
    "UnionsManager": "Менеджер уний.",
    "Terrain": "Тип местности.",
    "TerrainTypesManager": "Менеджер типов местности.",
    "Flag": "Флаг (текстура/данные).",
    "Image": "Обёртка текстуры.",
    "Save": "Базовый формат сохранения.",
    "Menu_InGame": "HUD в игре: верхняя/нижняя панели, скорость, календарь, кнопки перехода хода и открытия панелей.",
    "Menu_Main": "Главное меню: новая игра, загрузка, редактор, настройки, выход.",
    "Menu_InGame_FlagAction_Budget": "Панель бюджета цивилизации: доходы/расходы, налоги, содержание армии.",
    "Menu_InGame_FlagAction_Bot_Left": "Нижняя левая панель действий цивилизации (флаг, бюджет, статистика).",
    "Menu_InGame_FlagAction_Stats": "Панель статистики цивилизации в игре.",
    "Menu_InGame_ProvinceInfo": "Карточка провинции: население, экономика, армия, строения, стабильность, действия.",
    "Menu_InGame_Build": "Экран строительства в провинции (форты, порты, фермы, библиотеки...).",
    "Menu_InGame_Technology": "Экран технологий: дерево, стоимость, изучение.",
    "Menu_InGame_RecruitArmy": "Экран набора армии в провинции.",
    "Menu_InGame_Diplomacy": "Экран дипломатии: отношения, войны, союзы, договоры.",
    "Menu_InGame_Army": "Экран армии: список армий цивилизации, перемещение, перегруппировка.",
    "Menu_InGame_Slider_Recruit": "Слайдер набора армии в провинции.",
    "Menu_InGame_CivInfo_Stats_Diplomacy": "Дипломатия в карточке цивилизации: отношения и доступные действия.",
    "Menu_InGame_View_Army": "Режим карты «армия»: численность войск по провинциям.",
    "Menu_InGame_View_Recruitable": "Режим карты «доступный набор»: сколько армии можно набрать.",
    "Game_Render_Province": "Отрисовка провинций: заливка по режимам, границы, оккупация, подсветка, туман войны.",
    "Button_Diplomacy": "Кнопка открытия дипломатической панели.",
    "Menu_CreateScenario_Settings": "Настройки сценария: стартовые армии, дата, сложность, лимиты.",
    "Menu_InGame_GraphManager": "Экраны графиков статистики.",
    "Menu_InGame_WorldPopulation": "Экран населения мира.",
    "Menu_InGame_WarDetails": "Детали войны: участники, счёт, потери.",
    "Menu_InitGame": "Экран инициализации/загрузки игры.",
}

# --------------------------------------------------- перевод названий --------

# Префикс -> (регэксп отрезаемой части, русский префикс описания)
PREFIXES = [
    (r"^Menu_InGame_", "Экран в игре: "),
    (r"^Menu_CreateScenario_", "Редактор сценария: "),
    (r"^Menu_CreateNewGame", "Создание игры: "),
    (r"^Menu_Civilizations?_", "Инфо о цивилизации: "),
    (r"^Menu_Editor", "Редактор: "),
    (r"^Menu_Settings", "Настройки: "),
    (r"^Menu_", "Экран/попап: "),
    (r"^Button_", "Кнопка: "),
    (r"^Buton_", "Кнопка: "),
    (r"^Message_", "Сообщение: "),
    (r"^Event_", "Событие: "),
    (r"^Text_", "Текст: "),
    (r"^Slider_", "Слайдер: "),
    (r"^Save_", "Структура сейва: "),
    (r"^HistoryLog_", "Запись истории: "),
    (r"^MenuElement_", "UI-элемент: "),
    (r"^Graph_", "График: "),
    (r"^AI_Style_", "Стиль ИИ: "),
    (r"^AI_", "ИИ: "),
    (r"^Province_", "Провинция: "),
    (r"^Civilization_", "Цивилизация: "),
    (r"^CivArmyMission_", "Миссия армии ИИ: "),
    (r"^Construction_", "Строительство: "),
    (r"^Map_", "Карта: "),
    (r"^RegroupArmy_", "Перегруппировка: "),
    (r"^PeaceTreaty_", "Мирный договор: "),
    (r"^Timelapse_", "Таймлапс: "),
    (r"^MoveUnits_", "Перемещение войск: "),
    (r"^Civ_", "Цивилизация: "),
    (r"^Flag_", "Флаг: "),
    (r"^Turn_", "Ход: "),
    (r"^ServiceRibbon_", "Лента сервисов: "),
    (r"^Move_", "Движение: "),
    (r"^TradeRequest_", "Торговый запрос: "),
    (r"^Undo", "Отмена: "),
    (r"^UnionFlagsToGenerate", "Флаги уний: "),
]

# Слова-связки, которые в русском описании не нужны.
STOPWORDS = {
    "Of", "To", "The", "A", "Is", "For", "At", "By", "With", "And", "Or", "As",
    "Can", "We", "Have", "No", "Not", "v2", "2", "3", "1", "4", "5", "6", "7",
    "8", "9", "10", "11",
}

WORDS = {
    "Menu": "меню", "Game": "игра", "Games": "игры", "Button": "кнопка", "In": "в",
    "Create": "создание", "Scenario": "сценарий", "Scenarios": "сценарии",
    "Data": "данные", "Message": "сообщение", "Messages": "сообщения",
    "Event": "событие", "Events": "события", "Civ": "цивилизация", "Civs": "цивилизации",
    "Civilization": "цивилизация", "Civilizations": "цивилизации", "Editor": "редактор",
    "Editors": "редакторы", "Diplomacy": "дипломатия", "Diplo": "дипломатия",
    "Text": "текст", "Info": "информация", "Map": "карта", "Maps": "карты",
    "Action": "действие", "Actions": "действия", "War": "война", "Wars": "войны",
    "Army": "армия", "Armies": "армии", "Flag": "флаг", "Flags": "флаги",
    "Condition": "условие", "Conditions": "условия", "Cond": "условие",
    "Build": "строительство", "Building": "строение", "Buildings": "строения",
    "Province": "провинция", "Provinces": "провинции", "Slider": "слайдер",
    "Sliders": "слайдеры", "New": "новый", "Select": "выбор", "Selected": "выбранный",
    "View": "вид", "Views": "виды", "Outcome": "результат", "Peace": "мир",
    "Out": "результат", "List": "список", "Manager": "менеджер", "Manage": "управление",
    "Vassal": "вассал", "Vassals": "вассалы", "Treaty": "договор", "Low": "низкий",
    "High": "высокий", "Alliance": "альянс", "Alliances": "альянсы", "Add": "добавить",
    "Element": "элемент", "Statistics": "статистика", "Stats": "статистика",
    "Graph": "график", "Type": "тип", "Types": "типы", "Pact": "пакт", "Pacts": "пакты",
    "Population": "население", "Title": "заголовок", "Outliner": "обзор",
    "Num": "количество", "Save": "сохранение", "Saved": "сохранено",
    "Military": "военный", "Militarty": "военный", "Militart": "военный",
    "Right": "правый", "Left": "левый", "Top": "верх", "Bot": "низ", "Bottom": "низ",
    "Style": "стиль", "History": "история", "Economy": "экономика", "Eco": "экономика",
    "Access": "доступ", "Option": "опция", "Options": "опции", "Players": "игроки",
    "Player": "игрок", "Edit": "редактирование", "Move": "перемещение",
    "Movement": "движение", "Moved": "перемещено", "Hover": "подсказка",
    "Formable": "формируемый", "Log": "лог", "Alphabet": "алфавит",
    "Defensive": "оборонительный", "Random": "случайный", "Update": "обновление",
    "Regroup": "перегруппировка", "Units": "юниты", "Independence": "независимость",
    "Trade": "торговля", "Invest": "инвестиции", "Investments": "инвестиции",
    "Investemnts": "инвестиции", "Development": "развитие", "Dev": "развитие",
    "Port": "порт", "Relations": "отношения", "Relation": "отношение",
    "Happiness": "счастье", "Technology": "технология", "Tech": "технология",
    "Union": "уния", "Unions": "унии", "Bar": "панель", "Turn": "ход", "Turns": "ходы",
    "Terrain": "местность", "Mission": "миссия", "Color": "цвет", "Colors": "цвета",
    "Region": "регион", "Regions": "регионы", "Wasteland": "необитаемые земли",
    "Accepted": "принято", "Line": "линия", "Lines": "линии", "Value": "значение",
    "Cores": "коры", "Core": "кор", "Demands": "требования", "Demand": "требование",
    "Aggression": "агрессия", "Aggreession": "агрессия", "Vertical": "вертикальный",
    "Call": "призыв", "Destroy": "уничтожение", "Ideology": "идеология",
    "Ideologies": "идеологии", "Points": "очки", "Skills": "навыки",
    "Rebels": "повстанцы", "Age": "эпоха", "Ages": "эпохи", "Control": "контроль",
    "Controls": "контроль", "Conrol": "контроль", "Join": "вступление",
    "Capital": "столица", "Capitals": "столицы", "Service": "сервис",
    "Ribbon": "лента", "Construction": "строительство", "Leader": "лидер",
    "Leaders": "лидеры", "Ask": "запрос", "Fort": "форт", "Tower": "башня",
    "Allies": "союзники", "Ally": "союзник", "Arrow": "стрелка", "Cost": "стоимость",
    "Denied": "отказано", "Scale": "масштаб", "Settings": "настройки",
    "Ultimatum": "ультиматум", "Timelapse": "таймлапс", "Farm": "ферма",
    "Library": "библиотека", "Level": "уровень", "Levels": "уровни", "Arms": "оружие",
    "Change": "изменение", "Changes": "изменения", "Gift": "подарок", "Main": "главный",
    "Rate": "оценка", "Clear": "очистить", "Report": "отчёт", "Prepare": "подготовка",
    "Preparations": "подготовка", "Show": "показ", "Set": "установить",
    "Pallet": "палитра", "Pallets": "палитры", "Generate": "генерация",
    "Recruit": "набор", "Recruited": "набрано", "Recruitment": "набор",
    "Recruitable": "наборный", "Request": "запрос", "Reuest": "запрос",
    "Refused": "отклонено", "Armoury": "арсенал", "Workshop": "мастерская",
    "Loan": "кредит", "Loans": "кредиты", "Disease": "болезнь", "Diseases": "болезни",
    "Supplied": "снабжение", "Supply": "снабжение", "Supplies": "снабжение",
    "Transfer": "передача", "Dialog": "диалог", "Form": "форма", "Support": "поддержка",
    "Modes": "режимы", "Classic": "классический", "Load": "загрузка",
    "Image": "изображение", "Images": "изображения", "Part": "часть", "Growth": "рост",
    "Continent": "континент", "Continents": "континенты", "Decision": "решение",
    "Decisions": "решения", "Perc": "процент", "Percentage": "процент",
    "Overlay": "оверлей", "Package": "пакет", "Packages": "пакеты",
    "Assign": "назначение", "Side": "сторона", "Border": "граница",
    "Borders": "границы", "Expired": "истёк", "City": "город", "Cities": "города",
    "Names": "имена", "Name": "имя", "Assimilate": "ассимиляция",
    "Assimilation": "ассимиляция", "Position": "позиция", "Migrate": "миграция",
    "Offer": "предложение", "Plunder": "грабёж", "Plundred": "разграблено",
    "Take": "взятие", "Taken": "взято", "Box": "бокс", "Boxes": "боксы",
    "Active": "активный", "Clip": "клип", "Details": "детали", "Neutral": "нейтральный",
    "Treasury": "казна", "Date": "дата", "Draw": "рисование",
    "Guarantee": "гарантия", "Declaration": "объявление", "Customize": "настройка",
    "Sea": "море", "Desc": "описание", "Undo": "отмена", "Festival": "фестиваль",
    "Disband": "роспуск", "Calendar": "календарь", "Just": "только",
    "Colonize": "колонизация", "Colonization": "колонизация", "Remove": "удаление",
    "Next": "следующий", "Wonder": "чудо", "Wonders": "чудеса",
    "Reparations": "репарации", "Tribute": "дань", "Stability": "стабильность",
    "Expand": "экспансия", "Neighbors": "соседи", "Neighboring": "соседний",
    "Neigh": "сосед", "Trigger": "триггер", "Circle": "круг", "Signed": "подписано",
    "Current": "текущий", "Budget": "бюджет", "Response": "ответ",
    "Repaid": "выплачено", "Lost": "потеряно", "Truce": "перемирие",
    "Truces": "перемирия", "Front": "фронт", "Frontline": "линия фронта",
    "Research": "исследование", "Researched": "изучено", "Colony": "колония",
    "Colonies": "колонии", "Casualties": "потери", "Losses": "потери",
    "Government": "правительство", "Goverment": "правительство",
    "Goverments": "правительства", "Improve": "улучшение", "Improving": "улучшение",
    "Wiki": "вики", "Picker": "выбор", "Icon": "иконка", "Middle": "середина",
    "All": "все", "Elections": "выборы", "Today": "сегодня", "Victory": "победа",
    "Vicotry": "победа", "End": "конец", "Ended": "закончено", "After": "после",
    "Bonus": "бонус", "Bonues": "бонусы", "Task": "задача", "Tasks": "задачи",
    "Difficulty": "сложность", "Occupy": "оккупация", "Occupied": "оккупированный",
    "Declare": "объявление", "Increase": "увеличение", "Inc": "увеличение",
    "Decrease": "уменьшение", "Dec": "уменьшение", "Leave": "выход",
    "Leaves": "выходит", "Liberate": "освобождение", "Liberation": "освобождение",
    "Release": "освобождение", "Released": "освобождено", "Releaseable": "освобождаемый",
    "Money": "деньги", "World": "мир", "Friendly": "дружественный",
    "Language": "язык", "Localization": "локализация", "Localize": "локализация",
    "Instantly": "мгновенно", "Give": "дать", "Vasalization": "вассализация",
    "Vassalization": "вассализация", "Vassalize": "вассализировать",
    "Minimap": "мини-карта", "Uncolonized": "неколонизированный",
    "Claimant": "претендент", "Claimants": "претенденты", "Zones": "зоны",
    "Zone": "зона", "Religion": "религия", "Religions": "религии", "Start": "старт",
    "Starting": "стартовый", "Golden": "золотой", "Goods": "товары",
    "Plague": "болезнь", "Plagues": "болезни", "Balance": "баланс",
    "Tutorial": "туториал", "Range": "диапазон", "Achievement": "достижение",
    "Achievements": "достижения", "Bundle": "бандл", "Chatbox": "чат",
    "Opinions": "мнения", "Opinion": "мнение", "Abadon": "отказ",
    "Civilize": "цивилизовать", "Uncivilized": "нецивилизованный",
    "Likelihood": "вероятность", "Center": "центр", "Frame": "рамка",
    "Checkbox": "чекбокс", "Elector": "курфюрст", "Emperor": "император",
    "Keyboard": "клавиатура", "Reflected": "отражено", "Rank": "рейтинг",
    "Speed": "скорость", "Gold": "золото", "Total": "всего", "Result": "результат",
    "Transparent": "прозрачный", "Income": "доход", "Unrest": "беспорядки",
    "Check": "проверка", "Double": "двойной", "Shift": "сдвиг",
    "Exist": "существование", "Controlled": "контролируемый", "Chance": "шанс",
    "Number": "число", "Watch": "стража", "Owner": "владелец", "Owners": "владельцы",
    "Another": "другой", "White": "белый", "Render": "рендер",
    "Annexation": "аннексия", "Holy": "священный", "Roman": "римский",
    "Empire": "империя", "Init": "инициализация", "About": "об игре",
    "Choose": "выбор", "Available": "доступный", "Preview": "предпросмотр",
    "Download": "загрузка", "Pixel": "пиксель", "Graphics": "графика",
    "Help": "справка", "Console": "консоль", "Animation": "анимация",
    "Anim": "анимация", "Uncivilized2": "нецивилизованный 2", "Send": "отправить",
    "Insult": "оскорбление", "Convert": "конвертация", "Connections": "связи",
    "Optimization": "оптимизация", "Scores": "очки", "Resolution": "разрешение",
    "Joined": "вступил", "Done": "готово", "Open": "открытый",
    "Rejected": "отклонено", "Sign": "подписать", "Status": "статус",
    "Highlighted": "подсвеченный", "Taxes": "налоги", "Taxation": "налогообложение",
    "Scrollable": "прокручиваемый", "Upkeep": "содержание", "Influence": "влияние",
    "Rival": "соперник", "Administration": "администрация", "Adm": "администрация",
    "Production": "производство", "State": "государство", "Communism": "коммунизм",
    "Democracy": "демократия", "Fascism": "фашизм", "Horde": "орда",
    "Tribal": "племенной", "Supp": "поддержка", "Day": "день", "Close": "закрыть",
    "Closed": "закрытый", "Agree": "согласен", "Disagree": "не согласен",
    "Liberity": "свобода", "Desire": "желание", "Success": "успех",
    "Sort": "сортировка", "Without": "без", "Interest": "интерес",
    "Animated": "анимированный", "Accept": "принять", "Down": "вниз",
    "Decline": "отклонение", "Two": "два", "Search": "поиск",
    "Descripted": "описанный", "Important": "важный", "Special": "специальный",
    "Play": "играть", "HRE": "СРИ", "Prince": "князь", "Princes": "князья",
    "Enabled": "включено", "Naval": "морской", "Invasion": "вторжение",
    "Personality": "личность", "Plans": "планы", "Embassy": "посольство",
    "Friends": "друзья", "Hated": "ненавидимые", "Sent": "отправлено",
    "Commands": "команды", "Config": "конфиг", "Drag": "перетаскивание",
    "Texture": "текстура", "Pop": "население", "Division": "дивизия",
    "Mountains": "горы", "Mountain": "гора", "Simple": "простой",
    "Around": "вокруг", "Link": "ссылка", "Handler": "обработчик",
    "Coordinates": "координаты", "Scroll": "скролл", "Suggest": "предложить",
    "Suggested": "предложено", "Interactive": "интерактивный",
    "Printamap": "печать карты", "Reverse": "обратный", "Test": "тест",
    "Timeline": "таймлайн", "Deny": "отклонить", "Over": "над",
    "Science": "наука", "Inflation": "инфляция", "Expire": "истекает",
    "Soon": "скоро", "Enemy": "враг", "Straves": "голод",
    "Supported": "поддержано", "Relocate": "переезд", "Revolt": "восстание",
    "Green": "зелёный", "Fogof": "туман", "Nationalities": "национальности",
    "Order": "порядок", "Slide": "слайд", "Zero": "ноль", "Cross": "крест",
    "Sounds": "звуки", "Free": "свободный", "Logo": "логотип",
    "Progress": "прогресс", "Toast": "уведомление", "Route": "маршрут",
    "Kick": "исключение", "Vote": "голосование", "Playlist": "плейлист",
    "Proclaim": "провозглашение", "Repay": "выплата", "Summary": "сводка",
    "Census": "перепись", "Founded": "основано", "Conquered": "завоёвано",
    "Dices": "кубики", "Chat": "чат", "Data2": "данные 2", "Data3": "данные 3",
    "Games2": "игры 2", "Options2": "опции 2", "Text2": "текст 2",
    "Title2": "заголовок 2", "Bot2": "низ 2", "Element2": "элемент 2",
    "Relations2": "отношения 2", "Diplomacy2": "дипломатия 2",
    "Pacts2": "пакты 2", "Pacts3": "пакты 3", "Animation2": "анимация 2",
    "Units2": "юниты 2", "Modes2": "режимы 2", "Migration": "миграция",
    "Migration2": "миграция 2", "Opinion2": "мнение 2", "Decline2": "отклонение 2",
    "Province2": "провинция 2", "Qou": "кво", "Provinces2": "провинции 2",
    "Colonization2": "колонизация 2", "Edit2": "редактирование 2",
    "Civilize2": "цивилизовать 2", "Timelapse2": "таймлапс 2",
}

# Целые «остатки» имён -> аккуратный русский текст (после отрезания префикса).
PHRASES = {
    "ProvinceInfo": "информация о провинции",
    "ProvinceInfo2": "информация о провинции (2)",
    "CivInfo": "информация о цивилизации",
    "CivInfo_Stats": "информация о цивилизации: статистика",
    "WarDetails": "детали войны",
    "CivStats": "статистика цивилизации",
    "Main": "главное меню",
    "Games": "список партий",
    "About": "об игре",
    "Help": "справка",
    "Settings": "настройки",
    "LoadGame": "загрузка партии",
    "CreateNewGame": "создание новой игры",
    "ChooseScenario": "выбор сценария",
    "InGame": "в игре",
    "InGame_Technology": "технологии",
    "InGame_Build": "строительство",
    "InGame_Budget": "бюджет",
    "InGame_Diplomacy": "дипломатия",
    "InGame_Army": "армия",
    "InGame_Recruit": "набор войск",
    "InGame_Move": "перемещение войск",
    "InGame_ProvinceInfo": "информация о провинции",
    "InGame_WorldPopulation": "население мира",
    "InGame_WarDetails": "детали войны",
    "InGame_GraphManager": "графики",
    "InGame_FlagAction": "действия с цивилизацией",
    "InGame_CivInfo": "информация о цивилизации",
    "InGame_Religion": "религия",
    "InGame_Plague": "болезни",
    "InGame_Event": "события",
    "InGame_Alliance": "альянсы",
    "InGame_Union": "унии",
    "InGame_Vassal": "вассалы",
    "InGame_Trade": "торговля",
    "InGame_Stats": "статистика",
    "InGame_Timeline": "таймлайн",
    "InGame_Rank": "рейтинг",
    "InGame_Speed": "скорость игры",
    "InGame_Minimap": "мини-карта",
    "InGame_MapModes": "режимы карты",
    "InGame_History": "история",
    "InGame_Ideology": "идеология",
    "InGame_Wonder": "чудеса света",
    "InGame_City": "города",
    "InGame_Capital": "столица",
    "InGame_Rebels": "повстанцы",
    "InGame_Happiness": "счастье",
    "InGame_Stability": "стабильность",
    "InGame_Development": "развитие",
    "InGame_Population": "население",
    "InGame_Economy": "экономика",
    "InGame_Income": "доход",
    "InGame_Taxation": "налоги",
    "InGame_Supply": "снабжение",
    "InGame_Border": "границы",
    "InGame_Cores": "коры",
    "InGame_FogOfWar": "туман войны",
    "InGame_Outliner": "обзор",
}


def humanize(name):
    return " ".join(re.findall(r"[A-Z]{2,}(?![a-z])|[A-Z][a-z0-9]*|[a-z0-9]+", name))


def translate_name(rest):
    """ProvinceInfo -> 'информация о провинции' (best effort)."""
    if not rest:
        return ""
    if rest in PHRASES:
        return PHRASES[rest]
    tokens = re.findall(r"[A-Z]{2,}(?![a-z])|[A-Z][a-z0-9]*|[a-z0-9]+", rest)
    out = []
    for tok in tokens:
        if tok in STOPWORDS:
            continue
        out.append(WORDS.get(tok, tok))
    return " ".join(out) if out else humanize(rest)


def guess_description(name):
    if name in DESCRIPTIONS:
        return DESCRIPTIONS[name]
    for pattern, prefix in PREFIXES:
        m = re.match(pattern, name)
        if m:
            rest = name[m.end():].lstrip("_")
            text = translate_name(rest) or translate_name(name)
            return prefix + text
    return translate_name(name)


def parse_file(path):
    with open(path, "r", encoding="utf-8", errors="replace") as fh:
        text = fh.read()
    name = os.path.splitext(os.path.basename(path))[0]
    m = TYPE_RE.search(text)
    kind = m.group(1) if m else "?"
    tname = m.group(2) if m else name
    header = text[m.start():m.start() + 400] if m else ""
    ext = EXTENDS_RE.search(header)
    imp = IMPLEMENTS_RE.search(header)
    fields = list(dict.fromkeys(f.group(2) for f in FIELD_RE.finditer(text)))
    methods = list(dict.fromkeys(mm.group(2) for mm in METHOD_RE.finditer(text)))
    return {
        "file": os.path.basename(path),
        "name": name,
        "type": kind,
        "type_name": tname,
        "package": (PACKAGE_RE.search(text).group(1) if PACKAGE_RE.search(text) else ""),
        "extends": ext.group(1).strip() if ext else "",
        "implements": imp.group(1).strip() if imp else "",
        "lines": text.count("\n") + 1,
        "bytes": os.path.getsize(path),
        "n_fields": len(fields),
        "n_methods": len(methods),
        "fields": fields[:40],
        "methods": methods[:40],
        "description": guess_description(name),
    }


def family_of(name):
    # менеджеры подсистем — всё, что заканчивается на Manager (кроме UI-классов)
    if name.endswith("Manager") and not re.match(r"^(Menu|Button|Text|Slider|Message|Event_)", name):
        return "Менеджеры подсистем"
    # сериализуемые срезы состояния
    if name.endswith("GameData"):
        return "Структуры данных (GameData)"
    for fam, pattern in FAMILIES:
        if re.search(pattern, name):
            return fam
    return "Прочее"


def anchor(fam):
    return re.sub(r"[^\wа-яА-ЯёЁ -]", "", fam).strip().replace(" ", "-").lower()


def main():
    root = os.path.abspath(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
    os.chdir(root)
    if not os.path.isdir(SRC_ROOT):
        sys.exit(f"source directory not found: {SRC_ROOT}")

    paths = sorted(
        (os.path.join(SRC_ROOT, f) for f in os.listdir(SRC_ROOT) if f.endswith(".java")),
        key=lambda p: os.path.basename(p),
    )
    if os.path.isdir(DESKTOP_ROOT):
        paths += sorted(
            (os.path.join(DESKTOP_ROOT, f) for f in os.listdir(DESKTOP_ROOT) if f.endswith(".java")),
            key=lambda p: os.path.basename(p),
        )

    entries = [parse_file(p) for p in paths]
    for e in entries:
        e["family"] = family_of(e["name"])

    os.makedirs(DOCS, exist_ok=True)
    with open(os.path.join(DOCS, "files.json"), "w", encoding="utf-8") as fh:
        json.dump(entries, fh, ensure_ascii=False, indent=1)

    families = {}
    for e in entries:
        families.setdefault(e["family"], []).append(e)

    order = [
        "Ядро и точка входа",
        "Глобальное состояние",
        "Менеджеры подсистем",
        "Модель: провинции",
        "Модель: цивилизации",
        "Модель: регионы и континенты",
        "Модель: армия и приказы",
        "Модель: экономика и города",
        "Модель: дипломатия и войны",
        "Модель: события",
        "Модель: история",
        "Модель: задачи и планы ИИ",
        "Модель: лидеры",
        "Модель: прочее состояние",
        "Сохранения и данные",
        "Структуры данных (GameData)",
        "Сохранения и данные",
        "Игровой цикл и рендер",
        "ИИ",
        "Редактор карт и сценариев",
        "Steam и платформа",
        "UI: экраны",
        "UI: кнопки",
        "UI: элементы",
        "Прочее",
    ]
    order = [f for f in dict.fromkeys(order) if f in families]
    for f in sorted(families):  # на случай новых семейств
        if f not in order:
            order.append(f)

    out = []
    out.append("# Каталог файлов Age of Civilizations II (декомпиляция 1.4)\n")
    out.append(
        "Файл сгенерирован автоматически: `python tools/gen_file_index.py`.\n"
        f"Всего исходников: **{len(entries)}**, строк: **{sum(e['lines'] for e in entries):,}**.\n\n"
        "Столбец «Назначение» — краткая расшифровка роли файла; «Методы» — ключевые методы "
        "(по ним видно, что класс реально делает). Полные списки полей/методов — в `docs/files.json`.\n\n"
        "См. также [`ARCHITECTURE.md`](ARCHITECTURE.md) — карта подсистем и рецепты «куда идти, если…».\n"
    )
    out.append("## Содержание\n")
    for fam in order:
        out.append(f"- [{fam}](#{anchor(fam)}) — {len(families[fam])}")
    out.append("")

    for fam in order:
        items = sorted(families[fam], key=lambda e: (-e["bytes"], e["name"]))
        out.append(f"\n## {fam}\n")
        out.append(f"Файлов: **{len(items)}**\n")
        out.append("| Файл | Строк | Базовый класс | Назначение | Ключевые методы |")
        out.append("|---|---:|---|---|---|")
        for e in items:
            base = e["extends"] or e["implements"] or "—"
            desc = e["description"].replace("|", "\\|")
            meths = ", ".join(e["methods"][:4]).replace("|", "\\|")
            link = f"../{e['package'].replace('.', '/')}/{e['file']}" if e["package"] else f"../{e['file']}"
            out.append(f"| [`{e['file']}`]({link}) | {e['lines']} | {base} | {desc} | {meths} |")
        out.append("")

    with open(os.path.join(DOCS, "FILES.md"), "w", encoding="utf-8") as fh:
        fh.write("\n".join(out))

    print(f"ok: {len(entries)} files -> docs/files.json, docs/FILES.md")


if __name__ == "__main__":
    main()
