/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph;
import age.of.civilizations2.jakowski.lukasz.GraphData;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot_Right_Right;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Graph
extends SliderMenu {
    protected Menu_InGame_Graph() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tMenuWidth = CFG.GAME_WIDTH / 2;
        int tMenuHeight = CFG.GAME_WIDTH / 4;
        try {
            int i;
            int tBest;
            int i2;
            int i3;
            ArrayList<Boolean> tAdded = new ArrayList<Boolean>();
            for (int i4 = 0; i4 < CFG.game.getCivsSize(); ++i4) {
                tAdded.add(CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i4));
            }
            ArrayList<Integer> tempCivs = new ArrayList<Integer>();
            int nLoad = 1;
            tAdded.set(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), true);
            tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            for (i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
                if (((Boolean)tAdded.get(i3)).booleanValue() || !CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i3)) continue;
                tAdded.set(i3, true);
                tempCivs.add(i3);
                ++nLoad;
            }
            for (i3 = 0; i3 < CFG.game.getPlayersSize(); ++i3) {
                if (i3 == CFG.PLAYER_TURNID || CFG.game.getCiv(CFG.game.getPlayer(i3).getCivID()).getNumOfProvinces() <= 0 || CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getPlayer(i3).getCivID())) continue;
                tAdded.set(CFG.game.getPlayer(i3).getCivID(), true);
                tempCivs.add(CFG.game.getPlayer(i3).getCivID());
                ++nLoad;
            }
            ArrayList<Integer> tempNeighboors = new ArrayList<Integer>();
            for (i2 = 0; i2 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); ++i2) {
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvincesSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)).getCivID() <= 0 || ((Boolean)tAdded.get(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)).getCivID())).booleanValue()) continue;
                    tempNeighboors.add(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)).getCivID());
                    tAdded.set(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)).getCivID(), true);
                    ++nLoad;
                }
            }
            while (tempNeighboors.size() > 0) {
                tBest = 0;
                for (i = 1; i < tempNeighboors.size(); ++i) {
                    if (CFG.game.getCiv((Integer)tempNeighboors.get(tBest)).getNumOfProvinces() >= CFG.game.getCiv((Integer)tempNeighboors.get(i)).getNumOfProvinces()) continue;
                    tBest = i;
                }
                tempCivs.add((Integer)tempNeighboors.get(tBest));
                tempNeighboors.remove(tBest);
            }
            tempNeighboors.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (((Boolean)tAdded.get(i2)).booleanValue()) continue;
                tempNeighboors.add(i2);
            }
            if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 0) {
                while (tempNeighboors.size() > 0) {
                    tBest = 0;
                    for (i = 1; i < tempNeighboors.size(); ++i) {
                        if (CFG.game.getCiv((Integer)tempNeighboors.get(tBest)).getNumOfProvinces() >= CFG.game.getCiv((Integer)tempNeighboors.get(i)).getNumOfProvinces()) continue;
                        tBest = i;
                    }
                    tempCivs.add((Integer)tempNeighboors.get(tBest));
                    tempNeighboors.remove(tBest);
                }
            } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1) {
                ArrayList<Integer> tempPop = new ArrayList<Integer>();
                for (i = 0; i < tempNeighboors.size(); ++i) {
                    tempPop.add(CFG.game.getCiv((Integer)tempNeighboors.get(i)).countPopulation());
                }
                while (tempNeighboors.size() > 0) {
                    int tBest2 = 0;
                    for (int i5 = 1; i5 < tempNeighboors.size(); ++i5) {
                        if ((Integer)tempPop.get(tBest2) >= (Integer)tempPop.get(i5)) continue;
                        tBest2 = i5;
                    }
                    tempCivs.add((Integer)tempNeighboors.get(tBest2));
                    tempNeighboors.remove(tBest2);
                    tempPop.remove(tBest2);
                }
            } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 2) {
                while (tempNeighboors.size() > 0) {
                    tBest = 0;
                    for (i = 1; i < tempNeighboors.size(); ++i) {
                        if (!(CFG.game.getCiv((Integer)tempNeighboors.get(tBest)).getTechnologyLevel() < CFG.game.getCiv((Integer)tempNeighboors.get(i)).getTechnologyLevel())) continue;
                        tBest = i;
                    }
                    tempCivs.add((Integer)tempNeighboors.get(tBest));
                    tempNeighboors.remove(tBest);
                }
            } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3) {
                while (tempNeighboors.size() > 0) {
                    tBest = 0;
                    for (i = 1; i < tempNeighboors.size(); ++i) {
                        if (CFG.game.getCiv((Integer)tempNeighboors.get(tBest)).getRankScore() >= CFG.game.getCiv((Integer)tempNeighboors.get(i)).getRankScore()) continue;
                        tBest = i;
                    }
                    tempCivs.add((Integer)tempNeighboors.get(tBest));
                    tempNeighboors.remove(tBest);
                }
            }
            menuElements.add(new Graph(CFG.langManager.get("Turn"), Menu_InGame_FlagAction_Bot_Right_Right.getViewName(), CFG.PADDING, CFG.PADDING * 2, 150, 225, true, tempCivs, Math.min(nLoad, 1)){

                @Override
                protected void loadData(int i) {
                    if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 0) {
                        super.loadData(i);
                    } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1) {
                        int nStartTurnID = -1;
                        int jSize = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();
                        for (int j = 0; j < jSize; ++j) {
                            if (CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).size() <= ((GraphData)this.lData.get(i)).getCivID()) continue;
                            nStartTurnID = j;
                            break;
                        }
                        ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                        if (nStartTurnID >= 0) {
                            int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();
                            for (int j = nStartTurnID; j < jSize2; ++j) {
                                tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).get(((GraphData)this.lData.get(i)).getCivID()));
                            }
                        }
                        if (tempPoints.size() > 0) {
                            this.lData.set(i, new GraphData(((GraphData)this.lData.get(i)).getCivID(), tempPoints, nStartTurnID));
                            ((GraphData)this.lData.get(i)).setDrawData(true);
                            this.updateMoveable();
                            this.buildGraph();
                        }
                    } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 2) {
                        int nStartTurnID = -1;
                        int jSize = CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.size();
                        for (int j = 0; j < jSize; ++j) {
                            if (CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.get(j).size() <= ((GraphData)this.lData.get(i)).getCivID()) continue;
                            nStartTurnID = j;
                            break;
                        }
                        ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                        if (nStartTurnID >= 0) {
                            int jSize3 = CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.size();
                            for (int j = nStartTurnID; j < jSize3; ++j) {
                                tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.get(j).get(((GraphData)this.lData.get(i)).getCivID()));
                            }
                        }
                        if (tempPoints.size() > 0) {
                            this.lData.set(i, new GraphData(((GraphData)this.lData.get(i)).getCivID(), tempPoints, nStartTurnID));
                            ((GraphData)this.lData.get(i)).setDrawData(true);
                            this.updateMoveable();
                            this.buildGraph();
                        }
                    } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3) {
                        int nStartTurnID = -1;
                        int jSize = CFG.timelapseManager.timelapseStatsGD.lRank.size();
                        for (int j = 0; j < jSize; ++j) {
                            if (CFG.timelapseManager.timelapseStatsGD.lRank.get(j).size() <= ((GraphData)this.lData.get(i)).getCivID()) continue;
                            nStartTurnID = j;
                            break;
                        }
                        ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                        if (nStartTurnID >= 0) {
                            int jSize4 = CFG.timelapseManager.timelapseStatsGD.lRank.size();
                            for (int j = nStartTurnID; j < jSize4; ++j) {
                                tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lRank.get(j).get(((GraphData)this.lData.get(i)).getCivID()));
                            }
                        }
                        if (tempPoints.size() > 0) {
                            this.lData.set(i, new GraphData(((GraphData)this.lData.get(i)).getCivID(), tempPoints, nStartTurnID));
                            ((GraphData)this.lData.get(i)).setDrawData(true);
                            this.updateMoveable();
                            this.buildGraph();
                        }
                    }
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_Graph.this.getW() - CFG.PADDING * 2;
                }

                @Override
                protected int getHeight() {
                    return Menu_InGame_Graph.this.getH() - CFG.PADDING * 4;
                }
            });
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        this.initMenu(new SliderMenuTitle(Menu_InGame_FlagAction_Bot_Right_Right.getViewName(), CFG.BUTTON_HEIGHT / 2, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.22745098f, 0.4509804f, 0.4509804f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.22745098f, 0.4509804f, 0.4509804f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tMenuWidth / 2, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 4, tMenuWidth, tMenuHeight, menuElements, false, true);
        this.updateLanguage();
        this.getMenuElement(0).setCheckboxState(true);
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY, this.getW() - ImageManager.getImage(Images.new_game_box).getWidth(), this.getH(), false, true);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + this.getW() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX, -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY, ImageManager.getImage(Images.new_game_box).getWidth(), this.getH(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    protected final int getW() {
        return this.getWidth();
    }

    protected final int getH() {
        return this.getHeight();
    }

    @Override
    protected boolean setWidth(int iWidth) {
        boolean out = super.setWidth(iWidth);
        this.getMenuElement(0).setCheckboxState(true);
        return out;
    }
}

