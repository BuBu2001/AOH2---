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
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_SetUpArmy
extends SliderMenu {
    protected static final int DESELECT_ALL_ID = 4;
    private String sSetUpArmy;
    private int iStepWidth;

    protected Menu_CreateScenario_SetUpArmy() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, false){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.selectMode;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Slide(CFG.GAME_WIDTH - (CFG.PADDING + ImageManager.getImage(Images.slide_bg).getHeight() / 2) - ImageManager.getImage(Images.slide_bg).getHeight() * 2, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 5 + ImageManager.getImage(Images.slide_bg).getHeight() / 2, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sSetUpArmy = CFG.langManager.get("SetUpArmy");
        CFG.glyphLayout.setText(CFG.fontMain, this.sSetUpArmy);
        this.iStepWidth = (int)CFG.glyphLayout.width;
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(2).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(3).setText(CFG.langManager.get("Select"));
        this.getMenuElement(4).setText(CFG.langManager.get("DeselectAll"));
        this.getMenuElement(5).setText(CFG.langManager.get("Undo"));
        int extraX = this.updateButtonWidth(2, CFG.PADDING, CFG.BUTTON_WIDTH * 2) + CFG.PADDING;
        for (int i = 3; i < 6; ++i) {
            extraX += this.updateButtonWidth(i, extraX + CFG.PADDING, CFG.BUTTON_WIDTH) + CFG.PADDING;
        }
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(3).getWidth() - CFG.PADDING;
        this.getMenuElement(3).setPosX(tempX);
        tempX = tempX - this.getMenuElement(2).getWidth() - CFG.PADDING;
        this.getMenuElement(2).setPosX(tempX);
        tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
        this.getMenuElement(5).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(5).getPosX() - CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(5).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawTextWithShadow(oSB, this.sSetUpArmy, CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX, CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY, Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 2: {
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
                this.getMenuElement(6).setVisible(CFG.brushTool);
                this.getMenuElement(6).setClickable(CFG.brushTool);
                return;
            }
            case 3: {
                CFG.selectMode = !CFG.selectMode;
                return;
            }
            case 4: {
                CFG.game.getSelectedProvinces().clearSelectedProvinces();
                CFG.menuManager.setVisible_CreateScenario_SetUpArmies_Sliders(false);
                CFG.menuManager.setVisible_CreateScenario_SetUpArmies_Civs(false);
                CFG.selectMode = true;
                return;
            }
            case 5: {
                CFG.game.getSelectedProvinces().popProvince();
                if (CFG.game.getSelectedProvinces().getProvincesSize() == 0) {
                    CFG.selectMode = true;
                    CFG.menuManager.setVisible_CreateScenario_SetUpArmies_Sliders(false);
                    CFG.menuManager.setVisible_CreateScenario_SetUpArmies_Civs(false);
                } else {
                    CFG.menuManager.rebuildCreateScenario_SetUpArmies_Sliders();
                    CFG.menuManager.rebuildCreateScenario_SetUpArmies_Civs();
                }
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.brushTool = false;
        CFG.selectMode = true;
        this.getMenuElement(6).setVisible(CFG.brushTool);
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
    }
}

