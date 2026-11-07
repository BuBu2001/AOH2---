/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_Regions;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

class Menu_GameEditor_Regions
extends SliderMenu {
    protected static List<Color> lColors = new ArrayList<Color>();

    protected Menu_GameEditor_Regions() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.PADDING, CFG.PADDING, menuElements);
        for (int i = 0; i < CFG.game.getRegions().size(); ++i) {
            lColors.add(CFG.getRandomColor());
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        CFG.menuManager.setBackAnimation(true);
        Editor_Regions.lUndo.clear();
        CFG.brushTool = false;
        CFG.editorManager.resetInUseEditors();
        Game_Render_Province.updateDrawProvinces();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
        }
    }
}

