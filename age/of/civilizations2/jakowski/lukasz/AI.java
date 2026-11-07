package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class AI {
   protected boolean doneLoadingOrders = false;
   protected int iLoadingTurnActionsOfCivID = 0;
   private List<AI_Style> lAI_Styles = new ArrayList<>();
   protected int NUM_OF_CIVS_IN_THE_GAME = 0;
   protected int PLAYABLE_PROVINCES = 1;
   protected int MIN_NUM_OF_RIVALS = 1;
   protected List<List<AI_Frontline>> lFrontLines = new ArrayList<>();
   protected List<Integer> lNeutralProvincesWithSeaAccess = new ArrayList<>();
   protected int iNeutralProvincesWithSeaAccessSize = 0;
   protected List<Integer> lWastelandProvincesWithSeaAccess = new ArrayList<>();
   protected static int REBUILD_PERSONALITY = 87;
   protected static final int STATUS_QUO_TURNS = 39;
   protected static final int STATUS_QUO_TURNS_NO_ONE_ATTACKED = 19;
   protected static final int STATUS_QUO_NO_PROGRESS = 49;
   protected static final int STATUS_QUO_TOO_LONG = 299;
   protected int iNumOfColonizedProvinces = 0;
   protected final int DANGER_EXTRA_AT_WAR = 450;
   protected AI.Expand expandNeutral;

   protected AI() {
      this.updateExpand();
      this.lAI_Styles.add(new AI_Style());
      this.lAI_Styles.add(new AI_Style_Communism());
      this.lAI_Styles.add(new AI_Style_Horde());
      this.lAI_Styles.add(new AI_Style_Fascism());
      this.lAI_Styles.add(new AI_Style_CityState());
      this.lAI_Styles.add(new AI_Style_Tribal());
      this.lAI_Styles.add(new AI_Style_Rebels());
      this.build_RebuildPersonality();
   }

   protected final int getAIStyle_ByTag(String nTag) {
      for(int i = 0; i < this.lAI_Styles.size(); ++i) {
         if (this.lAI_Styles.get(i).TAG.equals(nTag)) {
            return i;
         }
      }

      return 0;
   }

   protected final AI_Style getAI_Style(int i) {
      try {
         return this.lAI_Styles.get(i);
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return this.lAI_Styles.get(0);
      }
   }

   protected final void resetNeutralProvincesWithSeaAccess() {
      this.lNeutralProvincesWithSeaAccess.clear();
      this.iNeutralProvincesWithSeaAccessSize = 0;
   }

   protected final void addNeutralProvincesWithSeaAccess(int nProvinceID) {
      this.lNeutralProvincesWithSeaAccess.add(nProvinceID);
   }

   protected final void resetWastelandProvincesWithSeaAccess() {
      this.lWastelandProvincesWithSeaAccess.clear();
   }

   protected final void addWastelandProvincesWithSeaAccess(int nProvinceID) {
      this.lWastelandProvincesWithSeaAccess.add(nProvinceID);
   }

   protected final void build_RebuildPersonality() {
      REBUILD_PERSONALITY = 79 + CFG.oR.nextInt(20);
   }

   protected final void checkCurrentWars_LookingForPeace() {
      try {
         for(int i = 0; i < CFG.game.getWarsSize(); ++i) {
            for(int j = 0; j < CFG.game.getWar(i).getDefendersSize(); ++j) {
               if (CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(j).getCivID()).getNumOfProvinces() == 0
                  && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(j).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                  for(int k = 0; k < CFG.game.getWar(i).getAggressorsSize(); ++k) {
                     if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(k).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game
                           .getCiv(CFG.game.getWar(i).getAggressorID(k).getCivID())
                           .getCivilization_Diplomacy_GameData()
                           .messageBox
                           .addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getDefenderID(j).getCivID()));
                     }
                  }
               }
            }

            for(int j = 0; j < CFG.game.getWar(i).getAggressorsSize(); ++j) {
               if (CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(j).getCivID()).getNumOfProvinces() == 0
                  && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(j).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                  for(int k = 0; k < CFG.game.getWar(i).getDefendersSize(); ++k) {
                     if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(k).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game
                           .getCiv(CFG.game.getWar(i).getDefenderID(k).getCivID())
                           .getCivilization_Diplomacy_GameData()
                           .messageBox
                           .addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getAggressorID(j).getCivID()));
                     }
                  }
               }
            }

            if (CFG.game.getWar(i).iLastFight_InTunrs > (CFG.game.getWar(i).wasAnyAttack ? 39 : 19)
               || CFG.game.getWar(i).iLastTurn_ConqueredProvince < Game_Calendar.TURN_ID - 49
               || CFG.game.getWar(i).getWarTurnID() < Game_Calendar.TURN_ID - (299 + CFG.game.getCivsSize())) {
               for(int j = 0; j < CFG.game.getWar(i).getAggressorsSize(); ++j) {
                  if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(j).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                     for(int k = 0; k < CFG.game.getWar(i).getDefendersSize(); ++k) {
                        if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(k).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                           CFG.game
                              .getCiv(CFG.game.getWar(i).getDefenderID(k).getCivID())
                              .getCivilization_Diplomacy_GameData()
                              .messageBox
                              .addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getAggressorID(j).getCivID()));
                        }
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
      } catch (NullPointerException var5) {
         CFG.exceptionStack(var5);
      }
   }

   protected final void turnOrders() {
      this.doneLoadingOrders = false;

      try {
         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (!CFG.game.getCiv(i).getControlledByPlayer()) {
               if (Game_Calendar.TURN_ID % REBUILD_PERSONALITY == i % REBUILD_PERSONALITY && CFG.oR.nextInt(100) > 54) {
                  CFG.game.getCiv(i).buildCivPersonality();
               } else if (Game_Calendar.TURN_ID % CFG.game.getCiv(i).civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN
                     == i % CFG.game.getCiv(i).civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN
                  && CFG.oR.nextInt(100) > 28) {
                  CFG.game.getCiv(i).buildCivPersonality_MoreOften();
               }
            }
         }

         this.checkCurrentWars_LookingForPeace();

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            try {
               if (!CFG.game.getCiv(i).getControlledByPlayer()) {
                  if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                     this.iLoadingTurnActionsOfCivID = i;
                     CFG.setRender_3(true);
                     this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).turnOrdersEssential(i);
                     this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).turnOrders(i);
                  } else {
                     this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).respondToMessages(i);
                  }
               }
            } catch (IndexOutOfBoundsException var8) {
               CFG.exceptionStack(var8);
            } catch (ArithmeticException var9) {
               CFG.exceptionStack(var9);
            } catch (NullPointerException var10) {
               CFG.exceptionStack(var10);
            }
         }

         for(int i = Game_Calendar.TURN_ID % 6; i < CFG.game.getCivsSize(); i += 6) {
            if (!CFG.game.getCiv(i).getControlledByPlayer()) {
               this.lAI_Styles.get(CFG.game.getCiv(i).getAI_Style()).manageVassalsTribute(i);
            }
         }
      } finally {
         this.doneLoadingOrders = true;
      }
   }

   protected final void updateMinRivals() {
      this.MIN_NUM_OF_RIVALS = (int)Math.min(3.0, Math.ceil((double)((float)(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME - 1) / 2.0F)));
   }

   protected final void buildAIData() {
      this.resetNeutralProvincesWithSeaAccess();
      this.resetWastelandProvincesWithSeaAccess();
      this.iNumOfColonizedProvinces = 0;
      this.NUM_OF_CIVS_IN_THE_GAME = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).setSeaAccess(0);
         CFG.game.getCiv(i).clearSeaAccess_Provinces();
         CFG.game.getCiv(i).clearSeaAccess_PortProvinces();
         CFG.game.getCiv(i).setBordersWithEnemy(0);
         CFG.game.getCiv(i).setIsAtWar(false);
         CFG.game.getCiv(i).setCanExpandOnContinent(false);
         CFG.game.getCiv(i).setNumOfNeighboringNeutralProvinces(0);
         CFG.game.getCiv(i).lArmiesPosition.clear();
         CFG.game.getCiv(i).iArmiesPositionSize = 0;
         CFG.game.getCiv(i).lBorderWithCivs.clear();
         CFG.game.getCiv(i).iBorderWithCivsSize = 0;
         CFG.game.getCiv(i).iAveragePopulation = 1;
         CFG.game.getCiv(i).lBordersWithNeutralProvincesID.clear();
         CFG.game.getCiv(i).lBordersWithWastelandProvincesID.clear();
         CFG.game.getCiv(i).civGameData.civPlans.updateObsolateMissions();
         CFG.game.countAvarageDevelopmentLevel_Float(i);
         CFG.game.getCiv(i).lProvincesWithHighRevRisk.clear();
         CFG.game.getCiv(i).isAtWarWithCivs.clear();
         CFG.game.getCiv(i).iNumOf_Forts = 0;
         CFG.game.getCiv(i).iNumOf_Towers = 0;
         CFG.game.getCiv(i).iNumOf_Ports = 0;
         CFG.game.getCiv(i).iNumOf_Farms = 0;
         CFG.game.getCiv(i).iNumOf_Farms_ProvincesPossibleToBuild = 0;
         CFG.game.getCiv(i).iNumOf_Workshops = 0;
         CFG.game.getCiv(i).iNumOf_Libraries = 0;
         CFG.game.getCiv(i).iNumOf_Armories = 0;
         CFG.game.getCiv(i).iNumOf_SuppliesCamp = 0;
         this.iNumOfColonizedProvinces += CFG.game.getCiv(i).civGameData.lColonies_Founded.size();
      }

      ViewsManager.updateMaxPopulation();
      ViewsManager.updateMaxEconomy();

      for(int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
         for(int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
            if (CFG.game.getCivsAtWar(i, j)) {
               CFG.game.getCiv(i).setIsAtWar(true);
               CFG.game.getCiv(j).setIsAtWar(true);
               CFG.game.getCiv(i).isAtWarWithCivs.add(j);
               CFG.game.getCiv(j).isAtWarWithCivs.add(i);
            }
         }

         for(int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
            CFG.game.getCiv(i).getCivRegion(j).iAveragePotential = 0;
         }
      }

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            ++this.NUM_OF_CIVS_IN_THE_GAME;
            if (CFG.game.getCiv(i).isAtWar()) {
               ++CFG.game.getCiv(i).civGameData.iNumOfTurnsAtWar;
            } else {
               Save_Civ_GameData var22 = CFG.game.getCiv(i).civGameData;
               var22.iNumOfTurnsAtWar -= 2;
               if (CFG.game.getCiv(i).civGameData.iNumOfTurnsAtWar < 0) {
                  CFG.game.getCiv(i).civGameData.iNumOfTurnsAtWar = 0;
               }
            }
         }
      }

      this.updateMinRivals();
      this.PLAYABLE_PROVINCES = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            if (CFG.game.getProvince(i).getWasteland() >= 0) {
               if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
                  for(int j = 0; j < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
                     if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getLevelOfPort() == -2) {
                        this.addWastelandProvincesWithSeaAccess(i);
                        break;
                     }
                  }
               }
            } else {
               this.buildProvinceData(i);
               ++this.PLAYABLE_PROVINCES;
            }
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
            if (CFG.game.getCiv(i).getCivRegion(j).getProvincesSize() > 0) {
               CFG.game.getCiv(i).getCivRegion(j).iAveragePotential /= CFG.game.getCiv(i).getCivRegion(j).getProvincesSize();
            }
         }

         CFG.game.getCiv(i).iArmiesPositionSize = CFG.game.getCiv(i).lArmiesPosition.size();
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            CFG.game.getCiv(i).iAveragePopulation /= CFG.game.getCiv(i).getNumOfProvinces();
         } else {
            CFG.game.getCiv(i).iAveragePopulation = 1;
         }

         for(int j = 0; j < CFG.game.getCiv(i).getConstructionsSize(); ++j) {
            if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.FARM) {
               ++CFG.game.getCiv(i).iNumOf_Farms;
            } else if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.ARMOURY) {
               ++CFG.game.getCiv(i).iNumOf_Armories;
            } else if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.TOWER) {
               ++CFG.game.getCiv(i).iNumOf_Towers;
            } else if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.LIBRARY) {
               ++CFG.game.getCiv(i).iNumOf_Libraries;
            } else if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.PORT) {
               ++CFG.game.getCiv(i).iNumOf_Ports;
            } else if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.FORT) {
               ++CFG.game.getCiv(i).iNumOf_Forts;
            } else if (CFG.game.getCiv(i).getConstruction(j).constructionType == ConstructionType.SUPPLY) {
               ++CFG.game.getCiv(i).iNumOf_SuppliesCamp;
            }
         }
      }

      this.lFrontLines.clear();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         List<AI_Frontline> nFrontline = new ArrayList<>();
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            for(int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
               for(int k = 0; k < CFG.game.getCiv(i).getCivRegion(j).getProvincesSize(); ++k) {
                  if (CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getDangerLevel() > 0) {
                     for(int u = 0; u < CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvincesSize(); ++u) {
                        if (CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                 .getCivID()
                              > 0
                           && CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                 .getCivID()
                              != i
                           && !CFG.game
                              .getCivsAreAllied(
                                 i,
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                    .getCivID()
                              )
                           && CFG.game.getCiv(i).getPuppetOfCivID()
                              != CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                 .getCivID()
                           && CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID()
                                 )
                                 .getPuppetOfCivID()
                              != i
                           && CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID()
                                 )
                                 .getPuppetOfCivID()
                              != CFG.game.getCiv(i).getPuppetOfCivID()) {
                           boolean addNew = true;

                           for(int o = 0; o < nFrontline.size(); ++o) {
                              if (nFrontline.get(o).iRegionID == j
                                 && nFrontline.get(o).iWithCivID
                                    == CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID()) {
                                 addNew = false;
                                 nFrontline.get(o).lProvinces.add(CFG.game.getCiv(i).getCivRegion(j).getProvince(k));
                                 if (CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getBordersWithEnemy()) {
                                    nFrontline.get(o).bordersWithEnemy = true;
                                 }
                                 break;
                              }
                           }

                           if (addNew) {
                              nFrontline.add(
                                 new AI_Frontline(
                                    CFG.game.getCiv(i).getCivRegion(j).getProvince(k),
                                    j,
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID(),
                                    CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getBordersWithEnemy()
                                 )
                              );
                           }
                        }
                     }
                  }
               }
            }
         }

         this.lFrontLines.add(nFrontline);
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            for(int j = 0; j < CFG.game.getCiv(i).civGameData.civPlans.iWarPreparationsSize; ++j) {
               for(int f = 0; f < this.lFrontLines.get(i - 1).size(); ++f) {
                  if (this.lFrontLines.get(i - 1).get(f).iWithCivID == CFG.game.getCiv(i).civGameData.civPlans.warPreparations.get(j).onCivID) {
                     for(int e = 0; e < this.lFrontLines.get(i - 1).get(f).lProvinces.size(); ++e) {
                        CFG.game
                           .getProvince(this.lFrontLines.get(i - 1).get(f).lProvinces.get(e))
                           .addDangerLevel(
                              (int)(450.0F * (0.675F + 0.325F / (float)CFG.game.getCiv(i).civGameData.civPlans.warPreparations.get(j).iNumOfTurnsLeft))
                           );
                     }
                  }
               }
            }
         }
      }

      this.iNeutralProvincesWithSeaAccessSize = this.lNeutralProvincesWithSeaAccess.size();
      Gdx.app.log("AI", "--------- TURN: " + Game_Calendar.TURN_ID + " ---------");
      CFG.setRender_3(true);
   }

   protected final void buildProvinceData(int i) {
      CFG.game.getProvince(i).setBordersWithEnemy(false);
      CFG.game.getProvince(i).setDangerLevel(0);
      CFG.game.getProvince(i).setPotential(245);
      CFG.game.getProvince(i).setNumOfNeighboringNeutralProvinces(0);
      CFG.game.getProvince(i).was = false;
      CFG.game.getProvince(i).buildRecruitableArmyPoints();
      if (CFG.game.getProvince(i).getRevolutionaryRisk() > 0.56F) {
         CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).lProvincesWithHighRevRisk.add(i);
      }

      if (CFG.game.getProvince(i).getCivID() > 0) {
         Civilization var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Forts += CFG.game.getProvince(i).getLevelOfFort();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Towers += CFG.game.getProvince(i).getLevelOfWatchTower();
         if (CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()) >= 0.0F) {
            var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
            var10000.iNumOf_Farms += CFG.game.getProvince(i).getLevelOfFarm();
            ++CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).iNumOf_Farms_ProvincesPossibleToBuild;
         }

         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Workshops += CFG.game.getProvince(i).getLevelOfWorkshop();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Libraries += CFG.game.getProvince(i).getLevelOfLibrary();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Armories += CFG.game.getProvince(i).getLevelOfArmoury();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_SuppliesCamp += CFG.game.getProvince(i).getLevelOfSupply();
         if (CFG.game.getProvince(i).getLevelOfPort() > 0) {
            var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
            var10000.iNumOf_Ports += CFG.game.getProvince(i).getLevelOfPort();
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addSeaAccess_PortProvinces(i);
         }

         if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addSeaAccess_Provinces(i);
         }

         for(int k = 0; k < CFG.game.getProvince(i).getCivsSize(); ++k) {
            if (CFG.game.getProvince(i).getArmy(k) > 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID(k)).lArmiesPosition.add(i);
            }
         }

         for(int j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() > 0) {
               CFG.game
                  .getCiv(CFG.game.getProvince(i).getCivID())
                  .addBordersWithCivID(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID());
            } else if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getWasteland() >= 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).lBordersWithWastelandProvincesID.add(CFG.game.getProvince(i).getNeighboringProvinces(j));
            } else if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() == 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).lBordersWithNeutralProvincesID.add(CFG.game.getProvince(i).getNeighboringProvinces(j));
            }
         }
      } else if (CFG.game.getProvince(i).getSeaProvince()) {
         for(int k = 1; k < CFG.game.getProvince(i).getCivsSize(); ++k) {
            if (CFG.game.getProvince(i).getArmy(k) > 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID(k)).lArmiesPosition.add(i);
            }
         }
      }

      if (CFG.game.getProvince(i).getWasAttacked() > 0) {
         CFG.game
            .getProvince(i)
            .addDangerLevel(
               (int)(
                  CFG.game.getProvince(i).getIsCapital()
                     ? 45.0F
                     : 10.0F
                        * (
                           (
                                 100.0F
                                    - (float)(35 * CFG.game.getProvince(i).getArmy(0))
                                       / (float)CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfUnits()
                              )
                              / 100.0F
                        )
               )
            );
         CFG.game.getProvince(i).setArmyWasRecruited(0);
         CFG.game.getProvince(i).setWasAttacked(CFG.game.getProvince(i).getWasAttacked() - 1);
      }

      CFG.game.getProvince(i).addPotential(CFG.game.getProvince(i).getNeighboringProvincesSize());
      CFG.game.getProvince(i).addPotential(CFG.game.getProvince(i).getNeighboringSeaProvincesSize());
      CFG.game
         .getProvince(i)
         .addPotential((int)((float)(235 * CFG.game.getProvince(i).getPopulationData().getPopulation()) / (float)ViewsManager.POPULATION_MAX));
      CFG.game.getProvince(i).addPotential((int)(185.0F * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm()));
      CFG.game.getProvince(i).addPotential((int)((float)(175 * CFG.game.getProvince(i).getEconomy()) / (float)ViewsManager.ECONOMY_MAX));
      CFG.game.getProvince(i).addPotential((int)(115.0F * CFG.game.getProvince(i).getDevelopmentLevel()));
      CFG.game.getProvince(i).addDangerLevel((int)CFG.game.getProvince(i).getRevolutionaryRisk());
      if (CFG.game.getProvince(i).getCivID() == 0) {
         CFG.game
            .getProvince(i)
            .addPotential(
               225
                  + (int)(
                     (375.0F + 275.0F * (0.5F + 0.1F * (float)CFG.game.getProvince(i).getNeighboringProvincesSize()))
                        * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm()
                  )
            );

         for(int j = 0; j < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getLevelOfPort() == -2) {
               this.addNeutralProvincesWithSeaAccess(i);
               break;
            }
         }
      } else {
         Civilization var23 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var23.iAveragePopulation += CFG.game.getProvince(i).getPopulationData().getPopulation();
         if (CFG.game.getProvince(i).getLevelOfWatchTower() > 0) {
            CFG.game.getProvince(i).addPotential(4 * CFG.game.getProvince(i).getLevelOfWatchTower() * CFG.game.getProvince(i).getNeighboringProvincesSize());
         }

         CFG.game.getProvince(i).addPotential(6 * CFG.game.getProvince(i).getLevelOfPort() * CFG.game.getProvince(i).getNeighboringProvincesSize());
         CFG.game.getProvince(i).addPotential(5 * CFG.game.getProvince(i).getLevelOfFort());
         CFG.game.getProvince(i).addPotential(3 * CFG.game.getProvince(i).getLevelOfFarm());
         CFG.game.getProvince(i).addPotential(4 * CFG.game.getProvince(i).getLevelOfWorkshop());
         if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setSeaAccess(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSeaAccess() + 1);
         }

         int nNeighbooringOwnProvinces = 0;

         for(int j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() > 0) {
               if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                  && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID()
                     != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()).getPuppetOfCivID()) {
                  if (CFG.game
                     .getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID())) {
                     CFG.game.getProvince(i).setBordersWithEnemy(true);
                     CFG.game
                        .getProvince(i)
                        .addDangerLevel(
                           (int)(
                              (float)(CFG.game.getProvince(i).getIsCapital() ? 64 : 24)
                                 * (CFG.game.getProvince(i).getWasAttacked() > 0 ? 1.775F : 1.0F)
                                 * (float)(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getWasConquered() + 1)
                           )
                        );
                  }

                  if (!CFG.game
                        .getCivsAreAllied(
                           CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                        )
                     && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID()
                        != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()).getPuppetOfCivID()
                     && CFG.game
                           .getDefensivePact(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                           )
                        == 0
                     && CFG.game
                           .getGuarantee(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                           )
                        == 0
                     && CFG.game
                           .getCivNonAggressionPact(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                           )
                        == 0
                     && CFG.game
                           .getCivTruce(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                           )
                        < 4) {
                     CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setCanExpandOnContinent(true);
                     CFG.game.getProvince(i).addDangerLevel(CFG.game.getProvince(i).getIsCapital() ? 14 : 6);
                     CFG.game
                        .getProvince(i)
                        .addDangerLevel(
                           (int)(
                              (CFG.game.getProvince(i).getIsCapital() ? 48.75F : 33.45F)
                                 * (
                                    CFG.game
                                          .getCivsAtWar(
                                             CFG.game.getProvince(i).getCivID(),
                                             CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                                          )
                                       ? 4.875F * (float)(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getWasConquered() + 1)
                                       : Math.max(
                                          0.75F,
                                          1.55F
                                             - CFG.game
                                                   .getCivRelation_OfCivB(
                                                      CFG.game.getProvince(i).getCivID(),
                                                      CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()
                                                   )
                                                / 25.0F
                                       )
                                 )
                                 * (
                                    0.625F
                                       + Math.min(
                                          1.42F,
                                          (float)CFG.game
                                                .getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID())
                                                .getNumOfProvinces()
                                             / (float)CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfProvinces()
                                             / (float)CFG.game.getProvince(i).getNeighboringProvincesSize()
                                       )
                                 )
                           )
                        );
                  }

                  CFG.game
                     .getProvince(i)
                     .addPotential(
                        -(
                           (int)(
                              CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_POPULATION
                                 * 0.85F
                                 * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getPopulationData().getPopulation()
                                 / (float)ViewsManager.POPULATION_MAX
                           )
                        )
                     );
                  CFG.game
                     .getProvince(i)
                     .addPotential(
                        -(
                           (int)(
                              CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_ECONOMY
                                 * 0.85F
                                 * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getEconomy()
                                 / (float)ViewsManager.ECONOMY_MAX
                           )
                        )
                     );
                  CFG.game.getProvince(i).addPotential(-24);
               } else {
                  CFG.game.getProvince(i).addPotential(24);
                  ++nNeighbooringOwnProvinces;
               }
            } else {
               CFG.game.getProvince(i).setNumOfNeighboringNeutralProvinces(CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces() + 1);
               CFG.game
                  .getProvince(i)
                  .addPotential(5 + (int)(4.0F + 46.0F * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getGrowthRate_Population()));
            }

            CFG.game
               .getProvince(i)
               .addPotential(
                  (int)(
                     CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_POPULATION
                        * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getPopulationData().getPopulation()
                        / (float)ViewsManager.POPULATION_MAX
                  )
               );
            CFG.game
               .getProvince(i)
               .addPotential(
                  (int)(
                     CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_ECONOMY
                        * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getEconomy()
                        / (float)ViewsManager.ECONOMY_MAX
                  )
               );
         }

         if (nNeighbooringOwnProvinces > 0) {
            CFG.game
               .getProvince(i)
               .setDangerLevel(
                  (int)(
                     (float)CFG.game.getProvince(i).getDangerLevel()
                        + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.DANGER_EXTRA_PER_OWN_PROVINCE
                           * (float)nNeighbooringOwnProvinces
                           * (float)CFG.game.getProvince(i).getDangerLevel()
                  )
               );
         }

         if (CFG.game.getProvince(i).getBordersWithEnemy()) {
            CFG.game.getProvince(i).addDangerLevel(450);
         }

         if (CFG.game.getProvince(i).getIsCapital()) {
            CFG.game.getProvince(i).addPotential(25);
            if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
               CFG.game.getProvince(i).addDangerLevel(125 + 25 * CFG.game.getProvince(i).getNeighboringSeaProvincesSize());
            }
         }
      }

      for(int j = 0; j < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); ++j) {
         for(int k = 1; k < CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivsSize(); ++k) {
            if (CFG.game
               .getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivID(k))) {
               CFG.game
                  .getProvince(i)
                  .addDangerLevel(
                     (int)(
                        (CFG.game.getProvince(i).getIsCapital() ? 28.75F : 14.87F)
                           * Math.min(
                              1.0F
                                 * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getArmy(k)
                                 / Math.max((float)CFG.game.getProvince(i).getArmy(0), 1.0F),
                              2.0F
                           )
                     )
                  );
            } else if (CFG.game
                  .getCivRelation_OfCivB(
                     CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivID(k)
                  )
               < 0.0F) {
               CFG.game
                  .getProvince(i)
                  .addDangerLevel(
                     (int)(
                        (CFG.game.getProvince(i).getIsCapital() ? 8.75F : 4.87F)
                           * Math.min(
                              1.0F
                                 * (float)CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getArmy(k)
                                 / Math.max((float)CFG.game.getProvince(i).getArmy(0), 1.0F),
                              2.0F
                           )
                           * (
                              -CFG.game
                                    .getCivRelation_OfCivB(
                                       CFG.game.getProvince(i).getCivID(),
                                       CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(j)).getCivID(k)
                                    )
                                 / 100.0F
                           )
                     )
                  );
            }
         }
      }

      try {
         if (CFG.game.getProvince(i).getArmy(0) > 0) {
            CFG.game
               .getProvince(i)
               .setDangerLevel_WithArmy(
                  (int)Math.ceil(
                     (double)(
                        (float)CFG.game.getProvince(i).getDangerLevel()
                           * (
                              1.0F
                                 - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.DANGER_PERC_OF_UNITS
                                    * (float)CFG.game.getProvince(i).getArmy(0)
                                    / (float)CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfUnits()
                           )
                     )
                  )
               );
         } else {
            CFG.game.getProvince(i).setDangerLevel_WithArmy(CFG.game.getProvince(i).getDangerLevel());
         }
      } catch (IllegalArgumentException var8) {
         CFG.game.getProvince(i).setDangerLevel_WithArmy(CFG.game.getProvince(i).getDangerLevel());
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      }

      if (CFG.game.getProvince(i).getLevelOfFort() > 0) {
         CFG.game.getProvince(i).setPotential((int)Math.ceil((double)((float)CFG.game.getProvince(i).getPotential() * 0.9566F)));
      }

      if (CFG.game.getProvince(i).getCivID() > 0) {
         try {
            Civilization_Region var24 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCivRegion(CFG.game.getProvince(i).getCivRegionID());
            var24.iAveragePotential += CFG.game.getProvince(i).getPotential();
         } catch (IndexOutOfBoundsException var6) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var6);
            }
         } catch (NullPointerException var7) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var7);
            }
         }
      }

      try {
         if (CFG.game.getProvince(i).getCivID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCivRegion(CFG.game.getProvince(i).getCivRegionID()).isKeyRegion) {
            CFG.game
               .getProvince(i)
               .setDangerLevel(
                  (int)(
                     (float)CFG.game.getProvince(i).getDangerLevel()
                        * CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.DANGER_EXTRA_KEY_REGION
                  )
               );
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (NullPointerException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }

      if (CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost() > 0) {
         CFG.game
            .getProvince(i)
            .addDangerLevel(
               (int)((float)CFG.game.getProvince(i).getDangerLevel() * 0.15F * (float)CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost())
            );
      }

      if (CFG.game.getProvince(i).getArmyWasRecruited() > 0) {
         CFG.game.getProvince(i).setArmyWasRecruited(CFG.game.getProvince(i).getArmyWasRecruited() - 1);
      }

      if (CFG.game.getProvince(i).getBordersWithEnemy()) {
         CFG.game
            .getCiv(CFG.game.getProvince(i).getCivID())
            .setBordersWithEnemy(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getBordersWithEnemy() + 1);
      }

      if (CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces() > 0) {
         CFG.game
            .getCiv(CFG.game.getProvince(i).getCivID())
            .setNumOfNeighboringNeutralProvinces(
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfNeighboringNeutralProvinces()
                  + CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces()
            );
      }

      CFG.game.getProvince(i).setWasConquered((byte)(CFG.game.getProvince(i).getWasConquered() - 1));
      CFG.game.getProvince(i).setNeighbooringProvinceOfCivWasLost((byte)(CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost() - 1));
   }

   protected final List<AI_NeighProvinces_Army> getAllNeighboringProvincesInRange_WithArmyToRegroup(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces_Army> out,
      List<Integer> was,
      int nRequiredArmy
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;
      int nArmyCollected = 0;

      while(iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() == nCivID
                     && (
                        !onlyTrueOwner
                           || CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()
                              == CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getTrueOwnerOfProvince()
                     )) {
                     if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getArmyCivID(nCivID)
                           - CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .haveMission_Army(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i))
                        > 0) {
                        int tArmy = CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getArmyCivID(nCivID)
                           - CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .haveMission_Army(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                        nArmyCollected += tArmy;
                        out.add(new AI_NeighProvinces_Army(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance, tArmy));
                     }

                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (nArmyCollected >= nRequiredArmy) {
            break;
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var16 = null;
      return out;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RecruitAtWAr(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())
                     || CFG.game
                           .getMilitaryAccess(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())
                        > 0) {
                     if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).isOccupied()
                        && nCivID == CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()
                        && CFG.game.getCiv(nCivID).isRecruitingArmyInProvinceID(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)) < 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                           iFirstFoundRange = nIteration_Distance;
                           iRange += 4;
                        }
                     }

                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 8 < nIteration_Distance) {
            break;
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var15 = null;
      return out;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Recruit(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;

      while(iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() == nCivID) {
                     if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).isOccupied()
                        && CFG.game.getCiv(nCivID).isRecruitingArmyInProvinceID(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)) < 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                     }

                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var14 = null;
      return out;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Clear(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;

      while(iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() == nCivID
                     && (
                        !onlyTrueOwner
                           || CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()
                              == CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getTrueOwnerOfProvince()
                     )) {
                     out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var14 = null;
      return out;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_OnlyOwn_Clear(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while(iRange-- > 0) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID() == nCivID) {
                     out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                     iFirstFoundRange = nIteration_Distance;
                  }

                  recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 4 < nIteration_Distance) {
            break;
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var15 = null;
      return out;
   }

   protected final int getLoadingTurnActionsOfCivID() {
      return this.iLoadingTurnActionsOfCivID;
   }

   protected final void setLoadingTurnActionsOfCivID(int iLoadingTurnActionsOfCivID) {
      this.iLoadingTurnActionsOfCivID = iLoadingTurnActionsOfCivID;
   }

   protected final void updateExpand() {
      if (!Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
         this.expandNeutral = new AI.Expand() {
            @Override
            public boolean expandToNeutralProvinces(int nCivID) {
               return AI.this.expandToNeutralProvinces_Out(nCivID, true);
            }
         };
      } else {
         this.expandNeutral = new AI.Expand() {
            @Override
            public boolean expandToNeutralProvinces(int nCivID) {
               return false;
            }
         };
      }
   }

   protected final void expandToNeutralProvinces_Run(int nCivID) {
      for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.EXPAND_NETURAL_PROVINCE
            && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
            CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).onRemove();
            CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
         }
      }
   }

   protected final boolean expandToNeutralProvinces_Out(int nCivID, boolean maybeGoToTheSea) {
      try {
         if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) {
            Gdx.app.log("AoC", "expandToNeutralProvinces_Out -> " + CFG.game.getCiv(nCivID).getCivName());
            this.expandToNeutralProvinces_Run(nCivID);
            Gdx.app.log("AoC", "expandToNeutralProvinces_Out -> movepoints" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
            if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE) {
               return false;
            }

            if (CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() > 0
               && (
                  this.iNeutralProvincesWithSeaAccessSize <= 0
                     || !maybeGoToTheSea
                     || CFG.oR.nextInt(100) >= 5
                     || CFG.game.getCiv(nCivID).getMoney() <= (long)BuildingsManager.getPort_BuildCost(1, CFG.game.getCiv(nCivID).getProvinceID(0))
               )) {
               int recruitableArmyMax = (int)(CFG.game.getCiv(nCivID).getMoney() / 5L);
               List<AI.NeutralProvinces> possibleProvinces = new ArrayList<>();

               for(int i = CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; --i) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i)).getArmy(0) + 2
                     < recruitableArmyMax + CFG.game.getCiv(nCivID).getNumOfUnits()) {
                     possibleProvinces.add(new AI.NeutralProvinces(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i), nCivID));
                  }
               }

               Gdx.app.log("AoC", "EXPAND -> 000 possibleProvinces.size: " + possibleProvinces.size());
               if (possibleProvinces.size() > 0) {
                  List<Integer> sorted = new ArrayList<>();
                  List<Integer> tempIDs = new ArrayList<>();
                  int i = 0;

                  for(int iSize = possibleProvinces.size(); i < iSize; ++i) {
                     tempIDs.add(i);
                  }

                  while(tempIDs.size() > 0) {
                     i = 0;

                     for(int ix = tempIDs.size() - 1; ix > 0; --ix) {
                        if (possibleProvinces.get(tempIDs.get(i)).iScore < possibleProvinces.get(tempIDs.get(ix)).iScore) {
                           i = ix;
                        }
                     }

                     sorted.add(tempIDs.get(i));
                     tempIDs.remove(i);
                  }

                  i = 0;

                  for(int iSize = sorted.size(); i < iSize; ++i) {
                     List<Integer> possibleFrom = new ArrayList<>();

                     for(int k = 0; k < CFG.game.getProvince(possibleProvinces.get(sorted.get(i)).iProvinceID).getNeighboringProvincesSize(); ++k) {
                        if (CFG.game.getProvince(CFG.game.getProvince(possibleProvinces.get(sorted.get(i)).iProvinceID).getNeighboringProvinces(k)).getCivID()
                           == nCivID) {
                           possibleFrom.add(CFG.game.getProvince(possibleProvinces.get(sorted.get(i)).iProvinceID).getNeighboringProvinces(k));
                        }
                     }

                     List<Integer> canMoveImmediately = new ArrayList<>();

                     for(int k = possibleFrom.size() - 1; k >= 0; --k) {
                        if (CFG.game.getProvince(possibleFrom.get(k)).getArmyCivID(nCivID)
                              - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(k))
                           > CFG.game.getProvince(possibleProvinces.get(sorted.get(i)).iProvinceID).getArmy(0)) {
                           canMoveImmediately.add(possibleFrom.get(k));
                        }
                     }

                     Gdx.app.log("AoC", "EXPAND -> 000 canMoveImmediately.size: " + canMoveImmediately.size());
                     if (canMoveImmediately.size() <= 0) {
                        Gdx.app.log("AoC", "EXPAND -> 000 ADDMISION: TOPROVINCEID: " + possibleProvinces.get(sorted.get(i)).iProvinceID);
                        if (CFG.game
                           .getCiv(nCivID)
                           .civGameData
                           .civPlans
                           .addNewArmyMission(
                              possibleProvinces.get(sorted.get(i)).iProvinceID,
                              new CivArmyMission_ExpandNeutralProvince(nCivID, possibleProvinces.get(sorted.get(i)).iProvinceID)
                           )) {
                        }
                     } else {
                        int randID = CFG.oR.nextInt(canMoveImmediately.size());
                        int numOfNeutral = 0;

                        for(int k = 0; k < CFG.game.getProvince(canMoveImmediately.get(randID)).getNeighboringProvincesSize(); ++k) {
                           if (CFG.game.getProvince(CFG.game.getProvince(canMoveImmediately.get(randID)).getNeighboringProvinces(k)).getCivID() == 0
                              && !CFG.game
                                 .getCiv(nCivID)
                                 .isMovingUnitsToProvinceID(CFG.game.getProvince(canMoveImmediately.get(randID)).getNeighboringProvinces(k))) {
                              ++numOfNeutral;
                           }
                        }

                        int tArmyToMove = CFG.game.getProvince(canMoveImmediately.get(randID)).getArmyCivID(nCivID);
                        if (numOfNeutral > 1) {
                           tArmyToMove = CFG.game.getProvince(possibleProvinces.get(sorted.get(i)).iProvinceID).getArmy(0) + 5 + CFG.oR.nextInt(5);
                        }

                        Gdx.app.log("AoC", "EXPAND -> 000 movearmy: TOPROVINCEID: " + possibleProvinces.get(sorted.get(i)).iProvinceID);
                        if (!CFG.gameAction
                           .moveArmy(canMoveImmediately.get(randID), possibleProvinces.get(sorted.get(i)).iProvinceID, tArmyToMove, nCivID, true, false)) {
                           break;
                        }
                     }
                  }
               } else {
                  for(int i = CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; --i) {
                     possibleProvinces.add(new AI.NeutralProvinces(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i), nCivID));
                  }

                  int tBest = 0;

                  for(int i = possibleProvinces.size() - 1; i > 0; --i) {
                     if (possibleProvinces.get(tBest).iScore < possibleProvinces.get(i).iScore) {
                        tBest = i;
                     }
                  }

                  CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .addNewArmyMission(
                        possibleProvinces.get(tBest).iProvinceID, new CivArmyMission_ExpandNeutralProvince(nCivID, possibleProvinces.get(tBest).iProvinceID)
                     );
               }
            } else if (maybeGoToTheSea) {
               Gdx.app.log("AoC", "iNeutralProvincesWithSeaAccessSize: " + this.iNeutralProvincesWithSeaAccessSize);
               maybeGoToTheSea = false;
               if (this.iNeutralProvincesWithSeaAccessSize > 0) {
                  List<AI.NeutralProvinces> possibleTo = new ArrayList<>();
                  List<Integer> possibleTo_MoveFrom = new ArrayList<>();
                  Gdx.app.log("AoC", "EXPAND EXTRA -> begin");

                  for(int i = 0; i < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i) {
                     if (CFG.game.getCiv(nCivID).getCivRegion(i).getSeaAccess()) {
                        for(int j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(i).getProvincesSize(); ++j) {
                           if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getLevelOfPort() > 0) {
                              for(int k = 0;
                                 k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvincesSize();
                                 ++k
                              ) {
                                 for(int o = 0;
                                    o
                                       < CFG.game
                                          .getProvince(
                                             CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvinces(k)
                                          )
                                          .getNeighboringProvincesSize();
                                    ++o
                                 ) {
                                    if (!CFG.game
                                          .getProvince(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvinces(k)
                                                )
                                                .getNeighboringProvinces(o)
                                          )
                                          .getSeaProvince()
                                       && CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                         .getNeighboringSeaProvinces(k)
                                                   )
                                                   .getNeighboringProvinces(o)
                                             )
                                             .getWasteland()
                                          < 0
                                       && CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                         .getNeighboringSeaProvinces(k)
                                                   )
                                                   .getNeighboringProvinces(o)
                                             )
                                             .getCivID()
                                          == 0) {
                                       possibleTo.add(
                                          new AI.NeutralProvinces(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvinces(k)
                                                )
                                                .getNeighboringProvinces(o),
                                             nCivID
                                          )
                                       );
                                       possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j));
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  for(int i = 0; i < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i) {
                     if (CFG.game.getCiv(nCivID).getCivRegion(i).getSeaAccess()) {
                        for(int j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(i).getProvincesSize(); ++j) {
                           if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getLevelOfPort() > 0) {
                              for(int k = 0;
                                 k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvincesSize();
                                 ++k
                              ) {
                                 for(int o = 0;
                                    o
                                       < CFG.game
                                          .getProvince(
                                             CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvinces(k)
                                          )
                                          .getNeighboringProvincesSize();
                                    ++o
                                 ) {
                                    if (CFG.game
                                       .getProvince(
                                          CFG.game
                                             .getProvince(
                                                CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j)).getNeighboringSeaProvinces(k)
                                             )
                                             .getNeighboringProvinces(o)
                                       )
                                       .getSeaProvince()) {
                                       for(int z = 0;
                                          z
                                             < CFG.game
                                                .getProvince(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game
                                                            .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                            .getNeighboringSeaProvinces(k)
                                                      )
                                                      .getNeighboringProvinces(o)
                                                )
                                                .getNeighboringProvincesSize();
                                          ++z
                                       ) {
                                          if (!CFG.game
                                                .getProvince(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game
                                                            .getProvince(
                                                               CFG.game
                                                                  .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                                  .getNeighboringSeaProvinces(k)
                                                            )
                                                            .getNeighboringProvinces(o)
                                                      )
                                                      .getNeighboringProvinces(z)
                                                )
                                                .getSeaProvince()
                                             && CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game
                                                               .getProvince(
                                                                  CFG.game
                                                                     .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                                     .getNeighboringSeaProvinces(k)
                                                               )
                                                               .getNeighboringProvinces(o)
                                                         )
                                                         .getNeighboringProvinces(z)
                                                   )
                                                   .getWasteland()
                                                < 0
                                             && CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game
                                                               .getProvince(
                                                                  CFG.game
                                                                     .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                                     .getNeighboringSeaProvinces(k)
                                                               )
                                                               .getNeighboringProvinces(o)
                                                         )
                                                         .getNeighboringProvinces(z)
                                                   )
                                                   .getCivID()
                                                == 0) {
                                             possibleTo.add(
                                                new AI.NeutralProvinces(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game
                                                            .getProvince(
                                                               CFG.game
                                                                  .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j))
                                                                  .getNeighboringSeaProvinces(k)
                                                            )
                                                            .getNeighboringProvinces(o)
                                                      )
                                                      .getNeighboringProvinces(z),
                                                   nCivID
                                                )
                                             );
                                             possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(i).getProvince(j));
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  Gdx.app.log("AoC", "EXPAND EXTRA -> 11 -> possibleTo.size: " + possibleTo.size());
                  if (possibleTo.size() > 0) {
                     int tBest = 0;

                     for(int i = possibleTo.size() - 1; i > 0; --i) {
                        if (possibleTo.get(tBest).iScore < possibleTo.get(i).iScore) {
                           tBest = i;
                        }
                     }

                     int neutralArmy = CFG.game.getProvince(possibleTo.get(tBest).iProvinceID).getArmy(0)
                        + 6
                        - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(possibleTo.get(tBest).iProvinceID)
                        - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(possibleTo.get(tBest).iProvinceID);
                     if (neutralArmy >= 0) {
                        if (CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID) > neutralArmy) {
                           RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID);
                           if (tryRegroupArmy.getRouteSize() > 0
                              && CFG.gameAction.moveArmy(possibleTo_MoveFrom.get(tBest), tryRegroupArmy.getRoute(0), neutralArmy, nCivID, true, false)) {
                              if (tryRegroupArmy.getRouteSize() > 1) {
                                 CFG.game
                                    .getCiv(nCivID)
                                    .civGameData
                                    .civPlans
                                    .lArmiesMissions
                                    .add(
                                       new CivArmyMission_ExpandNeutral_Check(
                                          nCivID, tryRegroupArmy.getRoute(0), possibleTo.get(tBest).iProvinceID, neutralArmy
                                       )
                                    );
                              }

                              return false;
                           }
                        } else {
                           int tArmyToRecruit = neutralArmy - CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                           CFG.game.getCiv(nCivID).recruitArmy_AI(possibleTo_MoveFrom.get(tBest), tArmyToRecruit);
                           int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(possibleTo_MoveFrom.get(tBest))
                              + CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                           if (tempArmy > 0) {
                              CFG.game
                                 .getCiv(nCivID)
                                 .civGameData
                                 .civPlans
                                 .lArmiesMissions
                                 .add(
                                    new CivArmyMission_ExpandNeutral_Check(nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID, tempArmy)
                                 );
                           }
                        }
                     }
                  } else {
                     possibleTo_MoveFrom.clear();
                     possibleTo.clear();

                     for(int z = 0; z < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++z) {
                        if (CFG.game.getCiv(nCivID).getCivRegion(z).getSeaAccess()) {
                           for(int j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(z).getProvincesSize(); ++j) {
                              if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getLevelOfPort() >= 0) {
                                 List<Integer> recentlyAdded = new ArrayList<>();
                                 List<Integer> was = new ArrayList<>();

                                 for(int k = 0;
                                    k < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvincesSize();
                                    ++k
                                 ) {
                                    recentlyAdded.add(
                                       CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvinces(k)
                                    );
                                    was.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvinces(k));
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j)).getNeighboringSeaProvinces(k))
                                       .was = true;
                                 }

                                 List<Integer> currProvinces = new ArrayList<>();
                                 int nIteration_Distance = 0;
                                 boolean foundProvince = false;

                                 while(
                                    nIteration_Distance < CFG.game.getCiv(nCivID).civGameData.iExpandNeutralProvinces_RangeCheck && recentlyAdded.size() > 0
                                 ) {
                                    currProvinces.clear();
                                    ++nIteration_Distance;

                                    for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
                                       boolean wasntAdded = true;

                                       for(int p = currProvinces.size() - 1; p >= 0; --p) {
                                          if (currProvinces.get(p) == recentlyAdded.get(a)) {
                                             wasntAdded = false;
                                             break;
                                          }
                                       }

                                       if (wasntAdded) {
                                          currProvinces.add(recentlyAdded.get(a));
                                       }
                                    }

                                    recentlyAdded.clear();

                                    for(int a = currProvinces.size() - 1; a >= 0; --a) {
                                       for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
                                          if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                                             was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                                             CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                                             if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getSeaProvince()) {
                                                recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                                             } else if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID()
                                                   == 0
                                                && CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getWasteland()
                                                   < 0) {
                                                possibleTo.add(
                                                   new AI.NeutralProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nCivID)
                                                );
                                                possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(z).getProvince(j));
                                                foundProvince = true;
                                                recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                                             }
                                          }
                                       }
                                    }

                                    if (foundProvince) {
                                       break;
                                    }
                                 }

                                 for(int p = was.size() - 1; p >= 0; --p) {
                                    CFG.game.getProvince(was.get(p)).was = false;
                                 }

                                 recentlyAdded.clear();
                                 recentlyAdded = null;
                                 was.clear();
                                 was = null;
                              }
                           }
                        }
                     }

                     Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> possibleTo.size: " + possibleTo.size());
                     if (possibleTo.size() == 0) {
                        CFG.game.getCiv(nCivID).civGameData.iExpandNeutralProvinces_RangeCheck = Math.max(
                           CFG.game.getCiv(nCivID).civGameData.iExpandNeutralProvinces_RangeCheck + 1, CFG.game.getProvincesSize() / 15
                        );
                        this.expandToNeutralProvinces_Out(nCivID, false);
                     } else {
                        int tBest = 0;

                        for(int i = possibleTo.size() - 1; i > 0; --i) {
                           if (possibleTo.get(tBest).iScore < possibleTo.get(i).iScore) {
                              tBest = i;
                           }
                        }

                        Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 0000 -> ProvinceID_TO: " + possibleTo.get(tBest).iProvinceID);
                        Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 0000 -> ProvinceID_FROM: " + possibleTo_MoveFrom.get(tBest));
                        int neutralArmy = CFG.game.getProvince(possibleTo.get(tBest).iProvinceID).getArmy(0)
                           + 10
                           - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(possibleTo.get(tBest).iProvinceID)
                           - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(possibleTo.get(tBest).iProvinceID);
                        if (neutralArmy >= 0) {
                           Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 1111");
                           if (CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID) > neutralArmy) {
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 2222");
                              RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(
                                 nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID
                              );
                              if (tryRegroupArmy.getRouteSize() > 0
                                 && CFG.gameAction.moveArmy(possibleTo_MoveFrom.get(tBest), tryRegroupArmy.getRoute(0), neutralArmy, nCivID, true, false)) {
                                 if (tryRegroupArmy.getRouteSize() > 1) {
                                    CFG.game
                                       .getCiv(nCivID)
                                       .civGameData
                                       .civPlans
                                       .lArmiesMissions
                                       .add(
                                          new CivArmyMission_Expand_BuildPort(
                                             nCivID, tryRegroupArmy.getRoute(0), possibleTo.get(tBest).iProvinceID, neutralArmy
                                          )
                                       );
                                 }

                                 return false;
                              }
                           } else {
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 3333");
                              int tArmyToRecruit = neutralArmy - CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION tArmyToRecruit: " + tArmyToRecruit);
                              CFG.game.getCiv(nCivID).recruitArmy_AI(possibleTo_MoveFrom.get(tBest), tArmyToRecruit);
                              int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(possibleTo_MoveFrom.get(tBest))
                                 + CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION tempArmy: " + tempArmy);
                              if (tempArmy > 0) {
                                 Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION ADDMISION");
                                 CFG.game
                                    .getCiv(nCivID)
                                    .civGameData
                                    .civPlans
                                    .lArmiesMissions
                                    .add(
                                       new CivArmyMission_Expand_BuildPort(nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID, tempArmy)
                                    );
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var15) {
         CFG.exceptionStack(var15);
      } catch (NullPointerException var16) {
         CFG.exceptionStack(var16);
      }

      return false;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Regroup_ForNavalInvasion(
      int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())) {
                     if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getArmyCivID(nCivID) > 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                           iFirstFoundRange = nIteration_Distance;
                        }
                     }

                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 2 < nIteration_Distance) {
            break;
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var13 = null;
      return out;
   }

   protected final boolean prepareForWar_BordersWithEnemy(int nCivID, int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getBordersWithEnemy()) {
         for(int z = 0; z < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++z) {
            if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(z)).getCivID())) {
               return true;
            }
         }
      }

      return this.prepareForWar_BordersWithEnemy_Just(nCivID, nProvinceID);
   }

   protected final boolean prepareForWar_BordersWithEnemy_Just(int nCivID, int nProvinceID) {
      for(int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
         for(int k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getCivID()
               == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
               return true;
            }
         }
      }

      return false;
   }

   interface Expand {
      boolean expandToNeutralProvinces(int var1);
   }

   class NeutralProvinces {
      protected int iProvinceID;
      protected float iScore;

      protected NeutralProvinces(int nProvinceID, int nCivID) {
         this.iProvinceID = nProvinceID;
         this.buildScore(nCivID);
      }

      protected final void buildScore(int nCivID) {
         int neighboring_NeutralProvinces = 0;
         int neighboring_CivProvinces = 0;
         int neighboring_OtherCivProvinces = 0;

         for(int i = 0; i < CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getWasteland() < 0) {
               if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                  if (CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                     this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_CAPITAL;
                  } else {
                     this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_OWN_PROVINCE;
                  }

                  ++neighboring_CivProvinces;
               } else if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID() == 0) {
                  ++neighboring_NeutralProvinces;
                  this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_MORE_NEUTRAL;
               } else {
                  ++neighboring_OtherCivProvinces;
                  this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_OTHER_CIV;
               }
            }
         }

         this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_GROWTH_RATE
            * CFG.game.getProvince(this.iProvinceID).getGrowthRate_Population();
         if (CFG.game.getProvince(this.iProvinceID).getNeighboringSeaProvincesSize() > 0) {
            this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS
               + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS_EXTRA
                  * (float)CFG.game.getProvince(this.iProvinceID).getNeighboringSeaProvincesSize();
         }

         this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES
            * (float)(neighboring_CivProvinces + neighboring_NeutralProvinces + neighboring_OtherCivProvinces);
         this.iScore += (float)(
            (int)(
               CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES_POTENITAL
                  * (float)(neighboring_NeutralProvinces + neighboring_CivProvinces)
                  / (float)(neighboring_CivProvinces + neighboring_NeutralProvinces + neighboring_OtherCivProvinces)
            )
         );
         if (neighboring_NeutralProvinces == 0 && CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize() > 0) {
            this.iScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_LAST_PROVINCE;
         } else if (neighboring_CivProvinces <= 1) {
            this.iScore *= 0.725F;
         }
      }
   }
}
