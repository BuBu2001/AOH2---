/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_Continents;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_Continents
extends SliderMenu {
    protected Menu_MapEditor_Continents() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Slide(CFG.PADDING + ImageManager.getImage(Images.slide_bg).getHeight() / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - ImageManager.getImage(Images.slide_bg).getHeight() * 2 - ImageManager.getImage(Images.slide_bg).getHeight() / 2, false));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 - CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH){

            @Override
            protected boolean getClickable() {
                return Editor_Continents.lUndo.size() > 0;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(1).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(3).setText(CFG.langManager.get("Undo"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getMenuElement(0).getWidth() + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(3).getPosX() - CFG.PADDING + iTranslateX, iTranslateY, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
                this.getMenuElement(iID + 1).setVisible(CFG.brushTool);
                return;
            }
            case 3: {
                Editor_Continents.popUndo();
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        CFG.menuManager.setBackAnimation(true);
        Editor_Continents.lUndo.clear();
        CFG.brushTool = false;
        CFG.editorManager.resetInUseEditors();
        Game_Render_Province.updateDrawProvinces();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
        }
    }
}

