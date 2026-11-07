/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options;
import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options2;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateNewGame_Options_Pallets
extends SliderMenu {
    protected static final int ANIMATION_TIME = 250;
    protected static long lTime = 0L;

    protected Menu_CreateNewGame_Options_Pallets() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempMaxH = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) - CFG.PADDING;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_CNG_Options("", -1, 0, 0, tempW, tempElemH, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                CFG.palletManager.drawSampleColors_Standard(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, this.getWidth() - CFG.PADDING * 4, this.getHeight() - CFG.PADDING * 4, 0, isActive || CFG.palletManager.getActivePalletID() == 0);
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }
        });
        for (int i = 0; i < CFG.palletManager.getNumOfPallets(); ++i) {
            try {
                menuElements.add(new Button_CNG_Options("" + CFG.palletManager.getNumOfColorsInPallet(i), -1, 0, tempElemH * (i + 2), tempW, tempElemH, true){
                    int iCurrent;
                    {
                        this.iCurrent = 0;
                    }

                    @Override
                    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        CFG.palletManager.drawSampleColors(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, this.getWidth() - CFG.PADDING * 4, this.getHeight() - CFG.PADDING * 4, this.getCurrent(), isActive || CFG.palletManager.getActivePalletID() == this.getCurrent() + 1);
                        oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9f);
                        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 - CFG.CIV_COLOR_WIDTH - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getTextWidth() + CFG.PADDING * 4, this.getTextHeight() + CFG.CIV_COLOR_WIDTH * 2);
                        oSB.setColor(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 1.0f);
                        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2 + iTranslateX, 1 + this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 - CFG.CIV_COLOR_WIDTH - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getTextWidth() + CFG.PADDING * 4, 1);
                        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2 + iTranslateX, this.getTextHeight() + CFG.CIV_COLOR_WIDTH * 2 - 2 + this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 - CFG.CIV_COLOR_WIDTH - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getTextWidth() + CFG.PADDING * 4, 1);
                        oSB.setColor(Color.WHITE);
                        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                    }

                    @Override
                    protected void setCurrent(int nCurrent) {
                        this.iCurrent = nCurrent;
                    }

                    @Override
                    protected int getCurrent() {
                        return this.iCurrent;
                    }

                    @Override
                    protected void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PalletCivColors"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfColors") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                });
                ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
                continue;
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
        menuElements.add(new Button_CNG_Options2(null, -1, 0, tempElemH, tempW, tempElemH, true));
        menuElements.add(new Button_CNG_Options(null, -1, 0, tempElemH * menuElements.size(), tempW, tempElemH, true));
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_CreateNewGame_Options_Pallets.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_Pallets.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_CreateNewGame_Options_Pallets.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_CreateNewGame_Options_Pallets.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_Pallets.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_CreateNewGame_Options_Pallets.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_CreateNewGame_Options_Pallets.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_Pallets.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_CreateNewGame_Options_Pallets.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_CreateNewGame_Options_Pallets.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_Pallets.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_CreateNewGame_Options_Pallets.this.getWidth(), 1);
                if (AoCGame.LEFT != 0) {
                    oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
                    ImageManager.getImage(Images.pix255_255_255).draw2(oSB, Menu_CreateNewGame_Options_Pallets.this.getPosX() + iTranslateX, Menu_CreateNewGame_Options_Pallets.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - this.getHeight(), 1, this.getHeight(), true, false);
                    oSB.setColor(Color.WHITE);
                }
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), CFG.COLOR_TEXT_OPTIONS_LEFT_NS);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, tempMaxH < tempElemH * menuElements.size() ? tempMaxH : tempElemH * menuElements.size(), menuElements);
        this.setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("DefaultColors"));
        this.getMenuElement(this.getMenuElementsSize() - 2).setText(CFG.langManager.get("DownloadMore"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Back"));
        this.getTitle().setText(CFG.langManager.get("PalletsOfColors"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0f));
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
            super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.viewsManager.disableAllViews();
                CFG.palletManager.setActivePalletID(0);
                CFG.palletManager.loadCivilizationStandardColors();
                CFG.toast.setInView(CFG.langManager.get("Done"));
                return;
            }
        }
        if (iID == this.getMenuElementsSize() - 1) {
            CFG.menuManager.setVisible_CreateNewGame_Options(true);
            return;
        }
        if (iID == this.getMenuElementsSize() - 2) {
            CFG.game.disableDrawCivlizationsRegions_Players();
            CFG.backToMenu = Menu.eCREATE_NEW_GAME;
            CFG.menuManager.setViewID(Menu.eDOWNLOAD_PALLETS);
            return;
        }
        CFG.viewsManager.disableAllViews();
        CFG.palletManager.setActivePalletID(iID);
        CFG.palletManager.loadCivilizationsPaletteOfColors(iID);
        CFG.toast.setInView(CFG.langManager.get("Done"));
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = System.currentTimeMillis();
    }
}

