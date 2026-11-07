package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class ViewsManager {
   private List<View_Type> lViews;
   private int iActiveViewID = -1;
   protected static int VIEW_POPULATION_MODE = -1;
   protected static int VIEW_POPULATION2_MODE = -1;
   protected static int VIEW_POPULATION_OF_CIV_MODE = -1;
   protected static int VIEW_ECONOMY_MODE = -1;
   protected static int VIEW_CONTINENT_MODE = -1;
   protected static int VIEW_REGIONS_MODE = -1;
   protected static int VIEW_TERRAIN_TYPE_MODE = -1;
   protected static int VIEW_GROWTH_RATE_MODE = -1;
   protected static int VIEW_SUPPLIES_MODE = -1;
   protected static int VIEW_DEVELOPMENT_MODE = -1;
   protected static int VIEW_TECHNOLOGY_MODE = -1;
   protected static int VIEW_DIPLOMACY_MODE = -1;
   protected static int VIEW_PROVINCE_VALUE_MODE = -1;
   protected static int VIEW_IDEOLOGIES_MODE = -1;
   protected static int VIEW_DISTANCE_MODE = -1;
   protected static int VIEW_INCOME_MODE = -1;
   protected static int VIEW_HAPPINESS_MODE = -1;
   protected static int VIEW_REVOLUTION_MODE = -1;
   protected static int VIEW_PROVINCE_STABILITY_MODE = -1;
   protected static int VIEW_ARMY_MODE = -1;
   protected static int VIEW_CORES_MODE = -1;
   protected static int VIEW_BUILDINGS_MODE = -1;
   protected static int VIEW_LEVEL_OF_PORT_MODE = -1;
   protected static int VIEW_LEVEL_OF_FORTIFICATIONS_MODE = -1;
   protected static int VIEW_LEVEL_OF_WATCH_TOWER_MODE = -1;
   protected static int VIEW_ALLIANCES_MODE = -1;
   protected static int VIEW_IMPERIAL_MODE = -1;
   protected static int VIEW_RECRUITABLE_ARMY_MODE = -1;
   protected static int VIEW_DEFENSIVE_POSITION_MODE = -1;
   protected static int VIEW_AI_POTENTIAL_MODE = -1;
   protected static int VIEW_AI_DANGER_MODE = -1;
   protected static int VIEW_BALANCE_MODE = -1;
   protected static int VIEW_TRUE_OWNERS_MODE = -1;
   protected static int VIEW_DISEASES_MODE = -1;
   protected boolean viewConfig = false;
   protected static int POPULATION_MAX = 1;
   protected static int POPULATION_OF_CIVID = 0;
   protected static int ECONOMY_MAX = 1;

   protected ViewsManager() {
      this.lViews = new ArrayList<>();
      VIEW_DIPLOMACY_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     int nActiveCivID = 0;
                     if (CFG.game.getActiveProvinceID() < 0
                        || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                        || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() <= 0
                        || CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(CFG.game.getActiveProvinceID())) {
                        nActiveCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                     } else {
                        nActiveCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0
                        && nActiveCivID != CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) {
                        if ((int)CFG.game.getCivRelation_OfCivB(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) == -100
                           )
                         {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, 0, 0));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), 0, CFG.PADDING
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.langManager.get("AtWar"),
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                                    1.0F
                                 )
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else if (CFG.game.getCivsAreAllied(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID())) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, 0, 0));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_alliance, CFG.PADDING, CFG.PADDING));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), 0, CFG.PADDING
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Ally")));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else if (CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getPuppetOfCivID() == nActiveCivID
                           )
                         {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassal") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getIdeologyID(), CFG.PADDING, 0
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else if (CFG.game.getCiv(nActiveCivID).getPuppetOfCivID() == CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()
                           )
                         {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Lord") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Ideology(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getIdeologyID(), CFG.PADDING, 0
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == nActiveCivID) {
                           int nOpinion = (int)CFG.game
                              .getCivRelation_OfCivB(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), nActiveCivID);
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 (nOpinion > 0 ? "+" : "") + nOpinion,
                                 nOpinion > 0
                                    ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                                    : (nOpinion == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else {
                           int nOpinion = (int)CFG.game
                              .getCivRelation_OfCivB(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID());
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 (nOpinion > 0 ? "+" : "") + nOpinion,
                                 nOpinion > 0
                                    ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                                    : (nOpinion == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getCivTruce(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, 0, 0));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, CFG.PADDING));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), 0, CFG.PADDING
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Truce")));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getDefensivePact(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, 0, 0));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_defensive_pact, CFG.PADDING, CFG.PADDING));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), 0, CFG.PADDING
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefensivePact")));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getCivNonAggressionPact(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, 0, 0));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_defensive_pact, CFG.PADDING, CFG.PADDING));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), 0, CFG.PADDING
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NonAggressionPact")));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getGuarantee(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GuaranteeTheirIndependence")));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_guarantee_gives, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getGuarantee(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), nActiveCivID) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GuaranteeIndependence")));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID, CFG.PADDING, CFG.PADDING));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(nActiveCivID).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_guarantee_has, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getMilitaryAccess(nActiveCivID, CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("HaveMilitaryAccess")));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        if (CFG.game.getMilitaryAccess(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), nActiveCivID) > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(nActiveCivID));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GivesMilitaryAccess")));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     }
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var5) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               if (CFG.chooseProvinceMode) {
                  CFG.game.resetChooseProvinceData();
               }
   
               CFG.game.updateActiveProvinceBorderStyle();
               if (CFG.menuManager.getInGameView() && (!CFG.menuManager.getVisible_InGame_CivInfo() || Menu_InGame_CivInfo.hideAnimation)) {
                  CFG.menuManager.setVisible_InGame_CivInfo(true);
               }
   
               if (CFG.getActiveCivInfo() > 0) {
                  if (CFG.FOG_OF_WAR == 2) {
                     CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.getActiveCivInfo(), 0);
                  } else {
                     CFG.game.enableDrawCivilizationRegions(CFG.getActiveCivInfo(), 0);
                  }
               }
            }
   
            @Override
            protected void disableViewAction() {
               CFG.game.updateActiveProvinceBorderStyle();
               CFG.game.disableDrawCivilizationRegions_Active();
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_CivInfo()) {
                  CFG.menuManager.setVisible_InGame_CivInfo(false);
               }
   
               if (CFG.game.getActiveProvinceID() >= 0 && !CFG.chooseProvinceMode && CFG.chosenProvinceID < 0) {
                  int tempAct = CFG.game.getActiveProvinceID();
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(tempAct);
               }
            }
   
            @Override
            protected void updateActiveCivInfo_ExtraAction(int newCivID) {
               CFG.game.disableDrawCivilizationRegions_Active();
               if (newCivID > 0) {
                  if (CFG.FOG_OF_WAR == 2) {
                     CFG.game.enableDrawCivilizationRegions_FogOfWar(newCivID, 0);
                  } else {
                     CFG.game.enableDrawCivilizationRegions(newCivID, 0);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               Game_Render_Province.drawOccupiedProvinces_FogOfWar(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               } else {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               Game_Render_Province.drawOccupiedProvinces(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_Just_OnlyCapitalMode(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               } else {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  int nActiveCivID = 0;
                  if (CFG.game.getActiveProvinceID() >= 0
                     && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                     && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                     && CFG.getMetProvince(CFG.game.getActiveProvinceID())) {
                     nActiveCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                  } else {
                     nActiveCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                  }
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        if (!CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                        } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == nActiveCivID) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if ((int)CFG.game.getCivRelation_OfCivB(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) == -100
                           )
                         {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID() == nActiveCivID) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCiv(nActiveCivID).getPuppetOfCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) {
                           oSB.setColor(
                              CFG.getColorStep(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 50,
                                 100,
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCiv(nActiveCivID).getAllianceID() > 0
                           && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()
                              == CFG.game.getCiv(nActiveCivID).getAllianceID()) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCivTruce(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(CFG.getTruceColor(CFG.ALPHA_DIPLOMACY));
                        } else if (CFG.game.getDefensivePact(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getGuarantee(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(
                              CFG.getColorStep(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 50,
                                 100,
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getGuarantee(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID) > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID) > 0) {
                           oSB.setColor(
                              CFG.getPactColor(
                                 CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getMilitaryAccess(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else {
                           int tempRelation = 0;
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == nActiveCivID) {
                              tempRelation = (int)CFG.game
                                 .getCivRelation_OfCivB(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID);
                           } else {
                              tempRelation = (int)CFG.game
                                 .getCivRelation_OfCivB(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID());
                           }
      
                           tempRelation = Math.min(tempRelation, 99);
                           if (tempRelation == 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 CFG.getRelationColor(
                                    tempRelation,
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * ((float)Math.abs(tempRelation) / 100.0F)
                                 )
                              );
                           }
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else if (!CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  int nActiveCivID = 0;
                  if (CFG.game.getActiveProvinceID() >= 0
                     && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                     && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                     nActiveCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                  } else {
                     nActiveCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                  }
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == nActiveCivID) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if ((int)CFG.game.getCivRelation_OfCivB(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) == -100
                           )
                         {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID() == nActiveCivID) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCiv(nActiveCivID).getPuppetOfCivID() == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) {
                           oSB.setColor(
                              CFG.getColorStep(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_VASSAL.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 50,
                                 100,
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCiv(nActiveCivID).getAllianceID() > 0
                           && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()
                              == CFG.game.getCiv(nActiveCivID).getAllianceID()) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCivTruce(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(CFG.getTruceColor(CFG.ALPHA_DIPLOMACY));
                        } else if (CFG.game.getDefensivePact(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getGuarantee(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(
                              CFG.getColorStep(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_OWN_PROVINCES.getB(),
                                    CFG.ALPHA_DIPLOMACY
                                 ),
                                 50,
                                 100,
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getGuarantee(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID) > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_INDEPENDENCE.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID) > 0) {
                           oSB.setColor(
                              CFG.getPactColor(
                                 CFG.game.getCivNonAggressionPact(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else if (CFG.game.getMilitaryAccess(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()) > 0) {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_MILITARY_ACCESS.getB(),
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        } else {
                           int tempRelation = 0;
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == nActiveCivID) {
                              tempRelation = (int)CFG.game
                                 .getCivRelation_OfCivB(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), nActiveCivID);
                           } else {
                              tempRelation = (int)CFG.game
                                 .getCivRelation_OfCivB(nActiveCivID, CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID());
                           }
      
                           tempRelation = Math.min(tempRelation, 99);
                           if (tempRelation == 0) {
                              oSB.setColor(
                                 new Color(
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                    CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 CFG.getRelationColor(
                                    tempRelation,
                                    CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F + CFG.ALPHA_DIPLOMACY * 2.0F / 5.0F * ((float)Math.abs(tempRelation) / 100.0F)
                                 )
                              );
                           }
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
      );
      VIEW_POPULATION_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getPopulationData().getPopulation()),
                           CFG.COLOR_TEXT_POPULATION
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxPopulation();
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_View(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_Just(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_Just(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getPopulationColor(
                                 (int)(
                                    (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                       / (float)ViewsManager.POPULATION_MAX
                                       * 100.0F
                                 ),
                                 0.6F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getColorStep_WithAlpha(
                                 CFG.game
                                    .getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                    .getRGB((float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F),
                                 CFG.getPopulationColor(
                                    (int)(
                                       (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                          / (float)ViewsManager.POPULATION_MAX
                                          * 100.0F
                                    ),
                                    0.6F
                                 ),
                                 tempStepID,
                                 250
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(
                           CFG.getPopulationColor(
                              (int)(
                                 (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                    / (float)ViewsManager.POPULATION_MAX
                                    * 100.0F
                              ),
                              0.6F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(
                           CFG.getColorStep_WithAlpha(
                              CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                 .getRGB((float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F),
                              CFG.getPopulationColor(
                                 (int)(
                                    (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                       / (float)ViewsManager.POPULATION_MAX
                                       * 100.0F
                                 ),
                                 0.6F
                              ),
                              tempStepID,
                              250
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
      );
      VIEW_CORES_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getCivsSize() > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cores") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
   
                     for(int i = 0; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getCivsSize(); ++i) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getCivID(i), 0, 0)
                        );
                     }
   
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
   
                     for(int i = 0; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getCivsSize(); ++i) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getCivID(i))
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(
                                    "ConsideredAsItsCoreProvinceSince",
                                    Game_Calendar.getDate_ByTurnID(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getSinceTurnID(i))
                                 )
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              " ["
                                 + CFG.langManager.get("Turn")
                                 + ": "
                                 + CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getSinceTurnID(i)
                                 + "]",
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     for(int i = 0; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivsSize(); ++i) {
                        if (!CFG.game
                           .getProvince(CFG.menuManager.getHoveredProvinceID())
                           .getCore()
                           .getHaveACore(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivID(i))) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivID(i)
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CoreConstruction") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 ""
                                    + Math.min(
                                       (int)(
                                          (float)CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_NumOfTurns(i)
                                             / (float)CFG.game
                                                .getProvince(CFG.menuManager.getHoveredProvinceID())
                                                .getCore()
                                                .getNumOfTurnsOwnershipToGetACore()
                                             * 100.0F
                                       ),
                                       99
                                    )
                                    + "%",
                                 CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 " "
                                    + Game_Calendar.getDate_ByTurnID(
                                       Game_Calendar.TURN_ID
                                          + Math.max(
                                             1,
                                             CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                          )
                                    ),
                                 CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 " ["
                                    + CFG.langManager
                                       .get(
                                          "TurnsX",
                                          Math.max(
                                             1,
                                             CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                          )
                                       )
                                    + "]",
                                 CFG.COLOR_TEXT_RANK_HOVER
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     }
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivsSize() > 0) {
                     for(int i = 0; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivsSize(); ++i) {
                        if (!CFG.game
                           .getProvince(CFG.menuManager.getHoveredProvinceID())
                           .getCore()
                           .getHaveACore(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivID(i))) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_CivID(i)
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CoreConstruction") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 ""
                                    + Math.min(
                                       (int)(
                                          (float)CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCore().getOwnership_NumOfTurns(i)
                                             / (float)CFG.game
                                                .getProvince(CFG.menuManager.getHoveredProvinceID())
                                                .getCore()
                                                .getNumOfTurnsOwnershipToGetACore()
                                             * 100.0F
                                       ),
                                       99
                                    )
                                    + "%",
                                 CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 " "
                                    + Game_Calendar.getDate_ByTurnID(
                                       Game_Calendar.TURN_ID
                                          + Math.max(
                                             1,
                                             CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                          )
                                    ),
                                 CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 " ["
                                    + CFG.langManager
                                       .get(
                                          "TurnsX",
                                          Math.max(
                                             1,
                                             CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                          )
                                       )
                                    + "]",
                                 CFG.COLOR_TEXT_RANK_HOVER
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     }
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NoCores"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(0, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
            }
   
            @Override
            protected void setActiveProvinceAction() {
               try {
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() != CFG.getActiveCivInfo()) {
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() == 0) {
                        CFG.setActiveCivInfo(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                     } else {
                        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
                     }
                  }
               } catch (IndexOutOfBoundsException var2) {
                  CFG.exceptionStack(var2);
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_Just(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  int nActiveCivID = 0;
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                     || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() <= 0
                     || CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(CFG.game.getActiveProvinceID())) {
                     nActiveCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                  } else {
                     nActiveCivID = CFG.getActiveCivInfo();
                  }
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getHaveACore(nActiveCivID)) {
                           oSB.setColor(
                              new Color(
                                 (float)CFG.game.getCiv(nActiveCivID).getR() / 255.0F,
                                 (float)CFG.game.getCiv(nActiveCivID).getG() / 255.0F,
                                 (float)CFG.game.getCiv(nActiveCivID).getB() / 255.0F,
                                 (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                              )
                           );
                        } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getHaveOwnership(nActiveCivID)) {
                           oSB.setColor(
                              new Color(
                                 (float)CFG.game.getCiv(nActiveCivID).getR() / 255.0F,
                                 (float)CFG.game.getCiv(nActiveCivID).getG() / 255.0F,
                                 (float)CFG.game.getCiv(nActiveCivID).getB() / 255.0F,
                                 (float)CFG.settingsManager.PROVINCE_ALPHA
                                    / 255.0F
                                    * Math.min(
                                       (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getNumOfOwnership(nActiveCivID)
                                          / (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getNumOfTurnsOwnershipToGetACore(),
                                       1.0F
                                    )
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  int nActiveCivID = 0;
                  if (CFG.game.getActiveProvinceID() < 0
                     || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                     || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() <= 0
                     || CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(CFG.game.getActiveProvinceID())) {
                     nActiveCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                  } else {
                     nActiveCivID = CFG.getActiveCivInfo();
                  }
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getHaveACore(nActiveCivID)) {
                        oSB.setColor(
                           new Color(
                              (float)CFG.game.getCiv(nActiveCivID).getR() / 255.0F,
                              (float)CFG.game.getCiv(nActiveCivID).getG() / 255.0F,
                              (float)CFG.game.getCiv(nActiveCivID).getB() / 255.0F,
                              (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getHaveOwnership(nActiveCivID)) {
                        oSB.setColor(
                           new Color(
                              (float)CFG.game.getCiv(nActiveCivID).getR() / 255.0F,
                              (float)CFG.game.getCiv(nActiveCivID).getG() / 255.0F,
                              (float)CFG.game.getCiv(nActiveCivID).getB() / 255.0F,
                              (float)CFG.settingsManager.PROVINCE_ALPHA
                                 / 255.0F
                                 * Math.min(
                                    (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getNumOfOwnership(nActiveCivID)
                                       / (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCore().getNumOfTurnsOwnershipToGetACore(),
                                    1.0F
                                 )
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_ALLIANCES_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Color(
                           new Color(
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getR(),
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getG(),
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getB(),
                              1.0F
                           )
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game
                              .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID())
                              .getAllianceName(),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Formation") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + Game_Calendar.getDate_ByTurnID(
                                 CFG.game
                                    .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID())
                                    .getFormationTurnID()
                              ),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Members") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getAllianceID())
                                 .getCivilizationsSize(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxPopulation();
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllianceMode_FlagAndCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_AlliancesMode_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawAllianceMode_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllianceMode_FlagAndCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_AlliancesMode(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawAllianceMode_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))
                        && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID() > 0) {
                        oSB.setColor(
                           new Color(
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getR(),
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getG(),
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getB(),
                              CFG.ALPHA_DIPLOMACY
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID() > 0) {
                        oSB.setColor(
                           new Color(
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getR(),
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getG(),
                              CFG.game
                                 .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID())
                                 .getColorOfAlliance()
                                 .getB(),
                              CFG.ALPHA_DIPLOMACY
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_SUPPLIES_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivsSize() > 1
                        && CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.menuManager.getHoveredProvinceID())) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        for(int i = 1; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivsSize(); ++i) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(i)));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(i)).getCivName()
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     } else {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                           return null;
                        }
   
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_Just(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() <= 0
                        || !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsSupplied()) {
                        oSB.setColor(
                           new Color(
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE.r,
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE.g,
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE.b,
                              CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r,
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g,
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b,
                              CFG.ALPHA_DIPLOMACY
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsSupplied()) {
                           oSB.setColor(
                              new Color(
                                 CFG.COLOR_TEXT_MODIFIER_POSITIVE.r,
                                 CFG.COLOR_TEXT_MODIFIER_POSITIVE.g,
                                 CFG.COLOR_TEXT_MODIFIER_POSITIVE.b,
                                 CFG.ALPHA_DIPLOMACY * 3.0F / 5.0F
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r,
                                 CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g,
                                 CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b,
                                 CFG.ALPHA_DIPLOMACY
                              )
                           );
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_INCOME_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     && CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getPuppetOfCivID()
                        != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Taxation") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(CFG.menuManager.getHoveredProvinceID())),
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Production") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getProvinceIncome_Production(CFG.menuManager.getHoveredProvinceID())),
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AdministrationCost") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.getNumberWithSpaces(
                                 ""
                                    + (int)CFG.game_NextTurnUpdate
                                       .getProvinceAdministration(
                                          CFG.menuManager.getHoveredProvinceID(),
                                          CFG.game_NextTurnUpdate
                                             .getAdministration_Capital(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID())
                                       )
                              ),
                           CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     int tTotal = (int)CFG.game_NextTurnUpdate.getProvinceIncomeAndExpenses_Total(CFG.menuManager.getHoveredProvinceID());
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Balance") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.getNumberWithSpaces("" + tTotal),
                           tTotal > 0
                              ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                              : (tTotal == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxIncome();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewIncome(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewIncome(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_IncomeMapMode(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawIcnomeMapMode_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_IncomeMapMode(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawIcnomeMapMode_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                              != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else {
                           oSB.setColor(
                              CFG.getColorStep(
                                 CFG.COLOR_TEXT_HAPPINESS_MIN,
                                 CFG.COLOR_TEXT_HAPPINESS_MAX,
                                 CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getBalance_LastTurn(),
                                 ViewsManager.POPULATION_MAX,
                                 0.6F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                           == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        oSB.setColor(
                           CFG.getColorStep(
                              CFG.COLOR_TEXT_HAPPINESS_MIN,
                              CFG.COLOR_TEXT_HAPPINESS_MAX,
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getBalance_LastTurn(),
                              ViewsManager.POPULATION_MAX,
                              0.6F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
      );
      VIEW_DISTANCE_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     && CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getPuppetOfCivID()
                        != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Distance") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.getNumberWithSpaces(
                                 ""
                                    + (int)CFG.game_NextTurnUpdate
                                       .getDistanceFromCapital(
                                          CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCapitalProvinceID(),
                                          CFG.menuManager.getHoveredProvinceID()
                                       )
                              ),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AdministrationCost") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.getNumberWithSpaces(
                                 ""
                                    + (int)CFG.game_NextTurnUpdate
                                       .getProvinceAdministration(
                                          CFG.menuManager.getHoveredProvinceID(),
                                          CFG.game_NextTurnUpdate
                                             .getAdministration_Capital(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID())
                                       )
                              ),
                           CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxDistance();
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_IncomeMapMode(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawIcnomeMapMode_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_IncomeMapMode(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawIcnomeMapMode_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                              != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else {
                           oSB.setColor(
                              CFG.getColorStep(
                                 CFG.COLOR_DISTANCE_MIN,
                                 CFG.COLOR_DISTANCE_MAX,
                                 (int)CFG.game_NextTurnUpdate
                                    .getDistanceFromCapital(
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getCapitalProvinceID(),
                                       CFG.game.getProvinceInViewID(i)
                                    ),
                                 ViewsManager.POPULATION_MAX,
                                 0.6F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                           == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        oSB.setColor(
                           CFG.getColorStep(
                              CFG.COLOR_DISTANCE_MIN,
                              CFG.COLOR_DISTANCE_MAX,
                              (int)CFG.game_NextTurnUpdate
                                 .getDistanceFromCapital(
                                    CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getCapitalProvinceID(),
                                    CFG.game.getProvinceInViewID(i)
                                 ),
                              ViewsManager.POPULATION_MAX,
                              0.6F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
      );
      VIEW_HAPPINESS_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (int)(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getHappiness() * 100.0F) + "%",
                           CFG.COLOR_TEXT_HAPPINESS_MAX
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Image(
                           CFG.getHappinesImage((int)(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getHappiness() * 100.0F)), CFG.PADDING, 0
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewHappiness(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewHappiness(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_ViewHappiness(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_Just(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getColorStep(
                                 CFG.COLOR_TEXT_HAPPINESS_MIN,
                                 CFG.COLOR_TEXT_HAPPINESS_MAX,
                                 (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getHappiness() * 100.0F),
                                 100,
                                 0.5F
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getColorStep_WithAlpha(
                                 CFG.game
                                    .getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                    .getRGB((float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F),
                                 CFG.getColorStep(
                                    CFG.COLOR_TEXT_HAPPINESS_MIN,
                                    CFG.COLOR_TEXT_HAPPINESS_MAX,
                                    (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getHappiness() * 100.0F),
                                    100,
                                    0.5F
                                 ),
                                 tempStepID,
                                 250
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(
                           CFG.getColorStep(
                              CFG.COLOR_TEXT_HAPPINESS_MIN,
                              CFG.COLOR_TEXT_HAPPINESS_MAX,
                              (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getHappiness() * 100.0F),
                              100,
                              0.5F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(
                           CFG.getColorStep_WithAlpha(
                              CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                 .getRGB((float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F),
                              CFG.getColorStep(
                                 CFG.COLOR_TEXT_HAPPINESS_MIN,
                                 CFG.COLOR_TEXT_HAPPINESS_MAX,
                                 (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getHappiness() * 100.0F),
                                 100,
                                 0.5F
                              ),
                              tempStepID,
                              250
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
      );
      VIEW_REVOLUTION_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (int)(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getRevolutionaryRisk() * 100.0F) + "%",
                           CFG.COLOR_TEXT_HAPPINESS_MAX
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewUnrest(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewUnrest(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_ViewUnrest(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRevolutionaryRisk() * 100.0F) == 0
                              ? CFG.COLOR_TEXT_REVOLUTION_MIN_0
                              : CFG.getColorStep(
                                 CFG.COLOR_TEXT_REVOLUTION_MIN,
                                 CFG.COLOR_TEXT_REVOLUTION_MAX,
                                 (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRevolutionaryRisk() * 100.0F),
                                 100,
                                 0.5F
                              )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(
                        (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRevolutionaryRisk() * 100.0F) == 0
                           ? CFG.COLOR_TEXT_REVOLUTION_MIN_0
                           : CFG.getColorStep(
                              CFG.COLOR_TEXT_REVOLUTION_MIN,
                              CFG.COLOR_TEXT_REVOLUTION_MAX,
                              (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRevolutionaryRisk() * 100.0F),
                              100,
                              0.5F
                           )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_PROVINCE_STABILITY_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceStability") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (int)(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getProvinceStability() * 100.0F) + "%",
                           CFG.COLOR_TEXT_HAPPINESS_MAX
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxPopulation();
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
   
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  CFG.game.getProvince(i).viewBool = false;
               }
   
               for(int j = 1; j < CFG.game.getCivsSize(); ++j) {
                  if (CFG.game.getCiv(j).getNumOfProvinces() > 0) {
                     for(int k = 0; k < CFG.game.getCiv(j).getAssimilatesSize(); ++k) {
                        if (!CFG.game.getProvince(CFG.game.getCiv(j).getAssimilate(k).iProvinceID).isOccupied()) {
                           CFG.game.getProvince(CFG.game.getCiv(j).getAssimilate(k).iProvinceID).viewBool = true;
                        }
                     }
                  }
               }
   
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewProvinceStability(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewProvinceStability(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_ViewProvinceStability(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceStability() * 100.0F) == 0
                              ? CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX
                              : CFG.getColorStep(
                                 CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN,
                                 CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX,
                                 (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceStability() * 100.0F),
                                 100,
                                 0.5F
                              )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
      
                  oSB.setShader(AoCGame.shaderAlpha2);
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))
                        && CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).viewBool) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
                     }
                  }
      
                  oSB.setShader(AoCGame.defaultShader);
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(
                        (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceStability() * 100.0F) == 0
                           ? CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX
                           : CFG.getColorStep(
                              CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN,
                              CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX,
                              (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getProvinceStability() * 100.0F),
                              100,
                              0.5F
                           )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
      
                  oSB.setShader(AoCGame.shaderAlpha2);
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).viewBool) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
                     }
                  }
      
                  oSB.setShader(AoCGame.defaultShader);
               }
            }
      );
      VIEW_ECONOMY_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getEconomy()),
                           CFG.COLOR_TEXT_ECONOMY
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxEconomy();
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewEconomy(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewEconomy(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_ViewEconomy(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getEconomyColor(
                                 (int)((float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getEconomy() / (float)ViewsManager.ECONOMY_MAX * 100.0F),
                                 0.6F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getColorStep_WithAlpha(
                                 CFG.game
                                    .getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                    .getRGB((float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F),
                                 CFG.getEconomyColor(
                                    (int)((float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getEconomy() / (float)ViewsManager.ECONOMY_MAX * 100.0F),
                                    0.6F
                                 ),
                                 tempStepID,
                                 250
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(
                           CFG.getEconomyColor(
                              (int)((float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getEconomy() / (float)ViewsManager.ECONOMY_MAX * 100.0F),
                              0.6F
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(
                           CFG.getColorStep_WithAlpha(
                              CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                 .getRGB((float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F),
                              CFG.getEconomyColor(
                                 (int)((float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getEconomy() / (float)ViewsManager.ECONOMY_MAX * 100.0F),
                                 0.6F
                              ),
                              tempStepID,
                              250
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
      );
      VIEW_POPULATION_OF_CIV_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               return null;
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxPopulationOfCivilization(CFG.getActiveCivInfo());
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
            }
   
            @Override
            protected void setActiveProvinceAction() {
               ViewsManager.updateMaxPopulationOfCivilization(CFG.getActiveCivInfo());
               Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER = System.currentTimeMillis();
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == ViewsManager.POPULATION_OF_CIVID) {
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                              oSB.setColor(
                                 new Color(
                                    (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                    (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                    (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                    0.15F
                                       + 0.7F
                                          * (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                          / (float)ViewsManager.POPULATION_MAX
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                                 )
                              );
                           }
      
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == ViewsManager.POPULATION_OF_CIVID) {
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                              oSB.setColor(
                                 new Color(
                                    (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                    (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                    (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                    (
                                          0.15F
                                             + 0.7F
                                                * (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                                / (float)ViewsManager.POPULATION_MAX
                                       )
                                       * (float)tempStepID
                                       / 250.0F
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                                 )
                              );
                           }
      
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  if (Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER + 250L <= System.currentTimeMillis()) {
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == ViewsManager.POPULATION_OF_CIVID) {
                           oSB.setColor(
                              new Color(
                                 (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                 (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                 (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                 0.15F
                                    + 0.7F
                                       * (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                       / (float)ViewsManager.POPULATION_MAX
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
                  } else {
                     int tempStepID = Math.min((int)(System.currentTimeMillis() - Game_Render_Province.PROVINCE_COLOR_ANIMATION_TIMER), 250);
      
                     for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == ViewsManager.POPULATION_OF_CIVID) {
                           oSB.setColor(
                              new Color(
                                 (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getR() / 255.0F,
                                 (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getG() / 255.0F,
                                 (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getB() / 255.0F,
                                 (
                                       0.15F
                                          + 0.7F
                                             * (float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPopulationData().getPopulation()
                                             / (float)ViewsManager.POPULATION_MAX
                                    )
                                    * (float)tempStepID
                                    / 250.0F
                              )
                           );
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                     }
      
                     CFG.setRender_3(true);
                  }
               }
            }
      );
      VIEW_CONTINENT_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.map.getMapContinents().getName(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getContinent())
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.fTerrainMode_LinePercentage = 0.0F;
               CFG.lTerrainMode_LineTime = System.currentTimeMillis();
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               CFG.game.drawActiveProvince(oSB);
               CFG.game.drawHighlightProvince(oSB);
               CFG.game.updateHighlitghtProvinceBorder(oSB);
               Game_Render_Province.drawProvincesBorder_ContinentMode_FogOfWarDiscovey(oSB);
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               CFG.game.drawActiveProvince(oSB);
               CFG.game.drawHighlightProvince(oSB);
               CFG.game.updateHighlitghtProvinceBorder(oSB);
               Game_Render_Province.drawProvincesBorder_ContinentMode(oSB);
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           new Color(
                              CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()).r,
                              CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()).g,
                              CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()).b,
                              0.7F
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(
                        new Color(
                           CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()).r,
                           CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()).g,
                           CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getContinent()).b,
                           0.7F
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_REGIONS_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Region") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.map.getMapRegions().getName(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getRegion()),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.fTerrainMode_LinePercentage = 0.0F;
               CFG.lTerrainMode_LineTime = System.currentTimeMillis();
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               CFG.game.drawActiveProvince(oSB);
               CFG.game.drawHighlightProvince(oSB);
               CFG.game.updateHighlitghtProvinceBorder(oSB);
               Game_Render_Province.drawProvincesBorder_RegionsMode_FogOfWarDiscovery(oSB);
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               CFG.game.drawActiveProvince(oSB);
               CFG.game.drawHighlightProvince(oSB);
               CFG.game.updateHighlitghtProvinceBorder(oSB);
               Game_Render_Province.drawProvincesBorder_RegionsMode(oSB);
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           new Color(
                              CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()).r,
                              CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()).g,
                              CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()).b,
                              0.45F
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(
                        new Color(
                           CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()).r,
                           CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()).g,
                           CFG.map.getMapRegions().getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getRegion()).b,
                           0.45F
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_TERRAIN_TYPE_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                        return CFG.game
                           .getHover_TerrainTypeInfo(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getTerrainTypeID(), CFG.menuManager.getHoveredProvinceID()
                           );
                     }
   
                     if (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                        return CFG.game
                           .getHover_TerrainTypeInfo(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getTerrainTypeID(), CFG.menuManager.getHoveredProvinceID()
                           );
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.fTerrainMode_LinePercentage = 0.0F;
               CFG.lTerrainMode_LineTime = System.currentTimeMillis();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewTerrain(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewTerrain(false);
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawMountains_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawMountains_FogOfWarDiscovery(oSB, 1.0F);
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawMountains(oSB, CFG.map.getMapScale().getCurrentScale());
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawMountains(oSB, 1.0F);
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(CFG.terrainTypesManager.getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTerrainTypeID()));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(CFG.terrainTypesManager.getColor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTerrainTypeID()));
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_GROWTH_RATE_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (int)(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getGrowthRate_Population() * 100.0F) + "%",
                           CFG.COLOR_TEXT_POPULATION
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     if (BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFarm()) > 0.0F) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Farm") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + (int)(
                                    BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFarm())
                                       * 100.0F
                                 )
                                 + "%",
                              CFG.COLOR_TEXT_POPULATION
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewGrowthRate(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewGrowthRate(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_ViewGrowthRate(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails() && Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           CFG.getGrowthRateColor(
                              (int)(
                                 (
                                       CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getGrowthRate_Population()
                                          + BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getLevelOfFarm())
                                    )
                                    * 100.0F
                              ),
                              0.6F
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(
                        CFG.getGrowthRateColor(
                           (int)(
                              (
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getGrowthRate_Population()
                                       + BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getLevelOfFarm())
                                 )
                                 * 100.0F
                           ),
                           0.6F
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_DEVELOPMENT_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getIsCapital()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + (float)(
                                       (int)(
                                          CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getTechnologyLevel()
                                             * 100.0F
                                       )
                                    )
                                    / 100.0F,
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Development") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (float)((int)(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getDevelopmentLevel() * 100.0F)) / 100.0F,
                           CFG.COLOR_TEXT_POPULATION
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewDevelopment(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewDevelopment(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_ViewDevelopment(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(
                           CFG.getTechnologyLevelColor(
                              (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getDevelopmentLevel() * 100.0F), CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL
                           )
                        );
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(
                        CFG.getTechnologyLevelColor(
                           (int)(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getDevelopmentLevel() * 100.0F), CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_IMPERIAL_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getIsPartOfHolyRomanEmpire()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.holyRomanEmpire_Manager.getHRE().getIsEmperor(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID())) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName() + " ",
                                 CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IsTheEmperor")));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.hre_flag, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else if (CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID())) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName() + " ",
                                 CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IsAnElector")));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.hre_icon, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else if (CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getIsPartOfHolyRomanEmpire()) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName() + " ",
                                 CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IsAPrince")));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName() + " ",
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IsNotAPrince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ThisProvinceIsPartOfEmpire"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(0, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName()
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(0, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ThisProvinceIsPartOfEmpire"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.hre_flag, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(0, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName()
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(0, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.map.getMapBG().updateWorldMap_Shaders();
            }
   
            @Override
            protected void disableViewAction() {
               CFG.map.getMapBG().updateWorldMap_Shaders();
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Imperial_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawFlagAndCrown_Emperor_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Imperial_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawFlagAndCrown_Emperor_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Imperial(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawFlagAndCrown_Emperor(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Imperial(oSB, 1.0F);
                  }
   
                  CFG.game.drawFlagAndCrown_Emperor(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsPartOfHolyRomanEmpire()) {
                           if (!CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIsPartOfHolyRomanEmpire()) {
                              oSB.setColor(
                                 new Color(
                                    HolyRomanEmpire_Manager.oColorHRE_NotControledByEmpire.r,
                                    HolyRomanEmpire_Manager.oColorHRE_NotControledByEmpire.g,
                                    HolyRomanEmpire_Manager.oColorHRE_NotControledByEmpire.b,
                                    (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                                 )
                              );
                           } else if (CFG.holyRomanEmpire_Manager.getHRE().getIsEmperor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())) {
                              oSB.setColor(
                                 new Color(
                                    HolyRomanEmpire_Manager.oColorHRE.r,
                                    HolyRomanEmpire_Manager.oColorHRE.g,
                                    HolyRomanEmpire_Manager.oColorHRE.b,
                                    (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                                 )
                              );
                           } else if (CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())) {
                              oSB.setColor(
                                 new Color(
                                    HolyRomanEmpire_Manager.oColorHRE_Electors.r,
                                    HolyRomanEmpire_Manager.oColorHRE_Electors.g,
                                    HolyRomanEmpire_Manager.oColorHRE_Electors.b,
                                    (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                                 )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(
                                    HolyRomanEmpire_Manager.oColorHRE_BG.r,
                                    HolyRomanEmpire_Manager.oColorHRE_BG.g,
                                    HolyRomanEmpire_Manager.oColorHRE_BG.b,
                                    (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                                 )
                              );
                           }
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                                 CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                                 (float)CFG.settingsManager.PROVINCE_ALPHA * 2.0F / 5.0F / 255.0F
                              )
                           );
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsPartOfHolyRomanEmpire()) {
                        if (!CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIsPartOfHolyRomanEmpire()) {
                           oSB.setColor(
                              new Color(
                                 HolyRomanEmpire_Manager.oColorHRE_NotControledByEmpire.r,
                                 HolyRomanEmpire_Manager.oColorHRE_NotControledByEmpire.g,
                                 HolyRomanEmpire_Manager.oColorHRE_NotControledByEmpire.b,
                                 (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                              )
                           );
                        } else if (CFG.holyRomanEmpire_Manager.getHRE().getIsEmperor(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())) {
                           oSB.setColor(
                              new Color(
                                 HolyRomanEmpire_Manager.oColorHRE.r,
                                 HolyRomanEmpire_Manager.oColorHRE.g,
                                 HolyRomanEmpire_Manager.oColorHRE.b,
                                 (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                              )
                           );
                        } else if (CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())) {
                           oSB.setColor(
                              new Color(
                                 HolyRomanEmpire_Manager.oColorHRE_Electors.r,
                                 HolyRomanEmpire_Manager.oColorHRE_Electors.g,
                                 HolyRomanEmpire_Manager.oColorHRE_Electors.b,
                                 (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 HolyRomanEmpire_Manager.oColorHRE_BG.r,
                                 HolyRomanEmpire_Manager.oColorHRE_BG.g,
                                 HolyRomanEmpire_Manager.oColorHRE_BG.b,
                                 (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                              )
                           );
                        }
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getR(),
                              CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getG(),
                              CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_NEUTRAL.getB(),
                              (float)CFG.settingsManager.PROVINCE_ALPHA * 2.0F / 5.0F / 255.0F
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_TECHNOLOGY_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + (float)(
                                    (int)(
                                       CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getTechnologyLevel() * 100.0F
                                    )
                                 )
                                 / 100.0F,
                           CFG.COLOR_TEXT_POPULATION
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  if (CFG.game.getProvince(i).getIsCapital()) {
                     CFG.game
                        .getProvince(i)
                        .getArmy_Obj(0)
                        .updateArmyWidth((float)((int)(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTechnologyLevel() * 100.0F)) / 100.0F);
                  }
               }
   
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewTechnology(true, ViewsManager.this.viewConfig);
               }
            }
   
            @Override
            protected void disableViewAction() {
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  if (CFG.game.getProvince(i).getIsCapital()) {
                     CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
                  }
               }
   
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewTechnology(false, ViewsManager.this.viewConfig);
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_Capitals_FogOfWarDiscovery(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvinces_TechnologyLevels_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvinces_TechnologyLevels_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_Capitals(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_Just(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvinces_TechnologyLevels(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvinces_TechnologyLevels(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.getTechnologyLevelColor(
                                 (int)(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getTechnologyLevel() * 100.0F),
                                 CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL
                              )
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(
                           CFG.getTechnologyLevelColor(
                              (int)(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getTechnologyLevel() * 100.0F),
                              CFG.PROVINCE_ALPHA_TECHNOLOGY_LEVEL
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
      );
      VIEW_PROVINCE_VALUE_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceValue") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvinceValue(CFG.menuManager.getHoveredProvinceID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.updateMAX_PROVINCE_VALUE();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View_ProvinceValue(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_View_ProvinceValue(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(CFG.getProvinceValueColor(CFG.game.getProvinceValue(CFG.game.getProvinceInViewID(i))));
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(CFG.getProvinceValueColor(CFG.game.getProvinceValue(CFG.game.getProvinceInViewID(i))));
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_ARMY_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivsSize() > 1
                        && CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.menuManager.getHoveredProvinceID())) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        for(int i = 1; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivsSize(); ++i) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(i)));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(i)).getCivName()
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     } else {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                           return null;
                        }
   
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfSupply() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getSupply_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfSupply())),
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_supply, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.updateMAX_Army();
   
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  CFG.game.getProvince(i).viewBool = CFG.game.getProvince(i).getLevelOfSupply() > 0;
               }
   
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewArmy(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewArmy(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 0
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i)) > 0) {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           oSB.setColor(CFG.getProvinceArmyColor_Own(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                        } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) {
                           oSB.setColor(CFG.getProvinceArmyColor_Neutral(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                        } else if ((int)CFG.game
                              .getCivRelation_OfCivB(
                                 CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                              )
                           == -100) {
                           oSB.setColor(CFG.getProvinceArmyColor_AtWar(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                        } else if ((
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0
                                 || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()
                                    != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()
                           )
                           && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID()
                              != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                           && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                              != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getCivID()) {
                           oSB.setColor(CFG.getProvinceArmyColor_Neutral(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                        } else {
                           oSB.setColor(CFG.getProvinceArmyColor_Alliance(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                        }
                     } else {
                        oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.1725F));
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
      
                  oSB.setShader(AoCGame.shaderAlpha2);
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).viewBool) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
                     }
                  }
      
                  oSB.setShader(AoCGame.defaultShader);
               }
            }
            : (
               CFG.FOG_OF_WAR == 2
                  ? new Game_Render_Province.DrawProvinces() {
                     @Override
                     public void draw(SpriteBatch oSB) {
                        for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                              if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.game.getProvinceInViewID(i))) {
                                 oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.0575F));
                              } else if (CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i)) > 0) {
                                 if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    oSB.setColor(CFG.getProvinceArmyColor_Own(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) {
                                    oSB.setColor(CFG.getProvinceArmyColor_Neutral(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else if ((int)CFG.game
                                       .getCivRelation_OfCivB(
                                          CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                       )
                                    == -100) {
                                    oSB.setColor(CFG.getProvinceArmyColor_AtWar(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else if ((
                                       CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0
                                          || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()
                                             != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()
                                    )
                                    && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID()
                                       != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                                    && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                                       != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getCivID()) {
                                    oSB.setColor(CFG.getProvinceArmyColor_Neutral(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else {
                                    oSB.setColor(CFG.getProvinceArmyColor_Alliance(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 }
                              } else {
                                 oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.1725F));
                              }
            
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           } else {
                              oSB.setColor(
                                 new Color(
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                    CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                                 )
                              );
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                           }
                        }
            
                        oSB.setShader(AoCGame.shaderAlpha2);
            
                        for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))
                              && CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).viewBool) {
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
                           }
                        }
            
                        oSB.setShader(AoCGame.defaultShader);
                     }
                  }
                  : new Game_Render_Province.DrawProvinces() {
                     @Override
                     public void draw(SpriteBatch oSB) {
                        for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                           if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.game.getProvinceInViewID(i))) {
                              if (CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i)) > 0) {
                                 if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    oSB.setColor(CFG.getProvinceArmyColor_Own(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() == 0) {
                                    oSB.setColor(CFG.getProvinceArmyColor_Neutral(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else if ((int)CFG.game
                                       .getCivRelation_OfCivB(
                                          CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                       )
                                    == -100) {
                                    oSB.setColor(CFG.getProvinceArmyColor_AtWar(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else if ((
                                       CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0
                                          || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getAllianceID()
                                             != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()
                                    )
                                    && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID()
                                       != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID()
                                    && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                                       != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getCivID()) {
                                    oSB.setColor(CFG.getProvinceArmyColor_Neutral(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 } else {
                                    oSB.setColor(CFG.getProvinceArmyColor_Alliance(CFG.game.getProvinceArmy(CFG.game.getProvinceInViewID(i))));
                                 }
                              } else {
                                 oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.1725F));
                              }
                           } else {
                              oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.0575F));
                           }
            
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
            
                        oSB.setShader(AoCGame.shaderAlpha2);
            
                        for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).viewBool) {
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawOccupiedProvince(oSB);
                           }
                        }
            
                        oSB.setShader(AoCGame.defaultShader);
                     }
                  }
            )
      );
      VIEW_BUILDINGS_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  } else {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFort() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getFort_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFort()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfWatchTower() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getTower_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfWatchTower()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfPort() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getPort_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfPort()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfLibrary() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getLibrary_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfLibrary()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_library, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFarm() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getFarm_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFarm()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfWorkshop() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getWorkshop_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfWorkshop()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_workshop, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfArmoury() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfArmoury()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_armoury, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfSupply() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager
                                 .get(BuildingsManager.getSupply_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfSupply()))
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_supply, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     return nElements.size() > 0 ? new MenuElement_Hover_v2(nElements) : null;
                  }
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View_Buildings(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View_Buildings(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
               if (CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  int oldCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(oldProvince);
                  int newCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(newProvince);
                  if (oldCivID != newCivID) {
                     CFG.menuManager.setVisible_InGame_View_Buildings(true);
                  }
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesBuildings_FogOfWar(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesBuildings_FogOfWar(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesBuildings(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesBuildings(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).setProvinceColor(oSB);
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_LEVEL_OF_PORT_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  }
   
                  if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfPort() > 0
                     && CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.port_ico));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Port") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  }
               } catch (IndexOutOfBoundsException var3) {
               }
   
               return null;
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_PortCities_L1_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_PortCities_L1_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_PortCities_L1(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_PortCities_L1(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(Game_Render_Province.getProvince_PortColor(CFG.game.getProvinceInViewID(i)));
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(Game_Render_Province.getProvince_PortColor(CFG.game.getProvinceInViewID(i)));
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_LEVEL_OF_FORTIFICATIONS_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  }
   
                  if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFort() > 0
                     && CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfFort()))
                              + ": "
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  }
               } catch (IndexOutOfBoundsException var3) {
               }
   
               return null;
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_Fort_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_Fort_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_Fort(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_Fort(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(Game_Render_Province.getProvince_FortColor(CFG.game.getProvinceInViewID(i)));
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(Game_Render_Province.getProvince_FortColor(CFG.game.getProvinceInViewID(i)));
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_LEVEL_OF_WATCH_TOWER_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  }
   
                  if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfWatchTower() > 0
                     && CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                     List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager
                                 .get(BuildingsManager.getTower_Name(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getLevelOfWatchTower()))
                              + ": "
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(), CFG.PADDING, 0)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  }
               } catch (IndexOutOfBoundsException var3) {
               }
   
               return null;
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_WatchTower_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails() && Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_WatchTower_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_WatchTower(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        oSB.setColor(Game_Render_Province.getProvince_WatchTowerColor(CFG.game.getProvinceInViewID(i)));
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     oSB.setColor(Game_Render_Province.getProvince_WatchTowerColor(CFG.game.getProvinceInViewID(i)));
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_IDEOLOGIES_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.ideologiesManager
                              .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getIdeologyID())
                              .getName(),
                           CFG.ideologiesManager
                              .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getIdeologyID())
                              .getColor()
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Ideology(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getIdeologyID(), CFG.PADDING, 0
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  } else {
                     return null;
                  }
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewGovernments(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewGovernments(false);
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               CFG.game.drawActiveProvince(oSB);
               CFG.game.drawHighlightProvince(oSB);
               CFG.game.updateHighlitghtProvinceBorder(oSB);
               Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_Capitals_FogOfWarDiscovery(oSB);
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               CFG.game.drawActiveProvince(oSB);
               CFG.game.drawHighlightProvince(oSB);
               CFG.game.updateHighlitghtProvinceBorder(oSB);
               Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_Capitals(oSB);
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (!Game_Render.DISABLE_CITIES) {
                  CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                           oSB.setColor(
                              CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsCapital()
                                 ? new Color(
                                    CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                       .getColor()
                                       .r,
                                    CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                       .getColor()
                                       .g,
                                    CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                       .getColor()
                                       .b,
                                    CFG.ideologiesManager
                                          .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                          .getColor()
                                          .a
                                       * 1.1F
                                 )
                                 : CFG.ideologiesManager
                                    .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                    .getColor()
                           );
                        } else {
                           oSB.setColor(
                              new Color(
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                                 CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                              )
                           );
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(
                           CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getIsCapital()
                              ? new Color(
                                 CFG.ideologiesManager
                                    .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                    .getColor()
                                    .r,
                                 CFG.ideologiesManager
                                    .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                    .getColor()
                                    .g,
                                 CFG.ideologiesManager
                                    .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                    .getColor()
                                    .b,
                                 CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                       .getColor()
                                       .a
                                    * 1.1F
                              )
                              : CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()).getIdeologyID())
                                 .getColor()
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
      );
      VIEW_RECRUITABLE_ARMY_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                        return null;
                     }
   
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                        }
   
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
   
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.getNumberWithSpaces(
                                 ""
                                    + CFG.gameAction
                                       .getRecruitableArmy(CFG.menuManager.getHoveredProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              ),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxRecruitable();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewRecruitable(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_ViewRecruitable(false);
               }
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
   
               Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                  }
   
                  CFG.game.drawProvincesArmy(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     float tRecr = 0.0F;
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        tRecr = (float)CFG.gameAction.getRecruitableArmy(CFG.game.getProvinceInViewID(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           / (float)ViewsManager.ECONOMY_MAX;
                        oSB.setColor(CFG.getColorStep(CFG.COLOR_TEXT_RECRUITABLE_MIN, CFG.COLOR_TEXT_RECRUITABLE_MAX, (int)(tRecr * 100.0F), 100, 0.5F));
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  float tRecr = 0.0F;
      
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     tRecr = (float)CFG.gameAction.getRecruitableArmy(CFG.game.getProvinceInViewID(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        / (float)ViewsManager.ECONOMY_MAX;
                     oSB.setColor(
                        CFG.getColorStep(
                           CFG.COLOR_TEXT_RECRUITABLE_MIN,
                           CFG.COLOR_TEXT_RECRUITABLE_MAX,
                           (int)(
                              (float)CFG.gameAction.getRecruitableArmy(CFG.game.getProvinceInViewID(i), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 / (float)ViewsManager.ECONOMY_MAX
                                 * 100.0F
                           ),
                           100,
                           0.5F
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      VIEW_AI_POTENTIAL_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               return null;
            }
   
            @Override
            protected void enableViewAction() {
               CFG.oAI.buildAIData();
               ViewsManager.updateMaxPotential();
   
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth("" + CFG.game.getProvince(i).getPotential());
               }
            }
   
            @Override
            protected void disableViewAction() {
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
               }
            }
         },
         new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvinces_Potential(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawProvinces_Potential(oSB, 1.0F);
               }
            }
         },
         new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                  oSB.setColor(
                     CFG.getColorStep(
                        new Color(1.0F, 1.0F, 1.0F, 1.0F),
                        new Color(0.11764706F, 0.13725491F, 0.29411766F, 1.0F),
                        (int)((float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getPotential() / (float)ViewsManager.ECONOMY_MAX * 100.0F),
                        100,
                        0.6F
                     )
                  );
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         }
      );
      VIEW_AI_DANGER_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               return null;
            }
   
            @Override
            protected void enableViewAction() {
               CFG.oAI.buildAIData();
               ViewsManager.updateMaxDanger();
   
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth("" + CFG.game.getProvince(i).getDangerLevel());
               }
            }
   
            @Override
            protected void disableViewAction() {
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
               }
            }
         },
         new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawProvinces_Danger(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawProvinces_Danger(oSB, 1.0F);
               }
            }
         },
         new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                  oSB.setColor(
                     CFG.getColorStep(
                        new Color(1.0F, 1.0F, 1.0F, 1.0F),
                        new Color(0.7254902F, 0.11764706F, 0.11764706F, 1.0F),
                        (int)((float)CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getDangerLevel() / (float)ViewsManager.ECONOMY_MAX * 100.0F),
                        100,
                        0.6F
                     )
                  );
                  CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
               }
            }
         }
      );
      VIEW_BALANCE_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID() > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Balance") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game_NextTurnUpdate.getBalance(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     return new MenuElement_Hover_v2(nElements);
                  } else {
                     return null;
                  }
               } catch (IndexOutOfBoundsException var3) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               ViewsManager.updateMaxBalance();
            }
   
            @Override
            protected void disableViewAction() {
            }
         },
         new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, 1.0F);
               }
            }
         },
         new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                  if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
                     oSB.setColor(
                        CFG.getColorStep(
                           CFG.COLOR_TEXT_HAPPINESS_MIN,
                           CFG.COLOR_TEXT_HAPPINESS_MAX,
                           (int)(
                              (float)CFG.game_NextTurnUpdate.getBalance(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID())
                                 / (float)ViewsManager.ECONOMY_MAX
                                 * 100.0F
                           ),
                           100,
                           0.6F
                        )
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
         }
      );
      VIEW_TRUE_OWNERS_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getTrueOwnerOfProvince()).getCivName(),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Flag(
                     CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getTrueOwnerOfProvince(), CFG.PADDING, 0
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               return new MenuElement_Hover_v2(nElements);
            }
   
            @Override
            protected void enableViewAction() {
            }
   
            @Override
            protected void disableViewAction() {
            }
         },
         new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
            }
         },
         new Game_Render_Province.DrawProvinces() {
            @Override
            public void draw(SpriteBatch oSB) {
               for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                  if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince() != 0) {
                     oSB.setColor(
                        (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()).getR() / 255.0F,
                        (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()).getG() / 255.0F,
                        (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getTrueOwnerOfProvince()).getB() / 255.0F,
                        (float)CFG.settingsManager.PROVINCE_ALPHA / 255.0F
                     );
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
         }
      );
      VIEW_DISEASES_MODE = this.addViewToTheGame(
         new View_Type() {
            @Override
            protected MenuElement_Hover getProvinceInformations() {
               try {
                  List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getWasteland() >= 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getSeaProvince()) {
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivsSize() > 1
                        && CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.menuManager.getHoveredProvinceID())) {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() > 0) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
   
                        for(int i = 1; i < CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivsSize(); ++i) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(i)));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID(i)).getCivName()
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     } else {
                        if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName().length() <= 0) {
                           return null;
                        }
   
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.menuManager.getHoveredProvinceID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).getCivID()).getCivName(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     if (CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).saveProvinceData.provincePlague != null) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Name") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.plagueManager
                                 .getPlague_InGame(
                                    CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).saveProvinceData.provincePlague.iPlagueID_InGame
                                 )
                                 .getPlagueName(),
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, CFG.PADDING));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Deaths") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getNumberWithSpaces(
                                 "" + CFG.game.getProvince(CFG.menuManager.getHoveredProvinceID()).saveProvinceData.provincePlague.iDeaths
                              ),
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }
   
                  return new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  return null;
               }
            }
   
            @Override
            protected void enableViewAction() {
               CFG.map.getMapBG().updateWorldMap_Shaders();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View_Diseases(true);
               }
            }
   
            @Override
            protected void disableViewAction() {
               CFG.map.getMapBG().updateWorldMap_Shaders();
               if (CFG.menuManager.getInGameView()) {
                  CFG.menuManager.setVisible_InGame_View_Diseases(false);
               }
            }
   
            @Override
            protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
            }
         },
         CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown_FogOfWarDiscovery(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvincesInGame(oSB);
               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawActiveProvince(oSB);
                  CFG.game.drawHighlightProvince(oSB);
                  CFG.game.updateHighlitghtProvinceBorder(oSB);
                  Game_Render_Province.drawProvincesBorder(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
               }
   
               CFG.game.drawActiveProvinceBorder(oSB);
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
               }
            }
   
            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
   
            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  if (!Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities(oSB, 1.0F);
                  }
   
                  CFG.game.drawCapitalsArmy_FlagAndCrown(oSB, 1.0F);
               }
            }
         },
         CFG.FOG_OF_WAR == 2
            ? new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getProvinceInViewID(i))) {
                        try {
                           if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).saveProvinceData.provincePlague != null) {
                              oSB.setColor(
                                 CFG.plagueManager
                                    .getPlagueColor_InGame(
                                       CFG.game.getProvinceInViewID(i),
                                       CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).saveProvinceData.provincePlague.iPlagueID_InGame,
                                       0.725F
                                    )
                              );
                           } else {
                              oSB.setColor(
                                 new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.027187502F)
                              );
                           }
                        } catch (IndexOutOfBoundsException var4) {
                           oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.027187502F));
                        }
      
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     } else {
                        oSB.setColor(
                           new Color(
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                              CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA
                           )
                        );
                        CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                     }
                  }
               }
            }
            : new Game_Render_Province.DrawProvinces() {
               @Override
               public void draw(SpriteBatch oSB) {
                  for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                     try {
                        if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).saveProvinceData.provincePlague != null) {
                           oSB.setColor(
                              CFG.plagueManager
                                 .getPlagueColor_InGame(
                                    CFG.game.getProvinceInViewID(i),
                                    CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).saveProvinceData.provincePlague.iPlagueID_InGame,
                                    0.725F
                                 )
                           );
                        } else {
                           oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.027187502F));
                        }
                     } catch (IndexOutOfBoundsException var4) {
                        oSB.setColor(new Color(CFG.COLOR_PROVINCE_ARMY_MIN.r, CFG.COLOR_PROVINCE_ARMY_MIN.g, CFG.COLOR_PROVINCE_ARMY_MIN.b, 0.027187502F));
                     }
      
                     CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).drawLandProvince(oSB);
                  }
               }
            }
      );
      this.lViews.get(VIEW_TECHNOLOGY_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_IDEOLOGIES_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_DIPLOMACY_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_ALLIANCES_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_IMPERIAL_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_PROVINCE_STABILITY_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_SUPPLIES_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_BUILDINGS_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_RECRUITABLE_ARMY_MODE).drawCivNamesOver = true;
      this.lViews.get(VIEW_ARMY_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_POPULATION_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_ECONOMY_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_TERRAIN_TYPE_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_GROWTH_RATE_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_RECRUITABLE_ARMY_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_LEVEL_OF_FORTIFICATIONS_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_LEVEL_OF_WATCH_TOWER_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_LEVEL_OF_PORT_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_DEVELOPMENT_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_REVOLUTION_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_PROVINCE_VALUE_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_PROVINCE_STABILITY_MODE).canMoveArmy = true;
      this.lViews.get(VIEW_SUPPLIES_MODE).canMoveArmy = true;
   }

   private final int addViewToTheGame(View_Type nView, Game_Render.Renderer nRenderer, Game_Render_Province.DrawProvinces nDrawProvinces) {
      nView.oRenderer = nRenderer;
      nView.drawProvinces = nDrawProvinces;
      this.lViews.add(nView);
      return this.lViews.size() - 1;
   }

   protected final void setActiveViewID(int iID, boolean viewConfig) {
      this.viewConfig = viewConfig;
      this.setActiveViewID(iID);
   }

   protected final void setActiveViewID(int iID) {
      try {
         if (this.iActiveViewID == iID) {
            int tempActive = this.iActiveViewID;
            this.iActiveViewID = -1;
            this.lViews.get(tempActive).disableViewAction();
         } else if (this.iActiveViewID >= 0) {
            int tempActive = this.iActiveViewID;
            this.iActiveViewID = iID;
            this.lViews.get(tempActive).disableViewAction();
            this.lViews.get(iID).enableViewAction();
         } else {
            this.iActiveViewID = iID;
            this.lViews.get(iID).enableViewAction();
         }
      } catch (IndexOutOfBoundsException var3) {
         this.iActiveViewID = -1;
      }

      if (this.iActiveViewID >= 0 && CFG.menuManager.getInGameView() && CFG.menuManager.getVisible_InGame_FlagAction()) {
         CFG.menuManager.setVisible_InGame_FlagAction(false);
      }

      Game_Render.updateRenderer();
      Game_Render_Province.updateDrawProvinces();
      Game_Render.updateDrawMoveUnits();
      CFG.menuManager.updateBuildProvinceHoverInformation();
      if (RTS.isEnabled() && !RTS.PAUSE) {
         RTS.updateTimePast_AfterAction(0.75F);
      }
   }

   protected final void disableAllViews() {
      if (this.iActiveViewID >= 0) {
         int tempActive = this.iActiveViewID;
         this.iActiveViewID = -1;
         this.lViews.get(tempActive).disableViewAction();
      }

      Game_Render.updateRenderer();
      Game_Render_Province.updateDrawProvinces();
      Game_Render.updateDrawMoveUnits();
      CFG.menuManager.updateBuildProvinceHoverInformation();
   }

   protected static final void updateMaxPopulation() {
      POPULATION_MAX = 1;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getPopulationData().getPopulation() > POPULATION_MAX) {
            POPULATION_MAX = CFG.game.getProvince(i).getPopulationData().getPopulation();
         }
      }

      POPULATION_MAX = (int)((float)POPULATION_MAX * 0.9F);
   }

   protected static final void updateMaxPopulationOfCivilization(int nCivID) {
      POPULATION_OF_CIVID = nCivID;
      POPULATION_MAX = 1;

      for(int i = 0; i < CFG.game.getCiv(POPULATION_OF_CIVID).getNumOfProvinces(); ++i) {
         if (!CFG.game.getProvince(CFG.game.getCiv(POPULATION_OF_CIVID).getProvinceID(i)).getSeaProvince()
            && CFG.game.getProvince(CFG.game.getCiv(POPULATION_OF_CIVID).getProvinceID(i)).getPopulationData().getPopulation() > POPULATION_MAX) {
            POPULATION_MAX = CFG.game.getProvince(CFG.game.getCiv(POPULATION_OF_CIVID).getProvinceID(i)).getPopulationData().getPopulation();
         }
      }
   }

   protected static final void updateMaxIncome() {
      POPULATION_MAX = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()
            && CFG.game.getProvince(i).getWasteland() < 0
            && (
               CFG.game.getProvince(i).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                  || CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            )) {
            CFG.game.getProvince(i).iIncome_Taxation = CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(i);
            CFG.game.getProvince(i).iIncome_Production = CFG.game_NextTurnUpdate.getProvinceIncome_Production(i);
            CFG.game.getProvince(i).iAdministrationCost = CFG.game_NextTurnUpdate
               .getProvinceAdministration(i, CFG.game_NextTurnUpdate.getAdministration_Capital(CFG.game.getProvince(i).getCivID()));
            if (CFG.game.getProvince(i).getBalance_LastTurn() > POPULATION_MAX) {
               POPULATION_MAX = CFG.game.getProvince(i).getBalance_LastTurn();
            }
         }
      }
   }

   protected static final void updateMaxDistance() {
      POPULATION_MAX = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()
            && CFG.game.getProvince(i).getWasteland() < 0
            && (
               CFG.game.getProvince(i).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                  || CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            )
            && CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID(), i)
               > (float)POPULATION_MAX) {
            POPULATION_MAX = (int)CFG.game_NextTurnUpdate
               .getDistanceFromCapital(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID(), i);
         }
      }
   }

   protected static final void updateMaxEconomy() {
      ECONOMY_MAX = 1;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getEconomy() > ECONOMY_MAX) {
            ECONOMY_MAX = CFG.game.getProvince(i).getEconomy();
         }
      }

      ECONOMY_MAX = (int)((float)ECONOMY_MAX * 0.9F);
   }

   protected static final void updateMaxPotential() {
      ECONOMY_MAX = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(i).getPotential() > ECONOMY_MAX) {
            ECONOMY_MAX = CFG.game.getProvince(i).getPotential();
         }
      }
   }

   protected static final void updateMaxDanger() {
      ECONOMY_MAX = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(i).getDangerLevel() > ECONOMY_MAX) {
            ECONOMY_MAX = CFG.game.getProvince(i).getDangerLevel();
         }
      }
   }

   protected static final void updateMaxBalance() {
      ECONOMY_MAX = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game_NextTurnUpdate.getBalance(i) > ECONOMY_MAX) {
            ECONOMY_MAX = CFG.game_NextTurnUpdate.getBalance(i);
         }
      }
   }

   protected static final void updateMaxRecruitable() {
      ECONOMY_MAX = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (CFG.gameAction.getRecruitableArmy(i, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > ECONOMY_MAX) {
            ECONOMY_MAX = CFG.gameAction.getRecruitableArmy(i, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         }
      }
   }

   protected final int getActiveViewID() {
      return this.iActiveViewID;
   }

   protected final View_Type getActiveView() {
      return this.lViews.get(this.iActiveViewID);
   }

   protected final void clearData() {
      this.lViews.clear();
      this.lViews = null;
   }
}
