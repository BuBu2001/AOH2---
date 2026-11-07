package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_ColorPicker extends Button_Game {
   protected Button_Game_ColorPicker(int iPosX, int iPosY, boolean isClickable) {
      super("", 0, iPosX, iPosY, isClickable);
   }

   protected Button_Game_ColorPicker(int iPosX, int iPosY, int nWidth, boolean isClickable) {
      super("", 0, iPosX, iPosY, nWidth, isClickable);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
         ImageManager.getImage(Images.pickeIcon)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickeIcon).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickeIcon).getHeight() / 2 + iTranslateY
            );
         oSB.setColor(Color.WHITE);
      } else {
         ImageManager.getImage(Images.pickeIcon)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.pickeIcon).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.pickeIcon).getHeight() / 2 + iTranslateY
            );
      }
   }
}
