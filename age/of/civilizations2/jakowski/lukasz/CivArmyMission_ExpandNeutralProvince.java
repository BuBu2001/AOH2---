package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class CivArmyMission_ExpandNeutralProvince extends CivArmyMission {
   private int iCivID;
   private int iConquerProvinceID;
   private int iRegroupArmyPlace = -1;
   private int iRangeOfRegroup = 3;

   protected CivArmyMission_ExpandNeutralProvince(int nCivID, int conquerProvinceID) {
      this.toProvinceID = conquerProvinceID;
      this.iConquerProvinceID = conquerProvinceID;
      this.MISSION_ID = -1;
      this.iCivID = nCivID;
      this.MISSION_TYPE = CivArmyMission_Type.EXPAND_NETURAL_PROVINCE;
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = 4;
      this.iArmy = 0;
      this.action(nCivID);
   }

   @Override
   protected boolean action(int nCivID) {
      List<Integer> possibleFrom = new ArrayList<>();
      if (CFG.game.getProvince(this.iConquerProvinceID).getCivID() != 0) {
         this.iObsolate = -1;
         return true;
      } else {
         for(int i = 0; i < CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvinces(i)).getCivID() == this.iCivID) {
               possibleFrom.add(CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvinces(i));
            }
         }

         if (possibleFrom.size() == 0) {
            this.iObsolate = -1;
            return true;
         } else {
            if ((long)CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)
               > (long)CFG.game.getCiv(this.iCivID).getNumOfUnits() + CFG.game.getCiv(this.iCivID).getMoney() / 5L) {
               CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = (
                     CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 5 - CFG.game.getCiv(this.iCivID).getNumOfUnits()
                  )
                  * 5;
               ++this.iObsolate;
            }

            List<Integer> canMoveImmediately = new ArrayList<>();

            for(int i = possibleFrom.size() - 1; i >= 0; --i) {
               if (CFG.game.getProvince(possibleFrom.get(i)).getArmyCivID(this.iCivID)
                     - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(i))
                  > CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)) {
                  canMoveImmediately.add(possibleFrom.get(i));
               }
            }

            if (canMoveImmediately.size() > 0) {
               int randID = CFG.oR.nextInt(canMoveImmediately.size());
               int numOfNeutral = 0;

               for(int k = 0; k < CFG.game.getProvince(canMoveImmediately.get(randID)).getNeighboringProvincesSize(); ++k) {
                  if (CFG.game.getProvince(CFG.game.getProvince(canMoveImmediately.get(randID)).getNeighboringProvinces(k)).getCivID() == 0) {
                     ++numOfNeutral;
                  }
               }

               int tArmyToMove = CFG.game.getProvince(canMoveImmediately.get(randID)).getArmyCivID(this.iCivID);
               if (numOfNeutral > 1) {
                  tArmyToMove = CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 5 + CFG.oR.nextInt(5);
               }

               if (CFG.gameAction.moveArmy(canMoveImmediately.get(randID), this.iConquerProvinceID, tArmyToMove, this.iCivID, true, false)) {
                  this.iProvinceID = canMoveImmediately.get(randID);
                  this.iObsolate = -1;
                  return true;
               } else {
                  return false;
               }
            } else {
               canMoveImmediately.clear();
               int nArmiesInNeighbooringProvinces = 0;

               for(int i = possibleFrom.size() - 1; i >= 0; --i) {
                  if (CFG.game.getProvince(possibleFrom.get(i)).getArmyCivID(this.iCivID)
                        - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(i))
                     > 0) {
                     canMoveImmediately.add(possibleFrom.get(i));
                     nArmiesInNeighbooringProvinces += CFG.game.getProvince(possibleFrom.get(i)).getArmyCivID(this.iCivID)
                        - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(i));
                  }
               }

               if (CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 4 < nArmiesInNeighbooringProvinces) {
                  List<Integer> sortedByArmy = new ArrayList<>();

                  while(canMoveImmediately.size() > 0) {
                     int tBest = 0;

                     for(int i = canMoveImmediately.size() - 1; i > 0; --i) {
                        if (CFG.game.getProvince(canMoveImmediately.get(tBest)).getArmyCivID(this.iCivID)
                              - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(canMoveImmediately.get(tBest))
                           < CFG.game.getProvince(canMoveImmediately.get(i)).getArmyCivID(this.iCivID)
                              - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(canMoveImmediately.get(i))) {
                           tBest = i;
                        }
                     }

                     sortedByArmy.add(canMoveImmediately.get(tBest));
                     canMoveImmediately.remove(tBest);
                  }

                  for(int i = 0; i < sortedByArmy.size(); ++i) {
                     if (!CFG.gameAction
                        .moveArmy(
                           sortedByArmy.get(i),
                           this.iConquerProvinceID,
                           CFG.game.getProvince(sortedByArmy.get(i)).getArmyCivID(this.iCivID)
                              - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(sortedByArmy.get(i)),
                           nCivID,
                           true,
                           false
                        )) {
                        return false;
                     }
                  }
               }

               if (this.iRegroupArmyPlace < 0) {
                  this.iRegroupArmyPlace = possibleFrom.get(0);
                  this.iProvinceID = this.iRegroupArmyPlace;
                  this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
               } else if (CFG.game.getProvince(this.iRegroupArmyPlace).getCivID() != nCivID) {
                  this.iRegroupArmyPlace = possibleFrom.get(0);
                  this.iProvinceID = this.iRegroupArmyPlace;
                  this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
               } else {
                  if (CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID) > 2) {
                     CFG.gameAction
                        .moveArmy(
                           this.iRegroupArmyPlace,
                           this.iConquerProvinceID,
                           CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID),
                           this.iCivID,
                           true,
                           false
                        );
                  }

                  this.iProvinceID = this.iRegroupArmyPlace;
                  this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
               }

               int requiredArmy = CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)
                  - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(this.iConquerProvinceID)
                  - CFG.game.getCiv(nCivID).isRegoupingArmy_ToProvinceID(this.iRegroupArmyPlace);
               if (CFG.game.getCiv(nCivID).getNumOfUnits() > requiredArmy) {
                  if (requiredArmy <= 0) {
                     return false;
                  }

                  List<AI_NeighProvinces_Army> closestArmy = CFG.oAI
                     .getAllNeighboringProvincesInRange_WithArmyToRegroup(
                        this.iRegroupArmyPlace, nCivID, this.iRangeOfRegroup, true, false, new ArrayList<>(), new ArrayList<>(), requiredArmy
                     );
                  int nClosestArmy_Num = 0;

                  for(int i = closestArmy.size() - 1; i >= 0; --i) {
                     nClosestArmy_Num += closestArmy.get(i).iArmy;
                  }

                  if (nClosestArmy_Num <= requiredArmy) {
                     if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
                        )
                      {
                        List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI
                           .getAllNeighboringProvincesInRange_Recruit(this.iRegroupArmyPlace, nCivID, 3, true, false, new ArrayList<>(), new ArrayList<>());
                        if (this.iRegroupArmyPlace >= 0
                           && !CFG.game.getProvince(this.iRegroupArmyPlace).isOccupied()
                           && CFG.game.getProvince(this.iRegroupArmyPlace).getCivID() == nCivID) {
                           listOfPossibleProvincesToRecruit.add(new AI_NeighProvinces(this.iRegroupArmyPlace, 1));
                        }

                        if (listOfPossibleProvincesToRecruit.size() > 0) {
                           int tempRand = 0;
                           int tBest = 0;
                           int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);

                           for(int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                              if (tBestArmy < CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID)) {
                                 tBest = k;
                                 tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID);
                              }
                           }

                           int tArmyToRecruit = Math.min(
                              requiredArmy,
                              Math.min(
                                 CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID),
                                 (int)(
                                    CFG.game.getCiv(nCivID).getMoney()
                                       / (long)(CFG.game.getProvince(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                                 )
                              )
                           );
                           CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID, tArmyToRecruit);
                           int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);
                           if (tempArmy > 0) {
                              CFG.game
                                 .getCiv(nCivID)
                                 .civGameData
                                 .civPlans
                                 .lArmiesMissions
                                 .add(
                                    new CivArmyMission_RegroupAfterRecruitment(
                                       nCivID, listOfPossibleProvincesToRecruit.get(tBest).iProvinceID, this.iRegroupArmyPlace, tempArmy
                                    )
                                 );
                           }
                        }
                     }

                     return false;
                  }

                  for(int i = closestArmy.size() - 1; i >= 0; --i) {
                     boolean alreadyNeighboors = false;

                     for(int j = 0; j < CFG.game.getProvince(closestArmy.get(i).iProvinceID).getNeighboringProvincesSize(); ++j) {
                        if (this.iConquerProvinceID == CFG.game.getProvince(closestArmy.get(i).iProvinceID).getNeighboringProvinces(j)) {
                           alreadyNeighboors = true;
                           break;
                        }
                     }

                     if (!alreadyNeighboors) {
                        RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, closestArmy.get(i).iProvinceID, this.iRegroupArmyPlace);
                        if (tryRegroupArmy.getRouteSize() > 0) {
                           if (tryRegroupArmy.getRouteSize() == 1) {
                              if (!CFG.gameAction
                                 .moveArmy(closestArmy.get(i).iProvinceID, this.iRegroupArmyPlace, closestArmy.get(i).iArmy, nCivID, true, false)) {
                                 return false;
                              }

                              requiredArmy -= closestArmy.get(i).iArmy;
                           } else {
                              if (!CFG.gameAction
                                 .moveArmy(closestArmy.get(i).iProvinceID, tryRegroupArmy.getRoute(0), closestArmy.get(i).iArmy, nCivID, true, false)) {
                                 return false;
                              }

                              tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                              tryRegroupArmy.removeRoute(0);
                              tryRegroupArmy.setNumOfUnits(closestArmy.get(i).iArmy);
                              CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                              requiredArmy -= closestArmy.get(i).iArmy;
                           }
                        }
                     }

                     if (requiredArmy < 0) {
                        return false;
                     }
                  }
               }

               if (requiredArmy > 0) {
                  int recrutiableUnits_Treasury = (int)(CFG.game.getCiv(nCivID).getMoney() / 5L);
                  if (recrutiableUnits_Treasury > requiredArmy) {
                     canMoveImmediately.clear();

                     for(int i = possibleFrom.size() - 1; i >= 0; --i) {
                        if (CFG.gameAction.getRecruitableArmy(possibleFrom.get(i), nCivID) > requiredArmy) {
                           canMoveImmediately.add(possibleFrom.get(i));
                        }
                     }

                     if (canMoveImmediately.size() != 0) {
                        int tRand = CFG.oR.nextInt(canMoveImmediately.size());
                        CFG.game.getCiv(nCivID).recruitArmy_AI(canMoveImmediately.get(tRand), requiredArmy + 5 + CFG.oR.nextInt(5));
                        return false;
                     }

                     int tBest = 0;

                     for(int i = possibleFrom.size() - 1; i > 0; --i) {
                        if (CFG.gameAction.getRecruitableArmy(possibleFrom.get(i), nCivID)
                           > CFG.gameAction.getRecruitableArmy(possibleFrom.get(tBest), nCivID)) {
                           tBest = i;
                        }
                     }

                     CFG.game.getCiv(nCivID).recruitArmy_AI(possibleFrom.get(tBest), requiredArmy + 5 + CFG.oR.nextInt(5));
                  } else {
                     this.iRangeOfRegroup = 6;
                  }
               }

               int i = 0;

               while(
                  i < possibleFrom.size()
                     && CFG.gameAction
                        .moveArmy(
                           possibleFrom.get(i),
                           this.iConquerProvinceID,
                           CFG.game.getProvince(possibleFrom.get(i)).getArmyCivID(this.iCivID),
                           this.iCivID,
                           true,
                           false
                        )
               ) {
                  ++i;
               }

               return false;
            }
         }
      }
   }

   @Override
   protected void onRemove() {
      CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = -1;
   }
}
