package age.of.civilizations2.jakowski.lukasz;

class Move_Units_Plunder {
   private int iFromProvinceID;
   private int iNumOfUnits;
   private int iNumOfUnitsWidth;

   protected Move_Units_Plunder(int iFromProvinceID, int iNumOfUnits) {
      this.iFromProvinceID = iFromProvinceID;
      this.setNumOfUnits(iNumOfUnits);
   }

   protected final int getFromProvinceID() {
      return this.iFromProvinceID;
   }

   protected final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   protected final void setNumOfUnits(int iNumOfUnits) {
      this.iNumOfUnits = iNumOfUnits;
      CFG.glyphLayout.setText(CFG.fontArmy, "" + iNumOfUnits);
      this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
   }

   protected final int getUnitsWidth() {
      return this.iNumOfUnitsWidth;
   }
}
