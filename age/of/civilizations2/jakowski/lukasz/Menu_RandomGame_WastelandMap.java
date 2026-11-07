/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options;
import age.of.civilizations2.jakowski.lukasz.Button_CNG_Options_Text2;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.WastelandMap_GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_RandomGame_WastelandMap
extends SliderMenu {
    protected static final int ANIMATION_TIME = 250;
    protected static long lTime = 0L;

    protected Menu_RandomGame_WastelandMap() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempMaxH = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) - CFG.PADDING;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        int tY = 0;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_CNG_Options(null, -1, 0, tY, tempW, tempElemH, true));
        menuElements.add(new Button_CNG_Options_Text2(CFG.map.getMapName(CFG.map.getActiveMapID()), CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces(), CFG.PADDING * 2, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, tempElemH, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        for (int i = 1; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
            menuElements.add(new Button_CNG_Options_Text2(CFG.map.getMapContinents().getName(i), CFG.langManager.get("Provinces") + ": " + CFG.game.countContinentProvinces(i), CFG.PADDING * 2, 0, tY, tempW, tempElemH, true));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
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
                    menuElements.add(new Button_CNG_Options_Text2(CFG.langManager.get(tempGameData.getName()), CFG.langManager.get("Provinces") + ": " + (tempLandProvinces - tempGameData.getWastelandProvincesSize()), CFG.PADDING * 2, 0, tY, tempW, tempElemH, true));
                    tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
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
        catch (GdxRuntimeException tagsSPLITED) {
            // empty catch block
        }
        menuElements.add(new Button_CNG_Options(null, -1, 0, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempW, tempElemH, true));
        for (int i = 1; i < menuElements.size() - 1; ++i) {
            ((MenuElement)menuElements.get(i)).setCurrent(i);
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX, Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_RandomGame_WastelandMap.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX, Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_RandomGame_WastelandMap.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX, Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_RandomGame_WastelandMap.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX, Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_RandomGame_WastelandMap.this.getWidth(), 1);
                if (AoCGame.LEFT != 0) {
                    oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
                    ImageManager.getImage(Images.pix255_255_255).draw2(oSB, Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX, Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - this.getHeight(), 1, this.getHeight(), true, false);
                    oSB.setColor(Color.WHITE);
                }
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), CFG.COLOR_TEXT_OPTIONS_LEFT_NS);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, tempMaxH < ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() ? tempMaxH : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), menuElements);
        this.setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("CustomizeWasteland"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Back"));
        this.getTitle().setText(CFG.langManager.get("Maps"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0f));
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight(), true, true);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
            super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void actionElement(int iID) {
        if (iID == 0) {
            CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<Integer>();
            CFG.backToMenu = Menu.eCREATE_RANDOM_GAME;
            CFG.goToMenu = Menu.eCREATE_RANDOM_GAME;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_AVAILABLE_PROVINCES);
            CFG.map.getMapCoordinates().centerToRandomMapPosition();
        } else if (iID == 1) {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince()) continue;
                CFG.game.getProvince(i).setWasteland(-1);
            }
            ArrayList<String> tMess = new ArrayList<String>();
            ArrayList<Color> tColor = new ArrayList<Color>();
            tMess.add(this.getMenuElement(iID).getText());
            tColor.add(Color.WHITE);
            tMess.add(CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces());
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
        } else if (iID == this.getMenuElementsSize() - 1) {
            CFG.randomGameManager.checkCapitals();
            CFG.menuManager.setVisible_CreateRandomGame_Options(true);
            CFG.map.getMapCoordinates().centerToRandomMapPosition();
        } else if (iID <= CFG.map.getMapContinents().getContinentsSize()) {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince()) continue;
                if (CFG.game.getProvince(i).getContinent() == iID - 1) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    continue;
                }
                CFG.game.getProvince(i).setWasteland(0);
            }
            CFG.game.buildWastelandLevels();
            ArrayList<String> tMess = new ArrayList<String>();
            ArrayList<Color> tColor = new ArrayList<Color>();
            tMess.add(this.getMenuElement(iID).getText());
            tColor.add(Color.WHITE);
            tMess.add(CFG.langManager.get("Provinces") + ": " + CFG.game.countContinentProvinces(iID - 1));
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
        } else {
            for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(i).getSeaProvince()) continue;
                CFG.game.getProvince(i).setWasteland(-1);
            }
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
            try {
                FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "wasteland_maps/" + tagsSPLITED[iID - 1 - CFG.map.getMapContinents().getContinentsSize()]);
                WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
                int iSize = tempGameData.getWastelandProvincesSize();
                for (int i = 0; i < iSize; ++i) {
                    CFG.game.getProvince(tempGameData.getWastelandProvinceID(i)).setWasteland(0);
                }
            }
            catch (ClassNotFoundException fileData) {
            }
            catch (IOException fileData) {
            }
            catch (IndexOutOfBoundsException fileData) {
                // empty catch block
            }
            CFG.game.buildWastelandLevels();
            ArrayList<String> tMess = new ArrayList<String>();
            ArrayList<Color> tColor = new ArrayList<Color>();
            tMess.add(this.getMenuElement(iID).getText());
            tColor.add(Color.WHITE);
            tMess.add(CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces_NotWasteland());
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = System.currentTimeMillis();
    }
}

