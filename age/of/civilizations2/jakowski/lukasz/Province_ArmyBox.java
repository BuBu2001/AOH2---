package age.of.civilizations2.jakowski.lukasz;

class Province_ArmyBox {
   private int iStartPosX;
   private int iStartPosY;
   private int iEndPosX;
   private int iEndPosY;

   protected Province_ArmyBox(int iStartPosX, int iStartPosY, int iEndPosX, int iEndPosY) {
      this.iStartPosX = iStartPosX;
      this.iStartPosY = iStartPosY;
      this.iEndPosX = iEndPosX;
      this.iEndPosY = iEndPosY;
   }

   protected final int getStartPosX() {
      return this.iStartPosX;
   }

   protected final int getStartPosY() {
      return this.iStartPosY;
   }

   protected final int getEndPosX() {
      return this.iEndPosX;
   }

   protected final int getEndPosY() {
      return this.iEndPosY;
   }
}
