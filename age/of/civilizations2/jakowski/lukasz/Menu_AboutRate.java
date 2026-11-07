/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import java.util.ArrayList;

class Menu_AboutRate
extends SliderMenu {
    protected Menu_AboutRate() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void actionElement(int iID) {
                if (CFG.isDesktop()) {
                    CFG.GO_TO_LINK = "http://www.AgeofCivilizationsGame.com";
                    CFG.setDialogType(Dialog.GO_TO_LINK);
                }
                CFG.menuManager.setViewID(Menu.eMAINMENU);
                CFG.menuManager.setBackAnimation(true);
            }
        });
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + 1, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.isDesktop() ? "www.AgeofCivilizationsGame.com" : CFG.langManager.get("Rate") + " " + "Age of Civilizations II");
    }

    @Override
    protected final void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }
}

