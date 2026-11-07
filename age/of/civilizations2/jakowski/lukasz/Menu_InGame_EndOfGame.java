/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_NS_Population;
import age.of.civilizations2.jakowski.lukasz.Button_Options_NS;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Victory;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_TitleStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_EndOfGame
extends SliderMenu {
    protected static final int ANIMATION_TIME = 225;
    private long lTime = 0L;
    protected static final float SCALE_CHANGE = 0.175f;

    protected int getElementWidth() {
        return (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.25f);
    }

    protected Menu_InGame_EndOfGame() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_TitleStyle(null, -1, CFG.GAME_WIDTH / 2 - this.getElementWidth() / 2 - CFG.PADDING, 0, this.getElementWidth() + CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2){

            @Override
            protected Color getColor_BG() {
                return Menu_Victory.VICTORIOUS ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            }
        });
        menuElements.add(new Button_NS_Population(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 1.0f), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.langManager.get("Score") + ": ", "" + (int)Math.ceil(CFG.game.getPlayer(CFG.PLAYER_TURNID).buildPlayerScore()), Images.victoryPoints, CFG.COLOR_TEXT_MODIFIER_NEUTRAL, CFG.GAME_WIDTH / 2 - this.getElementWidth() / 2, 0, this.getElementWidth()){});
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - this.getElementWidth() / 2 + CFG.PADDING, 0, this.getElementWidth() - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){});
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - this.getElementWidth() / 2 + CFG.PADDING, 0, this.getElementWidth() - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){});
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - this.getElementWidth() / 2 + CFG.PADDING, 0, this.getElementWidth() - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){

            @Override
            protected boolean getClickable() {
                return !CFG.SPECTATOR_MODE;
            }
        });
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - this.getElementWidth() / 2 + CFG.PADDING, 0, this.getElementWidth() - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true));
        int tempElementHeight = (menuElements.size() + 1) * CFG.PADDING + CFG.PADDING;
        for (int i = 0; i < menuElements.size(); ++i) {
            tempElementHeight += ((MenuElement)menuElements.get(i)).getHeight();
        }
        int tempY = CFG.PADDING;
        ((MenuElement)menuElements.get(0)).setPosY(CFG.GAME_HEIGHT * 2 / 5 - tempElementHeight / 2 + tempY);
        tempY += ((MenuElement)menuElements.get(0)).getHeight() + CFG.PADDING;
        for (int i = 1; i < menuElements.size(); ++i) {
            if (i == 2) {
                tempY += CFG.PADDING;
            }
            ((MenuElement)menuElements.get(i)).setPosY(CFG.GAME_HEIGHT * 2 / 5 - tempElementHeight / 2 + tempY);
            tempY += ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING;
        }
        menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
        super.setVisible(false);
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(Menu_Victory.VICTORIOUS ? CFG.langManager.get("Victory") : CFG.langManager.get("Defeat"));
        this.getMenuElement(2).setText(CFG.langManager.get("Timeline"));
        this.getMenuElement(3).setText(CFG.langManager.get("JustOneMoreTurnIPromise"));
        this.getMenuElement(4).setText(CFG.langManager.get("SpectatorMode"));
        this.getMenuElement(5).setText(CFG.langManager.get("ExitToMainMenu"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
        ImageManager.getImage(Images.patt2).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt2).getHeight(), this.getWidth(), this.getHeight());
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f * ((float)(System.currentTimeMillis() - this.lTime) / 225.0f)));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
        }
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getElementWidth() / 2, this.getHeight());
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getElementWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getElementWidth() / 2, this.getHeight(), true, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getElementWidth() / 2);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getElementWidth() / 2 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getElementWidth() / 2, false, true);
        oSB.setColor(Color.WHITE);
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            iTranslateY = iTranslateY - this.getHeight() * 4 / 5 + (int)((float)(this.getHeight() * 4 / 5) * ((float)(System.currentTimeMillis() - this.lTime) / 225.0f));
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, -ImageManager.getImage(Images.new_game_top_edge).getHeight() + this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() + this.getMenuPosY() + iTranslateY, this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getMenuElement(this.getMenuElementsSize() - 2).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 2).getHeight() + CFG.PADDING * 2 - (this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight()), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, -ImageManager.getImage(Images.new_game_top_edge).getHeight() + this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() + this.getMenuPosY() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getMenuElement(this.getMenuElementsSize() - 2).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 2).getHeight() + CFG.PADDING * 2 - (this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight()), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    protected static final void clickBack() {
        Map_Scale.SCALE_ANIMATION_TIME = 125;
        CFG.menuManager.setVisible_InGame_EndOfGame(false);
        CFG.game.checkProvinceActionMenu();
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 2: {
                if (Menu_Victory.VICTORIOUS) {
                    CFG.menuManager.setViewID(Menu.eVICTORY);
                } else {
                    CFG.menuManager.setViewID(Menu.eDEFEAT);
                }
                CFG.map.getMapBG().updateWorldMap_Shaders();
                break;
            }
            case 3: {
                CFG.setDialogType(Dialog.END_GAME_ONE_MORE_TURN);
                break;
            }
            case 4: {
                CFG.setDialogType(Dialog.END_GAME_SPECTACTOR);
                break;
            }
            case 5: {
                CFG.setDialogType(Dialog.END_GAME_EXIT_MAIN_MENU);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        Menu_InGame_EndOfGame.clickBack();
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        this.lTime = System.currentTimeMillis();
    }
}

