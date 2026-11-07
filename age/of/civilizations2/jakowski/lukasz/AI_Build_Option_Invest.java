package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Invest extends AI_Build_Option {
   @Override
   protected float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST
         * (1.0F - (float)CFG.game.getCiv(nCivID).getInvestsSize() / (float)CFG.game.getCiv(nCivID).getNumOfProvinces());
   }

   @Override
   protected AI_Build getData(int nCivID) {
      return new AI_Build_Invest(nCivID, this.getMoney(nCivID));
   }
}
