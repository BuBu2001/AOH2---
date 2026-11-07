# Каталог файлов Age of Civilizations II (декомпиляция 1.4)

Файл сгенерирован автоматически: `python tools/gen_file_index.py`.
Всего исходников: **1552**, строк: **343,978**.

Столбец «Назначение» — краткая расшифровка роли файла; «Методы» — ключевые методы (по ним видно, что класс реально делает). Полные списки полей/методов — в `docs/files.json`.

См. также [`ARCHITECTURE.md`](ARCHITECTURE.md) — карта подсистем и рецепты «куда идти, если…».

## Содержание

- [Ядро и точка входа](#ядро-и-точка-входа) — 8
- [Глобальное состояние](#глобальное-состояние) — 1
- [Менеджеры подсистем](#менеджеры-подсистем) — 31
- [Модель: провинции](#модель-провинции) — 18
- [Модель: цивилизации](#модель-цивилизации) — 9
- [Модель: регионы и континенты](#модель-регионы-и-континенты) — 3
- [Модель: армия и приказы](#модель-армия-и-приказы) — 33
- [Модель: экономика и города](#модель-экономика-и-города) — 16
- [Модель: дипломатия и войны](#модель-дипломатия-и-войны) — 20
- [Модель: события](#модель-события) — 96
- [Модель: история](#модель-история) — 17
- [Модель: задачи и планы ИИ](#модель-задачи-и-планы-ии) — 6
- [Модель: прочее состояние](#модель-прочее-состояние) — 45
- [Сохранения и данные](#сохранения-и-данные) — 14
- [Структуры данных (GameData)](#структуры-данных-gamedata) — 66
- [Игровой цикл и рендер](#игровой-цикл-и-рендер) — 55
- [ИИ](#ии) — 55
- [Редактор карт и сценариев](#редактор-карт-и-сценариев) — 29
- [Steam и платформа](#steam-и-платформа) — 1
- [UI: экраны](#ui-экраны) — 584
- [UI: кнопки](#ui-кнопки) — 259
- [UI: элементы](#ui-элементы) — 186


## Ядро и точка входа

Файлов: **8**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`AoCGame.java`](../age/of/civilizations2/jakowski/lukasz/AoCGame.java) | 1400 | ApplicationAdapter | Главный класс приложения (libGDX ApplicationAdapter + InputProcessor). Читает config.ini, создаёт камеру/viewport/шейдеры, гоняет update+render, обрабатывает ввод и скролл карты. Точка сборки подсистем через CFG. | updateRequestRendering, getIOSSafeAreaInsets, getIOSSafeAreaInsets_LeftRight, create |
| [`Keyboard.java`](../age/of/civilizations2/jakowski/lukasz/Keyboard.java) | 659 | SliderMenu | Экранная клавиатура/ввод текста (наследует SliderMenu, т.к. это всплывающая панель). | updateLanguage, draw, actionElement, shiftAction |
| [`DesktopLauncher.java`](../age/of/civilizations2/jakowski/lukasz/desktop/DesktopLauncher.java) | 97 | — | Точка входа desktop-сборки: парсит config.ini, настраивает LwjglApplicationConfiguration, грузит SteamAPI, запускает AoCGame. | main |
| [`Touch.java`](../age/of/civilizations2/jakowski/lukasz/Touch.java) | 88 | — | Абстракция ввода: позиция мыши/тача, кнопки, скролл-дельты, эмуляция тача на desktop. | setMousePosXY, getMousePosX, getMousePosY, actionDown |
| [`ConfigINI.java`](../age/of/civilizations2/jakowski/lukasz/ConfigINI.java) | 79 | — | Чтение/запись config.ini: WIDTH/HEIGHT/FULLSCREEN/ANTIALIASING/VSYNC/LANDSCAPE/UISCALE. | readConfig, saveConfig |
| [`InitGame.java`](../age/of/civilizations2/jakowski/lukasz/InitGame.java) | 40 | — | инициализация игра | Init |
| [`DesktopLinkHandler.java`](../age/of/civilizations2/jakowski/lukasz/desktop/DesktopLinkHandler.java) | 12 | LinkHandler | Desktop-реализация LinkHandler — открывает URL в браузере через Gdx.net.openURI. | openPage |
| [`LinkHandler.java`](../age/of/civilizations2/jakowski/lukasz/LinkHandler.java) | 6 | — | Интерфейс открытия внешних ссылок (отдельные реализации под desktop/mobile). |  |


## Глобальное состояние

Файлов: **1**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`CFG.java`](../age/of/civilizations2/jakowski/lukasz/CFG.java) | 6362 | — | Глобальный контекст игры: статические ссылки на все подсистемы (game, map, menuManager, settingsManager, langManager, soundsManager, viewsManager, oAI, менеджеры), пути к ассетам, шрифты/цвета, утилиты рендера текста, isDesktop/isAndroid/isIOS, setRender_*. | getLukaszJakowski, getLukaszJakowskiGames, isAndroid, isIOS |


## Менеджеры подсистем

Файлов: **31**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`ViewsManager.java`](../age/of/civilizations2/jakowski/lukasz/ViewsManager.java) | 8174 | — | Режимы отображения карты (политический, население, экономика, дипломатия, армия, ~35 режимов) + легенды и hover-подсказки для каждого. | addViewToTheGame, setActiveViewID, disableAllViews, updateMaxPopulation |
| [`TutorialManager.java`](../age/of/civilizations2/jakowski/lukasz/TutorialManager.java) | 4537 | — | Пошаговый туториал и его шаги. | startTutorial, draw, updateDrawTutorial, drawLine |
| [`DiplomacyManager.java`](../age/of/civilizations2/jakowski/lukasz/DiplomacyManager.java) | 3797 | — | Дипломатические действия и проверки: войны, союзы, проход войск, мир, вассалы, унии. | getLikelihoodScore, worldRecations, sendTransferControl, acceptTransferControl |
| [`Map_TouchManager.java`](../age/of/civilizations2/jakowski/lukasz/Map_TouchManager.java) | 1431 | — | Логика мыши/тача по карте: выбор провинции, drag, зум, клики по армиям. | updateEnableScaling, actionDown, actionMove, actionMoveMap |
| [`BuildingsManager.java`](../age/of/civilizations2/jakowski/lukasz/BuildingsManager.java) | 1370 | — | Строения: типы, стоимость, эффекты, лимиты и подсчёт по цивилизации. | getFort_MaxLevel, getFort_MaxLevel_CanBuild, getFort_Name, getFort_BuildCost |
| [`EventsManager.java`](../age/of/civilizations2/jakowski/lukasz/EventsManager.java) | 776 | — | Менеджер событий. | checkEvents, checkCondtsitionsAndTryRun, tryRunEvent, runEvent_Tag |
| [`SaveManager.java`](../age/of/civilizations2/jakowski/lukasz/SaveManager.java) | 808 | — | Сохранение/загрузка партии (JSON), автосейв, миграция версий Save_GameData_1..11, статистика, сейвы в saves/games/. | newGame, trySaveGame, gameWillBeSavedInNextTurn, gameWillBeSavedInThisTurn |
| [`FlagManager.java`](../age/of/civilizations2/jakowski/lukasz/FlagManager.java) | 608 | — | Генерация и кэш флагов цивилизаций/союзов/уний. | drawFlag, drawFlag_FlagFrameSize, drawDivision, drawDivision_FlagFrameSize |
| [`Ideologies_Manager.java`](../age/of/civilizations2/jakowski/lukasz/Ideologies_Manager.java) | 515 | — | идеологии менеджер | getChangeGovernmentCost, loadIdeologies, getRealTag, getIdeologyID |
| [`TimelapseManager.java`](../age/of/civilizations2/jakowski/lukasz/TimelapseManager.java) | 414 | — | Таймлапс: снимки карты по ходам и их проигрывание. | updateTime, timePasted, getRequiredTime, getTimePerc |
| [`Pallet_Manager.java`](../age/of/civilizations2/jakowski/lukasz/Pallet_Manager.java) | 318 | — | палитра менеджер | updatePalletsOfCivsColorsTags, loadCivilizationsPaletteOfColors, loadCivilizationPalletColor, loadCivilizationStandardColors |
| [`ServiceRibbon_Manager.java`](../age/of/civilizations2/jakowski/lukasz/ServiceRibbon_Manager.java) | 420 | — | Лента сервисов: менеджер | loadStatistics_Civ, saveStatistics_Civ, check_RequestTurns, getRequestTurns |
| [`PlagueManager.java`](../age/of/civilizations2/jakowski/lukasz/PlagueManager.java) | 335 | — | Болезни: распространение, смертность, затухание. | runPlagues, startDisease, loadPlagues, getPlague |
| [`SoundsManager.java`](../age/of/civilizations2/jakowski/lukasz/SoundsManager.java) | 333 | — | Музыка и звуки: плейлист, громкости, ID эффектов, console/start/defeat музыка. | randomizePlayList, loadNextMusic, playConsoleMusic, playStartMusic |
| [`TerrainTypesManager.java`](../age/of/civilizations2/jakowski/lukasz/TerrainTypesManager.java) | 234 | — | Менеджер типов местности. | loadTerrainTypes, addSea, getTerrainTypeID, saveTerrainData |
| [`SkillsManager.java`](../age/of/civilizations2/jakowski/lukasz/SkillsManager.java) | 139 | — | навыки менеджер | canAdd_PopGrowth, add_PopGrowth, canAdd_EcoGrowth, add_EcoGrowth |
| [`LanguageManager.java`](../age/of/civilizations2/jakowski/lukasz/LanguageManager.java) | 203 | — | I18N-бандлы (основной, цивилизации, экраны загрузки), выбор локали, режим вывода ключей. | updateKeyOutput, initCivsBundle, initLoadingBundle, dispose |
| [`HolyRomanEmpire_Manager.java`](../age/of/civilizations2/jakowski/lukasz/HolyRomanEmpire_Manager.java) | 155 | — | священный римский империя менеджер | initHolyRomanEmpire, loadHolyRomanEmpire_ScenarioData, getHRE, addProvince |
| [`TechnologyManager.java`](../age/of/civilizations2/jakowski/lukasz/TechnologyManager.java) | 135 | — | Дерево технологий: уровни, стоимость, эффекты. | updateAverageTechLevel, getResearch_NextLevel, updateResearch, updateReseatchOfCiv |
| [`LinesManager.java`](../age/of/civilizations2/jakowski/lukasz/LinesManager.java) | 152 | — | Менеджер линий (движение армий, границы). | loadNext, loadHighlight, loadMigrate, loadMoveLand |
| [`TradeZones_Manager.java`](../age/of/civilizations2/jakowski/lukasz/TradeZones_Manager.java) | 147 | — | торговля зоны менеджер | loadTradeZones_Age, loadTradeZones_All, isInAnotherTradeZone, clearData |
| [`EditorManager.java`](../age/of/civilizations2/jakowski/lukasz/EditorManager.java) | 155 | — | Состояние редактора: активный инструмент, кисть, undo-стек, палитры, режим редактора. | addEditor, draw, keyDown, keyUp |
| [`HistoryManager.java`](../age/of/civilizations2/jakowski/lukasz/HistoryManager.java) | 120 | — | История партии: события, данные для графиков. | updateLanguage, buildHistoryDates, clearHistoryDates, haveHistory |
| [`VicotryManager.java`](../age/of/civilizations2/jakowski/lukasz/VicotryManager.java) | 101 | — | победа менеджер | getDefault_VcitoryTechnology, checkVictoryConditions, updateVictoryConditions, domination_UpdateNumOfCivs |
| [`ReligionManager.java`](../age/of/civilizations2/jakowski/lukasz/ReligionManager.java) | 92 | — | Религии: список, распространение, эффекты. | loadReligions, saveReligionData, getReligionName, getReligionTag |
| [`UnionsManager.java`](../age/of/civilizations2/jakowski/lukasz/UnionsManager.java) | 93 | — | Менеджер уний. | saveUnions, getUnionTag |
| [`SettingsManager.java`](../age/of/civilizations2/jakowski/lukasz/SettingsManager.java) | 57 | Serializable | Сериализуемые настройки игрока: язык, размеры шрифтов, громкости, альфы провинций, границы, автозащита, цвета имён цивилизаций, режимы рендера. | updateCitiesFontScale |
| [`UnionFlagsToGenerate_Manager.java`](../age/of/civilizations2/jakowski/lukasz/UnionFlagsToGenerate_Manager.java) | 75 | — | Флаги уний: менеджер | generateFlags, loadImages, addFlagToLoad |
| [`RandomGame_Manager.java`](../age/of/civilizations2/jakowski/lukasz/RandomGame_Manager.java) | 75 | — | случайный игра менеджер | addPlayer, removePlayer, getPlayer, getPlayersSize |
| [`Wonders_Manager.java`](../age/of/civilizations2/jakowski/lukasz/Wonders_Manager.java) | 35 | — | Чудеса света: постройка, эффекты, размещение. | buildWondersProvinceID |
| [`ImageManager.java`](../age/of/civilizations2/jakowski/lukasz/ImageManager.java) | 38 | — | Реестр текстур по ID: addImage/getImage, фильтры и wrap. | addImage, getImage, getImagesSize |


## Модель: провинции

Файлов: **18**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Province.java`](../age/of/civilizations2/jakowski/lukasz/Province.java) | 5282 | — | Провинция: владелец и оккупант, население/экономика/развитие, армии по цивилизациям, соседи (суша/море), участки границ, полигон (точки), города, горы, чудеса, стабильность, опасность, потенциал, рост населения. | getBalance_LastTurn, loadProvinceInfo, checkLandBySeaProvinceBorders, checkSeaBySeaProvinceBorders |
| [`Province_Border.java`](../age/of/civilizations2/jakowski/lukasz/Province_Border.java) | 913 | — | Участок границы провинции для отрисовки (land-by-land / land-by-sea / sea-by-sea). | updateDrawProvinceBorder, updateDrawProvinceBorder_OwnerAnimation, updateDrawProvinceBorder_Inner, updateDrawProvinceBorderSeaBySea |
| [`Province_Core.java`](../age/of/civilizations2/jakowski/lukasz/Province_Core.java) | 169 | Serializable | Провинция: кор | addNewCore, removeCore, increaseOwnership, clearData |
| [`Province_Army.java`](../age/of/civilizations2/jakowski/lukasz/Province_Army.java) | 149 | Serializable | Провинция: армия | updateArmyWidth_Just, updateArmyWidth, getCivID, setCivID |
| [`Province_Population.java`](../age/of/civilizations2/jakowski/lukasz/Province_Population.java) | 83 | Serializable | Провинция: население | updatePopulationOfProvince, getPopulationID, getPopulationOfCivID, setPopulationOfCivID |
| [`Province_Animation_MoveUnits2.java`](../age/of/civilizations2/jakowski/lukasz/Province_Animation_MoveUnits2.java) | 64 | — | Провинция: анимация перемещение юниты 2 | resetData, update, updateColor, getLineOffset |
| [`Province_Animation2.java`](../age/of/civilizations2/jakowski/lukasz/Province_Animation2.java) | 74 | — | Анимация подсветки провинции. | update, resetAnimationData, getAlpha, getBorderAlpha |
| [`Province_GameData2.java`](../age/of/civilizations2/jakowski/lukasz/Province_GameData2.java) | 67 | Serializable | Провинция: игра данные 2 | getPointsX, getPointsY, getLevelOfPort, getProvinceBorder |
| [`Province_Port_Center.java`](../age/of/civilizations2/jakowski/lukasz/Province_Port_Center.java) | 36 | — | Провинция: порт центр | draw, getShiftX, getShiftY |
| [`Province_Border_Line.java`](../age/of/civilizations2/jakowski/lukasz/Province_Border_Line.java) | 32 | — | Провинция: граница линия | getPosX, getPosY, getWidth, getAngle |
| [`Province_ArmyBox.java`](../age/of/civilizations2/jakowski/lukasz/Province_ArmyBox.java) | 32 | — | Провинция: армия бокс | getStartPosX, getStartPosY, getEndPosX, getEndPosY |
| [`Province_Population_Nationalities.java`](../age/of/civilizations2/jakowski/lukasz/Province_Population_Nationalities.java) | 27 | Serializable | Провинция: население национальности | getCivID, getPopulation, setPopulaton |
| [`Province_SupportRebels.java`](../age/of/civilizations2/jakowski/lukasz/Province_SupportRebels.java) | 17 | Serializable | Провинция: поддержка повстанцы |  |
| [`Province_Info_GameData3.java`](../age/of/civilizations2/jakowski/lukasz/Province_Info_GameData3.java) | 14 | Serializable | Провинция: информация игра данные 3 |  |
| [`Province_FogofWar.java`](../age/of/civilizations2/jakowski/lukasz/Province_FogofWar.java) | 14 | — | Провинция: туман война | setVisible, getIsVisible |
| [`Province_SupportRebels_Help.java`](../age/of/civilizations2/jakowski/lukasz/Province_SupportRebels_Help.java) | 12 | — | Провинция: поддержка повстанцы справка |  |
| [`Province_DrawArmy.java`](../age/of/civilizations2/jakowski/lukasz/Province_DrawArmy.java) | 8 | — | Провинция: рисование армия |  |
| [`Province_Border_Draw.java`](../age/of/civilizations2/jakowski/lukasz/Province_Border_Draw.java) | 8 | — | Провинция: граница рисование |  |


## Модель: цивилизации

Файлов: **9**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Civilization.java`](../age/of/civilizations2/jakowski/lukasz/Civilization.java) | 3082 | — | Цивилизация: провинции и армии, бюджет/доходы/расходы, счастье, стабильность, развитие, идеология, регионы, отношения, флаг, теги, ИИ-стиль, счётчики строений. | initCivilization, buildCivPersonality_MoreOften, buildCivPersonality, buildCivPersonality_Colonization |
| [`Civilization_Region.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Region.java) | 1057 | — | Регион внутри цивилизации (группировка провинций для бонусов/ИИ). | checkRegionBordersWithEnemy, addProvince, removeProvinceID, removeProvince |
| [`Civilization_Region_Active.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Region_Active.java) | 68 | — | Цивилизация: регион активный | getCivID, isActive_RegionID, addRegion, removeRegion |
| [`Civilization_GameData3.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_GameData3.java) | 56 | Serializable | Цивилизация: игра данные 3 | setCivTag, getCivTag, setR, getR |
| [`Civilization_SentMessages.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_SentMessages.java) | 17 | Serializable | Цивилизация: отправлено сообщения |  |
| [`Civilization_ClosedEmbassy.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_ClosedEmbassy.java) | 15 | Serializable | Цивилизация: закрытый посольство |  |
| [`Civilization_Colonies.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Colonies.java) | 14 | Serializable | Цивилизация: колонии |  |
| [`Civilization_Region_Style.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Region_Style.java) | 8 | — | Цивилизация: регион стиль | updatePB |
| [`Civilization_Color.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Color.java) | 11 | Serializable | Цивилизация: цвет |  |


## Модель: регионы и континенты

Файлов: **3**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Region.java`](../age/of/civilizations2/jakowski/lukasz/Region.java) | 90 | — | Регион карты (группа провинций) для бонусов и ИИ. | addProvince, removeProvince, buildRegionBounds, getProvince |
| [`Map_Continents.java`](../age/of/civilizations2/jakowski/lukasz/Map_Continents.java) | 65 | — | Пакеты континентов (загрузка/хранение). | loadContinents, getName, getColor, getContinentsSize |
| [`Map_Regions.java`](../age/of/civilizations2/jakowski/lukasz/Map_Regions.java) | 56 | — | Пакеты регионов (загрузка/хранение). | loadRegions, getName, getColor, getRegionsSize |


## Модель: армия и приказы

Файлов: **33**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Commands.java`](../age/of/civilizations2/jakowski/lukasz/Commands.java) | 860 | — | Приказы игрока (движение, набор, стройка, дипломатия) с валидацией. | addMessage, execute, cheatMess, IllegalCommand |
| [`CivArmyMission_ExpandNeutralProvince.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_ExpandNeutralProvince.java) | 329 | CivArmyMission | Миссия армии ИИ: экспансия нейтральный провинция | action, onRemove |
| [`CivArmyMission_NavalInvasion.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_NavalInvasion.java) | 299 | CivArmyMission | Миссия армии ИИ: морской вторжение | canMakeAction, action, action_RecruitArmy, action_BuildPort |
| [`CivArmyMission_ColonizeProvince.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_ColonizeProvince.java) | 324 | CivArmyMission | Миссия армии ИИ: колонизация провинция | generateColonizeData, canMakeAction, action, action_RecruitArmy |
| [`RegroupArmy_Data.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data.java) | 237 | Serializable | Состояние операции перегруппировки. | continueMovingArmy, buildRoute, buildPath, clearWas |
| [`MoveUnits_Line.java`](../age/of/civilizations2/jakowski/lukasz/MoveUnits_Line.java) | 210 | — | Линия перемещения армии (логика + отрисовка). | updateColor, updateMovingLine, drawLine, drawLine2 |
| [`RegroupArmy_Data_PortToBuild.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_PortToBuild.java) | 132 | RegroupArmy_Data | Перегруппировка: данные порт строительство | buildRoute, buildPath |
| [`Selected_Provinces.java`](../age/of/civilizations2/jakowski/lukasz/Selected_Provinces.java) | 193 | — | Выделенные провинции и групповые операции. | update, updateProvinceAlpha, updateBorderAlpha, updateColor |
| [`Mountain.java`](../age/of/civilizations2/jakowski/lukasz/Mountain.java) | 126 | Serializable | Горный пик на карте. | draw, drawImage, getName, setName |
| [`RegroupArmy_Data_Migrate.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_Migrate.java) | 92 | RegroupArmy_Data | Перегруппировка: данные миграция | buildRoute, buildPath |
| [`CivArmyMission_Expand_BuildPort.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_Expand_BuildPort.java) | 88 | CivArmyMission | Миссия армии ИИ: экспансия строительство порт | canMakeAction, action, action_BuildPort, lockTreasury_Port |
| [`Move_Units.java`](../age/of/civilizations2/jakowski/lukasz/Move_Units.java) | 101 | — | Движение: юниты | draw, draw2, getNumOfUnits, setNumOfUnits |
| [`CivArmyMission_ColonizeProvince_Just.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_ColonizeProvince_Just.java) | 78 | CivArmyMission | Миссия армии ИИ: колонизация провинция только | generateColonizeData, canMakeAction, action, lockTreasury |
| [`CivArmyMission_PrepareForWar.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_PrepareForWar.java) | 48 | CivArmyMission | Миссия армии ИИ: подготовка война | canMakeAction, action |
| [`CivArmyMission_RegroupAfterRecruitment_War_Double.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_RegroupAfterRecruitment_War_Double.java) | 47 | CivArmyMission | Миссия армии ИИ: перегруппировка после набор война двойной | canMakeAction, action |
| [`CivArmyMission_ExpandNeutral_Check.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_ExpandNeutral_Check.java) | 47 | CivArmyMission | Миссия армии ИИ: экспансия нейтральный проверка | canMakeAction, action |
| [`CivArmyMission_RegroupAfterRecruitment_War.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_RegroupAfterRecruitment_War.java) | 47 | CivArmyMission | Миссия армии ИИ: перегруппировка после набор война | canMakeAction, action |
| [`CivArmyMission_RegroupAfterRecruitment.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_RegroupAfterRecruitment.java) | 47 | CivArmyMission | Миссия армии ИИ: перегруппировка после набор | canMakeAction, action |
| [`MoveUnits_Line_Highlighted.java`](../age/of/civilizations2/jakowski/lukasz/MoveUnits_Line_Highlighted.java) | 55 | MoveUnits_Line | Перемещение войск: линия подсвеченный | updateColor, getFlipX, getMoveSrcX, getImageID |
| [`RegroupArmy_Data_ToTheFront_Double.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_ToTheFront_Double.java) | 42 | RegroupArmy_Data_ToTheFront | Перегруппировка: данные фронт двойной | continueMovingArmy |
| [`MoveUnits_TurnData.java`](../age/of/civilizations2/jakowski/lukasz/MoveUnits_TurnData.java) | 57 | — | Перемещение войск: ход данные | getMoveUnitsSize, addMoveUnits, getMoveUnits, getMoveUnits_TotalNumOfUnits |
| [`MoveUnits_Line_Migrate.java`](../age/of/civilizations2/jakowski/lukasz/MoveUnits_Line_Migrate.java) | 47 | MoveUnits_Line | Перемещение войск: линия миграция | updateColor, getFlipX, getMoveSrcX, getImageID |
| [`MoveUnits_Line_Current.java`](../age/of/civilizations2/jakowski/lukasz/MoveUnits_Line_Current.java) | 26 | MoveUnits_Line | Перемещение войск: линия текущий | updateColor |
| [`MoveUnits_Line_MigrateHighlighted.java`](../age/of/civilizations2/jakowski/lukasz/MoveUnits_Line_MigrateHighlighted.java) | 31 | MoveUnits_Line_Migrate | Перемещение войск: линия миграция подсвеченный | updateColor |
| [`RecruitArmy_Request.java`](../age/of/civilizations2/jakowski/lukasz/RecruitArmy_Request.java) | 42 | — | набор армия запрос | getArmy, setArmy, getProvinceID, setProvinceID |
| [`RegroupArmy_Data_ToTheFront.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_ToTheFront.java) | 29 | RegroupArmy_Data | Перегруппировка: данные фронт | continueMovingArmy |
| [`Move_Units_Plunder.java`](../age/of/civilizations2/jakowski/lukasz/Move_Units_Plunder.java) | 31 | — | Движение: юниты грабёж | getFromProvinceID, getNumOfUnits, setNumOfUnits, getUnitsWidth |
| [`CivArmyMission.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission.java) | 30 | Serializable | Миссия армии ИИ: цель, маршрут, тип (атака/оборона/подкрепление). | canMakeAction, action, prepareData, onRemove |
| [`RegroupArmy_Data_AtWar.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_AtWar.java) | 17 | RegroupArmy_Data | Перегруппировка: данные война | continueMovingArmy |
| [`RegroupArmy_Data_ExpandCheck.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_ExpandCheck.java) | 15 | RegroupArmy_Data | Перегруппировка: данные экспансия проверка | continueMovingArmy |
| [`RegroupArmy_Data_AtPeace.java`](../age/of/civilizations2/jakowski/lukasz/RegroupArmy_Data_AtPeace.java) | 13 | RegroupArmy_Data | Перегруппировка: данные мир | continueMovingArmy |
| [`Move_Units_JustDraw.java`](../age/of/civilizations2/jakowski/lukasz/Move_Units_JustDraw.java) | 12 | — | Движение: юниты только рисование |  |
| [`CivArmyMission_Type.java`](../age/of/civilizations2/jakowski/lukasz/CivArmyMission_Type.java) | 11 | — | Миссия армии ИИ: тип |  |


## Модель: экономика и города

Файлов: **16**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`City.java`](../age/of/civilizations2/jakowski/lukasz/City.java) | 145 | Serializable | Город: уровень, население, отрисовка, бонусы. | draw, drawInLine, drawCityImage_Level, updateCityNameWidth |
| [`TradeZone.java`](../age/of/civilizations2/jakowski/lukasz/TradeZone.java) | 133 | — | Торговая зона. | loadName, addProvinceID, isConnected, removeProvinceID |
| [`CivInvest_Development.java`](../age/of/civilizations2/jakowski/lukasz/CivInvest_Development.java) | 19 | Serializable | цивилизация инвестиции развитие |  |
| [`TradeRequest_List.java`](../age/of/civilizations2/jakowski/lukasz/TradeRequest_List.java) | 18 | Serializable | Торговый запрос: список |  |
| [`CivInvest.java`](../age/of/civilizations2/jakowski/lukasz/CivInvest.java) | 19 | Serializable | Инвестиции. |  |
| [`Construction_GameData_Workshop.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Workshop.java) | 16 | Construction_GameData | Строительство: игра данные мастерская | onConstructed |
| [`Construction_GameData_Armoury.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Armoury.java) | 16 | Construction_GameData | Строительство: игра данные арсенал | onConstructed |
| [`Construction_GameData_Library.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Library.java) | 16 | Construction_GameData | Строительство: игра данные библиотека | onConstructed |
| [`Construction_GameData_Supply.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Supply.java) | 16 | Construction_GameData | Строительство: игра данные снабжение | onConstructed |
| [`Construction_GameData_Tower.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Tower.java) | 16 | Construction_GameData | Строительство: игра данные башня | onConstructed |
| [`Construction_GameData_Farm.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Farm.java) | 16 | Construction_GameData | Строительство: игра данные ферма | onConstructed |
| [`Construction_GameData_Fort.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData_Fort.java) | 16 | Construction_GameData | Строительство: игра данные форт | onConstructed |
| [`CivFestival.java`](../age/of/civilizations2/jakowski/lukasz/CivFestival.java) | 15 | Serializable | Праздник цивилизации. |  |
| [`ConstructionType.java`](../age/of/civilizations2/jakowski/lukasz/ConstructionType.java) | 13 | — | строительство тип |  |
| [`CivBonus_Type.java`](../age/of/civilizations2/jakowski/lukasz/CivBonus_Type.java) | 9 | — | цивилизация бонус тип |  |
| [`Buildings.java`](../age/of/civilizations2/jakowski/lukasz/Buildings.java) | 11 | — | Типы и данные строений. |  |


## Модель: дипломатия и войны

Файлов: **20**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`PeaceTreaty_Data.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_Data.java) | 2076 | — | Мирный договор: условия, участники, статус. | AI_UseVictoryPoints, AI_UseVictoryPoints_CivID_TakeVassal, AI_UseVictoryPoints_CivID, AI_UseVictoryPoints_CivID_Score |
| [`UnionFlagsToGenerate.java`](../age/of/civilizations2/jakowski/lukasz/UnionFlagsToGenerate.java) | 273 | — | Очередь генерации флагов уний. | generateFlag |
| [`Ideology.java`](../age/of/civilizations2/jakowski/lukasz/Ideology.java) | 258 | — | Идеология: модификаторы, цвета, доступность. | getMin_Goods, getName, setName, getExtraTag |
| [`CreateVassal_Data.java`](../age/of/civilizations2/jakowski/lukasz/CreateVassal_Data.java) | 170 | — | создание вассал данные | setCivTag, loadFlag, getFlagOfCiv, getFlagOfCivH |
| [`Alliance.java`](../age/of/civilizations2/jakowski/lukasz/Alliance.java) | 160 | Serializable | Альянс: участники, лидер, правила. | addCivilization, removeCivilization, updateCivilizationID, moveUp |
| [`PeaceTreaty_Demands.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_Demands.java) | 102 | Serializable | Мирный договор: требования | addDemandOnProvince, removeDemandOnProvince, removeWarReparationsFromCivID, addWarReparationsFromCivID |
| [`DiplomacyColors_GameData2.java`](../age/of/civilizations2/jakowski/lukasz/DiplomacyColors_GameData2.java) | 29 | Serializable | дипломатия цвета игра данные 2 | getName, setName |
| [`PeaceTreaty_ReleaseableVassals.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_ReleaseableVassals.java) | 38 | Serializable | Мирный договор: освобождаемый вассалы | getScoreValue, addProvince |
| [`Alliances_Names_GameData_Bundle.java`](../age/of/civilizations2/jakowski/lukasz/Alliances_Names_GameData_Bundle.java) | 35 | Serializable | альянсы имена игра данные бандл | addWord, removeWord, getWord, setWord |
| [`PeaceTreaty_Civs.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_Civs.java) | 27 | Serializable | Мирный договор: цивилизации | getVictoryPointsTotal |
| [`OpenBorders.java`](../age/of/civilizations2/jakowski/lukasz/OpenBorders.java) | 28 | — | Договор об открытых границах. | getToCivID, setToCivID, getNumOfTurns, setNumOfTurns |
| [`War_Points.java`](../age/of/civilizations2/jakowski/lukasz/War_Points.java) | 26 | — | война очки | addPoints, getNumOfProvincesTotal |
| [`PeaceTreaty_GameData_MessageData.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_GameData_MessageData.java) | 15 | Serializable | Мирный договор: игра данные сообщение данные |  |
| [`PeaceTreaty_ReleaseableVassals_TakeConrol.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_ReleaseableVassals_TakeConrol.java) | 15 | Serializable | Мирный договор: освобождаемый вассалы взятие контроль |  |
| [`PeaceTreaty_DrawData.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_DrawData.java) | 16 | — | Мирный договор: рисование данные |  |
| [`WarReparations.java`](../age/of/civilizations2/jakowski/lukasz/WarReparations.java) | 15 | Serializable | Репарации. |  |
| [`SupportRebels_Data.java`](../age/of/civilizations2/jakowski/lukasz/SupportRebels_Data.java) | 12 | — | поддержка повстанцы данные |  |
| [`Assimilate_Data.java`](../age/of/civilizations2/jakowski/lukasz/Assimilate_Data.java) | 12 | — | ассимиляция данные |  |
| [`UnionFlagsToGenerate_TypesOfAction.java`](../age/of/civilizations2/jakowski/lukasz/UnionFlagsToGenerate_TypesOfAction.java) | 8 | — | Флаги уний: типы действие |  |
| [`Diplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Diplomacy.java) | 7 | — | дипломатия |  |


## Модель: события

Файлов: **96**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Event_Outcome_FormCivilization.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_FormCivilization.java) | 302 | Event_Outcome | Событие: результат форма цивилизация | getCivID, setCivID, getText, setText |
| [`Event_Outcome_JoinAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_JoinAlliance.java) | 147 | Event_Outcome | Событие: результат вступление альянс | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_CreateVassal.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_CreateVassal.java) | 159 | Event_Outcome | Событие: результат создание вассал | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_UpdatePopulation.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdatePopulation.java) | 153 | Event_Outcome | Событие: результат обновление население | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_UpdatePopulationPerc.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdatePopulationPerc.java) | 147 | Event_Outcome | Событие: результат обновление население процент | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_ChangeOwner.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_ChangeOwner.java) | 150 | Event_Outcome | Событие: результат изменение владелец | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_AddArmy.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_AddArmy.java) | 143 | Event_Outcome | Событие: результат добавить армия | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_Wasteland.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_Wasteland.java) | 132 | Event_Outcome | Событие: результат необитаемые земли | getValue, setValue, getProvinces, setProvinces |
| [`Event_Outcome_Occupy.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_Occupy.java) | 148 | Event_Outcome | Событие: результат оккупация | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_NonAggression.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_NonAggression.java) | 140 | Event_Outcome | Событие: результат Non агрессия | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_DefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_DefensivePact.java) | 140 | Event_Outcome | Событие: результат оборонительный пакт | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_DevLevel.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_DevLevel.java) | 143 | Event_Outcome | Событие: результат развитие уровень | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_UpdateHappiness.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdateHappiness.java) | 143 | Event_Outcome | Событие: результат обновление счастье | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_MoveCapital.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_MoveCapital.java) | 130 | Event_Outcome | Событие: результат перемещение столица | getCivID, setCivID, updateCivIDAfterRemove, getValue |
| [`Event_Outcome_IncreaseRelation.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_IncreaseRelation.java) | 140 | Event_Outcome | Событие: результат увеличение отношение | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_UpdateEconomyPerc.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdateEconomyPerc.java) | 137 | Event_Outcome | Событие: результат обновление экономика процент | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_MilitaryAccess.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_MilitaryAccess.java) | 136 | Event_Outcome | Событие: результат военный доступ | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_Independence.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_Independence.java) | 134 | Event_Outcome | Событие: результат независимость | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_UpdateEconomy.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdateEconomy.java) | 132 | Event_Outcome | Событие: результат обновление экономика | getCivID, setCivID, getValue, setValue |
| [`Event_SelectCivAction.java`](../age/of/civilizations2/jakowski/lukasz/Event_SelectCivAction.java) | 137 | — | Событие: выбор цивилизация действие |  |
| [`Event_Outcome_UpdatePopulationOfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdatePopulationOfCiv.java) | 115 | Event_Outcome | Событие: результат обновление население цивилизация | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_RemoveCore.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_RemoveCore.java) | 121 | Event_Outcome | Событие: результат удаление кор | getCivID, setCivID, getProvinces, setProvinces |
| [`Event_Outcome_ChangeIdeology.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_ChangeIdeology.java) | 119 | Event_Outcome | Событие: результат изменение идеология | getCivID, setCivID, updateCivIDAfterRemove, getValue |
| [`Event_Outcome_AddCore.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_AddCore.java) | 117 | Event_Outcome | Событие: результат добавить кор | getCivID, setCivID, getProvinces, setProvinces |
| [`Event_Outcome_DeclareWar.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_DeclareWar.java) | 114 | Event_Outcome | Событие: результат объявление война | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_WhitePeace.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_WhitePeace.java) | 112 | Event_Outcome | Событие: результат белый мир | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_UpdateEconomyOfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdateEconomyOfCiv.java) | 108 | Event_Outcome | Событие: результат обновление экономика цивилизация | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_UpdateHappinessOfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_UpdateHappinessOfCiv.java) | 110 | Event_Outcome | Событие: результат обновление счастье цивилизация | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_DiplomacyPoints.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_DiplomacyPoints.java) | 109 | Event_Outcome | Событие: результат дипломатия очки | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_MovementPoints.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_MovementPoints.java) | 109 | Event_Outcome | Событие: результат движение очки | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_JoinUnion.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_JoinUnion.java) | 111 | Event_Outcome | Событие: результат вступление уния | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_Money.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_Money.java) | 109 | Event_Outcome | Событие: результат деньги | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_DecisionTaken.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_DecisionTaken.java) | 105 | Event_Conditions | Событие: условия решение взято | getText, setText, setCivID, getCivID |
| [`Event_Outcome_TechLevel.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_TechLevel.java) | 104 | Event_Outcome | Событие: результат технология уровень | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfNeighbors_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfNeighbors_Low.java) | 95 | Event_Conditions | Событие: условия количество соседи низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfNeighbors.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfNeighbors.java) | 95 | Event_Conditions | Событие: условия количество соседи | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_OccupyProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_OccupyProvinces.java) | 100 | Event_Conditions | Событие: условия оккупация провинции | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_LiberateVassal.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_LiberateVassal.java) | 83 | Event_Outcome | Событие: результат освобождение вассал | getCivID, setCivID, updateCivIDAfterRemove, outcomeAction |
| [`Event_Outcome_LeaveAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_LeaveAlliance.java) | 83 | Event_Outcome | Событие: результат выход альянс | getCivID, setCivID, updateCivIDAfterRemove, outcomeAction |
| [`Event_Conditions_ControlsProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_ControlsProvinces.java) | 91 | Event_Conditions | Событие: условия контроль провинции | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_IsCapital.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_IsCapital.java) | 91 | Event_Conditions | Событие: условия столица | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_HaveCore.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_HaveCore.java) | 90 | Event_Conditions | Событие: условия кор | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_HaveArmy.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_HaveArmy.java) | 90 | Event_Conditions | Событие: условия армия | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Relation_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Relation_Low.java) | 87 | Event_Conditions | Событие: условия отношение низкий | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_Relation.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Relation.java) | 87 | Event_Conditions | Событие: условия отношение | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Outcome_DecreaseRelation.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_DecreaseRelation.java) | 86 | Event_Outcome | Событие: результат уменьшение отношение | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_IsAVassalOfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_IsAVassalOfCiv.java) | 76 | Event_Conditions | Событие: условия вассал цивилизация | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_WatchTower.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_WatchTower.java) | 71 | Event_Conditions | Событие: условия стража башня | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Wasteland.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Wasteland.java) | 71 | Event_Conditions | Событие: условия необитаемые земли | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Neutral.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Neutral.java) | 71 | Event_Conditions | Событие: условия нейтральный | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Farm.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Farm.java) | 71 | Event_Conditions | Событие: условия ферма | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Fort.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Fort.java) | 71 | Event_Conditions | Событие: условия форт | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Port.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Port.java) | 71 | Event_Conditions | Событие: условия порт | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Allies.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Allies.java) | 71 | Event_Conditions | Событие: условия союзники | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_NonAggression.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NonAggression.java) | 74 | Event_Conditions | Событие: условия Non агрессия | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_Independence.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Independence.java) | 74 | Event_Conditions | Событие: условия независимость | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_MilitaryAccess.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_MilitaryAccess.java) | 74 | Event_Conditions | Событие: условия военный доступ | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_DefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_DefensivePact.java) | 74 | Event_Conditions | Событие: условия оборонительный пакт | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_NumOfVassals_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfVassals_Low.java) | 72 | Event_Conditions | Событие: условия количество вассалы низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfVassals.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfVassals.java) | 72 | Event_Conditions | Событие: условия количество вассалы | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Population_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Population_Low.java) | 70 | Event_Conditions | Событие: условия население низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Population.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Population.java) | 70 | Event_Conditions | Событие: условия население | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfWars_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfWars_Low.java) | 72 | Event_Conditions | Событие: условия количество войны низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfAllies_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfAllies_Low.java) | 68 | Event_Conditions | Событие: условия количество союзники низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfWars.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfWars.java) | 72 | Event_Conditions | Событие: условия количество войны | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_AtWar.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_AtWar.java) | 70 | Event_Conditions | Событие: условия война | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_NumOfAllies.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfAllies.java) | 68 | Event_Conditions | Событие: условия количество союзники | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Economy_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Economy_Low.java) | 70 | Event_Conditions | Событие: условия экономика низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Economy.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Economy.java) | 70 | Event_Conditions | Событие: условия экономика | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Ideology.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Ideology.java) | 68 | Event_Conditions | Событие: условия идеология | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Development_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Development_Low.java) | 63 | Event_Conditions | Событие: условия развитие низкий | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Development.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Development.java) | 63 | Event_Conditions | Событие: условия развитие | getValue, setValue, getProvinces, setProvinces |
| [`Event_Conditions_Technology_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Technology_Low.java) | 64 | Event_Conditions | Событие: условия технология низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Technology.java) | 64 | Event_Conditions | Событие: условия технология | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumberOfProvinces_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumberOfProvinces_Low.java) | 64 | Event_Conditions | Событие: условия число провинции низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumberOfProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumberOfProvinces.java) | 64 | Event_Conditions | Событие: условия число провинции | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfUnits_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfUnits_Low.java) | 64 | Event_Conditions | Событие: условия количество юниты низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_NumOfUnits.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_NumOfUnits.java) | 64 | Event_Conditions | Событие: условия количество юниты | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Happiness_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Happiness_Low.java) | 64 | Event_Conditions | Событие: условия счастье низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Treasury_Low.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Treasury_Low.java) | 64 | Event_Conditions | Событие: условия казна низкий | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Happiness.java) | 64 | Event_Conditions | Событие: условия счастье | getCivID, setCivID, getValue, setValue |
| [`Event_Conditions_Treasury.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_Treasury.java) | 64 | Event_Conditions | Событие: условия казна | getCivID, setCivID, getValue, setValue |
| [`Event_Outcome_TriggerAnotherEvent.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome_TriggerAnotherEvent.java) | 58 | Event_Outcome | Событие: результат триггер другой событие | getText, setText, outcomeAction, canMakeAction |
| [`Event_Trigger.java`](../age/of/civilizations2/jakowski/lukasz/Event_Trigger.java) | 44 | Serializable | Событие: триггер | getTriggerOut, getTriggerText |
| [`Event_Conditions_ControlledByPlayer.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_ControlledByPlayer.java) | 53 | Event_Conditions | Событие: условия контролируемый игрок | getCivID, setCivID, updateCivIDAfterRemove, outCondition |
| [`Event_Outcome.java`](../age/of/civilizations2/jakowski/lukasz/Event_Outcome.java) | 65 | Serializable | Событие: результат | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_IsAVassal.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_IsAVassal.java) | 53 | Event_Conditions | Событие: условия вассал | getCivID, setCivID, updateCivIDAfterRemove, outCondition |
| [`Event_Conditions_IsPartOfHRE.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_IsPartOfHRE.java) | 53 | Event_Conditions | Событие: условия часть СРИ | getCivID, setCivID, updateCivIDAfterRemove, outCondition |
| [`Event_Conditions_CivExist.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_CivExist.java) | 53 | Event_Conditions | Событие: условия цивилизация существование | getCivID, setCivID, updateCivIDAfterRemove, outCondition |
| [`Event_Conditions_IsAtWar.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_IsAtWar.java) | 53 | Event_Conditions | Событие: условия война | getCivID, setCivID, updateCivIDAfterRemove, outCondition |
| [`Event_Conditions.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions.java) | 62 | Serializable | Событие: условия | getCivID, setCivID, getCivID2, setCivID2 |
| [`Event_Conditions_EventChance.java`](../age/of/civilizations2/jakowski/lukasz/Event_Conditions_EventChance.java) | 35 | Event_Conditions | Событие: условия событие шанс | getValue, setValue, outCondition, getConditionText |
| [`Event_Decision.java`](../age/of/civilizations2/jakowski/lukasz/Event_Decision.java) | 19 | Serializable | Событие: решение | executeDecision |
| [`Event_Date.java`](../age/of/civilizations2/jakowski/lukasz/Event_Date.java) | 11 | Serializable | Событие: дата |  |
| [`Event_PopUp.java`](../age/of/civilizations2/jakowski/lukasz/Event_PopUp.java) | 10 | Serializable | Событие: население Up |  |
| [`Event_Type.java`](../age/of/civilizations2/jakowski/lukasz/Event_Type.java) | 8 | — | Событие: тип |  |


## Модель: история

Файлов: **17**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`HistoryLog_IsNotVassal.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_IsNotVassal.java) | 321 | HistoryLog | Запись истории: вассал | updateLanguage, draw |
| [`HistoryLog_IsVassal.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_IsVassal.java) | 321 | HistoryLog | Запись истории: вассал | updateLanguage, draw |
| [`HistoryLog_SignedNonAggressionPact.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_SignedNonAggressionPact.java) | 266 | HistoryLog | Запись истории: подписано Non агрессия пакт | updateLanguage, draw |
| [`HistoryLog_SignedDefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_SignedDefensivePact.java) | 266 | HistoryLog | Запись истории: подписано оборонительный пакт | updateLanguage, draw |
| [`HistoryLog_HaveMilitartyAccess.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_HaveMilitartyAccess.java) | 266 | HistoryLog | Запись истории: военный доступ | updateLanguage, draw |
| [`HistoryLog_FriendlyCivs.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_FriendlyCivs.java) | 266 | HistoryLog | Запись истории: дружественный цивилизации | updateLanguage, draw |
| [`HistoryLog_Guarantee.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_Guarantee.java) | 266 | HistoryLog | Запись истории: гарантия | updateLanguage, draw |
| [`HistoryLog_WarDeclaration.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_WarDeclaration.java) | 266 | HistoryLog | Запись истории: война объявление | updateLanguage, draw |
| [`HistoryLog_Annexation.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_Annexation.java) | 266 | HistoryLog | Запись истории: аннексия | updateLanguage, draw |
| [`HistoryLog_Peace.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_Peace.java) | 266 | HistoryLog | Запись истории: мир | updateLanguage, draw |
| [`HistoryLog_Disease.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_Disease.java) | 171 | HistoryLog | Запись истории: болезнь | updateLanguage, draw, getName |
| [`HistoryLog_LeavesAlliance.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_LeavesAlliance.java) | 166 | HistoryLog | Запись истории: выходит альянс | updateLanguage, draw, getName |
| [`HistoryLog_JoinAlliance.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_JoinAlliance.java) | 166 | HistoryLog | Запись истории: вступление альянс | updateLanguage, draw, getName |
| [`HistoryLog_NewColony.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_NewColony.java) | 139 | HistoryLog | Запись истории: новый колония | updateLanguage, draw |
| [`HistoryLog_Union.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_Union.java) | 138 | HistoryLog | Запись истории: уния | updateLanguage, draw |
| [`HistoryLog.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog.java) | 75 | Serializable | Запись в лог истории. | updateLanguage, draw, drawLeftIconBG, drawLeftIcon |
| [`HistoryLog_Types.java`](../age/of/civilizations2/jakowski/lukasz/HistoryLog_Types.java) | 20 | — | Запись истории: типы |  |


## Модель: задачи и планы ИИ

Файлов: **6**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`CivPersonality.java`](../age/of/civilizations2/jakowski/lukasz/CivPersonality.java) | 130 | Serializable | Личность ИИ-цивилизации. |  |
| [`CivPlans.java`](../age/of/civilizations2/jakowski/lukasz/CivPlans.java) | 159 | Serializable | Планы ИИ. | addNewWarPreparations, isPreparingForTheWar, checkWarPreparations, updatePreparationsTime |
| [`Civ_Mission_ChangeTypeOfGoverment.java`](../age/of/civilizations2/jakowski/lukasz/Civ_Mission_ChangeTypeOfGoverment.java) | 43 | Serializable | Цивилизация: миссия изменение тип правительство | action |
| [`Civ_Task.java`](../age/of/civilizations2/jakowski/lukasz/Civ_Task.java) | 14 | Serializable | Цивилизация: задача | runTask |
| [`Civ_Task_Assimilate.java`](../age/of/civilizations2/jakowski/lukasz/Civ_Task_Assimilate.java) | 9 | Civ_Task | Цивилизация: задача ассимиляция |  |
| [`Civ_Task_Type.java`](../age/of/civilizations2/jakowski/lukasz/Civ_Task_Type.java) | 6 | — | Цивилизация: задача тип |  |


## Модель: прочее состояние

Файлов: **45**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Turn_NewTurn.java`](../age/of/civilizations2/jakowski/lukasz/Turn_NewTurn.java) | 1253 | Thread | Поток расчёта нового хода: экономика, население, счастье, оккупации, восстания, голод. | run, checkOccupiedProvincesIfAreAtWar, doAction, updateGameData_TurnChanges |
| [`SliderMenu.java`](../age/of/civilizations2/jakowski/lukasz/SliderMenu.java) | 689 | — | Контейнер экрана: заголовок, список MenuElement, скролл по X/Y со ScissorStack, back-кнопка, закрытие. | initMenu, initMenuWithBackButton, updateScrollable, updateLanguage |
| [`Slider_FlagAction_Clear_Flag_Tribute.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Clear_Flag_Tribute.java) | 484 | Slider | Слайдер: флаг действие очистить флаг дань | draw, getImageScale, getColorLEFT, getColor |
| [`Slider_FlagAction_Taxes.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Taxes.java) | 489 | Slider | Слайдер: флаг действие налоги | draw, getColorLEFT, getColor, getDrawText |
| [`Player.java`](../age/of/civilizations2/jakowski/lukasz/Player.java) | 439 | — | Игрок: civID, туман войны, статистика, состояние открытых панелей, камера, «нет приказов». | buildPlayerScore, initMetProvince, initMetCivilization, initFogOfWar |
| [`Slider_FlagAction_Clear_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Clear_Flag.java) | 413 | Slider | Слайдер: флаг действие очистить флаг | draw, getImageScale, getColorLEFT, getColor |
| [`Slider_FlagAction.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction.java) | 407 | Slider | Слайдер: флаг действие | draw, getColorLEFT, getColor, getDrawText |
| [`Slider_FlagAction_Clear_Tech.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Clear_Tech.java) | 403 | Slider | Слайдер: флаг действие очистить технология | draw, getColorLEFT, getColor, getDrawText |
| [`Slider_FlagAction_Date.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Date.java) | 405 | Slider | Слайдер: флаг действие дата | draw, getColorLEFT, getColor, getDrawText |
| [`Slider_FlagAction_Gold.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Gold.java) | 385 | Slider | Слайдер: флаг действие золото | draw, getColorLEFT, getColor, getDrawText |
| [`Slider_CrossTheBorder.java`](../age/of/civilizations2/jakowski/lukasz/Slider_CrossTheBorder.java) | 378 | Slider | Слайдер: крест граница | draw, getColorLEFT, getColor, updateSlider |
| [`Slider_FlagAction_Military.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Military.java) | 346 | Slider | Слайдер: флаг действие военный | draw, getColorLEFT, getColor, getDrawText |
| [`Slider_FlagAction_Clear.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Clear.java) | 362 | Slider | Слайдер: флаг действие очистить | draw, getColorLEFT, getColor, getDrawText |
| [`Slider.java`](../age/of/civilizations2/jakowski/lukasz/Slider.java) | 326 | MenuElement | Базовый слайдер: значение, диапазон, перетаскивание, колбэк. | initSlider, draw, drawSliderBG_UpdateAnimation, drawSliderBG |
| [`Achievement_Data.java`](../age/of/civilizations2/jakowski/lukasz/Achievement_Data.java) | 246 | — | достижение данные | getPosX, getPosY, getWidth, getHeight |
| [`Slider_Relations.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Relations.java) | 196 | Slider | Слайдер: отношения | draw, drawSliderText |
| [`Slider_Relations2.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Relations2.java) | 196 | Slider | Слайдер: отношения 2 | draw, drawSliderText |
| [`Turn_Actions.java`](../age/of/civilizations2/jakowski/lukasz/Turn_Actions.java) | 164 | Thread | Очередь действий внутри хода. | run, runRevolts |
| [`Slider_FlagAction_Goods.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Goods.java) | 150 | Slider_FlagAction | Слайдер: флаг действие товары | draw, setCurrent, getSliderHeight |
| [`Slider_InGame.java`](../age/of/civilizations2/jakowski/lukasz/Slider_InGame.java) | 123 | Slider | Слайдер: в игре | drawSliderBG, drawSliderBorder, drawSliderText, sliderHeight |
| [`Slider_BG_CNG.java`](../age/of/civilizations2/jakowski/lukasz/Slider_BG_CNG.java) | 140 | Slider_BG | Слайдер: BG CNG | draw, drawSliderBG, drawSliderText, getColor |
| [`Age.java`](../age/of/civilizations2/jakowski/lukasz/Age.java) | 133 | — | Эпоха и её модификаторы. | getName, setName, getBeginningYear, getEndYear |
| [`Slider_FlagAction_Investments.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Investments.java) | 127 | Slider_FlagAction | Слайдер: флаг действие инвестиции | setCurrent, draw |
| [`Slider_MilitartAccess.java`](../age/of/civilizations2/jakowski/lukasz/Slider_MilitartAccess.java) | 87 | Slider_BG | Слайдер: военный доступ | draw, drawSliderText, buildElementHover |
| [`Slider_Guarantee.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Guarantee.java) | 87 | Slider_BG | Слайдер: гарантия | draw, drawSliderText, buildElementHover |
| [`Slider_Defensive.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Defensive.java) | 87 | Slider_BG | Слайдер: оборонительный | draw, drawSliderText, buildElementHover |
| [`Difficulty_Level.java`](../age/of/civilizations2/jakowski/lukasz/Difficulty_Level.java) | 91 | MenuElement | сложность уровень | draw, setCurrent, getCurrent |
| [`Slider_Pact.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Pact.java) | 80 | Slider_BG | Слайдер: пакт | draw, drawSliderText, buildElementHover |
| [`Slider_Truce.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Truce.java) | 80 | Slider_BG | Слайдер: перемирие | draw, drawSliderText, buildElementHover |
| [`Slider_Age.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Age.java) | 82 | Slider | Слайдер: эпоха | drawSliderBG, getColorLEFT |
| [`Slider_BG_ZeroInMiddle.java`](../age/of/civilizations2/jakowski/lukasz/Slider_BG_ZeroInMiddle.java) | 74 | Slider | Слайдер: BG ноль в середина | draw, getColorLEFT, getColorRIGHT |
| [`Terrain_GameData3.java`](../age/of/civilizations2/jakowski/lukasz/Terrain_GameData3.java) | 115 | Serializable | местность игра данные 3 | getName, setName, getIconName, setIconName |
| [`SliderMenuTitle.java`](../age/of/civilizations2/jakowski/lukasz/SliderMenuTitle.java) | 97 | — | Заголовок SliderMenu (текст, кнопки, иконки). | draw, drawText, getText, setText |
| [`Wonder.java`](../age/of/civilizations2/jakowski/lukasz/Wonder.java) | 73 | — | Чудо света в провинции. | draw, dispose |
| [`Slider_BG_CNG_Pact.java`](../age/of/civilizations2/jakowski/lukasz/Slider_BG_CNG_Pact.java) | 61 | Slider_BG_CNG | Слайдер: BG CNG пакт | drawSliderBG, buildElementHover |
| [`Slider_LR_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Slider_LR_Flag.java) | 45 | Slider_LR_Perc | Слайдер: LR флаг | drawSliderText |
| [`Slider_LR_Perc.java`](../age/of/civilizations2/jakowski/lukasz/Slider_LR_Perc.java) | 43 | Slider_LR | Слайдер: LR процент | drawSliderBG |
| [`Slider_Perc.java`](../age/of/civilizations2/jakowski/lukasz/Slider_Perc.java) | 43 | Slider | Слайдер: процент | drawSliderBG |
| [`Slide.java`](../age/of/civilizations2/jakowski/lukasz/Slide.java) | 38 | MenuElement | слайд | draw |
| [`Slider_LR.java`](../age/of/civilizations2/jakowski/lukasz/Slider_LR.java) | 33 | Slider | Слайдер: LR | drawSliderText |
| [`RandomTurnOrder.java`](../age/of/civilizations2/jakowski/lukasz/RandomTurnOrder.java) | 50 | — | Случайный порядок хода. | buildRandomOrder, getRTO, getPositionInRTOOfCiv, getRTOSize |
| [`Slider_BG.java`](../age/of/civilizations2/jakowski/lukasz/Slider_BG.java) | 20 | Slider | Слайдер: BG | draw |
| [`Turn_CivsInRange.java`](../age/of/civilizations2/jakowski/lukasz/Turn_CivsInRange.java) | 27 | Thread | Ход: цивилизации в диапазон | run |
| [`Slider_FlagAction_Clear_Adm.java`](../age/of/civilizations2/jakowski/lukasz/Slider_FlagAction_Clear_Adm.java) | 13 | Slider_FlagAction_Clear_Tech | Слайдер: флаг действие очистить администрация | getTextLeft |
| [`PopulationGrowth.java`](../age/of/civilizations2/jakowski/lukasz/PopulationGrowth.java) | 12 | — | Расчёт роста населения. |  |


## Сохранения и данные

Файлов: **14**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Save_GameData_1.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_1.java) | 67 | Serializable | Структура сейва: игра данные | buildData |
| [`Package_ContinentsData.java`](../age/of/civilizations2/jakowski/lukasz/Package_ContinentsData.java) | 41 | Serializable | пакет континенты данные | getPackageName, setPackageName, getContinentTag, getContinentsTagsSize |
| [`Package_RegionsData.java`](../age/of/civilizations2/jakowski/lukasz/Package_RegionsData.java) | 41 | Serializable | пакет регионы данные | getPackageName, setPackageName, getRegionTag, getRegionsTagsSize |
| [`Save_GameData_9.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_9.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_8.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_8.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_4.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_4.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_3.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_3.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_5.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_5.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_2.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_2.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_6.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_6.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_7.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_7.java) | 17 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_10.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_10.java) | 13 | Serializable | Структура сейва: игра данные | buildData |
| [`Save_GameData_11.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData_11.java) | 13 | Serializable | Актуальная (11-я) версия схемы сейва; добавляет Events_GameData. Более старые версии — шаги миграции. | buildData |
| [`Save_GameData2.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData2.java) | 13 | Serializable | Структура сейва: игра данные 2 | buildData |


