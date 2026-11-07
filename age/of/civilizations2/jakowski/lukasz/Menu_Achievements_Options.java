/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Statistics_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.Text_AchievementCiv;
import age.of.civilizations2.jakowski.lukasz.Text_Logo;
import age.of.civilizations2.jakowski.lukasz.Text_Scale;
import age.of.civilizations2.jakowski.lukasz.Text_ServiceRibbon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;

class Menu_Achievements_Options
extends SliderMenu {
    protected Menu_Achievements_Options() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        int tempMenuWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        menuElements.add(new Text_Logo("", 0, 0, tY, tempMenuWidth, ImageManager.getImage(Images.gameLogo).getHeight() + CFG.PADDING * 4));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        try {
            int i;
            FileHandle file = Gdx.files.local("saves/stats/civ/Age_of_Civilizations");
            String tempTags = file.readString();
            String[] tData = tempTags.split(";");
            ArrayList sortedIDs = new ArrayList();
            ArrayList<Integer> sortedStatsDataIDs = new ArrayList<Integer>();
            ArrayList<Integer> sortedStatsData = new ArrayList<Integer>();
            for (i = 0; i < tData.length; ++i) {
                try {
                    Statistics_Civ_GameData tempData = (Statistics_Civ_GameData)CFG.deserialize(Gdx.files.local("saves/stats/civ/" + tData[i]).readBytes());
                    sortedStatsData.add(tempData.getTurns());
                    sortedStatsDataIDs.add(i);
                    continue;
                }
                catch (GdxRuntimeException tempData) {
                    continue;
                }
                catch (ClassNotFoundException tempData) {
                    continue;
                }
                catch (IOException tempData) {
                    // empty catch block
                }
            }
            while (sortedStatsDataIDs.size() > 0) {
                int tBest = 0;
                for (int i2 = tBest + 1; i2 < sortedStatsDataIDs.size(); ++i2) {
                    if ((Integer)sortedStatsData.get(tBest) >= (Integer)sortedStatsData.get(i2)) continue;
                    tBest = i2;
                }
                sortedIDs.add(sortedStatsDataIDs.get(tBest));
                sortedStatsData.remove(tBest);
                sortedStatsDataIDs.remove(tBest);
            }
            for (i = 0; i < sortedIDs.size(); ++i) {
                try {
                    int tempLevel;
                    int j;
                    Statistics_Civ_GameData tempData = (Statistics_Civ_GameData)CFG.deserialize(Gdx.files.local("saves/stats/civ/" + tData[(Integer)sortedIDs.get(i)]).readBytes());
                    if (CFG.serviceRibbon_Manager.getRequestProvinces_Level(tempData.getConqueredProvinces()) - 1 < 0 && CFG.serviceRibbon_Manager.getRequestTurns_Level(tempData.getTurns()) - 1 < 0 && CFG.serviceRibbon_Manager.getRequestRecruitedArmy_Level(tempData.getRecruitedArmy()) - 1 < 0) continue;
                    menuElements.add(new Text_AchievementCiv(tempData.sTag, 0, tY, tempMenuWidth, tempData.sTag, tempData.getGamesWon() > 0));
                    tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    for (j = tempLevel = CFG.serviceRibbon_Manager.getRequestProvinces_Level(tempData.getConqueredProvinces()) - 1; j >= 0; --j) {
                        menuElements.add(new Text_ServiceRibbon(CFG.langManager.get("ConqueredProvinces") + ": ", 0, tY, tempMenuWidth, tempData.sTag, j, j == tempLevel ? tempData.getConqueredProvinces() : CFG.serviceRibbon_Manager.getRequestProvinces(j), 0));
                        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }
                    for (j = tempLevel = CFG.serviceRibbon_Manager.getRequestRecruitedArmy_Level(tempData.getRecruitedArmy()) - 1; j >= 0; --j) {
                        menuElements.add(new Text_ServiceRibbon(CFG.langManager.get("RecruitedArmy") + ": ", 0, tY, tempMenuWidth, tempData.sTag, j, j == tempLevel ? tempData.getRecruitedArmy() : CFG.serviceRibbon_Manager.getRequestRecruitedArmy(j), 1));
                        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }
                    for (j = tempLevel = CFG.serviceRibbon_Manager.getRequestTurns_Level(tempData.getTurns()) - 1; j >= 0; --j) {
                        menuElements.add(new Text_ServiceRibbon(CFG.langManager.get("Turns") + ": ", 0, tY, tempMenuWidth, tempData.sTag, j, j == tempLevel ? tempData.getTurns() : CFG.serviceRibbon_Manager.getRequestTurns(j), 2));
                        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }
                    continue;
                }
                catch (GdxRuntimeException gdxRuntimeException) {
                    continue;
                }
                catch (ClassNotFoundException classNotFoundException) {
                    continue;
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
        catch (GdxRuntimeException ex) {
            menuElements.add(new Text_Scale(CFG.langManager.get("-----"), -1, 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT, 0.75f));
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(false);
        }
        this.initMenu(null, CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2, tempMenuWidth, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING * 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("MapType"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - 2 + iTranslateY, this.getWidth() + 4, this.getHeight() + 4);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.075f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
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
    protected final void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }
}

