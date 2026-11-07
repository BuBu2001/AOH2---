/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Continent_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.IOException;
import java.util.ArrayList;

class Menu_Continents_CreateNewPackage
extends SliderMenu {
    private String sPackageName;

    protected Menu_Continents_CreateNewPackage() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return Menu_Continents_CreateNewPackage.this.sPackageName + ": " + super.getText();
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        boolean tempClickableRemove = CFG.editor_Package_ContinentsData.getContinentsTagsSize() > 1;
        for (int i = 0; i < CFG.editor_Package_ContinentsData.getContinentsTagsSize(); ++i) {
            menuElements.add(new Button_Menu(CFG.getContinentDataName(CFG.editor_Package_ContinentsData.getContinentTag(i)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, tempClickableRemove));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sPackageName = CFG.langManager.get("PackageName");
        this.getMenuElement(0).setText(CFG.editor_Package_ContinentsData.getContinentsTagsSize() > 1 ? CFG.langManager.get("Save") : CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.editor_Package_ContinentsData.getPackageName());
        this.getMenuElement(2).setText(CFG.langManager.get("AddNewContinent"));
        this.getMenuElement(3).setClickable(false);
        this.getMenuElement(4).setClickable(false);
        this.getTitle().setText(CFG.langManager.get("CreateNewPackage"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (CFG.editor_Package_ContinentsData.getContinentsTagsSize() > 1) {
                    if (this.getMenuElement(1).getText().length() == 0) {
                        CFG.showKeyboard(1);
                        CFG.toast.setInView(this.sPackageName);
                        CFG.toast.setTimeInView(3000);
                        break;
                    }
                    CFG.editor_Package_ContinentsData.setPackageName(this.getMenuElement(1).getText());
                    CFG.game.saveContinentPackage();
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
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CONTINENTS_ADDNEWCONTINENT_TOPACKAGE);
                break;
            }
            default: {
                if (iID % 2 == 0) {
                    CFG.editor_Package_ContinentsData.removeContinentTag((iID - 3) / 2);
                    CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_CONTINENTS_PACKAGE);
                    break;
                }
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.editor_Package_ContinentsData.getContinentTag((iID - 3) / 2);
                try {
                    FileHandle file = Gdx.files.internal("map/data/continents/packges_data/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                    CFG.editor_Continent_GameData = (Continent_GameData)CFG.deserialize(file.readBytes());
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                CFG.backToMenu = Menu.eMAP_EDITOR_CREATE_CONTINENTS_PACKAGE;
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_NEW_CONTINENT);
                Game_Render_Province.updateDrawProvinces();
                CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
                CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING + CFG.menuManager.getColorPicker().getPosX());
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editor_Continent_GameData.getR(), CFG.editor_Continent_GameData.getG(), CFG.editor_Continent_GameData.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.MAP_EDITOR_CONTINENT_COLOR);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PACKAGES_CONTINENTS);
        CFG.menuManager.setBackAnimation(true);
    }
}

