/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_FlagAction_GraphModes;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_GraphManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_FlagAction_GraphModes
extends SliderMenu {
    protected Menu_InGame_FlagAction_GraphModes() {
        int tempHeight = CFG.BUTTON_HEIGHT / 2;
        int tempWidth = 0;
        tempWidth = CFG.isAndroid() && !CFG.LANDSCAPE ? CFG.GAME_WIDTH - CFG.PADDING * 4 - 2 : Menu_InGame_FlagAction.getWindowWidth() - Menu_InGame_FlagAction.getWindowWidth() * 2 / 5 - CFG.PADDING * 2;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_FlagAction_GraphModes(null, 0, 0, 0, CFG.BUTTON_WIDTH, --tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 100, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 111, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 102, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 106, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 1, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 13, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 10, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_FlagAction_GraphModes(null, 11, 0, 0, CFG.BUTTON_WIDTH, tempHeight, true));
        menuElements.add(new Button_Transparent(0, 0, tempWidth, tempHeight, true));
        this.initMenu(null, (CFG.isAndroid() && !CFG.LANDSCAPE ? CFG.PADDING * 2 + 2 : Menu_InGame_FlagAction.getWindowWidth() - Menu_InGame_FlagAction.getWindowWidth() * 3 / 5) + AoCGame.LEFT, CFG.isAndroid() && !CFG.LANDSCAPE ? ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4 + (CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2) / 2 : ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING * 4), tempWidth - 2, ++tempHeight, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        int nID = 0;
        this.getMenuElement(nID++).setText(CFG.langManager.get("Provinces"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("Income"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("Balance"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("MilitaryUpkeep"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("WorldsPopulation"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("Population"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("Economy"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("ConqueredProvinces"));
        this.getMenuElement(nID++).setText(CFG.langManager.get("ConstructedBuildings"));
        this.updatedButtonsWidth_Padding(0, CFG.BUTTON_WIDTH * 3 / 4, 0);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), true, true);
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 3);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 4, false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight());
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, 1, this.getHeight(), false, true);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + this.getHeight() - 1 + iTranslateY, this.getWidth() * 3 / 4, 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }

    @Override
    protected void actionElement(int iID) {
        if (iID != this.getMenuElementsSize() - 1) {
            Menu_InGame_GraphManager.setActiveGraphID(this.getMenuElement(iID).getCurrent());
        }
    }
}

