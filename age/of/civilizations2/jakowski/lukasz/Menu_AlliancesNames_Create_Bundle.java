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

class Menu_AlliancesNames_Create_Bundle
extends SliderMenu {
    private String sWord;

    protected Menu_AlliancesNames_Create_Bundle() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 0; i < CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWordsSize(); ++i) {
            menuElements.add(new Button_Menu(CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWord(i), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

                @Override
                protected String getTextToDraw() {
                    return Menu_AlliancesNames_Create_Bundle.this.sWord + ": " + super.getText();
                }
            });
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, i > 0));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sWord = CFG.langManager.get("Word");
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(1).setText(CFG.langManager.get("AddNewWord"));
        this.getTitle().setText(CFG.langManager.get("CreateNewBundleOfWords"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).addWord("");
                this.updateBundle();
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE_BUNDLE);
                CFG.showKeyboard(0, CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWordsSize() * 2);
                CFG.toast.setInView(this.sWord);
                break;
            }
            default: {
                if (iID % 2 == 0) {
                    CFG.showKeyboard();
                    break;
                }
                for (int i = 0; i * 2 + 2 < this.getMenuElementsSize() && i < CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWordsSize(); ++i) {
                    CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).setWord(i, this.getMenuElement(i * 2 + 2).getText());
                }
                CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).removeWord((iID - 2) / 2);
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE_BUNDLE);
            }
        }
    }

    private final void updateBundle() {
        for (int i = 0; i * 2 + 2 < this.getMenuElementsSize() && i < CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWordsSize(); ++i) {
            CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).setWord(i, this.getMenuElement(i * 2 + 2).getText());
        }
    }

    @Override
    protected final void onBackPressed() {
        this.updateBundle();
        for (int i = 0; i < CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWordsSize(); ++i) {
            if (!CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWord(i).equals("")) continue;
            CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).removeWord(i);
            --i;
        }
        if (CFG.editorAlliancesNames_GameData.getBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).getWordsSize() == 0) {
            CFG.editorAlliancesNames_GameData.removeBundle(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID);
        }
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE);
        CFG.menuManager.setBackAnimation(true);
    }
}

