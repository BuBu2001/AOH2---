package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_Decline extends Button_Game {
   protected Button_Game_Decline(int iPosX, int iPosY, boolean isClickable) {
      super("", 0, iPosX, iPosY, isClickable);
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      if (isActive) {
         ImageManager.getImage(Images.btn_x_active)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_x_active).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_x_active).getHeight() / 2 + iTranslateY
            );
      } else {
         if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
         }

         ImageManager.getImage(Images.btn_x)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_x).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_x).getHeight() / 2 + iTranslateY
            );
         oSB.setColor(Color.WHITE);
      }
   }
}
