/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_DialogAgree;
import age.of.civilizations2.jakowski.lukasz.Button_DialogDisagree;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text_Scrollable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Dialog_Diplomacy
extends SliderMenu {
    private Color cTitleColor;
    private int iCivIDL = 0;
    private int iCivIDR = 0;

    protected Menu_Dialog_Diplomacy(Color nTitleColor, int nCivIDL, int nCivIDR) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tempHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 3 / 4;
        this.iCivIDL = nCivIDL;
        this.iCivIDR = nCivIDR;
        this.cTitleColor = nTitleColor;
        menuElements.add(new Button_DialogAgree(null, -1, 0, tempHeight - CFG.BUTTON_HEIGHT - 1, tempWidth / 2, CFG.BUTTON_HEIGHT, false));
        menuElements.add(new Button_DialogDisagree(null, -1, tempWidth / 2, tempHeight - CFG.BUTTON_HEIGHT - 1, tempWidth / 2, CFG.BUTTON_HEIGHT, false));
        menuElements.add(new Text_Scrollable("Spain offers us an Alliance", 0, 0, tempWidth, CFG.BUTTON_HEIGHT * 3 / 4, CFG.COLOR_TEXT_OPTIONS_NS, 0.8f, -1));
        this.initMenu(new SliderMenuTitle("Offer Alliance", CFG.BUTTON_HEIGHT * 3 / 4, true, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(Menu_Dialog_Diplomacy.this.cTitleColor);
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() + 2, nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(Color.WHITE);
                CFG.game.getCiv(Menu_Dialog_Diplomacy.this.iCivIDL).getFlag().draw(oSB, nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.game.getCiv(Menu_Dialog_Diplomacy.this.iCivIDR).getFlag().draw(oSB, nPosX + nWidth / 2 + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + nWidth / 2 + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.6f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 150, 150, tempWidth, tempHeight, menuElements, true, true);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Procced"));
        this.getMenuElement(1).setText(CFG.langManager.get("Decline"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.dialog_line).draw2(oSB, this.getPosX() + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.dialog_line).getHeight() + iTranslateY, this.getMenuElement(2).getWidth() - ImageManager.getImage(Images.dialog_line).getWidth(), this.getMenuElement(2).getHeight());
        ImageManager.getImage(Images.dialog_line).draw2(oSB, this.getPosX() + this.getMenuElement(2).getWidth() - ImageManager.getImage(Images.dialog_line).getWidth() + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.dialog_line).getHeight() + iTranslateY, ImageManager.getImage(Images.dialog_line).getWidth(), this.getMenuElement(2).getHeight(), true, false);
        oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.4f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + 2 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getMenuElement(2).getWidth() - 4, this.getMenuElement(2).getHeight());
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + 2 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getMenuElement(2).getWidth() - 4, this.getMenuElement(2).getHeight(), true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getMenuElement(2).getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getMenuElement(2).getWidth() - 4, this.getMenuElement(2).getHeight() * 3 / 4);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getHeight() - this.getMenuElement(2).getHeight() * 3 / 4 + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getMenuElement(2).getWidth() - 4, this.getMenuElement(2).getHeight() * 3 / 4, false, true);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getMenuElement(2).getHeight() - 2 + iTranslateY, this.getMenuElement(2).getWidth() - 4, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.35f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getMenuElement(2).getWidth() / 4 + this.getMenuElement(2).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getMenuElement(2).getHeight() - 2 + iTranslateY, this.getMenuElement(2).getWidth() - this.getMenuElement(2).getWidth() / 2, 1);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
    }
}

