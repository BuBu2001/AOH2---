/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Opinion2;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Sort;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivInfo;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CivInfo_Stats_Opinions
extends SliderMenu {
    protected static int iACTIVE_VIEW_ID = -999;

    protected Menu_InGame_CivInfo_Stats_Opinions() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int nPosY = 0;
        int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        menuElements.add(new Button_Diplomacy_Sort(Images.diplo_AZ, 0, CFG.PADDING, nPosY, (CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2) / 2, tempElemH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortByName"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Diplomacy_Sort(Images.diplo_heart, 0, CFG.PADDING + (CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2) / 2, nPosY, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - (CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2) / 2, tempElemH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortByOpinions"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        nPosY += tempElemH;
        try {
            int i;
            int tempAddID;
            int i2;
            if (iACTIVE_VIEW_ID == -999) {
                iACTIVE_VIEW_ID = 1;
            } else if (iACTIVE_VIEW_ID == -1) {
                for (int i3 = CFG.game.getCivsSize() - 1; i3 > 0; --i3) {
                    if (CFG.game.getCiv(CFG.game.getSortedCivsAZ(i3 - 1)).getNumOfProvinces() <= 0 || CFG.getActiveCivInfo() == CFG.game.getSortedCivsAZ(i3 - 1)) continue;
                    menuElements.add(new Button_Diplomacy_Opinion2(CFG.game.getSortedCivsAZ(i3 - 1), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getSortedCivsAZ(i3 - 1)), (int)CFG.game.getCivRelation_OfCivB(CFG.game.getSortedCivsAZ(i3 - 1), CFG.getActiveCivInfo()), 0, 0, nPosY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true));
                    nPosY += tempElemH;
                }
            } else if (iACTIVE_VIEW_ID == 1) {
                for (int i4 = 1; i4 < CFG.game.getCivsSize(); ++i4) {
                    if (CFG.game.getCiv(CFG.game.getSortedCivsAZ(i4 - 1)).getNumOfProvinces() <= 0 || CFG.getActiveCivInfo() == CFG.game.getSortedCivsAZ(i4 - 1)) continue;
                    menuElements.add(new Button_Diplomacy_Opinion2(CFG.game.getSortedCivsAZ(i4 - 1), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getSortedCivsAZ(i4 - 1)), (int)CFG.game.getCivRelation_OfCivB(CFG.game.getSortedCivsAZ(i4 - 1), CFG.getActiveCivInfo()), 0, 0, nPosY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true));
                    nPosY += tempElemH;
                }
            } else if (iACTIVE_VIEW_ID == 2) {
                ArrayList<Integer> tempC = new ArrayList<Integer>();
                for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                    if (CFG.game.getCiv(CFG.game.getSortedCivsAZ(i2 - 1)).getNumOfProvinces() <= 0 || CFG.getActiveCivInfo() == CFG.game.getSortedCivsAZ(i2 - 1)) continue;
                    tempC.add(CFG.game.getSortedCivsAZ(i2 - 1));
                }
                while (tempC.size() > 0) {
                    tempAddID = 0;
                    for (i = 1; i < tempC.size(); ++i) {
                        if (!(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempC.get(tempAddID)) < CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempC.get(i)))) continue;
                        tempAddID = i;
                    }
                    menuElements.add(new Button_Diplomacy_Opinion2((Integer)tempC.get(tempAddID), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempC.get(tempAddID)), (int)CFG.game.getCivRelation_OfCivB((Integer)tempC.get(tempAddID), CFG.getActiveCivInfo()), 0, 0, nPosY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true));
                    nPosY += tempElemH;
                    tempC.remove(tempAddID);
                }
            } else {
                ArrayList<Integer> tempC = new ArrayList<Integer>();
                for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                    if (CFG.game.getCiv(CFG.game.getSortedCivsAZ(i2 - 1)).getNumOfProvinces() <= 0 || CFG.getActiveCivInfo() == CFG.game.getSortedCivsAZ(i2 - 1)) continue;
                    tempC.add(CFG.game.getSortedCivsAZ(i2 - 1));
                }
                while (tempC.size() > 0) {
                    tempAddID = 0;
                    for (i = 1; i < tempC.size(); ++i) {
                        if (!(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempC.get(tempAddID)) > CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempC.get(i)))) continue;
                        tempAddID = i;
                    }
                    menuElements.add(new Button_Diplomacy_Opinion2((Integer)tempC.get(tempAddID), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempC.get(tempAddID)), (int)CFG.game.getCivRelation_OfCivB((Integer)tempC.get(tempAddID), CFG.getActiveCivInfo()), 0, 0, nPosY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true));
                    nPosY += tempElemH;
                    tempC.remove(tempAddID);
                }
            }
        }
        catch (IndexOutOfBoundsException tempC) {
            // empty catch block
        }
        this.initMenu(null, 0 + AoCGame.LEFT, ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f) + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, CFG.CIV_INFO_MENU_WIDTH, tempElemH * 8, menuElements, false, false);
        this.updateLanguage();
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
        }
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_CivInfo.lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = Menu_InGame_CivInfo.hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (Menu_InGame_CivInfo.hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2, true, false);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + 1, this.getWidth() - 2, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + this.getHeight(), this.getWidth() - 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + 2 + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() + 2, true, false);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                iACTIVE_VIEW_ID = iACTIVE_VIEW_ID == 1 ? -1 : 1;
                CFG.menuManager.rebuildInGame_Civ_Info_Opinions();
                return;
            }
            case 1: {
                iACTIVE_VIEW_ID = iACTIVE_VIEW_ID == 2 ? -2 : 2;
                CFG.menuManager.rebuildInGame_Civ_Info_Opinions();
                return;
            }
        }
        CFG.setActiveCivInfo(this.getMenuElement(iID).getCurrent());
        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
        CFG.updateActiveCivInfo_InGame();
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
        }
    }

    @Override
    protected void actionClose() {
        super.setVisible(false);
    }

    @Override
    protected void setPosY(int iPosY) {
        super.setPosY(iPosY);
        if (this.getPosY() + this.getHeight() > CFG.GAME_HEIGHT) {
            this.setHeight(Math.max(CFG.GAME_HEIGHT - this.getPosY(), CFG.BUTTON_HEIGHT / 2));
        }
        int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        this.setHeight(Math.min(this.getHeight(), tempElemH * 6));
        this.updateMenuElements_IsInView();
    }
}

