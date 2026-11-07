/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_MainMenu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Games2
extends SliderMenu {
    protected Menu_Games2() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempH = CFG.GAME_HEIGHT / 2 - (CFG.BUTTON_HEIGHT * 10 + CFG.PADDING * 11) / 2;
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, false));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, false));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 3, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 4, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 5, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 6, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, false));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 7 + CFG.PADDING * 7, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, false));
        menuElements.add(new Button_Menu_LR_MainMenu(null, (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 8, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 9 + CFG.PADDING * 9, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected final void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("LoadGame"));
        this.getMenuElement(1).setText(CFG.langManager.get("ContinueGame"));
        this.getMenuElement(2).setText(CFG.langManager.get("NewGame"));
        this.getMenuElement(3).setText(CFG.langManager.get("RandomGame"));
        this.getMenuElement(4).setText(CFG.langManager.get("Tutorial"));
        this.getMenuElement(5).setText(CFG.langManager.get("Achievements"));
        this.getMenuElement(6).setText(CFG.langManager.get("HallofFame"));
        this.getMenuElement(7).setText(CFG.langManager.get("Leaderboards"));
        this.getMenuElement(8).setText(CFG.langManager.get("Statistics"));
        this.getMenuElement(9).setText(CFG.langManager.get("Back"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() - 2 + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + this.getMenuElement(0).getPosY() + iTranslateY, this.getMenuElement(0).getWidth() + 4 - ImageManager.getImage(Images.main_menu_edge).getWidth(), this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() - this.getMenuElement(0).getPosY() + (ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING) * 2 - ImageManager.getImage(Images.main_menu_edge).getHeight());
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() + this.getMenuElement(0).getWidth() + 4 - ImageManager.getImage(Images.main_menu_edge).getWidth() - 2 + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + this.getMenuElement(0).getPosY() + iTranslateY, ImageManager.getImage(Images.main_menu_edge).getWidth(), this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() - this.getMenuElement(0).getPosY() + (ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING) * 2 - ImageManager.getImage(Images.main_menu_edge).getHeight(), true, false);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() - 2 + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() - this.getMenuElement(0).getPosY() + (ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING) * 2 - ImageManager.getImage(Images.main_menu_edge).getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + this.getMenuElement(0).getPosY() + iTranslateY, this.getMenuElement(0).getWidth() + 4 - ImageManager.getImage(Images.main_menu_edge).getWidth(), ImageManager.getImage(Images.main_menu_edge).getHeight(), false, true);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getPosX() + this.getMenuElement(0).getWidth() + 4 - ImageManager.getImage(Images.main_menu_edge).getWidth() - 2 + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() - this.getMenuElement(0).getPosY() + (ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING) * 2 - ImageManager.getImage(Images.main_menu_edge).getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + this.getMenuElement(0).getPosY() + iTranslateY, ImageManager.getImage(Images.main_menu_edge).getWidth(), ImageManager.getImage(Images.main_menu_edge).getHeight(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 9: {
                this.onBackPressed();
                break;
            }
            case 2: {
                CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
                CFG.menuManager.setVisible_CreateNewGame_Options(false);
                CFG.menuManager.setVisible_CreateNewGame_CivInfo(true);
                break;
            }
            case 3: {
                CFG.showKeyboard();
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAINMENU);
        CFG.menuManager.setBackAnimation(true);
    }
}

