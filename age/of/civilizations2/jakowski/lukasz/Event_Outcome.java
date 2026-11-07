package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome implements Serializable {
   private static final long serialVersionUID = 0L;

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

   protected String getText() {
      return "";
   }

   protected void setText(String nText) {
   }

   protected boolean updateCivIDAfterRemove(int nRemovedCivID) {
      return false;
   }

   protected void outcomeAction() {
   }

   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
      return tElements;
   }

   protected String getConditionText() {
      return "";
   }

   protected void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CHANGE_OWNER);
   }
}
