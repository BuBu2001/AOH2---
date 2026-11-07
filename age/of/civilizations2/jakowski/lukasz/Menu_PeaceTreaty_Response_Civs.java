/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text_PeaceTreaty_Result;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_PeaceTreaty_Response_Civs
extends SliderMenu {
    protected final float FONT_SCALE = 0.8f;

    protected Menu_PeaceTreaty_Response_Civs() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH * 2 / 5;
        int tElementH = Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4);
        int tPosY = 0;
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            menuElements.add(new Text_PeaceTreaty_Result(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).peaceTreatyAccepted, 0, tPosY, tMenuWidth - 2));
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            menuElements.add(new Text_PeaceTreaty_Result(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).peaceTreatyAccepted, 0, tPosY, tMenuWidth - 2));
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        int tempPosY = Math.max(Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING)) + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING);
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, this.getHeight(), false, false);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, this.getHeight(), false, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, this.getHeight(), false, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 2 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0, tempPosY + CFG.BUTTON_HEIGHT / 2, tMenuWidth, Math.min(tElementH * 6, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight()), menuElements, true, false);
        for (int i2 = 0; i2 < this.getMenuElementsSize(); ++i2) {
            this.getMenuElement(i2).setCurrent(i2 % 2);
        }
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Civilizations"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX + CFG.PADDING, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.getCloseButtonImage(sliderMenuIsActive).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX, this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5, ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5);
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }
}

