package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Event_Outcome_TriggerAnotherEvent extends Event_Outcome {
   protected String sText = "";

   @Override
   protected String getText() {
      return this.sText;
   }

   @Override
   protected void setText(String nText) {
      this.sText = nText;
   }

   @Override
   protected void outcomeAction() {
      if (this.canMakeAction()) {
         CFG.eventsManager.runEvent_Tag(this.getText());
      }
   }

   protected boolean canMakeAction() {
      return !this.sText.equals("");
   }

   @Override
   protected String getConditionText() {
      try {
         String tempName = "";

         for(int i = 0; i < CFG.eventsManager.getEventsSize(); ++i) {
            if (CFG.eventsManager.getEvent(i).getEventTag().equals(this.getText())) {
               tempName = CFG.eventsManager.getEvent(i).getEventName();
               break;
            }
         }

         return CFG.langManager.get("TriggerAnotherEvent") + ": " + tempName + "[" + this.getText() + "]";
      } catch (IndexOutOfBoundsException var3) {
         return CFG.langManager.get("TriggerAnotherEvent");
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      return new ArrayList<>();
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_TRIGGERANOTHEREVENT);
   }
}
