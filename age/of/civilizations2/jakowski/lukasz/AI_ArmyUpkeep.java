package age.of.civilizations2.jakowski.lukasz;

class AI_ArmyUpkeep {
   protected int iProvinceID;
   protected int iCost;
   protected float fScore = 0.0F;

   protected AI_ArmyUpkeep(int nCivID, int nProvinceID) {
      this.iProvinceID = nProvinceID;
      this.iCost = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep(nProvinceID, nCivID);
   }
}
