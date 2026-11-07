/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Demands;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_War;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_Messages;
import age.of.civilizations2.jakowski.lukasz.Message_UltimatumRefusedWar;
import age.of.civilizations2.jakowski.lukasz.Message_War;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Ultimatum_GameData;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_Message_Ultimatum
extends SliderMenu {
    protected static final int ANIMATION_TIME = 200;
    protected long lTime = 0L;
    private int iOnCivID = -1;
    private int iMessageID = 0;
    private Ultimatum_GameData oUltimatum;

    protected Menu_InGame_Message_Ultimatum() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2_sha).getHeight() + CFG.PADDING * 2 + (int)((float)CFG.BUTTON_HEIGHT * 0.6f) + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Ultimatum"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    protected Menu_InGame_Message_Ultimatum(final int onCivID, int iMessageID, int iValue, Ultimatum_GameData nUltimatum) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.iOnCivID = onCivID;
        this.iMessageID = iMessageID;
        this.oUltimatum = nUltimatum;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        menuElements.add(new Button_Diplomacy_War(onCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, tY, CFG.BUTTON_WIDTH * 2){

            @Override
            protected int getWidth() {
                return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (this.oUltimatum.demandAnexation) {
            menuElements.add(new Button_Diplomacy_Demands(CFG.langManager.get("DemandsAnnexationOfOurTerritory"), onCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

                @Override
                protected int getWidth() {
                    return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        if (this.oUltimatum.demandVasalization) {
            menuElements.add(new Button_Diplomacy_Demands(CFG.langManager.get("DemandVassalizationOfUs"), onCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

                @Override
                protected int getWidth() {
                    return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        if (this.oUltimatum.demandMilitaryAccess) {
            menuElements.add(new Button_Diplomacy_Demands(CFG.langManager.get("DemandMilitaryAccess"), onCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

                @Override
                protected int getWidth() {
                    return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        if (this.oUltimatum.demandLiberation.size() > 0) {
            for (int i = 0; i < this.oUltimatum.demandLiberation.size(); ++i) {
                menuElements.add(new Button_Diplomacy_Demands(CFG.langManager.get("DemandLiberationOfVassal") + ": " + CFG.game.getCiv(this.oUltimatum.demandLiberation.get(i)).getCivName(), onCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

                    @Override
                    protected int getWidth() {
                        return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        if (this.oUltimatum.demandProvinces.size() > 0) {
            menuElements.add(new Button_Diplomacy_Demands(CFG.langManager.get("DemandsOurProvinces"), onCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

                @Override
                protected int getWidth() {
                    return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
                }

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandsOurProvinces"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    for (int i = 0; i < ((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.size(); ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.get(i)).getName()));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }

                @Override
                protected void actionElement(int iID) {
                    CFG.game.getSelectedProvinces().clearSelectedProvinces();
                    for (int i = 0; i < ((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.size(); ++i) {
                        CFG.game.getSelectedProvinces().addProvince(((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.get(i));
                    }
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    CFG.game.setActiveProvinceID(-1);
                    CFG.menuManager.setViewID(Menu.eINGAME_SHOW_PROVINCES);
                    Game_Render_Province.updateDrawProvinces();
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        menuElements.add(new Button_Diplomacy_Demands(CFG.langManager.get("XUnitsAreReadyToAttackIfWeRefuseTheirOffer", iValue), onCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

            @Override
            protected int getWidth() {
                return Menu_InGame_Message_Ultimatum.this.getElementW() * 2;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Refuse"), -1, CFG.PADDING, tY += CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            protected int getWidth() {
                return (Menu_InGame_Message_Ultimatum.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RefuseProposal"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("War"), -1, 2, tY, CFG.BUTTON_WIDTH, true){

            @Override
            protected int getPosX() {
                return CFG.PADDING * 2 + (Menu_InGame_Message_Ultimatum.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            protected int getWidth() {
                return (Menu_InGame_Message_Ultimatum.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ImageManager.getImage(Images.diplo_war).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)(((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.diplo_war).getWidth() + (float)CFG.PADDING) / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_war).getHeight() / 2 + iTranslateY);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)(((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.diplo_war).getWidth() + (float)CFG.PADDING) / 2.0f) + ImageManager.getImage(Images.diplo_war).getWidth() + CFG.PADDING : this.getTextPos()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DeclareWarOn") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Message_Ultimatum.this.iOnCivID, 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(Menu_InGame_Message_Ultimatum.this.iOnCivID).getCivName()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_WAR2;
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Accept"), -1, 2, tY, CFG.BUTTON_WIDTH, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Message_Ultimatum.this.getW() - (Menu_InGame_Message_Ultimatum.this.getW() - CFG.PADDING * 4) / 3 - CFG.PADDING;
            }

            @Override
            protected int getWidth() {
                return (Menu_InGame_Message_Ultimatum.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            protected void buildElementHover() {
                int i;
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AcceptOffer"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Message_Ultimatum.this.iOnCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WeWillSignATruceUntilX", Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + 30)), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", 30) + "]", CFG.COLOR_TEXT_OPTIONS_NS_HOVER));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandAnexation) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandsAnnexationOfOurTerritory"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandVasalization) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandVassalizationOfUs"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandMilitaryAccess) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandMilitaryAccess"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandLiberation.size() > 0) {
                    for (i = 0; i < ((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandLiberation.size(); ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandLiberationOfVassal") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandLiberation.get(i)).getCivName()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandLiberation.get(i), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                }
                if (((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.size() > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandsOurProvinces"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                for (i = 0; i < ((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.size(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(((Menu_InGame_Message_Ultimatum)Menu_InGame_Message_Ultimatum.this).oUltimatum.demandProvinces.get(i)).getName()));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.getSend();
            }
        });
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Ultimatum"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.COLOR_MESSAGE_TITLE.r, CFG.COLOR_MESSAGE_TITLE.g, CFG.COLOR_MESSAGE_TITLE.b, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(CFG.COLOR_MESSAGE_TITLE.r, CFG.COLOR_MESSAGE_TITLE.g, CFG.COLOR_MESSAGE_TITLE.b, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(Images.diplo_message).draw(oSB, Menu_InGame_Message_Ultimatum.this.getPosX() + CFG.PADDING * 2 + iTranslateX, Menu_InGame_Message_Ultimatum.this.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.diplo_message).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        this.lTime = System.currentTimeMillis();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + 200L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - this.lTime) / 200.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            CFG.setRender_3(true);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            int tempID2 = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage((int)this.iMessageID).iFromCivID;
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(this.iMessageID).onAccept(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(this.iMessageID);
            CFG.gameAction.buildRank_Score(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.gameAction.buildRank_Score(tempID2);
            CFG.menuManager.rebuildInGame_Messages();
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("Accepted") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(4500);
            CFG.game.buildCivilizationRegions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.game.buildCivilizationRegions(tempID2);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && Menu_InGame_Messages.VIEW_BEFORE != CFG.viewsManager.getActiveViewID()) {
                CFG.viewsManager.setActiveViewID(Menu_InGame_Messages.VIEW_BEFORE);
            }
            this.setVisible(false);
            return;
        }
        if (iID == this.getMenuElementsSize() - 2) {
            CFG.game.declareWar(this.iOnCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
            CFG.game.getCiv((int)this.iOnCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.iOnCivID));
            CFG.game.getCiv((int)this.iOnCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_UltimatumRefusedWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(this.iMessageID);
            CFG.menuManager.rebuildInGame_Messages();
            CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && Menu_InGame_Messages.VIEW_BEFORE != CFG.viewsManager.getActiveViewID()) {
                CFG.viewsManager.setActiveViewID(Menu_InGame_Messages.VIEW_BEFORE);
            }
            CFG.toast.setInView(CFG.langManager.get("War") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            CFG.toast.setTimeInView(4500);
            this.setVisible(false);
            return;
        }
        if (iID == this.getMenuElementsSize() - 3) {
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(this.iMessageID).onDecline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(this.iMessageID);
            CFG.menuManager.rebuildInGame_Messages();
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE && Menu_InGame_Messages.VIEW_BEFORE != CFG.viewsManager.getActiveViewID()) {
                CFG.viewsManager.setActiveViewID(Menu_InGame_Messages.VIEW_BEFORE);
            }
            this.setVisible(false);
            return;
        }
        this.getMenuElement(iID).actionElement(iID);
        this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
    }

    protected final int getW() {
        return this.getWidth() - 4;
    }

    protected final int getElementW() {
        return this.getW() / 2;
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                this.getMenuElement(i).setVisible(false);
            }
        }
    }
}

