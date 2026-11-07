package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Loan_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iGoldPerTurn;
   protected int iTurnsLeft;

   protected Loan_GameData(int iGoldPerTurn, int iTurnsLeft) {
      this.iGoldPerTurn = iGoldPerTurn;
      this.iTurnsLeft = iTurnsLeft;
   }
}
