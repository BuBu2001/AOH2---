package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class AI_Rival implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected int iUntilTurnID;

   protected AI_Rival(int iCivID, int iUntilTurnID) {
      this.iCivID = iCivID;
      this.iUntilTurnID = iUntilTurnID;
   }
}
