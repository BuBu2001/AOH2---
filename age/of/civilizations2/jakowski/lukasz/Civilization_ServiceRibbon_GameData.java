package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Civilization_ServiceRibbon_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sSRTAG;
   private List<Color_GameData> lColors = new ArrayList<>();

   protected final String getSRTAG() {
      return this.sSRTAG;
   }

   protected final void setSRTAG(String sSRTAG) {
      this.sSRTAG = sSRTAG;
   }

   protected final List<Color_GameData> getColors() {
      return this.lColors;
   }

   protected final Color_GameData getColor(int i) {
      return this.lColors.get(i);
   }
}
