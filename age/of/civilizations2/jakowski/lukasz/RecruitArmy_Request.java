package age.of.civilizations2.jakowski.lukasz;

class RecruitArmy_Request {
   private int iArmy;
   private int iProvinceID;
   private int iArmyWidth = 0;

   protected RecruitArmy_Request(int iProvinceID, int iArmy) {
      this.iProvinceID = iProvinceID;
      this.setArmy(iArmy);
   }

   protected final int getArmy() {
      return this.iArmy;
   }

   protected final void setArmy(int iArmy) {
      this.iArmy = iArmy;

      try {
         CFG.glyphLayout.setText(CFG.fontArmy, "" + iArmy);
         this.iArmyWidth = (int)CFG.glyphLayout.width;
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   protected final int getProvinceID() {
      return this.iProvinceID;
   }

   protected final void setProvinceID(int iProvinceID) {
      this.iProvinceID = iProvinceID;
   }

   protected final int getArmyWidth() {
      return this.iArmyWidth;
   }
}
