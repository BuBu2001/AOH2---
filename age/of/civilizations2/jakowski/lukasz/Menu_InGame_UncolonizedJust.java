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

class Menu_InGame_UncolonizedJust
extends SliderMenu {
    protected static final float FONT_SCALE2 = 0.7f;
    private String sUncolonized;
    private int iUncolonizedWidth;

    protected Menu_InGame_UncolonizedJust() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.sUncolonized = CFG.langManager.get("UncolonizedProvince");
        CFG.glyphLayout.setText(CFG.fontMain, this.sUncolonized);
        this.iUncolonizedWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    private final int getPosX2() {
        return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
    }

    private final int getPosY2() {
        return CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING - this.getHeight2();
    }

    private final int getWidth2() {
        return (int)((float)(this.iUncolonizedWidth + CFG.PADDING * 2) * 1.5f);
    }

    private final int getHeight2() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        float tAlpha = 1.0f;
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.85f * tAlpha));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), this.getHeight2());
        oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.8f * tAlpha));
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
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.sUncolonized, this.getPosX2() + this.getWidth2() / 2 - this.iUncolonizedWidth / 2 + iTranslateX, this.getPosY2() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7f) / 2.0f) + iTranslateY, new Color(1.0f, 1.0f, 1.0f, 1.0f * tAlpha));
        CFG.fontMain.getData().setScale(1.0f);
    }
}

