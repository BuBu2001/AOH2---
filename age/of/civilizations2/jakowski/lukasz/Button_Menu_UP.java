/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_UP
extends Button_Menu {
    protected Button_Menu_UP(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super("", 0, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    @Override
    protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            ImageManager.getImage(Images.btnh_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
        } else if (this.getIsHovered() && this.getClickable()) {
            oSB.setColor(CFG.COLOR_BUTTON_MENU_HOVER_BG);
            ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            oSB.setColor(Color.WHITE);
        } else {
            ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
        }
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.45f));
            ImageManager.getImage(Images.btn_up).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_up).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_up).getHeight() / 2 + iTranslateY, true);
            oSB.setColor(Color.WHITE);
        } else {
            ImageManager.getImage(Images.btn_up).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_up).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_up).getHeight() / 2 + iTranslateY, true);
        }
    }
}

