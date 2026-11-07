package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class WastelandMap_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName = "";
   private List<Integer> lWastelandProvincesIDs = new ArrayList<>();

   protected WastelandMap_GameData() {
   }

   protected final void generateData() {
      if (this.lWastelandProvincesIDs != null) {
         this.lWastelandProvincesIDs.clear();
      } else {
         this.lWastelandProvincesIDs = new ArrayList<>();
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(i).getWasteland() >= 0) {
            this.lWastelandProvincesIDs.add(i);
         }
      }
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final int getWastelandProvincesSize() {
      return this.lWastelandProvincesIDs.size();
   }

   protected final int getWastelandProvinceID(int i) {
      return this.lWastelandProvincesIDs.get(i);
   }
}
