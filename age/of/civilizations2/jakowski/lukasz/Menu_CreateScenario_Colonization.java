/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Slider;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Undo_AssignProvinceCiv;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Colonization
extends SliderMenu {
    protected Menu_CreateScenario_Colonization() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, Game_Calendar.ENABLE_COLONIZATION){

            @Override
            protected boolean getCheckboxState() {
                return Game_Calendar.ENABLE_COLONIZATION;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofWastelandProvinces") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                Game_Calendar.ENABLE_COLONIZATION = !Game_Calendar.ENABLE_COLONIZATION;
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES){

            @Override
            protected boolean getCheckboxState() {
                return Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofNeutralProvinces") + "."));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
                Menu_CreateScenario_Colonization.this.updateLanguage();
            }
        });
        menuElements.add(new Slider(null, CFG.BUTTON_WIDTH / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, 0, 100, (int)(Game_Calendar.COLONIZATION_TECH_LEVEL * 100.0f)){

            @Override
            protected String getDrawText() {
                return super.getText() + (float)this.getCurrent() / 100.0f;
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() - CFG.BUTTON_WIDTH / 2 + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.BUTTON_WIDTH);
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                Game_Calendar.COLONIZATION_TECH_LEVEL = (float)this.getCurrent() / 100.0f;
                super.setCurrent(nCurrent);
            }

            @Override
            protected void actionElement(int iID) {
                Game_Calendar.COLONIZATION_TECH_LEVEL = (float)this.getCurrent() / 100.0f;
            }
        });
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
        CFG.lCreateScenario_UndoAssignProvincesCivID = new ArrayList<Undo_AssignProvinceCiv>();
        CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<Integer>();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("ColonizationofWastelandProvinces"));
        this.getMenuElement(1).setText(CFG.langManager.get("NeutralProvinces") + ": " + (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES ? CFG.langManager.get("Colonization") : CFG.langManager.get("Conquering")));
        this.getMenuElement(2).setText(CFG.langManager.get("RequiredTechnologyLevel") + ": ");
    }

    @Override
    protected final void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }
}

