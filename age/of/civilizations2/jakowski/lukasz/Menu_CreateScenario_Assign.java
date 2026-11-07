/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateScenario;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Assign
extends Menu_CreateScenario {
    private String assignProvinces;
    private int iStepWidth;
    private String assignProvinces2;
    private int iStepWidth2;

    protected Menu_CreateScenario_Assign() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true){

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
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ScenarioSettings"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tip") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectCapital"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_city, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, CFG.brushTool){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Slide(CFG.GAME_WIDTH - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() / 2 - ImageManager.getImage(Images.slide_bg).getHeight() * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - ImageManager.getImage(Images.slide_bg).getHeight() * 2 - ImageManager.getImage(Images.slide_bg).getHeight() / 2, false));
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, !CFG.VIEW_SHOW_VALUES){

            @Override
            protected boolean getCheckboxState() {
                return !CFG.VIEW_SHOW_VALUES;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        super.updateLanguage();
        this.getMenuElement(3).setText(CFG.langManager.get("SelectCivilization"));
        this.updatedButtonsWidthFromToID(3, 4, CFG.PADDING, CFG.BUTTON_WIDTH);
        this.assignProvinces = CFG.langManager.get("AssignProvinces");
        CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces);
        this.iStepWidth = (int)CFG.glyphLayout.width;
        this.assignProvinces2 = CFG.langManager.get("ClickAProvinceOnTheMapToAssignProvinceToCivilization") + ".";
        CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces2);
        this.iStepWidth2 = (int)CFG.glyphLayout.width;
        this.getMenuElement(4).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(6).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(7).setText(CFG.langManager.get("Flags"));
        this.updatedButtonsWidthFromToID(4, 5, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        this.updatedButtonsWidthFromToID(6, 8, this.getMenuElement(4).getPosX() + this.getMenuElement(4).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(7).getWidth() - CFG.PADDING;
        this.getMenuElement(7).setPosX(tempX);
        tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(6).getWidth() - CFG.PADDING;
        this.getMenuElement(6).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(6).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, this.getMenuPosY() + this.getMenuElement(3).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(3).getPosX() + this.getMenuElement(3).getWidth() + CFG.PADDING, this.getMenuElement(3).getHeight() + CFG.PADDING * 2);
        CFG.drawTextWithShadow(oSB, this.assignProvinces, CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f));
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawTextWithShadow(oSB, this.assignProvinces2, CFG.GAME_WIDTH / 2 - (int)((float)this.iStepWidth2 * 0.8f / 2.0f) + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75f));
        CFG.fontMain.getData().setScale(1.0f);
        if (CFG.iCreateScenario_AssignProvinces_Civ >= 0) {
            CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().draw(oSB, CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.CIV_FLAG_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() - CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + iTranslateX, CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.CIV_FLAG_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 1: {
                CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
                CFG.brushTool = false;
                this.getMenuElement(4).setCheckboxState(CFG.brushTool);
                this.getMenuElement(5).setVisible(CFG.brushTool);
                CFG.game.setActiveProvinceID(-1);
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
                CFG.menuManager.disposeFlagsCreate_Scenario_Assign();
                return;
            }
            case 2: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 3: {
                CFG.game.setActiveProvinceID(-1);
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN_SELECT);
                CFG.menuManager.disposeFlagsCreate_Scenario_Assign();
                return;
            }
            case 4: {
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
                this.getMenuElement(iID + 1).setVisible(CFG.brushTool);
                return;
            }
            case 6: {
                if (CFG.lCreateScenario_UndoAssignProvincesCivID.size() > 0) {
                    CFG.game.getProvince(CFG.lCreateScenario_UndoAssignProvincesCivID.get(CFG.lCreateScenario_UndoAssignProvincesCivID.size() - 1).getProvinceID()).setCivID(CFG.lCreateScenario_UndoAssignProvincesCivID.get(CFG.lCreateScenario_UndoAssignProvincesCivID.size() - 1).getCivID(), false);
                    CFG.game.getProvince(CFG.lCreateScenario_UndoAssignProvincesCivID.get(CFG.lCreateScenario_UndoAssignProvincesCivID.size() - 1).getProvinceID()).buildProvinceCore();
                    CFG.province_Cores_GameData.clearCoresData(CFG.lCreateScenario_UndoAssignProvincesCivID.get(CFG.lCreateScenario_UndoAssignProvincesCivID.size() - 1).getProvinceID());
                    CFG.game.setActiveProvinceID(CFG.lCreateScenario_UndoAssignProvincesCivID.get(CFG.lCreateScenario_UndoAssignProvincesCivID.size() - 1).getProvinceID());
                    if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                    CFG.removeUndoAssignProvinces();
                }
                return;
            }
            case 7: {
                CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
                return;
            }
        }
        super.actionElement(iID);
    }

    @Override
    protected void onBackPressed() {
        CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
        CFG.brushTool = false;
        this.getMenuElement(4).setCheckboxState(CFG.brushTool);
        this.getMenuElement(5).setVisible(CFG.brushTool);
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CIVILIZATIONS);
        CFG.game.setActiveProvinceID(-1);
        CFG.updateCreateScenario_Civilizations();
        CFG.menuManager.disposeFlagsCreate_Scenario_Assign();
    }
}