## Структуры данных (GameData)

Файлов: **66**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`War_GameData.java`](../age/of/civilizations2/jakowski/lukasz/War_GameData.java) | 896 | Serializable | Война: участники, счёт, оккупации. | addAggressor, removeAggressor, addDefender, removeDefender |
| [`Plague_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Plague_GameData.java) | 293 | Serializable | болезнь игра данные | runDisease, spreadDisease, getSpreadScore, addProvince |
| [`Save_Civ_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Save_Civ_GameData.java) | 218 | Serializable | Сериализуемая часть цивилизации. | addGift_Received, updateGift_Received, setLeader |
| [`HolyRomanEmpire_GameData.java`](../age/of/civilizations2/jakowski/lukasz/HolyRomanEmpire_GameData.java) | 306 | Serializable | священный римский империя игра данные | updateHRE_AfterRemoveCivilization, getProvinces, addProvince, removeProvince |
| [`Scenario_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData.java) | 196 | Serializable | сценарий игра данные | buildData, getCivSize, getCivTag, getCivCapital |
| [`MessageBox_GameData.java`](../age/of/civilizations2/jakowski/lukasz/MessageBox_GameData.java) | 134 | Serializable | сообщение бокс игра данные | updateNextTurn, addMessage, removeMessage, removeMessage_TypeFrom |
| [`Save_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Save_GameData.java) | 111 | Serializable | Базовая структура сейва партии. | buildData |
| [`Civilization_Diplomacy_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Diplomacy_GameData.java) | 136 | Serializable | Цивилизация: дипломатия игра данные | addImproveRelations, runImproveRelations, getImproveRelationsSize, getImproveRelation |
| [`Event_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Event_GameData.java) | 139 | Serializable | Событие: игра данные | checkTriggers, checkDecisions, getEventTag, setEventTag |
| [`Statistics_Civ_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Statistics_Civ_GameData.java) | 148 | Serializable | статистика цивилизация игра данные | getConqueredProvinces, setConqueredProvinces, getTurns, setTurns |
| [`Province_Cores_Provinces_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Province_Cores_Provinces_GameData.java) | 115 | Serializable | Провинция: коры провинции игра данные | addCore, updateCorePercOfPopulation, removeCore, getPercOfPop |
| [`Plagues_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Plagues_GameData.java) | 90 | — | болезни игра данные | getName, getName_Real, setName |
| [`Province_Cores_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Province_Cores_GameData.java) | 105 | Serializable | Провинция: коры игра данные | getProvincesSize, addCore, removeCore, updatePercOfPopulation |
| [`Save_CivDiplo_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Save_CivDiplo_GameData.java) | 65 | Serializable | Структура сейва: цивилизация дипломатия игра данные |  |
| [`Save_Provinces_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Save_Provinces_GameData.java) | 68 | Serializable | Сериализуемая часть провинции. | resetData |
| [`LeaderOfCiv_GameData.java`](../age/of/civilizations2/jakowski/lukasz/LeaderOfCiv_GameData.java) | 81 | Serializable | лидер цивилизация игра данные | getName, setName, getImage, setImage |
| [`Timelapse_Stats_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_Stats_GameData.java) | 46 | Serializable | Таймлапс: статистика игра данные | addProvinces, addPopulation, addRank, addTechLevel |
| [`FormableCivs_GameData.java`](../age/of/civilizations2/jakowski/lukasz/FormableCivs_GameData.java) | 70 | Serializable | формируемый цивилизации игра данные | addClaimant, removeClaimant, getClaimant, getClaimantsSize |
| [`TradeZone_GameData.java`](../age/of/civilizations2/jakowski/lukasz/TradeZone_GameData.java) | 68 | Serializable | торговля зона игра данные | getProvince, getCenterOfTradeProvinceID, getProvincesSize, getAge |
| [`PreDefined_Borders_Data_GameData.java`](../age/of/civilizations2/jakowski/lukasz/PreDefined_Borders_Data_GameData.java) | 60 | Serializable | Pre Defined границы данные игра данные | setCapitalProvinceID, getCapitalProvinceID, getProvincesSize, getProvinceID |
| [`WarCiv_GameData.java`](../age/of/civilizations2/jakowski/lukasz/WarCiv_GameData.java) | 61 | Serializable | война цивилизация игра данные | getCivID, setCivID, getCasualties, addCasualties |
| [`Ultimatum_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Ultimatum_GameData.java) | 45 | Serializable | ультиматум игра данные | isLiberationDemanded, updateLiberationDemand, canBeSend |
| [`WastelandMap_GameData.java`](../age/of/civilizations2/jakowski/lukasz/WastelandMap_GameData.java) | 45 | Serializable | необитаемые земли карта игра данные | generateData, getName, setName, getWastelandProvincesSize |
| [`TradeRequest_GameData.java`](../age/of/civilizations2/jakowski/lukasz/TradeRequest_GameData.java) | 31 | Serializable | Торговый запрос: игра данные | canBeSend |
| [`Leader_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Leader_GameData.java) | 44 | Serializable | лидер игра данные | addCiv, removeCiv, getCiv, getCivsSize |
| [`Line_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Line_GameData.java) | 47 | Serializable | линия игра данные | getImageName, setImageName, getRapeatImage, setReapeatImage |
| [`Alliances_Names_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Alliances_Names_GameData.java) | 40 | Serializable | альянсы имена игра данные | getBundle, addBundle, removeBundle, getSize |
| [`Civilization_Diplomacy_ImproveRelations_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Diplomacy_ImproveRelations_GameData.java) | 25 | Serializable | Цивилизация: дипломатия улучшение отношения игра данные | action |
| [`Skills_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Skills_GameData.java) | 28 | Serializable | навыки игра данные | getPointsLeft |
| [`ServiceRibbon_Overlay_GameData.java`](../age/of/civilizations2/jakowski/lukasz/ServiceRibbon_Overlay_GameData.java) | 41 | Serializable | Лента сервисов: оверлей игра данные | getPosX, setPosX, getWidth, setWidth |
| [`Continent_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Continent_GameData.java) | 47 | Serializable | континент игра данные | getName, setName, getR, setR |
| [`Region_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Region_GameData.java) | 47 | Serializable | регион игра данные | getName, setName, getR, setR |
| [`Civ_TasksManager_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civ_TasksManager_GameData.java) | 34 | Serializable | Цивилизация: задачи менеджер игра данные | addNewTask, runTasks |
| [`TradeRoute_GameData.java`](../age/of/civilizations2/jakowski/lukasz/TradeRoute_GameData.java) | 38 | Serializable | торговля маршрут игра данные | getFromTagID, setFromTagID, getToTagID, setToTagID |
| [`Province_Border_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Province_Border_GameData.java) | 30 | Serializable | Провинция: граница игра данные | getWithProvinceID, getPointsX, getPointsY |
| [`Color_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Color_GameData.java) | 41 | Serializable | цвет игра данные | getR, setR, getG, setG |
| [`Religion_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Religion_GameData.java) | 35 | Serializable | религия игра данные | getName, setName, getColor, setColor |
| [`PreDefined_Borders_GameData.java`](../age/of/civilizations2/jakowski/lukasz/PreDefined_Borders_GameData.java) | 30 | Serializable | Pre Defined границы игра данные | getDataSize, getData, removeData, addData |
| [`ServiceRibbon_GameData.java`](../age/of/civilizations2/jakowski/lukasz/ServiceRibbon_GameData.java) | 27 | Serializable | Лента сервисов: игра данные | getServiceRibbon_Overlay, addServiceRibbonOverlay, removeServiceRibbon_Overlay, getSize |
| [`CivBonus_GameData.java`](../age/of/civilizations2/jakowski/lukasz/CivBonus_GameData.java) | 19 | Serializable | цивилизация бонус игра данные |  |
| [`Construction_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Construction_GameData.java) | 22 | Serializable | Строительство: игра данные | onConstructed |
| [`Civilization_ServiceRibbon_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_ServiceRibbon_GameData.java) | 28 | Serializable | Цивилизация: сервис лента игра данные | getSRTAG, setSRTAG, getColors, getColor |
| [`PeaceTreaty_GameData.java`](../age/of/civilizations2/jakowski/lukasz/PeaceTreaty_GameData.java) | 17 | Serializable | Мирный договор: игра данные |  |
| [`Scenario_WastelandProvinces_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_WastelandProvinces_GameData.java) | 22 | Serializable | сценарий необитаемые земли провинции игра данные | getWastelandProvincesSize, getWastelandProvinceID |
| [`PlagueProvince_GameData.java`](../age/of/civilizations2/jakowski/lukasz/PlagueProvince_GameData.java) | 19 | Serializable | болезнь провинция игра данные |  |
| [`Province_Cores_Civs_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Province_Cores_Civs_GameData.java) | 24 | Serializable | Провинция: коры цивилизации игра данные | setPerc |
| [`Vassal_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Vassal_GameData.java) | 25 | Serializable | вассал игра данные | setTribute |
| [`Flag_Overlay_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Flag_Overlay_GameData.java) | 18 | Serializable | Флаг: оверлей игра данные |  |
| [`PalletOfCivsColors_Civ_GameData.java`](../age/of/civilizations2/jakowski/lukasz/PalletOfCivsColors_Civ_GameData.java) | 21 | Serializable | палитра цивилизации цвета цивилизация игра данные | getColor, setColor |
| [`Flag_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Flag_GameData.java) | 13 | Serializable | Флаг: игра данные |  |
| [`Civilization_Friends_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Friends_GameData.java) | 15 | Serializable | Цивилизация: друзья игра данные |  |
| [`Loan_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Loan_GameData.java) | 15 | Serializable | кредит игра данные |  |
| [`Civ_Gift_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civ_Gift_GameData.java) | 15 | Serializable | Цивилизация: подарок игра данные |  |
| [`Save_CivDiploInfo_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Save_CivDiploInfo_GameData.java) | 15 | Serializable | Структура сейва: цивилизация дипломатия информация игра данные |  |
| [`Save_Player_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Save_Player_GameData.java) | 13 | Serializable | Сериализуемая часть игрока. |  |
| [`Events_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Events_GameData.java) | 12 | Serializable | события игра данные |  |
| [`Union_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Union_GameData.java) | 12 | Serializable | уния игра данные |  |
| [`Timelapse_TurnChanges_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_TurnChanges_GameData.java) | 11 | Serializable | Таймлапс: ход изменения игра данные |  |
| [`Timelapse_Owners_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_Owners_GameData.java) | 11 | Serializable | Таймлапс: владельцы игра данные |  |
| [`Civilization_Hated_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_Hated_GameData.java) | 13 | Serializable | Цивилизация: ненавидимые игра данные |  |
| [`Timelapse_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_GameData.java) | 11 | Serializable | Таймлапс: игра данные |  |
| [`History_GameData.java`](../age/of/civilizations2/jakowski/lukasz/History_GameData.java) | 11 | Serializable | история игра данные |  |
| [`Unions_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Unions_GameData.java) | 11 | Serializable | унии игра данные |  |
| [`SaveActiveMap_GameData.java`](../age/of/civilizations2/jakowski/lukasz/SaveActiveMap_GameData.java) | 10 | Serializable | сохранение активный карта игра данные |  |
| [`Civilization_GameData.java`](../age/of/civilizations2/jakowski/lukasz/Civilization_GameData.java) | 11 | Serializable | Цивилизация: игра данные |  |
| [`SaveActiveMap_Status_GameData.java`](../age/of/civilizations2/jakowski/lukasz/SaveActiveMap_Status_GameData.java) | 9 | Serializable | сохранение активный карта статус игра данные |  |


