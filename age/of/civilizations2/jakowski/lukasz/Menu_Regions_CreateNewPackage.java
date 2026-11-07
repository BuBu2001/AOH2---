/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Region_GameData;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.IOException;
import java.util.ArrayList;

class Menu_Regions_CreateNewPackage
extends SliderMenu {
    private String sPackageName;

    protected Menu_Regions_CreateNewPackage() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return Menu_Regions_CreateNewPackage.this.sPackageName + ": " + super.getText();
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        boolean tempClickableRemove = CFG.editor_Package_RegionsData.getRegionsTagsSize() > 1;
        for (int i = 0; i < CFG.editor_Package_RegionsData.getRegionsTagsSize(); ++i) {
            menuElements.add(new Button_Menu(CFG.getRegionDataName(CFG.editor_Package_RegionsData.getRegionTag(i)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, tempClickableRemove));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sPackageName = CFG.langManager.get("PackageName");
        this.getMenuElement(0).setText(CFG.editor_Package_RegionsData.getRegionsTagsSize() > 1 ? CFG.langManager.get("Save") : CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.editor_Package_RegionsData.getPackageName());
        this.getMenuElement(2).setText(CFG.langManager.get("AddNewRegion"));
        this.getTitle().setText(CFG.langManager.get("CreateNewPackage"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (CFG.editor_Package_RegionsData.getRegionsTagsSize() > 1) {
                    if (this.getMenuElement(1).getText().length() == 0) {
                        CFG.showKeyboard(1);
                        CFG.toast.setInView(this.sPackageName);
                        CFG.toast.setTimeInView(3000);
                        break;
                    }
                    CFG.editor_Package_RegionsData.setPackageName(this.getMenuElement(1).getText());
                    CFG.game.saveRegionPackage();
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
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_REGIONS_ADDNEWREGION_TOPACKAGE);
                break;
            }
            default: {
                if (iID % 2 == 0) {
                    CFG.editor_Package_RegionsData.removeRegionTag((iID - 3) / 2);
                    CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_REGIONS_PACKAGE);
                    break;
                }
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.editor_Package_RegionsData.getRegionTag((iID - 3) / 2);
                try {
                    FileHandle file = Gdx.files.internal("map/data/regions/packges_data/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                    CFG.editor_Region_GameData = (Region_GameData)CFG.deserialize(file.readBytes());
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                CFG.backToMenu = Menu.eMAP_EDITOR_CREATE_REGIONS_PACKAGE;
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_NEW_REGION);
                Game_Render_Province.updateDrawProvinces();
                CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
                CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING + CFG.menuManager.getColorPicker().getPosX());
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.MAP_EDITOR_REGION_COLOR);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PACKAGES_REGIONS);
        CFG.menuManager.setBackAnimation(true);
    }
}

