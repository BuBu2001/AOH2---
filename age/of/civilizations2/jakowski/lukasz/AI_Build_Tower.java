package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Build_Tower extends AI_Build {
   private List<Integer> lBuildCost = new ArrayList<>();

   protected AI_Build_Tower(int nCivID, long nMoney) {
      super(nCivID, nMoney);

      try {
         for(int i = 0; i < BuildingsManager.getTower_MaxLevel(); ++i) {
            this.lBuildCost.add(BuildingsManager.getTower_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
            this.lProvincesToBuild.add(new ArrayList<>());
         }

         if (nMoney >= (long)this.lBuildCost.get(0).intValue()) {
            for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability()
                     > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk()
                     <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
                  && BuildingsManager.canBuildTower(CFG.game.getCiv(nCivID).getProvinceID(i))
                  && CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(i), ConstructionType.TOWER) == 0) {
                  try {
                     if (nMoney >= (long)this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfWatchTower()).intValue()
                        )
                      {
                        this.lProvincesToBuild
                           .get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfWatchTower())
                           .add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        ++this.iProvincesToBuild_NumOfElements;
                        this.iMaxDangerLevel = Math.max(this.iMaxDangerLevel, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDangerLevel());
                     }
                  } catch (IndexOutOfBoundsException var6) {
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      }
   }

   @Override
   protected int getNumOfAlreadyBuilt(int nCivID) {
      return CFG.game.getCiv(nCivID).iNumOf_Libraries;
   }

   @Override
   protected boolean build(int nCivID, int iteration, boolean out) {
      int iBestProvinceID = -1;
      float iBestProvinceID_Score = 0.0F;

      for(int i = this.lProvincesToBuild.size() - 1; i >= 0; --i) {
         for(int j = this.lProvincesToBuild.get(i).size() - 1; j >= 0; --j) {
            if (iBestProvinceID < 0) {
               iBestProvinceID = this.lProvincesToBuild.get(i).get(j);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            } else if (this.getProvinceBuildScore(nCivID, this.lProvincesToBuild.get(i).get(j)) > iBestProvinceID_Score) {
               iBestProvinceID = this.lProvincesToBuild.get(i).get(j);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            }
         }
      }

      if (iBestProvinceID >= 0 && BuildingsManager.constructTower(iBestProvinceID, nCivID)) {
         out = true;
      }

      return out;
   }

   protected float getProvinceBuildScore(int nCivID, int nProvinceID) {
      return (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
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
         * (1.0F - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk());
   }
}
