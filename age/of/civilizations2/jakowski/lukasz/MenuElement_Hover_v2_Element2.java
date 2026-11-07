/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class MenuElement_Hover_v2_Element2 {
    private List<MenuElement_Hover_v2_Element_Type> lElements = new ArrayList<MenuElement_Hover_v2_Element_Type>();

    protected MenuElement_Hover_v2_Element2(List<MenuElement_Hover_v2_Element_Type> nElements) {
        for (int i = 0; i < nElements.size(); ++i) {
            this.lElements.add(nElements.get(i));
        }
    }

    protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        int tX = 0;
        for (int i = 0; i < this.lElements.size(); ++i) {
            this.lElements.get(i).draw(oSB, nPosX + tX, nPosY, nAlpha);
            tX += this.lElements.get(i).getWidth();
        }
    }

    protected final int getWidth() {
        int out = 0;
        for (int i = 0; i < this.lElements.size(); ++i) {
            out += this.lElements.get(i).getWidth();
        }
        return out;
    }
}

