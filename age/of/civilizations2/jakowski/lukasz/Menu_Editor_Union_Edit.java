/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import java.util.ArrayList;

class Menu_Editor_Union_Edit
extends SliderMenu {
    protected Menu_Editor_Union_Edit() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(CFG.langManager.get("SelectCivilization") + ": " + CFG.langManager.getCiv(CFG.unionsManager.createUnion_Data.lCreateCivTag), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(CFG.langManager.get("AddCivilization"), -1, 0, CFG.PADDING * 2 + CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 0; i < CFG.unionsManager.createUnion_Data.lCivsTags.size(); ++i) {
            menuElements.add(new Button_Menu(CFG.langManager.get("Civilization") + ": " + CFG.langManager.getCiv(CFG.unionsManager.createUnion_Data.lCivsTags.get(i)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING * (i + 3) + CFG.BUTTON_HEIGHT * (i + 2), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH / 2, CFG.PADDING * (i + 3) + CFG.BUTTON_HEIGHT * (i + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        }
        menuElements.add(new Button_Menu_LR_Line(null, -1, CFG.GAME_WIDTH / 2, CFG.PADDING, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.getMenuElement(this.getMenuElementsSize() - 1).setPosY(this.getMenuElement(0).getPosY());
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Save"));
        this.getTitle().setText(CFG.langManager.get("Union"));
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID < 0) {
                if (CFG.unionsManager.createUnion_Data.lCreateCivTag.length() > 0 && CFG.unionsManager.createUnion_Data.lCivsTags.size() > 1) {
                    CFG.unionsManager.unions.lUnions.add(CFG.unionsManager.createUnion_Data);
                }
            } else if (CFG.unionsManager.createUnion_Data.lCreateCivTag.length() > 0 && CFG.unionsManager.createUnion_Data.lCivsTags.size() > 1) {
                CFG.unionsManager.unions.lUnions.set(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, CFG.unionsManager.createUnion_Data);
            }
            CFG.unionsManager.saveUnions();
            this.onBackPressed();
            return;
        }
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -2;
                CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS_ADDCIV);
                return;
            }
            case 2: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS_ADDCIV);
                return;
            }
        }
        if ((iID -= 3) % 2 == 0) {
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = iID / 2;
            CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS_ADDCIV);
        } else {
            try {
                CFG.unionsManager.createUnion_Data.lCivsTags.remove(iID / 2);
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
            CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS_EDIT);
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS);
        CFG.menuManager.setBackAnimation(true);
    }
}

