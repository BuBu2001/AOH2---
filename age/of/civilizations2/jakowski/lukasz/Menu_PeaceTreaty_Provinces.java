/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_Localize;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_Province;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_ReleaseVassal;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_TakeAll;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_TakeAll_VicPoints;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_Vassalize;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_WarReparations;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Civilization_Info;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Date;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_PeaceTreaty_Provinces
extends SliderMenu {
    protected Menu_PeaceTreaty_Provinces() {
        int j;
        int i;
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = 0;
        menuElements.add(new Slider_FlagAction_Date(CFG.langManager.get("Truce"), CFG.PADDING, tY, tempW - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, 30, 75, CFG.peaceTreatyData.peaceTreatyGameData.TRUCE_LENGTH, 0.65f){

            @Override
            protected String getDrawText() {
                return CFG.langManager.get("TurnsX", this.getCurrent());
            }

            @Override
            protected int getSliderHeight() {
                return CFG.PADDING * 2;
            }

            @Override
            protected void actionElement(int iID) {
                CFG.peaceTreatyData.peaceTreatyGameData.TRUCE_LENGTH = this.getCurrent();
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(0.06666667f, 0.24705882f, 0.5058824f, 0.75f);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            menuElements.add(new Button_PeaceTreaty_Demands_TakeAll(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, 0, tY, tempW - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
            menuElements.add(new Button_PeaceTreaty_Demands_TakeAll_VicPoints(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).getVictoryPointsTotal(), tempW - CFG.BUTTON_WIDTH, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
            menuElements.add(new Button_PeaceTreaty_Demands_Vassalize(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iWillBecomeVassalOfCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true));
            menuElements.add(new Button_PeaceTreaty_Demands_WarReparations(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iPaysWarReparationsToCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).iCivID, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_ReleaseVassal(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).iReleasesToCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get(j).getScoreValue(), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true));
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (!CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).showProvinces) continue;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).lProvincesLost.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_Province(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).lProvincesLost.get(j), 0, tY, tempW - CFG.BUTTON_HEIGHT, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
                menuElements.add(new Button_PeaceTreaty_Demands_Localize(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get((int)i).lProvincesLost.get(j), tempW - CFG.BUTTON_HEIGHT, tY, CFG.BUTTON_HEIGHT, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
            menuElements.add(new Button_PeaceTreaty_Demands_TakeAll(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, 0, tY, tempW - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
            menuElements.add(new Button_PeaceTreaty_Demands_TakeAll_VicPoints(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).getVictoryPointsTotal(), tempW - CFG.BUTTON_WIDTH, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
            menuElements.add(new Button_PeaceTreaty_Demands_Vassalize(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iWillBecomeVassalOfCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true));
            menuElements.add(new Button_PeaceTreaty_Demands_WarReparations(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iPaysWarReparationsToCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).iCivID, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_ReleaseVassal(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).iReleasesToCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get(j).getScoreValue(), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true));
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (!CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).showProvinces) continue;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_Province(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get(j), 0, tY, tempW - CFG.BUTTON_HEIGHT, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
                menuElements.add(new Button_PeaceTreaty_Demands_Localize(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get((int)i).lProvincesLost.get(j), tempW - CFG.BUTTON_HEIGHT, tY, CFG.BUTTON_HEIGHT, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true));
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        for (i = 0; i < menuElements.size(); ++i) {
            ((MenuElement)menuElements.get(i)).setCurrent(i % 4 / 2);
        }
        int tempPosY = Math.max(Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING)) + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING);
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 2, this.getHeight());
                oSB.setColor(new Color(0.23529412f, 0.3137255f, 0.4117647f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.23529412f, 0.3137255f, 0.4117647f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, tempPosY + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min(menuElements.size() > 0 ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING : CFG.PADDING, CFG.GAME_HEIGHT - (tempPosY + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT), menuElements, true, true);
        this.updateLanguage();
        this.getMenuElement(0).setCurrent(CFG.peaceTreatyData.peaceTreatyGameData.TRUCE_LENGTH);
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("MakeDemands"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0f));
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 4, this.getHeight(), false, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth() + 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    protected void actionClose() {
        this.setVisible(false);
        CFG.menuManager.hidePeaceTreatyProvinces();
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible && !this.getVisible()) {
            Menu_Civilization_Info.lTime = System.currentTimeMillis();
        }
        super.setVisible(visible);
    }
}

