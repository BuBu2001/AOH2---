/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_GenerateFlag
extends SliderMenu {
    protected Menu_GenerateFlag() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.flagManager.saveFlagTexture(oSB);
        CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
        this.onBackPressed();
    }

    @Override
    protected void actionElement(int nMenuElementID) {
    }

    @Override
    protected final void onBackPressed() {
        try {
            if (CFG.backToMenu == Menu.eINGAME_CREATE_VASSAL) {
                CFG.createVassal_Data.setCivTag(CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        CFG.menuManager.setViewIDWithoutAnimation(CFG.backToMenu);
        CFG.menuManager.setBackAnimation(true);
        CFG.map.getMapBG().updateWorldMap_Shaders();
        Game_Render_Province.updateDrawProvinces();
    }
}

