package age.of.civilizations2.jakowski.lukasz;

class OpenBorders {
   private int iToCivID;
   private int iNumOfTurns;

   protected OpenBorders(int iToCivID, int iNumOfTurns) {
      this.iToCivID = iToCivID;
      this.iNumOfTurns = iNumOfTurns;
   }

   protected final int getToCivID() {
      return this.iToCivID;
   }

   protected final void setToCivID(int iToCivID) {
      this.iToCivID = iToCivID;
   }

   protected final int getNumOfTurns() {
      return this.iNumOfTurns;
   }

   protected final void setNumOfTurns(int iNumOfTurns) {
      this.iNumOfTurns = iNumOfTurns;
   }
}
