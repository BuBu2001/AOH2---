package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_ViewEnd extends Button_View {
   protected Button_ViewEnd(String sText, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(isActive ? Images.top_view_right_h : Images.top_view_right_last)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getHeight() - ImageManager.getImage(Images.top_view_right_last).getHeight() * 2,
            this.getWidth(),
            ImageManager.getImage(Images.top_view_right_last).getHeight(),
            true
         );
   }
}
