package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Scenario_GameData_Diplomacy_Data implements Serializable {
   private static final long serialVersionUID = 0L;
   private int iCivA;
   private int iCivB;
   private int iValue;

   protected Scenario_GameData_Diplomacy_Data(int iCivA, int iCivB, int iValue) {
      this.iCivA = iCivA;
      this.iCivB = iCivB;
      this.iValue = iValue;
   }

   protected final int getCivA() {
      return this.iCivA;
   }

   protected final void setCivA(int iCivA) {
      this.iCivA = iCivA;
   }

   protected final int getCivB() {
      return this.iCivB;
   }

   protected final void setCivB(int iCivB) {
      this.iCivB = iCivB;
   }

   protected final int getValue() {
      return this.iValue;
   }

   protected final void setValue(int iValue) {
      this.iValue = iValue;
   }
}
