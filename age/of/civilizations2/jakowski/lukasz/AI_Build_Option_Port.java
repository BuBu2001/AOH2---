package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Port extends AI_Build_Option {
   @Override
   protected float getScore(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).getCivRegionsSize() > CFG.game.getCiv(nCivID).iNumOf_Ports) {
            for(int i = 0; i < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i) {
               if (CFG.game.getCiv(nCivID).getCivRegion(i).getSeaAccess()
                  && CFG.game.getCiv(nCivID).getCivRegion(i).getProvincesSize() > 0
                  && !CFG.game.getCiv(nCivID).getCivRegion(i).getSeaAccess_HavePort()) {
                  return 40.0F;
               }
            }
         }
      } catch (NullPointerException var3) {
      }

      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_PORT
         * (1.0F - (float)(CFG.game.getCiv(nCivID).iNumOf_Ports / Math.max(CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size(), 1)));
   }

   @Override
   protected AI_Build getData(int nCivID) {
      return new AI_Build_Port(nCivID, this.getMoney(nCivID));
   }
}
