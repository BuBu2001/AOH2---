package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MoveUnits_Line {
   private int fromProvinceID;
   private int toProvinceID;
   protected static int MOVE_SRC_X = 0;
   protected static int MOVE_WIDTH = 0;
   private int iWidth;
   private float fAngle;
   protected int offsetX = 0;
   protected int offsetY = 0;
   protected long lMovingTime = 0L;
   protected float fMovingPercentage = 0.0F;

   protected MoveUnits_Line() {
   }

   protected MoveUnits_Line(int fromProvinceID, int toProvinceID) {
      this.fromProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      if (!CFG.game.getProvince(fromProvinceID).getDrawProvince()) {
         CFG.game.updateDrawProvince(fromProvinceID);
      }

      if (!CFG.game.getProvince(toProvinceID).getDrawProvince()) {
         CFG.game.updateDrawProvince(toProvinceID);
      }

      this.iWidth = (int)Math.ceil(
         Math.sqrt(
            (double)(
               (
                        CFG.game.getProvince(toProvinceID).getCenterX()
                           + CFG.game.getProvince(toProvinceID).getShiftX()
                           + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
                           - (
                              CFG.game.getProvince(fromProvinceID).getCenterX()
                                 + CFG.game.getProvince(fromProvinceID).getShiftX()
                                 + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                           )
                     )
                     * (
                        CFG.game.getProvince(toProvinceID).getCenterX()
                           + CFG.game.getProvince(toProvinceID).getShiftX()
                           + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
                           - (
                              CFG.game.getProvince(fromProvinceID).getCenterX()
                                 + CFG.game.getProvince(fromProvinceID).getShiftX()
                                 + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                           )
                     )
                  + (
                        CFG.game.getProvince(fromProvinceID).getCenterY()
                           + CFG.game.getProvince(fromProvinceID).getShiftY()
                           - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
                     )
                     * (
                        CFG.game.getProvince(fromProvinceID).getCenterY()
                           + CFG.game.getProvince(fromProvinceID).getShiftY()
                           - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
                     )
            )
         )
      );
      this.fAngle = (float)(
         Math.atan2(
               (double)(
                  CFG.game.getProvince(fromProvinceID).getCenterY()
                     + CFG.game.getProvince(fromProvinceID).getShiftY()
                     - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
               ),
               (double)(
                  -(
                        CFG.game.getProvince(fromProvinceID).getCenterX()
                           + CFG.game.getProvince(fromProvinceID).getShiftX()
                           + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                     )
                     + CFG.game.getProvince(toProvinceID).getCenterX()
                     + CFG.game.getProvince(toProvinceID).getShiftX()
                     + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
               )
            )
            * 180.0
            / Math.PI
      );
      float tempAngle;
      if (this.fAngle > 90.0F) {
         tempAngle = 90.0F - this.fAngle % 90.0F;
      } else if (this.fAngle < -90.0F) {
         tempAngle = -(90.0F + this.fAngle % 90.0F);
      } else {
         tempAngle = this.fAngle;
      }

      this.offsetX = -((int)((float)this.getImageID().getHeight() / 2.0F * (tempAngle / 90.0F)));
      this.offsetY = -((int)((float)this.getImageID().getHeight() / 2.0F * ((90.0F - Math.abs(this.fAngle)) / 90.0F)));
      this.lMovingTime = System.currentTimeMillis();
      this.fMovingPercentage = 0.1F;
      MOVE_WIDTH = CFG.linesManager.moveLandImage.getWidth();
   }

   protected void updateColor(SpriteBatch oSB) {
      oSB.setColor(Color.WHITE);
   }

   protected void updateMovingLine() {
      this.fMovingPercentage += (float)(System.currentTimeMillis() - this.lMovingTime) / 600.0F * 0.9F;
   }

   protected void drawLine(SpriteBatch oSB, float nScale) {
      this.updateColor(oSB);
      this.drawLine2(oSB, nScale);
   }

   protected void drawLine2(SpriteBatch oSB, float nScale) {
      this.updateMovingLine();
      this.lMovingTime = System.currentTimeMillis();
      if (this.fMovingPercentage >= 1.0F) {
         this.fMovingPercentage = 1.0F;
      } else {
         CFG.setRender_3(true);
      }

      this.getImageID()
         .draw(
            oSB,
            (int)(
                  (float)(
                        CFG.game.getProvince(this.fromProvinceID).getCenterX()
                           + CFG.game.getProvince(this.fromProvinceID).getShiftX()
                           + CFG.game.getProvince(this.fromProvinceID).getTranslateProvincePosX()
                     )
                     * nScale
               )
               + this.offsetX,
            (int)(
                  (float)(
                        CFG.game.getProvince(this.fromProvinceID).getCenterY()
                           + CFG.game.getProvince(this.fromProvinceID).getShiftY()
                           + CFG.map.getMapCoordinates().getPosY()
                     )
                     * nScale
               )
               + this.offsetY,
            (int)((float)this.iWidth * this.fMovingPercentage * nScale),
            this.getImageID().getHeight(),
            this.fAngle,
            this.getMoveSrcX(),
            this.getFlipX()
         );
      oSB.setColor(Color.WHITE);
   }

   protected int getMoveSrcX() {
      return MOVE_SRC_X;
   }

   protected boolean getFlipX() {
      return CFG.linesManager.moveLandFlipX;
   }

   protected Image getImageID() {
      return CFG.linesManager.moveLandImage;
   }

   protected final int getFromProvinceID() {
      return this.fromProvinceID;
   }

   protected final void setFromProvinceID(int fromProvinceID) {
      this.fromProvinceID = fromProvinceID;
   }

   protected final int getToProvinceID() {
      return this.toProvinceID;
   }

   protected final void setToProvinceID(int toProvinceID) {
      this.toProvinceID = toProvinceID;
   }

   protected final int getWidth() {
      return this.iWidth;
   }

   protected final void setWidth(int iWidth) {
      this.iWidth = iWidth;
   }

   protected final float getAngle() {
      return this.fAngle;
   }

   protected final void setAngle(float fAngle) {
      this.fAngle = fAngle;
   }

   protected final float getMovingPercentage() {
      return this.fMovingPercentage;
   }

   protected final void updateMoveTime() {
      this.lMovingTime = System.currentTimeMillis();
      this.fMovingPercentage = 0.1F;
   }
}
