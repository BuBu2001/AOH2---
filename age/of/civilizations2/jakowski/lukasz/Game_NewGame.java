package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Game_NewGame {
   protected final void updateDeclareWar() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).civGameData.declareWar_CheckNextTurnID = 8
            + (int)((float)(CFG.oR.nextInt(45) + (int)((float)CFG.oR.nextInt(CFG.game.getCivsSize()) / 30.0F)) / Game_Calendar.AI_AGGRESSIVNESS);
         CFG.game.getCiv(i).civGameData.holdLookingForEnemy_UntilTurnID = (int)(
            2.0F + ((float)CFG.oR.nextInt(8) + (float)CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 50.0F) / Game_Calendar.AI_AGGRESSIVNESS
         );
         CFG.game.getCiv(i).civGameData.holdLookingForFriends_UntilTurnID = (int)(
            2.0F + ((float)CFG.oR.nextInt(8) + (float)CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 50.0F) / Game_Calendar.AI_AGGRESSIVNESS
         );
         CFG.game.getCiv(i).civGameData.allianceUpdate_TurnID = (int)(26.0F + (float)CFG.oR.nextInt(40) / Game_Calendar.AI_AGGRESSIVNESS);
         CFG.game.getCiv(i).civGameData.circledVassals_TurnID = (int)(60.0F + (float)CFG.oR.nextInt(60) / Game_Calendar.AI_AGGRESSIVNESS);
      }
   }

   protected final void newGame() {
      Game_Calendar.TURN_ID = 1;
      CFG.PLAYER_TURNID = 0;
      Game_Action.gameEnded = false;
      Gdx.app.log("AoC", "newGame: 0");
      CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
      SaveManager.saveRequest = false;
      CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
      Gdx.app.log("AoC", "newGame: 1");
      CFG.menuManager.setVisibleInGame_Event(false);
      CFG.viewsManager.disableAllViews();
      CFG.viewsManager.clearData();
      CFG.viewsManager = new ViewsManager();
      Gdx.app.log("AoC", "newGame: 2");
      if (CFG.RANDOM_PLACMENT || CFG.RANDOM_FILL) {
         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).resetArmies(0);
         }
      }

      Gdx.app.log("AoC", "newGame: 3");
      if (!CFG.FILL_THE_MAP) {
         Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
      }

      if (CFG.RANDOM_PLACMENT) {
         this.randomPlacment();
      }

      if (CFG.RANDOM_FILL) {
         this.randomFill();
      }

      Gdx.app.log("AoC", "newGame: 4");
      if (!CFG.FILL_THE_MAP) {
         Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
      }

      Gdx.app.log("AoC", "newGame: 5");
      if (!CFG.RANDOM_FILL && !CFG.RANDOM_PLACMENT && CFG.FILL_THE_MAP) {
         Gdx.app.log("AoC", "newGame: 5A");
         CFG.game.getGameScenarios().loadArmiesData();
      } else {
         CFG.game.getGameScenarios().buildProvincePopulationAndEconomy(false, false);
      }

      Gdx.app.log("AoC", "newGame: 6");
      if (CFG.TOTAL_WAR_MODE) {
         for(int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
               for(int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                  if (CFG.game.getCiv(j).getNumOfProvinces() > 0
                     && (CFG.game.getCiv(i).getAllianceID() <= 0 || CFG.game.getCiv(i).getAllianceID() != CFG.game.getCiv(j).getAllianceID())) {
                     CFG.game.setCivNonAggressionPact(i, j, 0);
                     CFG.game.setCivRelation_OfCivB(i, j, -100.0F);
                     CFG.game.setCivRelation_OfCivB(j, i, -100.0F);
                  }
               }
            }
         }
      }

      Gdx.app.log("AoC", "newGame: 7");
      Gdx.app.log("AoC", "newGame: 8");
      CFG.game.sortCivilizationsAZ();
      this.buildFormableCivilizations();
      if (CFG.SPECTATOR_MODE) {
         this.newGame_InitPlayers_SpectatorMode();
      } else {
         this.newGame_InitPlayers();
      }

      Gdx.app.log("AoC", "newGame: 9");

      try {
         if (!CFG.SPECTATOR_MODE) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCapitalProvinceID());
         }
      } catch (IndexOutOfBoundsException var3) {
      }

      this.build_StartingBuildings();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).buildNumOfUnits();
      }

      CFG.map.getMapCoordinates().setDisableMovingMap(false);
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
         CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
      }

      CFG.gameAction.updateCivsMovementPoints();
      CFG.gameAction.updateCivsDiplomacyPoints_StartTheGame();
      Gdx.app.log("AoC", "newGame: 10");
      CFG.gameAction.updateIsSupplied();
      this.build_ArmyInAnotherProvince();
      if (CFG.FOG_OF_WAR > 0) {
         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               CFG.PLAYER_TURNID = i;
               CFG.gameAction.buildFogOfWar(i);
               CFG.game.getPlayer(i).buildMetProvincesAndCivs();
            }

            CFG.PLAYER_TURNID = 0;

            for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
               CFG.game.getProvince(i).updateProvinceBorder();
            }

            Game_Render.updateDrawCivRegionNames_FogOfWar();
         } else {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               CFG.PLAYER_TURNID = i;
               CFG.gameAction.buildFogOfWar(i);
            }

            CFG.PLAYER_TURNID = 0;
         }
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).updateDrawArmy();
      }

      Gdx.app.log("AoC", "newGame: 11");
      CFG.gameAction.updateCivsHappiness();
      CFG.game_NextTurnUpdate.updateProvinceStability();
      Gdx.app.log("AoC", "newGame: 12");
      this.updateBudgetSpendings();
      Gdx.app.log("AoC", "newGame: 13");
      CFG.game_NextTurnUpdate.updateInflationPeakValue();
      CFG.game_NextTurnUpdate.updatePlayableProvinces();
      TechnologyManager.updateAverageTechLevel();
      Gdx.app.log("AoC", "newGame: 14");
      if (!CFG.TOTAL_WAR_MODE) {
         this.buildCurrentWars();
      }

      DiplomacyManager.sendUncivilizedMessages();
      DiplomacyManager.sendTechPointsMessages();
      this.buildAIPersonalities();
      if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
         this.sandboxMode();
      }

      CFG.holyRomanEmpire_Manager.getHRE().randomNextElections();
      Gdx.app.log("AoC", "newGame: 15");
      CFG.setActiveCivInfo(0);
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      CFG.timelapseManager.newGame();
      SaveManager.newGame();
      SaveManager.gameCanBeContinued = true;
      DiplomacyManager.buildFriendlyCivs();
      Gdx.app.log("AoC", "newGame: 16");
      VicotryManager.checkVictoryConditions();
      CFG.oAI.updateExpand();
      this.updateDeclareWar();
      Gdx.app.log("AoC", "newGame: END");
   }

   protected final void loadGame(int iLoadID) {
      CFG.game.setActiveProvinceID(-1);
      CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
      CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
      Game_Calendar.TURN_ID = 1;
      CFG.PLAYER_TURNID = 0;
      Game_Action.gameEnded = false;
      CFG.menuManager.setVisibleInGame_Event(false);
      CFG.viewsManager.disableAllViews();
      CFG.viewsManager.clearData();
      CFG.viewsManager = new ViewsManager();
      CFG.game.loadSavedGame(iLoadID);
      CFG.game.sortCivilizationsAZ();
      this.buildFormableCivilizations();
      if (CFG.SPECTATOR_MODE) {
         this.newGame_InitPlayers_SpectatorMode();
      } else {
         for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).setControlledByPlayer(false);
         }

         for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setControlledByPlayer(true);
         }
      }

      CFG.oAI.updateExpand();

      try {
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCapitalProvinceID());
      } catch (IndexOutOfBoundsException var3) {
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).buildNumOfUnits();
      }

      CFG.map.getMapCoordinates().setDisableMovingMap(false);
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
         CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
      }

      CFG.gameAction.updateCivsMovementPoints();
      CFG.gameAction.updateIsSupplied();
      this.build_ArmyInAnotherProvince();
      if (CFG.FOG_OF_WAR > 0) {
         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               CFG.PLAYER_TURNID = i;
               CFG.gameAction.buildFogOfWar(i);
            }

            CFG.PLAYER_TURNID = 0;

            for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
               CFG.game.getProvince(i).updateProvinceBorder();
            }

            Game_Render.updateDrawCivRegionNames_FogOfWar();
         } else {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               CFG.PLAYER_TURNID = i;
               CFG.gameAction.buildFogOfWar(i);
            }

            CFG.PLAYER_TURNID = 0;
         }
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).updateDrawArmy();
      }

      CFG.gameAction.moveRegroupArmy();
      CFG.gameAction.updateCivsHappiness();
      CFG.game_NextTurnUpdate.updateProvinceStability();
      this.updateBudgetSpendings();
      CFG.game_NextTurnUpdate.updateInflationPeakValue();
      CFG.game_NextTurnUpdate.updatePlayableProvinces();
      TechnologyManager.updateAverageTechLevel();
      if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
         this.sandboxMode();
      }

      CFG.setActiveCivInfo(0);
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      SaveManager.gameCanBeContinued = true;
   }

   protected final void build_ArmyInAnotherProvince() {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).build_ArmyInAnotherProvince();
      }
   }

   protected final void buildAIPersonalities() {
      List<Integer> lUncivilizedCivs = new ArrayList<>();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            lUncivilizedCivs.add(i);
         }
      }

      List<Integer> lUncivilizedSorted = new ArrayList<>();

      while(lUncivilizedCivs.size() > 0) {
         int tBestID = 0;

         for(int i = 1; i < lUncivilizedCivs.size(); ++i) {
            if (CFG.game.getCiv(lUncivilizedCivs.get(tBestID)).getTechnologyLevel() < CFG.game.getCiv(lUncivilizedCivs.get(i)).getTechnologyLevel()) {
               tBestID = i;
            }
         }

         lUncivilizedSorted.add(lUncivilizedCivs.get(tBestID));
         lUncivilizedCivs.remove(tBestID);
      }

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            CFG.game.getCiv(i).getCivPersonality().UNCIVILIZED_MIGRATE = 10 + CFG.oR.nextInt(65);
            CFG.game.getCiv(i).getCivPersonality().UNCIVILIZED_WILLING_TO_CIVILIZE = 15 + CFG.oR.nextInt(75);
         }
      }

      if (lUncivilizedSorted.size() > 0) {
         int top10 = Math.max(1, lUncivilizedSorted.size() / 10);

         for(int i = 0; i < top10 && i < lUncivilizedSorted.size(); ++i) {
            CFG.game.getCiv(lUncivilizedSorted.get(i)).getCivPersonality().UNCIVILIZED_MIGRATE = 55 + CFG.oR.nextInt(45);
            CFG.game.getCiv(lUncivilizedSorted.get(i)).getCivPersonality().UNCIVILIZED_WILLING_TO_CIVILIZE = 55 + CFG.oR.nextInt(45);
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).setCoreCapitalProvinceID(CFG.game.getCiv(i).getCapitalProvinceID());
      }
   }

   protected final void buildCurrentWars() {
      for(int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
         for(int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
            if ((int)CFG.game.getCivRelation_OfCivB(i, j) == -100) {
               CFG.game.addWarData(i, j);
               CFG.game.getCiv(i).setIsAtWar(true);
               CFG.game.getCiv(j).setIsAtWar(true);
               CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(j, i));
               CFG.game.getCiv(j).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_War(i, j));
            }
         }
      }
   }

   protected final void build_StartingBuildings() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.oAI.getAI_Style(CFG.game.getCiv(i).getAI_Style()).buildStartingBuildings(i);
      }
   }

   protected final void newRandomGame() {
      Game_Calendar.TURN_ID = 1;
      CFG.PLAYER_TURNID = 0;
      SaveManager.saveRequest = false;
      Game_Action.gameEnded = false;
      CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
      CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
      CFG.menuManager.setVisibleInGame_Event(false);
      CFG.viewsManager.disableAllViews();
      CFG.viewsManager.clearData();
      CFG.viewsManager = new ViewsManager();
      Game_Calendar.updateAge();

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).resetArmies(0);
      }

      if (CFG.RANDOM_FILL) {
         this.randomFill();
         CFG.game.getGameScenarios().buildProvincePopulationAndEconomy(false, false);
      }

      CFG.game.sortCivilizationsAZ();
      this.buildFormableCivilizations();

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            CFG.game.getProvince(i).buildProvinceCore();
         }
      }

      if (CFG.SPECTATOR_MODE) {
         this.newGame_InitPlayers_SpectatorMode();
      } else {
         this.newGame_InitPlayers();
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCapitalProvinceID());
      }

      this.build_StartingBuildings();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getCapitalProvinceID() >= 0) {
            CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).updateArmy(CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals());
         }

         CFG.game.getCiv(i).buildNumOfUnits();
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0 && CFG.game.getProvince(i).getCivID() == 0) {
            CFG.game.getProvince(i).updateArmy(CFG.randomGameManager.getNeutralArmy());
         }
      }

      CFG.map.getMapCoordinates().setDisableMovingMap(false);
      CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
      this.buildAIPersonalities();
      DiplomacyManager.sendUncivilizedMessages();
      DiplomacyManager.sendTechPointsMessages();
      CFG.gameAction.updateCivsMovementPoints();
      CFG.gameAction.updateCivsDiplomacyPoints_StartTheGame();
      CFG.game.buildWastelandLevels();
      CFG.gameAction.updateIsSupplied();
      this.build_ArmyInAnotherProvince();
      if (CFG.FOG_OF_WAR > 0) {
         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               CFG.PLAYER_TURNID = i;
               CFG.gameAction.buildFogOfWar(i);
               CFG.game.getPlayer(i).buildMetProvincesAndCivs();
            }

            CFG.PLAYER_TURNID = 0;

            for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
               CFG.game.getProvince(i).updateProvinceBorder();
            }

            Game_Render.updateDrawCivRegionNames_FogOfWar();
         } else {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               CFG.PLAYER_TURNID = i;
               CFG.gameAction.buildFogOfWar(i);
            }

            CFG.PLAYER_TURNID = 0;
         }
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).updateDrawArmy();
      }

      CFG.gameAction.updateCivsHappiness();
      CFG.game_NextTurnUpdate.updateProvinceStability();
      this.updateBudgetSpendings();
      CFG.game_NextTurnUpdate.updateInflationPeakValue();
      CFG.game_NextTurnUpdate.updatePlayableProvinces();
      TechnologyManager.updateAverageTechLevel();
      if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
         this.sandboxMode();
      }

      CFG.setActiveCivInfo(0);
      CFG.game_NextTurnUpdate.buildLevelsOfCities();
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      CFG.timelapseManager.newGame();
      SaveManager.newGame();
      SaveManager.gameCanBeContinued = true;
      VicotryManager.checkVictoryConditions();
      CFG.oAI.updateExpand();
      this.updateDeclareWar();
   }

   protected final void updateTrueOwners() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
            CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).setTrueOwnerOfProvince(i);
         }
      }
   }

   protected final void updateBudgetSpendings() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(i);
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game_NextTurnUpdate.updateSpendingsOfCiv(i, CFG.game.getCiv(i).iBudget);
      }
   }

   protected final void sandboxMode() {
      if (!CFG.SPECTATOR_MODE) {
         for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getPlayer(i).getCivID() > 0 && CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
               CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setMoney(9999999L);
               CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setMovePoints(999);
               CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setDiplomacyPoints(999);
            }
         }
      }
   }

   private final void newGame_InitPlayers() {
      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         if (CFG.game.getPlayer(i).getCivID() <= 0) {
            CFG.game.randomPlayerCivilizations(i);
         }
      }

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).setControlledByPlayer(false);
      }

      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         if (CFG.game.getPlayer(i).getCivID() > 0) {
            CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setControlledByPlayer(true);
         }
      }

      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         CFG.game.getPlayer(i).loadPlayersFlag();
      }
   }

   protected final void newGame_InitPlayers_SpectatorMode() {
      CFG.game.initPlayers();
      CFG.game.getPlayer(0).setCivID(1);
      CFG.FOG_OF_WAR = 0;

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).setControlledByPlayer(false);
      }

      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         CFG.game.getPlayer(i).loadPlayersFlag();
      }
   }

   protected final void randomPlacment() {
      Random oR = new Random();
      List<Integer> lExistingCivs = new ArrayList<>();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getIsAvailable()) {
            lExistingCivs.add(i);
         }

         CFG.game.getCiv(i).clearProvinces_FillTheMap(false);
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).setCivID_Just(0);
         CFG.game.getProvince(i).setIsCapital(false);
         int j = 1;

         while(j < CFG.game.getProvince(i).getCivsSize()) {
            CFG.game.getProvince(i).removeArmy(j);
         }
      }

      for(int i = 0; i < lExistingCivs.size(); ++i) {
         Gdx.app.log("AoC", "RP: 2 -> " + i);
         this.findRandomCapital(lExistingCivs.get(i), oR);
      }

      if (!CFG.RANDOM_FILL) {
         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            for(int j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
               CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(false, i);
            }
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).updateProvinceBorder();
      }

      for(int i = 0; i < lExistingCivs.size(); ++i) {
         CFG.game.getCiv(lExistingCivs.get(i)).addProvince(CFG.game.getCiv(lExistingCivs.get(i)).getCapitalProvinceID());
      }
   }

   protected final void findRandomCapital(int nCivID, Random oR) {
      try {
         int tempCapitalID = 0;
         int iNumOfItterations = 0;

         while(true) {
            tempCapitalID = this.getRandomLandProvinceID(oR);
            if (!CFG.game.getProvince(tempCapitalID).getIsCapital()) {
               boolean found = true;

               for(int i = 0; i < CFG.game.getProvince(tempCapitalID).getNeighboringProvincesSize(); ++i) {
                  if (CFG.game.getProvince(CFG.game.getProvince(tempCapitalID).getNeighboringProvinces(i)).getIsCapital()) {
                     found = false;
                     break;
                  }
               }

               if (found) {
                  CFG.game.getCiv(nCivID).setCapitalProvinceID(tempCapitalID);
                  CFG.game.getProvince(tempCapitalID).setCivID_LoadScenario(nCivID);
                  CFG.game.getProvince(tempCapitalID).setIsCapital(true);
                  break;
               }
            }

            if (++iNumOfItterations > 100) {
               for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                  if (!CFG.game.getProvince(i).getSeaProvince() && !CFG.game.getProvince(i).getIsCapital() && CFG.game.getProvince(i).getWasteland() < 0) {
                     CFG.game.getCiv(nCivID).setCapitalProvinceID(i);
                     CFG.game.getProvince(i).setCivID_LoadScenario(nCivID);
                     CFG.game.getProvince(i).setIsCapital(true);
                     break;
                  }
               }

               return;
            }
         }
      } catch (StackOverflowError var7) {
         Gdx.app.log("AoC", "NEWGMAE RANDOM PLACE");

         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(i).getSeaProvince() && !CFG.game.getProvince(i).getIsCapital() && CFG.game.getProvince(i).getWasteland() < 0) {
               CFG.game.getCiv(nCivID).setCapitalProvinceID(i);
               CFG.game.getProvince(i).setCivID_LoadScenario(nCivID);
               CFG.game.getProvince(i).setIsCapital(true);
               break;
            }
         }
      }
   }

   private final int getRandomLandProvinceID(Random oR) {
      int tID = oR.nextInt(CFG.game.getProvincesSize());
      return !CFG.game.getProvince(tID).getSeaProvince() && CFG.game.getProvince(tID).getWasteland() < 0 ? tID : this.getRandomLandProvinceID(oR);
   }

   protected final void randomFill() {
      Gdx.app.log("AoC", "RF 1");
      List<Integer> lLandProvinces = new ArrayList<>();
      List<Integer> lWas = new ArrayList<>();

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && !CFG.game.getProvince(i).getIsCapital() && CFG.game.getProvince(i).getWasteland() < 0) {
            lLandProvinces.add(i);
            lWas.add(0);
            CFG.game.getProvince(i).setCivID(0, false, false);
            int j = 1;

            while(j < CFG.game.getProvince(i).getCivsSize()) {
               CFG.game.getProvince(i).removeArmy(j);
            }
         }
      }

      int tProvinceID = 0;
      int tCivID = 0;
      List<Integer> lExistingCivs = new ArrayList<>();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getIsAvailable()) {
            lExistingCivs.add(i);
         }
      }

      Gdx.app.log("AoC", "RF 2");
      Random oR = new Random();
      List<Integer> lNeighCivs = new ArrayList<>();

      while(lLandProvinces.size() > 0) {
         tProvinceID = oR.nextInt(lLandProvinces.size());
         lNeighCivs.clear();

         for(int i = 0; i < CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getCivID() != 0) {
               lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getCivID());
               lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getCivID());
               if (CFG.game
                     .getCiv(CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getCivID())
                     .getCapitalProvinceID()
                  == lLandProvinces.get(tProvinceID)) {
                  for(int u = 0;
                     u
                        < 5
                           + 30
                              / CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getCivID())
                                 .getNumOfProvinces()
                           + (CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getIsCapital() ? 60 : 0);
                     ++u
                  ) {
                     lNeighCivs.add(CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringProvinces(i)).getCivID());
                  }
               }
            }
         }

         for(int i = 0; i < CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringSeaProvincesSize(); ++i) {
            for(int j = 0;
               j < CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(i)).getNeighboringProvincesSize();
               ++j
            ) {
               if (CFG.game
                        .getProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(i))
                              .getNeighboringProvinces(j)
                        )
                        .getCivID()
                     != 0
                  && !CFG.game
                     .getProvince(
                        CFG.game.getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(i)).getNeighboringProvinces(j)
                     )
                     .getSeaProvince()) {
                  lNeighCivs.add(
                     CFG.game
                        .getProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(lLandProvinces.get(tProvinceID)).getNeighboringSeaProvinces(i))
                              .getNeighboringProvinces(j)
                        )
                        .getCivID()
                  );
               }
            }
         }

         if (lNeighCivs.size() == 0) {
            if (lWas.get(tProvinceID) <= 4) {
               lWas.set(tProvinceID, lWas.get(tProvinceID) + 1);
               continue;
            }

            tCivID = lExistingCivs.get(oR.nextInt(lExistingCivs.size()));
         } else {
            tCivID = lNeighCivs.get(oR.nextInt(lNeighCivs.size()));
         }

         CFG.game.getProvince(lLandProvinces.get(tProvinceID)).setCivID(tCivID, false, false);
         lLandProvinces.remove(tProvinceID);
         lWas.remove(tProvinceID);
      }

      Gdx.app.log("AoC", "RF 3");
      boolean changeOwner = true;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()
            && !CFG.game.getProvince(i).getIsCapital()
            && CFG.game.getProvince(i).getWasteland() < 0
            && CFG.game.getProvince(i).getNeighboringProvincesSize() > 0) {
            changeOwner = true;

            for(int j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()) {
                  changeOwner = false;
                  break;
               }

               if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(0)).getCivID()
                  != CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID()) {
                  changeOwner = false;
                  break;
               }
            }

            if (changeOwner) {
               CFG.game.getProvince(i).setCivID(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(0)).getCivID(), false, false);
            }
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(i).getCapitalProvinceID() < 0) {
            CFG.game.getCiv(i).setCapitalProvinceID(CFG.game.getCiv(i).getProvinceID(0));
         }
      }

      Gdx.app.log("AoC", "RF 4");
      this.updateTrueOwners();
      CFG.game.buildCivilizationsRegions();
      Gdx.app.log("AoC", "RF END");
   }

   protected final void buildFormableCivilizations() {
      List<String> tempTags = new ArrayList<>();

      try {
         if (CFG.readLocalFiles()) {
            try {
               FileHandle tempFileT2 = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
               String tempT2 = tempFileT2.readString();
               String[] tagsSPLITED2 = tempT2.split(";");
               int i = 0;

               for(int iSize = tagsSPLITED2.length; i < iSize; ++i) {
                  tempTags.add(tagsSPLITED2[i]);
               }
            } catch (GdxRuntimeException var13) {
            }

            try {
               FileHandle tempFileT = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
               String tempT = tempFileT.readString();
               String[] tagsSPLITED = tempT.split(";");
               int i = 0;

               for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
                  boolean add = true;

                  for(int j = 0; j < tempTags.size(); ++j) {
                     if (tagsSPLITED[i].equals(tempTags.get(j))) {
                        add = false;
                     }
                  }

                  if (add) {
                     tempTags.add(tagsSPLITED[i]);
                  }
               }
            } catch (GdxRuntimeException var12) {
            }
         } else {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            int i = 0;

            for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
               tempTags.add(tagsSPLITED[i]);
            }
         }
      } catch (GdxRuntimeException var14) {
      }

      for(int i = tempTags.size() - 1; i >= 0; --i) {
         try {
            try {
               FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(i));
               CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
            } catch (GdxRuntimeException var9) {
               FileHandle filex = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(i));
               CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(filex.readBytes());
            }

            if (CFG.formableCivs_GameData != null) {
               for(int j = CFG.formableCivs_GameData.getClaimantsSize() - 1; j >= 0; --j) {
                  for(int k = 1; k < CFG.game.getCivsSize(); ++k) {
                     if (CFG.game.getCiv(k).getCivTag().equals(CFG.formableCivs_GameData.getClaimant(j))
                        || CFG.ideologiesManager.getRealTag(CFG.game.getCiv(k).getCivTag()).equals(CFG.formableCivs_GameData.getClaimant(j))
                           && !CFG.ideologiesManager
                              .getRealTag(CFG.game.getCiv(k).getCivTag())
                              .equals(CFG.ideologiesManager.getRealTag(CFG.formableCivs_GameData.getFormableCivTag()))) {
                        CFG.game.getCiv(k).addTagsCanForm(CFG.formableCivs_GameData.getFormableCivTag());
                     }
                  }
               }
            }
         } catch (ClassNotFoundException var10) {
         } catch (IOException var11) {
         }

         CFG.formableCivs_GameData.clearProvinces();
         CFG.formableCivs_GameData = null;
      }
   }

   protected final void updateFormableCivilizations(int nCivID) {
      List<String> tempTags = new ArrayList<>();

      try {
         if (CFG.readLocalFiles()) {
            try {
               FileHandle tempFileT2 = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
               String tempT2 = tempFileT2.readString();
               String[] tagsSPLITED2 = tempT2.split(";");
               int i = 0;

               for(int iSize = tagsSPLITED2.length; i < iSize; ++i) {
                  tempTags.add(tagsSPLITED2[i]);
               }
            } catch (GdxRuntimeException var14) {
            }

            try {
               FileHandle tempFileT = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
               String tempT = tempFileT.readString();
               String[] tagsSPLITED = tempT.split(";");
               int i = 0;

               for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
                  boolean add = true;

                  for(int j = 0; j < tempTags.size(); ++j) {
                     if (tagsSPLITED[i].equals(tempTags.get(j))) {
                        add = false;
                     }
                  }

                  if (add) {
                     tempTags.add(tagsSPLITED[i]);
                  }
               }
            } catch (GdxRuntimeException var13) {
            }
         } else {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            int i = 0;

            for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
               tempTags.add(tagsSPLITED[i]);
            }
         }
      } catch (GdxRuntimeException var15) {
      }

      CFG.game.getCiv(nCivID).clearTagsCanForm();
      int i = 0;

      for(int iSize = tempTags.size(); i < iSize; ++i) {
         try {
            try {
               FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(i));
               CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
            } catch (GdxRuntimeException var10) {
               FileHandle filex = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + (String)tempTags.get(i));
               CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(filex.readBytes());
            }

            if (CFG.formableCivs_GameData != null) {
               for(int j = CFG.formableCivs_GameData.getClaimantsSize() - 1; j >= 0; --j) {
                  if (CFG.game.getCiv(nCivID).getCivTag().equals(CFG.formableCivs_GameData.getClaimant(j))
                     || CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()).equals(CFG.formableCivs_GameData.getClaimant(j))
                        && !CFG.ideologiesManager
                           .getRealTag(CFG.game.getCiv(nCivID).getCivTag())
                           .equals(CFG.ideologiesManager.getRealTag(CFG.formableCivs_GameData.getFormableCivTag()))) {
                     CFG.game.getCiv(nCivID).addTagsCanForm(CFG.formableCivs_GameData.getFormableCivTag());
                  }
               }
            }
         } catch (ClassNotFoundException var11) {
         } catch (IOException var12) {
         }

         CFG.formableCivs_GameData.clearProvinces();
         CFG.formableCivs_GameData = null;
      }
   }
}
