package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

class Game_Ages {
   private List<Age> lAges;
   private int iAgesSize;
   private String sBC;

   protected Game_Ages() {
      this.loadAges();
   }

   protected final void loadAges() {
      this.lAges = new ArrayList<>();

      try {
         FileHandle fileList = Gdx.files.internal("game/Ages.json");
         String fileContent = fileList.readString();
         Json json = new Json();
         json.setElementType(Game_Ages.ConfigAgesData.class, "Age", Game_Ages.Data_Ages.class);
         new Game_Ages.ConfigAgesData();
         Game_Ages.ConfigAgesData data = json.fromJson(Game_Ages.ConfigAgesData.class, fileContent);

         for(Object e : data.Age) {
            Game_Ages.Data_Ages tempData = (Game_Ages.Data_Ages)e;
            this.lAges
               .add(
                  new Age(
                     tempData.Name,
                     tempData.AGE_BeginningYear,
                     tempData.AGE_EndYear,
                     tempData.POPULATION_GROWTH,
                     tempData.ECONOMY_GROWTH,
                     tempData.FOG_OF_WAR_DISCOVERY_MET_PROVINCES,
                     tempData.DEVELOPMENT_LEVEL_INCREASE,
                     tempData.INCOME_TAXATION_MODIFIER,
                     tempData.INCOME_PRODUCTION_MODIFIER,
                     tempData.EXPENSES_ADMINSTRATION_MODIFIER,
                     tempData.EXPENSES_MILITARY_UPKEEP_MODIFIER,
                     tempData.BASE_MOVEMENT_POINTS,
                     tempData.MOVEMENT_POINTS_MODIFIER,
                     tempData.BASE_DIPLOMACY_POINTS,
                     tempData.EXPENSES_ADMINSTRATION_DISTANCE,
                     tempData.DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE,
                     tempData.BASE_INCOME_TAXATION,
                     tempData.INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER,
                     tempData.BASE_MILITARY_UPKEEP,
                     tempData.GAME_STARTING_DEVELOPMENT,
                     tempData.GAME_DAYS_PER_TURN,
                     tempData.BASE_INCOME_PRODUCTION,
                     tempData.INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER,
                     tempData.REVOLUTIONARY_RISK_MODIFIER,
                     tempData.DISEASE_CHANCE,
                     tempData.COLONIZATION_COST,
                     tempData.COLONIZE_COST_MOVEMENT_POINTS,
                     tempData.COLONIZE_COST_DIPLOMACY_POINTS
                  )
               );
         }
      } catch (GdxRuntimeException var8) {
         CFG.exceptionStack(var8);
         this.lAges.add(new Age("AgeofCivilizations", -5000, -301, 0.3F, 0.2F));
         this.lAges.add(new Age("AgeofExpansion", -300, 499, 0.35F, 0.22F));
         this.lAges.add(new Age("AgeofDarkness", 500, 1065, 0.4F, 0.22F));
         this.lAges.add(new Age("AgeofFeudalism", 1066, 1491, 0.45F, 0.22F));
         this.lAges.add(new Age("AgeofDiscovery", 1492, 1749, 0.5F, 0.22F));
         this.lAges.add(new Age("AgeofRevolution", 1750, 1835, 0.55F, 0.22F));
         this.lAges.add(new Age("AgeofIndustrialisation", 1836, 1860, 0.6F, 0.22F));
         this.lAges.add(new Age("AgeofImperialism", 1861, 1918, 0.65F, 0.22F));
         this.lAges.add(new Age("AgeofConflict", 1919, 1946, 0.7F, 0.22F));
         this.lAges.add(new Age("AgeofBrinkmanship", 1947, 1990, 0.75F, 0.22F));
         this.lAges.add(new Age("AgeofInformation", 1991, 2049, 0.8F, 0.22F));
         this.lAges.add(new Age("AgeofTomorrow", 2050, 5000, 0.95F, 1.0F));
      }

      this.sBC = CFG.langManager.get("BeforeChrist");
      this.iAgesSize = this.lAges.size();

      for(int i = 0; i < this.iAgesSize; ++i) {
         this.lAges.get(i).setName(CFG.langManager.get(this.lAges.get(i).getName()));
      }
   }

   protected final void updateLanguage() {
      this.loadAges();
   }

   protected final String getYear(int nYear) {
      return nYear < 0 ? "" + -nYear + " " + this.getBC() : "" + nYear;
   }

   protected final int getAgeOfYear(int nYear) {
      for(int i = 0; i < this.lAges.size() - 1; ++i) {
         if (this.lAges.get(i).getBeginningYear() <= nYear && this.lAges.get(i).getEndYear() >= nYear) {
            return i;
         }
      }

      return this.lAges.size() - 1;
   }

   protected final float getAge_FogOfWarDiscovery_MetProvinces(int nAgeID) {
      return this.lAges.get(nAgeID).FOG_OF_WAR_DISCOVERY_MET_PROVINCES;
   }

   protected final float getAge_Population_GrowthRate(int nAgeID) {
      return this.lAges.get(nAgeID).getPopulationGrowthRate();
   }

   protected final float getAge_Economy_GrowthRate(int nAgeID) {
      return this.lAges.get(nAgeID).getEconomyGrowthRate();
   }

