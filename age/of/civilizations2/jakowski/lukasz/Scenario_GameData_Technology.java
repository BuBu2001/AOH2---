package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Scenario_GameData_Technology implements Serializable {
   private static final long serialVersionUID = 0L;
   private int iContinentID;
   private int iPercentage;

   protected Scenario_GameData_Technology(int iContinentID, int iPercentage) {
      this.iContinentID = iContinentID;
      this.iPercentage = iPercentage;
   }

   protected final int getPercentage() {
      return this.iPercentage;
   }

   protected final void setPercentage(int iPercentage) {
      this.iPercentage = iPercentage;
   }

   protected final int getContinentID() {
      return this.iContinentID;
   }

   protected final void setContinentID(int iContinentID) {
      this.iContinentID = iContinentID;
   }
}
