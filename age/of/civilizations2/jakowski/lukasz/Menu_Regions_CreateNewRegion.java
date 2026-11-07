/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Regions_CreateNewRegion
extends SliderMenu {
    private String sName;
    private int iNameWidth;

    protected Menu_Regions_CreateNewRegion() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Menu("", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : (this.getClickable() ? new Color(1.0f, 1.0f, 1.0f, 1.0f) : new Color(0.84f, 0.84f, 0.84f, 0.7f));
            }

            @Override
            protected String getTextToDraw() {
                return Menu_Regions_CreateNewRegion.this.sName + ": " + super.getText();
            }

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + Menu_Regions_CreateNewRegion.this.iNameWidth;
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(new Color(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB(), 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2, this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING, this.getTextWidth(), CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB(), 1.0f) : (this.getClickable() ? new Color(0.38f, 0.38f, 0.38f, 1.0f) : new Color(0.49f, 0.49f, 0.49f, 0.5f));
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("RegionName");
        CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
        this.iNameWidth = (int)CFG.glyphLayout.width;
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.editor_Region_GameData.getName());
        this.getMenuElement(2).setText(CFG.langManager.get("Save"));
        this.getMenuElement(3).setText(CFG.langManager.get("Color"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R(oSB, iTranslateX, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, this.getMenuElement(3).getWidth() + CFG.PADDING * 2, this.getMenuElement(3).getHeight() + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.showKeyboard();
                return;
            }
            case 2: {
                if (this.getMenuElement(1).getText().length() > 0) {
                    CFG.editor_Region_GameData.setName(this.getMenuElement(1).getText());
                    CFG.game.saveRegionPackagesData();
                    this.onBackPressed();
                } else {
                    CFG.showKeyboard(1);
                    CFG.toast.setInView(this.sName);
                    CFG.toast.setTimeInView(3000);
                }
                return;
            }
            case 3: {
                if (CFG.menuManager.getColorPicker().getVisible()) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
                    CFG.menuManager.getColorPicker().setPosY(this.getMenuElement(3).getPosY() + this.getMenuElement(3).getHeight() + CFG.PADDING + CFG.menuManager.getColorPicker().getPosX());
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB());
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.MAP_EDITOR_REGION_COLOR);
                }
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        CFG.menuManager.setViewID(CFG.backToMenu);
        CFG.menuManager.setBackAnimation(true);
        Game_Render_Province.updateDrawProvinces();
    }
}

