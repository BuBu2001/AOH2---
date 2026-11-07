package age.of.civilizations2.jakowski.lukasz;

class AI_BordersWith {
   protected int iWithCivID;
   protected int iNumOfConnections = 0;

   protected AI_BordersWith(int iWithCivID) {
      this.iWithCivID = iWithCivID;
      this.iNumOfConnections = 1;
   }

   protected AI_BordersWith(int iWithCivID, int iNumOfConnections) {
      this.iWithCivID = iWithCivID;
      this.iNumOfConnections = iNumOfConnections;
   }
}
