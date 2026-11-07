package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Game_Render {
   protected static boolean DISABLE_CIVS_NAMES = false;
   protected static boolean DISABLE_CITIES = false;
   protected static boolean drawCivNamesInCreateNewGame = false;
   private static Game_Render.Renderer oRenderer;
   protected static Game_Render.DrawMoveUnits oDrawMoveUnits;
   protected static float DISABLE_INNER_BORDERS = 0.4F;
   protected static float DISABLE_SEA_ARMIES = 0.65F;
   protected static float CIV_NAMES_START_DRAWING_NAMES_MAP_SCALE = 1.0F;
   protected static float CIVILIZATION_NAMES_ALPHA = 1.0F;
   private static long CIVILIZATIONS_NAMES_TIME = 0L;
   private static Game_Render.Renderer_CivRegionNames oRenderer_CivRegionNames;
   private static List<Integer> lRegions_Civs = new ArrayList<>();
   private static List<List<Integer>> lRegions_Civs_RegionsID = new ArrayList<>();

   private static final void updateRegionsInView() {
      if (CIVILIZATIONS_NAMES_TIME == 0L) {
         CIVILIZATIONS_NAMES_TIME = System.currentTimeMillis();
         CIVILIZATION_NAMES_ALPHA = 0.1F;
         CFG.setRender_3(true);
      } else if (CIVILIZATION_NAMES_ALPHA < 1.0F) {
         CIVILIZATION_NAMES_ALPHA = 0.1F
            + 0.9F * (float)(System.currentTimeMillis() - CIVILIZATIONS_NAMES_TIME) / (float)CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL;
         if (CIVILIZATION_NAMES_ALPHA > 1.0F) {
            CIVILIZATION_NAMES_ALPHA = 1.0F;
         } else {
            CFG.setRender_3(true);
         }
      }

      lRegions_Civs.clear();
      lRegions_Civs_RegionsID.clear();

      try {
         int[] tempCivs = new int[CFG.game.getCivsSize()];

         for(int i = 0; i < CFG.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID() > 0) {
               if (tempCivs[CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()] <= 0) {
                  lRegions_Civs.add(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID());
                  tempCivs[CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()] = lRegions_Civs.size();
                  lRegions_Civs_RegionsID.add(new ArrayList<>());
                  lRegions_Civs_RegionsID.get(tempCivs[CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()] - 1)
                     .add(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivRegionID());
               } else {
                  boolean tempAdd = true;

                  for(int j = lRegions_Civs_RegionsID.get(tempCivs[CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()] - 1).size() - 1;
                     j >= 0;
                     --j
                  ) {
                     if (lRegions_Civs_RegionsID.get(tempCivs[CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()] - 1).get(j)
                        == CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivRegionID()) {
                        tempAdd = false;
                        break;
                     }
                  }

                  if (tempAdd) {
                     lRegions_Civs_RegionsID.get(tempCivs[CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivID()] - 1)
                        .add(CFG.game.getProvince(CFG.game.getProvinceInViewID(i)).getCivRegionID());
                  }
               }
            }
         }

         CFG.NUM_OF_REGIONS_IN_VIEW = lRegions_Civs.size();
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected static final void draw(SpriteBatch oSB) {
      CFG.map.drawMap(oSB);
      oRenderer.draw(oSB);
      if (CFG.map.getMapScale().getCurrentScale() < CIV_NAMES_START_DRAWING_NAMES_MAP_SCALE) {
         oRenderer_CivRegionNames.update();
         oRenderer_CivRegionNames.draw(oSB);
      } else {
         CIVILIZATIONS_NAMES_TIME = 0L;
      }
   }

   protected static final void drawWithoutScale(SpriteBatch oSB) {
      CFG.unionFlagsToGenerate_Manager.generateFlags(oSB);
      CFG.game.updateLoadArmiesWidth_ErrorIDs(oSB);
      oRenderer.drawWithoutScale(oSB);
   }

   protected static final void drawMapDetails(SpriteBatch oSB) {
      oRenderer.drawMapDetails(oSB);
   }

   protected static final void drawCivRegions_Names(SpriteBatch oSB) {
      try {
         if (!DISABLE_CIVS_NAMES) {
            for(int i = 0; i < CFG.NUM_OF_REGIONS_IN_VIEW; ++i) {
               try {
                  for(int j = lRegions_Civs_RegionsID.get(i).size() - 1; j >= 0; --j) {
                     try {
                        if (CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).drawName
                           && CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getFontScale()
                              > CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT
                                 * (1.0F + (CIV_NAMES_START_DRAWING_NAMES_MAP_SCALE - CFG.map.getMapScale().getCurrentScale()) / 4.0F)) {
                           if (CFG.map.getMapCoordinates().getSecondSideOfMap()) {
                              CFG.game
                                 .getCiv(lRegions_Civs.get(i))
                                 .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                 .drawCivilizationName(
                                    oSB,
                                    CFG.game
                                       .getCiv(lRegions_Civs.get(i))
                                       .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                       .getProvince(
                                          CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getShortestPath().get(0)
                                       ),
                                    CFG.game
                                       .getCiv(lRegions_Civs.get(i))
                                       .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                       .getProvince(
                                          CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getShortestPath().get(1)
                                       ),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getFontScale(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getAngle(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getCharMaxWidth(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getCharMaxHeight()
                                 );
                              CFG.game
                                 .getCiv(lRegions_Civs.get(i))
                                 .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                 .drawCivilizationName_SecondSideOfMap(
                                    oSB,
                                    CFG.game
                                       .getCiv(lRegions_Civs.get(i))
                                       .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                       .getProvince(
                                          CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getShortestPath().get(0)
                                       ),
                                    CFG.game
                                       .getCiv(lRegions_Civs.get(i))
                                       .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                       .getProvince(
                                          CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getShortestPath().get(1)
                                       ),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getFontScale(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getAngle(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getCharMaxWidth(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getCharMaxHeight()
                                 );
                           } else {
                              CFG.game
                                 .getCiv(lRegions_Civs.get(i))
                                 .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                 .drawCivilizationName(
                                    oSB,
                                    CFG.game
                                       .getCiv(lRegions_Civs.get(i))
                                       .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                       .getProvince(
                                          CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getShortestPath().get(0)
                                       ),
                                    CFG.game
                                       .getCiv(lRegions_Civs.get(i))
                                       .getCivRegion(lRegions_Civs_RegionsID.get(i).get(j))
                                       .getProvince(
                                          CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getShortestPath().get(1)
                                       ),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getFontScale(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getAngle(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getCharMaxWidth(),
                                    CFG.game.getCiv(lRegions_Civs.get(i)).getCivRegion(lRegions_Civs_RegionsID.get(i).get(j)).getCharMaxHeight()
                                 );
                           }
                        }
                     } catch (IndexOutOfBoundsException var4) {
                     } catch (NullPointerException var5) {
                     }
                  }
               } catch (IndexOutOfBoundsException var6) {
               } catch (NullPointerException var7) {
               }
            }
         }
      } catch (IndexOutOfBoundsException var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      } catch (NullPointerException var9) {
         if (CFG.LOGS) {
            var9.printStackTrace();
         }
      }
   }

   protected static final void updateDrawCivRegionNames_FogOfWar() {
      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         try {
            for(int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
               CFG.game.getCiv(i).getCivRegion(j).updateDrawRegionName();
            }
         } catch (IndexOutOfBoundsException var2) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var2);
            }
         } catch (NullPointerException var3) {
            if (CFG.LOGS) {
               var3.printStackTrace();
            }
         }
      }
   }

   protected static final void drawInGame(SpriteBatch oSB) {
      long tTime = System.currentTimeMillis();
      long tTime2 = System.currentTimeMillis();
      Game_Render_Province.drawProvincesInGame(oSB);
      if (CFG.map.getMapScale().getCurrentScale() >= DISABLE_INNER_BORDERS) {
         CFG.game.drawActiveProvince(oSB);
         CFG.game.drawHighlightProvince(oSB);
         CFG.game.updateHighlitghtProvinceBorder(oSB);
         Game_Render_Province.drawProvincesBorder(oSB);
      } else {
         Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
      }

      CFG.game.drawActiveProvinceBorder(oSB);
   }

   protected static final boolean drawInGame_WithoutScale_MapDetails() {
      return CFG.map.getMapScale().getCurrentScale() >= 1.0F && CFG.map.getMapScale().getCurrentScale() < CFG.settingsManager.STOP_SCALING_ARMY;
   }

   protected static final boolean drawInGame_MapDetails() {
      return CFG.map.getMapScale().getCurrentScale() >= CFG.settingsManager.STOP_SCALING_ARMY;
   }

   protected static final void updateDrawMoveUnits() {
      if (CFG.menuManager.getInGameView()) {
         if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.TURN_ACTIONS) {
            oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
               @Override
               public void drawMoveUnits(SpriteBatch oSB) {
                  if (CFG.map.getMapScale().getCurrentScale() < 1.0F
                     && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES
                     && CFG.gameAction.getCurrentMoveunits() != null) {
                     for(int i = 0; i < CFG.gameAction.getCurrentMoveunits().getMoveUnitsSize(); ++i) {
                        CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).draw(oSB, 1.0F);
                     }
                  }
               }

               @Override
               public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
                  if (CFG.map.getMapScale().getCurrentScale() >= 1.0F && CFG.gameAction.getCurrentMoveunits() != null) {
                     for(int i = 0; i < CFG.gameAction.getCurrentMoveunits().getMoveUnitsSize(); ++i) {
                        CFG.gameAction.getCurrentMoveunits().getMoveUnits(i).draw(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }
               }
            };
         } else {
            try {
               if (CFG.viewsManager.getActiveViewID() == -1 || CFG.viewsManager.getActiveViewID() >= 0 && CFG.viewsManager.getActiveView().canMoveArmy) {
                  if (CFG.SPECTATOR_MODE) {
                     oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
                        @Override
                        public void drawMoveUnits(SpriteBatch oSB) {
                           if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                              CFG.game.drawMoveUnits_Spectactor(oSB, 1.0F);
                              CFG.game.drawMoveUnits_CurrentMove(oSB, 1.0F);
                           }
                        }

                        @Override
                        public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
                           if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                              CFG.game.drawMoveUnits_Spectactor(oSB, CFG.map.getMapScale().getCurrentScale());
                              CFG.game.drawMoveUnits_CurrentMove(oSB, CFG.map.getMapScale().getCurrentScale());
                           }
                        }
                     };
                  } else {
                     oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
                        @Override
                        public void drawMoveUnits(SpriteBatch oSB) {
                           if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                              CFG.game.drawMoveUnits(oSB, 1.0F);
                              CFG.game.drawMoveUnits_CurrentMove(oSB, 1.0F);
                           }
                        }

                        @Override
                        public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
                           if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                              CFG.game.drawMoveUnits(oSB, CFG.map.getMapScale().getCurrentScale());
                              CFG.game.drawMoveUnits_CurrentMove(oSB, CFG.map.getMapScale().getCurrentScale());
                           }
                        }
                     };
                  }
               } else {
                  oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
                     @Override
                     public void drawMoveUnits(SpriteBatch oSB) {
                     }

                     @Override
                     public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
                     }
                  };
               }
            } catch (NullPointerException var1) {
               if (CFG.SPECTATOR_MODE) {
                  oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
                     @Override
                     public void drawMoveUnits(SpriteBatch oSB) {
                        if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                           CFG.game.drawMoveUnits_Spectactor(oSB, 1.0F);
                           CFG.game.drawMoveUnits_CurrentMove(oSB, 1.0F);
                        }
                     }

                     @Override
                     public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
                        if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                           CFG.game.drawMoveUnits_Spectactor(oSB, CFG.map.getMapScale().getCurrentScale());
                           CFG.game.drawMoveUnits_CurrentMove(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  };
               } else {
                  oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
                     @Override
                     public void drawMoveUnits(SpriteBatch oSB) {
                        if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                           CFG.game.drawMoveUnits(oSB, 1.0F);
                           CFG.game.drawMoveUnits_CurrentMove(oSB, 1.0F);
                        }
                     }

                     @Override
                     public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
                        if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                           CFG.game.drawMoveUnits(oSB, CFG.map.getMapScale().getCurrentScale());
                           CFG.game.drawMoveUnits_CurrentMove(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  };
               }
            }
         }
      } else if (CFG.SPECTATOR_MODE) {
         oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
            @Override
            public void drawMoveUnits(SpriteBatch oSB) {
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                  CFG.game.drawMoveUnits_Spectactor(oSB, 1.0F);
                  CFG.game.drawMoveUnits_CurrentMove(oSB, 1.0F);
               }
            }

            @Override
            public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
               if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                  CFG.game.drawMoveUnits_Spectactor(oSB, CFG.map.getMapScale().getCurrentScale());
                  CFG.game.drawMoveUnits_CurrentMove(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
         };
      } else {
         oDrawMoveUnits = new Game_Render.DrawMoveUnits() {
            @Override
            public void drawMoveUnits(SpriteBatch oSB) {
               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                  CFG.game.drawMoveUnits(oSB, 1.0F);
                  CFG.game.drawMoveUnits_CurrentMove(oSB, 1.0F);
               }
            }

            @Override
            public void drawMoveUnits_WithoutScale(SpriteBatch oSB) {
               if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                  CFG.game.drawMoveUnits(oSB, CFG.map.getMapScale().getCurrentScale());
                  CFG.game.drawMoveUnits_CurrentMove(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }
         };
      }
   }

   protected static final void updateRenderer_CivNames() {
      if ((
            !CFG.menuManager.getInGameView()
               || CFG.menuManager.getVisible_InGame_FlagAction()
               || !CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME
               || (!CFG.menuManager.getInGameView() || CFG.viewsManager.getActiveViewID() >= 0 && !CFG.viewsManager.getActiveView().drawCivNamesOver)
                  && (!RTS.isEnabled() || RTS.PAUSE)
         )
         && !CFG.menuManager.getInCreateNewGame()
         && !CFG.menuManager.getInSettingsProvince()) {
         oRenderer_CivRegionNames = new Game_Render.Renderer_CivRegionNames() {
            @Override
            public void draw(SpriteBatch oSB) {
            }

            @Override
            public void update() {
            }
         };
      } else {
         oRenderer_CivRegionNames = new Game_Render.Renderer_CivRegionNames() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render.drawCivRegions_Names(oSB);
            }

            @Override
            public void update() {
               Game_Render.updateRegionsInView();
            }
         };
      }
   }

   protected static final void updateRenderer() {
      updateRenderer_CivNames();
      if (CFG.menuManager.getInGameView()
         || CFG.menuManager.getInGame_PeaceTreaty()
         || CFG.menuManager.getInGame_PeaceTreaty_Response()
         || SaveManager.gameCanBeContinued && (CFG.menuManager.getInSettingsProvince() || CFG.menuManager.getInSettings())) {
         if ((!CFG.menuManager.getInGame_PeaceTreaty() || !Menu_PeaceTreaty_Response.DRAW_TREATY_PROVINCES)
            && (!CFG.menuManager.getInGame_PeaceTreaty_Response() || !Menu_PeaceTreaty_Response.DRAW_TREATY_PROVINCES)) {
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOAD_AI_RTO && CFG.gameAction.showNextPlayerTurnView()) {
               oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_LoadAI_RTO(oSB);
                     Game_Render_Province.drawProvincesBorder_LoadAI_RTO(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               } : new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_LoadAI_RTO(oSB);
                     Game_Render_Province.drawProvincesBorder_LoadAI_RTO(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.viewsManager.getActiveViewID() >= 0) {
               oRenderer = CFG.viewsManager.getActiveView().oRenderer;
            } else if (!CFG.isDesktop() && CFG.menuManager.getVisible_InGame_FlagAction() && !CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render.drawInGame(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.FOG_OF_WAR == 2) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render.drawInGame(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                        if (CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                           CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery_Sea(oSB, 1.0F);
                        } else {
                           CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
                        }
                     }

                     Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                        CFG.game.drawProvincesInfo_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }

                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        if (!Game_Render.DISABLE_CITIES) {
                           CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                           CFG.game.drawCities_ActiveProvince_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                        }

                        CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
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
               };
            } else {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render.drawInGame(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                        if (CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_SEA_ARMIES) {
                           CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Sea(oSB, 1.0F);
                        } else {
                           CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                        }
                     }

                     Game_Render.oDrawMoveUnits.drawMoveUnits(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     Game_Render.oDrawMoveUnits.drawMoveUnits_WithoutScale(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() >= 1.0F) {
                        CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                     }

                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        if (!Game_Render.DISABLE_CITIES) {
                           CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                           CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                        }

                        CFG.game.drawProvincesArmy(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
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
               };
            }
         } else {
            oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
               @Override
               public void draw(SpriteBatch oSB) {
                  Game_Render_Province.drawProvinces_PeaceTreaty_FogOfWarDiscovery(oSB);
                  CFG.game.drawActiveProvince(oSB);
                  if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                     Game_Render_Province.drawProvincesBorder_PeaceTreaty_FogOfWarDiscovery(oSB);
                  } else {
                     Game_Render_Province.drawProvincesBorder_PeaceTreaty_FogOfWarDiscovery_Only_CivilizationBorder(oSB);
                  }

                  if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                     CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
                  }
               }

               @Override
               public void drawWithoutScale(SpriteBatch oSB) {
                  if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawProvinces_Army_PeaceTreaty_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               }

               @Override
               public void drawMapDetails(SpriteBatch oSB) {
                  if (Game_Render.drawInGame_MapDetails()) {
                     CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     CFG.game.drawProvinces_Army_PeaceTreaty_FogOfWarDiscovery(oSB, 1.0F);
                  }
               }
            } : new Game_Render.Renderer() {
               @Override
               public void draw(SpriteBatch oSB) {
                  Game_Render_Province.drawProvinces_PeaceTreaty(oSB);
                  CFG.game.drawActiveProvince(oSB);
                  if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                     Game_Render_Province.drawProvincesBorder_PeaceTreaty(oSB);
                  } else {
                     Game_Render_Province.drawProvincesBorder_PeaceTreaty_Only_CivilizationBorder(oSB);
                  }

                  if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                     CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                  }
               }

               @Override
               public void drawWithoutScale(SpriteBatch oSB) {
                  if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                     CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     CFG.game.drawProvinces_Army_PeaceTreaty(oSB, CFG.map.getMapScale().getCurrentScale());
                  } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               }

               @Override
               public void drawMapDetails(SpriteBatch oSB) {
                  if (Game_Render.drawInGame_MapDetails()) {
                     CFG.game.drawCities(oSB, 1.0F);
                     CFG.game.drawProvinces_Army_PeaceTreaty(oSB, 1.0F);
                  }
               }
            };
         }
      } else if (CFG.menuManager.getInGame_CreateAVassal()) {
         if (CFG.viewsManager.getActiveViewID() >= 0) {
            oRenderer = CFG.viewsManager.getActiveView().oRenderer;
         } else if (CFG.FOG_OF_WAR == 2) {
            oRenderer = new Game_Render.Renderer() {
               @Override
               public void draw(SpriteBatch oSB) {
                  Game_Render_Province.drawProvincesInGame(oSB);
                  CFG.game.getSelectedProvinces().draw_CreateAVassal(oSB);
                  CFG.game.drawActiveProvince(oSB);
                  if (CFG.VIEW_SHOW_VALUES) {
                     if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                        Game_Render_Province.drawProvincesBorder(oSB);
                     } else {
                        Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                     }
                  } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                  if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                     if (CFG.VIEW_SHOW_VALUES) {
                        CFG.game.drawCities_All_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else {
                        CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }

                     CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               }

               @Override
               public void drawMapDetails(SpriteBatch oSB) {
                  if (Game_Render.drawInGame_MapDetails()) {
                     if (CFG.VIEW_SHOW_VALUES) {
                        CFG.game.drawCities_All_FogOfWarDiscovery(oSB, 1.0F);
                     } else {
                        CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                     }

                     CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
                  }
               }
            };
         } else {
            oRenderer = new Game_Render.Renderer() {
               @Override
               public void draw(SpriteBatch oSB) {
                  Game_Render_Province.drawProvincesInGame(oSB);
                  CFG.game.getSelectedProvinces().draw_CreateAVassal(oSB);
                  CFG.game.drawActiveProvince(oSB);
                  if (CFG.VIEW_SHOW_VALUES) {
                     if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                        Game_Render_Province.drawProvincesBorder(oSB);
                     } else {
                        Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                     }
                  } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                  if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                     if (CFG.VIEW_SHOW_VALUES) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else {
                        CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     }

                     CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                  } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                     CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               }

               @Override
               public void drawMapDetails(SpriteBatch oSB) {
                  if (Game_Render.drawInGame_MapDetails()) {
                     if (CFG.VIEW_SHOW_VALUES) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     } else {
                        CFG.game.drawCities(oSB, 1.0F);
                     }

                     CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                  }
               }
            };
         }
      } else if (!CFG.menuManager.getInGame_Timeline() && !CFG.menuManager.getInVictory()) {
         if (CFG.menuManager.getInGame_SelectProvinces()) {
            if (CFG.FOG_OF_WAR == 2) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                        } else {
                           CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All_FogOfWarDiscovery(oSB, 1.0F);
                        } else {
                           CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
                     }
                  }
               };
            } else {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        } else {
                           CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All(oSB, 1.0F);
                        } else {
                           CFG.game.drawCities(oSB, 1.0F);
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                     }
                  }
               };
            }
         } else if (CFG.menuManager.getInGame_ShowProvinces()) {
            if (CFG.FOG_OF_WAR == 2) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
                     }
                  }
               };
            } else {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities(oSB, 1.0F);
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                     }
                  }
               };
            }
         } else if (CFG.menuManager.getInGame_TradeSelectCiv()) {
            if (CFG.FOG_OF_WAR == 2) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_FogOfWarDiscovery(oSB, 1.0F);
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_FogOfWarDiscovery(oSB, 1.0F);
                     }
                  }
               };
            } else {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
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
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                     } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F && !Game_Render.DISABLE_CITIES) {
                        CFG.game.drawCities_OnlyCapitals_Images(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities(oSB, 1.0F);
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                     }
                  }
               };
            }
         } else if (!CFG.menuManager.getInStartGameMenu() && !CFG.menuManager.getInEndGameMenu()) {
            if (CFG.menuManager.getInGame_Formable_Civ_Provinces()) {
               oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_FormableCiv_FogOfWarDiscovery(oSB);

                     try {
                        if (CFG.getIsInFormableCiv(MenuManager.iHoveredProvinceID)) {
                           CFG.game.drawActiveProvince_HoverJust_WithoutDrawingActiveProvince(oSB);
                        }
                     } catch (IndexOutOfBoundsException var3) {
                     }

                     Game_Render_Province.drawProvincesBorder_NextPlayer(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        try {
                           if (CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0) {
                              CFG.game.drawCities_OnlyFormableCivCapital(oSB, CFG.map.getMapScale().getCurrentScale());
                           }
                        } catch (IndexOutOfBoundsException var3) {
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        try {
                           if (CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0) {
                              CFG.game.drawCities_OnlyFormableCivCapital(oSB, 1.0F);
                           }
                        } catch (IndexOutOfBoundsException var3) {
                        }
                     }
                  }
               } : new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_FormableCiv(oSB);

                     try {
                        if (CFG.getIsInFormableCiv(MenuManager.iHoveredProvinceID)) {
                           CFG.game.drawActiveProvince_HoverJust_WithoutDrawingActiveProvince(oSB);
                        }
                     } catch (IndexOutOfBoundsException var3) {
                     }

                     Game_Render_Province.drawProvincesBorder_NextPlayer(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        try {
                           if (CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0) {
                              CFG.game.drawCities_OnlyFormableCivCapital(oSB, CFG.map.getMapScale().getCurrentScale());
                           }
                        } catch (IndexOutOfBoundsException var3) {
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        try {
                           if (CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0) {
                              CFG.game.drawCities_OnlyFormableCivCapital(oSB, 1.0F);
                           }
                        } catch (IndexOutOfBoundsException var3) {
                        }
                     }
                  }
               };
            } else if (CFG.menuManager.getInGame_FormAnimation()) {
               oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     Game_Render_Province.drawProvincesBorder_NextPlayer(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
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
                     Game_Render_Province.drawProvinces(oSB);
                     Game_Render_Province.drawProvincesBorder_NextPlayer(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateNewGame()) {
               if (CFG.viewsManager.getActiveViewID() >= 0) {
                  oRenderer = CFG.viewsManager.getActiveView().oRenderer;
               } else {
                  oRenderer = new Game_Render.Renderer() {
                     @Override
                     public void draw(SpriteBatch oSB) {
                        Game_Render_Province.drawProvinces(oSB);
                        CFG.game.drawActiveProvince(oSB);
                        if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                           Game_Render_Province.drawProvincesBorder(oSB);
                        } else {
                           Game_Render_Province.drawProvincesBorder_Only_CivilizationBorder_InGame(oSB);
                        }

                        CFG.game.drawActiveProvinceBorder(oSB);
                        if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                           if (CFG.map.getMapScale().getCurrentScale() > 0.65F) {
                              CFG.game.drawCities(oSB, 1.0F);
                              CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                           }

                           if (Game_Render.drawCivNamesInCreateNewGame) {
                              CFG.game.drawAllCivilizations_Name_Flag_InCapitals_Vassals(oSB, 1.0F);
                           } else {
                              CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                           }
                        }
                     }

                     @Override
                     public void drawWithoutScale(SpriteBatch oSB) {
                        if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                           CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                           CFG.game.drawCities_ActiveProvince(oSB, CFG.map.getMapScale().getCurrentScale());
                           if (Game_Render.drawCivNamesInCreateNewGame) {
                              CFG.game.drawAllCivilizations_Name_Flag_InCapitals_Vassals(oSB, CFG.map.getMapScale().getCurrentScale());
                           } else {
                              CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                           }
                        }
                     }

                     @Override
                     public void drawMapDetails(SpriteBatch oSB) {
                        if (Game_Render.drawInGame_MapDetails()) {
                           if (!Game_Render.DISABLE_CITIES) {
                              CFG.game.drawCities(oSB, 1.0F);
                              CFG.game.drawCities_ActiveProvince(oSB, 1.0F);
                           }

                           if (Game_Render.drawCivNamesInCreateNewGame) {
                              CFG.game.drawAllCivilizations_Name_Flag_InCapitals_Vassals(oSB, 1.0F);
                           } else {
                              CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                           }
                        }
                     }
                  };
               }
            } else if (CFG.menuManager.getInRandomGame()
               || (CFG.menuManager.getInCreateScenario_Available_Provinces() || CFG.menuManager.getCreateScenario_ScenarioAge())
                  && CFG.backToMenu == Menu.eCREATE_RANDOM_GAME
               || CFG.menuManager.getInRandomGame_Civilizations_Select()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInCreateRandomGame(oSB);
                     CFG.game.drawActiveProvince(oSB);

                     try {
                        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));

                        for(int i = 0; i < CFG.randomGameManager.getPlayersSize(); ++i) {
                           if (CFG.randomGameManager.getPlayer(i).getCapitalProvinceID() >= 0) {
                              CFG.game.getProvince(CFG.randomGameManager.getPlayer(i).getCapitalProvinceID()).drawProvinceFlag_CreateRandomGame(oSB, i);
                           }
                        }
                     } catch (NullPointerException var3) {
                     } catch (IndexOutOfBoundsException var4) {
                     }

                     oSB.setColor(Color.WHITE);
                     if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                        Game_Render_Province.drawProvincesBorder_CreateRandomGame(oSB);
                     }

                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInManageDiplomacy() || CFG.menuManager.getInCustomizeAlliance()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F) {
                        if (CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                           CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                        if (CFG.game.getActiveProvinceID() >= 0
                           && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                           && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                           CFG.game.drawCivilization_Name_Flag(oSB, CFG.game.getActiveProvinceID(), 1.0F);
                        }
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (CFG.game.getActiveProvinceID() >= 0
                           && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                           && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                           CFG.game.drawCivilization_Name_Flag(oSB, CFG.game.getActiveProvinceID(), CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInNewGamePlayers()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 0.25F) {
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 0.25F) {
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInSelectCiv()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvinceFlag(oSB);
                     oSB.setColor(Color.WHITE);

                     for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                        if (CFG.game.getPlayer(i).getCivID() > 0) {
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCapitalProvinceID()).drawProvinceFlag(oSB);
                        }
                     }

                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 0.25F) {
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 0.25F) {
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Civilizations() || CFG.menuManager.getInCreateScenario_Civilizations_Select()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals_Crowns(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals_Crowns(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Assign()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        if (!CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawAllProvinces_Name_Flag(oSB, 1.0F);
                        } else {
                           CFG.game.drawAllCivilizations_Flag_InCapitals(oSB, 1.0F);
                        }
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (!CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawAllProvinces_Name_Flag(oSB, CFG.map.getMapScale().getCurrentScale());
                        } else {
                           CFG.game.drawAllCivilizations_Flag_InCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Assign_Select()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_WastelandMap()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                        Game_Render_Province.drawLandProvincesBorder(oSB);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_SetUpArmy()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawProvincesInfo(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawProvincesArmy_SetUpArmy(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        CFG.game.drawProvincesArmy_SetUpArmy(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Events_SelectProvinces()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Cores()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawCores_Flags(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        CFG.game.drawCores_Flags(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_FormableCivs_Edit()
               || CFG.menuManager.getInMapEditor_FormableCivs_SelectFormable()
               || CFG.menuManager.getInMapEditor_FormableCivs_SelectClaimant()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     if (CFG.VIEW_SHOW_VALUES) {
                        Game_Render_Province.drawProvincesInGame(oSB);
                     } else {
                        CFG.game.updateProvincesInView();
                     }

                     CFG.game.getSelectedProvinces().draw(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        Game_Render_Province.drawProvincesBorder(oSB);
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                        Game_Render_Province.drawProvincesBorder_DrawJustInnerBorder(oSB);
                     }

                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        } else {
                           CFG.game.drawCities_OnlyFormableCivCapital(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All(oSB, 1.0F);
                        } else {
                           CFG.game.drawCities_OnlyFormableCivCapital(oSB, 1.0F);
                        }
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_HolyRomanEmpire()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     if (CFG.VIEW_SHOW_VALUES) {
                        Game_Render_Province.drawProvincesInGame(oSB);
                     } else {
                        CFG.game.updateProvincesInView();
                     }

                     CFG.game.getSelectedProvinces().draw_HolyRomanEmpire(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        Game_Render_Province.drawProvincesBorder(oSB);
                     } else if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                        Game_Render_Province.drawProvincesBorder_DrawJustInnerBorder(oSB);
                     }

                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        } else {
                           CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawCities_All(oSB, 1.0F);
                        } else {
                           CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                        }

                        CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Available_Provinces()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInSelectAvailableCivilizations()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInCreateNewGameSelectAvailableCivs(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawAllProvinces_Name_Flag(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawAllProvinces_Name_Flag(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMainMenu()
               || CFG.menuManager.getInAboutMenu()
               || CFG.menuManager.getInInitMenu()
               || CFG.menuManager.getInLoadMap()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInPalletOfCivsColorsEdit()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals_AvailableCivs(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawAllCivilizations_Name_Flag_InCapitals_AvailableCivs(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateCity()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawEditorCity(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        CFG.game.drawEditorCity(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_Create_NewContinent()
               || CFG.menuManager.getInMapEditor_Create_NewRegion()
               || CFG.menuManager.getInGameEditor_Create_DiplomacyPackage()
               || CFG.menuManager.getInGameEditor_ReligionAdd()
               || CFG.menuManager.getInGameEditor_TerrainAdd()
               || CFG.menuManager.getInEditor_GameCivs()
               || CFG.menuManager.getInCreateCivilization()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_Terrain() || CFG.menuManager.getInMapEditor_Continents() || CFG.menuManager.getInMapEditor_Regions()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_GrowthRate()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince_HoverJust_WithoutDrawingActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_GrowthRate(oSB, 1.0F);
                        }
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_GrowthRate(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_TechnologyLevels()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_TechnologyLevels(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_TechnologyLevels(oSB, 1.0F);
                        }
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Happiness()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_Happiness(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_Happiness(oSB, 1.0F);
                        }
                     }
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_StartingMoney()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_StartingMoney(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                        if (CFG.VIEW_SHOW_VALUES) {
                           CFG.game.drawProvinces_StartingMoney(oSB, 1.0F);
                        }
                     }
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_ArmyPosition()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince_HoverJust_WithoutDrawingActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawProvinces_ArmyPosition(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                        CFG.game.drawProvinces_ArmyPosition(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_PortPosition()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawProvinces_Ports(oSB, 1.0F);
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawProvinces_Ports(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_ProvinceBackground()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGame(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     if (CFG.VIEW_SHOW_VALUES) {
                        Game_Render_Province.drawProvincesBorder(oSB);
                        CFG.game.drawActiveProvinceBorder(oSB);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_LoadPreDefinedBorders()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_InLoad_PreDefinedBorders(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_LoadSuggestedOwners()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInSelectLanguage()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_WastelandMaps_Edit()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesIn_MapEditor_WastelandMaps(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_All(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_All(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInGameEditor_Regions()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInGameEditorRegions(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F
                        && CFG.map.getMapScale().getCurrentScale() > 0.4F
                        && Menu_MapEditor_OptimizationRegions.showValues) {
                        CFG.game.drawProvinces_OptimizationRegions(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F && Menu_MapEditor_OptimizationRegions.showValues) {
                        CFG.game.drawProvinces_OptimizationRegions(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_SeaProvinces()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInMapEditor_SeaProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawProvinces_SeaProvincesLevels(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawProvinces_SeaProvincesLevels(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_ArmySeaBoxes()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInMapEditor_ArmySeaBoxes(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawProvinces_SeaArmyBoxes(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawProvinces_SeaArmyBoxes(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_Connections()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInMapEditor_Connections(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawProvinces_ArmyPosition(oSB, 1.0F);
                        if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 >= 0 && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getDrawProvince()) {
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).drawArmyPosition_Active(oSB, 1.0F);
                        }
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawProvinces_ArmyPosition(oSB, CFG.map.getMapScale().getCurrentScale());
                        if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 >= 0 && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getDrawProvince()) {
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).drawArmyPosition_Active(oSB, CFG.map.getMapScale().getCurrentScale());
                        }
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInSettingsProvince()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     if (CFG.FOG_OF_WAR == 2) {
                        Game_Render_Province.drawOccupiedProvinces_FogOfWar(oSB);
                     } else {
                        Game_Render_Province.drawOccupiedProvinces(oSB);
                     }

                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                        CFG.game.drawProvinces_ArmyPosition_Capitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities(oSB, 1.0F);
                        CFG.game.drawProvinces_ArmyPosition_Capitals(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_ArmySeaBoxes_Edit()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInMapEditor_ArmySeaBoxes_Edit(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawProvinces_SeaArmyBoxes_Edit(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawProvinces_SeaArmyBoxes_Edit(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInMapEditor_ArmySeaBoxes_Add()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvincesInMapEditor_ArmySeaBoxes_Add(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInNextPlayerTurn()) {
               oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_NextPlayer_Turn(oSB);
                     Game_Render_Province.drawProvincesBorder_NextPlayer(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               } : new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_NextPlayer_Turn(oSB);
                     Game_Render_Province.drawProvincesBorder_NextPlayer(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInGame_CivlizationView()) {
               oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_CivilizationView_FogOfWar(oSB);
                     Game_Render_Province.drawProvincesBorder_CivilizationView(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               } : new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces_CivilizationView(oSB);
                     Game_Render_Province.drawProvincesBorder_CivilizationView(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else if (CFG.menuManager.getInCreateScenario_Preview()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     CFG.game.drawActiveProvince(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     CFG.game.drawActiveProvinceBorder(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                     if (Game_Render.drawInGame_MapDetails()) {
                        CFG.game.drawCities_OnlyCapitals(oSB, 1.0F);
                     }
                  }
               };
            } else if (CFG.menuManager.getInPrintAMap()) {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     oSB.setColor(new Color(0.06666667F, 0.11764706F, 0.19607843F, 1.0F));
                     ImageManager.getImage(Images.pix255_255_255)
                        .draw2(oSB, 0, -ImageManager.getImage(Images.pix255_255_255).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
                     oSB.setColor(Color.WHITE);
                     Game_Render_Province.drawProvinces_PrintAMap(oSB);
                     Game_Render_Province.drawProvincesBorder_PrintAMap(oSB);
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            } else {
               oRenderer = new Game_Render.Renderer() {
                  @Override
                  public void draw(SpriteBatch oSB) {
                     Game_Render_Province.drawProvinces(oSB);
                     Game_Render_Province.drawProvincesBorder(oSB);
                     if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                        CFG.game.drawCities(oSB, 1.0F);
                     }
                  }

                  @Override
                  public void drawWithoutScale(SpriteBatch oSB) {
                     if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                        CFG.game.drawCities(oSB, CFG.map.getMapScale().getCurrentScale());
                     }
                  }

                  @Override
                  public void drawMapDetails(SpriteBatch oSB) {
                  }
               };
            }
         } else if (CFG.FOG_OF_WAR == 2) {
            oRenderer = new Game_Render.Renderer() {
               @Override
               public void draw(SpriteBatch oSB) {
                  Game_Render_Province.drawProvincesInStartGame_FogOfWarDiscovery(oSB);
                  Game_Render_Province.drawProvincesBorderInStartGame_FogOfWar(oSB);
                  if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                     CFG.game.drawCities_OnlyCapitals_StartTheGame_FogOfWarDiscovery(oSB, 1.0F);
                  }
               }

               @Override
               public void drawWithoutScale(SpriteBatch oSB) {
                  if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                     CFG.game.drawCities_OnlyCapitals_StartTheGame_FogOfWarDiscovery(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               }

               @Override
               public void drawMapDetails(SpriteBatch oSB) {
               }
            };
         } else {
            oRenderer = new Game_Render.Renderer() {
               @Override
               public void draw(SpriteBatch oSB) {
                  Game_Render_Province.drawProvincesInStartGame(oSB);
                  Game_Render_Province.drawProvincesBorderInStartGame(oSB);
                  if (CFG.map.getMapScale().getCurrentScale() <= 1.0F && CFG.map.getMapScale().getCurrentScale() > 0.4F) {
                     CFG.game.drawCities_OnlyCapitals_StartTheGame(oSB, 1.0F);
                  }
               }

               @Override
               public void drawWithoutScale(SpriteBatch oSB) {
                  if (CFG.map.getMapScale().getCurrentScale() > 1.0F) {
                     CFG.game.drawCities_OnlyCapitals_StartTheGame(oSB, CFG.map.getMapScale().getCurrentScale());
                  }
               }

               @Override
               public void drawMapDetails(SpriteBatch oSB) {
               }
            };
         }
      } else {
         oRenderer = CFG.FOG_OF_WAR == 2 ? new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvinces_Timeline_FogOfWar(oSB);
               if (TimelapseManager.PAUSE) {
                  CFG.game.drawActiveProvince(oSB);
               }

               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  Game_Render_Province.drawProvincesBorder_Timeline(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Timeline_OnlyCivilizationBorder(oSB);
               }

               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline_FogOfWar(oSB, 1.0F);
               }
            }

            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawCities_Timeline_FogOfWar(oSB, CFG.map.getMapScale().getCurrentScale());
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline_FogOfWar(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F) {
                  CFG.game.drawCities_Timeline_FogOfWar_OnlyCapitalsImages(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }

            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCities_Timeline_FogOfWar(oSB, 1.0F);
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline_FogOfWar(oSB, 1.0F);
               }
            }
         } : new Game_Render.Renderer() {
            @Override
            public void draw(SpriteBatch oSB) {
               Game_Render_Province.drawProvinces_Timeline(oSB);
               if (TimelapseManager.PAUSE) {
                  CFG.game.drawActiveProvince(oSB);
               }

               if (CFG.map.getMapScale().getCurrentScale() >= Game_Render.DISABLE_INNER_BORDERS) {
                  Game_Render_Province.drawProvincesBorder_Timeline(oSB);
               } else {
                  Game_Render_Province.drawProvincesBorder_Timeline_OnlyCivilizationBorder(oSB);
               }

               if (CFG.map.getMapScale().getCurrentScale() < 1.0F && CFG.map.getMapScale().getCurrentScale() > Game_Render.DISABLE_INNER_BORDERS) {
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline(oSB, 1.0F);
               }
            }

            @Override
            public void drawWithoutScale(SpriteBatch oSB) {
               if (Game_Render.drawInGame_WithoutScale_MapDetails()) {
                  CFG.game.drawCities_Timeline(oSB, CFG.map.getMapScale().getCurrentScale());
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline(oSB, CFG.map.getMapScale().getCurrentScale());
               } else if (CFG.map.getMapScale().getCurrentScale() < 1.0F) {
                  CFG.game.drawCities_Timeline_OnlyCapitalsImages(oSB, CFG.map.getMapScale().getCurrentScale());
               }
            }

            @Override
            public void drawMapDetails(SpriteBatch oSB) {
               if (Game_Render.drawInGame_MapDetails()) {
                  CFG.game.drawCities_Timeline(oSB, 1.0F);
                  CFG.game.drawAllCivilizations_Flag_InCapitals_WithCrown_Timeline(oSB, 1.0F);
               }
            }
         };
      }
   }

   interface DrawMoveUnits {
      void drawMoveUnits(SpriteBatch var1);

      void drawMoveUnits_WithoutScale(SpriteBatch var1);
   }

   interface Renderer {
      void draw(SpriteBatch var1);

      void drawWithoutScale(SpriteBatch var1);

      void drawMapDetails(SpriteBatch var1);
   }

   interface Renderer_CivRegionNames {
      void draw(SpriteBatch var1);

      void update();
   }
}
