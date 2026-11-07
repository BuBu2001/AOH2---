/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Graph_MovementPoints
extends SliderMenu {
    protected Menu_InGame_Graph_MovementPoints() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Statistics"), CFG.BUTTON_HEIGHT / 2, true, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_InGame_Graph_MovementPoints.this.getPosX() + iTranslateX, Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_InGame_Graph_MovementPoints.this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight(), false, false);
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_InGame_Graph_MovementPoints.this.getPosX() + Menu_InGame_Graph_MovementPoints.this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_title).getWidth() + iTranslateX, Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.0627451f, 0.09411765f, 0.25490198f, 0.45f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_InGame_Graph_MovementPoints.this.getPosX() + iTranslateX, Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_InGame_Graph_MovementPoints.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_InGame_Graph_MovementPoints.this.getPosX() + 2 + iTranslateX, Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_InGame_Graph_MovementPoints.this.getWidth() - 4);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_InGame_Graph_MovementPoints.this.getPosX() + iTranslateX, Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_InGame_Graph_MovementPoints.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.6f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 150, 150, 500, 325, menuElements, true, true);
        this.updateLanguage();
        this.getMenuElement(0).setCheckboxState(true);
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY, this.getW() - ImageManager.getImage(Images.new_game_box).getWidth(), this.getH(), false, true);
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + this.getW() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX, -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY, ImageManager.getImage(Images.new_game_box).getWidth(), this.getH(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    protected final int getW() {
        return this.getWidth();
    }

    protected final int getH() {
        return this.getHeight();
    }

    @Override
    protected boolean setWidth(int iWidth) {
        boolean out = super.setWidth(iWidth);
        this.getMenuElement(0).setCheckboxState(true);
        return out;
    }
}

