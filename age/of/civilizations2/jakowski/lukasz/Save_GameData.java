package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Save_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Save_Civ_GameData> lCivsData = new ArrayList<>();
   protected List<Save_CivDiplo_GameData> lCivsDiploData = new ArrayList<>();
   protected List<Save_Provinces_GameData> lProvincesData = new ArrayList<>();
   protected List<Save_Player_GameData> lPlayers = new ArrayList<>();
   protected List<Alliance> lAlliances = new ArrayList<>();
   protected List<War_GameData> lWars = new ArrayList<>();
   protected List<PeaceTreaty_GameData_MessageData> lPeaceTreaties = new ArrayList<>();
   protected List<Plague_GameData> lPlagues_INGAME = new ArrayList<>();
   protected HolyRomanEmpire_GameData holyRomanEmpire_GameData;
   protected Events_GameData eventsGameData;
   protected int iTurnID;
   protected int TURNS_SINCE_LAST_WAR;
   protected int iDay;
   protected int iMonth;
   protected int iYear;
   protected int DIFFICULTY;
   protected float GAME_SPEED;
   protected int FOG_OF_WAR;
   protected boolean SPECTATOR_MODE;
   protected boolean SANDBOX_MODE;
   protected boolean ENABLE_COLONIZATION;
   protected boolean ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
   protected float COLONIZATION_TECH_LEVEL = 0.8F;
   protected int STARTING_POPULATION;
   protected int STARTING_ECONOMY;
   protected float POPULATION_GROWTH_RATE_MODIFIER;
   protected float ECONOMY_GROWTH_RATE_MODIFIER;
   protected float DISEASES_DEATH_REATE_MODIFIER;
   protected int VICTORY_CONTROL_PROVINCES_PERC;
   protected int VICTORY_LIMIT_OF_TURNS;
   protected float VICTORY_TECHNOLOGY;
   protected String sActiveScenarioTag = "";

   protected final void buildData() {
      this.iTurnID = Game_Calendar.TURN_ID;
      this.TURNS_SINCE_LAST_WAR = Game_Calendar.TURNS_SINCE_LAST_WAR;
      this.iDay = Game_Calendar.currentDay;
      this.iMonth = Game_Calendar.currentMonth;
      this.iYear = Game_Calendar.currentYear;
      this.GAME_SPEED = Game_Calendar.GAME_SPEED;
      this.SANDBOX_MODE = CFG.SANDBOX_MODE;
      this.ENABLE_COLONIZATION = Game_Calendar.ENABLE_COLONIZATION;
      this.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
      this.COLONIZATION_TECH_LEVEL = Game_Calendar.COLONIZATION_TECH_LEVEL;
      this.STARTING_POPULATION = CFG.game.getGameScenarios().getScenario_StartingPopulation();
      this.STARTING_ECONOMY = CFG.game.getGameScenarios().getScenario_StartingEconomy();
      this.POPULATION_GROWTH_RATE_MODIFIER = CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier();
      this.ECONOMY_GROWTH_RATE_MODIFIER = CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier();
      this.DISEASES_DEATH_REATE_MODIFIER = CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier();
      this.VICTORY_CONTROL_PROVINCES_PERC = VicotryManager.VICTORY_CONTROL_PROVINCES_PERC;
      this.VICTORY_LIMIT_OF_TURNS = VicotryManager.VICTORY_LIMIT_OF_TURNS;
      this.VICTORY_TECHNOLOGY = VicotryManager.VICTORY_TECHNOLOGY;
      this.FOG_OF_WAR = CFG.FOG_OF_WAR;
      this.SPECTATOR_MODE = CFG.SPECTATOR_MODE;
      this.DIFFICULTY = CFG.DIFFICULTY;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.lCivsData.add(CFG.game.getCiv(i).civGameData);
         this.lCivsDiploData.add(new Save_CivDiplo_GameData(i));
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         this.lProvincesData.add(CFG.game.getProvince(i).saveProvinceData);
      }

      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         this.lPlayers.add(CFG.game.getPlayer(i).savePlayer);
      }

      for(int i = 0; i < CFG.game.getAlliancesSize(); ++i) {
         this.lAlliances.add(CFG.game.getAlliance(i));
      }

      for(int i = 0; i < CFG.game.getWarsSize(); ++i) {
         this.lWars.add(CFG.game.getWar(i));
      }

      for(int i = 0; i < CFG.game.lPeaceTreaties.size(); ++i) {
         this.lPeaceTreaties.add(CFG.game.lPeaceTreaties.get(i));
      }

      for(int i = 0; i < CFG.plagueManager.lPlagues_INGAME.size(); ++i) {
         this.lPlagues_INGAME.add(CFG.plagueManager.lPlagues_INGAME.get(i));
      }

      this.holyRomanEmpire_GameData = CFG.holyRomanEmpire_Manager.getHRE();
      this.eventsGameData = CFG.eventsManager.eventsGD;
      if (this.sActiveScenarioTag.length() <= 0) {
         if (CFG.game.getGameScenarios().sActiveScenarioTag.length() <= 0) {
            try {
               CFG.game.getGameScenarios().sActiveScenarioTag = CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID());
            } catch (IndexOutOfBoundsException var2) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var2);
               }
            }
         }

         this.sActiveScenarioTag = CFG.game.getGameScenarios().sActiveScenarioTag;
      }
   }
}
