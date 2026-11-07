package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_Civs implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected List<Integer> lProvincesLost = new ArrayList<>();
   protected boolean showProvinces = false;

   protected PeaceTreaty_Civs(int iCivID) {
      this.iCivID = iCivID;
   }

   protected final int getVictoryPointsTotal() {
      int out = 0;

      for(int i = 0; i < this.lProvincesLost.size(); ++i) {
         out += CFG.game.getProvinceValue(this.lProvincesLost.get(i));
      }

      return out;
   }
}
