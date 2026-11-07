/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_InGame;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Wiki_Civ;
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

class Menu_Civilization_Info_Diplomacy
extends SliderMenu {
    protected Menu_Civilization_Info_Diplomacy() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = 0;
        ArrayList<Integer> tData = new ArrayList<Integer>();
        if (CFG.getActiveCivInfo() > 0) {
            int i;
            int i2;
            menuElements.add(new Button_Diplomacy_Wiki_Civ(CFG.getActiveCivInfo(), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), 0, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){});
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || (int)CFG.game.getCivRelation_OfCivB(i2, CFG.getActiveCivInfo()) != -100 || CFG.game.getCiv(i2).getNumOfProvinces() <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_war, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID() > 0) {
                tData.clear();
                for (i2 = 0; i2 < CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilizationsSize(); ++i2) {
                    if (CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(i2) == CFG.getActiveCivInfo()) continue;
                    tData.add(CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(i2));
                }
                if (tData.size() > 0) {
                    menuElements.add(new Button_Diplomacy(Images.diplo_alliance, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getCiv(i2).getPuppetOfCivID() != CFG.getActiveCivInfo()) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_vassal, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getCivTruce(i2, CFG.getActiveCivInfo()) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_truce, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getDefensivePact(i2, CFG.getActiveCivInfo()) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_defensive_pact, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getCivNonAggressionPact(i2, CFG.getActiveCivInfo()) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_non_aggression, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getGuarantee(i2, CFG.getActiveCivInfo()) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_guarantee_has, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getGuarantee(CFG.getActiveCivInfo(), i2) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_guarantee_gives, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getMilitaryAccess(i2, CFG.getActiveCivInfo()) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_access_has, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (i2 == CFG.getActiveCivInfo() || CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.game.getMilitaryAccess(CFG.getActiveCivInfo(), i2) <= 0) continue;
                tData.add(i2);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_guarantee_gives, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            ArrayList<Integer> tempOpinions = new ArrayList<Integer>();
            ArrayList tempSortedIDs = new ArrayList();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.getActiveCivInfo() == i) continue;
                tempOpinions.add(i);
            }
            while (tempOpinions.size() > 0) {
                int highestID = 0;
                for (int i3 = 1; i3 < tempOpinions.size(); ++i3) {
                    if (!(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempOpinions.get(highestID)) > CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempOpinions.get(i3)))) continue;
                    highestID = i3;
                }
                tempSortedIDs.add(tempOpinions.get(highestID));
                tempOpinions.remove(highestID);
            }
            tData.clear();
            i = tempSortedIDs.size() - 1;
            for (int j = 0; i >= 0 && j < 4 && !(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempSortedIDs.get(i)) < 25.0f); --i, ++j) {
                tData.add((Integer)tempSortedIDs.get(i));
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy(Images.diplo_heart, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getHatedCivsSize(); ++i) {
                tData.add(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getHatedCiv((int)i).iCivID);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_rivals, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        this.initMenu(new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, Menu_Civilization_Info_Diplomacy.this.getPosX() + iTranslateX, Menu_Civilization_Info_Diplomacy.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(), Menu_Civilization_Info_Diplomacy.this.getWidth(), this.getHeight());
                CFG.drawRect_InfoBox_Right_Title(oSB, Menu_Civilization_Info_Diplomacy.this.getPosX() + 2 + iTranslateX, Menu_Civilization_Info_Diplomacy.this.getPosY() - this.getHeight(), Menu_Civilization_Info_Diplomacy.this.getWidth(), this.getHeight());
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawTextWithShadow(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) / 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) / 2, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH, ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f) + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, CFG.CIV_INFO_MENU_WIDTH, (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * 3, menuElements, false, false);
        this.updateLanguage();
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setMax(i % 2);
        }
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Diplomacy"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0f));
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_CreateNewGame_CivInfo();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.getMenuElement(iID).actionElement(iID);
            }
        }
    }
}

