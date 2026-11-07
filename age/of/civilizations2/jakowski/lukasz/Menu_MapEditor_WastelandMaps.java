/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.WastelandMap_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_MapEditor_WastelandMaps
extends SliderMenu {
    protected Menu_MapEditor_WastelandMaps() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        try {
            String[] tagsSPLITED = null;
            if (CFG.isDesktop()) {
                int i;
                List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/");
                int iSize = tempFiles.size();
                for (i = 0; i < iSize; ++i) {
                    if (!tempFiles.get(i).equals("Age_of_Civilizations")) continue;
                    tempFiles.remove(i);
                    break;
                }
                tagsSPLITED = new String[tempFiles.size()];
                iSize = tempFiles.size();
                for (i = 0; i < iSize; ++i) {
                    tagsSPLITED[i] = tempFiles.get(i);
                }
            } else {
                FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
                String tempT = tempFileT.readString();
                tagsSPLITED = tempT.split(";");
            }
            for (int i = 0; i < tagsSPLITED.length; ++i) {
                FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + tagsSPLITED[i]);
                try {
                    WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
                    menuElements.add(new Button_Menu(tempGameData.getName() + ": " + tempGameData.getWastelandProvincesSize() + " " + CFG.langManager.get("Provinces"), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

                        @Override
                        protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                            CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getPosX() + this.getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() / 2 + iTranslateY);
                            super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                        }
                    });
                    continue;
                }
                catch (ClassNotFoundException classNotFoundException) {
                    continue;
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("AddNewWastelandMap"));
        this.getTitle().setText(CFG.langManager.get("WastelandMapsEditor"));
    }

    @Override
    protected final void actionElement(int iID) {
        CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<Integer>();
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.RELOAD_SCENARIO = true;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getSeaProvince()) continue;
                    CFG.game.getProvince(i).setWasteland(-1);
                }
                CFG.brushTool = false;
                CFG.bSetWasteland_AvailableProvinces = true;
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.CREATE_SCENARIO_NAME = "";
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_WASTELAND_MAPS_EDIT);
                return;
            }
        }
        CFG.RELOAD_SCENARIO = true;
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince()) continue;
            CFG.game.getProvince(i).setWasteland(-1);
        }
        CFG.brushTool = false;
        CFG.bSetWasteland_AvailableProvinces = true;
        String[] tagsSPLITED = null;
        if (CFG.isDesktop()) {
            int i;
            List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/");
            int iSize = tempFiles.size();
            for (i = 0; i < iSize; ++i) {
                if (!tempFiles.get(i).equals("Age_of_Civilizations")) continue;
                tempFiles.remove(i);
                break;
            }
            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();
            for (i = 0; i < iSize; ++i) {
                tagsSPLITED[i] = tempFiles.get(i);
            }
        } else {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
        }
        CFG.EDITOR_ACTIVE_GAMEDATA_TAG = tagsSPLITED[iID - 2];
        CFG.CREATE_SCENARIO_NAME = "";
        FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
        try {
            WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
            int iSize = tempGameData.getWastelandProvincesSize();
            for (int i = 0; i < iSize; ++i) {
                try {
                    if (CFG.game.getProvince(tempGameData.getWastelandProvinceID(i)).getSeaProvince()) continue;
                    CFG.game.getProvince(tempGameData.getWastelandProvinceID(i)).setWasteland(0);
                    continue;
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
            CFG.CREATE_SCENARIO_NAME = tempGameData.getName();
        }
        catch (ClassNotFoundException classNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        CFG.game.buildWastelandLevels();
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_WASTELAND_MAPS_EDIT);
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        CFG.menuManager.setBackAnimation(true);
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).setWasteland(-1);
        }
        CFG.lCreateScenario_UndoWastelandProvinces = null;
    }
}

