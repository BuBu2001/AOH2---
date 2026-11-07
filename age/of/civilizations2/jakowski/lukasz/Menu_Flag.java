/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_FlagPixel;
import age.of.civilizations2.jakowski.lukasz.Menu_FlagPixel_Color;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Flag
extends SliderMenu {
    protected Menu_Flag() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int pixelWidth = (CFG.GAME_WIDTH - CFG.PADDING * 6) / CFG.CIV_FLAG_WIDTH;
        for (int i = 0; i < CFG.CIV_FLAG_HEIGHT; ++i) {
            for (int j = 0; j < CFG.CIV_FLAG_WIDTH; ++j) {
                menuElements.add(new Menu_FlagPixel(CFG.PADDING * 3 + pixelWidth * j + (CFG.GAME_WIDTH - pixelWidth * CFG.CIV_FLAG_WIDTH - CFG.PADDING * 6) / 2, pixelWidth * i + CFG.PADDING, pixelWidth, pixelWidth));
            }
        }
        CFG.FlagPixelColor = new Menu_FlagPixel_Color();
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH, pixelWidth * CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2, menuElements);
    }

    @Override
    protected final void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
        int i;
        oSB.setColor(0.0f, 0.0f, 0.0f, 0.2f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY(), this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
        for (i = this.getMenuElementsSize() - 1; i >= 0; --i) {
            this.getMenuElement(i).draw(oSB, this.getMenuPosX() + iTranslateX, this.getMenuPosY(), i);
        }
        oSB.setColor(0.196f, 0.196f, 0.196f, 1.0f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), 1, this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT + this.getPosY(), this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), 1, this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT);
        oSB.setColor(0.196f, 0.196f, 0.196f, 0.15f);
        for (i = 1; i < CFG.CIV_FLAG_HEIGHT; ++i) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * i + this.getPosY(), this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH, 1);
        }
        for (i = 1; i < CFG.CIV_FLAG_WIDTH; ++i) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * i + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), 1, this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT);
        }
        oSB.setColor(0.196f, 0.196f, 0.196f, 0.4f);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * (CFG.CIV_FLAG_HEIGHT / 2) + this.getPosY(), this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * (CFG.CIV_FLAG_HEIGHT / 3) + this.getPosY(), this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * (CFG.CIV_FLAG_HEIGHT / 3 * 2 + 1) + this.getPosY(), this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * (CFG.CIV_FLAG_WIDTH / 2) + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), 1, this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * (CFG.CIV_FLAG_WIDTH / 3) + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), 1, this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * (CFG.CIV_FLAG_WIDTH / 3 * 2) + this.getPosX() + iTranslateX, this.getMenuElement(0).getPosY() + this.getPosY(), 1, this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected final void actionElement(int iID) {
        if (CFG.flagEditorMode == CFG.FlagEditorMode.PENCIL) {
            CFG.FlagPixelColor.setR(iID, (float)CFG.flagR / 2.55f / 100.0f);
            CFG.FlagPixelColor.setG(iID, (float)CFG.flagG / 2.55f / 100.0f);
            CFG.FlagPixelColor.setB(iID, (float)CFG.flagB / 2.55f / 100.0f);
        } else if (CFG.flagEditorMode == CFG.FlagEditorMode.PAINT_BUCKET && (CFG.FlagPixelColor.getR(iID) != (float)CFG.flagR / 2.55f / 100.0f || CFG.FlagPixelColor.getG(iID) != (float)CFG.flagG / 2.55f / 100.0f || CFG.FlagPixelColor.getB(iID) != (float)CFG.flagB / 2.55f / 100.0f)) {
            float tempR = CFG.FlagPixelColor.getR(iID);
            float tempG = CFG.FlagPixelColor.getG(iID);
            float tempB = CFG.FlagPixelColor.getB(iID);
            for (int i = 0; i < CFG.CIV_FLAG_HEIGHT * CFG.CIV_FLAG_WIDTH; ++i) {
                if (CFG.FlagPixelColor.getR(i) != tempR || CFG.FlagPixelColor.getG(i) != tempG || CFG.FlagPixelColor.getB(i) != tempB) continue;
                CFG.FlagPixelColor.setR(i, (float)CFG.flagR / 2.55f / 100.0f);
                CFG.FlagPixelColor.setG(i, (float)CFG.flagG / 2.55f / 100.0f);
                CFG.FlagPixelColor.setB(i, (float)CFG.flagB / 2.55f / 100.0f);
            }
        }
    }
}

