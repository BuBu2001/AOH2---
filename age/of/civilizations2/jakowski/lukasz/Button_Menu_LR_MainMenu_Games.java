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

class Button_Menu_LR_MainMenu_Games
extends Button_Menu {
    private long lTime = System.currentTimeMillis();
    private float fAlphaMod = 0.0f;
    private boolean backAnimation = false;

    protected Button_Menu_LR_MainMenu_Games(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    @Override
    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(1.0f, 1.0f, 1.0f, 0.55f);
        if (isActive) {
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
        if (!isActive) {
            if (this.lTime < System.currentTimeMillis() - 70L) {
                if (this.backAnimation) {
                    this.fAlphaMod -= 0.02f;
                    if (this.fAlphaMod < 0.0f) {
                        this.backAnimation = false;
                    }
                } else {
                    this.fAlphaMod += 0.02f;
                    if (this.fAlphaMod >= 0.35f) {
                        this.backAnimation = true;
                        this.fAlphaMod = 0.35f;
                    }
                }
                this.lTime = System.currentTimeMillis();
                CFG.setRender_3(true);
            }
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f - this.fAlphaMod));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 8);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - this.getHeight() / 8 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 8, false, true);
        }
        if (this.getClickable() && this.getIsHovered() && !isActive && animationState >= 0) {
            if (animationState == 0) {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0f, 1.0f);
                oSB.setColor(Button_Menu_LR_MainMenu_Games.getColorLine());
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < System.currentTimeMillis() - 750L) {
                    ++animationState;
                    lTimeAnimation = System.currentTimeMillis();
                }
            } else {
                float drawPerc = Math.min(1.0f * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0f, 1.0f);
                oSB.setColor(Button_Menu_LR_MainMenu_Games.getColorLine());
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
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, isActive ? 0.0f : (this.getIsHovered() ? 0.3f : 0.2f)));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - CFG.PADDING * 8) / 2 - this.getTextWidth() / 2, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - CFG.PADDING * 8) / 2 - this.getTextWidth() / 2, 1, false, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, isActive ? 0.0f : (this.getIsHovered() ? 0.3f : 0.175f)));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() - 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - CFG.PADDING * 8) / 2 - this.getTextWidth() / 2, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + CFG.PADDING * 2 + iTranslateX, this.getPosY() - 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - CFG.PADDING * 8) / 2 - this.getTextWidth() / 2, 1, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - CFG.PADDING * 8) / 2 - this.getTextWidth() / 2, 1, true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + CFG.PADDING * 2 + iTranslateX, this.getPosY() + 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - CFG.PADDING * 8) / 2 - this.getTextWidth() / 2, 1, false, false);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
    }
}

