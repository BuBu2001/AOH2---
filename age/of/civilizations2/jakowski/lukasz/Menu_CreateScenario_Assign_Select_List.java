/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_Assign_Select_List
extends SliderMenu {
    private List<Integer> lCivs;

    protected Menu_CreateScenario_Assign_Select_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.lCivs = new ArrayList<Integer>();
        int j = 0;
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.chosen_AlphabetCharachter == null) {
                menuElements.add(new Button_Flag(i, 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4, CFG.BUTTON_HEIGHT, Button_Flag.ButtonFlagType.FLAG_COLOR));
                menuElements.add(new Button_Menu_Classic(CFG.game.getCiv(i).getCivName(), CFG.PADDING, CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 4, CFG.BUTTON_HEIGHT, true));
                continue;
            }
            if (CFG.game.getCiv(i).getCivName().charAt(0) != CFG.chosen_AlphabetCharachter.charAt(0)) continue;
            this.lCivs.add(i);
            menuElements.add(new Button_Flag(i, 0, CFG.BUTTON_HEIGHT * j + CFG.PADDING * (j + 1), CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4, CFG.BUTTON_HEIGHT, Button_Flag.ButtonFlagType.FLAG_COLOR));
            menuElements.add(new Button_Menu_Classic(CFG.game.getCiv(i).getCivName(), CFG.PADDING, CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4, CFG.BUTTON_HEIGHT * j + CFG.PADDING * (j + 1), CFG.GAME_WIDTH - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 4, CFG.BUTTON_HEIGHT, true));
            ++j;
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected final void actionElement(int iID) {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN);
        if (CFG.chosen_AlphabetCharachter == null) {
            if (CFG.iCreateScenario_AssignProvinces_Civ != iID / 2) {
                CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
                CFG.game.enableDrawCivilizationRegions(iID / 2, 0);
            }
            CFG.iCreateScenario_AssignProvinces_Civ = iID / 2;
        } else {
            if (CFG.iCreateScenario_AssignProvinces_Civ != this.lCivs.get(iID / 2)) {
                CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
                CFG.game.enableDrawCivilizationRegions(this.lCivs.get(iID / 2), 0);
            }
            CFG.iCreateScenario_AssignProvinces_Civ = this.lCivs.get(iID / 2);
        }
    }
}

