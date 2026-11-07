package age.of.civilizations2.jakowski.lukasz;

class Event_Conditions_EventChance extends Event_Conditions {
   protected int iValue = 40;

   @Override
   protected int getValue() {
      return this.iValue;
   }

   @Override
   protected void setValue(int iValue) {
      this.iValue = iValue;
   }

   @Override
   protected boolean outCondition() {
      return CFG.oR.nextInt(100) <= this.getValue();
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("EventChance") + ": " + this.getValue() + "%";
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("EventChance");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_EVENTCHANCE);
   }
}
