/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

class MenuElement_Hover_v2_Element_Type_Leader
implements MenuElement_Hover_v2_Element_Type {
    protected MenuElement_Hover_v2_Element_Type_Leader() {
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        try {
            if (CFG.activeCivLeader != null) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
                CFG.activeCivLeader.draw(oSB, nPosX, nPosY + CFG.PADDING);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
    }

    @Override
    public int getWidth() {
        try {
            return CFG.activeCivLeader != null ? CFG.activeCivLeader.getWidth() : 0;
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        return 0;
    }
}

