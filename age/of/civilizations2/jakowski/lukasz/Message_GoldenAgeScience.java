package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Message_GoldenAgeScience extends Message {
   protected Message_GoldenAgeScience(int fromCivID, int iNumOfTurns) {
      super(fromCivID, 0);
      this.messageType = Message_Type.GOLDEN_AGE_SCIENCE;
      this.iNumOfTurnsLeft = iNumOfTurns;
   }

   @Override
   protected void onAction(int iMessageID) {
      CFG.toast.setInView(CFG.langManager.get("OurCivilizationIsInAGoldenAgeOf") + ": " + CFG.langManager.get("GAScience"), CFG.COLOR_TEXT_GOLDEN_AGE);
   }

   @Override
   protected void onAccept(int iCivID) {
   }

   @Override
   protected void onDecline(int iCivID) {
   }

   @Override
   protected int getImageID() {
      return Images.diplo_goldenage_s;
   }

   @Override
   protected MenuElement_Hover_v2 getHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OurCivilizationIsInAGoldenAgeOf") + ": ", CFG.COLOR_TEXT_GOLDEN_AGE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GAScience")));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      int nID = -1;

      for(int i = 0; i < CFG.game.getCiv(this.iFromCivID).getBonusesSize(); ++i) {
         if (CFG.game.getCiv(this.iFromCivID).getBonus(i).BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_SCIENCE) {
            nID = i;
            break;
         }
      }

      if (nID >= 0) {
         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_Research != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_Research > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_Research * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_Research > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_AttackBonus != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackBonus") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_AttackBonus > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_AttackBonus * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_AttackBonus > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_DefenseBonus != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_DefenseBonus > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_DefenseBonus * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_DefenseBonus > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_PopGrowth != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_PopGrowth > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_PopGrowth * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_PopGrowth > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_EconomyGrowth != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_EconomyGrowth > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_EconomyGrowth * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_EconomyGrowth > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeTaxation != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeTaxation > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeTaxation * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeTaxation > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeProduction != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeProduction > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeProduction * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_IncomeProduction > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MilitaryUpkeep != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MilitaryUpkeep > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MilitaryUpkeep * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MilitaryUpkeep < 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MovementPoints != 0.0F) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MovementPoints > 0.0F ? "+" : "")
                     + (int)(CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MovementPoints * 100.0F)
                     + "%",
                  CFG.game.getCiv(this.iFromCivID).getBonus(nID).fModifier_MovementPoints > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }
      }

      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MessageWillExpireIn") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft) + " ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (CFG.game.getCiv(this.iFromCivID).civGameData.leaderData != null) {
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).civGameData.leaderData.getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      return new MenuElement_Hover_v2(nElements);
   }
}