## Игровой цикл и рендер

Файлов: **55**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Game.java`](../age/of/civilizations2/jakowski/lukasz/Game.java) | 22761 | — | Модель партии: провинции, регионы, цивилизации, игроки, города, горы, альянсы, войны, мирные договоры, выделение, линии приказов, чудеса. Здесь же сериализация состояния и запросы (getProvince/getCiv/...). | updateActiveProvinceBorderStyle, addDrawProvinceBorder_LandBySea, removeDrawProvinceBorder_LandBySea, checkUncivilizedMenu |
| [`Game_Action.java`](../age/of/civilizations2/jakowski/lukasz/Game_Action.java) | 6841 | — | Правила хода и боя: обработка приказов, dice roll, набор/роспуск армии, восстания, смена владельца, движение войск, стоимости действий, переход хода. | tryToTakeNexTurn, checkMessages_PauseRTS, nextTurn, endOfInputOrders |
| [`Game_Render_Province.java`](../age/of/civilizations2/jakowski/lukasz/Game_Render_Province.java) | 2978 | — | Отрисовка провинций: заливка по режимам, границы, оккупация, подсветка, туман войны. | getProvince_PortColor, getProvince_FortColor, getProvince_WatchTowerColor, updateDrawProvinces |
| [`Game_Render.java`](../age/of/civilizations2/jakowski/lukasz/Game_Render.java) | 2537 | — | Отрисовка карты: заливка провинций по режимам, границы, названия цивилизаций, города, армии, анимации, подписи регионов. | updateRegionsInView, draw, drawWithoutScale, drawMapDetails |
| [`Game_Scenarios.java`](../age/of/civilizations2/jakowski/lukasz/Game_Scenarios.java) | 2124 | — | Загрузка/сохранение сценариев и стартовых условий. | loadGame_Scenarios, disposeScenarios, loadCivilizations_RandomGame, loadCivilizationsLoadGame |
| [`Graph.java`](../age/of/civilizations2/jakowski/lukasz/Graph.java) | 2052 | MenuElement | Графики статистики с осями, подписями и легендой. | getGraphButtonWidth, getGraphButtonHeight, updateHover, setHoveredID |
| [`Graph_Vertical.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical.java) | 1852 | MenuElement | Вертикальный график. | updateHover, setHoveredID, buildElementHover, setCheckboxState |
| [`ColorPicker_AoC.java`](../age/of/civilizations2/jakowski/lukasz/ColorPicker_AoC.java) | 1321 | — | Палитра выбора цвета (флаги, цвета цивилизаций). | updateColorPicker_Action, buildColors, updateColors, draw |
| [`Game_NextTurnUpdate.java`](../age/of/civilizations2/jakowski/lukasz/Game_NextTurnUpdate.java) | 962 | — | Межходовые обновления: экономика, население, счастье, ИИ-шаги. | updatePlayableProvinces, updateInflationPeakValue, updateCivs_Money, updateProvinceStability |
| [`Map_BG.java`](../age/of/civilizations2/jakowski/lukasz/Map_BG.java) | 994 | — | Фон карты (текстура/тайлинг/параллакс). | updateWorldMap, updateWorldMap_Shaders, drawMap, drawMapBorder |
| [`Game_NewGame.java`](../age/of/civilizations2/jakowski/lukasz/Game_NewGame.java) | 945 | — | игра новый игра | updateDeclareWar, newGame, loadGame, build_ArmyInAnotherProvince |
| [`Graph_Circle_VictoryConditions.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Circle_VictoryConditions.java) | 549 | Graph_Circle | График: круг победа условия | draw, getHeight, getHeight_Title, getColor |
| [`Map_Coordinates.java`](../age/of/civilizations2/jakowski/lukasz/Map_Coordinates.java) | 440 | — | Преобразование экранных координат в провинции и обратно. | updateWorldMap, update, updateMapPos, checkPositionOfMapX |
| [`Graph_Vertical_Data.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data.java) | 435 | — | График: вертикальный данные | drawData, drawData_ONLY_SPLTTED, buildHeights, buildContintentData |
| [`Graph_Circle.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Circle.java) | 398 | MenuElement | Круговой график. | draw, drawGraph, updateMoveable, getMaxHeight |
| [`Map_Scroll.java`](../age/of/civilizations2/jakowski/lukasz/Map_Scroll.java) | 342 | — | Скролл карты (инерция, границы). | updateEnableBackroundAnimation, update, changeAnimationPos, startScrollingTheMap |
| [`Map_Scale.java`](../age/of/civilizations2/jakowski/lukasz/Map_Scale.java) | 359 | — | Масштаб карты (зум, ограничения, шаги). | update, startScaleTheMap2, scaleTheMap2, setNewScale_ByTouch2 |
| [`Game_Calendar.java`](../age/of/civilizations2/jakowski/lukasz/Game_Calendar.java) | 345 | — | Календарь: год/месяц/день, TURN_ID, скорость хода, форматирование дат. | getColonizationOfWastelandIsEnabled, getCanColonize_TechLevel, getNumOfDaysInMonth, getCurrentDate |
| [`Minimap.java`](../age/of/civilizations2/jakowski/lukasz/Minimap.java) | 254 | MenuElement | Мини-карта: текстура, клики, рамка текущего вида. | draw, getWidth, getHeight |
| [`Map.java`](../age/of/civilizations2/jakowski/lukasz/Map.java) | 333 | — | Данные карты: список карт (теги, масштаб, фон, пакеты континентов/регионов), фон, координаты, зум, скролл, тач-менеджер, бассейны. | loadSettings_ActiveMap, load_MinScale, load_DeleteStatusFile, initMapContinents |
| [`Graph_CircleDraw.java`](../age/of/civilizations2/jakowski/lukasz/Graph_CircleDraw.java) | 271 | — | График: круг рисование | draw, drawGraph, drawCircle100, drawCircle |
| [`Toast.java`](../age/of/civilizations2/jakowski/lukasz/Toast.java) | 263 | — | Всплывающие уведомления внизу/вверху экрана. | draw, getPosX, getPosY, getWidth |
| [`Game_Ages.java`](../age/of/civilizations2/jakowski/lukasz/Game_Ages.java) | 244 | — | игра эпохи | loadAges, updateLanguage, getYear, getAgeOfYear |
| [`Images.java`](../age/of/civilizations2/jakowski/lukasz/Images.java) | 221 | — | ID всех изображений UI (константы). |  |
| [`GraphData.java`](../age/of/civilizations2/jakowski/lukasz/GraphData.java) | 228 | — | Данные графика. | draw, drawAnimation, drawGraphData, drawCivButton |
| [`Image.java`](../age/of/civilizations2/jakowski/lukasz/Image.java) | 183 | — | Обёртка текстуры. | init, draw, draw2, draw3 |
| [`Graph_Vertical_Info.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Info.java) | 177 | — | График: вертикальный информация | updateMoveable, draw, resetMoveable, getTextSize |
| [`RTS.java`](../age/of/civilizations2/jakowski/lukasz/RTS.java) | 142 | — | Режим реального времени: скорость, пауза, шаг хода. | updateTimePast_AfterAction, updateTime, timePasted, getRequiredTime |
| [`Start_The_Game_Data.java`](../age/of/civilizations2/jakowski/lukasz/Start_The_Game_Data.java) | 120 | — | старт игра данные | updateData, getProvincesAlpha, getCapitalsAlpha, getWastelandAlpha |
| [`Graph_Vertical_Data_Value_PopulationByProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value_PopulationByProvinces.java) | 76 | Graph_Vertical_Data_Value | График: вертикальный данные значение население провинции | draw, getColor |
| [`Graph_Vertical_Data_Value_PopulationAllAroundTheWorld.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value_PopulationAllAroundTheWorld.java) | 76 | Graph_Vertical_Data_Value | График: вертикальный данные значение население все вокруг мир | draw, getColor |
| [`Graph_Vertical_Data_Value_TechnologyLevels.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value_TechnologyLevels.java) | 76 | Graph_Vertical_Data_Value | График: вертикальный данные значение технология уровни | draw, getColor |
| [`Graph_Vertical_Data_Value_ArmyPerCapita.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value_ArmyPerCapita.java) | 76 | Graph_Vertical_Data_Value | График: вертикальный данные значение армия Per Capita | draw, getColor |
| [`Graph_Vertical_Data_Value_Population.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value_Population.java) | 76 | Graph_Vertical_Data_Value | График: вертикальный данные значение население | draw, getColor |
| [`Graph_Vertical_Data_Value_Continent.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value_Continent.java) | 76 | Graph_Vertical_Data_Value | График: вертикальный данные значение континент | draw, getColor |
| [`Game_Cities.java`](../age/of/civilizations2/jakowski/lukasz/Game_Cities.java) | 99 | — | игра города | loadCities, readCities |
| [`Report_Data.java`](../age/of/civilizations2/jakowski/lukasz/Report_Data.java) | 100 | — | отчёт данные | getAttackersArmy, getAttackersArmy_Lost, getDefendersArmy, getDefendersArmy_Lost |
| [`MinimapInfo.java`](../age/of/civilizations2/jakowski/lukasz/MinimapInfo.java) | 57 | MenuElement | Данные мини-карты. | draw |
| [`Graph_Vertical_Data_Value.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Value.java) | 55 | — | График: вертикальный данные значение | draw, getValue, getHeight, setHeight |
| [`Game_Wonders.java`](../age/of/civilizations2/jakowski/lukasz/Game_Wonders.java) | 61 | — | игра чудеса | loadWonders, readWonders |
| [`Game_Mountains.java`](../age/of/civilizations2/jakowski/lukasz/Game_Mountains.java) | 48 | — | игра горы | loadMountains, readMountains |
| [`Timelapse_Capitals.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_Capitals.java) | 35 | Serializable | Таймлапс: столицы | updateCapital, getCapitalID |
| [`GraphLine.java`](../age/of/civilizations2/jakowski/lukasz/GraphLine.java) | 32 | — | Линия графика. | draw, getPosY, getWidth, setWidth |
| [`GraphLine_Simple.java`](../age/of/civilizations2/jakowski/lukasz/GraphLine_Simple.java) | 20 | GraphLine | график линия простой | draw |
| [`View_Type.java`](../age/of/civilizations2/jakowski/lukasz/View_Type.java) | 28 | — | Базовый класс режима отображения карты (заливка, легенда, hover). | enableViewAction, disableViewAction, updateActiveCivInfo_ExtraAction, updateActiveProvinceID_ExtraAction |
| [`Flag_OverlayImage.java`](../age/of/civilizations2/jakowski/lukasz/Flag_OverlayImage.java) | 20 | Serializable | Флаг: оверлей изображение |  |
| [`Graph_CircleData.java`](../age/of/civilizations2/jakowski/lukasz/Graph_CircleData.java) | 24 | — | График: круг данные | getDataID, getPercentage, setPercentage |
| [`Point_XY.java`](../age/of/civilizations2/jakowski/lukasz/Point_XY.java) | 28 | — | Point XY | getPosX, setPosX, getPosY, setPosY |
| [`Graph_Vertical_Data_Type.java`](../age/of/civilizations2/jakowski/lukasz/Graph_Vertical_Data_Type.java) | 19 | — | График: вертикальный данные тип |  |
| [`Timelapse_TurnChanges.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_TurnChanges.java) | 17 | Serializable | Таймлапс: ход изменения |  |
| [`Timelapse_Capital.java`](../age/of/civilizations2/jakowski/lukasz/Timelapse_Capital.java) | 15 | Serializable | Таймлапс: столица |  |
| [`Space.java`](../age/of/civilizations2/jakowski/lukasz/Space.java) | 13 | MenuElement | Пустой UI-элемент-отступ. |  |
| [`Flag_Division.java`](../age/of/civilizations2/jakowski/lukasz/Flag_Division.java) | 12 | — | Флаг: дивизия |  |
| [`Flag_Overlay.java`](../age/of/civilizations2/jakowski/lukasz/Flag_Overlay.java) | 12 | — | Флаг: оверлей |  |
| [`ColorPicker_AoC_Action.java`](../age/of/civilizations2/jakowski/lukasz/ColorPicker_AoC_Action.java) | 8 | — | цвет выбор Ao C действие |  |


## ИИ

Файлов: **55**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`AI_Style.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style.java) | 9355 | — | Базовый стиль ИИ: веса агрессии/дипломатии/экономики/армии и правила принятия решений. | getMinMilitarySpendings, turnOrders, turnOrdersEssential, diplomacyActions |
| [`AI.java`](../age/of/civilizations2/jakowski/lukasz/AI.java) | 1970 | — | Каркас ИИ: стили (AI_Style), фронты, экспансия на нейтралов, пересборка личности, константы терпения/статус-кво. | getAIStyle_ByTag, getAI_Style, resetNeutralProvincesWithSeaAccess, addNeutralProvincesWithSeaAccess |
| [`AI_Style_Tribal.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_Tribal.java) | 309 | AI_Style | Стиль ИИ: племенной | turnOrders, armyOverBudget_Disband, buildStartingBuildings, migration |
| [`AI_Style_Rebels.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_Rebels.java) | 204 | AI_Style | Стиль ИИ: повстанцы | turnOrders, buildStartingBuildings, tryRegroupArmy, attackProvince_Score |
| [`AI_Build_Invest_Development.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Invest_Development.java) | 131 | AI_Build | ИИ: строительство инвестиции развитие | build, getProvinceBuildScore, getMoney |
| [`AI_Build_Port.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Port.java) | 139 | AI_Build | ИИ: строительство порт | getNumOfAlreadyBuilt, build, getProvinceBuildScore, civRegion_HaveBuiltPort |
| [`AI_Build_Invest.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Invest.java) | 129 | AI_Build | ИИ: строительство инвестиции | build, getProvinceBuildScore, getMoney |
| [`AI_FrontLine_War.java`](../age/of/civilizations2/jakowski/lukasz/AI_FrontLine_War.java) | 136 | — | ИИ: фронт линия война | getWithCivID, getEnemyRating, getFrontScore, getFrontArmy |
| [`AI_Build_Workshop.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Workshop.java) | 112 | AI_Build | ИИ: строительство мастерская | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_Build_Farm.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Farm.java) | 112 | AI_Build | ИИ: строительство ферма | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_Build_Armoury.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Armoury.java) | 106 | AI_Build | ИИ: строительство арсенал | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_Build_Library.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Library.java) | 108 | AI_Build | ИИ: строительство библиотека | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_Build_Tower.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Tower.java) | 92 | AI_Build | ИИ: строительство башня | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_Style_CityState.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_CityState.java) | 94 | AI_Style | Стиль ИИ: город государство | turnOrders, sendGiftToFriendlyCiv, shouldChangeTypeOfGoverment, buildStartingBuildings |
| [`AI_Build_Supplies.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Supplies.java) | 90 | AI_Build | ИИ: строительство снабжение | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_Build_Fort.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Fort.java) | 90 | AI_Build | ИИ: строительство форт | getNumOfAlreadyBuilt, build, getProvinceBuildScore |
| [`AI_ProvinceInfo_War.java`](../age/of/civilizations2/jakowski/lukasz/AI_ProvinceInfo_War.java) | 50 | — | ИИ: провинция информация война | buildEnemyProvinces, enemyProvinceBordersWithOurTrueProvince, isOccupied, getArmy |
| [`AI_Style_Horde.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_Horde.java) | 39 | AI_Style | Стиль ИИ: орда | buildStartingBuildings |
| [`AI_Style_Communism.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_Communism.java) | 32 | AI_Style | Стиль ИИ: коммунизм | buildStartingBuildings |
| [`AI_Frontline_AI.java`](../age/of/civilizations2/jakowski/lukasz/AI_Frontline_AI.java) | 41 | — | ИИ: линия фронта AI | addFrontLine, getFrontLine, getFrontLinesSize, getWithCivID |
| [`AI_Style_Fascism.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_Fascism.java) | 32 | AI_Style | Стиль ИИ: фашизм | buildStartingBuildings |
| [`AI_Build_Option_Port.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Port.java) | 28 | AI_Build_Option | ИИ: строительство опция порт | getScore, getData |
| [`AI_Frontline.java`](../age/of/civilizations2/jakowski/lukasz/AI_Frontline.java) | 39 | — | ИИ: линия фронта | containsProvince, getFrontLineArmy |
| [`AI_ReleaseVassal.java`](../age/of/civilizations2/jakowski/lukasz/AI_ReleaseVassal.java) | 40 | — | ИИ: освобождение вассал | addProvince, haveProvince, removeProvinceID |
| [`AI_Build_Option.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option.java) | 25 | — | ИИ: строительство опция | getScore, getData, getMoney |
| [`AI_Build.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build.java) | 28 | — | ИИ: строительство | build, getNumOfAlreadyBuilt, getMoney |
| [`AI_Skills.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills.java) | 25 | — | ИИ: навыки | addPoint_CivID, getScore_Personality, getScore |
| [`AI_Build_Option_Invest_Development.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Invest_Development.java) | 16 | AI_Build_Option | ИИ: строительство опция инвестиции развитие | getScore, getData |
| [`AI_Build_Option_Supplies.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Supplies.java) | 21 | AI_Build_Option | ИИ: строительство опция снабжение | getScore, getData |
| [`AI_Build_Option_Workshop.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Workshop.java) | 21 | AI_Build_Option | ИИ: строительство опция мастерская | getScore, getData |
| [`AI_Build_Option_Library.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Library.java) | 20 | AI_Build_Option | ИИ: строительство опция библиотека | getScore, getData |
| [`AI_Build_Option_Armoury.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Armoury.java) | 20 | AI_Build_Option | ИИ: строительство опция арсенал | getScore, getData |
| [`AI_Build_Option_Tower.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Tower.java) | 20 | AI_Build_Option | ИИ: строительство опция башня | getScore, getData |
| [`AI_WarPreparations.java`](../age/of/civilizations2/jakowski/lukasz/AI_WarPreparations.java) | 19 | Serializable | ИИ: война подготовка |  |
| [`AI_Skills_Administration.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills_Administration.java) | 19 | AI_Skills | ИИ: навыки администрация | addPoint_CivID, getScore_Personality |
| [`AI_Build_Option_Fort.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Fort.java) | 18 | AI_Build_Option | ИИ: строительство опция форт | getScore, getData |
| [`AI_Skills_Production.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills_Production.java) | 19 | AI_Skills | ИИ: навыки производство | addPoint_CivID, getScore_Personality |
| [`AI_Skills_Military.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills_Military.java) | 19 | AI_Skills | ИИ: навыки военный | addPoint_CivID, getScore_Personality |
| [`AI_Skills_Taxation.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills_Taxation.java) | 19 | AI_Skills | ИИ: навыки налогообложение | addPoint_CivID, getScore_Personality |
| [`AI_Skills_Research.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills_Research.java) | 19 | AI_Skills | ИИ: навыки исследование | addPoint_CivID, getScore_Personality |
| [`AI_Skills_Eco.java`](../age/of/civilizations2/jakowski/lukasz/AI_Skills_Eco.java) | 19 | AI_Skills | ИИ: навыки экономика | addPoint_CivID, getScore_Personality |
| [`AI_Build_Option_Invest.java`](../age/of/civilizations2/jakowski/lukasz/AI_Build_Option_Invest.java) | 15 | AI_Build_Option | ИИ: строительство опция инвестиции | getScore, getData |
| [`AI_Style_Democracy.java`](../age/of/civilizations2/jakowski/lukasz/AI_Style_Democracy.java) | 14 | AI_Style | Стиль ИИ: демократия |  |
| [`AI_Influence.java`](../age/of/civilizations2/jakowski/lukasz/AI_Influence.java) | 17 | Serializable | ИИ: влияние |  |
| [`AI_BordersWith.java`](../age/of/civilizations2/jakowski/lukasz/AI_BordersWith.java) | 17 | — | ИИ: границы |  |
| [`AI_ProvinceValue.java`](../age/of/civilizations2/jakowski/lukasz/AI_ProvinceValue.java) | 17 | — | ИИ: провинция значение |  |
| [`AI_Rival.java`](../age/of/civilizations2/jakowski/lukasz/AI_Rival.java) | 15 | Serializable | ИИ: соперник |  |
| [`AI_ProvinceInfo.java`](../age/of/civilizations2/jakowski/lukasz/AI_ProvinceInfo.java) | 14 | — | ИИ: информация о провинции |  |
| [`AI_CivsInRange.java`](../age/of/civilizations2/jakowski/lukasz/AI_CivsInRange.java) | 15 | Serializable | ИИ: цивилизации в диапазон |  |
| [`AI_ArmyUpkeep.java`](../age/of/civilizations2/jakowski/lukasz/AI_ArmyUpkeep.java) | 13 | — | ИИ: армия содержание |  |
| [`AI_NeighProvinces_Army.java`](../age/of/civilizations2/jakowski/lukasz/AI_NeighProvinces_Army.java) | 14 | — | ИИ: сосед провинции армия |  |
| [`AI_NeighProvinces.java`](../age/of/civilizations2/jakowski/lukasz/AI_NeighProvinces.java) | 12 | — | ИИ: сосед провинции |  |
| [`AI_RegoupArmyData.java`](../age/of/civilizations2/jakowski/lukasz/AI_RegoupArmyData.java) | 12 | — | ИИ: Regoup армия данные |  |
| [`AI_Regroup.java`](../age/of/civilizations2/jakowski/lukasz/AI_Regroup.java) | 12 | — | ИИ: перегруппировка |  |
| [`AI_CivInfo_War.java`](../age/of/civilizations2/jakowski/lukasz/AI_CivInfo_War.java) | 10 | — | ИИ: цивилизация информация война |  |


## Редактор карт и сценариев

Файлов: **29**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Editor_ProvinceTexture.java`](../age/of/civilizations2/jakowski/lukasz/Editor_ProvinceTexture.java) | 646 | Editor | редактор провинция текстура | keyDown, touchDown, touchDragged, dragged |
| [`Editor_NeighboringProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Editor_NeighboringProvinces.java) | 529 | Editor | редактор соседний провинции | keyDown, updateProvince, toString |
| [`PalletOfCivsColors_Data.java`](../age/of/civilizations2/jakowski/lukasz/PalletOfCivsColors_Data.java) | 165 | — | палитра цивилизации цвета данные | setCivColor, readData, saveData, getDataSize |
| [`Editor_Regions.java`](../age/of/civilizations2/jakowski/lukasz/Editor_Regions.java) | 175 | Editor | редактор регионы | addUndo, popUndo, keyDown, actionUpdateRegionID |
| [`Editor_Colors.java`](../age/of/civilizations2/jakowski/lukasz/Editor_Colors.java) | 107 | Editor | редактор цвета | keyDown, toString |
| [`Scenario_GameData_Diplomacy2.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Diplomacy2.java) | 117 | Serializable | сценарий игра данные дипломатия 2 | buildData, getAlliances, getRelations, getPacts |
| [`RandomGame_Player.java`](../age/of/civilizations2/jakowski/lukasz/RandomGame_Player.java) | 113 | — | случайный игра игрок | getTag, setTag, getCapitalProvinceID, setCapitalProvinceID |
| [`Editor_GrowthRate.java`](../age/of/civilizations2/jakowski/lukasz/Editor_GrowthRate.java) | 91 | Editor | редактор рост оценка | addUndo, popUndo, keyDown, actionSave |
| [`Editor_ShiftPort.java`](../age/of/civilizations2/jakowski/lukasz/Editor_ShiftPort.java) | 99 | Editor | редактор сдвиг порт | keyDown, savePortPosition, toString |
| [`Editor_Continents.java`](../age/of/civilizations2/jakowski/lukasz/Editor_Continents.java) | 80 | Editor | редактор континенты | addUndo, popUndo, actionSave, keyDown |
| [`Editor_TerrainType.java`](../age/of/civilizations2/jakowski/lukasz/Editor_TerrainType.java) | 82 | Editor | редактор местность тип | addUndo, popUndo, keyDown, actionSave |
| [`Editor_MapRegions.java`](../age/of/civilizations2/jakowski/lukasz/Editor_MapRegions.java) | 80 | Editor | редактор карта регионы | addUndo, popUndo, actionSave, keyDown |
| [`Editor_LevelOfPort.java`](../age/of/civilizations2/jakowski/lukasz/Editor_LevelOfPort.java) | 61 | Editor | редактор уровень порт | keyDown, toString |
| [`Editor.java`](../age/of/civilizations2/jakowski/lukasz/Editor.java) | 65 | — | Базовый класс редактора. | keyDown, keyUp, touchDown, touchDragged |
| [`Scenario_GameData_Armies.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Armies.java) | 42 | Serializable | сценарий игра данные армии | buildData |
| [`Editor_Wasteland.java`](../age/of/civilizations2/jakowski/lukasz/Editor_Wasteland.java) | 46 | Editor | редактор необитаемые земли | keyDown, actionSave, toString |
| [`Editor_ShiftArmy.java`](../age/of/civilizations2/jakowski/lukasz/Editor_ShiftArmy.java) | 38 | Editor | редактор сдвиг армия | keyDown, saveArmyPosition, toString |
| [`Scenario_GameData_Diplomacy_AlliancesData.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Diplomacy_AlliancesData.java) | 43 | Serializable | сценарий игра данные дипломатия альянсы данные | getName, setName, getColor, setColor |
| [`Scenario_GameData_Diplomacy_Data.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Diplomacy_Data.java) | 41 | Serializable | сценарий игра данные дипломатия данные | getCivA, setCivA, getCivB, setCivB |
| [`Scenario_GameData_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Technology.java) | 31 | Serializable | сценарий игра данные технология | getPercentage, setPercentage, getContinentID, setContinentID |
| [`Scenario_GameData_Diplomacy_VassalsData.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Diplomacy_VassalsData.java) | 31 | Serializable | сценарий игра данные дипломатия вассалы данные | getCivID, setCivID, getCivLordID, setCivLordID |
| [`Scenario_GameData_Army.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Army.java) | 29 | Serializable | сценарий игра данные армия | getProvinceID, getCivID, getArmy |
| [`Scenario_GameData_Province2.java`](../age/of/civilizations2/jakowski/lukasz/Scenario_GameData_Province2.java) | 23 | Serializable | сценарий игра данные провинция 2 | buildProvinceOwners, getProvinceOwners |
| [`Undo_AssignProvinceCiv.java`](../age/of/civilizations2/jakowski/lukasz/Undo_AssignProvinceCiv.java) | 20 | — | Отмена: назначение провинция цивилизация | getProvinceID, getCivID |
| [`UndoOptimizationRegions.java`](../age/of/civilizations2/jakowski/lukasz/UndoOptimizationRegions.java) | 12 | — | Undo оптимизации регионов. |  |
| [`UndoGrowthRate.java`](../age/of/civilizations2/jakowski/lukasz/UndoGrowthRate.java) | 12 | — | Undo изменения роста населения. |  |
| [`UndoContinent.java`](../age/of/civilizations2/jakowski/lukasz/UndoContinent.java) | 12 | — | Undo изменения континента. |  |
| [`Editors.java`](../age/of/civilizations2/jakowski/lukasz/Editors.java) | 15 | — | Перечисление редакторов. |  |
| [`UndoTerrain.java`](../age/of/civilizations2/jakowski/lukasz/UndoTerrain.java) | 12 | — | Undo изменения рельефа. |  |


## Steam и платформа

Файлов: **1**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Steam_Game.java`](../age/of/civilizations2/jakowski/lukasz/Steam_Game.java) | 504 | — | Интеграция Steam: user, stats/achievements, remote storage, UGC (моды), leaderboards, friends, utils. | uploadVassals, uploadUnions, uploadAlliance, uploadScore |


## UI: экраны

