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

class Button_Menu_Active
extends Button_Menu {
    protected Button_Menu_Active(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    @Override
    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - this.getTextWidth() / 2 : this.getTextPos()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        } else {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - this.getTextWidth() / 2 : this.getTextPos()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        }
    }

    @Override
    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (isActive) {
            ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
        } else if (this.getIsHovered() && this.getClickable()) {
            oSB.setColor(CFG.COLOR_BUTTON_MENU_HOVER_BG);
            ImageManager.getImage(Images.btnh_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            oSB.setColor(Color.WHITE);
        } else {
            ImageManager.getImage(Images.btnh_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
        }
    }

    @Override
    protected final Color getColor(boolean isActive) {
        return isActive ? (this.getClickable() ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : new Color(0.78f, 0.78f, 0.78f, 0.7f)) : new Color(0.1f, 0.1f, 0.1f, 1.0f);
    }
}

