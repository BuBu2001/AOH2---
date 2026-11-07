/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateScenario;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_PalletOfColors
extends Menu_CreateScenario {
    private String sTopText;
    private int iStepWidth;

    protected Menu_CreateScenario_PalletOfColors() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.getMenuElement(2).setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sTopText = CFG.langManager.get("PalletCivColors");
        CFG.glyphLayout.setText(CFG.fontMain, this.sTopText);
        this.iStepWidth = (int)CFG.glyphLayout.width;
        super.updateLanguage();
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawTextWithShadow(oSB, this.sTopText, CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX, CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY, Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 1: {
                this.onBackPressed();
                return;
            }
        }
        super.actionElement(iID);
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
    }
}

