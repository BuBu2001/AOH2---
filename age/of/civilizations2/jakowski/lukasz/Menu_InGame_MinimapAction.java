/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MinimapInfo;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_MinimapAction
extends SliderMenu {
    private int animationStepID = 0;
    private int animationChangePosY;
    private int animationChangePosX;
    private boolean closeMenu = false;

    protected Menu_InGame_MinimapAction() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new MinimapInfo(CFG.PADDING, CFG.PADDING, CFG.GAME_WIDTH - CFG.PADDING * 6));
        this.initMenu(new SliderMenuTitle("Map", CFG.BUTTON_HEIGHT / 2, false, false), CFG.PADDING * 2, CFG.GAME_HEIGHT / 2 - ((MenuElement)menuElements.get(0)).getHeight() / 2, CFG.GAME_WIDTH - CFG.PADDING * 4, ((MenuElement)menuElements.get(0)).getHeight() + CFG.PADDING * 2, menuElements, false, true);
    }

    @Override
    protected final void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
        if (this.closeMenu) {
            this.updateChangePosX();
        } else {
            this.updateChangePosY();
        }
        super.draw(oSB, iTranslateX + this.animationChangePosX, this.animationChangePosY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    @Override
    protected final void onBackPressed() {
        this.closeMenu();
    }

    private final void updateChangePosX() {
        switch (this.animationStepID) {
            case 0: 
            case 1: 
            case 12: {
                this.animationChangePosX = (int)((float)this.animationChangePosX + (float)CFG.GAME_WIDTH * 2.5f / 100.0f);
                break;
            }
            case 2: 
            case 3: 
            case 10: 
            case 11: {
                this.animationChangePosX = (int)((float)this.animationChangePosX + (float)CFG.GAME_WIDTH * 5.0f / 100.0f);
                break;
            }
            case 4: 
            case 5: 
            case 8: 
            case 9: {
                this.animationChangePosX = (int)((float)this.animationChangePosX + (float)CFG.GAME_WIDTH * 10.0f / 100.0f);
                break;
            }
            case 6: 
            case 7: {
                this.animationChangePosX = (int)((float)this.animationChangePosX + (float)CFG.GAME_WIDTH * 15.0f / 100.0f);
                break;
            }
            case 13: {
                this.animationChangePosX = CFG.GAME_WIDTH;
            }
        }
        if (CFG.iNumOfFPS < 22) {
            this.animationStepID = 13;
            this.animationChangePosX = CFG.GAME_WIDTH;
        }
        if (this.closeMenu && this.animationStepID == 13) {
            this.animationChangePosX = CFG.GAME_WIDTH;
            super.setVisible(false);
        }
        ++this.animationStepID;
        CFG.setRender_3(true);
    }

    private final void updateChangePosY() {
        switch (this.animationStepID) {
            case 0: 
            case 1: 
            case 12: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT - this.getPosY()) * 2.5f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 2: 
            case 3: 
            case 10: 
            case 11: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT - this.getPosY()) * 5.0f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 4: 
            case 5: 
            case 8: 
            case 9: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT - this.getPosY()) * 10.0f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 6: 
            case 7: {
                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT - this.getPosY()) * 15.0f / 100.0f * (float)(this.closeMenu ? -1 : 1));
                break;
            }
            case 13: {
                this.animationChangePosY = 0;
            }
        }
        if (CFG.iNumOfFPS < 22) {
            this.animationStepID = 13;
            this.animationChangePosY = 0;
        }
        if (this.closeMenu && this.animationStepID == 13) {
            this.animationChangePosY = CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT;
            super.setVisible(false);
        }
        ++this.animationStepID;
        CFG.setRender_3(true);
    }

    private final void closeMenu() {
        this.closeMenu = true;
        this.resetAnimation();
    }

    @Override
    protected final void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
        }
        if (!visible && this.closeMenu) {
            super.setVisible(visible);
        }
        this.closeMenu = !visible;
        this.resetAnimation();
    }

    private final void resetAnimation() {
        this.animationStepID = 0;
        if (!this.closeMenu) {
            this.animationChangePosY = CFG.GAME_HEIGHT - this.getPosY();
        }
        this.animationChangePosX = 0;
    }
}

