/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Add;
import age.of.civilizations2.jakowski.lukasz.Button_Alliance;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ColorPicker;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ManageDiplomacy_Alliances
extends SliderMenu {
    protected Menu_ManageDiplomacy_Alliances() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Add(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CreateNewAlliance") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        int tPosX = 0;
        int tWidth = 0;
        for (int i = 1; i < CFG.game.getAlliancesSize(); ++i) {
            tWidth = CFG.BUTTON_WIDTH * 2;
            if ((CFG.CIV_FLAG_WIDTH + CFG.PADDING) * CFG.game.getAlliance(i).getCivilizationsSize() + CFG.PADDING > CFG.BUTTON_WIDTH * 2) {
                tWidth = (CFG.CIV_FLAG_WIDTH + CFG.PADDING) * CFG.game.getAlliance(i).getCivilizationsSize() + CFG.PADDING;
            }
            menuElements.add(new Button_Alliance(i, CFG.game.getAlliance(i).getAllianceName(), -1, CFG.PADDING + CFG.BUTTON_WIDTH * 2 + CFG.PADDING + tPosX, CFG.PADDING, tWidth, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true){

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getAlliance(this.getCurrent()).getAllianceName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.countAlliance_Provinces(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.countAlliance_Population(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.countAlliance_Economy(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        for (int i = 0; i < CFG.game.getAlliance(this.getCurrent()).getCivilizationsSize(); ++i) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getAlliance(this.getCurrent()).getCivilization(i)));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getAlliance(this.getCurrent()).getCivilization(i)).getCivName()));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        this.menuElementHover = null;
                    }
                }
            });
            tPosX += (((MenuElement)menuElements.get(menuElements.size() - 1)).getTextWidth() + CFG.PADDING * 2 > tWidth ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getTextWidth() + CFG.PADDING * 2 : tWidth) + CFG.PADDING;
        }
        menuElements.add(new Button_Game_ColorPicker(CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING * 3, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                CFG.drawEditorButtons_Top_Edge_R(oSB, this.getPosX() - CFG.PADDING + iTranslateX + iTranslateY, this.getPosY() - CFG.PADDING, this.getWidth() + CFG.PADDING * 2, this.getHeight() + CFG.PADDING * 2);
                if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID <= 0 && (CFG.game.getActiveProvinceID() < 0 || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() <= 0)) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
                }
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ShowHideColorPicker"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.pickeIcon, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING * 5 + CFG.BUTTON_HEIGHT, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("NewAlliance"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getWidth(), this.getMenuElement(0).getHeight() + CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.575f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), (this.getMenuElement(0).getHeight() + CFG.PADDING * 2) / 4);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.675f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1, true, false);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
            int tempProvincePosX = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCenterX() + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftX() + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTranslateProvincePosX();
            int tempButtonPosX = this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() / 2 + this.getMenuPosX() + iTranslateX;
            int tempProvincePosY = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCenterY() + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftY() + CFG.map.getMapCoordinates().getPosY();
            int tempButtonPosY = this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() / 2 + this.getMenuPosY() + iTranslateY;
            tempProvincePosX = (int)((float)tempProvincePosX * CFG.map.getMapScale().getCurrentScale());
            tempProvincePosY = (int)((float)tempProvincePosY * CFG.map.getMapScale().getCurrentScale());
            int iWidth = (int)Math.ceil(Math.sqrt((tempButtonPosX - tempProvincePosX) * (tempButtonPosX - tempProvincePosX) + (tempProvincePosY - tempButtonPosY) * (tempProvincePosY - tempButtonPosY)));
            float fAngle = (float)(Math.atan2(tempProvincePosY - tempButtonPosY, -tempProvincePosX + tempButtonPosX) * 180.0 / Math.PI);
            float tempAngle = fAngle > 90.0f ? 90.0f - fAngle % 90.0f : (fAngle < -90.0f ? -(90.0f + fAngle % 90.0f) : fAngle);
            int offsetX = -((int)((float)ImageManager.getImage(Images.line_32).getHeight() / 2.0f * (tempAngle / 90.0f)));
            int offsetY = -((int)((float)ImageManager.getImage(Images.line_32).getHeight() / 2.0f * ((90.0f - Math.abs(fAngle)) / 90.0f)));
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.45f));
            ImageManager.getImage(Images.line_32).draw(oSB, tempProvincePosX + offsetX, tempProvincePosY + offsetY, iWidth, ImageManager.getImage(Images.line_32).getHeight(), fAngle, 0);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            if (CFG.menuManager.getColorPicker().getVisible()) {
                CFG.menuManager.getColorPicker().setVisible(false, null);
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
            } else if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() > 0) {
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID();
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getR(), CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getG(), CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getB());
                CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
                CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING * 7);
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CUSTOMIZE_ALLIANCE_COLOR);
            } else {
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
            }
            return;
        }
        switch (iID) {
            case 0: {
                CFG.game.addAlliance("");
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getAlliancesSize() - 1;
                CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE);
                CFG.game.disableDrawCivilizationRegions_ActiveProvince();
                break;
            }
            default: {
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = iID;
                CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE);
                CFG.game.disableDrawCivilizationRegions_ActiveProvince();
            }
        }
        CFG.menuManager.getColorPicker().setVisible(false, null);
    }
}

