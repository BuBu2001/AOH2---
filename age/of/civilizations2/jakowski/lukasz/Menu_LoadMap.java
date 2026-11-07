/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_ChooseScenario_Title;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_LoadMap
extends SliderMenu {
    private int iStepID = 0;
    private int iNumOfSteps = 5;

    protected Menu_LoadMap() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        CFG.drawLoading(oSB, (int)((float)CFG.GAME_WIDTH * CFG.getLoadingPadding()) + iTranslateX, CFG.GAME_HEIGHT - (int)((float)CFG.BUTTON_HEIGHT * 0.6f) * 2 - CFG.PADDING + iTranslateY, (int)((float)CFG.GAME_WIDTH * (1.0f - CFG.getLoadingPadding() * 2.0f)), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), (float)this.iStepID / (float)(this.iNumOfSteps + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2));
        CFG.drawJakowskiGames_RIGHT_BOT(oSB, iTranslateX);
        new Thread(new Runnable(){

            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable(){

                    @Override
                    public void run() {
                        Menu_LoadMap.this.loadMap();
                    }
                });
            }
        }).start();
        CFG.drawVersion_LEFT_BOT(oSB, iTranslateX);
        CFG.setRender_3(true);
    }

    private final void loadMap() {
        if (this.iStepID == 0) {
            SaveManager.gameCanBeContinued = false;
            CFG.map.getMapBG().loadGameMap();
            CFG.map.getMapScroll().stopScrollingTheMap();
            CFG.map.getMapScale().setCurrentScale(Map_Scale.MINSCALE);
            CFG.map.getMapCoordinates().setNewPosX(-((int)((float)(CFG.map.getMapBG().getWidth() / 2) - (float)CFG.GAME_WIDTH / Map_Scale.MINSCALE / 2.0f)));
            CFG.map.getMapCoordinates().setNewPosY(-((int)((float)(CFG.map.getMapBG().getHeight() / 2) - (float)CFG.GAME_HEIGHT / Map_Scale.MINSCALE / 2.0f)));
            CFG.map.getMapCoordinates().updateMapPos();
            CFG.sLoading = CFG.langManager.get("LoadingGraphics");
            ++this.iStepID;
        } else if (this.iStepID == 1) {
            CFG.game.disposeMapData();
            CFG.map.initMapContinents();
            CFG.map.initMapRegions();
            CFG.sLoading = CFG.langManager.get("LoadingMap");
            ++this.iStepID;
        } else if (this.iStepID >= 2 && this.iStepID < 2 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID())) {
            CFG.sLoading = CFG.langManager.get("LoadingMap") + " [#" + (this.iStepID - 2) + "/" + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) + "] - ";
            for (int i = 0; i < 75 && this.iStepID < 2 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()); ++i) {
                CFG.game.loadProvince(this.iStepID++ - 2);
            }
        } else if (this.iStepID == 2 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID())) {
            CFG.sLoading = CFG.langManager.get("LoadingProvinces");
            ++this.iStepID;
        } else if (this.iStepID >= 3 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) && this.iStepID < 3 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.sLoading = CFG.langManager.get("LoadingProvinces") + " [#" + (this.iStepID - (3 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()))) + "/" + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) + "] - ";
            for (int i = 0; i < 30 && this.iStepID < 3 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2; ++i) {
                CFG.game.loadProvinceTexture(this.iStepID++ - 3 - CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()));
            }
        } else if (this.iStepID == 3 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.game.updateProvincesSize();
            CFG.game.buildBasinsOfSeaProvinces();
            CFG.game.loadRegions();
            CFG.sLoading = CFG.langManager.get("LoadingGameData");
            ++this.iStepID;
        } else if (this.iStepID == 4 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.game.loadProvinceNames_ALL();
            CFG.game.checkLandBySeaProvincesBorders();
            CFG.game.checkSeaBySeaProvincesBorders();
            ++this.iStepID;
        } else if (this.iStepID == 5 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.game.buildProvinceBorder();
            ++this.iStepID;
        } else if (this.iStepID == 6 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.sLoading = CFG.langManager.get("Loading");
            CFG.game.getGameScenarios().loadGame_Scenarios(true);
            ++this.iStepID;
        } else if (this.iStepID == 7 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.PLAYER_TURNID = 0;
            CFG.game.getPlayer(0).initMetProvince(true);
            CFG.game.loadScenario(false);
            ++this.iStepID;
        } else if (this.iStepID == 8 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.game.initPlayers();
            CFG.game.buildDrawArmy();
            ++this.iStepID;
        } else if (this.iStepID == 9 + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2) {
            CFG.game.loadCities();
            CFG.game.loadMountains();
            CFG.map.getMapScroll().stopScrollingTheMap();
            CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
            CFG.map.getMapCoordinates().centerToRandomMapPosition();
            Menu_ChooseScenario_Title.iPreviewScenarioID = 0;
            CFG.menuManager.setViewID(CFG.goToMenu);
            CFG.saveSettings_ActiveMap();
            CFG.map.load_DeleteStatusFile();
        }
    }
}

