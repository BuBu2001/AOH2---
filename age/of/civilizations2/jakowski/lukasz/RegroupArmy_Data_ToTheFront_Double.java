package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class RegroupArmy_Data_ToTheFront_Double extends RegroupArmy_Data_ToTheFront {
   protected RegroupArmy_Data_ToTheFront_Double(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
   protected boolean continueMovingArmy(int nCivID) {
      if (CFG.game.getProvince(this.getFromProvinceID()).getBordersWithEnemy()) {
         Gdx.app
            .log(
               "AoC",
               "continueMovingArmy -> ToTheFront_Double -> "
                  + CFG.game.getCiv(nCivID).getCivName()
                  + " -> 0000: ARMY:"
                  + this.getNumOfUnits()
                  + " -> "
                  + CFG.game.getProvince(this.getFromProvinceID()).getName()
            );
         return false;
      } else if (!CFG.game.getProvince(this.getToProvinceID()).getBordersWithEnemy()
         && !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(this.getToProvinceID()).getCivID())) {
         Gdx.app
            .log(
               "AoC",
               "continueMovingArmy -> ToTheFront_Double -> "
                  + CFG.game.getCiv(nCivID).getCivName()
                  + " -> 1111: ARMY:"
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
