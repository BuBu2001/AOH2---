/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_NS_Wonder_2;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text_Scale;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGameWonders
extends SliderMenu {
    protected Menu_InGameWonders(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.75f);
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 6;
        this.initMenu(null, CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
    }

    protected Menu_InGameWonders() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.75f);
        int tY = 0;
        for (int i = 0; i < CFG.game.wondersManager.wonders_Provinces.size(); ++i) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.wondersManager.wonders_Provinces.get(i)).getWonderSize(); ++j) {
                if (!CFG.game.getProvince((int)CFG.game.wondersManager.wonders_Provinces.get((int)i).intValue()).getWonder((int)j).isAvailable) continue;
                menuElements.add(new Button_NS_Wonder_2(CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.wondersManager.wonders_Provinces.get(i)) ? new Color((float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.wondersManager.wonders_Provinces.get(i)).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.wondersManager.wonders_Provinces.get(i)).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.wondersManager.wonders_Provinces.get(i)).getCivID()).getB() / 255.0f, 1.0f) : new Color(0.09411765f, 0.3137255f, 0.43137255f, 0.5f), CFG.game.wondersManager.wonders_Provinces.get(i), j, 0, tY, tempWidth){

                    @Override
                    protected void actionElement(int iID) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getCurrent())) {
                            CFG.game.setActiveProvinceID(this.getCurrent());
                            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                                CFG.toast.setInView(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                            }
                        } else {
                            CFG.toast.setInView(CFG.langManager.get("Undiscovered"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        }
                    }

                    @Override
                    protected int getWidth() {
                        return Menu_InGameWonders.this.getElementW();
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        }
        if (menuElements.size() == 0) {
            menuElements.add(new Text_Scale(CFG.langManager.get("NoData"), -1, 0, CFG.PADDING, tempWidth - CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 3 / 4, 0.75f){

                @Override
                protected int getWidth() {
                    return Menu_InGameWonders.this.getElementW();
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(false);
        }
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Wonders"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.05882353f, 0.4117647f, 0.627451f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.05882353f, 0.4117647f, 0.627451f, 0.375f));
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
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
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

