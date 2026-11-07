package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Supplies extends AI_Build_Option {
   @Override
   protected float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_SUPPLYLINE
         * (
            1.0F
               - (float)(
                  CFG.game.getCiv(nCivID).iNumOf_SuppliesCamp
                     / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getSupply_MaxLevel(), 1)
               )
         );
   }

   @Override
   protected AI_Build getData(int nCivID) {
      return new AI_Build_Supplies(nCivID, this.getMoney(nCivID));
   }
}
