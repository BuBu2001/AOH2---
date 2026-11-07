/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_ArmySeaBoxes
extends SliderMenu {
    protected Menu_MapEditor_ArmySeaBoxes() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
        menuElements.add(new Text(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : (this.getClickable() ? new Color(1.0f, 1.0f, 1.0f, 1.0f) : new Color(0.84f, 0.84f, 0.84f, 0.7f));
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("Edit"));
        this.getMenuElement(2).setText(CFG.langManager.get("SeaArmyBoxesEditor"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getActiveProvinceID();
                    if (!CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getDrawProvince()) {
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
                    }
                    CFG.toast.setInView(CFG.langManager.get("Province") + " " + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
                    CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_EDIT);
                } else {
                    CFG.toast.setInView(CFG.langManager.get("SelectProvince"));
                }
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        CFG.menuManager.setBackAnimation(true);
    }
}

