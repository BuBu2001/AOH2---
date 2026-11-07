/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Editors;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

class Menu_MapEditor_PortPosition_Convert
extends SliderMenu {
    protected Menu_MapEditor_PortPosition_Convert() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + "Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        ArrayList<Integer> tempScales = new ArrayList<Integer>();
        for (i = 0; i < tagsSPLITED.length; ++i) {
            tempScales.add(Integer.parseInt(tagsSPLITED[i]));
        }
        for (i = 0; i < tempScales.size(); ++i) {
            if (CFG.map.getMapScale(CFG.map.getActiveMapID()) == ((Integer)tempScales.get(i)).intValue()) {
                menuElements.add(new Button_Menu(CFG.langManager.get("Scale") + " x" + tempScales.get(i) + " - [" + CFG.map.getMapBG().getWidth() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "x" + CFG.map.getMapBG().getHeight() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "]", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true));
                continue;
            }
            menuElements.add(new Button_Menu(CFG.langManager.get("Scale") + " x" + tempScales.get(i) + " - [" + CFG.map.getMapBG().getWidth() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "x" + CFG.map.getMapBG().getHeight() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "]", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getTitle().setText(CFG.langManager.get("ConvertToAnotherScale"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
        }
        FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + "Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = Integer.parseInt(tagsSPLITED[iID - 1]);
        if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 != CFG.map.getMapScale(CFG.map.getActiveMapID())) {
            CFG.setDialogType(Dialog.CONVERT_PORT_POSITION_TO_ANOTHER_SCALE);
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PORT_POSITION);
        CFG.menuManager.setBackAnimation(true);
        CFG.editorManager.setInUse(Editors.eSHIFT_PORT);
    }
}

