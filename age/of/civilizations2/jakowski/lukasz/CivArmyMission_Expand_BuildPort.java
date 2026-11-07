package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class CivArmyMission_Expand_BuildPort extends CivArmyMission {
   protected int iBuildPortInProvinceID = -1;

   protected CivArmyMission_Expand_BuildPort(int nCivID, int fromProvinceID, int toProvinceID, int iArmy) {
      this.iArmy = iArmy;
      this.iProvinceID = fromProvinceID;
      this.iBuildPortInProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      this.MISSION_ID = -1;
      this.MISSION_TYPE = CivArmyMission_Type.EXPAND_NETURAL_PROVINCE;
      RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(nCivID, this.iProvinceID, toProvinceID);
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = tryRegroupArmy.getRouteSize() + 6;
   }

   @Override
   protected boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return Game_Calendar.TURN_ID != this.TURN_ID;
   }

   @Override
   protected boolean action(int nCivID) {
      if (this.iProvinceID != this.toProvinceID && CFG.game.getProvince(this.toProvinceID).getCivID() == 0) {
         RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(nCivID, this.iProvinceID, this.toProvinceID);
         if (CFG.game.getProvince(this.iBuildPortInProvinceID).getCivID() != nCivID) {
            return true;
         }

         if (this.action_BuildPort(nCivID, this.iBuildPortInProvinceID) && tryRegroupArmy.getRouteSize() > 0) {
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

   protected final boolean action_BuildPort(int nCivID, int nFromProvinceID) {
      if (CFG.game.getProvince(nFromProvinceID).getLevelOfPort() == 0) {
         if (CFG.game.getProvince(nFromProvinceID).getCivID() == nCivID) {
            if (CFG.game.getCiv(nCivID).isInConstruction(nFromProvinceID, ConstructionType.PORT) == 0) {
               Gdx.app.log("AoC", "move: " + CFG.game.getCiv(nCivID).getMovePoints());
               if (BuildingsManager.constructPort(nFromProvinceID, nCivID)) {
                  ++this.iObsolate;
                  return false;
               } else {
                  this.lockTreasury_Port(nCivID, nFromProvinceID);
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   protected final void lockTreasury_Port(int nCivID, int nProvinceID) {
      int colonizeCost = (int)((float)BuildingsManager.getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID) * 1.05F);
      CFG.game.getCiv(nCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv(nCivID).civGameData.iLockTreasury, colonizeCost);
      if (CFG.game.getCiv(nCivID).iBudget > 0) {
         if (CFG.game.getCiv(nCivID).getMoney() > 0L && CFG.game.getCiv(nCivID).getMoney() < (long)colonizeCost) {
            this.iObsolate = Math.max(this.iObsolate, Math.max(2, (int)Math.ceil((double)((float)CFG.game.getCiv(nCivID).getMoney() / (float)colonizeCost))));
         } else {
            this.iObsolate = Math.max(2, this.iObsolate);
         }
      }
   }
}
