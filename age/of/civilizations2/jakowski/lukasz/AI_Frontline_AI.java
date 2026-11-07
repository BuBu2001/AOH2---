package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Frontline_AI {
   private List<AI_Frontline> lFrontLines = new ArrayList<>();
   private List<Boolean> lFrontLines_OwnFront = new ArrayList<>();
   private int iFrontLinesSize = 0;
   private int iWithCivID = 0;

   protected AI_Frontline_AI(int iWithCivID, AI_Frontline nFront, boolean ownFront) {
      this.iWithCivID = iWithCivID;
      this.lFrontLines.add(nFront);
      this.lFrontLines_OwnFront.add(ownFront);
      this.iFrontLinesSize = this.lFrontLines.size();
   }

   protected final void addFrontLine(AI_Frontline nFront, boolean ownFront) {
      this.lFrontLines.add(nFront);
      this.lFrontLines_OwnFront.add(ownFront);
      this.iFrontLinesSize = this.lFrontLines.size();
   }

   protected final AI_Frontline getFrontLine(int id) {
      return this.lFrontLines.get(id);
   }

   protected final int getFrontLinesSize() {
      return this.iFrontLinesSize;
   }

   protected final int getWithCivID() {
      return this.iWithCivID;
   }

   protected boolean ownFront(int i) {
      return this.lFrontLines_OwnFront.get(i);
   }
}
