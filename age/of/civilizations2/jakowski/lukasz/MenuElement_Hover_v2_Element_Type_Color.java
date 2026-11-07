/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MenuElement_Hover_v2_Element_Type_Color
implements MenuElement_Hover_v2_Element_Type {
    private Color oColor;
    private int offsetLeft = 0;
    private int offsetRight = 0;

    protected MenuElement_Hover_v2_Element_Type_Color(Color oColor) {
        this.oColor = oColor;
        this.offsetLeft = 0;
        this.offsetRight = 0;
    }

    protected MenuElement_Hover_v2_Element_Type_Color(Color oColor, int offsetLeft) {
        this.oColor = oColor;
        this.offsetLeft = offsetLeft;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Color(Color oColor, int offsetLeft, int offsetRight) {
        this.oColor = oColor;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f), 2, (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
        oSB.setColor(Color.WHITE);
    }

    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + 2;
    }

    private final float getImageScale() {
        return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }
}

