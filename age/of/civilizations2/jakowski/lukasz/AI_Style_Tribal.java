package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Style_Tribal extends AI_Style {
   private int MIGRATE_MAX_NUM_OF_PROVINCES = 10;

   protected AI_Style_Tribal() {
      this.TAG = "UNCIVILIZED";
      this.MIGRATE_MAX_NUM_OF_PROVINCES = 3 + CFG.oR.nextInt(3);
      this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.02F;
      this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 9;
      this.PERSONALITY_MIN_HAPPINESS_DEFAULT = 80;
      this.PERSONALITY_MIN_HAPPINESS_RANDOM = 18;
      this.PERSONALITY_FORGIVNESS_DEFAULT = 0.5F;
      this.PERSONALITY_FORGIVNESS_RANDOM = 20;
      this.MIN_TURNS_TO_ABANDON_USELESS_PROVINCE = 10;
   }

   @Override
   protected void turnOrders(int nCivID) {
      if (!CFG.game.getCiv(nCivID).isAtWar() && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
         if (CFG.oR.nextInt(100) < CFG.game.getCiv(nCivID).getCivPersonality().UNCIVILIZED_MIGRATE) {
            if (CFG.game.getCiv(nCivID).getNumOfProvinces() < this.MIGRATE_MAX_NUM_OF_PROVINCES) {
               if (!this.migration(nCivID)) {
               }
            } else {
               this.migration_NotConnected(nCivID);
               this.migration_NotConnected_OLD(nCivID);
            }
         } else {
            this.migration_NotConnected(nCivID);
            this.migration_NotConnected_OLD(nCivID);
         }
      }

      this.civilize(nCivID);
      this.checkBalanceOfProvinces_Tribal(nCivID);
      super.turnOrders(nCivID);
   }

   @Override
   protected void armyOverBudget_Disband(int nCivID) {
      for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.EXPAND_NETURAL_PROVINCE) {
            return;
         }
      }

      super.armyOverBudget_Disband(nCivID);
   }

   @Override
   protected void buildStartingBuildings(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
            && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(1) * 0.88F) {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfFarm(1);
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   protected final boolean migration(int nCivID) {
      if (this.canCivlize(nCivID)) {
         return false;
      } else {
         List<Integer> nMigrateFrom = new ArrayList<>();

         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID)) {
               nMigrateFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i));
            }
         }

         if (nMigrateFrom.size() <= 0) {
            return false;
         } else {
            List<Integer> nNotConnected = new ArrayList<>();

            for(int i = 0; i < nMigrateFrom.size(); ++i) {
               if (nMigrateFrom.get(i) != CFG.game.getCiv(nCivID).getCapitalProvinceID()
                  && !Game.provinceBordersWithProvince_LandByLand(nMigrateFrom.get(i), CFG.game.getCiv(nCivID).getCapitalProvinceID())) {
                  nNotConnected.add(nMigrateFrom.get(i));
               }
            }

            if (nNotConnected.size() > 0) {
               for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                  if (CFG.game.getCiv(nCivID).getProvinceID(i) != CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                     for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                        if (Game.provinceBordersWithProvince_LandByLand(
                           CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j),
                           CFG.game.getCiv(nCivID).getCapitalProvinceID()
                        )) {
                           CFG.gameAction
                              .migrateToProvince(
                                 CFG.game.getCiv(nCivID).getProvinceID(i),
                                 CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j),
                                 nCivID,
                                 false
                              );
                        }
                     }
                  }
               }
            } else if (Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID)) {
               List<Integer> nMigrateTo = new ArrayList<>();

               for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvincesSize(); ++i) {
                  if (Game.uncivilizedCanMigrate(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i), nCivID)) {
                     nMigrateTo.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i));
                  }
               }

               if (nMigrateTo.size() > 0) {
                  int nBestID = 0;

                  for(int i = 1; i < nMigrateTo.size(); ++i) {
                     if (this.migrationTo_Score(nMigrateTo.get(nBestID), nCivID) < this.migrationTo_Score(nMigrateTo.get(i), nCivID)) {
                        nBestID = i;
                     }
                  }

                  CFG.gameAction.migrateToProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nMigrateTo.get(nBestID), nCivID, false);
                  if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 1) {
                     try {
                        for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(1)).getNeighboringProvincesSize(); ++i) {
                           if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(1)).getNeighboringProvinces(i)
                              == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                              CFG.gameAction
                                 .migrateToProvince(CFG.game.getCiv(nCivID).getProvinceID(1), CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID, false);
                              break;
                           }
                        }
                     } catch (IndexOutOfBoundsException var7) {
                     }
                  }
               }
            }

            return true;
         }
      }
   }

   private final void migration_NotConnected_OLD(int nCivID) {
      List<Integer> nMigrateFrom = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         if (Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID)) {
            nMigrateFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i));
         }
      }

      List<Integer> nNotConnected = new ArrayList<>();

      for(int i = 0; i < nMigrateFrom.size(); ++i) {
         if (nMigrateFrom.get(i) != CFG.game.getCiv(nCivID).getCapitalProvinceID()
            && !Game.provinceBordersWithProvince_LandByLand(nMigrateFrom.get(i), CFG.game.getCiv(nCivID).getCapitalProvinceID())) {
            nNotConnected.add(nMigrateFrom.get(i));
         }
      }

      if (nNotConnected.size() > 0) {
         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (CFG.game.getCiv(nCivID).getProvinceID(i) != CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
               for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                  if (Game.provinceBordersWithProvince_LandByLand(
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j), CFG.game.getCiv(nCivID).getCapitalProvinceID()
                  )) {
                     CFG.gameAction
                        .migrateToProvince(
                           CFG.game.getCiv(nCivID).getProvinceID(i),
                           CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j),
                           nCivID,
                           false
                        );
                  }
               }
            }
         }
      }
   }

   private final void migration_NotConnected(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
            List<Integer> nMigrateFrom = new ArrayList<>();

            for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
               if (Game.uncivilizedCanMigrate_FromProvince(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID)
                  && !CFG.game.getCiv(nCivID).migratesFromProvinceID(CFG.game.getCiv(nCivID).getProvinceID(i))) {
                  nMigrateFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i));
               }
            }

            if (nMigrateFrom.size() > 0) {
               List<Integer> nNotConnected = new ArrayList<>();

               for(int i = 0; i < nMigrateFrom.size(); ++i) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivRegionID()
                     != CFG.game.getProvince(nMigrateFrom.get(i)).getCivRegionID()) {
                     nNotConnected.add(nMigrateFrom.get(i));
                  }
               }

               if (nNotConnected.size() > 0) {
                  List<Integer> nMigrateTo = new ArrayList<>();

                  for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvincesSize(); ++i) {
                     if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i)).getCivID() == 0
                        )
                      {
                        nMigrateTo.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i));
                     }
                  }

                  if (nMigrateTo.size() == 0) {
                     for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvincesSize(); ++i) {
                        for(int j = 0;
                           j
                              < CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i))
                                 .getNeighboringProvincesSize();
                           ++j
                        ) {
                           if (CFG.game
                                 .getProvince(
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i))
                                       .getNeighboringProvinces(j)
                                 )
                                 .getCivID()
                              == 0) {
                              nMigrateTo.add(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringProvinces(i))
                                    .getNeighboringProvinces(j)
                              );
                           }
                        }
                     }
                  }

                  if (nMigrateTo.size() > 0) {
                     List<RegroupArmy_Data_Migrate> tMigrate = new ArrayList<>();
                     int nMigrateNotConnectedID = nNotConnected.size() - 1;

                     for(int j = nMigrateTo.size() - 1; j >= 0; --j) {
                        RegroupArmy_Data_Migrate tData = new RegroupArmy_Data_Migrate(nCivID, nNotConnected.get(nMigrateNotConnectedID), nMigrateTo.get(j));
                        if (tData.getRouteSize() > 0) {
                           tMigrate.add(tData);
                        }
                     }

                     if (tMigrate.size() > 0) {
                        int tBestID = 0;

                        for(int i = tMigrate.size() - 1; i > 0; --i) {
                           if (tMigrate.get(tBestID).getRouteSize() > tMigrate.get(i).getRouteSize()) {
                              tBestID = i;
                           } else if (tMigrate.get(tBestID).getRouteSize() == tMigrate.get(i).getRouteSize() && CFG.oR.nextInt(100) < 50) {
                              tBestID = i;
                           }
                        }

                        CFG.gameAction.migrateToProvince(nNotConnected.get(nMigrateNotConnectedID), tMigrate.get(tBestID).getRoute(0), nCivID, false);
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var9) {
         CFG.exceptionStack(var9);
      }
   }

   private final int migrationTo_Score(int nProvinceID, int nCivID) {
      int out = 0;
      out += (int)(CFG.game.getProvince(nProvinceID).getGrowthRate_Population_WithFarm() * 100.0F);
      if (CFG.game.getProvince(nProvinceID).getCore().getHaveACore(nCivID)) {
         out += 250;
      }

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         out += (int)(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getGrowthRate_Population_WithFarm() * 10.0F);
         if (CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
            out += 50;
         }

         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() > 0
            && CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) {
            out -= 200;
         }
      }

      return out;
   }

   @Override
   protected void buildBuildings(int nCivID) {
   }
}
