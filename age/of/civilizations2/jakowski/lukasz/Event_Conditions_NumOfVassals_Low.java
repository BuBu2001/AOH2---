package age.of.civilizations2.jakowski.lukasz;

class Event_Conditions_NumOfVassals_Low extends Event_Conditions {
   protected int iCivID = -1;
   protected int iValue = 0;

   @Override
   protected int getCivID() {
      return this.iCivID;
   }

   @Override
   protected void setCivID(int nCivID) {
      this.iCivID = nCivID;
   }

   @Override
   protected int getValue() {
      return this.iValue;
   }

   @Override
   protected void setValue(int nValue) {
      this.iValue = nValue;
   }

   @Override
   protected boolean updateCivIDAfterRemove(int nRemovedCivID) {
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         return true;
      } else {
         if (nRemovedCivID < this.iCivID) {
            --this.iCivID;
         }

         return false;
      }
   }

   @Override
   protected boolean outCondition() {
      try {
         int tNumOfVassals = 0;

         for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getPuppetOfCivID() == this.getCivID() && i != this.getCivID()) {
               ++tNumOfVassals;
            }
         }

         return tNumOfVassals < this.getValue();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("NumberOfVassals") + " < " + this.getValue() + ", " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("NumberOfVassals");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_NUMOFVASSALS_LOW);
   }
}
