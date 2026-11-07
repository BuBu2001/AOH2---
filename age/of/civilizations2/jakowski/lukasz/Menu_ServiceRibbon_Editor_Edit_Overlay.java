/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ColorPicker;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ServiceRibbon_Editor_Edit_Overlay
extends SliderMenu {
    protected Menu_ServiceRibbon_Editor_Edit_Overlay() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game_ColorPicker(CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Menu_Classic("-", -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("", -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("-", -1, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("", -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic(null, -1, 0, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 5, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getReflected();
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
        this.getMenuElement(3).setText(CFG.langManager.get("Position") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
        this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
        this.getMenuElement(8).setText(CFG.langManager.get("Reflected"));
        CFG.glyphLayout.setText(CFG.fontMain, "" + CFG.SERVICE_RIBBON_WIDTH);
        CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = (int)CFG.glyphLayout.width;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.serviceRibbon_Manager.drawSR(oSB, CFG.GAME_WIDTH / 2 - CFG.SERVICE_RIBBON_WIDTH * 2 - CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING - CFG.SERVICE_RIBBON_HEIGHT, CFG.editorServiceRibbon_GameData, CFG.editorServiceRibbon_Colors, 2);
        CFG.serviceRibbon_Manager.drawSR(oSB, CFG.GAME_WIDTH / 2 + CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING - CFG.SERVICE_RIBBON_HEIGHT / 2, CFG.editorServiceRibbon_GameData, CFG.editorServiceRibbon_Colors, 1);
        CFG.serviceRibbon_Manager.drawSROver(oSB, CFG.GAME_WIDTH / 2 + CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING - CFG.SERVICE_RIBBON_HEIGHT / 2, 1);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, CFG.GAME_WIDTH / 2 + CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + CFG.SERVICE_RIBBON_HEIGHT / 2 + CFG.CIV_COLOR_WIDTH - ImageManager.getImage(Images.line_32_off1).getHeight(), CFG.SERVICE_RIBBON_WIDTH, 1);
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawText(oSB, "" + CFG.SERVICE_RIBBON_WIDTH, CFG.GAME_WIDTH / 2 + CFG.PADDING + (int)(((float)CFG.SERVICE_RIBBON_WIDTH - (float)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID * 0.6f) / 2.0f) + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + CFG.SERVICE_RIBBON_HEIGHT / 2 + CFG.CIV_COLOR_WIDTH + CFG.PADDING, Color.WHITE);
        CFG.fontMain.getData().setScale(1.0f);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (CFG.menuManager.getColorPicker().getVisible()) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).r, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).g, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).b);
                    CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
                    CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 9);
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_SERVICE_RIBBON_OVERLAY);
                }
                return;
            }
            case 1: {
                this.onBackPressed();
                return;
            }
            case 2: {
                CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setPosX(CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() - 1);
                if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() < 0) {
                    CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setPosX(0);
                    this.getMenuElement(3).setText(CFG.langManager.get("Position") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                }
                this.getMenuElement(3).setText(CFG.langManager.get("Position") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                return;
            }
            case 3: {
                return;
            }
            case 4: {
                CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setPosX(CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() + 1);
                if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getReflected()) {
                    if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() > CFG.SERVICE_RIBBON_WIDTH / 2) {
                        if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() >= CFG.SERVICE_RIBBON_WIDTH / 2) {
                            CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setPosX(CFG.SERVICE_RIBBON_WIDTH / 2 - 1);
                            CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(1);
                            this.getMenuElement(3).setText(CFG.langManager.get("Position") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                            this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                        } else {
                            CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.SERVICE_RIBBON_WIDTH / 2 - CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                            this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                        }
                    }
                } else if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() >= CFG.SERVICE_RIBBON_WIDTH) {
                    if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() >= CFG.SERVICE_RIBBON_WIDTH - 1) {
                        CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setPosX(CFG.SERVICE_RIBBON_WIDTH - 1);
                        CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(1);
                        this.getMenuElement(3).setText(CFG.langManager.get("Position") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                        this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                    } else {
                        CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.SERVICE_RIBBON_WIDTH - CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                        this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                    }
                }
                this.getMenuElement(3).setText(CFG.langManager.get("Position") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                return;
            }
            case 5: {
                CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() - 1);
                if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() <= 0) {
                    CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(1);
                }
                this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                return;
            }
            case 7: {
                CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() + 1);
                if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getReflected()) {
                    if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() >= CFG.SERVICE_RIBBON_WIDTH / 2) {
                        CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.SERVICE_RIBBON_WIDTH / 2 - CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                    }
                } else if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() >= CFG.SERVICE_RIBBON_WIDTH) {
                    CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.SERVICE_RIBBON_WIDTH - CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                }
                this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                return;
            }
            case 8: {
                CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setReflected(!CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getReflected());
                if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getReflected() && CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() > CFG.SERVICE_RIBBON_WIDTH / 2) {
                    if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX() > CFG.SERVICE_RIBBON_WIDTH / 2) {
                        CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setPosX(0);
                        if (CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth() > CFG.SERVICE_RIBBON_WIDTH / 2) {
                            CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.SERVICE_RIBBON_WIDTH / 2 - 1);
                            this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                        }
                    } else {
                        CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).setWidth(CFG.SERVICE_RIBBON_WIDTH / 2 - CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getPosX());
                        this.getMenuElement(6).setText(CFG.langManager.get("Width") + ": " + CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getWidth());
                    }
                }
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON_EDIT);
        CFG.menuManager.setBackAnimation(true);
    }
}

