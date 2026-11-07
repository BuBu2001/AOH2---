package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class AI_CivsInRange implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected float fRange;

   protected AI_CivsInRange(int iCivID, float fRange) {
      this.iCivID = iCivID;
      this.fRange = fRange;
   }
}
