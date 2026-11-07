/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateScenario;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Civilizations
extends Menu_CreateScenario {
    private String selectAProvince;
    private int iStepWidth = 0;
    private String selectAProvince2;
    private int iStepWidth2 = 0;
    private String sCivilizations;
    private int iCivilizationsWidth;
    private int iLastKnownNumOfCivs = -1;

    protected Menu_CreateScenario_Civilizations() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CustomizeWasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AssignProvinces"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddNewCivilization") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH + CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false, true){

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (!this.getClickable()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
                }
                CFG.game.getCiv(CFG.game.getActiveProvinceID() >= 0 ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() : 0).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getActiveProvinceID() >= 0 ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() : 0).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
            }

            @Override
            protected void buildElementHover() {
                try {
                    if (CFG.game.getActiveProvinceID() < 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectProvince") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else if (this.getClickable()) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RemoveCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        this.menuElementHover = null;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING * 3 + CFG.BUTTON_WIDTH * 2 + CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false, true){

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (!this.getClickable()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
                }
                CFG.game.getCiv(CFG.game.getActiveProvinceID() >= 0 ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() : 0).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getActiveProvinceID() >= 0 ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() : 0).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
            }

            @Override
            protected void buildElementHover() {
                try {
                    if (this.getClickable() && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        this.menuElementHover = null;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Game("", -1, CFG.PADDING * 4 + CFG.BUTTON_WIDTH * 3 + CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                }
                ImageManager.getImage(Images.wikipedia).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.wikipedia).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.wikipedia).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                try {
                    if (this.getClickable() && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        this.menuElementHover = null;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    this.menuElementHover = null;
                }
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        super.updateLanguage();
        this.selectAProvince = CFG.langManager.get("ManageCivilizations");
        CFG.glyphLayout.setText(CFG.fontMain, this.selectAProvince);
        this.iStepWidth = (int)CFG.glyphLayout.width;
        this.selectAProvince2 = CFG.langManager.get("ClickAprovinceOnTheMapToAddOrRemoveCivilization") + ".";
        CFG.glyphLayout.setText(CFG.fontMain, this.selectAProvince2);
        this.iStepWidth2 = (int)CFG.glyphLayout.width;
        this.getMenuElement(3).setText(CFG.langManager.get("AddCivilization"));
        this.getMenuElement(4).setText(CFG.langManager.get("Remove"));
        this.getMenuElement(5).setText(CFG.langManager.get("SetCapital"));
        this.updatedButtonsWidthFromToID(3, 7, CFG.PADDING, CFG.BUTTON_WIDTH);
        this.sCivilizations = CFG.langManager.get("Civilizations");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        if (this.getMenuElement(3).getVisible()) {
            CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, this.getMenuElement(3).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(6).getPosX() + this.getMenuElement(6).getWidth() + CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        }
        CFG.drawTextWithShadow(oSB, this.selectAProvince, CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.selectAProvince2, CFG.GAME_WIDTH / 2 - (int)((float)this.iStepWidth2 * 0.8f / 2.0f) + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75f));
        CFG.fontMain.getData().setScale(1.0f);
        if (this.iLastKnownNumOfCivs != CFG.game.getCivsSize()) {
            CFG.glyphLayout.setText(CFG.fontMain, this.sCivilizations + ": " + (CFG.game.getCivsSize() - 1));
            this.iCivilizationsWidth = (int)CFG.glyphLayout.width;
            this.iLastKnownNumOfCivs = CFG.game.getCivsSize();
        }
        oSB.setColor(new Color(0.06f, 0.06f, 0.06f, 1.0f));
        CFG.fontMain.getData().setScale(0.9f);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iCivilizationsWidth * 0.9f)) + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - CFG.TEXT_HEIGHT - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iCivilizationsWidth * 0.9f), CFG.TEXT_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight());
        ImageManager.getImage(Images.civ_name_bg).draw(oSB, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iCivilizationsWidth * 0.9f)) + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iCivilizationsWidth * 0.9f), false, true);
        CFG.drawTextWithShadow(oSB, this.sCivilizations + ": " + (CFG.game.getCivsSize() - 1), CFG.GAME_WIDTH - (int)((float)this.iCivilizationsWidth * 0.9f) - CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - CFG.TEXT_HEIGHT - CFG.CIV_NAME_BG_EXTRA_HEIGHT + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 1: {
                if (CFG.game.getCivsSize() < 3) {
                    CFG.toast.setInView(CFG.langManager.get("Error") + " - " + CFG.langManager.get("PlayableCivilizations") + ": " + (CFG.game.getCivsSize() - 1));
                } else {
                    CFG.iCreateScenario_AssignProvinces_Civ = 0;
                    CFG.lCreateScenario_UndoAssignProvincesCivID.clear();
                    if (CFG.game.getActiveProvinceID() >= 0) {
                        CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                    }
                    CFG.game.setActiveProvinceID(-1);
                    CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN);
                    CFG.menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
                }
                return;
            }
            case 2: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 3: {
                CFG.sSearch = null;
                CFG.iCreateScenario_ActiveProvinceID = CFG.game.getActiveProvinceID();
                if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                }
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CIVILIZATIONS_SELECT);
                CFG.menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
                return;
            }
            case 4: {
                CFG.iCreateScenario_ActiveProvinceID = CFG.game.getActiveProvinceID();
                CFG.setDialogType(Dialog.CREATE_SCENARIO_REMOVE_CIVILIZATION);
                return;
            }
            case 5: {
                if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID() >= 0) {
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).resetArmies(-1);
                        try {
                            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(1));
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            // empty catch block
                        }
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).setIsCapital(false);
                    }
                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setCapitalProvinceID(CFG.game.getActiveProvinceID());
                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).setIsCapital(true);
                    CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).resetArmies(-1);
                    try {
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                    CFG.updateCreateScenario_Civilizations();
                    CFG.toast.setInView(CFG.langManager.get("Capital") + ": " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
                }
                return;
            }
            case 6: {
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag();
                    CFG.setDialogType(Dialog.GO_TO_WIKI);
                }
                return;
            }
        }
        super.actionElement(iID);
    }

    @Override
    protected void onBackPressed() {
        CFG.brushTool = false;
        CFG.updateNumOfAvailableProvinces();
        CFG.lCreateScenario_UndoWastelandProvinces.clear();
        if (CFG.game.getActiveProvinceID() >= 0) {
            CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
        }
        CFG.game.setActiveProvinceID(-1);
        CFG.backToMenu = Menu.eCREATE_SCENARIO_WASTELAND;
        CFG.goToMenu = Menu.eCREATE_SCENARIO_CIVILIZATIONS;
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_AVAILABLE_PROVINCES);
        CFG.menuManager.setVisible_CreateScenario_Civilizations_Suggest(false);
    }
}

