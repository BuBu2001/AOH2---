package age.of.civilizations2.jakowski.lukasz;

class RegroupArmy_Data_ExpandCheck extends RegroupArmy_Data {
   protected RegroupArmy_Data_ExpandCheck(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
   protected boolean continueMovingArmy(int nCivID) {
      return CFG.game.getProvince(this.getToProvinceID()).getCivID() != 0 && CFG.game.getProvince(this.getToProvinceID()).getCivID() != nCivID
         ? false
         : super.continueMovingArmy(nCivID);
   }
}
