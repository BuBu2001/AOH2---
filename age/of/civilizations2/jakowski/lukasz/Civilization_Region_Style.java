package age.of.civilizations2.jakowski.lukasz;

class Civilization_Region_Style {
   protected void updatePB(int nProvinceID, int withProvinceID) {
      CFG.game.getProvince(nProvinceID).getProvinceBordersLandByLand(withProvinceID).updateDrawProvinceBorder_CivilizationRegion();
   }
}
