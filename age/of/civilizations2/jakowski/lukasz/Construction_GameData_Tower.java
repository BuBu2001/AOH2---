package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Tower extends Construction_GameData {
   protected Construction_GameData_Tower(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.TOWER;
   }

   @Override
   protected void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildTower(this.iProvinceID, nCivID);
      }
   }
}
