package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_Data {
   protected PeaceTreaty_GameData peaceTreatyGameData = new PeaceTreaty_GameData();
   protected List<PeaceTreaty_DrawData> drawProvinceOwners = new ArrayList<>();
   protected List<Integer> provincesLeftToTake = new ArrayList<>();
   protected int iProvincesLeftToTakeSize = 0;
   protected boolean scoreCountDefenders = false;
   protected int iBrushCivID = -1;
   protected int iPlayerTurnID = 0;
   protected int iLastTakenID = -1;
   protected static final float VASSALIZE_COST = 0.4F;
   protected static final float WAR_REPARATIONS_COST = 0.1F;

   protected PeaceTreaty_Data() {
   }

   protected PeaceTreaty_Data(PeaceTreaty_GameData nPeaceTreaty) {
      this.peaceTreatyGameData = nPeaceTreaty;
      this.iBrushCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
      this.iPlayerTurnID = CFG.PLAYER_TURNID;
      this.prepareProvinceData(false);
      this.prepareDemansVassalsData();
   }

   protected PeaceTreaty_Data(int iWarID, boolean scoreCountDefenders) {
      List<Boolean> addDefender = new ArrayList<>();
      List<Boolean> addAggressor = new ArrayList<>();

      for(int i = 0; i < CFG.game.getWar(iWarID).getDefendersSize(); ++i) {
         addDefender.add(true);
      }

      for(int i = 0; i < CFG.game.getWar(iWarID).getAggressorsSize(); ++i) {
         addAggressor.add(true);
      }

      this.initPeaceTreatyData(iWarID, addDefender, addAggressor, scoreCountDefenders);
   }

   protected PeaceTreaty_Data(int iWarID, List<Boolean> addDefender, List<Boolean> addAggressor, boolean scoreCountDefenders) {
      this.initPeaceTreatyData(iWarID, addDefender, addAggressor, scoreCountDefenders);
   }

   protected final void AI_UseVictoryPoints() {
      try {
         Gdx.app.log("AoC", "AI_UseVictoryPoints -> provincesLeftToTake.size: " + this.iProvincesLeftToTakeSize);
         if (this.iProvincesLeftToTakeSize > 0) {
            int iBestCivID = -1;
            int tBestPoints = -1;

            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft > tBestPoints) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft;
               } else if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft == tBestPoints && CFG.oR.nextInt(100) < 50) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft;
               }
            }

            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft > tBestPoints) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft;
               } else if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft == tBestPoints && CFG.oR.nextInt(100) < 50) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft;
               }
            }

            Gdx.app
               .log(
                  "AoC",
                  "AI_UseVictoryPoints -> iBestCivID: "
                     + iBestCivID
                     + (iBestCivID >= 0 ? ", " + CFG.game.getCiv(iBestCivID).getCivName() : "")
                     + ", tBestPoints: "
                     + tBestPoints
               );
            if (iBestCivID > 0 && tBestPoints > 0 && !CFG.game.getCiv(iBestCivID).getControlledByPlayer()) {
               Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE");
               this.AI_UseVictoryPoints_CivID(iBestCivID, tBestPoints);
            }
         }
      } catch (StackOverflowError var4) {
      }
   }

   protected final void AI_UseVictoryPoints_CivID_TakeVassal(int nCivID, int pointsLeft, boolean clearPoints) {
      try {
         List<Integer> canVassalizeCivs = new ArrayList<>();
         boolean doneCheck = false;

         for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
               for(int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++o) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(o).iWillBecomeVassalOfCivID < 0
                     && this.getVassalization_Cost(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(o).iCivID) <= pointsLeft) {
                     canVassalizeCivs.add(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(o).iCivID);
                  }
               }

               doneCheck = true;
               break;
            }
         }

         if (!doneCheck) {
            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
                  for(int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++o) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(o).iWillBecomeVassalOfCivID < 0
                        && this.getVassalization_Cost(this.peaceTreatyGameData.lCivsDemands_Defenders.get(o).iCivID) <= pointsLeft) {
                        canVassalizeCivs.add(this.peaceTreatyGameData.lCivsDemands_Defenders.get(o).iCivID);
                     }
                  }

                  doneCheck = true;
                  break;
               }
            }
         }

         if (canVassalizeCivs.size() > 0) {
            this.takeVassalize(canVassalizeCivs.get(CFG.oR.nextInt(canVassalizeCivs.size())), nCivID, nCivID);
            this.AI_UseVictoryPoints();
            return;
         }

         if (clearPoints) {
            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }

            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }
         }
      } catch (StackOverflowError var8) {
      }
   }

   protected final void AI_UseVictoryPoints_CivID(int nCivID, int pointsLeft) {
      try {
         List<Float> lScores = new ArrayList<>();
         List<Boolean> lNeigh = new ArrayList<>();
         List<Integer> toTake = new ArrayList<>();
         boolean canTakeNieghProvince = false;
         float maxDistance = 1.0E-4F;

         for(int i = 0; i < this.iProvincesLeftToTakeSize; ++i) {
            if (pointsLeft >= this.drawProvinceOwners.get(this.provincesLeftToTake.get(i)).iProvinceValue) {
               maxDistance = Math.max(
                  maxDistance, CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(nCivID).getCapitalProvinceID(), this.provincesLeftToTake.get(i))
               );
               if (CFG.game.getProvince(this.provincesLeftToTake.get(i)).getTrueOwnerOfProvince() == nCivID) {
                  lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i), 10.0F));
                  lNeigh.add(true);
                  toTake.add(this.provincesLeftToTake.get(i));
                  canTakeNieghProvince = true;
               } else {
                  boolean tempProvinceAdded = false;

                  for(int j = 0; j < CFG.game.getProvince(this.provincesLeftToTake.get(i)).getNeighboringProvincesSize(); ++j) {
                     if (this.drawProvinceOwners.get(CFG.game.getProvince(this.provincesLeftToTake.get(i)).getNeighboringProvinces(j)).iCivID == nCivID) {
                        if (CFG.game.getProvince(this.provincesLeftToTake.get(i)).getCore().getHaveACore(nCivID)) {
                           tempProvinceAdded = true;
                           lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i), 5.0F));
                           lNeigh.add(true);
                           toTake.add(this.provincesLeftToTake.get(i));
                           canTakeNieghProvince = true;
                        } else {
                           lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i), 4.25F));
                           lNeigh.add(true);
                           toTake.add(this.provincesLeftToTake.get(i));
                           tempProvinceAdded = true;
                           canTakeNieghProvince = true;
                        }
                        break;
                     }
                  }

                  if (!tempProvinceAdded) {
                     if (CFG.game.getProvince(this.provincesLeftToTake.get(i)).getCore().getHaveACore(nCivID)) {
                        lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i), 1.75F));
                        lNeigh.add(true);
                        toTake.add(this.provincesLeftToTake.get(i));
                        canTakeNieghProvince = true;
                     } else if (CFG.game.getProvince(this.provincesLeftToTake.get(i)).getNeighboringSeaProvincesSize() > 0) {
                        lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i), 0.325F));
                        lNeigh.add(true);
                        toTake.add(this.provincesLeftToTake.get(i));
                        canTakeNieghProvince = true;
                     } else {
                        lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i), 0.025F));
                        lNeigh.add(false);
                        toTake.add(this.provincesLeftToTake.get(i));
                     }
                  }
               }
            }
         }

         if (lNeigh.size() == 0 || toTake.size() == 0) {
            Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE -> lNeigh.size(): " + lNeigh.size());
            this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
            return;
         }

         if (!canTakeNieghProvince) {
            Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE -> canTakeNieghProvince: " + canTakeNieghProvince);
            this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
            return;
         }

         int tBest = 0;

         for(int i = lScores.size() - 1; i > 0; --i) {
            lScores.set(
               i,
               lScores.get(i)
                  * (
                     0.8F
                        + 0.2F
                           * (
                              1.0F
                                 - CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(nCivID).getCapitalProvinceID(), toTake.get(i)) / maxDistance
                           )
                  )
                  * (this.iLastTakenID == toTake.get(i) ? 0.05F : 1.0F)
            );
         }

         for(int i = lScores.size() - 1; i > 0; --i) {
            if (lScores.get(tBest) < lScores.get(i)) {
               tBest = i;
            }
         }

         if (lNeigh.get(tBest)) {
            if (!this.takeProvince(toTake.get(tBest), nCivID, nCivID)) {
               for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }

               for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }
            }
         } else if (toTake.size() == 1) {
            if (!this.takeProvince(toTake.get(tBest), nCivID, nCivID)) {
               for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }

               for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }
            }
         } else {
            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }

            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }
         }
      } catch (StackOverflowError var11) {
      }
   }

   protected final float AI_UseVictoryPoints_CivID_Score(int nCivID, int nProvinceID, float modifier) {
      int neigh_OwnProvinces = 0;
      int neigh_OtherCivsProvinces = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() < 0
            && CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() > 0) {
            if (this.drawProvinceOwners.get(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).iCivID == nCivID) {
               ++neigh_OwnProvinces;
            } else {
               ++neigh_OtherCivsProvinces;
            }
         }
      }

      if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
         ++neigh_OwnProvinces;
      }

      neigh_OtherCivsProvinces = Math.max(1, neigh_OtherCivsProvinces);
      return modifier
         + (float)neigh_OwnProvinces * modifier * 0.125F
         + modifier * (float)(neigh_OwnProvinces / (neigh_OwnProvinces + neigh_OtherCivsProvinces))
         + 0.125F
            * (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
            / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
         + 0.05F * (float)CFG.game.getProvince(nProvinceID).getEconomy() / (float)CFG.game.getGameScenarios().getScenario_StartingEconomy()
         + 0.0075F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel();
   }

   protected final void addProvincesLeftToTake(int nProvinceID) {
      Gdx.app.log("AoC", "addProvincesLeftToTake: nCivID: , nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());

      for(int i = 0; i < this.iProvincesLeftToTakeSize; ++i) {
         if (this.provincesLeftToTake.get(i) == nProvinceID) {
            return;
         }
      }

      this.provincesLeftToTake.add(nProvinceID);
      this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
   }

   protected final void removeProvincesLeftToTake(int nProvinceID) {
      Gdx.app.log("AoC", "removeProvincesLeftToTake: nCivID: , nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());

      for(int i = 0; i < this.iProvincesLeftToTakeSize; ++i) {
         if (this.provincesLeftToTake.get(i) == nProvinceID) {
            this.provincesLeftToTake.remove(i);
            this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
            return;
         }
      }
   }

   private final void initPeaceTreatyData(int iWarID, List<Boolean> addDefender, List<Boolean> addAggressor, boolean scoreCountDefenders) {
      try {
         this.peaceTreatyGameData.iWarID = iWarID;
         this.peaceTreatyGameData.WAR_TAG = CFG.game.getWar(iWarID).WAR_TAG;
         this.scoreCountDefenders = scoreCountDefenders;

         for(int i = 0; i < CFG.game.getWar(iWarID).getDefendersSize(); ++i) {
            if (addDefender.get(i)) {
               this.peaceTreatyGameData.lCivsData_Defenders.add(CFG.game.getWar(iWarID).getDefenders_ProvincesLost(i, addDefender, addAggressor));
               this.peaceTreatyGameData
                  .lCivsDemands_Defenders
                  .add(
                     new PeaceTreaty_Demands(
                        CFG.game.getWar(iWarID).getDefenderID(i).getCivID(),
                        CFG.game.getWar(iWarID).getWarScore_DefendersInProvinceValue_OnlyPositive(i, addDefender, addAggressor)
                     )
                  );
            }
         }

         for(int i = 0; i < CFG.game.getWar(iWarID).getAggressorsSize(); ++i) {
            if (addAggressor.get(i)) {
               this.peaceTreatyGameData.lCivsData_Aggressors.add(CFG.game.getWar(iWarID).getAggressors_ProvincesLost(i, addDefender, addAggressor));
               this.peaceTreatyGameData
                  .lCivsDemands_Aggressors
                  .add(
                     new PeaceTreaty_Demands(
                        CFG.game.getWar(iWarID).getAggressorID(i).getCivID(),
                        CFG.game.getWar(iWarID).getWarScore_AggressorsInProvinceValue_OnlyPositive(i, addDefender, addAggressor)
                     )
                  );
            }
         }

         this.iBrushCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
         this.iPlayerTurnID = CFG.PLAYER_TURNID;
         this.prepareProvinceData(true);
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      }
   }

   protected final void prepareProvinceData(boolean buildProvincesLost) {
      this.drawProvinceOwners.clear();
      this.drawProvinceOwners = new ArrayList<>();
      List<Boolean> tempParticipants = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         tempParticipants.add(false);
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
         tempParticipants.set(this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID, true);
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
         tempParticipants.set(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID, true);
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (tempParticipants.get(CFG.game.getProvince(i).getCivID())) {
            this.drawProvinceOwners.add(new PeaceTreaty_DrawData(CFG.game.getProvince(i).getCivID(), CFG.game.getProvinceValue(i), false));
         } else {
            this.drawProvinceOwners.add(new PeaceTreaty_DrawData(CFG.game.getProvince(i).getCivID() * -1, CFG.game.getProvinceValue(i), false));
         }
      }

      if (buildProvincesLost) {
         try {
            for(int i = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; i >= 0; --i) {
               for(int j = this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.size() - 1; j >= 0; --j) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).isToTake = true;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).iCivID = CFG.game
                     .getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j))
                     .getTrueOwnerOfProvince();
                  PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
                  var10000.iTotalNumOfVicotryPoints += CFG.game.getProvinceValue(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j));
               }

               for(int j = 0; j < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getNumOfProvinces(); ++j) {
                  PeaceTreaty_Demands var85 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
                  var85.iTotalNumOfVicotryPoints += CFG.game
                     .getProvinceValue(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j));
               }
            }
         } catch (IndexOutOfBoundsException var12) {
            CFG.exceptionStack(var12);
         }

         try {
            for(int i = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i >= 0; --i) {
               for(int j = this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size() - 1; j >= 0; --j) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).isToTake = true;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).iCivID = CFG.game
                     .getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j))
                     .getTrueOwnerOfProvince();
                  PeaceTreaty_Demands var86 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
                  var86.iTotalNumOfVicotryPoints += CFG.game.getProvinceValue(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j));
               }

               for(int j = 0; j < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getNumOfProvinces(); ++j) {
                  PeaceTreaty_Demands var87 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
                  var87.iTotalNumOfVicotryPoints += CFG.game
                     .getProvinceValue(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j));
               }
            }
         } catch (IndexOutOfBoundsException var11) {
            CFG.exceptionStack(var11);
         }

         for(int i = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; i >= 0; --i) {
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs = new ArrayList<>();

            for(int j = 0; j < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getNumOfProvinces(); ++j) {
               if (!CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j)).isOccupied()) {
                  for(int u = 0;
                     u
                        < CFG.game
                           .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j))
                           .getCore()
                           .getCivsSize();
                     ++u
                  ) {
                     if (CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j))
                              .getCore()
                              .getCivID(u)
                           != CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j))
                              .getCivID()
                        && CFG.game
                              .getCiv(
                                 CFG.game
                                    .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j))
                                    .getCore()
                                    .getCivID(u)
                              )
                              .getNumOfProvinces()
                           == 0) {
                        boolean tAdd = true;

                        for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.size() - 1; k >= 0; --k) {
                           if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(k).iCivID
                              == CFG.game
                                 .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j))
                                 .getCore()
                                 .getCivID(u)) {
                              tAdd = false;
                              this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i)
                                 .lReleasableCivs
                                 .get(k)
                                 .addProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j));
                              break;
                           }
                        }

                        if (tAdd) {
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i)
                              .lReleasableCivs
                              .add(
                                 new PeaceTreaty_ReleaseableVassals(
                                    CFG.game
                                       .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j))
                                       .getCore()
                                       .getCivID(u),
                                    CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getProvinceID(j)
                                 )
                              );
                        }
                     }
                  }
               }
            }

            for(int o = this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size() - 1; o >= 0; --o) {
               for(int j = 0;
                  j < CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)).getCore().getCivsSize();
                  ++j
               ) {
                  if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)).getCore().getCivID(j)
                        != CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)).getCivID()
                     && CFG.game
                           .getCiv(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)).getCore().getCivID(j))
                           .getNumOfProvinces()
                        == 0) {
                     boolean tAdd = true;

                     for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.size() - 1; k >= 0; --k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(k).iCivID
                           == CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)).getCore().getCivID(j)) {
                           tAdd = false;
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i)
                              .lReleasableCivs
                              .get(k)
                              .addProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o));
                           break;
                        }
                     }

                     if (tAdd) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i)
                           .lReleasableCivs
                           .add(
                              new PeaceTreaty_ReleaseableVassals(
                                 CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)).getCore().getCivID(j),
                                 this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(o)
                              )
                           );
                     }
                  }
               }
            }
         }

         for(int i = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; i >= 0; --i) {
            this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs = new ArrayList<>();

            for(int j = 0; j < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getNumOfProvinces(); ++j) {
               if (!CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j)).isOccupied()) {
                  for(int u = 0;
                     u
                        < CFG.game
                           .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j))
                           .getCore()
                           .getCivsSize();
                     ++u
                  ) {
                     if (CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j))
                              .getCore()
                              .getCivID(u)
                           != CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j)).getCivID()
                        && CFG.game
                              .getCiv(
                                 CFG.game
                                    .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j))
                                    .getCore()
                                    .getCivID(u)
                              )
                              .getNumOfProvinces()
                           == 0) {
                        boolean tAdd = true;

                        for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.size() - 1; k >= 0; --k) {
                           if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(k).iCivID
                              == CFG.game
                                 .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j))
                                 .getCore()
                                 .getCivID(u)) {
                              tAdd = false;
                              this.peaceTreatyGameData.lCivsDemands_Defenders.get(i)
                                 .lReleasableCivs
                                 .get(k)
                                 .addProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j));
                              break;
                           }
                        }

                        if (tAdd) {
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i)
                              .lReleasableCivs
                              .add(
                                 new PeaceTreaty_ReleaseableVassals(
                                    CFG.game
                                       .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j))
                                       .getCore()
                                       .getCivID(u),
                                    CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getProvinceID(j)
                                 )
                              );
                        }
                     }
                  }
               }
            }

            for(int o = this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.size() - 1; o >= 0; --o) {
               for(int j = 0; j < CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)).getCore().getCivsSize(); ++j) {
                  if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)).getCore().getCivID(j)
                        != CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)).getCivID()
                     && CFG.game
                           .getCiv(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)).getCore().getCivID(j))
                           .getNumOfProvinces()
                        == 0) {
                     boolean tAdd = true;

                     for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.size() - 1; k >= 0; --k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(k).iCivID
                           == CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)).getCore().getCivID(j)) {
                           tAdd = false;
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i)
                              .lReleasableCivs
                              .get(k)
                              .addProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o));
                           break;
                        }
                     }

                     if (tAdd) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i)
                           .lReleasableCivs
                           .add(
                              new PeaceTreaty_ReleaseableVassals(
                                 CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)).getCore().getCivID(j),
                                 this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(o)
                              )
                           );
                     }
                  }
               }
            }
         }

         for(int i = this.drawProvinceOwners.size() - 1; i >= 0; --i) {
            if (this.drawProvinceOwners.get(i).isToTake) {
               this.provincesLeftToTake.add(i);
            }
         }

         this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
      } else {
         try {
            List<Boolean> addDefender = new ArrayList<>();
            List<Boolean> addAggressor = new ArrayList<>();

            for(int i = 0; i < CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefendersSize(); ++i) {
               boolean addCiv = false;

               for(int j = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; j >= 0; --j) {
                  if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenderID(i).getCivID()
                     == this.peaceTreatyGameData.lCivsData_Defenders.get(j).iCivID) {
                     addCiv = true;
                     break;
                  }
               }

               addDefender.add(addCiv);
            }

            for(int i = 0; i < CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorsSize(); ++i) {
               boolean addCiv = false;

               for(int j = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; j >= 0; --j) {
                  if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorID(i).getCivID()
                     == this.peaceTreatyGameData.lCivsData_Aggressors.get(j).iCivID) {
                     addCiv = true;
                     break;
                  }
               }

               addAggressor.add(addCiv);
            }

            for(int i = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; i >= 0; --i) {
               PeaceTreaty_Civs tempLost = CFG.game
                  .getWar(this.peaceTreatyGameData.iWarID)
                  .getDefenders_ProvincesLost(
                     CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenderID_ByCivID(this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID),
                     addDefender,
                     addAggressor
                  );

               for(int j = tempLost.lProvincesLost.size() - 1; j >= 0; --j) {
                  boolean isAdded = false;

                  for(int k = this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.size() - 1; k >= 0; --k) {
                     if (tempLost.lProvincesLost.get(j).equals(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(k))) {
                        isAdded = true;
                        break;
                     }
                  }

                  if (!isAdded) {
                     this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.add(tempLost.lProvincesLost.get(j));
                     this.makeDemand_Province(
                        tempLost.lProvincesLost.get(j),
                        this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID,
                        this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID,
                        true
                     );
                  }

                  tempLost.lProvincesLost.remove(j);
               }
            }

            for(int i = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i >= 0; --i) {
               PeaceTreaty_Civs tempLost = CFG.game
                  .getWar(this.peaceTreatyGameData.iWarID)
                  .getAggressors_ProvincesLost(
                     CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorID_ByCivID(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID),
                     addDefender,
                     addAggressor
                  );

               for(int j = tempLost.lProvincesLost.size() - 1; j >= 0; --j) {
                  boolean isAdded = false;

                  for(int k = this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size() - 1; k >= 0; --k) {
                     if (tempLost.lProvincesLost.get(j).equals(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(k))) {
                        isAdded = true;
                        break;
                     }
                  }

                  if (!isAdded) {
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.add(tempLost.lProvincesLost.get(j));
                     this.makeDemand_Province(
                        tempLost.lProvincesLost.get(j),
                        this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
                        this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
                        true
                     );
                  }

                  tempLost.lProvincesLost.remove(j);
               }
            }

            for(int i = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; i >= 0; --i) {
               if (!CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID)
                  && !CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID)) {
                  this.peaceTreatyGameData.lCivsData_Defenders.remove(i);
                  this.peaceTreatyGameData.lCivsDemands_Defenders.remove(i);
               } else {
                  for(int j = this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.size() - 1; j >= 0; --j) {
                     if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).isOccupied()) {
                        if (!CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsDefender(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).getCivID())
                           && !CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsAggressor(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).getCivID())) {
                           boolean removed = false;

                           for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                              for(int o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.size() - 1; o >= 0; --o) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.get(o)
                                    == this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)) {
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.remove(o);
                                    k = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }

                           if (!removed) {
                              for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                                 for(int o = this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.size() - 1; o >= 0; --o) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.get(o)
                                       == this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)) {
                                       this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.remove(o);
                                       k = -1;
                                       removed = true;
                                       break;
                                    }
                                 }
                              }
                           }

                           this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.remove(j);
                        } else {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).isToTake = true;
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).iCivID = CFG.game
                              .getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j))
                              .getTrueOwnerOfProvince();
                        }
                     } else {
                        boolean removed = false;

                        for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                           for(int o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.size() - 1; o >= 0; --o) {
                              if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.get(o)
                                 == this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)) {
                                 this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.remove(o);
                                 k = -1;
                                 removed = true;
                                 break;
                              }
                           }
                        }

                        if (!removed) {
                           for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                              for(int o = this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.size() - 1; o >= 0; --o) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.get(o)
                                    == this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)) {
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.remove(o);
                                    k = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }
                        }

                        this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.remove(j);
                     }
                  }
               }
            }

            for(int i = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i >= 0; --i) {
               if (!CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID)
                  && !CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID)) {
                  this.peaceTreatyGameData.lCivsData_Aggressors.remove(i);
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.remove(i);
               } else {
                  for(int j = this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size() - 1; j >= 0; --j) {
                     if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).isOccupied()) {
                        if (!CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsDefender(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).getCivID())
                           && !CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsAggressor(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).getCivID())) {
                           boolean removed = false;

                           for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                              for(int o = this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.size() - 1; o >= 0; --o) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.get(o)
                                    == this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)) {
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.remove(o);
                                    k = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }

                           if (!removed) {
                              for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                                 for(int o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.size() - 1; o >= 0; --o) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.get(o)
                                       == this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)) {
                                       this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.remove(o);
                                       k = -1;
                                       removed = true;
                                       break;
                                    }
                                 }
                              }
                           }

                           this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.remove(j);
                        } else {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).isToTake = true;
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).iCivID = CFG.game
                              .getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j))
                              .getTrueOwnerOfProvince();
                        }
                     } else {
                        boolean removed = false;

                        for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; k >= 0; --k) {
                           for(int o = this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.size() - 1; o >= 0; --o) {
                              if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.get(o)
                                 == this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)) {
                                 this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lDemands.remove(o);
                                 k = -1;
                                 removed = true;
                                 break;
                              }
                           }
                        }

                        if (!removed) {
                           for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; k >= 0; --k) {
                              for(int o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.size() - 1; o >= 0; --o) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.get(o)
                                    == this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)) {
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lDemands.remove(o);
                                    k = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }
                        }

                        this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.remove(j);
                     }
                  }
               }
            }

            for(int i = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; i >= 0; --i) {
               for(int j = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.size() - 1; j >= 0; --j) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).isTaken = this.peaceTreatyGameData
                        .lCivsDemands_Defenders
                        .get(i)
                     .iCivID;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).iCivID = this.peaceTreatyGameData
                        .lCivsDemands_Defenders
                        .get(i)
                     .iCivID;
               }
            }

            for(int i = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; i >= 0; --i) {
               for(int j = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.size() - 1; j >= 0; --j) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).isTaken = this.peaceTreatyGameData
                        .lCivsDemands_Aggressors
                        .get(i)
                     .iCivID;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).iCivID = this.peaceTreatyGameData
                        .lCivsDemands_Aggressors
                        .get(i)
                     .iCivID;
               }
            }
         } catch (IndexOutOfBoundsException var10) {
            CFG.exceptionStack(var10);
         }
      }
   }

   protected final void prepareDemansVassalsData() {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.size(); ++j) {
            for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++k) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.get(j).iFromCivID
                  == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).iCivID) {
                  for(int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.size(); ++o) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).iCivID
                        == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.get(j).iVassalCivID) {
                        for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).lProvinces.size(); ++u) {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).lProvinces.get(u)).iCivID = this.peaceTreatyGameData
                                    .lCivsDemands_Defenders
                                    .get(i)
                                 .lReleasableCivs_TakeControl
                                 .get(j)
                              .iVassalCivID;
                        }
                     }
                  }
               }
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.size(); ++j) {
            for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++k) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(j).iFromCivID
                  == this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).iCivID) {
                  for(int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lReleasableCivs.size(); ++o) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o).iCivID
                        == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(j).iVassalCivID) {
                        for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o).lProvinces.size(); ++u) {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o).lProvinces.get(u)).iCivID = this.peaceTreatyGameData
                                    .lCivsDemands_Aggressors
                                    .get(i)
                                 .lReleasableCivs_TakeControl
                                 .get(j)
                              .iVassalCivID;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected final int takeReleaseVassal(int iFromCivID, int nReleaseCivID, int nCivID, int pointsUsedByCivID) {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == iFromCivID) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++j) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID == nCivID) {
                  int nID = -1;

                  for(int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.size(); ++o) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(o).iCivID == nReleaseCivID) {
                        nID = o;
                        break;
                     }
                  }

                  if (nID >= 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID > 0) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID != nCivID) {
                           return this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID;
                        }

                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID = -1;
                        PeaceTreaty_Demands var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                        var27.iVictoryPointsLeft += this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).getScoreValue();
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                        for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.size(); ++k) {
                           if (!this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isToTake
                              )
                            {
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getCivID();
                           } else {
                              if (this.drawProvinceOwners
                                       .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                    .isTaken
                                 > 0) {
                                 if (this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken
                                    != this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID) {
                                    for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Aggressors
                                             .get(u)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .isTaken) {
                                          var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u);
                                          var27.iVictoryPointsLeft += this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iProvinceValue;
                                       }
                                    }

                                    for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Defenders
                                             .get(u)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .isTaken) {
                                          var27 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(u);
                                          var27.iVictoryPointsLeft += this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iProvinceValue;
                                       }
                                    }

                                    var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                                    var27.iVictoryPointsLeft -= this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                                 }
                              } else {
                                 var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                                 var27.iVictoryPointsLeft -= this.drawProvinceOwners
                                       .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                    .iProvinceValue;
                              }

                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = -1;
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getCivID();
                           }
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft
                        < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).getScoreValue()) {
                        return 0;
                     }

                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID = nCivID;
                     PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                     var10000.iVictoryPointsLeft -= this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).getScoreValue();
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                     for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.size(); ++k) {
                        if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isToTake
                           )
                         {
                           if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken
                              > 0) {
                              for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Aggressors
                                       .get(i)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken) {
                                    var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
                                    var10000.iVictoryPointsLeft += this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                                 }
                              }

                              for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Defenders
                                       .get(i)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken) {
                                    var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
                                    var10000.iVictoryPointsLeft += this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                                 }
                              }

                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = -1;
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getTrueOwnerOfProvince();
                           }

                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = nCivID;
                        }

                        this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = nReleaseCivID;
                     }

                     return nCivID;
                  }

                  i = this.peaceTreatyGameData.lCivsDemands_Defenders.size();
                  break;
               }
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == iFromCivID) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++j) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iCivID == nCivID) {
                  int nID = -1;

                  for(int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.size(); ++o) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(o).iCivID == nReleaseCivID) {
                        nID = o;
                        break;
                     }
                  }

                  if (nID >= 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).iReleasesToCivID > 0) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).iReleasesToCivID != nCivID) {
                           return this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).iReleasesToCivID;
                        }

                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).iReleasesToCivID = -1;
                        PeaceTreaty_Demands var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                        var35.iVictoryPointsLeft += this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).getScoreValue();
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).removeReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                        for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.size(); ++k) {
                           if (!this.drawProvinceOwners
                                 .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                              .isToTake) {
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getCivID();
                           } else {
                              if (this.drawProvinceOwners
                                       .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                    .isTaken
                                 > 0) {
                                 if (this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken
                                    != this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iCivID) {
                                    for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Defenders
                                             .get(u)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .isTaken) {
                                          var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(u);
                                          var35.iVictoryPointsLeft += this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iProvinceValue;
                                       }
                                    }

                                    for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Aggressors
                                             .get(u)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .isTaken) {
                                          var35 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u);
                                          var35.iVictoryPointsLeft += this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iProvinceValue;
                                       }
                                    }

                                    var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                                    var35.iVictoryPointsLeft -= this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                                 }
                              } else {
                                 var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                                 var35.iVictoryPointsLeft -= this.drawProvinceOwners
                                       .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                    .iProvinceValue;
                              }

                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = -1;
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getCivID();
                           }
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).getScoreValue()
                        > this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iVictoryPointsLeft) {
                        return 0;
                     }

                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).iReleasesToCivID = nCivID;
                     PeaceTreaty_Demands var32 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                     var32.iVictoryPointsLeft -= this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).getScoreValue();
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).addReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                     for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.size(); ++k) {
                        if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isToTake
                           )
                         {
                           if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken
                              > 0) {
                              for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++u) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Defenders
                                       .get(i)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken) {
                                    var32 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
                                    var32.iVictoryPointsLeft += this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                                 }
                              }

                              for(int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++u) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Aggressors
                                       .get(i)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken) {
                                    var32 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
                                    var32.iVictoryPointsLeft += this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                                 }
                              }

                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = -1;
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getTrueOwnerOfProvince();
                           }

                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = nCivID;
                        }

                        this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = nReleaseCivID;
                     }

                     return nCivID;
                  }

                  i = this.peaceTreatyGameData.lCivsDemands_Aggressors.size();
                  break;
               }
            }
         }
      }

      return pointsUsedByCivID;
   }

   protected final int getVassalization_Cost(int nCivID) {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iTotalNumOfVicotryPoints * 0.4F, 1.0F);
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iTotalNumOfVicotryPoints * 0.4F, 1.0F);
         }
      }

      return 1;
   }

   protected final int takeVassalize(int nVasslizeCivID, int nCivID, int pointsUsedByCivID) {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nVasslizeCivID) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++j) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID = 0;
                        PeaceTreaty_Demands var11 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                        var11.iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeWillVassalizeCivID(nVasslizeCivID);
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID = 0;
                           PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k);
                           var10000.iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).removeWillVassalizeCivID(nVasslizeCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                     if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                           CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                        }

                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                     }

                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID = nCivID;
                  PeaceTreaty_Demands var10 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                  var10.iVictoryPointsLeft -= this.getVassalization_Cost(nVasslizeCivID);
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addWillVassalizeCivID(nVasslizeCivID);
                  if (CFG.menuManager.getInGame_PeaceTreaty()) {
                     if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                     }

                     CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                  }

                  return nCivID;
               }
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nVasslizeCivID) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++j) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID = 0;
                        PeaceTreaty_Demands var14 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                        var14.iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).removeWillVassalizeCivID(nVasslizeCivID);
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID = 0;
                           PeaceTreaty_Demands var12 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(k);
                           var12.iVictoryPointsLeft += this.getVassalization_Cost(nVasslizeCivID);
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).removeWillVassalizeCivID(nVasslizeCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                     if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                           CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                        }

                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                     }

                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID = nCivID;
                  PeaceTreaty_Demands var13 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                  var13.iVictoryPointsLeft -= this.getVassalization_Cost(nVasslizeCivID);
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).addWillVassalizeCivID(nVasslizeCivID);
                  if (CFG.menuManager.getInGame_PeaceTreaty()) {
                     if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                     }

                     CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                  }

                  return nCivID;
               }
            }
         }
      }

      return pointsUsedByCivID;
   }

   protected final int getWarReparation_Cost(int nCivID) {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iTotalNumOfVicotryPoints * 0.1F, 1.0F);
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
            return (int)Math.max((float)this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iTotalNumOfVicotryPoints * 0.1F, 1.0F);
         }
      }

      return 1;
   }

   protected final int takeWarReparations(int nWarRepartionsFromCivID, int nCivID, int pointsUsedByCivID) {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nWarRepartionsFromCivID) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++j) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID = 0;
                        PeaceTreaty_Demands var11 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                        var11.iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                        return 0;
                     }

                     for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID = 0;
                           PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k);
                           var10000.iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID = nCivID;
                  PeaceTreaty_Demands var10 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                  var10.iVictoryPointsLeft -= this.getWarReparation_Cost(nWarRepartionsFromCivID);
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addWarReparationsFromCivID(nWarRepartionsFromCivID);
                  return nCivID;
               }
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nWarRepartionsFromCivID) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++j) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID = 0;
                        PeaceTreaty_Demands var14 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                        var14.iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                        return 0;
                     }

                     for(int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++k) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID = 0;
                           PeaceTreaty_Demands var12 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(k);
                           var12.iVictoryPointsLeft += this.getWarReparation_Cost(nWarRepartionsFromCivID);
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(k).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID = nCivID;
                  PeaceTreaty_Demands var13 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(j);
                  var13.iVictoryPointsLeft -= this.getWarReparation_Cost(nWarRepartionsFromCivID);
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(j).addWarReparationsFromCivID(nWarRepartionsFromCivID);
                  return nCivID;
               }
            }
         }
      }

      return pointsUsedByCivID;
   }

   protected final boolean takeProvince(int nProvinceID, int nCivID, int pointsUsedByCivID) {
      if (nProvinceID >= 0 && !CFG.game.getProvince(nProvinceID).getSeaProvince() && this.drawProvinceOwners.get(nProvinceID).isToTake) {
         this.iLastTakenID = nProvinceID;
         if (this.drawProvinceOwners.get(nProvinceID).isTaken <= 0) {
            if (this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID)) {
               this.removeProvincesLeftToTake(nProvinceID);
               CFG.game.setActiveProvinceID(-1);
               this.AI_UseVictoryPoints();
               return true;
            }

            CFG.game.setActiveProvinceID(-1);
            this.AI_UseVictoryPoints();
            return false;
         }

         if (this.drawProvinceOwners.get(nProvinceID).iCivID != nCivID) {
            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(nProvinceID);
                  this.addProvincesLeftToTake(nProvinceID);
               }

               PeaceTreaty_Demands var9 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
               var9.iVictoryPointsLeft += this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
            }

            for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(nProvinceID);
                  this.addProvincesLeftToTake(nProvinceID);
               }

               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).isTaken) {
                  PeaceTreaty_Demands var10 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
                  var10.iVictoryPointsLeft += this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
               }
            }

            this.drawProvinceOwners.get(nProvinceID).isTaken = -1;
            if (this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID)) {
               this.removeProvincesLeftToTake(nProvinceID);
               CFG.game.setActiveProvinceID(-1);
               this.AI_UseVictoryPoints();
               return true;
            }

            CFG.game.setActiveProvinceID(-1);
            this.AI_UseVictoryPoints();
            return false;
         }

         for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
               this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(nProvinceID);
               this.addProvincesLeftToTake(nProvinceID);
            }

            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).isTaken) {
               PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
               var10000.iVictoryPointsLeft += this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
               if (CFG.menuManager.getInGame_PeaceTreaty()) {
                  CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
               }
            }
         }

         for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
               this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(nProvinceID);
               this.addProvincesLeftToTake(nProvinceID);
            }

            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).isTaken) {
               PeaceTreaty_Demands var8 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
               var8.iVictoryPointsLeft += this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
               if (CFG.menuManager.getInGame_PeaceTreaty()) {
                  CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
               }
            }
         }

         this.drawProvinceOwners.get(nProvinceID).isTaken = -1;
         this.drawProvinceOwners.get(nProvinceID).iCivID = CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince();
      }

      return false;
   }

   protected final boolean makeDemand_Province(int nProvinceID, int nCivID, int pointsUsedByCivID) {
      return this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID, false);
   }

   protected final boolean makeDemand_Province(int nProvinceID, int nCivID, int pointsUsedByCivID, boolean free_ToTrueOwner) {
      Gdx.app
         .log("AoC", "makeDemand_Province: nCivID: " + CFG.game.getCiv(nCivID).getCivName() + ", nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());
      if (nCivID != pointsUsedByCivID && CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() == nCivID) {
         pointsUsedByCivID = nCivID;
      }

      if (!free_ToTrueOwner) {
         for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
            if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == pointsUsedByCivID) {
                  if (this.drawProvinceOwners.get(nProvinceID).isTaken == pointsUsedByCivID) {
                     break;
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
                     return false;
                  }
               }
            } else if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == pointsUsedByCivID
               && this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
               return false;
            }
         }

         for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
            if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == pointsUsedByCivID) {
                  if (this.drawProvinceOwners.get(nProvinceID).isTaken == pointsUsedByCivID) {
                     break;
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
                     return false;
                  }
               }
            } else if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == pointsUsedByCivID
               && this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
               return false;
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
            this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).addDemandOnProvince(nProvinceID);
         }

         if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
               this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(nProvinceID);
            }
         } else if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == pointsUsedByCivID) {
            PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
            var10000.iVictoryPointsLeft -= this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == nCivID) {
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).addDemandOnProvince(nProvinceID);
         }

         if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
               this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).removeDemandOnProvince(nProvinceID);
            }
         } else if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID == pointsUsedByCivID) {
            PeaceTreaty_Demands var9 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
            var9.iVictoryPointsLeft -= this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
         }
      }

      this.drawProvinceOwners.get(nProvinceID).isTaken = pointsUsedByCivID;
      this.drawProvinceOwners.get(nProvinceID).iCivID = nCivID;
      if (CFG.menuManager.getInGame_PeaceTreaty()) {
         CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
      }

      return true;
   }

   protected final void preparePeaceTreatyToSend(int iFromCivID) {
      for(int i = 0; i < this.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
         for(int j = 0; j < this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.size(); ++j) {
            if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).isTaken < 0) {
               this.makeDemand_Province(
                  this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j),
                  this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID,
                  this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID,
                  true
               );
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
         for(int j = 0; j < this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size(); ++j) {
            if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).isTaken < 0) {
               this.makeDemand_Province(
                  this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j),
                  this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
                  this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
                  true
               );
            }
         }
      }

      boolean updateData = false;

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         if (!CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getControlledByPlayer()) {
            for(int j = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.size() - 1; j >= 0; --j) {
               int numOfConnections_Own = 0;
               int numOfConnections_Enemies = 0;

               for(int k = 0;
                  k < CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvincesSize();
                  ++k
               ) {
                  if (this.drawProvinceOwners
                           .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvinces(k))
                        .iCivID
                     == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID) {
                     ++numOfConnections_Own;
                  } else if (this.drawProvinceOwners
                              .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvinces(k))
                           .iCivID
                        < 0
                     || CFG.game
                        .getCivsAtWar(
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID,
                           this.drawProvinceOwners
                                 .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvinces(k))
                              .iCivID
                        )) {
                     ++numOfConnections_Enemies;
                  }
               }

               if (numOfConnections_Own <= 0
                  && numOfConnections_Enemies > 0
                  && (
                     CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvincesSize() > 2
                        || CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringSeaProvincesSize() <= 0
                  )) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).isTaken = -1;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).iCivID = CFG.game
                        .getProvince(i)
                        .getCivID()
                     * -1;
                  this.peaceTreatyGameData
                     .lCivsDemands_Defenders
                     .get(i)
                     .removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j));
                  updateData = true;
               }
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         if (!CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID).getControlledByPlayer()) {
            for(int j = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.size() - 1; j >= 0; --j) {
               int numOfConnections_Own = 0;
               int numOfConnections_Enemies = 0;

               for(int k = 0;
                  k < CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getNeighboringProvincesSize();
                  ++k
               ) {
                  if (this.drawProvinceOwners
                           .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getNeighboringProvinces(k))
                        .iCivID
                     == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID) {
                     ++numOfConnections_Own;
                  } else if (this.drawProvinceOwners
                              .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getNeighboringProvinces(k))
                           .iCivID
                        < 0
                     || CFG.game
                        .getCivsAtWar(
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID,
                           this.drawProvinceOwners
                                 .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getNeighboringProvinces(k))
                              .iCivID
                        )) {
                     ++numOfConnections_Enemies;
                  }
               }

               if (numOfConnections_Own <= 0
                  && numOfConnections_Enemies > 0
                  && (
                     CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getNeighboringProvincesSize() > 2
                        || CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getNeighboringSeaProvincesSize() <= 0
                  )) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).isTaken = -1;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).iCivID = CFG.game
                        .getProvince(i)
                        .getCivID()
                     * -1;
                  this.peaceTreatyGameData
                     .lCivsDemands_Aggressors
                     .get(i)
                     .removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j));
                  updateData = true;
               }
            }
         }
      }

      if (updateData) {
         for(int i = 0; i < this.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.size(); ++j) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j)).isTaken < 0) {
                  this.makeDemand_Province(
                     this.peaceTreatyGameData.lCivsData_Defenders.get(i).lProvincesLost.get(j),
                     this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID,
                     this.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID,
                     true
                  );
               }
            }
         }

         for(int i = 0; i < this.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            for(int j = 0; j < this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size(); ++j) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j)).isTaken < 0) {
                  this.makeDemand_Province(
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(j),
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
                     true
                  );
               }
            }
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ++i) {
         for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.size(); ++j) {
            for(int k = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).lProvinces.size() - 1; k >= 0; --k) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).lProvinces.get(k)).iCivID
                  != this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).iCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).lProvinces.remove(k);
               }
            }
         }

         if (iFromCivID == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID) {
            this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted = true;
         }
      }

      for(int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); ++i) {
         for(int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.size(); ++j) {
            for(int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).lProvinces.size() - 1; k >= 0; --k) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).lProvinces.get(k)).iCivID
                  != this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).iCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).lProvinces.remove(k);
               }
            }
         }

         if (iFromCivID == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID) {
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).peaceTreatyAccepted = true;
         }
      }
   }

   protected static int getProposal_Positive(boolean scoreCountDefenders) {
      return 0;
   }

   protected static int getProposal_Negative(boolean scoreCountDefenders) {
      return 0;
   }
}
