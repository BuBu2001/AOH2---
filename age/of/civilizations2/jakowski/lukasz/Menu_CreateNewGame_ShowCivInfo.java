/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_ShowMenu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Civilization_Info;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateNewGame_ShowCivInfo
extends SliderMenu {
    protected Menu_CreateNewGame_ShowCivInfo() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_ShowMenu(0, 0, CFG.BUTTON_WIDTH * 3 / 5, CFG.BUTTON_WIDTH * 3 / 5, true));
        this.initMenu(null, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 / 5, ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f), CFG.BUTTON_WIDTH * 3 / 5, CFG.BUTTON_WIDTH * 3 / 5 + 1, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0f));
            CFG.setRender_3(true);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.menuManager.setVisible_CreateNewGame_CivInfo(true);
            }
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible && !this.getVisible()) {
            Menu_Civilization_Info.lTime = System.currentTimeMillis();
        }
        super.setVisible(visible);
    }
}

