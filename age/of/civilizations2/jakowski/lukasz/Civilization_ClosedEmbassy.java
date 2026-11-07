package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civilization_ClosedEmbassy implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID = 0;
   protected int iNumOfTurns = 0;

   protected Civilization_ClosedEmbassy(int iCivID, int iNumOfTurns) {
      this.iCivID = iCivID;
      this.iNumOfTurns = iNumOfTurns;
   }
}