Файлов: **584**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`MenuManager.java`](../age/of/civilizations2/jakowski/lukasz/MenuManager.java) | 11043 | — | Менеджер UI: List<List<SliderMenu>> со всеми экранами, порядок наложения, переключение eMAINMENU/eINGAME_*/eEDITOR_*, hover, попапы, позиции элементов, отрисовка. | getHover_ExtraPosX, getHover_ExtraPosY, getViewID, getInGameView |
| [`Menu_InGame_ProvinceInfo.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ProvinceInfo.java) | 2228 | SliderMenu | Карточка провинции: население, экономика, армия, строения, стабильность, действия. | updateTurnWidth, updateLanguage, getStabilityHoverOfProvince, updateBuildingsList |
| [`Menu_InGame_Build.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build.java) | 1435 | SliderMenu | Экран строительства в провинции (форты, порты, фермы, библиотеки...). | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_FlagAction_Budget.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Budget.java) | 880 | SliderMenu | Панель бюджета цивилизации: доходы/расходы, налоги, содержание армии. | getIncomeTaxation, draw, updateIncomeAndExpenses, onHovered |
| [`Menu_InGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame.java) | 1161 | SliderMenu | HUD в игре: верхняя/нижняя панели, скорость, календарь, кнопки перехода хода и открытия панелей. | updateOverBudget, updateLanguage, draw_Time, draw_Speed |
| [`Menu_InGame_FlagAction_Bot_Left.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Bot_Left.java) | 817 | SliderMenu | Нижняя левая панель действий цивилизации (флаг, бюджет, статистика). | draw, updateIncomeAndExpenses, onHovered, actionElement |
| [`Menu_InGame_FlagAction_Stats.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Stats.java) | 756 | SliderMenu | Панель статистики цивилизации в игре. | getImageScale, draw, onHovered, actionElement |
| [`Menu_CreateScenario_Settings.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Settings.java) | 786 | SliderMenu | Настройки сценария: стартовые армии, дата, сложность, лимиты. | updateLanguage, draw, disposePreview, updateStartingArmyInCapitals |
| [`Menu_InGame_WorldPopulation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_WorldPopulation.java) | 856 | SliderMenu | Экран населения мира. | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InitGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InitGame.java) | 619 | SliderMenu | Экран инициализации/загрузки игры. | draw, loadAssets, loadArmyBGImages |
| [`Menu_InGame_WarDetails.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_WarDetails.java) | 662 | SliderMenu | Детали войны: участники, счёт, потери. | updateLanguage, getImageScale3, getImageScale2, clickFlag |
| [`Menu_CreateNewGame_Options_v2.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_Options_v2.java) | 708 | SliderMenu | Создание игры: опции | updateLanguage, draw, drawScrollPos, clickFillTheMap |
| [`Menu_Settings_Province.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Settings_Province.java) | 645 | SliderMenu | Настройки: провинция | updateLanguage, draw, updateArmyWidth, actionElement |
| [`Menu_InGame_CivInfo_Stats_Actions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo_Stats_Actions.java) | 595 | SliderMenu | Экран в игре: цивилизация информация статистика действия | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_InGame_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Technology.java) | 624 | SliderMenu | Экран технологий: дерево, стоимость, изучение. | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_AddNewCondition.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_AddNewCondition.java) | 468 | SliderMenu | Редактор сценария: события добавить новый условие | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_Message_TradeRequest.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_TradeRequest.java) | 617 | SliderMenu | Экран в игре: сообщение торговля запрос | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_ProvinceAction.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ProvinceAction.java) | 644 | SliderMenu | Экран в игре: провинция действие | updateLanguage, draw, beginClip, clickMove |
| [`Menu_TerrainType_Add.java`](../age/of/civilizations2/jakowski/lukasz/Menu_TerrainType_Add.java) | 692 | SliderMenu | Экран/попап: местность тип добавить | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ContinentPopulation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ContinentPopulation.java) | 558 | SliderMenu | Экран в игре: континент население | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_TradeRequest.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TradeRequest.java) | 601 | SliderMenu | Экран в игре: торговля запрос | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_InGame_CivInfo_Stats_Decisions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo_Stats_Decisions.java) | 489 | SliderMenu | Экран в игре: цивилизация информация статистика решения | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_Main.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Main.java) | 516 | SliderMenu | Главное меню: новая игра, загрузка, редактор, настройки, выход. | updateLanguage, drawScrollPos, beginClip, draw |
| [`Menu_DiplomacyColors_Create_Relations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_DiplomacyColors_Create_Relations.java) | 477 | SliderMenu | Экран/попап: дипломатия цвета создание отношения | draw, actionElement |
| [`Menu_InGame_Alliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Alliance.java) | 559 | SliderMenu | Экран в игре: альянс | updateLanguage, getImageScale2, draw, drawScrollPos |
| [`Menu_InGame_Assimilate.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Assimilate.java) | 435 | SliderMenu | Экран в игре: ассимиляция | updateLanguage, draw, drawScrollPos, clickCancel |
| [`Menu_InGame_Message_Ultimatum.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_Ultimatum.java) | 450 | SliderMenu | Экран в игре: сообщение ультиматум | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_CivInfo_Stats_Diplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo_Stats_Diplomacy.java) | 510 | SliderMenu | Дипломатия в карточке цивилизации: отношения и доступные действия. | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_About.java`](../age/of/civilizations2/jakowski/lukasz/Menu_About.java) | 507 | SliderMenu | Экран/попап: об игре | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_MilitaryAlliances.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_MilitaryAlliances.java) | 513 | SliderMenu | Экран в игре: военный альянсы | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Edit.java) | 415 | SliderMenu | Редактор сценария: события редактирование | updateLanguage, draw, saveEditData, actionElement |
| [`Menu_RandomGame_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_Options.java) | 474 | SliderMenu | Экран/попап: случайный игра опции | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Ultimatum.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Ultimatum.java) | 460 | SliderMenu | Экран в игре: ультиматум | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_FlagAction_Bot_Right_Right.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Bot_Right_Right.java) | 472 | SliderMenu | Экран в игре: флаг действие низ правый правый | getViewName, draw, drawScrollPos, onHovered |
| [`Menu_Victory.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Victory.java) | 463 | SliderMenu | Экран/попап: победа | updateLanguage, updateTurnData, draw, getVictoryPosMax |
| [`Menu_CreateScenario_Events_AddNewOutcome.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_AddNewOutcome.java) | 332 | SliderMenu | Редактор сценария: события добавить новый результат | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_Rank.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Rank.java) | 500 | SliderMenu | Экран в игре: рейтинг | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateNewGame_Top.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_Top.java) | 395 | SliderMenu | Создание игры: верх | updateLanguage, draw, clickChooseScenario, actionElement |
| [`Menu_InGame_OfferAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_OfferAlliance.java) | 336 | SliderMenu | Экран в игре: предложение альянс | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_VicotryConditions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_VicotryConditions.java) | 358 | SliderMenu | Экран в игре: победа условия | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_CivInfo.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo.java) | 369 | SliderMenu | Экран в игре: информация о цивилизации | updateLanguage, getImageScale, draw, drawCloseButton |
| [`Menu_InGame_TakeLoan.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TakeLoan.java) | 323 | SliderMenu | Экран в игре: взятие кредит | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Leader_Edit_Data.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leader_Edit_Data.java) | 316 | SliderMenu | Экран/попап: лидер редактирование данные | updateLanguage, draw, actionElement |
| [`Menu_InGame_Event.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Event.java) | 300 | SliderMenu | Экран в игре: событие | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Build_Library.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Library.java) | 280 | SliderMenu | Экран в игре: строительство библиотека | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Build_Workshop.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Workshop.java) | 283 | SliderMenu | Экран в игре: строительство мастерская | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Build_Port.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Port.java) | 284 | SliderMenu | Экран в игре: строительство порт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_DeclareWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_DeclareWar.java) | 347 | SliderMenu | Экран в игре: объявление война | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Build_Fort.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Fort.java) | 278 | SliderMenu | Экран в игре: строительство форт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Build_Farm.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Farm.java) | 280 | SliderMenu | Экран в игре: строительство ферма | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_TradeRequest_Side.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TradeRequest_Side.java) | 370 | SliderMenu | Экран в игре: торговля запрос сторона | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_CreateCiv_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCiv_Flag.java) | 287 | SliderMenu | Экран/попап: создание цивилизация флаг | updateLanguage, draw, actionElement |
| [`Menu_InGame_Build_Supply.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Supply.java) | 272 | SliderMenu | Экран в игре: строительство снабжение | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_MapEditor_FormableCivs_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_Edit.java) | 393 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации редактирование | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Build_Armoury.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Armoury.java) | 275 | SliderMenu | Экран в игре: строительство арсенал | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_TransferControl.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TransferControl.java) | 327 | SliderMenu | Экран в игре: передача контроль | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_CivInfo_Stats.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo_Stats.java) | 345 | SliderMenu | Экран в игре: информация о цивилизации: статистика | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_InGame_SupportRebels.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_SupportRebels.java) | 325 | SliderMenu | Экран в игре: поддержка повстанцы | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Outliner.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Outliner.java) | 413 | SliderMenu | Экран в игре: обзор | updateLanguage, draw, drawScrollPos, drawCloseButton |
| [`Menu_InGame_Build_Tower.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Build_Tower.java) | 278 | SliderMenu | Экран в игре: строительство башня | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Civilize.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Civilize.java) | 270 | SliderMenu | Экран в игре: цивилизовать | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Plunder.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Plunder.java) | 286 | SliderMenu | Экран в игре: грабёж | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_NextPlayerTurn.java`](../age/of/civilizations2/jakowski/lukasz/Menu_NextPlayerTurn.java) | 390 | SliderMenu | Экран/попап: следующий игрок ход | updateLanguage, updateData, draw, clickBack |
| [`Menu_CreateScenario_Civilizations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Civilizations.java) | 324 | Menu_CreateScenario | Редактор сценария: цивилизации | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_PrepareForWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_PrepareForWar.java) | 304 | SliderMenu | Экран в игре: подготовка война | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_MoveCapital.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_MoveCapital.java) | 275 | SliderMenu | Экран в игре: перемещение столица | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Timeline.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Timeline.java) | 360 | SliderMenu | Экран/попап: таймлайн | updateLanguage, draw, draw_Time, actionElement |
| [`Menu_InGame_Festival.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Festival.java) | 273 | SliderMenu | Экран в игре: фестиваль | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_CallToArms.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_CallToArms.java) | 273 | SliderMenu | Экран в игре: сообщение призыв оружие | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Civilizations_Select_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Civilizations_Select_List.java) | 372 | SliderMenu | Редактор сценария: цивилизации выбор список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_RecruitedArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_RecruitedArmy.java) | 298 | SliderMenu | Экран в игре: набрано армия | getRecruitedArmy, getRecruitedArmy_2, updateLanguage, draw |
| [`Menu_InGame_Invest_Development.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Invest_Development.java) | 281 | SliderMenu | Экран в игре: инвестиции развитие | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_HRE.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_HRE.java) | 254 | SliderMenu | Экран в игре: СРИ | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Army.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Army.java) | 298 | SliderMenu | Экран армии: список армий цивилизации, перемещение, перегруппировка. | getRecruitedArmy, getRecruitedArmy_2, updateLanguage, draw |
| [`Menu_CreateCiv_Data.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCiv_Data.java) | 299 | SliderMenu | Экран/попап: создание цивилизация данные | updateLanguage, draw, actionElement, saveData |
| [`Menu_ChooseScenario_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ChooseScenario_Title.java) | 247 | SliderMenu | Экран/попап: выбор сценарий заголовок | updateLanguage, loadPreview, disposePreview, draw |
| [`Menu_InGame_Invest.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Invest.java) | 275 | SliderMenu | Экран в игре: инвестиции | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Migrate.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Migrate.java) | 237 | SliderMenu | Экран в игре: миграция | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Settings_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Settings_Options.java) | 333 | SliderMenu | Настройки: опции | updateLanguage, draw, actionElement |
| [`Menu_InGame_BuildingsConstructed.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_BuildingsConstructed.java) | 290 | SliderMenu | Экран в игре: строения Constructed | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_ConqueredProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ConqueredProvinces.java) | 290 | SliderMenu | Экран в игре: завоёвано провинции | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Cores_AddCiv_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_AddCiv_List.java) | 344 | SliderMenu | Редактор сценария: коры добавить цивилизация список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_CurrentWars_Info.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CurrentWars_Info.java) | 224 | SliderMenu | Экран в игре: текущий войны информация | updateLanguage, draw, drawScrollPos, drawCloseButton |
| [`Menu_InGame_Message_DeclarationOfIndependence_ByVassal.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_DeclarationOfIndependence_ByVassal.java) | 250 | SliderMenu | Экран в игре: сообщение объявление независимость вассал | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_ChangeGovernment.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ChangeGovernment.java) | 257 | SliderMenu | Экран в игре: изменение правительство | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Civilization_Info.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilization_Info.java) | 273 | SliderMenu | Инфо о цивилизации: информация | updateLanguage, draw, drawCloseButton, getImageScale |
| [`Menu_RandomGame_Civilizations_Select_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_Civilizations_Select_List.java) | 338 | SliderMenu | Экран/попап: случайный игра цивилизации выбор список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_Leaders_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leaders_Options.java) | 371 | SliderMenu | Экран/попап: лидеры опции | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_Civilization_Info_Stats.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilization_Info_Stats.java) | 264 | SliderMenu | Инфо о цивилизации: информация статистика | updateLanguage, draw, onHovered, actionElement |
| [`Menu_CreateScenario_AvailableProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_AvailableProvinces.java) | 252 | Menu_CreateScenario | Редактор сценария: доступный провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Destroy_Fort.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Fort.java) | 246 | SliderMenu | Экран в игре: уничтожение форт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_PeaceTreaty_Response_Demands.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Response_Demands.java) | 232 | SliderMenu | Экран/попап: мир договор ответ требования | updateLanguage, countPoints, draw, drawScrollPos |
| [`Menu_InGame_Message_PrepareForWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_PrepareForWar.java) | 256 | SliderMenu | Экран в игре: сообщение подготовка война | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Cores_SetUp.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_SetUp.java) | 244 | SliderMenu | Редактор сценария: коры установить Up | updateLanguage, draw, actionElement |
| [`Menu_InGame_HRE_VoteFor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_HRE_VoteFor.java) | 217 | SliderMenu | Экран в игре: СРИ голосование | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_GraphManager.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_GraphManager.java) | 252 | — | Экраны графиков статистики. | setActiveGraphID, updateGraph |
| [`Menu_CreateCiv_Overlay.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCiv_Overlay.java) | 223 | SliderMenu | Экран/попап: создание цивилизация оверлей | updateLanguage, updateScale, draw, actionElement |
| [`Menu_InGame_CallToArms.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CallToArms.java) | 263 | SliderMenu | Экран в игре: призыв оружие | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_UltimatumRefused.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_UltimatumRefused.java) | 242 | SliderMenu | Экран в игре: сообщение ультиматум отклонено | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_SendGift.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_SendGift.java) | 249 | SliderMenu | Экран в игре: отправить подарок | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_Gift.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_Gift.java) | 242 | SliderMenu | Экран в игре: сообщение подарок | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_Union.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_Union.java) | 248 | SliderMenu | Экран в игре: сообщение уния | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_ImproveRelations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ImproveRelations.java) | 244 | SliderMenu | Экран в игре: улучшение отношения | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_SendInsult.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_SendInsult.java) | 238 | SliderMenu | Экран в игре: отправить оскорбление | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_JoinAWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_JoinAWar.java) | 246 | SliderMenu | Экран в игре: вступление война | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_AddCiv_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_AddCiv_List.java) | 328 | SliderMenu | Редактор сценария: события добавить цивилизация список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_MilitaryAccess_Give.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_MilitaryAccess_Give.java) | 243 | SliderMenu | Экран в игре: военный доступ дать | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_MilitaryAccess_Ask.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_MilitaryAccess_Ask.java) | 243 | SliderMenu | Экран в игре: военный доступ запрос | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_ProclaimIndependence.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ProclaimIndependence.java) | 242 | SliderMenu | Экран в игре: провозглашение независимость | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_TransferControl.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_TransferControl.java) | 236 | SliderMenu | Экран в игре: сообщение передача контроль | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Editor_Unions_AddCiv_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Unions_AddCiv_List.java) | 334 | SliderMenu | Редактор: унии добавить цивилизация список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_NonAggressionPact.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_NonAggressionPact.java) | 243 | SliderMenu | Экран в игре: Non агрессия пакт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_DefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_DefensivePact.java) | 242 | SliderMenu | Экран в игре: оборонительный пакт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Assign_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Assign_List.java) | 233 | SliderMenu | Редактор сценария: назначение список | updateLanguage, draw, updateMenuElements_IsInView, getIsLoaded |
| [`Menu_InGame_CensusOfProvince.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CensusOfProvince.java) | 265 | SliderMenu | Экран в игре: перепись провинция | getImageScale, updateLanguage, draw, drawScrollPos |
| [`Menu_InGame_Message_CallToArms_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_CallToArms_Denied.java) | 231 | SliderMenu | Экран в игре: сообщение призыв оружие отказано | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Games.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Games.java) | 295 | SliderMenu | Экран/попап: список партий | updateLanguage, drawScrollPos, draw, clickNewGame |
| [`Menu_CreateScenario_Cores_AddCore_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_AddCore_List.java) | 313 | SliderMenu | Редактор сценария: коры добавить кор список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_RepayLoan.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_RepayLoan.java) | 239 | SliderMenu | Экран в игре: выплата кредит | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Editor_GameCivs_Edit2.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_GameCivs_Edit2.java) | 287 | SliderMenu | Редактор: игра цивилизации редактирование 2 | updateLanguage, draw, actionElement, saveData |
| [`Menu_InGame_Destroy_Port.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Port.java) | 236 | SliderMenu | Экран в игре: уничтожение порт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_CreateAVassal_SelectCiv_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_SelectCiv_List.java) | 316 | SliderMenu | Экран в игре: создание вассал выбор цивилизация список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_Message_Alliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_Alliance.java) | 233 | SliderMenu | Экран в игре: сообщение альянс | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Destroy_Tower.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Tower.java) | 234 | SliderMenu | Экран в игре: уничтожение башня | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_CreateAVassal.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal.java) | 317 | SliderMenu | Экран в игре: создание вассал | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_FlagAction_Bot_Right_Left.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Bot_Right_Left.java) | 271 | SliderMenu | Экран в игре: флаг действие низ правый левый | draw, onHovered, actionElement, setVisible |
| [`Menu_InGame_Destroy_Library.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Library.java) | 231 | SliderMenu | Экран в игре: уничтожение библиотека | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_ChooseScenario.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ChooseScenario.java) | 294 | SliderMenu | Экран/попап: выбор сценария | updateLanguage, draw, updateMenuElements_IsInView, getIsLoaded |
| [`Menu_InGame_Destroy_Farm.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Farm.java) | 231 | SliderMenu | Экран в игре: уничтожение ферма | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Graph.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Graph.java) | 268 | SliderMenu | Экран в игре: график | updateLanguage, draw, actionElement, getW |
| [`Menu_InGame_Destroy_Workshop.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Workshop.java) | 230 | SliderMenu | Экран в игре: уничтожение мастерская | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Destroy_Supply.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Supply.java) | 230 | SliderMenu | Экран в игре: уничтожение снабжение | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_History.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_History.java) | 194 | SliderMenu | Экран в игре: история | updateLanguage, getImageScale2, draw, drawScrollPos |
| [`Menu_InGame_Destroy_Armoury.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Destroy_Armoury.java) | 229 | SliderMenu | Экран в игре: уничтожение арсенал | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_FlagAction.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction.java) | 280 | SliderMenu | Экран в игре: флаг действие | getWindowWidth, draw, onHovered, clickOptions |
| [`Menu_InGame_Message_MilitaryAccess_Ask.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_MilitaryAccess_Ask.java) | 228 | SliderMenu | Экран в игре: сообщение военный доступ запрос | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_Independence_Ask.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_Independence_Ask.java) | 228 | SliderMenu | Экран в игре: сообщение независимость запрос | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_MilitaryAccess_Give.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_MilitaryAccess_Give.java) | 228 | SliderMenu | Экран в игре: сообщение военный доступ дать | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_NonAggressionPact.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_NonAggressionPact.java) | 228 | SliderMenu | Экран в игре: сообщение Non агрессия пакт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Message_DefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_DefensivePact.java) | 228 | SliderMenu | Экран в игре: сообщение оборонительный пакт | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_ServiceRibbon_Editor_Edit_Overlay.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ServiceRibbon_Editor_Edit_Overlay.java) | 173 | SliderMenu | Экран/попап: сервис лента редактор редактирование оверлей | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Message_OfferVasalization.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Message_OfferVasalization.java) | 228 | SliderMenu | Экран в игре: сообщение предложение вассализация | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_SelectCiv_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectCiv_List.java) | 303 | SliderMenu | Редактор сценария: события выбор цивилизация список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_LiberateAVassal.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_LiberateAVassal.java) | 225 | SliderMenu | Экран в игре: освобождение вассал | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_FormUnion.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FormUnion.java) | 222 | SliderMenu | Экран в игре: форма уния | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_PeaceTreaty.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty.java) | 243 | SliderMenu | Экран/попап: мир договор | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_OfferVasalization.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_OfferVasalization.java) | 222 | SliderMenu | Экран в игре: предложение вассализация | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_RandomGame_Settings.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_Settings.java) | 281 | SliderMenu | Экран/попап: случайный игра настройки | draw, drawScrollPos, getW, actionElement |
| [`Menu_InGame_Tribute.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Tribute.java) | 226 | SliderMenu | Экран в игре: дань | updateLanguage, draw, drawScrollPos, updateIncomeFromVassals |
| [`Menu_MapEditor_FormableCivs_SelectFormable_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_SelectFormable_List.java) | 313 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации выбор формируемый список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_RandomGame_WastelandMap.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_WastelandMap.java) | 255 | SliderMenu | Экран/попап: случайный игра необитаемые земли карта | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_View_Buildings.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Buildings.java) | 203 | SliderMenu | Экран в игре: вид строения | countNumOfBuidligns, updateLanguage, drawScrollPos, draw |
| [`Menu_MapEditor_Edit_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Edit_Options.java) | 255 | SliderMenu | Экран/попап: карта редактор редактирование опции | updateLanguage, draw, actionElement |
| [`Menu_InGame_FormableCiv_Provinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FormableCiv_Provinces.java) | 237 | SliderMenu | Экран в игре: формируемый цивилизация провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Report.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Report.java) | 235 | SliderMenu | Экран в игре: отчёт | getRandomBattleName, draw, drawScrollPos, actionElement |
| [`Menu_PeaceTreaty_Response.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Response.java) | 229 | SliderMenu | Экран/попап: мир договор ответ | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CustomizeAlliance_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CustomizeAlliance_Options.java) | 226 | SliderMenu | Экран/попап: настройка альянс опции | updateLanguage, actionElement |
| [`Menu_MapEditor_Terrain_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Terrain_List.java) | 173 | SliderMenu | Экран/попап: карта редактор местность список | draw, actionElement |
| [`Menu_InGame_CreateAVassal_Info.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_Info.java) | 211 | SliderMenu | Экран в игре: создание вассал информация | updateLanguage, draw, drawCloseButton, getImageScale |
| [`Menu_InGame_KickFromAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_KickFromAlliance.java) | 216 | SliderMenu | Экран в игре: исключение From альянс | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_RTO2.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_RTO2.java) | 192 | SliderMenu | Экран в игре: RTO | updateLanguage, draw, getLoadPosY, getLoadHeight |
| [`Menu_InGame_DeclarationOfIndependence.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_DeclarationOfIndependence.java) | 211 | SliderMenu | Экран в игре: объявление независимость | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_View_Unrest.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Unrest.java) | 200 | SliderMenu | Экран в игре: вид беспорядки | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_PeaceTreaty_Provinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Provinces.java) | 173 | SliderMenu | Экран/попап: мир договор провинции | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_LeaveAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_LeaveAlliance.java) | 212 | SliderMenu | Экран в игре: выход альянс | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Civilizations_Info_Players.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilizations_Info_Players.java) | 221 | SliderMenu | Инфо о цивилизации: информация игроки | updateLanguage, draw, actionElement, drawScrollPos |
| [`Menu_InGame_Abadon.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Abadon.java) | 211 | SliderMenu | Экран в игре: отказ | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_View_Population.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Population.java) | 184 | SliderMenu | Экран в игре: вид население | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_WarPreparations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_WarPreparations.java) | 197 | SliderMenu | Экран в игре: война подготовка | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_AllianceInfo.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_AllianceInfo.java) | 131 | SliderMenu | Экран в игре: альянс информация | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_InGame_View_ProvinceValue.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_ProvinceValue.java) | 177 | SliderMenu | Экран в игре: вид провинция значение | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_War.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_War.java) | 131 | SliderMenu | Экран в игре: война | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_MapEditor_FormableCivs_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_Options.java) | 268 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации опции | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_DiplomacyColors_Create_Colors.java`](../age/of/civilizations2/jakowski/lukasz/Menu_DiplomacyColors_Create_Colors.java) | 177 | SliderMenu | Экран/попап: дипломатия цвета создание цвета | draw, actionElement |
| [`Menu_InGame_MapModes.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_MapModes.java) | 215 | SliderMenu | Экран в игре: карта режимы | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_ManageDiplomacy_Alliances.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Alliances.java) | 185 | SliderMenu | Экран/попап: управление дипломатия альянсы | updateLanguage, draw, actionElement |
| [`Menu_RandomGame_Players.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_Players.java) | 196 | SliderMenu | Экран/попап: случайный игра игроки | updateLanguage, draw, actionElement, drawScrollPos |
| [`Menu_Editor_GameCivs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_GameCivs.java) | 267 | SliderMenu | Редактор: игра цивилизации | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_View_Income.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Income.java) | 172 | SliderMenu | Экран в игре: вид доход | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_View_GrowthRate.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_GrowthRate.java) | 165 | SliderMenu | Экран в игре: вид рост оценка | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_CreateAVassal_Info_Stats.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_Info_Stats.java) | 210 | SliderMenu | Экран в игре: создание вассал информация статистика | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_InGame_Loans.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Loans.java) | 182 | SliderMenu | Экран в игре: кредиты | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_HRE_Princes.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_HRE_Princes.java) | 170 | SliderMenu | Редактор сценария: СРИ князья | updateLanguage, draw, actionElement |
| [`Menu_MapEditor_WastelandMaps_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_WastelandMaps_Edit.java) | 250 | SliderMenu | Экран/попап: карта редактор необитаемые земли карты редактирование | updateLanguage, draw, actionElement, saveData |
| [`Menu_InGame_View_ProvinceStability.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_ProvinceStability.java) | 163 | SliderMenu | Экран в игре: вид провинция стабильность | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_CreateNewGame_Options_Pallets.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_Options_Pallets.java) | 191 | SliderMenu | Создание игры: опции палитры | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_View_Army.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Army.java) | 176 | SliderMenu | Режим карты «армия»: численность войск по провинциям. | getButtonHeight, updateLanguage, drawScrollPos, draw |
| [`Menu_InGame_View_Development.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Development.java) | 163 | SliderMenu | Экран в игре: вид развитие | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_View_Economy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Economy.java) | 165 | SliderMenu | Экран в игре: вид экономика | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_MapEditor_FormableCivs_SelectClaimant_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_SelectClaimant_List.java) | 253 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации выбор претендент список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_Leader_Edit_SelectCiv_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leader_Edit_SelectCiv_List.java) | 253 | SliderMenu | Экран/попап: лидер редактирование выбор цивилизация список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGame_View_Recruitable.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Recruitable.java) | 166 | SliderMenu | Режим карты «доступный набор»: сколько армии можно набрать. | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_View_Diseases.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Diseases.java) | 173 | SliderMenu | Экран в игре: вид болезни | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_View_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Happiness.java) | 163 | SliderMenu | Экран в игре: вид счастье | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_CreateScenario_Events_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_List.java) | 165 | SliderMenu | Редактор сценария: события список | updateLanguage, draw, actionElement |
| [`Menu_InGame_Messages.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Messages.java) | 121 | SliderMenu | Экран в игре: сообщения | updateLanguage, draw, actionElement |
| [`Menu_Civilization_Info_Diplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilization_Info_Diplomacy.java) | 214 | SliderMenu | Инфо о цивилизации: информация дипломатия | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_CreateScenario_Assign.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Assign.java) | 197 | Menu_CreateScenario | Редактор сценария: назначение | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ManageDiplomacy_Vassals.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Vassals.java) | 138 | SliderMenu | Экран/попап: управление дипломатия вассалы | updateLanguage, draw, actionElement |
| [`Menu_InGame_BuildConfirm.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_BuildConfirm.java) | 197 | SliderMenu | Экран в игре: строительство Confirm | draw, drawScrollPos, actionElement, setVisible |
| [`Menu_MapEditor_FormableCivs_Claimants.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_Claimants.java) | 178 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации претенденты | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_TerrainTypes_Editor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_TerrainTypes_Editor.java) | 157 | SliderMenu | Экран/попап: местность типы редактор | updateLanguage, draw, actionElement |
| [`Menu_InGame_ActionInfo_Recruit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_Recruit.java) | 184 | SliderMenu | Экран в игре: действие информация набор | actionElement, onHovered |
| [`Menu_InGame_CreateAVassal_Civ.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_Civ.java) | 150 | SliderMenu | Экран в игре: создание вассал цивилизация | updateLanguage, draw, actionElement |
| [`Menu_InGame_Playlist.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Playlist.java) | 197 | SliderMenu | Экран в игре: плейлист | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_InGame_Dices.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Dices.java) | 153 | SliderMenu | Экран в игре: кубики | updateLanguage, getPosX2, getPosY2, getWidth2 |
| [`Menu_ManageDiplomacy_Relations_Interactive.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Relations_Interactive.java) | 170 | SliderMenu | Экран/попап: управление дипломатия отношения интерактивный | updateLanguage, draw, actionElement, updateRelation |
| [`Menu_Dialog.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Dialog.java) | 208 | SliderMenu | Экран/попап: диалог | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_CivInfo_Stats_Opinions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo_Stats_Opinions.java) | 205 | SliderMenu | Экран в игре: цивилизация информация статистика мнения | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_ServiceRibbon_Editor_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ServiceRibbon_Editor_Edit.java) | 178 | SliderMenu | Экран/попап: сервис лента редактор редактирование | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Relation_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Relation_Low.java) | 153 | SliderMenu | Редактор сценария: события условие отношение низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Relation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Relation.java) | 153 | SliderMenu | Редактор сценария: события условие отношение | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateNewGame_Scenarios.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_Scenarios.java) | 162 | SliderMenu | Создание игры: сценарии | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Editor_Scenarios.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Scenarios.java) | 181 | SliderMenu | Редактор: сценарии | updateLanguage, draw, actionElement |
| [`Menu_InGame_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Options.java) | 194 | SliderMenu | Экран в игре: опции | updateLanguage, draw, clickBack, actionElement |
| [`Menu_ManageDiplomacy_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Options.java) | 180 | SliderMenu | Экран/попап: управление дипломатия опции | updateLanguage, draw, actionElement |
| [`Menu_CreateScenario_Events_Date_Calendar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Date_Calendar.java) | 163 | SliderMenu | Редактор сценария: события дата календарь | draw, actionElement |
| [`Menu_Leader_Edit_Civs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leader_Edit_Civs.java) | 163 | SliderMenu | Экран/попап: лидер редактирование цивилизации | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_CurrentWars.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CurrentWars.java) | 174 | SliderMenu | Экран в игре: текущий войны | updateLanguage, draw, drawScrollPos, drawCloseButton |
| [`Menu_InGame_View_Goverments.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Goverments.java) | 154 | SliderMenu | Экран в игре: вид правительства | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_CreateScenario_Events_Cond_NonAggreession.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NonAggreession.java) | 140 | SliderMenu | Редактор сценария: события условие Non агрессия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_MilitaryAccess.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_MilitaryAccess.java) | 140 | SliderMenu | Редактор сценария: события условие военный доступ | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Independence.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Independence.java) | 140 | SliderMenu | Редактор сценария: события условие независимость | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_IsVassal_OfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_IsVassal_OfCiv.java) | 140 | SliderMenu | Редактор сценария: события условие вассал цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Defensive.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Defensive.java) | 140 | SliderMenu | Редактор сценария: события условие оборонительный | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Allies.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Allies.java) | 140 | SliderMenu | Редактор сценария: события условие союзники | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_AtWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_AtWar.java) | 140 | SliderMenu | Редактор сценария: события условие война | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_VicotryConditions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_VicotryConditions.java) | 173 | SliderMenu | Экран/попап: победа условия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_PalletOfColors_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_PalletOfColors_List.java) | 147 | SliderMenu | Редактор сценария: палитра цвета список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Calendar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Calendar.java) | 157 | SliderMenu | Экран/попап: календарь | draw, actionElement |
| [`Menu_MapEditor_FormableCivs_SelectFormable_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_SelectFormable_Alphabet.java) | 212 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации выбор формируемый алфавит | updateLanguage, actionElement |
| [`Menu_InGame_View_Terrain.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Terrain.java) | 151 | SliderMenu | Экран в игре: вид местность | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_InGame_View_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_View_Technology.java) | 143 | SliderMenu | Экран в игре: вид технология | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_CreateScenario_Events_Date.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Date.java) | 184 | SliderMenu | Редактор сценария: события дата | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateCity.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCity.java) | 206 | SliderMenu | Экран/попап: создание город | updateLanguage, draw, actionElement, updatePosX |
| [`Menu_Editor_Civilizations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Civilizations.java) | 210 | SliderMenu | Редактор: цивилизации | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_InGameWonders.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGameWonders.java) | 141 | SliderMenu | Экран/попап: в игра чудеса | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_SetUp_StartingMoney.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_SetUp_StartingMoney.java) | 165 | Menu_CreateScenario | Редактор сценария: установить Up стартовый деньги | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_ControlsProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_ControlsProvinces.java) | 144 | SliderMenu | Редактор сценария: события условие контроль провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_OccupiedProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_OccupiedProvinces.java) | 144 | SliderMenu | Редактор сценария: события условие оккупированный провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Wars.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Wars.java) | 152 | SliderMenu | Экран в игре: войны | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_Cond_IsCapital.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_IsCapital.java) | 144 | SliderMenu | Редактор сценария: события условие столица | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_HaveArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_HaveArmy.java) | 144 | SliderMenu | Редактор сценария: события условие армия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_HaveCore.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_HaveCore.java) | 144 | SliderMenu | Редактор сценария: события условие кор | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_SetUpArmy_Civs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_SetUpArmy_Civs.java) | 155 | SliderMenu | Редактор сценария: установить Up армия цивилизации | addCiv, updateLanguage, draw, drawScrollPos |
| [`Menu_SelectLanguage.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectLanguage.java) | 185 | SliderMenu | Экран/попап: выбор язык | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_StartTheGame_Reverse.java`](../age/of/civilizations2/jakowski/lukasz/Menu_StartTheGame_Reverse.java) | 188 | SliderMenu | Экран/попап: старт игра обратный | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Civilizations_Select_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Civilizations_Select_Alphabet.java) | 201 | SliderMenu | Редактор сценария: цивилизации выбор алфавит | updateLanguage, actionElement |
| [`Menu_Leaders_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leaders_Alphabet.java) | 200 | SliderMenu | Экран/попап: лидеры алфавит | updateLanguage, actionElement |
| [`Menu_CreateCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCiv.java) | 147 | SliderMenu | Экран/попап: создание цивилизация | updateLanguage, draw, actionElement |
| [`Menu_CreateScenario_Civilizations_Suggest.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Civilizations_Suggest.java) | 196 | SliderMenu | Редактор сценария: цивилизации предложить | updateMenuElements_IsInView, getIsLoaded, loadFlag, getFlagID |
| [`Menu_PalletOfCivilizationsColors.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PalletOfCivilizationsColors.java) | 179 | SliderMenu | Экран/попап: палитра цивилизации цвета | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_PeaceTreaty_Scores.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Scores.java) | 141 | SliderMenu | Экран/попап: мир договор очки | updateLanguage, draw, drawScrollPos, drawCloseButton |
| [`Menu_CreateScenario_Events_Cond_DecisionTaken.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_DecisionTaken.java) | 143 | SliderMenu | Редактор сценария: события условие решение взято | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_LoadGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_LoadGame.java) | 160 | SliderMenu | Экран/попап: загрузка партии | updateLanguage, draw, actionElement, onHovered |
| [`Menu_Civilizations_Info_Statistics.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilizations_Info_Statistics.java) | 158 | SliderMenu | Инфо о цивилизации: информация статистика | updateLanguage, draw, actionElement, drawScrollPos |
| [`Menu_InGame_EndOfGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_EndOfGame.java) | 149 | SliderMenu | Экран в игре: конец игра | getElementWidth, updateLanguage, draw, clickBack |
| [`Menu_RandomGame_Civilizations_Select_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_Civilizations_Select_Alphabet.java) | 184 | SliderMenu | Экран/попап: случайный игра цивилизации выбор алфавит | updateLanguage, actionElement |
| [`Menu_MapEditor_FormableCivs_SelectClaimant_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_SelectClaimant_Alphabet.java) | 184 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации выбор претендент алфавит | updateLanguage, actionElement |
| [`Menu_InGame_CreateAVassal_SelectCiv_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_SelectCiv_Alphabet.java) | 184 | SliderMenu | Экран в игре: создание вассал выбор цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_CreateScenario_Events_AddCiv_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_AddCiv_Alphabet.java) | 184 | SliderMenu | Редактор сценария: события добавить цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_ManageDiplomacy_Vassals_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Vassals_List.java) | 139 | SliderMenu | Экран/попап: управление дипломатия вассалы список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Cores_AddCiv_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_AddCiv_Alphabet.java) | 184 | SliderMenu | Редактор сценария: коры добавить цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_Leader_Edit_SelectCiv_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leader_Edit_SelectCiv_Alphabet.java) | 184 | SliderMenu | Экран/попап: лидер редактирование выбор цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_CreateScenario_Events_Out_JoinAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_JoinAlliance.java) | 117 | SliderMenu | Редактор сценария: события результат вступление альянс | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CustomizeAlliance_AddCivilization_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CustomizeAlliance_AddCivilization_List.java) | 141 | SliderMenu | Экран/попап: настройка альянс добавить цивилизация список | updateLanguage, actionElement |
| [`Menu_CreateNewGame_MapModes.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_MapModes.java) | 144 | SliderMenu | Создание игры: карта режимы | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Civilizations_Ideologies.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Civilizations_Ideologies.java) | 170 | SliderMenu | Редактор сценария: цивилизации идеологии | updateMenuElements_IsInView, getIsLoaded, loadFlag, getFlagID |
| [`Menu_CreateScenario_Events_Cond_NumOfProvinces_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfProvinces_Low.java) | 139 | SliderMenu | Редактор сценария: события условие количество провинции низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfProvinces.java) | 139 | SliderMenu | Редактор сценария: события условие количество провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_CreateAVassal_MapModes.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_MapModes.java) | 141 | SliderMenu | Экран в игре: создание вассал карта режимы | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_Editor_Unions_AddCiv_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Unions_AddCiv_Alphabet.java) | 182 | SliderMenu | Редактор: унии добавить цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_CreateScenario_Events_Cond_Ideology.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Ideology.java) | 132 | SliderMenu | Редактор сценария: события условие идеология | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_CreateAVassal.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_CreateAVassal.java) | 110 | SliderMenu | Редактор сценария: события результат создание вассал | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_ChangeOwner.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_ChangeOwner.java) | 110 | SliderMenu | Редактор сценария: события результат изменение владелец | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Occupy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Occupy.java) | 110 | SliderMenu | Редактор сценария: события результат оккупация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_HRE.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_HRE.java) | 179 | SliderMenu | Редактор сценария: СРИ | updateLanguage, draw, actionElement |
| [`Menu_Dialog_Diplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Dialog_Diplomacy.java) | 93 | SliderMenu | Экран/попап: диалог дипломатия | updateLanguage, draw, actionElement |
| [`Menu_CreateScenario_Events_Cond_NumOfNeighbors_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfNeighbors_Low.java) | 133 | SliderMenu | Редактор сценария: события условие количество соседи низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfVassals_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfVassals_Low.java) | 133 | SliderMenu | Редактор сценария: события условие количество вассалы низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfNeighbors.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfNeighbors.java) | 133 | SliderMenu | Редактор сценария: события условие количество соседи | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfAllies_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfAllies_Low.java) | 133 | SliderMenu | Редактор сценария: события условие количество союзники низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Technology_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Technology_Low.java) | 133 | SliderMenu | Редактор сценария: события условие технология низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_TechnologyLevels_Continents.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_TechnologyLevels_Continents.java) | 120 | SliderMenu | Редактор сценария: технология уровни континенты | updateLanguage, getContinentsOfCiv, draw, actionElement |
| [`Menu_CreateScenario_Events_Cond_NumOfVassals.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfVassals.java) | 133 | SliderMenu | Редактор сценария: события условие количество вассалы | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Population_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Population_Low.java) | 133 | SliderMenu | Редактор сценария: события условие население низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfWars_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfWars_Low.java) | 133 | SliderMenu | Редактор сценария: события условие количество войны низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfAllies.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfAllies.java) | 133 | SliderMenu | Редактор сценария: события условие количество союзники | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Technology.java) | 133 | SliderMenu | Редактор сценария: события условие технология | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Treasury_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Treasury_Low.java) | 133 | SliderMenu | Редактор сценария: события условие казна низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Population.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Population.java) | 133 | SliderMenu | Редактор сценария: события условие население | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfWars.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfWars.java) | 133 | SliderMenu | Редактор сценария: события условие количество войны | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfUnits.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfUnits.java) | 133 | SliderMenu | Редактор сценария: события условие количество юниты | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_NumOfUnits_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_NumOfUnits_Low.java) | 133 | SliderMenu | Редактор сценария: события условие количество юниты низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Treasury.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Treasury.java) | 133 | SliderMenu | Редактор сценария: события условие казна | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Happiness_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Happiness_Low.java) | 133 | SliderMenu | Редактор сценария: события условие счастье низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Economy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Economy.java) | 133 | SliderMenu | Редактор сценария: события условие экономика | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ManageDiplomacy_MilitaryAccess_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_MilitaryAccess_List.java) | 125 | SliderMenu | Экран/попап: управление дипломатия военный доступ список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_Cond_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Happiness.java) | 133 | SliderMenu | Редактор сценария: события условие счастье | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ManageDiplomacy_DefensivePact_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_DefensivePact_List.java) | 125 | SliderMenu | Экран/попап: управление дипломатия оборонительный пакт список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_ManageDiplomacy_Guarantee_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Guarantee_List.java) | 125 | SliderMenu | Экран/попап: управление дипломатия гарантия список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_ManageDiplomacy_Pacts_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Pacts_List.java) | 125 | SliderMenu | Экран/попап: управление дипломатия пакты список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_ManageDiplomacy_Truces_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Truces_List.java) | 125 | SliderMenu | Экран/попап: управление дипломатия перемирия список | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_WastelandMap_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_WastelandMap_List.java) | 173 | SliderMenu | Редактор сценария: необитаемые земли карта список | updateLanguage, updateMenuElements_IsInView, draw, actionElement |
| [`Menu_Editor_GameCivs_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_GameCivs_Alphabet.java) | 175 | SliderMenu | Редактор: игра цивилизации алфавит | updateLanguage, actionElement |
| [`Menu_Editor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor.java) | 141 | SliderMenu | Редактор: меню редактор | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_IncRelation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_IncRelation.java) | 111 | SliderMenu | Редактор сценария: события результат увеличение отношение | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_FormableCivs_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_Alphabet.java) | 164 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации алфавит | updateLanguage, actionElement |
| [`Menu_CreateScenario_TechnologyLevels.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_TechnologyLevels.java) | 151 | Menu_CreateScenario | Редактор сценария: технология уровни | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Economy_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Economy_Low.java) | 133 | SliderMenu | Редактор сценария: события условие экономика низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Independence.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Independence.java) | 106 | SliderMenu | Редактор сценария: события результат независимость | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_NonAggression.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_NonAggression.java) | 106 | SliderMenu | Редактор сценария: события результат Non агрессия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_MilitaryAccess.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_MilitaryAccess.java) | 106 | SliderMenu | Редактор сценария: события результат военный доступ | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Defensive.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Defensive.java) | 106 | SliderMenu | Редактор сценария: события результат оборонительный | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_Move.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_Move.java) | 147 | SliderMenu | Экран в игре: действие информация перемещение | actionElement, onHovered |
| [`Menu_CreateScenario_Events_Out_DecRelation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_DecRelation.java) | 105 | SliderMenu | Редактор сценария: события результат уменьшение отношение | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu.java`](../age/of/civilizations2/jakowski/lukasz/Menu.java) | 247 | — | enum всех экранов и состояний UI (eMAINMENU, eINGAME_*, eEDITOR_*, ...). |  |
| [`Menu_InGame_CityFounded.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CityFounded.java) | 110 | SliderMenu | Экран в игре: город основано | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_CreateScenario_Cores.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores.java) | 153 | Menu_CreateScenario | Редактор сценария: коры | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Happiness.java) | 151 | Menu_CreateScenario | Редактор сценария: счастье | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_CapitalMoved.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CapitalMoved.java) | 110 | SliderMenu | Экран в игре: столица перемещено | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_StartTheGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_StartTheGame.java) | 167 | SliderMenu | Экран/попап: старт игра | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_FlagEditor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_FlagEditor.java) | 186 | SliderMenu | Экран/попап: флаг редактор | updateLanguage, drawTitle, drawIconFlag, actionElement |
| [`Menu_InGame_VassalReleased.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_VassalReleased.java) | 110 | SliderMenu | Экран в игре: вассал освобождено | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_Scenario_Age.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Scenario_Age.java) | 158 | SliderMenu | Экран/попап: сценарий эпоха | updateLanguage, draw, actionElement, onBackPressed |
| [`MenuElement_Hover_v2.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2.java) | 190 | MenuElement_Hover | Составная подсказка v2. | resetAnimation, resetAnimation_2, draw, drawAlwaysOver |
| [`Menu_Editor_Cities.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Cities.java) | 156 | SliderMenu | Редактор: города | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Civilizations_Info_Diplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilizations_Info_Diplomacy.java) | 129 | SliderMenu | Инфо о цивилизации: информация дипломатия | updateLanguage, draw, actionElement, drawScrollPos |
| [`Menu_MapEditor_OptimizationRegions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_OptimizationRegions.java) | 168 | SliderMenu | Экран/попап: карта редактор оптимизация регионы | updateLanguage, draw, actionElement, centerToActiveRegionID |
| [`Menu_InGame_FormableCiv_Provinces_TodayPartOf.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FormableCiv_Provinces_TodayPartOf.java) | 135 | SliderMenu | Экран в игре: формируемый цивилизация провинции сегодня часть | updateLanguage, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_Cond_ControlledByPlayer.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_ControlledByPlayer.java) | 120 | SliderMenu | Редактор сценария: события условие контролируемый игрок | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_ArmySeaBoxes_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ArmySeaBoxes_Edit.java) | 105 | SliderMenu | Экран/попап: карта редактор армия море боксы редактирование | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_IsPartOfHRE.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_IsPartOfHRE.java) | 120 | SliderMenu | Редактор сценария: события условие часть СРИ | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_CivExist.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_CivExist.java) | 120 | SliderMenu | Редактор сценария: события условие цивилизация существование | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_IsVassal.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_IsVassal.java) | 120 | SliderMenu | Редактор сценария: события условие вассал | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_TechnologyLevels_Civs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_TechnologyLevels_Civs.java) | 118 | SliderMenu | Редактор сценария: технология уровни цивилизации | updateLanguage, drawScrollPos, draw, actionElement |
| [`Menu_CreateScenario_Events_Cond_IsAtWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_IsAtWar.java) | 120 | SliderMenu | Редактор сценария: события условие война | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Slider_Recruit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Slider_Recruit.java) | 168 | SliderMenu | Слайдер набора армии в провинции. | updateLanguage, draw, beginClip, onHovered |
| [`Menu_MapEditor_WastelandMaps.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_WastelandMaps.java) | 172 | SliderMenu | Экран/попап: карта редактор необитаемые земли карты | updateLanguage, actionElement, onBackPressed |
| [`Menu_Packages_RandomAllianceNames.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Packages_RandomAllianceNames.java) | 153 | SliderMenu | Экран/попап: пакеты случайный альянс имена | updateLanguage, actionElement, updateEnabled, getPackageName |
| [`Menu_MapEditor_GrowthRate.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_GrowthRate.java) | 148 | SliderMenu | Экран/попап: карта редактор рост оценка | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ManageDiplomacy_MilitaryAccess.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_MilitaryAccess.java) | 98 | SliderMenu | Экран/попап: управление дипломатия военный доступ | updateLanguage, draw, actionElement |
| [`Menu_ManageDiplomacy_DefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_DefensivePact.java) | 98 | SliderMenu | Экран/попап: управление дипломатия оборонительный пакт | updateLanguage, draw, actionElement |
| [`Menu_InGame_Slider_RecruitInstantly.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Slider_RecruitInstantly.java) | 167 | SliderMenu | Экран в игре: слайдер набор мгновенно | updateLanguage, draw, beginClip, extraAction |
| [`Menu_ManageDiplomacy_Pacts3.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Pacts3.java) | 98 | SliderMenu | Экран/попап: управление дипломатия пакты 3 | updateLanguage, draw, actionElement |
| [`Menu_ManageDiplomacy_Guarantee.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Guarantee.java) | 98 | SliderMenu | Экран/попап: управление дипломатия гарантия | updateLanguage, draw, actionElement |
| [`Menu_ManageDiplomacy_Truces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Truces.java) | 98 | SliderMenu | Экран/попап: управление дипломатия перемирия | updateLanguage, draw, actionElement |
| [`Menu_CreateScenario_Events_SelectCiv_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectCiv_Alphabet.java) | 152 | SliderMenu | Редактор сценария: события выбор цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_CreateScenario_Cores_AddCore_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_AddCore_Alphabet.java) | 152 | SliderMenu | Редактор сценария: коры добавить кор алфавит | updateLanguage, actionElement |
| [`Menu_SelectMapType.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectMapType.java) | 131 | SliderMenu | Экран/попап: выбор карта тип | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_FlagAction_Bot.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Bot.java) | 95 | SliderMenu | Экран в игре: флаг действие низ | draw, onHovered, actionElement, setVisible |
| [`Menu_InGame_SelectProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_SelectProvinces.java) | 173 | SliderMenu | Экран в игре: выбор провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_DeclareWar.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_DeclareWar.java) | 99 | SliderMenu | Редактор сценария: события результат объявление война | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_WhitePeace.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_WhitePeace.java) | 99 | SliderMenu | Редактор сценария: события результат белый мир | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_JoinUnion.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_JoinUnion.java) | 99 | SliderMenu | Редактор сценария: события результат вступление уния | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Editor_Unions_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Unions_List.java) | 168 | SliderMenu | Редактор: унии список | updateLanguage, updateMenuElements_IsInView, getIsLoaded, getFlagID |
| [`Menu_SelectMapType_Scale.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectMapType_Scale.java) | 122 | SliderMenu | Экран/попап: выбор карта тип масштаб | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Uncolonized.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Uncolonized.java) | 91 | SliderMenu | Экран в игре: неколонизированный | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_ManageDiplomacy_Pacts.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Pacts.java) | 131 | SliderMenu | Экран/попап: управление дипломатия пакты | updateLanguage, draw, actionElement, centerToPactID |
| [`Menu_CreateScenario_Events_Cond_Neutral.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Neutral.java) | 121 | SliderMenu | Редактор сценария: события условие нейтральный | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_FlagAction_Console.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_Console.java) | 99 | — | Экран в игре: флаг действие консоль | draw, drawScrollPos, onHovered, actionElement |
| [`Menu_CreateScenario_Events_Cond_WatchTower.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_WatchTower.java) | 121 | SliderMenu | Редактор сценария: события условие стража башня | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Wasteland.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Wasteland.java) | 121 | SliderMenu | Редактор сценария: события условие необитаемые земли | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Farm.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Farm.java) | 121 | SliderMenu | Редактор сценария: события условие ферма | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Port.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Port.java) | 121 | SliderMenu | Редактор сценария: события условие порт | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Fort.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Fort.java) | 121 | SliderMenu | Редактор сценария: события условие форт | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Games2.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Games2.java) | 84 | SliderMenu | Экран/попап: игры 2 | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Achievements_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Achievements_Options.java) | 135 | SliderMenu | Экран/попап: достижения опции | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_Trigger.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Trigger.java) | 119 | Menu_CreateScenario | Редактор сценария: события триггер | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Decision.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Decision.java) | 136 | Menu_CreateScenario | Редактор сценария: события решение | updateLanguage, draw, saveData, actionElement |
| [`Menu_CreateScenario_Events_Cond_Development_Low.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Development_Low.java) | 122 | SliderMenu | Редактор сценария: события условие развитие низкий | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_Development.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_Development.java) | 122 | SliderMenu | Редактор сценария: события условие развитие | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_UpdatePopulation_Perc.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_UpdatePopulation_Perc.java) | 103 | SliderMenu | Редактор сценария: события результат обновление население процент | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_UpdateEconomyPerc.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_UpdateEconomyPerc.java) | 103 | SliderMenu | Редактор сценария: события результат обновление экономика процент | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Development.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Development.java) | 103 | SliderMenu | Редактор сценария: события результат развитие | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_UpdatePopulation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_UpdatePopulation.java) | 103 | SliderMenu | Редактор сценария: события результат обновление население | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_UpdateEconomy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_UpdateEconomy.java) | 103 | SliderMenu | Редактор сценария: события результат обновление экономика | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_SetUpArmy_NeutralArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_SetUpArmy_NeutralArmy.java) | 95 | SliderMenu | Редактор сценария: установить Up армия нейтральный армия | updateLanguage, draw, actionElement, getW |
| [`Menu_CreateScenario_Events_Out_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Happiness.java) | 103 | SliderMenu | Редактор сценария: события результат счастье | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_SetUpArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_SetUpArmy.java) | 149 | SliderMenu | Редактор сценария: установить Up армия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ManageDiplomacy_AddNewPact.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_AddNewPact.java) | 105 | SliderMenu | Экран/попап: управление дипломатия добавить новый пакт | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_AddArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_AddArmy.java) | 97 | SliderMenu | Редактор сценария: события результат добавить армия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ManageDiplomacy_Pacts2.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Pacts2.java) | 106 | SliderMenu | Экран/попап: управление дипломатия пакты 2 | updateLanguage, draw, actionElement, updateNonAggressionPact |
| [`Menu_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Flag.java) | 82 | SliderMenu | Экран/попап: флаг | draw, actionElement |
| [`Menu_InGame_Slider_Regroup.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Slider_Regroup.java) | 139 | SliderMenu | Экран в игре: слайдер перегруппировка | updateLanguage, draw, beginClip, extraAction |
| [`Menu_InGame_ActionInfo_RecruitInstantly.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_RecruitInstantly.java) | 113 | SliderMenu | Экран в игре: действие информация набор мгновенно | actionElement, onHovered |
| [`Menu_Civilizations_Info_Difficulty.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Civilizations_Info_Difficulty.java) | 87 | SliderMenu | Инфо о цивилизации: информация сложность | updateLanguage, draw, actionElement, drawScrollPos |
| [`Menu_LoadMap.java`](../age/of/civilizations2/jakowski/lukasz/Menu_LoadMap.java) | 124 | SliderMenu | Экран/попап: загрузка карта | draw, loadMap |
| [`Menu_CreateScenario_Events_Out_MoveCapital.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_MoveCapital.java) | 95 | SliderMenu | Редактор сценария: события результат перемещение столица | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateCivilization_Info.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCivilization_Info.java) | 117 | SliderMenu | Экран/попап: создание цивилизация информация | updateLanguage, draw, actionElement |
| [`Menu_MapEditor_PortPosition.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_PortPosition.java) | 107 | SliderMenu | Экран/попап: карта редактор порт позиция | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_RegroupArmy_Move.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_RegroupArmy_Move.java) | 98 | SliderMenu | Экран в игре: действие информация перегруппировка армия перемещение | actionElement, onHovered |
| [`Menu_ManageDiplomacy_Relations_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Relations_Alphabet.java) | 114 | SliderMenu | Экран/попап: управление дипломатия отношения алфавит | updateLanguage, drawTitle, actionElement |
| [`Menu_InGame_FlagAction_GraphModes.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FlagAction_GraphModes.java) | 87 | SliderMenu | Экран в игре: флаг действие график режимы | updateLanguage, draw, onHovered, actionElement |
| [`Menu_PeaceTreaty_Response_Civs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Response_Civs.java) | 94 | SliderMenu | Экран/попап: мир договор ответ цивилизации | updateLanguage, draw, drawScrollPos, drawCloseButton |
| [`Menu_CreateScenario_WastelandMap.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_WastelandMap.java) | 114 | Menu_CreateScenario | Редактор сценария: необитаемые земли карта | updateLanguage, draw, actionElement |
| [`Menu_CreateScenario_SetUpArmy_Sliders.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_SetUpArmy_Sliders.java) | 103 | SliderMenu | Редактор сценария: установить Up армия слайдеры | draw, drawScrollPos, actionElement |
| [`Menu_MapEditor_Continents_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Continents_List.java) | 99 | SliderMenu | Экран/попап: карта редактор континенты список | draw, actionElement |
| [`Menu_CreateScenario_Events_Out_RemoveCore.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_RemoveCore.java) | 90 | SliderMenu | Редактор сценария: события результат удаление кор | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_SelectUIScale.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectUIScale.java) | 130 | SliderMenu | Экран/попап: выбор UI масштаб | updateLanguage, actionElement, setVisible |
| [`Menu_InGame_TurnSummary.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TurnSummary.java) | 86 | SliderMenu | Экран в игре: ход сводка | updateLanguage, draw, drawScrollPos, actionElement |
| [`Menu_CreateScenario_Events_Out_AddCore.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_AddCore.java) | 90 | SliderMenu | Редактор сценария: события результат добавить кор | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_Regions_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Regions_List.java) | 99 | SliderMenu | Экран/попап: карта редактор регионы список | draw, actionElement |
| [`Menu_Continents_CreateNewContinent.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Continents_CreateNewContinent.java) | 132 | SliderMenu | Экран/попап: континенты создание новый континент | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_CivInfo_Stats_DiploActions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivInfo_Stats_DiploActions.java) | 124 | SliderMenu | Экран в игре: цивилизация информация статистика дипломатия действия | updateLanguage, draw, drawScrollPos, onHovered |
| [`Menu_NewGame_Players.java`](../age/of/civilizations2/jakowski/lukasz/Menu_NewGame_Players.java) | 116 | SliderMenu | Экран/попап: новый игра игроки | initMenu, buildMenuElements, updateLanguage, draw |
| [`Menu_RandomGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame.java) | 126 | SliderMenu | Экран/попап: случайный игра | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Colonization.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Colonization.java) | 117 | SliderMenu | Редактор сценария: колонизация | updateLanguage, actionElement |
| [`Menu_Regions_CreateNewRegion.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Regions_CreateNewRegion.java) | 132 | SliderMenu | Экран/попап: регионы создание новый регион | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Slider.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Slider.java) | 130 | SliderMenu | Экран в игре: слайдер | updateLanguage, draw, beginClip, extraAction |
| [`Menu_CreateScenario_Events_Out_ChangeIdeology.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_ChangeIdeology.java) | 91 | SliderMenu | Редактор сценария: события результат изменение идеология | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ProvinceAction_Uncivilized.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ProvinceAction_Uncivilized.java) | 115 | SliderMenu | Экран в игре: провинция действие нецивилизованный | updateLanguage, draw, beginClip, actionElement |
| [`Menu_Leader_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leader_Edit.java) | 134 | SliderMenu | Экран/попап: лидер редактирование | updateLanguage, draw, actionElement, saveLeader |
| [`Menu_CreateScenario_Events_Out_UpdatePopulation_OfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_UpdatePopulation_OfCiv.java) | 92 | SliderMenu | Редактор сценария: события результат обновление население цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_DiplomacyPoints.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_DiplomacyPoints.java) | 92 | SliderMenu | Редактор сценария: события результат дипломатия очки | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_MovementPoints.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_MovementPoints.java) | 92 | SliderMenu | Редактор сценария: события результат движение очки | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Happiness_OfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Happiness_OfCiv.java) | 92 | SliderMenu | Редактор сценария: события результат счастье цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_UpdateEconomy_OfCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_UpdateEconomy_OfCiv.java) | 92 | SliderMenu | Редактор сценария: события результат обновление экономика цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Continents_AddNewContinentToPackage.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Continents_AddNewContinentToPackage.java) | 125 | SliderMenu | Экран/попап: континенты добавить новый континент пакет | updateLanguage, actionElement, setView_MAP_EDITOR_CREATE_NEW_CONTINENT, onBackPressed |
| [`Menu_SelectAvailableCivilizations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectAvailableCivilizations.java) | 109 | SliderMenu | Экран/попап: выбор доступный цивилизации | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Continents_CreateNewPackage.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Continents_CreateNewPackage.java) | 118 | SliderMenu | Экран/попап: континенты создание новый пакет | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_SelectProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectProvinces.java) | 132 | Menu_CreateScenario | Редактор сценария: события выбор провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Money.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Money.java) | 92 | SliderMenu | Редактор сценария: события результат деньги | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateNewGame.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame.java) | 127 | SliderMenu | Создание игры: меню создание новый игра | updateLanguage, draw, clickOptions, actionElement |
| [`Menu_InGame_Slider_Disband.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Slider_Disband.java) | 132 | SliderMenu | Экран в игре: слайдер роспуск | updateLanguage, draw, beginClip, extraAction |
| [`Menu_CreateScenario_Events_Out_TechLevel.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_TechLevel.java) | 91 | SliderMenu | Редактор сценария: события результат технология уровень | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Regions_AddNewRegionToPackage.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Regions_AddNewRegionToPackage.java) | 125 | SliderMenu | Экран/попап: регионы добавить новый регион пакет | updateLanguage, actionElement, setView_MAP_EDITOR_CREATE_NEW_REGION, onBackPressed |
| [`Menu_GameEditor_Lines.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GameEditor_Lines.java) | 130 | SliderMenu | Экран/попап: игра редактор линии | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Assign_Select_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Assign_Select_Alphabet.java) | 115 | SliderMenu | Редактор сценария: назначение выбор алфавит | updateLanguage, actionElement |
| [`Menu_Graphics.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Graphics.java) | 124 | SliderMenu | Экран/попап: графика | updateLanguage, actionElement |
| [`Menu_Regions_CreateNewPackage.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Regions_CreateNewPackage.java) | 116 | SliderMenu | Экран/попап: регионы создание новый пакет | updateLanguage, actionElement, onBackPressed |
| [`Menu_MapEditor_ArmySeaBoxes_Add.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ArmySeaBoxes_Add.java) | 115 | SliderMenu | Экран/попап: карта редактор армия море боксы добавить | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_FormCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_FormCiv.java) | 86 | SliderMenu | Редактор сценария: события результат форма цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Packages_DiplomacyColors.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Packages_DiplomacyColors.java) | 96 | SliderMenu | Экран/попап: пакеты дипломатия цвета | updateLanguage, actionElement, onBackPressed |
| [`Menu_AlliancesNames_Create.java`](../age/of/civilizations2/jakowski/lukasz/Menu_AlliancesNames_Create.java) | 128 | SliderMenu | Экран/попап: альянсы имена создание | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events.java) | 113 | Menu_CreateScenario | Редактор сценария: события | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Cond_EventChance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Cond_EventChance.java) | 101 | SliderMenu | Редактор сценария: события условие событие шанс | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_GameSaved.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_GameSaved.java) | 96 | SliderMenu | Экран в игре: игра сохранено | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_CreateScenario_WastelandMap_Continents.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_WastelandMap_Continents.java) | 112 | SliderMenu | Редактор сценария: необитаемые земли карта континенты | updateLanguage, draw, actionElement |
| [`Menu_InGame_MinimapAction.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_MinimapAction.java) | 161 | SliderMenu | Экран в игре: мини-карта действие | draw, actionElement, onBackPressed, updateChangePosX |
| [`Menu_InGame_Uncolonized_BorderOrArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Uncolonized_BorderOrArmy.java) | 78 | SliderMenu | Экран в игре: неколонизированный граница армия | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`MenuElement.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement.java) | 243 | — | Базовый UI-элемент: позиция, размер, видимость, кликабельность, hover, отрисовка. | buildElementHover, resetElementHover, drawMenuElementHover2, getMenuElement_Hover_IsNull |
| [`Menu_MapEditor_ArmyPosition.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ArmyPosition.java) | 101 | SliderMenu | Экран/попап: карта редактор армия позиция | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_ServiceRibbon_Editor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ServiceRibbon_Editor.java) | 101 | SliderMenu | Экран/попап: сервис лента редактор | updateLanguage, draw, actionElement |
| [`Menu_CreateScenario_Events_Out_LiberateVassal.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_LiberateVassal.java) | 79 | SliderMenu | Редактор сценария: события результат освобождение вассал | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_LeaveAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_LeaveAlliance.java) | 79 | SliderMenu | Редактор сценария: события результат выход альянс | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_Graph_MovementPoints.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Graph_MovementPoints.java) | 77 | SliderMenu | Экран в игре: график движение очки | updateLanguage, draw, actionElement, getW |
| [`Menu_Packages_WorldContinents.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Packages_WorldContinents.java) | 104 | SliderMenu | Экран/попап: пакеты мир континенты | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_GameEditor_Lines_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GameEditor_Lines_Edit.java) | 126 | SliderMenu | Экран/попап: игра редактор линии редактирование | updateLanguage, actionElement, onBackPressed |
| [`Menu_AlliancesNames_Create_Bundle.java`](../age/of/civilizations2/jakowski/lukasz/Menu_AlliancesNames_Create_Bundle.java) | 97 | SliderMenu | Экран/попап: альянсы имена создание бандл | updateLanguage, actionElement, updateBundle, onBackPressed |
| [`Menu_InGame_TopViews.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TopViews.java) | 81 | SliderMenu | Экран в игре: верх виды | updateLanguage, updateMenuElements_IsInView, draw, actionElement |
| [`Menu_Packages_Regions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Packages_Regions.java) | 103 | SliderMenu | Экран/попап: пакеты регионы | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_GameEditor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GameEditor.java) | 94 | SliderMenu | Экран/попап: игра редактор | updateLanguage, actionElement |
| [`Menu_CreateScenario_SetUpArmy_Options.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_SetUpArmy_Options.java) | 97 | SliderMenu | Редактор сценария: установить Up армия опции | updateLanguage, draw, actionElement |
| [`Menu_MapEditor_TradeZones_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_TradeZones_Edit.java) | 92 | SliderMenu | Экран/попап: карта редактор торговля зоны редактирование | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ShowProvinces_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ShowProvinces_List.java) | 79 | SliderMenu | Экран в игре: показ провинции список | updateLanguage, drawScrollPos, actionElement |
| [`Menu_InGame_ProvinceAction_Colonize.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ProvinceAction_Colonize.java) | 102 | SliderMenu | Экран в игре: провинция действие колонизация | updateLanguage, draw, beginClip, actionElement |
| [`Menu_Editor_Union_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Union_Edit.java) | 91 | SliderMenu | Редактор: уния редактирование | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Settings_Top.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Settings_Top.java) | 97 | Menu_CreateScenario | Редактор сценария: настройки верх | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Resolution.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Resolution.java) | 92 | SliderMenu | Экран/попап: разрешение | updateLanguage, actionElement |
| [`Menu_InGame_UncolonizedJust.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_UncolonizedJust.java) | 70 | SliderMenu | Экран в игре: неколонизированный только | getPosX2, getPosY2, getWidth2, getHeight2 |
| [`Menu_MapEditor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor.java) | 86 | SliderMenu | Экран/попап: карта редактор | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_Continents.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Continents.java) | 94 | SliderMenu | Экран/попап: карта редактор континенты | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_Regions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Regions.java) | 94 | SliderMenu | Экран/попап: карта редактор регионы | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_Terrain.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Terrain.java) | 91 | SliderMenu | Экран/попап: карта редактор местность | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_DiplomacyColors_Create.java`](../age/of/civilizations2/jakowski/lukasz/Menu_DiplomacyColors_Create.java) | 103 | SliderMenu | Экран/попап: дипломатия цвета создание | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Help.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Help.java) | 76 | SliderMenu | Экран/попап: справка | updateLanguage, actionElement, onBackPressed |
| [`Menu_Packages_PalletOfCivsColors.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Packages_PalletOfCivsColors.java) | 86 | SliderMenu | Экран/попап: пакеты палитра цивилизации цвета | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_Wasteland.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_Wasteland.java) | 72 | SliderMenu | Редактор сценария: события результат необитаемые земли | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_Disband.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_Disband.java) | 69 | SliderMenu | Экран в игре: действие информация роспуск | actionElement, onHovered |
| [`Menu_MapEditor_PortPosition_Convert.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_PortPosition_Convert.java) | 73 | SliderMenu | Экран/попап: карта редактор порт позиция конвертация | updateLanguage, actionElement, onBackPressed |
| [`Menu_MapEditor_TradeZones.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_TradeZones.java) | 68 | SliderMenu | Экран/попап: карта редактор торговля зоны | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_Connections_IDs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Connections_IDs.java) | 67 | SliderMenu | Экран/попап: карта редактор связи I Ds | draw, actionElement |
| [`Menu_MapEditor_ArmyPosition_Convert.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ArmyPosition_Convert.java) | 73 | SliderMenu | Экран/попап: карта редактор армия позиция конвертация | updateLanguage, actionElement, onBackPressed |
| [`Menu_SelectCivilization.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectCivilization.java) | 77 | SliderMenu | Экран/попап: выбор цивилизация | updateLanguage, draw, drawTitle, actionElement |
| [`Menu_InGame_RTO_Bot2.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_RTO_Bot2.java) | 76 | SliderMenu | Экран в игре: RTO низ 2 | updateLanguage, draw, actionElement |
| [`Menu_Load_GeneratePreDefinedBorders.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Load_GeneratePreDefinedBorders.java) | 74 | SliderMenu | Экран/попап: загрузка генерация Pre Defined границы | draw, loadData |
| [`Menu_MapEditor_ArmySeaBoxes.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ArmySeaBoxes.java) | 78 | SliderMenu | Экран/попап: карта редактор армия море боксы | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CustomizeAlliance_AddCivilization_Alphabet.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CustomizeAlliance_AddCivilization_Alphabet.java) | 79 | SliderMenu | Экран/попап: настройка альянс добавить цивилизация алфавит | updateLanguage, actionElement |
| [`Menu_Games_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Games_Title.java) | 78 | SliderMenu | Экран/попап: игры заголовок | getMenuWidth, updateLanguage, draw, actionElement |
| [`Menu_ManageDiplomacy_Relations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy_Relations.java) | 56 | SliderMenu | Экран/попап: управление дипломатия отношения | updateLanguage, actionElement |
| [`Menu_LoadGame_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_LoadGame_Title.java) | 73 | SliderMenu | Экран/попап: загрузка игра заголовок | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_ArmySeaBoxes_Edit_Top.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ArmySeaBoxes_Edit_Top.java) | 56 | SliderMenu | Экран/попап: карта редактор армия море боксы редактирование верх | draw, actionElement |
| [`Menu_MapEditor_Connections.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Connections.java) | 80 | SliderMenu | Экран/попап: карта редактор связи | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_RegroupArmy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_RegroupArmy.java) | 74 | SliderMenu | Экран в игре: действие информация перегруппировка армия | updateLanguage, draw, beginClip, actionElement |
| [`Menu_InGame_ActionInfo.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo.java) | 74 | SliderMenu | Экран в игре: действие информация | updateLanguage, draw, beginClip, actionElement |
| [`Menu_MapEditor_UpdateProvinceData.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_UpdateProvinceData.java) | 73 | SliderMenu | Экран/попап: карта редактор обновление провинция данные | updateLanguage, actionElement, onBackPressed |
| [`MenuElement_Hover_v2_Element_Type_Flag.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Flag.java) | 63 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип флаг | draw, getWidth, getImageScale |
| [`Menu_CreateScenario_Events_Date_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Date_List.java) | 52 | SliderMenu | Редактор сценария: события дата список | getMenuElementIsActive, actionElement |
| [`Menu_CreateScenario_Assign_Select_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Assign_Select_List.java) | 58 | SliderMenu | Редактор сценария: назначение выбор список | actionElement |
| [`MenuElement_Hover_v2_Element_Type_Wonder.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Wonder.java) | 76 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип чудо | draw, getWidth, getImageScale |
| [`Menu_InGame_TradeRequest_SelectCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_TradeRequest_SelectCiv.java) | 66 | SliderMenu | Экран в игре: торговля запрос выбор цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateTranslation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateTranslation.java) | 82 | SliderMenu | Экран/попап: создание Translation | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_CivilizationView.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CivilizationView.java) | 67 | SliderMenu | Экран в игре: цивилизация вид | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_Scenario_Age_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Scenario_Age_List.java) | 52 | SliderMenu | Экран/попап: сценарий эпоха список | getMenuElementIsActive, actionElement |
| [`Menu_CreateScenario_Events_SelectDecision_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectDecision_List.java) | 52 | SliderMenu | Редактор сценария: события выбор решение список | updateLanguage, actionElement |
| [`Menu_Printamap.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Printamap.java) | 80 | SliderMenu | Экран/попап: печать карты | draw, actionElement, onBackPressed, saveScenarioMinimapPreviewTexture |
| [`Menu_NewGame_Difficulty.java`](../age/of/civilizations2/jakowski/lukasz/Menu_NewGame_Difficulty.java) | 73 | SliderMenu | Экран/попап: новый игра сложность | updateLanguage, actionElement, onBackPressed |
| [`Menu_Load_GenerateSuggestedOwners.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Load_GenerateSuggestedOwners.java) | 61 | SliderMenu | Экран/попап: загрузка генерация предложено владельцы | draw, loadData |
| [`Menu_ManageDiplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ManageDiplomacy.java) | 66 | SliderMenu | Экран/попап: управление дипломатия | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_SelectCivilization_Classic.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectCivilization_Classic.java) | 67 | SliderMenu | Экран/попап: выбор цивилизация классический | initMenu, updateLanguage, draw, actionElement |
| [`MenuElement_Hover_v2_Element_Type_Terrain.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Terrain.java) | 55 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип местность | draw, getWidth, getImageScale |
| [`Menu_MapEditor_ProvinceBG.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_ProvinceBG.java) | 69 | SliderMenu | Экран/попап: карта редактор провинция BG | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_PalletOfColors.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_PalletOfColors.java) | 64 | Menu_CreateScenario | Редактор сценария: палитра цвета | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateCivilization.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCivilization.java) | 66 | SliderMenu | Экран/попап: создание цивилизация | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_InGame_ProvincePopulation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ProvincePopulation.java) | 65 | SliderMenu | Экран в игре: провинция население | updateLanguage, draw, actionElement, getPosX |
| [`Menu_Download_Pallets.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Download_Pallets.java) | 55 | SliderMenu | Экран/попап: загрузка палитры | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_Out_TriggerAnotherEvent.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_Out_TriggerAnotherEvent.java) | 56 | SliderMenu | Редактор сценария: события результат триггер другой событие | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_ShowProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ShowProvinces.java) | 60 | SliderMenu | Экран в игре: показ провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_SeaProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_SeaProvinces.java) | 63 | SliderMenu | Экран/попап: карта редактор море провинции | updateLanguage, draw, actionElement, onBackPressed |
| [`MenuElement_Hover_v2_Element_Type_Ideology_Vassal.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Ideology_Vassal.java) | 51 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип идеология вассал | draw, getWidth, getImageScale |
| [`MenuElement_Hover_v2_Element_Type_Ideology.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Ideology.java) | 51 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип идеология | draw, getWidth, getImageScale |
| [`Menu_CreateNewGame_Top_Views.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_Top_Views.java) | 43 | SliderMenu | Создание игры: верх виды | draw, actionElement |
| [`Menu_PeaceTreaty_Response_Demands_Show.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Response_Demands_Show.java) | 51 | SliderMenu | Экран/попап: мир договор ответ требования показ | draw, actionElement, setVisible |
| [`Menu_Settings_Language.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Settings_Language.java) | 52 | SliderMenu | Настройки: язык | updateLanguage, actionElement, onBackPressed |
| [`Menu_PeaceTreaty_Provinces_Show.java`](../age/of/civilizations2/jakowski/lukasz/Menu_PeaceTreaty_Provinces_Show.java) | 51 | SliderMenu | Экран/попап: мир договор провинции показ | draw, actionElement, setVisible |
| [`MenuElement_Hover_v2_Element_Type_Image.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Image.java) | 52 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип изображение | draw, getWidth, getImageScale |
| [`Menu_CreateNewGame_ShowCivInfo.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateNewGame_ShowCivInfo.java) | 52 | SliderMenu | Создание игры: показ цивилизация информация | draw, actionElement, setVisible |
| [`Menu_CreateScenario_Events_SelectIdeology.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectIdeology.java) | 53 | SliderMenu | Редактор сценария: события выбор идеология | updateLanguage, actionElement, onBackPressed |
| [`Menu_Editor_GameCivs_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_GameCivs_Title.java) | 52 | SliderMenu | Редактор: игра цивилизации заголовок | updateLanguage, actionElement, onBackPressed |
| [`MenuElement_Hover_v2_Element_Type_Color.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Color.java) | 54 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип цвет | draw, getWidth, getImageScale |
| [`Menu_Editor_Unions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Unions.java) | 52 | SliderMenu | Редактор: унии | updateLanguage, actionElement, onBackPressed |
| [`Menu_FlagPixel_Color.java`](../age/of/civilizations2/jakowski/lukasz/Menu_FlagPixel_Color.java) | 64 | — | Экран/попап: флаг пиксель цвет | getR, setR, getG, setG |
| [`Menu_CreateScenario_Preview.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Preview.java) | 52 | SliderMenu | Редактор сценария: предпросмотр | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_SelectEvent_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectEvent_List.java) | 38 | SliderMenu | Редактор сценария: события выбор событие список | updateLanguage, actionElement |
| [`Menu_Editor_Translations.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Translations.java) | 52 | SliderMenu | Редактор: Translations | updateLanguage, actionElement, onBackPressed |
| [`Menu_GenerateFlag.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GenerateFlag.java) | 51 | SliderMenu | Экран/попап: генерация флаг | draw, actionElement, onBackPressed |
| [`Menu_TerrainTypes_Editor_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_TerrainTypes_Editor_Title.java) | 50 | SliderMenu | Экран/попап: местность типы редактор заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_Settings.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Settings.java) | 50 | SliderMenu | Настройки: меню настройки | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_RecruitOccupied.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_RecruitOccupied.java) | 49 | SliderMenu | Экран в игре: действие информация набор оккупированный | updateLanguage, actionElement, onHovered |
| [`Menu_GameEditor_Lines_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GameEditor_Lines_Title.java) | 48 | SliderMenu | Экран/попап: игра редактор линии заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_Test.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Test.java) | 47 | SliderMenu | Экран/попап: тест | actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_NoMovementPoints.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_NoMovementPoints.java) | 49 | SliderMenu | Экран в игре: действие информация движение очки | updateLanguage, actionElement, onHovered |
| [`Menu_CreateScenario_ColonizationTitle.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_ColonizationTitle.java) | 47 | SliderMenu | Редактор сценария: колонизация заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_Editor_Scenarios_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Scenarios_Title.java) | 48 | SliderMenu | Редактор: сценарии заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_NoUnits.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_NoUnits.java) | 49 | SliderMenu | Экран в игре: действие информация юниты | updateLanguage, actionElement, onHovered |
| [`Menu_ServiceRibbon_Editor_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ServiceRibbon_Editor_Title.java) | 47 | SliderMenu | Экран/попап: сервис лента редактор заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_ActionInfo_TreasuryIsEmpty.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ActionInfo_TreasuryIsEmpty.java) | 49 | SliderMenu | Экран в игре: действие информация казна Empty | updateLanguage, actionElement, onHovered |
| [`Menu_Graphics_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Graphics_Title.java) | 47 | SliderMenu | Экран/попап: графика заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_InGame_CreateAVassal_SelectCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_CreateAVassal_SelectCiv.java) | 47 | SliderMenu | Экран в игре: создание вассал выбор цивилизация | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_ReligionsEditor_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ReligionsEditor_Title.java) | 47 | SliderMenu | Экран/попап: религии редактор заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_RandomGame_Civilizations_Select.java`](../age/of/civilizations2/jakowski/lukasz/Menu_RandomGame_Civilizations_Select.java) | 46 | SliderMenu | Экран/попап: случайный игра цивилизации выбор | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_Achievements.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Achievements.java) | 47 | SliderMenu | Экран/попап: достижения | updateLanguage, actionElement, onBackPressed |
| [`Menu_MapEditor_FormableCivs_SelectFormable.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_SelectFormable.java) | 46 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации выбор формируемый | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_MapEditor_FormableCivs_SelectClaimant.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs_SelectClaimant.java) | 46 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации выбор претендент | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_CreateScenario_Civilizations_Select.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Civilizations_Select.java) | 46 | SliderMenu | Редактор сценария: цивилизации выбор | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_AboutRate.java`](../age/of/civilizations2/jakowski/lukasz/Menu_AboutRate.java) | 45 | SliderMenu | Экран/попап: об игре оценка | updateLanguage, actionElement |
| [`Menu_ReligionEditor_Add.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ReligionEditor_Add.java) | 50 | SliderMenu | Экран/попап: религия редактор добавить | updateLanguage, draw, actionElement, onBackPressed |
| [`Menu_MapEditor_Edit.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_Edit.java) | 46 | SliderMenu | Экран/попап: карта редактор редактирование | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Cores_AddCore.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_AddCore.java) | 46 | SliderMenu | Редактор сценария: коры добавить кор | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_CreateScenario_Cores_AddCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Cores_AddCiv.java) | 46 | SliderMenu | Редактор сценария: коры добавить цивилизация | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_GameEditor_Regions.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GameEditor_Regions.java) | 49 | SliderMenu | Экран/попап: игра редактор регионы | actionElement, onBackPressed |
| [`Menu_Leader_Edit_SelectCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leader_Edit_SelectCiv.java) | 46 | SliderMenu | Экран/попап: лидер редактирование выбор цивилизация | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_CustomizeAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CustomizeAlliance.java) | 47 | SliderMenu | Экран/попап: настройка альянс | updateLanguage, actionElement, onBackPressed |
| [`Menu_MapEditor_FormableCivs.java`](../age/of/civilizations2/jakowski/lukasz/Menu_MapEditor_FormableCivs.java) | 45 | SliderMenu | Экран/попап: карта редактор формируемый цивилизации | updateLanguage, actionElement, onBackPressed |
| [`Menu_Editor_Unions_AddCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Editor_Unions_AddCiv.java) | 46 | SliderMenu | Редактор: унии добавить цивилизация | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_ReligionsEditor.java`](../age/of/civilizations2/jakowski/lukasz/Menu_ReligionsEditor.java) | 42 | SliderMenu | Экран/попап: религии редактор | updateLanguage, draw, actionElement |
| [`Menu_Resolution_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Resolution_Title.java) | 45 | SliderMenu | Экран/попап: разрешение заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_SelectUIScale_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_SelectUIScale_Title.java) | 45 | SliderMenu | Экран/попап: выбор UI масштаб заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario.java) | 45 | SliderMenu | Экран/попап: создание сценарий | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_SelectCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectCiv.java) | 45 | SliderMenu | Редактор сценария: события выбор цивилизация | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_GameEditor_Title.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GameEditor_Title.java) | 45 | SliderMenu | Экран/попап: игра редактор заголовок | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateScenario_Events_AddCiv.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_AddCiv.java) | 45 | SliderMenu | Редактор сценария: события добавить цивилизация | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_Chatbox.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Chatbox.java) | 42 | SliderMenu | Экран/попап: чат | draw, actionElement |
| [`Menu_Leaders.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Leaders.java) | 45 | SliderMenu | Экран/попап: лидеры | updateLanguage, actionElement, onBackPressed |
| [`Menu_CustomizeAlliance_AddCivilization.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CustomizeAlliance_AddCivilization.java) | 41 | SliderMenu | Экран/попап: настройка альянс добавить цивилизация | updateLanguage, actionElement, onBackPressed |
| [`Menu_CreateCiv_BG.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateCiv_BG.java) | 32 | SliderMenu | Экран/попап: создание цивилизация BG | draw |
| [`Menu_CreateScenario_Events_SelectDecision.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectDecision.java) | 44 | SliderMenu | Редактор сценария: события выбор решение | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_CreateScenario_Events_SelectEvent.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Events_SelectEvent.java) | 44 | SliderMenu | Редактор сценария: события выбор событие | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_GeneratePreview.java`](../age/of/civilizations2/jakowski/lukasz/Menu_GeneratePreview.java) | 41 | SliderMenu | Экран/попап: генерация предпросмотр | draw, actionElement, onBackPressed |
| [`MenuElement_Hover_v2_Element_Type_Leader.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Leader.java) | 47 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип лидер | draw, getWidth |
| [`MenuElement_Hover_v2_Element_Type_Text.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Text.java) | 43 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип текст | init, draw, getWidth |
| [`Menu_InGame_FormAnimation.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_FormAnimation.java) | 40 | SliderMenu | Экран в игре: форма анимация | actionElement, onBackPressed |
| [`Menu_CreateScenario_Assign_Select.java`](../age/of/civilizations2/jakowski/lukasz/Menu_CreateScenario_Assign_Select.java) | 43 | SliderMenu | Редактор сценария: назначение выбор | updateLanguage, actionElement, onBackPressed, actionClose |
| [`Menu_InGame_ViewDetails.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_ViewDetails.java) | 39 | SliderMenu | Экран в игре: вид детали | updateLanguage, draw, actionElement |
| [`Menu_FlagPixel.java`](../age/of/civilizations2/jakowski/lukasz/Menu_FlagPixel.java) | 31 | MenuElement | Экран/попап: флаг пиксель | draw |
| [`MenuElement_Hover_v2_Element2.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element2.java) | 37 | — | Строка подсказки v2. | draw, getWidth |
| [`MenuElement_Hover_v2_Element_Type_Space.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type_Space.java) | 32 | MenuElement_Hover_v2_Element_Type | UI-элемент: подсказка элемент тип Space | draw, getWidth |
| [`Menu_Download_Pallets_List.java`](../age/of/civilizations2/jakowski/lukasz/Menu_Download_Pallets_List.java) | 28 | SliderMenu | Экран/попап: загрузка палитры список | updateLanguage, actionElement |
| [`MenuElement_Hover.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover.java) | 24 | — | Всплывающая подсказка. | draw, drawAlwaysBelow, drawAlwaysOver, drawAlwaysOver_Mobile |
| [`MenuElement_Hover_Graph_Data.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_Graph_Data.java) | 32 | — | UI-элемент: подсказка график данные | getCivID, setCivID, getText, setText |
| [`MenuElement_Hover_Data.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_Data.java) | 18 | — | UI-элемент: подсказка данные |  |
| [`MenuElement_Hover_v2_Element_Type.java`](../age/of/civilizations2/jakowski/lukasz/MenuElement_Hover_v2_Element_Type.java) | 14 | — | Тип элемента подсказки. | draw, getWidth |
| [`Menu_InGame_Console.java`](../age/of/civilizations2/jakowski/lukasz/Menu_InGame_Console.java) | 11 | — | Экран в игре: консоль |  |


