package age.of.civilizations2.jakowski.lukasz;

class War_Points {
   protected int iCivID;
   protected int iPoints = 0;
   protected int iNumOfLostProvinces = 0;
   protected int iMinScore = 0;

   protected War_Points(int iCivID) {
      this.iCivID = iCivID;
   }

   protected void addPoints(int nP) {
      this.iPoints += nP;
      if (nP > this.iMinScore) {
         this.iMinScore = nP;
      }

      ++this.iNumOfLostProvinces;
   }

   protected final int getNumOfProvincesTotal() {
      return this.iNumOfLostProvinces + CFG.game.getCiv(this.iCivID).getNumOfProvinces();
   }
}
