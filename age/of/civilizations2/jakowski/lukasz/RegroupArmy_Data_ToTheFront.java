package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class RegroupArmy_Data_ToTheFront extends RegroupArmy_Data {
   protected RegroupArmy_Data_ToTheFront(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
   protected boolean continueMovingArmy(int nCivID) {
      if (CFG.game.getProvince(this.getFromProvinceID()).getBordersWithEnemy()) {
         Gdx.app
            .log(
               "AoC",
               "continueMovingArmy -> ToTheFront -> "
                  + CFG.game.getCiv(nCivID).getCivName()
                  + " -> 0000: ARMY:"
                  + this.getNumOfUnits()
                  + " -> "
                  + CFG.game.getProvince(this.getFromProvinceID()).getName()
            );
         return false;
      } else {
         return super.continueMovingArmy(nCivID);
      }
   }
}
