/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_RTO;
import age.of.civilizations2.jakowski.lukasz.Button_RTO2;
import age.of.civilizations2.jakowski.lukasz.Button_RTO_Player;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_ProvinceInfo;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_RTO2
extends SliderMenu {
    protected static final int ANIMATION_TIME = 250;
    protected static long lTime = 0L;
    private String sLoading;
    protected static final int TIME_REQUIRED_TO_CONTINUE = 30;
    protected static long TIME_CONTINUE;

    protected Menu_InGame_RTO2() {
        int tempRowH = CFG.TEXT_HEIGHT + CFG.PADDING * 2 > CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 ? CFG.TEXT_HEIGHT + CFG.PADDING * 2 : CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2;
        int tempW = CFG.CIV_INFO_MENU_WIDTH * 4 / 5;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.settingsManager.showOrderOfMovesView) {
            if (CFG.FOG_OF_WAR == 2) {
                for (int i = 0; i < CFG.game.getRTO().getRTOSize(); ++i) {
                    if (CFG.game.getCiv(CFG.game.getRTO().getRTO(i)).getControlledByPlayer()) {
                        menuElements.add(new Button_RTO_Player(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                        continue;
                    }
                    if (i % 2 == 0) {
                        menuElements.add(new Button_RTO(i + 1, CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getRTO().getRTO(i)) ? CFG.game.getRTO().getRTO(i) : -1, 0, tempRowH * i, tempW, tempRowH, true));
                        continue;
                    }
                    menuElements.add(new Button_RTO2(i + 1, CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getRTO().getRTO(i)) ? CFG.game.getRTO().getRTO(i) : -1, 0, tempRowH * i, tempW, tempRowH, true));
                }
            } else {
                for (int i = 0; i < CFG.game.getRTO().getRTOSize(); ++i) {
                    if (CFG.game.getCiv(CFG.game.getRTO().getRTO(i)).getControlledByPlayer()) {
                        menuElements.add(new Button_RTO_Player(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                        continue;
                    }
                    if (i % 2 == 0) {
                        menuElements.add(new Button_RTO(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                        continue;
                    }
                    menuElements.add(new Button_RTO2(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                }
            }
        } else {
            int i = 0;
            if (i < CFG.game.getRTO().getRTOSize()) {
                if (CFG.game.getCiv(CFG.game.getRTO().getRTO(i)).getControlledByPlayer()) {
                    menuElements.add(new Button_RTO_Player(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                } else if (i % 2 == 0) {
                    menuElements.add(new Button_RTO(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                } else {
                    menuElements.add(new Button_RTO2(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
                }
            }
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_InGame_RTO2.this.getPosX() + iTranslateX, Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_InGame_RTO2.this.getWidth(), this.getHeight());
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.8f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_InGame_RTO2.this.getPosX() + 2 + iTranslateX, Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_InGame_RTO2.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_InGame_RTO2.this.getPosX() + 2 + iTranslateX, Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_InGame_RTO2.this.getWidth() - 2);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_InGame_RTO2.this.getPosX() + 2 + iTranslateX, Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_InGame_RTO2.this.getWidth() - 2, 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), CFG.COLOR_TEXT_OPTIONS_LEFT_NS);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, CFG.GAME_HEIGHT * 4 / 5 - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 4) - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2) < (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 8 ? CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 4) - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2) : CFG.GAME_HEIGHT * 4 / 5 - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 4) - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2), menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("OrderOfMoves"));
        this.sLoading = CFG.langManager.get("Loading") + ": ";
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (CFG.settingsManager.showOrderOfMovesView) {
            if (lTime + 250L >= System.currentTimeMillis()) {
                iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0f));
                CFG.setRender_3(true);
            }
            ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            if (!CFG.oAI.doneLoadingOrders) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.line_32_off1).getHeight(), CFG.map.getMapBG().getMinimapWidth(), this.getLoadHeight(), false, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth() / 2, this.getLoadHeight(), false, false);
                oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() + this.getLoadHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() + this.getLoadHeight() - 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
                oSB.setColor(Color.WHITE);
                CFG.game.getCiv(CFG.oAI.iLoadingTurnActionsOfCivID).getFlag().draw(oSB, CFG.PADDING * 2, this.getLoadPosY() + this.getLoadHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.oAI.iLoadingTurnActionsOfCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, CFG.PADDING * 2, this.getLoadPosY() + this.getLoadHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawTextWithShadow(oSB, this.sLoading + (int)((float)CFG.oAI.iLoadingTurnActionsOfCivID / (float)(CFG.game.getCivsSize() - 1) * 100.0f) + "%", CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH, this.getLoadPosY() + (int)(((float)this.getLoadHeight() - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                CFG.fontMain.getData().setScale(1.0f);
            } else if (TIME_CONTINUE > 0L) {
                CFG.setRender_3(true);
                if (TIME_CONTINUE < System.currentTimeMillis() - 30L) {
                    Menu_InGame_ProvinceInfo.clickEndTurn();
                }
            }
        } else if (!CFG.oAI.doneLoadingOrders) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.line_32_off1).getHeight(), CFG.map.getMapBG().getMinimapWidth(), this.getLoadHeight(), false, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth() / 2, this.getLoadHeight(), false, false);
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() + this.getLoadHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, 0, this.getLoadPosY() + this.getLoadHeight() - 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
            oSB.setColor(Color.WHITE);
            CFG.game.getCiv(CFG.oAI.iLoadingTurnActionsOfCivID).getFlag().draw(oSB, CFG.PADDING * 2, this.getLoadPosY() + this.getLoadHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.oAI.iLoadingTurnActionsOfCivID).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, CFG.PADDING * 2, this.getLoadPosY() + this.getLoadHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
            CFG.fontMain.getData().setScale(0.7f);
            CFG.drawTextWithShadow(oSB, this.sLoading + (int)((float)CFG.oAI.iLoadingTurnActionsOfCivID / (float)(CFG.game.getCivsSize() - 1) * 100.0f) + "%", CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH, this.getLoadPosY() + (int)(((float)this.getLoadHeight() - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.fontMain.getData().setScale(1.0f);
            CFG.setRender_3(true);
        } else if (TIME_CONTINUE > 0L) {
            CFG.setRender_3(true);
            if (TIME_CONTINUE < System.currentTimeMillis() - 30L) {
                Menu_InGame_ProvinceInfo.clickEndTurn();
            }
        }
    }

    protected final int getLoadPosY() {
        return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getLoadHeight();
    }

    protected final int getLoadHeight() {
        return CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4;
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive || this.getScrollModeY()) {
            super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void actionElement(int nID) {
        if (CFG.FOG_OF_WAR != 2 || CFG.getMetCiv_AllPlayers(CFG.game.getRTO().getRTO(nID))) {
            if (CFG.FOG_OF_WAR != 2 || CFG.getMetProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(nID)).getCapitalProvinceID())) {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getRTO().getRTO(nID)).getCapitalProvinceID());
            }
            CFG.toast.setInView(CFG.game.getCiv(CFG.game.getRTO().getRTO(nID)).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
        } else {
            CFG.toast.setInView(CFG.langManager.get("UndiscoveredCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = System.currentTimeMillis();
        TIME_CONTINUE = -1L;
        if (!visible) {
            CFG.menuManager.setVisibleInGame_RTOBot(visible);
        }
    }
}

