/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_WastelandMap_Continents
extends SliderMenu {
    protected Menu_CreateScenario_WastelandMap_Continents() {
        int i;
        ArrayList lSortedIDs = new ArrayList();
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
        for (i = 0; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
            if (i == CFG.map.getMapContinents().getOceanContinentID()) continue;
            menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, true, true){
                int iCurrent;
                {
                    this.iCurrent = 0;
                }

                @Override
                protected int getCurrent() {
                    return this.iCurrent;
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapContinents().getName(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfProvinces") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countContinentProvinces(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            tempIDs.add(i);
        }
        while (tempIDs.size() > 0) {
            int nMinID = 0;
            for (int i2 = 1; i2 < tempIDs.size(); ++i2) {
                if (!CFG.compareAlphabetic_TwoString(CFG.map.getMapContinents().getName((Integer)tempIDs.get(nMinID)), CFG.map.getMapContinents().getName((Integer)tempIDs.get(i2)))) continue;
                nMinID = i2;
            }
            lSortedIDs.add(tempIDs.get(nMinID));
            tempIDs.remove(nMinID);
        }
        for (i = 0; i < menuElements.size(); ++i) {
            ((MenuElement)menuElements.get(i)).setText(CFG.map.getMapContinents().getName((Integer)lSortedIDs.get(i)));
            ((MenuElement)menuElements.get(i)).setCurrent((Integer)lSortedIDs.get(i));
        }
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.bg_game_action).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 - ImageManager.getImage(Images.bg_game_action).getHeight() + iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1, this.getHeight() + 1, true, false);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        int i;
        int numOfWastelandProvinces = 0;
        int numOfNormalProvinces = 0;
        int chosenContinent = this.getMenuElement(iID).getCurrent();
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getContinent() != chosenContinent) continue;
            if (CFG.game.getProvince(i).getWasteland() >= 0) {
                ++numOfWastelandProvinces;
                continue;
            }
            ++numOfNormalProvinces;
        }
        this.getMenuElement(iID).setCheckboxState(numOfWastelandProvinces > numOfNormalProvinces);
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getContinent() != chosenContinent) continue;
            CFG.game.getProvince(i).setWasteland(numOfWastelandProvinces < numOfNormalProvinces ? 0 : -1);
        }
        CFG.game.buildWastelandLevels();
        CFG.toast.setInView(this.getMenuElement(iID).getText());
    }
}

