package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_Age extends Slider {
   protected Slider_Age(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   protected Slider_Age(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   protected void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG_UpdateAnimation();
      oSB.setColor(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, 0.8F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight());
      oSB.setColor(this.getColorLEFT().r * 0.45F, this.getColorLEFT().g * 0.45F, this.getColorLEFT().b * 0.45F, 1.0F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getHeight() / 4,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getHeight() / 4,
            false,
            true
         );
      oSB.setColor(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.55F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY() - 1 + iTranslateY,
            this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX,
            this.getHeight()
         );
      oSB.setColor(this.getColorRIGHT().r * 0.65F, this.getColorRIGHT().g * 0.65F, this.getColorRIGHT().b * 0.65F, 1.0F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY() - 1 + iTranslateY,
            this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX,
            this.getHeight() / 4,
            true,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() / 4 - 1 + iTranslateY,
            this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX,
            this.getHeight() / 4,
            true,
            false
         );
      oSB.setColor(Color.BLACK);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeight());
   }

   @Override
   protected Color getColorLEFT() {
      return new Color(0.047058824F, 0.0627451F, 0.15686275F, 1.0F);
   }
}
