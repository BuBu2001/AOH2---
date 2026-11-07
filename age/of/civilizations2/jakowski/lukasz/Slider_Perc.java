package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_Perc extends Slider {
   protected Slider_Perc(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   protected Slider_Perc(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   protected void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      super.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);

      for(int i = 1; i < 10; ++i) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.04F));
         ImageManager.getImage(Images.line_32_vertical)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() / 10 * i + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
               1,
               this.getHeight()
            );
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.8F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 10 * i + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               1,
               Math.max(CFG.PADDING, this.getHeight() / 6)
            );
      }

      oSB.setColor(Color.WHITE);
   }
}
