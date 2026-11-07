/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ManageDiplomacy
extends SliderMenu {
    protected Menu_ManageDiplomacy() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        CFG.sAtWar = CFG.langManager.get("AtWar");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getMenuElement(0).getWidth() + CFG.PADDING * 2, this.getMenuElement(0).getHeight() + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.game.checkAlliances();
        CFG.game.setActiveProvinceID(-1);
        CFG.map.getMapCoordinates().setDisableMovingMap(false);
        CFG.menuManager.setViewID(CFG.backToMenu);
        CFG.menuManager.setBackAnimation(true);
        CFG.menuManager.getColorPicker().setVisible(false, null);
        Game_Render_Province.updateDrawProvinces();
        CFG.map.getMapTouchManager().update_ExtraAction();
    }
}

