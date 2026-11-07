/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph;
import age.of.civilizations2.jakowski.lukasz.GraphData;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot_Right_Right;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_FlagAction_Bot_Right_Left
extends SliderMenu {
    protected static long lTime = 0L;

    protected Menu_InGame_FlagAction_Bot_Right_Left() {
        int i;
        int tBest;
        int i2;
        int i3;
        int tempHeight = 0;
        int tempWidth = 0;
        if (CFG.isAndroid() && CFG.LANDSCAPE || CFG.isIOS() || AoCGame.LEFT != 0) {
            tempHeight = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4) - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT / 2;
            tempWidth = Menu_InGame_FlagAction.getWindowWidth() - Menu_InGame_FlagAction.getWindowWidth() * 2 / 5 - CFG.PADDING * 2;
        } else {
            tempHeight = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT / 2;
            tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
        }
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
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
        menuElements.add(new Graph(CFG.langManager.get("Turn"), Menu_InGame_FlagAction_Bot_Right_Right.getViewName(), CFG.PADDING * 2, CFG.PADDING * 2, tempWidth * 7 / 10 - CFG.PADDING * 4, tempHeight - tempHeight / 2 - CFG.PADDING * 3, true, tempCivs, Math.min(nLoad, 5)){

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
        });
        menuElements.add(new Button_Transparent(0, 0, tempWidth * 7 / 10, tempHeight - tempHeight / 2, true));
        this.initMenu(null, Menu_InGame_FlagAction.getWindowWidth() - Menu_InGame_FlagAction.getWindowWidth() * 3 / 5 + AoCGame.LEFT, tempHeight / 2 + ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT / 2, tempWidth * 7 / 10, tempHeight - tempHeight / 2, menuElements, false, false);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 225L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX(), CFG.GAME_HEIGHT - this.getPosY(), this.getWidth(), -((int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - lTime) / 225.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge_line_horizontal).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line_horizontal).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, true);
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.25f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 2, CFG.BUTTON_HEIGHT / 4);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.BUTTON_HEIGHT / 4, this.getHeight() - 2);
            oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() - 2);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, 1, this.getHeight() - 2);
            oSB.setColor(Color.WHITE);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            CFG.setRender_3(true);
            try {
                oSB.flush();
                ScissorStack.popScissors();
            }
            catch (IllegalStateException illegalStateException) {}
        } else {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge_line_horizontal).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line_horizontal).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, true);
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.25f));
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 2, CFG.BUTTON_HEIGHT / 4);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.BUTTON_HEIGHT / 4, this.getHeight() - 2);
            oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() - 2);
            oSB.setColor(Color.WHITE);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = System.currentTimeMillis();
    }

    @Override
    protected boolean getVisible() {
        return CFG.isAndroid() && !CFG.LANDSCAPE ? false : super.getVisible();
    }
}

