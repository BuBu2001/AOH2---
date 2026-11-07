package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class CivArmyMission implements Serializable {
   private static final long serialVersionUID = 0L;
   protected CivArmyMission_Type MISSION_TYPE = CivArmyMission_Type.PREAPARE_FOR_WAR;
   protected int iProvinceID;
   protected int toProvinceID;
   protected int iArmy = 0;
   protected int MISSION_ID = 0;
   protected int iObsolate = 10;
   protected int TURN_ID = 0;

   protected boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
   }

   protected boolean action(int nCivID) {
      return true;
   }

   protected boolean prepareData() {
      return true;
   }

   protected void onRemove() {
   }
}
