/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_Regions_AddNewRegionToPackage
extends SliderMenu {
    private List<String> lTags = new ArrayList<String>();
    private List<Color> lColors = new ArrayList<Color>();

    protected Menu_Regions_AddNewRegionToPackage() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        try {
            FileHandle tempFileT = Gdx.files.internal("map/data/regions/packges_data/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            int tempAdded = 0;
            for (int i = 0; i < tagsSPLITED.length; ++i) {
                if (this.getIsInPackage(tagsSPLITED[i])) continue;
                menuElements.add(new Button_Menu(CFG.getRegionDataName(tagsSPLITED[i]), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (tempAdded + 1) + CFG.PADDING * (tempAdded + 2), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
                menuElements.add(new Button_Menu_ReflectedBG(CFG.langManager.get("Edit"), -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * (tempAdded + 1) + CFG.PADDING * (tempAdded + 2), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
                this.lTags.add(tagsSPLITED[i]);
                ++tempAdded;
                this.lColors.add(CFG.getRegionDataColor(tagsSPLITED[i]));
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("CreateNewRegion"));
        this.getTitle().setText(CFG.langManager.get("AddNewRegion"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.editor_Region_GameData = new Region_GameData();
                Color tempColor = CFG.getRandomColor();
                CFG.editor_Region_GameData.setR(tempColor.r);
                CFG.editor_Region_GameData.setG(tempColor.g);
                CFG.editor_Region_GameData.setB(tempColor.b);
                this.setView_MAP_EDITOR_CREATE_NEW_REGION();
                break;
            }
            default: {
                if (iID % 2 == 0) {
                    CFG.editor_Package_RegionsData.addRegionTag(this.lTags.get((iID - 2) / 2));
                    this.onBackPressed();
                    break;
                }
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lTags.get((iID - 2) / 2);
                try {
                    FileHandle file = Gdx.files.internal("map/data/regions/packges_data/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                    CFG.editor_Region_GameData = (Region_GameData)CFG.deserialize(file.readBytes());
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                this.setView_MAP_EDITOR_CREATE_NEW_REGION();
            }
        }
    }

    private final void setView_MAP_EDITOR_CREATE_NEW_REGION() {
        CFG.backToMenu = Menu.eMAP_EDITOR_REGIONS_ADDNEWREGION_TOPACKAGE;
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_NEW_REGION);
        Game_Render_Province.updateDrawProvinces();
        CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
        CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING + CFG.menuManager.getColorPicker().getPosX());
        CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editor_Region_GameData.getR(), CFG.editor_Region_GameData.getG(), CFG.editor_Region_GameData.getB());
        CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.MAP_EDITOR_REGION_COLOR);
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_REGIONS_PACKAGE);
        CFG.menuManager.setBackAnimation(true);
    }

    private final boolean getIsInPackage(String nTag) {
        for (int i = 0; i < CFG.editor_Package_RegionsData.getRegionsTagsSize(); ++i) {
            if (!nTag.equals(CFG.editor_Package_RegionsData.getRegionTag(i))) continue;
            return true;
        }
        return false;
    }
}

