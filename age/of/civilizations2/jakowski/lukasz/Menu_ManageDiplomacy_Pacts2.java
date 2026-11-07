/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_BG_CNG_Pact;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ManageDiplomacy_Pacts2
extends SliderMenu {
    protected Menu_ManageDiplomacy_Pacts2() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int nAddedNum = 1;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                if (CFG.game.getCivNonAggressionPact(i, j) <= 0) continue;
                menuElements.add(new Slider_BG_CNG_Pact(i, j, CFG.langManager.get("Turns") + ": ", CFG.PADDING * 2, CFG.PADDING + tempElemH * nAddedNum, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 1, 40, CFG.game.getCivNonAggressionPact(i, j)));
                ++nAddedNum;
            }
        }
        menuElements.add(new Button_CNG_Options(null, -1, 0, 0, tempW, tempElemH, true));
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_ManageDiplomacy_Pacts2.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_ManageDiplomacy_Pacts2.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_ManageDiplomacy_Pacts2.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_ManageDiplomacy_Pacts2.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0, CFG.BUTTON_HEIGHT, tempW, Math.min(tempElemH * menuElements.size(), CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3), menuElements);
        this.setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("NonAggressionPact"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("AddNewPact"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
            Game_Render_Province.updateDrawProvinces();
            CFG.map.getMapTouchManager().update_ExtraAction();
            return;
        }
        this.updateNonAggressionPact(iID, this.getMenuElement(iID).getCurrent());
    }

    private final void updateNonAggressionPact(int pactID, int iNumOfTurns) {
        int foundPacts = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                if (CFG.game.getCivNonAggressionPact(i, j) <= 0) continue;
                if (foundPacts == pactID) {
                    CFG.game.setCivNonAggressionPact(i, j, iNumOfTurns);
                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 != i && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 != j) {
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(i).getCapitalProvinceID());
                        CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = i;
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                    return;
                }
                ++foundPacts;
            }
        }
    }
}

