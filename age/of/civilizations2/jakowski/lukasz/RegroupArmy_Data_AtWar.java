package age.of.civilizations2.jakowski.lukasz;

class RegroupArmy_Data_AtWar extends RegroupArmy_Data {
   protected RegroupArmy_Data_AtWar(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
   protected boolean continueMovingArmy(int nCivID) {
      if (!CFG.game.getCiv(nCivID).isAtWar()) {
         return false;
      } else {
         return CFG.game.getProvince(this.getFromProvinceID()).getBordersWithEnemy() ? false : super.continueMovingArmy(nCivID);
      }
   }
}
