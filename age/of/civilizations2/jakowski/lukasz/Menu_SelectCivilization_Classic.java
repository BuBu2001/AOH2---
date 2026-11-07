/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_SelectCivilization_Classic
extends SliderMenu {
    protected Menu_SelectCivilization_Classic() {
        this.initMenu();
    }

    private final void initMenu() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            menuElements.add(new Button_Menu(CFG.game.getCiv(i).getCivName(), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("RandomCivilization"));
        this.getTitle().setText(CFG.langManager.get("SelectCivilization"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).getFlag().draw(oSB, this.getMenuElement(i + 1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i + 1).getPosY() + this.getMenuElement(i + 1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() + iTranslateY);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            default: {
                CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
        CFG.menuManager.setBackAnimation(true);
    }
}

