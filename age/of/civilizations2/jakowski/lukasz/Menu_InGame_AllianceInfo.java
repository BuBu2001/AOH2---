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

class Menu_InGame_AllianceInfo
extends SliderMenu {
    protected static final float FONT_SCALE2 = 0.9f;
    protected static final float FONT_SCALE = 0.8f;
    private int iAggressorID;
    private int iDefenderID;
    private int iAggressorWidth;
    private int iDefenderWidth;
    private String sWar;
    private int iWarWidth;
    private long lTime;
    private int TIME_IN_VIEW = 4500;
    private int TIME_IN_VIEW_HIDE_ANIMATION = 500;

    protected Menu_InGame_AllianceInfo() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.setVisible(false);
    }

    protected Menu_InGame_AllianceInfo(int nCivA, int nCivB) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.sWar = CFG.langManager.get("Alliance") + "!";
        CFG.glyphLayout.setText(CFG.fontMain, this.sWar);
        this.iWarWidth = (int)CFG.glyphLayout.width;
        this.iAggressorID = nCivA;
        this.iDefenderID = nCivB;
        CFG.glyphLayout.setText(CFG.fontMain, CFG.game.getCiv(this.iAggressorID).getCivName());
        this.iAggressorWidth = (int)(CFG.glyphLayout.width * 0.8f);
        CFG.glyphLayout.setText(CFG.fontMain, CFG.game.getCiv(this.iDefenderID).getCivName());
        this.iDefenderWidth = (int)(CFG.glyphLayout.width * 0.8f);
        this.lTime = System.currentTimeMillis();
    }

    private final int getPosX2() {
        return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
    }

    private final int getPosY2() {
        return CFG.BUTTON_HEIGHT * 3 / 4;
    }

    private final int getWidth2() {
        return (int)((float)Math.max(this.iWarWidth + CFG.PADDING * 2, (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) + CFG.PADDING * 2 + 4 + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 2 + CFG.PADDING * 2 + Math.max(this.iAggressorWidth, this.iDefenderWidth) * 2 + CFG.PADDING * 2) * 1.3f);
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
        ImageManager.getImage(Images.diplo_alliance).draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getHeight() * this.getImageScale(Images.diplo_alliance)) / 2 - ImageManager.getImage(Images.diplo_alliance).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)), (int)((float)ImageManager.getImage(Images.diplo_alliance).getHeight() * this.getImageScale(Images.diplo_alliance)));
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iAggressorID).getR() / 255.0f, (float)CFG.game.getCiv(this.iAggressorID).getG() / 255.0f, (float)CFG.game.getCiv(this.iAggressorID).getB() / 255.0f, 1.0f * tAlpha));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 - CFG.PADDING - 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        try {
            oSB.setColor(new Color((float)CFG.game.getCiv(this.iDefenderID).getR() / 255.0f, (float)CFG.game.getCiv(this.iDefenderID).getG() / 255.0f, (float)CFG.game.getCiv(this.iDefenderID).getB() / 255.0f, 1.0f * tAlpha));
        }
        catch (IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f));
        }
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 + CFG.PADDING + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 2, (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, tAlpha));
        CFG.game.getCiv(this.iAggressorID).getFlag().draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(this.iAggressorID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        CFG.game.getCiv(this.iDefenderID).getFlag().draw(oSB, this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 + CFG.PADDING + 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(this.iDefenderID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 + CFG.PADDING + 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.9f);
        CFG.drawText(oSB, this.sWar, this.getPosX2() + this.getWidth2() / 2 - this.iWarWidth / 2 + iTranslateX, this.getPosY2() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.9f) / 2.0f) + iTranslateY, new Color(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(), 1.0f * tAlpha));
        CFG.fontMain.getData().setScale(0.8f);
        CFG.drawText(oSB, CFG.game.getCiv(this.iAggressorID).getCivName(), this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) - CFG.PADDING - this.iAggressorWidth + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.8f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, CFG.COLOR_TEXT_NUM_OF_PROVINCES.a * tAlpha));
        CFG.drawText(oSB, CFG.game.getCiv(this.iDefenderID).getCivName(), this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * this.getImageScale(Images.diplo_alliance)) / 2 + CFG.PADDING + 2 + CFG.PADDING + iTranslateX, this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.8f) / 2.0f) + iTranslateY, new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, CFG.COLOR_TEXT_NUM_OF_PROVINCES.a * tAlpha));
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

