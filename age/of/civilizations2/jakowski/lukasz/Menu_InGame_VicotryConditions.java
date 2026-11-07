/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Graph_Circle_VictoryConditions;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_VicotryConditions
extends SliderMenu {
    protected Menu_InGame_VicotryConditions(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.75f);
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
        }
        this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, CFG.GAME_HEIGHT * 4 / 5, menuElements, false, false);
    }

    protected Menu_InGame_VicotryConditions() {
        float nScore;
        boolean tRow = false;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.5f);
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
        }
        int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 2 : CFG.TEXT_HEIGHT + CFG.PADDING * 2;
        int tY = 0;
        ArrayList<Integer> nData2 = new ArrayList<Integer>();
        ArrayList<Integer> nCivs2 = new ArrayList<Integer>();
        VicotryManager.domination_UpdateNumOfCivs();
        nData2.add(VicotryManager.domination_NumOfCivsInGame - VicotryManager.domination_CivScore(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        nCivs2.add(0);
        nData2.add(VicotryManager.domination_CivScore(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        nCivs2.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        tRow = !tRow;
        menuElements.add(new Graph_Circle_VictoryConditions(false, Images.diplo_war, tRow, CFG.langManager.get("Domination"), new Color(0.627451f, 0.09803922f, 0.078431375f, 1.0f), 2, tY, nData2, nCivs2, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getNumberWithSpaces("" + VicotryManager.domination_CivScore(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), " / " + CFG.getNumberWithSpaces("" + VicotryManager.domination_NumOfCivsInGame), "" + CFG.getPercentage(VicotryManager.domination_CivScore(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), VicotryManager.domination_NumOfCivsInGame, 4) + "%"){

            @Override
            protected int getWidth() {
                return Menu_InGame_VicotryConditions.this.getElementW() - 4;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Domination"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AnnihilateAllOfYourEnemies"), Color.WHITE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": ", Color.WHITE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + VicotryManager.domination_CivScore(this.iCivID)), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                int added = 0;
                for (int i = CFG.game.getCiv((int)this.iCivID).civGameData.lVassals.size() - 1; i >= 0 && added < 5; --i) {
                    if (CFG.game.getCiv(CFG.game.getCiv((int)this.iCivID).civGameData.lVassals.get((int)i).iCivID).getNumOfProvinces() <= 0) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv((int)this.iCivID).civGameData.lVassals.get((int)i).iCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getCiv((int)this.iCivID).civGameData.lVassals.get((int)i).iCivID).getCivName(), Color.WHITE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    ++added;
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        ArrayList<Integer> nData = new ArrayList<Integer>();
        ArrayList<Integer> nCivs = new ArrayList<Integer>();
        ArrayList tSorted = new ArrayList();
        ArrayList<Integer> tempCivs = new ArrayList<Integer>();
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            tempCivs.add(i);
        }
        while (tempCivs.size() > 0) {
            int tBest = 0;
            for (int j = 1; j < tempCivs.size(); ++j) {
                if (CFG.game.getCiv((Integer)tempCivs.get(j)).getNumOfProvinces() <= CFG.game.getCiv((Integer)tempCivs.get(tBest)).getNumOfProvinces()) continue;
                tBest = j;
            }
            tSorted.add(tempCivs.get(tBest));
            tempCivs.remove(tBest);
        }
        int nTotal = 0;
        for (int i = tSorted.size() - 1; i >= 0; --i) {
            nTotal += CFG.game.getCiv((Integer)tSorted.get(i)).getNumOfProvinces();
            if (((Integer)tSorted.get(i)).intValue() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
            tSorted.remove(i);
        }
        nData.add(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces());
        nCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        int tRestScore = 0;
        for (int i = 0; i < tSorted.size(); ++i) {
            if ((float)CFG.game.getCiv((Integer)tSorted.get(i)).getNumOfProvinces() / (float)nTotal > 0.015f) {
                nData.add(CFG.game.getCiv((Integer)tSorted.get(i)).getNumOfProvinces());
                nCivs.add(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tSorted.get(i)) ? (Integer)tSorted.get(i) : -((Integer)tSorted.get(i)).intValue());
                continue;
            }
            tRestScore += CFG.game.getCiv((Integer)tSorted.get(i)).getNumOfProvinces();
        }
        if (tRestScore > 0) {
            nData.add(tRestScore);
            nCivs.add(0);
        }
        tRow = !tRow;
        menuElements.add(new Graph_Circle_VictoryConditions(false, Images.provinces, tRow, CFG.langManager.get("ControlProvinces") + ": " + VicotryManager.VICTORY_CONTROL_PROVINCES_PERC + "%", new Color(0.09803922f, 0.23529412f, 0.43137255f, 1.0f), 2, tY, nData, nCivs, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces()), " / " + CFG.getNumberWithSpaces("" + (int)Math.ceil((float)nTotal * ((float)VicotryManager.VICTORY_CONTROL_PROVINCES_PERC / 100.0f))), CFG.getPercentage((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() / (float)nTotal, (float)VicotryManager.VICTORY_CONTROL_PROVINCES_PERC / 1.0f, 5) + "%"){

            @Override
            protected int getWidth() {
                return Menu_InGame_VicotryConditions.this.getElementW() - 4;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ControlProvinces"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": ", Color.WHITE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.iCivID).getNumOfProvinces()), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        ArrayList<Integer> nData4 = new ArrayList<Integer>();
        ArrayList<Integer> nCivs4 = new ArrayList<Integer>();
        int tempBestCivID = VicotryManager.technology_BestCiv();
        nData4.add((int)(CFG.game.getCiv(tempBestCivID).getTechnologyLevel() * 100.0f));
        nCivs4.add(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tempBestCivID) ? tempBestCivID : -tempBestCivID);
        if (VicotryManager.VICTORY_TECHNOLOGY > 0.0f && (nScore = Math.max(0.0f, (VicotryManager.VICTORY_TECHNOLOGY - CFG.game.getCiv(tempBestCivID).getTechnologyLevel()) * 100.0f)) > 0.0f) {
            nData4.add((int)Math.max(1.0f, nScore));
            nCivs4.add(0);
        }
        tRow = !tRow;
        menuElements.add(new Graph_Circle_VictoryConditions(VicotryManager.VICTORY_TECHNOLOGY == 0.0f, Images.technology, tRow, CFG.langManager.get("Technology") + ": " + (float)((int)(VicotryManager.VICTORY_TECHNOLOGY * 100.0f)) / 100.0f, new Color(0.11764706f, 0.17254902f, 0.33333334f, 1.0f), 2, tY, nData4, nCivs4, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, " / " + (float)((int)(VicotryManager.VICTORY_TECHNOLOGY * 100.0f)) / 100.0f, "" + CFG.getPercentage(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel(), VicotryManager.VICTORY_TECHNOLOGY / 100.0f, 4) + "%"){

            @Override
            protected int getWidth() {
                return Menu_InGame_VicotryConditions.this.getElementW() - 4;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology"), this.disabled ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (!this.disabled) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": ", Color.WHITE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_TECHNOLOGY_MODE, true);
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_TECHNOLOGY_MODE) {
                    CFG.toast.setInView(CFG.langManager.get("Technology"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (VicotryManager.VICTORY_LIMIT_OF_TURNS > 0) {
            ArrayList<Integer> nData3 = new ArrayList<Integer>();
            ArrayList<Integer> nCivs3 = new ArrayList<Integer>();
            nData3.add(Game_Calendar.TURN_ID);
            nCivs3.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            if (VicotryManager.VICTORY_LIMIT_OF_TURNS > 0) {
                nData3.add(VicotryManager.VICTORY_LIMIT_OF_TURNS - Game_Calendar.TURN_ID);
                nCivs3.add(0);
            }
            tRow = !tRow;
            menuElements.add(new Graph_Circle_VictoryConditions(VicotryManager.VICTORY_LIMIT_OF_TURNS == 0, Images.time, tRow, CFG.langManager.get("TurnsLimit") + ": " + VicotryManager.VICTORY_LIMIT_OF_TURNS, new Color(0.11764706f, 0.3137255f, 0.3137255f, 1.0f), 2, tY, nData3, nCivs3, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getNumberWithSpaces("" + Game_Calendar.TURN_ID), " / " + CFG.getNumberWithSpaces("" + VicotryManager.VICTORY_LIMIT_OF_TURNS), "" + CFG.getPercentage(Game_Calendar.TURN_ID, VicotryManager.VICTORY_LIMIT_OF_TURNS, 4) + "%"){

                @Override
                protected int getWidth() {
                    return Menu_InGame_VicotryConditions.this.getElementW() - 4;
                }

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsLimit"), this.disabled ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (!this.disabled) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Turn") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + Game_Calendar.TURN_ID, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" / " + VicotryManager.VICTORY_LIMIT_OF_TURNS, CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }

                @Override
                protected void actionElement(int iID) {
                    if (CFG.menuManager.getVisibleInGame_Rank()) {
                        CFG.menuManager.setVisibleInGame_Rank(false);
                    } else {
                        CFG.menuManager.rebuildInGame_Rank();
                    }
                    CFG.toast.setInView(CFG.langManager.get("Ranking"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.25882354f, 0.32941177f, 0.4627451f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.25882354f, 0.32941177f, 0.4627451f, 0.375f));
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
        int i = 0;
        int j = 0;
        while (i < this.getMenuElementsSize()) {
            this.getMenuElement(i).setCurrent(j % 2);
            i += 2;
            ++j;
        }
    }

    @Override
    protected void updateLanguage() {
        try {
            this.getTitle().setText(CFG.langManager.get("VictoryConditions"));
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
        this.getMenuElement(iID).actionElement(iID);
    }

    protected final int getW() {
        return this.getWidth();
    }

    protected final int getElementW() {
        return this.getW();
    }
}

