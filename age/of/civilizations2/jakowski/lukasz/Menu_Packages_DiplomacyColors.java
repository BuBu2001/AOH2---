/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic_ReflectedCheckbox;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.List;

class Menu_Packages_DiplomacyColors
extends SliderMenu {
    private List<String> lTags = new ArrayList<String>();

    protected Menu_Packages_DiplomacyColors() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        FileHandle tempFileT = Gdx.files.internal("game/diplomacy_colors/packages/Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        for (int i = 0; i < tagsSPLITED.length; ++i) {
            menuElements.add(new Button_Menu(CFG.getPackageDiplomacyColorsDataName(tagsSPLITED[i]), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_Classic_ReflectedCheckbox(tagsSPLITED[i].equals(CFG.sACTIVE_DIPLOMACY_COLORS_TAG) ? CFG.langManager.get("Active") : CFG.langManager.get("Enable"), (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true, tagsSPLITED[i].equals(CFG.sACTIVE_DIPLOMACY_COLORS_TAG)));
            this.lTags.add(tagsSPLITED[i]);
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("CreateNewPackage"));
        this.getTitle().setText(CFG.langManager.get("DiplomacyColorsPackages"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.initEditdiplomacyColors_GameData();
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_DIPLOMACY_COLORS_PACKAGES_CREATE);
                Game_Render_Province.updateDrawProvinces();
                CFG.menuManager.getColorPicker().setPosX(CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 5);
                CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.COLOR_DIPLOMACY_OWN_PROVINCES);
                break;
            }
            default: {
                if (iID % 2 == 0) {
                    CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG = this.lTags.get((iID - 2) / 2);
                    CFG.loadDiplomacyColors_GameData(CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG);
                    CFG.menuManager.setViewID(Menu.eGAME_EDITOR_DIPLOMACY_COLORS_PACKAGES_CREATE);
                    Game_Render_Province.updateDrawProvinces();
                    CFG.menuManager.getColorPicker().setPosX(CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 5);
                    CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(), CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB());
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.COLOR_DIPLOMACY_OWN_PROVINCES);
                    break;
                }
                if (CFG.sACTIVE_DIPLOMACY_COLORS_TAG.equals(this.lTags.get((iID - 2) / 2))) break;
                CFG.sACTIVE_DIPLOMACY_COLORS_TAG = this.lTags.get((iID - 2) / 2);
                FileHandle fileSave = Gdx.files.local("game/diplomacy_colors/Age_of_Civilizations_Active");
                fileSave.writeString(CFG.sACTIVE_DIPLOMACY_COLORS_TAG, false);
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_DIPLOMACY_COLORS_PACKAGES);
                CFG.toast.setInView(CFG.langManager.get("Enabled"));
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR);
        CFG.menuManager.setBackAnimation(true);
        CFG.loadDiplomacyColors_GameData(CFG.sACTIVE_DIPLOMACY_COLORS_TAG);
    }
}

