package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Event_Conditions_Farm extends Event_Conditions {
   protected List<Integer> lProvinces = new ArrayList<>();
   protected int iValue = 0;

   @Override
   protected int getValue() {
      return this.iValue;
   }

   @Override
   protected void setValue(int nValue) {
      this.iValue = nValue;
   }

   @Override
   protected List<Integer> getProvinces() {
      return this.lProvinces;
   }

   @Override
   protected void setProvinces(List<Integer> nProvinces) {
      this.lProvinces.clear();

      for(int i = 0; i < nProvinces.size(); ++i) {
         this.lProvinces.add(nProvinces.get(i));
      }
   }

   @Override
   protected boolean outCondition() {
      try {
         if (this.getValue() == 0) {
            for(int i = 0; i < this.getProvinces().size(); ++i) {
               if (CFG.game.getProvince(this.getProvinces().get(i)).getLevelOfFarm() == 0) {
                  return false;
               }
            }
         } else {
            for(int i = 0; i < this.getProvinces().size(); ++i) {
               if (CFG.game.getProvince(this.getProvinces().get(i)).getLevelOfFarm() > 0) {
                  return false;
               }
            }
         }

         return true;
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("Farm") + ": " + (this.getValue() == 0);
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Farm");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_FARM);
   }
}
