/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Descripted;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Scenario;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Events_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Scenarios;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Province_Cores_GameData;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Undo_AssignProvinceCiv;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Editor_Scenarios
extends SliderMenu {
    protected Menu_Editor_Scenarios() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_Descripted(CFG.map.getMapAuthor(CFG.map.getActiveMapID()), CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()), (int)(50.0f * CFG.GUI_SCALE), 0 + AoCGame.LEFT, CFG.PADDING, CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapName_Just(CFG.map.getActiveMapID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LandProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countLandProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SeaProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countSeaProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
        for (int i = 0; i < Game_Scenarios.SCENARIOS_SIZE; ++i) {
            menuElements.add(new Button_Menu_Scenario(i, "", (int)(50.0f * CFG.GUI_SCALE), 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getGameScenarios().getScenarioDay(this.getCurrent()) + " " + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(this.getCurrent())) + " " + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(this.getCurrent())).getName()));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getNumOfCivs(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioAuthor(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tag") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioTag(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
        CFG.lCreateScenario_UndoAssignProvincesCivID = new ArrayList<Undo_AssignProvinceCiv>();
        CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<Integer>();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(1).setText(CFG.langManager.get("CreateNewScenario"));
        for (int i = 0; i < Game_Scenarios.SCENARIOS_SIZE; ++i) {
            this.getMenuElement(i + 2).setText(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(i)));
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.backToMenu = Menu.eEDITOR_SCENARIOS;
                CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
                return;
            }
            case 1: {
                CFG.province_Cores_GameData = new Province_Cores_GameData();
                CFG.chosen_AlphabetCharachter = null;
                CFG.RELOAD_SCENARIO = true;
                if (CFG.FOG_OF_WAR > 1) {
                    CFG.FOG_OF_WAR = 1;
                }
                CFG.game.initPlayers();
                CFG.game.getPlayer(0).setCivID(0);
                CFG.palletManager.setActivePalletID(0);
                CFG.iCreateScenario_AssignProvinces_Civ = 0;
                CFG.lCreateScenario_UndoAssignProvincesCivID.clear();
                CFG.lCreateScenario_UndoWastelandProvinces.clear();
                CFG.eventsManager.eventsGD = new Events_GameData();
                CFG.game.setActiveProvinceID(-1);
                CFG.game.createScenarioClearCivilizations();
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_WASTELAND);
                CFG.CREATE_SCENARIO_NAME = "";
                CFG.CREATE_SCENARIO_AUTHOR = "";
                CFG.CREATE_SCENARIO_WIKI = "";
                CFG.CREATE_SCENARIO_GAME_DATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.CREATE_SCENARIO_AGE = 0;
                Game_Calendar.currentYear = CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getBeginningYear();
                Game_Calendar.ENABLE_COLONIZATION = false;
                Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
                Game_Calendar.COLONIZATION_TECH_LEVEL = 0.8f;
                CFG.CREATE_SCENARIO_IS_PART_OF_CAMPAIGN = false;
                CFG.lCREATE_SCENARIO_IS_PART_OF_CAMPAIGN_CIVSIDS.clear();
                CFG.buildCreateScenario_TechnologyLevelsByContinents();
                CFG.map.getMapBG().disposeMinimapOfCivilizations();
                CFG.game.getGameScenarios().setScenario_StartingArmyInCapitals(750);
                CFG.game.getGameScenarios().setScenario_StartingPopulation(65000);
                CFG.game.getGameScenarios().setScenario_StartingEconomy(32000);
                CFG.game.getGameScenarios().setScenario_StartingMoney(4500);
                CFG.game.getGameScenarios().setScenario_ActivePallet_TAG(null);
                return;
            }
        }
        CFG.chosen_AlphabetCharachter = null;
        CFG.RELOAD_SCENARIO = true;
        if (CFG.FOG_OF_WAR > 1) {
            CFG.FOG_OF_WAR = 1;
        }
        CFG.game.initPlayers();
        CFG.game.getPlayer(0).setCivID(0);
        CFG.game.setActiveProvinceID(-1);
        CFG.iCreateScenario_AssignProvinces_Civ = 0;
        CFG.lCreateScenario_UndoAssignProvincesCivID.clear();
        CFG.lCreateScenario_UndoWastelandProvinces.clear();
        CFG.game.getGameScenarios().editScenario(iID - 2);
        CFG.game.getGameScenarios().loadArmiesData();
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CIVILIZATIONS);
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
    }
}

