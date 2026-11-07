package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_CrossTheBorder extends Slider {
   protected static final Color bgColor = new Color(0.0F, 0.0F, 0.0F, 0.3F);
   protected float FONT_SCALE = 0.8F;

   protected Slider_CrossTheBorder(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   protected Slider_CrossTheBorder(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent, float FONT_SCALE) {
      this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
      this.FONT_SCALE = FONT_SCALE;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG_UpdateAnimation();
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.8F));
      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 3 / 5,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getSliderHeight()
         );
      oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getSliderHeight()
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getSliderHeight()
         );
      oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a * 0.92F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getSliderHeight()
         );

      for(int i = 1; i < 10; ++i) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.04F));
         ImageManager.getImage(Images.line_32_vertical)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() / 10 * i + iTranslateX,
               this.getPosY()
                  + this.getHeight()
                  - CFG.PADDING
                  - this.getSliderHeight()
                  - ImageManager.getImage(Images.line_32_vertical).getHeight()
                  + iTranslateY,
               1,
               this.getSliderHeight()
            );
      }

      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.675F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               + 1
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            1,
            this.getSliderHeight() - 2
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               + 1
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            1,
            this.getSliderHeight() - 2
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               + 1
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            1,
            this.getSliderHeight() - 2
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING + 1 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      if (isActive) {
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.045F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               + 1
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               - 1
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.diplo_army)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - (int)((float)ImageManager.getImage(Images.diplo_army).getWidth() * this.getImageScale(this.FONT_SCALE, Images.diplo_army))
               + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING * 2
               - this.getSliderHeight()
               - (int)((float)ImageManager.getImage(Images.diplo_army).getHeight() * this.getImageScale(this.FONT_SCALE, Images.diplo_army))
               - ImageManager.getImage(Images.diplo_army).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.diplo_army).getWidth() * this.getImageScale(this.FONT_SCALE, Images.diplo_army)),
            (int)((float)ImageManager.getImage(Images.diplo_army).getHeight() * this.getImageScale(this.FONT_SCALE, Images.diplo_army))
         );
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * this.FONT_SCALE) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColorLEFT() {
      return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75F);
   }

   protected Color getColor(boolean isActive) {
      return isActive ? new Color(0.71F, 0.71F, 0.71F, 1.0F) : (this.getIsHovered() ? new Color(0.82F, 0.82F, 0.82F, 1.0F) : CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
   }

   @Override
   protected void updateSlider(int nX) {
      super.updateSlider(nX);
      this.setText(CFG.langManager.get("OrXUnitsWillInvade", this.getCurrent()));
   }

   protected int getSliderHeight() {
      return CFG.PADDING * 3;
   }

   protected final float getImageScale(float fScale, int nImageID) {
      return (float)this.getTextHeight() * fScale / (float)ImageManager.getImage(nImageID).getHeight();
   }
}
