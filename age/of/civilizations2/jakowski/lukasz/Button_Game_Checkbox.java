package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_Checkbox extends Button {
   protected Button_Game_Checkbox(String sText, int iTextPositionX, int iPosX, int iPosY, int nWidth, boolean isClickable, boolean checkBoxState) {
      super.init(sText, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, checkBoxState, null);
      super.setCheckbox(true);
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         if (this.getCheckboxState()) {
            ImageManager.getImage(Images.btn_clear_checkbox_false)
               .draw2(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY,
                  this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(),
                  ImageManager.getImage(Images.btnh_clear).getHeight()
               );
            ImageManager.getImage(Images.btn_clear_checkbox_false)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX,
                  this.getPosY() + iTranslateY,
                  true
               );
         } else {
            ImageManager.getImage(Images.btn_clear_checkbox_true)
               .draw2(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY,
                  this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(),
                  ImageManager.getImage(Images.btnh_clear).getHeight()
               );
            ImageManager.getImage(Images.btn_clear_checkbox_true)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX,
                  this.getPosY() + iTranslateY,
                  true
               );
         }
      } else if (this.getCheckboxState()) {
         ImageManager.getImage(Images.btn_clear_checkbox_true)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(),
               ImageManager.getImage(Images.btnh_clear).getHeight()
            );
         ImageManager.getImage(Images.btn_clear_checkbox_true)
            .draw(
               oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true
            );
      } else {
         ImageManager.getImage(Images.btn_clear_checkbox_false)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.btnh_clear).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth(),
               ImageManager.getImage(Images.btnh_clear).getHeight()
            );
         ImageManager.getImage(Images.btn_clear_checkbox_false)
            .draw(
               oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_clear).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true
            );
      }
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (
                  this.getCheckboxState()
                     ? (this.getIsHovered() ? new Color(0.33F, 0.48F, 0.008F, 1.0F) : new Color(0.396F, 0.576F, 0.012F, 1.0F))
                     : (this.getIsHovered() ? new Color(0.584F, 0.075F, 0.004F, 1.0F) : new Color(0.643F, 0.113F, 0.008F, 1.0F))
               )
               : new Color(0.674F, 0.09F, 0.066F, 0.5F)
         );
   }
}
