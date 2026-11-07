/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Slider_Relations;
import java.util.ArrayList;
import java.util.List;

class Menu_ManageDiplomacy_Relations
extends SliderMenu {
    protected List<Integer> lCivIDs;

    protected Menu_ManageDiplomacy_Relations() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.lCivIDs = new ArrayList<Integer>();
        int j = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (i == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID || CFG.chosen_AlphabetCharachter != null && CFG.chosen_AlphabetCharachter.charAt(0) != CFG.game.getCiv(i).getCivName().charAt(0)) continue;
            menuElements.add(new Button_Flag(i, 0, CFG.PADDING * (j + 1) + CFG.BUTTON_HEIGHT * j, CFG.CIV_FLAG_WIDTH + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 4, CFG.BUTTON_HEIGHT, Button_Flag.ButtonFlagType.FLAG_COLOR));
            menuElements.add(new Button_Menu_Classic("-", -1, CFG.CIV_FLAG_WIDTH + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 4, CFG.PADDING * (j + 1) + CFG.BUTTON_HEIGHT * j, CFG.BUTTON_WIDTH * 3 / 4, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Slider_Relations(CFG.BUTTON_WIDTH * 3 / 4 + CFG.CIV_FLAG_WIDTH + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 4, CFG.PADDING * (j + 1) + CFG.BUTTON_HEIGHT * j + CFG.PADDING, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 / 4 * 2 - (CFG.CIV_FLAG_WIDTH + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 4), CFG.BUTTON_HEIGHT - CFG.PADDING * 2, -100, 100, (int)CFG.game.getCivRelation_OfCivB(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, i)));
            menuElements.add(new Button_Menu_Classic("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 / 4, CFG.PADDING * (j + 1) + CFG.BUTTON_HEIGHT * j, CFG.BUTTON_WIDTH * 3 / 4, CFG.BUTTON_HEIGHT, true));
            this.lCivIDs.add(i);
            ++j;
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID % 4 == 0) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = this.lCivIDs.get(iID / 4);
        } else if (iID % 4 == 1) {
            this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
            CFG.game.setCivRelation_OfCivB(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, this.lCivIDs.get(iID / 4), this.getMenuElement(iID + 1).getCurrent());
        } else if (iID % 4 == 2) {
            CFG.game.setCivRelation_OfCivB(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, this.lCivIDs.get(iID / 4), this.getMenuElement(iID).getCurrent());
        } else if (iID % 4 == 3) {
            CFG.game.setCivRelation_OfCivB(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, this.lCivIDs.get(iID / 4), this.getMenuElement(iID - 1).getCurrent());
            this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
        }
    }
}

