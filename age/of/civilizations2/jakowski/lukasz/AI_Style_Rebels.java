package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Style_Rebels extends AI_Style {
   protected AI_Style_Rebels() {
      this.TAG = "REBELS";
      this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.3F;
      this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 20;
   }

   @Override
   protected void turnOrders(int nCivID) {
      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         if ((float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0)
               < (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                  * 0.15F
                  * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getGrowthRate_Population()
            && CFG.oR.nextInt(100) < 24) {
            CFG.game
               .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
               .updateArmy(
                  CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0)
                     + Math.max(
                        CFG.oR.nextInt(18),
                        (int)(
                           (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) * (0.0125F + (float)CFG.oR.nextInt(78) / 1000.0F)
                        )
                     )
               );
         }
      }

      this.tryRegroupArmy(nCivID);
   }

   @Override
   protected void buildStartingBuildings(int nCivID) {
   }

   protected final void tryRegroupArmy(int nCivID) {
      List<Integer> tempProvincesToRegroup = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDangerLevel() == 0
            && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) > 0) {
            boolean canBeRegroped = false;

            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() == nCivID
                  && CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getDangerLevel() > 0) {
                  canBeRegroped = true;
                  CFG.gameAction
                     .moveArmy(
                        CFG.game.getCiv(nCivID).getProvinceID(i),
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j),
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0),
                        nCivID,
                        false,
                        true
                     );
                  break;
               }
            }

            if (canBeRegroped) {
               tempProvincesToRegroup.add(CFG.game.getCiv(nCivID).getProvinceID(i));
            }
         }
      }

      if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
         && CFG.oR.nextInt(100) < 65) {
         List<Integer> tempProvincesFrom = new ArrayList<>();
         List<Integer> tempProvincesTo = new ArrayList<>();

         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() > 0
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getArmy(0) > 0
                  && CFG.game
                        .getCiv(CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID())
                        .getNumOfProvinces()
                     > 1
                  && CFG.game
                     .getCivsAtWar(
                        nCivID, CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()
                     )) {
                  int canBeMoved = 0;

                  for(int k = 0;
                     k
                        < CFG.game
                           .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j))
                           .getNeighboringProvincesSize();
                     ++k
                  ) {
                     if (CFG.game
                              .getProvince(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                              .getCivID()
                           == CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()
                        && CFG.game
                              .getProvince(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                              .getCivID()
                           == CFG.game
                              .getProvince(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                              .getTrueOwnerOfProvince()) {
                        ++canBeMoved;
                        break;
                     }
                  }

                  if (canBeMoved <= 1) {
                     tempProvincesFrom.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                     tempProvincesTo.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j));
                  }
               }
            }
         }

         if (tempProvincesFrom.size() > 0) {
            for(int tRandMove = 0;
               tempProvincesFrom.size() > 0
                  && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  && tRandMove < 35;
               tRandMove = CFG.oR.nextInt(100)
            ) {
               int tBest = -1;
               float fBestScore = 0.0F;

               for(int i = tempProvincesFrom.size() - 1; i >= 0; --i) {
                  if (!CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(tempProvincesTo.get(i))) {
                     if (tBest < 0) {
                        tBest = i;
                        fBestScore = this.attackProvince_Score(nCivID, tempProvincesFrom.get(i), tempProvincesTo.get(i));
                     } else {
                        float tCurrScore = this.attackProvince_Score(nCivID, tempProvincesFrom.get(i), tempProvincesTo.get(i));
                        if (fBestScore < this.attackProvince_Score(nCivID, tempProvincesFrom.get(i), tempProvincesTo.get(i))) {
                           tBest = i;
                           fBestScore = tCurrScore;
                        } else if (fBestScore == tCurrScore && CFG.oR.nextInt(100) < 50) {
                           tBest = i;
                           fBestScore = tCurrScore;
                        }
                     }
                  }
               }

               if (tBest < 0) {
                  return;
               }

               if (CFG.game.getProvince(tempProvincesFrom.get(tBest)).getArmy(0) > 0) {
                  CFG.gameAction
                     .moveArmy(
                        tempProvincesFrom.get(tBest),
                        tempProvincesTo.get(tBest),
                        CFG.game.getProvince(tempProvincesFrom.get(tBest)).getArmy(0),
                        nCivID,
                        false,
                        true
                     );
               }

               tempProvincesFrom.remove(tBest);
               tempProvincesTo.remove(tBest);
            }
         }
      }
   }

   protected final float attackProvince_Score(int nCivID, int nFromProvinceID, int toProvinceID) {
      int ownProvinces = 0;
      int enemyProvinces = 0;

      for(int i = 0; i < CFG.game.getProvince(toProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(toProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
            ++ownProvinces;
         } else if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(toProvinceID).getNeighboringProvinces(i)).getCivID())) {
            ++enemyProvinces;
         }
      }

      try {
         return (float)ownProvinces / (float)(ownProvinces + enemyProvinces) + 0.075F * CFG.game.getProvince(toProvinceID).getRevolutionaryRisk();
      } catch (ArithmeticException var7) {
         return 0.0F;
      }
   }
}