## UI: кнопки

Файлов: **259**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Button_Diplomacy.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy.java) | 1519 | Button | Кнопка открытия дипломатической панели. | setMaxDiploWidth, getMaxDiploWidth_ExtraPadding, drawButtonBG, updateHover |
| [`Button_Statistics_WarDetails.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_WarDetails.java) | 980 | Button_Statistics | Кнопка: статистика война детали | drawButtonBG, drawFlag, getMaxNameWidth, drawText |
| [`Button_Statistics_War.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_War.java) | 927 | Button_Statistics | Кнопка: статистика война | drawButtonBG, drawText, getImageScale, getImageScale2 |
| [`Button_View_Buildings.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Buildings.java) | 676 | Button | Кнопка: вид строения | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Statistics_WarDetails_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_WarDetails_Right.java) | 608 | Button_Statistics_WarDetails | Кнопка: статистика война детали правый | drawButtonBG, drawFlag, drawText |
| [`Button_View_Economy.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Economy.java) | 564 | Button | Кнопка: вид экономика | drawButtonBG, drawText, getImageScale2, getColor |
| [`Button_Build.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build.java) | 556 | Button | Кнопка: строительство | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_PeaceTreaty_Demands_ReleaseVassal.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_ReleaseVassal.java) | 552 | Button | Кнопка: мир договор требования освобождение вассал | drawButtonBG, getImageScale, getImageScale2, drawText |
| [`Button_NS_Opinion.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Opinion.java) | 542 | Button | Кнопка: NS мнение | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_Diplomacy_Civilize2.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Civilize2.java) | 527 | Button | Кнопка: дипломатия цивилизовать 2 | drawButtonBG, buildCheckbox, drawText, getRightWidth |
| [`Button_Diplomacy_FormCivilization.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_FormCivilization.java) | 523 | Button | Кнопка: дипломатия форма цивилизация | drawButtonBG, buildCheckbox, drawText, getRightWidth |
| [`Button_View_Income.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Income.java) | 495 | Button | Кнопка: вид доход | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Diplomacy_Civilize.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Civilize.java) | 501 | Button | Кнопка: дипломатия цивилизовать | drawButtonBG, buildCheckbox, drawText, getRightWidth |
| [`Button_View_GrowthRate.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_GrowthRate.java) | 472 | Button | Кнопка: вид рост оценка | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Diplomacy_SupportRebels.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_SupportRebels.java) | 489 | Button_Statistics | Кнопка: дипломатия поддержка повстанцы | drawButtonBG, drawText, buildCheckbox, getImageScale |
| [`Button_View_Population.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Population.java) | 473 | Button | Кнопка: вид население | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_NS_Disease.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Disease.java) | 476 | Button | Кнопка: NS болезнь | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_HRE_Elector.java`](../age/of/civilizations2/jakowski/lukasz/Button_HRE_Elector.java) | 475 | Button | Кнопка: СРИ курфюрст | drawButtonBG, drawText, getImageScale, getLEFTWidth |
| [`Button_HRE_Emperor.java`](../age/of/civilizations2/jakowski/lukasz/Button_HRE_Emperor.java) | 460 | Button | Кнопка: СРИ император | drawButtonBG, drawText, getImageScale, getLEFTWidth |
| [`Button_View_Unrest.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Unrest.java) | 434 | Button | Кнопка: вид беспорядки | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Game_Colonize.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_Colonize.java) | 408 | Button_Game | Кнопка: игра колонизация | drawButtonBG, drawText, getWidth, getColor |
| [`Button_View_Army.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Army.java) | 413 | Button | Кнопка: вид армия | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_PeaceTreaty_Demands_WarReparations.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_WarReparations.java) | 441 | Button | Кнопка: мир договор требования война репарации | drawButtonBG, getImageScale, getImageScale2, drawText |
| [`Button_View_Development.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Development.java) | 419 | Button | Кнопка: вид развитие | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Diplomacy_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Flag.java) | 450 | Button_Statistics | Кнопка: дипломатия флаг | drawButtonBG, drawFlag, drawText, buildElementHover |
| [`Button_PeaceTreaty_Demands_Province.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_Province.java) | 440 | Button | Кнопка: мир договор требования провинция | drawButtonBG, getImageScale, getImageScale2, drawText |
| [`Button_PeaceTreaty_Demands_Vassalize.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_Vassalize.java) | 442 | Button | Кнопка: мир договор требования вассализировать | drawButtonBG, getImageScale, getImageScale2, drawText |
| [`Button_Diplomacy_OfferAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_OfferAlliance.java) | 412 | Button_Statistics | Кнопка: дипломатия предложение альянс | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_ChangeGovernment.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_ChangeGovernment.java) | 421 | Button_Statistics | Кнопка: дипломатия изменение правительство | loadFlag, drawButtonBG, drawText, buildCheckbox |
| [`Button_Statistics_CallAlly.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_CallAlly.java) | 402 | Button_Statistics | Кнопка: статистика призыв союзник | callAlly, drawButtonBG, drawText, buildCheckbox |
| [`Button_View_Disease.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Disease.java) | 384 | Button | Кнопка: вид болезнь | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_View_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Happiness.java) | 379 | Button | Кнопка: вид счастье | drawButtonBG, getImageScale2, drawText, getColor |
| [`Button_Diplomacy_Migrate2.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Migrate2.java) | 380 | Button | Кнопка: дипломатия Migrate2 | drawButtonBG, drawText, getRightWidth, getImageScale |
| [`Button_NS_Population.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Population.java) | 400 | Button | Кнопка: NS население | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Buton_Diplomacy_CallAllies.java`](../age/of/civilizations2/jakowski/lukasz/Buton_Diplomacy_CallAllies.java) | 395 | Button_Statistics | Кнопка: дипломатия призыв союзники | drawButtonBG, drawText, buildCheckbox, getImageScale |
| [`Buton_Diplomacy_Loan.java`](../age/of/civilizations2/jakowski/lukasz/Buton_Diplomacy_Loan.java) | 379 | Button_Statistics | Кнопка: дипломатия кредит | drawButtonBG, drawText, buildCheckbox, getImageScale |
| [`Button_View_ProvinceStability.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_ProvinceStability.java) | 371 | Button | Кнопка: вид провинция стабильность | drawButtonBG, getImageScale2, drawText, getColor |
| [`Button_NS_Diseases.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Diseases.java) | 368 | Button | Кнопка: NS болезни | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_Diplomacy_LiberityDesire.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_LiberityDesire.java) | 356 | Button | Кнопка: дипломатия свобода желание | drawButtonBG, getImageScale, drawText, buildElementHover |
| [`Button_View_Recruitable.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Recruitable.java) | 350 | Button | Кнопка: вид наборный | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_View_ProvinceValue.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_ProvinceValue.java) | 346 | Button | Кнопка: вид провинция значение | drawButtonBG, drawText, getImageScale2, getColor |
| [`Button_Diplomacy_Message_JoinAWar_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Message_JoinAWar_Denied.java) | 357 | Button_Statistics | Кнопка: дипломатия сообщение вступление война отказано | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_View_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Technology.java) | 345 | Button | Кнопка: вид технология | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_NS_Goverments.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Goverments.java) | 371 | Button | Кнопка: NS правительства | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_BuildTech.java`](../age/of/civilizations2/jakowski/lukasz/Button_BuildTech.java) | 357 | Button | Кнопка: строительство технология | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_NS_Wonder.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Wonder.java) | 347 | Button | Кнопка: NS чудо | buildElementHover, drawButtonBG, drawText, getImageScale |
| [`Button_NS_Wonder_2.java`](../age/of/civilizations2/jakowski/lukasz/Button_NS_Wonder_2.java) | 350 | Button | Кнопка: NS чудо | buildElementHover, drawButtonBG, drawText, getImageScale |
| [`Button_Diplomacy_ImprovingRelations.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_ImprovingRelations.java) | 315 | Button | Кнопка: дипломатия улучшение отношения | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_BotBar_SuppRebels.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_SuppRebels.java) | 307 | Button_BotBar | Кнопка: низ панель поддержка повстанцы | drawText, getCurrent, setCurrent, setText |
| [`Button_PeaceTreaty_Demands_TakeAll.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_TakeAll.java) | 325 | Button | Кнопка: мир договор требования взятие все | getElemHeight, drawButtonBG, getImageScale, drawText |
| [`Button_Diplomacy_War.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_War.java) | 328 | Button_Statistics | Кнопка: дипломатия война | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Build_Level.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Level.java) | 289 | Button_Build | Кнопка: строительство уровень | drawText |
| [`Button_Build_Building_Destroy.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Building_Destroy.java) | 317 | Button | Кнопка: строительство строение уничтожение | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_Message_JoinAWar.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Message_JoinAWar.java) | 330 | Button_Statistics | Кнопка: дипломатия сообщение вступление война | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_Flag_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Flag_Right.java) | 307 | Button_Diplomacy_Flag | Кнопка: дипломатия флаг правый | drawButtonBG, drawFlag, drawText |
| [`Button_Diplomacy_DecreaseRelations.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_DecreaseRelations.java) | 291 | Button_Statistics | Кнопка: дипломатия уменьшение отношения | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Statistics_WarDetails_WarResult.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_WarDetails_WarResult.java) | 334 | Button_Statistics | Кнопка: статистика война детали война результат | drawButtonBG, drawText, buildElementHover, getCurrent |
| [`Button_Diplomacy_ImproveRelations.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_ImproveRelations.java) | 286 | Button_Statistics | Кнопка: дипломатия улучшение отношения | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_PeaceTreaty.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty.java) | 307 | Button | Кнопка: мир договор | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_Diplomacy_Relation.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Relation.java) | 279 | Button_Statistics | Кнопка: дипломатия отношение | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_Opinion.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Opinion.java) | 294 | Button | Кнопка: дипломатия мнение | drawButtonBG, getImageScale, drawText, buildElementHover |
| [`Button_Build_DiplomacyCost.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_DiplomacyCost.java) | 253 | Button_Build | Кнопка: строительство дипломатия стоимость | drawText |
| [`Button_Diplomacy_Abadon.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Abadon.java) | 286 | Button_Statistics | Кнопка: дипломатия отказ | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_MoveCapital.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_MoveCapital.java) | 296 | Button_Statistics | Кнопка: дипломатия перемещение столица | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_CallToArms.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_CallToArms.java) | 284 | Button_Statistics | Кнопка: дипломатия призыв оружие | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_Migrate.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Migrate.java) | 278 | Button_Statistics | Кнопка: дипломатия миграция | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Options_NS_MapModes.java`](../age/of/civilizations2/jakowski/lukasz/Button_Options_NS_MapModes.java) | 287 | Button_Options_NS | Кнопка: опции NS карта режимы | drawButtonBG, buildCheckbox, drawText, getCheckboxState |
| [`Button_Statistics_Alliance_Clip.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Alliance_Clip.java) | 244 | Button_Statistics_Flag | Кнопка: статистика альянс клип | drawText, getImageScale, buildElementHover |
| [`Button_Diplomacy_Plunder.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Plunder.java) | 272 | Button_Statistics | Кнопка: дипломатия грабёж | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_PeaceTreaty_VicPoints.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_VicPoints.java) | 263 | Button | Кнопка: мир договор Vic очки | drawButtonBG, getImageScale, drawText, buildElementHover |
| [`Button_Diplomacy_Wiki_Civ.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Wiki_Civ.java) | 268 | Button | Кнопка: дипломатия вики цивилизация | drawButtonBG, getImageScale, drawText, buildElementHover |
| [`Button_Diplomacy_TakeLoan_Interest.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_TakeLoan_Interest.java) | 275 | Button_Statistics | Кнопка: дипломатия взятие кредит интерес | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Likelihood.java`](../age/of/civilizations2/jakowski/lukasz/Button_Likelihood.java) | 270 | Button | Кнопка: вероятность | drawButtonBG, getColor, buildElementHover, getCurrent |
| [`Button_View_Terrain.java`](../age/of/civilizations2/jakowski/lukasz/Button_View_Terrain.java) | 270 | Button | Кнопка: вид местность | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Diplomacy_Action_Tech.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Action_Tech.java) | 253 | Button | Кнопка: дипломатия действие технология | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Diplomacy_Message_Pact.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Message_Pact.java) | 274 | Button_Statistics | Кнопка: дипломатия сообщение пакт | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_PeaceTreaty_Demands_TakeAll_VicPoints.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_TakeAll_VicPoints.java) | 261 | Button | Кнопка: мир договор требования взятие все Vic очки | drawButtonBG, getImageScale, drawText, buildElementHover |
| [`Button_Diplomacy_Demand.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Demand.java) | 279 | Button_Statistics | Кнопка: дипломатия требование | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_Diplomacy_Message_Gift.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Message_Gift.java) | 272 | Button_Statistics | Кнопка: дипломатия сообщение подарок | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_PeaceTreaty_Demands_Localize.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_Localize.java) | 257 | Button | Кнопка: мир договор требования локализация | drawButtonBG, getImageScale, drawText, buildElementHover |
| [`Button_Casualties.java`](../age/of/civilizations2/jakowski/lukasz/Button_Casualties.java) | 246 | Button | Кнопка: потери | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_Build_Text.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Text.java) | 261 | Button | Кнопка: строительство текст | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_TodayPartOf.java`](../age/of/civilizations2/jakowski/lukasz/Button_TodayPartOf.java) | 280 | Button | Кнопка: сегодня часть | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_Build_Building_Library.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Building_Library.java) | 218 | Button_Build_Building | Кнопка: строительство строение библиотека | drawText |
| [`Button_Diplomacy_MessageAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_MessageAlliance.java) | 259 | Button_Statistics | Кнопка: дипломатия сообщение альянс | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Message.java`](../age/of/civilizations2/jakowski/lukasz/Button_Message.java) | 255 | Button | Кнопка: сообщение | drawButtonBG, drawText, getColor, getCurrent |
| [`Button_Build_Destroy.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Destroy.java) | 226 | Button | Кнопка: строительство уничтожение | drawButtonBG, drawText, buildElementHover, getImageScale |
| [`Button_Stats_Image_Population.java`](../age/of/civilizations2/jakowski/lukasz/Button_Stats_Image_Population.java) | 223 | Button_Stats | Кнопка: статистика изображение население | drawText, setText, getImageScale, getCurrent |
| [`Button_Report_Units.java`](../age/of/civilizations2/jakowski/lukasz/Button_Report_Units.java) | 233 | Button | Кнопка: отчёт юниты | drawButtonBG, getColor, drawText, buildElementHover |
| [`Button_Statistics_CallAlly_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_CallAlly_Right.java) | 220 | Button_Statistics_CallAlly | Кнопка: статистика призыв союзник правый | callAlly, drawText, buildCheckbox |
| [`Button_ShowProvincesList.java`](../age/of/civilizations2/jakowski/lukasz/Button_ShowProvincesList.java) | 238 | Button | Кнопка: показ провинции список | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_Statistics_Flag_Clip_CivName.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Flag_Clip_CivName.java) | 195 | Button_Statistics_Flag | Кнопка: статистика флаг клип цивилизация имя | drawText, getImageScale |
| [`Button_Government.java`](../age/of/civilizations2/jakowski/lukasz/Button_Government.java) | 187 | Button_Build | Кнопка: правительство | drawText, setMin |
| [`Button_Diplomacy_Action_Goverment.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Action_Goverment.java) | 213 | Button | Кнопка: дипломатия действие правительство | drawButtonBG, drawText, getColor, setCurrent |
| [`Button_Diplomacy_MessageAlliance_Center.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_MessageAlliance_Center.java) | 232 | Button_Statistics | Кнопка: дипломатия сообщение альянс центр | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Build_Building.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Building.java) | 182 | Button_Build | Кнопка: строительство строение | drawText, setMin |
| [`Button_Diplomacy_Action.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Action.java) | 207 | Button | Кнопка: дипломатия действие | drawButtonBG, drawText, getColor, setCurrent |
| [`Button_Build_Assimilate.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Assimilate.java) | 185 | Button_Build | Кнопка: строительство ассимиляция | drawText, setCurrent |
| [`Button_New_Game_Players_Anim.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_Anim.java) | 184 | Button_New_Game_Players | Кнопка: новый игра игроки анимация | drawButtonBG, setIsHovered |
| [`Button_Diplomacy_Wiki.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Wiki.java) | 214 | Button | Кнопка: дипломатия вики | drawButtonBG, drawText, buildElementHover, actionElement |
| [`Button_TodayPartOf_Population.java`](../age/of/civilizations2/jakowski/lukasz/Button_TodayPartOf_Population.java) | 225 | Button | Кнопка: сегодня часть население | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_Diplomacy_Demands.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Demands.java) | 219 | Button_Statistics | Кнопка: дипломатия требования | buildCheckbox, drawButtonBG, drawText, getImageScale |
| [`Button_Report_Armies_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Report_Armies_Right.java) | 213 | Button | Кнопка: отчёт армии правый | drawButtonBG, drawText, buildElementHover |
| [`Button_Report_Armies.java`](../age/of/civilizations2/jakowski/lukasz/Button_Report_Armies.java) | 204 | Button | Кнопка: отчёт армии | drawButtonBG, drawText, buildElementHover |
| [`Button_Options_NS.java`](../age/of/civilizations2/jakowski/lukasz/Button_Options_NS.java) | 210 | Button_Menu | Кнопка: опции NS | drawButtonBG, drawText, buildCheckbox, getColor |
| [`Button_Build_Invest_Development.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Invest_Development.java) | 179 | Button_Build | Кнопка: строительство инвестиции развитие | drawText, setMin |
| [`Button_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Button_Flag.java) | 173 | MenuElement | Кнопка: флаг | draw |
| [`Button_Statistics_Flag_Clip.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Flag_Clip.java) | 177 | Button_Statistics_Flag | Кнопка: статистика флаг клип | drawText, getImageScale |
| [`Button_Build_Invest.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Invest.java) | 177 | Button_Build | Кнопка: строительство инвестиции | drawText, setMin |
| [`Button_Menu_LR_MainMenu_Games.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_MainMenu_Games.java) | 98 | Button_Menu | Кнопка: меню LR главный меню игры | drawButtonBG, drawText |
| [`Button_PrepareForWar.java`](../age/of/civilizations2/jakowski/lukasz/Button_PrepareForWar.java) | 164 | Button_Build | Кнопка: подготовка война | drawText |
| [`Button.java`](../age/of/civilizations2/jakowski/lukasz/Button.java) | 263 | MenuElement | Базовый класс кнопки: текст, позиция текста, чекбокс, состояния (обычное/hover/нажато/выключено). | init, draw, buildCheckbox, drawButtonBG |
| [`Button_Diplomacy_LikelihoodOfSuccess.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_LikelihoodOfSuccess.java) | 189 | Button_Statistics | Кнопка: дипломатия вероятность успех | drawButtonBG, drawText, getImageScale, getColor |
| [`Button_Statistics.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics.java) | 183 | Button | Кнопка: статистика | drawButtonBG, drawText, getColor, setCurrent |
| [`Button_NewGameStyle.java`](../age/of/civilizations2/jakowski/lukasz/Button_NewGameStyle.java) | 157 | Button | Кнопка: новый игра стиль | drawButtonBG, drawText, getColor |
| [`Button_VictoryStats.java`](../age/of/civilizations2/jakowski/lukasz/Button_VictoryStats.java) | 203 | Button | Кнопка: победа статистика | drawButtonBG, drawText, getColor, setCurrent |
| [`Button_Alliance.java`](../age/of/civilizations2/jakowski/lukasz/Button_Alliance.java) | 184 | Button | Кнопка: альянс | drawButtonBG, drawText, getColor, setText |
| [`Button_CNG_Options_Text2.java`](../age/of/civilizations2/jakowski/lukasz/Button_CNG_Options_Text2.java) | 177 | Button_CNG_Options | Кнопка: CNG опции текст 2 | drawButtonBG, drawText, setCurrent |
| [`Button_Statistics_War_Casualties.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_War_Casualties.java) | 175 | Button_Statistics | Кнопка: статистика война потери | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_NewGameStyle_Middle.java`](../age/of/civilizations2/jakowski/lukasz/Button_NewGameStyle_Middle.java) | 150 | Button | Кнопка: новый игра стиль середина | drawButtonBG, drawText, getColor |
| [`Button_New_Game_Players_Player.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_Player.java) | 167 | Button_New_Game_Players | Кнопка: новый игра игроки игрок | drawButtonBG, drawText |
| [`Button_Build_Festival.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Festival.java) | 147 | Button_Build | Кнопка: строительство фестиваль | drawText |
| [`Button_New_Game_Players.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players.java) | 152 | Button | Кнопка: новый игра игроки | drawButtonBG, drawText, getColor |
| [`Button_Diplomacy_SupportRebelsWithoutProvinces.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_SupportRebelsWithoutProvinces.java) | 146 | Button_Diplomacy_SupportRebels | Кнопка: дипломатия поддержка повстанцы без провинции | drawText |
| [`Button_CivName.java`](../age/of/civilizations2/jakowski/lukasz/Button_CivName.java) | 177 | Button | Кнопка: цивилизация имя | drawButtonBG, drawText, buildElementHover, getColor |
| [`Button_FlagActionSliderStyle.java`](../age/of/civilizations2/jakowski/lukasz/Button_FlagActionSliderStyle.java) | 159 | Button | Кнопка: флаг действие слайдер стиль | drawButtonBG, drawText, getColor |
| [`Button_CNG_Options.java`](../age/of/civilizations2/jakowski/lukasz/Button_CNG_Options.java) | 163 | Button_Menu | Кнопка: CNG опции | drawButtonBG, drawText, buildCheckbox, getColor |
| [`Button_Game_NextTurn.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_NextTurn.java) | 145 | Button_Game | Кнопка: игра следующий ход | drawButtonBG, setIsHovered, getColor |
| [`Button_FlagAction_GraphModes.java`](../age/of/civilizations2/jakowski/lukasz/Button_FlagAction_GraphModes.java) | 157 | Button_Menu | Кнопка: флаг действие график режимы | drawButtonBG, drawText, getColor, buildElementHover |
| [`Button_Report_ProvinceLosses.java`](../age/of/civilizations2/jakowski/lukasz/Button_Report_ProvinceLosses.java) | 136 | Button | Кнопка: отчёт провинция потери | drawButtonBG, drawText, buildElementHover |
| [`Button_NewGameStyle_Clear.java`](../age/of/civilizations2/jakowski/lukasz/Button_NewGameStyle_Clear.java) | 128 | Button | Кнопка: новый игра стиль очистить | drawButtonBG, drawText, getColor |
| [`Button_PeaceTreaty_Demands_Province2.java`](../age/of/civilizations2/jakowski/lukasz/Button_PeaceTreaty_Demands_Province2.java) | 134 | Button_PeaceTreaty_Demands_Province | Кнопка: мир договор требования провинция 2 | drawText |
| [`Button_Menu_LR_MainMenu_TextScale_Active.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_MainMenu_TextScale_Active.java) | 78 | Button_Menu_LR_MainMenu | Кнопка: меню LR главный меню текст масштаб активный | drawButtonBG, drawText |
| [`Button_PlayAsVassal.java`](../age/of/civilizations2/jakowski/lukasz/Button_PlayAsVassal.java) | 133 | Button | Кнопка: играть вассал | drawButtonBG, drawText, buildCheckbox, getColor |
| [`Button_New_Game_Players_Players_LEFT.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_Players_LEFT.java) | 147 | Button_New_Game_Players | Кнопка: новый игра игроки игроки LEFT | drawButtonBG, drawText, getCurrent |
| [`Button_FlagFrame.java`](../age/of/civilizations2/jakowski/lukasz/Button_FlagFrame.java) | 151 | Button | Кнопка: флаг рамка | drawButtonBG, getColor, getFlag |
| [`Button_NewGameStyle_Left.java`](../age/of/civilizations2/jakowski/lukasz/Button_NewGameStyle_Left.java) | 134 | Button | Кнопка: новый игра стиль левый | drawButtonBG, drawText, getColor |
| [`Button_CreateNewGameScenario.java`](../age/of/civilizations2/jakowski/lukasz/Button_CreateNewGameScenario.java) | 142 | Button | Кнопка: создание новый игра сценарий | drawButtonBG, getColor, drawText |
| [`Button_Technology.java`](../age/of/civilizations2/jakowski/lukasz/Button_Technology.java) | 122 | Button_Build | Кнопка: технология | drawText, buildElementHover, setMin |
| [`Button_Tribute.java`](../age/of/civilizations2/jakowski/lukasz/Button_Tribute.java) | 134 | Button_Build | Кнопка: дань | drawText, setMin |
| [`Button_RandomGame_Players_LEFT.java`](../age/of/civilizations2/jakowski/lukasz/Button_RandomGame_Players_LEFT.java) | 136 | Button_New_Game_Players | Кнопка: случайный игра игроки LEFT | drawButtonBG, drawText |
| [`Button_Statistics_War_Casualties_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_War_Casualties_Right.java) | 133 | Button_Statistics_War_Casualties | Кнопка: статистика война потери правый | drawButtonBG |
| [`Button_Menu_LR_MainMenu_Rate.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_MainMenu_Rate.java) | 72 | Button_Menu | Кнопка: меню LR главный меню оценка | drawButtonBG, getColor |
| [`Button_EditorFlag.java`](../age/of/civilizations2/jakowski/lukasz/Button_EditorFlag.java) | 137 | Button | Кнопка: редактор флаг | drawButtonBG, getColor, setCurrent, getCurrent |
| [`Button_NewGameStyle_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_NewGameStyle_Right.java) | 125 | Button | Кнопка: новый игра стиль правый | drawButtonBG, drawText, getColor |
| [`Button_BotBar_Cores.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_Cores.java) | 129 | Button_BotBar | Кнопка: низ панель коры | drawText, getCurrent, setCurrent, setText |
| [`Button_Statistics_Flag_Clip_ProvinceID.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Flag_Clip_ProvinceID.java) | 124 | Button_Statistics_Flag | Кнопка: статистика флаг клип провинция ID | drawText, getImageScale, getCurrent |
| [`Button_Statistics_Elections.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Elections.java) | 118 | Button_Statistics | Кнопка: статистика выборы | drawButtonBG, drawText, getColor, getImageScale |
| [`Button_Menu_LR_MainMenu.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_MainMenu.java) | 67 | Button_Menu | Кнопка: меню LR главный меню | drawButtonBG |
| [`Button_CNG_Scenario.java`](../age/of/civilizations2/jakowski/lukasz/Button_CNG_Scenario.java) | 130 | Button_Menu | Кнопка: CNG сценарий | drawButtonBG, drawText, getCurrent, getColor |
| [`Button_New_Game_Players_CivID_LEFT.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_CivID_LEFT.java) | 132 | Button_New_Game_Players | Кнопка: новый игра игроки цивилизация ID LEFT | drawButtonBG, drawText, getCurrent |
| [`Button_Options_NS_MapModes2.java`](../age/of/civilizations2/jakowski/lukasz/Button_Options_NS_MapModes2.java) | 125 | Button_Options_NS_MapModes | Кнопка: опции NS карта режимы 2 | drawButtonBG |
| [`Button_RandomGame_Players_Localization_Middle.java`](../age/of/civilizations2/jakowski/lukasz/Button_RandomGame_Players_Localization_Middle.java) | 110 | Button_New_Game_Players | Кнопка: случайный игра игроки локализация середина | drawButtonBG, drawText |
| [`Button_RandomGame_Players_Localization.java`](../age/of/civilizations2/jakowski/lukasz/Button_RandomGame_Players_Localization.java) | 110 | Button_New_Game_Players | Кнопка: случайный игра игроки локализация | drawButtonBG, drawText |
| [`Button_Diplomacy_Opinion2.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Opinion2.java) | 102 | Button_Diplomacy_Opinion | Кнопка: дипломатия мнение 2 | drawText, buildElementHover |
| [`Button_New_Game_Players_Players_RIGHT.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_Players_RIGHT.java) | 110 | Button_New_Game_Players | Кнопка: новый игра игроки игроки RIGHT | drawButtonBG, drawText |
| [`Button_Menu.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu.java) | 80 | Button | Кнопка: меню | drawButtonBG, getColor, setIsHovered, getColorLine |
| [`Button_Game_PLAY.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_PLAY.java) | 103 | Button_Game | Кнопка: игра PLAY | drawButtonBG |
| [`Button_Menu_LR.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR.java) | 63 | Button_Menu | Кнопка: меню LR | drawButtonBG |
| [`Button_Speed.java`](../age/of/civilizations2/jakowski/lukasz/Button_Speed.java) | 123 | Button | Кнопка: скорость | drawButtonBG, drawText, getColor, getSFX |
| [`Button_Diplomacy_Sort.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_Sort.java) | 112 | Button | Кнопка: дипломатия сортировка | drawButtonBG, drawText, getColor |
| [`Button_BotBar_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_Flag.java) | 112 | Button_BotBar | Кнопка: низ панель флаг | drawText, getCurrent, setCurrent, setText |
| [`Button_Game_Accept.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_Accept.java) | 93 | Button_Game | Кнопка: игра принять | drawButtonBG |
| [`Button_Diplo_Actions.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplo_Actions.java) | 111 | Button | Кнопка: дипломатия действия | drawButtonBG, drawText, getColor |
| [`Button_Menu_LR_MainMenu_TextScale_Important.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_MainMenu_TextScale_Important.java) | 44 | Button_Menu_LR_MainMenu | Кнопка: меню LR главный меню текст масштаб важный | drawText |
| [`Button_Statistics_Title.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Title.java) | 113 | Button_Statistics | Кнопка: статистика заголовок | drawButtonBG, drawText, getColor, getSFX |
| [`Button_FormCivTitle.java`](../age/of/civilizations2/jakowski/lukasz/Button_FormCivTitle.java) | 108 | Button | Кнопка: форма цивилизация заголовок | drawButtonBG, drawText, getImageScale, buildCheckbox |
| [`Button_Game_Checkbox.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_Checkbox.java) | 92 | Button | Кнопка: игра чекбокс | drawButtonBG, getColor |
| [`Button_RTO.java`](../age/of/civilizations2/jakowski/lukasz/Button_RTO.java) | 116 | Button_Menu | Кнопка: RTO | drawButtonBG, drawText, getCurrent, getColor |
| [`Button_ViewPercentageInfo.java`](../age/of/civilizations2/jakowski/lukasz/Button_ViewPercentageInfo.java) | 110 | Button | Кнопка: вид процент информация | drawButtonBG, drawText, getColor, setCurrent |
| [`Button_Diplo_Opinions.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplo_Opinions.java) | 103 | Button_Diplo_Actions | Кнопка: дипломатия мнения | drawButtonBG, drawText |
| [`Button_Menu_ReflectedBG.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_ReflectedBG.java) | 60 | Button_Menu | Кнопка: меню отражено BG | drawButtonBG |
| [`Button_Menu_Classic.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Classic.java) | 60 | Button_Menu | Кнопка: меню классический | drawButtonBG |
| [`Button_Chatbox.java`](../age/of/civilizations2/jakowski/lukasz/Button_Chatbox.java) | 93 | Button | Кнопка: чат | drawButtonBG, drawText, getColor |
| [`Button_FlagActionSliderStyle_Animated.java`](../age/of/civilizations2/jakowski/lukasz/Button_FlagActionSliderStyle_Animated.java) | 80 | Button_FlagActionSliderStyle | Кнопка: флаг действие слайдер стиль анимированный | drawButtonBG, getColorLine |
| [`Button_RTO_Player.java`](../age/of/civilizations2/jakowski/lukasz/Button_RTO_Player.java) | 96 | Button_RTO | Кнопка: RTO игрок | drawButtonBG, drawText, getColor |
| [`Button_Game_Ideology.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_Ideology.java) | 91 | Button_Game | Кнопка: игра идеология | drawText, buildElementHover, getCurrent, getTextWidth |
| [`Button_Statistics_Flag_HREPrince.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Flag_HREPrince.java) | 78 | Button_Statistics_Flag | Кнопка: статистика флаг СРИ князь | drawText, getImageScale, buildElementHover |
| [`Button_Build_TransferControl.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_TransferControl.java) | 74 | Button_Build | Кнопка: строительство передача контроль | drawText |
| [`Button_Menu_Classic_YT.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Classic_YT.java) | 73 | Button_Menu | Кнопка: меню классический YT | drawButtonBG, drawText, buildElementHover, getCurrent |
| [`Button_Speed_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Speed_Right.java) | 90 | Button_Speed | Кнопка: скорость правый | drawButtonBG |
| [`Button_Stats_FlagName.java`](../age/of/civilizations2/jakowski/lukasz/Button_Stats_FlagName.java) | 86 | Button_Stats | Кнопка: статистика флаг имя | drawText, getCurrent, setCurrent, setText |
| [`Button_Statistics_Flag.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Flag.java) | 90 | Button_Statistics | Кнопка: статистика флаг | drawText, getCurrent |
| [`Button_CalendarDay.java`](../age/of/civilizations2/jakowski/lukasz/Button_CalendarDay.java) | 85 | Button | Кнопка: календарь день | drawButtonBG, getColor, getCurrent |
| [`Button_Menu_Scenario.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Scenario.java) | 53 | Button_Menu | Кнопка: меню сценарий | init, drawText, getCurrent |
| [`Button_Game_NewGameBoxStyle_RIGHT_Remove.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_NewGameBoxStyle_RIGHT_Remove.java) | 77 | Button | Кнопка: игра новый игра бокс стиль RIGHT удаление | drawButtonBG |
| [`Button_CNG_Options2.java`](../age/of/civilizations2/jakowski/lukasz/Button_CNG_Options2.java) | 80 | Button_CNG_Options | Кнопка: CNG опции 2 | drawButtonBG |
| [`Button_DialogAgree.java`](../age/of/civilizations2/jakowski/lukasz/Button_DialogAgree.java) | 82 | Button | Кнопка: диалог согласен | drawButtonBG, getColor |
| [`Button_Menu_Classic_Wiki.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Classic_Wiki.java) | 65 | Button_Menu | Кнопка: меню классический вики | drawButtonBG, drawText, buildElementHover, getCurrent |
| [`Button_New_Game_Players_Report.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_Report.java) | 82 | Button_New_Game_Players | Кнопка: новый игра игроки отчёт | drawText, getCurrent |
| [`Button_BotBar_ISNotSupplied.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_ISNotSupplied.java) | 84 | Button_BotBar | Кнопка: низ панель IS снабжение | drawButtonBG, drawText, setText, getWidth |
| [`Button_BotBar_Disease.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_Disease.java) | 84 | Button_BotBar | Кнопка: низ панель болезнь | drawButtonBG, drawText, setText, getWidth |
| [`Button_Rank.java`](../age/of/civilizations2/jakowski/lukasz/Button_Rank.java) | 100 | Button | Кнопка: рейтинг | drawButtonBG, drawText, setText, getColor |
| [`Button_Add.java`](../age/of/civilizations2/jakowski/lukasz/Button_Add.java) | 76 | Button | Кнопка: добавить | drawButtonBG, getColor |
| [`Button_BotBar_DefensivePosition.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_DefensivePosition.java) | 82 | Button_BotBar | Кнопка: низ панель оборонительный позиция | drawButtonBG, drawText, setText, getWidth |
| [`Button_BotBar_Assimilate.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_Assimilate.java) | 82 | Button_BotBar | Кнопка: низ панель ассимиляция | drawButtonBG, drawText, setText, getWidth |
| [`Button_BotBar_InvestDevelopment.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_InvestDevelopment.java) | 82 | Button_BotBar | Кнопка: низ панель инвестиции развитие | drawButtonBG, drawText, setText, getWidth |
| [`Button_BotBar_NewColony.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_NewColony.java) | 82 | Button_BotBar | Кнопка: низ панель новый колония | drawButtonBG, drawText, setText, getWidth |
| [`Button_New_Game_Players_CivID.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_CivID.java) | 80 | Button_New_Game_Players | Кнопка: новый игра игроки цивилизация ID | drawText, getCurrent, getTextToDraw |
| [`Button_FlagActionSliderStyle_War.java`](../age/of/civilizations2/jakowski/lukasz/Button_FlagActionSliderStyle_War.java) | 76 | Button_FlagActionSliderStyle | Кнопка: флаг действие слайдер стиль война | drawText |
| [`Button_BotBar_NotSupplied.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_NotSupplied.java) | 78 | Button_BotBar | Кнопка: низ панель снабжение | drawButtonBG, drawText, setText, getWidth |
| [`Button_Game.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game.java) | 63 | Button | Кнопка: игра | drawButtonBG, getColor |
| [`Button_RandomGame_Players_Elector.java`](../age/of/civilizations2/jakowski/lukasz/Button_RandomGame_Players_Elector.java) | 73 | Button_New_Game_Players | Кнопка: случайный игра игроки курфюрст | drawButtonBG, drawText, getCurrent |
| [`Button_BotBar_Invest.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_Invest.java) | 78 | Button_BotBar | Кнопка: низ панель инвестиции | drawButtonBG, drawText, setText, getWidth |
| [`Button_Build_Text_Disband.java`](../age/of/civilizations2/jakowski/lukasz/Button_Build_Text_Disband.java) | 62 | Button_Build_Text | Кнопка: строительство текст роспуск | drawText, getImageScale |
| [`Button_Keyboard.java`](../age/of/civilizations2/jakowski/lukasz/Button_Keyboard.java) | 58 | Button | Кнопка: клавиатура | drawButtonBG, getColor |
| [`Button_Statistics_City.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_City.java) | 70 | Button_Statistics | Кнопка: статистика город | drawText, getImageScale, getCurrent |
| [`Button_Diplomacy_War_Cost.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_War_Cost.java) | 58 | Button_Diplomacy_War | Кнопка: дипломатия война стоимость | drawText, getImageScale |
| [`Button_Flag_JustFrame.java`](../age/of/civilizations2/jakowski/lukasz/Button_Flag_JustFrame.java) | 73 | Button | Кнопка: флаг только рамка | drawButtonBG, getSFX |
| [`Button_View.java`](../age/of/civilizations2/jakowski/lukasz/Button_View.java) | 64 | Button | Кнопка: вид | drawButtonBG, drawText, getColor |
| [`Button_Menu_ScenarioLoad.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_ScenarioLoad.java) | 43 | Button_Menu | Кнопка: меню сценарий загрузка | drawText, getCurrent |
| [`Button_DialogDisagree.java`](../age/of/civilizations2/jakowski/lukasz/Button_DialogDisagree.java) | 68 | Button | Кнопка: диалог не согласен | drawButtonBG, getColor |
| [`Button_BotBar.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar.java) | 73 | Button | Кнопка: низ панель | drawButtonBG, drawText, getColor, setText |
| [`Button_Game_TextTwoLines.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_TextTwoLines.java) | 71 | Button_Game | Кнопка: игра текст два линии | drawText, getTextWidth, getTextPos, getColor |
| [`Button_Stats_Image.java`](../age/of/civilizations2/jakowski/lukasz/Button_Stats_Image.java) | 61 | Button_Stats | Кнопка: статистика изображение | drawText, setText, getImageScale |
| [`Button_Menu_Age.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Age.java) | 44 | Button_Menu | Кнопка: меню эпоха | drawButtonBG |
| [`Button_Menu_Remove.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Remove.java) | 43 | Button_Menu | Кнопка: меню удаление | drawButtonBG |
| [`Button_Statistics_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Happiness.java) | 57 | Button_Statistics | Кнопка: статистика счастье | drawText, getImageScale |
| [`Button_Menu_Active.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Active.java) | 47 | Button_Menu | Кнопка: меню активный | drawText, drawButtonBG, getColor |
| [`Button_Menu_Classic_ReflectedCheckbox.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Classic_ReflectedCheckbox.java) | 49 | Button_Menu_Classic | Кнопка: меню классический отражено чекбокс | buildCheckbox |
| [`Button_Menu_Classic_Search.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Classic_Search.java) | 41 | Button_Menu | Кнопка: меню классический поиск | drawButtonBG |
| [`Button_Game_NewGameBoxStyle_LEFT.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_NewGameBoxStyle_LEFT.java) | 53 | Button | Кнопка: игра новый игра бокс стиль LEFT | drawButtonBG |
| [`Button_Tribute_Vassal.java`](../age/of/civilizations2/jakowski/lukasz/Button_Tribute_Vassal.java) | 53 | Button_Build | Кнопка: дань вассал | drawText, buildElementHover, actionElement |
| [`Button_Stats.java`](../age/of/civilizations2/jakowski/lukasz/Button_Stats.java) | 61 | Button | Кнопка: статистика | drawButtonBG, drawText, getColor, setText |
| [`Button_BotBar_ProvinceValue.java`](../age/of/civilizations2/jakowski/lukasz/Button_BotBar_ProvinceValue.java) | 56 | Button_BotBar | Кнопка: низ панель провинция значение | drawText, setText, getWidth |
| [`Button_Menu_UP.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_UP.java) | 40 | Button_Menu | Кнопка: меню UP | drawButtonBG |
| [`Button_Menu_ArrowRight.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_ArrowRight.java) | 38 | Button_Menu | Кнопка: меню стрелка правый | drawButtonBG |
| [`Button_Diplomacy_InGame.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_InGame.java) | 45 | Button_Diplomacy | Кнопка: дипломатия в игра | setAnotherView |
| [`Button_Menu_ArrowLeft.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_ArrowLeft.java) | 38 | Button_Menu | Кнопка: меню стрелка левый | drawButtonBG |
| [`Button_ArrowLeft.java`](../age/of/civilizations2/jakowski/lukasz/Button_ArrowLeft.java) | 46 | Button | Кнопка: стрелка левый | drawText |
| [`Button_ArrowRight.java`](../age/of/civilizations2/jakowski/lukasz/Button_ArrowRight.java) | 46 | Button | Кнопка: стрелка правый | drawText |
| [`Button_ShowMenu.java`](../age/of/civilizations2/jakowski/lukasz/Button_ShowMenu.java) | 48 | Button | Кнопка: показ меню | drawButtonBG, drawText |
| [`Button_Menu_Descripted.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_Descripted.java) | 35 | Button_Menu | Кнопка: меню описанный | drawText |
| [`Button_RTO2.java`](../age/of/civilizations2/jakowski/lukasz/Button_RTO2.java) | 48 | Button_RTO | Кнопка: RTO | drawButtonBG |
| [`Button_New_Game_Players_Special.java`](../age/of/civilizations2/jakowski/lukasz/Button_New_Game_Players_Special.java) | 39 | Button_New_Game_Players | Кнопка: новый игра игроки специальный | drawText |
| [`Button_Game_ArrowDown.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_ArrowDown.java) | 43 | Button_Game | Кнопка: игра стрелка вниз | drawText |
| [`Button_Game_ArrowUp.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_ArrowUp.java) | 43 | Button_Game | Кнопка: игра стрелка Up | drawText |
| [`Button_Menu_LR_Line.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_Line.java) | 33 | Button_Menu_LR | Кнопка: меню LR линия | drawButtonBG |
| [`Button_Statistics_Color.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Color.java) | 46 | Button_Statistics | Кнопка: статистика цвет | drawText |
| [`Button_Menu_LR_MainMenu_TextScale.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_LR_MainMenu_TextScale.java) | 32 | Button_Menu_LR_MainMenu | Кнопка: меню LR главный меню текст масштаб | drawText |
| [`Button_RTO_Player_Info.java`](../age/of/civilizations2/jakowski/lukasz/Button_RTO_Player_Info.java) | 37 | Button_RTO_Player | Кнопка: RTO игрок информация | drawText |
| [`Button_Statistics_Clip.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Clip.java) | 40 | Button_Statistics | Кнопка: статистика клип | drawText |
| [`Button_Game_ColorPicker.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_ColorPicker.java) | 37 | Button_Game | Кнопка: игра цвет выбор | drawButtonBG |
| [`Button_Menu_CivilizationAndFlag.java`](../age/of/civilizations2/jakowski/lukasz/Button_Menu_CivilizationAndFlag.java) | 34 | Button_Menu | Кнопка: меню цивилизация флаг | drawButtonBG, getCurrent |
| [`Button_Add_V.java`](../age/of/civilizations2/jakowski/lukasz/Button_Add_V.java) | 37 | Button_Add | Кнопка: добавить V | drawButtonBG |
| [`Button_Game_Decline.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_Decline.java) | 36 | Button_Game | Кнопка: игра отклонение | drawButtonBG |
| [`Button_Diplomacy_InGameWar.java`](../age/of/civilizations2/jakowski/lukasz/Button_Diplomacy_InGameWar.java) | 34 | Button_Diplomacy | Кнопка: дипломатия в игра война | setAnotherView |
| [`Button_Statistics_Gold.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Gold.java) | 33 | Button_Statistics | Кнопка: статистика золото | drawText |
| [`Button_Statistics_Total.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Total.java) | 35 | Button_Statistics | Кнопка: статистика всего | drawText, getColor |
| [`Button_Game_ArrowRight.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_ArrowRight.java) | 31 | Button_Game | Кнопка: игра стрелка правый | drawText |
| [`Button_Game_ArrowLeft.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_ArrowLeft.java) | 29 | Button_Game | Кнопка: игра стрелка левый | drawText |
| [`Button_Game_Decline2.java`](../age/of/civilizations2/jakowski/lukasz/Button_Game_Decline2.java) | 29 | Button_Game | Кнопка: игра отклонение 2 | drawButtonBG |
| [`Button_Icon.java`](../age/of/civilizations2/jakowski/lukasz/Button_Icon.java) | 24 | Button_Build | Кнопка: иконка | drawText |
| [`Button_Statistics_Title_Right.java`](../age/of/civilizations2/jakowski/lukasz/Button_Statistics_Title_Right.java) | 23 | Button_Statistics_Title | Кнопка: статистика заголовок правый | drawText |
| [`Button_Transparent_WithHoverEnabled.java`](../age/of/civilizations2/jakowski/lukasz/Button_Transparent_WithHoverEnabled.java) | 22 | Button | Кнопка: прозрачный подсказка включено | drawButtonBG, drawText |
| [`Button_ViewEnd.java`](../age/of/civilizations2/jakowski/lukasz/Button_ViewEnd.java) | 23 | Button_View | Кнопка: вид конец | drawButtonBG |
| [`Button_Transparent.java`](../age/of/civilizations2/jakowski/lukasz/Button_Transparent.java) | 14 | Button | Кнопка: прозрачный |  |
| [`Button_Close.java`](../age/of/civilizations2/jakowski/lukasz/Button_Close.java) | 15 | Button | Кнопка: закрыть | drawButtonBG |
| [`Button_ViewIcon.java`](../age/of/civilizations2/jakowski/lukasz/Button_ViewIcon.java) | 18 | Button | Кнопка: вид иконка | drawButtonBG, drawText |


