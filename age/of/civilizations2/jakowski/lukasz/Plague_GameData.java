package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Plague_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName;
   private int iPlagueID_InGame = 0;
   protected List<Integer> lProvinces = new ArrayList<>();
   protected int iProvincesSize = 0;
   protected List<Integer> lProvinces_Active = new ArrayList<>();
   protected float fDeathRate = 0.0F;
   protected int iDurationTurnsLeft = 0;
   private int iDeaths = 0;
   protected int iDurationTurnsLeft_BEGINNING = 0;
   protected float fR;
   protected float fG;
   protected float fB;
   protected float EXPANSION_MODIFIER;
   protected float EXPANSION_SCORE;

   protected Plague_GameData(
      int outbreakProvince,
      String sName,
      float fR,
      float fG,
      float fB,
      int nPlagueID_InGame,
      float fDeathRate,
      int iDurationTurnsLeft,
      float EXPANSION_MODIFIER
   ) {
      this.sName = sName;
      this.iPlagueID_InGame = nPlagueID_InGame;
      this.fR = fR;
      this.fG = fG;
      this.fB = fB;
      this.fDeathRate = fDeathRate;
      this.iDurationTurnsLeft = iDurationTurnsLeft;
      this.iDurationTurnsLeft_BEGINNING = iDurationTurnsLeft;
      this.EXPANSION_MODIFIER = EXPANSION_MODIFIER;
      this.addProvince(outbreakProvince);
   }

   protected final void runDisease() {
      for(int i = this.lProvinces_Active.size() - 1; i >= 0; --i) {
         if (CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague != null
            && CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague.iPlagueID_InGame == this.getPlagueID_InGame()) {
            int nPopBefore = CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulation();
            int nDeaths = (int)Math.ceil(
               (double)(
                  (float)nPopBefore
                     * this.fDeathRate
                     * (1.0F + CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier())
                     * (0.225F + 0.325F * this.getDurationPercLEFT() + 0.55F * (float)CFG.oR.nextInt(100) / 100.0F)
               )
            );

            for(int k = CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getNationalitiesSize() - 1; k >= 0; --k) {
               CFG.game
                  .getProvince(this.lProvinces_Active.get(i))
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getCivID(k),
                     (int)(
                        (double)CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulationID(k)
                           - Math.floor(
                              (double)(
                                 (float)nDeaths
                                    * ((float)CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulationID(k) / (float)nPopBefore)
                              )
                           )
                     )
                  );
            }

            nPopBefore -= CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulation();
            PlagueProvince_GameData var10000 = CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague;
            var10000.iDeaths += nPopBefore;
            Save_Provinces_GameData var6 = CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData;
            var6.iPlaguesDeaths += nPopBefore;
            this.iDeaths += nPopBefore;
            var10000 = CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague;
            var10000.iDurationTurnsLeft -= 0.875F
               - 0.065F * CFG.game.getProvince(this.lProvinces_Active.get(i)).getGrowthRate_Population_WithFarm()
               + (float)CFG.oR.nextInt(825) / 1000.0F;
            if (CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague.iDurationTurnsLeft <= 0.0F) {
               CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.iLastPlagueTurnID = Game_Calendar.TURN_ID;
               CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague = null;
               this.lProvinces_Active.remove(i);
            }
         }
      }

      this.fDeathRate = this.fDeathRate
         * (1.0F + CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier())
         * (0.965F - (float)CFG.oR.nextInt(875) / 10000.0F);
   }

   protected final void spreadDisease() {
      if (this.iDurationTurnsLeft > 0 && this.lProvinces_Active.size() > 0) {
         if ((float)this.lProvinces.size() / (float)CFG.game.getProvincesSize() > 0.35F) {
            return;
         }

         this.EXPANSION_SCORE += (float)this.lProvinces_Active.size() * 0.425F * this.EXPANSION_MODIFIER * (0.1F + 0.9F * this.getDurationPercLEFT());
         this.EXPANSION_MODIFIER *= 0.925F - (float)CFG.oR.nextInt(17850) / 100000.0F;
         if (this.EXPANSION_SCORE >= 1.0F) {
            int nRand = CFG.oR.nextInt((int)this.EXPANSION_SCORE);
            if (nRand > 0) {
               this.EXPANSION_SCORE -= (float)nRand;
               this.spreadDisease(nRand);
            }
         }
      }
   }

   protected final void spreadDisease(int nNumOfProvinces) {
      try {
         nNumOfProvinces = (int)Math.min((float)nNumOfProvinces, Math.max((float)CFG.game.getProvincesSize() * 0.01425F, 16.0F));
         List<Integer> tPossibleSpreadProvinces = new ArrayList<>();
         List<Integer> tPossibleSpreadProvinces_Scores = new ArrayList<>();

         for(int i = 0; i < this.lProvinces_Active.size(); ++i) {
            if (CFG.game.getProvince(this.lProvinces_Active.get(i)).getSeaProvince()) {
               for(int k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                  if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).saveProvinceData.provincePlague
                        == null
                     && Game_Calendar.TURN_ID
                           - CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).saveProvinceData.iLastPlagueTurnID
                        > 38) {
                     tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                  }
               }
            } else {
               for(int k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                  if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).getWasteland() < 0
                     && CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).saveProvinceData.provincePlague
                        == null
                     && Game_Calendar.TURN_ID
                           - CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).saveProvinceData.iLastPlagueTurnID
                        > 38) {
                     tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                  }
               }

               if (CFG.game.getProvince(this.lProvinces_Active.get(i)).getLevelOfPort() > 0
                  || CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize() < 2) {
                  for(int k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvincesSize(); ++k) {
                     if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).getWasteland() < 0
                        && CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).saveProvinceData.provincePlague
                           == null
                        && Game_Calendar.TURN_ID
                              - CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).saveProvinceData.iLastPlagueTurnID
                           > 38) {
                        tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k));
                     }
                  }
               }
            }
         }

         if (tPossibleSpreadProvinces.size() > 0) {
            int tTotalScore = 0;

            for(int i = tPossibleSpreadProvinces.size() - 1; i >= 0; --i) {
               int tempScore = this.getSpreadScore(tPossibleSpreadProvinces.get(i)) * 3 + 1;
               tPossibleSpreadProvinces_Scores.add(tempScore);
               tTotalScore += tempScore;
            }

            if (tTotalScore > 0) {
               while(tPossibleSpreadProvinces_Scores.size() > 0 && nNumOfProvinces > 0) {
                  int tRandScore = CFG.oR.nextInt(tTotalScore);
                  int i = 0;

                  for(int tCurrentScore = 0; i < tPossibleSpreadProvinces_Scores.size(); ++i) {
                     tCurrentScore += tPossibleSpreadProvinces_Scores.get(i);
                     if (tCurrentScore > tRandScore) {
                        this.addProvince(tPossibleSpreadProvinces.get(i));
                        tTotalScore -= tPossibleSpreadProvinces_Scores.get(i);
                        tPossibleSpreadProvinces_Scores.remove(i);
                        tPossibleSpreadProvinces.remove(i);
                        --nNumOfProvinces;
                        break;
                     }
                  }
               }

               if (nNumOfProvinces > 0) {
                  this.spreadDisease(nNumOfProvinces);
               }
            }
         }
      } catch (IndexOutOfBoundsException var8) {
         CFG.exceptionStack(var8);
      } catch (IllegalArgumentException var9) {
         CFG.exceptionStack(var9);
      }
   }

   protected final int getSpreadScore(int nProvinceID) {
      int tempScore = 0;

      for(int k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).saveProvinceData.provincePlague == null) {
            tempScore += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getSeaProvince() ? 1 : 2;
         }
      }

      for(int k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++k) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).saveProvinceData.provincePlague == null) {
            tempScore += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).getSeaProvince() ? 1 : 2;
         }
      }

      return tempScore;
   }

   protected final void addProvince(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return;
         }
      }

      CFG.game.getProvince(nProvinceID).saveProvinceData.iLastPlagueTurnID = Game_Calendar.TURN_ID;
      if (CFG.game.getProvince(nProvinceID).saveProvinceData.provincePlague == null) {
         CFG.game.getProvince(nProvinceID).saveProvinceData.provincePlague = new PlagueProvince_GameData(
            this.iPlagueID_InGame, Game_Calendar.TURN_ID, (float)this.iDurationTurnsLeft * (0.625F + (float)CFG.oR.nextInt(6000) / 10000.0F), 0
         );
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getControlledByPlayer()) {
            CFG.game
               .getCiv(CFG.game.getProvince(nProvinceID).getCivID())
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_Disease(CFG.game.getProvince(nProvinceID).getCivID(), nProvinceID));
         }

         ++CFG.game.getProvince(nProvinceID).saveProvinceData.iNumOfPlaguesTotal;
         this.lProvinces.add(nProvinceID);
         this.lProvinces_Active.add(nProvinceID);
         this.iProvincesSize = this.lProvinces.size();
      }
   }

   protected final String getPlagueName() {
      try {
         return this.sName;
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Plague");
      }
   }

   protected final void setPlagueID_InGame(int iPlagueID_InGame) {
      this.iPlagueID_InGame = iPlagueID_InGame;
   }

   protected final int getPlagueID_InGame() {
      return this.iPlagueID_InGame;
   }

   protected final float getDurationPercLEFT() {
      return (float)this.iDurationTurnsLeft / (float)this.iDurationTurnsLeft_BEGINNING;
   }

   protected final float getDurationPercLEFT(int nNumOfTurns) {
      return (float)nNumOfTurns / (float)this.iDurationTurnsLeft_BEGINNING;
   }

   protected final int getOutbreakProvinceID() {
      try {
         return this.lProvinces.get(0);
      } catch (IndexOutOfBoundsException var2) {
         return -1;
      }
   }

   protected final int getDeaths() {
      return this.iDeaths;
   }

   protected final int getNumOfProvinces_Total() {
      return this.lProvinces.size();
   }

   protected final int getNumOfProvinces_Active() {
      return this.lProvinces_Active.size();
   }
}
