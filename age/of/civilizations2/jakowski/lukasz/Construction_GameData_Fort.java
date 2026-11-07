package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Fort extends Construction_GameData {
   protected Construction_GameData_Fort(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.FORT;
   }

   @Override
   protected void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildFort(this.iProvinceID, nCivID);
      }
   }
}
