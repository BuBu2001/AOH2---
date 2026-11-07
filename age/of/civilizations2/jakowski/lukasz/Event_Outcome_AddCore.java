package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_AddCore extends Event_Outcome {
   protected int iCivID = -1;
   protected List<Integer> lProvinces = new ArrayList<>();

   @Override
   protected int getCivID() {
      return this.iCivID;
   }

   @Override
   protected void setCivID(int nCivID) {
      this.iCivID = nCivID;
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
   protected void outcomeAction() {
      if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
         for(int i = 0; i < this.lProvinces.size(); ++i) {
            try {
               if (this.canMakeAction(i)) {
                  CFG.game.getProvince(this.lProvinces.get(i)).getCore().addNewCore(this.getCivID(), Game_Calendar.TURN_ID);
               }
            } catch (IndexOutOfBoundsException var3) {
            }
         }
      }
   }

   protected boolean canMakeAction(int i) {
      try {
         return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince() && CFG.game.getProvince(this.getProvinces().get(i)).getWasteland() < 0;
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();

         for(int i = 0; i < this.getProvinces().size(); ++i) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FabricateClaim") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (
                        CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0
                           ? (Serializable)this.getProvinces().get(i)
                           : CFG.game.getProvince(this.getProvinces().get(i)).getName()
                     )
                     + ": "
               )
            );
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getProvinces().get(i)).getCivID(), CFG.PADDING, 0));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }

         return tElements;
      } catch (IndexOutOfBoundsException var4) {
      } catch (NullPointerException var5) {
      }

      return new ArrayList<>();
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("AddCore") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("AddCore");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_ADDCORE);
   }
}
