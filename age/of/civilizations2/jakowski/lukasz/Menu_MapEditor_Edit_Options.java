/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Editors;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_SelectMapType_Scale;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_Edit_Options
extends SliderMenu {
    protected Menu_MapEditor_Edit_Options() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 6, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 5, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 7 + CFG.PADDING * 8, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 9 + CFG.PADDING * 10, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 10 + CFG.PADDING * 11, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 11 + CFG.PADDING * 12, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 14 + CFG.PADDING * 15, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, CFG.getFileNames_Length("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/") > 0){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BasedOnAllScenarios") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 16 + CFG.PADDING * 17, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, CFG.getFileNames_Length("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "sea_routes/") > 0));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 12 + CFG.PADDING * 13, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 15 + CFG.PADDING * 16, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, CFG.getFileNames_Length("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "predefined_borders/") > 0){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BasedOnAllScenarios") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 9, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 7, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 17 + CFG.PADDING * 18, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 13 + CFG.PADDING * 14, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("EditTerrainTypes"));
        this.getMenuElement(1).setText(CFG.langManager.get("ContinentsEditor"));
        this.getMenuElement(2).setText(CFG.langManager.get("GrowthRateEditor"));
        this.getMenuElement(3).setText(CFG.langManager.get("ArmyPositionEditor"));
        this.getMenuElement(4).setText(CFG.langManager.get("SeaArmyBoxesEditor"));
        this.getMenuElement(5).setText(CFG.langManager.get("OptimizationRegionsEditor"));
        this.getMenuElement(6).setText(CFG.langManager.get("WastelandMapsEditor"));
        this.getMenuElement(7).setText(CFG.langManager.get("GenerateSuggestedCivilizations"));
        this.getMenuElement(8).setText(CFG.langManager.get("GenerateSeaRoutes"));
        this.getMenuElement(9).setText(CFG.map.getMapName(CFG.map.getActiveMapID()) + " - " + CFG.langManager.get("Scale") + ": " + CFG.map.getMapScale(CFG.map.getActiveMapID()));
        this.getMenuElement(10).setText(CFG.langManager.get("EditConnectionsAndProvinces"));
        this.getMenuElement(11).setText(CFG.langManager.get("EditProvinceBackground"));
        this.getMenuElement(12).setText(CFG.langManager.get("EditSeaProvinces"));
        this.getMenuElement(13).setText(CFG.langManager.get("GeneratePreDefinedBorders"));
        this.getMenuElement(14).setText(CFG.langManager.get("PortPositionEditor"));
        this.getMenuElement(15).setText(CFG.langManager.get("RegionsEditor"));
        this.getMenuElement(16).setText(CFG.langManager.get("PrintAMap"));
        this.getMenuElement(17).setText(CFG.langManager.get("FormableCivilizations"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getMenuElement(9).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX, this.getMenuElement(9).getPosY() + this.getMenuElement(9).getHeight() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() / 2 + this.getMenuPosY() + iTranslateY);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawText(oSB, CFG.sAUTHOR + ": " + CFG.map.getMapAuthor(CFG.map.getActiveMapID()), this.getMenuElement(9).getTextPos() + this.getMenuElement(9).getTextWidth() + CFG.PADDING + iTranslateX, this.getMenuElement(9).getPosY() + this.getMenuElement(9).getHeight() / 2 + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.6f) + this.getMenuPosY() + iTranslateY, CFG.COLOR_BUTTON_EXTRA_DESCRIPTION);
        CFG.fontMain.getData().setScale(1.0f);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                }
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_TERRAIN);
                CFG.editorManager.setInUse(Editors.eTERRAINS);
                Game_Render_Province.updateDrawProvinces();
                return;
            }
            case 1: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CONTINENTS);
                CFG.editorManager.setInUse(Editors.ePROVINCE_CONTINENTS);
                Game_Render_Province.updateDrawProvinces();
                return;
            }
            case 2: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_GROWTH_RATE);
                CFG.editorManager.setInUse(Editors.eGROWTH_RATE);
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth("" + (int)(CFG.game.getProvince(i).getGrowthRate_Population() * 100.0f) + "%");
                }
                Game_Render_Province.updateDrawProvinces();
                return;
            }
            case 3: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_POSITION);
                CFG.editorManager.setInUse(Editors.eSHIFT_ARMY);
                Game_Render_Province.updateDrawProvinces();
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
                }
                return;
            }
            case 4: {
                CFG.RELOAD_SCENARIO = true;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    if (!CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getCivsSize() != 1) continue;
                    CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(i, true);
                    CFG.game.getProvince(i).addArmy(1, i);
                    CFG.game.getProvince(i).updateDrawArmy();
                }
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES);
                return;
            }
            case 5: {
                int i;
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                }
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_REGIONS);
                CFG.editorManager.setInUse(Editors.ePROVINCE_REGIONS);
                Game_Render_Province.updateDrawProvinces();
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(CFG.game.getRegionID(i));
                }
                return;
            }
            case 6: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_WASTELAND_MAPS);
                return;
            }
            case 7: {
                CFG.setDialogType(Dialog.GENERATE_SUGGESTED_OWNERS);
                return;
            }
            case 8: {
                CFG.setDialogType(Dialog.GENERATE_SEA_ROUTES);
                this.getMenuElement(iID).setCheckboxState(CFG.getFileNames_Length("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "sea_routes/") > 0);
                return;
            }
            case 9: {
                CFG.backToMenu = Menu.eMAP_EDITOR_EDIT;
                CFG.goToMenu = Menu.eMAP_EDITOR_EDIT;
                Menu_SelectMapType_Scale.MAP_ID_TO_LOAD = CFG.map.getActiveMapID();
                CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE_SCALE);
                return;
            }
            case 10: {
                CFG.VIEW_SHOW_VALUES = true;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
                }
                CFG.editorManager.setInUse(Editors.eNEIGHBORING_PROVINCES);
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_CONNECTIONS);
                return;
            }
            case 11: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 0;
                CFG.VIEW_SHOW_VALUES = true;
                CFG.map.getMapScale().setScaleBeforeReset(8.0f);
                CFG.editorManager.setInUse(Editors.ePROVINCE_TEXTURE);
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PROVINCE_BACKGROUND);
                return;
            }
            case 12: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(CFG.game.getProvince(i).getLevelOfPort());
                }
                CFG.editorManager.setInUse(Editors.eLEVEL_OF_PORT);
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_SEA_PROVINCES);
                return;
            }
            case 13: {
                CFG.setDialogType(Dialog.GENERATE_PRE_DEFINED_BORDERS);
                return;
            }
            case 14: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PORT_POSITION);
                CFG.editorManager.setInUse(Editors.eSHIFT_PORT);
                Game_Render_Province.updateDrawProvinces();
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getLevelOfPort() < 0) continue;
                    CFG.game.getProvince(i).setLevelOfPort(1);
                }
                return;
            }
            case 15: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_REGIONS);
                CFG.editorManager.setInUse(Editors.ePROVINCE_MAP_REGIONS);
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                    if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getLevelOfPort() < 0) continue;
                    CFG.game.getProvince(i).setLevelOfPort(1);
                }
                Game_Render_Province.updateDrawProvinces();
                return;
            }
            case 16: {
                return;
            }
            case 17: {
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).setWasteland(-1);
                }
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS);
                return;
            }
        }
    }
}

