package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_BG_CNG extends Slider_BG {
   protected Slider_BG_CNG(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super(iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   protected Slider_BG_CNG(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (isActive) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
      } else {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - CFG.PADDING + iTranslateY,
            this.getWidth() + CFG.PADDING * 4,
            this.getHeight() + CFG.PADDING * 2
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING + iTranslateY,
            this.getWidth() + CFG.PADDING * 4,
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + CFG.PADDING + this.getHeight() - this.getHeight() / 4 + iTranslateY,
            this.getWidth() + CFG.PADDING * 4,
            this.getHeight() / 4,
            false,
            true
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.35F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY,
            this.getWidth() + CFG.PADDING * 4,
            1
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.65F));
      ImageManager.getImage(Images.line_32)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() + CFG.PADDING - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY,
            this.getWidth() + CFG.PADDING * 4,
            1
         );
      oSB.setColor(Color.WHITE);
      this.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      super.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG_UpdateAnimation();
      oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(0.05F, 0.07F, 0.12F, 1.0F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4,
            true,
            true
         );
      oSB.setColor(this.getColorLEFT());
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight());
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeight());
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.getDrawText(),
         this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   protected Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.66F, 0.658F, 0.665F, 1.0F)
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_MENU_TEXT_HOVERED : CFG.COLOR_BUTTON_MENU_TEXT)
               : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
         );
   }
}
