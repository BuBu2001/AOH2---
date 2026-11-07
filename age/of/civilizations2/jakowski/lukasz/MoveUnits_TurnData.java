package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class MoveUnits_TurnData {
   private List<Move_Units> lMoveUnits = new ArrayList<>();
   private int iMoveUnitsSize;
   private List<Integer> lCivID = new ArrayList<>();

   protected MoveUnits_TurnData(int iCivID) {
      this.iMoveUnitsSize = 0;
   }

   protected final int getMoveUnitsSize() {
      return this.iMoveUnitsSize;
   }

   protected final void addMoveUnits(Move_Units nMoveUnits, int nCivID) {
      if (nMoveUnits.getMoveUnitsLine() == null) {
         nMoveUnits.buildMoveUnitsLine();
      }

      this.lMoveUnits.add(nMoveUnits);
      this.lCivID.add(nCivID);
      this.iMoveUnitsSize = this.lMoveUnits.size();
   }

   protected final Move_Units getMoveUnits(int i) {
      return this.lMoveUnits.get(i);
   }

   protected final int getMoveUnits_TotalNumOfUnits() {
      int out = 0;

      for(int i = 0; i < this.iMoveUnitsSize; ++i) {
         out += this.lMoveUnits.get(i).getNumOfUnits();
      }

      return out;
   }

   protected final int getCivID(int i) {
      return this.lCivID.get(i);
   }

   protected final boolean isPlayerMoving() {
      for(int i = 0; i < this.iMoveUnitsSize; ++i) {
         if (CFG.game.getCiv(this.lCivID.get(i)).getControlledByPlayer()) {
            return true;
         }
      }

      return false;
   }
}
