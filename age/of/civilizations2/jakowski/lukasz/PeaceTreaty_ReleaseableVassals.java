package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_ReleaseableVassals implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected List<Integer> lProvinces = new ArrayList<>();
   protected int iReleasesToCivID = -1;

   protected PeaceTreaty_ReleaseableVassals(int iCivID, int iProvinceID) {
      this.iCivID = iCivID;
      this.lProvinces.add(iProvinceID);
   }

   protected int getScoreValue() {
      int out = 0;

      for(int i = 0; i < this.lProvinces.size(); ++i) {
         out += CFG.game.getProvinceValue(this.lProvinces.get(i));
      }

      return Math.max(out, 1);
   }

   protected final void addProvince(int nProvinceID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return;
         }
      }

      this.lProvinces.add(nProvinceID);
   }
}
