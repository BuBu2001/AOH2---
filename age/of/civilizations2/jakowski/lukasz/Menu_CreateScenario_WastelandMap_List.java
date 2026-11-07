/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_TextTwoLines;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.WastelandMap_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_WastelandMap_List
extends SliderMenu {
    protected Menu_CreateScenario_WastelandMap_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game_TextTwoLines(null, CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces(), -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countLandProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        try {
            int i;
            String[] tagsSPLITED = null;
            if (CFG.isDesktop()) {
                int i2;
                List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/");
                int iSize = tempFiles.size();
                for (i2 = 0; i2 < iSize; ++i2) {
                    if (!tempFiles.get(i2).equals("Age_of_Civilizations")) continue;
                    tempFiles.remove(i2);
                    break;
                }
                tagsSPLITED = new String[tempFiles.size()];
                iSize = tempFiles.size();
                for (i2 = 0; i2 < iSize; ++i2) {
                    tagsSPLITED[i2] = tempFiles.get(i2);
                }
            } else {
                FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
                String tempT = tempFileT.readString();
                tagsSPLITED = tempT.split(";");
            }
            int tempLandProvinces = 0;
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince()) continue;
                ++tempLandProvinces;
            }
            for (i = 0; i < tagsSPLITED.length; ++i) {
                FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + tagsSPLITED[i]);
                try {
                    WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
                    menuElements.add(new Button_Game_TextTwoLines(CFG.langManager.get(tempGameData.getName()), CFG.langManager.get("Provinces") + ": " + (tempLandProvinces - tempGameData.getWastelandProvincesSize()), -1, CFG.PADDING * (i + 2), CFG.PADDING, CFG.BUTTON_WIDTH, true));
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
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
        this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("World"));
    }

    @Override
    protected void updateMenuElements_IsInView() {
        super.updateMenuElements_IsInView_X();
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.55f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.137f, 0.141f, 0.145f, 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        int i;
        for (i = 0; i < CFG.menuManager.getCreateScenario_WastelandContinents().getMenuElementsSize(); ++i) {
            CFG.menuManager.getCreateScenario_WastelandContinents().getMenuElement(i).setCheckboxState(true);
        }
        CFG.toast.setInView(this.getMenuElement(iID).getText());
        if (iID == 0) {
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                CFG.game.getProvince(i).setWasteland(-1);
            }
        } else {
            for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince()) continue;
                CFG.game.getProvince(i).setWasteland(-1);
            }
            String[] tagsSPLITED = null;
            if (CFG.isDesktop()) {
                int i2;
                List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/");
                int iSize = tempFiles.size();
                for (i2 = 0; i2 < iSize; ++i2) {
                    if (!tempFiles.get(i2).equals("Age_of_Civilizations")) continue;
                    tempFiles.remove(i2);
                    break;
                }
                tagsSPLITED = new String[tempFiles.size()];
                iSize = tempFiles.size();
                for (i2 = 0; i2 < iSize; ++i2) {
                    tagsSPLITED[i2] = tempFiles.get(i2);
                }
            } else {
                FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + "Age_of_Civilizations");
                String tempT = tempFileT.readString();
                tagsSPLITED = tempT.split(";");
            }
            try {
                FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + tagsSPLITED[iID - 1]);
                WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
                int iSize = tempGameData.getWastelandProvincesSize();
                for (int i3 = 0; i3 < iSize; ++i3) {
                    CFG.game.getProvince(tempGameData.getWastelandProvinceID(i3)).setWasteland(0);
                }
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
            CFG.game.buildWastelandLevels();
        }
    }
}

