package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game extends Button {
   protected Button_Game(String sText, int iTextPositionX, int iPosX, int iPosY, int nWidth) {
      super.init(sText, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, true, true, false, false, null);
   }

   protected Button_Game(String sText, int iTextPositionX, int iPosX, int iPosY, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, isClickable, true, false, false, null);
   }

   protected Button_Game(String sText, int iTextPositionX, int iPosX, int iPosY, int nWidth, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, false, null);
   }

   protected Button_Game(String sText, int iTextPositionX, int iPosX, int iPosY, boolean isClickable, boolean isVisible) {
      super.init(sText, iTextPositionX, iPosX, iPosY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, isClickable, isVisible, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.btnh_clear)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(),
               ImageManager.getImage(Images.btnh_clear).getHeight()
            );
         ImageManager.getImage(Images.btnh_clear)
            .draw(
               oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true
            );
      } else {
         ImageManager.getImage(Images.btn_clear)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.btn_clear).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.btn_clear).getWidth(),
               ImageManager.getImage(Images.btn_clear).getHeight()
            );
         ImageManager.getImage(Images.btn_clear)
            .draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : CFG.COLOR_BUTTON_GAME_TEXT)
               : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
         );
   }
}
