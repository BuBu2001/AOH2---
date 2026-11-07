package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Invest_Development extends AI_Build_Option {
   @Override
   protected float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT
         * (1.0F - (float)(CFG.game.getCiv(nCivID).getInvestsSize_Development() * 2) / (float)CFG.game.getCiv(nCivID).getNumOfProvinces())
         * (0.65F + 0.45F * (1.0F - CFG.game.getCiv(nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel()));
   }

   @Override
   protected AI_Build getData(int nCivID) {
      return new AI_Build_Invest_Development(nCivID, this.getMoney(nCivID));
   }
}
