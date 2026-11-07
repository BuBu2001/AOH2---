/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MenuElement_Hover_v2_Element_Type_Image
implements MenuElement_Hover_v2_Element_Type {
    private int iImageID;
    private int offsetLeft;
    private int offsetRight;

    protected MenuElement_Hover_v2_Element_Type_Image(int iImageID) {
        this.iImageID = iImageID;
        this.offsetLeft = 0;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Image(int iImageID, int offsetLeft) {
        this.iImageID = iImageID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Image(int iImageID, int offsetLeft, int offsetRight) {
        this.iImageID = iImageID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ImageManager.getImage(this.iImageID).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING - ImageManager.getImage(this.iImageID).getHeight() + CFG.TEXT_HEIGHT / 2 - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale() / 2.0f), (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale()), (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale()));
    }

    @Override
    public int getWidth() {
        return this.offsetLeft + this.offsetRight + (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale());
    }

    private final float getImageScale() {
        return (float)CFG.TEXT_HEIGHT * 0.75f / (float)ImageManager.getImage(this.iImageID).getHeight();
    }
}

