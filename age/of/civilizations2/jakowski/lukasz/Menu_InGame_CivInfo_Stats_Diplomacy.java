/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_ImprovingRelations;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_InGame;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_InGameWar;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_LiberityDesire;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Opinion;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Wiki;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Wiki_Civ;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Alliance;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivInfo;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_WarDetails;
import age.of.civilizations2.jakowski.lukasz.Message_Relations_Increase_Ended;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CivInfo_Stats_Diplomacy
extends SliderMenu {
    protected Menu_InGame_CivInfo_Stats_Diplomacy() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = 0;
        ArrayList<Integer> tData = new ArrayList<Integer>();
        if (CFG.getActiveCivInfo() > 0) {
            int i;
            int i2;
            int i3;
            if (CFG.getActiveCivInfo() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                menuElements.add(new Button_Diplomacy_Opinion(CFG.getActiveCivInfo(), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), 0, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2 - (Button_Diplomacy.iDiploWidth + CFG.PADDING * 2), CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                    @Override
                    protected void actionElement(int iID) {
                        int nWarID;
                        if ((int)CFG.game.getCivRelation_OfCivB(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo()) == -100 && (nWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo())) >= 0 && nWarID < CFG.game.getWarsSize()) {
                            Menu_InGame_WarDetails.WAR_ID = nWarID;
                            CFG.menuManager.rebuildInGame_WarDetails();
                        }
                    }
                });
                menuElements.add(new Button_Diplomacy_Wiki(CFG.getActiveCivInfo(), CFG.CIV_INFO_MENU_WIDTH - 2 - (Button_Diplomacy.iDiploWidth + CFG.PADDING * 2), tPosY, Button_Diplomacy.iDiploWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){});
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            } else {
                menuElements.add(new Button_Diplomacy_Wiki_Civ(CFG.getActiveCivInfo(), (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), 0, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){});
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (CFG.getActiveCivInfo() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && !CFG.SPECTATOR_MODE) {
                for (i3 = 0; i3 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelationsSize(); ++i3) {
                    menuElements.add(new Button_Diplomacy_ImprovingRelations(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i3).iWithCivID, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i3).iNumOfTurns, 0, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                        @Override
                        protected void actionElement(int iID) {
                            if (!CFG.SPECTATOR_MODE) {
                                CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Increase_Ended(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)(iID - 1)).iWithCivID));
                                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().removeImproveRelations(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), iID - 1);
                                CFG.updateActiveCivInfo_InGame();
                                CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                CFG.toast.setInView(CFG.langManager.get("Removed"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                                CFG.menuManager.rebuildInGame_Messages();
                            }
                        }
                    });
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            if (!CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer() && CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID() != CFG.getActiveCivInfo()) {
                menuElements.add(new Button_Diplomacy_LiberityDesire(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID(), (int)CFG.game.getCiv(CFG.getActiveCivInfo()).getVassalLiberityDesire(), 0, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true){

                    @Override
                    protected void actionElement(int iID) {
                        if (CFG.game.getCiv(this.getCurrent()).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                            if (CFG.menuManager.getVisibleInGame_Tribute()) {
                                CFG.menuManager.setVisibleInGame_Tribute(false);
                            } else {
                                CFG.menuManager.rebuildInGame_Tribute();
                            }
                        } else {
                            CFG.toast.setInView(CFG.langManager.get("Lord") + ": " + CFG.game.getCiv(CFG.game.getCiv(this.getCurrent()).getPuppetOfCivID()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        }
                    }
                });
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
                if (i3 == CFG.getActiveCivInfo() || (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), i3) != -100 || CFG.game.getCiv(i3).getNumOfProvinces() <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i3)) {
                    tData.add(i3);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGameWar(Images.diplo_war, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2){

                    @Override
                    protected void actionElement(int iID) {
                        ArrayList<Integer> tWars = new ArrayList<Integer>();
                        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                            if (i == CFG.getActiveCivInfo() || (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), i) != -100 || CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                            int tWarID = CFG.game.getWarID(i, CFG.getActiveCivInfo());
                            boolean added = false;
                            for (int j = 0; j < tWars.size(); ++j) {
                                if ((Integer)tWars.get(j) != tWarID) continue;
                                added = true;
                                break;
                            }
                            if (added) continue;
                            tWars.add(tWarID);
                        }
                        if (tWars.size() > 0) {
                            if (CFG.menuManager.getVisibleInGame_WarDetails()) {
                                int nWarID = 0;
                                for (int i = 0; i < tWars.size(); ++i) {
                                    if (Menu_InGame_WarDetails.WAR_ID != (Integer)tWars.get(i)) continue;
                                    nWarID = i + 1;
                                    break;
                                }
                                if (nWarID >= tWars.size()) {
                                    CFG.menuManager.setVisibleInGame_WarDetails(false);
                                } else {
                                    Menu_InGame_WarDetails.WAR_ID = (Integer)tWars.get(nWarID);
                                    CFG.menuManager.rebuildInGame_WarDetails();
                                }
                            } else {
                                Menu_InGame_WarDetails.WAR_ID = (Integer)tWars.get(0);
                                CFG.menuManager.rebuildInGame_WarDetails();
                            }
                        } else {
                            CFG.menuManager.setVisibleInGame_WarDetails(false);
                        }
                    }
                });
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID() > 0) {
                tData.clear();
                for (i3 = 0; i3 < CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilizationsSize(); ++i3) {
                    if (CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(i3) == CFG.getActiveCivInfo()) continue;
                    if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(i3))) {
                        tData.add(CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(i3));
                        continue;
                    }
                    tData.add(-1);
                }
                if (tData.size() > 0) {
                    menuElements.add(new Button_Diplomacy_InGame(Images.diplo_alliance, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2){

                        @Override
                        protected void actionElement(int iID) {
                            try {
                                if (CFG.menuManager.getVisible_InGame_Alliance() && Menu_InGame_Alliance.ALLIANCE_ID == CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()) {
                                    CFG.menuManager.setVisible_InGame_Alliance(false);
                                } else {
                                    CFG.menuManager.rebuildInGame_Alliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID());
                                }
                            }
                            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                                // empty catch block
                            }
                        }
                    });
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            tData.clear();
            for (i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
                if (i3 == CFG.getActiveCivInfo() || CFG.game.getCiv(i3).getNumOfProvinces() <= 0 || CFG.game.getCiv(i3).getPuppetOfCivID() != CFG.getActiveCivInfo()) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i3)) {
                    tData.add(i3);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_vassal, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i3 = 1; i3 < CFG.game.getCivsSize(); ++i3) {
                if (i3 == CFG.getActiveCivInfo() || CFG.game.getCiv(i3).getNumOfProvinces() <= 0 || CFG.game.getCivTruce(i3, CFG.getActiveCivInfo()) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i3)) {
                    tData.add(i3);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_truce, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            ArrayList<Integer> tempOpinions = new ArrayList<Integer>();
            ArrayList tempSortedIDs = new ArrayList();
            for (i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
                if (CFG.game.getCiv(i2).getNumOfProvinces() <= 0 || CFG.getActiveCivInfo() == i2) continue;
                tempOpinions.add(i2);
            }
            while (tempOpinions.size() > 0) {
                int highestID = 0;
                for (i = 1; i < tempOpinions.size(); ++i) {
                    if (!(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempOpinions.get(highestID)) > CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempOpinions.get(i)))) continue;
                    highestID = i;
                }
                tempSortedIDs.add(tempOpinions.get(highestID));
                tempOpinions.remove(highestID);
            }
            tData.clear();
            i2 = tempSortedIDs.size() - 1;
            for (int j = 0; i2 >= 0 && j < 6 && !(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempSortedIDs.get(i2)) < 25.0f); --i2, ++j) {
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tempSortedIDs.get(i2))) {
                    tData.add((Integer)tempSortedIDs.get(i2));
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_heart, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i2 = 0; i2 < CFG.game.getCiv(CFG.getActiveCivInfo()).getHatedCivsSize(); ++i2) {
                tData.add(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getHatedCiv((int)i2).iCivID);
            }
            for (i2 = 0; i2 < 10 && i2 < tempSortedIDs.size() && !(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempSortedIDs.get(i2)) > -25.0f); ++i2) {
                boolean addCiv = true;
                for (int z = tData.size() - 1; z >= 0; --z) {
                    if (!((Integer)tData.get(z)).equals(tempSortedIDs.get(i2))) continue;
                    addCiv = false;
                    break;
                }
                if (!addCiv) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tempSortedIDs.get(i2))) {
                    tData.add((Integer)tempSortedIDs.get(i2));
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_rivals, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            try {
                tData.clear();
                if (CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces() > 0) {
                    for (int j = 0; j < CFG.game.getCiv(CFG.getActiveCivInfo()).getWarReparationsPaysSize(); ++j) {
                        if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getWarReparationsPays((int)j).iFromCivID)) {
                            tData.add(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getWarReparationsPays((int)j).iFromCivID);
                            continue;
                        }
                        tData.add(-1);
                    }
                }
                if (tData.size() > 0) {
                    menuElements.add(new Button_Diplomacy_InGame(Images.top_gold2, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            catch (IndexOutOfBoundsException j) {
                // empty catch block
            }
            tData.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getDefensivePact(i, CFG.getActiveCivInfo()) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                    tData.add(i);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_defensive_pact, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getCivNonAggressionPact(i, CFG.getActiveCivInfo()) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                    tData.add(i);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_non_aggression, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getGuarantee(i, CFG.getActiveCivInfo()) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                    tData.add(i);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_guarantee_has, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getGuarantee(CFG.getActiveCivInfo(), i) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                    tData.add(i);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_guarantee_gives, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getMilitaryAccess(i, CFG.getActiveCivInfo()) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                    tData.add(i);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_access_has, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getMilitaryAccess(CFG.getActiveCivInfo(), i) <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                    tData.add(i);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_access_gives, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosedSize(); ++i) {
                if (i == CFG.getActiveCivInfo() || CFG.game.getCiv(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed((int)i).iCivID).getNumOfProvinces() <= 0 || CFG.game.getCiv((int)CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed((int)i).iNumOfTurns <= 0) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed((int)i).iCivID)) {
                    tData.add(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed((int)i).iCivID);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_relations_dec, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (CFG.getActiveCivInfo() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.SPECTATOR_MODE) {
                tData.clear();
                for (i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getImproveRelationsSize(); ++i) {
                    if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID)) {
                        tData.add(CFG.game.getCiv((int)CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID);
                        continue;
                    }
                    tData.add(-1);
                }
                if (tData.size() > 0) {
                    menuElements.add(new Button_Diplomacy_InGame(Images.diplo_relations_inc, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            tData.clear();
            block23: for (int k = 1; k < CFG.game.getCivsSize(); ++k) {
                if (CFG.game.getCiv(k).getNumOfProvinces() <= 0) continue;
                for (i = 0; i < CFG.game.getCiv(k).getCivilization_Diplomacy_GameData().getImproveRelationsSize(); ++i) {
                    if (CFG.getActiveCivInfo() != CFG.game.getCiv((int)k).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID) continue;
                    if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(k)) {
                        tData.add(k);
                        continue block23;
                    }
                    tData.add(-1);
                    continue block23;
                }
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_relations, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            if (CFG.game.getCiv(CFG.getActiveCivInfo()).getIsPartOfHolyRomanEmpire()) {
                tData.clear();
                for (i = 0; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || i == CFG.getActiveCivInfo() || !CFG.game.getCiv(i).getIsPartOfHolyRomanEmpire()) continue;
                    if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
                        tData.add(i);
                        continue;
                    }
                    tData.add(-1);
                }
                if (tData.size() > 0) {
                    menuElements.add(new Button_Diplomacy_InGame(Images.hre_icon, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2){

                        @Override
                        protected void actionElement(int iID) {
                            if (CFG.menuManager.getVisibleInGame_HRE()) {
                                CFG.menuManager.setVisible_InGame_HRE(false);
                            } else {
                                CFG.menuManager.rebuildInGame_HRE();
                            }
                        }
                    });
                    tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            tData.clear();
            for (i = CFG.game.getCiv((int)CFG.getActiveCivInfo()).civGameData.lGifts_Received.size() - 1; i >= 0; --i) {
                if (i == CFG.getActiveCivInfo()) continue;
                if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getCiv((int)CFG.getActiveCivInfo()).civGameData.lGifts_Received.get((int)i).iFromCivID)) {
                    tData.add(CFG.game.getCiv((int)CFG.getActiveCivInfo()).civGameData.lGifts_Received.get((int)i).iFromCivID);
                    continue;
                }
                tData.add(-1);
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_gift, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
            tData.clear();
            for (i = CFG.game.getCiv((int)CFG.getActiveCivInfo()).civGameData.lLoansTaken.size() - 1; i >= 0; --i) {
                tData.add(CFG.getActiveCivInfo());
            }
            if (tData.size() > 0) {
                menuElements.add(new Button_Diplomacy_InGame(Images.diplo_loan, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
                tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        menuElements.add(new Button_Transparent(0, 0, CFG.CIV_INFO_MENU_WIDTH, (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * 3, true));
        this.initMenu(new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosX() + iTranslateX, Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(), Menu_InGame_CivInfo_Stats_Diplomacy.this.getWidth(), this.getHeight(), true, false);
                CFG.drawRect_InfoBox_Left_Title(oSB, Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosX() + iTranslateX, Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosY() - this.getHeight(), Menu_InGame_CivInfo_Stats_Diplomacy.this.getWidth() - 2, this.getHeight());
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawTextWithShadow(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) / 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) / 2, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0 + AoCGame.LEFT, ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f) + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, CFG.CIV_INFO_MENU_WIDTH, (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * (CFG.isDesktop() ? 4 : 3), menuElements, false, false);
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
        if (Menu_InGame_CivInfo.lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = Menu_InGame_CivInfo.hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (Menu_InGame_CivInfo.hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2, true, false);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() + 2, true, false);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
        }
    }
}

