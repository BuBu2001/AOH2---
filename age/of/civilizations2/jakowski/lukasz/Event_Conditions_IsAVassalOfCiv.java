package age.of.civilizations2.jakowski.lukasz;

class Event_Conditions_IsAVassalOfCiv extends Event_Conditions {
   protected int iCivID = -1;
   protected int iCivID2 = -1;

   @Override
   protected int getCivID() {
      return this.iCivID;
   }

   @Override
   protected void setCivID(int nCivID) {
      this.iCivID = nCivID;
   }

   @Override
   protected int getCivID2() {
      return this.iCivID2;
   }

   @Override
   protected void setCivID2(int nCivID) {
      this.iCivID2 = nCivID;
   }

   @Override
   protected boolean updateCivIDAfterRemove(int nRemovedCivID) {
      boolean out = false;
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID) {
         --this.iCivID;
      }

      if (this.iCivID2 == nRemovedCivID) {
         this.iCivID2 = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID2) {
         --this.iCivID2;
      }

      return out;
   }

   @Override
   protected boolean outCondition() {
      try {
         return CFG.game.getCiv(this.getCivID2()).getPuppetOfCivID() == this.getCivID();
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("IsAVassalOfCiv")
            + ": "
            + CFG.langManager.get("Lord")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + " - "
            + CFG.game.getCiv(this.getCivID2()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("IsAVassalOfCiv");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_ISVASSAL_OFCIV);
   }
}
