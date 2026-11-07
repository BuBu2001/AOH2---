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

class MenuElement_Hover_v2_Element_Type_Terrain
implements MenuElement_Hover_v2_Element_Type {
    private int iTerrainID;
    private int offsetLeft = 0;
    private int offsetRight = 0;

    protected MenuElement_Hover_v2_Element_Type_Terrain(int iTerrainID) {
        this.iTerrainID = iTerrainID;
        this.offsetLeft = 0;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Terrain(int iTerrainID, int offsetLeft) {
        this.iTerrainID = iTerrainID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = CFG.PADDING;
    }

    protected MenuElement_Hover_v2_Element_Type_Terrain(int iTerrainID, int offsetLeft, int offsetRight) {
        this.iTerrainID = iTerrainID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }

    @Override
    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        CFG.terrainTypesManager.getIcon(this.iTerrainID).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING - CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale() / 2.0f), (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()), (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale()));
        ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING - ImageManager.getImage(Images.flag_rect).getHeight() + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale() / 2.0f), (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()), (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale()));
        oSB.setColor(Color.WHITE);
    }

    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale());
    }

    private final float getImageScale() {
        return (float)CFG.TEXT_HEIGHT / (float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight();
    }
}

