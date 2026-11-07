/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_TerrainType;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_Terrain_List
extends SliderMenu {
    protected Menu_MapEditor_Terrain_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (CFG.GAME_WIDTH - CFG.PADDING * 2 - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2 - CFG.PADDING * (CFG.terrainTypesManager.getTerrainsSize() - 2)) / (CFG.terrainTypesManager.getTerrainsSize() - 1);
        if (tempWidth < CFG.BUTTON_WIDTH) {
            tempWidth = CFG.BUTTON_WIDTH;
        }
        for (int i = 1; i < CFG.terrainTypesManager.getTerrainsSize(); ++i) {
            menuElements.add(new Button_Game_Checkbox(CFG.terrainTypesManager.getName(i), -1, CFG.PADDING + tempWidth * (i - 1) + CFG.PADDING * (i - 1), CFG.PADDING, tempWidth, true, false){
                int iCurrent;
                {
                    this.iCurrent = 0;
                }

                @Override
                protected boolean getCheckboxState() {
                    return Editor_TerrainType.currentTerrainTypeID == this.getCurrent();
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
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (isActive) {
                        CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + CFG.PADDING + CFG.terrainTypesManager.getIcon(this.iCurrent).getWidth() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
                    } else {
                        CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + CFG.PADDING + CFG.terrainTypesManager.getIcon(this.iCurrent).getWidth() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
                    }
                    CFG.terrainTypesManager.getIcon(this.iCurrent).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.terrainTypesManager.getIcon(this.iCurrent).getHeight() / 2 + iTranslateY);
                }

                @Override
                protected int getTextWidth() {
                    return super.getTextWidth() + CFG.PADDING + CFG.terrainTypesManager.getIcon(this.iCurrent).getWidth();
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Terrain") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(this.getCurrent(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        if (CFG.terrainTypesManager.getDefense(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseModifier") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getDefense(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getDefense(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getDefense(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getDefense(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeepModifier") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getMovementCost(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementCostModifier") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getMovementCost(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getMovementCost(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getMovementCost(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getMovementCost(this.getCurrent()) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getBuildCost(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildCostModifier") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBuildCost(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getBuildCost(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getBuildCost(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBuildCost(this.getCurrent()) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) != 0) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseProvinceValue") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) > 0 ? "+" : "") + CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()), CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) != 0.0f) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseDevelopmentLevel") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        this.menuElementHover = null;
                    }
                }

                @Override
                protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElementHover != null) {
                        this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), Touch.getMousePosY());
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
            oSB.setColor(new Color(CFG.terrainTypesManager.getColor((int)(i + 1)).r, CFG.terrainTypesManager.getColor((int)(i + 1)).g, CFG.terrainTypesManager.getColor((int)(i + 1)).b, 1.0f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getWidth() / 2 - this.getMenuElement(i).getTextWidth() / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getWidth() / 2 + CFG.PADDING - this.getMenuElement(i).getTextWidth() / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, this.getMenuElement(i).getTextWidth() - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getWidth() / 2 - this.getMenuElement(i).getTextWidth() / 2 + this.getMenuElement(i).getTextWidth() - CFG.PADDING + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
            oSB.setColor(Color.WHITE);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        Editor_TerrainType.currentTerrainTypeID = iID + 1;
    }
}

