package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class Turn_Actions extends Thread {
   @Override
   public void run() {
      Gdx.app.log("Turn_Actions", "Turn_Actions..., fontMain" + CFG.fontMain.getData().scaleY);

      try {
         CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setText(CFG.langManager.get("Next"));
         if (CFG.isDesktop()) {
            runRevolts();
         }

         Gdx.app.log("Turn_Actions", "Turn_Actions 000000, fontMain" + CFG.fontMain.getData().scaleY);
         if (!CFG.SPECTATOR_MODE) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               try {
                  CFG.game.getPlayer(i).iTurnPopulation = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).countPopulation_WithoutOccupied();
                  CFG.game.getPlayer(i).iTurnEconomy = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).countEconomy_WithoutOccupied();
               } catch (IndexOutOfBoundsException var14) {
                  CFG.game.getPlayer(i).iTurnPopulation = 0;
                  CFG.game.getPlayer(i).iTurnEconomy = 0;
               }
            }
         }

         Gdx.app.log("Turn_Actions", "Turn_Actions 11111, fontMain" + CFG.fontMain.getData().scaleY);
         if (CFG.isDesktop()) {
            for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
               CFG.game.getProvince(i).saveProvinceData.turnChange_Population = CFG.game.getProvince(i).getPopulationData().getPopulation();
               CFG.game.getProvince(i).saveProvinceData.turnChange_Economy = CFG.game.getProvince(i).getEconomy();
               CFG.game.getProvince(i).saveProvinceData.turnChange_Development = CFG.game.getProvince(i).getDevelopmentLevel();
               CFG.game.getProvince(i).saveProvinceData.turnChange_Happiness = CFG.game.getProvince(i).getHappiness();
               CFG.game.getProvince(i).saveProvinceData.turnChange_Stability = CFG.game.getProvince(i).getProvinceStability();
               CFG.game.getProvince(i).saveProvinceData.turnChange_RevRisk = CFG.game.getProvince(i).getRevolutionaryRisk();
            }
         }

         Gdx.app.log("Turn_Actions", "Turn_Actions 222, fontMain" + CFG.fontMain.getData().scaleY);
         Gdx.app.log("Turn_Actions", "Turn_Actions 3333, fontMain" + CFG.fontMain.getData().scaleY);
         Gdx.app.log("Turn_Actions", "Turn_Actions 44444, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.menuManager.updateBuildProvinceHoverInformation();
         CFG.gameAction.resetCurrentMoveUnits();
         Gdx.app.log("Turn_Actions", "Turn_Actions 55555, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.menuManager.setVisibleInGame_Messages(false);
         Gdx.app.log("Turn_Actions", "Turn_Actions 6666, fontMain" + CFG.fontMain.getData().scaleY);
         if ((!RTS.isEnabled() || RTS.PAUSE) && CFG.gameAction.showNextPlayerTurnView()) {
            CFG.map.getMapBG().updateWorldMap_Shaders();
            Game_Render.updateRenderer();
            if (CFG.FOG_OF_WAR == 2) {
               CFG.game.enableDrawCivlizationsRegions_Player(CFG.PLAYER_TURNID);
            } else {
               CFG.game.enableDrawCivlizationsRegions_Players();
            }
         }

         Gdx.app.log("Turn_Actions", "Turn_Actions 7777, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.game.clearMoveUnits_JustDraw_AnotherArmies();
         Gdx.app.log("Turn_Actions", "Turn_Actions 8888, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.map.getMapTouchManager().update_ExtraAction();
         Gdx.app.log("Turn_Actions", "Turn_Actions 9999, fontMain" + CFG.fontMain.getData().scaleY);
         TechnologyManager.updateCivs_ResearchProgress();
         Gdx.app.log("Turn_Actions", "Turn_Actions 10, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.oAI.setLoadingTurnActionsOfCivID(0);
         CFG.oAI.buildAIData();

         try {
            CFG.oAI.turnOrders();
         } catch (Exception var13) {
            var13.printStackTrace();
         }

         Gdx.app.log("Turn_Actions", "Turn_Actions 11, fontMain" + CFG.fontMain.getData().scaleY);
         Gdx.app.log("Turn_Actions", "RETURN ARMY..");

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            try {
               for(int j = 0; j < CFG.game.getCiv(i).getMoveUnitsSize(); ++j) {
                  CFG.game
                     .getProvince(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID())
                     .updateArmy(
                        i,
                        CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits(j).getFromProvinceID()).getArmyCivID(i)
                           + CFG.game.getCiv(i).getMoveUnits(j).getNumOfUnits()
                     );
               }

               for(int j = 0; j < CFG.game.getCiv(i).getMoveUnitsPlunderSize(); ++j) {
                  CFG.game
                     .getProvince(CFG.game.getCiv(i).getMoveUnits_Plunder(j).getFromProvinceID())
                     .updateArmy(
                        i,
                        CFG.game.getProvince(CFG.game.getCiv(i).getMoveUnits_Plunder(j).getFromProvinceID()).getArmyCivID(i)
                           + CFG.game.getCiv(i).getMoveUnits_Plunder(j).getNumOfUnits()
                     );
               }
            } catch (IndexOutOfBoundsException var15) {
               if (CFG.LOGS) {
                  var15.printStackTrace();
               }
            } catch (NullPointerException var16) {
               if (CFG.LOGS) {
                  var16.printStackTrace();
               }
            }
         }

         Gdx.app.log("Turn_Actions", "RETURN END.., fontMain" + CFG.fontMain.getData().scaleY);
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 0000000, fontMain" + CFG.fontMain.getData().scaleY);
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 11111111, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.TURN_ACTIONS);
         CFG.menuManager.updateBuildProvinceHoverInformation();
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 222222, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.game.disableDrawCivlizationsRegions_Players();
         CFG.map.getMapTouchManager().update_ExtraAction();
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 33333, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.map.getMapBG().updateWorldMap_Shaders();
         Game_Render.updateRenderer();
         Game_Render.updateDrawMoveUnits();
         CFG.game.updateDrawMoveUnitsArmy();
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 444444, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.gameAction.SHOW_REPORT = false;

         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            CFG.game.getCiv(i).recruitArmy_NewTurn();
         }

         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 5555555, fontMain" + CFG.fontMain.getData().scaleY);
      } finally {
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 77777, fontMain" + CFG.fontMain.getData().scaleY);

         try {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
         } catch (IndexOutOfBoundsException var12) {
         }
      }

      CFG.setRender_3(true);
      Menu_InGame_RTO2.TIME_CONTINUE = System.currentTimeMillis();
   }

   protected static final void runRevolts() {
      try {
         CFG.gameAction.revoltDeclareIndependence();
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 66666, fontMain" + CFG.fontMain.getData().scaleY);
      } catch (IndexOutOfBoundsException var3) {
         var3.printStackTrace();
      } catch (StackOverflowError var4) {
         var4.printStackTrace();
      }

      try {
         CFG.gameAction.startUprising();
         Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI 666 BBB, fontMain" + CFG.fontMain.getData().scaleY);
      } catch (IndexOutOfBoundsException var1) {
         var1.printStackTrace();
      } catch (StackOverflowError var2) {
         var2.printStackTrace();
      }
   }
}
