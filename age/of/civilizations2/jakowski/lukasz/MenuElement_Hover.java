/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

interface MenuElement_Hover {
    public void draw(SpriteBatch var1, int var2, int var3);

    public void drawAlwaysBelow(SpriteBatch var1, int var2, int var3);

    public void drawAlwaysOver(SpriteBatch var1, int var2, int var3);

    public void drawAlwaysOver_Mobile(SpriteBatch var1, int var2, int var3);

    public void drawProvinceInfo(SpriteBatch var1, int var2, int var3);

    public void draw_Hover(SpriteBatch var1, int var2, int var3);

    public void draw_HoverWithoutAnimation(SpriteBatch var1, int var2, int var3);
}

