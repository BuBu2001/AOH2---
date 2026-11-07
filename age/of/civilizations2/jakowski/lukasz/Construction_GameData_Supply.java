package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Supply extends Construction_GameData {
   protected Construction_GameData_Supply(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.SUPPLY;
   }

   @Override
   protected void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildSupply(this.iProvinceID, nCivID);
      }
   }
}
