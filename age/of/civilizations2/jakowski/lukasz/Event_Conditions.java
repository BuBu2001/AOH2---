package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Event_Conditions implements Serializable {
   private static final long serialVersionUID = 0L;
   protected Event_Type conditionType = Event_Type.AND;

   protected int getCivID() {
      return -1;
   }

   protected void setCivID(int nCivID) {
   }

   protected int getCivID2() {
      return -1;
   }

   protected void setCivID2(int nCivID) {
   }

   protected int getValue() {
      return -1;
   }

   protected void setValue(int nValue) {
   }

   protected List<Integer> getProvinces() {
      return new ArrayList<>();
   }

   protected void setProvinces(List<Integer> nProvinces) {
   }

   protected boolean updateCivIDAfterRemove(int nRemovedCivID) {
      return false;
   }

   protected String getText() {
      return "";
   }

   protected void setText(String nText) {
   }

   protected boolean outCondition() {
      return false;
   }

   protected String getConditionText() {
      return "";
   }

   protected void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_CIVEXIST);
   }
}
