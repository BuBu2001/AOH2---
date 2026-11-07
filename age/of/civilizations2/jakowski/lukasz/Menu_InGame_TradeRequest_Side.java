/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_SelectProvinces;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_TradeRequest_SelectCiv;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_TradeRequest_Side
extends SliderMenu {
    private boolean left = false;
    private int iOnCivID = -1;

    protected Menu_InGame_TradeRequest_Side() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("TradeRequest"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    protected Menu_InGame_TradeRequest_Side(int onCivID, final boolean left) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.iOnCivID = onCivID;
        this.left = left;
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tY = 0;
        menuElements.add(new Button_Statistics(CFG.langManager.get("Gold"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.iGold > 0 : CFG.tradeRequest.listRight.iGold > 0;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (left) {
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() > 0L);
        }
        menuElements.add(new Button_Statistics(CFG.langManager.get("Provinces"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.lProvinces.size() > 0 : CFG.tradeRequest.listRight.lProvinces.size() > 0;
            }
        });
        menuElements.add(new Button_Statistics(CFG.langManager.get("DeclareWar"), CFG.PADDING * 2, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.iDeclarWarOnCivID > 0 : CFG.tradeRequest.listRight.iDeclarWarOnCivID > 0;
            }
        });
        menuElements.add(new Button_Statistics(CFG.langManager.get("FormACoalitionAgainst"), CFG.PADDING * 2, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return CFG.tradeRequest.listLEFT.iFormCoalitionAgainst > 0 || CFG.tradeRequest.listRight.iFormCoalitionAgainst > 0;
            }
        });
        menuElements.add(new Button_Statistics(CFG.langManager.get("DefensivePact"), CFG.PADDING * 2, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.defensivePact : CFG.tradeRequest.listRight.defensivePact;
            }
        });
        menuElements.add(new Button_Statistics(CFG.langManager.get("NonAggressionPact"), CFG.PADDING * 2, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.nonAggressionPact : CFG.tradeRequest.listRight.nonAggressionPact;
            }
        });
        menuElements.add(new Button_Statistics(CFG.langManager.get("ProclaimIndependence"), CFG.PADDING * 2, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.proclaimIndependence : CFG.tradeRequest.listRight.proclaimIndependence;
            }
        });
        menuElements.add(new Button_Statistics(CFG.langManager.get("MilitaryAccess"), CFG.PADDING * 2, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.BUTTON_WIDTH * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), false){

            @Override
            protected int getWidth() {
                return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            protected boolean getCheckboxState() {
                return left ? CFG.tradeRequest.listLEFT.militaryAccess : CFG.tradeRequest.listRight.militaryAccess;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.game.getCiv(onCivID).getCivName(), CFG.BUTTON_HEIGHT * 3 / 5, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color((float)CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getR() / 255.0f, (float)CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getG() / 255.0f, (float)CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getB() / 255.0f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color((float)CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getR() / 255.0f, (float)CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getG() / 255.0f, (float)CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getB() / 255.0f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + (nWidth - 2) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, false);
        this.updateLanguage();
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
        }
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
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
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_TradeRequest();
    }

    @Override
    protected final void actionElement(int iID) {
        int tempPosY = this.getMenuPosY();
        switch (iID) {
            case 0: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.iGold > 0) {
                        CFG.tradeRequest.listLEFT.iGold = 0;
                    } else {
                        CFG.tradeRequest.listLEFT.iGold = 100;
                        if ((long)CFG.tradeRequest.listLEFT.iGold > CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()) {
                            CFG.tradeRequest.listLEFT.iGold = (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney();
                        }
                    }
                } else {
                    CFG.tradeRequest.listRight.iGold = CFG.tradeRequest.listRight.iGold > 0 ? 0 : 100;
                }
                CFG.menuManager.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 1: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.lProvinces.size() == 0) {
                        CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivLEFT;
                        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                        CFG.viewsManager.disableAllViews();
                        CFG.game.setActiveProvinceID(-1);
                        Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT;
                        CFG.VIEW_SHOW_VALUES = false;
                        CFG.selectMode = true;
                        CFG.game.getSelectedProvinces().clearSelectedProvinces();
                        CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                        Game_Render_Province.updateDrawProvinces();
                    } else {
                        CFG.tradeRequest.listLEFT.lProvinces.clear();
                        CFG.menuManager.rebuildInGame_TradeRequest_Just();
                    }
                } else if (CFG.tradeRequest.listRight.lProvinces.size() == 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivRIGHT;
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    CFG.game.setActiveProvinceID(-1);
                    Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT;
                    CFG.VIEW_SHOW_VALUES = false;
                    CFG.selectMode = true;
                    CFG.game.getSelectedProvinces().clearSelectedProvinces();
                    CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                    Game_Render_Province.updateDrawProvinces();
                } else {
                    CFG.tradeRequest.listRight.lProvinces.clear();
                    CFG.menuManager.rebuildInGame_TradeRequest_Just();
                }
                this.setMenuPosY(tempPosY);
                return;
            }
            case 2: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.iDeclarWarOnCivID <= 0) {
                        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                        CFG.viewsManager.disableAllViews();
                        CFG.game.setActiveProvinceID(-1);
                        Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_DECLAREWAR;
                        CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
                        CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        Game_Render_Province.updateDrawProvinces();
                    } else {
                        CFG.tradeRequest.listLEFT.iDeclarWarOnCivID = -1;
                        CFG.menuManager.rebuildInGame_TradeRequest_Just();
                    }
                } else if (CFG.tradeRequest.listRight.iDeclarWarOnCivID <= 0) {
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    CFG.game.setActiveProvinceID(-1);
                    Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_DECLAREWAR;
                    CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
                    CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    Game_Render_Province.updateDrawProvinces();
                } else {
                    CFG.tradeRequest.listRight.iDeclarWarOnCivID = -1;
                    CFG.menuManager.rebuildInGame_TradeRequest_Just();
                }
                this.setMenuPosY(tempPosY);
                return;
            }
            case 3: {
                if (this.left) {
                    if (CFG.tradeRequest.listLEFT.iFormCoalitionAgainst <= 0) {
                        CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                        CFG.viewsManager.disableAllViews();
                        CFG.game.setActiveProvinceID(-1);
                        Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_COALITION;
                        CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
                        CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        Game_Render_Province.updateDrawProvinces();
                    } else {
                        CFG.tradeRequest.listLEFT.iFormCoalitionAgainst = -1;
                        CFG.menuManager.rebuildInGame_TradeRequest_Just();
                    }
                } else if (CFG.tradeRequest.listRight.iFormCoalitionAgainst <= 0) {
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    CFG.game.setActiveProvinceID(-1);
                    Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_COALITION;
                    CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
                    CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    Game_Render_Province.updateDrawProvinces();
                } else {
                    CFG.tradeRequest.listRight.iFormCoalitionAgainst = -1;
                    CFG.menuManager.rebuildInGame_TradeRequest_Just();
                }
                this.setMenuPosY(tempPosY);
                return;
            }
            case 4: {
                CFG.tradeRequest.listLEFT.defensivePact = !CFG.tradeRequest.listLEFT.defensivePact;
                CFG.tradeRequest.listRight.defensivePact = !CFG.tradeRequest.listRight.defensivePact;
                CFG.menuManager.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 5: {
                CFG.tradeRequest.listLEFT.nonAggressionPact = !CFG.tradeRequest.listLEFT.nonAggressionPact;
                CFG.tradeRequest.listRight.nonAggressionPact = !CFG.tradeRequest.listRight.nonAggressionPact;
                CFG.menuManager.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 6: {
                if (this.left) {
                    CFG.tradeRequest.listLEFT.proclaimIndependence = !CFG.tradeRequest.listLEFT.proclaimIndependence;
                } else {
                    CFG.tradeRequest.listRight.proclaimIndependence = !CFG.tradeRequest.listRight.proclaimIndependence;
                }
                CFG.menuManager.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
            case 7: {
                if (this.left) {
                    CFG.tradeRequest.listLEFT.militaryAccess = !CFG.tradeRequest.listLEFT.militaryAccess;
                } else {
                    CFG.tradeRequest.listRight.militaryAccess = !CFG.tradeRequest.listRight.militaryAccess;
                }
                CFG.menuManager.rebuildInGame_TradeRequest_Just();
                this.setMenuPosY(tempPosY);
                return;
            }
        }
    }

    protected final int getW() {
        return this.getWidth();
    }

    protected final int getElementW() {
        return this.getW() - 4;
    }
}

