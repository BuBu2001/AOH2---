/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Add;
import age.of.civilizations2.jakowski.lukasz.Button_Add_V;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ManageDiplomacy_Vassals
extends SliderMenu {
    protected Menu_ManageDiplomacy_Vassals() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Add("", -1, CFG.PADDING, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0) {
                    CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getIdeologyID()).getiCrownImage().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getIdeologyID()).getiCrownImage().getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getIdeologyID()).getiCrownImage().getHeight() * 4 / 5 + iTranslateY);
                    CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getFlag().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                } else {
                    CFG.ideologiesManager.getIdeology(0).getiCrownImage().draw(oSB, this.getPosX() + this.getWidth() / 2 - CFG.ideologiesManager.getIdeology(0).getiCrownImage().getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.ideologiesManager.getIdeology(0).getiCrownImage().getHeight() / 2 + iTranslateY);
                }
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Lord"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Add("", -1, CFG.PADDING * 2 + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                    CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getIdeologyID()).getiCrownVassalImage().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getIdeologyID()).getiCrownVassalImage().getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getIdeologyID()).getiCrownImage().getHeight() * 4 / 5 - (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getIdeologyID()).getiCrownVassalImage().getHeight() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getIdeologyID()).getiCrownImage().getHeight()) + iTranslateY);
                    CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getFlag().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                } else {
                    CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().draw(oSB, this.getPosX() + this.getWidth() / 2 - CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getHeight() / 2 + iTranslateY);
                }
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassal"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Add_V("", -1, CFG.PADDING * 3 + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3 * 2, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, false));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getWidth(), this.getMenuElement(0).getHeight() + CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.575f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), (this.getMenuElement(0).getHeight() + CFG.PADDING * 2) / 4);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0425f, 0.0475f, 0.06f, 0.7f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
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
        switch (iID) {
            case 2: {
                if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 <= 0 || CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 <= 0) break;
                CFG.game.setVassal_OfCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
                ArrayList<String> tempMess = new ArrayList<String>();
                ArrayList<Color> tempMessColors = new ArrayList<Color>();
                tempMess.add(CFG.langManager.get("Lord") + ": " + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCivName());
                tempMessColors.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                tempMess.add(CFG.langManager.get("Vassal") + ": " + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getCivName());
                tempMessColors.add(Color.WHITE);
                CFG.toast.setInView(tempMess, tempMessColors);
                CFG.toast.setTimeInView(4500);
                CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCapitalProvinceID());
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_Vassals();
            }
        }
    }
}

