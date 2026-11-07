/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Turn_CivsInRange;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_StartTheGame
extends SliderMenu {
    private String s1;
    private int iWidth1;
    private String s2;
    private int iWidth2;
    protected Turn_CivsInRange turn_civsInRange;

    protected Menu_StartTheGame() {
        block13: {
            block12: {
                this.s1 = "";
                this.s2 = "";
                ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
                menuElements.add(new Button_Transparent(1, 1, CFG.GAME_WIDTH - 2, CFG.GAME_HEIGHT - 2, true));
                this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
                try {
                    this.s1 = CFG.EDITOR_ACTIVE_GAMEDATA_TAG;
                    CFG.glyphLayout.setText(CFG.fontMain, this.s1);
                    this.iWidth1 = (int)CFG.glyphLayout.width;
                }
                catch (IndexOutOfBoundsException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (NullPointerException ex) {
                    if (CFG.LOGS) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (IllegalStateException ex) {
                    if (!CFG.LOGS) break block12;
                    CFG.exceptionStack(ex);
                }
            }
            try {
                this.s2 = Game_Calendar.getCurrentDate();
                CFG.glyphLayout.setText(CFG.fontMain, this.s2);
                this.iWidth2 = (int)CFG.glyphLayout.width;
            }
            catch (IndexOutOfBoundsException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (IllegalStateException ex) {
                if (!CFG.LOGS) break block13;
                CFG.exceptionStack(ex);
            }
        }
        this.updateLanguage();
        this.turn_civsInRange = new Turn_CivsInRange();
        this.turn_civsInRange.start();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        try {
            oSB.setColor(new Color(0.1f, 0.1f, 0.1f, 0.2f - 0.2f * (float)CFG.startTheGameData.getProvincesAlpha()));
            ImageManager.getImage(Images.patt).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0f, 0);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.4f));
            ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
            ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT);
            oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT);
            oSB.setColor(CFG.COLOR_FLAG_FRAME);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.BUTTON_HEIGHT - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 4, 1);
            oSB.setColor(Color.WHITE);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.35f));
            ImageManager.getImage(Images.gameLogo).draw2(oSB, CFG.GAME_WIDTH - ImageManager.getImage(Images.gameLogo).getWidth() - CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() * 2 - CFG.PADDING, ImageManager.getImage(Images.gameLogo).getWidth(), ImageManager.getImage(Images.gameLogo).getHeight());
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.gameLogo).draw2(oSB, CFG.GAME_WIDTH - ImageManager.getImage(Images.gameLogo).getWidth() - CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() * 2 - CFG.PADDING, (int)((float)(ImageManager.getImage(Images.gameLogo).getWidth() * CFG.startTheGameData.getProvincesAlpha()) / 100.0f), ImageManager.getImage(Images.gameLogo).getHeight());
            CFG.drawText(oSB, this.s1, CFG.GAME_WIDTH / 2 - this.iWidth1 / 2 + iTranslateX, CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + iTranslateY, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME);
            CFG.fontMain.getData().setScale(0.8f);
            CFG.drawText(oSB, this.s2, CFG.GAME_WIDTH / 2 - (int)((float)this.iWidth2 * 0.8f / 2.0f) + iTranslateX, CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + iTranslateY, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO);
            CFG.fontMain.getData().setScale(1.0f);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            if (CFG.startTheGameData.getIsDone()) {
                this.onBackPressed();
            }
            try {
                if (Turn_CivsInRange.DONE_CIVS < CFG.game.getCivsSize()) {
                    CFG.fontMain.getData().setScale(0.8f);
                    CFG.drawText(oSB, "" + Turn_CivsInRange.DONE_CIVS + " / " + CFG.game.getCivsSize(), CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - (int)((float)CFG.TEXT_HEIGHT * 0.8f) - CFG.PADDING + iTranslateY, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                }
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            CFG.fontMain.getData().setScale(1.0f);
        }
        catch (NullPointerException ex) {
            this.onBackPressed();
        }
        catch (IndexOutOfBoundsException ex) {
            this.onBackPressed();
        }
        oSB.setColor(Color.WHITE);
        CFG.setRender_3(true);
    }

    @Override
    protected final void actionElement(int iID) {
        try {
            if (Turn_CivsInRange.DONE_CIVS >= CFG.game.getCivsSize()) {
                Menu_StartTheGame.done();
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    @Override
    protected final void onBackPressed() {
    }

    protected static final void done() {
        CFG.gameAction.hideExtraViews();
        CFG.menuManager.setViewID(Menu.eINGAME);
        CFG.menuManager.setVisible_InGame_Options(false);
        CFG.menuManager.setVisible_InGame_EndOfGame(false);
        CFG.menuManager.setVisible_InGame_ActionInfo(false);
        CFG.menuManager.setVisible_InGame_View(false);
        CFG.gameAction.updateInGame_ProvinceInfo();
        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        CFG.menuManager.rebuildInGame_Messages();
        CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
        Game_Render_Province.updateDrawProvinces();
        CFG.game.checkProvinceActionMenu();
        CFG.menuManager.setOrderOfMenu_InGame();
        CFG.game.updateDrawMoveUnitsArmy();
    }
}

