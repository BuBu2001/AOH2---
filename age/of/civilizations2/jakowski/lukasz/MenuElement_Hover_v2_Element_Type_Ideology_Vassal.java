/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MenuElement_Hover_v2_Element_Type_Ideology_Vassal
implements MenuElement_Hover_v2_Element_Type {
    private int iIdeologyID;
    private int offsetLeft = 0;
    private int offsetRight = 0;

    protected MenuElement_Hover_v2_Element_Type_Ideology_Vassal(int iIdeologyID) {
        this.iIdeologyID = iIdeologyID;
        this.offsetLeft = 0;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Ideology_Vassal(int iIdeologyID, int offsetLeft) {
        this.iIdeologyID = iIdeologyID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Ideology_Vassal(int iIdeologyID, int offsetLeft, int offsetRight) {
        this.iIdeologyID = iIdeologyID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING - CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().getHeight() + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().getHeight() * this.getImageScale() / 2.0f), (int)((float)CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().getWidth() * this.getImageScale()), (int)((float)CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().getHeight() * this.getImageScale()));
    }

    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + (int)((float)CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().getWidth() * this.getImageScale());
    }

    private final float getImageScale() {
        return (float)CFG.TEXT_HEIGHT / (float)CFG.ideologiesManager.getIdeology(this.iIdeologyID).getiCrownVassalImage().getHeight();
    }
}

