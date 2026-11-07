/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_MainMenu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_LR_MainMenu_TextScale_Active
extends Button_Menu_LR_MainMenu {
    protected Button_Menu_LR_MainMenu_TextScale_Active(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    protected Button_Menu_LR_MainMenu_TextScale_Active(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
    }

    @Override
    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(1.0f, 1.0f, 1.0f, 0.55f);
        if (!this.getClickable()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.4f));
            ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth());
            ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        } else if (isActive) {
            ImageManager.getImage(Images.btnh_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btnh_menu_h).getWidth());
            ImageManager.getImage(Images.btnh_menu_h).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        } else if (this.getIsHovered() && this.getClickable()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.485f));
            ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth());
            ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        } else {
            ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth());
            ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
        }
        if (this.getClickable() && !isActive && animationState >= 0) {
            if (animationState == 0) {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0f, this.getIsHovered() ? 1.0f : 0.65f);
                oSB.setColor(Button_Menu_LR_MainMenu_TextScale_Active.getColorLine());
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < System.currentTimeMillis() - 750L) {
                    ++animationState;
                    lTimeAnimation = System.currentTimeMillis();
                }
            } else {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0f, this.getIsHovered() ? 1.0f : 0.65f);
                oSB.setColor(Button_Menu_LR_MainMenu_TextScale_Active.getColorLine());
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < System.currentTimeMillis() - 750L) {
                    animationState = 0;
                    lTimeAnimation = System.currentTimeMillis();
                }
            }
            CFG.setRender_3(true);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.9f);
        if (isActive) {
            CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.9f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.9f / 2.0f) + iTranslateY, this.getColor(isActive));
        } else {
            CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.9f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.9f / 2.0f) + iTranslateY, this.getColor(isActive));
        }
        CFG.fontMain.getData().setScale(1.0f);
    }
}

