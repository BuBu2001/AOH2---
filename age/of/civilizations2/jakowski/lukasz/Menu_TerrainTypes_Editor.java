/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Terrain_GameData3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.ArrayList;

class Menu_TerrainTypes_Editor
extends SliderMenu {
    protected Menu_TerrainTypes_Editor() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 1; i < CFG.terrainTypesManager.getTerrainsSize(); ++i) {
            menuElements.add(new Button_Menu(CFG.terrainTypesManager.getName(i), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){
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
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getDefense(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getDefense(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getDefense(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getDefense(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeepModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getMilitaryUpkeep(this.getCurrent()) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementCostModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getMovementCost(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getMovementCost(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getMovementCost(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getMovementCost(this.getCurrent()) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getPopulationGrowth(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getEconomyGrowth(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildCostModifier") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBuildCost(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getBuildCost(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getBuildCost(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBuildCost(this.getCurrent()) < 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseProvinceValue") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) > 0 ? "+" : "") + CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()), CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBaseProvinceValue(this.getCurrent()) > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseDevelopmentLevel") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) > 0.0f ? "+" : "") + (int)(CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) * 100.0f) + "%", CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) == 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.terrainTypesManager.getBaseDevelopmentModifier(this.getCurrent()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)));
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
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("AddNewTerrain"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        for (int i = 1; i < this.getMenuElementsSize(); ++i) {
            CFG.terrainTypesManager.getIcon(i).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.terrainTypesManager.getIcon(i).getWidth() / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.terrainTypesManager.getIcon(i).getHeight() / 2 + this.getMenuPosY() + iTranslateY);
            oSB.setColor(new Color(CFG.terrainTypesManager.getColor((int)i).r, CFG.terrainTypesManager.getColor((int)i).g, CFG.terrainTypesManager.getColor((int)i).b, 1.0f));
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 + this.getMenuElement(i).getTextHeight() / 2 + CFG.PADDING + iTranslateY, this.getMenuElement(i).getTextWidth(), CFG.CIV_COLOR_WIDTH);
            oSB.setColor(Color.WHITE);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.editorTerrain_Data2 = new Terrain_GameData3();
                Color tempColor = CFG.getRandomColor();
                CFG.editorTerrain_Data2.setColor(new Color_GameData(tempColor.r, tempColor.g, tempColor.b));
                CFG.menuManager.setViewID(Menu.eTERRAIN_TYPE_ADD);
                break;
            }
            default: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.terrainTypesManager.getTag(iID);
                FileHandle fileData = Gdx.files.internal("game/terrain_types/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                try {
                    CFG.editorTerrain_Data2 = (Terrain_GameData3)CFG.deserialize(fileData.readBytes());
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                CFG.menuManager.setViewID(Menu.eTERRAIN_TYPE_ADD);
            }
        }
        Game_Render_Province.updateDrawProvinces();
    }
}

