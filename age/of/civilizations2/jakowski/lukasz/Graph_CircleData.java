package age.of.civilizations2.jakowski.lukasz;

class Graph_CircleData {
   private int iDataID;
   private float fPercentage;

   protected Graph_CircleData(int iDataID, float fPercentage) {
      this.iDataID = iDataID;
      this.fPercentage = fPercentage;
   }

   protected final int getDataID() {
      return this.iDataID;
   }

   protected final float getPercentage() {
      return this.fPercentage;
   }

   protected final void setPercentage(float fPercentage) {
      this.fPercentage = fPercentage;
   }
}
