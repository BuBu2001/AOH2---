/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options;
import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options2;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_BG_CNG;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateNewGame_Options_v2
extends SliderMenu {
    protected static final int ANIMATION_TIME = 175;
    protected static long lTime = 0L;
    protected static boolean hideAnimation = true;

    protected Menu_CreateNewGame_Options_v2() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempMaxH = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) - CFG.PADDING;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2 + CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth(), 0, 0, tempW, tempElemH, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(Color.WHITE);
                CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateNewGame_Options_v2.this.getMenuPosY() + this.getHeight() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() / 2);
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }

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
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageGrowthRateOfProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countAvarageGrowthRate() + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 25, 0){

            @Override
            protected String getDrawText() {
                return this.getText() + ": " + (int)((1.0f + (float)this.getCurrent() * 0.1f) * 100.0f) + "%";
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefaultScaleOfMap") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 2, tempW, tempElemH, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    if (CFG.game.getPlayer(i).getCivID() > 0) {
                        CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFlag().draw(oSB, this.getTextPos() + (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getTextPos() + (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                        oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getB() / 255.0f, 1.0f));
                        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getTextPos() + (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY - 2 - (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE), CFG.CIV_FLAG_WIDTH, (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE));
                    } else {
                        ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getTextPos() + (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getTextPos() + (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                        oSB.setColor(CFG.RANDOM_CIVILIZATION_COLOR);
                        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getTextPos() + (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY - 2 - (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE), CFG.CIV_FLAG_WIDTH, (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE));
                    }
                    oSB.setColor(Color.WHITE);
                }
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Players") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getPlayersSize()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(i).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getPlayer(i).getCivID() > 0 ? CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCivName() : CFG.langManager.get("RandomCivilization")));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 3, tempW, tempElemH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(CFG.game.getScenarioID())).getName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioAuthor(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 4, tempW, tempElemH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("VictoryConditions") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Domination")));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ControlProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + VicotryManager.VICTORY_CONTROL_PROVINCES_PERC + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsLimit") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (VicotryManager.VICTORY_LIMIT_OF_TURNS == 0 ? CFG.langManager.get("NoThanks") : CFG.langManager.get("TurnsX", VicotryManager.VICTORY_LIMIT_OF_TURNS)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (VicotryManager.VICTORY_TECHNOLOGY == 0.0f ? CFG.langManager.get("Disabled") : Float.valueOf((float)((int)(VicotryManager.VICTORY_TECHNOLOGY * 100.0f)) / 100.0f)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 5 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 9, CFG.DIFFICULTY * 2 + 1){

            @Override
            protected String getDrawText() {
                return this.getText();
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DifficultyLevel") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getDifficultyName(CFG.DIFFICULTY)));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Beginner")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Normal")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Hard")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Extreme")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Legendary")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 6 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 5, CFG.FOG_OF_WAR * 2 + 1){

            @Override
            protected String getDrawText() {
                return this.getText();
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Fogofwar") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getFogOfWarName(CFG.FOG_OF_WAR)));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Off") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheWholeMapAndSoldiersAreVisibleAtAllTimes") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Classic") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceOwnershipIsKnownButSoldiersCanOnlyBeSeenInAdjacentProvinces") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Discovery") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheWorldIsCoveredByFogCivilizationsMustBeDiscoveredBeforeTheyCanBeInteractedWith") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 9, tempW, tempElemH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeDiplomaticRelationsBetweenCivilizations") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 10, tempW, tempElemH, true, CFG.FILL_THE_MAP){

            @Override
            protected boolean getCheckboxState() {
                return CFG.FILL_THE_MAP;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IfDisabledAllCivilizationsStartWithOnlyTheirCapitalProvince") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 12, tempW, tempElemH, true, CFG.RANDOM_PLACMENT){

            @Override
            protected boolean getCheckboxState() {
                return CFG.RANDOM_PLACMENT;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlacesCapitalsInRandomProvinces") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 13, tempW, tempElemH, true, CFG.RANDOM_FILL){

            @Override
            protected boolean getCheckboxState() {
                return CFG.RANDOM_FILL;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RandomnlyFillsTheWorldWithDifferentCivilizations") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 14, tempW, tempElemH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SwapCivilizationsToRandomPlaces") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 15, tempW, tempElemH, true, CFG.SANDBOX_MODE){

            @Override
            protected boolean getCheckboxState() {
                return CFG.SANDBOX_MODE;
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 19, tempW, tempElemH, true, CFG.TOTAL_WAR_MODE){

            @Override
            protected boolean getCheckboxState() {
                return CFG.TOTAL_WAR_MODE;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NoneoftheseCivilizationshasthewordforPeaceintheirlanguage") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2("", CFG.PADDING * 2, 0, tempElemH * 11, tempW, tempElemH, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (CFG.palletManager.getActivePalletID() == 0) {
                    CFG.palletManager.drawSampleColors_Standard(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, this.getWidth() - CFG.PADDING * 4, this.getHeight() - CFG.PADDING * 4, 0, isActive);
                } else {
                    CFG.palletManager.drawSampleColors(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, this.getWidth() - CFG.PADDING * 4, this.getHeight() - CFG.PADDING * 4, CFG.palletManager.getActivePalletID() - 1, isActive);
                }
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SetsOfTheColorsForCivilizations") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 16, tempW, tempElemH, true, CFG.SPECTATOR_MODE){

            @Override
            protected boolean getCheckboxState() {
                return CFG.SPECTATOR_MODE;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ObserveCivilizationsAndTheirStruggleForSupremacy") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 17, tempW, tempElemH, true, Game_Calendar.ENABLE_COLONIZATION){

            @Override
            protected boolean getCheckboxState() {
                return Game_Calendar.ENABLE_COLONIZATION;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.langManager.get("ColonizationofWastelandProvinces") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 18, tempW, tempElemH, true, Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES){

            @Override
            protected boolean getCheckboxState() {
                return Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofNeutralProvinces") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 7 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, (int)(Game_Calendar.GAME_SPEED_MIN * 10.0f), (int)(Game_Calendar.GAME_SPEED_MAX * 10.0f), (int)(Game_Calendar.GAME_SPEED * 10.0f)){

            @Override
            protected String getDrawText() {
                return this.getText() + this.getCurrent() * 10 + "%";
            }
        });
        menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 8 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 50, 400, (int)(Game_Calendar.AI_AGGRESSIVNESS * 100.0f)){

            @Override
            protected String getDrawText() {
                return this.getText() + this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65f);
            }
        });
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_CreateNewGame_Options_v2.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_CreateNewGame_Options_v2.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_CreateNewGame_Options_v2.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_CreateNewGame_Options_v2.this.getWidth(), 1);
                if (AoCGame.LEFT != 0) {
                    oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
                    ImageManager.getImage(Images.pix255_255_255).draw2(oSB, Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - this.getHeight(), 1, this.getHeight(), true, false);
                    oSB.setColor(Color.WHITE);
                }
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), CFG.COLOR_TEXT_OPTIONS_LEFT_NS);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, tempMaxH < tempElemH * menuElements.size() ? tempMaxH : tempElemH * menuElements.size(), menuElements);
        this.setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Options"));
        this.getMenuElement(0).setText(CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()));
        this.getMenuElement(1).setText(CFG.langManager.get("ScaleOfMap"));
        this.getMenuElement(2).setText(CFG.langManager.get("Players") + ":");
        this.getMenuElement(3).setText(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())) + " | " + CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID()) + " " + CFG.langManager.get("Civilizations"));
        this.getMenuElement(4).setText(CFG.langManager.get("VictoryConditions") + ": " + CFG.langManager.get("Domination"));
        this.getMenuElement(5).setText(CFG.langManager.get("Difficulty") + ": " + CFG.getDifficultyName(CFG.DIFFICULTY));
        this.getMenuElement(6).setText(CFG.langManager.get("Fogofwar") + ": " + CFG.getFogOfWarName(CFG.FOG_OF_WAR));
        this.getMenuElement(7).setText(CFG.langManager.get("ManageDiplomacy"));
        this.getMenuElement(8).setText(CFG.langManager.get("FillTheMap"));
        this.getMenuElement(9).setText(CFG.langManager.get("RandomPlacement"));
        this.getMenuElement(10).setText(CFG.langManager.get("RandomFill"));
        this.getMenuElement(11).setText(CFG.langManager.get("ShuffleCivilizations"));
        this.getMenuElement(12).setText(CFG.langManager.get("SandboxMode"));
        this.getMenuElement(13).setText(CFG.langManager.get("EternalWar"));
        this.getMenuElement(15).setText(CFG.langManager.get("SpectatorMode"));
        this.getMenuElement(16).setText(CFG.langManager.get("ColonizationofWastelandProvinces"));
        this.getMenuElement(17).setText(CFG.langManager.get("NeutralProvinces") + ": " + (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES ? CFG.langManager.get("Colonization") : CFG.langManager.get("Conquering")));
        this.getMenuElement(18).setText(CFG.langManager.get("GameSpeed") + ": ");
        this.getMenuElement(19).setText(CFG.langManager.get("AIAggressiveness") + ": ");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
            super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
        }
    }

    protected static final void clickFillTheMap() {
        CFG.viewsManager.disableAllViews();
        CFG.FILL_THE_MAP = !CFG.FILL_THE_MAP;
        CFG.game.disableDrawCivlizationsRegions_Players();
        if (CFG.FILL_THE_MAP) {
            CFG.game.getGameScenarios().enableFillTheMap();
            CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
        } else {
            CFG.game.getGameScenarios().disableFillTheMap();
            try {
                if (CFG.getActiveCivInfo() > 0) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                } else {
                    CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
        CFG.game.enableDrawCivlizationsRegions_Players();
        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
        CFG.updateActiveCivInfo_CreateNewGame();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.backToMenu = Menu.eCREATE_NEW_GAME;
                CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
                break;
            }
            case 1: {
                Map_Scale.STANDARD_SCALE = 1.0f + (float)this.getMenuElement(iID).getCurrent() * 0.1f;
                CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
                CFG.map.getMapScale().setScaleBeforeReset(Map_Scale.STANDARD_SCALE >= 3.0f ? 2.0f : (Map_Scale.STANDARD_SCALE > 1.0f ? 1.0f : 0.5f));
                break;
            }
            case 2: {
                CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
                break;
            }
            case 3: {
                CFG.menuManager.setVisible_CreateNewGame_Options_Scenarios(true);
                break;
            }
            case 4: {
                CFG.backToMenu = Menu.eCREATE_NEW_GAME;
                CFG.menuManager.setViewID(Menu.eVICTORY_CONDITIONS);
                break;
            }
            case 5: {
                if (CFG.DIFFICULTY == this.getMenuElement(iID).getCurrent() / 2) break;
                CFG.DIFFICULTY = this.getMenuElement(iID).getCurrent() / 2;
                this.getMenuElement(iID).setText(CFG.langManager.get("Difficulty") + ": " + CFG.getDifficultyName(CFG.DIFFICULTY));
                break;
            }
            case 6: {
                if (CFG.FOG_OF_WAR == this.getMenuElement(iID).getCurrent() / 2) break;
                CFG.FOG_OF_WAR = this.getMenuElement(iID).getCurrent() / 2;
                this.getMenuElement(iID).setText(CFG.langManager.get("Fogofwar") + ": " + CFG.getFogOfWarName(CFG.FOG_OF_WAR));
                break;
            }
            case 7: {
                CFG.game.setActiveProvinceID(-1);
                CFG.menuManager.rebuildManageDiplomacy_Alliances();
                CFG.game.disableDrawCivlizationsRegions_Players();
                CFG.chosen_AlphabetCharachter = null;
                CFG.resetManageDiplomacyIDs();
                CFG.backToMenu = Menu.eCREATE_NEW_GAME;
                CFG.menuManager.setViewID(Menu.eMANAGE_DIPLOMACY);
                Game_Render_Province.updateDrawProvinces();
                CFG.map.getMapTouchManager().update_ExtraAction();
                break;
            }
            case 8: {
                Menu_CreateNewGame_Options_v2.clickFillTheMap();
                break;
            }
            case 9: {
                CFG.RANDOM_PLACMENT = !CFG.RANDOM_PLACMENT;
                this.getMenuElement(iID).setCheckboxState(CFG.RANDOM_PLACMENT);
                break;
            }
            case 10: {
                CFG.RANDOM_FILL = !CFG.RANDOM_FILL;
                this.getMenuElement(iID).setCheckboxState(CFG.RANDOM_FILL);
                break;
            }
            case 11: {
                CFG.setDialogType(Dialog.SHUFFLE_CIVILIZATIONS);
                break;
            }
            case 12: {
                CFG.SANDBOX_MODE = !CFG.SANDBOX_MODE;
                this.getMenuElement(iID).setCheckboxState(CFG.SANDBOX_MODE);
                break;
            }
            case 13: {
                CFG.TOTAL_WAR_MODE = !CFG.TOTAL_WAR_MODE;
                this.getMenuElement(iID).setCheckboxState(CFG.TOTAL_WAR_MODE);
                if (CFG.TOTAL_WAR_MODE) {
                    CFG.toast.setInView(CFG.langManager.get("TotalWar") + " - " + CFG.langManager.get("Enabled"));
                    break;
                }
                CFG.toast.setInView(CFG.langManager.get("TotalWar") + " - " + CFG.langManager.get("Disabled"));
                break;
            }
            case 14: {
                CFG.menuManager.setVisible_CreateNewGame_Options_Pallets(true);
                break;
            }
            case 15: {
                CFG.SPECTATOR_MODE = !CFG.SPECTATOR_MODE;
                this.getMenuElement(iID).setCheckboxState(CFG.SPECTATOR_MODE);
                break;
            }
            case 16: {
                boolean bl = Game_Calendar.ENABLE_COLONIZATION = !Game_Calendar.ENABLE_COLONIZATION;
                if (Game_Calendar.ENABLE_COLONIZATION) {
                    CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Enabled"));
                    break;
                }
                CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Disabled"));
                break;
            }
            case 17: {
                Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
                this.updateLanguage();
                CFG.toast.setInView(this.getMenuElement(iID).getText());
                break;
            }
            case 18: {
                Game_Calendar.GAME_SPEED = (float)this.getMenuElement(iID).getCurrent() / 10.0f;
                break;
            }
            case 19: {
                Game_Calendar.AI_AGGRESSIVNESS = (float)this.getMenuElement(iID).getCurrent() / 100.0f;
            }
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
            this.setHideAnimation(false);
        } else {
            this.setHideAnimation(true);
        }
    }

    protected final void setHideAnimation(boolean nHideAnimation) {
        if (nHideAnimation != hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 175L ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        hideAnimation = nHideAnimation;
    }
}

