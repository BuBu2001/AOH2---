/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Start_The_Game_Data;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_RandomGame
extends SliderMenu {
    protected Menu_RandomGame() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2 + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("PLAY"));
        this.getMenuElement(1).setText(CFG.langManager.get("Options"));
        this.getMenuElement(2).setText(CFG.langManager.get("Back"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(Color.WHITE);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - 1 + iTranslateY, this.getMenuElement(2).getPosX() + this.getMenuElement(2).getWidth() + CFG.PADDING + 1, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1);
        CFG.drawEditorButtons_Bot_Edge_R_Reflected(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING - 1 + iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - 1 + iTranslateY, this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 1, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.lCreateScenario_UndoWastelandProvinces = null;
                CFG.soundsManager.playSound(SoundsManager.SOUND_RANDOM);
                this.newGame();
                break;
            }
            case 1: {
                CFG.menuManager.setVisible_CreateRandomGame_Options(!CFG.menuManager.getVisible_CreateRandomGame_Options());
                break;
            }
            case 2: {
                CFG.setDialogType(Dialog.CREATE_RANDOM_GAME_EXIT_MAIN_MENU);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        Menu_RandomGame.backToGames();
    }

    protected static final void backToGames() {
        if (CFG.menuManager.getVisible_CreateRandomGame_WastelandMaps()) {
            CFG.randomGameManager.checkCapitals();
            CFG.menuManager.setVisible_CreateRandomGame_Options(true);
            CFG.map.getMapCoordinates().centerToRandomMapPosition();
            return;
        }
        CFG.lCreateScenario_UndoWastelandProvinces = null;
        CFG.randomGameManager = null;
        CFG.game.loadScenario(false);
        SaveManager.gameCanBeContinued = false;
        CFG.game.setActiveProvinceID(-1);
        CFG.menuManager.setViewID(Menu.eGAMES);
        CFG.menuManager.setBackAnimation(true);
    }

    protected final void newGame() {
        RTS.reset();
        CFG.game.loadScenario_RandomGame();
        CFG.viewsManager.disableAllViews();
        if (CFG.map.getMapScale().getCurrentScale() < Map_Scale.STANDARD_SCALE) {
            CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
        }
        CFG.RANDOM_PLACMENT = false;
        CFG.gameNewGame.newRandomGame();
        CFG.startTheGameData = new Start_The_Game_Data(false);
        CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "Age of Civilizations II";
        CFG.menuManager.setViewIDWithoutAnimation(Menu.eSTART_THE_GAME);
        CFG.map.getMapBG().disposeMinimapOfCivilizations();
    }
}

