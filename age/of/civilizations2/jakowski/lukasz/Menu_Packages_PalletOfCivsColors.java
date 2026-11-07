/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.PalletOfCivsColors_Data;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Packages_PalletOfCivsColors
extends SliderMenu {
    protected Menu_Packages_PalletOfCivsColors() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 0; i < CFG.palletManager.getNumOfPallets(); ++i) {
            menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){
                int iCurrent;
                {
                    this.iCurrent = 0;
                }

                @Override
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    CFG.palletManager.drawSampleColors(oSB, this.getPosX() + this.getWidth() / 2 - CFG.BUTTON_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getHeight() / 4 + Menu_Packages_PalletOfCivsColors.this.getMenuPosY(), CFG.BUTTON_WIDTH * 2, this.getHeight() / 2, this.iCurrent, isActive);
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("CreateNewPackage"));
        this.getTitle().setText(CFG.langManager.get("PalletCivColorsPackages"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.palletManager.loadCivilizationsPaletteOfColors(CFG.palletManager.getActivePalletID());
                CFG.editorPalletOfCivsColors_Data = new PalletOfCivsColors_Data();
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES_EDIT);
                break;
            }
            default: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.palletManager.getPalletTag(iID - 2);
                if (CFG.editorPalletOfCivsColors_Data == null) {
                    CFG.editorPalletOfCivsColors_Data = new PalletOfCivsColors_Data();
                }
                CFG.editorPalletOfCivsColors_Data.readData(CFG.palletManager.getIsInternal(iID - 2));
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES_EDIT);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.palletManager.loadCivilizationsPaletteOfColors(CFG.palletManager.getActivePalletID());
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR);
        CFG.menuManager.setBackAnimation(true);
    }
}

