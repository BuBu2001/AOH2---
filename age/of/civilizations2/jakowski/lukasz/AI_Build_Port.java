package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Build_Port extends AI_Build {
   private List<Integer> lBuildCost = new ArrayList<>();
   private List<Boolean> haveAccessToBasins = new ArrayList<>();

   protected AI_Build_Port(int nCivID, long nMoney) {
      super(nCivID, nMoney);

      try {
         for(int i = 0; i < BuildingsManager.getPort_MaxLevel(); ++i) {
            this.lBuildCost.add(BuildingsManager.getPort_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
            this.lProvincesToBuild.add(new ArrayList<>());
         }

         if (nMoney >= (long)this.lBuildCost.get(0).intValue()) {
            for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability()
                     > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk()
                     <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
                  && BuildingsManager.canBuildPort(CFG.game.getCiv(nCivID).getProvinceID(i))
                  && CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(i), ConstructionType.PORT) == 0) {
                  try {
                     if (nMoney >= (long)this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort()).intValue()) {
                        this.lProvincesToBuild
                           .get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort())
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

      for(int i = 0; i < CFG.map.iNumOfBasins; ++i) {
         this.haveAccessToBasins.add(false);
      }

      for(int i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
            this.haveAccessToBasins
               .set(
                  CFG.game
                     .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                     .getBasinID(),
                  true
               );
         }
      }

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

      if (iBestProvinceID >= 0 && BuildingsManager.constructPort(iBestProvinceID, nCivID)) {
         out = true;
      }

      this.haveAccessToBasins.clear();
      return out;
   }

   protected float getProvinceBuildScore(int nCivID, int nProvinceID) {
      return this.civRegion_HaveBuiltPort(nCivID, nProvinceID)
         ? (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
            / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
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
         : (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (1.0F + CFG.game.getProvince(nProvinceID).getGrowthRate_Population() * 10.0F);
   }

   protected boolean civRegion_HaveBuiltPort(int nCivID, int nProvinceID) {
      try {
         return CFG.game
            .getCiv(CFG.game.getProvince(nProvinceID).getCivID())
            .getCivRegion(CFG.game.getProvince(nProvinceID).getCivRegionID())
            .getSeaAccess_HavePort();
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
         return true;
      } catch (NullPointerException var5) {
         CFG.exceptionStack(var5);
         return true;
      }
   }

   protected boolean haveAccessToBasinWithoutPort(int nProvinceID) {
      boolean out = false;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
         if (!this.haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getBasinID())) {
            out = true;
            break;
         }
      }

      return out;
   }
}
