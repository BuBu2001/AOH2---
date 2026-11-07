/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Package_RegionsData;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_Packages_Regions
extends SliderMenu {
    private List<String> lTags = new ArrayList<String>();
    private List<String> lRegions = new ArrayList<String>();

    protected Menu_Packages_Regions() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        try {
            List<String> tempTags = CFG.getFileNames("map/data/regions/packges/");
            for (int i = 0; i < tempTags.size(); ++i) {
                menuElements.add(new Button_Menu(CFG.getPackageRegionDataName(tempTags.get(i)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
                this.lTags.add(tempTags.get(i));
                this.lRegions.add(CFG.getPackageRegionsData_AllNames(tempTags.get(i)));
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
        this.getMenuElement(1).setText(CFG.langManager.get("CreateNewPackage"));
        this.getTitle().setText(CFG.langManager.get("RegionsPackages"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        for (int i = 2; i < this.getMenuElementsSize(); ++i) {
            if (!this.getMenuElement(i).getIsInView()) continue;
            CFG.fontMain.getData().setScale(0.6f);
            CFG.drawText(oSB, this.lRegions.get(i - 2), this.getMenuElement(i).getTextPos() + this.getMenuElement(i).getTextWidth() + CFG.PADDING + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuElement(i).getHeight() / 2 + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.6f) + this.getMenuPosY() + iTranslateY, CFG.COLOR_BUTTON_EXTRA_DESCRIPTION);
            CFG.fontMain.getData().setScale(1.0f);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.editor_Package_RegionsData = new Package_RegionsData();
                CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_REGIONS_PACKAGE);
                break;
            }
            default: {
                CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG = this.lTags.get(iID - 2);
                try {
                    FileHandle file = Gdx.files.internal("map/data/regions/packges/" + CFG.CREATE_PACKAGE_CONTINENT_GAME_DATA_TAG);
                    CFG.editor_Package_RegionsData = (Package_RegionsData)CFG.deserialize(file.readBytes());
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CREATE_REGIONS_PACKAGE);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR);
        CFG.menuManager.setBackAnimation(true);
        CFG.map.getMapRegions().loadRegions(CFG.map.getMapRegionsPackageTag(CFG.map.getActiveMapID()));
    }
}

