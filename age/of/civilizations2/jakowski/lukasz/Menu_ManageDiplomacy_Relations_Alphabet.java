/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Active;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_ManageDiplomacy_Relations_Alphabet
extends SliderMenu {
    private List<Character> lCharacters;

    protected Menu_ManageDiplomacy_Relations_Alphabet() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        String sSelectOnMap = CFG.langManager.get("SelectOnMap");
        CFG.glyphLayout.setText(CFG.fontMain, sSelectOnMap);
        int iSelectOnMapWidth = (int)CFG.glyphLayout.width + CFG.PADDING * 4;
        menuElements.add(new Button_Menu_Classic(null, -1, 0, CFG.PADDING, iSelectOnMapWidth, CFG.BUTTON_HEIGHT, true));
        if (CFG.chosen_AlphabetCharachter == null) {
            menuElements.add(new Button_Menu_Active(null, -1, iSelectOnMapWidth, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
        } else {
            menuElements.add(new Button_Menu_Classic(null, -1, iSelectOnMapWidth, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
        }
        this.lCharacters = new ArrayList<Character>();
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (i == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID) continue;
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
            if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(i).charValue() == CFG.chosen_AlphabetCharachter.charAt(0)) {
                menuElements.add(new Button_Menu_Active("[" + this.lCharacters.get(i) + "]", -1, iSelectOnMapWidth + CFG.BUTTON_HEIGHT * (i + 1), CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
                continue;
            }
            menuElements.add(new Button_Menu_Classic("[" + this.lCharacters.get(i) + "]", -1, iSelectOnMapWidth + CFG.BUTTON_HEIGHT * (i + 1), CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("SelectOnMap"));
        this.getMenuElement(1).setText("[" + CFG.langManager.get("ALL") + "]");
        this.getTitle().setText(CFG.langManager.get("CustomizeRelations") + " " + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName());
    }

    @Override
    protected void drawTitle(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive, int nPosY) {
        super.drawTitle(oSB, iTranslateX, iTranslateY, sliderMenuIsActive, nPosY);
        CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getFlag().draw(oSB, this.getWidth() / 2 + this.getTitle().getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getTitle().getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getWidth() / 2 + this.getTitle().getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getTitle().getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                return;
            }
            case 1: {
                if (CFG.chosen_AlphabetCharachter != null) {
                    for (int i = 0; i < this.lCharacters.size(); ++i) {
                        if (this.lCharacters.get(i).charValue() != CFG.chosen_AlphabetCharachter.charAt(0)) continue;
                        this.setMenuElement(i + 2, new Button_Menu_Classic(this.getMenuElement(i + 2).getText(), -1, this.getMenuElement(i + 2).getPosX(), this.getMenuElement(i + 2).getPosY(), this.getMenuElement(i + 2).getWidth(), this.getMenuElement(i + 2).getHeight(), true));
                        this.setMenuElement(iID, new Button_Menu_Active(this.getMenuElement(iID).getText(), -1, this.getMenuElement(iID).getPosX(), this.getMenuElement(iID).getPosY(), this.getMenuElement(iID).getWidth(), this.getMenuElement(iID).getHeight(), true));
                        break;
                    }
                }
                CFG.chosen_AlphabetCharachter = null;
                return;
            }
        }
        int toDisable = 0;
        if (CFG.chosen_AlphabetCharachter == null) {
            toDisable = 1;
        } else {
            for (int i = 0; i < this.lCharacters.size(); ++i) {
                if (this.lCharacters.get(i).charValue() != CFG.chosen_AlphabetCharachter.charAt(0)) continue;
                toDisable = i + 2;
                break;
            }
        }
        this.setMenuElement(toDisable, new Button_Menu_Classic(this.getMenuElement(toDisable).getText(), -1, this.getMenuElement(toDisable).getPosX(), this.getMenuElement(toDisable).getPosY(), this.getMenuElement(toDisable).getWidth(), this.getMenuElement(toDisable).getHeight(), true));
        this.setMenuElement(iID, new Button_Menu_Active(this.getMenuElement(iID).getText(), -1, this.getMenuElement(iID).getPosX(), this.getMenuElement(iID).getPosY(), this.getMenuElement(iID).getWidth(), this.getMenuElement(iID).getHeight(), true));
        CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
    }
}

