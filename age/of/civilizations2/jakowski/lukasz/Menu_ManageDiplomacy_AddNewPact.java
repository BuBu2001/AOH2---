/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Add;
import age.of.civilizations2.jakowski.lukasz.Button_Add_V;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ManageDiplomacy_AddNewPact
extends SliderMenu {
    protected Menu_ManageDiplomacy_AddNewPact() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Add("", -1, CFG.PADDING, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0) {
                    CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                }
            }
        });
        menuElements.add(new Button_Add("", -1, CFG.PADDING * 2 + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                    CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                }
            }
        });
        menuElements.add(new Button_Add_V("", -1, CFG.PADDING * 3 + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3 * 2, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, false));
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getTitle().setText(CFG.langManager.get("AddNewPact"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight(), this.getWidth(), this.getMenuElement(1).getHeight() + CFG.PADDING * 2, false, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
            int tempProvincePosX = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCenterX() + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftX() + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTranslateProvincePosX();
            int tempButtonPosX = this.getMenuElement(1).getPosX() + this.getMenuElement(1).getWidth() / 2 + this.getMenuPosX() + iTranslateX;
            int tempProvincePosY = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCenterY() + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftY() + CFG.map.getMapCoordinates().getPosY();
            int tempButtonPosY = this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 + this.getMenuPosY() + iTranslateY;
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
            case 0: {
                this.onBackPressed();
                break;
            }
            case 3: {
                CFG.game.setCivNonAggressionPact(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, 5);
                CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCapitalProvinceID());
                this.onBackPressed();
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMANAGE_DIPLOMACY);
        CFG.menuManager.setBackAnimation(true);
        Game_Render_Province.updateDrawProvinces();
        CFG.map.getMapTouchManager().update_ExtraAction();
    }
}

