/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_Circle;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_ProvincePopulation
extends SliderMenu {
    protected Menu_InGame_ProvincePopulation() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        ArrayList<Integer> nData = new ArrayList<Integer>();
        ArrayList<Integer> nCivs = new ArrayList<Integer>();
        for (int i = 1; i < 5; ++i) {
            nData.add(CFG.game.getCiv(i).getNumOfProvinces());
            nCivs.add(i);
        }
        menuElements.add(new Graph_Circle(CFG.PADDING + 2, CFG.PADDING + 2, nData, nCivs, null));
        this.initMenu(null, CFG.GAME_WIDTH - CFG.graphCircleDraw.getWidth() - 2 - CFG.PADDING * 2, CFG.GAME_HEIGHT - 2 - CFG.map.getMapBG().getMinimapHeight() - CFG.graphCircleDraw.getWidth() - CFG.PADDING * 2, CFG.graphCircleDraw.getWidth() + CFG.PADDING * 2 + 2, CFG.graphCircleDraw.getWidth() + CFG.PADDING * 2 + 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.bg_game_action).draw2(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.bg_game_action).getHeight() + this.getMenuPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, false);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    @Override
    protected int getPosX() {
        return CFG.GAME_WIDTH - this.getMenuElement(0).getWidth() - CFG.PADDING * 2 - 2;
    }

    @Override
    protected int getMenuPosX() {
        return CFG.GAME_WIDTH - this.getMenuElement(0).getWidth() - CFG.PADDING * 2 - 2;
    }

    @Override
    protected int getWidth() {
        return this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 2;
    }
}

