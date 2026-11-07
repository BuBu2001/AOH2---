/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import java.util.ArrayList;
import java.util.List;

class Menu_CustomizeAlliance_AddCivilization_Alphabet
extends SliderMenu {
    private List<Character> lCharacters;

    protected Menu_CustomizeAlliance_AddCivilization_Alphabet() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_Classic(null, -1, 0, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
        this.lCharacters = new ArrayList<Character>();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getAllianceID() != 0) continue;
            boolean addChar = true;
            for (int a = 0; a < this.lCharacters.size(); ++a) {
                if (this.lCharacters.get(a).charValue() != CFG.game.getCiv(i).getCivName().charAt(0)) continue;
                addChar = false;
                break;
            }
            if (!addChar) continue;
            this.lCharacters.add(Character.valueOf(CFG.game.getCiv(i).getCivName().charAt(0)));
        }
        for (i = 0; i < this.lCharacters.size() - 1; ++i) {
            for (int j = i + 1; j < this.lCharacters.size(); ++j) {
                if (this.lCharacters.get(i).charValue() <= this.lCharacters.get(j).charValue()) continue;
                char temp = this.lCharacters.get(i).charValue();
                this.lCharacters.set(i, this.lCharacters.get(j));
                this.lCharacters.set(j, Character.valueOf(temp));
            }
        }
        for (i = 0; i < this.lCharacters.size(); ++i) {
            menuElements.add(new Button_Menu_Classic("[" + this.lCharacters.get(i) + "]", -1, CFG.BUTTON_HEIGHT * (i + 1), CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
        }
        if (((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() < CFG.GAME_WIDTH) {
            int tempElementWidth = CFG.GAME_WIDTH / menuElements.size();
            int tempPosX = 0;
            for (int i2 = 0; i2 < menuElements.size() - 1; ++i2) {
                ((MenuElement)menuElements.get(i2)).setPosX(tempPosX);
                ((MenuElement)menuElements.get(i2)).setWidth(tempElementWidth);
                tempPosX += tempElementWidth;
            }
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setPosX(tempPosX);
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setWidth(CFG.GAME_WIDTH - tempPosX);
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText("[" + CFG.langManager.get("ALL") + "]");
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.chosen_AlphabetCharachter = null;
                CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE_ADD_CIVILIZATION);
                return;
            }
        }
        CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 1);
        CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE_ADD_CIVILIZATION);
    }
}

