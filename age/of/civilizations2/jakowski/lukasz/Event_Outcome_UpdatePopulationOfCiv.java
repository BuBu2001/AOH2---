package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Event_Outcome_UpdatePopulationOfCiv extends Event_Outcome {
   protected int iCivID = -1;
   protected int iValue = 0;

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
      return this.iValue;
   }

   @Override
   protected void setValue(int nValue) {
      this.iValue = nValue;
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
      if (this.canMakeAction()) {
         for(int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); ++i) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
               CFG.game
                  .getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i))
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getCivID(j),
                     CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getPopulationID(j)
                        + (int)(
                           (float)CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getPopulationID(j)
                              * ((float)this.getValue() / 100.0F)
                        )
                  );
            }
         }
      }
   }

   protected boolean canMakeAction() {
      try {
         return this.getCivID() > 0 && this.getCivID() < CFG.game.getCivsSize() && CFG.game.getCiv(this.getCivID()).getNumOfProvinces() > 0;
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("UpdatePopulationOfCiv") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + ", " + this.getValue() + "%";
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdatePopulationOfCiv");
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         if (this.canMakeAction()) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (this.getValue() > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + this.getValue()) + "%",
                  this.getValue() > 0
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : (this.getValue() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
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

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_UPDATEPOPULATION_OFCIV);
   }
}
