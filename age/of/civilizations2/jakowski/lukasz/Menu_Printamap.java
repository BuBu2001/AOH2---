/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

class Menu_Printamap
extends SliderMenu {
    private int iMapPosX = 0;
    private int iMapPosY = 0;
    private int id = 0;

    protected Menu_Printamap() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getMapCoordinates().setNewPosX(this.iMapPosX);
        CFG.map.getMapCoordinates().setNewPosY(this.iMapPosY);
        this.iMapPosX -= CFG.GAME_WIDTH;
        if (-this.iMapPosX >= CFG.map.getMapBG().getWidth()) {
            this.iMapPosX = 0;
            this.iMapPosY -= CFG.GAME_HEIGHT;
            if (-this.iMapPosY >= CFG.map.getMapBG().getHeight()) {
                this.onBackPressed();
                CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            }
        }
        this.saveScenarioMinimapPreviewTexture(oSB);
        CFG.setRender_3(true);
    }

    @Override
    protected void actionElement(int nMenuElementID) {
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewIDWithoutAnimation(CFG.backToMenu);
        CFG.menuManager.setBackAnimation(true);
    }

    protected final void saveScenarioMinimapPreviewTexture(SpriteBatch oSB) {
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        Image tempMinimapPrerivew = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - CFG.GAME_HEIGHT, CFG.GAME_WIDTH, CFG.GAME_HEIGHT)));
        try {
            tempMinimapPrerivew.getTexture().getTextureData().prepare();
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        PixmapIO.writePNG(Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "PRINT/map" + this.id++ + ".png"), tempMinimapPrerivew.getTexture().getTextureData().consumePixmap());
        CFG.setRender_3(true);
        tempMinimapPrerivew.getTexture().dispose();
        tempMinimapPrerivew = null;
    }
}

