package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class War_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<WarCiv_GameData> lAggressors = new ArrayList<>();
   private List<WarCiv_GameData> lDefenders = new ArrayList<>();
   private int iWarTurnID = 1;
   protected int iLastFight_InTunrs = 0;
   protected int iLastTurn_ConqueredProvince = 0;
   protected boolean wasAnyAttack = false;
   protected String WAR_TAG;
   protected static final float WAR_SCORE_MODIFIER = 0.7F;
   protected static final float WAR_SCORE_MODIFIER2 = 0.2F;

   protected War_GameData(int nAggressor, int nDefender) {
      this.addAggressor(nAggressor);
      this.addDefender(nDefender);
      this.iWarTurnID = Game_Calendar.TURN_ID;
      this.WAR_TAG = CFG.game.getCiv(nAggressor).getCivTag() + CFG.game.getCiv(nDefender).getCivTag() + CFG.extraRandomTag() + this.iWarTurnID;
   }

   protected final void addAggressor(int nCivID) {
      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.lAggressors.get(i).getCivID() == nCivID) {
            return;
         }
      }

      this.lAggressors.add(new WarCiv_GameData(nCivID));

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (!CFG.game.getCivsAtWar(nCivID, this.getDefenderID(i).getCivID())) {
            CFG.game.setCivRelation_OfCivB(nCivID, this.getDefenderID(i).getCivID(), -100.0F);
            CFG.game.setCivRelation_OfCivB(this.getDefenderID(i).getCivID(), nCivID, -100.0F);
         }
      }

      this.iLastFight_InTunrs = 0;
      this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
   }

   protected final void removeAggressor(int nCivID) {
      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.lAggressors.get(i).getCivID() == nCivID) {
            this.lAggressors.remove(i);
            return;
         }
      }
   }

   protected final void addDefender(int nCivID) {
      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.lDefenders.get(i).getCivID() == nCivID) {
            return;
         }
      }

      this.lDefenders.add(new WarCiv_GameData(nCivID));

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (!CFG.game.getCivsAtWar(nCivID, this.getAggressorID(i).getCivID())) {
            CFG.game.setCivRelation_OfCivB(nCivID, this.getAggressorID(i).getCivID(), -100.0F);
            CFG.game.setCivRelation_OfCivB(this.getAggressorID(i).getCivID(), nCivID, -100.0F);
         }
      }

      this.iLastFight_InTunrs = 0;
      this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
   }

   protected final void removeDefender(int nCivID) {
      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.lDefenders.get(i).getCivID() == nCivID) {
            this.lDefenders.remove(i);
            return;
         }
      }
   }

   protected final void updateAfterUnion(int nCivA, int nCivB) {
      if (this.getIsAggressor(nCivA) && this.getIsAggressor(nCivB)) {
         int nID = this.getAggressorID_ByCivID(nCivA);
         int nID2 = this.getAggressorID_ByCivID(nCivB);
         if (nID >= 0 && nID2 >= 0) {
            this.getAggressorID(nID).addCivilianDeaths(this.getAggressorID(nID2).getCivilianDeaths());
            this.getAggressorID(nID).addCasualties(this.getAggressorID(nID2).getCasualties());
            this.getAggressorID(nID).addEconomicLosses(this.getAggressorID(nID2).getEconomicLosses());
            this.removeAggressor(nCivB);
         }
      } else if (this.getIsDefender(nCivA) && this.getIsDefender(nCivB)) {
         int nID = this.getDefenderID_ByCivID(nCivA);
         int nID2 = this.getDefenderID_ByCivID(nCivB);
         if (nID >= 0 && nID2 >= 0) {
            this.getDefenderID(nID).addCivilianDeaths(this.getDefenderID(nID2).getCivilianDeaths());
            this.getDefenderID(nID).addCasualties(this.getDefenderID(nID2).getCasualties());
            this.getDefenderID(nID).addEconomicLosses(this.getDefenderID(nID2).getEconomicLosses());
            this.removeDefender(nCivB);
         }
      } else if (this.getIsAggressor(nCivB) && !this.getIsDefender(nCivA)) {
         int nID = this.getAggressorID_ByCivID(nCivB);
         if (nID >= 0) {
            this.getAggressorID(nID).setCivID(nCivA);
         }
      } else if (this.getIsDefender(nCivB) && !this.getIsAggressor(nCivA)) {
         int nID = this.getDefenderID_ByCivID(nCivB);
         if (nID >= 0) {
            this.getDefenderID(nID).setCivID(nCivA);
         }
      }
   }

   protected final boolean getIsAggressor(int nCivID) {
      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.getAggressorID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean getIsDefender(int nCivID) {
      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.getDefenderID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final int getWarScore() {
      int tempNumOfProvincesInWar_Aggrersors = 0;
      int tempNumOfProvincesInWar_Defenders = 0;
      int tempControledEnemyProvinces_ByAggrersors = 0;
      int tempControledEnemyProvinces_ByDefenders = 0;

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Aggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
            } else {
               for(int k = 0; k < this.getDefendersSize(); ++k) {
                  if (this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByAggrersors += CFG.game
                        .getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Defenders += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
            } else {
               for(int k = 0; k < this.getAggressorsSize(); ++k) {
                  if (this.getAggressorID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByDefenders += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      int tempAggressorsPerc = 0;
      int tempDefendersPerc = 0;

      try {
         tempAggressorsPerc = (int)(
            (float)tempControledEnemyProvinces_ByAggrersors
               / (float)(tempNumOfProvincesInWar_Defenders + tempControledEnemyProvinces_ByDefenders + tempControledEnemyProvinces_ByAggrersors)
               * 100.0F
         );
      } catch (ArithmeticException var9) {
         tempAggressorsPerc = 0;
      }

      try {
         tempDefendersPerc = (int)(
            (float)tempControledEnemyProvinces_ByDefenders
               / (float)(tempNumOfProvincesInWar_Aggrersors + tempControledEnemyProvinces_ByAggrersors + tempControledEnemyProvinces_ByDefenders)
               * 100.0F
         );
      } catch (ArithmeticException var8) {
         tempDefendersPerc = 0;
      }

      return -tempAggressorsPerc + tempDefendersPerc;
   }

   protected final int getWarScore_PeaceTreaty() {
      int tempNumOfProvincesInWar_Aggrersors = 0;
      int tempNumOfProvincesInWar_Defenders = 0;
      int tempControledEnemyProvinces_ByAggrersors = 0;
      int tempControledEnemyProvinces_ByDefenders = 0;

      for(int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getNumOfProvinces(); ++j) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID
               == CFG.game
                  .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j))
                  .getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Aggrersors += CFG.game
                  .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j));
            } else {
               for(int k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++k) {
                  if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(k).iCivID
                     == CFG.game
                        .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j))
                        .getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByAggrersors += CFG.game
                        .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for(int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getNumOfProvinces(); ++j) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID
               == CFG.game
                  .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getProvinceID(j))
                  .getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Defenders += CFG.game
                  .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getProvinceID(j));
            } else {
               for(int k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++k) {
                  if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(k).iCivID
                     == CFG.game
                        .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getProvinceID(j))
                        .getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByDefenders += CFG.game
                        .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      int tempAggressorsPerc = 0;
      int tempDefendersPerc = 0;

      try {
         tempAggressorsPerc = (int)(
            (float)tempControledEnemyProvinces_ByAggrersors
               / (float)(tempNumOfProvincesInWar_Defenders + tempControledEnemyProvinces_ByDefenders + tempControledEnemyProvinces_ByAggrersors)
               * 100.0F
         );
      } catch (ArithmeticException var9) {
         tempAggressorsPerc = 0;
      }

      try {
         tempDefendersPerc = (int)(
            (float)tempControledEnemyProvinces_ByDefenders
               / (float)(tempNumOfProvincesInWar_Aggrersors + tempControledEnemyProvinces_ByAggrersors + tempControledEnemyProvinces_ByDefenders)
               * 100.0F
         );
      } catch (ArithmeticException var8) {
         tempDefendersPerc = 0;
      }

      return -tempAggressorsPerc + tempDefendersPerc;
   }

   protected final int getWarScore_DefendersInProvinceValue() {
      int outScore = 0;

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               for(int k = 0; k < this.getDefendersSize(); ++k) {
                  if (this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               for(int k = 0; k < this.getAggressorsSize(); ++k) {
                  if (this.getAggressorID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      return outScore;
   }

   protected final int getWarScore_DefendersInProvinceValue(int id) {
      int outScore = 0;

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID()
                  != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
               && this.getDefenderID(id).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
            }
         }
      }

      for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getDefenderID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for(int k = 0; k < this.getAggressorsSize(); ++k) {
               if (this.getAggressorID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j));
                  break;
               }
            }
         }
      }

      return outScore;
   }

   protected final int getWarScore_DefendersInProvinceValue_OnlyPositive(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      int outScore = 0;
      int iMinScore = 0;
      List<War_Points> nPoints = new ArrayList<>();

      for(int k = 0; k < this.getAggressorsSize(); ++k) {
         nPoints.add(new War_Points(this.getAggressorID(k).getCivID()));
      }

      for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getDefenderID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for(int k = 0; k < this.getAggressorsSize(); ++k) {
               if (addAggressor.get(k)
                  && this.getAggressorID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  int nValue = CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j));
                  if (nValue > iMinScore) {
                     iMinScore = nValue;
                  }

                  nPoints.get(k).addPoints(nValue);
                  break;
               }
            }
         }
      }

      int defenderNumOfTrueProvinces = 0;

      for(int i = 0; i < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); ++i) {
         if (CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince()
            == this.getDefenderID(id).getCivID()) {
            ++defenderNumOfTrueProvinces;
         }
      }

      for(int i = nPoints.size() - 1; i >= 0; --i) {
         if (!((float)defenderNumOfTrueProvinces >= (float)nPoints.get(i).getNumOfProvincesTotal() * 2.5F)
            && nPoints.get(i).getNumOfProvincesTotal() > 2
            && nPoints.get(i).iNumOfLostProvinces > 2) {
            float fModifer = 1.0F;

            try {
               if (nPoints.get(i).getNumOfProvincesTotal() == 3) {
                  fModifer = 0.7F
                     + 0.3F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getAggressorID(i).getConqueredProvinces() / (float)Math.max(this.getDefenderID(id).getConqueredProvinces(), 1),
                                 1.0F
                              )
                        );
               } else if (defenderNumOfTrueProvinces < nPoints.get(i).getNumOfProvincesTotal()) {
                  fModifer = 0.2F
                     + 0.1F * (float)(nPoints.get(i).iNumOfLostProvinces / nPoints.get(i).getNumOfProvincesTotal())
                     + 0.2F * (1.0F - (float)defenderNumOfTrueProvinces / (float)nPoints.get(i).getNumOfProvincesTotal())
                     + 0.35F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getAggressorID(i).getConqueredProvinces() / (float)Math.max(this.getDefenderID(id).getConqueredProvinces(), 1),
                                 1.0F
                              )
                        );
               } else {
                  fModifer = 0.2F
                     + 0.1F * (float)(nPoints.get(i).iNumOfLostProvinces / nPoints.get(i).getNumOfProvincesTotal())
                     + 0.35F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getAggressorID(i).getConqueredProvinces() / (float)Math.max(this.getDefenderID(id).getConqueredProvinces(), 1),
                                 1.0F
                              )
                        );
               }
            } catch (IllegalArgumentException var11) {
               fModifer = 0.7F;
               CFG.exceptionStack(var11);
            }

            outScore += (int)Math.max(Math.ceil((double)((float)nPoints.get(i).iPoints * fModifer)), (double)nPoints.get(i).iMinScore);
         } else {
            outScore += nPoints.get(i).iPoints;
         }
      }

      return Math.max(outScore, iMinScore);
   }

   protected final int getWarScore_AggressorsInProvinceValue() {
      int outScore = 0;

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               for(int k = 0; k < this.getDefendersSize(); ++k) {
                  if (this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               for(int k = 0; k < this.getAggressorsSize(); ++k) {
                  if (this.getAggressorID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      return outScore;
   }

   protected final int getWarScore_AggressorsInProvinceValue(int id) {
      int outScore = 0;

      for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getAggressorID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for(int k = 0; k < this.getDefendersSize(); ++k) {
               if (this.getDefenderID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j));
                  break;
               }
            }
         }
      }

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID()
                  != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
               && this.getAggressorID(id).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
            }
         }
      }

      return outScore;
   }

   protected final int getWarScore_AggressorsInProvinceValue_OnlyPositive(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      int outScore = 0;
      int iMinScore = 0;
      List<War_Points> nPoints = new ArrayList<>();

      for(int k = 0; k < this.getDefendersSize(); ++k) {
         nPoints.add(new War_Points(this.getDefenderID(k).getCivID()));
      }

      for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getAggressorID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for(int k = 0; k < this.getDefendersSize(); ++k) {
               if (addDefender.get(k)
                  && this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  int nValue = CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j));
                  if (nValue > iMinScore) {
                     iMinScore = nValue;
                  }

                  nPoints.get(k).addPoints(nValue);
                  break;
               }
            }
         }
      }

      int defenderNumOfTrueProvinces = 0;

      for(int i = 0; i < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); ++i) {
         if (CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince()
            == this.getAggressorID(id).getCivID()) {
            ++defenderNumOfTrueProvinces;
         }
      }

      for(int i = nPoints.size() - 1; i >= 0; --i) {
         if (!((float)defenderNumOfTrueProvinces >= (float)nPoints.get(i).getNumOfProvincesTotal() * 2.5F)
            && nPoints.get(i).getNumOfProvincesTotal() > 2
            && nPoints.get(i).iNumOfLostProvinces > 2) {
            float fModifer = 1.0F;

            try {
               if (nPoints.get(i).getNumOfProvincesTotal() == 3) {
                  fModifer = 0.7F
                     + 0.3F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getDefenderID(i).getConqueredProvinces() / (float)Math.max(this.getAggressorID(id).getConqueredProvinces(), 1),
                                 1.0F
                              )
                        );
               } else if (defenderNumOfTrueProvinces < nPoints.get(i).getNumOfProvincesTotal()) {
                  fModifer = 0.2F
                     + 0.1F * (float)(nPoints.get(i).iNumOfLostProvinces / nPoints.get(i).getNumOfProvincesTotal())
                     + 0.2F * (1.0F - (float)defenderNumOfTrueProvinces / (float)nPoints.get(i).getNumOfProvincesTotal())
                     + 0.35F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getDefenderID(i).getConqueredProvinces() / (float)Math.max(this.getAggressorID(id).getConqueredProvinces(), 1),
                                 1.0F
                              )
                        );
               } else {
                  fModifer = 0.2F
                     + 0.1F * (float)(nPoints.get(i).iNumOfLostProvinces / nPoints.get(i).getNumOfProvincesTotal())
                     + 0.35F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getDefenderID(i).getConqueredProvinces() / (float)Math.max(this.getAggressorID(id).getConqueredProvinces(), 1),
                                 1.0F
                              )
                        );
               }
            } catch (IllegalArgumentException var11) {
               fModifer = 0.7F;
               CFG.exceptionStack(var11);
            }

            outScore += (int)Math.max(Math.ceil((double)((float)nPoints.get(i).iPoints * fModifer)), (double)nPoints.get(i).iMinScore);
         } else {
            outScore += nPoints.get(i).iPoints;
         }
      }

      return Math.max(outScore, iMinScore);
   }

   protected final PeaceTreaty_Civs getDefenders_ProvincesLost(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      PeaceTreaty_Civs outPC = new PeaceTreaty_Civs(this.getDefenderID(id).getCivID());

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (addAggressor.get(i)) {
            for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
               if (this.getAggressorID(i).getCivID()
                     != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
                  && this.getDefenderID(id).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outPC.lProvincesLost.add(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
               }
            }
         }
      }

      return outPC;
   }

   protected final PeaceTreaty_Civs getAggressors_ProvincesLost(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      PeaceTreaty_Civs outPC = new PeaceTreaty_Civs(this.getAggressorID(id).getCivID());

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (addDefender.get(i)) {
            for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
               if (this.getDefenderID(i).getCivID()
                     != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
                  && this.getAggressorID(id).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outPC.lProvincesLost.add(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
               }
            }
         }
      }

      return outPC;
   }

   protected final int getProvinces_Aggressor_Own(int i) {
      int out = 0;

      for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getAggressorID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            ++out;
         }
      }

      for(int k = 0; k < this.getDefendersSize(); ++k) {
         for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(k).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getAggressorID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(k).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               ++out;
            }
         }
      }

      return out;
   }

   protected final int getProvinces_Aggressor_OwnTotal(int i) {
      int out = 0;

      for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getAggressorID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            ++out;
         } else {
            for(int k = 0; k < this.getDefendersSize(); ++k) {
               if (this.getDefenderID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  ++out;
                  break;
               }
            }
         }
      }

      return out;
   }

   protected final int getProvinces_Defender_Own(int i) {
      int out = 0;

      for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getDefenderID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            ++out;
         }
      }

      for(int k = 0; k < this.getAggressorsSize(); ++k) {
         for(int j = 0; j < CFG.game.getCiv(this.getAggressorID(k).getCivID()).getNumOfProvinces(); ++j) {
            if (this.getDefenderID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(k).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               ++out;
            }
         }
      }

      return out;
   }

   protected final int getProvinces_Defender_OwnTotal(int i) {
      int out = 0;

      for(int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); ++j) {
         if (this.getDefenderID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            ++out;
         } else {
            for(int k = 0; k < this.getAggressorsSize(); ++k) {
               if (this.getAggressorID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  ++out;
                  break;
               }
            }
         }
      }

      return out;
   }

   protected final WarCiv_GameData getAggressorID(int i) {
      return this.lAggressors.get(i);
   }

   protected final int getAggressorID_ByCivID(int nCivID) {
      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.getAggressorID(i).getCivID() == nCivID) {
            return i;
         }
      }

      return -1;
   }

   protected final boolean getIsInAggressors(int nCivID) {
      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.getAggressorID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final int getAggressorsSize() {
      return this.lAggressors.size();
   }

   protected final WarCiv_GameData getDefenderID(int i) {
      return this.lDefenders.get(i);
   }

   protected final int getDefenderID_ByCivID(int nCivID) {
      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.getDefenderID(i).getCivID() == nCivID) {
            return i;
         }
      }

      return -1;
   }

   protected final boolean getIsInDefenders(int nCivID) {
      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.getDefenderID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final int getDefendersSize() {
      return this.lDefenders.size();
   }

   protected final int getParticipation_DefenderID(int nID) {
      int out = 0;

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         out += this.getDefenderID(i).getCasualties();
      }

      return out == 0
         ? 100
         : (int)(
            nID == 0
               ? Math.ceil((double)((float)this.getDefenderID(nID).getCasualties() / (float)out * 100.0F))
               : Math.floor((double)((float)this.getDefenderID(nID).getCasualties() / (float)out * 100.0F))
         );
   }

   protected final int getParticipation_AggressorID(int nID) {
      int out = 0;

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         out += this.getAggressorID(i).getCasualties();
      }

      return out == 0
         ? 100
         : (int)(
            nID == 0
               ? Math.ceil((double)((float)this.getAggressorID(nID).getCasualties() / (float)out * 100.0F))
               : Math.floor((double)((float)this.getAggressorID(nID).getCasualties() / (float)out * 100.0F))
         );
   }

   protected final void addConqueredProvinces(int iCivID) {
      this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.getDefenderID(i).getCivID() == iCivID) {
            this.getDefenderID(i).addConqueredProvinces();
            return;
         }
      }

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.getAggressorID(i).getCivID() == iCivID) {
            this.getAggressorID(i).addConqueredProvinces();
            return;
         }
      }
   }

   protected final void addCasualties(int iCivID, int iCasualties) {
      this.iLastFight_InTunrs = 0;
      this.wasAnyAttack = true;

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.getDefenderID(i).getCivID() == iCivID) {
            this.getDefenderID(i).addCasualties(iCasualties);
            return;
         }
      }

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.getAggressorID(i).getCivID() == iCivID) {
            this.getAggressorID(i).addCasualties(iCasualties);
            return;
         }
      }
   }

   protected final void addCivilianEconomicLosses(int iCivID, int iCivilianDeaths, int iEconomicLosses) {
      this.iLastFight_InTunrs = 0;

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         if (this.getDefenderID(i).getCivID() == iCivID) {
            this.getDefenderID(i).addCivilianDeaths(iCivilianDeaths);
            this.getDefenderID(i).addEconomicLosses(iEconomicLosses);
            return;
         }
      }

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         if (this.getAggressorID(i).getCivID() == iCivID) {
            this.getAggressorID(i).addCivilianDeaths(iCivilianDeaths);
            this.getAggressorID(i).addEconomicLosses(iEconomicLosses);
            return;
         }
      }
   }

   protected final int getCasualties_Defenders() {
      int out = 0;

      for(int i = 0; i < this.getDefendersSize(); ++i) {
         out += this.getDefenderID(i).getCasualties();
         out += this.getDefenderID(i).getCivilianDeaths();
      }

      return out;
   }

   protected final int getCasualties_Aggressors() {
      int out = 0;

      for(int i = 0; i < this.getAggressorsSize(); ++i) {
         out += this.getAggressorID(i).getCasualties();
         out += this.getAggressorID(i).getCivilianDeaths();
      }

      return out;
   }

   protected final int getWarTurnID() {
      return this.iWarTurnID;
   }

   protected final void setWarTurnID(int iWarTurnID) {
      this.iWarTurnID = iWarTurnID;
   }
}
