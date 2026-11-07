package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Scenario_GameData_Armies implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Scenario_GameData_Army> lArmies = new ArrayList<>();

   protected final void buildData() {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(i).getWasteland() < 0) {
            if (CFG.game.getProvince(i).getSeaProvince()) {
               for(int j = 1; j < CFG.game.getProvince(i).getCivsSize(); ++j) {
                  this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(j), CFG.game.getProvince(i).getArmy(j)));
               }
            } else if (CFG.game.getProvince(i).getCivID() == 0) {
               if (CFG.game.getProvince(i).getArmy(0) != CFG.game.getGameScenarios().getScenario_NeutralArmy()) {
                  this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(i).getArmy(0)));
               }
            } else {
               int j = 0;
               if (CFG.game.getProvince(i).getIsCapital()) {
                  if (CFG.game.getProvince(i).getArmy(0) != CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals()) {
                     this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(0), CFG.game.getProvince(i).getArmy(0)));
                  }

                  j = 1;
               }

               for(; j < CFG.game.getProvince(i).getCivsSize(); ++j) {
                  if (CFG.game.getProvince(i).getArmy(j) > 0) {
                     this.lArmies.add(new Scenario_GameData_Army(i, CFG.game.getProvince(i).getCivID(j), CFG.game.getProvince(i).getArmy(j)));
                  }
               }
            }
         }
      }
   }
}
