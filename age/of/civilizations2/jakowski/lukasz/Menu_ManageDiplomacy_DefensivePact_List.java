/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_Defensive;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_ManageDiplomacy_DefensivePact_List
extends SliderMenu {
    protected Menu_ManageDiplomacy_DefensivePact_List() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH / 4;
        int tempElemH = CFG.BUTTON_HEIGHT;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID > 0) {
            int multiplePosY = 0;
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID || CFG.game.getDefensivePact(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i) <= 0) continue;
                menuElements.add(new Slider_Defensive(i, CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, CFG.PADDING, CFG.PADDING * (multiplePosY + 1) + CFG.BUTTON_HEIGHT * multiplePosY + CFG.PADDING, tempW - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.PADDING * 2, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, 1, 40, CFG.game.getDefensivePact(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i)));
                menuElements.add(new Button_Menu_Remove(tempW - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.PADDING, CFG.PADDING * (multiplePosY + 1) + CFG.BUTTON_HEIGHT * multiplePosY, CFG.BUTTON_HEIGHT * 3 / 4, CFG.BUTTON_HEIGHT, true){

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
                ++multiplePosY;
            }
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_ManageDiplomacy_DefensivePact_List.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_DefensivePact_List.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_ManageDiplomacy_DefensivePact_List.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_ManageDiplomacy_DefensivePact_List.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_DefensivePact_List.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_ManageDiplomacy_DefensivePact_List.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_ManageDiplomacy_DefensivePact_List.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_DefensivePact_List.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_ManageDiplomacy_DefensivePact_List.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_ManageDiplomacy_DefensivePact_List.this.getPosX() + iTranslateX, Menu_ManageDiplomacy_DefensivePact_List.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_ManageDiplomacy_DefensivePact_List.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                try {
                    CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getFlag().draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min((tempElemH + CFG.PADDING) * (menuElements.size() / 2) + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2), menuElements, false, true);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("DefensivePacts"));
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
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void actionElement(int iID) {
        if (iID % 2 == 0) {
            this.updateDefensive(iID / 2, this.getMenuElement(iID).getCurrent());
        } else if (iID % 2 == 1) {
            this.updateDefensive(iID / 2, 0);
            CFG.menuManager.rebuildManageDiplomacy_DefensivePacts_List();
        }
    }

    private final void updateDefensive(int pactID, int iNumOfTurns) {
        if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID > 0) {
            int foundPacts = 0;
            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                if (i == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID || CFG.game.getDefensivePact(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i) <= 0) continue;
                if (foundPacts == pactID) {
                    CFG.game.setDefensivePact(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i, iNumOfTurns);
                    return;
                }
                ++foundPacts;
            }
        }
    }
}

