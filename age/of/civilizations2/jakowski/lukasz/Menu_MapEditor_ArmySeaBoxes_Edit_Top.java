/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_NewGameBoxStyle_LEFT;
import age.of.civilizations2.jakowski.lukasz.Button_Game_NewGameBoxStyle_RIGHT_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_MapEditor_ArmySeaBoxes_Add;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_ArmySeaBoxes_Edit_Top
extends SliderMenu {
    protected Menu_MapEditor_ArmySeaBoxes_Edit_Top() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null) {
            for (int j = 0; j < CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size(); ++j) {
                menuElements.add(new Button_Game_NewGameBoxStyle_LEFT(CFG.langManager.get("Edit") + ": " + (j + 1), -1, CFG.PADDING * (j + 1) + CFG.BUTTON_WIDTH * 2 * j, CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 4, CFG.BUTTON_HEIGHT * 3 / 4, true));
                menuElements.add(new Button_Game_NewGameBoxStyle_RIGHT_Remove(CFG.PADDING * (j + 1) + CFG.BUTTON_WIDTH * 2 * j + CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 4, CFG.PADDING, CFG.BUTTON_WIDTH * 3 / 4, CFG.BUTTON_HEIGHT * 3 / 4, true));
            }
        }
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (this.getMenuElementsSize() > 0) {
            CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, this.getMenuElement(0).getHeight() + CFG.PADDING * 2);
        }
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID % 2 == 0) {
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = iID / 2;
            Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint = new Point_XY(CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getStartPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getStartPosY());
            Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint = new Point_XY(CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getEndPosX(), CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getEndPosY());
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_ADD);
        } else {
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = iID / 2;
            CFG.setDialogType(Dialog.MAP_EDITOR_SEA_ARMY_BOXES_ROMVE);
        }
    }
}

