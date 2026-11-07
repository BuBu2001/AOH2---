package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Armoury extends Construction_GameData {
   protected Construction_GameData_Armoury(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.ARMOURY;
   }

   @Override
   protected void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildArmoury(this.iProvinceID, nCivID);
      }
   }
}
