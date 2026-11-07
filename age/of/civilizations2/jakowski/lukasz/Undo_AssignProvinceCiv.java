package age.of.civilizations2.jakowski.lukasz;

class Undo_AssignProvinceCiv {
   private int iProvinceID;
   private int iCivID;

   protected Undo_AssignProvinceCiv(int iProvinceID, int iCivID) {
      this.iProvinceID = iProvinceID;
      this.iCivID = iCivID;
   }

   protected final int getProvinceID() {
      return this.iProvinceID;
   }

   protected final int getCivID() {
      return this.iCivID;
   }
}
