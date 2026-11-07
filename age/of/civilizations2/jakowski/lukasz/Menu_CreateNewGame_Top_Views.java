/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateNewGame_Top;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_CreateNewGame_Top_Views
extends SliderMenu {
    protected Menu_CreateNewGame_Top_Views() {
        ArrayList menuElements = new ArrayList();
        this.updateLanguage();
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + (iTranslateY -= (int)((float)CFG.GAME_HEIGHT * (100.0f - Menu_CreateNewGame_Top.fMovePercentage) / 100.0f)), this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_box).getWidth(), this.getHeight(), true, true);
        this.drawBackgroundMode(oSB, sliderMenuIsActive);
        Rectangle clipBounds = new Rectangle(this.getPosX() + 2 + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth() - 4, -this.getHeight());
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }
}

