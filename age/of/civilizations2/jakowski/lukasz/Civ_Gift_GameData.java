package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civ_Gift_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iFromCivID;
   protected int iTurnID;

   protected Civ_Gift_GameData(int iFromCivID, int iTurnID) {
      this.iFromCivID = iFromCivID;
      this.iTurnID = iTurnID;
   }
}
