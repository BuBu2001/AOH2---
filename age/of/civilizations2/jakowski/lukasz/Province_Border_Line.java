package age.of.civilizations2.jakowski.lukasz;

class Province_Border_Line {
   private int iPosX;
   private int iPosY;
   private int iWidth;
   private float fAngle;

   protected Province_Border_Line(int nPosX, int nPosY, int nPosX2, int nPosY2) {
      this.iPosX = nPosX;
      this.iPosY = nPosY;
      this.iWidth = (int)Math.ceil(Math.sqrt((double)((nPosX2 - nPosX) * (nPosX2 - nPosX) + (nPosY - nPosY2) * (nPosY - nPosY2))));
      this.fAngle = (float)(Math.atan2((double)(nPosY - nPosY2), (double)(-nPosX + nPosX2)) * 180.0 / Math.PI);
   }

   protected int getPosX() {
      return this.iPosX;
   }

   protected int getPosY() {
      return this.iPosY;
   }

   protected int getWidth() {
      return this.iWidth;
   }

   protected float getAngle() {
      return this.fAngle;
   }
}
