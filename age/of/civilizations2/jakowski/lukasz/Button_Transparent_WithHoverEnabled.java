package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Transparent_WithHoverEnabled extends Button {
   protected Button_Transparent_WithHoverEnabled(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   protected Button_Transparent_WithHoverEnabled(int iTextPos, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", iTextPos, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }
}
