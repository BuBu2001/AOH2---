package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Close extends Button {
   protected Button_Close(int iPosX, int iPosY, int iWidth, int iHeight) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, true, true, false, false, null);
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.btn_close).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
   }
}
