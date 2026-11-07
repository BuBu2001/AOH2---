package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Scenario_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<String> lCivsTags;
   private List<Integer> lCivsCapitals;
   private List<Float> lTechnologyLevels;
   private List<List<Scenario_GameData_Technology>> lTechnologyByContinents;
   private List<Integer> lHappiness;
   private List<Integer> lStartingMoney;
   private int iStartingArmyInCapitals = 500;
   private int iNeutralArmy = 500;
   private int iStartingPopulation = 500;
   private int iStartingEconomy = 500;
   private int iStartingMoney = 500;
   private float iPopulationGrowthRate_Modifier = 0.0F;
   private float iEconomyGrowthRate_Modifier = 0.0F;
   private float iDiseasesDeathRate_Modifier = 0.0F;
   private boolean COLONIZATION = true;
   protected boolean ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
   protected float COLONIZATION_TECH_LEVEL = 0.8F;
   private String ACTIVE_PALLET_OF_COLORS_TAG = null;
   protected boolean isPartOfCampaign = false;
   protected List<Integer> lCampaingCivsIDs = new ArrayList<>();

   protected final void buildData() {
      this.lCivsTags = new ArrayList<>();
      this.lCivsCapitals = new ArrayList<>();
      this.lTechnologyLevels = new ArrayList<>();
      this.lStartingMoney = new ArrayList<>();
      this.lHappiness = new ArrayList<>();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.lCivsTags.add(CFG.game.getCiv(i).getCivTag());
         this.lCivsCapitals.add(CFG.game.getCiv(i).getCapitalProvinceID());
         this.lTechnologyLevels.add(CFG.game.getCiv(i).getTechnologyLevel());
         this.lStartingMoney.add((int)CFG.game.getCiv(i).getMoney());
         this.lHappiness.add(CFG.game.getCiv(i).getHappiness());
      }

      this.lTechnologyByContinents = new ArrayList<>();

      for(int i = 0; i < CFG.lCreateScenario_TechnologyBContinents.size(); ++i) {
         if (CFG.lCreateScenario_TechnologyBContinents.get(i).size() <= 0) {
            this.lTechnologyByContinents.add(null);
         } else {
            for(int j = 0; j < CFG.lCreateScenario_TechnologyBContinents.get(i).size(); ++j) {
               if (CFG.lCreateScenario_TechnologyBContinents.get(i).get(j).getPercentage() != 100) {
                  if (this.lTechnologyByContinents.size() <= i) {
                     this.lTechnologyByContinents.add(new ArrayList<>());
                  }

                  this.lTechnologyByContinents
                     .get(i)
                     .add(
                        new Scenario_GameData_Technology(
                           CFG.lCreateScenario_TechnologyBContinents.get(i).get(j).getContinentID(),
                           CFG.lCreateScenario_TechnologyBContinents.get(i).get(j).getPercentage()
                        )
                     );
               }
            }

            if (this.lTechnologyByContinents.size() <= i) {
               this.lTechnologyByContinents.add(null);
            }
         }
      }

      this.iStartingArmyInCapitals = CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals();
      this.iNeutralArmy = CFG.game.getGameScenarios().getScenario_NeutralArmy();
      this.iStartingPopulation = CFG.game.getGameScenarios().getScenario_StartingPopulation();
      this.iStartingEconomy = CFG.game.getGameScenarios().getScenario_StartingEconomy();
      this.iStartingMoney = CFG.game.getGameScenarios().getScenario_StartingMoney();
      this.iPopulationGrowthRate_Modifier = CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier();
      this.iEconomyGrowthRate_Modifier = CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier();
      this.iDiseasesDeathRate_Modifier = CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier();
      this.COLONIZATION = Game_Calendar.ENABLE_COLONIZATION;
      this.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
      this.COLONIZATION_TECH_LEVEL = Game_Calendar.COLONIZATION_TECH_LEVEL;
      this.ACTIVE_PALLET_OF_COLORS_TAG = CFG.palletManager.getActivePalletID() == 0
         ? null
         : CFG.palletManager.getPalletTag(CFG.palletManager.getActivePalletID() - 1);
   }

   protected final int getCivSize() {
      return this.lCivsTags.size();
   }

   protected final String getCivTag(int i) {
      return this.lCivsTags.get(i);
   }

   protected final int getCivCapital(int i) {
      return this.lCivsCapitals.get(i);
   }

   protected final float getTechnologyLevel(int i) {
      return this.lTechnologyLevels.get(i);
   }

   protected final int getHappiness(int i) {
      return this.lHappiness.get(i);
   }

   protected final int getStartingMoneyCiv(int i) {
      return this.lStartingMoney.get(i);
   }

   protected final int getStartingArmyInCapitals() {
      return this.iStartingArmyInCapitals;
   }

   protected final void setStartingArmyInCapitals(int iStartingArmyInCapitals) {
      this.iStartingArmyInCapitals = iStartingArmyInCapitals;
   }

   protected final int getStartingPopulation() {
      return this.iStartingPopulation;
   }

   protected final void setStartingPopulation(int iStartingPopulation) {
      this.iStartingPopulation = iStartingPopulation;
   }

   protected final int getStartingEconomy() {
      return this.iStartingEconomy;
   }

   protected final void setStartingEconomy(int iStartingEconomy) {
      this.iStartingEconomy = iStartingEconomy;
   }

   protected final int getStartingMoney() {
      return this.iStartingMoney;
   }

   protected final void setStartingMoney(int iStartingMoney) {
      this.iStartingMoney = iStartingMoney;
   }

   protected final String getActivePalletOfColors_TAG() {
      return this.ACTIVE_PALLET_OF_COLORS_TAG;
   }

   protected final void setActivePalletOfColors_TAG(String aCTIVE_PALLET_OF_COLORS_TAG) {
      this.ACTIVE_PALLET_OF_COLORS_TAG = aCTIVE_PALLET_OF_COLORS_TAG;
   }

   protected final boolean getColonization() {
      return this.COLONIZATION;
   }

   protected final void setColonization(boolean COLONIZATION) {
      this.COLONIZATION = COLONIZATION;
   }

   protected final List<Scenario_GameData_Technology> getTechnologyByContinents(int i) {
      return this.lTechnologyByContinents.get(i);
   }

   protected final int getNeutralArmy() {
      return this.iNeutralArmy;
   }

   protected final void setNeutralArmy(int iNeutralArmy) {
      this.iNeutralArmy = iNeutralArmy;
   }

   protected final float getPopulationGrowthRate_Modifier() {
      return this.iPopulationGrowthRate_Modifier;
   }

   protected final float getEconomyGrowthRate_Modifier() {
      return this.iEconomyGrowthRate_Modifier;
   }

   protected final float getDiseasesDeathRate_Modifier() {
      return this.iDiseasesDeathRate_Modifier;
   }

   protected final void addCampaingCivsIDs(int nID) {
      for(int i = 0; i < this.lCampaingCivsIDs.size(); ++i) {
         if (this.lCampaingCivsIDs.get(i) == nID) {
            return;
         }
      }

      this.lCampaingCivsIDs.add(nID);
   }
}
