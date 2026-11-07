package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Event_Outcome_Wasteland extends Event_Outcome {
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
   protected void outcomeAction() {
      for(int i = 0; i < this.getProvinces().size(); ++i) {
         if (this.canMakeAction(i)) {
            if (this.getValue() == 0) {
               CFG.game.getProvince(this.getProvinces().get(i)).setWasteland(-1);
               CFG.game.getProvince(this.getProvinces().get(i)).setCivID(0, false);
               CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().setPopulationOfCivID(0, 92);
               CFG.game.getProvince(this.getProvinces().get(i)).setEconomy(19);
               CFG.game.buildWastelandLevels();
               if (CFG.game.getActiveProvinceID() == this.getProvinces().get(i)) {
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(this.getProvinces().get(i));
               }
            } else {
               CFG.game.getProvince(this.getProvinces().get(i)).setWasteland(0);
               CFG.game.getProvince(this.getProvinces().get(i)).setCivID(0, false);
               CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().setPopulationOfCivID(0, 92);
               CFG.game.getProvince(this.getProvinces().get(i)).setEconomy(19);
               CFG.game.buildWastelandLevels();
               if (CFG.game.getActiveProvinceID() == this.getProvinces().get(i)) {
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(this.getProvinces().get(i));
               }
            }
         }
      }

      CFG.game.buildWastelandLevels();
   }

   protected boolean canMakeAction(int i) {
      try {
         return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   protected String getConditionText() {
      try {
         return CFG.langManager.get("UpdateWastelandProvinces")
            + ": "
            + CFG.langManager.get("Provinces")
            + ": "
            + this.getProvinces().size()
            + ", "
            + (this.getValue() == 0 ? CFG.langManager.get("WontBeAWastelandAnymore") : CFG.langManager.get("WillTurnIntoAWasteland"));
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdateWastelandProvinces");
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();

         for(int i = 0; i < this.getProvinces().size(); ++i) {
            if (this.canMakeAction(i)) {
               tData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (
                           CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0
                              ? (Serializable)this.getProvinces().get(i)
                              : CFG.game.getProvince(this.getProvinces().get(i)).getName()
                        )
                        + ": "
                  )
               );
               if (this.getValue() == 0) {
                  tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WontBeAWastelandAnymore"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               } else {
                  tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WillTurnIntoAWasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               }

               tElements.add(new MenuElement_Hover_v2_Element2(tData));
               tData.clear();
            }
         }

         return tElements;
      } catch (IndexOutOfBoundsException var4) {
      } catch (NullPointerException var5) {
      }

      return new ArrayList<>();
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_WASTELAND);
   }
}
