package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class AI_Influence implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected int iMinRelation;
   protected int iUntilTurnID;

   protected AI_Influence(int iCivID, int iMinRelation, int iUntilTurnID) {
      this.iCivID = iCivID;
      this.iMinRelation = iMinRelation;
      this.iUntilTurnID = iUntilTurnID;
   }
}
