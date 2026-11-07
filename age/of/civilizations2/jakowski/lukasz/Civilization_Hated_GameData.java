package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civilization_Hated_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;

   protected Civilization_Hated_GameData(int iCivID) {
      this.iCivID = iCivID;
   }
}
