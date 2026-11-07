package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Game_Action {
   private Game_Action.TurnStates activeTurnAction = Game_Action.TurnStates.INPUT_ORDERS;
   private MoveUnits_TurnData currentMoveUnits = null;
   private int iPlayerAttack_ShowArmyInProvinceID = -1;
   protected boolean SHOW_REPORT = false;
   private Turn_Actions turnActions;
   private Turn_NewTurn turnNewTurn;
   protected boolean updatePosOfMap_NewTurn = false;
   protected static final float POINTS_PER_ENEMY = 6.0F;
   protected static float RISE_REVOLT_RISK_HAPPINESS = 0.56F;
   protected static float RISE_REVOLT_RISK_STABILITY = 0.62F;
   protected int eRTO_START = 0;
   protected int eRTO_START2 = 0;
   protected int eRTO_START3 = 0;
   protected static final int MAX_RELATION = 30;
   protected static final int MIN_RELATION = -20;
   protected static final float MAX_RELATION_DIFF = 0.295F;
   protected static final float MIN_RELATION_DIFF = 0.0145F;
   protected static final float UPRISE_MIN = 0.16F;
   protected static final float UPRISE_IGNITE = 0.64F;
   protected static final int SPAWN_REVOLUTIONARY_ARMY_MIN = 10;
   protected static final int SPAWN_REVOLUTIONARY_ARMY_RANDOM = 50;
   protected int diceAggressors;
   protected int diceDefenders;
   protected int diceAggressorsCivID;
   protected int diceDefendersCivID;
   protected static final float DICE_ROLL_BONUS = 2.5F;
   protected static final int TECHNOLGY_LEVEL_BONUS_ARMY = 18;
   protected static final float DEFENSE_BONUS_LOSS_PER_TURN_FOR_NOT_SUPPLIED_PROVINCE = 0.1F;
   protected static final int NOT_SUPPLIED_PROVINCE_STRAVE_START_NUM_OF_TURNS = 2;
   protected static final float NOT_SUPPLIED_PROVINCE_STRAVE__PERC_PER_TURN = 0.04F;
   protected static final int NOT_SUPPLIED_PROVINCE_LOOSE_CONTROL = 10;
   protected static boolean gameEnded = false;
   protected static final float RECRUITABLE_ARMY_OWN_POP = 0.175F;
   protected static final float RECRUITABLE_ARMY_OTHER_POP_ALLIANCE = 0.125F;
   protected static final float RECRUITABLE_ARMY_NEUTRAL_POP = 0.0675F;
   protected static final float RECRUITABLE_ARMY_OTHER_POP = 0.00725F;
   protected static final float RECRUITABLE_ARMY_OTHER_POP_ATWAR = 0.0025F;
   protected static final float RECRUIT_HAPPINESS_CHANGE = 0.1375F;
   protected static final float RECRUIT_ECONOMY_CHANGE = 0.575F;
   protected static final float RECRUIT_DEVELOPMENT_CHANGE = 0.1625F;
   protected static final float DISBAND_PERC_POP = 0.05F;
   protected static final float MOVE_CAPITAL_HAPPINESS_CHANGE_OLD = 0.12168F;
   protected static final float MOVE_CAPITAL_HAPPINESS_CHANGE_NEW = 0.025F;
   protected static final int MOVE_CAPITAL_LOCK_MOVING_FOR_X_TURNS = 50;
   protected static final float BASE_COST_OF_MOVE_CAPITAL_PERC = 0.1925F;
   protected static final float BASE_COST_OF_MOVE_CAPITAL_POP_OF_CAPITAL_PERC = 0.125F;

   protected final void tryToTakeNexTurn() {
      if (CFG.menuManager.getVisibleInGame_Event()) {
         CFG.menuManager.centerInGame_Event();
      } else {
         if (!CFG.SPECTATOR_MODE && this.activeTurnAction == Game_Action.TurnStates.INPUT_ORDERS) {
            for(int i = 0;
               i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize();
               ++i
            ) {
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).requestsResponse
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iNumOfTurnsLeft
                     <= 1) {
                  this.checkMessages_PauseRTS();
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAction(i);
                  CFG.toast.setInView(CFG.langManager.get("TheMessageRequiresAResponse"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  CFG.toast.setTimeInView(3000);
                  return;
               }
            }

            this.checkMessages_PauseRTS();
         }

         this.nextTurn();
      }
   }

   protected final void checkMessages_PauseRTS() {
      if (!CFG.SPECTATOR_MODE) {
         for(int i = 0;
            i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize();
            ++i
         ) {
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).willPauseTheGame
               )
             {
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).willPauseTheGame = false;
               if (!RTS.PAUSE) {
                  RTS.pauseUnpause();
                  return;
               }
            }
         }
      }
   }

   protected final void nextTurn() {
      this.resetTurnData();
      this.hideAllViews();
      switch(this.activeTurnAction) {
         case INPUT_ORDERS:
            CFG.game.resetLastActiveProvince();
            if (CFG.game.getPlayersSize() == 1) {
               this.updatePlayerData();
               this.endOfInputOrders();
            } else {
               this.inputOrders();
            }

            return;
         case LOAD_AI_RTO:
            CFG.menuManager.updateInGameRTO(false);
            this.turnMoves();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI end");
            return;
         case TURN_ACTIONS:
            this.turnMoves();
            return;
         case LOADING_NEXT_TURN:
            this.startNewTurn_End();
            return;
         case START_NEXT_TURN:
            return;
      }
   }

   private final void endOfInputOrders() {
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(false);
      this.activeTurnAction = Game_Action.TurnStates.LOAD_AI_RTO;
      if (this.getNumOfPlayersInGame() > 1) {
         CFG.menuManager.updateInGame_TOP_All_NextTurnActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      }

      this.eRTO_START = 0;
      this.eRTO_START2 = 0;
      this.eRTO_START3 = 0;
      CFG.game.getRTO().buildRandomOrder();
      CFG.menuManager.updateInGameRTO(true);
      if (!CFG.isDesktop()) {
         Turn_Actions.runRevolts();
      }

      this.turnActions = new Turn_Actions();
      this.turnActions.start();
   }

   protected final void startNewTurn() {
      Menu_InGame.TIME_CONTINUE = -1L;
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(false);
      if (CFG.isAndroid()) {
         Turn_NewTurn.doAction();
         this.startNewTurn_End();
      } else {
         this.activeTurnAction = Game_Action.TurnStates.LOADING_NEXT_TURN;
         this.turnNewTurn = new Turn_NewTurn();
         this.turnNewTurn.start();
      }
   }

   protected final void startNewTurn_End() {
      CFG.PLAYER_TURNID = 0;
      CFG.gameAction.loadActivePlayerData();
      this.updatePosOfMap_NewTurn = false;
      CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setText(CFG.langManager.get("NextTurn"));
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
      CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
      Game_Render.updateDrawMoveUnits();
      CFG.game.updateDrawMoveUnitsArmy();
      Menu_InGame_Messages.START_ANIMATION = true;
      if (Game_Calendar.TURN_ID % 100 == 92 && !CFG.SPECTATOR_MODE) {
         CFG.soundsManager.playSound(SoundsManager.SOUND_CROW);
      }

      Game_Render.updateRenderer();
      CFG.game.checkProvinceActionMenu();
   }

   private final void updatePlayerData() {
      CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_PosX = CFG.map.getMapCoordinates().getPosX();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_PosY = CFG.map.getMapCoordinates().getPosY();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).fBefore_Scale = CFG.map.getMapScale().getCurrentScale();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_CivInfo = CFG.menuManager.getVisible_InGame_CivInfo() ? CFG.getActiveCivInfo() : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Outliner = CFG.menuManager.getVisible_Menu_InGame_Outliner();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_CensusOfProvince = CFG.menuManager.getVisibleInGame_CensusOfProvince()
         ? Menu_InGame_CensusOfProvince.PROVINCE_ID
         : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Wars = CFG.menuManager.getVisibleInGame_Wars();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_WarStats = CFG.menuManager.getVisibleInGame_WarDetails() ? Menu_InGame_WarDetails.WAR_ID : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Alliances = CFG.menuManager.getVisibleInGame_MilitaryAlliances();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Alliance = CFG.menuManager.getVisible_InGame_Alliance() ? Menu_InGame_Alliance.ALLIANCE_ID : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Rank = CFG.menuManager.getVisibleInGame_Rank();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_ConqueredProvinces = CFG.menuManager.getVisibleInGame_ConquredProvinces();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_VictoryConditions = CFG.menuManager.getVisibleInGame_VictoryConditions();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_BuildingsConstructed = CFG.menuManager.getVisibleInGame_BuildingsConstructed();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_RecruitedArmy = CFG.menuManager.getVisibleInGame_RecruitedArmy();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Tribute = CFG.menuManager.getVisibleInGame_Tribute();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Technology = CFG.menuManager.getVisibleInGame_Technology();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Army = CFG.menuManager.getVisibleInGame_Army();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_WorldPop = CFG.menuManager.getVisibleInGame_WorldPopulation();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_MapModes = CFG.menuManager.getVisible_InGame_MapModes();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_History = CFG.menuManager.getVisibleInGame_History();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_BuildingsMore = CFG.menuManager.getInGame_ProvinceBuild_Visible();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_HRE = CFG.menuManager.getVisibleInGame_HRE();
      this.hideExtraViews();
   }

   protected final void hideExtraViews() {
      try {
         CFG.menuManager.setVisible_InGame_CivInfo(false);
         CFG.menuManager.setVisible_InGame_FlagAction(false);
         CFG.menuManager.setVisibleInGame_WarDetails(false);
         CFG.menuManager.setVisibleInGame_Wars(false);
         CFG.menuManager.setVisibleInGame_CensusOfProvince(false);
         CFG.menuManager.setVisibleInGame_Rank(false);
         CFG.menuManager.setVisibleInGame_MilitaryAlliances(false);
         CFG.menuManager.setVisible_InGame_Alliance(false);
         CFG.menuManager.setVisible_Menu_InGame_Outliner(false);
         CFG.menuManager.setVisibleInGame_WorldPopulation(false);
         CFG.menuManager.setVisible_InGame_MapModes(false);
         CFG.menuManager.setVisibleInGame_Playlist(false);
         CFG.menuManager.setVisibleInGame_WarPreparations(false);
         CFG.menuManager.setVisibleInGame_ConquredProvinces(false);
         CFG.menuManager.setVisibleInGame_VictoryConditions(false);
         CFG.menuManager.setVisibleInGame_BuildingsConstructed(false);
         CFG.menuManager.setVisibleInGame_RecruitedArmy(false);
         CFG.menuManager.setVisibleInGame_Tribute(false);
         CFG.menuManager.setVisibleInGame_Technology(false);
         CFG.menuManager.setVisibleInGame_Wonders(false);
         CFG.menuManager.setVisibleInGame_SendMessage(false);
         CFG.menuManager.setVisibleInGame_Plunder(false);
         CFG.menuManager.setVisibleInGame_MessageView(false);
         CFG.menuManager.setVisible_Menu_InGame_War(false);
         CFG.menuManager.setVisible_Menu_InGame_CapitalMoved(false);
         CFG.menuManager.setVisible_Menu_InGame_VassalReleased(false);
         CFG.menuManager.setVisible_Menu_InGame_CityHaveBeenFounded(false);
         CFG.menuManager.setVisible_Menu_InGame_AllianceInfo(false);
         CFG.menuManager.setVisible_InGame_Budget(false);
         CFG.menuManager.setVisible_Menu_InGame_CurrentWars(false);
         CFG.menuManager.setVisible_InGame_HRE(false);
         CFG.menuManager.setVisible_InGame_HRE_VoteFor(false);
         CFG.menuManager.setVisible_Menu_InGame_Graph(false);
         CFG.menuManager.setVisibleInGame_History(false);
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }

   private final void inputOrders() {
      this.updatePlayerData();
      if (CFG.PLAYER_TURNID == CFG.game.getPlayersSize() - 1) {
         this.endOfInputOrders();
      } else {
         ++CFG.PLAYER_TURNID;
         this.updatePosOfMap_NewTurn = true;
         this.loadActivePlayerData();
         if (CFG.FOG_OF_WAR == 2) {
            CFG.map.getMapBG().disposeMinimapOfCivilizations();
         }
      }
   }

   protected final void updateIsSupplied() {
      try {
         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
               for(int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
                  if (!CFG.game
                     .getCiv(i)
                     .getCivRegion(j)
                     .setIsSupplied(CFG.game.getCiv(i).getCivRegion(j).getSeaAccess() || CFG.game.getCiv(i).getCivRegion(j).getHaveNotOccupiedProvince())) {
                     try {
                        for(int k = 0; k < CFG.game.getCiv(i).getCivRegion(j).getProvincesSize(); ++k) {
                           for(int o = 0; o < CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvincesSize(); ++o) {
                              if (CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                    .getWasteland()
                                 < 0) {
                                 if (CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                       .getCivID()
                                    == 0) {
                                    CFG.game.getCiv(i).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i).getCivRegion(j).getProvincesSize();
                                    break;
                                 }

                                 if (CFG.game
                                          .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                          .getCivID()
                                       != i
                                    && (
                                       CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                   )
                                                   .getCivID()
                                             )
                                             .getCivRegion(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                   )
                                                   .getCivRegionID()
                                             )
                                             .getSeaAccess()
                                          || CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                   )
                                                   .getCivID()
                                             )
                                             .getCivRegion(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                   )
                                                   .getCivRegionID()
                                             )
                                             .getHaveNotOccupiedProvince()
                                    )
                                    && (
                                       CFG.game
                                                .getCiv(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                      )
                                                      .getCivID()
                                                )
                                                .getPuppetOfCivID()
                                             == i
                                          || CFG.game.getCiv(i).getPuppetOfCivID()
                                             == CFG.game
                                                .getProvince(
                                                   CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                )
                                                .getCivID()
                                          || CFG.game.getCiv(i).getAllianceID() > 0
                                             && CFG.game.getCiv(i).getAllianceID()
                                                == CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                          || CFG.game
                                                .getMilitaryAccess(
                                                   i,
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game.getProvince(CFG.game.getCiv(i).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                      )
                                                      .getCivID()
                                                )
                                             > 0
                                    )) {
                                    CFG.game.getCiv(i).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i).getCivRegion(j).getProvincesSize();
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (IndexOutOfBoundsException var5) {
                        CFG.exceptionStack(var5);
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var6) {
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0 && CFG.game.getProvince(i).getCivID() > 0) {
            CFG.game.getProvince(i).updateIsNotSuppliedForXTurns();
            CFG.game.getProvince(i).updateDefensivePosition();
         }
      }
   }

   protected final void updateCivsHappiness() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.updateCivsHappiness(i);
      }
   }

   protected final void updateCivsHappiness(int nCivID) {
      CFG.game.getCiv(nCivID).setHappiness((int)(this.getCivHappiness(nCivID) * 100.0F));
   }

   protected final float getCivHappiness(int nCivID) {
      float tHappiness = 0.0F;
      CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.clear();

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         tHappiness += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness();
         if (CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL
            > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness()) {
            CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.add(CFG.game.getCiv(nCivID).getProvinceID(i));
         }
      }

      for(int i = CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() - 1; i >= 0; --i) {
         if (CFG.game.getCiv(nCivID).isFestivalOrganized(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i))) {
            CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.remove(i);
         }
      }

      return tHappiness / (float)CFG.game.getCiv(nCivID).getNumOfProvinces();
   }

   protected final void updateCivsMovementPoints() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.updateCivsMovementPoints(i);
      }
   }

   protected final void updateCivsMovementPoints(int nCivID) {
      CFG.game
         .getCiv(nCivID)
         .setMovePoints(this.getMovementPoints_BaseValue(nCivID) + this.getMovementPoints_FromCivSize(nCivID) + this.getMovementPoints_FromTechnology(nCivID));
   }

   protected final int getMovementPoints_BaseValue(int nCivID) {
      return 6
         + (int)(
            (float)CFG.gameAges.getAge_StartingMovementPoints(Game_Calendar.CURRENT_AGEID)
               * this.modifierMovementPoints_CivID(nCivID)
               * (1.0F + CFG.game.getCiv(nCivID).getModifier_MovementPoints())
         );
   }

   protected final int getMovementPoints_FromCivSize(int nCivID) {
      return (int)(
         (float)CFG.game.getCiv(nCivID).getNumOfProvinces()
            * CFG.gameAges.getAge_MovementPointsModifier(Game_Calendar.CURRENT_AGEID)
            * Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 1.213854F, 1.0F)
            * this.modifierMovementPoints_CivID(nCivID)
            * (1.0F + CFG.game.getCiv(nCivID).getModifier_MovementPoints())
      );
   }

   protected final int getMovementPoints_FromTechnology(int nCivID) {
      return (int)(
         (float)CFG.gameAges.getAge_StartingMovementPoints(Game_Calendar.CURRENT_AGEID)
            * CFG.game.getCiv(nCivID).getTechnologyLevel()
            * 2.143798F
            * (1.0F + CFG.game.getCiv(nCivID).getModifier_MovementPoints())
      );
   }

   protected final void updateCivsDiplomacyPoints_StartTheGame() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.updateCivsDiplomacyPoints(i);
         CFG.game.getCiv(i).setDiplomacyPoints((int)Math.max((float)CFG.game.getCiv(i).getDiplomacyPoints() * 2.65F, 22.0F));
      }
   }

   protected final void updateCivsDiplomacyPoints() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.updateCivsDiplomacyPoints(i);
      }
   }

   protected final void updateCivsDiplomacyPoints(int nCivID) {
      CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() + this.getUpdateCivsDiplomacyPoints(nCivID));
   }

   protected final int getUpdateCivsDiplomacyPoints(int nCivID) {
      return Math.max(
         this.getDiplomacyPoints_BaseValue(nCivID)
            + this.getDiplomacyPoints_FromEnemies(nCivID)
            + this.getDiplomacyPoints_FromRank(nCivID)
            + this.getDiplomacyPoints_FromTechnology(nCivID)
            - DiplomacyManager.getCostOfCurrentDiplomaticActionsUpdate(nCivID),
         0
      );
   }

   protected final int getDiplomacyPoints_BaseValue(int nCivID) {
      return 1 + (int)((float)CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * this.modifierMovementPoints_CivID(nCivID) * 0.375F);
   }

   protected final int getDiplomacyPoints_FromTechnology(int nCivID) {
      return (int)((float)CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * CFG.game.getCiv(nCivID).getTechnologyLevel() * 2.75F);
   }

   protected final int getDiplomacyPoints_FromRank(int nCivID) {
      return (int)(
         (float)CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID)
            * (1.0F - (float)CFG.game.getCiv(nCivID).getRankPosition() / (float)CFG.game.getCivsSize())
            * 0.775F
      );
   }

   protected final int getDiplomacyPoints_FromEnemies(int nCivID) {
      return (int)(-6.0F + (float)Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getHatedCivsSize()) * 6.0F);
   }

   protected float modifierMovementPoints_CivID(int nCivID) {
      if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
         switch(CFG.DIFFICULTY) {
            case 0:
               return 1.3F;
            case 1:
               return 1.15F;
            case 2:
               return 1.0F;
            case 3:
               return 0.95F;
         }
      }

      switch(CFG.DIFFICULTY) {
         case 0:
            return 0.8F;
         case 1:
            return 0.95F;
         case 2:
            return 1.05F;
         case 3:
            return 1.15F;
         default:
            return 1.0F;
      }
   }

   protected final void turnMoves() {
      if (this.currentMoveUnits != null && this.currentMoveUnits.getMoveUnitsSize() > 0) {
         this.turnMoves_MoveCurrentArmy();
      } else {
         if (CFG.menuManager.getInGame_Report_Visible()) {
            CFG.menuManager.setInGame_Report_Visible(false);
         }

         for(int e = this.eRTO_START2; e < CFG.game.getRTO().getRTOSize(); ++this.eRTO_START2) {
            this.turnMoves_UpdatePlayersFogOfWar(CFG.game.getRTO().getRTO(e));

            for(int i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++i) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() > 39
                  && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() > 0
                  && !CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).isOccupied()
                  && CFG.game
                     .getCivsAtWar(
                        CFG.game.getRTO().getRTO(e),
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()
                     )) {
                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits()
                     > CFG.game
                        .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID())
                        .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                     CFG.game
                        .getCiv(CFG.game.getRTO().getRTO(e))
                        .getMoveUnits(i)
                        .setNumOfUnits(
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID())
                              .getArmyCivID(CFG.game.getRTO().getRTO(e))
                        );
                  }

                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() <= 0) {
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                  } else {
                     this.currentMoveUnits = new MoveUnits_TurnData(CFG.game.getRTO().getRTO(e));
                     this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i), CFG.game.getRTO().getRTO(e));
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                     if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
                        for(int k = i + 1; k < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++k) {
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                              == CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getToProvinceID()) {
                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getNumOfUnits()
                                 > CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getFromProvinceID())
                                    .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                                 CFG.game
                                    .getCiv(CFG.game.getRTO().getRTO(e))
                                    .getMoveUnits(k)
                                    .setNumOfUnits(
                                       CFG.game
                                          .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getFromProvinceID())
                                          .getArmyCivID(CFG.game.getRTO().getRTO(e))
                                    );
                              }

                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getNumOfUnits() > 0) {
                                 this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k), CFG.game.getRTO().getRTO(e));
                                 CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(k--);
                              }
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0) {
                           for(int a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilizationsSize(); ++a) {
                              if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                 != CFG.game.getRTO().getRTO(e)) {
                                 for(int k = 0;
                                    k
                                       < CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                          .getMoveUnitsSize();
                                    ++k
                                 ) {
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       == CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                          .getMoveUnits(k)
                                          .getToProvinceID()) {
                                       if (CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .getNumOfUnits()
                                          > CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                                   .getMoveUnits(k)
                                                   .getFromProvinceID()
                                             )
                                             .getArmyCivID(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                             )) {
                                          CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .setNumOfUnits(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getCiv(
                                                            CFG.game
                                                               .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID())
                                                               .getCivilization(a)
                                                         )
                                                         .getMoveUnits(k)
                                                         .getFromProvinceID()
                                                   )
                                                   .getArmyCivID(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                             );
                                       }

                                       if (CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .getNumOfUnits()
                                          > 0) {
                                          this.currentMoveUnits
                                             .addMoveUnits(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                                   .getMoveUnits(k),
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                             );
                                          CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .removeMove(k--);
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        for(int a = 1; a < CFG.game.getCivsSize(); ++a) {
                           if (a != CFG.game.getRTO().getRTO(e)
                              && (
                                 CFG.game.getCiv(a).getPuppetOfCivID() == CFG.game.getRTO().getRTO(e)
                                    || a == CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID()
                              )) {
                              for(int k = 0; k < CFG.game.getCiv(a).getMoveUnitsSize(); ++k) {
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getCiv(a).getMoveUnits(k).getToProvinceID()) {
                                    if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits()
                                       > CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a)) {
                                       CFG.game
                                          .getCiv(a)
                                          .getMoveUnits(k)
                                          .setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a));
                                    }

                                    if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits() > 0) {
                                       this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(a).getMoveUnits(k), a);
                                       CFG.game.getCiv(a).removeMove(k--);
                                    }
                                 }
                              }
                           }
                        }
                     }

                     int attackingArmy = 0;

                     for(int o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                        attackingArmy += this.currentMoveUnits.getMoveUnits(o).getNumOfUnits();
                     }

                     Gdx.app.log("AoC", "attackingArmy: " + attackingArmy);
                     Gdx.app.log("AoC", "MIN_ARMY_TO_ATTACK: 10");
                     Gdx.app
                        .log(
                           "AoC",
                           "atWar: "
                              + CFG.game
                                 .getCivsAtWar(
                                    CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                 )
                        );
                     if (attackingArmy < 10
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0
                        && CFG.game
                           .getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                        )
                      {
                        Gdx.app.log("AoC", "attackingArmy: remove");
                        this.currentMoveUnits = null;
                     } else {
                        for(int o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                           this.currentMoveUnits.getMoveUnits(o).getMoveUnitsLine().updateMoveTime();
                        }

                        this.rollDices();
                        if (!CFG.SHOW_ALL_MOVES
                           && !CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getControlledByPlayer()
                           && !CFG.game
                              .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                              .getControlledByPlayer()) {
                           this.turnMoves_MoveCurrentArmy();
                        } else {
                           if (!CFG.SHOW_ONLY_COMBAT_MOVES) {
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           if ((
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && CFG.game
                                          .getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                    || !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && this.turnMoves_IsACombatMove(
                                          this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       )
                              )
                              && (!RTS.isEnabled() || !RTS.PAUSE && RTS.showReport() || RTS.PAUSE)) {
                              this.SHOW_REPORT = true;
                              this.iPlayerAttack_ShowArmyInProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), true);
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateDrawArmy();
                              this.diceDefendersCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID();
                              this.diceAggressorsCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getCivID();
                              CFG.menuManager
                                 .setVisible_InGame_Dices(!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince());
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           this.turnMoves_MoveCurrentArmy();
                        }
                     }
                  }
               }
            }

            ++e;
         }

         for(int e = this.eRTO_START; e < CFG.game.getRTO().getRTOSize(); ++this.eRTO_START) {
            this.turnMoves_UpdatePlayersFogOfWar(CFG.game.getRTO().getRTO(e));

            for(int i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() == 0
                  || CFG.game
                     .getCivsAtWar(
                        CFG.game.getRTO().getRTO(e),
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()
                     )
                  || CFG.game
                        .getMilitaryAccess(
                           CFG.game.getRTO().getRTO(e),
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()
                        )
                     > 0
                  || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()
                     == CFG.game.getRTO().getRTO(e)
                  || CFG.game
                        .getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID())
                        .getPuppetOfCivID()
                     == CFG.game.getRTO().getRTO(e)
                  || CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()
                  || CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0
                     && CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()
                        == CFG.game
                           .getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID())
                           .getAllianceID()) {
                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits()
                     > CFG.game
                        .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID())
                        .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                     CFG.game
                        .getCiv(CFG.game.getRTO().getRTO(e))
                        .getMoveUnits(i)
                        .setNumOfUnits(
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID())
                              .getArmyCivID(CFG.game.getRTO().getRTO(e))
                        );
                  }

                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() <= 0) {
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                  } else {
                     this.currentMoveUnits = new MoveUnits_TurnData(CFG.game.getRTO().getRTO(e));
                     this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i), CFG.game.getRTO().getRTO(e));
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                     if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
                        for(int k = i + 1; k < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); ++k) {
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                              == CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getToProvinceID()) {
                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getNumOfUnits()
                                 > CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getFromProvinceID())
                                    .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                                 CFG.game
                                    .getCiv(CFG.game.getRTO().getRTO(e))
                                    .getMoveUnits(k)
                                    .setNumOfUnits(
                                       CFG.game
                                          .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getFromProvinceID())
                                          .getArmyCivID(CFG.game.getRTO().getRTO(e))
                                    );
                              }

                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k).getNumOfUnits() > 0) {
                                 this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k), CFG.game.getRTO().getRTO(e));
                                 CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(k--);
                              }
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0) {
                           for(int a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilizationsSize(); ++a) {
                              if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                 != CFG.game.getRTO().getRTO(e)) {
                                 for(int k = 0;
                                    k
                                       < CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                          .getMoveUnitsSize();
                                    ++k
                                 ) {
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       == CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                          .getMoveUnits(k)
                                          .getToProvinceID()) {
                                       if (CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .getNumOfUnits()
                                          > CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                                   .getMoveUnits(k)
                                                   .getFromProvinceID()
                                             )
                                             .getArmyCivID(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                             )) {
                                          CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .setNumOfUnits(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getCiv(
                                                            CFG.game
                                                               .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID())
                                                               .getCivilization(a)
                                                         )
                                                         .getMoveUnits(k)
                                                         .getFromProvinceID()
                                                   )
                                                   .getArmyCivID(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                             );
                                       }

                                       if (CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .getNumOfUnits()
                                          > 0) {
                                          this.currentMoveUnits
                                             .addMoveUnits(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                                   .getMoveUnits(k),
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                             );
                                          CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .removeMove(k--);
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        for(int a = 1; a < CFG.game.getCivsSize(); ++a) {
                           if (a != CFG.game.getRTO().getRTO(e)
                              && (
                                 CFG.game.getCiv(a).getPuppetOfCivID() == CFG.game.getRTO().getRTO(e)
                                    || a == CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID()
                              )) {
                              for(int k = 0; k < CFG.game.getCiv(a).getMoveUnitsSize(); ++k) {
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getCiv(a).getMoveUnits(k).getToProvinceID()) {
                                    if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits()
                                       > CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a)) {
                                       CFG.game
                                          .getCiv(a)
                                          .getMoveUnits(k)
                                          .setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(a).getMoveUnits(k).getFromProvinceID()).getArmyCivID(a));
                                    }

                                    if (CFG.game.getCiv(a).getMoveUnits(k).getNumOfUnits() > 0) {
                                       this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(a).getMoveUnits(k), a);
                                       CFG.game.getCiv(a).removeMove(k--);
                                    }
                                 }
                              }
                           }
                        }
                     }

                     int attackingArmy = 0;

                     for(int o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                        attackingArmy += this.currentMoveUnits.getMoveUnits(o).getNumOfUnits();
                     }

                     Gdx.app.log("AoC", "attackingArmy: " + attackingArmy);
                     Gdx.app.log("AoC", "MIN_ARMY_TO_ATTACK: 10");
                     Gdx.app
                        .log(
                           "AoC",
                           "atWar: "
                              + CFG.game
                                 .getCivsAtWar(
                                    CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                 )
                        );
                     if (attackingArmy < 10
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0
                        && CFG.game
                           .getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                        )
                      {
                        Gdx.app.log("AoC", "attackingArmy: remove");
                        this.currentMoveUnits = null;
                     } else {
                        for(int o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); ++o) {
                           this.currentMoveUnits.getMoveUnits(o).getMoveUnitsLine().updateMoveTime();
                        }

                        this.rollDices();
                        if (!CFG.SHOW_ALL_MOVES
                           && !this.currentMoveUnits.isPlayerMoving()
                           && !CFG.game
                              .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                              .getControlledByPlayer()) {
                           this.turnMoves_MoveCurrentArmy();
                        } else {
                           if (!CFG.SHOW_ONLY_COMBAT_MOVES) {
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           if ((
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && CFG.game
                                          .getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                    || !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && this.turnMoves_IsACombatMove(
                                          this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       )
                              )
                              && (!RTS.isEnabled() || !RTS.PAUSE && RTS.showReport() || RTS.PAUSE)) {
                              this.SHOW_REPORT = true;
                              this.iPlayerAttack_ShowArmyInProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), true);
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateDrawArmy();
                              this.diceDefendersCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID();
                              this.diceAggressorsCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getCivID();
                              CFG.menuManager
                                 .setVisible_InGame_Dices(!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince());
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           this.turnMoves_MoveCurrentArmy();
                        }
                     }
                  }
               } else {
                  CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
               }
            }

            for(int i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsPlunderSize(); ++i) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getNumOfUnits()
                  > CFG.game
                     .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getFromProvinceID())
                     .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                  CFG.game
                     .getCiv(CFG.game.getRTO().getRTO(e))
                     .getMoveUnits_Plunder(i)
                     .setNumOfUnits(
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getFromProvinceID())
                           .getArmyCivID(CFG.game.getRTO().getRTO(e))
                     );
               }

               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getNumOfUnits() > 0) {
                  DiplomacyManager.plunder(
                     CFG.game.getRTO().getRTO(e),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getFromProvinceID(),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits_Plunder(i).getNumOfUnits()
                  );
               }

               CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removePlunder(i--);
            }

            for(int i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMigrateSize(); ++i) {
               this.migrateFromTo(
                  CFG.game.getRTO().getRTO(e),
                  CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMigrate(i).getFromProvinceID(),
                  CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMigrate(i).getToProvinceID()
               );
               CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMigrate(i--);
            }

            ++e;
         }

         CFG.PROVINCE_BORDER_ANIMATION_TIME.clear();
         this.currentMoveUnits = null;
         this.diceDefenders = 1;
         this.diceAggressors = 1;
         ++Game_Calendar.TURN_ID;
         CFG.gameAction.updateInGame_Date();

         try {
            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).clearMoveUnits();
               CFG.game.getCiv(i).clearMoveUnits_Plunder();
            }
         } catch (IndexOutOfBoundsException var5) {
         }

         this.startNewTurn();
      }
   }

   protected final void updateRelations() {
      List<Integer> tempCivs = new ArrayList<>();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            tempCivs.add(i);
         }
      }

      int i = 0;

      for(int iSize = tempCivs.size(); i < iSize - 1; ++i) {
         for(int j = i + 1; j < iSize; ++j) {
            if (CFG.game.getCivRelation_OfCivB(i, j) > 30.0F) {
               CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) - 0.295F);
            } else if (CFG.game.getCivRelation_OfCivB(i, j) < -20.0F && !CFG.game.getCivsAtWar(i, j)) {
               CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) + 0.0145F);
            }

            if (CFG.game.getCivRelation_OfCivB(j, i) > 30.0F) {
               CFG.game.setCivRelation_OfCivB(j, i, CFG.game.getCivRelation_OfCivB(j, i) - 0.295F);
            } else if (CFG.game.getCivRelation_OfCivB(j, i) < -20.0F && !CFG.game.getCivsAtWar(j, i)) {
               CFG.game.setCivRelation_OfCivB(j, i, CFG.game.getCivRelation_OfCivB(j, i) + 0.0145F);
            }
         }
      }

      tempCivs.clear();
      tempCivs = null;
   }

   protected final boolean isEmperorInTheGame() {
      try {
         return CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getNumOfProvinces() > 0
            && CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getPuppetOfCivID() == CFG.holyRomanEmpire_Manager.getHRE().getEmperor();
      } catch (IndexOutOfBoundsException var2) {
      } catch (NullPointerException var3) {
      }

      return true;
   }

   protected final void updateHRE_Elections() {
      try {
         CFG.holyRomanEmpire_Manager.getHRE().setNextElectionsIn(CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() - 1);
         if (CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() > 0 && this.isEmperorInTheGame()) {
            if (CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() == 1) {
               for(int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ++i) {
                  if (CFG.game
                     .getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)))
                     .getControlledByPlayer()) {
                     CFG.game
                        .getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)))
                        .getCivilization_Diplomacy_GameData()
                        .messageBox
                        .addMessage(new Message_HRE_ElectionsInNextTurn(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()));
                  }
               }

               this.updateHRE_VotesFor();
            } else if (Game_Calendar.TURN_ID % 6 == 0) {
               this.updateHRE_VotesFor();
            }
         } else {
            List<Integer> lNumOfVotes = new ArrayList<>();

            for(int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++i) {
               lNumOfVotes.add(0);
            }

            for(int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ++i) {
               for(int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++j) {
                  if (CFG.holyRomanEmpire_Manager.getHRE().getPrince(j) == CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.get(i)) {
                     lNumOfVotes.set(j, lNumOfVotes.get(j) + 1);
                     break;
                  }
               }
            }

            int maxVotes = 0;

            for(int i = 0; i < lNumOfVotes.size(); ++i) {
               if (lNumOfVotes.get(i) > maxVotes) {
                  maxVotes = lNumOfVotes.get(i);
               }
            }

            List<Integer> nCivsWithMaxVotes = new ArrayList<>();

            for(int i = 0; i < lNumOfVotes.size(); ++i) {
               if (lNumOfVotes.get(i) == maxVotes) {
                  nCivsWithMaxVotes.add(i);
               }
            }

            if (nCivsWithMaxVotes.size() > 0) {
               int newEmperorID = 0;
               int oldEmperorID = CFG.holyRomanEmpire_Manager.getHRE().getEmperor();
               boolean wasElector = false;
               if (nCivsWithMaxVotes.size() == 1) {
                  wasElector = CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(0)));
                  CFG.holyRomanEmpire_Manager.getHRE().setEmperor(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(0)));
               } else {
                  boolean emperorVoted = false;

                  for(int i = 0; i < nCivsWithMaxVotes.size(); ++i) {
                     if (CFG.holyRomanEmpire_Manager.getHRE().getEmperor() == CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(i))) {
                        emperorVoted = true;
                        break;
                     }
                  }

                  if (!emperorVoted) {
                     int tBest = 0;

                     for(int i = tBest + 1; i < nCivsWithMaxVotes.size(); ++i) {
                        if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(tBest))).countPopulation()
                           < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(i))).countPopulation()) {
                           tBest = i;
                        }
                     }

                     wasElector = CFG.holyRomanEmpire_Manager
                        .getHRE()
                        .getIsElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(tBest)));
                     CFG.holyRomanEmpire_Manager.getHRE().setEmperor(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(tBest)));
                  }
               }

               if (CFG.holyRomanEmpire_Manager.getHRE().getEmperor() != oldEmperorID && wasElector) {
                  CFG.holyRomanEmpire_Manager.getHRE().addElector(oldEmperorID);
               }
            }

            for(int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++i) {
               if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i)).getControlledByPlayer()) {
                  CFG.game
                     .getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i))
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_HRE_Elections_NewEmperor(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()));
               }
            }

            CFG.holyRomanEmpire_Manager.getHRE().randomNextElections();
            this.updateHRE_VotesFor();
         }
      } catch (IndexOutOfBoundsException var10) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var10);
         }
      }
   }

   protected final void updateHRE_VotesFor() {
      boolean rebuildVotes = false;
      if (CFG.holyRomanEmpire_Manager.getHRE().lVotesFor == null
         || CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.size() != CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize()) {
         rebuildVotes = true;
      }

      for(int i = CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize() - 1; i >= 0; --i) {
         if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))).getNumOfProvinces() == 0) {
            CFG.holyRomanEmpire_Manager
               .getHRE()
               .removeElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)));
            CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
            rebuildVotes = true;
         }
      }

      if (rebuildVotes) {
         CFG.holyRomanEmpire_Manager.getHRE().buildVotesFor();
      }

      int nMaxProvinces = 1;
      int nMaxScore = 1;

      for(int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++j) {
         if (nMaxProvinces < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces()) {
            nMaxProvinces = CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces();
         }

         if (nMaxScore < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore()) {
            nMaxScore = CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore();
         }
      }

      try {
         for(int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ++i) {
            if (!CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))).getControlledByPlayer()) {
               List<Float> tempScores = new ArrayList<>();

               for(int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); ++j) {
                  float nScore = 0.0F;
                  if (CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                     == CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))) {
                     nScore += 16.0F;
                  } else {
                     nScore += 10.0F
                        * CFG.game
                           .getCivRelation_OfCivB(
                              CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)),
                              CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                           )
                        / 100.0F;
                  }

                  nScore += CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).civGameData.civPersonality.HRE_VOTE_FOR_PROVINCES
                     * (float)CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces()
                     / (float)nMaxProvinces
                     * (
                        0.4F
                           + 0.6F
                              * CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)),
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                                 )
                              / 100.0F
                     );
                  nScore += CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).civGameData.civPersonality.HRE_VOTE_FOR_RANK
                     * (float)CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore()
                     / (float)nMaxScore
                     * (
                        0.5F
                           + 0.55F
                              * CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)),
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                                 )
                              / 100.0F
                     );
                  if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getPuppetOfCivID()
                     != CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)) {
                     nScore = -500.0F;
                  }

                  if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces() <= 0) {
                     nScore = -10000.0F;
                  }

                  tempScores.add(nScore);
               }

               if (tempScores.size() > 0) {
                  int tBestID = 0;

                  for(int j = tBestID + 1; j < tempScores.size(); ++j) {
                     if (tempScores.get(tBestID) < tempScores.get(j)) {
                        tBestID = j;
                     }
                  }

                  CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.set(i, CFG.holyRomanEmpire_Manager.getHRE().getPrince(tBestID));
               }

               tempScores.clear();
            }
         }
      } catch (IndexOutOfBoundsException var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      } catch (NullPointerException var9) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var9);
         }
      }
   }

   protected final void revoltDeclareIndependence() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).REVOLUTIONARY
            && Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_SinceTurn >= 10 + CFG.oR.nextInt(10)
            && (
               Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_LastTurnLostProvince > 2
                  || Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_SinceTurn > 49
            )) {
            this.rebels_DeclareIndependence(i);
         }
      }
   }

   protected final void rebels_DeclareIndependence(int nCivID) {
      List<Integer> tempPossibleCivs = new ArrayList<>();
      List<Integer> tempPopulation = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j)).getNumOfProvinces() == 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j)
                  != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getTrueOwnerOfProvince()) {
               boolean wasAdded = false;

               for(int o = 0; o < tempPossibleCivs.size(); ++o) {
                  if (tempPossibleCivs.get(o) == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j)) {
                     wasAdded = true;
                     tempPopulation.set(
                        o, tempPopulation.get(o) + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationID(j)
                     );
                     break;
                  }
               }

               if (!wasAdded) {
                  tempPossibleCivs.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j));
                  tempPopulation.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationID(j));
               }
            }
         }
      }

      List<String> possibleNewCivsByTags = new ArrayList<>();
      List<Integer> possibleNewCivsByTags_Capitals = new ArrayList<>();
      if (tempPossibleCivs.size() == 0 || CFG.oR.nextInt(100) < 33) {
         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            try {
               FileHandle file = Gdx.files
                  .internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + CFG.game.getCiv(nCivID).getProvinceID(i));
               String sOwners = file.readString();
               String[] sRes = sOwners.split(";");

               for(int j = 0; j < sRes.length; j += 2) {
                  boolean canBeAdded = true;
                  int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(sRes[j]);
                  if (CFG.ideologiesManager.getIdeology(tempIdeologyID).REVOLUTIONARY
                     || CFG.ideologiesManager.getIdeology(tempIdeologyID).AVAILABLE_SINCE_AGE_ID <= Game_Calendar.CURRENT_AGEID) {
                     String realTag = CFG.ideologiesManager.getRealTag(sRes[j]);

                     try {
                        if (Game_Calendar.currentYear < Integer.parseInt(sRes[j + 1])) {
                           canBeAdded = false;
                        }
                     } catch (NumberFormatException var15) {
                        CFG.exceptionStack(var15);
                     } catch (IndexOutOfBoundsException var16) {
                        CFG.exceptionStack(var16);
                     }

                     if (canBeAdded) {
                        for(int k = 0; k < CFG.game.getCivsSize(); ++k) {
                           if (CFG.ideologiesManager.getRealTag(CFG.game.getCiv(k).getCivTag()).equals(realTag)) {
                              canBeAdded = false;
                              break;
                           }
                        }
                     }

                     if (canBeAdded) {
                        for(int k = 0; k < possibleNewCivsByTags.size(); ++k) {
                           if (possibleNewCivsByTags.get(k).equals(sRes[j])) {
                              canBeAdded = false;
                              break;
                           }
                        }

                        if (canBeAdded) {
                           possibleNewCivsByTags.add(sRes[j]);
                           possibleNewCivsByTags_Capitals.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        }
                     }
                  }
               }
            } catch (GdxRuntimeException var20) {
            }
         }
      }

      try {
         if (tempPossibleCivs.size() > 0 || possibleNewCivsByTags.size() > 0) {
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);

               for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize(); ++i) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(i).getCityLevel() == CFG.getEditorCityLevel(0)) {
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
                  }
               }
            }

            int declareCivID = -1;
            List<Integer> joinProvinces = new ArrayList<>();

            for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
               joinProvinces.add(CFG.game.getCiv(nCivID).getProvinceID(i));
            }

            if (possibleNewCivsByTags.size() > 0) {
               int randCiv = CFG.oR.nextInt(possibleNewCivsByTags.size());
               String newTag = possibleNewCivsByTags.get(randCiv);
               float nTech = CFG.game.getCiv(CFG.game.getProvince(possibleNewCivsByTags_Capitals.get(randCiv)).getTrueOwnerOfProvince()).getTechnologyLevel();
               CFG.game.createScenarioAddCivilization(newTag, possibleNewCivsByTags_Capitals.get(randCiv), false, false, true);

               for(int i = CFG.game.getCivsSize() - 1; i >= 0; --i) {
                  if (CFG.game.getCiv(i).getCivTag().equals(newTag)) {
                     declareCivID = i;
                     CFG.game.getCiv(i).setTechnologyLevel(nTech * (0.625F + (float)CFG.oR.nextInt(375) / 1000.0F));
                     break;
                  }
               }

               if (declareCivID < 0) {
                  declareCivID = tempPossibleCivs.get(0);
               }

               int nPop = 0;

               try {
                  CFG.game
                     .getProvince(CFG.game.getCiv(declareCivID).getCoreCapitalProvinceID())
                     .setHappiness(Math.max(CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getHappiness(), 0.75F));
                  CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCoreCapitalProvinceID()).setRevolutionaryRisk(0.0F);

                  for(int i = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getNationalitiesSize() - 1;
                     i >= 0;
                     --i
                  ) {
                     int nDiff = (int)Math.ceil(
                        (double)(
                           (float)CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getPopulationID(i)
                              * (0.625F + (float)CFG.oR.nextInt(325) / 1000.0F)
                        )
                     );
                     CFG.game
                        .getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID())
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getCivID(i),
                           CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getPopulationID(i) - nDiff
                        );
                     nPop += nDiff;
                  }
               } catch (IndexOutOfBoundsException var17) {
               }

               CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().setPopulationOfCivID(declareCivID, nPop);
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, true);
            } else if (tempPossibleCivs.size() <= 1) {
               declareCivID = tempPossibleCivs.get(0);
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
            } else if (CFG.oR.nextInt(100) < 85) {
               int tHighestPop = 0;

               for(int i = tHighestPop + 1; i < tempPopulation.size(); ++i) {
                  if (tempPopulation.get(tHighestPop) < tempPopulation.get(i)) {
                     tHighestPop = i;
                  }
               }

               declareCivID = tempPossibleCivs.get(tHighestPop);
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
            } else {
               declareCivID = tempPossibleCivs.get(CFG.oR.nextInt(tempPossibleCivs.size()));
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
            }
         }
      } catch (IndexOutOfBoundsException var18) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var18);
         }
      } catch (NullPointerException var19) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var19);
         }
      }
   }

   protected final void rebels_DeclareIndependence_Civ(int nCivID, int declareCivID, List<Integer> joinProvinces, boolean newCivilization) {
      try {
         boolean updateCapital = true;
         if (CFG.game.getCiv(declareCivID).getCapitalProvinceID() >= 0) {
            for(int i = 0; i < joinProvinces.size(); ++i) {
               if (joinProvinces.get(i) == CFG.game.getCiv(declareCivID).getCapitalProvinceID()) {
                  updateCapital = false;
                  break;
               }
            }
         }

         if (updateCapital) {
            int newCapital = 0;

            for(int i = 1; i < joinProvinces.size(); ++i) {
               if (CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getPopulationOfCivID(declareCivID)
                  > CFG.game.getProvince(joinProvinces.get(newCapital)).getPopulationData().getPopulationOfCivID(declareCivID)) {
                  newCapital = i;
               }
            }

            CFG.game.getCiv(declareCivID).setCapitalProvinceID(joinProvinces.get(newCapital));
         }

         for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
            if (i != nCivID && CFG.game.getCivsAtWar(i, nCivID)) {
               CFG.game.whitePeace(nCivID, i);
               CFG.game
                  .getCiv(i)
                  .civGameData
                  .civilization_Diplomacy_GameData
                  .messageBox
                  .addMessage(new Message_DeclarationOfIndependence(declareCivID, CFG.game.getCiv(declareCivID).getCapitalProvinceID()));
            }
         }

         if (joinProvinces.size() > 1) {
            for(int i = joinProvinces.size() - 1; i >= 0; --i) {
               boolean removeNotConnected = joinProvinces.get(i) != CFG.game.getCiv(declareCivID).getCapitalProvinceID();
               if (removeNotConnected) {
                  for(int j = 0; j < CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
                     if (CFG.game.getProvince(CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j)).getCivID()
                        == CFG.game.getProvince(joinProvinces.get(i)).getCivID()) {
                        removeNotConnected = false;
                        break;
                     }
                  }
               }

               if (removeNotConnected) {
                  CFG.game.getProvince(joinProvinces.get(i)).setCivID(CFG.game.getProvince(joinProvinces.get(i)).getTrueOwnerOfProvince(), false);
                  CFG.game.getProvince(joinProvinces.get(i)).setRevolutionaryRisk(CFG.game.getProvince(joinProvinces.get(i)).getRevolutionaryRisk() * 0.15F);
                  joinProvinces.remove(i);
               }
            }
         }

         for(int i = 0; i < joinProvinces.size(); ++i) {
            CFG.game.getProvince(joinProvinces.get(i)).setTrueOwnerOfProvince(declareCivID);
            if (CFG.game.getProvince(joinProvinces.get(i)).getCivID() != declareCivID) {
               CFG.game.getProvince(joinProvinces.get(i)).setCivID(declareCivID, false, true);
               CFG.game.getProvince(joinProvinces.get(i)).setRevolutionaryRisk(0.0F);
               if (CFG.game.getProvince(joinProvinces.get(i)).getHappiness() < 0.7F) {
                  CFG.game.getProvince(joinProvinces.get(i)).setHappiness(0.7F + (float)CFG.oR.nextInt(20) / 100.0F);
               } else {
                  CFG.game.getProvince(joinProvinces.get(i)).setHappiness(CFG.game.getProvince(joinProvinces.get(i)).getHappiness() * 1.1775F);
               }
            }

            if (newCivilization) {
               int nPop = 0;

               try {
                  for(int j = CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getNationalitiesSize() - 1; i >= 0; --j) {
                     int nDiff = (int)Math.ceil(
                        (double)(
                           (float)CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getPopulationID(j)
                              * (0.325F + (float)CFG.oR.nextInt(350) / 1000.0F)
                        )
                     );
                     CFG.game
                        .getProvince(joinProvinces.get(i))
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getCivID(j),
                           CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().getPopulationID(j) - nDiff
                        );
                     nPop += nDiff;
                  }
               } catch (IndexOutOfBoundsException var11) {
               }

               CFG.game.getProvince(joinProvinces.get(i)).getPopulationData().setPopulationOfCivID(declareCivID, nPop);
            }

            CFG.game.getProvince(joinProvinces.get(i)).saveProvinceData.iNumOfRevolutions = 0;
         }

         if (CFG.game.getCiv(declareCivID).getCapitalProvinceID() >= 0) {
            CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).setIsCapital(true);
            if (CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
               CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
            }
         }

         for(int i = 0; i < CFG.game.getCiv(declareCivID).getNumOfProvinces(); ++i) {
            CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).updateDrawArmy();
            if (!CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getCore().getHaveACore(declareCivID)) {
               CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getCore().addNewCore(declareCivID, Game_Calendar.TURN_ID);
            }

            for(int j = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize() - 1; j >= 0; --j) {
               int tempPop = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(j);
               int tempPopCiv = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getCivID(j);
               int tRand = (int)Math.floor((double)(0.0625F + (float)CFG.oR.nextInt(63) / 100.0F * (float)tempPop));
               if (tRand > 0) {
                  CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().setPopulationOfCivID(tempPopCiv, tempPop - tRand);
                  CFG.game
                     .getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i))
                     .getPopulationData()
                     .setPopulationOfCivID(
                        declareCivID,
                        tempPop
                           - CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(declareCivID)
                           + tRand
                     );
               }
            }
         }

         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               for(int j = 0; j < CFG.game.getCiv(declareCivID).getNumOfProvinces(); ++j) {
                  if (CFG.game.getPlayer(i).getMetProvince(CFG.game.getCiv(declareCivID).getProvinceID(j))) {
                     CFG.game.getPlayer(i).setMetCivilization(declareCivID, true);
                     break;
                  }
               }
            }
         }

         CFG.game.getCiv(declareCivID).buildNumOfUnits();
         CFG.game.getCiv(declareCivID).setMoney(Math.max(50L, CFG.game.getCiv(declareCivID).getMoney()));
      } catch (IndexOutOfBoundsException var12) {
      }
   }

   protected final boolean canAnyCivUprise(int nProvinceID) {
      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); ++i) {
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i)).getNumOfProvinces() == 0
            && CFG.game.getProvince(nProvinceID).getCore().getCivID(i) != CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()) {
            return true;
         }
      }

      return false;
   }

   protected final void startUprising() {
      Gdx.app.log("AoC", "GA -> startUprising: BEGIN");
      List<Integer> tempPossibleUprising = new ArrayList<>();
      List<Integer> tempPossibleUprising_CheckSuggest = new ArrayList<>();
      List<Integer> overMin = new ArrayList<>();
      int numOfTrueOwnerProvinces = 0;

      for(int i = 1 + Game_Calendar.TURN_ID % 3; i < CFG.game.getCivsSize(); i += 3) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED < 0
            && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).REVOLUTIONARY) {
            tempPossibleUprising.clear();
            tempPossibleUprising_CheckSuggest.clear();
            overMin.clear();
            numOfTrueOwnerProvinces = 0;

            for(int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); ++j) {
               if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  ++numOfTrueOwnerProvinces;
                  if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getRevolutionaryRisk() > 0.16F
                     && !CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getIsCapital()) {
                     if (this.getModifiedRevolutionsRisk(CFG.game.getCiv(i).getProvinceID(j)) > 0.64F * (0.4F + 0.6F * CFG.game.getCiv(i).getStability())
                        && CFG.oR.nextInt((int)(this.getModifiedRevolutionsRisk(CFG.game.getCiv(i).getProvinceID(j)) * 100.0F)) > 40) {
                        if (this.canAnyCivUprise(CFG.game.getCiv(i).getProvinceID(j))) {
                           tempPossibleUprising.add(CFG.game.getCiv(i).getProvinceID(j));
                        } else {
                           tempPossibleUprising_CheckSuggest.add(CFG.game.getCiv(i).getProvinceID(j));
                        }
                     }

                     overMin.add(CFG.game.getCiv(i).getProvinceID(j));
                  }
               }
            }

            if (tempPossibleUprising.size() == 0 && tempPossibleUprising_CheckSuggest.size() > 0) {
               for(int j = tempPossibleUprising_CheckSuggest.size() - 1; j >= 0; --j) {
                  try {
                     FileHandle file = Gdx.files
                        .internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + tempPossibleUprising_CheckSuggest.get(j));
                     String sOwners = file.readString();
                     String[] sRes = sOwners.split(";");

                     for(int k = 0; k < sRes.length; k += 2) {
                        boolean canBeAdded = true;
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(sRes[k]);
                        if (!CFG.ideologiesManager.getIdeology(tempIdeologyID).REVOLUTIONARY
                           && CFG.ideologiesManager.getIdeology(tempIdeologyID).AVAILABLE_SINCE_AGE_ID <= Game_Calendar.CURRENT_AGEID) {
                           String realTag = CFG.ideologiesManager.getRealTag(sRes[k]);

                           for(int o = 0; o < CFG.game.getCivsSize(); ++o) {
                              if (CFG.ideologiesManager.getRealTag(CFG.game.getCiv(o).getCivTag()).equals(realTag)) {
                                 canBeAdded = false;
                                 break;
                              }
                           }

                           if (canBeAdded) {
                              tempPossibleUprising.add(tempPossibleUprising_CheckSuggest.get(j));
                              break;
                           }
                        }
                     }
                  } catch (GdxRuntimeException var15) {
                  }
               }
            }

            if (tempPossibleUprising.size() > 0 || overMin.size() > 0) {
               this.spawnRevolution(i, tempPossibleUprising, overMin, numOfTrueOwnerProvinces);
            }
         }
      }

      tempPossibleUprising.clear();
      tempPossibleUprising = null;
      tempPossibleUprising_CheckSuggest.clear();
      tempPossibleUprising_CheckSuggest = null;
      overMin.clear();
      overMin = null;
   }

   protected final float getModifiedRevolutionsRisk(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getRevolutionaryRisk() * (1.0F + (float)CFG.game.getProvince(nProvinceID).getCore().getCivsSize() / 10.0F)
         - (float)CFG.game.getProvinceArmy(nProvinceID) / (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 50.0F;
   }

   protected final void spawnRevolution(int nCivID, List<Integer> nProvinces, List<Integer> nOverMin, int numOfTrueOwnerProvinces) {
      Gdx.app.log("AoC", "GA -> spawnRevolution: BEGIN: " + CFG.game.getCiv(nCivID).getCivName());
      List<Integer> tempSorted = new ArrayList<>();

      while(nProvinces.size() > 0) {
         int tBest = 0;

         for(int i = nProvinces.size() - 1; i > 0; --i) {
            if (CFG.game.getProvince(nProvinces.get(i)).getRevolutionaryRisk() > CFG.game.getProvince(nProvinces.get(tBest)).getRevolutionaryRisk()) {
               tBest = i;
            }
         }

         tempSorted.add(nProvinces.get(tBest));
         nProvinces.remove(tBest);
      }

      Gdx.app.log("AoC", "GA -> spawnRevolution: 000");
      if ((float)numOfTrueOwnerProvinces * 0.63F < (float)nOverMin.size() && CFG.oR.nextInt(1000) < 47) {
         Gdx.app.log("AoC", "GA -> spawnRevolution: 111");
         List<Integer> possibleIdeologies = new ArrayList<>();
         List<Integer> possibleCivsExisting = new ArrayList<>();

         for(int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
            if (CFG.ideologiesManager.getIdeology(i).CAN_BECOME_CIVILIZED < 0
               && !CFG.ideologiesManager.getIdeology(i).REVOLUTIONARY
               && Game_Calendar.CURRENT_AGEID >= CFG.ideologiesManager.getIdeology(i).AVAILABLE_SINCE_AGE_ID) {
               String tempTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(i).getExtraTag();
               boolean isInTheGame = CFG.game.getCiv(nCivID).getCivTag().equals(tempTag);
               if (!isInTheGame) {
                  for(int j = 0; j < CFG.game.getCivsSize(); ++j) {
                     if (CFG.game.getCiv(j).getCivTag().equals(tempTag)) {
                        if (CFG.game.getCiv(j).getNumOfProvinces() > 0) {
                           isInTheGame = true;
                        } else {
                           possibleCivsExisting.add(j);
                        }
                        break;
                     }
                  }

                  if (!isInTheGame) {
                     possibleIdeologies.add(i);
                  }
               }
            }
         }

         Gdx.app.log("AoC", "GA -> spawnRevolution: 222");
         if (possibleIdeologies.size() > 0 || possibleCivsExisting.size() > 0) {
            Gdx.app.log("AoC", "GA -> spawnRevolution: 333");
            List<Integer> allProvincesSorted = new ArrayList<>();

            for(int i = tempSorted.size() - 1; i >= 0; --i) {
               allProvincesSorted.add(tempSorted.get(i));
            }

            for(int i = nOverMin.size() - 1; i >= 0; --i) {
               boolean wasAdded = false;

               for(int j = 0; j < allProvincesSorted.size(); ++j) {
                  if (allProvincesSorted.get(j) == nOverMin.get(i)) {
                     wasAdded = true;
                     break;
                  }
               }

               if (!wasAdded) {
                  allProvincesSorted.add(nOverMin.get(i));
               }
            }

            Gdx.app.log("AoC", "GA -> spawnRevolution: 444");
            List<Integer> revoltProvinces = new ArrayList<>();
            int numOfTrueProvinces = 0;

            for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
               if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getTrueOwnerOfProvince()) {
                  ++numOfTrueProvinces;
               }
            }

            Gdx.app.log("AoC", "GA -> spawnRevolution: 555");
            int numOfRevoltProvincesMax = (int)((float)numOfTrueProvinces * (0.3F + (float)CFG.oR.nextInt(25) / 100.0F));
            if (numOfRevoltProvincesMax > 0 && allProvincesSorted.size() > 0) {
               Gdx.app.log("AoC", "GA -> spawnRevolution: 666");
               int igniteProvince = allProvincesSorted.get(CFG.oR.nextInt(allProvincesSorted.size()));
               revoltProvinces.add(igniteProvince);
               Gdx.app.log("AoC", "GA -> spawnRevolution: 777");
               if (numOfRevoltProvincesMax > revoltProvinces.size()) {
                  Gdx.app.log("AoC", "GA -> spawnRevolution: 888");

                  for(int j = 0; j < CFG.game.getProvince(igniteProvince).getNeighboringProvincesSize(); ++j) {
                     if (CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j)).getCivID() == nCivID
                        && CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j)).getCivID()
                           == CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j)).getTrueOwnerOfProvince()
                        && !CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j)).getIsCapital()
                        && CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j)).getRevolutionaryRisk() > 0.16F) {
                        revoltProvinces.add(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j));
                        if (numOfRevoltProvincesMax <= revoltProvinces.size()) {
                           break;
                        }
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 999");
                  if (numOfRevoltProvincesMax > revoltProvinces.size()) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 10");

                     for(int i = allProvincesSorted.size() - 1; i >= 0; --i) {
                        for(int j = revoltProvinces.size() - 1; j >= 0; --j) {
                           if (allProvincesSorted.get(i) == revoltProvinces.get(j)) {
                              allProvincesSorted.remove(i);
                              break;
                           }
                        }
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 11");

                     while(numOfRevoltProvincesMax > revoltProvinces.size() && allProvincesSorted.size() > 0) {
                        int counter = 0;
                        int nRand = 0;

                        while(true) {
                           if (counter++ < 8) {
                              nRand = CFG.oR.nextInt(allProvincesSorted.size());
                              boolean endRand = false;

                              for(int o = revoltProvinces.size() - 1; o >= 0; --o) {
                                 for(int p = 0; p < CFG.game.getProvince(allProvincesSorted.get(nRand)).getNeighboringProvincesSize(); ++p) {
                                    if (CFG.game.getProvince(allProvincesSorted.get(nRand)).getNeighboringProvinces(p) == revoltProvinces.get(o)) {
                                       endRand = true;
                                       o = -1;
                                       break;
                                    }
                                 }
                              }

                              if (!endRand) {
                                 continue;
                              }
                           }

                           revoltProvinces.add(allProvincesSorted.get(nRand));
                           allProvincesSorted.remove(nRand);
                           break;
                        }
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 12");
                  }
               }

               Gdx.app.log("AoC", "GA -> spawnRevolution: 13");
               boolean spawnedCivWithDifferentGovernment = false;
               if (revoltProvinces.size() > 0) {
                  Gdx.app.log("AoC", "GA -> spawnRevolution: 14");
                  String nRevTag = "";
                  List<Province_Army> tempArmies = new ArrayList<>();
                  List<Integer> tempArmiesProvinces = new ArrayList<>();
                  if (possibleCivsExisting.size() > 0 && (CFG.oR.nextInt(10) < 5 || possibleIdeologies.size() == 0)) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 15");
                     int randCiv = CFG.oR.nextInt(possibleCivsExisting.size());
                     nRevTag = CFG.game.getCiv(possibleCivsExisting.get(randCiv)).getCivTag();
                     CFG.game.getCiv(possibleCivsExisting.get(randCiv)).setCapitalProvinceID(revoltProvinces.get(0));
                     if (CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0) > 0) {
                        tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0), revoltProvinces.get(0)));
                        tempArmiesProvinces.add(revoltProvinces.get(0));
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 16");
                  } else if (possibleIdeologies.size() > 0) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 17");
                     nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
                        + CFG.ideologiesManager.getIdeology(possibleIdeologies.get(CFG.oR.nextInt(possibleIdeologies.size()))).getExtraTag();
                     if (CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0) > 0) {
                        tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0), revoltProvinces.get(0)));
                        tempArmiesProvinces.add(revoltProvinces.get(0));
                     }

                     CFG.game.createScenarioAddCivilization(nRevTag, revoltProvinces.get(0), false, false, true);
                     spawnedCivWithDifferentGovernment = true;
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 18");
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 19");
                  int nRebelsCivID = -1;

                  for(int i = CFG.game.getCivsSize() - 1; i > 0; --i) {
                     if (CFG.game.getCiv(i).getCivTag().equals(nRevTag)) {
                        nRebelsCivID = i;
                        break;
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 20");
                  if (nRebelsCivID > 0) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 21");

                     for(int i = 0; i < revoltProvinces.size(); ++i) {
                        if (CFG.game.getProvince(revoltProvinces.get(i)).getCivID() != nRebelsCivID) {
                           if (CFG.game.getProvince(revoltProvinces.get(i)).getArmy(0) > 0) {
                              tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(revoltProvinces.get(i)).getArmy(0), revoltProvinces.get(i)));
                              tempArmiesProvinces.add(revoltProvinces.get(i));
                           }

                           if (spawnedCivWithDifferentGovernment) {
                              CFG.game.getProvince(revoltProvinces.get(i)).setTrueOwnerOfProvince(nRebelsCivID);
                              CFG.game.getProvince(revoltProvinces.get(i)).setCivID(nRebelsCivID, true);
                           } else {
                              CFG.game.getProvince(revoltProvinces.get(i)).setCivID(nRebelsCivID, true);
                              CFG.game.getProvince(revoltProvinces.get(i)).setTrueOwnerOfProvince(nRebelsCivID);
                           }

                           this.updateProvinceAfterRevolution(revoltProvinces.get(i));
                           this.spawnRevolutionaryArmy(revoltProvinces.get(i), nCivID, nRebelsCivID);
                        }
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 22");

                     for(int i = 0; i < tempArmies.size(); ++i) {
                        CFG.game.getProvince(tempArmiesProvinces.get(i)).updateArmy(tempArmies.get(i).getCivID(), tempArmies.get(i).getArmy());
                        CFG.game
                           .getCiv(tempArmies.get(i).getCivID())
                           .newMove(tempArmiesProvinces.get(i), tempArmiesProvinces.get(i), tempArmies.get(i).getArmy(), true);

                        for(int a = CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivsSize() - 1; a >= 0; --a) {
                           if (CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivID(a) != nCivID
                              && CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivID(a) != nRebelsCivID) {
                              this.accessLost_MoveArmyToClosetsProvince(
                                 CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivID(a), tempArmiesProvinces.get(i)
                              );
                           }
                        }
                     }

                     CFG.game.getCiv(nCivID).setNumOfUnits(0);
                     CFG.game.getCiv(nCivID).buildNumOfUnits();
                     CFG.game.getCiv(nRebelsCivID).setNumOfUnits(0);
                     CFG.game.getCiv(nRebelsCivID).buildNumOfUnits();
                     Color nColor = CFG.getRandomColor();
                     CFG.game.getCiv(nRebelsCivID).setR((int)(nColor.r * 255.0F));
                     CFG.game.getCiv(nRebelsCivID).setG((int)(nColor.g * 255.0F));
                     CFG.game.getCiv(nRebelsCivID).setB((int)(nColor.b * 255.0F));
                     CFG.game.getCiv(nRebelsCivID).setMoney(Math.max(CFG.game.getCiv(nRebelsCivID).getMoney(), 50L));
                     CFG.game
                        .getCiv(nRebelsCivID)
                        .setTechnologyLevel(CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.845F + (float)CFG.oR.nextInt(125) / 1000.0F));
                     if (CFG.game.getCiv(nCivID).getCivID() != CFG.game.getCiv(nCivID).getPuppetOfCivID()) {
                        CFG.game.getCiv(nRebelsCivID).setPuppetOfCivID(CFG.game.getCiv(nCivID).getPuppetOfCivID());
                     }

                     try {
                        for(int p = 0; p < CFG.game.getPlayersSize(); ++p) {
                           if (!CFG.game.getPlayer(p).getMetCivilization(nRebelsCivID)) {
                              for(int o = 0; o < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++o) {
                                 if (CFG.game.getPlayer(p).getMetProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(o))) {
                                    CFG.game.getPlayer(p).setMetCivilization(nRebelsCivID, true);
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (IndexOutOfBoundsException var23) {
                        CFG.exceptionStack(var23);
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 23");
                     CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civilization_Diplomacy_GameData
                        .messageBox
                        .addMessage(new Message_Revolt(nRebelsCivID, revoltProvinces.get(0)));
                     CFG.game.declareWar(nRebelsCivID, nCivID, true);
                     ++CFG.game.getCiv(nCivID).civGameData.iNumOfRevolutions;
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 24");
                  }

                  for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                     CFG.game
                        .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                        .setRevolutionaryRisk(
                           CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk()
                              * (0.7F + (float)CFG.oR.nextInt(300) / 1000.0F)
                        );
                  }

                  for(int i = 0; i < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++i) {
                     CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i)).setRevolutionaryRisk(0.0F);
                     CFG.game
                        .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i))
                        .setHappiness(
                           Math.max(
                              0.66F + (float)CFG.oR.nextInt(24) / 100.0F, CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i)).getHappiness()
                           )
                        );
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 25");
                  if (spawnedCivWithDifferentGovernment) {
                     for(int i = 0; i < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++i) {
                        if (!CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i)).getCore().getHaveACore(nRebelsCivID)) {
                           CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i)).getCore().addNewCore(nRebelsCivID, Game_Calendar.TURN_ID);
                        }

                        int popOfNativeCiv = CFG.game
                           .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i))
                           .getPopulationData()
                           .getPopulationOfCivID(nCivID);
                        if (popOfNativeCiv > 0) {
                           float randPerc = (float)CFG.oR.nextInt(625) / 1000.0F;
                           CFG.game
                              .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i))
                              .getPopulationData()
                              .setPopulationOfCivID(nCivID, (int)((float)popOfNativeCiv * randPerc));
                           CFG.game
                              .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i))
                              .getPopulationData()
                              .setPopulationOfCivID(nRebelsCivID, (int)((float)popOfNativeCiv * (1.0F - randPerc)));
                        }
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 26");

                  for(int i = 0; i < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++i) {
                     CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(i)).updateDrawArmy();
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 27");

                  for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                     if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCivID()
                        == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getTrueOwnerOfProvince()) {
                        CFG.game
                           .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                           .setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk() * 0.645F);
                        CFG.game
                           .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                           .setHappiness((CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness() + 0.08F) * 1.124F);
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness() < 0.32F) {
                           CFG.game
                              .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                              .setHappiness(0.32F + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness() * 0.1F);
                        }
                     } else {
                        CFG.game
                           .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                           .setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getRevolutionaryRisk() * 0.4638F);
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 28");
                  if (CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID() >= 0) {
                     CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).setIsCapital(true);
                     boolean updateCapitalLevel = true;

                     for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize(); ++i) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i).getCityLevel() == CFG.getEditorCityLevel(0)) {
                           updateCapitalLevel = false;
                           break;
                        }
                     }

                     if (updateCapitalLevel && CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
                        CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 29");
                  if (CFG.FOG_OF_WAR == 2) {
                     for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                        for(int j = 0; j < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); ++j) {
                           if (CFG.game.getPlayer(i).getMetProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(j))) {
                              CFG.game.getPlayer(i).setMetCivilization(nRebelsCivID, true);
                              break;
                           }
                        }
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 30");
                  return;
               }
            }
         }
      }

      Gdx.app.log("AoC", "GA -> spawnRevolution: SECOND 0000");
      String nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
         + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag();
      int revoltCivID = -1;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getIdeologyID() == CFG.ideologiesManager.REBELS_ID && this.getSpawnRebels_CivRebelsTag(i).equals(nRevTag)) {
            if (CFG.game.getCiv(i).getNumOfProvinces() == 0) {
               revoltCivID = i;
            } else {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 1
                  && CFG.oR.nextInt(1500) % 100
                     < Math.min(50, 20 + 10 * (Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_LastTurnLostProvince))
                  && CFG.game.getCiv(i).getNumOfProvinces() < CFG.game.getCiv(nCivID).getNumOfProvinces() - 1) {
                  int theBestProvinceID = -1;
                  int theBestConnections = 0;

                  for(int j = 0; j < tempSorted.size(); ++j) {
                     int currentConnections = 0;
                     int ownProvincesConnection = 0;

                     for(int k = 0; k < CFG.game.getProvince(tempSorted.get(j)).getNeighboringProvincesSize(); ++k) {
                        if (CFG.game.getProvince(CFG.game.getProvince(tempSorted.get(j)).getNeighboringProvinces(k)).getCivID() == i) {
                           ++currentConnections;
                        } else if (CFG.game.getProvince(CFG.game.getProvince(tempSorted.get(j)).getNeighboringProvinces(k)).getCivID()
                           == CFG.game.getProvince(tempSorted.get(j)).getCivID()) {
                           ++ownProvincesConnection;
                        }
                     }

                     if (currentConnections > 0) {
                        if (ownProvincesConnection == 0) {
                           currentConnections += 2;
                        } else if (ownProvincesConnection == 1) {
                           ++currentConnections;
                        }
                     }

                     if (currentConnections > theBestConnections
                        || currentConnections > 0 && currentConnections == theBestConnections && CFG.oR.nextInt(150) % 2 == 1) {
                        theBestProvinceID = tempSorted.get(j);
                        theBestConnections = currentConnections;
                     }
                  }

                  if (theBestProvinceID < 0) {
                     for(int j = 0; j < nOverMin.size(); ++j) {
                        int currentConnections = 0;
                        int ownProvincesConnection = 0;

                        for(int k = 0; k < CFG.game.getProvince(nOverMin.get(j)).getNeighboringProvincesSize(); ++k) {
                           if (CFG.game.getProvince(CFG.game.getProvince(nOverMin.get(j)).getNeighboringProvinces(k)).getCivID() == i) {
                              ++currentConnections;
                           } else if (CFG.game.getProvince(CFG.game.getProvince(nOverMin.get(j)).getNeighboringProvinces(k)).getCivID()
                              == CFG.game.getProvince(nOverMin.get(j)).getCivID()) {
                              ++ownProvincesConnection;
                           }
                        }

                        if (currentConnections > 0) {
                           if (ownProvincesConnection == 0) {
                              currentConnections += 2;
                           } else if (ownProvincesConnection == 1) {
                              ++currentConnections;
                           }
                        }

                        if (currentConnections > theBestConnections
                           || currentConnections > 0 && currentConnections == theBestConnections && CFG.oR.nextInt(150) % 2 == 1) {
                           theBestProvinceID = nOverMin.get(j);
                           theBestConnections = currentConnections;
                        }
                     }
                  }

                  if (theBestProvinceID >= 0) {
                     for(int z = tempSorted.size() - 1; z >= 0; --z) {
                        if (tempSorted.get(z) == theBestProvinceID) {
                           tempSorted.remove(z);
                           break;
                        }
                     }

                     int nArmy0 = CFG.game.getProvince(theBestProvinceID).getArmy(0);
                     CFG.game.getProvince(theBestProvinceID).setCivID(i, false, true);
                     this.updateProvinceAfterRevolution(theBestProvinceID);
                     this.spawnRevolutionaryArmy(theBestProvinceID, nCivID, i);
                     if (nArmy0 > 0) {
                        CFG.game.getProvince(theBestProvinceID).updateArmy(nCivID, nArmy0);
                        CFG.game.getCiv(nCivID).newMove(theBestProvinceID, theBestProvinceID, nArmy0, true);

                        for(int a = CFG.game.getProvince(theBestProvinceID).getCivsSize() - 1; a >= 0; --a) {
                           if (CFG.game.getProvince(theBestProvinceID).getCivID(a) != nCivID && CFG.game.getProvince(theBestProvinceID).getCivID(a) != i) {
                              this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(theBestProvinceID).getCivID(a), theBestProvinceID);
                           }
                        }
                     }

                     CFG.game.getCiv(nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(i, theBestProvinceID));
                  }
               }

               if (tempSorted.size() == 0) {
                  return;
               }
            }
         }
      }

      if (tempSorted.size() != 0) {
         if (revoltCivID <= 0) {
            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getIdeologyID() == CFG.ideologiesManager.REBELS_ID && CFG.game.getCiv(i).getNumOfProvinces() == 0) {
                  revoltCivID = i;
               }
            }
         }

         try {
            this.spawnRevolutionInProvinceID(nCivID, revoltCivID, tempSorted.get(0), tempSorted, nOverMin);
         } catch (IndexOutOfBoundsException var21) {
            CFG.exceptionStack(var21);
         } catch (StackOverflowError var22) {
            CFG.exceptionStack(var22);
         }
      }
   }

   protected final String getSpawnRebels_CivRebelsTag(int nCivID) {
      return CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) > 0
         ? CFG.game.getCiv(nCivID).getCivTag().substring(0, CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) + 2)
         : CFG.game.getCiv(nCivID).getCivTag();
   }

   protected final int getSpawnRebels_CivRebelsTag_GetID(int nCivID) {
      if (CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) > 0) {
         try {
            return Integer.parseInt(
               CFG.game
                  .getCiv(nCivID)
                  .getCivTag()
                  .substring(CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) + 2, CFG.game.getCiv(nCivID).getCivTag().length())
            );
         } catch (NumberFormatException var3) {
            CFG.exceptionStack(var3);
         }
      }

      return 0;
   }

   protected final void updateMetCivilization(int nProvinceID) {
      try {
         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               if (CFG.game.getPlayer(i).getMetProvince(nProvinceID)) {
                  CFG.game.getPlayer(i).setMetCivilization(CFG.game.getProvince(nProvinceID).getCivID(), true);
               }
            }
         }
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }
   }

   protected final void spawnRevolutionInProvinceID(int nCivID, int nRebelsCivID, int nProvinceID, List<Integer> nProvinces, List<Integer> nOverMin) {
      String nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
         + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag();
      int nLastID = -1;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (this.getSpawnRebels_CivRebelsTag(i).equals(nRevTag)) {
            int tID = this.getSpawnRebels_CivRebelsTag_GetID(i);
            if (tID >= nLastID) {
               nLastID = tID + 1;
            }
         }
      }

      if (nLastID >= 0) {
         nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
            + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag()
            + nLastID;
      }

      List<Province_Army> tempArmies = new ArrayList<>();
      List<Integer> tempArmiesProvinces = new ArrayList<>();
      if (CFG.game.getProvince(nProvinceID).getArmy(0) > 0) {
         tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(nProvinceID).getArmy(0), nProvinceID));
         tempArmiesProvinces.add(nProvinceID);
         CFG.game.getProvince(nProvinceID).updateArmy(0);
      }

      if (nRebelsCivID <= 0) {
         CFG.game.createScenarioAddCivilization(nRevTag, nProvinceID, false, false, true);

         for(int i = CFG.game.getCivsSize() - 1; i > 0; --i) {
            if (CFG.game.getCiv(i).getIdeologyID() == CFG.ideologiesManager.REBELS_ID && CFG.game.getCiv(i).getCivTag().equals(nRevTag)) {
               nRebelsCivID = i;
               break;
            }
         }

         this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
      } else if (!CFG.game.getCiv(nRebelsCivID).getCivTag().equals(nRevTag)) {
         this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
      } else {
         this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
      }

      CFG.game.getCiv(nRebelsCivID).civGameData.iRevolt_SinceTurn = Game_Calendar.TURN_ID;
      CFG.game.getCiv(nRebelsCivID).civGameData.iRevolt_LastTurnLostProvince = Game_Calendar.TURN_ID;
      CFG.game.getCiv(nRebelsCivID).setCapitalProvinceID(nProvinceID);
      CFG.game.getProvince(nProvinceID).setIsCapital(true);
      if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
         for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i).getCityLevel() == CFG.getEditorCityLevel(0)) {
               CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
            }
         }

         CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
      }

      CFG.game.getProvince(nProvinceID).setCivID(nRebelsCivID, true);
      CFG.game.getProvince(nProvinceID).setTrueOwnerOfProvince(nCivID);
      this.updateProvinceAfterRevolution(nProvinceID);
      CFG.game.getProvince(nProvinceID).updateArmy(nRebelsCivID, 0);
      CFG.game.getCiv(nRebelsCivID).setNumOfUnits(0);
      this.spawnRevolutionaryArmy(nProvinceID, nCivID, nRebelsCivID);
      CFG.game.getCiv(nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(nRebelsCivID, nProvinceID));
      int mainCivProvinces = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getTrueOwnerOfProvince()) {
            ++mainCivProvinces;
         }
      }

      int revelsMaxPercOfProvinces = (int)Math.ceil((double)((float)mainCivProvinces * (0.12F + (float)CFG.oR.nextInt(15) / 100.0F)));
      List<Integer> tempRevCivsIDs = new ArrayList<>();

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); ++i) {
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i)).getNumOfProvinces() == 0) {
            tempRevCivsIDs.add(CFG.game.getProvince(nProvinceID).getCore().getCivID(i));
         }
      }

      List<Integer> joinProvinces = new ArrayList<>();

      for(int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
         if (!CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getIsCapital()
            && CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getCivID() == nCivID
            && this.getModifiedRevolutionsRisk(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)) > 0.16F) {
            joinProvinces.add(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j));
         }
      }

      if (revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
         for(int i = joinProvinces.size() - 1; i >= 0; --i) {
            boolean bRemove = true;

            for(int j = 0; j < tempRevCivsIDs.size(); ++j) {
               if (CFG.game.getProvince(joinProvinces.get(i)).getCore().getHaveACore(tempRevCivsIDs.get(j))) {
                  bRemove = false;
               }
            }

            if (bRemove) {
               joinProvinces.remove(i);
               if (revelsMaxPercOfProvinces >= joinProvinces.size() + 1) {
                  break;
               }
            }
         }

         if (revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
            while(joinProvinces.size() > 0 && revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
               joinProvinces.remove(CFG.oR.nextInt(joinProvinces.size()));
            }
         }
      } else {
         List<Integer> tempPossibleToAdd = new ArrayList<>();

         for(int i = 0; i < joinProvinces.size(); ++i) {
            for(int j = 0; j < CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
               for(int k = 0; k < tempRevCivsIDs.size(); ++k) {
                  if (CFG.game.getProvince(CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j)).getCivID() == nCivID
                     && CFG.game
                        .getProvince(CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j))
                        .getCore()
                        .getHaveACore(tempRevCivsIDs.get(k))) {
                     boolean canBeAdded = CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j) != nProvinceID;
                     if (canBeAdded) {
                        for(int o = 0; o < joinProvinces.size(); ++o) {
                           if (CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j) == joinProvinces.get(o)) {
                              canBeAdded = false;
                              break;
                           }
                        }

                        if (canBeAdded) {
                           for(int o = 0; o < tempPossibleToAdd.size(); ++o) {
                              if (tempPossibleToAdd.get(o) == CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j)) {
                                 canBeAdded = false;
                                 break;
                              }
                           }

                           if (canBeAdded) {
                              tempPossibleToAdd.add(CFG.game.getProvince(joinProvinces.get(i)).getNeighboringProvinces(j));
                           }
                        }
                     }
                  }
               }
            }
         }

         List<Integer> sortedPossibleToAdd = new ArrayList<>();

         while(tempPossibleToAdd.size() > 0) {
            int tBest = 0;

            for(int i = 1; i < tempPossibleToAdd.size(); ++i) {
               if ((float)CFG.game.getProvince(tempPossibleToAdd.get(i)).getPopulationData().getPopulation()
                     * CFG.game.getProvince(tempPossibleToAdd.get(i)).getRevolutionaryRisk()
                  > (float)CFG.game.getProvince(tempPossibleToAdd.get(tBest)).getPopulationData().getPopulation()
                     * CFG.game.getProvince(tempPossibleToAdd.get(tBest)).getRevolutionaryRisk()) {
                  tBest = i;
               }
            }

            sortedPossibleToAdd.add(tempPossibleToAdd.get(tBest));
            tempPossibleToAdd.remove(tBest);
         }

         for(int i = 0; i < sortedPossibleToAdd.size() && revelsMaxPercOfProvinces > joinProvinces.size() + 1; ++i) {
            joinProvinces.add(sortedPossibleToAdd.get(i));
         }
      }

      for(int i = 0; i < joinProvinces.size(); ++i) {
         if (CFG.game.getProvince(joinProvinces.get(i)).getCivID() != nRebelsCivID) {
            if (CFG.game.getProvince(joinProvinces.get(i)).getArmy(0) > 0) {
               tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(joinProvinces.get(i)).getArmy(0), joinProvinces.get(i)));
               tempArmiesProvinces.add(joinProvinces.get(i));
               CFG.game.getProvince(joinProvinces.get(i)).updateArmy(0);
            }

            CFG.game.getProvince(joinProvinces.get(i)).setCivID(nRebelsCivID, true);
            this.spawnRevolutionaryArmy(joinProvinces.get(i), nCivID, nRebelsCivID);
            this.updateProvinceAfterRevolution(joinProvinces.get(i));
         }
      }

      CFG.game.getCiv(nRebelsCivID).buildCivPersonality();

      for(int i = 0; i < tempArmies.size(); ++i) {
         CFG.game.getProvince(tempArmiesProvinces.get(i)).updateArmy(tempArmies.get(i).getCivID(), tempArmies.get(i).getArmy());
         CFG.game.getCiv(tempArmies.get(i).getCivID()).newMove(tempArmiesProvinces.get(i), tempArmiesProvinces.get(i), tempArmies.get(i).getArmy(), true);

         for(int a = CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivsSize() - 1; a >= 0; --a) {
            if (CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivID(a) != nCivID
               && CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivID(a) != nRebelsCivID) {
               this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(tempArmiesProvinces.get(i)).getCivID(a), tempArmiesProvinces.get(i));
            }
         }
      }
   }

   protected final void spawnRevolution_UpdateCivData(int nCivID, int nRebelsCivID, String nRevTag) {
      CFG.game.getCiv(nRebelsCivID).setCivTag(nRevTag);
      Color nColor = CFG.getRandomColor();
      CFG.game.getCiv(nRebelsCivID).setR((int)(nColor.r * 255.0F));
      CFG.game.getCiv(nRebelsCivID).setG((int)(nColor.g * 255.0F));
      CFG.game.getCiv(nRebelsCivID).setB((int)(nColor.b * 255.0F));
      if (CFG.game.getCiv(nRebelsCivID).getMoney() < 100L) {
         CFG.game.getCiv(nRebelsCivID).setMoney(100L);
      }

      CFG.game.getCiv(nRebelsCivID).setCivName(CFG.langManager.get("Rebels"));
      CFG.game.getCiv(nRebelsCivID).setTechnologyLevel(CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.575F + (float)CFG.oR.nextInt(25) / 100.0F));
      CFG.game.declareWar(nRebelsCivID, nCivID, true);
   }

   protected final void spawnRevolutionaryArmy(int nProvinceID, int nCivID, int nRebelsCivID) {
      int revolutionaryPop = 10 + CFG.oR.nextInt(50);

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == nCivID) {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00125F);
         } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getNumOfProvinces() == 0) {
            revolutionaryPop += (int)(
               (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * (0.0125F + (float)CFG.oR.nextInt(35) / 1000.0F)
            );
         } else if (CFG.game.getCivsAtWar(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), nCivID)) {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0145F);
         } else {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 8.5E-4F);
         }
      }

      int nArmy = (int)(
         (float)revolutionaryPop
            * Math.min(
               0.1F
                  + 0.15F * (float)CFG.game.getProvince(nProvinceID).saveProvinceData.iNumOfRevolutions
                  + 0.075F * (float)CFG.game.getCiv(nCivID).civGameData.iNumOfRevolutions
                  + (float)CFG.oR.nextInt(90) / 1000.0F,
               10.0F
            )
      );
      CFG.game.getProvince(nProvinceID).updateArmy(nRebelsCivID, nArmy);
      CFG.game.getCiv(nRebelsCivID).setNumOfUnits(CFG.game.getCiv(nRebelsCivID).getNumOfUnits() + nArmy);
      ++CFG.game.getProvince(nProvinceID).saveProvinceData.iNumOfRevolutions;
   }

   protected final int getSpawnRevolutionaryArmy_MAX(int nProvinceID, int nCivID) {
      int revolutionaryPop = 10 + CFG.oR.nextInt(50);

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == nCivID) {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00125F);
         } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getNumOfProvinces() == 0) {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0475F);
         } else if (CFG.game.getCivsAtWar(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), nCivID)) {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0145F);
         } else {
            revolutionaryPop += (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 8.5E-4F);
         }
      }

      return (int)((float)revolutionaryPop * 0.17199999F) + 2;
   }

   protected final void updateProvinceAfterRevolution(int nProvinceID) {
      CFG.game
         .getProvince(nProvinceID)
         .setRevolutionaryRisk(CFG.game.getProvince(nProvinceID).getRevolutionaryRisk() * (0.42241F + (float)CFG.oR.nextInt(400) / 1000.0F));
      CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() * (1.075F + (float)CFG.oR.nextInt(52) / 100.0F));
      CFG.game
         .getProvince(nProvinceID)
         .setEconomy((int)((float)CFG.game.getProvince(nProvinceID).getEconomy() * (0.98244F - (float)CFG.oR.nextInt(78) / 1000.0F)));
      CFG.game
         .getProvince(nProvinceID)
         .setDevelopmentLevel(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (0.93244F - (float)CFG.oR.nextInt(184) / 1000.0F));
      if (CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0 && CFG.oR.nextInt(100) < 64) {
         CFG.game.getProvince(nProvinceID).setLevelOfLibrary(0);
      }

      this.updateMetCivilization(nProvinceID);
   }

   protected final void moveRegroupArmy() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).moveRegroupArmy();
      }
   }

   private final void migrateFromTo(int nCivID, int fromProvinceID, int toProvinceID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() == nCivID && Game.uncivilizedCanMigrate(toProvinceID, nCivID)) {
            List<Integer> tCivs = new ArrayList<>();
            List<Integer> tArmies = new ArrayList<>();

            for(int j = 0; j < CFG.game.getProvince(fromProvinceID).getCivsSize(); ++j) {
               tCivs.add(CFG.game.getProvince(fromProvinceID).getCivID(j));
               tArmies.add(CFG.game.getProvince(fromProvinceID).getArmy(j));
            }

            int minus_A = CFG.game.getProvince(fromProvinceID).saveProvinceData.iNumOfTurnsWithBalanceOnMinus;
            CivFestival tFestival = CFG.game.getCiv(nCivID).isFestivalOrganized_GET(fromProvinceID);
            if (tFestival != null) {
               CFG.game.getCiv(nCivID).removeFestival_ProvinceID(fromProvinceID);
            }

            CivFestival tAssimilate = CFG.game.getCiv(nCivID).isAssimilateOrganized_GET(fromProvinceID);
            if (tAssimilate != null) {
               CFG.game.getCiv(nCivID).removeAssimilate_ProvinceID(fromProvinceID);
            }

            CivInvest tInvest = CFG.game.getCiv(nCivID).isInvestOrganized_GET(fromProvinceID);
            if (tInvest != null) {
               CFG.game.getCiv(nCivID).removeInvest_ProvinceID(fromProvinceID);
            }

            int tNeutral = CFG.game.getProvince(toProvinceID).getArmy(0);

            for(int j = CFG.game.getProvince(fromProvinceID).getCivsSize() - 1; j >= 0; --j) {
               CFG.game.getProvince(fromProvinceID).updateArmy(CFG.game.getProvince(fromProvinceID).getCivID(j), 0);
            }

            for(int j = CFG.game.getProvince(toProvinceID).getCivsSize() - 1; j >= 0; --j) {
               CFG.game.getProvince(toProvinceID).updateArmy(CFG.game.getProvince(toProvinceID).getCivID(j), 0);
            }

            CFG.game.getProvince(fromProvinceID).setTrueOwnerOfProvince(nCivID);
            CFG.game.getProvince(toProvinceID).setCivID(nCivID, false);
            CFG.game.getProvince(fromProvinceID).setTrueOwnerOfProvince(0);
            CFG.game.getProvince(fromProvinceID).setCivID(0, false);
            CFG.game.getProvince(toProvinceID).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = minus_A;
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() == fromProvinceID) {
               CFG.game.getProvince(toProvinceID).setIsCapital(true);
               CFG.game.getProvince(fromProvinceID).setIsCapital(false);
               CFG.game.getCiv(nCivID).setCapitalProvinceID(toProvinceID);

               try {
                  CFG.game.getProvince(fromProvinceID).getCity(0).setCityLevel(CFG.getEditorCityLevel(3));
               } catch (IndexOutOfBoundsException var16) {
               }

               try {
                  CFG.game.getProvince(toProvinceID).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
               } catch (IndexOutOfBoundsException var15) {
               }
            }

            CFG.game.getProvince(fromProvinceID).setDrawCities(false);
            CFG.game.getProvince(toProvinceID).setDrawCities(true);
            Province_Population tempD = CFG.game.getProvince(toProvinceID).getPopulationData();
            CFG.game.getProvince(toProvinceID).setPopulationData(CFG.game.getProvince(fromProvinceID).getPopulationData());
            CFG.game.getProvince(fromProvinceID).setPopulationData(tempD);
            int tData = CFG.game.getProvince(toProvinceID).getEconomy();
            CFG.game.getProvince(toProvinceID).setEconomy(CFG.game.getProvince(fromProvinceID).getEconomy());
            CFG.game.getProvince(fromProvinceID).setEconomy(tData);
            float fData = CFG.game.getProvince(toProvinceID).getHappiness();
            CFG.game.getProvince(toProvinceID).setHappiness(CFG.game.getProvince(fromProvinceID).getHappiness());
            CFG.game.getProvince(fromProvinceID).setHappiness(fData);
            fData = CFG.game.getProvince(toProvinceID).getDevelopmentLevel();
            CFG.game.getProvince(toProvinceID).setDevelopmentLevel(CFG.game.getProvince(fromProvinceID).getDevelopmentLevel());
            CFG.game.getProvince(fromProvinceID).setDevelopmentLevel(fData);
            if (tFestival != null) {
               tFestival.iProvinceID = toProvinceID;
               CFG.game.getCiv(nCivID).addFestival(tFestival);
            }

            if (tAssimilate != null) {
               tAssimilate.iProvinceID = toProvinceID;
               CFG.game.getCiv(nCivID).addAssimilate(tAssimilate);
            }

            if (tInvest != null) {
               tInvest.iProvinceID = toProvinceID;
               CFG.game.getCiv(nCivID).addInvest(tInvest);
            }

            for(int j = 0; j < tCivs.size(); ++j) {
               CFG.game.getProvince(toProvinceID).updateArmy(tCivs.get(j), tArmies.get(j));
            }

            CFG.game.getProvince(fromProvinceID).updateArmy(0, tNeutral);
            CFG.game.getProvince(toProvinceID).iIncome_Taxation = CFG.game.getProvince(fromProvinceID).iIncome_Taxation;
            CFG.game.getProvince(toProvinceID).iIncome_Production = CFG.game.getProvince(fromProvinceID).iIncome_Production;
            CFG.game.getProvince(toProvinceID).iAdministrationCost = CFG.game.getProvince(fromProvinceID).iAdministrationCost;
            CFG.game.getProvince(fromProvinceID).getCore().resetOwnership(nCivID);
            CFG.game.getProvince(toProvinceID).getCore().resetOwnership(nCivID);
            CFG.game.getProvince(fromProvinceID).updateDrawArmy();
            CFG.game.getProvince(toProvinceID).updateDrawArmy();
            TechnologyManager.updateCivs_ResearchProgress_Migrate(nCivID, toProvinceID);
         }
      } catch (IndexOutOfBoundsException var17) {
         CFG.exceptionStack(var17);
      } catch (NullPointerException var18) {
         CFG.exceptionStack(var18);
      }
   }

   private final boolean turnMoves_IsACombatMove(int nCivID, int toProvinceID) {
      if (nCivID == CFG.game.getProvince(toProvinceID).getCivID()
         || nCivID == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
         || CFG.game.getProvince(toProvinceID).getCivID() == CFG.game.getCiv(nCivID).getPuppetOfCivID()
         || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(toProvinceID).getCivID()) != 0
         || CFG.game.getCiv(nCivID).getAllianceID() > 0
            && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()) {
         for(int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i > 0; --i) {
            if (CFG.game
               .getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private final void turnMoves_UpdatePlayersFogOfWar(int nCivID) {
      if (CFG.game.getCiv(nCivID).getControlledByPlayer() && CFG.PLAYER_TURNID != CFG.game.getPlayerID_ByCivID(nCivID)) {
         CFG.PLAYER_TURNID = CFG.game.getPlayerID_ByCivID(nCivID);
         if (CFG.FOG_OF_WAR > 0) {
            for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
               CFG.game.getProvince(i).updateDrawArmy();
            }
         }

         if (this.getNumOfPlayersInGame() > 1) {
            CFG.menuManager.updateInGame_TOP_All_NextTurnActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         }
      }
   }

   private final void turnMoves_MoveCurrentArmy() {
      CFG.menuManager.setVisible_InGame_Dices(false);
      if (this.currentMoveUnits.getCivID(0) != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
         && this.currentMoveUnits.getCivID(0)
            != CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getPuppetOfCivID()
         && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
            != CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID()
         && !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
         if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0
            && (
               CFG.game
                     .getCivsInAlliance(
                        this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                     )
                  || CFG.game
                        .getMilitaryAccess(
                           this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                        )
                     > 0
            )) {
            for(int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i > 0; --i) {
               if (CFG.game
                  .getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) {
                  int losses = Math.min(
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i),
                     this.currentMoveUnits.getMoveUnits_TotalNumOfUnits()
                  );
                  if (losses > 0) {
                     int tWarID = CFG.game
                        .getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                     int tempArmy = Math.min(losses, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                     if (tWarID >= 0) {
                        CFG.game
                           .getWar(tWarID)
                           .addCasualties(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), tempArmy);
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - losses
                        );

                     for(int j = 0; j < this.currentMoveUnits.getMoveUnitsSize(); ++j) {
                        if (this.currentMoveUnits.getMoveUnits(j).getNumOfUnits() > 0) {
                           tempArmy = Math.min(this.currentMoveUnits.getMoveUnits(j).getNumOfUnits(), losses);
                           if (tWarID >= 0) {
                              CFG.game.getWar(tWarID).addCasualties(this.currentMoveUnits.getCivID(j), tempArmy);
                           }

                           this.currentMoveUnits.getMoveUnits(j).setNumOfUnits(Math.max(this.currentMoveUnits.getMoveUnits(j).getNumOfUnits() - losses, 0));
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(j).getFromProvinceID())
                              .updateArmy(
                                 this.currentMoveUnits.getCivID(j),
                                 CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(j).getFromProvinceID())
                                       .getArmyCivID(this.currentMoveUnits.getCivID(j))
                                    - losses
                              );
                           losses -= tempArmy;
                           if (losses <= 0) {
                              break;
                           }
                        }
                     }
                  }
               }
            }

            this.turnMoves_MoveCurrentArmy_JustMove();
         } else {
            try {
               Gdx.app.log("AoC", "ATTACK: 111");
               if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() != 0
                  && !CFG.game
                     .getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                  )
                {
                  int tNumOfCivs = 1;

                  for(int c = 1; c < this.currentMoveUnits.getMoveUnitsSize(); ++c) {
                     if (this.currentMoveUnits.getCivID(0) != this.currentMoveUnits.getCivID(c)) {
                        ++tNumOfCivs;
                        break;
                     }
                  }

                  if (tNumOfCivs == 1) {
                     this.turnMoves_MoveCurrentArmy_JustMove();
                     this.currentMoveUnits = null;
                     return;
                  }

                  if (!CFG.game
                     .isAlly(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())) {
                     CFG.game
                        .declareWar(
                           this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(), false
                        );
                  }
               }

               if (this.SHOW_REPORT) {
                  CFG.reportData = new Report_Data();
                  CFG.reportData.iBattleOfProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
               }

               Gdx.app.log("AoC", "ATTACK: 222");
               int tempNumOfUnits = 0;

               for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                  tempNumOfUnits += this.currentMoveUnits.getMoveUnits(i).getNumOfUnits();
               }

               int tempPopulationBefore = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation();
               int tempEconomyBefore = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy();
               if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() == 0) {
                  this.updatePopulationLosses(
                     this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                     (int)Math.min(
                        (float)tempNumOfUnits * 0.0375F,
                        (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation() * 0.0025F
                     )
                  );
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateProvinceEconomyLosses(tempNumOfUnits, 0.0575F);
               } else {
                  this.updatePopulationLosses(
                     this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                     (int)Math.min(
                        (float)tempNumOfUnits * (0.0305F + 0.0065F * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel()),
                        (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation() * 0.0025F
                     )
                  );
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateProvinceEconomyLosses(tempNumOfUnits, 0.0575F);
               }

               Gdx.app.log("AoC", "ATTACK: 333");
               if (this.SHOW_REPORT) {
                  CFG.reportData.iPopulationLosses = tempPopulationBefore
                     - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation();
                  CFG.reportData.iEconomyLosses = tempEconomyBefore
                     - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy();
               }

               int tempWarID = CFG.game
                  .getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
               CFG.game
                  .updateWarStatistics(
                     tempWarID,
                     this.currentMoveUnits.getCivID(0),
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(),
                     tempPopulationBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation(),
                     tempEconomyBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy()
                  );
               Gdx.app.log("AoC", "ATTACK: 444");
               if (this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) {
                  Gdx.app.log("AoC", "WON: 111");
                  int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setWasAttacked(2);
                  Gdx.app.log("AoC", "WONBEFORE: attackersArmy: " + tempNumOfUnits);
                  Gdx.app.log("AoC", "WONBEFORE: defendersArmy: " + defendersArmy);
                  int var39 = (int)Math.ceil((double)tempNumOfUnits);
                  defendersArmy = (int)Math.ceil(
                     (double)(
                        (float)defendersArmy
                           * (
                              1.0F
                                 - this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 + this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                           )
                     )
                  );
                  Gdx.app.log("AoC", "WON: attackersArmy: " + var39);
                  Gdx.app.log("AoC", "WON: defendersArmy: " + defendersArmy);
                  CFG.game
                     .updateWarStatistics_Casualties(
                        tempWarID,
                        this.currentMoveUnits.getCivID(0),
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                     );
                  Gdx.app.log("AoC", "WON: 222");
                  if (this.SHOW_REPORT) {
                     CFG.reportData.attackersWon = true;
                     CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                     CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
                  }

                  CFG.game
                     .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))
                     .setNumOfUnits(
                        CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).getNumOfUnits()
                           - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                     );
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                  Gdx.app.log("AoC", "WON: 333");
                  int i = 1;

                  for(int iBreak = 0; i < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() && iBreak < 50; ++iBreak) {
                     if ((int)CFG.game
                              .getCivRelation_OfCivB(
                                 this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                              )
                           != -100
                        && !CFG.game
                           .isAlly(
                              this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                           )) {
                        CFG.game
                           .declareWar(
                              this.currentMoveUnits.getCivID(0),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                              false
                           );
                     }

                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                        );
                     if (this.SHOW_REPORT
                        && (
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) > 0
                              || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() == 1
                        )) {
                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                        CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                     }

                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits()
                              - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                        );
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), 0);
                  }

                  Gdx.app.log("AoC", "WON: 444");
                  List<Integer> tempAttackersCivID = new ArrayList<>();
                  List<Integer> tempAttackersArmy = new ArrayList<>();

                  for(int ix = 0; ix < this.currentMoveUnits.getMoveUnitsSize(); ++ix) {
                     boolean tempAdd = true;

                     for(int j = 0; j < tempAttackersCivID.size(); ++j) {
                        if (tempAttackersCivID.get(j) == this.currentMoveUnits.getCivID(ix)) {
                           tempAdd = false;
                           tempAttackersArmy.set(j, tempAttackersArmy.get(j) + this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits());
                           break;
                        }
                     }

                     if (tempAdd) {
                        tempAttackersCivID.add(this.currentMoveUnits.getCivID(ix));
                        tempAttackersArmy.add(this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits());
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(ix).getFromProvinceID())
                        .updateArmy(
                           this.currentMoveUnits.getCivID(ix),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(ix).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(ix))
                              - this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits()
                        );
                  }

                  Gdx.app.log("AoC", "WON: 555");
                  if (tempAttackersCivID.size() > 1) {
                     Gdx.app.log("AoC", "WON: 666A");
                     int ix = 0;

                     for(int iSize = tempAttackersCivID.size(); ix < iSize - 1; ++ix) {
                        int tempBiggestArmyID = ix;

                        for(int j = ix + 1; j < iSize; ++j) {
                           if (tempAttackersArmy.get(tempBiggestArmyID) < tempAttackersArmy.get(j)) {
                              tempBiggestArmyID = j;
                           }
                        }

                        if (tempBiggestArmyID != ix) {
                           int tempC = tempAttackersCivID.get(ix);
                           int tempA = tempAttackersArmy.get(ix);
                           tempAttackersCivID.set(ix, tempAttackersCivID.get(tempBiggestArmyID));
                           tempAttackersArmy.set(ix, tempAttackersArmy.get(tempBiggestArmyID));
                           tempAttackersCivID.set(tempBiggestArmyID, tempC);
                           tempAttackersArmy.set(tempBiggestArmyID, tempA);
                        }
                     }

                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           tempAttackersCivID.get(0),
                           tempAttackersArmy.get(0)
                              - (int)Math.ceil((double)((float)tempAttackersArmy.get(0).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy)))
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lAttackers_IDs.add(tempAttackersCivID.get(0));
                        CFG.reportData.lAttackers_Armies.add(tempAttackersArmy.get(0));
                        CFG.reportData
                           .lAttackers_Armies_Lost
                           .add(
                              tempAttackersArmy.get(0)
                                 - (int)Math.ceil(
                                    (double)((float)tempAttackersArmy.get(0).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy))
                                 )
                           );
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           (int)Math.ceil((double)((float)tempAttackersArmy.get(0).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy)))
                        );
                     CFG.game
                        .getCiv(tempAttackersCivID.get(0))
                        .setNumOfUnits(
                           CFG.game.getCiv(tempAttackersCivID.get(0)).getNumOfUnits()
                              - Math.min(
                                 tempAttackersArmy.get(0),
                                 tempAttackersArmy.get(0)
                                    - (int)Math.ceil(
                                       (double)((float)tempAttackersArmy.get(0).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy))
                                    )
                              )
                        );

                     for(int ixx = 1; ixx < tempAttackersCivID.size(); ++ixx) {
                        if ((int)CFG.game
                                 .getCivRelation_OfCivB(
                                    tempAttackersCivID.get(ixx), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                 )
                              != -100
                           && !CFG.game
                              .isAlly(tempAttackersCivID.get(ixx), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                           CFG.game
                              .declareWar(
                                 tempAttackersCivID.get(ixx), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0), false
                              );
                        }

                        CFG.game
                           .updateWarStatistics_Casualties(
                              tempWarID,
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                              tempAttackersCivID.get(ixx),
                              tempAttackersArmy.get(ixx)
                                 - (int)Math.floor(
                                    (double)((float)tempAttackersArmy.get(ixx).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy))
                                 )
                           );
                        if (this.SHOW_REPORT) {
                           CFG.reportData.lAttackers_IDs.add(tempAttackersCivID.get(ixx));
                           CFG.reportData.lAttackers_Armies.add(tempAttackersArmy.get(ixx));
                           CFG.reportData
                              .lAttackers_Armies_Lost
                              .add(
                                 tempAttackersArmy.get(ixx)
                                    - (int)Math.floor(
                                       (double)((float)tempAttackersArmy.get(ixx).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy))
                                    )
                              );
                        }

                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                           .updateArmy(
                              tempAttackersCivID.get(ixx),
                              (int)Math.floor((double)((float)tempAttackersArmy.get(ixx).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy)))
                           );
                        CFG.game
                           .getCiv(tempAttackersCivID.get(ixx))
                           .setNumOfUnits(
                              CFG.game.getCiv(tempAttackersCivID.get(ixx)).getNumOfUnits()
                                 - Math.min(
                                    tempAttackersArmy.get(ixx),
                                    tempAttackersArmy.get(ixx)
                                       - (int)Math.floor(
                                          (double)((float)tempAttackersArmy.get(ixx).intValue() / (float)tempNumOfUnits * (float)(var39 - defendersArmy))
                                       )
                                 )
                           );
                     }

                     if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() > 0
                        && CFG.ideologiesManager
                           .getIdeology(
                              CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getIdeologyID()
                           )
                           .REVOLUTIONARY) {
                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              != this.currentMoveUnits.getCivID(0)
                           && !CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 this.currentMoveUnits.getCivID(0)
                              )) {
                           ix = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           int tArmyTrueOwner = CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince());
                           int tTrueOwner = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince();
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tTrueOwner, tArmyTrueOwner);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), ix);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        } else {
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() >= 1
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() != tempAttackersCivID.get(0)
                        )
                      {
                        if (!CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                              )
                           || !CFG.game
                                 .getCivsAreAllied(
                                    tempAttackersCivID.get(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )
                              && CFG.game
                                    .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                    .getPuppetOfCivID()
                                 != tempAttackersCivID.get(0)
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game
                                       .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                       .getPuppetOfCivID(),
                                    tempAttackersCivID.get(0)
                                 )
                              && CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID()
                                 != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID(),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )) {
                           boolean ownerChanged = false;

                           for(int ixx = 0; ixx < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++ixx) {
                              if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx)
                                 == tempAttackersCivID.get(0)) {
                                 CFG.game
                                    .updateWarStatistics_ConqueredProvinces(
                                       tempWarID,
                                       tempAttackersCivID.get(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                    );
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(tempAttackersCivID.get(0), true);
                                 ownerChanged = true;
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                    this.updateInGame_ProvinceInfo();
                                 }
                                 break;
                              }
                           }

                           if (!ownerChanged) {
                              for(int ixx = 0;
                                 ixx < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize();
                                 ++ixx
                              ) {
                                 if (CFG.game
                                       .getCivsAtWar(
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                       )
                                    && (
                                       CFG.game
                                             .getCivsAreAllied(
                                                tempAttackersCivID.get(0),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx)
                                             )
                                          || CFG.game
                                                .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx))
                                                .getPuppetOfCivID()
                                             == tempAttackersCivID.get(0)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx)
                                                   )
                                                   .getPuppetOfCivID(),
                                                tempAttackersCivID.get(0)
                                             )
                                          || CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID()
                                             == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID(),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx)
                                             )
                                    )) {
                                    int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                    int tArmyTrue = CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince());
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), 0);
                                    CFG.game
                                       .updateWarStatistics_ConqueredProvinces(
                                          tempWarID,
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                       );
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ixx), true);
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), tArmyTrue
                                       );
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                                    ownerChanged = true;
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                       this.updateInGame_ProvinceInfo();
                                    }
                                    break;
                                 }
                              }
                           }

                           if (!ownerChanged) {
                              CFG.game
                                 .updateWarStatistics_ConqueredProvinces(
                                    tempWarID,
                                    tempAttackersCivID.get(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                 );
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(tempAttackersCivID.get(0), true);
                              if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                 this.updateInGame_ProvinceInfo();
                              }
                           }
                        } else {
                           ix = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           int tArmyTrue = CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince());
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), 0);
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), tArmyTrue);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), ix);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else {
                        CFG.game
                           .updateWarStatistics_ConqueredProvinces(
                              tempWarID, tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                           );
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(tempAttackersCivID.get(0), true);
                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                           this.updateInGame_ProvinceInfo();
                        }
                     }

                     Gdx.app.log("AoC", "WON: 777A END");
                  } else {
                     Gdx.app.log("AoC", "WON: 666B");
                     int tempDefendersArmyLeft = defendersArmy;

                     for(int ix = 0; ix < this.currentMoveUnits.getMoveUnitsSize(); ++ix) {
                        CFG.game
                           .updateWarStatistics_Casualties(
                              tempWarID,
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                              this.currentMoveUnits.getCivID(ix),
                              this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits() > tempDefendersArmyLeft
                                 ? tempDefendersArmyLeft
                                 : this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits()
                           );
                        tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits();
                        if (tempDefendersArmyLeft < 0) {
                           tempDefendersArmyLeft = 0;
                        }
                     }

                     if (this.SHOW_REPORT) {
                        tempDefendersArmyLeft = defendersArmy;

                        for(int ix = 0; ix < this.currentMoveUnits.getMoveUnitsSize(); ++ix) {
                           CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(ix));
                           CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits());
                           CFG.reportData
                              .lAttackers_Armies_Lost
                              .add(
                                 this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits() > tempDefendersArmyLeft
                                    ? tempDefendersArmyLeft
                                    : this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits()
                              );
                           tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits();
                           if (tempDefendersArmyLeft < 0) {
                              tempDefendersArmyLeft = 0;
                           }
                        }
                     }

                     Gdx.app.log("AoC", "WON: 777B");
                     Gdx.app.log("AoC", "WON: 777B: attackersArmy: " + var39);
                     Gdx.app.log("AoC", "WON: 777B: defendersArmy: " + defendersArmy);
                     Gdx.app.log("AoC", "WON: 777B: FROM ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmy(0));
                     Gdx.app.log("AoC", "WON: 777B: TO ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(var39 - defendersArmy);
                     CFG.game
                        .getCiv(this.currentMoveUnits.getCivID(0))
                        .setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - defendersArmy);
                     Gdx.app.log("AoC", "WON: 777C");
                     Gdx.app.log("AoC", "WON: 777B: FROM ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmy(0));
                     Gdx.app.log("AoC", "WON: 777B: TO ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() > 0
                        && CFG.ideologiesManager
                           .getIdeology(
                              CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getIdeologyID()
                           )
                           .REVOLUTIONARY) {
                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              != this.currentMoveUnits.getCivID(0)
                           && !CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 this.currentMoveUnits.getCivID(0)
                              )) {
                           int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           int tArmyTrueOwner = CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince());
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tArmyTrueOwner);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        } else {
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() >= 1
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                           != this.currentMoveUnits.getCivID(0)) {
                        if (!CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                              )
                           || !CFG.game
                                 .getCivsAreAllied(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )
                              && CFG.game
                                    .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                    .getPuppetOfCivID()
                                 != this.currentMoveUnits.getCivID(0)
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game
                                       .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                       .getPuppetOfCivID(),
                                    this.currentMoveUnits.getCivID(0)
                                 )
                              && CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID()
                                 != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID(),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )) {
                           boolean ownerChanged = false;

                           for(int ix = 0; ix < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++ix) {
                              if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix)
                                 == this.currentMoveUnits.getCivID(0)) {
                                 CFG.game
                                    .updateWarStatistics_ConqueredProvinces(
                                       tempWarID,
                                       this.currentMoveUnits.getCivID(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                    );
                                 CFG.game
                                    .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                    .setCivID(this.currentMoveUnits.getCivID(0), true);
                                 ownerChanged = true;
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                    this.updateInGame_ProvinceInfo();
                                 }
                                 break;
                              }
                           }

                           if (!ownerChanged) {
                              for(int ix = 0; ix < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ++ix) {
                                 if (CFG.game
                                       .getCivsAtWar(
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                       )
                                    && (
                                       CFG.game
                                             .getCivsAreAllied(
                                                this.currentMoveUnits.getCivID(0),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix)
                                             )
                                          || CFG.game
                                                .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix))
                                                .getPuppetOfCivID()
                                             == this.currentMoveUnits.getCivID(0)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix)
                                                   )
                                                   .getPuppetOfCivID(),
                                                this.currentMoveUnits.getCivID(0)
                                             )
                                          || CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID()
                                             == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID(),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix)
                                             )
                                    )) {
                                    int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                    CFG.game
                                       .updateWarStatistics_ConqueredProvinces(
                                          tempWarID,
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                       );
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix), true);
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                                    ownerChanged = true;
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                       this.updateInGame_ProvinceInfo();
                                    }
                                    break;
                                 }
                              }
                           }

                           if (!ownerChanged) {
                              CFG.game
                                 .updateWarStatistics_ConqueredProvinces(
                                    tempWarID,
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                 );
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                              if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                 this.updateInGame_ProvinceInfo();
                              }
                           }
                        } else {
                           int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else {
                        CFG.game
                           .updateWarStatistics_ConqueredProvinces(
                              tempWarID,
                              this.currentMoveUnits.getCivID(0),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                           );
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                           this.updateInGame_ProvinceInfo();
                        }
                     }

                     Gdx.app.log("AoC", "WON: 777B END");
                  }
               } else {
                  Gdx.app.log("AoC", "LOSS: 111");
                  if (this.SHOW_REPORT) {
                     CFG.reportData.attackersWon = false;
                  }

                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setWasAttacked(2);
                  int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefenders(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  int var38 = (int)Math.ceil(
                     (double)(
                        (float)tempNumOfUnits
                           * (
                              1.0F
                                 - this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 + this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                           )
                     )
                  );

                  for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           this.currentMoveUnits.getCivID(i),
                           this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i));
                        CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                        CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID())
                        .updateArmy(
                           this.currentMoveUnits.getCivID(i),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i))
                              - this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
                        );
                     CFG.game
                        .getCiv(this.currentMoveUnits.getCivID(i))
                        .setNumOfUnits(
                           CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getNumOfUnits() - this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
                        );
                  }

                  Gdx.app.log("AoC", "LOSS: 222");
                  if (numOfDefenders <= 1) {
                     Gdx.app.log("AoC", "LOSS: 333B");
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(),
                           var38
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                        CFG.reportData.lDefenders_ArmiesLost.add(var38);
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) - var38);
                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getNumOfUnits() - var38
                        );
                     Gdx.app.log("AoC", "LOSS: 333B END");
                  } else {
                     Gdx.app.log("AoC", "LOSS: 333A");
                     Gdx.app.log("AoC", "defendersArmy: " + defendersArmy);
                     Gdx.app.log("AoC", "attackersArmy: " + var38);
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           (int)Math.ceil(
                              (double)(
                                 (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                    / (float)defendersArmy
                                    * (float)var38
                              )
                           )
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                        CFG.reportData
                           .lDefenders_ArmiesLost
                           .add(
                              (int)Math.ceil(
                                 (double)(
                                    (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                       / (float)defendersArmy
                                       * (float)var38
                                 )
                              )
                           );
                     }

                     Gdx.app.log("AoC", "LOSS: 333A - 111");
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           (int)(
                              (double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                 - Math.ceil(
                                    (double)(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                          / (float)defendersArmy
                                          * (float)var38
                                    )
                                 )
                           )
                        );
                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).getNumOfUnits()
                              - (int)Math.ceil(
                                 (double)(
                                    (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                       / (float)defendersArmy
                                       * (float)var38
                                 )
                              )
                        );
                     Gdx.app.log("AoC", "LOSS: 333A - 222");
                     List<Integer> tempIDs = new ArrayList<>();
                     List<Integer> tempArmies = new ArrayList<>();
                     List<Integer> tempArmies_Lost = new ArrayList<>();

                     for(int i = 1; i < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize(); ++i) {
                        if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(
                           this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                        )) {
                           if ((int)CFG.game
                                    .getCivRelation_OfCivB(
                                       this.currentMoveUnits.getCivID(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                                    )
                                 != -100
                              && !CFG.game
                                 .isAlly(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                                 )) {
                              CFG.game
                                 .declareWar(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                    false
                                 );
                           }

                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 (int)Math.floor(
                                    (double)(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                          / (float)defendersArmy
                                          * (float)var38
                                    )
                                 )
                              );
                           if (this.SHOW_REPORT) {
                              CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                              CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                              CFG.reportData
                                 .lDefenders_ArmiesLost
                                 .add(
                                    (int)Math.floor(
                                       (double)(
                                          (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                             / (float)defendersArmy
                                             * (float)var38
                                       )
                                    )
                                 );
                           }

                           tempIDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                           tempArmies.add(
                              (int)(
                                 (double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                    - Math.floor(
                                       (double)(
                                          (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                             / (float)defendersArmy
                                             * (float)var38
                                       )
                                    )
                              )
                           );
                           tempArmies_Lost.add(
                              (int)Math.floor(
                                 (double)(
                                    (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                       / (float)defendersArmy
                                       * (float)var38
                                 )
                              )
                           );
                        }
                     }

                     for(int i = 0; i < tempIDs.size(); ++i) {
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tempIDs.get(i), tempArmies.get(i));
                        CFG.game.getCiv(tempIDs.get(i)).setNumOfUnits(CFG.game.getCiv(tempIDs.get(i)).getNumOfUnits() - tempArmies_Lost.get(i));
                     }

                     for(int i = 1; i < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize(); ++i) {
                        if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(
                           this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                        )) {
                           if ((int)CFG.game
                                    .getCivRelation_OfCivB(
                                       this.currentMoveUnits.getCivID(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                                    )
                                 != -100
                              && !CFG.game
                                 .isAlly(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                                 )) {
                              CFG.game
                                 .declareWar(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                    false
                                 );
                           }

                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 (int)Math.floor(
                                    (double)(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                          / (float)defendersArmy
                                          * (float)var38
                                    )
                                 )
                              );
                           if (this.SHOW_REPORT) {
                              CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                              CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                              CFG.reportData
                                 .lDefenders_ArmiesLost
                                 .add(
                                    (int)Math.floor(
                                       (double)(
                                          (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                             / (float)defendersArmy
                                             * (float)var38
                                       )
                                    )
                                 );
                           }

                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .updateArmy(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 (int)(
                                    (double)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                       - Math.floor(
                                          (double)(
                                             (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                                / (float)defendersArmy
                                                * (float)var38
                                          )
                                       )
                                 )
                              );
                           CFG.game
                              .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))
                              .setNumOfUnits(
                                 CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits()
                                    - (int)Math.floor(
                                       (double)(
                                          (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                                             / (float)defendersArmy
                                             * (float)var38
                                       )
                                    )
                              );
                           if (CFG.game
                                 .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))
                              == 0) {
                              --i;
                              Gdx.app.log("AoC", "LOSS: 333A - 222-----");
                           }
                        }
                     }

                     Gdx.app.log("AoC", "LOSS: 333A END");
                  }
               }

               if (this.SHOW_REPORT) {
                  CFG.menuManager.rebuildInGame_Report();
                  this.SHOW_REPORT = false;
               }

               if (this.iPlayerAttack_ShowArmyInProvinceID >= 0 && this.iPlayerAttack_ShowArmyInProvinceID < CFG.game.getProvincesSize()) {
                  CFG.game.getProvince(this.iPlayerAttack_ShowArmyInProvinceID).updateFogOfWar(CFG.PLAYER_TURNID);
               }
            } catch (IndexOutOfBoundsException var16) {
               CFG.exceptionStack(var16);
            } catch (NullPointerException var17) {
               CFG.exceptionStack(var17);
            }
         }
      } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
         && CFG.game.getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())) {
         Gdx.app.log("AoC", "SEA ATTACK");

         try {
            Gdx.app.log("AoC", "ATTACK: 111");
            if (this.SHOW_REPORT) {
               CFG.reportData = new Report_Data();
               CFG.reportData.iBattleOfProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
            }

            Gdx.app.log("AoC", "ATTACK: 222");
            int tempNumOfUnits = 0;

            for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
               tempNumOfUnits += this.currentMoveUnits.getMoveUnits(i).getNumOfUnits();
            }

            Gdx.app.log("AoC", "ATTACK: 333");
            if (this.SHOW_REPORT) {
               CFG.reportData.iPopulationLosses = 0;
               CFG.reportData.iEconomyLosses = 0;
            }

            int tempWarID = CFG.game
               .getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
            Gdx.app.log("AoC", "ATTACK: 444");
            if (this.turnMoves_MoveCurrentArmy_AttackResult_SEA(
               this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits, this.currentMoveUnits.getCivID(0)
            )) {
               int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(
                  this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), this.currentMoveUnits.getCivID(0)
               );
               int attackersArmy = this.currentMoveUnits.getMoveUnits(0).getNumOfUnits();
               if (this.SHOW_REPORT) {
                  CFG.reportData.attackersWon = true;
                  CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
               }

               for(int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i >= 1; --i) {
                  if (CFG.game
                     .getCivsAtWar(
                        this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                     )) {
                     if (this.SHOW_REPORT) {
                        tempWarID = CFG.game
                           .getWarID(
                              this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                           );
                        if (tempWarID >= 0) {
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                              );
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                              );
                        }

                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                        CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                     }

                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits()
                              - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i)
                        );
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).removeArmy_ID(i);
                  }
               }

               if (this.SHOW_REPORT) {
                  int var37 = defendersArmy;

                  for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                     CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i));
                     CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                     CFG.reportData
                        .lAttackers_Armies_Lost
                        .add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits() > var37 ? var37 : this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                     var37 -= this.currentMoveUnits.getMoveUnits(i).getNumOfUnits();
                     if (var37 < 0) {
                        var37 = 0;
                     }
                  }
               }

               CFG.game
                  .getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID())
                  .updateArmy(
                     this.currentMoveUnits.getCivID(0),
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(0))
                        - this.currentMoveUnits.getMoveUnits(0).getNumOfUnits()
                  );
               CFG.game
                  .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                  .updateArmy(this.currentMoveUnits.getCivID(0), attackersArmy - defendersArmy);
               CFG.game
                  .getCiv(this.currentMoveUnits.getCivID(0))
                  .setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - defendersArmy);
            } else {
               int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(
                  this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), this.currentMoveUnits.getCivID(0)
               );
               int attackersArmy = this.currentMoveUnits.getMoveUnits(0).getNumOfUnits();
               if (this.SHOW_REPORT) {
                  CFG.reportData.attackersWon = false;
                  CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
               }

               int tempDefendersArmyLeft = attackersArmy;
               boolean firstCeil = true;

               for(int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i >= 1; --i) {
                  if (CFG.game
                     .getCivsAtWar(
                        this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                     )) {
                     float tempCurrentLosses = (float)attackersArmy
                        * ((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) / (float)defendersArmy);
                     int currentLosses = (int)(firstCeil ? Math.ceil((double)tempCurrentLosses) : Math.floor((double)tempCurrentLosses));
                     firstCeil = false;
                     if (this.SHOW_REPORT) {
                        tempWarID = CFG.game
                           .getWarID(
                              this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)
                           );
                        if (tempWarID >= 0) {
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 currentLosses
                              );
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                                 this.currentMoveUnits.getCivID(0),
                                 currentLosses
                              );
                        }

                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                        CFG.reportData.lDefenders_ArmiesLost.add(currentLosses);
                     }

                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i)).getNumOfUnits()
                              - currentLosses
                        );
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - currentLosses
                        );
                     tempDefendersArmyLeft -= currentLosses;
                     if (tempDefendersArmyLeft < 0) {
                        tempDefendersArmyLeft = 0;
                     }
                  }
               }

               if (this.SHOW_REPORT) {
                  tempDefendersArmyLeft = defendersArmy;

                  for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
                     CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(i));
                     CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                     CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(i).getNumOfUnits());
                     tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(i).getNumOfUnits();
                     if (tempDefendersArmyLeft < 0) {
                        tempDefendersArmyLeft = 0;
                     }
                  }
               }

               CFG.game
                  .getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID())
                  .updateArmy(
                     this.currentMoveUnits.getCivID(0),
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(0))
                        - this.currentMoveUnits.getMoveUnits(0).getNumOfUnits()
                  );
               CFG.game
                  .getCiv(this.currentMoveUnits.getCivID(0))
                  .setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - attackersArmy);
            }

            if (this.SHOW_REPORT) {
               CFG.menuManager.rebuildInGame_Report();
               this.SHOW_REPORT = false;
            }

            if (this.iPlayerAttack_ShowArmyInProvinceID >= 0 && this.iPlayerAttack_ShowArmyInProvinceID < CFG.game.getProvincesSize()) {
               CFG.game.getProvince(this.iPlayerAttack_ShowArmyInProvinceID).updateFogOfWar(CFG.PLAYER_TURNID);
            }
         } catch (IndexOutOfBoundsException var14) {
            CFG.exceptionStack(var14);
         } catch (NullPointerException var15) {
            CFG.exceptionStack(var15);
         }
      } else {
         this.turnMoves_MoveCurrentArmy_JustMove();
      }

      this.currentMoveUnits = null;
   }

   private final void rollDices() {
      this.diceAggressors = CFG.oR.nextInt(725) % 6 + 1;
      this.diceDefenders = CFG.oR.nextInt(600) % 6 + 1;
   }

   protected final float diceRollBonus(boolean defenders) {
      int tDifference = defenders ? this.diceDefenders - this.diceAggressors : this.diceAggressors - this.diceDefenders;
      return tDifference > 0 ? 2.5F * (float)tDifference : 0.0F;
   }

   private final boolean turnMoves_MoveCurrentArmy_AttackResult(int toProvinceID, int numOfAttackers) {
      int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(toProvinceID);
      float fDefensiveArmyModifiers = 1.0F;
      float fOffensiveArmyModifiers = 1.0F;
      fDefensiveArmyModifiers += this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(toProvinceID);
      fOffensiveArmyModifiers += this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(toProvinceID);
      if (fDefensiveArmyModifiers < 0.01F) {
         fDefensiveArmyModifiers = 0.01F;
      }

      if (fOffensiveArmyModifiers < 0.01F) {
         fOffensiveArmyModifiers = 0.01F;
      }

      return (float)numOfAttackers * fOffensiveArmyModifiers > (float)numOfDefenders * fDefensiveArmyModifiers;
   }

   private final boolean turnMoves_MoveCurrentArmy_AttackResult_SEA(int toProvinceID, int numOfAttackers, int attackersCivID) {
      int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(toProvinceID, attackersCivID);
      return numOfAttackers > numOfDefenders;
   }

   private final int turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(int toProvinceID) {
      int numOfDefenders = CFG.game.getProvince(toProvinceID).getArmy(0);

      for(int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); ++i) {
         if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(toProvinceID, CFG.game.getProvince(toProvinceID).getCivID(i))) {
            numOfDefenders += CFG.game.getProvince(toProvinceID).getArmy(i);
         }
      }

      return numOfDefenders;
   }

   private final int turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(int toProvinceID, int attackersCivID) {
      int numOfDefenders = 0;

      for(int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); ++i) {
         if (CFG.game.getCivsAtWar(CFG.game.getProvince(toProvinceID).getCivID(i), attackersCivID)) {
            numOfDefenders += CFG.game.getProvince(toProvinceID).getArmy(i);
         }
      }

      return numOfDefenders;
   }

   private final int turnMoves_MoveCurrentArmy_Attack_NumOfDefenders(int toProvinceID) {
      int numOfDefenders = 1;

      for(int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); ++i) {
         if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(toProvinceID, CFG.game.getProvince(toProvinceID).getCivID(i))) {
            ++numOfDefenders;
         }
      }

      return numOfDefenders;
   }

   private final boolean turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(int toProvinceID, int nCivID) {
      return CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID(0)).getAllianceID() > 0
         && CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID(0)).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID();
   }

   protected final float getDefenseBonusFromTechnology(int nCivID) {
      return nCivID > 0 ? Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 18.0F * 1.75F, 31.5F) : 0.0F;
   }

   protected final int moveArmyModifiers_Defenders(int fromProvinceID, int toProvinceID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            float fOut = (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS
               / 100.0F;
            fOut += (float)((int)this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID())) / 100.0F;
            if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
               fOut += 0.15F;
            }

            fOut += (float)BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) / 100.0F;
            fOut += (float)BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) / 100.0F;
            fOut += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
            fOut += CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus();
            if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
               fOut -= this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID);
            }

            return (int)(fOut * 100.0F);
         } else {
            return 0;
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected final List<MenuElement_Hover_v2_Element2> getMoveArmyModifiers_Defenders_Hover(int fromProvinceID, int toProvinceID) {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseOfTheCapital") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+15%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            int fTech = (int)this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID());
            if (fTech > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + fTech + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(toProvinceID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) > 0) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(toProvinceID).getLevelOfFort())) + ": "
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) > 0) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get(BuildingsManager.getTower_Name(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower())) + ": "
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) + "%",
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() != 0.0F) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Bonus") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     (CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() > 0.0F ? "+" : "")
                        + (int)(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() * 100.0F)
                        + "%",
                     CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() > 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(toProvinceID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) != 0.0F) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) + ": ")
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0F ? "+" : "")
                        + (int)(CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) * 100.0F)
                        + "%",
                     CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(toProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceIsNotSupplied") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "-" + (int)(this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID) * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }
         }
      } catch (IndexOutOfBoundsException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }

      return nElements;
   }

   protected final float getAttackersBonusFromTechnology(int nCivID) {
      return Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 18.0F, 18.0F);
   }

   protected final int moveArmyModifiers_Attackers(int fromProvinceID, int toProvinceID, int iCivID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            float fOut = 0.0F;
            if (CFG.game.getProvince(fromProvinceID).getIsCapital()) {
               fOut += 0.1F;
            }

            fOut += this.getAttackersBonusFromTechnology(iCivID) / 100.0F;
            fOut += CFG.game.getCiv(iCivID).getModifier_AttackBonus();
            return (int)(fOut * 100.0F);
         } else {
            return 0;
         }
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }

         return 0;
      }
   }

   protected final List<MenuElement_Hover_v2_Element2> getMoveArmyModifiers_Attackers_Hover(int fromProvinceID, int toProvinceID, int iCivID) {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            if (CFG.game.getProvince(fromProvinceID).getIsCapital()) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackFromCapital") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+10%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            int fTech = (int)this.getAttackersBonusFromTechnology(iCivID);
            if (fTech > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + fTech + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getCiv(iCivID).getModifier_AttackBonus() != 0.0F) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Bonus") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     (CFG.game.getCiv(iCivID).getModifier_AttackBonus() > 0.0F ? "+" : "")
                        + (int)(CFG.game.getCiv(iCivID).getModifier_AttackBonus() * 100.0F)
                        + "%",
                     CFG.game.getCiv(iCivID).getModifier_AttackBonus() > 0.0F ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var7);
         }
      }

      return nElements;
   }

   protected final float turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(int toProvinceID) {
      float fOffensiveArmyModifiers = (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS
         / 100.0F;
      if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
         fOffensiveArmyModifiers += 0.15F;
      }

      fOffensiveArmyModifiers += (float)BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) / 100.0F;
      fOffensiveArmyModifiers += this.diceRollBonus(true) / 100.0F;
      fOffensiveArmyModifiers += (float)BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) / 100.0F;
      if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0F) {
         fOffensiveArmyModifiers += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
      }

      fOffensiveArmyModifiers += this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID()) / 100.0F;
      return fOffensiveArmyModifiers + CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus();
   }

   protected final float getDefenseBonusLossPerTurnForNotSuppliedProvince(int toProvinceID) {
      return Math.min(0.1F * (float)CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns(), 0.85F);
   }

   protected final float turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(int toProvinceID) {
      float fDefensiveArmyModifiers = 0.0F;
      if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) < 0.0F) {
         fDefensiveArmyModifiers += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
      }

      if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
         fDefensiveArmyModifiers += this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID);
      }

      fDefensiveArmyModifiers += this.diceRollBonus(false) / 100.0F;

      for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
         if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getIsCapital()) {
            fDefensiveArmyModifiers += 0.1F;
            break;
         }
      }

      float fBest = 0.0F;

      for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
         if (CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getModifier_AttackBonus() > fBest) {
            fBest = CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getModifier_AttackBonus();
         }
      }

      fDefensiveArmyModifiers += fBest;
      fBest = 0.0F;

      for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
         if (this.getAttackersBonusFromTechnology(this.currentMoveUnits.getCivID(i)) / 100.0F > fBest) {
            fBest = this.getAttackersBonusFromTechnology(this.currentMoveUnits.getCivID(i)) / 100.0F;
         }
      }

      return fDefensiveArmyModifiers + fBest;
   }

   private final void turnMoves_MoveCurrentArmy_JustMove() {
      for(int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); ++i) {
         CFG.game
            .getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID())
            .updateArmy(
               this.currentMoveUnits.getCivID(i),
               CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i))
                  - this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
            );
         CFG.game
            .getProvince(this.currentMoveUnits.getMoveUnits(i).getToProvinceID())
            .updateArmy(
               this.currentMoveUnits.getCivID(i),
               CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getToProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i))
                  + this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
            );
      }
   }

   protected final void loadActivePlayerData() {
      Gdx.app.log("AoC", "loadActivePlayerData: 00000");
      if (CFG.FOG_OF_WAR > 0) {
         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
               CFG.game.getProvince(i).updateProvinceBorder();
            }

            Game_Render.updateDrawCivRegionNames_FogOfWar();
         }

         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getProvince(i).updateDrawArmy();
         }
      }

      Gdx.app.log("AoC", "loadActivePlayerData: 1111");
      CFG.menuManager.rebuildInGame_Messages();
      Gdx.app.log("AoC", "loadActivePlayerData: 222");
      CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
      Gdx.app.log("AoC", "loadActivePlayerData: 333");
      CFG.game.buildMoveUnits_JustDraw_AnotherArmies();
      Gdx.app.log("AoC", "loadActivePlayerData: 444");

      try {
         if (!CFG.SPECTATOR_MODE
            && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() == 0
            && this.showDefeatView(CFG.PLAYER_TURNID)
            && !gameEnded) {
            CFG.menuManager.setViewID(Menu.eDEFEAT);
            CFG.map.getMapBG().updateWorldMap_Shaders();
            CFG.toast.setInView(CFG.langManager.get("Defeat"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            gameEnded = true;
         } else if (CFG.settingsManager.CONFIRM_NEXT_PLAYER_TURN) {
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            if ((!RTS.isEnabled() || RTS.PAUSE) && !CFG.SPECTATOR_MODE && this.showNextPlayerTurnView_NextTurn()) {
               CFG.menuManager.setViewIDWithoutAnimation(Menu.eNEXT_PLAYER_TURN);
               CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0);
               CFG.map.getMapBG().updateWorldMap_Shaders();
            } else {
               Menu_NextPlayerTurn.clickEnd();
            }

            Menu_InGame_Messages.START_ANIMATION = true;
         }
      } catch (IndexOutOfBoundsException var2) {
         Menu_NextPlayerTurn.clickEnd();
      } catch (NullPointerException var3) {
         Menu_NextPlayerTurn.clickEnd();
      } catch (StackOverflowError var4) {
         Menu_NextPlayerTurn.clickEnd();
      } catch (ArithmeticException var5) {
         Menu_NextPlayerTurn.clickEnd();
      }

      Gdx.app.log("AoC", "loadActivePlayerData: END");
   }

   protected final void checkGameEnd() {
      if (!CFG.SPECTATOR_MODE && !gameEnded) {
         for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            int numOfProvinces = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces();
            Gdx.app.log("AoC", "checkGameEnd: numOfProvinces1: " + numOfProvinces);

            for(int z = 0; z < CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).civGameData.iVassalsSize; ++z) {
               numOfProvinces += CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).civGameData.lVassals.get(z).iCivID).getNumOfProvinces();
            }

            Gdx.app.log("AoC", "checkGameEnd: numOfProvinces2: " + numOfProvinces);
            if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() > 0) {
               for(int z = 0; z < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilizationsSize(); ++z) {
                  if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(z)
                        != CFG.game.getPlayer(i).getCivID()
                     && CFG.game
                           .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(z))
                           .getPuppetOfCivID()
                        != CFG.game.getPlayer(i).getCivID()) {
                     numOfProvinces += CFG.game
                        .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(z))
                        .getNumOfProvinces();
                  }
               }
            }

            Gdx.app.log("AoC", "checkGameEnd: numOfProvinces3: " + numOfProvinces);
            Gdx.app.log("AoC", "checkGameEnd: VIC CFG.oAI.PLAYABLE_PROVINCES: " + CFG.oAI.PLAYABLE_PROVINCES);
            Gdx.app.log("AoC", "checkGameEnd: CFG.oAI.NUM_OF_CIVS_IN_THE_GAME: " + CFG.oAI.NUM_OF_CIVS_IN_THE_GAME);
            if (VicotryManager.VICTORY_LIMIT_OF_TURNS != 0 && VicotryManager.VICTORY_LIMIT_OF_TURNS < Game_Calendar.TURN_ID) {
               Gdx.app.log("AoC", "checkGameEnd: VIC 0000");
               CFG.menuManager.setViewID(Menu.eVICTORY);
               CFG.map.getMapBG().updateWorldMap_Shaders();
               CFG.toast.setInView("TurnsLimit", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
               CFG.toast.setTimeInView(4500);
               gameEnded = true;
            } else if (CFG.oAI.PLAYABLE_PROVINCES > numOfProvinces
               && !((float)CFG.oAI.PLAYABLE_PROVINCES <= (float)numOfProvinces * ((float)VicotryManager.VICTORY_CONTROL_PROVINCES_PERC / 100.0F))
               && CFG.oAI.NUM_OF_CIVS_IN_THE_GAME >= 2) {
               Gdx.app.log("AoC", "checkGameEnd: VIC 2222");
               if (VicotryManager.VICTORY_TECHNOLOGY > 0.0F) {
                  for(int z = 1; z < CFG.game.getCivsSize(); ++z) {
                     Gdx.app.log("AoC", "checkGameEnd: VIC 222: CIV: " + CFG.game.getCiv(z).getCivName());
                     Gdx.app.log("AoC", "checkGameEnd: VIC 222: CFG.game.getCiv(z).getTechnologyLevel(): " + CFG.game.getCiv(z).getTechnologyLevel());
                     Gdx.app.log("AoC", "checkGameEnd: VIC 222:  VicotryManager.VICTORY_TECHNOLOGY: " + VicotryManager.VICTORY_TECHNOLOGY);
                     if (CFG.game.getCiv(z).getNumOfProvinces() > 0 && CFG.game.getCiv(z).getTechnologyLevel() >= VicotryManager.VICTORY_TECHNOLOGY) {
                        if (CFG.game.getCiv(z).getControlledByPlayer()) {
                           CFG.menuManager.setViewID(Menu.eVICTORY);
                           CFG.map.getMapBG().updateWorldMap_Shaders();
                           CFG.toast.setInView("Technology: " + VicotryManager.VICTORY_TECHNOLOGY, CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                           CFG.toast.setTimeInView(4500);
                           gameEnded = true;
                        } else {
                           CFG.menuManager.setViewID(Menu.eDEFEAT);
                           CFG.map.getMapBG().updateWorldMap_Shaders();
                           CFG.toast.setInView("Technology: " + VicotryManager.VICTORY_TECHNOLOGY, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                           CFG.toast.setTimeInView(4500);
                           gameEnded = true;
                        }
                     }
                  }
               }
            } else {
               Gdx.app.log("AoC", "checkGameEnd: VIC 1111");
               CFG.menuManager.setViewID(Menu.eVICTORY);
               CFG.map.getMapBG().updateWorldMap_Shaders();
               gameEnded = true;
            }
         }
      }
   }

   protected final boolean showDefeatView(int nPlayerID) {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()
            && CFG.game.getProvince(i).getWasteland() < 0
            && CFG.game.getProvince(i).getTrueOwnerOfProvince() == CFG.game.getPlayer(nPlayerID).getCivID()) {
            return false;
         }
      }

      if (!CFG.game.getPlayer(nPlayerID).savePlayer.lostNextTurn) {
         CFG.game.getPlayer(nPlayerID).savePlayer.lostNextTurn = true;
         return false;
      } else {
         return true;
      }
   }

   protected final boolean showNextPlayerTurnView() {
      return CFG.settingsManager.showNextPlayerView || SaveManager.gameWillBeSavedInThisTurn() || this.getNumOfPlayersInGame() > 1;
   }

   protected final boolean showNextPlayerTurnView_NextTurn() {
      return CFG.settingsManager.showNextPlayerView || SaveManager.forceShowNextPlayerTurnView || this.getNumOfPlayersInGame() > 1;
   }

   protected int getNumOfPlayersInGame() {
      int out = 0;

      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
            ++out;
         }
      }

      return out;
   }

   protected final void buildFogOfWar(int nPlayerID) {
      try {
         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            CFG.game.getPlayer(nPlayerID).setFogOfWar(i, false);
         }

         this.buildFogOfWar_CivID(nPlayerID, CFG.game.getPlayer(nPlayerID).getCivID());
         if (CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() > 0) {
            for(int i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilizationsSize(); ++i) {
               if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilization(i)
                  != CFG.game.getPlayer(nPlayerID).getCivID()) {
                  this.buildFogOfWar_CivID(
                     nPlayerID, CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilization(i)
                  );
               }
            }
         }

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (i != CFG.game.getPlayer(nPlayerID).getCivID() && CFG.game.getCiv(i).getPuppetOfCivID() == CFG.game.getPlayer(nPlayerID).getCivID()) {
               this.buildFogOfWar_CivID(nPlayerID, i);
            }
         }

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (i != CFG.game.getPlayer(nPlayerID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() == i) {
               this.buildFogOfWar_CivID(nPlayerID, i);
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      }
   }

   protected final void buildFogOfWar_CivID(int nPlayerID, int nCivID) {
      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getCiv(nCivID).getProvinceID(i), true);

         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
            CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j), true);
         }

         this.buildFogOfWar_WatchTower(nPlayerID, CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
         CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), true);
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getSeaProvince()) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getNeighboringProvinces(j)).getSeaProvince()
                  )
                {
                  CFG.game
                     .getPlayer(nPlayerID)
                     .setFogOfWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getNeighboringProvinces(j), true);
               }
            }
         }
      }
   }

   protected final void buildFogOfWar_WatchTower(int nPlayerID, int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() > 0) {
         if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() == 1) {
            for(int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getLevelOfFort() < 1) {
                  CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j), true);
               }
            }
         } else {
            for(int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getLevelOfFort() < 1) {
                  CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j), true);

                  for(int k = 0; k < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getNeighboringProvincesSize(); ++k) {
                     if (CFG.game
                           .getProvince(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getNeighboringProvinces(k))
                           .getLevelOfFort()
                        < 1) {
                        CFG.game
                           .getPlayer(nPlayerID)
                           .setFogOfWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getNeighboringProvinces(k), true);
                     }
                  }
               }
            }
         }
      }
   }

   protected final boolean hasArmyInProvince(int nProvinceID, int nCivID) {
      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getCivID(i) == nCivID) {
            if (CFG.game.getProvince(nProvinceID).getArmy(i) > 0) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   protected final boolean hasArmyInProvince_AllianceID(int nProvinceID, int nAllianceID) {
      if (nAllianceID == 0) {
         return false;
      } else {
         for(int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getAllianceID() == nAllianceID) {
               return true;
            }
         }

         return false;
      }
   }

   protected final boolean isMovingArmyFromProvince(int nProvinceID) {
      return this.isMovingArmyFromProvince(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
   }

   protected final boolean isMovingArmyFromProvince(int nProvinceID, int nCivID) {
      for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
         if (CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID() == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean controlsArmyInProvince(int nProvinceID) {
      return this.controlsArmyInProvince(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
   }

   protected final boolean controlsArmyInProvince(int nProvinceID, int nCivID) {
      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getCivID(i) == nCivID && CFG.game.getProvince(nProvinceID).getArmy(i) > 0) {
            CFG.activeCivilizationArmyID = i;
            return true;
         }
      }

      CFG.activeCivilizationArmyID = 0;
      return false;
   }

   protected final boolean canColonizieWasteland_Tech(int nProvinceID, int nCivID) {
      if (!Game_Calendar.getColonizationOfWastelandIsEnabled()) {
         return false;
      } else {
         return Game_Calendar.getCanColonize_TechLevel(nCivID);
      }
   }

   protected final boolean canColonizieNeutral_Tech(int nProvinceID, int nCivID) {
      return Game_Calendar.getCanColonize_TechLevel(nCivID);
   }

   protected final boolean canColonizieWasteland_BorderOrArmy(int nProvinceID, int nCivID) {
      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() < 0) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
               return true;
            }

            for(int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j) == nCivID) {
                  return true;
               }
            }
         }
      }

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
         for(int j = 1; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivsSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivID(j) == nCivID) {
               return true;
            }
         }
      }

      return false;
   }

   protected final void resetTurnData() {
      if (Game_Action.TurnStates.INPUT_ORDERS == this.activeTurnAction) {
         CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
      }

      if (CFG.chooseProvinceMode) {
         CFG.game.resetChooseProvinceData();
      }

      if (CFG.regroupArmyMode) {
         CFG.game.resetRegroupArmyData();
      }
   }

   protected final void hideAllProvinceActionViews() {
      CFG.menuManager.setVisible_InGame_ActionInfo(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction(false);
      CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(false);
      CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
      CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
      CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
      CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
   }

   protected final void hideAllViews() {
      this.hideAllProvinceActionViews();
      CFG.menuManager.updateInGameRTO(false);
      if (CFG.menuManager.getColorPicker().getVisible()) {
         CFG.menuManager.getColorPicker().setVisible(false, null);
      }
   }

   protected final boolean canMigrate_MovementPoints(int iCivID) {
      return CFG.game.getCiv(iCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_MOVE;
   }

   protected final boolean migrateToProvince(int fromProvinceID, int toProvinceID, int iCivID, boolean buildLine) {
      if (!this.canMigrate_MovementPoints(iCivID)) {
         return false;
      } else if (!Game.uncivilizedCanMigrate_FromProvince(fromProvinceID, iCivID)) {
         return false;
      } else if (CFG.game.getCiv(iCivID).migratesFromProvinceID(fromProvinceID)) {
         return false;
      } else {
         CFG.game.getCiv(iCivID).newMigrate(fromProvinceID, toProvinceID, buildLine);
         CFG.game
            .getCiv(iCivID)
            .setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_MOVE);
         return true;
      }
   }

   protected final boolean moveArmy(int fromProvinceID, int toProvinceID, int nNumOfUnits, int iCivID, boolean regroupMode, boolean buildLine) {
      try {
         if (nNumOfUnits == 0) {
            for(int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ++i) {
               if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() == fromProvinceID
                  && CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
                  CFG.game
                     .getProvince(fromProvinceID)
                     .updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) + CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits());
                  CFG.game.getCiv(iCivID).removeMove(i);

                  for(int j = 0; j < CFG.game.getCiv(iCivID).getRegroupArmySize(); ++j) {
                     if (CFG.game.getCiv(iCivID).getRegroupArmy(j).getFromProvinceID() == toProvinceID) {
                        CFG.game.getCiv(iCivID).removeRegroupArmy(j--);
                     }
                  }

                  CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() + this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID));
                  return false;
               }
            }

            return false;
         } else {
            for(int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ++i) {
               if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() == fromProvinceID
                  && CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
                  if (regroupMode) {
                     if (CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) < nNumOfUnits) {
                        nNumOfUnits = CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID);
                     }

                     CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits);
                     CFG.game.getCiv(iCivID).getMoveUnits(i).setNumOfUnits(CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits() + nNumOfUnits);
                  } else {
                     CFG.game
                        .getProvince(fromProvinceID)
                        .updateArmy(
                           iCivID,
                           CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - (nNumOfUnits - CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits())
                        );
                     CFG.game.getCiv(iCivID).getMoveUnits(i).setNumOfUnits(nNumOfUnits);
                  }

                  return true;
               }
            }

            if (CFG.game.getCiv(iCivID).getMovePoints() < this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID)) {
               return false;
            } else if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()
               && CFG.game.getProvince(toProvinceID).getSeaProvince()
               && CFG.game.getProvince(fromProvinceID).getLevelOfPort() < 1) {
               return false;
            } else {
               if (nNumOfUnits > CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID)) {
                  nNumOfUnits = CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID);
               }

               if (nNumOfUnits <= 0) {
                  return false;
               } else {
                  CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID));
                  CFG.game.getCiv(iCivID).newMove(fromProvinceID, toProvinceID, nNumOfUnits, buildLine);
                  CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var9) {
         CFG.exceptionStack(var9);
         return false;
      }
   }

   protected final int costOfMoveArmy(int fromProvinceID, int toProvinceID, int nCivID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() > 0
            && CFG.game.getProvince(toProvinceID).getCivID() > 0
            && CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() == nCivID) {
            return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE;
         } else if (CFG.game.getProvince(fromProvinceID).getSeaProvince()) {
            return CFG.game.getProvince(toProvinceID).getSeaProvince()
               ? (int)((float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 1.5F)
               : CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 2;
         } else {
            for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
               if (CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
                  return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE;
               }
            }

            return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
         }
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }

         return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
      }
   }

   protected final boolean getIsFreeMove(int iCivID, int fromProvinceID, int toProvinceID) {
      for(int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ++i) {
         if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() == fromProvinceID
            && CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final void updatePopulationLosses(int nProvinceID, int iLosses) {
      int nRecuritedPop = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(
                           (double)((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))
                        )
                  )
               )) {
               --i;
            }
         } else if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.ceil(
                           (double)((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))
                        )
                  )
               )) {
               --i;
            }
         } else if ((int)CFG.game
               .getCivRelation_OfCivB(CFG.game.getProvince(nProvinceID).getCivID(), CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i))
            == -100) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(
                           (double)((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))
                        )
                  )
               )) {
               --i;
            }
         } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID()
               == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(
                           (double)((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))
                        )
                  )
               )) {
               --i;
            }
         } else if (CFG.game
            .getProvince(nProvinceID)
            .getPopulationData()
            .setPopulationOfCivID(
               CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
               (int)(
                  (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                     - Math.floor(
                        (double)((float)iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop))
                     )
               )
            )) {
            --i;
         }
      }

      nRecuritedPop -= CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
      if (nRecuritedPop < iLosses) {
         nRecuritedPop = iLosses - nRecuritedPop;
         int i = 0;

         for(int tPop = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            tPop = Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i));
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                     - Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i))
               )) {
               --i;
            }

            nRecuritedPop -= tPop;
            if (nRecuritedPop <= 0) {
               break;
            }
         }
      }
   }

   protected final void recruitArmyInstantly(int nProvinceID, int nNumOfUnits, int nCivID) {
      if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
         >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT) {
         if ((long)nNumOfUnits
            >= CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMoney() / (long)CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID)) {
            nNumOfUnits = (int)CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID);
         }

         if (nNumOfUnits >= this.getRecruitableArmy(nProvinceID)) {
            nNumOfUnits = this.getRecruitableArmy(nProvinceID);
         }

         if (nNumOfUnits > 0) {
            CFG.game
               .getCiv(nCivID)
               .setMovePoints(
                  CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
               );
            CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)(nNumOfUnits * CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID)));
            this.recruitArmy(nProvinceID, nNumOfUnits, nCivID);
         }
      }
   }

   protected final void recruitArmy(int nProvinceID, int nNumOfUnits, int nCivID) {
      if (nNumOfUnits >= this.getRecruitableArmy(nProvinceID)) {
         nNumOfUnits = this.getRecruitableArmy(nProvinceID);
      }

      if (nNumOfUnits > 0) {
         int tempProvincePopulation = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
         CFG.game
            .getProvince(nProvinceID)
            .setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() - 0.1375F * ((float)nNumOfUnits / (float)tempProvincePopulation));
         CFG.game
            .getProvince(nProvinceID)
            .setEconomy(
               (int)(
                  (float)CFG.game.getProvince(nProvinceID).getEconomy()
                     - (float)CFG.game.getProvince(nProvinceID).getEconomy()
                        * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2875F : 0.575F + (float)CFG.oR.nextInt(175) / 1000.0F)
                        * ((float)nNumOfUnits / (float)tempProvincePopulation)
               )
            );
         CFG.game
            .getProvince(nProvinceID)
            .setDevelopmentLevel(
               CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                  - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                     * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.08125F : 0.1625F + (float)CFG.oR.nextInt(125) / 1000.0F)
                     * ((float)nNumOfUnits / (float)tempProvincePopulation)
            );
         CFG.game.getProvince(nProvinceID).updateArmy(CFG.game.getProvince(nProvinceID).getArmy(0) + Math.max(nNumOfUnits, 0));
         Save_Civ_GameData var10000 = CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).civGameData;
         var10000.iRecruitedArmy += Math.max(nNumOfUnits, 0);
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getControlledByPlayer()) {
            int nPlayerID = CFG.game.getPlayerID_ByCivID(CFG.game.getProvince(nProvinceID).getCivID());

            try {
               CFG.game
                  .getPlayer(nPlayerID)
                  .statistics_Civ_GameData
                  .setRecruitedArmy(CFG.game.getPlayer(nPlayerID).statistics_Civ_GameData.getRecruitedArmy() + Math.max(nNumOfUnits, 0));
            } catch (IndexOutOfBoundsException var8) {
               CFG.exceptionStack(var8);
            } catch (NullPointerException var9) {
               CFG.exceptionStack(var9);
            }
         }

         CFG.game
            .getCiv(CFG.game.getProvince(nProvinceID).getCivID())
            .setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfUnits() + nNumOfUnits);
         int nRecuritedPop = tempProvincePopulation;

         for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.floor(
                              (double)(
                                 (float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop)
                              )
                           )
                     )
                  )) {
                  --i;
                  Gdx.app.log("GameAction", "recruit--1");
               }
            } else if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.ceil(
                              (double)(
                                 (float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop)
                              )
                           )
                     )
                  )) {
                  --i;
                  Gdx.app.log("GameAction", "recruit--2");
               }
            } else if ((int)CFG.game
                  .getCivRelation_OfCivB(CFG.game.getProvince(nProvinceID).getCivID(), CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i))
               == -100) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.floor(
                              (double)(
                                 (float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop)
                              )
                           )
                     )
                  )) {
                  --i;
                  Gdx.app.log("GameAction", "recruit--3");
               }
            } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
               && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID()
                  == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.floor(
                              (double)(
                                 (float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop)
                              )
                           )
                     )
                  )) {
                  --i;
                  Gdx.app.log("GameAction", "recruit--4");
               }
            } else if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     (double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(
                           (double)(
                              (float)nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / (float)nRecuritedPop)
                           )
                        )
                  )
               )) {
               --i;
               Gdx.app.log("GameAction", "recruit--5");
            }
         }

         nRecuritedPop -= CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
         if (nRecuritedPop < nNumOfUnits) {
            nRecuritedPop = nNumOfUnits - nRecuritedPop;
            int i = 0;

            for(int tPop = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
               tPop = Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i));
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i))
                  )) {
                  --i;
               }

               nRecuritedPop -= tPop;
               if (nRecuritedPop <= 0) {
                  break;
               }
            }
         }
      }
   }

   protected final int getRecruitableArmy(int nProvinceID) {
      return this.getRecruitableArmy(nProvinceID, CFG.game.getProvince(nProvinceID).getCivID());
   }

   protected final int getRecruitableArmy(int nProvinceID, int nCivID) {
      int nOut = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
            nOut = (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0675F);
         } else if (nCivID == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
            nOut = (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.175F);
         } else if ((int)CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) == -100) {
            nOut = (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0025F);
         } else if (CFG.game.getCiv(nCivID).getAllianceID() > 0
            && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
            nOut = (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.125F);
         } else {
            nOut = (int)((float)nOut + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00725F);
         }
      }

      return nOut;
   }

   protected final void updateRecruitSlider() {
      try {
         int tMaxRecruit = 0;
         tMaxRecruit = (int)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getMoney()
            / CFG.getCostOfRecruitArmyMoney(CFG.game.getActiveProvinceID());
         if (tMaxRecruit < 0) {
            tMaxRecruit = 0;
         } else if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
            tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
         }

         int isRecruiting = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isRecruitingArmyInProvinceID(CFG.game.getActiveProvinceID());
         if (isRecruiting >= 0) {
            tMaxRecruit += CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(isRecruiting).getArmy();
            if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
               tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
            }

            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(tMaxRecruit);
            CFG.menuManager
               .getInGame_ProvinceRecruit_Slider()
               .setCurrent(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(isRecruiting).getArmy());
         } else {
            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(tMaxRecruit);
            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(tMaxRecruit / 2);
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(0);
         CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(0);
      }
   }

   protected final void updateRecruitSlider_Instantly() {
      try {
         int tMaxRecruit = 0;
         tMaxRecruit = (int)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getMoney()
            / CFG.getCostOfRecruitArmyMoney_Instantly(CFG.game.getActiveProvinceID());
         if (tMaxRecruit < 0) {
            tMaxRecruit = 0;
         } else if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
            tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
         }

         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setMax(tMaxRecruit);
         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(tMaxRecruit / 2);
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setMax(0);
         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(0);
      }
   }

   protected final void disbandArmy(int nProvinceID, int nNumOfUnits, int nCivID) {
      if (nNumOfUnits >= 0) {
         if (nNumOfUnits > CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID)) {
            nNumOfUnits = CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID);
         }

         if (nNumOfUnits > 0) {
            if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_DISBAND) {
               return;
            }

            CFG.game
               .getCiv(nCivID)
               .setMovePoints(
                  CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_DISBAND
               );
            nNumOfUnits = Math.min(CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID), nNumOfUnits);
            if (nNumOfUnits <= 0) {
               return;
            }

            CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nNumOfUnits);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID) - nNumOfUnits);
            int nNeightboring = 1;

            for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                  ++nNeightboring;
               }
            }

            int nPop = (int)Math.ceil((double)((float)nNumOfUnits * 0.05F));
            nNumOfUnits -= nPop;
            CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  nCivID, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID) + (int)Math.ceil((double)(nPop / nNeightboring))
               );
            nPop -= (int)Math.ceil((double)(nPop / nNeightboring));
            if (--nNeightboring > 0) {
               for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                  if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                     CFG.game
                        .getProvince(nProvinceID)
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(),
                           CFG.game
                                 .getProvince(nProvinceID)
                                 .getPopulationData()
                                 .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID())
                              + nPop / nNeightboring
                        );
                  }
               }

               nNumOfUnits += nPop - nPop / nNeightboring * nNeightboring;
            }

            if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
               nPop = (int)Math.floor((double)(nNumOfUnits / CFG.game.getCiv(nCivID).getNumOfProvinces()));
               CFG.game
                  .getProvince(CFG.game.getCiv(nCivID).getProvinceID(0))
                  .getPopulationData()
                  .setPopulationOfCivID(
                     nCivID,
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(0)).getPopulationData().getPopulationOfCivID(nCivID)
                        + (int)Math.ceil((double)(nNumOfUnits / CFG.game.getCiv(nCivID).getNumOfProvinces()))
                  );

               for(int i = 1; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                  CFG.game
                     .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                     .getPopulationData()
                     .setPopulationOfCivID(
                        nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(nCivID) + nPop
                     );
               }
            } else {
               CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(nCivID, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID) + nNumOfUnits);
            }
         }
      }
   }

   protected final void updateDisbandSlider() {
      CFG.menuManager
         .getInGame_ProvinceDisband_Slider()
         .setMax(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
      CFG.menuManager
         .getInGame_ProvinceDisband_Slider()
         .setCurrent(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 2);
   }

   protected final void updateInGame_Date() {
      CFG.menuManager.getInGame().getMenuElement(4).setWidth(1);
      CFG.menuManager.getInGame().getMenuElement(4).setText(Game_Calendar.getCurrentDate());
      CFG.menuManager.getInGame().getMenuElement(5).setWidth(1);
      CFG.menuManager.getInGame().getMenuElement(5).setText(CFG.langManager.get("Turn") + ": " + Game_Calendar.TURN_ID);
   }

   protected final void updateInGame_ProvinceInfo() {
      try {
         CFG.ACTIVE_PROVINCE_INFO = CFG.chosenProvinceID >= 0 ? CFG.chosenProvinceID : CFG.game.getActiveProvinceID();
         if (CFG.ACTIVE_PROVINCE_INFO < 0) {
            Menu_InGame_ProvinceInfo.iMaxWidth = 0;
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(false);
            return;
         }

         if (CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(CFG.ACTIVE_PROVINCE_INFO)) {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.langManager.get("Undiscovered"));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-3);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(false);
         } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getWasteland() >= 0) {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.langManager.get("Wasteland"));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(24)
               .setText(
                  ""
                     + (
                        CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize()
                           + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()
                     )
               );
         } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getSeaProvince()) {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(2)
               .setText(
                  CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0
                     ? CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName()
                     : CFG.langManager.get("Sea")
               );
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-1);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(24)
               .setText(
                  ""
                     + (
                        CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize()
                           + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()
                     )
               );
         } else {
            if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0) {
               CFG.game.updateProvinceNameWidth(CFG.ACTIVE_PROVINCE_INFO);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(true);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setText(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName());
            } else {
               CFG.game.updateProvinceNameWidth("Fokus");
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(true);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setText(CFG.langManager.get("Fokus"));
            }

            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(2)
               .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).getCivName());
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setText("" + CFG.game.getProvinceValue(CFG.ACTIVE_PROVINCE_INFO));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(4)
               .setText("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation());
            CFG.menuManager.updateInGame_ProvinceInfoGraph(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(9)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population_WithFarm_WithTerrain() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(9)
               .setText("" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population_WithFarm_WithTerrain() * 100.0F) + "%");
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setText("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getEconomy());
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(10)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(10)
               .setText("" + (float)((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0F)) / 100.0F);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(11)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(11)
               .setText("" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0F) + "%");
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(12)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isFestivalOrganized(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(12)
                  .setText(
                     "" + CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isFestivalOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                  );
            }

            if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
               CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.ACTIVE_PROVINCE_INFO);
            }

            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(13)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getProvinceStability() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(13)
               .setText("" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getProvinceStability() * 100.0F) + "%");
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(true);
            Menu_InGame_ProvinceInfo.updateBuildingsList(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(15)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(15)
               .setText("" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0F) + "%");
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(16)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isAssimilateOrganized(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(16)
                  .setText(
                     ""
                        + CFG.game
                           .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                           .isAssimialateOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                  );
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(17)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(17)
                  .setText(
                     "" + CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                  );
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(18)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_Development(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(18)
                  .setText(
                     ""
                        + CFG.game
                           .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                           .isInvestOrganized_TurnsLeft_Development(CFG.ACTIVE_PROVINCE_INFO)
                  );
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(19)
               .setVisible(
                  !CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsSupplied()
                     && CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns() > 0
               );
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(19)
                  .setText("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns());
            }

            if (!CFG.SPECTATOR_MODE && CFG.FOG_OF_WAR != 0 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.ACTIVE_PROVINCE_INFO)) {
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            } else {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(20)
                  .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition() > 0);
               if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()) {
                  CFG.menuManager
                     .getInGame_ProvinceInfo()
                     .getMenuElement(20)
                     .setText("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition());
               }
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(21)
               .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague != null);
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(21)
                  .setText("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iDeaths);
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(22)
               .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus > 0);
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(22)
                  .setText("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus);
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(23)
               .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iSupportRebelsSize > 0);
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getVisible()) {
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
            }

            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(24)
               .setText(
                  ""
                     + (
                        CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize()
                           + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()
                     )
               );
            if (!CFG.SPECTATOR_MODE && Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() == 0) {
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            }
         }
      } catch (IndexOutOfBoundsException var2) {
         CFG.exceptionStack(var2);
      }

      this.updateInGame_ProvinceInfo_PosX();
   }

   protected final void updateInGame_ProvinceInfo_PosX() {
      try {
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(3)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getWidth()
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(8)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getWidth()
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(9)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(10)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(11)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(12)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(13)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(15)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(16)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                        : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(17)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                              : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(18)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                    : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(19)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                          : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(20)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(21)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                                : (
                                                   CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                      ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                      : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                                )
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(22)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                                : (
                                                   CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                                      ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                                      : (
                                                         CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                            ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                               + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                            : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                               + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(23)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                                                : (
                                                   CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                                      ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                                      : (
                                                         CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                                            ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                                               + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                                            : (
                                                               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                                  ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                                     + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                                  : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                                     + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                                            )
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         Menu_InGame_ProvinceInfo.iMaxWidth = 1;
         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         Menu_InGame_ProvinceInfo.iMaxWidth += CFG.PADDING * 2;
         if ((float)(
               Menu_InGame_ProvinceInfo.iMaxWidth + CFG.GAME_WIDTH - CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).getPosX() + CFG.PADDING * 2
            )
            >= (float)CFG.GAME_WIDTH * 0.8F) {
            Menu_InGame_ProvinceInfo.iMaxWidth = -1;
         }
      } catch (IndexOutOfBoundsException var2) {
      } catch (NullPointerException var3) {
      }
   }

   protected final void buildRank_Score() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.buildRank_Score(i);
      }

      this.buildRank_Positions();
   }

   protected final void buildRank_Score(int nCivID) {
      CFG.game
         .getCiv(nCivID)
         .setRankScore(this.buildRank_Score_Population(nCivID) + this.buildRank_Score_Economy(nCivID) + this.buildRank_Score_Prestige(nCivID));
   }

   protected final void buildRank_Positions() {
      List<Integer> tCivIDs = new ArrayList<>();
      if (CFG.game.getSortedCivsSize() > 0) {
         if (CFG.game.getSortedCivsSize() != CFG.game.getCivsSize() - 1) {
            CFG.game.sortCivilizationsAZ();
         }

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            tCivIDs.add(CFG.game.getSortedCivsAZ(i - 1));
         }
      } else {
         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            tCivIDs.add(i);
         }
      }

      int tRank = 1;
      int tAddID = 0;

      while(tCivIDs.size() > 0) {
         tAddID = 0;

         for(int i = tCivIDs.size() - 1; i > 0; --i) {
            if (CFG.game.getCiv(tCivIDs.get(tAddID)).getRankScore() < CFG.game.getCiv(tCivIDs.get(i)).getRankScore()) {
               tAddID = i;
            }
         }

         CFG.game.getCiv(tCivIDs.get(tAddID)).setRankPosition(tRank++);
         tCivIDs.remove(tAddID);
      }
   }

   protected final int buildRank_Score_Population(int nCivID) {
      float nScore = 0.0F;
      float nTech = Math.min(1.0F, CFG.game.getCiv(nCivID).getTechnologyLevel());

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
            nScore += (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationID(j)
               / ((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() / 2.65F)
               * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j) == nCivID ? 1.0F : 0.275F)
               * (0.6F + 0.4F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability())
               * (0.625F + 0.375F * nTech)
               * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied() ? 0.15F : 1.0F);
         }
      }

      return (int)Math.ceil((double)nScore);
   }

   protected final int buildRank_Score_Economy(int nCivID) {
      float nScore = 0.0F;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         nScore += (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getEconomy()
            / ((float)CFG.game.getGameScenarios().getScenario_StartingEconomy() / 16.25F)
            * (0.425F + 0.675F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel())
            * (0.275F + 0.725F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability());
      }

      return (int)Math.ceil((double)nScore);
   }

   protected final int buildRank_Score_Prestige(int nCivID) {
      float nScore = 0.0F;
      float nTech = Math.min(1.0F, CFG.game.getCiv(nCivID).getTechnologyLevel());
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            nScore += 2.25F
               * (0.125F + 0.875F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getGrowthRate_Population_WithFarm())
               * (0.785F + 0.215F * nTech)
               * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCore().getHaveACore(nCivID) ? 1.0F : 0.475F)
               * (0.375F + 0.625F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel());
         }

         nScore += 17.5F * CFG.game.getCiv(nCivID).getTechnologyLevel();
      }

      return (int)Math.ceil((double)nScore);
   }

   protected final boolean moveCapital(int nCivID, int toProvinceID) {
      if (nCivID < 1 || toProvinceID < 0) {
         return false;
      } else if (!this.moveCapital_CanMove(nCivID)) {
         return false;
      } else if ((
            CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0
               || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
               || !CFG.game.getCiv(nCivID).isAtWar()
               || !CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID(), nCivID)
         )
         && CFG.game.getProvince(toProvinceID).getTrueOwnerOfProvince() == nCivID
         && CFG.game.getProvince(toProvinceID).getCivID() == nCivID
         && CFG.game.getCiv(nCivID).getCapitalProvinceID() != toProvinceID
         && CFG.game.getCiv(nCivID).getMoney() >= (long)this.moveCapital_Cost(nCivID)) {
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)this.moveCapital_Cost(nCivID));
         CFG.game.getCiv(nCivID).setCapitalMoved_LastTurnID(Game_Calendar.TURN_ID);
         int tempOld = CFG.game.getCiv(nCivID).getCapitalProvinceID();
         CFG.game.getCiv(nCivID).setCapitalProvinceID(toProvinceID);
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);
         if (tempOld >= 0) {
            CFG.game.getProvince(tempOld).setIsCapital(false);
            CFG.game.getProvince(tempOld).updateDrawArmy();
            CFG.game
               .getProvince(tempOld)
               .setHappiness(CFG.game.getProvince(tempOld).getHappiness() - CFG.game.getProvince(tempOld).getHappiness() * 0.12168F - 0.12168F);

            try {
               CFG.game.getProvince(tempOld).getCity(0).setCityLevel(CFG.getEditorCityLevel(1));
            } catch (IndexOutOfBoundsException var6) {
            }
         }

         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(true);
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).updateDrawArmy();
         CFG.game
            .getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID())
            .setHappiness(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getHappiness() + 0.025F);

         try {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
         } catch (IndexOutOfBoundsException var5) {
         }

         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
         return true;
      } else {
         return false;
      }
   }

   protected final boolean moveCapital_CanMove(int nCivID) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
         && (
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
               || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied()
                  && CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID())
         )) {
         return CFG.game.getCiv(nCivID).getCapitalMoved_LastTurnID() <= Game_Calendar.TURN_ID - 50;
      } else {
         return true;
      }
   }

   protected final int moveCapital_Cost(int nCivID) {
      return CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
            && (
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
                  || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied()
                     && CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID())
            )
         ? 1
            + (int)(
               (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1925F
                  + (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getPopulationData().getPopulation() * 0.125F
                  + (
                        CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                           + CFG.game_NextTurnUpdate.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                     )
                     * (2.1348F + 1.86584F * CFG.game.getCiv(nCivID).getTechnologyLevel())
            )
         : 25;
   }

   protected final boolean abadonProvince(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID
         && CFG.game.getCiv(nCivID).getCapitalProvinceID() != nProvinceID
         && !CFG.game.getProvince(nProvinceID).isOccupied()
         && CFG.game.getCiv(nCivID).getNumOfProvinces() > 1) {
         for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
            if (CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeMove(i--);
            }
         }

         for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsPlunderSize(); ++i) {
            if (CFG.game.getCiv(nCivID).getMoveUnits_Plunder(i).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removePlunder(i--);
            }
         }

         for(int i = 0; i < CFG.game.getCiv(nCivID).getMigrateSize(); ++i) {
            if (CFG.game.getCiv(nCivID).getMigrate(i).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeMigrate(i--);
            }
         }

         for(int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); ++i) {
            if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeRecruitArmy(i--);
            }
         }

         for(int i = CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize() - 1; i >= 0; --i) {
            CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     (float)CFG.game
                           .getProvince(nProvinceID)
                           .getPopulationData()
                           .getPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i))
                        * (0.05F + (float)CFG.oR.nextInt(20) / 100.0F)
                  )
               );
         }

         CFG.game
            .getProvince(nProvinceID)
            .setEconomy((int)((float)CFG.game.getProvince(nProvinceID).getEconomy() * (0.025F + (float)CFG.oR.nextInt(15) / 100.0F)));
         CFG.game
            .getProvince(nProvinceID)
            .setDevelopmentLevel((float)((int)(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (0.045F + (float)CFG.oR.nextInt(20) / 100.0F))));
         CFG.game.getProvince(nProvinceID).setTrueOwnerOfProvince(0);
         CFG.game.getProvince(nProvinceID).setCivID(0, false);

         try {
            CFG.game
               .getProvince(nProvinceID)
               .resetArmies(
                  CFG.oR.nextInt(CFG.game.getGameScenarios().getScenario_NeutralArmy() / 2) + CFG.game.getGameScenarios().getScenario_NeutralArmy() / 2
               );
            CFG.game.getProvince(nProvinceID).updateDrawArmy();
         } catch (IllegalArgumentException var4) {
         }

         return true;
      } else {
         return false;
      }
   }

   protected final void accessLost_UpdateArmies(int inCivID, int nCivID) {
      List<Integer> tempProvincesToMove = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() == inCivID) {
            tempProvincesToMove.add(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
         }
      }

      for(int i = CFG.game.getCiv(nCivID).getMoveUnitsSize() - 1; i >= 0; --i) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID()).getCivID() == inCivID) {
            tempProvincesToMove.add(CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID());
            this.moveArmy(
               CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID(), 0, nCivID, false, false
            );
         }
      }

      for(int i = 0; i < tempProvincesToMove.size(); ++i) {
         this.accessLost_MoveArmyToClosetsProvince(nCivID, tempProvincesToMove.get(i));
      }
   }

   protected final void accessLost_MoveArmyToClosetsProvince(int nCivID, int nProvinceID) {
      this.accessLost_MoveArmyToClosetsProvince(nCivID, nProvinceID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID));
   }

   protected final void accessLost_MoveArmyToClosetsProvince(int nCivID, int nProvinceID, int nArmy) {
      if (nArmy > 0) {
         if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            try {
               int toProvinceID = CFG.game.getCiv(nCivID).getProvinceID(0);
               float fMinDistance = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvinceID, toProvinceID);
               float tempDistance = 0.0F;

               for(int i = 1; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                  tempDistance = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvinceID, CFG.game.getCiv(nCivID).getProvinceID(i));
                  if (fMinDistance > tempDistance) {
                     toProvinceID = CFG.game.getCiv(nCivID).getProvinceID(i);
                     fMinDistance = tempDistance;
                  }
               }

               CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
               CFG.game.getProvince(toProvinceID).updateArmy(nCivID, CFG.game.getProvince(toProvinceID).getArmyCivID(nCivID) + nArmy);
            } catch (IndexOutOfBoundsException var8) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var8);
               }

               CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nArmy);
               CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
            }
         } else {
            CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nArmy);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
         }
      }
   }

   protected final Game_Action.TurnStates getActiveTurnState() {
      return this.activeTurnAction;
   }

   protected final void setActiveTurnState(Game_Action.TurnStates nState) {
      this.activeTurnAction = nState;
   }

   protected final MoveUnits_TurnData getCurrentMoveunits() {
      return this.currentMoveUnits;
   }

   protected final void resetCurrentMoveUnits() {
      this.currentMoveUnits = null;
   }

   static enum TurnStates {
      INPUT_ORDERS,
      LOAD_AI_RTO,
      TURN_ACTIONS,
      LOADING_NEXT_TURN,
      START_NEXT_TURN,
      SAVE_THE_GAME,
      RESULTS_STANDINGS,
      END_OF_THE_GAME;
   }
}
