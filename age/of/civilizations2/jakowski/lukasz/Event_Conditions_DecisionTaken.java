package age.of.civilizations2.jakowski.lukasz;

class Event_Conditions_DecisionTaken extends Event_Conditions {
   private String sTag = "";
   private int iCivID = -1;

   @Override
   protected String getText() {
      return this.sTag;
   }

   @Override
   protected void setText(String nText) {
      this.sTag = nText;
   }

   @Override
   protected void setCivID(int nCivID) {
      this.iCivID = nCivID;
   }

   @Override
   protected int getCivID() {
      return this.iCivID;
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
      if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
         return CFG.game.getCiv(this.getCivID()).getEvent_TookDecision(this.getText());
      } else {
         for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getEvent_TookDecision(this.getText())) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         String tName = "";

         try {
            if (CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getText()
                  .length()
               > 0) {
               String[] tData = CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getText()
                  .split("_");
               int tID = Integer.parseInt(tData[1]);

               for(int i = 0; i < CFG.eventsManager.getEventsSize(); ++i) {
                  if (tData[0].equals(CFG.eventsManager.getEvent(i).getEventTag())) {
                     tName = CFG.eventsManager.getEvent(i).lDecisions.get(tID).sTitle;

                     try {
                        tName = tName + " - [" + CFG.game.getCiv(CFG.eventsManager.getEvent(i).getCivID()).getCivName() + "]";
                     } catch (IndexOutOfBoundsException var6) {
                     }
                  }
               }
            }
         } catch (IndexOutOfBoundsException var7) {
         } catch (IllegalArgumentException var8) {
         }

         return CFG.langManager.get("DecisionTaken") + ": " + (tName.length() == 0 ? "NOT FOUND!" : tName);
      } catch (IndexOutOfBoundsException var9) {
         return CFG.langManager.get("DecisionTaken");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_DECISIONTAKEN);
   }
}
