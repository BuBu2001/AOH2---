package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomTurnOrder {
   private List<Integer> lRandomTurnOrder = new ArrayList<>();
   private int iRTOSize = 0;

   protected final void buildRandomOrder() {
      this.lRandomTurnOrder.clear();
      List<Integer> tempIDs = new ArrayList<>();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            tempIDs.add(i);
         }
      }

      Random oR = new Random();

      while(tempIDs.size() > 0) {
         int tempID = oR.nextInt(tempIDs.size());
         this.lRandomTurnOrder.add(tempIDs.get(tempID));
         tempIDs.remove(tempID);
      }

      this.iRTOSize = this.lRandomTurnOrder.size();
   }

   protected final int getRTO(int i) {
      return this.lRandomTurnOrder.get(i);
   }

   protected final int getPositionInRTOOfCiv(int nCivID) {
      for(int i = 0; i < this.iRTOSize; ++i) {
         if (nCivID == this.lRandomTurnOrder.get(i)) {
            return i + 1;
         }
      }

      return 0;
   }

   protected final int getRTOSize() {
      return this.iRTOSize;
   }
}
