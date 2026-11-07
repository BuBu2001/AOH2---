package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_FormCivTitle extends Button {
   protected Button_FormCivTitle(String sText, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
      super.init(sText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, true, checkboxState, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.55F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65F));
      } else {
         oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
      }

      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(CFG.COLOR_FLAG_FRAME);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.85F) / 2.0F) + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.85F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   private final float getImageScale(int nImageID) {
      return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7F / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7F / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return new Button.Checkbox() {
         @Override
         public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
         }
      };
   }

   @Override
   protected Color getColor(boolean isActive) {
      if (this.getCheckboxState()) {
         return isActive
            ? CFG.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE
            : (
               this.getClickable()
                  ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_POSITIVE_HOVER : CFG.COLOR_TEXT_MODIFIER_POSITIVE)
                  : new Color(0.78F, 0.78F, 0.78F, 0.7F)
            );
      } else {
         return isActive
            ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE
            : (
               this.getClickable()
                  ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                  : new Color(0.78F, 0.78F, 0.78F, 0.7F)
            );
      }
   }
}
