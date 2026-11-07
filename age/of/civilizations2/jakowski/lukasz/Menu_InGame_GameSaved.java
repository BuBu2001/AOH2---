/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_GameSaved
extends SliderMenu {
    protected static final float FONT_SCALE2 = 0.75f;
    protected static final float FONT_SCALE = 0.75f;
    private String sWar;
    private int iWarWidth;
    private long lTime = -1L;
    private int TIME_IN_VIEW = 1750;
    private int TIME_IN_VIEW_HIDE_ANIMATION = 475;

    protected Menu_InGame_GameSaved() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.setVisible(false);
    }

    protected Menu_InGame_GameSaved(int init) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.sWar = CFG.langManager.get("GameSaved");
        CFG.glyphLayout.setText(CFG.fontMain, this.sWar);
        this.iWarWidth = (int)(CFG.glyphLayout.width * 0.75f);
        this.lTime = -1L;
    }

    private final int getPosX2() {
        return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
    }

    private final int getPosY2() {
        return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getHeight2();
    }

    private final int getWidth2() {
        return (int)((float)(this.iWarWidth + CFG.PADDING * 2) * 1.65f);
    }

    private final int getHeight2() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 2;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime < 0L) {
            this.lTime = System.currentTimeMillis();
        }
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
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.75f);
        CFG.drawText(oSB, this.sWar, this.getPosX2() + this.getWidth2() / 2 - this.iWarWidth / 2 + iTranslateX, this.getPosY2() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE.r, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE.g, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE.b, 1.0f * tAlpha));
        CFG.fontMain.getData().setScale(1.0f);
        CFG.setRender_3(true);
        if (System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW) {
            this.setVisible(false);
        }
    }

    private final float getAlpha() {
        if (System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION) {
            return Math.max(0.0f, 1.0f - (float)(System.currentTimeMillis() - (this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION)) / (float)this.TIME_IN_VIEW_HIDE_ANIMATION);
        }
        return 1.0f;
    }
}

