package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.List;

public class Scenario_WastelandProvinces_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Integer> lWastelandProvincesIDs;

   protected Scenario_WastelandProvinces_GameData(List<Integer> lProvinces) {
      this.lWastelandProvincesIDs = lProvinces;
   }

   protected final int getWastelandProvincesSize() {
      return this.lWastelandProvincesIDs.size();
   }

   protected final int getWastelandProvinceID(int i) {
      return this.lWastelandProvincesIDs.get(i);
   }
}
