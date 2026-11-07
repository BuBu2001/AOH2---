package age.of.civilizations2.jakowski.lukasz;

class TechnologyManager {
   protected static final float MAX_TECHNOLOGY_LEVEL = 2.0F;
   protected static final int POINTS_PER_LEVEL_BASE = 500;
   protected static final float POINTS_PER_LEVEL_PROVINCES = 52.45487F;
   protected static float fAverageTechLevel = 0.5F;
   protected static final float MIGRATE_RESEARCH_PROGRESS = 0.1F;
   protected static final float MIGRATE_RESEARCH_PROGRESS_NOT_CAPITAL = 0.05F;

   protected static final void updateAverageTechLevel() {
      fAverageTechLevel = 1.0F;
      int numOfCivs = 1;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            fAverageTechLevel += CFG.game.getCiv(i).getTechnologyLevel();
            ++numOfCivs;
         }
      }

      fAverageTechLevel /= (float)numOfCivs;
   }

   protected static final int getResearch_NextLevel(int nCivID) {
      return (int)(
         Math.pow(
               (double)(
                  500.0F
                     + 52.45487F * (float)CFG.game.getCiv(nCivID).getNumOfProvinces()
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.719174F
                        * (CFG.game.getCiv(nCivID).getTechnologyLevel() + 0.01F + 0.287643F * CFG.game.getCiv(nCivID).getTechnologyLevel())
               ),
               (double)(
                  0.7462489F
                     + 0.284985F * CFG.game.getCiv(nCivID).getTechnologyLevel()
                     + (CFG.game.getCiv(nCivID).getTechnologyLevel() * 1.086845F + 0.01F) / 8.618698F
               )
            )
            * (double)CFG.game.getCiv(nCivID).getTechnologyLevel()
            / (double)fAverageTechLevel
            * (double)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).RESEARCH_COST
      );
   }

   protected static final void updateResearch() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
            && CFG.game.getCiv(i).getTechnologyLevel() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CIVILIZE_TECH_LEVEL) {
            CFG.game
               .getCiv(i)
               .addResearchProgress(
                  (float)getResearch_NextLevel(i)
                     * (0.0011254F + (float)CFG.oR.nextInt(545) / 10000.0F)
                     * (
                        0.325F
                           + 0.675F
                              * (
                                 1.0F
                                    - CFG.game.getCiv(i).getTechnologyLevel()
                                       / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CIVILIZE_TECH_LEVEL
                              )
                     )
               );
         }

         updateResearch(i);
      }

      updateAverageTechLevel();
   }

   protected static final void updateResearch(int nCivID) {
      updateReseatchOfCiv(nCivID, 0);
      updateAverageTechLevel();
   }

   private static final void updateReseatchOfCiv(int i, int iterration) {
      if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(i).getResearchProgress() > (float)getResearch_NextLevel(i)) {
         CFG.game.getCiv(i).setResearchProgress(CFG.game.getCiv(i).getResearchProgress() - (float)getResearch_NextLevel(i));
         CFG.game.getCiv(i).setTechnologyLevel_INT(CFG.game.getCiv(i).getTechnologyLevel_INT() + 1);
         CFG.game.getCiv(i).setGoldenAge_Science(CFG.game.getCiv(i).getGoldenAge_Science() + 250);
         CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TechResearched(i));
         if (CFG.game.getCiv(i).getControlledByPlayer()) {
            int tID = CFG.game.getPlayerID_ByCivID(i);
            if (tID >= 0 && tID < CFG.game.getPlayersSize()) {
               CFG.game.getPlayer(tID).buildMetProvinces_BasedOnDistance();
            }
         }

         if (iterration++ < 5) {
            updateReseatchOfCiv(i, iterration);
         }
      }
   }

   protected static final void updateCivs_ResearchProgress() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            CFG.game
               .getCiv(i)
               .addResearchProgress(
                  CFG.game_NextTurnUpdate.getResearchSpendings(i, CFG.game.getCiv(i).iBudget) * (1.0F + CFG.game.getCiv(i).getModifier_Research())
               );

            for(int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
               if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getLevelOfLibrary() > 0
                  && !CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).isOccupied()) {
                  CFG.game
                     .getCiv(i)
                     .addResearchProgress(
                        (float)CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getPopulationData().getPopulation()
                           / (float)BuildingsManager.getLibrary_ResearchPerPopulation(
                              CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getLevelOfLibrary()
                           )
                           * CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getProvinceStability()
                     );
               }
            }
         }
      }

      updateResearch();
   }

   protected static final void updateCivs_ResearchProgress_Migrate(int nCivID, int nProvinceID) {
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
         CFG.game.getCiv(nCivID).addResearchProgress((float)getResearch_NextLevel(nCivID) * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.1F : 0.05F));
      }

      updateResearch(nCivID);
   }
}
