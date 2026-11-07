package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Timelapse_Capitals implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Timelapse_Capital> lCapitals = new ArrayList<>();

   protected Timelapse_Capitals(int iProvinceID, int iSinceTurnID) {
      this.lCapitals.add(new Timelapse_Capital(iProvinceID, iSinceTurnID));
   }

   protected void updateCapital(int iProvinceID, int iSinceTurnID) {
      try {
         if (this.lCapitals.get(this.lCapitals.size() - 1).iProvinceID != iProvinceID) {
            this.lCapitals.add(new Timelapse_Capital(iProvinceID, iSinceTurnID));
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lCapitals.add(new Timelapse_Capital(iProvinceID, iSinceTurnID));
      }
   }

   protected int getCapitalID(int iTurnID) {
      for(int i = 0; i < this.lCapitals.size() - 1; ++i) {
         if (this.lCapitals.get(i).iSinceTurnID <= iTurnID && this.lCapitals.get(i + 1).iSinceTurnID > iTurnID) {
            return this.lCapitals.get(i).iProvinceID;
         }
      }

      return this.lCapitals.get(this.lCapitals.size() - 1).iProvinceID;
   }
}
