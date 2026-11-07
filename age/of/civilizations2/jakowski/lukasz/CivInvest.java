package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class CivInvest implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iProvinceID;
   protected int iTurnsLeft;
   protected int iEconomyLeft;
   protected int iEconomyPerTurn;

   protected CivInvest(int iProvinceID, int iTurnsLeft, int iEconomyLeft, int iEconomyPerTurn) {
      this.iProvinceID = iProvinceID;
      this.iTurnsLeft = iTurnsLeft;
      this.iEconomyLeft = iEconomyLeft;
      this.iEconomyPerTurn = iEconomyPerTurn;
   }
}
