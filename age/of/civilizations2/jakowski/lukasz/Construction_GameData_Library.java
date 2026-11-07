package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Library extends Construction_GameData {
   protected Construction_GameData_Library(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.LIBRARY;
   }

   @Override
   protected void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildLibrary(this.iProvinceID, nCivID);
      }
   }
}
