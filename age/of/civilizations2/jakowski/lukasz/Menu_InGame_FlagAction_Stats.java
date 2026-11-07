/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Terrain;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_FlagActionStats;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_FlagAction_Stats
extends SliderMenu {
    private boolean allianceButton = false;

    protected Menu_InGame_FlagAction_Stats() {
        int tempHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tempWidth = Menu_InGame_FlagAction.getWindowWidth() - CFG.PADDING * 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, 0, CFG.PADDING, tempHeight, false));
        menuElements.add(new Text_FlagActionStats(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCitiesSize() > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCity(0).getCityName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("HOME", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName().length() > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("HOME", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()));
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
            }

            @Override
            protected void actionElement(int iID) {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                    if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                        CFG.toast.setInView(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                    }
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                    CFG.viewsManager.disableAllViews();
                }
            }
        });
        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
            this.allianceButton = true;
            menuElements.add(new Text_FlagActionStats("" + CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getAllianceName(), CFG.PADDING, 0){

                @Override
                protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                    super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                    ImageManager.getImage(Images.diplo_alliance).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_alliance).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())) / 2 - ImageManager.getImage(Images.diplo_alliance).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())), (int)((float)ImageManager.getImage(Images.diplo_alliance).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())));
                }

                @Override
                protected int getWidth() {
                    return super.getWidth() + this.getTextPos();
                }

                @Override
                protected int getTextPos() {
                    return CFG.PADDING + (int)((float)ImageManager.getImage(Images.diplo_alliance).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight()));
                }

                @Override
                protected void buildElementHover() {
                    block5: {
                        try {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            for (int i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++i) {
                                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                                nData.clear();
                            }
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                            return;
                        }
                        catch (IndexOutOfBoundsException ex) {
                            if (CFG.LOGS) {
                                CFG.exceptionStack(ex);
                            }
                        }
                        catch (NullPointerException ex) {
                            if (!CFG.LOGS) break block5;
                            CFG.exceptionStack(ex);
                        }
                    }
                    this.menuElementHover = null;
                }
            });
        }
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Provinces") + ": ", "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(), CFG.COLOR_TEXT_NUM_OF_PROVINCES, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.provinces).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.provinces).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())) / 2 - ImageManager.getImage(Images.provinces).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.provinces).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())), (int)((float)ImageManager.getImage(Images.provinces).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.provinces).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                int j;
                int i;
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                ArrayList<Integer> tempTerrainTypes = new ArrayList<Integer>();
                ArrayList<Integer> numOfProvinccesByTerrain = new ArrayList<Integer>();
                for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); ++i) {
                    boolean add = true;
                    for (j = 0; j < tempTerrainTypes.size(); ++j) {
                        if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getTerrainTypeID() != ((Integer)tempTerrainTypes.get(j)).intValue()) continue;
                        add = false;
                        numOfProvinccesByTerrain.set(j, (Integer)numOfProvinccesByTerrain.get(j) + 1);
                        break;
                    }
                    if (!add) continue;
                    tempTerrainTypes.add(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getTerrainTypeID());
                    numOfProvinccesByTerrain.add(1);
                }
                int iSize = tempTerrainTypes.size();
                for (i = 0; i < iSize - 1; ++i) {
                    for (j = i + 1; j < iSize; ++j) {
                        if ((Integer)numOfProvinccesByTerrain.get(i) >= (Integer)numOfProvinccesByTerrain.get(j)) continue;
                        int tempD = (Integer)tempTerrainTypes.get(i);
                        tempTerrainTypes.set(i, (Integer)tempTerrainTypes.get(j));
                        tempTerrainTypes.set(j, tempD);
                        tempD = (Integer)numOfProvinccesByTerrain.get(i);
                        numOfProvinccesByTerrain.set(i, (Integer)numOfProvinccesByTerrain.get(j));
                        numOfProvinccesByTerrain.set(j, tempD);
                    }
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                for (i = 0; i < tempTerrainTypes.size(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color(CFG.terrainTypesManager.getColor((int)((Integer)tempTerrainTypes.get((int)i)).intValue()).r, CFG.terrainTypesManager.getColor((int)((Integer)tempTerrainTypes.get((int)i)).intValue()).g, CFG.terrainTypesManager.getColor((int)((Integer)tempTerrainTypes.get((int)i)).intValue()).b, 1.0f)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Terrain((Integer)tempTerrainTypes.get(i)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName((Integer)tempTerrainTypes.get(i)) + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + numOfProvinccesByTerrain.get(i), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = nElements.size() > 0 ? new MenuElement_Hover_v2(nElements) : null;
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Population") + ": ", CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation()), CFG.COLOR_TEXT_POPULATION, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.population).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.population).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())) / 2 - ImageManager.getImage(Images.population).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.population).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())), (int)((float)ImageManager.getImage(Images.population).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.population).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Army") + ": ", CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits()), CFG.COLOR_TEXT_MODIFIER_NEUTRAL, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.diplo_army).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_army).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())) / 2 - ImageManager.getImage(Images.diplo_army).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_army).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())), (int)((float)ImageManager.getImage(Images.diplo_army).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.diplo_army).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits()), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                int nUpkeep = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + nUpkeep, nUpkeep == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)((float)nUpkeep / (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits() * 100.0f)) / 100.0f, CFG.COLOR_INGAME_GOLD));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerUnit")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0f)) / 100.0f + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeepH1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Economy") + ": ", CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countEconomy()), CFG.COLOR_TEXT_ECONOMY, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.economy).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.economy).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())) / 2 - ImageManager.getImage(Images.economy).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.economy).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())), (int)((float)ImageManager.getImage(Images.economy).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.economy).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Unemployment") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game_NextTurnUpdate.getUnemploymentPopulation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) + " ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.getPercentage(CFG.game_NextTurnUpdate.getUnemploymentPopulation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation(), 4) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Technology") + ": ", "" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_TEXT_TECHNOLOGY, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.technology).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.technology).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())) / 2 - ImageManager.getImage(Images.technology).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.technology).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())), (int)((float)ImageManager.getImage(Images.technology).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.technology).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f + "/" + 2.0f, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech3"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech4"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("AverageDevelopment") + ": " + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + " [" + (int)(CFG.game.countAvarageDevelopmentLevel_Float(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f) + "%]", CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.development).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.development).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())) / 2 - ImageManager.getImage(Images.development).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.development).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())), (int)((float)ImageManager.getImage(Images.development).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.development).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageDevelopment") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + "/" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech4"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech5"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Inflation") + ": ", "" + (float)((int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0f)) / 100.0f + "%", CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0.0f ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.development_down).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.development_down).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())) / 2 - ImageManager.getImage(Images.development_down).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.development_down).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())), (int)((float)ImageManager.getImage(Images.development_down).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.development_down).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Inflation") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + (float)((int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0f)) / 100.0f + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development_down, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("InflationH1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("InflationH2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("WarWeariness") + ": ", "" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0f)) / 100.0f + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.diplo_weariness).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_weariness).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())) / 2 - ImageManager.getImage(Images.diplo_weariness).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_weariness).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())), (int)((float)ImageManager.getImage(Images.diplo_weariness).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.diplo_weariness).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0f)) / 100.0f + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWearinessH1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWearinessH2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWearinessH3"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Happiness") + ": ", "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%", CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness(), 100, 1.0f), CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                try {
                    ImageManager.getImage(CFG.getHappinesImage(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness())).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.happiness).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())) / 2 - ImageManager.getImage(Images.happiness).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.happiness).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())), (int)((float)ImageManager.getImage(Images.happiness).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())));
                }
                catch (IndexOutOfBoundsException ex) {
                    ImageManager.getImage(Images.happiness).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.happiness).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())) / 2 - ImageManager.getImage(Images.happiness).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.happiness).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())), (int)((float)ImageManager.getImage(Images.happiness).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())));
                }
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.happiness).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Stability") + ": ", "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getStability() * 100.0f) + "%", CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getStability() * 100.0f), 100, 1.0f), CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.diplo_popstability).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_popstability).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())) / 2 - ImageManager.getImage(Images.diplo_popstability).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_popstability).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())), (int)((float)ImageManager.getImage(Images.diplo_popstability).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.diplo_popstability).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Stability") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getStability() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("CivRank") + ": ", "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition(), CFG.COLOR_TEXT_NUM_OF_PROVINCES, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                ImageManager.getImage(Images.rank).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.rank).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())) / 2 - ImageManager.getImage(Images.rank).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.rank).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())), (int)((float)ImageManager.getImage(Images.rank).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.rank).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition() + "/" + (CFG.game.getCivsSize() - 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Government") + ": ", "" + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getName(), new Color(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor().r, CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor().g, CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor().b, 1.0f), CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight())) / 2 - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight() + iTranslateY, (int)((float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight())), (int)((float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight())));
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getCrownImageScaled().getHeight()));
            }

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.ideologiesManager.getIdeologyHover(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Difficulty") + ": " + CFG.getDifficultyName(CFG.DIFFICULTY), "", CFG.COLOR_TEXT_MODIFIER_NEUTRAL, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.8f));
                }
                ImageManager.getImage(Images.editor_map).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.editor_map).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())) / 2 - ImageManager.getImage(Images.editor_map).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.editor_map).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())), (int)((float)ImageManager.getImage(Images.editor_map).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())));
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.editor_map).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Difficulty") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getDifficultyName(CFG.DIFFICULTY), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Text_FlagActionStats(CFG.langManager.get("Wiki"), "", CFG.COLOR_TEXT_MODIFIER_NEUTRAL, CFG.PADDING, 0){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.8f));
                }
                ImageManager.getImage(Images.wikipedia).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.wikipedia).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())) / 2 - ImageManager.getImage(Images.wikipedia).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.wikipedia).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())), (int)((float)ImageManager.getImage(Images.wikipedia).getHeight() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())));
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + this.getTextPos();
            }

            @Override
            protected int getTextPos() {
                return CFG.PADDING + (int)((float)ImageManager.getImage(Images.wikipedia).getWidth() * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight()));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getWikiInormationsLink_Clear(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivTag()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivTag();
                CFG.setDialogType(Dialog.GO_TO_WIKI);
            }
        });
        menuElements.add(new Button_Transparent(0, 0, CFG.PADDING, tempHeight, false));
        int tElementsWidth = 0;
        for (int i = 0; i < menuElements.size(); ++i) {
            tElementsWidth += ((MenuElement)menuElements.get(i)).getWidth();
        }
        int tStartX = 0;
        tStartX = (tElementsWidth += CFG.PADDING * 2 * (menuElements.size() - 1)) > tempWidth ? 0 : (tempWidth - tElementsWidth) / 2;
        for (int i = 0; i < menuElements.size(); ++i) {
            ((MenuElement)menuElements.get(i)).setPosX(tStartX);
            tStartX += ((MenuElement)menuElements.get(i)).getWidth() + CFG.PADDING * 2;
        }
        menuElements.add(new Button_Transparent(0, 0, tempWidth - 4, tempHeight, true));
        this.initMenu(null, CFG.PADDING * 2 + 2 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, tempWidth - 4, tempHeight, menuElements, false, false);
    }

    private final float getImageScale(int nImageHeight) {
        return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7f / (float)nImageHeight < 1.0f ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7f / (float)nImageHeight : 1.0f;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + this.getWidth() + 2 - ImageManager.getImage(Images.new_game_top_edge_line).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge_line).getWidth(), this.getHeight(), true, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() / 3);
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.225f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 8, this.getHeight());
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 8, this.getHeight(), true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.9f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() * 4 / 5);
        oSB.setColor(new Color(0.01f, 0.02f, 0.04f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.85f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.8f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() / 4, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() / 4, 1, true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() / 10, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 10 + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() / 10, 1, true, false);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, CFG.PADDING, this.getHeight());
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, CFG.PADDING, this.getHeight(), true, false);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }
}

