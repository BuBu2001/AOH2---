/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Descripted;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor
extends SliderMenu {
    protected Menu_MapEditor() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 0; i < CFG.map.getNumOfMaps(); ++i) {
            menuElements.add(new Button_Menu_Descripted(CFG.map.getMapAuthor(i), CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(i), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, CFG.map.getActiveMapID() == i));
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("MapEditor") + ": " + CFG.langManager.get("Download"));
        this.getTitle().setText(CFG.langManager.get("MapEditor"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getIcon(0).draw(oSB, this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.map.getIcon(0).getHeight() - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        for (int i = 0; i < CFG.map.getNumOfMaps(); ++i) {
            if (!this.getMenuElement(i + 2).getIsInView()) continue;
            CFG.map.getIcon(i).draw(oSB, this.getMenuElement(i + 2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i + 2).getPosY() + this.getMenuElement(i + 2).getHeight() / 2 - CFG.map.getIcon(i).getHeight() - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.GO_TO_LINK = "http://www.ageofcivilizationsgame.com/map_editor.html";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
        }
        if (CFG.map.getActiveMapID() != iID - 2) {
            CFG.RELOAD_SCENARIO = true;
            CFG.FOG_OF_WAR = 1;
            Game_Render_Province.updateDrawProvinces();
            CFG.map.setActiveMapID(iID - 2);
            CFG.goToMenu = Menu.eMAP_EDITOR_EDIT;
            CFG.menuManager.setViewIDWithoutAnimation(Menu.eLOAD_MAP);
        } else {
            CFG.RELOAD_SCENARIO = true;
            CFG.FOG_OF_WAR = 1;
            Game_Render_Province.updateDrawProvinces();
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eEDITOR);
        CFG.menuManager.setBackAnimation(true);
    }
}

