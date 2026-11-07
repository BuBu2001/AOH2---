package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_ViewIcon extends Button {
   protected Button_ViewIcon(int iPosX, int iPosY, boolean isClickable) {
      super.init("", -1, iPosX, iPosY, 25, 10, isClickable, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }
}
