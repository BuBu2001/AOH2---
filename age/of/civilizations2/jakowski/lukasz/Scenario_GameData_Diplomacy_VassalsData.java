package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Scenario_GameData_Diplomacy_VassalsData implements Serializable {
   private static final long serialVersionUID = 0L;
   private int iCivID;
   private int iCivLordID;

   protected Scenario_GameData_Diplomacy_VassalsData(int iCivID, int iCivLordID) {
      this.setCivID(iCivID);
      this.setCivLordID(iCivLordID);
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final void setCivID(int iCivID) {
      this.iCivID = iCivID;
   }

   protected final int getCivLordID() {
      return this.iCivLordID;
   }

   protected final void setCivLordID(int iCivLordID) {
      this.iCivLordID = iCivLordID;
   }
}
