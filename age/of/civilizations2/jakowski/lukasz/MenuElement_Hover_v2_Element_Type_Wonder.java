/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Wonder
implements MenuElement_Hover_v2_Element_Type {
    private int iProvinceID;
    private int iWonderID;
    private int offsetLeft;
    private int offsetRight;

    protected MenuElement_Hover_v2_Element_Type_Wonder(int nProvinceID) {
        this.iProvinceID = nProvinceID;
        this.offsetLeft = 0;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Wonder(int nProvinceID, int offsetLeft) {
        this.iProvinceID = nProvinceID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Wonder(int nProvinceID, int iWonderID, int offsetLeft, int offsetRight) {
        this.iProvinceID = nProvinceID;
        this.iWonderID = iWonderID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        try {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
            CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING - CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight() + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight() * this.getImageScale() / 2.0f), (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getWidth() * this.getImageScale()), (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight() * this.getImageScale()));
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    public int getWidth() {
        try {
            return this.offsetLeft + this.offsetRight + (int)((float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getWidth() * this.getImageScale());
        }
        catch (IndexOutOfBoundsException ex) {
            return this.offsetLeft + this.offsetRight;
        }
        catch (NullPointerException ex) {
            return this.offsetLeft + this.offsetRight;
        }
    }

    private final float getImageScale() {
        try {
            return (float)CFG.TEXT_HEIGHT * 0.75f / (float)CFG.game.getProvince((int)this.iProvinceID).getWonder((int)this.iWonderID).nImage.getHeight();
        }
        catch (IndexOutOfBoundsException ex) {
            return (float)CFG.TEXT_HEIGHT * 0.75f;
        }
        catch (NullPointerException ex) {
            return (float)CFG.TEXT_HEIGHT * 0.75f;
        }
    }
}

