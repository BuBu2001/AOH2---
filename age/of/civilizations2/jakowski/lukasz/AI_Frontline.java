package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Frontline {
   protected List<Integer> lProvinces = new ArrayList<>();
   protected int iRegionID = -1;
   protected int iWithCivID = 0;
   protected boolean bordersWithEnemy = false;

   protected AI_Frontline(int nProvinceID, int iRegionID, int iWithCivID, boolean bordersWithEnemy) {
      this.lProvinces.add(nProvinceID);
      this.iRegionID = iRegionID;
      this.iWithCivID = iWithCivID;
      this.bordersWithEnemy = bordersWithEnemy;
   }

   protected boolean containsProvince(int nProvinceID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected int getFrontLineArmy(int nCivID) {
      int out = 0;

      for(int i = this.lProvinces.size() - 1; i >= 0; --i) {
         out += CFG.game.getProvince(this.lProvinces.get(i)).getArmyCivID(nCivID);
      }

      return out;
   }
}
