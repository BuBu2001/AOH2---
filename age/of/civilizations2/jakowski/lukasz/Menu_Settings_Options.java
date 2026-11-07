/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConfigINI;
import age.of.civilizations2.jakowski.lukasz.LanguageManager;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Settings_Options
extends SliderMenu {
    protected Menu_Settings_Options() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, CFG.goToMenu2 == Menu.eMAINMENU){

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
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true){});
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, Gdx.app.getType() == Application.ApplicationType.Android, ConfigINI.landscape){

            @Override
            protected boolean getCheckboxState() {
                return ConfigINI.landscape;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.randomLeaders){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.randomLeaders;
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, tY, Menu_InGame_FlagAction.getWindowWidth() - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2) * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, Menu_InGame_FlagAction.getWindowWidth() - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), tY, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu("-", -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, tY, Menu_InGame_FlagAction.getWindowWidth() - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2) * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, Menu_InGame_FlagAction.getWindowWidth() - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), tY, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.showNextPlayerView){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.showNextPlayerView;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.showOrderOfMovesView){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.showOrderOfMovesView;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.CONTINUOUS_RENDERING){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.CONTINUOUS_RENDERING;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.CONFIRM_END_TURN){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.CONFIRM_END_TURN;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.CONFIRM_NO_ORDERS){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.CONFIRM_NO_ORDERS;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.reverseDirectionX){

            @Override
            protected boolean getCheckboxState() {
                return CFG.reverseDirectionX;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.reverseDirectionY){

            @Override
            protected boolean getCheckboxState() {
                return CFG.reverseDirectionY;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, LanguageManager.translationsKeysMode){

            @Override
            protected boolean getCheckboxState() {
                return LanguageManager.translationsKeysMode;
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true, CFG.settingsManager.loadCursor){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.loadCursor;
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, Menu_InGame_FlagAction.getWindowWidth(), CFG.BUTTON_HEIGHT, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        this.initMenu(null, 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT * 3 / 4, Menu_InGame_FlagAction.getWindowWidth(), CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()));
        this.getMenuElement(1).setText(CFG.langManager.get("Language") + ": " + CFG.langManager.get("LANGUAGENAME"));
        this.getMenuElement(2).setText(CFG.langManager.get("Graphics"));
        this.getMenuElement(3).setText(CFG.langManager.get("UIScale"));
        this.getMenuElement(4).setText(CFG.langManager.get("ProvinceSettings"));
        this.getMenuElement(5).setText(CFG.langManager.get("Audio"));
        this.getMenuElement(6).setText(CFG.langManager.get("Landscape"));
        this.getMenuElement(7).setText(CFG.langManager.get("Leaders") + ": " + CFG.langManager.get("Random"));
        this.getMenuElement(9).setText(CFG.langManager.get("FontSize") + ": " + CFG.settingsManager.FONT_MAIN_SIZE);
        this.getMenuElement(12).setText(CFG.langManager.get("TurnsBetweenAutosave") + ": " + CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE);
        this.getMenuElement(14).setText(CFG.langManager.get("ShowNextPlayerTurnView"));
        this.getMenuElement(15).setText(CFG.langManager.get("OrderOfMoves"));
        this.getMenuElement(16).setText(CFG.langManager.get("ContinuousRendering"));
        this.getMenuElement(17).setText(CFG.langManager.get("ConfirmEndTurn"));
        this.getMenuElement(18).setText(CFG.langManager.get("ConfirmNoOrders"));
        this.getMenuElement(19).setText(CFG.langManager.get("InvertXAxis"));
        this.getMenuElement(20).setText(CFG.langManager.get("InvertYAxis"));
        this.getMenuElement(21).setText(CFG.langManager.get("TranslationKeys"));
        this.getMenuElement(22).setText(CFG.langManager.get("CustomCursor"));
        this.getMenuElement(23).setText(CFG.langManager.get("Defaults"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getMenuElement(0).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.backToMenu = Menu.eSETTINGS;
                CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
                return;
            }
            case 1: {
                CFG.backToMenu = Menu.eSETTINGS;
                CFG.menuManager.setViewID(Menu.eSELECT_LANGUAGE);
                CFG.map.getMapBG().updateWorldMap_Shaders();
                CFG.VIEW_SHOW_VALUES = true;
                break;
            }
            case 2: {
                CFG.menuManager.setViewID(Menu.eSETTINGS_GRAPHICS);
                break;
            }
            case 3: {
                CFG.menuManager.setViewID(Menu.eSELECT_UI_SCALE);
                return;
            }
            case 4: {
                if (!SaveManager.gameCanBeContinued) {
                    for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                        CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
                    }
                }
                CFG.menuManager.setViewID(Menu.eSETTINGS_PROVINCE);
                return;
            }
            case 5: {
                CFG.menuManager.setVisible_Settings_Audio();
                break;
            }
            case 6: {
                ConfigINI.landscape = !ConfigINI.landscape;
                ConfigINI.saveConfig();
                Preferences prefs = Gdx.app.getPreferences("AND");
                prefs.putBoolean("landscape", ConfigINI.landscape);
                CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
                CFG.toast.setTimeInView(6000);
                break;
            }
            case 7: {
                CFG.settingsManager.randomLeaders = !CFG.settingsManager.randomLeaders;
                break;
            }
            case 8: {
                --CFG.settingsManager.FONT_MAIN_SIZE;
                if (CFG.settingsManager.FONT_MAIN_SIZE < 8) {
                    CFG.settingsManager.FONT_MAIN_SIZE = 8;
                }
                CFG.loadFontMain();
                this.updateLanguage();
                CFG.menuManager.updateLanguage();
                break;
            }
            case 9: {
                CFG.settingsManager.FONT_MAIN_SIZE = CFG.XXXXHDPI ? 42 : (CFG.XXXHDPI ? 36 : (CFG.XXHDPI ? 32 : (CFG.XHDPI ? 24 : 18)));
                CFG.loadFontMain();
                this.updateLanguage();
                CFG.menuManager.updateLanguage();
                break;
            }
            case 10: {
                ++CFG.settingsManager.FONT_MAIN_SIZE;
                if (CFG.settingsManager.FONT_MAIN_SIZE > 128) {
                    CFG.settingsManager.FONT_MAIN_SIZE = 128;
                }
                CFG.loadFontMain();
                this.updateLanguage();
                CFG.menuManager.updateLanguage();
                break;
            }
            case 11: {
                --CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE;
                if (CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE < 0) {
                    CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE = 0;
                }
                this.updateLanguage();
                break;
            }
            case 12: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 13: {
                ++CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE;
                if (CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE > 100) {
                    CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE = 100;
                }
                this.updateLanguage();
                break;
            }
            case 14: {
                CFG.settingsManager.showNextPlayerView = !CFG.settingsManager.showNextPlayerView;
                break;
            }
            case 15: {
                CFG.settingsManager.showOrderOfMovesView = !CFG.settingsManager.showOrderOfMovesView;
                break;
            }
            case 16: {
                CFG.settingsManager.CONTINUOUS_RENDERING = !CFG.settingsManager.CONTINUOUS_RENDERING;
                break;
            }
            case 17: {
                CFG.settingsManager.CONFIRM_END_TURN = !CFG.settingsManager.CONFIRM_END_TURN;
                break;
            }
            case 18: {
                CFG.settingsManager.CONFIRM_NO_ORDERS = !CFG.settingsManager.CONFIRM_NO_ORDERS;
                break;
            }
            case 19: {
                CFG.reverseDirectionX = !CFG.reverseDirectionX;
                CFG.map.getMapTouchManager().buildReversePosX();
                CFG.map.getMapTouchManager().buildReversePosX2();
                break;
            }
            case 20: {
                CFG.reverseDirectionY = !CFG.reverseDirectionY;
                CFG.map.getMapTouchManager().buildReversePosY();
                CFG.map.getMapTouchManager().buildReversePosY2();
                break;
            }
            case 21: {
                LanguageManager.translationsKeysMode = !LanguageManager.translationsKeysMode;
                CFG.langManager.updateKeyOutput();
                CFG.menuManager.updateLanguage();
                break;
            }
            case 22: {
                CFG.settingsManager.loadCursor = !CFG.settingsManager.loadCursor;
                AoCGame.loadCursor(false);
                if (!CFG.settingsManager.loadCursor) break;
                CFG.toast.setInView(" --- The cursor may disappear during video recording --- ", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            }
        }
        CFG.saveSettings();
    }
}

