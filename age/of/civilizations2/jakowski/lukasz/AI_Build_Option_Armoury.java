package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Armoury extends AI_Build_Option {
   @Override
   protected float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_ARMOURY
         * (
            1.0F
               - (float)(
                  CFG.game.getCiv(nCivID).iNumOf_Armories / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getArmoury_MaxLevel(), 1)
               )
         );
   }

   @Override
   protected AI_Build getData(int nCivID) {
      return new AI_Build_Armoury(nCivID, this.getMoney(nCivID));
   }
}
