package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Timelapse_Capital implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iSinceTurnID = 1;
   protected int iProvinceID;

   protected Timelapse_Capital(int iProvinceID, int iSinceTurnID) {
      this.iProvinceID = iProvinceID;
      this.iSinceTurnID = iSinceTurnID;
   }
}
