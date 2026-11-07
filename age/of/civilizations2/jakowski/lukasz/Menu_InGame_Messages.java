/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Message;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Message_Type;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Messages
extends SliderMenu {
    protected static int VIEW_BEFORE = -1;
    protected static long ANIMATION_TIME = 0L;
    protected static long ANIMATION_TIMER = 325L;
    protected static boolean START_ANIMATION = false;
    protected static boolean IN_ANIMATION = false;

    protected Menu_InGame_Messages() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = 0;
        int tX = 0;
        for (i = 0; i < CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize(); ++i) {
            if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).messageType != Message_Type.PEACE_TREATY_LIST_OF_DEMANDS || CFG.game.getPeaceTreaty_GameDataID(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).TAG) >= 0) continue;
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
        }
        for (i = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1; i >= 0; --i) {
            menuElements.add(new Button_Message(tX, tY, i, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)i).iFromCivID, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).getImageID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).getBGImageID()));
            tX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
        }
        this.initMenu(null, CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING, ImageManager.getImage(Images.top_left2_sha).getHeight() + CFG.PADDING, CFG.GAME_WIDTH - (CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING), (int)((float)CFG.BUTTON_HEIGHT * 0.6f) + 1, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (START_ANIMATION) {
            ANIMATION_TIME = System.currentTimeMillis() - ANIMATION_TIMER * 2L / 5L;
            IN_ANIMATION = true;
            START_ANIMATION = false;
        }
        if (IN_ANIMATION) {
            if (ANIMATION_TIME + ANIMATION_TIMER >= System.currentTimeMillis()) {
                iTranslateY += -this.getHeight() + (int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - ANIMATION_TIME) / (float)ANIMATION_TIMER));
                CFG.setRender_3(true);
            } else {
                IN_ANIMATION = false;
            }
        }
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS && !CFG.SPECTATOR_MODE) {
            Message_Type tempMessageType = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).messageType;
            if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && tempMessageType != Message_Type.HIGH_INFLATION) {
                try {
                    if (CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getNumOfProvinces() > 0) {
                        if (CFG.FOG_OF_WAR == 2) {
                            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getCapitalProvinceID()).getCivID() == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID) {
                                CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getCapitalProvinceID(), true);
                                CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetCivilization(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID, true);
                                CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getCapitalProvinceID());
                            } else {
                                CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getProvinceID(0), true);
                                CFG.game.getPlayer(CFG.PLAYER_TURNID).setMetCivilization(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID, true);
                                CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getProvinceID(0));
                            }
                        } else if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getCapitalProvinceID()).getCivID() == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID) {
                            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getCapitalProvinceID());
                        } else {
                            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID).getProvinceID(0));
                        }
                        VIEW_BEFORE = CFG.viewsManager.getActiveViewID();
                        if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DIPLOMACY_MODE) {
                            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
                        }
                    }
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && tempMessageType != Message_Type.HIGH_INFLATION) {
                try {
                    if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID)).iFromCivID) {
                        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.updateActiveCivInfo_InGame();
                        CFG.game.disableDrawCivilizationRegions_Active();
                        if (CFG.FOG_OF_WAR == 2) {
                            CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                        } else {
                            CFG.game.enableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                        }
                    }
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID).onAction(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID);
        }
    }
}

