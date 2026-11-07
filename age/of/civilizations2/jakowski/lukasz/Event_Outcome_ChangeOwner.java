package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Event_Outcome_ChangeOwner extends Event_Outcome {
   protected int iCivID = -1;
   protected int iCivID_ControlledBy = -1;
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
   protected int getCivID2() {
      return this.iCivID_ControlledBy;
   }

   @Override
   protected void setCivID2(int nCivID) {
      this.iCivID_ControlledBy = nCivID;
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
      boolean out = false;
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID) {
         --this.iCivID;
      }

      if (this.iCivID_ControlledBy == nRemovedCivID) {
         this.iCivID_ControlledBy = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID_ControlledBy) {
         --this.iCivID;
      }

      return out;
   }

   @Override
   protected void outcomeAction() {
      if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
         for(int i = 0; i < this.lProvinces.size(); ++i) {
            try {
               if (this.canMakeAction(i)) {
                  CFG.game.getProvince(this.lProvinces.get(i)).setCivID(this.getCivID(), false);
                  CFG.game.getProvince(this.lProvinces.get(i)).setTrueOwnerOfProvince(this.getCivID());
               }
            } catch (IndexOutOfBoundsException var3) {
               CFG.exceptionStack(var3);
            }
         }
      }

      CFG.gameAction.updateCivsHappiness(this.getCivID());
      if (this.getCivID2() > 0) {
         CFG.gameAction.updateCivsHappiness(this.getCivID2());
      }

      if (CFG.game.getCiv(this.getCivID()).getCapitalProvinceID() < 0
         || CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).getCivID() != this.getCivID()) {
         CFG.game.moveCapitalToTheLargestCity(this.getCivID());
      }

      CFG.game.buildCivilizationRegions(this.getCivID());
      if (this.getCivID2() > 0) {
         CFG.game.buildCivilizationRegions(this.getCivID2());
      }
   }

   protected boolean canMakeAction(int i) {
      try {
         return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince()
            && CFG.game.getProvince(this.getProvinces().get(i)).getWasteland() < 0
            && (CFG.game.getProvince(this.getProvinces().get(i)).getCivID() == this.getCivID2() || this.getCivID2() < 0)
            && this.getCivID() != this.getCivID2();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();

      for(int i = 0; i < this.getProvinces().size(); ++i) {
         if (this.canMakeAction(i)) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Annexation") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  ""
                     + (
                        CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0
                           ? (Serializable)this.getProvinces().get(i)
                           : CFG.game.getProvince(this.getProvinces().get(i)).getName()
                     )
               )
            );
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getProvinces().get(i)).getCivID(), CFG.PADDING, 0));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }
      }

      return tElements;
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("Annexation") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Annexation");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CHANGE_OWNER);
   }
}
