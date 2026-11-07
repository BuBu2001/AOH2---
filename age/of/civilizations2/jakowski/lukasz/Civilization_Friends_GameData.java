package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civilization_Friends_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected int iSinceTurnID = 0;

   protected Civilization_Friends_GameData(int iCivID, int iSinceTurnID) {
      this.iCivID = iCivID;
      this.iSinceTurnID = iSinceTurnID;
   }
}
