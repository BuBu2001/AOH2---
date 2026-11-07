package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class WarReparations implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iFromCivID = 0;
   protected int iTurnsLeft = 0;

   protected WarReparations(int iFromCivID, int iTurnsLeft) {
      this.iFromCivID = iFromCivID;
      this.iTurnsLeft = iTurnsLeft;
   }
}
