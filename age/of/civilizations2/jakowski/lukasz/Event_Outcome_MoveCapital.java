package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Event_Outcome_MoveCapital extends Event_Outcome {
   protected int iCivID = -1;
   protected int iValue = -1;

   @Override
   protected int getCivID() {
      return this.iCivID;
   }

   @Override
   protected void setCivID(int nCivID) {
      this.iCivID = nCivID;
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
   protected int getValue() {
      return this.iValue;
   }

   @Override
   protected void setValue(int nValue) {
      this.iValue = nValue;
   }

   @Override
   protected void outcomeAction() {
      if (this.canMakeAction()) {
         if (CFG.game.getCiv(this.getCivID()).getCapitalProvinceID() >= 0) {
            CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).setIsCapital(false);
            CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).updateDrawArmy();

            try {
               CFG.game
                  .getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID())
                  .getCity(0)
                  .setCityLevel(CFG.getEditorCityLevel(1));
            } catch (IndexOutOfBoundsException var3) {
            }
         }

         CFG.game.getProvince(this.getValue()).setIsCapital(true);
         CFG.game.getCiv(this.getCivID()).setCapitalProvinceID(this.getValue());
         CFG.game.getCiv(this.getCivID()).setCoreCapitalProvinceID(this.getValue());

         try {
            CFG.game
               .getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID())
               .getCity(0)
               .setCityLevel(CFG.getEditorCityLevel(0));
         } catch (IndexOutOfBoundsException var2) {
         }

         CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).updateDrawArmy();
         CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).setDrawCities(true);
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         if (this.canMakeAction()) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoveCapital") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "" + (CFG.game.getProvince(this.getValue()).getName().length() == 0 ? this.getValue() : CFG.game.getProvince(this.getValue()).getName())
               )
            );
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }

         return tElements;
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }

      return new ArrayList<>();
   }

   protected boolean canMakeAction() {
      try {
         return this.getCivID() >= 0
            && this.getCivID() < CFG.game.getCivsSize()
            && this.getValue() >= 0
            && this.getValue() < CFG.game.getProvincesSize()
            && !CFG.game.getProvince(this.getValue()).getSeaProvince()
            && CFG.game.getProvince(this.getValue()).getWasteland() < 0
            && CFG.game.getProvince(this.getValue()).getCivID() == this.getCivID();
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("MoveCapital") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("MoveCapital");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_MOVECAPITAL);
   }
}
