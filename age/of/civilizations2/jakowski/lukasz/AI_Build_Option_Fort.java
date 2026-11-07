package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Fort extends AI_Build_Option {
   @Override
   protected float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_FORT
         * (
            1.0F
               - (float)(CFG.game.getCiv(nCivID).iNumOf_Forts / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getFort_MaxLevel(), 1))
         );
   }

   @Override
   protected AI_Build getData(int nCivID) {
      return new AI_Build_Fort(nCivID, this.getMoney(nCivID));
   }
}
