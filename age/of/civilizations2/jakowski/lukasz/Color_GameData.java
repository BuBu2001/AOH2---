package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Color_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private float fR;
   private float fG;
   private float fB;

   protected Color_GameData(float fR, float fG, float fB) {
      this.fR = fR;
      this.fB = fB;
      this.fG = fG;
   }

   protected final float getR() {
      return this.fR;
   }

   protected final void setR(float fR) {
      this.fR = fR;
   }

   protected final float getG() {
      return this.fG;
   }

   protected final void setG(float fG) {
      this.fG = fG;
   }

   protected final float getB() {
      return this.fB;
   }

   protected final void setB(float fB) {
      this.fB = fB;
   }
}
