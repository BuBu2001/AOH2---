package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_ArrowRight extends Button_Game {
   protected Button_Game_ArrowRight(int iPosX, int iPosY, boolean isClickable) {
      super("", 0, iPosX, iPosY, isClickable);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.arrow_active)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.arrow_active).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.arrow_active).getHeight() / 2 + iTranslateY,
               true
            );
      } else {
         ImageManager.getImage(Images.arrow)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.arrow).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.arrow).getHeight() / 2 + iTranslateY,
               true
            );
      }
   }
}
