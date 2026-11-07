/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Flag_Clip;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Title;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Rank
extends SliderMenu {
    protected static int iSort = 4;

    protected Menu_InGame_Rank(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 2.75f);
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        this.initMenu(null, CFG.GAME_WIDTH - CFG.PADDING * 4 - tempWidth, tempMenuPosY, tempWidth, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY - CFG.PADDING, menuElements, false, false);
    }

    protected Menu_InGame_Rank() {
        int i;
        int tAddID;
        int i2;
        ArrayList<Integer> tempScore;
        int i3;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 2.75f);
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 2 : CFG.TEXT_HEIGHT + CFG.PADDING * 2;
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Civilizations"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight){

            @Override
            protected int getWidth() {
                return Menu_InGame_Rank.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
            }

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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("PopulationScore"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_Rank.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_Rank.this.getElementW();
            }

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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("EconomicScore"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_Rank.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_Rank.this.getElementW();
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Prestige"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_Rank.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_Rank.this.getElementW();
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 3 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("TotalScore"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_Rank.this.getElementW() * 5 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_Rank.this.getW() - Menu_InGame_Rank.this.getElementW() * 5 + CFG.PADDING * 2 - 2;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 4 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
        });
        ArrayList<Integer> tSorted = new ArrayList<Integer>();
        if (iSort == 0) {
            for (i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
                tSorted.add(CFG.game.getSortedCivsAZ(i3 - 1));
            }
        } else if (iSort == 1) {
            ArrayList<Integer> tempIDS = new ArrayList<Integer>();
            tempScore = new ArrayList<Integer>();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                tempIDS.add(CFG.game.getSortedCivsAZ(i2 - 1));
                tempScore.add(CFG.gameAction.buildRank_Score_Population(CFG.game.getSortedCivsAZ(i2 - 1)));
            }
            tAddID = 0;
            while (tempIDS.size() > 0) {
                tAddID = 0;
                for (i = 1; i < tempIDS.size(); ++i) {
                    if ((Integer)tempScore.get(tAddID) >= (Integer)tempScore.get(i)) continue;
                    tAddID = i;
                }
                tSorted.add((Integer)tempIDS.get(tAddID));
                tempIDS.remove(tAddID);
                tempScore.remove(tAddID);
            }
        } else if (iSort == 2) {
            ArrayList<Integer> tempIDS = new ArrayList<Integer>();
            tempScore = new ArrayList();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                tempIDS.add(CFG.game.getSortedCivsAZ(i2 - 1));
                tempScore.add(CFG.gameAction.buildRank_Score_Economy(CFG.game.getSortedCivsAZ(i2 - 1)));
            }
            tAddID = 0;
            while (tempIDS.size() > 0) {
                tAddID = 0;
                for (i = 1; i < tempIDS.size(); ++i) {
                    if ((Integer)tempScore.get(tAddID) >= (Integer)tempScore.get(i)) continue;
                    tAddID = i;
                }
                tSorted.add((Integer)tempIDS.get(tAddID));
                tempIDS.remove(tAddID);
                tempScore.remove(tAddID);
            }
        } else if (iSort == 3) {
            ArrayList<Integer> tempIDS = new ArrayList<Integer>();
            tempScore = new ArrayList();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                tempIDS.add(CFG.game.getSortedCivsAZ(i2 - 1));
                tempScore.add(CFG.gameAction.buildRank_Score_Prestige(CFG.game.getSortedCivsAZ(i2 - 1)));
            }
            tAddID = 0;
            while (tempIDS.size() > 0) {
                tAddID = 0;
                for (i = 1; i < tempIDS.size(); ++i) {
                    if ((Integer)tempScore.get(tAddID) >= (Integer)tempScore.get(i)) continue;
                    tAddID = i;
                }
                tSorted.add((Integer)tempIDS.get(tAddID));
                tempIDS.remove(tAddID);
                tempScore.remove(tAddID);
            }
        } else {
            ArrayList<Integer> tempIDS = new ArrayList<Integer>();
            tempScore = new ArrayList();
            try {
                for (int i4 = 1; i4 < CFG.game.getCivsSize(); ++i4) {
                    tempIDS.add(CFG.game.getSortedCivsAZ(i4 - 1));
                    tempScore.add(CFG.game.getCiv(CFG.game.getSortedCivsAZ(i4 - 1)).getRankScore());
                }
            }
            catch (IndexOutOfBoundsException ex) {
                tempIDS.clear();
                tempScore.clear();
                CFG.game.sortCivilizationsAZ();
                for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                    tempIDS.add(CFG.game.getSortedCivsAZ(i - 1));
                    tempScore.add(CFG.game.getCiv(CFG.game.getSortedCivsAZ(i - 1)).getRankScore());
                }
            }
            tAddID = 0;
            while (tempIDS.size() > 0) {
                tAddID = 0;
                for (i = 1; i < tempIDS.size(); ++i) {
                    if ((Integer)tempScore.get(tAddID) >= (Integer)tempScore.get(i)) continue;
                    tAddID = i;
                }
                tSorted.add((Integer)tempIDS.get(tAddID));
                tempIDS.remove(tAddID);
                tempScore.remove(tAddID);
            }
        }
        int iSize = tSorted.size();
        for (i3 = 0; i3 < iSize; ++i3) {
            if (CFG.FOG_OF_WAR == 2) {
                if (CFG.getMetCiv((Integer)tSorted.get(i3))) {
                    menuElements.add(new Button_Statistics_Flag_Clip((int)((Integer)tSorted.get(i3)), "" + (i3 + 1) + ". " + CFG.game.getCiv((Integer)tSorted.get(i3)).getCivName(), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2){

                        @Override
                        protected int getWidth() {
                            return Menu_InGame_Rank.this.getElementW() * 2;
                        }

                        @Override
                        protected Color getColor(boolean isActive) {
                            return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                        }
                    });
                } else {
                    menuElements.add(new Button_Statistics_Flag_Clip(-1, "" + (i3 + 1) + ". " + CFG.langManager.get("Undiscovered"), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2){

                        @Override
                        protected int getWidth() {
                            return Menu_InGame_Rank.this.getElementW() * 2;
                        }

                        @Override
                        protected Color getColor(boolean isActive) {
                            return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                        }
                    });
                }
            } else {
                menuElements.add(new Button_Statistics_Flag_Clip((int)((Integer)tSorted.get(i3)), "" + (i3 + 1) + ". " + CFG.game.getCiv((Integer)tSorted.get(i3)).getCivName(), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2){

                    @Override
                    protected int getWidth() {
                        return Menu_InGame_Rank.this.getElementW() * 2;
                    }

                    @Override
                    protected Color getColor(boolean isActive) {
                        return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                    }
                });
            }
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + CFG.gameAction.buildRank_Score_Population((Integer)tSorted.get(i3))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_Rank.this.getElementW() * 2 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_Rank.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + CFG.gameAction.buildRank_Score_Economy((Integer)tSorted.get(i3))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_Rank.this.getElementW() * 3 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_Rank.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + CFG.gameAction.buildRank_Score_Prestige((Integer)tSorted.get(i3))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_Rank.this.getElementW() * 4 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_Rank.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + CFG.game.getCiv((Integer)tSorted.get(i3)).getRankScore()), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_Rank.this.getElementW() * 5 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_Rank.this.getW() - Menu_InGame_Rank.this.getElementW() * 5;
                }
            });
            tPosY += tElemHeight2;
        }
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.11764706f, 0.30588236f, 0.40784314f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.11764706f, 0.30588236f, 0.40784314f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        for (int i5 = 0; i5 < this.getMenuElementsSize(); ++i5) {
            this.getMenuElement(i5).setCurrent(i5 / 5 % 2);
        }
    }

    @Override
    protected void updateLanguage() {
        try {
            this.getTitle().setText(CFG.langManager.get("Ranking"));
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + 2, false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + 2, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() - 1 + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getMenuElement(0).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getMenuElement(0).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_Rank();
                }
                return;
            }
            case 1: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_Rank();
                }
                return;
            }
            case 2: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_Rank();
                }
                return;
            }
            case 3: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_Rank();
                }
                return;
            }
            case 4: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_Rank();
                }
                return;
            }
        }
        if (iID % 5 == 0) {
            if (this.getMenuElement(iID).getCurrent() > 0) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
            } else {
                CFG.toast.setInView(CFG.langManager.get("UndiscoveredCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            }
        }
    }

    protected final int getW() {
        return this.getWidth() - CFG.PADDING * 4;
    }

    protected final int getElementW() {
        return this.getW() / 6;
    }
}

