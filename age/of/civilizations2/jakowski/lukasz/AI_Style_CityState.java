package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Style_CityState extends AI_Style {
   protected AI_Style_CityState() {
      this.TAG = "CITYSTATE";
      this.PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.08F;
      this.PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 19;
      this.PERSONALITY_MIN_HAPPINESS_DEFAULT = 77;
      this.PERSONALITY_MIN_HAPPINESS_RANDOM = 20;
      this.PERSONALITY_FORGIVNESS_DEFAULT = 0.4F;
      this.PERSONALITY_FORGIVNESS_RANDOM = 10;
   }

   @Override
   protected void turnOrders(int nCivID) {
      this.shouldChangeTypeOfGoverment(nCivID);
      super.turnOrders(nCivID);
   }

   protected final void sendGiftToFriendlyCiv(int nCivID) {
      if (Game_Calendar.TURN_ID >= CFG.game.getCiv(nCivID).civGameData.iSendGift_LastTurnID) {
         if (CFG.game.getCiv(nCivID).isAtWar()) {
            CFG.game.getCiv(nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 10 + CFG.oR.nextInt(15);
         } else if (CFG.game.getCiv(nCivID).getMoney() <= 0L || CFG.game.getCiv(nCivID).iBudget <= 0) {
            CFG.game.getCiv(nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(8);
         } else if (CFG.game.getCiv(nCivID).getFriendlyCivsSize() > 0) {
            DiplomacyManager.sendGift(
               CFG.game.getCiv(nCivID).getFriendlyCiv(CFG.oR.nextInt(CFG.game.getCiv(nCivID).getFriendlyCivsSize())).iCivID,
               nCivID,
               (int)Math.ceil((double)((float)CFG.game.getCiv(nCivID).iBudget * (0.05F + (float)CFG.oR.nextInt(75) / 1000.0F)))
            );
            CFG.game.getCiv(nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 5 + CFG.oR.nextInt(8);
         } else {
            CFG.game.getCiv(nCivID).civGameData.iSendGift_LastTurnID = Game_Calendar.TURN_ID + 10 + CFG.oR.nextInt(10);
         }
      }
   }

   protected final void shouldChangeTypeOfGoverment(int nCivID) {
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 5 && CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment == null) {
         List<Boolean> canChaneTo = CFG.ideologiesManager.canChangeToIdeology(nCivID);
         List<Integer> possibleIdeologiesIDs = new ArrayList<>();

         for(int i = 0; i < canChaneTo.size(); ++i) {
            if (canChaneTo.get(i)) {
               possibleIdeologiesIDs.add(i);
            }
         }

         if (possibleIdeologiesIDs.size() > 0) {
            CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment = new Civ_Mission_ChangeTypeOfGoverment(
               possibleIdeologiesIDs.get(CFG.oR.nextInt(possibleIdeologiesIDs.size())), nCivID
            );
         }
      } else {
         this.sendGiftToFriendlyCiv(nCivID);
      }
   }

   @Override
   protected void buildStartingBuildings(int nCivID) {
      super.buildStartingBuildings(nCivID);

      try {
         if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(1) * 0.92F) {
               CFG.game
                  .getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                  .setLevelOfFarm(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfFarm(), 1));
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getLibrary_TechLevel(1) * 1.08F) {
               CFG.game
                  .getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                  .setLevelOfLibrary(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfLibrary(), 1));
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(2) * 1.08F) {
               CFG.game
                  .getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                  .setLevelOfLibrary(Math.max(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfFort(), 2));
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }
}
