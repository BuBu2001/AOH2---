/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MenuElement_Hover_v2_Element_Type_Space
implements MenuElement_Hover_v2_Element_Type {
    private static final String sText = "-----";
    private int iTextWidth;

    protected MenuElement_Hover_v2_Element_Type_Space() {
        CFG.glyphLayout.setText(CFG.fontMain, sText);
        this.iTextWidth = (int)(CFG.glyphLayout.width * 0.75f);
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        CFG.drawText(oSB, sText, nPosX, nPosY + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f), new Color(0.85f, 0.85f, 0.85f, nAlpha));
    }

    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
}

