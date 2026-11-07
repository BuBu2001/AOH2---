/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowDown;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowLeft;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowRight;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowUp;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_ShiftPort;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_PortPosition
extends SliderMenu {
    protected Menu_MapEditor_PortPosition() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
        menuElements.add(new Button_Game_ArrowLeft(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 - CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Button_Game_ArrowDown(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Button_Game_ArrowRight(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Button_Game_ArrowUp(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 2, true));
        menuElements.add(new Button_Transparent(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 - CFG.PADDING * 4, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, true));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(6).setText(CFG.langManager.get("ConvertToAnotherScale"));
        this.updateButtonWidth(6, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        this.getMenuElement(6).setPosX(CFG.GAME_WIDTH - this.getMenuElement(6).getWidth() - CFG.PADDING);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX, iTranslateY, this.getMenuElement(6).getWidth() + CFG.PADDING * 2, this.getMenuElement(6).getHeight() + CFG.PADDING * 2);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 - CFG.PADDING * 5 + iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 4 - 1 - ImageManager.getImage(Images.new_game_top_edge).getHeight(), CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 5 + 1, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + 1, false, false);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                    Editor_ShiftPort.savePortPosition(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftX() - 1, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftY());
                }
                return;
            }
            case 2: {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                    Editor_ShiftPort.savePortPosition(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftY() + 1);
                }
                return;
            }
            case 3: {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                    Editor_ShiftPort.savePortPosition(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftX() + 1, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftY());
                }
                return;
            }
            case 4: {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                    Editor_ShiftPort.savePortPosition(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftX(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPortShiftY() - 1);
                }
                return;
            }
            case 6: {
                CFG.editorManager.resetInUseEditors();
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PORT_POSITION_CONVERT);
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        CFG.menuManager.setBackAnimation(true);
        CFG.editorManager.resetInUseEditors();
        Game_Render_Province.updateDrawProvinces();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
        }
    }
}

