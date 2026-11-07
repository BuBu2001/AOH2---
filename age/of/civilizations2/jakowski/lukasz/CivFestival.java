package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class CivFestival implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iProvinceID;
   protected int iTurnsLeft;

   protected CivFestival(int iProvinceID, int iTurnsLeft) {
      this.iProvinceID = iProvinceID;
      this.iTurnsLeft = iTurnsLeft;
   }
}
