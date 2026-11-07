/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.HistoryLog_Types;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_Alliance;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_AllianceLeaves;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_Annexation;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_Disease;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_FriendlyCivs;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_Guarantee;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_HaveMilitaryAccess;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_IsNotVassal;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_IsVassal;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_NewColony;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_SignedDefensivePact;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_SignedNonAggressionPact;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_Truce;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_Union;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_DiploInfo_War;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CurrentWars_Info
extends SliderMenu {
    protected final float FONT_SCALE = 0.7f;
    protected static final int ANIMATION_TIME = 135;
    protected static long lTime = 0L;
    protected static boolean hideAnimation = true;

    protected Menu_InGame_CurrentWars_Info(int init) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Wars"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * (CFG.isDesktop() ? 10 : 6)) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    protected Menu_InGame_CurrentWars_Info() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempMaxTextW = 1;
        try {
            CFG.glyphLayout.setText(CFG.fontMain, "+100% ");
            tempMaxTextW = (int)(CFG.glyphLayout.width * 0.7f);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        int tMenuWidth = ImageManager.getImage(Images.diplo_war).getWidth() / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING + tempMaxTextW + CFG.PADDING;
        int tPosY = 0;
        if (CFG.historyManager.getHistorySize() > 0) {
            int iSize;
            int i = iSize = CFG.historyManager.getHistorySize() - 1;
            int tTurn = 0;
            while (i > iSize - 3 && i >= 0) {
                int j;
                int jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.UNION || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_Union(CFG.historyManager.getHistory((int)i, (int)j).iCivA, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.WAR_DECLARAION || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_War(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.TRUCE || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_Truce(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.NEW_COONY || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_NewColony(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.ANNEXATION || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_Annexation(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.DISEASE || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_Disease(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, CFG.historyManager.getHistory(i, j).getName(), Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.JOINS_ALLIANCE || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_Alliance(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.LEAVES_ALLIANCE || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_AllianceLeaves(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory(i, j).getName(), Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.IS_VASSAL || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_IsVassal(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.IS_NOT_VASSAL || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_IsNotVassal(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.FRIENDLY_CIVS || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_FriendlyCivs(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.SIGNED_DEFENSIVE_PACT || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_SignedDefensivePact(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.SIGNED_NON_AGGRESSION_PACT || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_SignedNonAggressionPact(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.GUARANTEE || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_Guarantee(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                jSize = CFG.historyManager.getHistoryTurnSize(i);
                for (j = 0; j < jSize; ++j) {
                    if (CFG.historyManager.getHistory((int)i, (int)j).historyLog_Type != HistoryLog_Types.HAVE_MILITARY_ACCESS || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivA) && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory((int)i, (int)j).iCivB)) continue;
                    menuElements.add(new Text_Outliner_DiploInfo_HaveMilitaryAccess(CFG.historyManager.getHistory((int)i, (int)j).iCivA, CFG.historyManager.getHistory((int)i, (int)j).iCivB, Game_Calendar.TURN_ID - tTurn, 2, tPosY, tMenuWidth - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
                --i;
                ++tTurn;
            }
        }
        this.initMenu(null, CFG.GAME_WIDTH - tMenuWidth, -1 + (CFG.menuManager.getVisible_Menu_InGame_CurrentWars() ? CFG.menuManager.getMenu_InGame_CurrentWars().getPosY() + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight() : (CFG.menuManager.getVisible_Menu_InGame_Outliner() ? CFG.menuManager.getMenu_InGame_Outliner().getPosY() + CFG.menuManager.getMenu_InGame_Outliner().getHeight() : ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2)), tMenuWidth, Math.min((CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * (CFG.isDesktop() ? 5 : 3), menuElements.size() > 0 ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() : 0) + 1, menuElements, false, false);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent((CFG.menuManager.getMenu_InGame_CurrentWars().getMenuElementsSize() + i) % 2);
        }
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 135L >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX += (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0f))) : (iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0f)));
            CFG.setRender_3(true);
        } else if (hideAnimation) {
            super.setVisible(false);
            return;
        }
        super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX + CFG.PADDING, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.getCloseButtonImage(sliderMenuIsActive).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX, this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5, ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5);
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
            this.setHideAnimation(false);
        } else {
            this.setHideAnimation(true);
        }
    }

    protected final void setHideAnimation(boolean hideAnimation) {
        if (hideAnimation != Menu_InGame_CurrentWars_Info.hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 135L ? System.currentTimeMillis() - (135L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        Menu_InGame_CurrentWars_Info.hideAnimation = hideAnimation;
    }
}

