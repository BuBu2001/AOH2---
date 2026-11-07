/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.WastelandMap_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

class Menu_MapEditor_WastelandMaps_Edit
extends SliderMenu {
    private String sName;
    private int iNameWidth;

    protected Menu_MapEditor_WastelandMaps_Edit() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Menu("", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive || this.getIsHovered() ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : (this.getClickable() ? new Color(1.0f, 1.0f, 1.0f, 1.0f) : new Color(0.84f, 0.84f, 0.84f, 0.7f));
            }

            @Override
            protected String getTextToDraw() {
                return Menu_MapEditor_WastelandMaps_Edit.this.sName + ": " + super.getText();
            }

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + Menu_MapEditor_WastelandMaps_Edit.this.iNameWidth;
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING * 3 + CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2, true, CFG.bSetWasteland_AvailableProvinces));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, CFG.brushTool));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING * 3 + CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, false));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Button_Game(CFG.langManager.get("Min") + " X", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING) * 2, true));
        menuElements.add(new Button_Game(CFG.langManager.get("Max") + " X", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING) * 3, true));
        menuElements.add(new Button_Game(CFG.langManager.get("Min") + " Y", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING) * 4, true));
        menuElements.add(new Button_Game(CFG.langManager.get("Max") + " Y", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING) * 5, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("Name");
        CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
        this.iNameWidth = (int)CFG.glyphLayout.width;
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.CREATE_SCENARIO_NAME);
        this.getMenuElement(2).setText(CFG.langManager.get("Save"));
        this.getMenuElement(3).setText(CFG.langManager.get("Wasteland"));
        this.getMenuElement(4).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(5).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(6).setText(CFG.langManager.get("World"));
        this.updateButtonWidth(6, 0, CFG.BUTTON_WIDTH);
        this.getMenuElement(6).setPosX(CFG.GAME_WIDTH - this.getMenuElement(6).getWidth() - CFG.PADDING);
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(3).getWidth() - CFG.PADDING;
        this.getMenuElement(3).setPosX(tempX);
        tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
        this.getMenuElement(5).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(5).getPosX() - CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(5).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R_Reflected(oSB, this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX, this.getMenuElement(10).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(6).getWidth() + CFG.PADDING * 2, CFG.GAME_HEIGHT - this.getMenuElement(10).getPosY() + CFG.PADDING);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.showKeyboard();
                return;
            }
            case 2: {
                this.saveData();
                CFG.toast.setInView(CFG.langManager.get("Saved"));
                this.onBackPressed();
                return;
            }
            case 3: {
                CFG.bSetWasteland_AvailableProvinces = !CFG.bSetWasteland_AvailableProvinces;
                this.getMenuElement(iID).setCheckboxState(CFG.bSetWasteland_AvailableProvinces);
                return;
            }
            case 4: {
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
                return;
            }
            case 5: {
                if (CFG.lCreateScenario_UndoWastelandProvinces.size() > 0) {
                    CFG.game.getProvince(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1)).setWasteland(CFG.game.getProvince(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1)).getWasteland() >= 0 ? -1 : 0);
                    CFG.game.setActiveProvinceID(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1));
                    if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    }
                    CFG.removeUndoWastelandProvince();
                    CFG.updateNumOfAvailableProvinces();
                }
                return;
            }
            case 6: {
                CFG.setDialogType(Dialog.MAP_EDITOR_WASTELANDMAPS_WORLD_FILL);
                return;
            }
            case 7: {
                int tMinX = CFG.map.getMapBG().getWidth();
                int nProvinceID = 0;
                int tMaxX = -CFG.map.getMapBG().getWidth();
                int tMinY = CFG.map.getMapBG().getHeight();
                boolean tMaxY = false;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getMinX() >= tMinX) continue;
                    tMinX = CFG.game.getProvince(i).getMinX();
                    nProvinceID = i;
                }
                CFG.map.getMapCoordinates().centerToProvinceID(nProvinceID);
                CFG.game.setActiveProvinceID(nProvinceID);
                return;
            }
            case 8: {
                int tMinX = CFG.map.getMapBG().getWidth();
                int nProvinceID = 0;
                int tMaxX = -CFG.map.getMapBG().getWidth();
                int tMinY = CFG.map.getMapBG().getHeight();
                boolean tMaxY = false;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getMaxX() <= tMaxX) continue;
                    tMaxX = CFG.game.getProvince(i).getMaxX();
                    nProvinceID = i;
                }
                CFG.map.getMapCoordinates().centerToProvinceID(nProvinceID);
                CFG.game.setActiveProvinceID(nProvinceID);
                return;
            }
            case 9: {
                int tMinX = CFG.map.getMapBG().getWidth();
                int nProvinceID = 0;
                int tMaxX = -CFG.map.getMapBG().getWidth();
                int tMinY = CFG.map.getMapBG().getHeight();
                boolean tMaxY = false;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getMinY() >= tMinY) continue;
                    tMinY = CFG.game.getProvince(i).getMinY();
                    nProvinceID = i;
                }
                CFG.map.getMapCoordinates().centerToProvinceID(nProvinceID);
                CFG.game.setActiveProvinceID(nProvinceID);
                return;
            }
            case 10: {
                int tMinX = CFG.map.getMapBG().getWidth();
                int nProvinceID = 0;
                int tMaxX = -CFG.map.getMapBG().getWidth();
                int tMinY = CFG.map.getMapBG().getHeight();
                int tMaxY = 0;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0 || CFG.game.getProvince(i).getMaxY() <= tMaxY) continue;
                    tMaxY = CFG.game.getProvince(i).getMaxY();
                    nProvinceID = i;
                }
                CFG.map.getMapCoordinates().centerToProvinceID(nProvinceID);
                CFG.game.setActiveProvinceID(nProvinceID);
                return;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected final void saveData() {
        WastelandMap_GameData wastelandMap_GameData = new WastelandMap_GameData();
        wastelandMap_GameData.setName(this.getMenuElement(1).getText().length() == 0 ? "NO NAME" : this.getMenuElement(1).getText());
        wastelandMap_GameData.generateData();
        if (wastelandMap_GameData.getWastelandProvincesSize() > 0) {
            OutputStream os = null;
            try {
                FileHandle fileData = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                fileData.writeBytes(CFG.serialize(wastelandMap_GameData), false);
            }
            catch (IOException fileData) {
            }
            finally {
                if (os != null) {
                    try {
                        os.close();
                        CFG.toast.setInView(CFG.langManager.get("Saved"));
                    }
                    catch (Exception fileData) {}
                }
            }
        }
        try {
            FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations") : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
            String tempTags = file.readString();
            if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
                FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
                fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            }
        }
        catch (GdxRuntimeException ex) {
            FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
            fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.brushTool = false;
        CFG.game.setActiveProvinceID(-1);
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_WASTELAND_MAPS);
        CFG.menuManager.setBackAnimation(true);
    }
}

