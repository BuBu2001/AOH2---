package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_ReleaseVassal {
   protected int iCivID;
   protected List<Integer> lProvinces = new ArrayList<>();

   protected AI_ReleaseVassal(int iCivID, int nProvinceID) {
      this.iCivID = iCivID;
      this.addProvince(nProvinceID);
   }

   protected final void addProvince(int nProvinceID) {
      this.lProvinces.add(nProvinceID);
   }

   protected final boolean haveProvince(int nProvinceID) {
      for(int i = this.lProvinces.size() - 1; i >= 0; --i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean removeProvinceID(int nProvinceID) {
      for(int i = this.lProvinces.size() - 1; i >= 0; --i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            this.lProvinces.remove(i);
            return true;
         }
      }

      return false;
   }
}
