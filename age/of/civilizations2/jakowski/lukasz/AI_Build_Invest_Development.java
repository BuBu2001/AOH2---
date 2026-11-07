package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Build_Invest_Development extends AI_Build {
   private List<Integer> lProvincesToInvest = new ArrayList<>();
   private float fReserve = 1.0F;
   private int iMaxPop = 1;

   protected AI_Build_Invest_Development(int nCivID, long nMoney) {
      super(nCivID, nMoney);
      this.fReserve = 1.0F
         - (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_RESRVE_RAND / 100.0F
         + (float)CFG.oR.nextInt(CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_RESRVE_RAND) / 100.0F;

      try {
         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability()
                  > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk()
                  <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
               && !CFG.game.getCiv(nCivID).isInvestOrganized_Development(CFG.game.getCiv(nCivID).getProvinceID(i))
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel() / CFG.game.getCiv(nCivID).getTechnologyLevel() < 0.95F) {
               this.lProvincesToInvest.add(CFG.game.getCiv(nCivID).getProvinceID(i));
               this.iMaxDangerLevel = Math.max(this.iMaxDangerLevel, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDangerLevel());
               this.iMaxPop = Math.max(this.iMaxPop, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation());
            }
         }
      } catch (IndexOutOfBoundsException var5) {
         CFG.exceptionStack(var5);
      }
   }

   @Override
   protected boolean build(int nCivID, int iteration, boolean out) {
      int iBestProvinceID = -1;
      float iBestProvinceID_Score = 0.0F;

      for(int i = this.lProvincesToInvest.size() - 1; i >= 0; --i) {
         if (iBestProvinceID < 0) {
            iBestProvinceID = this.lProvincesToInvest.get(i);
            iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
         } else if (this.getProvinceBuildScore(nCivID, this.lProvincesToInvest.get(i)) > iBestProvinceID_Score) {
            iBestProvinceID = this.lProvincesToInvest.get(i);
            iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
         }
      }

      if (iBestProvinceID >= 0) {
         int maxInvestmentGold = (int)Math.min(this.getMoney(nCivID), (long)DiplomacyManager.invest_MaxDevelopment_Gold(iBestProvinceID, nCivID));
         int minNumOfInvests = (int)Math.min(
            Math.floor((double)(CFG.game.getCiv(nCivID).getMovePoints() / 12)), (double)(CFG.game.getCiv(nCivID).getNumOfProvinces() / 10)
         );
         if (iteration == 0
            && minNumOfInvests > 1
            && CFG.oR.nextInt(100) < CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST_SECOND_INVEST_CHANCE) {
            maxInvestmentGold = (int)(
               (float)maxInvestmentGold
                  * (
                     1.0F
                        - (float)CFG.oR.nextInt(Math.max(2, CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST_SECOND_INVEST_MAX_PERC)) / 100.0F
                  )
            );
         }

         if (DiplomacyManager.investDevelopment(iBestProvinceID, nCivID, maxInvestmentGold)) {
            out = true;
            if (this.getMoney(nCivID) > 10L && 8 <= CFG.game.getCiv(nCivID).getMovePoints()) {
               int tSize = 0;

               for(int i = this.lProvincesToInvest.size() - 1; i >= 0; --i) {
                  if (this.lProvincesToInvest.get(i) == iBestProvinceID) {
                     this.lProvincesToInvest.remove(i);
                  } else {
                     ++tSize;
                  }
               }

               if (tSize > 0 && iteration < 4) {
                  return this.build(nCivID, ++iteration, out);
               }
            }
         }
      }

      return out;
   }

   protected float getProvinceBuildScore(int nCivID, int nProvinceID) {
      return (
            (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                  / (float)this.iMaxPop
                  * CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST_POP_SCORE
               + CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                  / CFG.game.getCiv(nCivID).getTechnologyLevel()
                  * CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT_SCORE
               + (
                     1.0F
                        - Math.min(
                           1.0F,
                           (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                              / (float)CFG.game.getProvince(nProvinceID).getEconomy()
                        )
                  )
                  * CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST_POP_ECO_DIFFERENCE_SCORE
         )
         * (
            1.0F
               - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE
               + CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE * CFG.game.getProvince(nProvinceID).getProvinceStability()
         )
         * (
            1.0F
               - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_DANGER_SCORE
                  * (float)CFG.game.getProvince(nProvinceID).getDangerLevel()
                  / (float)this.iMaxDangerLevel
         )
         * (1.0F - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk())
         * (0.575F + 0.425F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() / CFG.game.getCiv(nCivID).getTechnologyLevel());
   }

   @Override
   protected long getMoney(int nCivID) {
      return CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)
         ? 0L
         : (long)((float)CFG.game.getCiv(nCivID).getMoney() - (float)AI_Style.getMoney_MinReserve(nCivID) * this.fReserve);
   }
}
