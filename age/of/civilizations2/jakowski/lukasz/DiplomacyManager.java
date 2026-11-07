package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class DiplomacyManager {
   protected static final int COST_OFFER_ALLIANCE = 20;
   protected static final int COST_OFFER_IMPROVERELATIONS = 5;
   protected static final int COST_OFFER_DECREASERELATIONS = 2;
   protected static final int IMPROVERELATIONS_MAX_NUM_OF_TURS = 35;
   protected static final int SUSPEND_DIPLOMATIC_RELATIONS_MAX = 50;
   protected static final int SUSPEND_DIPLOMATIC_RELATIONS_MIN = 15;
   protected static final int COST_OFFER_NONAGGRESSIONPACT = 8;
   protected static final int COST_OFFER_DEFENSIVEPACT = 10;
   protected static final int COST_OFFER_PROCLAIMINDEPENDENCE = 5;
   protected static final int COST_OFFER_FORMUNION = 22;
   protected static final int COST_ALLIANCE_LEAVE = 2;
   protected static final int COST_OFFER_SUPPORTREBELS = 34;
   protected static final int COST_OFFER_TRADEREQUEST = 10;
   protected static final int COST_OFFER_LIBERATEAVASSAL = 2;
   protected static final int COST_OFFER_VASSALIZATION = 16;
   protected static final int COST_OFFER_MILITARYACCESS_ASK = 10;
   protected static final int COST_OFFER_MILITARYACCESS_GIVE = 4;
   protected static final int COST_OFFER_GIFT = 8;
   protected static final int COST_CALL_TO_ARMS = 0;
   protected static final int COST_WAR_PREPARATIONS = 0;
   protected static final int COST_TAKE_LOAN = 6;
   protected static final int COST_ABADON = 0;
   protected static final int COST_ULTIMATUM = 24;
   protected static final int COST_TRANSFER_CONTROL = 4;
   protected static final int COST_INVEST_DEVLOPMENT = 8;
   protected static final int INVEST_NUM_OF_TURNS_DEVLOPMENT = 4;
   protected static final float COST_INVEST_ECONOMY_PER_GOLD_DEVELOPMENT = 1.075F;
   protected static final int COST_INVEST = 12;
   protected static final int INVEST_NUM_OF_TURNS = 4;
   protected static final float COST_INVEST_ECONOMY_PER_GOLD = 3.5F;
   protected static final float COST_INVEST_ECONOMY_PER_GOLD2 = 6.75F;
   protected static final int COLONIZE_NEW_COLONY_BONUS = 92;
   protected static final int COST_FESTIVAL = 8;
   protected static final int BASE_COST_OF_FESTIVAL = 500;
   protected static final int FESTIVAL_NUM_OF_TURNS = 7;
   protected static final int COST_ASSIMILATE = 6;
   protected static final int BASE_COST_OF_ASSIMILATE = 265;
   protected static final int ASSIMILATE_NUM_OF_TURNS_MIN = 10;
   protected static final int ASSIMILATE_NUM_OF_TURNS_MAX = 50;
   protected static final int SUPPORT_REBELS_NUM_OF_TURNS_MAX = 35;
   protected static final float SUPPORT_REBELS_ASSIMILATE_COST_MODIFIER = 1.6275F;
   protected static final float SUPPORT_REBELS_ASSIMILATE_PERC = 0.845F;
   protected static final float SUPPORT_REBELS_ASSIMILATE_PERC_EXTRA = 0.125F;
   protected static final int COST_CIVILIZE = 10;
   protected static final int DIPLOMAT_COST_ALLIANCE = 6;
   protected static final int DIPLOMAT_COST_NONAGGRESSION = 2;
   protected static final int DIPLOMAT_COST_GUARANTEE = 1;
   protected static final int DIPLOMAT_COST_DEFENSIVE_PACT = 3;
   protected static final int DIPLOMAT_COST_FRIENDLY_CIV = 3;
   protected static final int DIPLOMAT_COST_MILITARYACCESS = 1;
   protected static final int DIPLOMAT_COST_VASSAL = 1;
   protected static final int GOLDEN_AGE_EVERY_X_TURNS = 30;
   protected static final int CALL_TO_ARMS_RELATION_DENY = -15;
   protected static final int CALL_TO_ARMS_RELATION_DENY_INSULT = -20;
   protected static final int CALL_TO_ARMS_RELATION_ACCEPT = 10;
   protected static int WAR_PREPARATIONS_REFUSE_OPINION_CHANGE = -10;
   protected static final float GIFT_MAX_PERC_OF_TREASURY = 0.25F;
   protected static final int GIFT_REMOVE_RECEIVED_GIFT_INFO_TURNS = 5;
   protected static final int GIFT_REFUSE_OPINION_CHANGE = -8;
   protected static final int ULTIMATUM_TRUCE_TURNS = 30;
   protected static final int ULTIMATUM_REQUIRED_RELATIONS = -10;
   protected static final int PEACETREATY_DEFAULT = 45;
   protected static final int PEACETREATY_MIN_DURATION = 30;
   protected static final int PEACETREATY_MAX_DURATION = 75;
   protected static final int WAR_REPARATIONS_LENGTH = 12;
   protected static int RELEASED_VASSAL_MIN_OPINION = 25;
   protected static final int OUDATED_RELATIONS = 6;
   protected static final int OUDATED_RELATIONS_MAX = 15;
   protected static final int OUDATED_RELATIONS_MIN = -20;
   protected static final int FRIENDLY_MIN_RELATION = 44;
   protected static final int HATED_WAR = 85;
   protected static final int HATED_MIN_RELATION = -25;
   protected static final int HATED_INSULT = 20;
   protected static final int INSULT_DECREASE_RELATIONS = 30;
   protected static final int LOAN_MAX_NUM_OF_LOANS = 5;
   protected static final int LOAN_MIN_DURATION = 5;
   protected static final int LOAN_MAX_DURATION = 30;
   protected static final float LOAN_MAX_VALUE_OF_INCOME = 0.6F;
   protected static final float PLUNDER_INCOME_MULTIPLY = 1.45F;
   protected static final float PLUNDER_INCOME_HIGH_REV_RISK_MODIFIER = 0.625F;

   protected static final float getLikelihoodScore(int iScore) {
      return (float)(Math.min(Math.max(iScore, -100), 100) + 100) / 200.0F;
   }

   protected static final void worldRecations(int iModifier, int iAgressorCivID, int iCivB) {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && i != iAgressorCivID && i != iCivB && !CFG.game.getCivsAtWar(i, iAgressorCivID)) {
            float tDistance = CFG.game_NextTurnUpdate
               .getDistanceFromAToB_PercOfMax(CFG.game.getCiv(i).getCapitalProvinceID(), CFG.game.getCiv(iCivB).getCapitalProvinceID());
            float out = -(tDistance < 0.375F ? (float)iModifier / 20.0F * (1.0F - tDistance) : 0.0F)
               + (float)iModifier
                  * (-(CFG.game.getCivRelation_OfCivB(i, iCivB) + (float)(iModifier / 5)) / 100.0F)
                  * Math.max(1.0F - tDistance * 1.35F, 0.01F);
            CFG.game
               .setCivRelation_OfCivB(
                  i,
                  iAgressorCivID,
                  CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) + out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) + out
               );
            CFG.game
               .setCivRelation_OfCivB(
                  iAgressorCivID,
                  i,
                  CFG.game.getCivRelation_OfCivB(iAgressorCivID, i) > -100.0F && CFG.game.getCivRelation_OfCivB(iAgressorCivID, i) + out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(iAgressorCivID, i) + out
               );
         }
      }
   }

   protected static final void sendTransferControl(int iToCivID, int iFromCivID, int iProvinceID) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TransferControl(iFromCivID, iProvinceID));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 0);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.TRANSFER_CONTROL));
      }
   }

   protected static final void acceptTransferControl(int iCivID, int iFromCivID, int iValue) {
      if (CFG.game.getProvince(iValue).getCivID() == iFromCivID
         && CFG.game.getProvince(iValue).isOccupied()
         && (
            CFG.game.getCivsAreAllied(iCivID, iFromCivID)
               || CFG.game.getCiv(iCivID).getPuppetOfCivID() == iFromCivID
               || CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iCivID
               || CFG.game.getProvince(iValue).getTrueOwnerOfProvince() == iCivID
         )) {
         CFG.game
            .getCiv(iFromCivID)
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message_TransferControl_Accepted(iCivID, iValue, iFromCivID));
         int oldOwnerArmy = CFG.game.getProvince(iValue).getArmyCivID(iFromCivID);
         int newOwnerArmy = CFG.game.getProvince(iValue).getArmyCivID(iCivID);
         if (oldOwnerArmy != 0) {
            CFG.game.getProvince(iValue).updateArmy(iFromCivID, 0);
         }

         if (newOwnerArmy != 0) {
            CFG.game.getProvince(iValue).updateArmy(iCivID, 0);
         }

         CFG.game.getProvince(iValue).setCivID(iCivID, false, true);
         if (oldOwnerArmy > 0) {
            CFG.game.getProvince(iValue).updateArmy(iFromCivID, oldOwnerArmy);
         }

         if (newOwnerArmy > 0) {
            CFG.game.getProvince(iValue).updateArmy(iCivID, newOwnerArmy);
         }
      }
   }

   protected static final void declineTransferControl(int iCivID, int iFromCivID, int iValue) {
      if (CFG.game.getProvince(iValue).getCivID() == iFromCivID
         && CFG.game.getProvince(iValue).isOccupied()
         && (
            CFG.game.getCivsAreAllied(iCivID, iFromCivID)
               || CFG.game.getCiv(iCivID).getPuppetOfCivID() == iFromCivID
               || CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iCivID
         )) {
         CFG.game
            .getCiv(iFromCivID)
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message_TransferControl_Refused(iCivID, iValue, iFromCivID));
      }
   }

   protected static final float invest_DevelopmentByGold(int nProvinceID, int nMoney) {
      return (float)nMoney
         / ((float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * 1.075F)
         * (0.375F + 0.625F * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID) * 100.0F);
   }

   protected static final int invest_MaxDevelopment_Gold(int nProvinceID, int nCivID) {
      return (int)Math.max(
         Math.min(
            Math.min(
                  CFG.game.getCiv(nCivID).getTechnologyLevel() + 0.01F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel(),
                  Math.max(CFG.game.getProvince(nProvinceID).getDevelopmentLevel(), 0.1F) * 0.725F
               )
               * (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
               * 1.075F
               * (0.375F + 0.625F * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID) * 100.0F),
            (float)CFG.game.getCiv(nCivID).getMoney()
         ),
         0.0F
      );
   }

   protected static final boolean investDevelopment(int nProvinceID, int nCivID, int nMoney) {
      if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID && CFG.game.getCiv(nCivID).getMovePoints() >= 8) {
         if (CFG.game.getCiv(nCivID).getMoney() < (long)nMoney) {
            nMoney = (int)CFG.game.getCiv(nCivID).getMoney();
         }

         if (nMoney > 0) {
            float ecoPoints = invest_DevelopmentByGold(nProvinceID, nMoney);
            if (ecoPoints > 0.0F) {
               float ecoPointsPerTurn = Math.max(ecoPoints / 4.0F, 1.0E-5F);
               if (CFG.game.getCiv(nCivID).addInvest_Development(new CivInvest_Development(nProvinceID, 4, ecoPoints, ecoPointsPerTurn))) {
                  CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 8);
                  CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)nMoney);
                  return true;
               }
            }
         }
      }

      return false;
   }

   protected static final int invest_EconomyByGold(int nProvinceID, int nMoney) {
      return (int)(
         (float)nMoney
            / 3.5F
            * (0.875F + 0.125F * Math.min(1.0F, CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * 1.75F))
            * (0.375F + 0.625F * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID) * 10.0F)
      );
   }

   protected static final int invest_MaxEconomy(int nProvinceID, int nCivID) {
      return (int)Math.min(
         (float)CFG.game.getProvince(nProvinceID).getEconomy() * 0.375F,
         (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.26854F
      );
   }

   protected static final int invest_MaxEconomy_Gold(int nProvinceID, int nCivID) {
      return Math.max(
         (int)Math.min(
            Math.min(
                  (float)CFG.game.getProvince(nProvinceID).getEconomy() * 0.325F,
                  (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.265F
               )
               * (0.65F + 0.35F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               * 6.75F,
            (float)CFG.game.getCiv(nCivID).getMoney()
         ),
         0
      );
   }

   protected static final boolean invest(int nProvinceID, int nCivID, int nMoney) {
      if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID && CFG.game.getCiv(nCivID).getMovePoints() >= 12) {
         if (CFG.game.getCiv(nCivID).getMoney() < (long)nMoney) {
            nMoney = (int)CFG.game.getCiv(nCivID).getMoney();
         }

         if (nMoney > 0) {
            int ecoPoints = invest_EconomyByGold(nProvinceID, nMoney);
            if (ecoPoints > 0) {
               int ecoPointsPerTurn = Math.max(ecoPoints / 4, 1);
               if (CFG.game.getCiv(nCivID).addInvest(new CivInvest(nProvinceID, 4, ecoPoints, ecoPointsPerTurn))) {
                  CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 12);
                  CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)nMoney);
                  return true;
               }
            }
         }
      }

      return false;
   }

   protected static final boolean canMoveToNaighbooringProvince(int nProvinceID, int nCivID) {
      return !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES
         || CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getCivID() > 0
         || CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0;
   }

   protected static final int getColonizeCost(int nProvinceID, int nCivID) {
      return (int)(
         (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
            * (
               CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_GOLD_PERC
                  + 0.0845F * CFG.game.getProvince(nProvinceID).getGrowthRate_Population()
                  + 0.1325F
                     * (
                        CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
                           ? 3.475F * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nProvinceID)
                           : 1.0F
                     )
            )
            * getColonizeCost_OwnNeighboringProvincesModifier(nProvinceID, nCivID)
            * getColonizeCost_ContinentAndRegion_Modifier(nProvinceID, nCivID)
            * (1.0F - CFG.game.getCiv(nCivID).civGameData.fModifier_ColonizationCost)
            * (
               CFG.game.getCiv(nCivID).getTechnologyLevel() < Game_Calendar.COLONIZATION_TECH_LEVEL
                  ? 2.675F + (Game_Calendar.COLONIZATION_TECH_LEVEL - CFG.game.getCiv(nCivID).getTechnologyLevel()) * 8.25F
                  : 1.0F
            )
      );
   }

   protected static final int getColonizeCost_AI(int nCivID) {
      return (int)(
         (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
            * (CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_GOLD_PERC + 0.021125F + 0.0795F)
            * (1.0F - CFG.game.getCiv(nCivID).civGameData.fModifier_ColonizationCost)
            * (
               CFG.game.getCiv(nCivID).getTechnologyLevel() < Game_Calendar.COLONIZATION_TECH_LEVEL
                  ? 2.675F + (Game_Calendar.COLONIZATION_TECH_LEVEL - CFG.game.getCiv(nCivID).getTechnologyLevel()) * 8.25F
                  : 1.0F
            )
      );
   }

   protected static final float getColonizeCost_ContinentAndRegion_Modifier(int nProvinceID, int nCivID) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getContinent() == CFG.game.getProvince(nProvinceID).getContinent()) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getRegion() == CFG.game.getProvince(nProvinceID).getRegion()) {
               return 0.815F;
            }

            return 0.865F;
         }

         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getRegion() == CFG.game.getProvince(nProvinceID).getRegion()) {
            return 0.915F;
         }
      }

      return 1.0F;
   }

   protected static final float getColonizeCost_OwnNeighboringProvincesModifier(int nProvinceID, int nCivID) {
      int ownsNeighboringProvinces = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
            ++ownsNeighboringProvinces;
         }
      }

      return 1.0F - 0.4F * (float)ownsNeighboringProvinces / (float)Math.max(CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(), 1);
   }

   protected static final int getColonizeCost_Movement(int nProvinceID, int nCivID) {
      return (int)Math.min(
         40.0F,
         (float)CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_MOVEMENT_POINTS
            + (float)CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_MOVEMENT_POINTS
               * (
                  CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
                     ? 1.6275F * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nProvinceID)
                     : 2.0F
               )
      );
   }

   protected static final boolean colonizeWastelandProvince(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getWasteland() < 0 && CFG.game.getProvince(nProvinceID).getCivID() != 0) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() < getColonizeCost_Movement(nProvinceID, nCivID)) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getDiplomacyPoints() < CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMoney() < (long)getColonizeCost(nProvinceID, nCivID)) {
         return false;
      } else if (!CFG.gameAction.canColonizieWasteland_BorderOrArmy(nProvinceID, nCivID)) {
         return false;
      } else {
         boolean wasWasteland = CFG.game.getProvince(nProvinceID).getWasteland() >= 0;
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getColonizeCost_Movement(nProvinceID, nCivID));
         CFG.game
            .getCiv(nCivID)
            .setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() - CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS);
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getColonizeCost(nProvinceID, nCivID));
         CFG.game.getProvince(nProvinceID).setWasteland(-1);
         CFG.game.getProvince(nProvinceID).resetArmies(0);
         CFG.game.getProvince(nProvinceID).setCivID(nCivID, false, true);
         int ranArmy = 5 + CFG.oR.nextInt(15);
         CFG.game.getProvince(nProvinceID).updateArmy(nCivID, ranArmy);
         CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() + ranArmy);
         CFG.game.getProvince(nProvinceID).getCore().addNewCore(nCivID, Game_Calendar.TURN_ID);
         CFG.game.getProvince(nProvinceID).setHappiness(Math.max(CFG.game.getProvince(nProvinceID).getHappiness(), (float)(62 + CFG.oR.nextInt(31)) / 100.0F));
         CFG.game
            .getProvince(nProvinceID)
            .setDevelopmentLevel(
               Math.max(
                  CFG.game.getProvince(nProvinceID).getDevelopmentLevel(),
                  CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.125F + (float)CFG.oR.nextInt(100) / 1000.0F)
               )
            );
         CFG.game.getProvince(nProvinceID).saveProvinceData.iNewColonyBonus = 92;
         if (wasWasteland) {
            CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  nCivID, Math.max(299 + CFG.oR.nextInt(460), CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID))
               );
            CFG.game.getProvince(nProvinceID).setEconomy(Math.max(CFG.game.getProvince(nProvinceID).getEconomy(), 42 + CFG.oR.nextInt(76)));
            CFG.game.buildWastelandLevels();
         }

         for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
               float randPop = 0.375F + (float)CFG.oR.nextInt(35) / 100.0F;
               CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     nCivID,
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID)
                        + (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * randPop)
                  );
               CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - (int)((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * randPop)
                  );
               break;
            }
         }

         CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.add(new Civilization_Colonies(nProvinceID));
         CFG.oAI.buildProvinceData(nProvinceID);
         if (CFG.game.getActiveProvinceID() == nProvinceID) {
            CFG.game.setActiveProvinceID(-1);
            CFG.game.setActiveProvinceID(nProvinceID);
         }

         try {
            CFG.historyManager.addHistoryLog(new HistoryLog_NewColony(nCivID, nProvinceID));
         } catch (NullPointerException var6) {
         } catch (IndexOutOfBoundsException var7) {
         }

         return true;
      }
   }

   protected static final int festivalCost(int nProvinceID) {
      return 500
         + (int)(
            (CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(nProvinceID) + CFG.game_NextTurnUpdate.getProvinceIncome_Production(nProvinceID))
               * (
                  0.6425F
                     + 0.1625F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                     + 0.2F * (float)CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getFestivalsSize()
               )
         );
   }

   protected static final float festivalHappinessPerTurn(int nProvinceID) {
      return 0.0145F + 0.006F * (1.0F - CFG.game.getProvince(nProvinceID).getHappiness());
   }

   protected static final float festivalHappinessPerTurn_NeighboringProvinces() {
      return 0.0045F;
   }

   protected static final boolean addFestival(int nCivID, int nProvinceID) {
      if (nCivID == CFG.game.getProvince(nProvinceID).getCivID()
         && CFG.game.getCiv(nCivID).getMovePoints() >= 8
         && CFG.game.getCiv(nCivID).getMoney() >= (long)festivalCost(nProvinceID)
         && CFG.game.getCiv(nCivID).addFestival(new CivFestival(nProvinceID, 7))) {
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 8);
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)festivalCost(nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final int assimilateCost(int nProvinceID, int numOfTurns) {
      return (int)(
         (float)(
               265
                  + (int)(
                     (
                           CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(nProvinceID) * 0.775F
                              + CFG.game_NextTurnUpdate.getProvinceIncome_Production(nProvinceID) * 0.237F
                        )
                        * (
                           0.665F
                              + 0.412F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                              + 0.0825F * (float)CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAssimilatesSize()
                        )
                        * (
                           1.0F
                              + CFG.game_NextTurnUpdate
                                 .getDistanceFromAToB_PercOfMax(
                                    CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID(), nProvinceID
                                 )
                        )
                        * (
                           1.625F
                              - (float)CFG.game
                                    .getProvince(nProvinceID)
                                    .getPopulationData()
                                    .getPopulationOfCivID(CFG.game.getProvince(nProvinceID).getCivID())
                                 / (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                        )
                  )
            )
            / 10.0F
            * (float)numOfTurns
      );
   }

   protected static final boolean addAssimilate(int nCivID, int nProvinceID, int numOfTurns) {
      if (nCivID == CFG.game.getProvince(nProvinceID).getCivID()
         && !CFG.game.getProvince(nProvinceID).isOccupied()
         && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 6
         && CFG.game.getCiv(nCivID).getMoney() >= (long)assimilateCost(nProvinceID, numOfTurns)
         && CFG.game.getCiv(nCivID).addAssimilate(new CivFestival(nProvinceID, numOfTurns))) {
         CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() - 6);
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)Math.abs(assimilateCost(nProvinceID, numOfTurns)));
         return true;
      } else {
         return false;
      }
   }

   protected static final SupportRebels_Data supportRebels(int iOnCivID) {
      SupportRebels_Data outCivs = new SupportRebels_Data();

      for(int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivsSize(); ++j) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j)).getNumOfProvinces() <= 0) {
               boolean tAdd = true;

               for(int k = 0; k < outCivs.lMovements.size(); ++k) {
                  if (outCivs.lMovements.get(k) == CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j)) {
                     tAdd = false;
                     outCivs.lPopulation
                        .set(
                           k,
                           outCivs.lPopulation.get(k)
                              + CFG.game
                                 .getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i))
                                 .getPopulationData()
                                 .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j))
                        );
                     outCivs.lUnrest
                        .set(
                           k, outCivs.lUnrest.get(k) + (int)(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getRevolutionaryRisk() * 100.0F)
                        );
                     outCivs.lProvinces.set(k, outCivs.lProvinces.get(k) + 1);
                     break;
                  }
               }

               if (tAdd) {
                  outCivs.lMovements.add(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j));
                  outCivs.lPopulation
                     .add(
                        CFG.game
                           .getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i))
                           .getPopulationData()
                           .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j))
                     );
                  outCivs.lUnrest.add((int)(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getRevolutionaryRisk() * 100.0F));
                  outCivs.lProvinces.add(1);
               }
            }
         }
      }

      return outCivs;
   }

   protected static final List<Integer> supportRebels_Provinces(int iOnCivID, int iRebelsID) {
      List<Integer> outProvinces = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivsSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j) == iRebelsID) {
               outProvinces.add(CFG.game.getCiv(iOnCivID).getProvinceID(i));
               break;
            }
         }
      }

      return outProvinces;
   }

   protected static final float getSUPPORT_REBELS_ASSIMILATE_PERC(int iNumOfSupporters) {
      return iNumOfSupporters <= 1 ? 0.845F : 0.845F + 0.125F * Math.min(1.0F, (float)(iNumOfSupporters / 4));
   }

   protected static final int supportRebels_MaxGold(List<Integer> nProvinces) {
      int out = 1;
      int i = 0;

      for(int iSize = nProvinces.size(); i < iSize; ++i) {
         out += (int)((float)assimilateCost(nProvinces.get(i), 35) * 1.6275F);
      }

      return out * 2;
   }

   protected static final boolean supportRebels(int byCivID, int iOnCivID, int supportCivID, int nMoney) {
      if (CFG.game.getCiv(byCivID).getMoney() < (long)nMoney) {
         nMoney = (int)CFG.game.getCiv(byCivID).getMoney();
      }

      if (nMoney <= 0) {
         return false;
      } else if (CFG.game.getCiv(byCivID).getDiplomacyPoints() < 34) {
         return false;
      } else {
         CFG.game.getCiv(byCivID).setDiplomacyPoints(CFG.game.getCiv(byCivID).getDiplomacyPoints() - 34);
         CFG.game.getCiv(byCivID).setMoney(CFG.game.getCiv(byCivID).getMoney() - (long)nMoney);
         List<Integer> supportedProvinces = new ArrayList<>();
         List<Integer> supportedPopulation = new ArrayList<>();
         List<Integer> supportCostPerTurn = new ArrayList<>();
         int supportedPopulationTotal = 0;

         for(int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getHaveACore(supportCivID)) {
               supportedProvinces.add(CFG.game.getCiv(iOnCivID).getProvinceID(i));
               supportedPopulation.add(
                  CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(supportCivID) + 1
               );
               supportCostPerTurn.add((int)((float)assimilateCost(CFG.game.getCiv(iOnCivID).getProvinceID(i), 1) * 1.6275F));
               supportedPopulationTotal += CFG.game
                     .getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i))
                     .getPopulationData()
                     .getPopulationOfCivID(supportCivID)
                  + 1;
            }
         }

         try {
            CFG.game
               .getCiv(iOnCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_RebelsSupported(supportCivID, supportedProvinces.get(0)));
         } catch (IndexOutOfBoundsException var13) {
         }

         while(supportedProvinces.size() > 0 && nMoney > 0) {
            int nRandPop = CFG.oR.nextInt(supportedPopulationTotal);
            int currPop = 0;
            int bestSuppProvID = 0;

            for(int i = 0; i < supportedProvinces.size(); ++i) {
               if (nRandPop >= currPop && nRandPop <= currPop + supportedPopulation.get(i)) {
                  bestSuppProvID = i;
                  break;
               }
            }

            if (!(Math.floor((double)(nMoney / supportCostPerTurn.get(bestSuppProvID))) > 0.0)) {
               break;
            }

            int numOfTunrs = (int)Math.floor((double)(nMoney / supportCostPerTurn.get(bestSuppProvID)));
            if (numOfTunrs <= 1) {
               break;
            }

            numOfTunrs = 1 + CFG.oR.nextInt(numOfTunrs);
            if (numOfTunrs > 35) {
               numOfTunrs = 35;
            }

            Province_SupportRebels_Help outHelp = CFG.game
               .getProvince(supportedProvinces.get(bestSuppProvID))
               .addSupportRebels(new Province_SupportRebels(byCivID, supportCivID, numOfTunrs));
            nMoney -= supportCostPerTurn.get(bestSuppProvID) * outHelp.iTurns;
            if (outHelp.max) {
               supportedPopulationTotal -= supportedPopulation.get(bestSuppProvID);
               supportedProvinces.remove(bestSuppProvID);
               supportedPopulation.remove(bestSuppProvID);
               supportCostPerTurn.remove(bestSuppProvID);
            }
         }

         supportedProvinces.clear();
         supportedPopulation.clear();
         supportedPopulationTotal = 0;

         for(int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(supportCivID) > 0) {
               supportedProvinces.add(CFG.game.getCiv(iOnCivID).getProvinceID(i));
               supportedPopulation.add(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(supportCivID));
               supportedPopulationTotal += supportedPopulation.get(supportedPopulation.size() - 1);
            }
         }

         float efficiency = (float)nMoney / ((float)supportedPopulationTotal * 0.11625F * 5.0F);

         for(int i = 0; i < supportedProvinces.size(); ++i) {
            float tempPercOfPopulation = (float)supportedPopulation.get(i).intValue()
               / (float)CFG.game.getProvince(supportedProvinces.get(i)).getPopulationData().getPopulation();
            CFG.game
               .getProvince(supportedProvinces.get(i))
               .setRevolutionaryRisk(
                  CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID)
                        * CFG.game.getProvince(supportedProvinces.get(i)).getRevolutionaryRisk()
                     + 0.2F * efficiency * tempPercOfPopulation * (1.01F - CFG.game.getProvince(supportedProvinces.get(i)).getHappiness())
               );
         }

         return true;
      }
   }

   protected static final boolean civilizeCiv(int nCivID) {
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
         && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10
         && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL <= CFG.game.getCiv(nCivID).getTechnologyLevel()) {
         CFG.game.getCiv(nCivID).setIdeologyID(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED);
         CFG.game
            .getCiv(nCivID)
            .setCivTag(
               CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
                  + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getExtraTag()
            );
         CFG.unionFlagsToGenerate_Manager.addFlagToLoad(nCivID);
         CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() - 10);
         if (CFG.game.getPlayerID_ByCivID(nCivID) >= 0) {
            CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(nCivID)).loadPlayersFlag();
         }

         CFG.viewsManager.disableAllViews();

         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).setFromCivID(0);
         }

         CFG.palletManager.loadCivilizationStandardColor(nCivID);
         if (CFG.game.getCiv(nCivID).getNumOfNeighboringNeutralProvinces() > 0) {
            List<Integer> possibleProvinces = new ArrayList<>();

            for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
               for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                  possibleProvinces.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j));
               }
            }

            if (possibleProvinces.size() > 0) {
               CFG.game.getProvince(possibleProvinces.get(CFG.oR.nextInt(possibleProvinces.size()))).setCivID(nCivID, false);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected static void sendTechPointsMessages() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            if (CFG.game.getCiv(i).civGameData.skills.getPointsLeft(i) > 0) {
               CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TechPoints(i));
            }

            CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_OpenBudget(i));
         }
      }
   }

   protected static void sendUncivilizedMessages() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getControlledByPlayer()
            && CFG.game.getCiv(i).getNumOfProvinces() > 0
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Uncivilized(i));
         }
      }
   }

   protected static void sendLowHappiness() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() >= 0) {
            if (CFG.game.getCiv(i).getHappiness() < 50) {
               CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_LowHappiness(i, 0));
            }

            if (CFG.game.getCiv(i).lProvincesWithLowStability.size() > 0) {
               boolean sendLowStability = false;

               for(int j = CFG.game.getCiv(i).lProvincesWithLowStability.size() - 1; j >= 0; --j) {
                  if (CFG.game.getProvince(CFG.game.getCiv(i).lProvincesWithLowStability.get(j)).getProvinceStability() < 75.0F) {
                     sendLowStability = true;
                     break;
                  }
               }

               if (sendLowStability) {
                  CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_LowStability(i, 0));
               }
            }
         }
      }
   }

   protected static final int getCostOfCurrentDiplomaticActions(int nCivID) {
      int out = 0;
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize() > 1) {
         out += 6;
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && i != nCivID) {
            if (CFG.game.getCivNonAggressionPact(nCivID, i) > 0) {
               out += 2;
            }

            if (CFG.game.getGuarantee(nCivID, i) > 0) {
               ++out;
            }

            if (CFG.game.getDefensivePact(nCivID, i) > 0) {
               out += 3;
            }

            if (CFG.game.getMilitaryAccess(nCivID, i) > 0) {
               ++out;
            }

            out += 1 * CFG.game.getCiv(nCivID).civGameData.iVassalsSize;
            out += getCostOfFriendlyCivs(nCivID);
         }
      }

      return out;
   }

   protected static final int getCostOfFriendlyCivs(int nCivID) {
      return 3 * CFG.game.getCiv(nCivID).getFriendlyCivsSize();
   }

   protected static final int getCostOfCurrentDiplomaticActionsUpdate(int nCivID) {
      int out = 0;
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize() > 1) {
         out += 6;
      }

      return out;
   }

   protected static final void updateGoldenAge() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            CFG.game
               .getCiv(i)
               .setGoldenAge_Prosperity(
                  CFG.game.getCiv(i).getGoldenAge_Prosperity()
                     + (int)(
                        (CFG.game.getCiv(i).getSpendings_Goods() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i))
                           * 100.0F
                     )
                     + (int)(
                        (CFG.game.getCiv(i).getSpendings_Investments() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS)
                           * 100.0F
                     )
               );
            CFG.game.getCiv(i).setGoldenAge_Science(CFG.game.getCiv(i).getGoldenAge_Science() + (int)(CFG.game.getCiv(i).getSpendings_Research() * 100.0F));
            CFG.game
               .getCiv(i)
               .setGoldenAge_Military(CFG.game.getCiv(i).getGoldenAge_Military() + CFG.game_NextTurnUpdate.getMilitarySpendings(i, CFG.game.getCiv(i).iBudget));
         }
      }

      if (Game_Calendar.TURN_ID % 30 == 10) {
         if (getNumOfCivsInTheGame() > 7) {
            int nAverageScore = 0;
            int nCivs = 0;

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                  nAverageScore += CFG.game.getCiv(i).getGoldenAge_Science();
                  ++nCivs;
               }
            }

            float fAverage = (float)Math.ceil((double)((float)nAverageScore / (float)Math.max(nCivs, 1)));
            List<Integer> tCivs = new ArrayList<>();
            int toRand = 0;

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && (float)CFG.game.getCiv(i).getGoldenAge_Science() >= fAverage) {
                  toRand += CFG.game.getCiv(i).getGoldenAge_Science();
                  tCivs.add(i);
               }
            }

            if (toRand > 0) {
               toRand = CFG.oR.nextInt(toRand);
               int i = 0;

               for(int counted = 0; i < tCivs.size(); ++i) {
                  if (toRand >= counted && toRand < counted + CFG.game.getCiv(tCivs.get(i)).getGoldenAge_Science()) {
                     goldenAge_Science(tCivs.get(i));
                     CFG.game.getCiv(tCivs.get(i)).setGoldenAge_Science(0);
                     break;
                  }

                  counted += CFG.game.getCiv(tCivs.get(i)).getGoldenAge_Science();
               }
            }

            tCivs.clear();

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).setGoldenAge_Science((int)((float)CFG.game.getCiv(i).getGoldenAge_Science() * 0.3F));
            }
         } else {
            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).setGoldenAge_Science((int)((float)CFG.game.getCiv(i).getGoldenAge_Science() * 0.15F));
            }
         }
      } else if (Game_Calendar.TURN_ID % 30 == 15) {
         if (getNumOfCivsInTheGame() > 7) {
            int nAverageScore = 0;
            int nCivs = 0;

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                  nAverageScore += CFG.game.getCiv(i).getGoldenAge_Military();
                  ++nCivs;
               }
            }

            float fAverage = (float)Math.ceil((double)((float)nAverageScore / (float)Math.max(nCivs, 1)));
            List<Integer> tCivs = new ArrayList<>();
            int toRand = 0;

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && (float)CFG.game.getCiv(i).getGoldenAge_Military() >= fAverage) {
                  toRand += CFG.game.getCiv(i).getGoldenAge_Military();
                  tCivs.add(i);
               }
            }

            if (toRand > 0) {
               toRand = CFG.oR.nextInt(toRand);
               int i = 0;

               for(int counted = 0; i < tCivs.size(); ++i) {
                  if (toRand >= counted && toRand < counted + CFG.game.getCiv(tCivs.get(i)).getGoldenAge_Military()) {
                     goldenAge_Military(tCivs.get(i));
                     CFG.game.getCiv(tCivs.get(i)).setGoldenAge_Military(0);
                     break;
                  }

                  counted += CFG.game.getCiv(tCivs.get(i)).getGoldenAge_Military();
               }
            }

            tCivs.clear();

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).setGoldenAge_Military((int)((float)CFG.game.getCiv(i).getGoldenAge_Military() * 0.3F));
            }
         } else {
            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).setGoldenAge_Military((int)((float)CFG.game.getCiv(i).getGoldenAge_Military() * 0.15F));
            }
         }
      } else if (Game_Calendar.TURN_ID % 30 == 20) {
         if (getNumOfCivsInTheGame() > 7) {
            int nAverageScore = 0;
            int nCivs = 0;

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                  nAverageScore += CFG.game.getCiv(i).getGoldenAge_Prosperity();
                  ++nCivs;
               }
            }

            float fAverage = (float)Math.ceil((double)((float)nAverageScore / (float)Math.max(nCivs, 1)));
            List<Integer> tCivs = new ArrayList<>();
            int toRand = 0;

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && (float)CFG.game.getCiv(i).getGoldenAge_Prosperity() >= fAverage) {
                  toRand += CFG.game.getCiv(i).getGoldenAge_Prosperity();
                  tCivs.add(i);
               }
            }

            if (toRand > 0) {
               toRand = CFG.oR.nextInt(toRand);
               int i = 0;

               for(int counted = 0; i < tCivs.size(); ++i) {
                  if (toRand >= counted && toRand < counted + CFG.game.getCiv(tCivs.get(i)).getGoldenAge_Prosperity()) {
                     goldenAge_Prosperity(tCivs.get(i));
                     CFG.game.getCiv(tCivs.get(i)).setGoldenAge_Prosperity(0);
                     break;
                  }

                  counted += CFG.game.getCiv(tCivs.get(i)).getGoldenAge_Prosperity();
               }
            }

            tCivs.clear();

            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).setGoldenAge_Prosperity((int)((float)CFG.game.getCiv(i).getGoldenAge_Prosperity() * 0.15F));
            }
         } else {
            for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
               CFG.game.getCiv(i).setGoldenAge_Prosperity((int)((float)CFG.game.getCiv(i).getGoldenAge_Prosperity() * 0.1F));
            }
         }
      }
   }

   protected static int getNumOfCivsInTheGame() {
      int nCivs = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            ++nCivs;
         }
      }

      return nCivs;
   }

   protected static final void goldenAge_Prosperity(int nCivID) {
      Gdx.app.log("AoC", "PROSPERITY: " + CFG.game.getCiv(nCivID).getCivName());
      CivBonus_GameData nGodlenAge = new CivBonus_GameData();
      nGodlenAge.iTurnsLeft = 8;
      nGodlenAge.BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_PROSPERITY;
      nGodlenAge.fModifier_PopGrowth = 0.1F + (float)CFG.oR.nextInt(10) / 100.0F;
      nGodlenAge.fModifier_EconomyGrowth = 0.08F + (float)CFG.oR.nextInt(5) / 100.0F;
      nGodlenAge.fModifier_IncomeTaxation = 0.06F + (float)CFG.oR.nextInt(6) / 100.0F;
      if (CFG.game.getCiv(nCivID).addNewBonus(nGodlenAge)) {
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoldenAge(nCivID, nGodlenAge.iTurnsLeft));
      }
   }

   protected static final void goldenAge_Military(int nCivID) {
      Gdx.app.log("AoC", "MILITARY: " + CFG.game.getCiv(nCivID).getCivName());
      CivBonus_GameData nGodlenAge = new CivBonus_GameData();
      nGodlenAge.iTurnsLeft = 10;
      nGodlenAge.BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_MILITARY;
      nGodlenAge.fModifier_AttackBonus = 0.08F + (float)CFG.oR.nextInt(6) / 100.0F;
      nGodlenAge.fModifier_MilitaryUpkeep = -0.14F - (float)CFG.oR.nextInt(6) / 100.0F;
      nGodlenAge.fModifier_MovementPoints = 0.06F + (float)CFG.oR.nextInt(10) / 100.0F;
      if (CFG.game.getCiv(nCivID).addNewBonus(nGodlenAge)) {
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoldenAgeMilitary(nCivID, nGodlenAge.iTurnsLeft));
      }
   }

   protected static final void goldenAge_Science(int nCivID) {
      Gdx.app.log("AoC", "SCIENCE: " + CFG.game.getCiv(nCivID).getCivName());
      CivBonus_GameData nGodlenAge = new CivBonus_GameData();
      nGodlenAge.iTurnsLeft = 8;
      nGodlenAge.BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_SCIENCE;
      nGodlenAge.fModifier_Research = 0.15F + (float)CFG.oR.nextInt(10) / 100.0F;
      nGodlenAge.fModifier_DefenseBonus = 0.1F + (float)CFG.oR.nextInt(6) / 100.0F;
      nGodlenAge.fModifier_IncomeProduction = 0.06F + (float)CFG.oR.nextInt(8) / 100.0F;
      if (CFG.game.getCiv(nCivID).addNewBonus(nGodlenAge)) {
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoldenAgeScience(nCivID, nGodlenAge.iTurnsLeft));
      }
   }

   protected static final void sendAllianceProposal(int iToCivID, int iFromCivID) {
      if (CFG.game.getCiv(iToCivID).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(iToCivID).getAllianceID()).getCivilizationsSize() > 0) {
         CFG.game
            .getCiv(CFG.game.getAlliance(CFG.game.getCiv(iToCivID).getAllianceID()).getCivilization(0))
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message(iFromCivID, 0));
      } else {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message(iFromCivID, 0));
      }

      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 20);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.JOIN_ALLIANCE));
      }
   }

   protected static final void declineAllianceProposal(int iCivID, int iFromCivID) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Alliance_Denied(iCivID));
   }

   protected static final void acceptAllianceProposal(int iCivID, int iFromCivID) {
      if (CFG.game.getCiv(iCivID).getAllianceID() == 0 && CFG.game.getCiv(iFromCivID).getAllianceID() == 0) {
         CFG.game.addAlliance(CFG.getRandomAllianceName(0));
         int tempAllianceID = CFG.game.getAlliancesSize() - 1;
         if (CFG.game.getCiv(iCivID).getControlledByPlayer()) {
            CFG.game.getAlliance(tempAllianceID).addCivilization(iCivID);
            CFG.game.getAlliance(tempAllianceID).addCivilization(iFromCivID);
         } else if (CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getAlliance(tempAllianceID).addCivilization(iFromCivID);
            CFG.game.getAlliance(tempAllianceID).addCivilization(iCivID);
         } else {
            CFG.game.getAlliance(tempAllianceID).addCivilization(iCivID);
            CFG.game.getAlliance(tempAllianceID).addCivilization(iFromCivID);
         }

         CFG.game.getCiv(iCivID).setAllianceID(tempAllianceID);
         CFG.game.getCiv(iFromCivID).setAllianceID(tempAllianceID);
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iCivID, tempAllianceID));
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iFromCivID, tempAllianceID));
      } else if (CFG.game.getCiv(iFromCivID).getAllianceID() > 0 && CFG.game.getCiv(iCivID).getAllianceID() == 0) {
         CFG.game.getAlliance(CFG.game.getCiv(iFromCivID).getAllianceID()).addCivilization(iCivID);
         CFG.game.getCiv(iCivID).setAllianceID(CFG.game.getCiv(iFromCivID).getAllianceID());
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iCivID, CFG.game.getCiv(iFromCivID).getAllianceID()));
      } else if (CFG.game.getCiv(iCivID).getAllianceID() > 0 && CFG.game.getCiv(iFromCivID).getAllianceID() == 0) {
         CFG.game.getAlliance(CFG.game.getCiv(iCivID).getAllianceID()).addCivilization(iFromCivID);
         CFG.game.getCiv(iFromCivID).setAllianceID(CFG.game.getCiv(iCivID).getAllianceID());
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iFromCivID, CFG.game.getCiv(iCivID).getAllianceID()));
      } else {
         CFG.game.getAlliance(CFG.game.getCiv(iCivID).getAllianceID()).removeCivilization(iCivID);
         CFG.game.getAlliance(CFG.game.getCiv(iFromCivID).getAllianceID()).addCivilization(iCivID);
         CFG.game.getCiv(iCivID).setAllianceID(CFG.game.getCiv(iFromCivID).getAllianceID());
         CFG.game.getCiv(iCivID).setAllianceID(CFG.game.getCiv(iFromCivID).getAllianceID());
      }

      if (CFG.game.getCiv(iCivID).getControlledByPlayer()) {
         CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iCivID));
         CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(iCivID)).buildMetProvincesAndCivs();
      }

      if (CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iFromCivID));
         CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(iFromCivID)).buildMetProvincesAndCivs();
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Alliance_Accepted(iCivID));
   }

   protected static int getAllianceProposal_Positive(int nCivA, int nCivB) {
      int out = 0;
      out += getAllianceProposal_Positive_Opinion(nCivA, nCivB);
      out += getAllianceProposal_Positive_Goverment(nCivA, nCivB);
      if (getAllianceProposale_CivStrength(nCivA, nCivB) > 0) {
         out += getAllianceProposale_CivStrength(nCivA, nCivB);
      }

      return out + getAllianceProposal_Positive_HRE(nCivA, nCivB);
   }

   protected static int getAllianceProposal_Negative(int nCivA, int nCivB) {
      int out = 0;
      out += getAllianceProposal_Negative_Opinion(nCivA, nCivB);
      out += getAllianceProposal_Negative_Goverment(nCivA, nCivB);
      out += getAllianceProposal_Negative_HRE(nCivA, nCivB);
      out += getAllianceProposal_Negative_PowerfulAllies(nCivA, nCivB);
      out += getAllianceProposal_Negative_PowerfulAllies(nCivB, nCivA);
      out += getAllianceProposal_Negative_CivIsAtWar(nCivA);
      out += getAllianceProposal_Negative_EmbassyClosed(nCivA, nCivB);
      out += getAllianceProposal_Negative_HaveACore(nCivA, nCivB);
      out += getAllianceProposal_Negative_IsAVassal(nCivA, nCivB);
      out += getAllianceProposal_Negative_Distance(nCivA, nCivB);
      if (getAllianceProposale_CivStrength(nCivA, nCivB) < 0) {
         out += getAllianceProposale_CivStrength(nCivA, nCivB);
      }

      return out;
   }

   protected static int getAllianceProposal_Positive_HRE(int nCivA, int nCivB) {
      return CFG.game.getCiv(nCivA).getIsPartOfHolyRomanEmpire() && CFG.game.getCiv(nCivB).getIsPartOfHolyRomanEmpire() ? 4 : 0;
   }

   protected static int getAllianceProposal_Positive_Opinion(int nCivA, int nCivB) {
      return CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION > 0.0F
         ? (int)((CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION) / 1.94F)
         : 0;
   }

   protected static int getAllianceProposal_Positive_Goverment(int nCivA, int nCivB) {
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivA).getIdeologyID()).GOV_GROUP_ID
         == CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivB).getIdeologyID()).GOV_GROUP_ID) {
         return CFG.game.getCiv(nCivA).getIdeologyID() == CFG.game.getCiv(nCivB).getIdeologyID() ? 6 : 2;
      } else {
         return 0;
      }
   }

   protected static int getAllianceProposale_CivStrength(int nCivA, int nCivB) {
      return (int)(
         -CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH / 2.0F
            + CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH
               / 2.0F
               * Math.min((float)CFG.game.getCiv(nCivA).getRankScore() / (float)CFG.game.getCiv(nCivB).getRankScore(), 2.0F)
      );
   }

   protected static int getAllianceProposal_Negative_Opinion(int nCivA, int nCivB) {
      return CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION < 0.0F
         ? (int)(
            (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION) / 2.0F
               - (float)(CFG.game.getCivRelation_OfCivB(nCivB, nCivA) < 0.0F ? 5 : 0)
         )
         : 0;
   }

   protected static int getAllianceProposal_Negative_HRE(int nCivA, int nCivB) {
      return (!CFG.game.getCiv(nCivA).getIsPartOfHolyRomanEmpire() || CFG.game.getCiv(nCivB).getIsPartOfHolyRomanEmpire())
            && (CFG.game.getCiv(nCivA).getIsPartOfHolyRomanEmpire() || !CFG.game.getCiv(nCivB).getIsPartOfHolyRomanEmpire())
         ? 0
         : -6;
   }

   protected static int getAllianceProposal_Negative_Goverment(int nCivA, int nCivB) {
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivA).getIdeologyID()).GOV_GROUP_ID
         == CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivB).getIdeologyID()).GOV_GROUP_ID) {
         return 0;
      } else {
         return CFG.game.getCiv(nCivA).getIdeologyID() != CFG.game.getCiv(nCivB).getIdeologyID() ? -15 : 0;
      }
   }

   protected static int getAllianceProposal_Negative_PowerfulAllies(int nCivA, int nCivB) {
      int out = 0;

      try {
         if (CFG.game.getCiv(nCivA).getAllianceID() > 0) {
            for(int i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(nCivA).getAllianceID()).getCivilizationsSize(); ++i) {
               if (nCivA != CFG.game.getAlliance(CFG.game.getCiv(nCivA).getAllianceID()).getCivilization(i)) {
                  out -= (int)Math.min(
                     12.0F,
                     10.0F
                        * (
                           (float)CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(nCivA).getAllianceID()).getCivilization(i)).getRankScore()
                              / (float)CFG.game.getCiv(nCivA).getRankScore()
                        )
                  );
                  out += (int)(
                     (float)getAllianceProposal_Negative_Opinion(nCivB, CFG.game.getAlliance(CFG.game.getCiv(nCivA).getAllianceID()).getCivilization(i))
                        * 0.715F
                  );
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         if (i != nCivA && CFG.game.getCiv(i).getPuppetOfCivID() == nCivA && CFG.game.getCiv(nCivA).getNumOfProvinces() > 0) {
            --out;
         }
      }

      return out;
   }

   protected static int getAllianceProposal_Negative_CivIsAtWar(int nCivA) {
      return CFG.game.getCiv(nCivA).isAtWar() ? -250 : 0;
   }

   protected static int getAllianceProposal_Negative_EmbassyClosed(int nCivA, int nCivB) {
      return !CFG.game.getCiv(nCivA).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivB)
            && !CFG.game.getCiv(nCivB).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivA)
         ? 0
         : -1000;
   }

   protected static int getAllianceProposal_Negative_HaveACore(int nCivA, int nCivB) {
      int nNumOfCores = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivA).getNumOfProvinces(); ++i) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivA).getProvinceID(i)).getCore().getHaveACore(nCivB)) {
            ++nNumOfCores;
         }
      }

      return nNumOfCores > 0 ? -Math.min(15 + 5 * (nNumOfCores - 1), 30) : 0;
   }

   protected static int getAllianceProposal_Negative_IsAVassal(int nCivA, int nCivB) {
      return CFG.game.getCiv(nCivA).getPuppetOfCivID() != nCivA && CFG.game.getCiv(nCivA).getPuppetOfCivID() != nCivB ? -250 : 0;
   }

   protected static int getAllianceProposal_Negative_Distance(int nCivA, int nCivB) {
      float minDistance = 1.0F;

      for(int i = 0; i < CFG.game.getCiv(nCivA).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(nCivB).getNumOfProvinces(); ++j) {
            minDistance = Math.min(
               minDistance,
               CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivA).getProvinceID(i), CFG.game.getCiv(nCivB).getProvinceID(j))
            );
         }
      }

      return (int)(-CFG.gameAges.getAge_DistanceDiplomacy(Game_Calendar.CURRENT_AGEID) * minDistance);
   }

   protected static final void joinAWar(int iCivID, int iFromCivID, int iValue) {
      int tWarID = CFG.game.getWarID(iFromCivID, iValue);
      CFG.game.joinWar(iCivID, iValue, tWarID);
      if (CFG.game.getCivsAtWar(iCivID, iValue)) {
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_AllyJoinedAWar(iCivID, iValue, iFromCivID));
         CFG.game.setCivRelation_OfCivB(iCivID, iFromCivID, CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + 5.0F);
         CFG.game.setCivRelation_OfCivB(iFromCivID, iCivID, CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + 5.0F);
      }
   }

   protected static final List<Integer> callToArmsListOfCivs(int byCivID, int onCivID) {
      List<Integer> alliesToCall = new ArrayList<>();
      int tWarID = CFG.game.getWarID(byCivID, onCivID);
      if (CFG.game.getCiv(byCivID).getAllianceID() > 0) {
         for(int i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilizationsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i)).getNumOfProvinces() > 0
               && CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i) != byCivID
               && !CFG.game.getCivsAtWar(CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i), onCivID)) {
               alliesToCall.add(CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i));
            }
         }
      }

      for(int i = 0; i < CFG.game.getCiv(byCivID).civGameData.iVassalsSize; ++i) {
         if (!CFG.game.getWar(tWarID).getIsInDefenders(CFG.game.getCiv(byCivID).civGameData.lVassals.get(i).iCivID)
            && !CFG.game.getWar(tWarID).getIsAggressor(CFG.game.getCiv(byCivID).civGameData.lVassals.get(i).iCivID)
            && CFG.game.getCiv(CFG.game.getCiv(byCivID).civGameData.lVassals.get(i).iCivID).getNumOfProvinces() > 0) {
            boolean wasAdded = false;

            for(int j = 0; j < alliesToCall.size(); ++j) {
               if (alliesToCall.get(j) == CFG.game.getCiv(byCivID).civGameData.lVassals.get(i).iCivID) {
                  wasAdded = true;
                  break;
               }
            }

            if (!wasAdded) {
               alliesToCall.add(CFG.game.getCiv(byCivID).civGameData.lVassals.get(i).iCivID);
            }
         }
      }

      if (CFG.game.getCiv(byCivID).getCivID() != CFG.game.getCiv(byCivID).getPuppetOfCivID()
         && !CFG.game.getWar(tWarID).getIsInDefenders(CFG.game.getCiv(byCivID).getPuppetOfCivID())
         && !CFG.game.getWar(tWarID).getIsAggressor(CFG.game.getCiv(byCivID).getPuppetOfCivID())
         && CFG.game.getCiv(CFG.game.getCiv(byCivID).getPuppetOfCivID()).getNumOfProvinces() > 0) {
         boolean wasAdded = false;

         for(int j = 0; j < alliesToCall.size(); ++j) {
            if (alliesToCall.get(j) == CFG.game.getCiv(byCivID).getPuppetOfCivID()) {
               wasAdded = true;
               break;
            }
         }

         if (!wasAdded) {
            alliesToCall.add(CFG.game.getCiv(byCivID).getPuppetOfCivID());
         }
      }

      return alliesToCall;
   }

   protected static final void sendCallToArms(int iToCivID, int iFromCivID, int warAgainstCivID) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_CallToArms(iFromCivID, warAgainstCivID));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 0);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.WAR_DECLARED_ON_ALLY));
      }
   }

   protected static final void acceptCallToArms(int iCivID, int iFromCivID, int iValue) {
      int tWarID = CFG.game.getWarID(iFromCivID, iValue);
      CFG.game.joinWar(iCivID, iValue, tWarID);
      if (CFG.game.getCivsAtWar(iCivID, iValue)) {
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_CallToArms_Join(iCivID, iValue, iFromCivID));
         CFG.game.setCivRelation_OfCivB(iCivID, iFromCivID, CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + 10.0F);
         CFG.game.setCivRelation_OfCivB(iFromCivID, iCivID, CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + 10.0F);
      }
   }

   protected static final void declineCallToArms(int iCivID, int iFromCivID, int iValue) {
      if (!CFG.game.getCivsAtWar(iCivID, iValue)) {
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_CallToArms_Deny(iCivID, iValue, iFromCivID));
         CFG.game
            .setCivRelation_OfCivB(
               iCivID,
               iFromCivID,
               CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -15.0F <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -15.0F
            );
         CFG.game
            .setCivRelation_OfCivB(
               iFromCivID,
               iCivID,
               CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -15.0F <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -15.0F
            );
      }
   }

   protected static final void callToArms_Denied_SendInsult(int iCivID, int iFromCivID, int iValue) {
      decreaseRelation(iCivID, iFromCivID, 15);
   }

   protected static final void sendPrepareForWar(int iToCivID, int iFromCivID, int warAgainstCivID, int numOfTurns, int iLeaderCivID) {
      CFG.game
         .getCiv(iToCivID)
         .getCivilization_Diplomacy_GameData()
         .messageBox
         .addMessage(new Message_PrepareForWar(iFromCivID, warAgainstCivID, Game_Calendar.TURN_ID + numOfTurns, iLeaderCivID));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 0);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.PREPARE_FOR_WAR));
      }
   }

   protected static final void acceptPrepareForWar(int iLeaderCivID, int iCivID, int iFromCivID, int warAgainstCivID, int numOfTurns) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_PrepareForWar_Accepted(iCivID, warAgainstCivID));
      CFG.game.getCiv(iFromCivID).civGameData.civPlans.addNewWarPreparations(iLeaderCivID, iFromCivID, warAgainstCivID, numOfTurns);
      CFG.game.getCiv(iCivID).civGameData.civPlans.addNewWarPreparations(iLeaderCivID, iCivID, warAgainstCivID, numOfTurns);
   }

   protected static final void declinePrepareForWar(int iLeaderCivID, int iCivID, int iFromCivID, int warAgainstCivID, int numOfTurns) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_PrepareForWar_Refused(iCivID, warAgainstCivID));
      CFG.game
         .setCivRelation_OfCivB(
            iCivID,
            iFromCivID,
            CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > -100.0F
                  && CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + (float)WAR_PREPARATIONS_REFUSE_OPINION_CHANGE <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + (float)WAR_PREPARATIONS_REFUSE_OPINION_CHANGE
         );
      CFG.game
         .setCivRelation_OfCivB(
            iFromCivID,
            iCivID,
            CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > -100.0F
                  && CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + (float)WAR_PREPARATIONS_REFUSE_OPINION_CHANGE <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + (float)WAR_PREPARATIONS_REFUSE_OPINION_CHANGE
         );
   }

   protected static final void sendUnionProposal(int iToCivID, int iFromCivID) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union(iFromCivID, 0));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 22);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.UNION));
      }
   }

   protected static final void acceptUnionProposal(int iCivID, int iFromCivID) {
      if (iCivID != iFromCivID && CFG.game.getCiv(iCivID).getNumOfProvinces() > 0 && CFG.game.getCiv(iFromCivID).getNumOfProvinces() > 0) {
         ++CFG.game.getCiv(iCivID).civGameData.numOfUnions;
         ++CFG.game.getCiv(iFromCivID).civGameData.numOfUnions;
         CFG.createUnion(iCivID, iFromCivID);
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union_Accepted(iCivID, 0));
         CFG.game.getCiv(iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union_Accepted(iFromCivID, 0));
      }
   }

   protected static final void declineUnionProposal(int iCivID, int iFromCivID) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union_Refused(iCivID, 0));
   }

   protected static final void sendNonAggressionProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 8);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.NONAGGRESSIONPACT));
      }
   }

   protected static final void acceptNonAggressionPact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setCivNonAggressionPact(iCivID, iFromCivID, iValue);
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_SignedNonAggressionPact(iFromCivID, iCivID));
   }

   protected static final void declineNonAggressionPact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact_Denied(iCivID));
   }

   protected static final void sendOfferVasalizationProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_OfferVasalization(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 16);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.OFFERVASALIZATION));
      }
   }

   protected static final void acceptOfferVasalization(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iCivID).setPuppetOfCivID(iFromCivID);
      if (CFG.game.getCiv(iFromCivID).getControlledByPlayer() && CFG.FOG_OF_WAR > 0) {
         int tPlayerID = CFG.game.getPlayerID_ByCivID(iFromCivID);
         if (tPlayerID >= 0) {
            for(int i = 0; i < CFG.game.getCiv(iCivID).getNumOfProvinces(); ++i) {
               CFG.game.getProvince(CFG.game.getCiv(iCivID).getProvinceID(i)).updateFogOfWar(tPlayerID);
            }
         }
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Vassalization_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_IsVassal(iFromCivID, iCivID));
   }

   protected static final void declineOfferVasalization(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Vassalization_Rejected(iCivID));
   }

   protected static final void sendMilitaryAccess_AskProposal(int iToCivID, int iFromCivID, int iValue) {
      if (CFG.game.getCiv(iFromCivID).getDiplomacyPoints() >= 10) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Ask(iFromCivID, iValue));
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.MILITARY_ACCESS_ASK));
         }
      }
   }

   protected static final void acceptMilitaryAccess_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setMilitaryAccess(iFromCivID, iCivID, iValue);
      if (CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > 0.0F) {
         CFG.game
            .setCivRelation_OfCivB(
               iCivID,
               iFromCivID,
               CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) - Math.max(CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) / 9.325F, 1.127F)
            );
      }

      if (CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > 0.0F) {
         CFG.game
            .setCivRelation_OfCivB(
               iFromCivID,
               iCivID,
               CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) - Math.max(CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) / 9.325F, 1.127F)
            );
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Ask_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
   }

   protected static final void declineMilitaryAccess_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Ask_Denied(iCivID));
   }

   protected static final void sendMilitaryAccess_GiveProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Give(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 4);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.MILITARY_ACCESS_GIVE));
      }
   }

   protected static final void acceptMilitaryAccess_Give(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setMilitaryAccess(iCivID, iFromCivID, iValue);
      CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
   }

   protected static final void declineMilitaryAccess_Give(int iCivID, int iFromCivID, int iValue) {
   }

   protected static final void sendGuaranteeIndependence_AskProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Ask(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.GUARANTEE_ASK));
      }
   }

   protected static final void acceptGuaranteeIndependence_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setGuarantee(iFromCivID, iCivID, iValue);
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Ask_Accepted(iCivID));

      try {
         CFG.historyManager.addHistoryLog(new HistoryLog_Guarantee(iCivID, iFromCivID));
      } catch (NullPointerException var4) {
      } catch (IndexOutOfBoundsException var5) {
      }
   }

   protected static final void declineGuaranteeIndependence_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Ask_Denied(iCivID));
   }

   protected static final void sendDefensivePactProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.DEFENSIVEPACT));
      }
   }

   protected static final void acceptDefensivePact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setDefensivePact(iCivID, iFromCivID, iValue);
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_SignedDefensivePact(iFromCivID, iCivID));
   }

   protected static final void declineDefensivePact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact_Denied(iCivID));
   }

   protected static final void sendGift(int iToCivID, int iFromCivID, int iValue) {
      if ((float)CFG.game.getCiv(iFromCivID).getMoney() * 0.25F < (float)iValue) {
         iValue = (int)Math.max(0.0F, (float)CFG.game.getCiv(iFromCivID).getMoney() * 0.25F);
      }

      if (iValue > 0) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift(iFromCivID, iValue));
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() - (long)iValue);
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 8);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.GIFT));
         }
      }
   }

   protected static final void acceptGift(int iCivID, int iFromCivID, int iValue) {
      if (iValue >= 0) {
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + (long)iValue);
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift_Accepted(iCivID, iValue));
         CFG.game.getCiv(iCivID).civGameData.addGift_Received(iFromCivID);
      }
   }

   protected static final void declineGift(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() + (long)iValue);
      CFG.game
         .setCivRelation_OfCivB(
            iCivID,
            iFromCivID,
            CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -8.0F <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -8.0F
         );
      CFG.game
         .setCivRelation_OfCivB(
            iFromCivID,
            iCivID,
            CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -8.0F <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -8.0F
         );
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift_Refused(iCivID, iValue));
   }

   protected static final boolean sendUltimatum(int iToCivID, int iFromCivID, Ultimatum_GameData nUltimatum, int nUnits) {
      if (CFG.game.getCivRelation_OfCivB(iToCivID, iFromCivID) > -10.0F) {
         return false;
      } else {
         if (CFG.game.getCiv(iToCivID).getPuppetOfCivID() == iToCivID || CFG.game.getCiv(iToCivID).getPuppetOfCivID() == iFromCivID) {
            if (CFG.game.getCiv(iFromCivID).getDiplomacyPoints() < 24) {
               return false;
            }

            CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Ultimatum(iFromCivID, nUltimatum, nUnits));
            CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 24);
         }

         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.ULTIMATUM));
         }

         return true;
      }
   }

   protected static final void acceptUltimatum(int iToCivID, int iFromCivID, Ultimatum_GameData ultimatum) {
      if (CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iFromCivID || CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iToCivID) {
         CFG.game
            .getCiv(iFromCivID)
            .setVassalLiberityDesire(CFG.game.getCiv(iFromCivID).getVassalLiberityDesire() * 1.25F + 18.0F + (float)CFG.oR.nextInt(36));
         if (ultimatum.demandAnexation) {
            List<Integer> tempProvinces = new ArrayList<>();

            for(int i = 0; i < CFG.game.getCiv(iFromCivID).getNumOfProvinces(); ++i) {
               tempProvinces.add(CFG.game.getCiv(iFromCivID).getProvinceID(i));
            }

            for(int i = 0; i < tempProvinces.size(); ++i) {
               if (CFG.game.getProvince(tempProvinces.get(i)).getCivID() == iFromCivID
                  && CFG.game.getProvince(tempProvinces.get(i)).getTrueOwnerOfProvince() == iFromCivID) {
                  int nArmyNewOwnerArmy = CFG.game.getProvince(tempProvinces.get(i)).getArmyCivID(iToCivID);
                  CFG.game.getProvince(tempProvinces.get(i)).updateArmy(0);
                  CFG.game.getProvince(tempProvinces.get(i)).updateArmy(iToCivID, 0);
                  CFG.game.getProvince(tempProvinces.get(i)).setTrueOwnerOfProvince(iToCivID);
                  CFG.game.getProvince(tempProvinces.get(i)).setCivID(iToCivID, false);
                  CFG.game.getProvince(tempProvinces.get(i)).updateArmy(iToCivID, nArmyNewOwnerArmy);

                  for(int j = CFG.game.getProvince(tempProvinces.get(i)).getCivsSize() - 1; j >= 0; --j) {
                     if (CFG.game.getCiv(CFG.game.getProvince(tempProvinces.get(i)).getCivID(j)).getPuppetOfCivID() != iToCivID
                        && CFG.game.getCiv(iToCivID).getPuppetOfCivID() != CFG.game.getProvince(tempProvinces.get(i)).getCivID(j)
                        && (
                           CFG.game.getCiv(CFG.game.getProvince(tempProvinces.get(i)).getCivID(j)).getAllianceID() <= 0
                              || CFG.game.getCiv(CFG.game.getProvince(tempProvinces.get(i)).getCivID(j)).getAllianceID()
                                 != CFG.game.getCiv(iToCivID).getAllianceID()
                        )
                        && CFG.game.getMilitaryAccess(CFG.game.getProvince(tempProvinces.get(i)).getCivID(j), iToCivID) <= 0) {
                        CFG.gameAction.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(tempProvinces.get(i)).getCivID(j), tempProvinces.get(i));
                     }
                  }
               }
            }

            if (CFG.game.getCiv(iFromCivID).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).setIsCapital(false);

               for(int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).getCitiesSize(); ++i) {
                  if (CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).getCity(i).getCityLevel() == CFG.getEditorCityLevel(0)) {
                     CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
                  }
               }
            }

            CFG.game.getCiv(iFromCivID).buildNumOfUnits();
            tempProvinces.clear();
            CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
            CFG.game.buildCivilizationsRegions_TextOver(iToCivID);
            CFG.game.getCiv(iFromCivID).setPuppetOfCivID(iFromCivID);
            CFG.historyManager.addHistoryLog(new HistoryLog_Annexation(iFromCivID, iToCivID));
         }

         if (ultimatum.demandVasalization) {
            CFG.game.getCiv(iFromCivID).setPuppetOfCivID(iToCivID);
            if (CFG.game.getCiv(iToCivID).getControlledByPlayer() && CFG.FOG_OF_WAR > 0) {
               int tPlayerID = CFG.game.getPlayerID_ByCivID(iToCivID);
               if (tPlayerID >= 0) {
                  for(int i = 0; i < CFG.game.getCiv(iFromCivID).getNumOfProvinces(); ++i) {
                     CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getProvinceID(i)).updateFogOfWar(tPlayerID);
                  }
               }
            }

            CFG.historyManager.addHistoryLog(new HistoryLog_IsVassal(iToCivID, iFromCivID));
         }

         if (ultimatum.demandMilitaryAccess) {
            CFG.game.setMilitaryAccess(iToCivID, iFromCivID, Math.max(CFG.game.getMilitaryAccess(iToCivID, iFromCivID), 40));
            CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iToCivID));
         }

         if (ultimatum.demandLiberation.size() > 0) {
            for(int i = 0; i < ultimatum.demandLiberation.size(); ++i) {
               liberateAVassal(iFromCivID, ultimatum.demandLiberation.get(i));
               CFG.game.setCivTruce(iFromCivID, ultimatum.demandLiberation.get(i), 22);
            }
         }

         if (ultimatum.demandProvinces.size() > 0) {
            for(int i = 0; i < ultimatum.demandProvinces.size(); ++i) {
               if (CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getCivID() == iFromCivID
                  && CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getTrueOwnerOfProvince() == iFromCivID) {
                  List<Integer> tempCivs = new ArrayList<>();
                  List<Integer> tempArmies = new ArrayList<>();

                  for(int j = 0; j < CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getCivsSize(); ++j) {
                     tempCivs.add(CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getCivID(j));
                     tempArmies.add(CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getArmy(j));
                  }

                  int nArmyNewOwnerArmy = CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getArmyCivID(iToCivID);
                  int nOwnerArmy = CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getArmy(0);
                  int nOwnerCivID = CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getCivID();
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i)).updateArmy(0);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i)).updateArmy(iToCivID, 0);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i)).setTrueOwnerOfProvince(iToCivID);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i)).setCivID(iToCivID, false);
                  if (!CFG.game.getProvince(ultimatum.demandProvinces.get(i)).getIsCapital()) {
                     CFG.game.getProvince(ultimatum.demandProvinces.get(i)).removeCapitalCityIcon();
                  }

                  CFG.game.getProvince(ultimatum.demandProvinces.get(i)).updateArmy(iToCivID, nArmyNewOwnerArmy);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i)).updateArmy(nOwnerCivID, nOwnerArmy);

                  for(int j = 0; j < tempCivs.size(); ++j) {
                     if (CFG.game.getCiv(tempCivs.get(j)).getPuppetOfCivID() != iToCivID
                        && CFG.game.getCiv(iToCivID).getPuppetOfCivID() != tempCivs.get(j)
                        && (
                           CFG.game.getCiv(tempCivs.get(j)).getAllianceID() <= 0
                              || CFG.game.getCiv(tempCivs.get(j)).getAllianceID() != CFG.game.getCiv(iToCivID).getAllianceID()
                        )
                        && CFG.game.getMilitaryAccess(tempCivs.get(j), iToCivID) <= 0) {
                        CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivs.get(j), ultimatum.demandProvinces.get(i), tempArmies.get(j));
                     }
                  }
               }
            }

            CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
            CFG.game.buildCivilizationsRegions_TextOver(iToCivID);
         }

         CFG.game.setCivTruce(iToCivID, iFromCivID, 30);
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_UltimatumAccepted(iFromCivID));
      }
   }

   protected static final void refuseUltimatum(int iToCivID, int iFromCivID, Ultimatum_GameData ultimatum) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_UltimatumRefused(iFromCivID));
   }

   protected static final void refuseUltimatum_AcceptWar(int iFromCivID, int iToCivID) {
      CFG.game.declareWar(iFromCivID, iToCivID, false);
   }

   protected static final void vassalDeclareIndependence_War(int iFromCivID, int iToCivID) {
      CFG.game.declareWar(iFromCivID, iToCivID, true);
   }

   protected static final void vassalDeclareIndependence_Fine(int iFromCivID, int iToCivID) {
      CFG.game.acceptPeaceOffer(iFromCivID, iToCivID, 30);
   }

   protected static final void sendPeaceTreaty(boolean toDefenders, int iFromCivID, PeaceTreaty_GameData peaceTreaty_GameData) {
      try {
         CFG.peaceTreatyData.preparePeaceTreatyToSend(iFromCivID);
         CFG.game.lPeaceTreaties.add(new PeaceTreaty_GameData_MessageData(peaceTreaty_GameData));
         String peaceTreatyTag = CFG.game.lPeaceTreaties.get(CFG.game.lPeaceTreaties.size() - 1).PEACE_TREATY_TAG;

         for(int i = 0; i < peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
            if (!peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted) {
               CFG.game
                  .getCiv(peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_PeaceTreaty(iFromCivID, peaceTreatyTag));
            }
         }

         for(int i = 0; i < peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++i) {
            if (!peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).peaceTreatyAccepted) {
               CFG.game
                  .getCiv(peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_PeaceTreaty(iFromCivID, peaceTreatyTag));
            }
         }
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }
   }

   protected static final void acceptPeaceTreaty(int iCivID, String nTag) {
      int peaceID = CFG.game.getPeaceTreaty_GameDataID(nTag);
      boolean everyoneAccepted = true;

      for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
         if (iCivID == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID) {
            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted = true;
         }

         if (!CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted) {
            everyoneAccepted = false;
         }
      }

      for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++i) {
         if (iCivID == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID) {
            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).peaceTreatyAccepted = true;
         }

         if (!CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).peaceTreatyAccepted) {
            everyoneAccepted = false;
         }
      }

      if (everyoneAccepted) {
         try {
            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
               for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.size(); ++j) {
                  CFG.game
                     .getCiv(
                        CFG.game
                           .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                           .getCivID()
                     )
                     .removePlunder_ProvinceID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j));
                  int nArmy0 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .getArmy(0);
                  int nCiv0 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .getCivID();
                  int nArmyNewOwner = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID);
                  int nCivNewOwner = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID;
                  CFG.game.getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).updateArmy(0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID, 0);
                  if (CFG.game.getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getCivID()
                     == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID) {
                     CFG.timelapseManager
                        .addChange(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                           false
                        );
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .setTrueOwnerOfProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .setCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID, false, true);
                  if (!CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .getIsCapital()) {
                     CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                        .removeCapitalCityIcon();
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .updateArmy(nCiv0, nArmy0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lDemands.get(j))
                     .updateArmy(nCivNewOwner, nArmyNewOwner);
               }

               for(int j = 0;
                  j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWarReparationsFromCivsID.size();
                  ++j
               ) {
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID)
                     .addWarReparationsGets(
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWarReparationsFromCivsID.get(j)
                     );
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWarReparationsFromCivsID.get(j))
                     .addWarReparationsPay(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID);
               }
            }

            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++i) {
               for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.size(); ++j) {
                  CFG.game
                     .getCiv(
                        CFG.game
                           .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                           .getCivID()
                     )
                     .removePlunder_ProvinceID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j));
                  int nArmy0 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .getArmy(0);
                  int nCiv0 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .getCivID();
                  int nArmyNewOwner = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID);
                  int nCivNewOwner = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID;
                  CFG.game.getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).updateArmy(0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID, 0);
                  if (CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                        .getCivID()
                     == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID) {
                     CFG.timelapseManager
                        .addChange(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                           false
                        );
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .setTrueOwnerOfProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .setCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID, false, true);
                  if (!CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .getIsCapital()) {
                     CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                        .removeCapitalCityIcon();
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .updateArmy(nCiv0, nArmy0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lDemands.get(j))
                     .updateArmy(nCivNewOwner, nArmyNewOwner);
               }

               for(int j = 0;
                  j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.size();
                  ++j
               ) {
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID)
                     .addWarReparationsGets(
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.get(j)
                     );
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.get(j))
                     .addWarReparationsPay(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID);
               }
            }

            try {
               for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
                  for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.size(); ++j) {
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.get(j))
                        .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID);
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.get(j),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.get(j),
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID
                                 ),
                              22.0F
                           )
                        );
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.get(j),
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.get(j)
                                 ),
                              22.0F
                           )
                        );
                     CFG.historyManager
                        .addHistoryLog(
                           new HistoryLog_IsVassal(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lWillVassalizeCivsID.get(j)
                           )
                        );
                  }
               }
            } catch (IndexOutOfBoundsException var15) {
               CFG.exceptionStack(var15);
            }

            try {
               for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++i) {
                  for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.size(); ++j) {
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.get(j))
                        .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID);
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.get(j),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.get(j),
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID
                                 ),
                              22.0F
                           )
                        );
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.get(j),
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.get(j)
                                 ),
                              22.0F
                           )
                        );
                     CFG.historyManager
                        .addHistoryLog(
                           new HistoryLog_IsVassal(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lWillVassalizeCivsID.get(j)
                           )
                        );
                  }
               }
            } catch (IndexOutOfBoundsException var16) {
               CFG.exceptionStack(var16);
            }

            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
               for(int j = 0;
                  j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.size();
                  ++j
               ) {
                  for(int k = 0; k < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++k) {
                     if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.get(j).iFromCivID
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).iCivID) {
                        for(int o = 0;
                           o < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.size();
                           ++o
                        ) {
                           if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).iCivID
                              == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.get(j).iVassalCivID
                              )
                            {
                              boolean zeroProvinces = CFG.game
                                    .getCiv(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                             .lReleasableCivs_TakeControl
                                             .get(j)
                                          .iVassalCivID
                                    )
                                    .getNumOfProvinces()
                                 == 0;

                              for(int u = 0;
                                 u
                                    < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                       .lProvinces
                                       .size();
                                 ++u
                              ) {
                                 int tempArmy0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmy(0);
                                 int tempCiv0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getCivID();
                                 int nArmyNewOwner = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID);
                                 int nCivNewOwner = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID;
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, 0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setTrueOwnerOfProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                             .lReleasableCivs_TakeControl
                                             .get(j)
                                          .iVassalCivID
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setCivID(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                             .lReleasableCivs_TakeControl
                                             .get(j)
                                          .iVassalCivID,
                                       false,
                                       true
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(tempCiv0, tempArmy0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, nArmyNewOwner);
                                 if (zeroProvinces) {
                                    CFG.game
                                       .getCiv(
                                          CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                                .lReleasableCivs_TakeControl
                                                .get(j)
                                             .iVassalCivID
                                       )
                                       .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID);
                                    CFG.historyManager
                                       .addHistoryLog(
                                          new HistoryLog_IsVassal(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID
                                          )
                                       );
                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID
                                          )
                                       < (float)RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                                             (float)RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID
                                          )
                                       < (float)RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID,
                                             (float)RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    zeroProvinces = false;
                                 }

                                 for(int m = CFG.game
                                          .getProvince(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                .lProvinces
                                                .get(u)
                                          )
                                          .getCivsSize()
                                       - 1;
                                    m >= 0;
                                    --m
                                 ) {
                                    if (CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                      .lReleasableCivs
                                                      .get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(m)
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                      .lReleasableCivs
                                                      .get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                            .lReleasableCivs
                                                            .get(o)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID(m)
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                      .lReleasableCivs
                                                      .get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                            .lReleasableCivs
                                                            .get(o)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID()
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                      .lReleasableCivs
                                                      .get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(m)
                                       && (
                                          CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(m)
                                                   )
                                                   .getAllianceID()
                                                <= 0
                                             || CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(m)
                                                   )
                                                   .getAllianceID()
                                                != CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                       )) {
                                       CFG.gameAction
                                          .accessLost_MoveArmyToClosetsProvince(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                         .lReleasableCivs
                                                         .get(o)
                                                      .lProvinces
                                                      .get(u)
                                                )
                                                .getCivID(m),
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                .lProvinces
                                                .get(u)
                                          );
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++i) {
               for(int j = 0;
                  j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.size();
                  ++j
               ) {
                  for(int k = 0; k < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++k) {
                     if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(j).iFromCivID
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).iCivID) {
                        for(int o = 0; o < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.size(); ++o) {
                           if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o).iCivID
                              == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(j).iVassalCivID
                              )
                            {
                              boolean zeroProvinces = CFG.game
                                    .getCiv(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                             .lReleasableCivs_TakeControl
                                             .get(j)
                                          .iVassalCivID
                                    )
                                    .getNumOfProvinces()
                                 == 0;

                              for(int u = 0;
                                 u
                                    < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                       .lProvinces
                                       .size();
                                 ++u
                              ) {
                                 int tempArmy0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmy(0);
                                 int tempCiv0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getCivID();
                                 int nArmyNewOwner = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID);
                                 int nCivNewOwner = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID;
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, 0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setTrueOwnerOfProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                             .lReleasableCivs_TakeControl
                                             .get(j)
                                          .iVassalCivID
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setCivID(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                             .lReleasableCivs_TakeControl
                                             .get(j)
                                          .iVassalCivID,
                                       false,
                                       true
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(tempCiv0, tempArmy0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, nArmyNewOwner);
                                 if (zeroProvinces) {
                                    CFG.game
                                       .getCiv(
                                          CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                                .lReleasableCivs_TakeControl
                                                .get(j)
                                             .iVassalCivID
                                       )
                                       .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID);
                                    CFG.historyManager
                                       .addHistoryLog(
                                          new HistoryLog_IsVassal(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID
                                          )
                                       );
                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID
                                          )
                                       < (float)RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                                             (float)RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID
                                          )
                                       < (float)RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j)
                                                .iVassalCivID,
                                             (float)RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    zeroProvinces = false;
                                 }

                                 for(int m = CFG.game
                                          .getProvince(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                                .lProvinces
                                                .get(u)
                                          )
                                          .getCivsSize()
                                       - 1;
                                    m >= 0;
                                    --m
                                 ) {
                                    if (CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(m)
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k)
                                                            .lReleasableCivs
                                                            .get(o)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID(m)
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k)
                                                            .lReleasableCivs
                                                            .get(o)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID()
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(m)
                                       && (
                                          CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(m)
                                                   )
                                                   .getAllianceID()
                                                <= 0
                                             || CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(m)
                                                   )
                                                   .getAllianceID()
                                                != CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                       )) {
                                       CFG.gameAction
                                          .accessLost_MoveArmyToClosetsProvince(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k)
                                                         .lReleasableCivs
                                                         .get(o)
                                                      .lProvinces
                                                      .get(u)
                                                )
                                                .getCivID(m),
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(k).lReleasableCivs.get(o)
                                                .lProvinces
                                                .get(u)
                                          );
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
               for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++j) {
                  if (CFG.game
                     .getCivsAtWar(
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID
                     )) {
                     CFG.game
                        .acceptPeaceOffer(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.TRUCE_LENGTH + 1
                        );
                     if (CFG.game
                              .getMilitaryAccess(
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID
                              )
                           <= 0
                        && CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID).getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID
                        && CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID).getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID) {
                        CFG.gameAction
                           .accessLost_UpdateArmies(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID
                           );
                     }

                     if (CFG.game
                              .getMilitaryAccess(
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID,
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID
                              )
                           <= 0
                        && CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID).getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID
                        && CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID).getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID) {
                        CFG.gameAction
                           .accessLost_UpdateArmies(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID
                           );
                     }
                  }
               }
            }

            for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++j) {
               if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID).getNumOfProvinces() == 0) {
                  for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
                     if (CFG.game.getCiv(i).getPuppetOfCivID()
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID) {
                        CFG.game.getCiv(i).setPuppetOfCivID(i);
                     }
                  }

                  if (CFG.holyRomanEmpire_Manager
                     .getHRE()
                     .getIsElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID)) {
                     CFG.holyRomanEmpire_Manager
                        .getHRE()
                        .removeElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID);
                     CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
                  }
               }
            }

            for(int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++j) {
               if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID).getNumOfProvinces() == 0) {
                  for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
                     if (CFG.game.getCiv(i).getPuppetOfCivID()
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID) {
                        CFG.game.getCiv(i).setPuppetOfCivID(i);
                     }

                     if (CFG.holyRomanEmpire_Manager
                        .getHRE()
                        .getIsElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID)) {
                        CFG.holyRomanEmpire_Manager
                           .getHRE()
                           .removeElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j).iCivID);
                        CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
                     }
                  }
               }
            }

            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.size(); ++i) {
               CFG.game.buildCivilizationRegions(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(i).iCivID);
            }

            for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.size(); ++i) {
               CFG.game.buildCivilizationRegions(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(i).iCivID);
            }

            try {
               for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.size(); ++i) {
                  if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(i).iCivID).getNumOfProvinces() == 0) {
                     for(int z = CFG.game
                              .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(i).iCivID)
                              .getArmyInAnotherProvinceSize()
                           - 1;
                        z >= 0;
                        --z
                     ) {
                        CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(i).iCivID)
                                 .getArmyInAnotherProvince(z)
                           )
                           .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(i).iCivID, 0);
                     }

                     CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(i).iCivID).setNumOfUnits(0);
                  }
               }

               for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.size(); ++i) {
                  if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(i).iCivID).getNumOfProvinces() == 0) {
                     for(int z = CFG.game
                              .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(i).iCivID)
                              .getArmyInAnotherProvinceSize()
                           - 1;
                        z >= 0;
                        --z
                     ) {
                        CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(i).iCivID)
                                 .getArmyInAnotherProvince(z)
                           )
                           .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(i).iCivID, 0);
                     }

                     CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(i).iCivID).setNumOfUnits(0);
                  }
               }
            } catch (IndexOutOfBoundsException var17) {
            }

            int tWarID = -1;

            for(int i = 0; i < CFG.game.getWarsSize(); ++i) {
               if (CFG.game.getWar(i).WAR_TAG.equals(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.WAR_TAG)) {
                  tWarID = i;
                  break;
               }
            }

            try {
               if (tWarID >= 0) {
                  boolean everyoneAtPeace = true;

                  for(int i = 0; i < CFG.game.getWar(tWarID).getDefendersSize(); ++i) {
                     for(int j = 0; j < CFG.game.getWar(tWarID).getAggressorsSize(); ++j) {
                        if (CFG.game.getCivsAtWar(CFG.game.getWar(tWarID).getDefenderID(i).getCivID(), CFG.game.getWar(tWarID).getAggressorID(j).getCivID())) {
                           everyoneAtPeace = false;
                           i = CFG.game.getWar(tWarID).getDefendersSize();
                           break;
                        }
                     }
                  }

                  if (everyoneAtPeace) {
                     CFG.game.removeWarData(tWarID);
                  } else {
                     for(int i = CFG.game.getWar(tWarID).getDefendersSize() - 1; i >= 0; --i) {
                        boolean isAtPeace = true;

                        for(int j = 0; j < CFG.game.getWar(tWarID).getAggressorsSize(); ++j) {
                           if (CFG.game
                              .getCivsAtWar(CFG.game.getWar(tWarID).getDefenderID(i).getCivID(), CFG.game.getWar(tWarID).getAggressorID(j).getCivID())) {
                              isAtPeace = false;
                              break;
                           }
                        }

                        if (isAtPeace) {
                           CFG.game.getWar(tWarID).removeDefender(CFG.game.getWar(tWarID).getDefenderID(i).getCivID());
                        }
                     }

                     for(int i = CFG.game.getWar(tWarID).getAggressorsSize() - 1; i >= 0; --i) {
                        boolean isAtPeace = true;

                        for(int j = 0; j < CFG.game.getWar(tWarID).getDefendersSize(); ++j) {
                           if (CFG.game
                              .getCivsAtWar(CFG.game.getWar(tWarID).getDefenderID(j).getCivID(), CFG.game.getWar(tWarID).getAggressorID(i).getCivID())) {
                              isAtPeace = false;
                              break;
                           }
                        }

                        if (isAtPeace) {
                           CFG.game.getWar(tWarID).removeAggressor(CFG.game.getWar(tWarID).getAggressorID(i).getCivID());
                        }
                     }

                     if (CFG.game.getWar(tWarID).getDefendersSize() == 0 || CFG.game.getWar(tWarID).getAggressorsSize() == 0) {
                        CFG.game.removeWarData(tWarID);
                     }
                  }
               }
            } catch (IndexOutOfBoundsException var18) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var18);
               }
            }
         } catch (IndexOutOfBoundsException var19) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var19);
            }
         } catch (NullPointerException var20) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var20);
            }
         }

         CFG.game.lPeaceTreaties.remove(peaceID);
      }
   }

   protected static final void declinePeaceTreaty(int iCivID, String nTag) {
      int peaceID = CFG.game.getPeaceTreaty_GameDataID(nTag);

      for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); ++i) {
         if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).peaceTreatyAccepted) {
            CFG.game
               .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_PeaceTreaty_Rejected(iCivID));
         }
      }

      for(int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); ++i) {
         if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted) {
            CFG.game
               .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_PeaceTreaty_Rejected(iCivID));
         }
      }

      CFG.game.lPeaceTreaties.remove(peaceID);
   }

   protected static final boolean sendTradeRequest(int iToCivID, int iFromCivID, TradeRequest_GameData tradeRequest) {
      if (CFG.game.getCiv(iFromCivID).getDiplomacyPoints() >= 10) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TradeReuest(iFromCivID, tradeRequest));
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.TRADE_REQUEST));
         }

         return true;
      } else {
         return false;
      }
   }

   protected static final void acceptTradeRequest(int iCivID, int iFromCivID, TradeRequest_GameData tradeRequest) {
      if (tradeRequest.listLEFT.militaryAccess) {
         CFG.game.setMilitaryAccess(iCivID, iFromCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
      }

      if (tradeRequest.listRight.militaryAccess) {
         CFG.game.setMilitaryAccess(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
      }

      if (tradeRequest.listLEFT.iGold > 0) {
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() - (long)tradeRequest.listLEFT.iGold);
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + (long)tradeRequest.listLEFT.iGold);
      }

      if (tradeRequest.listLEFT.lProvinces.size() > 0) {
         for(int i = 0; i < tradeRequest.listLEFT.lProvinces.size(); ++i) {
            if (CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivID() == iFromCivID
               && CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getTrueOwnerOfProvince() == iFromCivID) {
               int tempArmy0 = CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getArmy(0);
               int tempCiv0 = CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivID();
               int tempArmyNewOwner = CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getArmyCivID(iCivID);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).updateArmy(0);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).setTrueOwnerOfProvince(iCivID);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).setCivID(iCivID, false);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).updateArmy(tempCiv0, tempArmy0);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).updateArmy(iCivID, tempArmyNewOwner);
               List<Integer> tempCivsLostAccess = new ArrayList<>();

               for(int j = 0; j < CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivsSize(); ++j) {
                  tempCivsLostAccess.add(CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivID(j));
               }

               for(int j = 0; j < tempCivsLostAccess.size(); ++j) {
                  if (CFG.game.getCiv(tempCivsLostAccess.get(j)).getPuppetOfCivID() != iCivID
                     && CFG.game.getCiv(iCivID).getPuppetOfCivID() != tempCivsLostAccess.get(j)
                     && (
                        CFG.game.getCiv(tempCivsLostAccess.get(j)).getAllianceID() <= 0
                           || CFG.game.getCiv(tempCivsLostAccess.get(j)).getAllianceID() != CFG.game.getCiv(iCivID).getAllianceID()
                     )
                     && CFG.game.getMilitaryAccess(tempCivsLostAccess.get(j), iCivID) <= 0) {
                     CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivsLostAccess.get(j), tradeRequest.listLEFT.lProvinces.get(i));
                  }
               }

               if (!CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getIsCapital()) {
                  CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).removeCapitalCityIcon();
               }

               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCore().removeCore(tradeRequest.iCivLEFT);
            }
         }

         CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
         CFG.game.buildCivilizationsRegions_TextOver(iCivID);
      }

      if (tradeRequest.listLEFT.iDeclarWarOnCivID > 0) {
         CFG.game.declareWar(iFromCivID, tradeRequest.listLEFT.iDeclarWarOnCivID, false);
      }

      if (tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
         CFG.game.declareWar(iFromCivID, tradeRequest.listLEFT.iFormCoalitionAgainst, false);
         CFG.game.declareWar(iCivID, tradeRequest.listLEFT.iFormCoalitionAgainst, false);
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iCivID, iFromCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
      }

      if (tradeRequest.listLEFT.defensivePact) {
         CFG.game.setDefensivePact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedDefensivePact(iCivID, iFromCivID));
      }

      if (tradeRequest.listLEFT.nonAggressionPact) {
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedNonAggressionPact(iCivID, iFromCivID));
      }

      if (tradeRequest.listLEFT.proclaimIndependence) {
         CFG.game.setGuarantee(iFromCivID, iCivID, 100);
         CFG.historyManager.addHistoryLog(new HistoryLog_Guarantee(iFromCivID, iCivID));
      }

      if (tradeRequest.listRight.iGold > 0) {
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() - (long)tradeRequest.listRight.iGold);
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() + (long)tradeRequest.listRight.iGold);
      }

      if (tradeRequest.listRight.lProvinces.size() > 0) {
         for(int i = 0; i < tradeRequest.listRight.lProvinces.size(); ++i) {
            if (CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getCivID() == iCivID
               && CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getTrueOwnerOfProvince() == iCivID) {
               int tempArmy0 = CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getArmy(0);
               int tempCiv0 = CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getCivID();
               int tempArmyNewOwner = CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getArmyCivID(iCivID);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).updateArmy(0);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).setTrueOwnerOfProvince(iFromCivID);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).setCivID(iFromCivID, false);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).updateArmy(tempCiv0, tempArmy0);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).updateArmy(iCivID, tempArmyNewOwner);
               List<Integer> tempCivsLostAccess = new ArrayList<>();

               for(int j = 0; j < CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getCivsSize(); ++j) {
                  tempCivsLostAccess.add(CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getCivID(j));
               }

               for(int j = 0; j < tempCivsLostAccess.size(); ++j) {
                  if (CFG.game.getCiv(tempCivsLostAccess.get(j)).getPuppetOfCivID() != iFromCivID
                     && CFG.game.getCiv(iFromCivID).getPuppetOfCivID() != tempCivsLostAccess.get(j)
                     && (
                        CFG.game.getCiv(tempCivsLostAccess.get(j)).getAllianceID() <= 0
                           || CFG.game.getCiv(tempCivsLostAccess.get(j)).getAllianceID() != CFG.game.getCiv(iFromCivID).getAllianceID()
                     )
                     && CFG.game.getMilitaryAccess(tempCivsLostAccess.get(j), iFromCivID) <= 0) {
                     CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivsLostAccess.get(j), tradeRequest.listRight.lProvinces.get(i));
                  }
               }

               if (!CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getIsCapital()) {
                  CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).removeCapitalCityIcon();
               }

               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(i)).getCore().removeCore(tradeRequest.iCivRIGHT);
            }
         }

         CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
         CFG.game.buildCivilizationsRegions_TextOver(iCivID);
      }

      if (tradeRequest.listRight.iDeclarWarOnCivID > 0) {
         CFG.game.declareWar(iCivID, tradeRequest.listRight.iDeclarWarOnCivID, false);
      }

      if (tradeRequest.listRight.iFormCoalitionAgainst > 0) {
         CFG.game.declareWar(iFromCivID, tradeRequest.listRight.iFormCoalitionAgainst, false);
         CFG.game.declareWar(iCivID, tradeRequest.listRight.iFormCoalitionAgainst, false);
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iCivID, iFromCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
      }

      if (tradeRequest.listRight.defensivePact) {
         CFG.game.setDefensivePact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedDefensivePact(iFromCivID, iCivID));
      }

      if (tradeRequest.listRight.nonAggressionPact) {
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedNonAggressionPact(iCivID, iFromCivID));
      }

      if (tradeRequest.listRight.proclaimIndependence) {
         CFG.game.setGuarantee(iCivID, iFromCivID, 100);
         CFG.historyManager.addHistoryLog(new HistoryLog_Guarantee(iFromCivID, iCivID));
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TradeReuest_Accepted(iCivID));
   }

   protected static final void declineTradeRequest(int iCivID, int iFromCivID, TradeRequest_GameData tradeRequest) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TradeReuest_Denied(iCivID));
   }

   protected static String getTradeRequest_LikelihoodOfSuccess_Text() {
      return !CFG.game.getCiv(CFG.tradeRequest.iCivRIGHT).getControlledByPlayer() ? CFG.langManager.get("Medium") : CFG.langManager.get("NoData");
   }

   protected static void runRelationsOutDated() {
      for(int i = Game_Calendar.TURN_ID % 6; i < CFG.game.getCivsSize(); i += 6) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            for(int j = 1; j < CFG.game.getCivsSize(); ++j) {
               if (CFG.game.getCiv(j).getNumOfProvinces() > 0) {
                  if (CFG.game.getCivRelation_OfCivB(i, j) > 15.0F) {
                     CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) - 0.625F);
                  } else if (CFG.game.getCivRelation_OfCivB(i, j) < -20.0F) {
                     CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) + 0.535F);
                  }
               }
            }
         }
      }
   }

   protected static final void buildFriendlyCivs() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).clearFreidnlyCivs();
      }

      for(int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
         for(int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
            if (CFG.game.getCivRelation_OfCivB(i, j) > 44.0F) {
               CFG.game.getCiv(i).addFriendlyCiv(j);
            } else if (CFG.game.getCivRelation_OfCivB(i, j) < -25.0F) {
               CFG.game.getCiv(i).addHatedCiv(j);
            }

            if (CFG.game.getCivRelation_OfCivB(j, i) > 44.0F) {
               CFG.game.getCiv(j).addFriendlyCiv(i);
            } else if (CFG.game.getCivRelation_OfCivB(j, i) < -25.0F) {
               CFG.game.getCiv(j).addHatedCiv(i);
            }
         }
      }
   }

   protected static final void updatePlayersFriendlyCivs() {
      if (!CFG.SPECTATOR_MODE) {
         try {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
                  for(int z = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFriendlyCivsSize() - 1; z >= 0; --z) {
                     if (CFG.game
                           .getCivRelation_OfCivB(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFriendlyCiv(z).iCivID, CFG.game.getPlayer(i).getCivID())
                        < 39.0F) {
                        CFG.game
                           .getCiv(CFG.game.getPlayer(i).getCivID())
                           .removeFriendlyCiv(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFriendlyCiv(z).iCivID);
                     }
                  }
               }
            }
         } catch (IndexOutOfBoundsException var2) {
         }
      }
   }

   protected static final void checkCivsHatedCivilizations_IfStillExsits() {
      if (Game_Calendar.TURN_ID % 9 == 0) {
         for(int i = 1 + Game_Calendar.TURN_ID % 2; i < CFG.game.getCivsSize(); i += 2) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
               for(int z = CFG.game.getCiv(i).getHatedCivsSize() - 1; z >= 0; --z) {
                  if (CFG.game.getCiv(CFG.game.getCiv(i).getHatedCiv(z).iCivID).getNumOfProvinces() == 0) {
                     CFG.game.getCiv(i).removeHatedCiv(CFG.game.getCiv(i).getHatedCiv(z).iCivID);
                  }
               }
            }
         }
      }
   }

   protected static final void updateFriendlyCiv(int nCivA, int nCivB) {
      if (CFG.game.getCivRelation_OfCivB(nCivA, nCivB) > 44.0F) {
         if (CFG.game.getCiv(nCivB).addFriendlyCiv(nCivA)) {
            CFG.game.getCiv(nCivA).removeHatedCiv(nCivB);
         }
      } else if (CFG.game.getCivRelation_OfCivB(nCivA, nCivB) < -25.0F && CFG.game.getCiv(nCivA).addHatedCiv(nCivB)) {
         CFG.game.getCiv(nCivB).removeFriendlyCiv(nCivA);
      }

      if (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) > 44.0F) {
         if (CFG.game.getCiv(nCivA).addFriendlyCiv(nCivB)) {
            CFG.game.getCiv(nCivB).removeHatedCiv(nCivA);
         }
      } else if (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) < -25.0F && CFG.game.getCiv(nCivB).addHatedCiv(nCivA)) {
         CFG.game.getCiv(nCivA).removeFriendlyCiv(nCivB);
      }
   }

   protected static boolean improveRelation(int iCivA, int iCivB) {
      if (CFG.game.getCiv(iCivA).getNumOfProvinces() == 0) {
         return false;
      } else if (CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().isEmassyClosed(iCivA)) {
         return false;
      } else if (CFG.game.getCiv(iCivA).getDiplomacyPoints() >= 5 && !CFG.game.getCivsAtWar(iCivA, iCivB)) {
         float out = getImproveRelation(iCivA, iCivB);
         float out2 = getImproveRelation(iCivB, iCivA) * 0.9175F;
         boolean updateFriendlyRelation = false;
         if (CFG.game.getCivRelation_OfCivB(iCivA, iCivB) < 44.0F) {
            updateFriendlyRelation = true;
         }

         CFG.game.setCivRelation_OfCivB(iCivA, iCivB, CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + out2);
         CFG.game.setCivRelation_OfCivB(iCivB, iCivA, CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + out);
         if (updateFriendlyRelation) {
            updateFriendlyCiv(iCivA, iCivB);
         }

         return true;
      } else {
         return false;
      }
   }

   protected static float getImproveRelation(int iCivA, int iCivB) {
      float out = 0.8425F + (float)CFG.oR.nextInt(121) / 100.0F;
      return 0.125F
         + out
            * (Math.min(CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + 100.0F, 145.0F) / 200.0F)
            * Math.min(Math.max(0.325F, (float)CFG.game.getCiv(iCivA).getRankScore() / (float)CFG.game.getCiv(iCivB).getRankScore()), 1.0F);
   }

   protected static boolean decreaseRelation(int iCivA, int iCivB, int nNumOfTurns) {
      if (CFG.game.getCiv(iCivA).getDiplomacyPoints() < 2) {
         return false;
      } else {
         CFG.game.getCiv(iCivA).setDiplomacyPoints(CFG.game.getCiv(iCivA).getDiplomacyPoints() - 2);
         CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Insult(iCivA));
         CFG.game.getCiv(iCivA).getCivilization_Diplomacy_GameData().removeImproveRelations_WithCivID(iCivA, iCivB);
         CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().removeImproveRelations_WithCivID(iCivB, iCivA);
         CFG.game.getCiv(iCivA).getCivilization_Diplomacy_GameData().addEmbeassyClosed(new Civilization_ClosedEmbassy(iCivB, nNumOfTurns));
         CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().addEmbeassyClosed(new Civilization_ClosedEmbassy(iCivA, nNumOfTurns));
         float out = getDecreaseRelation(iCivA, iCivB);
         CFG.game
            .setCivRelation_OfCivB(
               iCivA,
               iCivB,
               CFG.game.getCivRelation_OfCivB(iCivA, iCivB) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + out
            );
         out *= 0.415F;
         CFG.game
            .setCivRelation_OfCivB(
               iCivB,
               iCivA,
               CFG.game.getCivRelation_OfCivB(iCivB, iCivA) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + out
            );
         worldRecations((int)Math.min(30.0F, CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + 100.0F) / 3, iCivA, iCivB);
         updateFriendlyCiv(iCivA, iCivB);
         return true;
      }
   }

   protected static float getDecreaseRelation(int iCivA, int iCivB) {
      float out = -(26.25F + (float)CFG.oR.nextInt(27) / 100.0F);
      return out * 0.4F + out * 0.725F * ((CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + 100.0F) / 200.0F);
   }

   protected static final void liberateAVassal(int iLord, int iVassal) {
      if (CFG.game.getCiv(iVassal).getPuppetOfCivID() == iLord) {
         CFG.game.getCiv(iVassal).setPuppetOfCivID(iVassal);
         if (CFG.game.getMilitaryAccess(iLord, iVassal) <= 0) {
            CFG.gameAction.accessLost_UpdateArmies(iVassal, iLord);
         }

         if (CFG.game.getMilitaryAccess(iVassal, iLord) <= 0) {
            CFG.gameAction.accessLost_UpdateArmies(iLord, iVassal);
         }

         if (CFG.FOG_OF_WAR > 0) {
            if (CFG.game.getPlayerID_ByCivID(iLord) >= 0) {
               CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iLord));
            }

            if (CFG.game.getPlayerID_ByCivID(iVassal) >= 0) {
               CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iVassal));
            }
         }

         CFG.historyManager.addHistoryLog(new HistoryLog_IsNotVassal(iLord, iVassal));
         CFG.game.getCiv(iVassal).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Liberation(iLord));
         if (!CFG.game.getCiv(iLord).getControlledByPlayer()) {
            CFG.game.getCiv(iLord).addSentMessages(new Civilization_SentMessages(iVassal, Message_Type.LIBERATION_OF_VASSAL));
         }
      }
   }

   protected static final void declarationOfIndependeceByVassal(int iLord, int iVassal) {
      if (CFG.game.getCivTruce(iLord, iVassal) <= 0) {
         if (!CFG.NO_LIBERITY) {
            if (CFG.game.getCiv(iVassal).getPuppetOfCivID() == iLord) {
               CFG.game.getCiv(iVassal).setPuppetOfCivID(iVassal);
               CFG.game.getCiv(iVassal).setVassalLiberityDesire(0.0F);
               if (CFG.game.getMilitaryAccess(iLord, iVassal) <= 0) {
                  CFG.gameAction.accessLost_UpdateArmies(iVassal, iLord);
               }

               if (CFG.game.getMilitaryAccess(iVassal, iLord) <= 0) {
                  CFG.gameAction.accessLost_UpdateArmies(iLord, iVassal);
               }

               if (CFG.FOG_OF_WAR > 0) {
                  if (CFG.game.getPlayerID_ByCivID(iLord) >= 0) {
                     CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iLord));
                  }

                  if (CFG.game.getPlayerID_ByCivID(iVassal) >= 0) {
                     CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iVassal));
                  }
               }

               CFG.historyManager.addHistoryLog(new HistoryLog_IsNotVassal(iLord, iVassal));
               CFG.game.getCiv(iVassal).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Liberation(iLord));
               CFG.game.getCiv(iLord).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DeclarationOfIndependence_ByVassal(iVassal));
               if (!CFG.game.getCiv(iLord).getControlledByPlayer()) {
                  CFG.game.getCiv(iLord).addSentMessages(new Civilization_SentMessages(iVassal, Message_Type.LIBERATION_OF_VASSAL));
               }
            }
         }
      }
   }

   protected static final boolean canTakeMoreLoans(int nCivID) {
      return CFG.game.getCiv(nCivID).getLoansSize() < 5;
   }

   protected static final void takeLoan(int iCivID, int iGold, int iDuration) {
      if (canTakeMoreLoans(iCivID) && iGold > 0 && iDuration >= 5 && iDuration <= 30) {
         if (!canTakeMoreLoans(iCivID)) {
            return;
         }

         if (iGold > takeLoan_MaxValue(iCivID)) {
            iGold = takeLoan_MaxValue(iCivID);
         }

         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + (long)iGold);
         CFG.game
            .getCiv(iCivID)
            .addLoan(
               (int)Math.max(
                  Math.ceil((double)(((float)iGold + (float)iGold * takeLoan_InterestRate(iCivID, iGold, iDuration) / 100.0F) / (float)iDuration)), 1.0
               ),
               iDuration
            );
         CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - 6);
      }
   }

   protected static final int takeLoan_MinValue() {
      return 30;
   }

   protected static final int takeLoan_MaxValue(int iCivID) {
      return (int)Math.max((float)(CFG.game.getCiv(iCivID).iIncomeTaxation + CFG.game.getCiv(iCivID).iIncomeProduction) * 0.6F, 35.0F);
   }

   protected static final float takeLoan_InterestRate(int iCivID, int iGold, int iDuration) {
      return iGold == 0
         ? 0.0F
         : 7.25F
            + (float)CFG.game.getCiv(iCivID).getLoansSize() * 0.7F
            + (8.0F + (float)CFG.game.getCiv(iCivID).getLoansSize() / 4.0F) * (float)(iDuration - 5) / 25.0F;
   }

   protected static final void repayLoan(int iCivID, int iLoanID) {
      try {
         CFG.game
            .getCiv(iCivID)
            .setMoney(
               CFG.game.getCiv(iCivID).getMoney()
                  - (long)(CFG.game.getCiv(iCivID).getLoan(iLoanID).iTurnsLeft * CFG.game.getCiv(iCivID).getLoan(iLoanID).iGoldPerTurn)
            );
         CFG.game.getCiv(iCivID).removeLoan(iLoanID);
      } catch (IndexOutOfBoundsException var3) {
      }
   }

   protected static final float plunderEfficiency(int nCivID, int nProvinceID, int nArmy) {
      return Math.min(1.0F, (float)nArmy / plunderEfficiency_RequiredMAX(nCivID, nProvinceID));
   }

   protected static final float plunderEfficiency_RequiredMAX(int nCivID, int nProvinceID) {
      return (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
         * (0.1375F - 0.035F * Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel(), 1.0F));
   }

   protected static final int plunderProvinceIncome(int nCivID, int nProvinceID, int nArmy) {
      return (int)(CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(nProvinceID) + CFG.game_NextTurnUpdate.getProvinceIncome_Production(nProvinceID));
   }

   protected static final int plunderTreasuryIncome(int nCivID, int nProvinceID, int nArmy) {
      return (int)(
         (float)plunderProvinceIncome(nCivID, nProvinceID, nArmy)
            * 1.45F
            * plunderEfficiency(nCivID, nProvinceID, nArmy)
            * (1.0F - 0.625F * CFG.game.getProvince(nProvinceID).getRevolutionaryRisk())
      );
   }

   protected static final float plunder_LossesEconomy_Perc(int nCivID, int nProvinceID, int nArmy) {
      return (0.0425F + (float)CFG.oR.nextInt(525) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy);
   }

   protected static final float plunder_LossesDevelopment_Perc(int nCivID, int nProvinceID, int nArmy) {
      return (0.0875F + (float)CFG.oR.nextInt(625) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy);
   }

   protected static final float plunder_Happiness(int nCivID, int nProvinceID, int nArmy) {
      return (0.05728F + (float)CFG.oR.nextInt(426) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy);
   }

   protected static final float plunder_RevolutionaryRisk(int nCivID, int nProvinceID, int nArmy) {
      return Math.max((0.011861F + (float)CFG.oR.nextInt(268) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy), 0.034378F);
   }

   protected static final int plunder_Population(int nCivID, int nProvinceID, int nArmy) {
      return (int)Math.min(
         (float)nArmy * (0.04864F + (float)CFG.oR.nextInt(412) / 10000.0F),
         (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.0786F
      );
   }

   protected static final void plunderProvince(int iCivID, int nProvinceID, int nArmy) {
      if (CFG.game.getCiv(iCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_PLUNDER) {
         if (nProvinceID >= 0 && CFG.game.getProvince(nProvinceID).isOccupied() && !CFG.game.getProvince(nProvinceID).getSeaProvince()) {
            int currPlunderArmy = 0;

            for(int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsPlunderSize(); ++i) {
               if (CFG.game.getCiv(iCivID).getMoveUnits_Plunder(i).getFromProvinceID() == nProvinceID) {
                  currPlunderArmy = CFG.game.getCiv(iCivID).getMoveUnits_Plunder(i).getNumOfUnits();
                  if (nArmy == 0) {
                     CFG.game.getCiv(iCivID).removePlunder(i);
                     CFG.game.getProvince(nProvinceID).updateArmy(iCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy);
                     if (currPlunderArmy > 0) {
                        CFG.game
                           .getCiv(iCivID)
                           .setMovePoints(
                              CFG.game.getCiv(iCivID).getMovePoints()
                                 + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_PLUNDER
                           );
                     }

                     return;
                  }
                  break;
               }
            }

            if (nArmy > CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy) {
               nArmy = CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy;
            }

            if (nArmy > 0) {
               CFG.game.getCiv(iCivID).newPlunder(nProvinceID, nArmy);
               CFG.game.getProvince(nProvinceID).updateArmy(iCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy - nArmy);
               if (currPlunderArmy == 0) {
                  CFG.game
                     .getCiv(iCivID)
                     .setMovePoints(
                        CFG.game.getCiv(iCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_PLUNDER
                     );
               }
            }
         }
      }
   }

   protected static final void plunder(int iCivID, int nProvinceID, int nArmy) {
      if (CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() != iCivID) {
         int nTreasury = plunderTreasuryIncome(iCivID, nProvinceID, nArmy);
         float nHappiness = plunder_Happiness(iCivID, nProvinceID, nArmy);
         int nEconomy = (int)(
            4.0 + Math.ceil((double)((float)CFG.game.getProvince(nProvinceID).getEconomy() * plunder_LossesEconomy_Perc(iCivID, nProvinceID, nArmy)))
         );
         float nDevelopment = CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * plunder_LossesDevelopment_Perc(iCivID, nProvinceID, nArmy);
         float fRevolutionary = plunder_RevolutionaryRisk(iCivID, nProvinceID, nArmy);
         int nPopulation = plunder_Population(iCivID, nProvinceID, nArmy);
         int tempPopulationBefore = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
         int tempEconomyBefore = CFG.game.getProvince(nProvinceID).getEconomy();
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + (long)nTreasury);
         CFG.game.getProvince(nProvinceID).setEconomy(CFG.game.getProvince(nProvinceID).getEconomy() - nEconomy);
         CFG.game.getProvince(nProvinceID).setDevelopmentLevel(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - nDevelopment);
         CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() - nHappiness);
         CFG.game
            .getProvince(nProvinceID)
            .setRevolutionaryRisk(
               CFG.game.getProvince(nProvinceID).getRevolutionaryRisk()
                  + CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID) * fRevolutionary
            );
         CFG.gameAction.updatePopulationLosses(nProvinceID, nPopulation);
         int tempWarID = CFG.game.getWarID(iCivID, CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince());
         if (tempWarID >= 0) {
            CFG.game
               .updateWarStatistics(
                  tempWarID,
                  iCivID,
                  CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince(),
                  Math.max(tempPopulationBefore - CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation(), 0),
                  Math.max(tempEconomyBefore - CFG.game.getProvince(nProvinceID).getEconomy(), 0)
               );
         }

         CFG.game
            .getCiv(iCivID)
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message_Plunder(iCivID, nProvinceID, nTreasury, nEconomy, nDevelopment, nHappiness, nPopulation));
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()).getControlledByPlayer()) {
            CFG.game
               .getCiv(CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince())
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_Plunder_Plundred(iCivID, nProvinceID, nEconomy, nDevelopment, nHappiness, nPopulation));
         }
      }
   }

   protected static final void leaveAlliance(int nCivID) {
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getCiv(nCivID).getAllianceID() < CFG.game.getAlliancesSize()) {
         int allianceID = CFG.game.getCiv(nCivID).getAllianceID();
         CFG.game.getAlliance(allianceID).removeCivilization(nCivID);
         CFG.game.getCiv(nCivID).setAllianceID(0);
         if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
            int tPlayerID = CFG.game.getPlayerID_ByCivID(nCivID);

            for(int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); ++i) {
               if (CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getControlledByPlayer()) {
                  int tPlayerID2 = CFG.game.getPlayerID_ByCivID(CFG.game.getAlliance(allianceID).getCivilization(i));
                  if (tPlayerID2 >= 0) {
                     for(int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).updateFogOfWar(tPlayerID2);
                     }

                     for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); ++j) {
                        for(int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(j).iCivID).getNumOfProvinces(); ++k) {
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(j).iCivID).getProvinceID(j))
                              .updateFogOfWar(tPlayerID2);
                        }
                     }
                  }
               }

               if (tPlayerID >= 0) {
                  for(int j = 0; j < CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getNumOfProvinces(); ++j) {
                     CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getProvinceID(j)).updateFogOfWar(tPlayerID);
                  }

                  for(int j = 0; j < CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).civGameData.lVassals.size(); ++j) {
                     for(int k = 0;
                        k
                           < CFG.game
                              .getCiv(CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).civGameData.lVassals.get(j).iCivID)
                              .getNumOfProvinces();
                        ++k
                     ) {
                        CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).civGameData.lVassals.get(j).iCivID)
                                 .getProvinceID(j)
                           )
                           .updateFogOfWar(tPlayerID);
                     }
                  }
               }
            }
         }

         for(int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); ++i) {
            int out = -10;
            CFG.game
               .setCivRelation_OfCivB(
                  nCivID,
                  CFG.game.getAlliance(allianceID).getCivilization(i),
                  CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getAlliance(allianceID).getCivilization(i)) > -100.0F
                        && CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getAlliance(allianceID).getCivilization(i)) + (float)out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getAlliance(allianceID).getCivilization(i)) + (float)out
               );
            CFG.game
               .setCivRelation_OfCivB(
                  CFG.game.getAlliance(allianceID).getCivilization(i),
                  nCivID,
                  CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(allianceID).getCivilization(i), nCivID) > -100.0F
                        && CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(allianceID).getCivilization(i), nCivID) + (float)out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(allianceID).getCivilization(i), nCivID) + (float)out
               );
            CFG.game
               .getCiv(CFG.game.getAlliance(allianceID).getCivilization(i))
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_LeftAlliance(nCivID, allianceID));
         }

         CFG.historyManager.addHistoryLog(new HistoryLog_LeavesAlliance(nCivID, allianceID));
      }
   }

   protected static final void kickFromAlliance(int nCivID, int byCivID) {
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0
         && CFG.game.getCiv(nCivID).getAllianceID() < CFG.game.getAlliancesSize()
         && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(byCivID).getAllianceID()) {
         int allianceID = CFG.game.getCiv(nCivID).getAllianceID();
         CFG.game.getAlliance(allianceID).removeCivilization(nCivID);
         CFG.game.getCiv(nCivID).setAllianceID(0);

         for(int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getControlledByPlayer()) {
               if (CFG.game.getAlliance(allianceID).getCivilization(i) == nCivID) {
                  int tPlayerID = CFG.game.getPlayerID_ByCivID(nCivID);
                  if (tPlayerID >= 0) {
                     for(int j = 0; j < CFG.game.getCiv(byCivID).getNumOfProvinces(); ++j) {
                        CFG.game.getProvince(CFG.game.getCiv(byCivID).getProvinceID(j)).updateFogOfWar(tPlayerID);
                     }

                     for(int j = 0; j < CFG.game.getCiv(byCivID).civGameData.lVassals.size(); ++j) {
                        for(int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(byCivID).civGameData.lVassals.get(j).iCivID).getNumOfProvinces(); ++k) {
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getCiv(byCivID).civGameData.lVassals.get(j).iCivID).getProvinceID(j))
                              .updateFogOfWar(tPlayerID);
                        }
                     }
                  }
               }

               if (CFG.game.getAlliance(allianceID).getCivilization(i) == nCivID) {
                  int tPlayerID = CFG.game.getPlayerID_ByCivID(nCivID);
                  if (tPlayerID >= 0) {
                     for(int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).updateFogOfWar(tPlayerID);
                     }

                     for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); ++j) {
                        for(int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(j).iCivID).getNumOfProvinces(); ++k) {
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(j).iCivID).getProvinceID(j))
                              .updateFogOfWar(tPlayerID);
                        }
                     }
                  }
               }
            }
         }

         for(int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getControlledByPlayer()) {
               int tPlayerID2 = CFG.game.getPlayerID_ByCivID(CFG.game.getAlliance(allianceID).getCivilization(i));
               if (tPlayerID2 >= 0) {
                  for(int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).updateFogOfWar(tPlayerID2);
                  }

                  for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); ++j) {
                     for(int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(j).iCivID).getNumOfProvinces(); ++k) {
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(j).iCivID).getProvinceID(j))
                           .updateFogOfWar(tPlayerID2);
                     }
                  }
               }
            }
         }

         int out = -25;
         CFG.game
            .setCivRelation_OfCivB(
               nCivID,
               byCivID,
               CFG.game.getCivRelation_OfCivB(nCivID, byCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(nCivID, byCivID) + (float)out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(nCivID, byCivID) + (float)out
            );
         CFG.game
            .setCivRelation_OfCivB(
               byCivID,
               nCivID,
               CFG.game.getCivRelation_OfCivB(byCivID, nCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(byCivID, nCivID) + (float)out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(byCivID, nCivID) + (float)out
            );

         for(int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); ++i) {
            CFG.game
               .getCiv(CFG.game.getAlliance(allianceID).getCivilization(i))
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_LeftAlliance(nCivID, allianceID));
         }

         CFG.historyManager.addHistoryLog(new HistoryLog_LeavesAlliance(nCivID, allianceID));
      }
   }

   protected static final boolean changeGovernmentType(int nCivID, int toGovType) {
      if (CFG.game.getCiv(nCivID).getIdeologyID() == toGovType) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMoney() < (long)Ideologies_Manager.getChangeGovernmentCost(nCivID)) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() < 22) {
         return false;
      } else {
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)Ideologies_Manager.getChangeGovernmentCost(nCivID));
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 22);
         CFG.game
            .updateCivilizationIdeology(
               nCivID, CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(toGovType).getExtraTag()
            );
         if (CFG.isDesktop() && CFG.game.getCiv(nCivID).getControlledByPlayer() && AoCGame.steamGame != null) {
            AoCGame.steamGame.checkGovermentAchievement(toGovType);
         }

         for(int i = 0; i < CFG.game.getCiv(nCivID).getCivRegionsSize(); ++i) {
            CFG.game.getCiv(nCivID).getCivRegion(i).buildScaleOfText();
         }

         return true;
      }
   }
}
