package age.of.civilizations2.jakowski.lukasz;

class Point_XY {
   private int iPosX;
   private int iPosY;

   protected Point_XY(int nPosX, int nPosY) {
      this.iPosX = nPosX;
      this.iPosY = nPosY;
   }

   protected final int getPosX() {
      return this.iPosX;
   }

   protected final void setPosX(int iPosX) {
      this.iPosX = iPosX;
   }

   protected final int getPosY() {
      return this.iPosY;
   }

   protected final void setPosY(int iPosY) {
      this.iPosY = iPosY;
   }
}
