package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class CivArmyMission_ColonizeProvince extends CivArmyMission {
   private int iCivID;
   private int iColonizeProvinceID;

   protected CivArmyMission_ColonizeProvince(int nCivID, int colonizeProvinceID) {
      this.toProvinceID = colonizeProvinceID;
      this.iColonizeProvinceID = colonizeProvinceID;
      this.MISSION_ID = -1;
      this.iCivID = nCivID;
      this.MISSION_TYPE = CivArmyMission_Type.COLONIZE_PROVINCE;
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = (int)Math.max((float)CFG.game.getProvincesSize() * 0.01F, 30.0F);
      this.iArmy = 0;
      this.generateColonizeData();
   }

   protected final boolean generateColonizeData() {
      this.iProvinceID = -1;
      if (CFG.game.getCiv(this.iCivID).iBudget < 0) {
         this.iObsolate = 0;
         CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
         return false;
      } else {
         List<Boolean> haveAccessToBasins = new ArrayList<>();

         for(int i = 0; i < CFG.map.iNumOfBasins; ++i) {
            haveAccessToBasins.add(false);
         }

         List<Integer> lPossibleProvincesFrom = new ArrayList<>();
         List<Integer> lPossibleProvincesTo = new ArrayList<>();

         for(int i = 0; i < CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringSeaProvinces(i)).getBasinID(), true);
            lPossibleProvincesTo.add(CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringSeaProvinces(i));
         }

         for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            if (!CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).isOccupied()
               && CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getLevelOfPort() > 0) {
               for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                  if (haveAccessToBasins.get(
                     CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getBasinID()
                  )) {
                     lPossibleProvincesFrom.add(CFG.game.getCiv(this.iCivID).getProvinceID(i));
                     break;
                  }
               }
            }
         }

         if (lPossibleProvincesFrom.size() > 0) {
            int tBestID = -1;
            int tBestRouteSize = -1;
            int tBestID_To = -1;

            for(int i = lPossibleProvincesFrom.size() - 1; i >= 0; --i) {
               for(int j = lPossibleProvincesTo.size() - 1; j >= 0; --j) {
                  RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(this.iCivID, lPossibleProvincesFrom.get(i), lPossibleProvincesTo.get(j));
                  if (tryRegroupArmy.getRouteSize() > 0) {
                     if (tBestID < 0) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                     } else if (tBestRouteSize > tryRegroupArmy.getRouteSize()) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                     } else if (tBestRouteSize == tryRegroupArmy.getRouteSize() && CFG.oR.nextInt(100) < 50) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                     }
                  }
               }
            }

            if (tBestID >= 0) {
               if (CFG.game.getProvince(lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID) > 0) {
                  this.iArmy = Math.min(CFG.game.getProvince(lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID), 1 + CFG.oR.nextInt(9));
               } else {
                  this.iArmy = Math.max(2, 1 + CFG.oR.nextInt(9));
               }

               RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(this.iCivID, lPossibleProvincesFrom.get(tBestID), lPossibleProvincesTo.get(tBestID_To));
               if (tryRegroupArmy.getRouteSize() > 0) {
                  this.iProvinceID = lPossibleProvincesFrom.get(tBestID);
                  this.toProvinceID = lPossibleProvincesTo.get(tBestID_To);
                  this.iObsolate = Math.max(this.iObsolate, (int)((float)tryRegroupArmy.getRouteSize() * 1.25F));
                  this.lockTreasury();
                  return true;
               }
            }
         }

         lPossibleProvincesFrom.clear();

         for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
            if (!CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).isOccupied()) {
               for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
                  if (haveAccessToBasins.get(
                     CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getNeighboringSeaProvinces(j)).getBasinID()
                  )) {
                     lPossibleProvincesFrom.add(CFG.game.getCiv(this.iCivID).getProvinceID(i));
                     break;
                  }
               }
            }
         }

         if (lPossibleProvincesFrom.size() > 0) {
            int tBestID = -1;
            int tBestRouteSize = -1;
            int tBestID_To = -1;

            for(int i = lPossibleProvincesFrom.size() - 1; i >= 0; --i) {
               for(int j = lPossibleProvincesTo.size() - 1; j >= 0; --j) {
                  RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(
                     this.iCivID, lPossibleProvincesFrom.get(i), lPossibleProvincesTo.get(j)
                  );
                  if (tryRegroupArmy.getRouteSize() > 0) {
                     if (tBestID < 0) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                     } else if (tBestRouteSize > tryRegroupArmy.getRouteSize()) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                     } else if (tBestRouteSize == tryRegroupArmy.getRouteSize() && CFG.oR.nextInt(100) < 50) {
                        tBestID = i;
                        tBestID_To = j;
                        tBestRouteSize = tryRegroupArmy.getRouteSize();
                     }
                  }
               }
            }

            if (tBestID >= 0) {
               if (CFG.game.getProvince(lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID) > 0) {
                  this.iArmy = Math.min(CFG.game.getProvince(lPossibleProvincesFrom.get(tBestID)).getArmyCivID(this.iCivID), 1 + CFG.oR.nextInt(9));
               } else {
                  this.iArmy = Math.max(2, 1 + CFG.oR.nextInt(9));
               }

               RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(
                  this.iCivID, lPossibleProvincesFrom.get(tBestID), lPossibleProvincesTo.get(tBestID_To)
               );
               if (tryRegroupArmy.getRouteSize() > 0) {
                  this.iProvinceID = lPossibleProvincesFrom.get(tBestID);
                  this.toProvinceID = lPossibleProvincesTo.get(tBestID_To);
                  this.iObsolate = Math.max(this.iObsolate, (int)((float)tryRegroupArmy.getRouteSize() * 1.25F));
                  this.lockTreasury();
                  return true;
               }
            }
         }

         if (this.iProvinceID < 0) {
            this.iObsolate = 0;
            CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
            return false;
         } else {
            return true;
         }
      }
   }

   @Override
   protected boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
   }

   @Override
   protected boolean action(int nCivID) {
      if (CFG.game.getProvince(this.iColonizeProvinceID).getCivID() != 0) {
         CFG.game
            .setCivRelation_OfCivB(
               this.iCivID,
               CFG.game.getProvince(this.iColonizeProvinceID).getCivID(),
               CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.game.getProvince(this.iColonizeProvinceID).getCivID())
                  - 0.25F
                  - (float)CFG.oR.nextInt(425) / 100.0F
            );
         List<Integer> tProv = new ArrayList<>();

         for(int i = 0; i < CFG.game.getProvince(this.toProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i) != this.iColonizeProvinceID
               && !CFG.game.getProvince(CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i)).getSeaProvince()
               && CFG.game.getProvince(CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i)).getCivID() == 0) {
               tProv.add(CFG.game.getProvince(this.toProvinceID).getNeighboringProvinces(i));
            }
         }

         if (tProv.size() == 0) {
            this.iObsolate = 0;
            CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
            return true;
         }

         int tBest = 0;

         for(int i = tProv.size() - 1; i > 0; --i) {
            if (CFG.game.getProvince(tProv.get(tBest)).getGrowthRate_Population() < CFG.game.getProvince(tProv.get(i)).getGrowthRate_Population()) {
               tBest = i;
            }
         }

         this.iColonizeProvinceID = tProv.get(tBest);
      }

      if (this.iProvinceID != this.toProvinceID) {
         if (this.action_RecruitArmy()) {
            RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(nCivID, this.iProvinceID, this.toProvinceID);
            if (tryRegroupArmy.getRouteSize() > 0) {
               int moveToProvinceID = tryRegroupArmy.getRoute(0);
               if (!CFG.game.getProvince(this.iProvinceID).getSeaProvince()
                  && CFG.game.getProvince(tryRegroupArmy.getRoute(0)).getSeaProvince()
                  && !this.action_BuildPort(this.iProvinceID, tryRegroupArmy.getRoute(0))) {
                  return false;
               }

               if (CFG.gameAction.moveArmy(this.iProvinceID, tryRegroupArmy.getRoute(0), this.iArmy, nCivID, true, false)) {
                  this.iProvinceID = tryRegroupArmy.getRoute(0);
                  return false;
               }
            }
         }

         CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
         return true;
      } else if (DiplomacyManager.colonizeWastelandProvince(this.iColonizeProvinceID, this.iCivID)) {
         CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
         CFG.game.getCiv(this.iCivID).buildCivPersonality_Colonization();
         if (!CFG.gameAction.moveArmy(this.iProvinceID, this.iColonizeProvinceID, this.iArmy, nCivID, true, false)) {
            CFG.game
               .getCiv(this.iCivID)
               .civGameData
               .civPlans
               .addNewArmyMission(
                  this.iProvinceID, new CivArmyMission_RegroupAfterRecruitment(this.iCivID, this.iProvinceID, this.iColonizeProvinceID, this.iArmy)
               );
         }

         return true;
      } else {
         this.lockTreasury();
         return false;
      }
   }

   protected final boolean action_RecruitArmy() {
      if (CFG.game.getProvince(this.iProvinceID).getArmyCivID(this.iCivID) < 1) {
         if (CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince() == this.iCivID && !CFG.game.getProvince(this.iProvinceID).getSeaProvince()) {
            return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).COST_OF_RECRUIT
                     <= CFG.game.getCiv(this.iCivID).getMovePoints()
                  && CFG.game.getCiv(this.iCivID).recruitArmy_AI(this.iProvinceID, this.iArmy)
               ? false
               : false;
         } else {
            this.generateColonizeData();
            return false;
         }
      } else {
         return true;
      }
   }

   protected final boolean action_BuildPort(int nFromProvinceID, int toProvinceID) {
      if (CFG.game.getProvince(nFromProvinceID).getLevelOfPort() == 0) {
         if (CFG.game.getProvince(nFromProvinceID).getCivID() == this.iCivID) {
            if (CFG.game.getCiv(this.iCivID).isInConstruction(nFromProvinceID, ConstructionType.PORT) == 0) {
               if (BuildingsManager.constructPort(nFromProvinceID, this.iCivID)) {
                  return false;
               } else {
                  this.lockTreasury_Port(nFromProvinceID);
                  return false;
               }
            } else {
               return false;
            }
         } else {
            this.generateColonizeData();
            return false;
         }
      } else {
         return true;
      }
   }

   protected final void lockTreasury() {
      int colonizeCost = (int)((float)DiplomacyManager.getColonizeCost(this.iColonizeProvinceID, this.iCivID) * 1.05F);
      CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury, colonizeCost);
      if (CFG.game.getCiv(this.iCivID).iBudget > 0) {
         if (CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < (long)colonizeCost) {
            this.iObsolate = Math.max(
               this.iObsolate, Math.max(2, (int)Math.ceil((double)((float)CFG.game.getCiv(this.iCivID).getMoney() / (float)colonizeCost)))
            );
         } else {
            this.iObsolate = Math.max(2, this.iObsolate);
         }
      }
   }

   protected final void lockTreasury_Port(int nProvinceID) {
      int colonizeCost = (int)((float)BuildingsManager.getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID) * 1.05F);
      CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury, colonizeCost);
      if (CFG.game.getCiv(this.iCivID).iBudget > 0) {
         if (CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < (long)colonizeCost) {
            this.iObsolate = Math.max(
               this.iObsolate, Math.max(2, (int)Math.ceil((double)((float)CFG.game.getCiv(this.iCivID).getMoney() / (float)colonizeCost)))
            );
         } else {
            this.iObsolate = Math.max(2, this.iObsolate);
         }
      }
   }
}
