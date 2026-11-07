package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider extends MenuElement {
   protected int iMin;
   protected int iMax;
   public int iCurrentPosX = -1;
   private String sText = null;
   private int iCurrent;
   private int iTextWidth = -1;
   private int iTextHeight = -1;
   private long lTime = 0L;
   protected int iDifference_CurrentPosX = 0;
   private int iDifference_PosX = 0;

   protected Slider() {
   }

   protected Slider(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   protected Slider(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   protected void initSlider(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.sText = sText;
      this.iMin = iMin;
      this.iMax = iMax;
      this.iCurrent = iCurrent;
      this.updateSlider(-1);
      this.typeOfElement = MenuElement.TypeOfElement.SLIDER;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      oSB.setColor(Color.WHITE);
   }

   protected final void drawSliderBG_UpdateAnimation() {
      if (this.iDifference_CurrentPosX != 0) {
         if (this.lTime == 0L) {
            this.lTime = System.currentTimeMillis();
         }

         this.iDifference_CurrentPosX = this.iDifference_PosX
            - (int)((float)this.iDifference_PosX * ((float)(System.currentTimeMillis() - this.lTime) / 375.0F));
         CFG.setRender_3(true);
         if (System.currentTimeMillis() >= this.lTime + 375L) {
            this.iDifference_CurrentPosX = 0;
         }
      }
   }

   protected void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG_UpdateAnimation();
      oSB.setColor(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, 0.7F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight());
      oSB.setColor(this.getColorLEFT().r * 1.3F, this.getColorLEFT().g * 1.3F, this.getColorLEFT().b * 1.3F, 1.0F);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.6F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY() - 1 + iTranslateY,
            this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX,
            this.getHeight()
         );
      oSB.setColor(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.6F);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY() - 1 + iTranslateY,
            this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX,
            this.getHeight(),
            true,
            false
         );
   }

   protected Color getColorLEFT() {
      return CFG.COLOR_SLIDER_LEFT_BG;
   }

   protected Color getColorRIGHT() {
      return CFG.COLOR_SLIDER_RIGHT_BG;
   }

   protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.drawTextWithShadow(
         oSB,
         this.getDrawText(),
         this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
         new Color(0.945F, 0.945F, 0.945F, 1.0F)
      );
   }

   protected void drawSliderBorder(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(0.008F, 0.012F, 0.014F, 0.3F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 4 + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4,
            false,
            true
         );
      oSB.setColor(0.05F, 0.06F, 0.065F, 0.45F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY + 1, this.getWidth(), 1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() - 2 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, this.getClickable() ? 1.0F : 0.5F));
      ImageManager.getImage(Images.slider_rect_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_rect_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight()
         );
      ImageManager.getImage(Images.slider_rect_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_rect_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.slider_rect_edge).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.slider_rect_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight() * 2 + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth(),
            ImageManager.getImage(Images.slider_rect_edge).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.slider_rect_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.slider_rect_edge).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.slider_rect_edge).getHeight() * 2 + iTranslateY,
            ImageManager.getImage(Images.slider_rect_edge).getWidth(),
            ImageManager.getImage(Images.slider_rect_edge).getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.3F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() - 1 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }

   protected String getDrawText() {
      return this.sText + this.iCurrent;
   }

   @Override
   protected void updateSlider(int nX) {
      if (nX >= 0) {
         nX -= this.getPosX();
         this.iCurrent = (int)((float)nX * 100.0F / (float)this.getWidth() * (float)(this.iMax - this.iMin) / 100.0F + (float)this.iMin);
      }

      if (this.iCurrent < this.iMin) {
         this.iCurrent = this.iMin;
      } else if (this.iCurrent > this.iMax) {
         this.iCurrent = this.iMax;
      }

      this.updateCurrentPosX();
      this.updateTextWidth();
      this.iDifference_CurrentPosX = 0;
      this.iDifference_PosX = 0;
   }

   private final void updateCurrentPosX() {
      this.iCurrentPosX = (int)((float)(this.iCurrent - this.iMin) * 100.0F / (float)(this.iMax - this.iMin) * (float)this.getWidth() / 100.0F);
   }

   protected final void updateTextWidth() {
      CFG.glyphLayout.setText(CFG.fontMain, this.getDrawText());
      this.iTextWidth = (int)CFG.glyphLayout.width;
      this.iTextHeight = (int)CFG.glyphLayout.height;
   }

   @Override
   protected final String getText() {
      return this.sText;
   }

   @Override
   protected void setText(String sText) {
      this.sText = sText;
      this.updateTextWidth();
   }

   @Override
   protected void setCurrent(int nCurrent) {
      int tempCurr = this.iCurrentPosX;
      if (nCurrent > this.iMax) {
         this.iCurrent = this.iMax;
      } else if (nCurrent < this.iMin) {
         this.iCurrent = this.iMin;
      } else {
         this.iCurrent = nCurrent;
      }

      this.updateCurrentPosX();
      this.updateTextWidth();
      if (tempCurr != this.iCurrentPosX) {
         this.lTime = 0L;
         this.iDifference_CurrentPosX = tempCurr - this.iCurrentPosX;
         this.iDifference_PosX = this.iDifference_CurrentPosX;
      }

      CFG.setRender_3(true);
   }

   @Override
   protected final int getCurrent() {
      return this.iCurrent;
   }

   @Override
   protected int getTextWidth() {
      return this.iTextWidth;
   }

   @Override
   protected final int getTextHeight() {
      return this.iTextHeight;
   }

   @Override
   protected void setMin(int iMin) {
      this.iMin = iMin;
      if (this.iCurrent < iMin) {
         this.iCurrent = iMin;
         this.updateTextWidth();
      }
   }

   @Override
   protected void setMax(int iMax) {
      this.iMax = iMax;
      if (this.iCurrent > iMax) {
         this.iCurrent = iMax;
         this.updateTextWidth();
      }
   }

   @Override
   protected int getTextPos() {
      return this.iMax;
   }

   @Override
   protected void srollByWheel(int nScoll) {
      this.setCurrent(this.getCurrent() + nScoll);
   }

   @Override
   protected boolean getIsScrollable() {
      return true;
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK3;
   }
}
