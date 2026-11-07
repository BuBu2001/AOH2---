/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_Descripted
extends Button_Menu {
    private String sDesc;

    protected Button_Menu_Descripted(String sDesc, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
        this.sDesc = sDesc;
    }

    protected Button_Menu_Descripted(String sDesc, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
        this.sDesc = sDesc;
    }

    @Override
    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        CFG.fontMain.getData().setScale(0.9f);
        CFG.drawText(oSB, this.getText(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.9f + (float)CFG.PADDING + (float)CFG.TEXT_HEIGHT * 0.7f) / 2 + iTranslateY, this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.sDesc, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.9f + (float)CFG.PADDING + (float)CFG.TEXT_HEIGHT * 0.7f) / 2 + CFG.PADDING + (int)((float)CFG.TEXT_HEIGHT * 0.9f) + iTranslateY, new Color(0.58f, 0.58f, 0.58f, 1.0f));
        CFG.fontMain.getData().setScale(1.0f);
    }
}

