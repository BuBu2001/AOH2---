/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_NeighboringProvinces;
import age.of.civilizations2.jakowski.lukasz.Editors;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import java.util.ArrayList;
import java.util.List;

class Menu_MapEditor_UpdateProvinceData
extends SliderMenu {
    protected Menu_MapEditor_UpdateProvinceData() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        List<String> tempL = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "update/");
        for (int i = 0; i < tempL.size(); ++i) {
            menuElements.add(new Button_Menu(tempL.get(i), 50, 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_ReflectedBG("CENTER", (int)(50.0f * CFG.GUI_SCALE), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getTitle().setText(CFG.langManager.get("UpdateProvinceData") + " - " + "map/" + CFG.map.getFile_ActiveMap_Path() + "update/");
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.editorManager.setInUse(Editors.eNEIGHBORING_PROVINCES);
                this.onBackPressed();
                return;
            }
        }
        List<String> tempL = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "update/");
        try {
            if ((iID - 1) % 2 == 0) {
                Editor_NeighboringProvinces.updateProvince(Integer.parseInt(tempL.get((iID - 1) / 2)));
                CFG.game.setActiveProvinceID(Integer.parseInt(tempL.get((iID - 1) / 2)));
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            } else {
                CFG.game.setActiveProvinceID(Integer.parseInt(tempL.get((iID - 1) / 2)));
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
        }
        catch (IndexOutOfBoundsException e) {
            CFG.toast.setInView("ERROR");
        }
        catch (IllegalArgumentException ex) {
            CFG.toast.setInView("ERROR FILE NAME");
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CONNECTIONS);
        CFG.menuManager.setBackAnimation(true);
    }
}

