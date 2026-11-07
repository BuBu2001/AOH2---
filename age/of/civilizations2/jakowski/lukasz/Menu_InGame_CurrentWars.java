/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.TechnologyManager;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_CurrentWar;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_PartOfHRE;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_ResearchProgress;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_SaveGame;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_TechLevel;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner_WarPreparations;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CurrentWars
extends SliderMenu {
    protected final float FONT_SCALE = 0.7f;
    protected static final int ANIMATION_TIME = 135;
    protected static long lTime = 0L;
    protected static boolean hideAnimation = true;

    protected Menu_InGame_CurrentWars(int init) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Wars"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    protected Menu_InGame_CurrentWars() {
        int i;
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
        ArrayList<Integer> tempWars = new ArrayList<Integer>();
        for (int i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
            int tWarID;
            if (i2 == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || !CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i2) || (tWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i2)) < 0) continue;
            boolean added = false;
            for (int j = 0; j < tempWars.size(); ++j) {
                if ((Integer)tempWars.get(j) != tWarID) continue;
                added = true;
                break;
            }
            if (added) continue;
            tempWars.add(tWarID);
        }
        ArrayList tempSorted = new ArrayList();
        while (tempWars.size() > 0) {
            int tBest = 0;
            for (i = 1; i < tempWars.size(); ++i) {
                if (CFG.game.getWar((Integer)tempWars.get(i)).getCasualties_Aggressors() + CFG.game.getWar((Integer)tempWars.get(i)).getCasualties_Defenders() <= CFG.game.getWar((Integer)tempWars.get(tBest)).getCasualties_Aggressors() + CFG.game.getWar((Integer)tempWars.get(tBest)).getCasualties_Defenders()) continue;
                tBest = i;
            }
            tempSorted.add(tempWars.get(tBest));
            tempWars.remove(tBest);
        }
        if (SaveManager.gameWillBeSavedInNextTurn()) {
            menuElements.add(new Text_Outliner_SaveGame(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, tPosY, tMenuWidth - 2){});
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        boolean research = true;
        for (i = 0; i < CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize(); ++i) {
            if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).messageType != Message_Type.TECHNOLOGY_RESEARCHED) continue;
            menuElements.add(new Text_Outliner_TechLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, 2, tPosY, tMenuWidth - 2){});
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            break;
        }
        if (research) {
            menuElements.add(new Text_Outliner_ResearchProgress(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + CFG.getPercentage_Max100((int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress(), TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), 4) + "%", 2, tPosY, tMenuWidth - 2){});
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        if (research || tempSorted.size() > 0 || CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.warPreparations.size() > 0 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            for (i = 0; i < tempSorted.size(); ++i) {
                menuElements.add(new Text_Outliner_CurrentWar((int)((Integer)tempSorted.get(i)), 2, tPosY, tMenuWidth - 2){});
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            for (i = 0; i < CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.iWarPreparationsSize; ++i) {
                menuElements.add(new Text_Outliner_WarPreparations(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.warPreparations.get((int)i).onCivID, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.warPreparations.get((int)i).iNumOfTurnsLeft, 2, tPosY, tMenuWidth - 2){});
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIsPartOfHolyRomanEmpire()) {
                menuElements.add(new Text_Outliner_PartOfHRE(CFG.holyRomanEmpire_Manager.getHRE().getEmperor(), 2, tPosY, tMenuWidth - 2){});
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        } else {
            menuElements.add(new Text_Outliner("NoWars", CFG.PADDING * 2, 2, tPosY, tMenuWidth - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){});
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setVisible(false);
        }
        this.initMenu(null, CFG.GAME_WIDTH - tMenuWidth, CFG.menuManager.getVisible_Menu_InGame_Outliner() ? CFG.menuManager.getMenu_InGame_Outliner().getPosY() + CFG.menuManager.getMenu_InGame_Outliner().getHeight() : ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2, tMenuWidth, Math.min(Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4) * (CFG.isDesktop() ? 6 : 5), menuElements.size() > 0 ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() : 0) + 1, menuElements, false, false);
        for (i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
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
        if (hideAnimation != Menu_InGame_CurrentWars.hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 135L ? System.currentTimeMillis() - (135L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        Menu_InGame_CurrentWars.hideAnimation = hideAnimation;
    }
}

