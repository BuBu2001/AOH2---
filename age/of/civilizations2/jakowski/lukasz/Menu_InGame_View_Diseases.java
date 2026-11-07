/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button_NS_Disease;
import age.of.civilizations2.jakowski.lukasz.Button_NS_Diseases;
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

class Menu_InGame_View_Diseases
extends SliderMenu {
    protected static final int ANIMATION_TIME = 175;
    protected static long lTime = 0L;
    protected static boolean hideAnimation = true;
    private int iCivID = 0;

    protected Menu_InGame_View_Diseases(int init) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Diseases"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    protected Menu_InGame_View_Diseases() {
        int i;
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tY = 0;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
        ArrayList tempSorted = new ArrayList();
        try {
            for (i = 0; i < CFG.plagueManager.lPlagues_INGAME.size(); ++i) {
                tempIDs.add(i);
            }
            while (tempIDs.size() > 0) {
                int tBestID = 0;
                for (int i2 = tBestID + 1; i2 < tempIDs.size(); ++i2) {
                    if (CFG.plagueManager.lPlagues_INGAME.get((Integer)tempIDs.get(tBestID)).getDeaths() >= CFG.plagueManager.lPlagues_INGAME.get((Integer)tempIDs.get(i2)).getDeaths()) continue;
                    tBestID = i2;
                }
                tempSorted.add(tempIDs.get(tBestID));
                tempIDs.remove(tBestID);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        menuElements.add(new Button_NS_Diseases(0, tY, tempW){});
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (tempSorted.size() > 0) {
            for (i = 0; i < tempSorted.size(); ++i) {
                menuElements.add(new Button_NS_Disease((Integer)tempSorted.get(i), CFG.plagueManager.getPlagueColor_InGame((Integer)tempSorted.get(i), 0.275f), CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getPlagueName(), CFG.FOG_OF_WAR < 2 || CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getOutbreakProvinceID() < 0 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getOutbreakProvinceID()) ? CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getOutbreakProvinceID() : -1, CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getDeaths(), CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getNumOfProvinces_Active() + "/" + CFG.plagueManager.lPlagues_INGAME.get((Integer)tempSorted.get(i)).getNumOfProvinces_Total(), 0, tY, CFG.CIV_INFO_MENU_WIDTH){

                    @Override
                    protected void actionElement(int iID) {
                        CFG.game.setActiveProvinceID(this.getCurrent());
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                            CFG.toast.setInView(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        }
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            }
        } else {
            menuElements.add(new Text_Scale(CFG.langManager.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_HEIGHT * 3 / 4, 0.75f));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Diseases"), CFG.BUTTON_HEIGHT * 3 / 5, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_InGame_View_Diseases.this.getPosX() + iTranslateX, Menu_InGame_View_Diseases.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_InGame_View_Diseases.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.627451f, 0.11764706f, 0.09803922f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.627451f, 0.11764706f, 0.09803922f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_InGame_View_Diseases.this.getPosX() + iTranslateX, Menu_InGame_View_Diseases.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_InGame_View_Diseases.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_InGame_View_Diseases.this.getPosX() + iTranslateX, Menu_InGame_View_Diseases.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_InGame_View_Diseases.this.getWidth(), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_InGame_View_Diseases.this.getPosX() + iTranslateX, Menu_InGame_View_Diseases.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1, Menu_InGame_View_Diseases.this.getWidth(), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, Menu_InGame_View_Diseases.this.getPosX() + iTranslateX, Menu_InGame_View_Diseases.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), Menu_InGame_View_Diseases.this.getWidth() / 4, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, Menu_InGame_View_Diseases.this.getPosX() + Menu_InGame_View_Diseases.this.getWidth() - Menu_InGame_View_Diseases.this.getWidth() / 4 + iTranslateX, Menu_InGame_View_Diseases.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), Menu_InGame_View_Diseases.this.getWidth() / 4, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5, tempW, Math.min(tY + 1, CFG.isAndroid() && !CFG.LANDSCAPE ? (CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 4 + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2)) / 2 : CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 4 + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2)), menuElements, false, true);
        this.updateLanguage();
        for (i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
        }
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight() + CFG.PADDING, true, true);
        oSB.setColor(new Color(0.09803922f, 0.05882353f, 0.37254903f, 0.25f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING * 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + CFG.PADDING, this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + this.getHeight() + CFG.PADDING, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + CFG.PADDING, this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void actionElement(int nID) {
        this.getMenuElement(nID).actionElement(nID);
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

    protected final void setHideAnimation(boolean nHideAnimation) {
        if (nHideAnimation != hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 175L ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        hideAnimation = nHideAnimation;
    }
}

