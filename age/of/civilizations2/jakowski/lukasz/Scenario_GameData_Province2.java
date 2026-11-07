package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Scenario_GameData_Province2 implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Integer> lProvinceOwners = null;

   protected final void buildProvinceOwners() {
      this.lProvinceOwners = new ArrayList<>();

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         this.lProvinceOwners.add(CFG.game.getProvince(i).getCivID());
      }
   }

   protected final List<Integer> getProvinceOwners() {
      return this.lProvinceOwners;
   }
}
