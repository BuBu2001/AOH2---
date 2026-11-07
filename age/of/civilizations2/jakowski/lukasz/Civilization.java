package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Civilization {
   protected Save_Civ_GameData civGameData = new Save_Civ_GameData();
   private int iCivID;
   private boolean controlledByPlayer = false;
   private boolean isAvailable = true;
   private List<Character> lCivNameChars;
   private int iCivNameLength = 0;
   private int iCivNameWidth;
   private int iCivNameHeight;
   private Image civFlag = null;
   private List<String> sTagsCanForm = null;
   private List<Integer> lEventsToRun = new ArrayList<>();
   private int iMovePoints;
   private int iHappiness;
   private int iIdeologyID = 0;
   private int iNumOfProvinces;
   private int iNumOfUnits;
   private List<Integer> lProvinces = new ArrayList<>();
   private List<Integer> lArmyInAnotherProvince = new ArrayList<>();
   private int iArmyInAnotherProvinceSize = 0;
   protected List<Integer> lArmiesPosition = new ArrayList<>();
   protected int iArmiesPositionSize = 0;
   protected List<AI_BordersWith> lBorderWithCivs = new ArrayList<>();
   protected int iBorderWithCivsSize = 0;
   protected List<Integer> lBordersWithWastelandProvincesID = new ArrayList<>();
   protected List<Integer> lBordersWithNeutralProvincesID = new ArrayList<>();
   private List<Civilization_Region> lCivRegions = new ArrayList<>();
   private int iCivRegionsSize;
   private boolean updateRegions = false;
   protected int iLeague = 0;
   protected int iBudget = 0;
   protected int iIncomeTaxation = 0;
   protected int iIncomeProduction = 0;
   protected int iAdministrationCosts = 0;
   protected int iMilitaryUpkeep_Total = 0;
   protected float iMilitaryUpkeep_PERC = 0.0F;
   protected int iAveragePopulation = 0;
   protected List<Integer> lProvincesWithLowStability = new ArrayList<>();
   protected List<Integer> lProvincesWithLowHappiness = new ArrayList<>();
   protected List<Integer> lProvincesWithHighRevRisk = new ArrayList<>();
   protected float fStability = 1.0F;
   protected float fAverageDevelopment = 1.0F;
   protected int iNumOf_Forts = 0;
   protected int iNumOf_Towers = 0;
   protected int iNumOf_Ports = 0;
   protected int iNumOf_Farms = 0;
   protected int iNumOf_Farms_ProvincesPossibleToBuild = 0;
   protected int iNumOf_Workshops = 0;
   protected int iNumOf_Libraries = 0;
   protected int iNumOf_Armories = 0;
   protected int iNumOf_SuppliesCamp = 0;
   protected List<Integer> lNonAggressionPact = new ArrayList<>();
   protected List<Integer> lOpt_NonAggressionPact = new ArrayList<>();
   protected List<Integer> lTruce = new ArrayList<>();
   protected List<Integer> lOpt_Truce = new ArrayList<>();
   protected List<Integer> lDefensivePact = new ArrayList<>();
   protected List<Integer> lOpt_DefensivePact = new ArrayList<>();
   protected List<Byte> lGuarantee = new ArrayList<>();
   protected List<Integer> lOpt_Guarantee = new ArrayList<>();
   protected List<Byte> lMilitirayAccess = new ArrayList<>();
   protected List<Integer> lOpt_MilitirayAccess = new ArrayList<>();
   private int seaAccess = 0;
   private List<Integer> seaAccess_Provinces = new ArrayList<>();
   private List<Integer> seaAccess_Port = new ArrayList<>();
   private int bordersWithEnemy = 0;
   private boolean isAtWar = false;
   protected List<Integer> isAtWarWithCivs = new ArrayList<>();
   private int iNumOfNeighboringNeutralProvinces = 0;
   private boolean canExpandOnContinent = false;
   private int iRankPosition = 1;
   private int iRankScore = 1;
   private List<Move_Units> lMoveUnits;
   private int iMoveUnitsSize;
   private List<Move_Units_Plunder> lMove_Units_Plunder;
   private int iMove_Units_PlunderSize;
   private List<List<MoveUnits_Line>> lCurrentRegroupArmyLine = new ArrayList<>();
   private List<RecruitArmy_Request> lRecruitArmy;
   private int iRecruitArmySize;
   private List<Move_Units> lMigrate;
   private int iMigrateSize;
   protected static final int ADD_CIV_DEFAULT_TECH_LEVEL = 45;
   protected static final int MIN_MONEY_REQUIRED_TO_ENABLE_RESEARCH = -500;

   protected Civilization(String nCivTag, int iR, int iG, int iB, int nCapitalProvinceID, int nCivID) {
      this.setCivID(nCivID);
      this.initCivilization(nCivTag, iR, iG, iB, nCapitalProvinceID);
   }

   protected Civilization(Save_Civ_GameData nCivData, int nCivID) {
      this.setCivID(nCivID);
      this.setCivName(nCivData.sCivName);
      this.civGameData = nCivData;
      this.updateCivilizationIdeology();
      this.sTagsCanForm = new ArrayList<>();
      this.lMoveUnits = new ArrayList<>();
      this.iMoveUnitsSize = 0;
      this.lMove_Units_Plunder = new ArrayList<>();
      this.iMove_Units_PlunderSize = 0;
      this.lRecruitArmy = new ArrayList<>();
      this.iRecruitArmySize = 0;
      this.lMigrate = new ArrayList<>();
      this.iMigrateSize = 0;
      this.lCurrentRegroupArmyLine.clear();
      this.controlledByPlayer = false;
      this.isAvailable = true;
      this.iHappiness = 75;
      this.lEventsToRun.clear();
      this.lEventsToRun = new ArrayList<>();
      this.civGameData.lEvents_DecisionsTaken.clear();
      this.civGameData.lEvents_DecisionsTaken = new ArrayList<>();
      this.loadFlag();
   }

   private final void initCivilization(String nCivTag, int iR, int iG, int iB, int nCapitalProvinceID) {
      this.setCivName(CFG.langManager.getCiv(nCivTag));
      this.civGameData.sCivTag = nCivTag;
      this.updateCivilizationIdeology();
      this.civGameData.iCapitalProvinceID = nCapitalProvinceID;
      if (nCapitalProvinceID >= 0) {
         CFG.game.getProvince(nCapitalProvinceID).setIsCapital(true);
      }

      this.civGameData.iR = (short)iR;
      this.civGameData.iG = (short)iG;
      this.civGameData.iB = (short)iB;
      this.civGameData.civilization_Diplomacy_GameData = new Civilization_Diplomacy_GameData();
      this.buildCivPersonality();
      this.sTagsCanForm = new ArrayList<>();
      this.civGameData.lLoansTaken = new ArrayList<>();
      this.civGameData.lWarReparationsGets = new ArrayList<>();
      this.civGameData.lWarReparationsPay = new ArrayList<>();
      this.lMoveUnits = new ArrayList<>();
      this.iMoveUnitsSize = 0;
      this.lMove_Units_Plunder = new ArrayList<>();
      this.iMove_Units_PlunderSize = 0;
      this.lRecruitArmy = new ArrayList<>();
      this.iRecruitArmySize = 0;
      this.civGameData.lRegroupArmy = new ArrayList<>();
      this.civGameData.iRegroupArmySize = 0;
      this.lMigrate = new ArrayList<>();
      this.iMigrateSize = 0;
      this.lCurrentRegroupArmyLine.clear();
      this.controlledByPlayer = false;
      this.isAvailable = true;
      this.civGameData.fTechnologyLevel = 45;
      this.iHappiness = 75;
      this.lEventsToRun.clear();
      this.lEventsToRun = new ArrayList<>();
      this.civGameData.lEvents_DecisionsTaken.clear();
      this.civGameData.lEvents_DecisionsTaken = new ArrayList<>();
      this.loadFlag();
   }

   protected final void buildCivPersonality_MoreOften() {
      this.civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN = 7 + CFG.oR.nextInt(15);
      this.civGameData.civPersonality.TAXATION_LEVEL = 0.9F + (float)CFG.oR.nextInt(100) / 1000.0F;
      this.civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS = (float)CFG.oAI.getAI_Style(this.getAI_Style()).USE_OF_BUDGET_FOR_SPENDINGS / 100.0F
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).USE_OF_BUDGET_FOR_SPENDINGS_RANDOM * 10) / 1000.0F;
      this.civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET = 0.04F
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_GOODS_RANDOM) / 100.0F;
      this.civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET = 0.04F
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_INVESTMENTS_RANDOM) / 100.0F;
      this.civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET = 0.0F
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_RESEARCH_RANDOM) / 100.0F;
   }

   protected final void buildCivPersonality() {
      Gdx.app.log("AoC", "buildCivPersonality: " + this.getCivName());
      this.civGameData.civPersonality.WAR_CLOSE_REGION_PROVINCES = 3 + CFG.oR.nextInt(4);
      this.civGameData.civPersonality.WAR_CLOSE_REGION_EXTRA_SCORE = 1.115F + (float)CFG.oR.nextInt(675) / 1000.0F;
      this.civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY = CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_DEFAULT
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_RANDOM) / 100.0F;
      this.buildCivPersonality_MoreOften();
      this.civGameData.civPersonality.TREASURY_RESERVE = 3.85F + (float)CFG.oR.nextInt(625) / 100.0F;
      this.civGameData.civPersonality.TREASURY_RESERVE_MODIFIER = 0.05F + (float)CFG.oR.nextInt(150) / 1000.0F;
      this.civGameData.civPersonality.PLUNDER_CHANCE = (float)CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_PLUNDER_MIN
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_PLUNDER_RANDOM) / 1000.0F;
      this.civGameData.civPersonality.WAR_POTENTIAL = 0.275F + (float)CFG.oR.nextInt(375) / 1000.0F;
      this.civGameData.civPersonality.WAR_DANGER = 0.525F + (float)CFG.oR.nextInt(525) / 1000.0F;
      this.civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES = 0.0375F + (float)CFG.oR.nextInt(275) / 1000.0F;
      this.civGameData.civPersonality.WAR_REGION_POTENTIAL = 0.425F + (float)CFG.oR.nextInt(625) / 1000.0F;
      this.civGameData.civPersonality.WAR_NUM_OF_UNITS = 0.925F - (float)CFG.oR.nextInt(725) / 1000.0F;
      this.civGameData.civPersonality.WAR_ATTACK_NAVAL_DISTANCE = 0.695F - (float)CFG.oR.nextInt(335) / 1000.0F;
      this.civGameData.civPersonality.WAR_ATTACK_DISTANCE = 0.625F - (float)CFG.oR.nextInt(275) / 1000.0F;
      this.civGameData.civPersonality.WAR_ATTACK_SCORE_ARMY = 0.315F - (float)CFG.oR.nextInt(350) / 1000.0F;
      this.civGameData.civPersonality.WAR_ATTACK_SCORE_POTENTIAL = 0.325F - (float)CFG.oR.nextInt(350) / 1000.0F;
      this.civGameData.civPersonality.WAR_ATTACK_SCORE_WAS_CONQUERED = 0.275F - (float)CFG.oR.nextInt(725) / 1000.0F;
      this.civGameData.civPersonality.WAR_REGROUP_SPLIT_MIN = 1 + CFG.oR.nextInt(2);
      this.civGameData.civPersonality.WAR_REGROUP_SPLIT_EXTRA = 1 + CFG.oR.nextInt(3);
      this.civGameData.civPersonality.VALUABLE_POTENTIAL = 0.275F + (float)CFG.oR.nextInt(375) / 1000.0F;
      this.civGameData.civPersonality.VALUABLE_POTENTIAL_MODIFIED_OWN_LOST_PROVINCE = 1.625F + (float)CFG.oR.nextInt(1750) / 1000.0F;
      this.civGameData.civPersonality.VALUABLE_DANGER = 0.575F + (float)CFG.oR.nextInt(625) / 1000.0F;
      this.civGameData.civPersonality.VALUABLE_REGION_NUM_OF_PROVINCES = 0.025F + (float)CFG.oR.nextInt(175) / 1000.0F;
      this.civGameData.civPersonality.VALUABLE_REGION_POTENTIAL = 0.275F + (float)CFG.oR.nextInt(625) / 1000.0F;
      this.civGameData.civPersonality.VALUABLE_NUM_OF_UNITS = 0.925F - (float)CFG.oR.nextInt(425) / 1000.0F;
      this.civGameData.civPersonality.VALUABLE_NUM_OF_UNITS_RECRUITMENT = 0.0F - (float)CFG.oR.nextInt(475) / 1000.0F;
      this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS = CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM) / 100.0F;
      this.civGameData.civPersonality.VALUABLE_RECRUIT_FROM_FAR_AWAY_CHANCE = 1 + CFG.oR.nextInt(54);
      this.civGameData.civPersonality.AGGRESSION = CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_AGGRESION_DEFAULT
         + (float)CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_AGGRESION_RANDOM) / 100.0F;
      this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS_RECRUIT_AT_WAR = 0.905F + (float)CFG.oR.nextInt(675) / 1000.0F;
      this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY = 0.49F + (float)CFG.oR.nextInt(260) / 1000.0F;
      this.civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR_MODIFIER = 1.02F + (float)CFG.oR.nextInt(165) / 1000.0F;
      this.civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV = CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_HAPPINESS_DEFAULT
         + CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_MIN_HAPPINESS_RANDOM);
      this.civGameData.civPersonality.MIN_HAPPINESS_CRISIS = 54 + CFG.oR.nextInt(12);
      this.civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL = Game_Action.RISE_REVOLT_RISK_HAPPINESS + (float)CFG.oR.nextInt(23) / 100.0F;
      this.civGameData.civPersonality.DEFENSE = 20 + CFG.oR.nextInt(40);
      this.civGameData.civPersonality.FORGIVENESS = CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_FORGIVNESS_DEFAULT
         + (float)(
               CFG.oR.nextInt(CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_FORGIVNESS_RANDOM)
                  - CFG.oAI.getAI_Style(this.getAI_Style()).PERSONALITY_FORGIVNESS_RANDOM / 2
            )
            / 100.0F;
      if (this.getControlledByPlayer()) {
         this.civGameData.civPersonality.MIN_PROVINCE_STABILITY = 0.71F;
      } else {
         this.civGameData.civPersonality.MIN_PROVINCE_STABILITY = 0.42F + (float)CFG.oR.nextInt(58) / 100.0F;
      }

      this.civGameData.civPersonality.MIN_HAPPINESS_TO_ASSMILIATE_PROVINCE = 0.45F + (float)CFG.oR.nextInt(20) / 100.0F;
      this.civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE = 0.1F + (float)CFG.oR.nextInt(65) / 100.0F;
      this.civGameData.civPersonality.ASSIMILATE_PERC_LOW_STABILITY_SCORE = 0.1F + (float)CFG.oR.nextInt(65) / 100.0F;
      this.civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE = 0.1F + (float)CFG.oR.nextInt(75) / 100.0F;
      this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DISTANCE_SCORE = 6.95F + (float)CFG.oR.nextInt(90) / 100.0F;
      this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RELATION_SCORE = 32.325F + (float)CFG.oR.nextInt(28);
      this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_SCORE = 5.15F + (float)CFG.oR.nextInt(65) / 10.0F;
      this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_OWN_SCORE = 5.25F + (float)CFG.oR.nextInt(65) / 10.0F;
      this.civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DEFENSIVE_PACT_SCORE = 16.25F + (float)CFG.oR.nextInt(150) / 10.0F;
      this.civGameData.civPersonality.HRE_VOTE_FOR_RANK = 14.0F + (float)CFG.oR.nextInt(16);
      this.civGameData.civPersonality.HRE_VOTE_FOR_PROVINCES = 16.0F + (float)CFG.oR.nextInt(16);
      this.civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION = 32.5F + (float)CFG.oR.nextInt(220) / 10.0F;
      this.civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH = 25.75F + (float)CFG.oR.nextInt(265) / 10.0F;
      this.civGameData.civPersonality.BUILD_MIN_STABILITY = 0.64F + (float)CFG.oR.nextInt(26) / 100.0F;
      this.civGameData.civPersonality.BUILD_STABILITY_SCORE = 0.52F + (float)CFG.oR.nextInt(36) / 100.0F;
      this.civGameData.civPersonality.BUILD_MAX_REV_RISK = 0.0F + (float)CFG.oR.nextInt(10) / 100.0F;
      this.civGameData.civPersonality.BUILD_DANGER_SCORE = 0.01F + (float)CFG.oR.nextInt(34) / 100.0F;
      this.buildCivPersonality_Buildings();
      this.buildCivPersonality_Colonization();
      this.civGameData.civPersonality.TECH_POP = 0.15F + (float)CFG.oR.nextInt(85) / 100.0F;
      this.civGameData.civPersonality.TECH_ECO = 0.15F + (float)CFG.oR.nextInt(85) / 100.0F;
      this.civGameData.civPersonality.TECH_TAXATION = 0.1F + (float)CFG.oR.nextInt(90) / 100.0F;
      this.civGameData.civPersonality.TECH_PRODUCTION = 0.1F + (float)CFG.oR.nextInt(90) / 100.0F;
      this.civGameData.civPersonality.TECH_ADMINISTARTION = 0.01F + (float)CFG.oR.nextInt(75) / 100.0F;
      this.civGameData.civPersonality.TECH_MILITARY_UPKEEP = 0.01F + (float)CFG.oR.nextInt(75) / 100.0F;
      this.civGameData.civPersonality.TECH_RESEARCH = 0.01F + (float)CFG.oR.nextInt(70) / 100.0F;
      this.civGameData.civPersonality.LIBERITY_DECLARATION = (float)(66 + CFG.oR.nextInt(32));
      this.civGameData.civPersonality.LIBERITY_ACCEPTABLE_TRIBUTE = 0.45F + (float)CFG.oR.nextInt(40) / 100.0F;
      this.civGameData.civPersonality.VASSALS_TRIBUTE_PERC = 0.1F + (float)CFG.oR.nextInt(60) / 100.0F;
      this.civGameData.civPersonality.VASSALS_TRIBUTE_PERC_RAND = 0.05F + (float)CFG.oR.nextInt(25) / 100.0F;
      this.civGameData.civPersonality.VASSALS_TRIBUTE_PERC_FRIENDLY = 0.3F + (float)CFG.oR.nextInt(65) / 100.0F;
      this.civGameData.civPersonality.POTENTIAL_POPULATION = 24.25F + (float)CFG.oR.nextInt(9999) / 1000.0F;
      this.civGameData.civPersonality.POTENTIAL_ECONOMY = 21.5F + (float)CFG.oR.nextInt(9999) / 1000.0F;
      this.civGameData.civPersonality.DANGER_EXTRA_KEY_REGION = 1.825F + (float)CFG.oR.nextInt(325) / 1000.0F;
      this.civGameData.civPersonality.DANGER_EXTRA_PER_OWN_PROVINCE = 0.025F + (float)CFG.oR.nextInt(175) / 1000.0F;
      this.civGameData.civPersonality.DANGER_PERC_OF_UNITS = 0.35F + (float)CFG.oR.nextInt(425) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_CAPITAL = 14.25F + (float)CFG.oR.nextInt(25250) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_OWN_PROVINCE = 7.25F + (float)CFG.oR.nextInt(9500) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_MORE_NEUTRAL = 2.75F + (float)CFG.oR.nextInt(8000) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_OTHER_CIV = 2.5F + (float)CFG.oR.nextInt(8750) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_GROWTH_RATE = 41.75F + (float)CFG.oR.nextInt(50000) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_LAST_PROVINCE = 46.75F + (float)CFG.oR.nextInt(50000) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS = 8.75F + (float)CFG.oR.nextInt(19000) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS_EXTRA = 1.75F + (float)CFG.oR.nextInt(3500) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES = 1.65F + (float)CFG.oR.nextInt(3750) / 1000.0F;
      this.civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES_POTENITAL = 17.85F + (float)CFG.oR.nextInt(25000) / 1000.0F;
   }

   protected final void buildCivPersonality_Colonization() {
      this.civGameData.civPersonality.COLONIZATION_SEA = (float)(4 + CFG.oR.nextInt(14) / Math.max(this.civGameData.lColonies_Founded.size(), 1));
      this.civGameData.civPersonality.COLONIZATION_OWN_PROVINCES = 30.0F + (float)CFG.oR.nextInt(40);
      this.civGameData.civPersonality.COLONIZATION_GROWTH_RATE = 12.0F + (float)CFG.oR.nextInt(11);
      this.civGameData.civPersonality.COLONIZATION_DISTANCE = 4.0F + (float)CFG.oR.nextInt(11);
   }

   protected final void buildCivPersonality_Buildings() {
      this.civGameData.civPersonality.BUILD_FORT = 0.001F + (float)CFG.oR.nextInt(21) / 100.0F;
      this.civGameData.civPersonality.BUILD_TOWER = 2.5E-4F + (float)CFG.oR.nextInt(6) / 100.0F;
      this.civGameData.civPersonality.BUILD_PORT = 0.01F + (float)CFG.oR.nextInt(18) / 100.0F;
      this.civGameData.civPersonality.BUILD_FARM = 0.1F + (float)CFG.oR.nextInt(100) / 100.0F;
      this.civGameData.civPersonality.BUILD_WORKSHOP = 0.1F + (float)CFG.oR.nextInt(95) / 100.0F;
      this.civGameData.civPersonality.BUILD_WORKSHOP_POP_SCORE = 0.51F + (float)CFG.oR.nextInt(42) / 100.0F;
      this.civGameData.civPersonality.BUILD_WORKSHOP_ECO_SCORE = 0.82F + (float)CFG.oR.nextInt(35) / 100.0F;
      this.civGameData.civPersonality.BUILD_LIBRARY = 0.075F + (float)CFG.oR.nextInt(90) / 100.0F;
      this.civGameData.civPersonality.BUILD_ARMOURY = 0.02F + (float)CFG.oR.nextInt(30) / 100.0F;
      this.civGameData.civPersonality.BUILD_ARMOURY_RECRUITABLE_SCORE = 0.375F + (float)CFG.oR.nextInt(325) / 1000.0F;
      this.civGameData.civPersonality.BUILD_SUPPLYLINE = 0.01F + (float)CFG.oR.nextInt(20) / 100.0F;
      this.civGameData.civPersonality.BUILD_INVEST = 0.1275F + (float)CFG.oR.nextInt(100) / 100.0F;
      this.civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT = 0.0125F + (float)CFG.oR.nextInt(75) / 100.0F;
      this.civGameData.civPersonality.BUILD_INVEST_POP_SCORE = 0.175F + (float)CFG.oR.nextInt(275) / 1000.0F;
      this.civGameData.civPersonality.BUILD_INVEST_DEVELOPMENT_SCORE = 0.15F + (float)CFG.oR.nextInt(275) / 1000.0F;
      this.civGameData.civPersonality.BUILD_INVEST_POP_ECO_DIFFERENCE_SCORE = 0.875F + (float)CFG.oR.nextInt(2825) / 1000.0F;
      this.civGameData.civPersonality.BUILD_INVEST_SECOND_INVEST_MAX_PERC = 0 + CFG.oR.nextInt(40);
      this.civGameData.civPersonality.BUILD_INVEST_SECOND_INVEST_CHANCE = 45 + CFG.oR.nextInt(55);
      this.civGameData.civPersonality.BUILD_RESRVE_RAND = 1 + CFG.oR.nextInt(4);
   }

   protected final void createCivilizationRegion(int nProvinceID) {
      this.lCivRegions.add(new Civilization_Region(nProvinceID, this.iCivRegionsSize));
      this.iCivRegionsSize = this.lCivRegions.size();
      CFG.game.getProvince(nProvinceID).setCivRegionID(this.iCivRegionsSize - 1);
      CFG.game.getProvince(nProvinceID).was = true;
      this.buildCivilizationRegion(nProvinceID, this.iCivRegionsSize - 1);

      for(int i = 0; i < this.getNumOfProvinces(); ++i) {
         CFG.game.getProvince(this.getProvinceID(i)).was = false;
      }
   }

   private final void buildCivilizationRegion(int nProvinceID, int nCivRegionID) {
      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == this.iCivID
            && !CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).was) {
            CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).was = true;
            this.lCivRegions.get(nCivRegionID).addProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i));
            CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).setCivRegionID(nCivRegionID);
            this.buildCivilizationRegion(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i), nCivRegionID);
         }
      }
   }

   protected final boolean civRegionsContainsProvince(int nProvinceID) {
      for(int i = 0; i < this.iCivRegionsSize; ++i) {
         if (this.lCivRegions.get(i).containsProvince(nProvinceID)) {
            return true;
         }
      }

      return false;
   }

   private final void removeCivRegionID(int id) {
      for(int i = 0; i < this.lCivRegions.get(id).getProvincesSize(); ++i) {
         CFG.game.getProvince(this.lCivRegions.get(id).getProvince(i)).setCivRegionID(-1);
      }

      this.lCivRegions.remove(id);
      this.iCivRegionsSize = this.lCivRegions.size();

      for(int i = 0; i < this.iCivRegionsSize; ++i) {
         this.lCivRegions.get(i).setRegionID(i);
      }
   }

   protected final void clearCivRegions() {
      for(int i = 0; i < this.getNumOfProvinces(); ++i) {
         CFG.game.getProvince(this.getProvinceID(i)).setCivRegionID(-1);
      }

      this.lCivRegions.clear();
      this.iCivRegionsSize = 0;
   }

   protected final void updateCivilizationIdeology(String nCivTag, int iR, int iG, int iB) {
      this.setCivTag(nCivTag);
      this.civGameData.iR = (short)iR;
      this.civGameData.iG = (short)iG;
      this.civGameData.iB = (short)iB;
      this.updateCivilizationIdeology();
      this.loadFlag();
   }

   protected final void updateCivilizationIdeology() {
      this.setIdeologyID(CFG.ideologiesManager.getIdeologyID(this.getCivTag()));
   }

   protected final void buildDiplomacy(boolean buildRelations) {
      if (buildRelations) {
         this.civGameData.lRelation.clear();

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            this.civGameData.lRelation.add(0.0F);
         }
      }

      this.lGuarantee.clear();
      this.lMilitirayAccess.clear();
      this.lNonAggressionPact.clear();
      this.lTruce.clear();
      this.lDefensivePact.clear();
      this.lOpt_Truce.clear();
      this.lOpt_MilitirayAccess.clear();
      this.lOpt_DefensivePact.clear();
      this.lOpt_Guarantee.clear();
      this.lOpt_NonAggressionPact.clear();
      if (buildRelations) {
         this.civGameData.iAllianceID = 0;
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.lGuarantee.add((byte)0);
         this.lMilitirayAccess.add((byte)0);
      }

      for(int i = this.iCivID + 1; i < CFG.game.getCivsSize(); ++i) {
         this.lNonAggressionPact.add(0);
         this.lTruce.add(0);
         this.lDefensivePact.add(0);
      }
   }

   protected final void updateDiplomacy_AfterRemoveCivilization_Relations(int nIDToRemove) {
      this.civGameData.lRelation.remove(nIDToRemove);
      this.lGuarantee.remove(nIDToRemove);
      this.lMilitirayAccess.remove(nIDToRemove);

      for(int i = 0; i < this.lOpt_Guarantee.size(); ++i) {
         if (this.lOpt_Guarantee.get(i) == nIDToRemove) {
            this.lOpt_Guarantee.remove(i);
            break;
         }
      }

      for(int i = 0; i < this.lOpt_MilitirayAccess.size(); ++i) {
         if (this.lOpt_MilitirayAccess.get(i) == nIDToRemove) {
            this.lOpt_MilitirayAccess.remove(i);
            break;
         }
      }
   }

   protected final void updateDiplomacy_AfterRemoveCivilization(int nIDToRemove) {
      this.lNonAggressionPact.remove(nIDToRemove);
      this.lTruce.remove(nIDToRemove);
      this.lDefensivePact.remove(nIDToRemove);

      for(int i = 0; i < this.lOpt_Truce.size(); ++i) {
         if (this.lOpt_Truce.get(i) == nIDToRemove) {
            this.lOpt_Truce.remove(i);
            break;
         }
      }

      for(int i = 0; i < this.lOpt_DefensivePact.size(); ++i) {
         if (this.lOpt_DefensivePact.get(i) == nIDToRemove) {
            this.lOpt_DefensivePact.remove(i);
            break;
         }
      }

      for(int i = 0; i < this.lOpt_NonAggressionPact.size(); ++i) {
         if (this.lOpt_NonAggressionPact.get(i) == nIDToRemove) {
            this.lOpt_NonAggressionPact.remove(i);
            break;
         }
      }
   }

   protected final void updateDiplomacy_AfterAddingCivilization() {
      this.civGameData.lRelation.add(0.0F);
      this.lGuarantee.add((byte)0);
      this.lMilitirayAccess.add((byte)0);
      this.lNonAggressionPact.add(0);
      this.lTruce.add(0);
      this.lDefensivePact.add(0);
   }

   protected final void newMove(int fromProvinceID, int toProvinceID, int nNumOfUnits, boolean buildLine) {
      this.lMoveUnits.add(new Move_Units(fromProvinceID, toProvinceID, nNumOfUnits, buildLine));
      this.iMoveUnitsSize = this.lMoveUnits.size();
   }

   protected final void removeMove(int i) {
      this.lMoveUnits.remove(i);
      this.iMoveUnitsSize = this.lMoveUnits.size();
   }

   protected final void clearMoveUnits() {
      this.lMoveUnits.clear();
      this.iMoveUnitsSize = this.lMoveUnits.size();
   }

   protected final void newMigrate(int fromProvinceID, int toProvinceID, boolean buildLine) {
      for(int i = 0; i < this.iMigrateSize; ++i) {
         if (this.lMigrate.get(i).getFromProvinceID() == fromProvinceID) {
            this.removeMigrate(i);
            this.setMovePoints(this.getMovePoints() + CFG.ideologiesManager.getIdeology(this.getIdeologyID()).COST_OF_MOVE);
            break;
         }
      }

      this.lMigrate
         .add(new Move_Units(fromProvinceID, toProvinceID, CFG.game.getProvince(fromProvinceID).getPopulationData().getPopulation(), buildLine, true));
      this.iMigrateSize = this.lMigrate.size();
   }

   protected final void removeMigrate(int i) {
      this.lMigrate.remove(i);
      this.iMigrateSize = this.lMigrate.size();
   }

   protected final void clearMigrate() {
      this.lMigrate.clear();
      this.iMigrateSize = this.lMigrate.size();
   }

   protected final boolean migratesFromProvinceID(int nProvinceID) {
      for(int i = 0; i < this.iMigrateSize; ++i) {
         if (this.lMigrate.get(i).getFromProvinceID() == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final void newPlunder(int fromProvinceID, int nNumOfUnits) {
      for(int i = 0; i < this.iMove_Units_PlunderSize; ++i) {
         if (this.lMove_Units_Plunder.get(i).getFromProvinceID() == fromProvinceID) {
            this.lMove_Units_Plunder.get(i).setNumOfUnits(nNumOfUnits);
            return;
         }
      }

      this.lMove_Units_Plunder.add(new Move_Units_Plunder(fromProvinceID, nNumOfUnits));
      this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
   }

   protected final void removePlunder(int i) {
      this.lMove_Units_Plunder.remove(i);
      this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
   }

   protected final void removePlunder_ProvinceID(int nProvinceID) {
      for(int i = 0; i < this.iMove_Units_PlunderSize; ++i) {
         if (this.lMove_Units_Plunder.get(i).getFromProvinceID() == nProvinceID) {
            CFG.game
               .getProvince(this.lMove_Units_Plunder.get(i).getFromProvinceID())
               .updateArmy(
                  this.getCivID(),
                  CFG.game.getProvince(this.lMove_Units_Plunder.get(i).getFromProvinceID()).getArmyCivID(this.getCivID())
                     + this.lMove_Units_Plunder.get(i).getNumOfUnits()
               );
            this.lMove_Units_Plunder.remove(i);
            this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
            return;
         }
      }
   }

   protected final void clearMoveUnits_Plunder() {
      this.lMove_Units_Plunder.clear();
      this.iMove_Units_PlunderSize = this.lMove_Units_Plunder.size();
   }

   protected final boolean isPlundred(int nProvinceID) {
      for(int i = 0; i < this.iMove_Units_PlunderSize; ++i) {
         if (this.lMove_Units_Plunder.get(i).getFromProvinceID() == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean addFestival(CivFestival nFestival) {
      for(int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
         if (nFestival.iProvinceID == this.civGameData.lFestivals.get(i).iProvinceID) {
            return false;
         }
      }

      this.civGameData.lFestivals.add(nFestival);
      return true;
   }

   protected final CivFestival getFestival(int i) {
      return this.civGameData.lFestivals.get(i);
   }

   protected final void removeFestival(int i) {
      this.civGameData.lFestivals.remove(i);
   }

   protected final void removeFestival_ProvinceID(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
         if (nProvinceID == this.civGameData.lFestivals.get(i).iProvinceID) {
            this.civGameData.lFestivals.remove(i);
            break;
         }
      }
   }

   protected final void runFestivals() {
      for(int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
         if (CFG.game.getProvince(this.civGameData.lFestivals.get(i).iProvinceID).getCivID() != this.getCivID()) {
            this.civGameData.lFestivals.remove(i--);
         } else {
            --this.civGameData.lFestivals.get(i).iTurnsLeft;
            CFG.game
               .getProvince(this.civGameData.lFestivals.get(i).iProvinceID)
               .setHappiness(
                  CFG.game.getProvince(this.civGameData.lFestivals.get(i).iProvinceID).getHappiness()
                     + DiplomacyManager.festivalHappinessPerTurn(this.civGameData.lFestivals.get(i).iProvinceID)
               );

            for(int j = 0; j < CFG.game.getProvince(this.civGameData.lFestivals.get(i).iProvinceID).getNeighboringProvincesSize(); ++j) {
               CFG.game
                  .getProvince(CFG.game.getProvince(this.civGameData.lFestivals.get(i).iProvinceID).getNeighboringProvinces(j))
                  .setHappiness(
                     CFG.game.getProvince(CFG.game.getProvince(this.civGameData.lFestivals.get(i).iProvinceID).getNeighboringProvinces(j)).getHappiness()
                        + DiplomacyManager.festivalHappinessPerTurn_NeighboringProvinces()
                  );
            }

            if (this.civGameData.lFestivals.get(i).iTurnsLeft <= 0) {
               CFG.game
                  .getCiv(this.iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_FestivalIsOver(this.iCivID, this.civGameData.lFestivals.get(i).iProvinceID));
               this.civGameData.lFestivals.remove(i--);
            }
         }
      }
   }

   protected final boolean isFestivalOrganized(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
         if (nProvinceID == this.civGameData.lFestivals.get(i).iProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final CivFestival isFestivalOrganized_GET(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
         if (nProvinceID == this.civGameData.lFestivals.get(i).iProvinceID) {
            return this.civGameData.lFestivals.get(i);
         }
      }

      return null;
   }

   protected final int isFestivalOrganized_TurnsLeft(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lFestivals.size(); ++i) {
         if (nProvinceID == this.civGameData.lFestivals.get(i).iProvinceID) {
            return this.civGameData.lFestivals.get(i).iTurnsLeft;
         }
      }

      return 0;
   }

   protected final int getFestivalsSize() {
      return this.civGameData.lFestivals.size();
   }

   protected final boolean addAssimilate(CivFestival nFestival) {
      for(int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
         if (nFestival.iProvinceID == this.civGameData.lAssimilates.get(i).iProvinceID) {
            return false;
         }
      }

      this.civGameData.lAssimilates.add(nFestival);
      return true;
   }

   protected final CivFestival getAssimilate(int i) {
      return this.civGameData.lAssimilates.get(i);
   }

   protected final void removeAssimilate(int i) {
      this.civGameData.lAssimilates.remove(i);
   }

   protected final void removeAssimilate_ProvinceID(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
         if (nProvinceID == this.civGameData.lAssimilates.get(i).iProvinceID) {
            this.civGameData.lAssimilates.remove(i);
            break;
         }
      }
   }

   protected final void runAssimilates() {
      for(int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
         if (CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getCivID() != this.getCivID()) {
            this.civGameData.lAssimilates.remove(i--);
         } else {
            --this.civGameData.lAssimilates.get(i).iTurnsLeft;
            int popToAssimilate = 0;
            int ownerPop = 1
               + CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getPopulationOfCivID(this.getCivID());

            for(int j = 0; j < CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getNationalitiesSize(); ++j) {
               if (CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getCivID(j)
                  != CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getCivID()) {
                  popToAssimilate += CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getPopulationID(j);
               }
            }

            int assimilatedPop = 0;
            int j = CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getNationalitiesSize() - 1;

            for(int tCurrentPopChange = 0; j >= 0; --j) {
               if (CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getCivID(j)
                  != CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getCivID()) {
                  float tPerc = (
                        0.00425F
                           + (0.04971F + (float)CFG.oR.nextInt(1087) / 10000.0F)
                              * ((float)ownerPop / (float)(popToAssimilate + ownerPop))
                              * CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getHappiness()
                              * Math.min(1.0F - CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getDevelopmentLevel() / 3.75F, 1.0F)
                     )
                     * (
                        1.0F
                           - 0.225F * (1.0F - CFG.game.getCiv(this.getCivID()).getStability())
                           - 0.075F * CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getRevolutionaryRisk()
                     )
                     * 0.8F;
                  tCurrentPopChange = (int)(
                     (float)CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getPopulationID(j) * tPerc
                  );
                  if (tCurrentPopChange == 0) {
                     tCurrentPopChange = CFG.oR.nextInt(2);
                  }

                  assimilatedPop += tCurrentPopChange;
                  CFG.game
                     .getProvince(this.civGameData.lAssimilates.get(i).iProvinceID)
                     .getPopulationData()
                     .setPopulationOfCivID(
                        CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getCivID(j),
                        CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getPopulationID(j) - tCurrentPopChange
                     );
               }
            }

            CFG.game
               .getProvince(this.civGameData.lAssimilates.get(i).iProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  this.getCivID(),
                  CFG.game.getProvince(this.civGameData.lAssimilates.get(i).iProvinceID).getPopulationData().getPopulationOfCivID(this.getCivID())
                     + assimilatedPop
               );
            if (this.civGameData.lAssimilates.get(i).iTurnsLeft <= 0) {
               CFG.game
                  .getCiv(this.iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_AssimilationEnd(this.iCivID, this.civGameData.lAssimilates.get(i).iProvinceID));
               this.civGameData.lAssimilates.remove(i--);
            }
         }
      }
   }

   protected final CivFestival isAssimilateOrganized_GET(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
         if (nProvinceID == this.civGameData.lAssimilates.get(i).iProvinceID) {
            return this.civGameData.lAssimilates.get(i);
         }
      }

      return null;
   }

   protected final boolean isAssimilateOrganized(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
         if (nProvinceID == this.civGameData.lAssimilates.get(i).iProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final int isAssimialateOrganized_TurnsLeft(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lAssimilates.size(); ++i) {
         if (nProvinceID == this.civGameData.lAssimilates.get(i).iProvinceID) {
            return this.civGameData.lAssimilates.get(i).iTurnsLeft;
         }
      }

      return 0;
   }

   protected final int getAssimilatesSize() {
      return this.civGameData.lAssimilates.size();
   }

   protected final boolean addInvest(CivInvest nInvest) {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (nInvest.iProvinceID == this.civGameData.lInvest.get(i).iProvinceID) {
            return false;
         }
      }

      this.civGameData.lInvest.add(nInvest);
      return true;
   }

   protected final CivInvest getInvest(int i) {
      return this.civGameData.lInvest.get(i);
   }

   protected final void removeInvest(int i) {
      this.civGameData.lInvest.remove(i);
   }

   protected final void removeInvest_ProvinceID(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest.get(i).iProvinceID) {
            this.civGameData.lInvest.remove(i);
            break;
         }
      }
   }

   protected final void runInvests() {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (CFG.game.getProvince(this.civGameData.lInvest.get(i).iProvinceID).getCivID() == this.getCivID()) {
            --this.civGameData.lInvest.get(i).iTurnsLeft;
            int ecoToAdd = Math.min(this.civGameData.lInvest.get(i).iEconomyPerTurn, this.civGameData.lInvest.get(i).iEconomyLeft);
            if (this.civGameData.lInvest.get(i).iTurnsLeft == 0) {
               ecoToAdd = this.civGameData.lInvest.get(i).iEconomyLeft;
            }

            CFG.game
               .getProvince(this.civGameData.lInvest.get(i).iProvinceID)
               .setEconomy(CFG.game.getProvince(this.civGameData.lInvest.get(i).iProvinceID).getEconomy() + ecoToAdd);
            CivInvest var10000 = this.civGameData.lInvest.get(i);
            var10000.iEconomyLeft -= ecoToAdd;
            if (this.civGameData.lInvest.get(i).iTurnsLeft <= 0 || this.civGameData.lInvest.get(i).iEconomyLeft <= 0) {
               CFG.game
                  .getCiv(this.iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_InvestDone(this.iCivID, this.civGameData.lInvest.get(i).iProvinceID));
               this.civGameData.lInvest.remove(i--);
            }
         } else {
            this.civGameData.lInvest.remove(i--);
         }
      }
   }

   protected final boolean isInvestOrganized(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest.get(i).iProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final int isInvestOrganized_TurnsLeft(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest.get(i).iProvinceID) {
            return this.civGameData.lInvest.get(i).iTurnsLeft;
         }
      }

      return 0;
   }

   protected final CivInvest isInvestOrganized_GET(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest.get(i).iProvinceID) {
            return this.civGameData.lInvest.get(i);
         }
      }

      return null;
   }

   protected final int isInvestOrganized_EconomyLeft(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest.get(i).iProvinceID) {
            return this.civGameData.lInvest.get(i).iEconomyLeft;
         }
      }

      return 0;
   }

   protected final int getInvestsSize() {
      return this.civGameData.lInvest.size();
   }

   protected final boolean addInvest_Development(CivInvest_Development nInvest) {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (nInvest.iProvinceID == this.civGameData.lInvest_Development.get(i).iProvinceID) {
            return false;
         }
      }

      this.civGameData.lInvest_Development.add(nInvest);
      return true;
   }

   protected final CivInvest_Development getInvest_Development(int i) {
      return this.civGameData.lInvest_Development.get(i);
   }

   protected final void removeInvest_Development(int i) {
      this.civGameData.lInvest_Development.remove(i);
   }

   protected final void removeInvest_ProvinceID_Development(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest_Development.get(i).iProvinceID) {
            this.civGameData.lInvest_Development.remove(i);
            break;
         }
      }
   }

   protected final void runInvests_Development() {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (CFG.game.getProvince(this.civGameData.lInvest_Development.get(i).iProvinceID).getCivID() == this.getCivID()) {
            --this.civGameData.lInvest_Development.get(i).iTurnsLeft;
            float ecoToAdd = Math.min(
               this.civGameData.lInvest_Development.get(i).iDevelopemntPerTurn, this.civGameData.lInvest_Development.get(i).iDevelopemntLeft
            );
            if (this.civGameData.lInvest_Development.get(i).iTurnsLeft == 0) {
               ecoToAdd = this.civGameData.lInvest_Development.get(i).iDevelopemntLeft;
            }

            CFG.game
               .getProvince(this.civGameData.lInvest_Development.get(i).iProvinceID)
               .setDevelopmentLevel(CFG.game.getProvince(this.civGameData.lInvest_Development.get(i).iProvinceID).getDevelopmentLevel() + ecoToAdd);
            CivInvest_Development var10000 = this.civGameData.lInvest_Development.get(i);
            var10000.iDevelopemntLeft -= ecoToAdd;
            if (this.civGameData.lInvest_Development.get(i).iTurnsLeft <= 0 || this.civGameData.lInvest_Development.get(i).iDevelopemntLeft <= 0.0F) {
               CFG.game
                  .getCiv(this.iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_InvestDone_Development(this.iCivID, this.civGameData.lInvest_Development.get(i).iProvinceID));
               this.civGameData.lInvest_Development.remove(i--);
            }
         } else {
            this.civGameData.lInvest_Development.remove(i--);
         }
      }
   }

   protected final boolean isInvestOrganized_Development(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest_Development.get(i).iProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final int isInvestOrganized_TurnsLeft_Development(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest_Development.get(i).iProvinceID) {
            return this.civGameData.lInvest_Development.get(i).iTurnsLeft;
         }
      }

      return 0;
   }

   protected final CivInvest_Development isInvestOrganized_GET_Development(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest_Development.get(i).iProvinceID) {
            return this.civGameData.lInvest_Development.get(i);
         }
      }

      return null;
   }

   protected final float isInvestOrganized_EconomyLeft_Development(int nProvinceID) {
      for(int i = 0; i < this.civGameData.lInvest_Development.size(); ++i) {
         if (nProvinceID == this.civGameData.lInvest_Development.get(i).iProvinceID) {
            return this.civGameData.lInvest_Development.get(i).iDevelopemntLeft;
         }
      }

      return 0.0F;
   }

   protected final int getInvestsSize_Development() {
      return this.civGameData.lInvest_Development.size();
   }

   protected final void addNewConstruction(Construction_GameData nConstruction) {
      for(int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
         if (this.civGameData.lConstructions.get(i).iProvinceID == nConstruction.iProvinceID
            && this.civGameData.lConstructions.get(i).constructionType == nConstruction.constructionType) {
            return;
         }
      }

      this.civGameData.lConstructions.add(nConstruction);
   }

   protected final int getConstructionsSize() {
      return this.civGameData.lConstructions.size();
   }

   protected final Construction_GameData getConstruction(int i) {
      return this.civGameData.lConstructions.get(i);
   }

   protected final void runConstruction() {
      for(int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
         if (CFG.game.getProvince(this.civGameData.lConstructions.get(i).iProvinceID).getCivID() != this.getCivID()) {
            this.civGameData.lConstructions.remove(i--);
         } else {
            --this.civGameData.lConstructions.get(i).iNumOfTurnsLeft;
            if (this.civGameData.lConstructions.get(i).iNumOfTurnsLeft <= 0) {
               this.civGameData.lConstructions.get(i).onConstructed(this.getCivID());
               this.civGameData.lConstructions.remove(i--);
            }
         }
      }
   }

   protected final int isInConstruction(int nProvinceID, ConstructionType nType) {
      for(int i = 0; i < this.civGameData.lConstructions.size(); ++i) {
         if (this.civGameData.lConstructions.get(i).iProvinceID == nProvinceID && this.civGameData.lConstructions.get(i).constructionType == nType) {
            return this.civGameData.lConstructions.get(i).iNumOfTurnsLeft;
         }
      }

      return 0;
   }

   protected final void clearConstructions() {
      this.civGameData.lConstructions.clear();
   }

   protected final void recruitArmy_NewTurn() {
      for(int i = 0; i < this.iRecruitArmySize; ++i) {
         try {
            if (CFG.game.getProvince(this.lRecruitArmy.get(i).getProvinceID()).getCivID() == this.getCivID()) {
               CFG.gameAction.recruitArmy(this.lRecruitArmy.get(i).getProvinceID(), this.lRecruitArmy.get(i).getArmy(), this.getCivID());
            } else {
               CFG.game
                  .getCiv(this.getCivID())
                  .setMoney(
                     CFG.game.getCiv(this.getCivID()).getMoney()
                        + (long)(
                           (int)(
                              (float)(this.lRecruitArmy.get(i).getArmy() * CFG.getCostOfRecruitArmyMoney(this.lRecruitArmy.get(i).getProvinceID())) * 0.725F
                           )
                        )
                  );
            }
         } catch (IllegalArgumentException var3) {
         } catch (IndexOutOfBoundsException var4) {
         }
      }

      this.clearRecruitArmy();
   }

   protected final boolean recruitArmy_AI(int nProvinceID, int nArmy) {
      for(int i = 0; i < this.iRecruitArmySize; ++i) {
         if (this.lRecruitArmy.get(i).getProvinceID() == nProvinceID) {
            return this.recruitArmy(nProvinceID, Math.max(this.lRecruitArmy.get(i).getArmy(), nArmy));
         }
      }

      return this.recruitArmy(nProvinceID, nArmy);
   }

   protected final boolean recruitArmy(int nProvinceID, int nArmy) {
      if (CFG.game.getProvince(nProvinceID).getCivID() != CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()) {
         Gdx.app
            .log(
               "AoC",
               "recruitArmy: NOT TRUE OWNER, OCCUPIED PROVINCE -> "
                  + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCivName()
                  + ", "
                  + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()).getCivName()
            );
         return false;
      } else {
         if (nArmy >= CFG.gameAction.getRecruitableArmy(nProvinceID)) {
            nArmy = CFG.gameAction.getRecruitableArmy(nProvinceID);
         }

         for(int i = 0; i < this.iRecruitArmySize; ++i) {
            if (this.lRecruitArmy.get(i).getProvinceID() == nProvinceID) {
               if (nArmy == 0 && this.lRecruitArmy.get(i).getArmy() > 0) {
                  CFG.game
                     .getCiv(this.getCivID())
                     .setMovePoints(
                        CFG.game.getCiv(this.getCivID()).getMovePoints()
                           + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.getCivID()).getIdeologyID()).COST_OF_RECRUIT
                     );
                  CFG.game
                     .getCiv(this.getCivID())
                     .setMoney(
                        CFG.game.getCiv(this.getCivID()).getMoney() + (long)(this.lRecruitArmy.get(i).getArmy() * CFG.getCostOfRecruitArmyMoney(nProvinceID))
                     );
                  this.removeRecruitArmy(i);
                  return true;
               }

               int tDiff = this.lRecruitArmy.get(i).getArmy() - nArmy;
               this.lRecruitArmy.get(i).setArmy(nArmy);
               CFG.game
                  .getCiv(this.getCivID())
                  .setMoney(CFG.game.getCiv(this.getCivID()).getMoney() + (long)(tDiff * CFG.getCostOfRecruitArmyMoney(nProvinceID)));
               return true;
            }
         }

         if (CFG.game.getCiv(this.getCivID()).getMovePoints()
            < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.getCivID()).getIdeologyID()).COST_OF_RECRUIT) {
            Gdx.app.log("AoC", "RECRUIT NO MOVEMNETS POINTS 1111");
            return false;
         } else {
            if ((long)nArmy >= CFG.game.getCiv(this.getCivID()).getMoney() / (long)CFG.getCostOfRecruitArmyMoney(nProvinceID)) {
               nArmy = (int)CFG.game.getCiv(this.getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney(nProvinceID);
            }

            if (nArmy <= 0) {
               return false;
            } else {
               CFG.game
                  .getCiv(this.getCivID())
                  .setMovePoints(
                     CFG.game.getCiv(this.getCivID()).getMovePoints()
                        - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.getCivID()).getIdeologyID()).COST_OF_RECRUIT
                  );
               CFG.game
                  .getCiv(this.getCivID())
                  .setMoney(CFG.game.getCiv(this.getCivID()).getMoney() - (long)(nArmy * CFG.getCostOfRecruitArmyMoney(nProvinceID)));
               this.lRecruitArmy.add(new RecruitArmy_Request(nProvinceID, nArmy));
               this.iRecruitArmySize = this.lRecruitArmy.size();
               return true;
            }
         }
      }
   }

   protected final void removeRecruitArmy(int i) {
      this.lRecruitArmy.remove(i);
      this.iRecruitArmySize = this.lRecruitArmy.size();
   }

   protected final void clearRecruitArmy() {
      this.lRecruitArmy.clear();
      this.iRecruitArmySize = this.lRecruitArmy.size();
   }

   protected final int isRecruitingArmyInProvinceID(int nProvinceID) {
      for(int i = 0; i < this.iRecruitArmySize; ++i) {
         if (this.lRecruitArmy.get(i).getProvinceID() == nProvinceID) {
            return i;
         }
      }

      return -1;
   }

   protected final void buildRegroupLines_AfterLoading() {
      for(int j = 0; j < this.civGameData.iRegroupArmySize; ++j) {
         List<MoveUnits_Line> tMoveUnitsLine = new ArrayList<>();
         tMoveUnitsLine.add(
            new MoveUnits_Line_Highlighted(this.civGameData.lRegroupArmy.get(j).getFromProvinceID(), this.civGameData.lRegroupArmy.get(j).getRoute(0))
         );

         for(int i = 0; i < this.civGameData.lRegroupArmy.get(j).getRouteSize() - 1; ++i) {
            tMoveUnitsLine.add(
               new MoveUnits_Line_Highlighted(this.civGameData.lRegroupArmy.get(j).getRoute(i), this.civGameData.lRegroupArmy.get(j).getRoute(i + 1))
            );
         }

         this.lCurrentRegroupArmyLine.add(tMoveUnitsLine);
      }
   }

   protected final void addRegroupArmy(RegroupArmy_Data nData) {
      this.civGameData.lRegroupArmy.add(nData);
      this.civGameData.iRegroupArmySize = this.civGameData.lRegroupArmy.size();
      List<MoveUnits_Line> tMoveUnitsLine = new ArrayList<>();
      tMoveUnitsLine.add(new MoveUnits_Line_Highlighted(nData.getFromProvinceID(), nData.getRoute(0)));

      for(int i = 0; i < nData.getRouteSize() - 1; ++i) {
         tMoveUnitsLine.add(new MoveUnits_Line_Highlighted(nData.getRoute(i), nData.getRoute(i + 1)));
      }

      this.lCurrentRegroupArmyLine.add(tMoveUnitsLine);
   }

   protected final void moveRegroupArmy() {
      for(int i = 0; i < this.civGameData.iRegroupArmySize; ++i) {
         try {
            if (!RegroupArmy_Data.canBeUsedInPath(
               this.getCivID(), this.civGameData.lRegroupArmy.get(i).getRoute(0), false, this.civGameData.lRegroupArmy.get(i).getToProvinceID()
            )) {
               this.removeRegroupArmy(i);
               --i;
            } else if (!this.civGameData.lRegroupArmy.get(i).continueMovingArmy(this.getCivID())) {
               this.removeRegroupArmy(i);
               --i;
            } else if (this.civGameData.lRegroupArmy.get(i).getObsolate() < 0) {
               this.removeRegroupArmy(i);
               --i;
            } else {
               this.civGameData.lRegroupArmy.get(i).updateObsolate();
               if (CFG.game.getProvince(this.civGameData.lRegroupArmy.get(i).getFromProvinceID()).getArmyCivID(this.getCivID())
                  <= this.civGameData.lRegroupArmy.get(i).getNumOfUnits()) {
                  if (CFG.game.getProvince(this.civGameData.lRegroupArmy.get(i).getFromProvinceID()).getArmyCivID(this.getCivID()) <= 0) {
                     this.removeRegroupArmy(i);
                     --i;
                     continue;
                  }

                  this.civGameData
                     .lRegroupArmy
                     .get(i)
                     .setNumOfUnits(CFG.game.getProvince(this.civGameData.lRegroupArmy.get(i).getFromProvinceID()).getArmyCivID(this.getCivID()));
               }

               if (CFG.gameAction
                  .moveArmy(
                     this.civGameData.lRegroupArmy.get(i).getFromProvinceID(),
                     this.civGameData.lRegroupArmy.get(i).getRoute(0),
                     this.civGameData.lRegroupArmy.get(i).getNumOfUnits(),
                     this.getCivID(),
                     true,
                     true
                  )) {
                  this.civGameData.lRegroupArmy.get(i).setFromProvinceID(this.civGameData.lRegroupArmy.get(i).getRoute(0));
                  this.civGameData.lRegroupArmy.get(i).removeRoute(0);
                  this.lCurrentRegroupArmyLine.get(i).remove(0);
                  if (this.civGameData.lRegroupArmy.get(i).getRouteSize() == 0) {
                     this.removeRegroupArmy(i);
                     --i;
                  }
               }
            }
         } catch (IndexOutOfBoundsException var3) {
            this.removeRegroupArmy(i);
            --i;
         } catch (NullPointerException var4) {
            this.removeRegroupArmy(i);
            --i;
         }
      }
   }

   protected final void removeRegroupArmy(int i) {
      this.civGameData.lRegroupArmy.remove(i);
      this.lCurrentRegroupArmyLine.remove(i);
      this.civGameData.iRegroupArmySize = this.civGameData.lRegroupArmy.size();
   }

   protected final void clearRegroupArmy() {
      this.civGameData.lRegroupArmy.clear();
      this.lCurrentRegroupArmyLine.clear();
      this.civGameData.iRegroupArmySize = this.civGameData.lRegroupArmy.size();
   }

   protected final void addProvince_Just(int nProvinceID) {
      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return;
         }
      }

      this.lProvinces.add(nProvinceID);
      this.iNumOfProvinces = this.lProvinces.size();
   }

   protected final void addProvince(int nProvinceID) {
      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return;
         }
      }

      this.lProvinces.add(nProvinceID);
      this.iNumOfProvinces = this.lProvinces.size();
      CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
   }

   protected final void removeProvince(int nProvinceID) {
      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            this.lProvinces.remove(i);
            this.iNumOfProvinces = this.lProvinces.size();
            break;
         }
      }

      CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
   }

   protected final void clearProvinces_FillTheMap(boolean addCapital) {
      this.lCivRegions.clear();
      this.iCivRegionsSize = 0;
      this.lProvinces.clear();
      if (addCapital) {
         this.lProvinces.add(this.getCapitalProvinceID());
         this.iNumOfProvinces = this.lProvinces.size();
         this.createCivilizationRegion(this.getCapitalProvinceID());
      } else {
         this.iNumOfProvinces = this.lProvinces.size();
      }
   }

   protected final int getProvinceID(int i) {
      try {
         return this.lProvinces.get(i);
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return -1;
      }
   }

   protected final boolean controlsProvince(int nProvinceID) {
      for(int i = 0; i < this.getNumOfProvinces(); ++i) {
         if (nProvinceID == this.getProvinceID(i)) {
            return true;
         }
      }

      return false;
   }

   protected final int getNumOfProvinces() {
      return this.iNumOfProvinces;
   }

   protected final void setCivName(String sCivName) {
      if (sCivName.length() <= 0) {
         sCivName = "A";
      }

      this.civGameData.sCivName = sCivName;
      CFG.glyphLayout.setText(CFG.fontMain, sCivName);
      this.iCivNameWidth = (int)CFG.glyphLayout.width;
      this.iCivNameHeight = (int)CFG.glyphLayout.height;
      this.lCivNameChars = new ArrayList<>();
      sCivName = sCivName.toUpperCase();

      for(int i = 0; i < this.civGameData.sCivName.length(); ++i) {
         this.lCivNameChars.add(sCivName.charAt(i));
      }

      this.iCivNameLength = this.lCivNameChars.size();
   }

   protected final int getCivNameWidth() {
      return this.iCivNameWidth;
   }

   protected final int getCivNameHeight() {
      return this.iCivNameHeight;
   }

   protected final char getCivNameCharacter(int id) {
      return this.lCivNameChars.get(id);
   }

   protected final int getCivNameLength() {
      return this.iCivNameLength;
   }

   protected final int getR() {
      return this.civGameData.iR;
   }

   protected final void setR(int iR) {
      this.civGameData.iR = (short)iR;
   }

   protected final int getG() {
      return this.civGameData.iG;
   }

   protected final void setG(int iG) {
      this.civGameData.iG = (short)iG;
   }

   protected final int getB() {
      return this.civGameData.iB;
   }

   protected final void setB(int iB) {
      this.civGameData.iB = (short)iB;
   }

   protected final Color getRGB() {
      return this.getRGB(1.0F);
   }

   protected final Color getRGB(float nAlpha) {
      return new Color((float)this.getR() / 255.0F, (float)this.getG() / 255.0F, (float)this.getB() / 255.0F, nAlpha);
   }

   protected final int getMovePoints() {
      return this.iMovePoints;
   }

   protected final void setMovePoints(int iMovePoints) {
      this.iMovePoints = iMovePoints;
   }

   protected final long getMoney() {
      return this.civGameData.iMoney;
   }

   protected final void setMoney(long iMoney) {
      this.civGameData.iMoney = iMoney;
   }

   protected final int getCapitalProvinceID() {
      return this.civGameData.iCapitalProvinceID;
   }

   protected final void setCapitalProvinceID(int iCapitalProvinceID) {
      this.civGameData.iCapitalProvinceID = iCapitalProvinceID;
   }

   protected final int getCoreCapitalProvinceID() {
      return this.civGameData.iCoreCapitalProvinceID;
   }

   protected final void setCoreCapitalProvinceID(int iCoreCapitalProvinceID) {
      this.civGameData.iCoreCapitalProvinceID = iCoreCapitalProvinceID;
   }

   protected final int getCapitalMoved_LastTurnID() {
      return this.civGameData.iCapitalMoved_LastTurnID;
   }

   protected final void setCapitalMoved_LastTurnID(int iCapitalMoved_LastTurnID) {
      this.civGameData.iCapitalMoved_LastTurnID = iCapitalMoved_LastTurnID;
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final void setCivID(int iCivID) {
      this.iCivID = iCivID;
      this.civGameData.iPuppetOfCivID = iCivID;
      this.iRankPosition = iCivID;
   }

   protected final void setCivID_Just(int iCivID) {
      this.iCivID = iCivID;
   }

   protected final String getCivName() {
      return this.civGameData.sCivName;
   }

   protected final String getCivTag() {
      return this.civGameData.sCivTag;
   }

   protected final void setCivTag(String sCivTag) {
      this.civGameData.sCivTag = sCivTag;
      if (sCivTag.indexOf(59) > 0) {
         String[] tempTags = sCivTag.split(";");
         String tempName = "";

         for(int i = 0; i < tempTags.length; ++i) {
            tempName = tempName + CFG.langManager.getCiv(tempTags[i]) + (i < tempTags.length - 1 ? "-" : "");
         }

         this.setCivName(tempName);
      } else {
         this.setCivName(CFG.langManager.getCiv(sCivTag));
      }
   }

   protected final int getHappiness() {
      return this.iHappiness;
   }

   protected final void setHappiness(int nHappiness) {
      this.iHappiness = nHappiness;
      if (this.iHappiness > 100) {
         this.iHappiness = 100;
      } else if (this.iHappiness < 0) {
         this.iHappiness = 0;
      }
   }

   protected final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   protected final void setNumOfUnits(int iNumOfUnits) {
      this.iNumOfUnits = Math.max(iNumOfUnits, 0);
   }

   protected final void buildNumOfUnits() {
      this.iNumOfUnits = 0;

      for(int i = 0; i < this.getNumOfProvinces(); ++i) {
         this.iNumOfUnits += CFG.game.getProvince(this.getProvinceID(i)).getArmyCivID(this.getCivID());
      }

      for(int i = 0; i < this.getMoveUnitsSize(); ++i) {
         this.iNumOfUnits += this.getMoveUnits(i).getNumOfUnits();
      }

      for(int i = 0; i < this.getArmyInAnotherProvinceSize(); ++i) {
         this.iNumOfUnits += CFG.game.getProvince(this.getArmyInAnotherProvince(i)).getArmyCivID(this.getCivID());
      }
   }

   protected final int getArmyInAnotherProvince(int i) {
      try {
         return this.lArmyInAnotherProvince.get(i);
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return -1;
      }
   }

   protected final void addArmyInAnotherProvince(int nProvinceID) {
      Gdx.app
         .log(
            "AoC",
            "addArmyInAnotherProvince: "
               + this.getCivName()
               + ", nProvinceID: "
               + nProvinceID
               + " -> "
               + CFG.game.getProvince(nProvinceID).getName()
               + ", "
               + this.iArmyInAnotherProvinceSize
         );

      for(int i = 0; i < this.getArmyInAnotherProvinceSize(); ++i) {
         if (this.getArmyInAnotherProvince(i) == nProvinceID) {
            return;
         }
      }

      this.lArmyInAnotherProvince.add(nProvinceID);
      this.iArmyInAnotherProvinceSize = this.lArmyInAnotherProvince.size();
   }

   protected final void removeArmyInAnotherProvince(int nProvinceID) {
      Gdx.app
         .log(
            "AoC",
            "removeArmyInAnotherProvince: "
               + this.getCivName()
               + ", nProvinceID: "
               + nProvinceID
               + " -> "
               + CFG.game.getProvince(nProvinceID).getName()
               + ", , "
               + this.iArmyInAnotherProvinceSize
         );

      for(int i = 0; i < this.getArmyInAnotherProvinceSize(); ++i) {
         if (this.getArmyInAnotherProvince(i) == nProvinceID) {
            this.lArmyInAnotherProvince.remove(i);
            this.iArmyInAnotherProvinceSize = this.lArmyInAnotherProvince.size();
            return;
         }
      }
   }

   protected final int getArmyInAnotherProvinceSize() {
      return this.iArmyInAnotherProvinceSize;
   }

   protected final float getRelation(int i) {
      try {
         return this.civGameData.lRelation.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.civGameData.lRelation.add(0.0F);
         return 0.0F;
      }
   }

   protected final void setRelation(int iID, float nOpinion) {
      try {
         if (nOpinion > 100.0F) {
            nOpinion = 100.0F;
         } else if (nOpinion < -100.0F) {
            nOpinion = -100.0F;
         }

         this.civGameData.lRelation.set(iID, nOpinion);
      } catch (IndexOutOfBoundsException var4) {
         this.civGameData.lRelation.add(0.0F);
      }
   }

   protected final int getNonAggressionPact(int i) {
      try {
         return this.lNonAggressionPact.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.lNonAggressionPact.add(0);
         return 0;
      }
   }

   protected final boolean setNonAggressionPact(int iID, int iNumOfTurns) {
      try {
         if (iNumOfTurns < 0) {
            iNumOfTurns = 0;
         } else if (iNumOfTurns > 40) {
            iNumOfTurns = 40;
         }

         this.lNonAggressionPact.set(iID, iNumOfTurns);
         if (iNumOfTurns > 0) {
            for(int i = 0; i < this.lOpt_NonAggressionPact.size(); ++i) {
               if (this.lOpt_NonAggressionPact.get(i) == iID) {
                  return false;
               }
            }

            this.lOpt_NonAggressionPact.add(iID);
         } else {
            for(int i = 0; i < this.lOpt_NonAggressionPact.size(); ++i) {
               if (this.lOpt_NonAggressionPact.get(i) == iID) {
                  this.lOpt_NonAggressionPact.remove(i);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lNonAggressionPact.add(0);
      }

      return false;
   }

   protected final int getTruce(int i) {
      try {
         return this.lTruce.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.lTruce.add(0);
         return 0;
      }
   }

   protected final boolean setTruce(int iID, int iNumOfTurns) {
      try {
         if (iNumOfTurns < 0) {
            iNumOfTurns = 0;
         } else if (iNumOfTurns > 50) {
            iNumOfTurns = 50;
         }

         this.lTruce.set(iID, iNumOfTurns);
         if (iNumOfTurns > 0) {
            for(int i = 0; i < this.lOpt_Truce.size(); ++i) {
               if (this.lOpt_Truce.get(i) == iID) {
                  return false;
               }
            }

            this.lOpt_Truce.add(iID);
         } else {
            for(int i = 0; i < this.lOpt_Truce.size(); ++i) {
               if (this.lOpt_Truce.get(i) == iID) {
                  this.lOpt_Truce.remove(i);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lTruce.add(0);
      }

      return false;
   }

   protected final int getDefensivePact(int i) {
      try {
         return this.lDefensivePact.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.lDefensivePact.add(0);
         return 0;
      }
   }

   protected final boolean setDefensivePact(int iID, int iNumOfTurns) {
      try {
         if (iNumOfTurns < 0) {
            iNumOfTurns = 0;
         } else if (iNumOfTurns > 40) {
            iNumOfTurns = 40;
         }

         this.lDefensivePact.set(iID, iNumOfTurns);
         if (iNumOfTurns > 0) {
            for(int i = 0; i < this.lOpt_DefensivePact.size(); ++i) {
               if (this.lOpt_DefensivePact.get(i) == iID) {
                  return false;
               }
            }

            this.lOpt_DefensivePact.add(iID);
         } else {
            for(int i = 0; i < this.lOpt_DefensivePact.size(); ++i) {
               if (this.lOpt_DefensivePact.get(i) == iID) {
                  this.lOpt_DefensivePact.remove(i);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lDefensivePact.add(0);
      }

      return false;
   }

   protected final int getGuarantee(int i) {
      try {
         return this.lGuarantee.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.lGuarantee.add((byte)0);
         return 0;
      }
   }

   protected final boolean setGuarantee(int iID, int iNumOfTurns) {
      try {
         if (iNumOfTurns < 0) {
            iNumOfTurns = 0;
         } else if (iNumOfTurns > 100) {
            iNumOfTurns = 100;
         }

         this.lGuarantee.set(iID, (byte)iNumOfTurns);
         if (iNumOfTurns > 0) {
            for(int i = 0; i < this.lOpt_Guarantee.size(); ++i) {
               if (this.lOpt_Guarantee.get(i) == iID) {
                  return false;
               }
            }

            this.lOpt_Guarantee.add(iID);
         } else {
            for(int i = 0; i < this.lOpt_Guarantee.size(); ++i) {
               if (this.lOpt_Guarantee.get(i) == iID) {
                  this.lOpt_Guarantee.remove(i);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lGuarantee.add((byte)0);
      }

      return false;
   }

   protected final int getMilitaryAccess(int i) {
      try {
         return this.lMilitirayAccess.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.lMilitirayAccess.add((byte)0);
         return 0;
      }
   }

   protected final boolean setMilitaryAccess(int iID, int iNumOfTurns) {
      try {
         if (iNumOfTurns < 0) {
            iNumOfTurns = 0;
         } else if (iNumOfTurns > 40) {
            iNumOfTurns = 40;
         }

         this.lMilitirayAccess.set(iID, (byte)iNumOfTurns);
         if (iNumOfTurns > 0) {
            for(int i = 0; i < this.lOpt_MilitirayAccess.size(); ++i) {
               if (this.lOpt_MilitirayAccess.get(i) == iID) {
                  return false;
               }
            }

            this.lOpt_MilitirayAccess.add(iID);
         } else {
            for(int i = 0; i < this.lOpt_MilitirayAccess.size(); ++i) {
               if (this.lOpt_MilitirayAccess.get(i) == iID) {
                  this.lOpt_MilitirayAccess.remove(i);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lMilitirayAccess.add((byte)0);
      }

      return false;
   }

   protected final void disposeFlag() {
      if (this.civFlag != null) {
         this.civFlag.getTexture().dispose();
         this.civFlag = null;
      }
   }

   protected final void setFlag(Image nFlag) {
      this.disposeFlag();
      this.civFlag = nFlag;
   }

   protected final boolean loadFlag() {
      if (this.getCivTag().indexOf(";") > 0) {
         try {
            CFG.unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
            int tGenerateID = CFG.unionFlagsToGenerate_Manager.lFlags.size() - 1;
            String[] tempD = this.getCivTag().split(";");

            for(int i = 0; i < tempD.length; ++i) {
               CFG.unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).lTags.add(tempD[i]);
            }

            CFG.unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.CIV_ID_SMALL;
            CFG.unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).iID = this.getCivID();
            this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest);
         } catch (GdxRuntimeException var5) {
            CFG.exceptionStack(var5);
         } catch (RuntimeException var6) {
            CFG.exceptionStack(var6);
         }

         return true;
      } else {
         try {
            if (this.civFlag != null) {
               this.disposeFlag();
            }
         } catch (RuntimeException var11) {
            CFG.unionFlagsToGenerate_Manager.addFlagToLoad(this.getCivID());
            return false;
         }

         try {
            try {
               this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + this.civGameData.sCivTag + ".png")), Texture.TextureFilter.Nearest);
            } catch (GdxRuntimeException var8) {
               if (CFG.ideologiesManager.getIdeology(this.getIdeologyID()).REVOLUTIONARY) {
                  this.civFlag = new Image(
                     new Texture(Gdx.files.internal("game/flags/rb" + (this.getCivID() + this.getCivTag().charAt(0)) % 6 + ".png")),
                     Texture.TextureFilter.Nearest
                  );
                  return true;
               }

               try {
                  this.civFlag = new Image(
                     new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag) + ".png")),
                     Texture.TextureFilter.Nearest
                  );
               } catch (GdxRuntimeException var7) {
                  if (CFG.isAndroid()) {
                     try {
                        this.civFlag = new Image(
                           new Texture(
                              Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag)
                                       + "_FL.png"
                                 )
                           ),
                           Texture.TextureFilter.Nearest
                        );
                     } catch (GdxRuntimeException var4) {
                        this.civFlag = new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag)
                                       + "_FL.png"
                                 )
                           ),
                           Texture.TextureFilter.Nearest
                        );
                     }
                  } else {
                     this.civFlag = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.civGameData.sCivTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  }
               }
            }
         } catch (GdxRuntimeException var9) {
            this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest);
         } catch (RuntimeException var10) {
            CFG.unionFlagsToGenerate_Manager.addFlagToLoad(this.getCivID());
            return false;
         }

         return true;
      }
   }

   protected final Image getFlag() {
      return this.civFlag == null ? ImageManager.getImage(Images.randomCivilizationFlag) : this.civFlag;
   }

   protected final boolean getFlag_IsNull() {
      return this.civFlag == null;
   }

   protected final Civilization_Region getCivRegion(int i) {
      try {
         return this.lCivRegions.get(i);
      } catch (IndexOutOfBoundsException var3) {
         this.updateRegions = true;
         return new Civilization_Region();
      }
   }

   protected final int getCivRegionsSize() {
      return this.iCivRegionsSize;
   }

   protected final boolean getUpdateRegions() {
      return this.updateRegions;
   }

   protected final void setUpdateRegions(boolean updateRegions) {
      this.updateRegions = updateRegions;
   }

   protected final int getPuppetOfCivID() {
      return this.civGameData.iPuppetOfCivID;
   }

   protected final void setPuppetOfCivID(int iPuppetOfCivID) {
      if (this.civGameData.iPuppetOfCivID != this.iCivID && this.civGameData.iPuppetOfCivID != iPuppetOfCivID) {
         CFG.game.getCiv(this.civGameData.iPuppetOfCivID).removeVassal(this.iCivID);
      }

      this.civGameData.iPuppetOfCivID = iPuppetOfCivID;
      if (this.civGameData.iPuppetOfCivID != this.iCivID) {
         CFG.game.getCiv(this.civGameData.iPuppetOfCivID).addVassal(this.iCivID);
      }
   }

   protected final float getVassalLiberityDesire() {
      return this.civGameData.fVassalLiberityDisere;
   }

   protected final void setVassalLiberityDesire(float fLiberityDesire) {
      if (fLiberityDesire < 0.0F) {
         fLiberityDesire = 0.0F;
      } else if (fLiberityDesire > 100.0F) {
         fLiberityDesire = 100.0F;
      }

      this.civGameData.fVassalLiberityDisere = fLiberityDesire;
   }

   protected final void addVassal(int nCivID) {
      for(int i = 0; i < this.civGameData.lVassals.size(); ++i) {
         if (this.civGameData.lVassals.get(i).iCivID == nCivID) {
            return;
         }
      }

      this.civGameData.lVassals.add(new Vassal_GameData(nCivID));
      this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
   }

   protected final void removeVassal(int nCivID) {
      for(int i = 0; i < this.civGameData.lVassals.size(); ++i) {
         if (this.civGameData.lVassals.get(i).iCivID == nCivID) {
            this.civGameData.lVassals.remove(i);
            this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
            return;
         }
      }
   }

   protected final int getVassal_Tribute(int nCivID) {
      for(int i = 0; i < this.civGameData.lVassals.size(); ++i) {
         if (this.civGameData.lVassals.get(i).iCivID == nCivID) {
            return this.civGameData.lVassals.get(i).iTribute;
         }
      }

      this.civGameData.lVassals.add(new Vassal_GameData(nCivID));
      this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
      return 9;
   }

   protected final void setVassal_Tribute(int nCivID, int nTribute) {
      for(int i = 0; i < this.civGameData.lVassals.size(); ++i) {
         if (this.civGameData.lVassals.get(i).iCivID == nCivID) {
            this.civGameData.lVassals.get(i).setTribute(nTribute);
            return;
         }
      }

      this.civGameData.lVassals.add(new Vassal_GameData(nCivID));
      this.civGameData.iVassalsSize = this.civGameData.lVassals.size();
   }

   protected final boolean getIsPupet() {
      return this.iCivID != this.civGameData.iPuppetOfCivID;
   }

   protected final Move_Units getMoveUnits(int i) {
      return this.lMoveUnits.get(i);
   }

   protected final boolean isMovingUnitsFromProvinceID(int nProvinceID) {
      for(int i = 0; i < this.getMoveUnitsSize(); ++i) {
         if (this.getMoveUnits(i).getFromProvinceID() == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean isMovingUnitsToProvinceID(int nProvinceID) {
      for(int i = 0; i < this.getMoveUnitsSize(); ++i) {
         if (this.getMoveUnits(i).getToProvinceID() == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final int isMovingUnitsToProvinceID_Num(int nProvinceID) {
      for(int i = 0; i < this.getMoveUnitsSize(); ++i) {
         if (this.getMoveUnits(i).getToProvinceID() == nProvinceID) {
            return this.getMoveUnits(i).getNumOfUnits();
         }
      }

      return 0;
   }

   protected final Move_Units getMigrate(int i) {
      return this.lMigrate.get(i);
   }

   protected final Move_Units_Plunder getMoveUnits_Plunder(int i) {
      return this.lMove_Units_Plunder.get(i);
   }

   protected final RecruitArmy_Request getRecruitArmy(int i) {
      return this.lRecruitArmy.get(i);
   }

   protected final int getRecruitArmy_BasedOnProvinceID(int nProvinceID) {
      for(int i = 0; i < this.iRecruitArmySize; ++i) {
         if (this.lRecruitArmy.get(i).getProvinceID() == nProvinceID) {
            return this.lRecruitArmy.get(i).getArmy();
         }
      }

      return 0;
   }

   protected final int getRecruitArmySize() {
      return this.iRecruitArmySize;
   }

   protected final int getMoveUnitsSize() {
      return this.iMoveUnitsSize;
   }

   protected final int getMigrateSize() {
      return this.iMigrateSize;
   }

   protected final int getMoveUnitsPlunderSize() {
      return this.iMove_Units_PlunderSize;
   }

   protected final List<MoveUnits_Line> getCurrentRegroupArmyLine(int i) {
      return this.lCurrentRegroupArmyLine.get(i);
   }

   protected final RegroupArmy_Data getRegroupArmy(int i) {
      return this.civGameData.lRegroupArmy.get(i);
   }

   protected final int isRegoupingArmy_ToProvinceID(int toProvinceID) {
      for(int i = 0; i < this.civGameData.iRegroupArmySize; ++i) {
         if (this.civGameData.lRegroupArmy.get(i).getToProvinceID() == toProvinceID) {
            return this.civGameData.lRegroupArmy.get(i).getNumOfUnits();
         }
      }

      return 0;
   }

   protected final int getRegroupArmySize() {
      return this.civGameData.iRegroupArmySize;
   }

   protected final int getAllianceID() {
      return this.civGameData.iAllianceID;
   }

   protected final void setAllianceID(int iAllianceID) {
      this.civGameData.iAllianceID = iAllianceID;
   }

   protected final boolean getControlledByPlayer() {
      return !CFG.SPECTATOR_MODE && this.controlledByPlayer;
   }

   protected final void setControlledByPlayer(boolean controlledByPlayer) {
      this.controlledByPlayer = controlledByPlayer;
   }

   protected final int getAI_Style() {
      return this.civGameData.iAI_Style;
   }

   protected final void setAI_Style(int iAI_Style) {
      this.civGameData.iAI_Style = iAI_Style;
   }

   protected final CivPersonality getCivPersonality() {
      return this.civGameData.civPersonality;
   }

   protected final CivPlans getCivPlans() {
      return this.civGameData.civPlans;
   }

   protected final boolean getIsAvailable() {
      return this.isAvailable;
   }

   protected final void setIsAvailable(boolean isAvailable) {
      this.isAvailable = isAvailable;
   }

   protected final int countPopulation() {
      int nPopulation = 0;

      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         nPopulation += CFG.game.getProvince(this.getProvinceID(i)).getPopulationData().getPopulation();
      }

      return nPopulation;
   }

   protected final int countPopulation_WithoutOccupied() {
      int nPopulation = 0;

      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         if (!CFG.game.getProvince(this.getProvinceID(i)).isOccupied()) {
            nPopulation += CFG.game.getProvince(this.getProvinceID(i)).getPopulationData().getPopulation();
         }
      }

      return nPopulation;
   }

   protected final int countEconomy() {
      int nEconomy = 0;

      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         nEconomy += CFG.game.getProvince(this.getProvinceID(i)).getEconomy();
      }

      return nEconomy;
   }

   protected final int countEconomy_WithoutOccupied() {
      int nEconomy = 0;

      for(int i = 0; i < this.iNumOfProvinces; ++i) {
         if (!CFG.game.getProvince(this.getProvinceID(i)).isOccupied()) {
            nEconomy += CFG.game.getProvince(this.getProvinceID(i)).getEconomy();
         }
      }

      return nEconomy;
   }

   protected final float getTechnologyLevel() {
      return (float)this.civGameData.fTechnologyLevel / 100.0F;
   }

   protected final int getTechnologyLevel_INT() {
      return this.civGameData.fTechnologyLevel;
   }

   protected final void setTechnologyLevel(float nTechnologyLevel) {
      this.civGameData.fTechnologyLevel = (int)(nTechnologyLevel * 100.0F);
      if ((float)this.civGameData.fTechnologyLevel > 200.0F) {
         this.civGameData.fTechnologyLevel = 200;
      }
   }

   protected final void setTechnologyLevel_INT(int nTechnologyLevel) {
      this.civGameData.fTechnologyLevel = nTechnologyLevel;
      if ((float)this.civGameData.fTechnologyLevel > 200.0F) {
         this.civGameData.fTechnologyLevel = 200;
      }
   }

   protected final float getSpendings_Research() {
      return this.civGameData.fSpendings_Research;
   }

   protected final void setSpendings_Research(float fSpendings_Research) {
      if (this.getMoney() < -500L) {
         this.civGameData.fSpendings_Research = 0.0F;
      } else {
         this.civGameData.fSpendings_Research = fSpendings_Research;
         if (this.civGameData.fSpendings_Research < 0.0F) {
            this.civGameData.fSpendings_Research = 0.0F;
         } else if (this.civGameData.fSpendings_Research > 1.0F) {
            this.civGameData.fSpendings_Research = 1.0F;
         }
      }
   }

   protected final float getSpendings_Investments() {
      return this.civGameData.fSpendings_Investments;
   }

   protected final void setSpendings_Investments(float fSpendings_Investments) {
      this.civGameData.fSpendings_Investments = fSpendings_Investments;
      if (this.civGameData.fSpendings_Investments < 0.0F) {
         this.civGameData.fSpendings_Investments = 0.0F;
      } else if (this.civGameData.fSpendings_Investments > 1.0F) {
         this.civGameData.fSpendings_Investments = 1.0F;
      }
   }

   protected final float getSpendings_Goods() {
      return this.civGameData.fSpendings_Goods;
   }

   protected final void setSpendings_Goods(float fSpendings_Goods) {
      this.civGameData.fSpendings_Goods = fSpendings_Goods;
      if (this.civGameData.fSpendings_Goods < 0.0F) {
         this.civGameData.fSpendings_Goods = 0.0F;
      } else if (this.civGameData.fSpendings_Goods > 1.0F) {
         this.civGameData.fSpendings_Goods = 1.0F;
      }
   }

   protected final float getTaxationLevel() {
      return this.civGameData.fTaxationLevel;
   }

   protected final void setTaxationLevel(float fTaxationLevel) {
      this.civGameData.fTaxationLevel = fTaxationLevel;
      if (this.civGameData.fTaxationLevel < 0.0F) {
         this.civGameData.fTaxationLevel = 0.0F;
      } else if (this.civGameData.fTaxationLevel > 1.0F) {
         this.civGameData.fTaxationLevel = 1.0F;
      }
   }

   protected final int getDiplomacyPoints() {
      return this.civGameData.iDiplomacyPoints;
   }

   protected final void setDiplomacyPoints(int nDiplomacyPoints) {
      if ((float)nDiplomacyPoints > 85.0F + 85.0F * this.getTechnologyLevel() / 4.0F && nDiplomacyPoints > this.civGameData.iDiplomacyPoints) {
         nDiplomacyPoints = this.civGameData.iDiplomacyPoints + 1;
         if (nDiplomacyPoints > 170) {
            nDiplomacyPoints = 170;
         }
      }

      this.civGameData.iDiplomacyPoints = nDiplomacyPoints;
   }

   protected final int getRankPosition() {
      return this.iRankPosition;
   }

   protected final void setRankPosition(int iRankPosition) {
      this.iRankPosition = iRankPosition;
   }

   protected final int getRankScore() {
      return this.iRankScore;
   }

   protected final void setRankScore(int iRankScore) {
      this.iRankScore = iRankScore;
   }

   protected final int getIdeologyID() {
      return this.iIdeologyID;
   }

   protected final void setIdeologyID(int iIdeologyID) {
      this.iIdeologyID = iIdeologyID;
      this.setAI_Style(CFG.oAI.getAIStyle_ByTag(CFG.ideologiesManager.getIdeology(this.getIdeologyID()).AI_TYPE));
   }

   protected final int getSeaAccess() {
      return this.seaAccess;
   }

   protected final void setSeaAccess(int seaAccess) {
      this.seaAccess = seaAccess;
   }

   protected final void clearSeaAccess_Provinces() {
      this.seaAccess_Provinces.clear();
   }

   protected final void addSeaAccess_Provinces(int nProvinceID) {
      this.seaAccess_Provinces.add(nProvinceID);
   }

   protected final List<Integer> getSeaAccess_Provinces() {
      return this.seaAccess_Provinces;
   }

   protected final int getSeaAccess_Provinces_Size() {
      return this.seaAccess_Provinces.size();
   }

   protected final void clearSeaAccess_PortProvinces() {
      this.seaAccess_Port.clear();
   }

   protected final void addSeaAccess_PortProvinces(int nProvinceID) {
      this.seaAccess_Port.add(nProvinceID);
   }

   protected final List<Integer> getSeaAccess_PortProvinces() {
      return this.seaAccess_Port;
   }

   protected final int getSeaAccess_PortProvinces_Size() {
      return this.seaAccess_Port.size();
   }

   protected final int getBordersWithEnemy() {
      return this.bordersWithEnemy;
   }

   protected final void setBordersWithEnemy(int bordersWithEnemy) {
      this.bordersWithEnemy = bordersWithEnemy;
   }

   protected final boolean isAtWar() {
      return this.isAtWar;
   }

   protected final void setIsAtWar(boolean isAtWar) {
      this.isAtWar = isAtWar;
   }

   protected final boolean getCanExpandOnContinent() {
      return this.canExpandOnContinent;
   }

   protected final void setCanExpandOnContinent(boolean canExpandOnContinent) {
      this.canExpandOnContinent = canExpandOnContinent;
   }

   protected final int getNumOfNeighboringNeutralProvinces() {
      return this.iNumOfNeighboringNeutralProvinces;
   }

   protected final void setNumOfNeighboringNeutralProvinces(int iNumOfNeighboringNeutralProvinces) {
      this.iNumOfNeighboringNeutralProvinces = iNumOfNeighboringNeutralProvinces;
   }

   protected final void clearTagsCanForm() {
      this.sTagsCanForm.clear();
   }

   protected final int getTagsCanFormSize() {
      return this.sTagsCanForm.size();
   }

   protected final String getTagsCanForm(int i) {
      return this.sTagsCanForm.get(i);
   }

   protected final void addTagsCanForm(String nTag) {
      for(int i = 0; i < this.sTagsCanForm.size(); ++i) {
         if (this.sTagsCanForm.get(i).equals(nTag)) {
            return;
         }
      }

      this.sTagsCanForm.add(nTag);
   }

   protected final void removeTagsCanForm(int i) {
      this.sTagsCanForm.remove(i);
   }

   protected final void removeTagsCanForm(String nTag) {
      for(int i = 0; i < this.sTagsCanForm.size(); ++i) {
         if (this.sTagsCanForm.get(i).equals(nTag)) {
            this.sTagsCanForm.remove(i);
            return;
         }
      }
   }

   protected final float getResearchProgress() {
      return this.civGameData.fResearchProgress;
   }

   protected final void addResearchProgress(float fAdd) {
      this.civGameData.fResearchProgress += fAdd;
   }

   protected final void setResearchProgress(float fResearchProgress) {
      this.civGameData.fResearchProgress = fResearchProgress;
   }

   protected final boolean getIsPartOfHolyRomanEmpire() {
      return this.civGameData.isPartOfHolyRomaEmpire;
   }

   protected final void setIsPartOfHolyRomanEmpire(boolean isPartOfHolyRomaEmpire) {
      this.civGameData.isPartOfHolyRomaEmpire = isPartOfHolyRomaEmpire;
   }

   protected final void runNextEvent() {
      try {
         if (this.getControlledByPlayer()) {
            if (this.getEventsToRunSize() > 0) {
               Menu_InGame_Event.EVENT_ID = this.getEventsToRun(0);
               this.removeEventToRun(0);
               CFG.menuManager.rebuildInGame_Event();
            }
         } else {
            for(int i = this.getEventsToRunSize() - 1; i >= 0; --i) {
               Commands.addMessage("runEvent: " + this.getCivName() + ": " + CFG.eventsManager.getEvent(this.getEventsToRun(i)).getEventName());

               try {
                  int decistionTaken = 0;
                  int tempAIChanceTotal = 0;

                  for(int j = 0; j < CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.size(); ++j) {
                     tempAIChanceTotal += CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.get(j).iAIChance;
                  }

                  int randNum = CFG.oR.nextInt(tempAIChanceTotal);
                  int j = 0;

                  for(int countChance = 0; j < CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.size(); ++j) {
                     if (randNum >= countChance && randNum < countChance + CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.get(j).iAIChance) {
                        decistionTaken = j;
                        break;
                     }

                     countChance += CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.get(j).iAIChance;
                  }

                  if (CFG.eventsManager.getEvent(this.getEventsToRun(i)).getCivID() >= 0) {
                     CFG.game
                        .getCiv(CFG.eventsManager.getEvent(this.getEventsToRun(i)).getCivID())
                        .addEvent_DecisionTaken(CFG.eventsManager.getEvent(this.getEventsToRun(i)).getEventTag() + "_" + decistionTaken);
                  }

                  Commands.addMessage(
                     "runEvent: "
                        + this.getCivName()
                        + ": Decision: "
                        + CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.get(decistionTaken).sTitle
                  );
                  CFG.eventsManager.getEvent(this.getEventsToRun(i)).lDecisions.get(decistionTaken).executeDecision();
                  this.removeEventToRun(0);
               } catch (IndexOutOfBoundsException var7) {
                  CFG.exceptionStack(var7);
               }
            }
         }
      } catch (IndexOutOfBoundsException var8) {
      } catch (NullPointerException var9) {
      } catch (StackOverflowError var10) {
      } catch (IllegalArgumentException var11) {
      }
   }

   protected final int getEventsToRun(int i) {
      return this.lEventsToRun.get(i);
   }

   protected final void addEventToRunID(int id) {
      this.lEventsToRun.add(id);
   }

   protected final void removeEventToRun(int i) {
      this.lEventsToRun.remove(i);
   }

   protected final int getEventsToRunSize() {
      return this.lEventsToRun.size();
   }

   protected final void addEvent_DecisionTaken(String nEventDecTAG) {
      this.civGameData.lEvents_DecisionsTaken.add(nEventDecTAG);
   }

   protected final boolean getEvent_TookDecision(String nEventDecTAG) {
      for(int i = 0; i < this.civGameData.lEvents_DecisionsTaken.size(); ++i) {
         if (this.civGameData.lEvents_DecisionsTaken.get(i).equals(nEventDecTAG)) {
            return true;
         }
      }

      return false;
   }

   protected final Civilization_Diplomacy_GameData getCivilization_Diplomacy_GameData() {
      return this.civGameData.civilization_Diplomacy_GameData;
   }

   protected final Loan_GameData getLoan(int i) {
      return this.civGameData.lLoansTaken.get(i);
   }

   protected final int getLoansSize() {
      return this.civGameData.lLoansTaken.size();
   }

   protected final void addLoan(int iGoldPerTurn, int iDuration) {
      this.civGameData.lLoansTaken.add(new Loan_GameData(iGoldPerTurn, iDuration));
   }

   protected final void updateLoansNextTurn() {
      for(int i = 0; i < this.civGameData.lLoansTaken.size(); ++i) {
         --this.civGameData.lLoansTaken.get(i).iTurnsLeft;
         if (this.civGameData.lLoansTaken.get(i).iTurnsLeft <= 0) {
            this.civGameData.lLoansTaken.remove(i--);
            this.getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Repaid(this.getCivID(), 0));
         }
      }
   }

   protected final int getLoans_GoldTotalPerTurn() {
      int out = 0;

      for(int i = 0; i < this.civGameData.lLoansTaken.size(); ++i) {
         out += this.civGameData.lLoansTaken.get(i).iGoldPerTurn;
      }

      return out;
   }

   protected final void clearLoans() {
      this.civGameData.lLoansTaken.clear();
   }

   protected final void removeLoan(int i) {
      this.civGameData.lLoansTaken.remove(i);
   }

   protected final void addWarReparationsGets(int nCivID) {
      for(int i = 0; i < this.civGameData.lWarReparationsGets.size(); ++i) {
         if (this.civGameData.lWarReparationsGets.get(i).iFromCivID == nCivID) {
            this.civGameData.lWarReparationsGets.get(i).iTurnsLeft = 12;
            return;
         }
      }

      this.civGameData.lWarReparationsGets.add(new WarReparations(nCivID, 12));
   }

   protected final void addWarReparationsPay(int nCivID) {
      for(int i = 0; i < this.civGameData.lWarReparationsPay.size(); ++i) {
         if (this.civGameData.lWarReparationsPay.get(i).iFromCivID == nCivID) {
            this.civGameData.lWarReparationsPay.get(i).iTurnsLeft = 12;
            return;
         }
      }

      this.civGameData.lWarReparationsPay.add(new WarReparations(nCivID, 12));
   }

   protected final WarReparations getWarReparationsPays(int i) {
      return this.civGameData.lWarReparationsPay.get(i);
   }

   protected final int getWarReparationsPays_TurnsLeft(int nCivID) {
      for(int i = 0; i < this.civGameData.lWarReparationsPay.size(); ++i) {
         if (this.civGameData.lWarReparationsPay.get(i).iFromCivID == nCivID) {
            return this.civGameData.lWarReparationsPay.get(i).iTurnsLeft;
         }
      }

      return 0;
   }

   protected final WarReparations getWarReparationsGets(int i) {
      return this.civGameData.lWarReparationsGets.get(i);
   }

   protected final int getWarReparationsGets_TurnsLeft(int nCivID) {
      for(int i = 0; i < this.civGameData.lWarReparationsGets.size(); ++i) {
         if (this.civGameData.lWarReparationsGets.get(i).iFromCivID == nCivID) {
            return this.civGameData.lWarReparationsGets.get(i).iTurnsLeft;
         }
      }

      return 0;
   }

   protected final void runWarReparations() {
      for(int i = this.civGameData.lWarReparationsPay.size() - 1; i >= 0; --i) {
         if (this.civGameData.lWarReparationsPay.get(i).iTurnsLeft-- <= 0) {
            this.getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_WarReparationsRepaid_Green(this.civGameData.lWarReparationsPay.get(i).iFromCivID));
            this.civGameData.lWarReparationsPay.remove(i);
         }
      }

      for(int i = this.civGameData.lWarReparationsGets.size() - 1; i >= 0; --i) {
         if (this.civGameData.lWarReparationsGets.get(i).iTurnsLeft-- <= 0) {
            this.getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_WarReparationsRepaid(this.civGameData.lWarReparationsGets.get(i).iFromCivID));
            this.civGameData.lWarReparationsGets.remove(i);
         }
      }
   }

   protected final int getWarReparationsPaysSize() {
      return this.civGameData.lWarReparationsPay.size();
   }

   protected final int getWarReparationsGetsSize() {
      return this.civGameData.lWarReparationsGets.size();
   }

   protected final boolean addNewBonus(CivBonus_GameData nBonus) {
      if (nBonus.BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_PROSPERITY) {
         for(int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
            if (this.civGameData.lBonuses.get(i).BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_PROSPERITY) {
               return false;
            }
         }
      } else if (nBonus.BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_SCIENCE) {
         for(int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
            if (this.civGameData.lBonuses.get(i).BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_SCIENCE) {
               return false;
            }
         }
      } else if (nBonus.BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_MILITARY) {
         for(int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
            if (this.civGameData.lBonuses.get(i).BONUS_TYPE == CivBonus_Type.GOLDEN_AGE_MILITARY) {
               return false;
            }
         }
      }

      this.civGameData.lBonuses.add(nBonus);
      this.applyBonusChanges(nBonus);
      return true;
   }

   protected final void updateBonuses() {
      for(int i = 0; i < this.civGameData.lBonuses.size(); ++i) {
         --this.civGameData.lBonuses.get(i).iTurnsLeft;
         if (this.civGameData.lBonuses.get(i).iTurnsLeft <= 0) {
            this.applyBonusChanges_Expired(this.civGameData.lBonuses.get(i));
            this.civGameData.lBonuses.remove(i--);
         }
      }
   }

   protected final CivBonus_GameData getBonus(int i) {
      return this.civGameData.lBonuses.get(i);
   }

   protected final int getBonusesSize() {
      return this.civGameData.lBonuses.size();
   }

   private final void applyBonusChanges(CivBonus_GameData nBonus) {
      this.civGameData.fModifier_PopGrowth += nBonus.fModifier_PopGrowth;
      this.civGameData.fModifier_EconomyGrowth += nBonus.fModifier_EconomyGrowth;
      this.civGameData.fModifier_IncomeTaxation += nBonus.fModifier_IncomeTaxation;
      this.civGameData.fModifier_IncomeProduction += nBonus.fModifier_IncomeProduction;
      this.civGameData.fModifier_Research += nBonus.fModifier_Research;
      this.civGameData.fModifier_MilitaryUpkeep += nBonus.fModifier_MilitaryUpkeep;
      this.civGameData.fModifier_AttackBonus += nBonus.fModifier_AttackBonus;
      this.civGameData.fModifier_DefenseBonus += nBonus.fModifier_DefenseBonus;
      this.civGameData.fModifier_MovementPoints += nBonus.fModifier_MovementPoints;
   }

   private final void applyBonusChanges_Expired(CivBonus_GameData nBonus) {
      this.civGameData.fModifier_PopGrowth -= nBonus.fModifier_PopGrowth;
      this.civGameData.fModifier_EconomyGrowth -= nBonus.fModifier_EconomyGrowth;
      this.civGameData.fModifier_IncomeTaxation -= nBonus.fModifier_IncomeTaxation;
      this.civGameData.fModifier_IncomeProduction -= nBonus.fModifier_IncomeProduction;
      this.civGameData.fModifier_Research -= nBonus.fModifier_Research;
      this.civGameData.fModifier_MilitaryUpkeep -= nBonus.fModifier_MilitaryUpkeep;
      this.civGameData.fModifier_AttackBonus -= nBonus.fModifier_AttackBonus;
      this.civGameData.fModifier_DefenseBonus -= nBonus.fModifier_DefenseBonus;
      this.civGameData.fModifier_MovementPoints -= nBonus.fModifier_MovementPoints;
   }

   protected final float getModifier_PopGrowth() {
      return this.civGameData.fModifier_PopGrowth;
   }

   protected final void setModifier_PopGrowth(float fModifier_PopGrowth) {
      this.civGameData.fModifier_PopGrowth = fModifier_PopGrowth;
   }

   protected final float getModifier_EconomyGrowth() {
      return this.civGameData.fModifier_EconomyGrowth;
   }

   protected final void setModifier_EconomyGrowth(float fModifier_EconomyGrowth) {
      this.civGameData.fModifier_EconomyGrowth = fModifier_EconomyGrowth;
   }

   protected final float getModifier_IncomeTaxation() {
      return this.civGameData.fModifier_IncomeTaxation;
   }

   protected final float getModifier_Administation() {
      return this.civGameData.fModifier_Administration;
   }

   protected final void setModifier_IncomeTaxation(float fModifier_IncomeTaxation) {
      this.civGameData.fModifier_IncomeTaxation = fModifier_IncomeTaxation;
   }

   protected final float getModifier_IncomeProduction() {
      return this.civGameData.fModifier_IncomeProduction;
   }

   protected final void setModifier_IncomeProduction(float fModifier_IncomeProduction) {
      this.civGameData.fModifier_IncomeProduction = fModifier_IncomeProduction;
   }

   protected final float getModifier_Research() {
      return this.civGameData.fModifier_Research;
   }

   protected final void setModifier_Research(float fModifier_Research) {
      this.civGameData.fModifier_Research = fModifier_Research;
   }

   protected final float getModifier_MilitaryUpkeep() {
      return this.civGameData.fModifier_MilitaryUpkeep;
   }

   protected final void setModifier_MilitaryUpkeep(float fModifier_MilitaryUpkeep) {
      this.civGameData.fModifier_MilitaryUpkeep = fModifier_MilitaryUpkeep;
   }

   protected final float getModifier_AttackBonus() {
      return this.civGameData.fModifier_AttackBonus;
   }

   protected final void setModifier_AttackBonus(float fModifier_AttackBonus) {
      this.civGameData.fModifier_AttackBonus = fModifier_AttackBonus;
   }

   protected final float getModifier_DefenseBonus() {
      return this.civGameData.fModifier_DefenseBonus;
   }

   protected final void setModifier_DefenseBonus(float fModifier_DefenseBonus) {
      this.civGameData.fModifier_DefenseBonus = fModifier_DefenseBonus;
   }

   protected final float getModifier_MovementPoints() {
      return this.civGameData.fModifier_MovementPoints;
   }

   protected final void setModifier_MovementPoints(float fModifier_MovementPoints) {
      this.civGameData.fModifier_MovementPoints = fModifier_MovementPoints;
   }

   public int getGoldenAge_Science() {
      return this.civGameData.iGoldenAge_Science;
   }

   public void setGoldenAge_Science(int iGoldenAge_Science) {
      this.civGameData.iGoldenAge_Science = iGoldenAge_Science;
   }

   public int getGoldenAge_Military() {
      return this.civGameData.iGoldenAge_Military;
   }

   public void setGoldenAge_Military(int iGoldenAge_Miitary) {
      this.civGameData.iGoldenAge_Military = this.civGameData.iGoldenAge_Military;
   }

   public int getGoldenAge_Prosperity() {
      return this.civGameData.iGoldenAge_Prosperity;
   }

   public void setGoldenAge_Prosperity(int iGoldenAge_Prosperity) {
      this.civGameData.iGoldenAge_Prosperity = iGoldenAge_Prosperity;
   }

   protected final float getWarWeariness() {
      return this.civGameData.fWarWeariness;
   }

   protected final void setWarWeariness(float fWarWeariness) {
      if (fWarWeariness > 1.0F) {
         fWarWeariness = 1.0F;
      } else if (fWarWeariness < 0.0F) {
         fWarWeariness = 0.0F;
      }

      this.civGameData.fWarWeariness = fWarWeariness;
   }

   protected final void addBordersWithCivID(int nCivID) {
      for(int i = 0; i < this.iBorderWithCivsSize; ++i) {
         if (this.lBorderWithCivs.get(i).iWithCivID == nCivID) {
            ++this.lBorderWithCivs.get(i).iNumOfConnections;
            return;
         }
      }

      this.lBorderWithCivs.add(new AI_BordersWith(nCivID));
      ++this.iBorderWithCivsSize;
   }

   protected final boolean addHatedCiv(int nCivID) {
      for(int i = 0; i < this.getHatedCivsSize(); ++i) {
         if (nCivID == this.civGameData.lHatedCivs.get(i).iCivID) {
            return false;
         }
      }

      CFG.game.getCiv(nCivID).addHatedCiv_By(this.getCivID());
      this.civGameData.lHatedCivs.add(new Civilization_Hated_GameData(nCivID));
      this.civGameData.iHatedCivsSize = this.civGameData.lHatedCivs.size();
      return true;
   }

   protected final int getHatedCivsSize() {
      return this.civGameData.iHatedCivsSize;
   }

   protected final Civilization_Hated_GameData getHatedCiv(int i) {
      return this.civGameData.lHatedCivs.get(i);
   }

   protected final boolean isHatedCiv(int nCivID) {
      for(int i = this.getHatedCivsSize() - 1; i >= 0; --i) {
         if (this.civGameData.lHatedCivs.get(i).iCivID == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final void clearHatedCivs() {
      for(int i = 0; i < this.getHatedCivsSize(); ++i) {
         CFG.game.getCiv(this.civGameData.lHatedCivs.get(i).iCivID).removeHatedCiv_BY(this.getCivID());
      }

      this.civGameData.lHatedCivs.clear();
      this.civGameData.iHatedCivsSize = this.civGameData.lHatedCivs.size();
   }

   protected final void removeHatedCiv(int nCivID) {
      for(int i = this.getHatedCivsSize() - 1; i >= 0; --i) {
         if (this.civGameData.lHatedCivs.get(i).iCivID == nCivID) {
            CFG.game.getCiv(this.civGameData.lHatedCivs.get(i).iCivID).removeHatedCiv_BY(this.getCivID());
            this.civGameData.lHatedCivs.remove(i);
            this.civGameData.iHatedCivsSize = this.civGameData.lHatedCivs.size();
            return;
         }
      }
   }

   protected final int getHatedCivs_BySize() {
      return this.civGameData.iHatedCivs_BySize;
   }

   protected final int getHatedCiv_By(int i) {
      return this.civGameData.lHatedCivs_By.get(i);
   }

   protected final void addHatedCiv_By(int nCivID) {
      for(int i = 0; i < this.getHatedCivs_BySize(); ++i) {
         if (this.civGameData.lHatedCivs_By.get(i) == nCivID) {
            return;
         }
      }

      this.civGameData.lHatedCivs_By.add(nCivID);
      this.civGameData.iHatedCivs_BySize = this.civGameData.lHatedCivs_By.size();
   }

   protected final void removeHatedCiv_BY(int nCivID) {
      for(int i = this.getHatedCivs_BySize() - 1; i >= 0; --i) {
         if (this.civGameData.lHatedCivs_By.get(i) == nCivID) {
            this.civGameData.lHatedCivs_By.remove(i);
            this.civGameData.iHatedCivs_BySize = this.civGameData.lHatedCivs_By.size();
            return;
         }
      }
   }

   protected final boolean addFriendlyCiv(int nCivID) {
      for(int i = 0; i < this.civGameData.lFriendlyCivs.size(); ++i) {
         if (nCivID == this.civGameData.lFriendlyCivs.get(i).iCivID) {
            return false;
         }
      }

      this.civGameData.lFriendlyCivs.add(new Civilization_Friends_GameData(nCivID, Game_Calendar.TURN_ID));
      this.getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Friendly(nCivID));

      try {
         CFG.historyManager.addHistoryLog(new HistoryLog_FriendlyCivs(this.getCivID(), nCivID));
      } catch (NullPointerException var3) {
      } catch (IndexOutOfBoundsException var4) {
      }

      return true;
   }

   protected final void updateFriendlyCiv() {
   }

   protected final int getFriendlyCivsSize() {
      return this.civGameData.lFriendlyCivs.size();
   }

   protected final Civilization_Friends_GameData getFriendlyCiv(int i) {
      return this.civGameData.lFriendlyCivs.get(i);
   }

   protected final int isFriendlyCiv(int nCivID) {
      for(int i = this.civGameData.lFriendlyCivs.size() - 1; i >= 0; --i) {
         if (this.civGameData.lFriendlyCivs.get(i).iCivID == nCivID) {
            return (int)Math.ceil((double)this.civGameData.lFriendlyCivs.get(i).iSinceTurnID);
         }
      }

      return -1;
   }

   protected final void clearFreidnlyCivs() {
      this.civGameData.lFriendlyCivs.clear();
   }

   protected final void removeFriendlyCiv(int nCivID) {
      for(int i = this.civGameData.lFriendlyCivs.size() - 1; i >= 0; --i) {
         if (this.civGameData.lFriendlyCivs.get(i).iCivID == nCivID) {
            this.civGameData.lFriendlyCivs.remove(i);
            return;
         }
      }
   }

   protected final void addSentMessages(Civilization_SentMessages nSentMessage) {
      for(int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
         if (this.civGameData.lSentMessages.get(i).iToCivID == nSentMessage.iToCivID
            && this.civGameData.lSentMessages.get(i).messageType == nSentMessage.messageType) {
            this.civGameData.lSentMessages.get(i).iSentInTurnID = Game_Calendar.TURN_ID;
            return;
         }
      }

      this.civGameData.lSentMessages.add(nSentMessage);
   }

   protected final void clearSentMessages() {
      this.civGameData.lSentMessages.clear();
   }

   protected final void removeSentMessages(Message_Type nMessageType) {
      for(int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
         if (this.civGameData.lSentMessages.get(i).messageType == nMessageType) {
            this.civGameData.lSentMessages.remove(i);
         }
      }
   }

   protected final void removeSentMessage(int i) {
      this.civGameData.lSentMessages.remove(i);
   }

   protected final boolean messageWasSent(int nToCivID, Message_Type nMessageType) {
      for(int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
         if (this.civGameData.lSentMessages.get(i).iToCivID == nToCivID && this.civGameData.lSentMessages.get(i).messageType == nMessageType) {
            return true;
         }
      }

      return false;
   }

   protected final boolean messageWasSent(int nToCivID) {
      for(int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
         if (this.civGameData.lSentMessages.get(i).iToCivID == nToCivID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean messageWasSent(Message_Type nMessageType) {
      for(int i = this.civGameData.lSentMessages.size() - 1; i >= 0; --i) {
         if (this.civGameData.lSentMessages.get(i).messageType == nMessageType) {
            return true;
         }
      }

      return false;
   }

   protected final int getSentMessagesSize() {
      return this.civGameData.lSentMessages.size();
   }

   protected final Civilization_SentMessages getSentMessage(int i) {
      return this.civGameData.lSentMessages.get(i);
   }

   protected final float getStability() {
      return this.fStability;
   }

   protected final void setStability(float nStability) {
      nStability = Math.min(Math.max(nStability, 0.01F), 1.0F);
      this.fStability = nStability;
   }
}
