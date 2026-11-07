/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Menu_FlagPixel
extends MenuElement {
    protected Menu_FlagPixel(int iPosX, int iPosY, int iWidth, int iHeight) {
        this.typeOfElement = MenuElement.TypeOfElement.FLAG_PIXEL;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
    }

    @Override
    protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, int flagPixelID) {
        oSB.setColor(CFG.FlagPixelColor.getR(flagPixelID), CFG.FlagPixelColor.getG(flagPixelID), CFG.FlagPixelColor.getB(flagPixelID), CFG.FlagPixelColor.getA(flagPixelID));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
    }
}

