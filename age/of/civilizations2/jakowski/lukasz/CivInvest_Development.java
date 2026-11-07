package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class CivInvest_Development implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iProvinceID;
   protected int iTurnsLeft;
   protected float iDevelopemntLeft;
   protected float iDevelopemntPerTurn;

   protected CivInvest_Development(int iProvinceID, int iTurnsLeft, float iDevelopemntLeft, float iDevelopemntPerTurn) {
      this.iProvinceID = iProvinceID;
      this.iTurnsLeft = iTurnsLeft;
      this.iDevelopemntLeft = iDevelopemntLeft;
      this.iDevelopemntPerTurn = iDevelopemntPerTurn;
   }
}
