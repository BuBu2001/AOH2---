package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class ServiceRibbon_Overlay_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private int iPosX;
   private int iWidth;
   private boolean reflected;

   protected ServiceRibbon_Overlay_GameData(int iPosX, int iWidth, boolean reflected) {
      this.iPosX = iPosX;
      this.iWidth = iWidth;
      this.reflected = reflected;
   }

   protected final int getPosX() {
      return this.iPosX;
   }

   protected final void setPosX(int iPosX) {
      this.iPosX = iPosX;
   }

   protected final int getWidth() {
      return this.iWidth;
   }

   protected final void setWidth(int iWidth) {
      this.iWidth = iWidth;
   }

   protected final boolean getReflected() {
      return this.reflected;
   }

   protected final void setReflected(boolean reflected) {
      this.reflected = reflected;
   }
}
