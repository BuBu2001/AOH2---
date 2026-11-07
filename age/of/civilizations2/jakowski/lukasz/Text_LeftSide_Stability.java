package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_LeftSide_Stability extends Text_LeftSide {
   private int iCurrent = 0;

   protected Text_LeftSide_Stability(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(Images.diplo_popstability)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(
                           (float)ImageManager.getImage(Images.diplo_popstability).getHeight()
                              * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
                        )
                  )
                  / 2
               - ImageManager.getImage(Images.diplo_popstability).getHeight()
               + iTranslateY,
            (int)(
               (float)ImageManager.getImage(Images.diplo_popstability).getWidth()
                  * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
            ),
            (int)(
               (float)ImageManager.getImage(Images.diplo_popstability).getHeight()
                  * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
            )
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX()
            + (int)(
               (float)ImageManager.getImage(Images.diplo_popstability).getWidth()
                  * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
            )
            + CFG.PADDING
            + iTranslateX,
         this.getPosY() + (int)((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getPosX() {
      return super.getPosX()
         - (int)(
            (float)ImageManager.getImage(Images.diplo_popstability).getWidth()
               * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
         )
         - CFG.PADDING;
   }

   @Override
   protected int getWidth() {
      return (int)((float)this.getTextWidth() * 0.7F)
         + (int)(
            (float)ImageManager.getImage(Images.diplo_popstability).getWidth()
               * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
         )
         + CFG.PADDING;
   }

   private final float getImageScale(int nImageHeight) {
      return (float)this.getHeight() / (float)nImageHeight < 1.0F ? (float)this.getHeight() / (float)nImageHeight : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
                     : CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
               )
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }

   @Override
   protected int getCurrent() {
      return this.iCurrent;
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.iCurrent = nCurrent;
      this.setText("" + this.iCurrent + "%");
   }
}
