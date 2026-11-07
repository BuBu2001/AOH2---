package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Province_Port_Center {
   protected int iShiftX;
   protected int iShiftY;

   protected Province_Port_Center() {
      this.iShiftX = 0;
      this.iShiftY = 0;
   }

   protected Province_Port_Center(int iShiftX, int iShiftY) {
      this.iShiftX = iShiftX;
      this.iShiftY = iShiftY;
   }

   protected void draw(SpriteBatch oSB, int nPosX, int nPosY, float nScale) {
      ImageManager.getImage(Images.port_ico)
         .draw(
            oSB,
            nPosX + (int)((float)this.iShiftX * nScale) - ImageManager.getImage(Images.port_ico).getWidth() / 2,
            nPosY + (int)((float)this.iShiftY * nScale) - ImageManager.getImage(Images.port_ico).getHeight() / 2
         );
   }

   protected final int getShiftX() {
      return this.iShiftX;
   }

   protected final int getShiftY() {
      return this.iShiftY;
   }
}
