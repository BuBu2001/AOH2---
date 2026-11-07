package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Add extends Button {
   protected Button_Add(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.getClickable()) {
         if (isActive) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
         } else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
         } else {
            oSB.setColor(Color.WHITE);
         }
      } else if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
      } else {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
      }

      ImageManager.getImage(Images.btn_add)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.btn_add).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight()
         );
      ImageManager.getImage(Images.btn_add)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.btn_add).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_add).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight(),
            true
         );
      ImageManager.getImage(Images.btn_add)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight() * 2 + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth(),
            ImageManager.getImage(Images.btn_add).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.btn_add)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight() + iTranslateY,
            true,
            true
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? new Color(0.82F, 0.82F, 0.82F, 1.0F) : new Color(0.7F, 0.7F, 0.7F, 1.0F))
               : new Color(0.764F, 0.764F, 0.764F, 0.6F)
         );
   }
}
