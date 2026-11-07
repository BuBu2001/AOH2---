package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PreDefined_Borders_Data_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Integer> lProvinces = new ArrayList<>();
   private int iCapitalProvinceID = -1;

   protected PreDefined_Borders_Data_GameData() {
   }

   protected final void setCapitalProvinceID(int nProvinceID) {
      this.iCapitalProvinceID = nProvinceID;
   }

   protected final int getCapitalProvinceID() {
      return this.iCapitalProvinceID;
   }

   protected final int getProvincesSize() {
      return this.lProvinces.size();
   }

   protected final int getProvinceID(int i) {
      return this.lProvinces.get(i);
   }

   protected final boolean hasProvinceID(int nProvinceID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final void addProvinceID(int nProvinceID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return;
         }
      }

      this.lProvinces.add(nProvinceID);
   }

   protected final void removeProvinceID(int nProvinceID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            this.lProvinces.remove(i);
            return;
         }
      }
   }
}
