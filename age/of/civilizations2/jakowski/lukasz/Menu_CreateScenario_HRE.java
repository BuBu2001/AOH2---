/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_HRE
extends SliderMenu {
    protected Menu_CreateScenario_HRE() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true));
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true, false){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.selectMode;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.VIEW_SHOW_VALUES;
            }
        });
        menuElements.add(new Slide(CFG.PADDING + ImageManager.getImage(Images.slide_bg).getHeight() / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() / 2 - ImageManager.getImage(Images.slide_bg).getHeight() * 2, CFG.brushTool));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH){

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
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.holyRomanEmpire_Manager.getHRE_Name(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException e) {
                    this.menuElementHover = null;
                }
                catch (NullPointerException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(2).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(3).setText(CFG.langManager.get("Select"));
        this.getMenuElement(4).setText(CFG.langManager.get("DeselectAll"));
        this.getMenuElement(5).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(6).setText(CFG.langManager.get("Map"));
        this.updateButtonWidth(3, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        for (int i = 3; i < 7; ++i) {
            this.updateButtonWidth(i, CFG.PADDING, CFG.BUTTON_WIDTH);
        }
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(3).getWidth() - CFG.PADDING;
        this.getMenuElement(3).setPosX(tempX);
        tempX = tempX - this.getMenuElement(2).getWidth() - CFG.PADDING;
        this.getMenuElement(2).setPosX(tempX);
        tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
        this.getMenuElement(5).setPosX(tempX);
        tempX = tempX - this.getMenuElement(6).getWidth() - CFG.PADDING;
        this.getMenuElement(6).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(6).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING, this.getMenuElement(0).getHeight() + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.brushTool = false;
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
                break;
            }
            case 1: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 2: {
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(7).setVisible(CFG.brushTool);
                break;
            }
            case 3: {
                CFG.selectMode = !CFG.selectMode;
                break;
            }
            case 4: {
                CFG.setDialogType(Dialog.DESELET_ALL_SELECTED_PROVINCES_CREATE_HOLY_ROMAN_EMPIRE);
                break;
            }
            case 5: {
                if (CFG.game.getSelectedProvinces().getProvincesSize() > 0 && CFG.holyRomanEmpire_Manager.removeProvince(CFG.game.getSelectedProvinces().getProvince(CFG.game.getSelectedProvinces().getProvincesSize() - 1))) {
                    CFG.menuManager.rebuildCreateScenario_HolyRomanEmpire_Princes();
                }
                CFG.game.getSelectedProvinces().popProvince();
                if (CFG.game.getSelectedProvinces().getProvincesSize() != 0) break;
                CFG.selectMode = true;
                break;
            }
            case 6: {
                CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
                break;
            }
            case 8: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "holy";
                CFG.setDialogType(Dialog.GO_TO_WIKI);
            }
        }
    }
}

