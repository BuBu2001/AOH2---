/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_Connections_IDs
extends SliderMenu {
    protected Menu_MapEditor_Connections_IDs(int nProvinceID) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempY = 0;
        if (nProvinceID >= 0) {
            int i;
            for (i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                menuElements.add(new Button_Game("" + CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i), -1, CFG.PADDING, CFG.PADDING * (tempY + 1) + CFG.BUTTON_HEIGHT * tempY, CFG.BUTTON_WIDTH));
                ++tempY;
            }
            for (i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
                menuElements.add(new Button_Game("" + CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i), -1, CFG.PADDING, CFG.PADDING * (tempY + 1) + CFG.BUTTON_HEIGHT * tempY, CFG.BUTTON_WIDTH));
                ++tempY;
            }
        }
        this.initMenu(new SliderMenuTitle("ACT: " + nProvinceID, CFG.BUTTON_HEIGHT * 3 / 4, menuElements.size() > 0, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge).getHeight() - this.getHeight(), Menu_MapEditor_Connections_IDs.this.getWidth() + 2, this.getHeight());
                oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
                ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX - 1 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_MapEditor_Connections_IDs.this.getWidth() + 1, 1);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, iTranslateX, nPosX, nPosY, nWidth, sliderMenuIsActive);
            }
        }, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 4, CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.getMenuElementsSize() > 0) {
            ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight(), this.getWidth() + 2, this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() + CFG.PADDING, false, true);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        try {
            CFG.game.setActiveProvinceID(Integer.parseInt(this.getMenuElement(iID).getText()));
            CFG.map.getMapCoordinates().centerToProvinceID(Integer.parseInt(this.getMenuElement(iID).getText()));
            CFG.toast.setInView(" --" + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 + " - " + CFG.game.getActiveProvinceID() + "-- ");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }
}

