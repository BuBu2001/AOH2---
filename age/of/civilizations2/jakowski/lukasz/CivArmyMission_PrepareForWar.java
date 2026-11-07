package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class CivArmyMission_PrepareForWar extends CivArmyMission {
   protected int iTurnsRequiredToMoveToFrontLine = 1;

   protected CivArmyMission_PrepareForWar(int nCivID, int fromProvinceID, int toProvinceID, int iArmy, int MISSION_ID) {
      this.iArmy = iArmy;
      this.iProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      this.MISSION_ID = MISSION_ID;
      RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, this.iProvinceID, toProvinceID);
      this.iTurnsRequiredToMoveToFrontLine = tryRegroupArmy.getRouteSize();
      Gdx.app.log("AoC", "CivArmyMission_PrepareForWar -> INIT: " + CFG.game.getCiv(nCivID).getCivName() + ", LEFT: " + this.iTurnsRequiredToMoveToFrontLine);
      RegroupArmy_Data var7 = null;
   }

   @Override
   protected boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
   }

   @Override
   protected boolean action(int nCivID) {
      if (this.iProvinceID != this.toProvinceID) {
         RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, this.iProvinceID, this.toProvinceID);
         if (tryRegroupArmy.getRouteSize() > 0) {
            if (tryRegroupArmy.getRouteSize() == 1) {
               return CFG.gameAction.moveArmy(this.iProvinceID, this.toProvinceID, this.iArmy, nCivID, true, false);
            }

            if (CFG.gameAction.moveArmy(this.iProvinceID, tryRegroupArmy.getRoute(0), this.iArmy, nCivID, true, false)) {
               tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
               tryRegroupArmy.removeRoute(0);
               tryRegroupArmy.setNumOfUnits(this.iArmy);
               CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
               return true;
            }

            return false;
         }
      }

      return true;
   }
}
