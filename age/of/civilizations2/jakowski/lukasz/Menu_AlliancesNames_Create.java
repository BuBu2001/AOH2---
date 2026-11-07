/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_AlliancesNames_Create
extends SliderMenu {
    private String sPackageName;
    private long lTime = 0L;

    protected Menu_AlliancesNames_Create() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return Menu_AlliancesNames_Create.this.sPackageName + ": " + super.getText();
            }
        });
        menuElements.add(new Button_Menu(null, -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 0; i < CFG.editorAlliancesNames_GameData.getSize(); ++i) {
            menuElements.add(new Button_Menu(CFG.getAlliances_Random_Names_All_BundleID(CFG.editorAlliancesNames_GameData, i), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

                @Override
                protected Color getColor(boolean isActive) {
                    return isActive ? new Color(0.1f, 0.1f, 0.1f, 1.0f) : (this.getClickable() ? CFG.COLOR_BUTTON_EXTRA_DESCRIPTION : new Color(0.78f, 0.78f, 0.78f, 0.7f));
                }
            });
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, i > 0));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sPackageName = CFG.langManager.get("PackageName");
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(1).setText(CFG.editorAlliancesNames_GameData.getPackageName());
        this.getMenuElement(2).setText(CFG.langManager.get("CreateNewBundleOfWords"));
        this.getTitle().setText(CFG.langManager.get("CreateNewPackage"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (!CFG.toast.getInView() && this.lTime + 2000L + 725L < System.currentTimeMillis()) {
            String tempText = CFG.getRandomAllianceName(CFG.editorAlliancesNames_GameData);
            if (!tempText.equals("")) {
                CFG.toast.setInView(tempText);
                this.lTime = System.currentTimeMillis();
            } else {
                this.lTime = System.currentTimeMillis() * 2L;
            }
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (this.getMenuElementsSize() > 3 && !this.getMenuElement(3).getText().equals("")) {
                    if (this.getMenuElement(1).getText().length() == 0) {
                        CFG.showKeyboard(1);
                        CFG.toast.setInView(this.sPackageName);
                        CFG.toast.setTimeInView(3000);
                        break;
                    }
                    CFG.editorAlliancesNames_GameData.setPackageName(this.getMenuElement(1).getText());
                    CFG.game.saveAlliancesNamesPackage();
                    this.onBackPressed();
                    break;
                }
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.showKeyboard();
                break;
            }
            case 2: {
                CFG.editorAlliancesNames_GameData.addBundle("");
                CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID = CFG.editorAlliancesNames_GameData.getSize() - 1;
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE_BUNDLE);
                CFG.toast.setInView(false);
                break;
            }
            default: {
                if (iID % 2 == 0) {
                    CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID = (iID - 3) / 2;
                    CFG.setDialogType(Dialog.REMOVE_RANDOM_ALLIANCES_NAMES_BUNDLE);
                    break;
                }
                CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID = (iID - 3) / 2;
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE_CREATE_BUNDLE);
                CFG.toast.setInView(false);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE);
        CFG.menuManager.setBackAnimation(true);
    }

    @Override
    protected void setVisible(boolean visible) {
        this.lTime = 0L;
        super.setVisible(visible);
    }
}

