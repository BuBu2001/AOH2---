package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option {
   protected float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_FARM
         * (
            1.0F
               - (float)(
                  CFG.game.getCiv(nCivID).iNumOf_Farms
                     / Math.max(CFG.game.getCiv(nCivID).iNumOf_Farms_ProvincesPossibleToBuild * BuildingsManager.getFarm_MaxLevel(), 1)
               )
         );
   }

   protected AI_Build getData(int nCivID) {
      return new AI_Build_Farm(nCivID, this.getMoney(nCivID));
   }

   protected long getMoney(int nCivID) {
      return CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)
         ? 0L
         : CFG.game.getCiv(nCivID).getMoney() - AI_Style.getMoney_MinReserve(nCivID);
   }
}
