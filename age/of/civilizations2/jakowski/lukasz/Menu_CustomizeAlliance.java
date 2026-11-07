/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import java.util.ArrayList;

class Menu_CustomizeAlliance
extends SliderMenu {
    protected Menu_CustomizeAlliance() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        CFG.game.checkAlliances();
        CFG.menuManager.rebuildManageDiplomacy_Alliances();
        CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 0;
        CFG.menuManager.setViewID(Menu.eMANAGE_DIPLOMACY);
        CFG.menuManager.setBackAnimation(true);
    }
}

