package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Event_Outcome_IncreaseRelation extends Event_Outcome {
   protected int iCivID = -1;
   protected int iCivID2 = -1;
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
   protected int getCivID2() {
      return this.iCivID2;
   }

   @Override
   protected void setCivID2(int nCivID) {
      this.iCivID2 = nCivID;
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

      if (this.iCivID2 == nRemovedCivID) {
         this.iCivID2 = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID2) {
         --this.iCivID2;
      }

      return out;
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
         CFG.game
            .setCivRelation_OfCivB(
               this.getCivID(), this.getCivID2(), CFG.game.getCivRelation_OfCivB(this.getCivID(), this.getCivID2()) + (float)this.getValue()
            );
         CFG.game
            .setCivRelation_OfCivB(
               this.getCivID2(), this.getCivID(), CFG.game.getCivRelation_OfCivB(this.getCivID2(), this.getCivID()) + (float)this.getValue()
            );
      }
   }

   protected boolean canMakeAction() {
      try {
         return this.getValue() != 0
            && this.getCivID() >= 0
            && this.getCivID() < CFG.game.getCivsSize()
            && this.getCivID2() >= 0
            && this.getCivID2() < CFG.game.getCivsSize()
            && this.getCivID() != this.getCivID2()
            && !CFG.game.getCivsAtWar(this.getCivID(), this.getCivID2());
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         List<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         if (this.canMakeAction()) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Relation") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), 0, CFG.PADDING));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID2()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID2(), CFG.PADDING, 0));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  ": " + (this.getValue() > 0 ? "+" : "") + this.getValue(),
                  this.getValue() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
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
   protected String getConditionText() {
      try {
         return CFG.langManager.get("UpdateRelation")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + ", "
            + CFG.game.getCiv(this.getCivID2()).getCivName()
            + ": "
            + (this.getValue() > 0 ? "+" : "")
            + this.getValue();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdateRelation");
      }
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_INCRELATION);
   }
}
