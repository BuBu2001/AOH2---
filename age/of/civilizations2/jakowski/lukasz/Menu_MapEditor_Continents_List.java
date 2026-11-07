/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_Continents;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_Continents_List
extends SliderMenu {
    protected Menu_MapEditor_Continents_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (CFG.GAME_WIDTH - CFG.PADDING * 2 - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2 - CFG.PADDING * (CFG.map.getMapContinents().getContinentsSize() - 1)) / CFG.map.getMapContinents().getContinentsSize();
        if (tempWidth < CFG.BUTTON_WIDTH) {
            tempWidth = CFG.BUTTON_WIDTH;
        }
        for (int i = 0; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
            menuElements.add(new Button_Game_Checkbox(CFG.map.getMapContinents().getName(i), -1, CFG.PADDING + tempWidth * i + CFG.PADDING * i, CFG.PADDING, tempWidth, true, false){
                int iCurrent;
                {
                    this.iCurrent = 0;
                }

                @Override
                protected boolean getCheckboxState() {
                    return Editor_Continents.iActiveContinentID == this.getCurrent();
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }

                @Override
                protected int getCurrent() {
                    return this.iCurrent;
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapContinents().getName(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfProvinces") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.countContinentProvinces(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        this.menuElementHover = null;
                    }
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
        }
        this.initMenu(null, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH - (CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2), CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements);
        if (tempWidth < CFG.BUTTON_WIDTH * 2) {
            this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            oSB.setColor(new Color(CFG.map.getMapContinents().getColor((int)i).r, CFG.map.getMapContinents().getColor((int)i).g, CFG.map.getMapContinents().getColor((int)i).b, 1.0f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getWidth() / 2 - this.getMenuElement(i).getTextWidth() / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getWidth() / 2 - this.getMenuElement(i).getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, this.getMenuElement(i).getTextWidth() - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getTextWidth() - CFG.PADDING + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getWidth() / 2 - this.getMenuElement(i).getTextWidth() / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
            oSB.setColor(Color.WHITE);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        Editor_Continents.iActiveContinentID = iID;
    }
}

