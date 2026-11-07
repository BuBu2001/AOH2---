/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CapitalMoved
extends SliderMenu {
    protected static final float FONT_SCALE2 = 0.8f;
    protected static final float FONT_SCALE = 0.7f;
    private int iCivA;
    private String sWar;
    private int iWarWidth;
    private String sDate;
    private int iDateWidth;
    private long lTime;
    private int TIME_IN_VIEW = 4750;
    private int TIME_IN_VIEW_HIDE_ANIMATION = 500;

    protected Menu_InGame_CapitalMoved() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.setVisible(false);
    }

    protected Menu_InGame_CapitalMoved(int nProvinceID, int nCivID) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.iCivA = nCivID;
        this.sWar = CFG.game.getProvince(nProvinceID).getCitiesSize() > 0 ? CFG.langManager.get("CapitalMoved") + ": " + CFG.game.getProvince(nProvinceID).getCity(0).getCityName() : CFG.langManager.get("CapitalMoved");
        CFG.glyphLayout.setText(CFG.fontMain, this.sWar);
        this.iWarWidth = (int)(CFG.glyphLayout.width * 0.8f);
        this.sDate = Game_Calendar.getCurrentDate();
        CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
        this.iDateWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.lTime = System.currentTimeMillis();
    }

    private final int getPosX2() {
        return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
    }

    private final int getPosY2() {
        return CFG.BUTTON_HEIGHT * 3 / 4;
    }

    private final int getWidth2() {
        return (int)((float)Math.max(this.iWarWidth + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING, this.iDateWidth + CFG.PADDING * 2) * 1.3f);
    }

    private final int getHeight2() {
        return (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2 + CFG.PADDING * 2;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        float tAlpha = this.getAlpha();
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.85f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), this.getHeight2());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 1.0f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - 2 + this.getHeight2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - 1 + this.getHeight2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), CFG.TEXT_HEIGHT);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - 1 + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() + CFG.TEXT_HEIGHT + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, tAlpha));
        CFG.game.getCiv(this.iCivA).getFlag().draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (this.iWarWidth + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING) / 2 + iTranslateX, this.getPosY2() + CFG.PADDING * 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 + iTranslateY - CFG.game.getCiv(this.iCivA).getFlag().getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (this.iWarWidth + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING) / 2 + iTranslateX, this.getPosY2() + CFG.PADDING * 2 + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2 + iTranslateY - ImageManager.getImage(Images.flag_rect).getHeight(), (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawText(oSB, this.sWar, this.getPosX2() + this.getWidth2() / 2 - (this.iWarWidth + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING) / 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING + iTranslateX, this.getPosY2() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.8f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL2.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2.b, 1.0f * tAlpha));
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.sDate, this.getPosX2() + this.getWidth2() / 2 - this.iDateWidth / 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, CFG.COLOR_TEXT_NUM_OF_PROVINCES.a * tAlpha));
        CFG.fontMain.getData().setScale(1.0f);
        CFG.setRender_3(true);
        if (System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW) {
            this.setVisible(false);
        }
    }

    private final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    private final float getAlpha() {
        if (System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION) {
            return Math.max(0.0f, 1.0f - (float)(System.currentTimeMillis() - (this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION)) / (float)this.TIME_IN_VIEW_HIDE_ANIMATION);
        }
        return 1.0f;
    }
}

