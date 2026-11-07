/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Accept;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Decline;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Slider_LR_Perc;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_Slider
extends SliderMenu {
    protected Menu_InGame_Slider() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game_Decline(CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game_Accept(CFG.GAME_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH, CFG.PADDING, true){

            @Override
            protected int getSFX() {
                return CFG.soundsManager.playMoveArmy();
            }
        });
        menuElements.add(new Slider_LR_Perc(CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.PADDING, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 4, CFG.BUTTON_HEIGHT, 0, 200, 100));
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, false, false);
        this.updateLanguage();
        CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((CFG.fMOVE_MENU_PERCENTAGE += (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 250.0f * 95.0f) > 100.0f) {
            CFG.fMOVE_MENU_PERCENTAGE = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        ImageManager.getImage(Images.bg_game_menu).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.bg_game_menu).getHeight() + (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(CFG.COLOR_BG_GAME_MENU_SHADOW);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) + iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
    }

    @Override
    protected void extraAction() {
        block7: {
            try {
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.chosenProvinceID >= 0) {
                    CFG.gameAction.moveArmy(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, this.getMenuElement(2).getCurrent(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false, true);
                    try {
                        if (this.getMenuElement(2).getCurrent() < 10 && CFG.game.getProvince(CFG.chosenProvinceID).getCivID() > 0 && CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.chosenProvinceID).getCivID())) {
                            CFG.toast.setInView(CFG.langManager.get("MinArmyRequiredToAttack") + ": " + 10 + " " + CFG.langManager.get("Units"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                            CFG.toast.setTimeInView(4500);
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                    CFG.game.resetChooseProvinceData();
                    CFG.game.checkProvinceActionMenu();
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.game.getPlayer(CFG.PLAYER_TURNID).setNoOrders(false);
                    if (RTS.isEnabled() && !RTS.PAUSE) {
                        RTS.updateTimePast_AfterAction(0.75f);
                    }
                    break block7;
                }
                this.setVisible(false);
            }
            catch (IndexOutOfBoundsException ex) {
                if (!CFG.LOGS) break block7;
                CFG.exceptionStack(ex);
            }
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.game.resetChooseProvinceData();
                CFG.game.checkProvinceActionMenu();
                if (!RTS.isEnabled() || RTS.PAUSE) break;
                RTS.updateTimePast_AfterAction(0.5f);
                break;
            }
            case 1: {
                this.extraAction();
                break;
            }
            case 2: {
                CFG.menuManager.updateInGame_ActionInfo_Move();
            }
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
            CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        }
    }
}

