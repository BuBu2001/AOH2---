/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_Province2;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_ReleaseVassal;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_TakeAll;
import age.of.civilizations2.jakowski.lukasz.Button_PeaceTreaty_Demands_WarReparations;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Civilization_Info;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_PeaceTreaty_Response_Demands
extends SliderMenu {
    protected Menu_PeaceTreaty_Response_Demands() {
        int j;
        boolean addCiv;
        int i;
        int tempW = CFG.CIV_INFO_MENU_WIDTH * 4 / 5;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = 0;
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.size() <= 0 && CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lWarReparationsFromCivsID.size() <= 0 && CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.size() <= 0) continue;
            addCiv = CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lWarReparationsFromCivsID.size() > 0;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.size(); ++j) {
                if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j)).getTrueOwnerOfProvince() == CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID) continue;
                addCiv = true;
                break;
            }
            if (!addCiv && CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.size() <= 0) continue;
            menuElements.add(new Button_PeaceTreaty_Demands_TakeAll(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID, 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                @Override
                protected void actionElement(int iID) {
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_ReleaseVassal(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iFromCivID, this.countPoints(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs_TakeControl.get((int)j).iFromCivID), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true){

                    @Override
                    protected void actionElement(int iID) {
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lWarReparationsFromCivsID.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_WarReparations(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lWarReparationsFromCivsID.get(j), CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lWarReparationsFromCivsID.get(j), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                    @Override
                    protected void actionElement(int iID) {
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.size(); ++j) {
                if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j)).getTrueOwnerOfProvince() == CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID) continue;
                menuElements.add(new Button_PeaceTreaty_Demands_Province2(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lDemands.get(j), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                    @Override
                    protected void actionElement(int iID) {
                        CFG.game.setActiveProvinceID(this.getCurrent());
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.size() <= 0 && CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lWarReparationsFromCivsID.size() <= 0 && CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.size() <= 0) continue;
            addCiv = CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lWarReparationsFromCivsID.size() > 0;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.size(); ++j) {
                if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j)).getTrueOwnerOfProvince() == CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID) continue;
                addCiv = true;
                break;
            }
            if (!addCiv && CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.size() <= 0) continue;
            menuElements.add(new Button_PeaceTreaty_Demands_TakeAll(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID, 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                @Override
                protected void actionElement(int iID) {
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_ReleaseVassal(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iFromCivID, this.countPoints(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iVassalCivID, CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs_TakeControl.get((int)j).iFromCivID), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 5, true){

                    @Override
                    protected void actionElement(int iID) {
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lWarReparationsFromCivsID.size(); ++j) {
                menuElements.add(new Button_PeaceTreaty_Demands_WarReparations(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lWarReparationsFromCivsID.get(j), CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lWarReparationsFromCivsID.get(j), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                    @Override
                    protected void actionElement(int iID) {
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.size(); ++j) {
                if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j)).getTrueOwnerOfProvince() == CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID) continue;
                menuElements.add(new Button_PeaceTreaty_Demands_Province2(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lDemands.get(j), 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                    @Override
                    protected void actionElement(int iID) {
                        CFG.game.setActiveProvinceID(this.getCurrent());
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        for (i = 0; i < menuElements.size(); ++i) {
            ((MenuElement)menuElements.get(i)).setCurrent(i % 2);
        }
        int tempPosY = Math.max(Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING)) + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING);
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 2, this.getHeight());
                oSB.setColor(new Color(0.27450982f, 0.43137255f, 0.64705884f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.27450982f, 0.43137255f, 0.64705884f, 0.375f));
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
        }, CFG.GAME_WIDTH - tempW, tempPosY + CFG.BUTTON_HEIGHT * 3 / 5, tempW, Math.min(menuElements.size() > 0 ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING : CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2) - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 4 - CFG.BUTTON_HEIGHT - CFG.PADDING * 2), menuElements, true, true);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Demands"));
    }

    protected final int countPoints(int iCivID, int iReleaseCivID, int toReleaseByCivID) {
        int k;
        int j;
        int i;
        int out = 0;
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).iCivID != toReleaseByCivID) continue;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.size(); ++j) {
                if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).iCivID != iReleaseCivID) continue;
                for (k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).lProvinces.size(); ++k) {
                    out += CFG.game.getProvinceValue(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get((int)i).lReleasableCivs.get((int)j).lProvinces.get(k));
                }
            }
        }
        for (i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).iCivID != toReleaseByCivID) continue;
            for (j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.size(); ++j) {
                if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).iCivID != iReleaseCivID) continue;
                for (k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).lProvinces.size(); ++k) {
                    out += CFG.game.getProvinceValue(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get((int)i).lReleasableCivs.get((int)j).lProvinces.get(k));
                }
            }
        }
        return out;
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
        CFG.menuManager.hidePeaceTreaty_ResponseProvinces();
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible && !this.getVisible()) {
            Menu_Civilization_Info.lTime = System.currentTimeMillis();
        }
        super.setVisible(visible);
    }
}

