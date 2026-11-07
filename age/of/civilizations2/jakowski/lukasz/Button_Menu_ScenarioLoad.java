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

class Button_Menu_ScenarioLoad
extends Button_Menu {
    private int iLoadID;
    private String sScenarioName;
    private String sScenarioDate;

    protected Button_Menu_ScenarioLoad(int iLoadID, String sText, int iNumOfCivs, String sDate, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(CFG.langManager.getCiv(sText), (int)(50.0f * CFG.GUI_SCALE), iPosX, iPosY, iWidth, iHeight, isClickable);
        this.iLoadID = iLoadID;
        this.sScenarioName = CFG.langManager.get("Civilizations") + ": " + iNumOfCivs;
        this.sScenarioDate = sDate;
    }

    @Override
    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(Images.time).draw(oSB, this.getPosX() + this.getTextPos() / 2 - ImageManager.getImage(Images.time).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.time).getHeight() / 2 + iTranslateY);
        CFG.fontMain.getData().setScale(0.9f);
        CFG.drawText(oSB, this.getText(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.9f + (float)CFG.PADDING + (float)CFG.TEXT_HEIGHT * 0.7f) / 2 + iTranslateY, this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.sScenarioName, this.getPosX() + this.getTextPos() + CFG.PADDING + (int)((float)this.getTextWidth() * 0.9f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.9f + (float)CFG.PADDING + (float)CFG.TEXT_HEIGHT * 0.7f) / 2 + (int)((float)CFG.TEXT_HEIGHT * 0.9f - (float)CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, new Color(0.67f, 0.67f, 0.67f, 1.0f));
        CFG.drawText(oSB, this.sScenarioDate, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.9f + (float)CFG.PADDING + (float)CFG.TEXT_HEIGHT * 0.7f) / 2 + CFG.PADDING + (int)((float)CFG.TEXT_HEIGHT * 0.9f) + iTranslateY, new Color(0.58f, 0.58f, 0.58f, 1.0f));
        CFG.fontMain.getData().setScale(1.0f);
    }

    @Override
    protected int getCurrent() {
        return this.iLoadID;
    }
}

