package age.of.civilizations2.jakowski.lukasz;

class SkillsManager {
   protected static final int MAX_POINTS_POP_GROWTH = 25;
   protected static final float PER_POINT_POP_GROWTH = 0.75F;
   protected static final int MAX_POINTS_ECONOMY_GROWTH = 25;
   protected static final float PER_POINT_ECONOMY_GROWTH = 0.75F;
   protected static final int MAX_POINTS_INCOME_TAXATION = 25;
   protected static final float PER_POINT_INCOME_TAXATION = 0.2F;
   protected static final int MAX_POINTS_INCOME_PRODUCTION = 25;
   protected static final float PER_POINT_INCOME_PRODUCTION = 0.25F;
   protected static final int MAX_POINTS_ADMINISTRATION = 20;
   protected static final float PER_POINT_ADMINISTRATION = 0.3F;
   protected static final int MAX_POINTS_MILITARY_UPKEEP = 30;
   protected static final float PER_POINT_MILITARY_UPKEEP = 0.35F;
   protected static final int MAX_POINTS_RESEARCH = 30;
   protected static final float PER_POINT_RESEARCH = 0.75F;
   protected static final int MAX_POINTS_COLONIZATION = 15;
   protected static final float PER_POINT_COLONIZATION = 1.0F;

   protected static final boolean canAdd_PopGrowth(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH < 25;
   }

   protected static final void add_PopGrowth(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_PopGrowth -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH * 0.75F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH + 1, 25);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_PopGrowth += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH * 0.75F / 100.0F;
      }
   }

   protected static final boolean canAdd_EcoGrowth(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH < 25;
   }

   protected static final void add_EcoGrowth(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_EconomyGrowth -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 0.75F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH + 1, 25);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_EconomyGrowth += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 0.75F / 100.0F;
      }
   }

   protected static final boolean canAdd_IncomeTaxation(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION < 25;
   }

   protected static final void add_IncomeTaxation(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeTaxation -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.2F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION = Math.min(
            CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION + 1, 25
         );
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeTaxation += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.2F / 100.0F;
      }
   }

   protected static final boolean canAdd_IncomeProduction(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION < 25;
   }

   protected static final void add_IncomeProduction(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeProduction -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.25F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION = Math.min(
            CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION + 1, 25
         );
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeProduction += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.25F / 100.0F;
      }
   }

   protected static final boolean canAdd_Administration(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION < 20;
   }

   protected static final void add_Administration(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Administration += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.3F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION + 1, 20);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Administration -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.3F / 100.0F;
      }
   }

   protected static final boolean canAdd_MilitaryUpkeep(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP < 30;
   }

   protected static final void add_MilitaryUpkeep(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_MilitaryUpkeep += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP = Math.min(
            CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP + 1, 30
         );
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_MilitaryUpkeep -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35F / 100.0F;
      }
   }

   protected static final boolean canAdd_Research(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH < 30;
   }

   protected static final void add_Research(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Research -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH * 0.75F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH + 1, 30);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Research += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH * 0.75F / 100.0F;
      }
   }

   protected static final boolean canAdd_Colonization(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION < 15;
   }

   protected static final void add_Colonization(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_ColonizationCost -= (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION + 1, 15);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_ColonizationCost += (float)CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0F / 100.0F;
      }
   }
}
