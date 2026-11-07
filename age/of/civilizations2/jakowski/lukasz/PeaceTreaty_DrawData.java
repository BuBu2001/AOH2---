package age.of.civilizations2.jakowski.lukasz;

class PeaceTreaty_DrawData {
   protected int iCivID = 0;
   protected int iProvinceValue = 0;
   protected boolean isToTake = false;
   protected int isTaken = -1;

   protected PeaceTreaty_DrawData(int iCivID, int iProvinceValue, boolean isToTake) {
      this.iCivID = iCivID;
      this.iProvinceValue = iProvinceValue;
      this.isToTake = isToTake;
      this.isTaken = -1;
   }
}
