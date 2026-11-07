/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MenuElement_Hover_v2_Element_Type_Text
implements MenuElement_Hover_v2_Element_Type {
    private String sText;
    private int iTextWidth;
    private Color oColor;

    protected MenuElement_Hover_v2_Element_Type_Text(String sText) {
        this.init(sText, new Color(0.9843137f, 0.9843137f, 0.9843137f, 1.0f));
    }

    protected MenuElement_Hover_v2_Element_Type_Text(String sText, Color nColor) {
        this.init(sText, nColor);
    }

    private final void init(String sText, Color oColor) {
        this.sText = sText;
        this.oColor = oColor;
        CFG.glyphLayout.setText(CFG.fontMain, sText);
        this.iTextWidth = (int)(CFG.glyphLayout.width * 0.75f);
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        CFG.drawText(oSB, this.sText, nPosX, nPosY + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75f) / 2.0f), new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
    }

    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
}

