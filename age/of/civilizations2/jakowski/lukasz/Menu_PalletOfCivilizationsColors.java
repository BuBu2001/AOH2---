/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ColorPicker;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.PalletOfCivsColors_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;

class Menu_PalletOfCivilizationsColors
extends SliderMenu {
    protected Menu_PalletOfCivilizationsColors() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Menu("", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : (this.getClickable() ? new Color(1.0f, 1.0f, 1.0f, 1.0f) : new Color(0.84f, 0.84f, 0.84f, 0.7f));
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game_ColorPicker(CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (CFG.game.getActiveProvinceID() < 0) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
                }
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, true));
        CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
        CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("Scenario") + ": " + CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())));
        this.getMenuElement(2).setText(CFG.langManager.get("Save"));
        this.getMenuElement(4).setText(CFG.langManager.get("Colors"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R(oSB, this.getMenuElement(3).getPosX() - CFG.PADDING + iTranslateX + iTranslateY, this.getMenuElement(3).getPosY() - CFG.PADDING, this.getMenuElement(4).getPosX() + this.getMenuElement(4).getWidth() + CFG.PADDING, this.getMenuElement(3).getHeight() + CFG.PADDING * 2);
        if (CFG.game.getActiveProvinceID() >= 0) {
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getFlag().draw(oSB, this.getMenuElement(1).getPosX() + this.getMenuElement(1).getWidth() / 2 - this.getMenuElement(1).getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getMenuPosY() + this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(1).getPosX() + this.getMenuElement(1).getWidth() / 2 - this.getMenuElement(1).getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getMenuPosY() + this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.menuManager.getColorPicker().setVisible(false, null);
                CFG.menuManager.setViewID(Menu.eCHOOSE_SCENARIO);
                CFG.backToMenu = Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES_EDIT;
                CFG.goToMenu = Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES_EDIT;
                return;
            }
            case 2: {
                CFG.menuManager.getColorPicker().setVisible(false, null);
                CFG.editorPalletOfCivsColors_Data.saveData();
                CFG.palletManager.updatePalletsOfCivsColorsTags();
                this.onBackPressed();
                return;
            }
            case 3: {
                if (CFG.menuManager.getColorPicker().getVisible() || CFG.game.getActiveProvinceID() < 0) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PALLET_OF_COLORS);
                }
                return;
            }
            case 4: {
                if (this.getMenuElement(iID).getCheckboxState()) {
                    CFG.editorPalletOfCivsColors_Data.saveData();
                    for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                        FileHandle file = null;
                        try {
                            file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
                            try {
                                PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                                CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0f));
                                CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0f));
                                CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0f));
                            }
                            catch (ClassNotFoundException e) {
                                CFG.game.getCiv(i).setR(0);
                                CFG.game.getCiv(i).setG(1);
                                CFG.game.getCiv(i).setB(2);
                            }
                            catch (IOException e) {
                                CFG.game.getCiv(i).setR(0);
                                CFG.game.getCiv(i).setG(1);
                                CFG.game.getCiv(i).setB(2);
                            }
                            continue;
                        }
                        catch (GdxRuntimeException ex) {
                            CFG.game.getCiv(i).setR(0);
                            CFG.game.getCiv(i).setG(1);
                            CFG.game.getCiv(i).setB(2);
                        }
                    }
                    this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
                    break;
                }
                CFG.editorPalletOfCivsColors_Data.saveData();
                for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                    FileHandle file = null;
                    try {
                        file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
                        try {
                            PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                            CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0f));
                            CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0f));
                            CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0f));
                        }
                        catch (ClassNotFoundException e) {
                            CFG.palletManager.loadCivilizationStandardColor(i);
                        }
                        catch (IOException e) {
                            CFG.palletManager.loadCivilizationStandardColor(i);
                        }
                        continue;
                    }
                    catch (GdxRuntimeException ex) {
                        CFG.palletManager.loadCivilizationStandardColor(i);
                    }
                }
                this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES);
        CFG.menuManager.setBackAnimation(true);
        CFG.menuManager.getColorPicker().setVisible(false, null);
    }
}

