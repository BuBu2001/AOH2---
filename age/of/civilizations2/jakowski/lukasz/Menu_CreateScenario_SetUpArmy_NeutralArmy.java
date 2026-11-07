/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_LR_Flag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_SetUpArmy_NeutralArmy
extends SliderMenu {
    protected Menu_CreateScenario_SetUpArmy_NeutralArmy() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH * 2;
        if (tempW > CFG.GAME_WIDTH - CFG.PADDING * 4) {
            tempW = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Slider_LR_Flag(0, CFG.PADDING, CFG.PADDING, tempW - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 4, 0, 400, CFG.game.getGameScenarios().getScenario_NeutralArmy() / 25){

            @Override
            protected String getDrawText() {
                return "" + this.getCurrent() * 25;
            }

            @Override
            protected int getWidth() {
                return Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getW() - CFG.PADDING * 2;
            }
        });
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosX() + iTranslateX, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() - this.getHeight(), Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getWidth() - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), false, false);
                ImageManager.getImage(Images.dialog_title).draw2(oSB, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosX() + Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getWidth() - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() - this.getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.r, CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.g, CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.b, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosX() + iTranslateX, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosX() + 2 + iTranslateX, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getWidth() - 4);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.85f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosX() + 2 + iTranslateX, Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_CreateScenario_SetUpArmy_NeutralArmy.this.getWidth() - 4, 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING + CFG.BUTTON_HEIGHT / 2, tempW, CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2, menuElements, false, true);
        this.getMenuElement(0).setCurrent(this.getMenuElement(0).getCurrent());
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("NeutralArmy"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_line).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getHeight(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth());
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void actionElement(int iID) {
        int nBefore = CFG.game.getGameScenarios().getScenario_NeutralArmy();
        CFG.game.getGameScenarios().setScenario_NeutralArmy(this.getMenuElement(iID).getCurrent() * 25);
        if (nBefore != CFG.game.getGameScenarios().getScenario_NeutralArmy()) {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getCivID() != 0 || CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getArmy(0) != nBefore) continue;
                CFG.game.getProvince(i).updateArmy(CFG.game.getGameScenarios().getScenario_NeutralArmy());
            }
        }
    }

    private int getW() {
        return this.getWidth();
    }
}

