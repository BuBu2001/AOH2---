package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Timelapse_TurnChanges implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iProvinceID;
   protected int iToCivID;
   protected boolean isOccupied = false;

   protected Timelapse_TurnChanges(int iProvinceID, int iToCivID, boolean isOccupied) {
      this.iProvinceID = iProvinceID;
      this.iToCivID = iToCivID;
      this.isOccupied = isOccupied;
   }
}
