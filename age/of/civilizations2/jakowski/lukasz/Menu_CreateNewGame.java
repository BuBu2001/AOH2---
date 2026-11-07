/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_PLAY;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Start_The_Game_Data;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateNewGame
extends SliderMenu {
    protected Menu_CreateNewGame() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game_PLAY(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2 + CFG.PADDING * 2, CFG.PADDING, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements);
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
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - 1 + iTranslateY, this.getMenuElement(2).getPosX() + this.getMenuElement(2).getWidth() + CFG.PADDING + 1, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1);
        CFG.drawEditorButtons_Bot_Edge_R_Reflected(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING - 1 + iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - 1 + iTranslateY, this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 1, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    protected static final void clickOptions() {
        if (!CFG.menuManager.getVisible_CreateNewGame_Options()) {
            CFG.menuManager.getColorPicker().setVisible(false, null);
        }
        CFG.menuManager.setVisible_CreateNewGame_Options(!CFG.menuManager.getVisible_CreateNewGame_Options());
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                Menu_CreateNewGame.newGame();
                break;
            }
            case 1: {
                Menu_CreateNewGame.clickOptions();
                break;
            }
            case 2: {
                this.onBackPressed();
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        if (CFG.menuManager.getVisible_CreateNewGame_Options_Pallets() || CFG.menuManager.getVisible_CreateNewGame_Options_Scenarios()) {
            CFG.menuManager.setVisible_CreateNewGame_Options(true);
        } else {
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.game.disableDrawCivlizationsRegions_Players();
            CFG.game.setActiveProvinceID(-1);
            CFG.menuManager.setViewID(Menu.eGAMES);
            CFG.menuManager.setBackAnimation(true);
        }
    }

    protected static final void newGame() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        Gdx.app.log("AoC", "CREATE NG -> newGame: 000");
        RTS.reset();
        CFG.game.disableDrawCivlizationsRegions_Players();
        CFG.viewsManager.disableAllViews();
        Gdx.app.log("AoC", "CREATE NG -> newGame: 111");
        if (CFG.map.getMapScale().getCurrentScale() < Map_Scale.STANDARD_SCALE) {
            CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
        }
        Gdx.app.log("AoC", "CREATE NG -> newGame: 222");
        CFG.gameNewGame.newGame();
        Gdx.app.log("AoC", "CREATE NG -> newGame: 333");
        CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID()));
        Gdx.app.log("AoC", "CREATE NG -> newGame: 444");
        CFG.startTheGameData = new Start_The_Game_Data(false);
        Gdx.app.log("AoC", "CREATE NG -> newGame: 555");
        CFG.menuManager.setViewIDWithoutAnimation(Menu.eSTART_THE_GAME);
    }
}