## UI: элементы

Файлов: **186**

| Файл | Строк | Базовый класс | Назначение | Ключевые методы |
|---|---:|---|---|---|
| [`Text_Outliner_CurrentWar.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_CurrentWar.java) | 413 | Text | Текст: обзор текущий война | draw, getColor, buildElementHover, setCurrent |
| [`Text_AchievementCiv.java`](../age/of/civilizations2/jakowski/lukasz/Text_AchievementCiv.java) | 345 | Text | Текст: достижение цивилизация | draw, loadFlag, disposeFlag, getColor |
| [`Text_Outliner_DiploInfo_Alliance.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_Alliance.java) | 363 | Text | Текст: обзор дипломатия информация альянс | getImage, draw, getColor, buildElementHover |
| [`Text_PeaceTreaty_Scores.java`](../age/of/civilizations2/jakowski/lukasz/Text_PeaceTreaty_Scores.java) | 346 | Text | Текст: мир договор очки | draw, drawActive, getImageScale, getColor |
| [`Text_Outliner_DiploInfo_War.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_War.java) | 346 | Text | Текст: обзор дипломатия информация война | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_SignedDefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_SignedDefensivePact.java) | 338 | Text | Текст: обзор дипломатия информация подписано оборонительный пакт | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_Guarantee.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_Guarantee.java) | 338 | Text | Текст: обзор дипломатия информация гарантия | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_FriendlyCivs.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_FriendlyCivs.java) | 338 | Text | Текст: обзор дипломатия информация дружественный цивилизации | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_Truce.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_Truce.java) | 337 | Text | Текст: обзор дипломатия информация перемирие | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_Annexation.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_Annexation.java) | 338 | Text | Текст: обзор дипломатия информация аннексия | getImage, draw, getColor, buildElementHover |
| [`Message_PeaceTreaty.java`](../age/of/civilizations2/jakowski/lukasz/Message_PeaceTreaty.java) | 275 | Message | Сообщение: мир договор | onAction, onAccept, onDecline, getImageID |
| [`Text_Outliner_DiploInfo_SignedNonAggressionPact.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_SignedNonAggressionPact.java) | 337 | Text | Текст: обзор дипломатия информация подписано Non агрессия пакт | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_HaveMilitaryAccess.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_HaveMilitaryAccess.java) | 335 | Text | Текст: обзор дипломатия информация военный доступ | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_IsVassal.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_IsVassal.java) | 335 | Text | Текст: обзор дипломатия информация вассал | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_DiploInfo_IsNotVassal.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_IsNotVassal.java) | 335 | Text | Текст: обзор дипломатия информация вассал | getImage, draw, getColor, buildElementHover |
| [`Text_PeaceTreaty_Result.java`](../age/of/civilizations2/jakowski/lukasz/Text_PeaceTreaty_Result.java) | 312 | Text | Текст: мир договор результат | draw, drawActive, getImageScale, getColor |
| [`Text_Outliner_DiploInfo_NewColony.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_NewColony.java) | 317 | Text | Текст: обзор дипломатия информация новый колония | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_TechLevel.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_TechLevel.java) | 309 | Text | Текст: обзор технология уровень | draw, getColor, buildElementHover, setCurrent |
| [`Text_Outliner_ResearchProgress.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_ResearchProgress.java) | 309 | Text | Текст: обзор исследование прогресс | draw, getColor, buildElementHover, setCurrent |
| [`Text_Outliner_WarPreparations.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_WarPreparations.java) | 318 | Text | Текст: обзор война подготовка | draw, getColor, buildElementHover, setCurrent |
| [`Text_Outliner_DiploInfo_Union.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_Union.java) | 299 | Text | Текст: обзор дипломатия информация уния | getImage, draw, getColor, buildElementHover |
| [`Message_GoldenAgeMilitary.java`](../age/of/civilizations2/jakowski/lukasz/Message_GoldenAgeMilitary.java) | 230 | Message | Сообщение: золотой эпоха военный | onAction, onAccept, onDecline, getImageID |
| [`Message_GoldenAgeScience.java`](../age/of/civilizations2/jakowski/lukasz/Message_GoldenAgeScience.java) | 230 | Message | Сообщение: золотой эпоха наука | onAction, onAccept, onDecline, getImageID |
| [`Message_GoldenAge.java`](../age/of/civilizations2/jakowski/lukasz/Message_GoldenAge.java) | 230 | Message | Сообщение: золотой эпоха | onAction, onAccept, onDecline, getImageID |
| [`Text_Economy_SliderDesc_Research.java`](../age/of/civilizations2/jakowski/lukasz/Text_Economy_SliderDesc_Research.java) | 270 | Text | Текст: экономика слайдер описание исследование | draw, getImageScale, setMin |
| [`Text_ServiceRibbon.java`](../age/of/civilizations2/jakowski/lukasz/Text_ServiceRibbon.java) | 290 | Text | Текст: сервис лента | draw, getColor |
| [`Text_Outliner_SaveGame.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_SaveGame.java) | 302 | Text | Текст: обзор сохранение игра | draw, getImageScale, buildElementHover, setCurrent |
| [`Text_Economy_Total.java`](../age/of/civilizations2/jakowski/lukasz/Text_Economy_Total.java) | 296 | Text | Текст: экономика всего | draw, getImageScale, setMax, getSFX |
| [`Text_Outliner_DiploInfo_Disease.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_Disease.java) | 274 | Text | Текст: обзор дипломатия информация болезнь | getImage, draw, getColor, buildElementHover |
| [`Text_Outliner_PartOfHRE.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_PartOfHRE.java) | 279 | Text | Текст: обзор часть СРИ | draw, getColor, buildElementHover, setCurrent |
| [`Text_Outliner_DiploInfo_AllianceLeaves.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner_DiploInfo_AllianceLeaves.java) | 260 | Text | Текст: обзор дипломатия информация альянс выходит | getImage, draw, getColor, buildElementHover |
| [`Text_Economy_Value.java`](../age/of/civilizations2/jakowski/lukasz/Text_Economy_Value.java) | 258 | Text | Текст: экономика значение | draw, setCurrent, setMax |
| [`TextSlider.java`](../age/of/civilizations2/jakowski/lukasz/TextSlider.java) | 262 | MenuElement | Слайдер с текстом. | init, addText, drawBG, draw |
| [`Text_Economy_Balance.java`](../age/of/civilizations2/jakowski/lukasz/Text_Economy_Balance.java) | 237 | Text | Текст: экономика баланс | draw, getImageScale, setMax |
| [`Text_ActionInfo_Cost_Right_Balance_Disband.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Cost_Right_Balance_Disband.java) | 187 | Text_ActionInfo | Текст: действие информация стоимость правый баланс роспуск | draw, getImageScale, getPosX, getWidth |
| [`Text_ActionInfo_Cost_Right_Balance.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Cost_Right_Balance.java) | 187 | Text_ActionInfo | Текст: действие информация стоимость правый баланс | draw, getImageScale, getPosX, getWidth |
| [`Text_Economy_SliderDesc.java`](../age/of/civilizations2/jakowski/lukasz/Text_Economy_SliderDesc.java) | 186 | Text | Текст: экономика слайдер описание | draw, getImageScale |
| [`Text_Scrollable.java`](../age/of/civilizations2/jakowski/lukasz/Text_Scrollable.java) | 193 | Text | Текст: прокручиваемый | init, draw_StartClip, draw_Element, draw_EndClip |
| [`Message_Uncivilized.java`](../age/of/civilizations2/jakowski/lukasz/Message_Uncivilized.java) | 154 | Message | Сообщение: нецивилизованный | onAction, onAccept, onDecline, getImageID |
| [`Text_Investemnts_SliderDescGoods.java`](../age/of/civilizations2/jakowski/lukasz/Text_Investemnts_SliderDescGoods.java) | 162 | Text | Текст: инвестиции слайдер описание товары | draw, getImageScale |
| [`Message_LowStability.java`](../age/of/civilizations2/jakowski/lukasz/Message_LowStability.java) | 149 | Message | Сообщение: низкий стабильность | onAction, onAccept, onDecline, getImageID |
| [`Text_Outliner.java`](../age/of/civilizations2/jakowski/lukasz/Text_Outliner.java) | 193 | Text | Текст: обзор | draw, getColor, setCurrent, getSFX |
| [`Text_LeftSide_Ideology.java`](../age/of/civilizations2/jakowski/lukasz/Text_LeftSide_Ideology.java) | 174 | Text_LeftSide | Текст: левый сторона идеология | draw, getPosX, getWidth, getImageScale |
| [`Text_BudgetTitle2.java`](../age/of/civilizations2/jakowski/lukasz/Text_BudgetTitle2.java) | 166 | Text_EconomyTitle | Текст: бюджет заголовок 2 | draw |
| [`Text_Investemnts_SliderDesc.java`](../age/of/civilizations2/jakowski/lukasz/Text_Investemnts_SliderDesc.java) | 147 | Text | Текст: инвестиции слайдер описание | draw, getImageScale |
| [`Message_Plunder.java`](../age/of/civilizations2/jakowski/lukasz/Message_Plunder.java) | 140 | Message | Сообщение: грабёж | onAction, onAccept, onDecline, getImageID |
| [`Message_Plunder_Plundred.java`](../age/of/civilizations2/jakowski/lukasz/Message_Plunder_Plundred.java) | 139 | Message | Сообщение: грабёж разграблено | onAction, onAccept, onDecline, getImageID |
| [`Message_Ultimatum.java`](../age/of/civilizations2/jakowski/lukasz/Message_Ultimatum.java) | 134 | Message | Сообщение: ультиматум | onAction, onAccept, onDecline, getBGImageID |
| [`Message_Bulit_Port.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Port.java) | 133 | Message | Сообщение: Bulit порт | onAction, onAccept, onDecline, getImageID |
| [`Message_InvestDone_Development.java`](../age/of/civilizations2/jakowski/lukasz/Message_InvestDone_Development.java) | 130 | Message | Сообщение: инвестиции готово развитие | onAction, onAccept, onDecline, getImageID |
| [`Message_Bulit_Tower.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Tower.java) | 130 | Message | Сообщение: Bulit башня | onAction, onAccept, onDecline, getImageID |
| [`Message_Bulit_Fort.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Fort.java) | 132 | Message | Сообщение: Bulit форт | onAction, onAccept, onDecline, getImageID |
| [`Message_Bulit_Library.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Library.java) | 132 | Message | Сообщение: Bulit библиотека | onAction, onAccept, onDecline, getImageID |
| [`Text_BudgetTitle.java`](../age/of/civilizations2/jakowski/lukasz/Text_BudgetTitle.java) | 140 | Text_EconomyTitle | Текст: бюджет заголовок | draw |
| [`Message_Bulit_Supply.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Supply.java) | 128 | Message | Сообщение: Bulit снабжение | onAction, onAccept, onDecline, getImageID |
| [`Message_Bulit_Farm.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Farm.java) | 128 | Message | Сообщение: Bulit ферма | onAction, onAccept, onDecline, getImageID |
| [`Message_Bulit_Workshop.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Workshop.java) | 127 | Message | Сообщение: Bulit мастерская | onAction, onAccept, onDecline, getImageID |
| [`Message_Bulit_Armoury.java`](../age/of/civilizations2/jakowski/lukasz/Message_Bulit_Armoury.java) | 123 | Message | Сообщение: Bulit арсенал | onAction, onAccept, onDecline, getImageID |
| [`Message_GoodsLow.java`](../age/of/civilizations2/jakowski/lukasz/Message_GoodsLow.java) | 134 | Message | Сообщение: товары низкий | onAction, onAccept, onDecline, getImageID |
| [`Message_Relations_Insult.java`](../age/of/civilizations2/jakowski/lukasz/Message_Relations_Insult.java) | 128 | Message | Сообщение: отношения оскорбление | onAction, onAccept, onDecline, getImageID |
| [`Message_HighInflation.java`](../age/of/civilizations2/jakowski/lukasz/Message_HighInflation.java) | 119 | Message | Сообщение: высокий инфляция | onAction, onAccept, onDecline, getImageID |
| [`Message_Disease.java`](../age/of/civilizations2/jakowski/lukasz/Message_Disease.java) | 126 | Message | Сообщение: болезнь | onAction, onAccept, onDecline, getPlagueName |
| [`Message_Relations_Increase_Ended.java`](../age/of/civilizations2/jakowski/lukasz/Message_Relations_Increase_Ended.java) | 109 | Message | Сообщение: отношения увеличение закончено | onAction, onAccept, onDecline, getImageID |
| [`Message_ProvincesNotSupplied.java`](../age/of/civilizations2/jakowski/lukasz/Message_ProvincesNotSupplied.java) | 110 | Message | Сообщение: провинции снабжение | onAction, onAccept, onDecline, getImageID |
| [`Message_ProvincesNotSupplied_Straves.java`](../age/of/civilizations2/jakowski/lukasz/Message_ProvincesNotSupplied_Straves.java) | 111 | Message | Сообщение: провинции снабжение голод | onAction, onAccept, onDecline, getImageID |
| [`Text_TitleStyle.java`](../age/of/civilizations2/jakowski/lukasz/Text_TitleStyle.java) | 115 | Text | Текст: заголовок стиль | draw, getColor, getColor_BG |
| [`Message_Relations_Friendly.java`](../age/of/civilizations2/jakowski/lukasz/Message_Relations_Friendly.java) | 105 | Message | Сообщение: отношения дружественный | onAction, onAccept, onDecline, getImageID |
| [`Message_ProvincesNotSupplied_LostControl_EnemyLost.java`](../age/of/civilizations2/jakowski/lukasz/Message_ProvincesNotSupplied_LostControl_EnemyLost.java) | 101 | Message | Сообщение: провинции снабжение потеряно контроль враг потеряно | onAction, onAccept, onDecline, getImageID |
| [`Message_AssimilationEnd.java`](../age/of/civilizations2/jakowski/lukasz/Message_AssimilationEnd.java) | 110 | Message | Сообщение: ассимиляция конец | onAction, onAccept, onDecline, getImageID |
| [`Message_InvestDone.java`](../age/of/civilizations2/jakowski/lukasz/Message_InvestDone.java) | 109 | Message | Сообщение: инвестиции готово | onAction, onAccept, onDecline, getImageID |
| [`Message_FestivalIsOver.java`](../age/of/civilizations2/jakowski/lukasz/Message_FestivalIsOver.java) | 110 | Message | Сообщение: фестиваль над | onAction, onAccept, onDecline, getImageID |
| [`Text_AlliesNotInWar.java`](../age/of/civilizations2/jakowski/lukasz/Text_AlliesNotInWar.java) | 105 | Text | Текст: союзники в война | draw, drawTopLine, getColor |
| [`Text_ActionInfo_Cost.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Cost.java) | 98 | Text_ActionInfo | Текст: действие информация стоимость | draw, getImageScale |
| [`Message_ProvincesOccupiedNotAtWar_LostControl.java`](../age/of/civilizations2/jakowski/lukasz/Message_ProvincesOccupiedNotAtWar_LostControl.java) | 99 | Message | Сообщение: провинции оккупированный война потеряно контроль | onAction, onAccept, onDecline, getImageID |
| [`Message_HRE_Elections_NewEmperor.java`](../age/of/civilizations2/jakowski/lukasz/Message_HRE_Elections_NewEmperor.java) | 104 | Message | Сообщение: СРИ выборы новый император | onAction, onAccept, onDecline, getImageID |
| [`Message_PrepareForWar_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_PrepareForWar_Accepted.java) | 110 | Message | Сообщение: подготовка война принято | onAction, onAccept, onDecline, getImageID |
| [`Message_PrepareForWar_Refused.java`](../age/of/civilizations2/jakowski/lukasz/Message_PrepareForWar_Refused.java) | 110 | Message | Сообщение: подготовка война отклонено | onAction, onAccept, onDecline, getImageID |
| [`Message_UltimatumRefusedWar.java`](../age/of/civilizations2/jakowski/lukasz/Message_UltimatumRefusedWar.java) | 100 | Message | Сообщение: ультиматум отклонено война | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_Move.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Move.java) | 110 | Text_ActionInfo | Текст: действие информация перемещение | draw, getImageScale, getWidth, getColor |
| [`Message_ProvincesNotSupplied_LostControl.java`](../age/of/civilizations2/jakowski/lukasz/Message_ProvincesNotSupplied_LostControl.java) | 98 | Message | Сообщение: провинции снабжение потеряно контроль | onAction, onAccept, onDecline, getImageID |
| [`Message_LeftAlliance.java`](../age/of/civilizations2/jakowski/lukasz/Message_LeftAlliance.java) | 109 | Message | Сообщение: левый альянс | onAction, onAccept, onDecline, getImageID |
| [`Message_Gift_Refused.java`](../age/of/civilizations2/jakowski/lukasz/Message_Gift_Refused.java) | 103 | Message | Сообщение: подарок отклонено | onAction, onAccept, onDecline, getImageID |
| [`Message_LowHappiness.java`](../age/of/civilizations2/jakowski/lukasz/Message_LowHappiness.java) | 99 | Message | Сообщение: низкий счастье | onAction, onAccept, onDecline, getImageID |
| [`Text_FlagActionStats.java`](../age/of/civilizations2/jakowski/lukasz/Text_FlagActionStats.java) | 133 | Text | Текст: флаг действие статистика | draw, setText, getWidth, getColor |
| [`Message_Vassalization_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_Vassalization_Accepted.java) | 99 | Message | Сообщение: вассализация принято | onAction, onAccept, onDecline, getImageID |
| [`Message_Vassalization_Rejected.java`](../age/of/civilizations2/jakowski/lukasz/Message_Vassalization_Rejected.java) | 99 | Message | Сообщение: вассализация отклонено | onAction, onAccept, onDecline, getImageID |
| [`Message_InvestmentsLow.java`](../age/of/civilizations2/jakowski/lukasz/Message_InvestmentsLow.java) | 96 | Message | Сообщение: инвестиции низкий | onAction, onAccept, onDecline, getImageID |
| [`Message_Gift_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_Gift_Accepted.java) | 97 | Message | Сообщение: подарок принято | onAction, onAccept, onDecline, getImageID |
| [`Message_HRE_ElectionsInNextTurn.java`](../age/of/civilizations2/jakowski/lukasz/Message_HRE_ElectionsInNextTurn.java) | 96 | Message | Сообщение: СРИ выборы в следующий ход | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_Cost_Right.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Cost_Right.java) | 107 | Text_ActionInfo | Текст: действие информация стоимость правый | draw, getImageScale, getPosX, getWidth |
| [`Message_Revolt.java`](../age/of/civilizations2/jakowski/lukasz/Message_Revolt.java) | 94 | Message | Сообщение: восстание | onAction, onAccept, onDecline, getImageID |
| [`Message_MilitaryAccess_ExpireSoon.java`](../age/of/civilizations2/jakowski/lukasz/Message_MilitaryAccess_ExpireSoon.java) | 98 | Message | Сообщение: военный доступ истекает скоро | onAction, onAccept, onDecline, getImageID |
| [`Message_RebelsSupported.java`](../age/of/civilizations2/jakowski/lukasz/Message_RebelsSupported.java) | 100 | Message | Сообщение: повстанцы поддержано | onAction, onAccept, onDecline, getImageID |
| [`Message_WeCanSignPeace_StatusQou.java`](../age/of/civilizations2/jakowski/lukasz/Message_WeCanSignPeace_StatusQou.java) | 88 | Message | Сообщение: подписать мир статус кво | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_RecruitInstantly.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_RecruitInstantly.java) | 97 | Text_ActionInfo | Текст: действие информация набор мгновенно | draw, getImageScale, getWidth, getColor |
| [`Message_RelocateCapital.java`](../age/of/civilizations2/jakowski/lukasz/Message_RelocateCapital.java) | 99 | Message | Сообщение: переезд столица | onAction, onAccept, onDecline, getBGImageID |
| [`Message_WeCanSignPeace.java`](../age/of/civilizations2/jakowski/lukasz/Message_WeCanSignPeace.java) | 88 | Message | Сообщение: подписать мир | onAction, onAccept, onDecline, getImageID |
| [`Message_Independence_Expired.java`](../age/of/civilizations2/jakowski/lukasz/Message_Independence_Expired.java) | 96 | Message | Сообщение: независимость истёк | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_Turns.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Turns.java) | 97 | Text_ActionInfo | Текст: действие информация ходы | draw, getImageScale, getWidth, getColor |
| [`Message_IndependenceFrom_Expired.java`](../age/of/civilizations2/jakowski/lukasz/Message_IndependenceFrom_Expired.java) | 94 | Message | Сообщение: независимость From истёк | onAction, onAccept, onDecline, getImageID |
| [`Message_NonAggressionPact_Expired.java`](../age/of/civilizations2/jakowski/lukasz/Message_NonAggressionPact_Expired.java) | 94 | Message | Сообщение: Non агрессия пакт истёк | onAction, onAccept, onDecline, getImageID |
| [`Message_MilitaryAccess_Expired.java`](../age/of/civilizations2/jakowski/lukasz/Message_MilitaryAccess_Expired.java) | 94 | Message | Сообщение: военный доступ истёк | onAction, onAccept, onDecline, getImageID |
| [`Message_DefensivePact_Expired.java`](../age/of/civilizations2/jakowski/lukasz/Message_DefensivePact_Expired.java) | 94 | Message | Сообщение: оборонительный пакт истёк | onAction, onAccept, onDecline, getImageID |
| [`Message_PrepareForWar.java`](../age/of/civilizations2/jakowski/lukasz/Message_PrepareForWar.java) | 89 | Message | Сообщение: подготовка война | onAction, onAccept, onDecline, getImageID |
| [`Message_JoinedAWar.java`](../age/of/civilizations2/jakowski/lukasz/Message_JoinedAWar.java) | 98 | Message | Сообщение: вступил война | onAction, onAccept, onDecline, getImageID |
| [`Message_WarReparationsRepaid_Green.java`](../age/of/civilizations2/jakowski/lukasz/Message_WarReparationsRepaid_Green.java) | 94 | Message | Сообщение: война репарации выплачено зелёный | onAction, onAccept, onDecline, getImageID |
| [`Message_WarReparationsRepaid.java`](../age/of/civilizations2/jakowski/lukasz/Message_WarReparationsRepaid.java) | 94 | Message | Сообщение: война репарации выплачено | onAction, onAccept, onDecline, getImageID |
| [`Message_UltimatumAccepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_UltimatumAccepted.java) | 94 | Message | Сообщение: ультиматум принято | onAction, onAccept, onDecline, getImageID |
| [`Message_Truce_Expired.java`](../age/of/civilizations2/jakowski/lukasz/Message_Truce_Expired.java) | 94 | Message | Сообщение: перемирие истёк | onAction, onAccept, onDecline, getImageID |
| [`Text.java`](../age/of/civilizations2/jakowski/lukasz/Text.java) | 139 | MenuElement | Базовый текстовый элемент (шрифт, цвет, выравнивание, перенос). | updateTextPosition, draw, getColor, getText |
| [`Message_TechResearched.java`](../age/of/civilizations2/jakowski/lukasz/Message_TechResearched.java) | 92 | Message | Сообщение: технология изучено | onAction, onAccept, onDecline, getImageID |
| [`Message_CallToArms_Join.java`](../age/of/civilizations2/jakowski/lukasz/Message_CallToArms_Join.java) | 92 | Message | Сообщение: призыв оружие вступление | onAction, onAccept, onDecline, getImageID |
| [`Message_LoanRepaid.java`](../age/of/civilizations2/jakowski/lukasz/Message_LoanRepaid.java) | 96 | Message | Сообщение: кредит выплачено | onAction, onAccept, onDecline, getImageID |
| [`Message_AllyJoinedAWar.java`](../age/of/civilizations2/jakowski/lukasz/Message_AllyJoinedAWar.java) | 92 | Message | Сообщение: союзник вступил война | onAction, onAccept, onDecline, getImageID |
| [`Message_TechPoints.java`](../age/of/civilizations2/jakowski/lukasz/Message_TechPoints.java) | 97 | Message | Сообщение: технология очки | onAction, onAccept, onDecline, getImageID |
| [`Message_Union_Refused.java`](../age/of/civilizations2/jakowski/lukasz/Message_Union_Refused.java) | 97 | Message | Сообщение: уния отклонено | onAction, onAccept, onDecline, getImageID |
| [`Message_Independence_Ask_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_Independence_Ask_Accepted.java) | 94 | Message | Сообщение: независимость запрос принято | onAction, onAccept, onDecline, getImageID |
| [`Message_NonAggressionPact_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_NonAggressionPact_Accepted.java) | 94 | Message | Сообщение: Non агрессия пакт принято | onAction, onAccept, onDecline, getImageID |
| [`Message_MilitaryAccess_Ask_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_MilitaryAccess_Ask_Accepted.java) | 94 | Message | Сообщение: военный доступ запрос принято | onAction, onAccept, onDecline, getImageID |
| [`Message_Alliance_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_Alliance_Accepted.java) | 95 | Message | Сообщение: альянс принято | onAction, onAccept, onDecline, getImageID |
| [`Message_NonAggressionPact_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Message_NonAggressionPact_Denied.java) | 94 | Message | Сообщение: Non агрессия пакт отказано | onAction, onAccept, onDecline, getImageID |
| [`Message_Independence_Ask_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Message_Independence_Ask_Denied.java) | 94 | Message | Сообщение: независимость запрос отказано | onAction, onAccept, onDecline, getImageID |
| [`Message_DefensivePact_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_DefensivePact_Accepted.java) | 94 | Message | Сообщение: оборонительный пакт принято | onAction, onAccept, onDecline, getImageID |
| [`Message_MilitaryAccess_Ask_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Message_MilitaryAccess_Ask_Denied.java) | 94 | Message | Сообщение: военный доступ запрос отказано | onAction, onAccept, onDecline, getImageID |
| [`Message_DefensivePact_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Message_DefensivePact_Denied.java) | 94 | Message | Сообщение: оборонительный пакт отказано | onAction, onAccept, onDecline, getImageID |
| [`Message_TradeReuest_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_TradeReuest_Accepted.java) | 94 | Message | Сообщение: торговля запрос принято | onAction, onAccept, onDecline, getImageID |
| [`Message_Truce.java`](../age/of/civilizations2/jakowski/lukasz/Message_Truce.java) | 94 | Message | Сообщение: перемирие | onAction, onAccept, onDecline, getImageID |
| [`Message_PeaceTreaty_Rejected.java`](../age/of/civilizations2/jakowski/lukasz/Message_PeaceTreaty_Rejected.java) | 94 | Message | Сообщение: мир договор отклонено | onAction, onAccept, onDecline, getImageID |
| [`Message_TradeReuest_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Message_TradeReuest_Denied.java) | 94 | Message | Сообщение: торговля запрос отказано | onAction, onAccept, onDecline, getImageID |
| [`Text_LeftSide_Stability.java`](../age/of/civilizations2/jakowski/lukasz/Text_LeftSide_Stability.java) | 106 | Text_LeftSide | Текст: левый сторона стабильность | draw, getPosX, getWidth, getImageScale |
| [`Message_TransferControl_Refused.java`](../age/of/civilizations2/jakowski/lukasz/Message_TransferControl_Refused.java) | 93 | Message | Сообщение: передача контроль отклонено | onAction, onAccept, onDecline, getImageID |
| [`Message_Union_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_Union_Accepted.java) | 93 | Message | Сообщение: уния принято | onAction, onAccept, onDecline, getImageID |
| [`Message_Age.java`](../age/of/civilizations2/jakowski/lukasz/Message_Age.java) | 92 | Message | Сообщение: эпоха | onAction, onAccept, onDecline, getImageID |
| [`Message_TransferControl_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_TransferControl_Accepted.java) | 93 | Message | Сообщение: передача контроль принято | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_MovementCost_Right.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_MovementCost_Right.java) | 96 | Text | Текст: действие информация движение стоимость правый | draw, getColor, getSFX |
| [`Message_DeclarationOfIndependence.java`](../age/of/civilizations2/jakowski/lukasz/Message_DeclarationOfIndependence.java) | 87 | Message | Сообщение: объявление независимость | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_Right_ArmyBonues.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Right_ArmyBonues.java) | 100 | Text_ActionInfo_Right | Текст: действие информация правый армия бонусы | draw, getWidth, getColor, getColorValue |
| [`Message_Alliance_Denied.java`](../age/of/civilizations2/jakowski/lukasz/Message_Alliance_Denied.java) | 93 | Message | Сообщение: альянс отказано | onAction, onAccept, onDecline, getImageID |
| [`Message_PeaceTreaty_Accepted.java`](../age/of/civilizations2/jakowski/lukasz/Message_PeaceTreaty_Accepted.java) | 93 | Message | Сообщение: мир договор принято | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_ArmyBonus.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_ArmyBonus.java) | 95 | Text_ActionInfo | Текст: действие информация армия бонус | draw, getWidth, getColor |
| [`Text_EconomyTitle.java`](../age/of/civilizations2/jakowski/lukasz/Text_EconomyTitle.java) | 83 | Text | Текст: экономика заголовок | draw, getColor |
| [`Message_Relations_Increase.java`](../age/of/civilizations2/jakowski/lukasz/Message_Relations_Increase.java) | 84 | Message | Сообщение: отношения увеличение | onAction, onAccept, onDecline, getImageID |
| [`Message_War_CivIsAtWar.java`](../age/of/civilizations2/jakowski/lukasz/Message_War_CivIsAtWar.java) | 84 | Message | Сообщение: война цивилизация война | onAction, onAccept, onDecline, getImageID |
| [`Message_Liberation.java`](../age/of/civilizations2/jakowski/lukasz/Message_Liberation.java) | 86 | Message | Сообщение: освобождение | onAction, onAccept, onDecline, getImageID |
| [`Text_Economy_SliderDesc_Taxes.java`](../age/of/civilizations2/jakowski/lukasz/Text_Economy_SliderDesc_Taxes.java) | 83 | Text | Текст: экономика слайдер описание налоги | draw, getImageScale, setText, setMax |
| [`Message_War.java`](../age/of/civilizations2/jakowski/lukasz/Message_War.java) | 84 | Message | Сообщение: война | onAction, onAccept, onDecline, getImageID |
| [`Message_Repaid.java`](../age/of/civilizations2/jakowski/lukasz/Message_Repaid.java) | 85 | Message | Сообщение: выплачено | onAction, onAccept, onDecline, getImageID |
| [`Message_CallToArms_Deny.java`](../age/of/civilizations2/jakowski/lukasz/Message_CallToArms_Deny.java) | 84 | Message | Сообщение: призыв оружие отклонить | onAction, onAccept, onDecline, getImageID |
| [`Text_LeftSide_Happiness.java`](../age/of/civilizations2/jakowski/lukasz/Text_LeftSide_Happiness.java) | 91 | Text_LeftSide | Текст: левый сторона счастье | draw, getPosX, getWidth, getImageScale |
| [`Message_TransferControl.java`](../age/of/civilizations2/jakowski/lukasz/Message_TransferControl.java) | 81 | Message | Сообщение: передача контроль | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo.java) | 81 | Text | Текст: действие информация | draw, getColor |
| [`Drag_Civilization.java`](../age/of/civilizations2/jakowski/lukasz/Drag_Civilization.java) | 87 | — | Перетаскивание цивилизации (редактор). | draw, getCivID, setCivID, getPosX |
| [`Message_CallToArms.java`](../age/of/civilizations2/jakowski/lukasz/Message_CallToArms.java) | 77 | Message | Сообщение: призыв оружие | onAction, onAccept, onDecline, getImageID |
| [`Text_ActionInfo_Right.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_Right.java) | 79 | Text | Текст: действие информация правый | draw, getColor |
| [`Message_DeclarationOfIndependence_ByVassal.java`](../age/of/civilizations2/jakowski/lukasz/Message_DeclarationOfIndependence_ByVassal.java) | 75 | Message | Сообщение: объявление независимость вассал | onAction, onAccept, onDecline, getImageID |
| [`Message_Gift.java`](../age/of/civilizations2/jakowski/lukasz/Message_Gift.java) | 75 | Message | Сообщение: подарок | onAction, onAccept, onDecline, getImageID |
| [`Message_OpenBudget.java`](../age/of/civilizations2/jakowski/lukasz/Message_OpenBudget.java) | 78 | Message | Сообщение: открытый бюджет | onAction, onAccept, onDecline, getImageID |
| [`Message.java`](../age/of/civilizations2/jakowski/lukasz/Message.java) | 76 | Serializable | Базовое сообщение игроку. | onAction, onAccept, onDecline, getImageID |
| [`Message_Independence_Ask.java`](../age/of/civilizations2/jakowski/lukasz/Message_Independence_Ask.java) | 71 | Message | Сообщение: независимость запрос | onAction, onAccept, onDecline, getImageID |
| [`Message_NonAggressionPact.java`](../age/of/civilizations2/jakowski/lukasz/Message_NonAggressionPact.java) | 71 | Message | Сообщение: Non агрессия пакт | onAction, onAccept, onDecline, getImageID |
| [`Message_MilitaryAccess_Give.java`](../age/of/civilizations2/jakowski/lukasz/Message_MilitaryAccess_Give.java) | 69 | Message | Сообщение: военный доступ дать | onAction, onAccept, onDecline, getImageID |
| [`Message_MilitaryAccess_Ask.java`](../age/of/civilizations2/jakowski/lukasz/Message_MilitaryAccess_Ask.java) | 69 | Message | Сообщение: военный доступ запрос | onAction, onAccept, onDecline, getImageID |
| [`Message_TradeReuest.java`](../age/of/civilizations2/jakowski/lukasz/Message_TradeReuest.java) | 70 | Message | Сообщение: торговля запрос | onAction, onAccept, onDecline, getImageID |
| [`Message_DefensivePact.java`](../age/of/civilizations2/jakowski/lukasz/Message_DefensivePact.java) | 69 | Message | Сообщение: оборонительный пакт | onAction, onAccept, onDecline, getImageID |
| [`Message_OfferVasalization.java`](../age/of/civilizations2/jakowski/lukasz/Message_OfferVasalization.java) | 69 | Message | Сообщение: предложение вассализация | onAction, onAccept, onDecline, getImageID |
| [`Message_UltimatumRefused.java`](../age/of/civilizations2/jakowski/lukasz/Message_UltimatumRefused.java) | 71 | Message | Сообщение: ультиматум отклонено | onAction, onAccept, onDecline, getImageID |
| [`Text_Logo.java`](../age/of/civilizations2/jakowski/lukasz/Text_Logo.java) | 68 | Text | Текст: логотип | draw, getSFX |
| [`Message_Union.java`](../age/of/civilizations2/jakowski/lukasz/Message_Union.java) | 69 | Message | Сообщение: уния | onAction, onAccept, onDecline, getImageID |
| [`Message_Type.java`](../age/of/civilizations2/jakowski/lukasz/Message_Type.java) | 111 | — | Сообщение: тип |  |
| [`TutorialBox.java`](../age/of/civilizations2/jakowski/lukasz/TutorialBox.java) | 71 | — | UI-бокс туториала. | draw, getPosX, getPosY, getWidth |
| [`Text_Scrollable_CNG_Options.java`](../age/of/civilizations2/jakowski/lukasz/Text_Scrollable_CNG_Options.java) | 57 | Text_Scrollable | Текст: прокручиваемый CNG опции | draw_Element |
| [`TextSlider_Line.java`](../age/of/civilizations2/jakowski/lukasz/TextSlider_Line.java) | 65 | — | текст слайдер линия | draw, setHeight, getHeight |
| [`Text_Army.java`](../age/of/civilizations2/jakowski/lukasz/Text_Army.java) | 58 | Text | Текст: армия | draw, setText |
| [`Text_LeftSide_Icon.java`](../age/of/civilizations2/jakowski/lukasz/Text_LeftSide_Icon.java) | 40 | Text_LeftSide | Текст: левый сторона иконка | draw, getImageScale |
| [`Text_BuildTitle_Terrain.java`](../age/of/civilizations2/jakowski/lukasz/Text_BuildTitle_Terrain.java) | 39 | Text_BuildTitle | Текст: строительство заголовок местность | draw, getImageScale |
| [`Text_LeftSide.java`](../age/of/civilizations2/jakowski/lukasz/Text_LeftSide.java) | 56 | Text | Текст: левый сторона | draw, getPosX, getHeight, getWidth |
| [`Dialog.java`](../age/of/civilizations2/jakowski/lukasz/Dialog.java) | 60 | — | Диалоговое окно. |  |
| [`Text_Scale.java`](../age/of/civilizations2/jakowski/lukasz/Text_Scale.java) | 41 | Text | Текст: масштаб | draw |
| [`Text_CivInfo.java`](../age/of/civilizations2/jakowski/lukasz/Text_CivInfo.java) | 51 | Text | Текст: информация о цивилизации | draw, getHeight, getWidth, getColor |
| [`Text_BuildTitle.java`](../age/of/civilizations2/jakowski/lukasz/Text_BuildTitle.java) | 35 | Text_EconomyTitle | Текст: строительство заголовок | draw, getColor |
| [`Text_DialogBG.java`](../age/of/civilizations2/jakowski/lukasz/Text_DialogBG.java) | 35 | Text | Текст: диалог BG | draw |
| [`Text_Build.java`](../age/of/civilizations2/jakowski/lukasz/Text_Build.java) | 29 | Text | Текст: строительство | draw |
| [`Text_Clear.java`](../age/of/civilizations2/jakowski/lukasz/Text_Clear.java) | 34 | Text | Текст: очистить | draw, getCurrent, getTextPos |
| [`Text_ActionInfo_MovementCost_Right_Free.java`](../age/of/civilizations2/jakowski/lukasz/Text_ActionInfo_MovementCost_Right_Free.java) | 15 | Text_ActionInfo_MovementCost_Right | Текст: действие информация движение стоимость правый свободный | getColor |
| [`TextData.java`](../age/of/civilizations2/jakowski/lukasz/TextData.java) | 21 | — | Данные текста (текст, шрифт, цвет). | getString, getWidth |
| [`Tutorial_ActionType.java`](../age/of/civilizations2/jakowski/lukasz/Tutorial_ActionType.java) | 6 | — | туториал действие тип |  |
