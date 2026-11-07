/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Flag_Clip;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Title;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_FlagAction_Bot_Right_Right
extends SliderMenu {
    protected static int iViewMode = 1;
    protected static final int iViewsSize = 3;
    protected static int iSort = 1;
    protected static long lTime = 0L;

    protected static final String getViewName() {
        switch (iViewMode) {
            case 0: {
                return CFG.langManager.get("Provinces");
            }
            case 1: {
                return CFG.langManager.get("Population");
            }
            case 2: {
                return CFG.langManager.get("TechnologyLevel");
            }
            case 3: {
                return CFG.langManager.get("RankScore");
            }
        }
        return CFG.langManager.get("Provinces");
    }

    protected Menu_InGame_FlagAction_Bot_Right_Right() {
        int j;
        int i;
        ArrayList<Integer> tSorted;
        int tempHeight = 0;
        int tempWidth = 0;
        if (CFG.isAndroid() && CFG.LANDSCAPE || CFG.isIOS() || AoCGame.LEFT != 0) {
            tempHeight = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4) - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT / 2;
            tempWidth = Menu_InGame_FlagAction.getWindowWidth() - Menu_InGame_FlagAction.getWindowWidth() * 2 / 5 - CFG.PADDING * 2;
        } else {
            tempHeight = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT / 2;
            tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
        }
        int tY = 0;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Statistics_Title("<", -1, 1, tY, (int)Math.ceil((float)(tempWidth - tempWidth * 7 / 10 - 3) * 0.2f), CFG.TEXT_HEIGHT + CFG.PADDING * 6){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Previous"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void actionElement(int iID) {
                if (--iViewMode < 0) {
                    iViewMode = 3;
                }
                CFG.menuManager.rebuildInGame_FlagActionRightBoth();
            }
        });
        menuElements.add(new Button_Statistics_Title(Menu_InGame_FlagAction_Bot_Right_Right.getViewName(), -1, 1 + (int)Math.ceil((float)(tempWidth - tempWidth * 7 / 10 - 3) * 0.2f), tY, (int)((double)(tempWidth - tempWidth * 7 / 10 - 3) - Math.ceil((float)(tempWidth - tempWidth * 7 / 10 - 3) * 0.2f) * 2.0), CFG.TEXT_HEIGHT + CFG.PADDING * 6){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                CFG.menuManager.rebuildInGame_FlagActionRightLeft();
            }
        });
        menuElements.add(new Button_Statistics_Title(">", -1, 1 + (int)((double)(tempWidth - tempWidth * 7 / 10 - 3) - Math.ceil((float)(tempWidth - tempWidth * 7 / 10 - 3) * 0.2f)), tY, (int)Math.ceil((float)(tempWidth - tempWidth * 7 / 10 - 3) * 0.2f), CFG.TEXT_HEIGHT + CFG.PADDING * 6){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Next"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void actionElement(int iID) {
                if (++iViewMode > 3) {
                    iViewMode = 0;
                }
                CFG.menuManager.rebuildInGame_FlagActionRightBoth();
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Name"), CFG.PADDING * 2, 1, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - tempWidth * 7 / 10) * 3 / 5, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                if (iSort != 0) {
                    iSort = 0;
                    CFG.menuManager.rebuildInGame_FlagActionRightRight();
                }
            }
        });
        menuElements.add(new Button_Statistics_Title(Menu_InGame_FlagAction_Bot_Right_Right.getViewName(), CFG.PADDING * 2, (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1, tY, tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                if (iSort != 1) {
                    iSort = 1;
                    CFG.menuManager.rebuildInGame_FlagActionRightRight();
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (iViewMode == 0) {
            tSorted = new ArrayList<Integer>();
            if (iSort == 0) {
                for (i = 0; i < CFG.game.getSortedCivsSize(); ++i) {
                    tSorted.add(CFG.game.getSortedCivsAZ(i));
                }
            } else {
                ArrayList<Integer> tempCivs = new ArrayList<Integer>();
                for (int i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                    if (CFG.game.getCiv(i2).getNumOfProvinces() <= 0) continue;
                    tempCivs.add(i2);
                }
                while (tempCivs.size() > 0) {
                    int tBest = 0;
                    for (j = 1; j < tempCivs.size(); ++j) {
                        if (CFG.game.getCiv((Integer)tempCivs.get(j)).getNumOfProvinces() <= CFG.game.getCiv((Integer)tempCivs.get(tBest)).getNumOfProvinces()) continue;
                        tBest = j;
                    }
                    tSorted.add((Integer)tempCivs.get(tBest));
                    tempCivs.remove(tBest);
                }
            }
            for (i = 0; i < tSorted.size(); ++i) {
                menuElements.add(new Button_Statistics_Flag_Clip(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? (Integer)tSorted.get(i) : -1, CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? "" + (i + 1) + ". " + CFG.game.getCiv((Integer)tSorted.get(i)).getCivName() : "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered"), CFG.PADDING, 1, tY, (tempWidth - tempWidth * 7 / 10) * 3 / 5, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

                    @Override
                    protected Color getColor(boolean isActive) {
                        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                    }

                    @Override
                    protected void actionElement(int iID) {
                        if (this.getCurrent() >= 0) {
                            CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                        }
                    }

                    @Override
                    protected void buildElementHover() {
                        if (this.getCurrent() >= 0) {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        } else {
                            MenuElement_Hover_v2.resetAnimation_2();
                            this.menuElementHover = null;
                        }
                    }
                });
                menuElements.add(new Button_Statistics("" + CFG.game.getCiv((Integer)tSorted.get(i)).getNumOfProvinces(), CFG.PADDING, (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1, tY, tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3, CFG.TEXT_HEIGHT + CFG.PADDING * 2){});
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        } else if (iViewMode == 1) {
            tSorted = new ArrayList();
            if (iSort == 0) {
                for (int i3 = 0; i3 < CFG.game.getSortedCivsSize(); ++i3) {
                    tSorted.add(CFG.game.getSortedCivsAZ(i3));
                }
            } else {
                ArrayList<Integer> tempCivs = new ArrayList<Integer>();
                ArrayList<Integer> tempCivsPop = new ArrayList<Integer>();
                for (int i4 = 1; i4 < CFG.game.getCivsSize(); ++i4) {
                    if (CFG.game.getCiv(i4).getNumOfProvinces() <= 0) continue;
                    tempCivs.add(i4);
                    tempCivsPop.add(CFG.game.getCiv(i4).countPopulation());
                }
                while (tempCivs.size() > 0) {
                    int tBest = 0;
                    for (int j2 = 1; j2 < tempCivs.size(); ++j2) {
                        if ((Integer)tempCivsPop.get(j2) <= (Integer)tempCivsPop.get(tBest)) continue;
                        tBest = j2;
                    }
                    tSorted.add((Integer)tempCivs.get(tBest));
                    tempCivs.remove(tBest);
                    tempCivsPop.remove(tBest);
                }
            }
            for (i = 0; i < tSorted.size(); ++i) {
                menuElements.add(new Button_Statistics_Flag_Clip(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? (Integer)tSorted.get(i) : -1, CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? "" + (i + 1) + ". " + CFG.game.getCiv((Integer)tSorted.get(i)).getCivName() : "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered"), CFG.PADDING, 1, tY, (tempWidth - tempWidth * 7 / 10) * 3 / 5, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

                    @Override
                    protected Color getColor(boolean isActive) {
                        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                    }

                    @Override
                    protected void actionElement(int iID) {
                        if (this.getCurrent() >= 0) {
                            CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                        }
                    }

                    @Override
                    protected void buildElementHover() {
                        if (this.getCurrent() >= 0) {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        } else {
                            MenuElement_Hover_v2.resetAnimation_2();
                            this.menuElementHover = null;
                        }
                    }
                });
                menuElements.add(new Button_Statistics("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv((Integer)tSorted.get(i)).countPopulation()), CFG.PADDING, (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1, tY, tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3, CFG.TEXT_HEIGHT + CFG.PADDING * 2){});
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        } else if (iViewMode == 2) {
            tSorted = new ArrayList();
            if (iSort == 0) {
                for (int i5 = 0; i5 < CFG.game.getSortedCivsSize(); ++i5) {
                    tSorted.add(CFG.game.getSortedCivsAZ(i5));
                }
            } else {
                ArrayList<Integer> tempCivs = new ArrayList<Integer>();
                for (int i6 = 1; i6 < CFG.game.getCivsSize(); ++i6) {
                    if (!(CFG.game.getCiv(i6).getTechnologyLevel() > 0.0f)) continue;
                    tempCivs.add(i6);
                }
                while (tempCivs.size() > 0) {
                    int tBest = 0;
                    for (j = 1; j < tempCivs.size(); ++j) {
                        if (!(CFG.game.getCiv((Integer)tempCivs.get(j)).getTechnologyLevel() > CFG.game.getCiv((Integer)tempCivs.get(tBest)).getTechnologyLevel())) continue;
                        tBest = j;
                    }
                    tSorted.add((Integer)tempCivs.get(tBest));
                    tempCivs.remove(tBest);
                }
            }
            for (i = 0; i < tSorted.size(); ++i) {
                menuElements.add(new Button_Statistics_Flag_Clip(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? (Integer)tSorted.get(i) : -1, CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? "" + (i + 1) + ". " + CFG.game.getCiv((Integer)tSorted.get(i)).getCivName() : "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered"), CFG.PADDING, 1, tY, (tempWidth - tempWidth * 7 / 10) * 3 / 5, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

                    @Override
                    protected Color getColor(boolean isActive) {
                        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                    }

                    @Override
                    protected void actionElement(int iID) {
                        if (this.getCurrent() >= 0) {
                            CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                        }
                    }

                    @Override
                    protected void buildElementHover() {
                        if (this.getCurrent() >= 0) {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        } else {
                            MenuElement_Hover_v2.resetAnimation_2();
                            this.menuElementHover = null;
                        }
                    }
                });
                menuElements.add(new Button_Statistics("" + (float)((int)(CFG.game.getCiv((Integer)tSorted.get(i)).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.PADDING, (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1, tY, tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3, CFG.TEXT_HEIGHT + CFG.PADDING * 2){});
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        } else if (iViewMode == 3) {
            tSorted = new ArrayList();
            if (iSort == 0) {
                for (int i7 = 0; i7 < CFG.game.getSortedCivsSize(); ++i7) {
                    tSorted.add(CFG.game.getSortedCivsAZ(i7));
                }
            } else {
                ArrayList<Integer> tempCivs = new ArrayList<Integer>();
                for (int i8 = 1; i8 < CFG.game.getCivsSize(); ++i8) {
                    if (CFG.game.getCiv(i8).getRankScore() <= 0) continue;
                    tempCivs.add(i8);
                }
                while (tempCivs.size() > 0) {
                    int tBest = 0;
                    for (j = 1; j < tempCivs.size(); ++j) {
                        if (CFG.game.getCiv((Integer)tempCivs.get(j)).getRankScore() <= CFG.game.getCiv((Integer)tempCivs.get(tBest)).getRankScore()) continue;
                        tBest = j;
                    }
                    tSorted.add((Integer)tempCivs.get(tBest));
                    tempCivs.remove(tBest);
                }
            }
            for (i = 0; i < tSorted.size(); ++i) {
                menuElements.add(new Button_Statistics_Flag_Clip(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? (Integer)tSorted.get(i) : -1, CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? "" + (i + 1) + ". " + CFG.game.getCiv((Integer)tSorted.get(i)).getCivName() : "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered"), CFG.PADDING, 1, tY, (tempWidth - tempWidth * 7 / 10) * 3 / 5, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

                    @Override
                    protected Color getColor(boolean isActive) {
                        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                    }

                    @Override
                    protected void actionElement(int iID) {
                        if (this.getCurrent() >= 0) {
                            CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                        }
                    }

                    @Override
                    protected void buildElementHover() {
                        if (this.getCurrent() >= 0) {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        } else {
                            MenuElement_Hover_v2.resetAnimation_2();
                            this.menuElementHover = null;
                        }
                    }
                });
                menuElements.add(new Button_Statistics("" + CFG.game.getCiv((Integer)tSorted.get(i)).getRankScore(), CFG.PADDING, (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1, tY, tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3, CFG.TEXT_HEIGHT + CFG.PADDING * 2){});
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        menuElements.add(new Button_Transparent(0, 0, tempWidth - tempWidth * 7 / 10, tempHeight - tempHeight / 2 - 2 < ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() : tempHeight - tempHeight / 2 - 2, true));
        this.initMenu(null, Menu_InGame_FlagAction.getWindowWidth() - Menu_InGame_FlagAction.getWindowWidth() * 3 / 5 + tempWidth * 7 / 10 + AoCGame.LEFT, tempHeight / 2 + ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT / 2, tempWidth - tempWidth * 7 / 10, tempHeight - tempHeight / 2 - 2, menuElements, false, false);
        for (int i9 = 3; i9 < menuElements.size(); ++i9) {
            this.getMenuElement(i9).setCurrent(((i9 - 3) / 2 + 1) % 2);
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2, true, true);
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.25f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2);
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 2, CFG.BUTTON_HEIGHT / 4);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, CFG.BUTTON_HEIGHT / 4, this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight());
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
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

