/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.ServiceRibbon_Overlay_GameData;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ServiceRibbon_Editor_Edit
extends SliderMenu {
    protected Menu_ServiceRibbon_Editor_Edit() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, false){

            @Override
            protected boolean getClickable() {
                return CFG.editorServiceRibbon_GameData.getSize() > 1;
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING * 3 + CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("", 0, 0, CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 2, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.85f));
                }
                ImageManager.getImage(Images.pickeIcon).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickeIcon).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickeIcon).getHeight() / 2 + Menu_ServiceRibbon_Editor_Edit.this.getMenuPosY() + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new Button_Menu_Classic("", 0, CFG.BUTTON_WIDTH, CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 2, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 1; i < CFG.editorServiceRibbon_GameData.getSize(); ++i) {
            menuElements.add(new Button_Menu_Classic("", 0, 0, CFG.PADDING * (i + 4) + CFG.BUTTON_HEIGHT * (i + 2), CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                    ImageManager.getImage(Images.pickeIcon).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickeIcon).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickeIcon).getHeight() / 2 + Menu_ServiceRibbon_Editor_Edit.this.getMenuPosY() + iTranslateY);
                }
            });
            menuElements.add(new Button_Menu_Classic("", 0, CFG.BUTTON_WIDTH, CFG.PADDING * (i + 4) + CFG.BUTTON_HEIGHT * (i + 2), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.PADDING * (i + 4) + CFG.BUTTON_HEIGHT * (i + 2), CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
        }
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
        this.getMenuElement(2).setText(CFG.langManager.get("AddNewOverlay"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.serviceRibbon_Manager.drawSR(oSB, CFG.GAME_WIDTH / 2 - CFG.SERVICE_RIBBON_WIDTH / 2 + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING - CFG.SERVICE_RIBBON_HEIGHT / 2, CFG.editorServiceRibbon_GameData, CFG.editorServiceRibbon_Colors, 1);
        CFG.serviceRibbon_Manager.drawSROver(oSB, CFG.GAME_WIDTH / 2 - CFG.SERVICE_RIBBON_WIDTH / 2 + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING - CFG.SERVICE_RIBBON_HEIGHT / 2, 1);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.serviceRibbon_Manager.drawSROverlay(oSB, CFG.GAME_WIDTH / 2 - CFG.SERVICE_RIBBON_WIDTH + iTranslateX, this.getMenuElement(4).getPosY() + this.getMenuElement(4).getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT, CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(0), CFG.editorServiceRibbon_Colors.get(0), 2);
        for (int i = 1; i < CFG.editorServiceRibbon_GameData.getSize(); ++i) {
            CFG.serviceRibbon_Manager.drawSROverlay(oSB, CFG.GAME_WIDTH / 2 - CFG.SERVICE_RIBBON_WIDTH + iTranslateX, this.getMenuElement(4).getPosY() + this.getMenuElement(4).getHeight() / 2 + CFG.BUTTON_HEIGHT * i + CFG.PADDING * i - CFG.SERVICE_RIBBON_HEIGHT, CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(0), CFG.editorServiceRibbon_Colors.get(0), 2);
            CFG.serviceRibbon_Manager.drawSROverlay(oSB, CFG.GAME_WIDTH / 2 - CFG.SERVICE_RIBBON_WIDTH + iTranslateX, this.getMenuElement(4).getPosY() + this.getMenuElement(4).getHeight() / 2 + CFG.BUTTON_HEIGHT * i + CFG.PADDING * i - CFG.SERVICE_RIBBON_HEIGHT, CFG.editorServiceRibbon_GameData.getServiceRibbon_Overlay(i), CFG.editorServiceRibbon_Colors.get(i), 2);
        }
        oSB.setColor(Color.WHITE);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.serviceRibbon_Manager.saveData();
                CFG.serviceRibbon_Manager.loadSR();
                this.onBackPressed();
                return;
            }
            case 2: {
                CFG.menuManager.getColorPicker().setVisible(false, null);
                CFG.editorServiceRibbon_GameData.addServiceRibbonOverlay(new ServiceRibbon_Overlay_GameData(0, 1, true));
                CFG.editorServiceRibbon_Colors_Add();
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.editorServiceRibbon_GameData.getSize() - 1;
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON_EDIT_OVERLAY);
                return;
            }
            case 3: {
                if (CFG.menuManager.getColorPicker().getVisible()) {
                    if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == 0) {
                        CFG.menuManager.getColorPicker().setVisible(false, null);
                    } else {
                        CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 0;
                        CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).r, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).g, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).b);
                    }
                } else {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = 0;
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).r, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).g, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).b);
                    CFG.menuManager.getColorPicker().setPosX(CFG.BUTTON_WIDTH + CFG.PADDING * 3);
                    CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_SERVICE_RIBBON_OVERLAY);
                }
                return;
            }
            case 4: {
                return;
            }
        }
        if (iID % 3 == 1) {
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.editorServiceRibbon_GameData.removeServiceRibbon_Overlay((iID - 5) / 3 + 1);
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON_EDIT);
        } else if (iID % 3 == 2) {
            if (CFG.menuManager.getColorPicker().getVisible()) {
                if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == (iID - 5) / 3 + 1) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = (iID - 5) / 3 + 1;
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).r, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).g, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).b);
                }
            } else {
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = (iID - 5) / 3 + 1;
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).r, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).g, CFG.editorServiceRibbon_Colors.get((int)CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).b);
                CFG.menuManager.getColorPicker().setPosX(CFG.BUTTON_WIDTH + CFG.PADDING * 3);
                CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_SERVICE_RIBBON_OVERLAY);
            }
        } else {
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = (iID - 5) / 3 + 1;
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON_EDIT_OVERLAY);
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON);
        CFG.menuManager.setBackAnimation(true);
    }
}

