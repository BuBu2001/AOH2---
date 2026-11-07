/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph;
import age.of.civilizations2.jakowski.lukasz.GraphData;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data;
import age.of.civilizations2.jakowski.lukasz.Graph_Vertical_Data_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import java.util.ArrayList;

class Menu_InGame_GraphManager {
    protected static int iActiveGraphID = 0;

    Menu_InGame_GraphManager() {
    }

    protected static final void setActiveGraphID(int nID) {
        if (iActiveGraphID != nID) {
            iActiveGraphID = nID;
        }
        if (iActiveGraphID == 0) {
            ArrayList<Graph_Vertical_Data> tempData = new ArrayList<Graph_Vertical_Data>();
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempData.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT, CFG.langManager.get("Civilizations"), CFG.langManager.get("Provinces"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempData));
        } else if (iActiveGraphID == 1) {
            ArrayList<Graph_Vertical_Data> tempL = new ArrayList<Graph_Vertical_Data>();
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempL.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATIONS, CFG.langManager.get("Civilizations"), CFG.langManager.get("Population"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempL));
        } else if (iActiveGraphID == 10) {
            ArrayList<Graph_Vertical_Data> tempL = new ArrayList<Graph_Vertical_Data>();
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempL.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.CONQUERED_PROVINCES, CFG.langManager.get("Civilizations"), CFG.langManager.get("ConqueredProvinces"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempL));
        } else if (iActiveGraphID == 11) {
            ArrayList<Graph_Vertical_Data> tempL = new ArrayList<Graph_Vertical_Data>();
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempL.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.CONSTRUCTED_BUILDINGS, CFG.langManager.get("Civilizations"), CFG.langManager.get("ConstructedBuildings"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempL));
        } else if (iActiveGraphID == 13) {
            ArrayList<Graph_Vertical_Data> tempL = new ArrayList<Graph_Vertical_Data>();
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempL.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.ECONOMY_OF_CIVILIZATIONS, CFG.langManager.get("Civilizations"), CFG.langManager.get("Economy"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempL));
        } else if (iActiveGraphID == 2) {
            ArrayList<Graph_Vertical_Data> tempL = new ArrayList<Graph_Vertical_Data>();
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempL.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATION_BY_NATIONALITIES, CFG.langManager.get("EthnicGroups"), CFG.langManager.get("EthnicGroups"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempL));
        } else if (iActiveGraphID == 3) {
            ArrayList<Graph_Vertical_Data> tempL = new ArrayList<Graph_Vertical_Data>();
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) continue;
                tempL.add(new Graph_Vertical_Data(i));
            }
            Menu_InGame_GraphManager.updateGraph(new Graph_Vertical(Graph_Vertical_Data_Type.TECHNOLOGY_LEVELS, CFG.langManager.get("Technology"), CFG.langManager.get("Technology"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempL));
        } else if (iActiveGraphID == 100) {
            ArrayList<Integer> tempCivs = new ArrayList<Integer>();
            tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            Menu_InGame_GraphManager.updateGraph(new Graph(CFG.langManager.get("Turn"), CFG.langManager.get("Income"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempCivs, 1){

                @Override
                protected void loadData(int i) {
                    block7: {
                        try {
                            if (iActiveGraphID == 100) {
                                int nStartTurnID = -1;
                                int jSize = CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.size();
                                for (int j = 0; j < jSize; ++j) {
                                    if (CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.get(j).size() <= CFG.PLAYER_TURNID) continue;
                                    nStartTurnID = j;
                                    break;
                                }
                                ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                                if (nStartTurnID >= 0) {
                                    int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.size();
                                    for (int j = nStartTurnID; j < jSize2; ++j) {
                                        tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.get(j).get(CFG.PLAYER_TURNID));
                                    }
                                }
                                if (tempPoints.size() > 0) {
                                    this.lData.set(i, new GraphData(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempPoints, nStartTurnID));
                                    ((GraphData)this.lData.get(i)).setDrawData(true);
                                    this.updateMoveable();
                                    this.buildGraph();
                                }
                            }
                        }
                        catch (IndexOutOfBoundsException ex) {
                            if (!CFG.LOGS) break block7;
                            CFG.exceptionStack(ex);
                        }
                    }
                }
            });
        } else if (iActiveGraphID == 111) {
            ArrayList<Integer> tempCivs = new ArrayList<Integer>();
            tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            Menu_InGame_GraphManager.updateGraph(new Graph(CFG.langManager.get("Turn"), CFG.langManager.get("Balance"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempCivs, 1){

                @Override
                protected void loadData(int i) {
                    block7: {
                        try {
                            if (iActiveGraphID == 111) {
                                int nStartTurnID = -1;
                                int jSize = CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.size();
                                for (int j = 0; j < jSize; ++j) {
                                    if (CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.get(j).size() <= CFG.PLAYER_TURNID) continue;
                                    nStartTurnID = j;
                                    break;
                                }
                                ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                                if (nStartTurnID >= 0) {
                                    int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.size();
                                    for (int j = nStartTurnID; j < jSize2; ++j) {
                                        tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.get(j).get(CFG.PLAYER_TURNID));
                                    }
                                }
                                if (tempPoints.size() > 0) {
                                    this.lData.set(i, new GraphData(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempPoints, nStartTurnID));
                                    ((GraphData)this.lData.get(i)).setDrawData(true);
                                    this.updateMoveable();
                                    this.buildGraph();
                                }
                            }
                        }
                        catch (IndexOutOfBoundsException ex) {
                            if (!CFG.LOGS) break block7;
                            CFG.exceptionStack(ex);
                        }
                    }
                }
            });
        } else if (iActiveGraphID == 102) {
            ArrayList<Integer> tempCivs = new ArrayList<Integer>();
            tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            Menu_InGame_GraphManager.updateGraph(new Graph(CFG.langManager.get("Turn"), CFG.langManager.get("MilitaryUpkeep"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempCivs, 1){

                @Override
                protected void loadData(int i) {
                    try {
                        if (iActiveGraphID == 102) {
                            int nStartTurnID = -1;
                            int jSize = CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.size();
                            for (int j = 0; j < jSize; ++j) {
                                if (CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.get(j).size() <= CFG.PLAYER_TURNID) continue;
                                nStartTurnID = j;
                                break;
                            }
                            ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                            if (nStartTurnID >= 0) {
                                int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.size();
                                for (int j = nStartTurnID; j < jSize2; ++j) {
                                    tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.get(j).get(CFG.PLAYER_TURNID));
                                }
                            }
                            if (tempPoints.size() > 0) {
                                this.lData.set(i, new GraphData(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempPoints, nStartTurnID));
                                ((GraphData)this.lData.get(i)).setDrawData(true);
                                this.updateMoveable();
                                this.buildGraph();
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
            });
        } else if (iActiveGraphID == 106) {
            ArrayList<Integer> tempCivs = new ArrayList<Integer>();
            tempCivs.add(0);
            tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            Menu_InGame_GraphManager.updateGraph(new Graph(CFG.langManager.get("Turn"), CFG.langManager.get("WorldsPopulation"), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(), CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(), true, tempCivs, 2){

                @Override
                protected void loadData(int i) {
                    try {
                        if (iActiveGraphID == 106) {
                            if (i == 0) {
                                ArrayList<Integer> tempPoints = new ArrayList<Integer>();
                                int jSize = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();
                                for (int j = 0; j < jSize; ++j) {
                                    int tempTurnPop = 0;
                                    for (int k = 0; k < CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).size(); ++k) {
                                        tempTurnPop += CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).get(k).intValue();
                                    }
                                    tempPoints.add(tempTurnPop);
                                }
                                if (tempPoints.size() > 0) {
                                    this.lData.set(i, new GraphData(0, tempPoints, 0));
                                    ((GraphData)this.lData.get(i)).setDrawData(true);
                                    this.updateMoveable();
                                    this.buildGraph();
                                }
                            } else {
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
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
            });
        }
    }

    private static final void updateGraph(MenuElement tElem) {
        CFG.menuManager.getInGame_FlagActionGraph().setMenuElement(0, tElem);
        CFG.menuManager.getInGame_FlagActionGraph().updateMenuElements_IsInView();
    }
}

