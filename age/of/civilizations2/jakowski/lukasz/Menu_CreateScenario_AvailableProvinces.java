/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateScenario;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_AvailableProvinces
extends Menu_CreateScenario {
    private String selectMapOfAvailableProvinces;
    private int iStepWidth;
    private String selectMapOfAvailableProvinces2;
    private int iStepWidth2;
    private String sPlayableProvinces;
    private int iPlayableProvincesWidth;
    private String sWastelandProvinces;
    private int iWastelandProvincesWidth;

    protected Menu_CreateScenario_AvailableProvinces() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectRegions"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
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
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManageCivilizations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Slide(CFG.GAME_WIDTH - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() / 2 - ImageManager.getImage(Images.slide_bg).getHeight() * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() * 2 - ImageManager.getImage(Images.slide_bg).getHeight() / 2, CFG.brushTool));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING * 3 + CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2, true, CFG.bSetWasteland_AvailableProvinces){

            @Override
            protected boolean getCheckboxState() {
                return CFG.bSetWasteland_AvailableProvinces;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, CFG.brushTool){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING * 3 + CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true, Game_Calendar.ENABLE_COLONIZATION){

            @Override
            protected boolean getCheckboxState() {
                return Game_Calendar.ENABLE_COLONIZATION;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofWastelandProvinces") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2 + CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        super.updateLanguage();
        this.selectMapOfAvailableProvinces = CFG.langManager.get("CustomizeWasteland");
        CFG.glyphLayout.setText(CFG.fontMain, this.selectMapOfAvailableProvinces);
        this.iStepWidth = (int)CFG.glyphLayout.width;
        this.selectMapOfAvailableProvinces2 = CFG.langManager.get("SetWhichProvincesOfTheWorldAreWasteland") + ".";
        CFG.glyphLayout.setText(CFG.fontMain, this.selectMapOfAvailableProvinces2);
        this.iStepWidth2 = (int)CFG.glyphLayout.width;
        this.getMenuElement(4).setText(CFG.langManager.get("Wasteland"));
        this.getMenuElement(5).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(6).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(7).setText(CFG.langManager.get("ColonizationofWasteland"));
        this.getMenuElement(8).setText(CFG.langManager.get("Reverse"));
        this.updatedButtonsWidthFromToID(4, 6, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        this.updatedButtonsWidthFromToID(6, 7, this.getMenuElement(5).getPosX() + this.getMenuElement(5).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
        this.updatedButtonsWidthFromToID(7, 8, CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
        this.updatedButtonsWidthFromToID(8, 9, CFG.PADDING, CFG.BUTTON_WIDTH);
        this.getMenuElement(8).setPosX(this.getMenuElement(7).getPosX() + this.getMenuElement(7).getWidth() + CFG.PADDING);
        this.sPlayableProvinces = CFG.langManager.get("Playable");
        CFG.glyphLayout.setText(CFG.fontMain, this.sPlayableProvinces + ": ");
        this.iPlayableProvincesWidth = (int)CFG.glyphLayout.width;
        this.sWastelandProvinces = CFG.langManager.get("Wasteland");
        CFG.glyphLayout.setText(CFG.fontMain, this.sWastelandProvinces + ": ");
        this.iWastelandProvincesWidth = (int)CFG.glyphLayout.width;
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
        this.getMenuElement(5).setPosX(tempX);
        tempX = tempX - this.getMenuElement(6).getWidth() - CFG.PADDING;
        this.getMenuElement(6).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(6).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, this.getMenuPosY() + this.getMenuElement(7).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING, this.getMenuElement(7).getHeight() + CFG.PADDING * 2);
        CFG.drawTextWithShadow(oSB, this.selectMapOfAvailableProvinces, CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.selectMapOfAvailableProvinces2, CFG.GAME_WIDTH / 2 - (int)((float)this.iStepWidth2 * 0.8f / 2.0f) + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75f));
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(new Color(0.06f, 0.06f, 0.06f, 1.0f));
        CFG.fontMain.getData().setScale(0.9f);
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iPlayableProvincesWidth * 0.9f) + CFG.iNumOfAvailableProvincesWidth) + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.TEXT_HEIGHT * 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 4 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iPlayableProvincesWidth * 0.9f) + CFG.iNumOfAvailableProvincesWidth, CFG.TEXT_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight());
        ImageManager.getImage(Images.civ_name_bg).draw(oSB, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iPlayableProvincesWidth * 0.9f) + CFG.iNumOfAvailableProvincesWidth) + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.TEXT_HEIGHT - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iPlayableProvincesWidth * 0.9f) + CFG.iNumOfAvailableProvincesWidth, false, true);
        CFG.drawTextWithShadow(oSB, this.sPlayableProvinces + ": " + CFG.iNumOfAvailableProvinces, CFG.GAME_WIDTH - (int)((float)this.iPlayableProvincesWidth * 0.9f) - CFG.iNumOfAvailableProvincesWidth - CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.TEXT_HEIGHT * 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 3 + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        ImageManager.getImage(Images.civ_name_bg).draw2(oSB, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iWastelandProvincesWidth * 0.9f) + CFG.iNumOfWastelandProvincesWidth) + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - CFG.TEXT_HEIGHT - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iWastelandProvincesWidth * 0.9f) + CFG.iNumOfWastelandProvincesWidth, CFG.TEXT_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight());
        ImageManager.getImage(Images.civ_name_bg).draw(oSB, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iWastelandProvincesWidth * 0.9f) + CFG.iNumOfWastelandProvincesWidth) + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY, CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)((float)this.iWastelandProvincesWidth * 0.9f) + CFG.iNumOfWastelandProvincesWidth, false, true);
        CFG.drawTextWithShadow(oSB, this.sWastelandProvinces + ": " + CFG.iNumOfWastelandProvinces, CFG.GAME_WIDTH - (int)((float)this.iWastelandProvincesWidth * 0.9f) - CFG.iNumOfWastelandProvincesWidth - CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - CFG.TEXT_HEIGHT - CFG.CIV_NAME_BG_EXTRA_HEIGHT + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 1: {
                int i;
                int nPlayableProvinces = 0;
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
                    ++nPlayableProvinces;
                }
                if (nPlayableProvinces < 2) {
                    CFG.toast.setInView(CFG.langManager.get("Error") + " - " + CFG.langManager.get("PlayableProvinces") + ": " + nPlayableProvinces);
                } else {
                    CFG.brushTool = false;
                    CFG.menuManager.setViewID(CFG.goToMenu);
                    if (CFG.goToMenu != Menu.eCREATE_RANDOM_GAME) {
                        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() < 0 || CFG.game.getProvince(i).getCivID() <= 0) continue;
                            CFG.game.getProvince(i).setCivID(0, false, false);
                        }
                        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                            if (CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getWasteland() < 0) continue;
                            boolean foundAnotherCapital = false;
                            for (int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
                                if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getWasteland() >= 0) continue;
                                CFG.game.getCiv(i).setCapitalProvinceID(CFG.game.getCiv(i).getProvinceID(j));
                                foundAnotherCapital = true;
                                break;
                            }
                            if (foundAnotherCapital) continue;
                            CFG.game.createScenarioRemoveCivilization(i);
                        }
                        CFG.game.buildWastelandLevels();
                        CFG.updateCreateScenario_Civilizations();
                        CFG.map.getMapBG().disposeMinimapOfCivilizations();
                    } else {
                        CFG.game.buildWastelandLevels();
                    }
                }
                return;
            }
            case 2: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 4: {
                CFG.bSetWasteland_AvailableProvinces = !CFG.bSetWasteland_AvailableProvinces;
                this.getMenuElement(iID).setCheckboxState(CFG.bSetWasteland_AvailableProvinces);
                return;
            }
            case 5: {
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
                this.getMenuElement(3).setVisible(CFG.brushTool);
                return;
            }
            case 6: {
                if (CFG.lCreateScenario_UndoWastelandProvinces.size() > 0) {
                    CFG.game.getProvince(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1)).setWasteland(CFG.game.getProvince(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1)).getWasteland() >= 0 ? -1 : 0);
                    CFG.game.setActiveProvinceID(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1));
                    if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                    CFG.removeUndoWastelandProvince();
                    CFG.updateNumOfAvailableProvinces();
                }
                return;
            }
            case 7: {
                boolean bl = Game_Calendar.ENABLE_COLONIZATION = !Game_Calendar.ENABLE_COLONIZATION;
                if (Game_Calendar.ENABLE_COLONIZATION) {
                    CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Enabled"));
                } else {
                    CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Disabled"));
                }
                return;
            }
            case 8: {
                CFG.setDialogType(Dialog.REVERSE_WASTELAND);
                return;
            }
        }
        super.actionElement(iID);
    }

    @Override
    protected void onBackPressed() {
        CFG.game.setActiveProvinceID(-1);
        CFG.brushTool = false;
        CFG.menuManager.setViewID(CFG.backToMenu);
    }
}

