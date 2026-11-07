/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_View;
import age.of.civilizations2.jakowski.lukasz.Button_ViewEnd;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_TopViews
extends SliderMenu {
    protected Menu_InGame_TopViews() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (CFG.GAME_WIDTH - ImageManager.getImage(Images.top_left).getWidth() + CFG.topBox.leftExtraViewPadding) / 4;
        if (tempWidth > CFG.BUTTON_WIDTH * 3) {
            tempWidth = (CFG.GAME_WIDTH - ImageManager.getImage(Images.top_left).getWidth() + CFG.topBox.leftExtraViewPadding) / 5;
        }
        menuElements.add(new Button_View(null, 0, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 2, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 3, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 4, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 5, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 6, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 7, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 8, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 9, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 10, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 11, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_View(null, tempWidth * 12, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        menuElements.add(new Button_ViewEnd(null, tempWidth * 13, 0, tempWidth, ImageManager.getImage(Images.top_left).getHeight(), true));
        this.initMenu(null, ImageManager.getImage(Images.top_left).getWidth() - CFG.topBox.leftExtraViewPadding, 0, CFG.GAME_WIDTH - ImageManager.getImage(Images.top_left).getWidth() + CFG.topBox.leftExtraViewPadding, ImageManager.getImage(Images.top_left).getHeight() + 1, menuElements);
        this.updateLanguage();
        this.setVisible(false);
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Army"));
        this.getMenuElement(1).setText(CFG.langManager.get("Economy"));
        this.getMenuElement(2).setText(CFG.langManager.get("Population"));
        this.getMenuElement(3).setText(CFG.langManager.get("Diplomacy"));
        this.getMenuElement(4).setText(CFG.langManager.get("SupplyLines"));
        this.getMenuElement(5).setText(CFG.langManager.get("TerrainType"));
        this.getMenuElement(6).setText(CFG.langManager.get("GrowthRate"));
        this.getMenuElement(7).setText("Continents");
        this.getMenuElement(8).setText("Gold Income");
        this.getMenuElement(9).setText(CFG.langManager.get("Happiness"));
        this.getMenuElement(10).setText("Ports");
        this.getMenuElement(11).setText("Fortifications");
        this.getMenuElement(12).setText("Watch Towers");
        this.getMenuElement(13).setText(CFG.langManager.get("TechnologyLevels"));
    }

    @Override
    protected void updateMenuElements_IsInView() {
        super.updateMenuElements_IsInView_X();
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
        Game_Render.updateRenderer();
        Game_Render_Province.updateDrawProvinces();
    }
}

