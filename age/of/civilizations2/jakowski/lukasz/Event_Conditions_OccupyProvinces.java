package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class Event_Conditions_OccupyProvinces extends Event_Conditions {
   protected int iCivID = -1;
   protected List<Integer> lProvinces = new ArrayList<>();
   protected int iPercentage = 100;

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
      return this.iPercentage;
   }

   @Override
   protected void setValue(int nValue) {
      this.iPercentage = nValue;
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
      int numOfControlledProvinces = 0;

      try {
         for(int i = 0; i < this.lProvinces.size(); ++i) {
            if (CFG.game.getProvince(this.lProvinces.get(i)).getCivID() == this.getCivID() && CFG.game.getProvince(this.lProvinces.get(i)).isOccupied()) {
               ++numOfControlledProvinces;
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }

      Gdx.app.log("AoC", "EOccupyProvinces: numOfControlledProvinces:" + numOfControlledProvinces);
      Gdx.app.log("AoC", "EOccupyProvinces: getValue:" + this.getValue());
      Gdx.app.log("AoC", "EOccupyProvinces: lProvinces.size:" + this.lProvinces.size());
      if ((float)numOfControlledProvinces / (float)this.lProvinces.size() < (float)this.getValue() / 100.0F) {
         Gdx.app.log("AoC", "EOccupyProvinces: return:false");
         return false;
      } else {
         Gdx.app.log("AoC", "EOccupyProvinces: return:true");
         return true;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("OccupiedProvinces") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("OccupiedProvinces");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_OCCUPIED_PROVINCES);
   }
}
