package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class AI_WarPreparations implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iLeaderCivID = 0;
   protected int onCivID;
   protected int iNumOfTurnsLeft = 0;
   protected boolean declareWar = true;

   protected AI_WarPreparations(int iLeaderCivID, int onCivID, boolean declareWar, int numOfTurns) {
      this.onCivID = onCivID;
      this.declareWar = declareWar;
      this.iLeaderCivID = iLeaderCivID;
      this.iNumOfTurnsLeft = numOfTurns;
   }
}
