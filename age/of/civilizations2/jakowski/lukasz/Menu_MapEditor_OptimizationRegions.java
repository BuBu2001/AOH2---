/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_Regions;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.Slider;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_OptimizationRegions
extends SliderMenu {
    protected static boolean showValues = true;

    protected Menu_MapEditor_OptimizationRegions() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4 - CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Slide(CFG.PADDING + ImageManager.getImage(Images.slide_bg).getHeight() / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - ImageManager.getImage(Images.slide_bg).getHeight() * 2 - ImageManager.getImage(Images.slide_bg).getHeight() / 2, false));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 5 - CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH){

            @Override
            protected boolean getClickable() {
                return Editor_Regions.lUndo.size() > 0;
            }
        });
        menuElements.add(new Button_Game("-", -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Slider(CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4 - CFG.PADDING * 6, CFG.BUTTON_HEIGHT, 0, CFG.game.getRegions().size(), 0){

            @Override
            protected String getDrawText() {
                return "SET TO REGION ID: " + this.getCurrent();
            }
        });
        menuElements.add(new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true, true){

            @Override
            protected boolean getCheckboxState() {
                return showValues;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING * 3 - CFG.BUTTON_HEIGHT * 2, CFG.BUTTON_WIDTH * 2));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(1).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(3).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(7).setText(CFG.langManager.get("ShowValues"));
        this.getMenuElement(8).setText(CFG.langManager.get("Back"));
        this.getMenuElement(9).setText("CENTER TO REGION");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        ImageManager.getImage(Images.editor_line).draw2(oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(3).getPosX() - CFG.PADDING + iTranslateX, iTranslateY, CFG.BUTTON_WIDTH * 5 + CFG.PADDING * 4, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R_Reflected(oSB, this.getMenuElement(9).getPosX() - CFG.PADDING + iTranslateX, this.getMenuElement(9).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(9).getWidth() + CFG.PADDING * 2, this.getMenuElement(9).getHeight() + CFG.PADDING * 2);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                Editor_Regions.saveRegions();
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
                Editor_Regions.popUndo();
                this.getMenuElement(5).setCurrent(Editor_Regions.activeRegion);
                this.getMenuElement(5).setMax(CFG.game.getRegions().size());
                return;
            }
            case 4: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                Editor_Regions.activeRegion = this.getMenuElement(iID + 1).getCurrent();
                this.getMenuElement(5).setMax(CFG.game.getRegions().size());
                this.centerToActiveRegionID();
                break;
            }
            case 5: {
                Editor_Regions.activeRegion = this.getMenuElement(iID).getCurrent();
                this.getMenuElement(5).setMax(CFG.game.getRegions().size());
                this.centerToActiveRegionID();
                break;
            }
            case 6: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                Editor_Regions.activeRegion = this.getMenuElement(iID - 1).getCurrent();
                this.getMenuElement(5).setMax(CFG.game.getRegions().size());
                this.centerToActiveRegionID();
                break;
            }
            case 7: {
                boolean bl = showValues = !showValues;
                if (!showValues) break;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(CFG.game.getRegionID(i));
                }
                break;
            }
            case 8: {
                this.onBackPressed();
                CFG.game.loadRegions();
                break;
            }
            case 9: {
                this.centerToActiveRegionID();
            }
        }
    }

    private final void centerToActiveRegionID() {
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getRegionID(i) != Editor_Regions.activeRegion) continue;
            CFG.map.getMapCoordinates().centerToProvinceID(i);
            CFG.game.setActiveProvinceID(i);
            return;
        }
        CFG.toast.setInView("0 PROVINCES");
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
        CFG.menuManager.setBackAnimation(true);
        Editor_Regions.lUndo.clear();
        CFG.brushTool = false;
        CFG.editorManager.resetInUseEditors();
        Game_Render_Province.updateDrawProvinces();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
        }
    }
}