   protected final float getAge_DevelopmentLevel_Increase(int nAgeID) {
      return this.lAges.get(nAgeID).DEVELOPMENT_LEVEL_INCREASE;
   }

   protected final float getAge_TreasuryModifier(int nAgeID) {
      return this.lAges.get(nAgeID).INCOME_TAXATION_MODIFIER;
   }

   protected final float getAge_TreasuryModifier_Production(int nAgeID) {
      return this.lAges.get(nAgeID).INCOME_PRODUCTION_MODIFIER;
   }

   protected final float getAge_TreasuryModifier_Administration(int nAgeID) {
      return this.lAges.get(nAgeID).EXPENSES_ADMINSTRATION_MODIFIER;
   }

   protected final float getAge_TreasuryModifier_MilitaryUpkeep(int nAgeID) {
      return this.lAges.get(nAgeID).EXPENSES_MILITARY_UPKEEP_MODIFIER;
   }

   protected final int getAge_StartingMovementPoints(int nAgeID) {
      return this.lAges.get(nAgeID).BASE_MOVEMENT_POINTS;
   }

   protected final float getAge_MovementPointsModifier(int nAgeID) {
      return this.lAges.get(nAgeID).MOVEMENT_POINTS_MODIFIER;
   }

   protected final int getAge_StartingDiplomacyPoints(int nAgeID) {
      return this.lAges.get(nAgeID).BASE_DIPLOMACY_POINTS;
   }

   protected final float getAge_AdministrationCost_Distance(int nAgeID) {
      return this.lAges.get(nAgeID).EXPENSES_ADMINSTRATION_DISTANCE;
   }

   protected final float getAge_DistanceDiplomacy(int nAgeID) {
      return (float)this.lAges.get(nAgeID).DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE;
   }

   protected final float getAge_IncomeTaxation_Base(int nAgeID) {
      return this.lAges.get(nAgeID).BASE_INCOME_TAXATION;
   }

   protected final float getAge_IncomeTaxation_PerTechnology(int nAgeID) {
      return this.lAges.get(nAgeID).INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER;
   }

   protected final float getAge_MilitaryUpkeep(int nAgeID) {
      return this.lAges.get(nAgeID).BASE_MILITARY_UPKEEP;
   }

   protected final float getAge_StartingDevelopment(int nAgeID) {
      return this.lAges.get(nAgeID).GAME_STARTING_DEVELOPMENT;
   }

   protected final float getAge_IncomeProduction_Base(int nAgeID) {
      return this.lAges.get(nAgeID).BASE_INCOME_PRODUCTION;
   }

   protected final float getAge_IncomeProduction_PerDevelopment(int nAgeID) {
      return this.lAges.get(nAgeID).INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER;
   }

   protected final float getAge_RevolutionaryRiskModifier(int nAgeID) {
      return this.lAges.get(nAgeID).REVOLUTIONARY_RISK_MODIFIER;
   }

   protected final float getAge_DiseaseChance(int nAgeID) {
      return this.lAges.get(nAgeID).DISEASE_CHANCE;
   }

   protected final int getAge_TurnDays(int nAgeID) {
      return (int)((float)this.lAges.get(nAgeID).GAME_DAYS_PER_TURN * Game_Calendar.GAME_SPEED);
   }

   protected final Age getAge(int i) {
      return this.lAges.get(i);
   }

   protected final String getBC() {
      return this.sBC;
   }

   protected final int getAgesSize() {
      return this.iAgesSize;
   }

   protected static class ConfigAgesData {
      protected String Age_of_Civilizations;
      protected ArrayList Age;
   }

   protected static class Data_Ages {
      protected String Name;
      protected int AGE_BeginningYear;
      protected int AGE_EndYear;
      protected float POPULATION_GROWTH;
      protected float ECONOMY_GROWTH;
      protected float DEVELOPMENT_LEVEL_INCREASE;
      protected float INCOME_TAXATION_MODIFIER;
      protected float INCOME_PRODUCTION_MODIFIER;
      protected float EXPENSES_ADMINSTRATION_MODIFIER;
      protected float EXPENSES_MILITARY_UPKEEP_MODIFIER;
      protected int BASE_MOVEMENT_POINTS;
      protected float FOG_OF_WAR_DISCOVERY_MET_PROVINCES;
      protected float MOVEMENT_POINTS_MODIFIER;
      protected int BASE_DIPLOMACY_POINTS;
      protected float EXPENSES_ADMINSTRATION_DISTANCE;
      protected int DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE;
      protected float BASE_INCOME_TAXATION;
      protected float INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER;
      protected float BASE_MILITARY_UPKEEP;
      protected float GAME_STARTING_DEVELOPMENT;
      protected int GAME_DAYS_PER_TURN;
      protected float BASE_INCOME_PRODUCTION;
      protected float INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER;
      protected float REVOLUTIONARY_RISK_MODIFIER;
      protected float COLONIZATION_COST;
      protected int COLONIZE_COST_MOVEMENT_POINTS;
      protected int COLONIZE_COST_DIPLOMACY_POINTS;
      protected float DISEASE_CHANCE;
   }
}
