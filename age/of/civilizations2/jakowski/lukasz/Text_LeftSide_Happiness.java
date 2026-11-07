package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_LeftSide_Happiness extends Text_LeftSide {
   private int iCurrent = 0;

   protected Text_LeftSide_Happiness(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(CFG.getHappinesImage(this.getCurrent()))
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(
                           (float)ImageManager.getImage(Images.happiness).getHeight()
                              * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                        )
                  )
                  / 2
               - ImageManager.getImage(Images.happiness).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())),
            (int)((float)ImageManager.getImage(Images.happiness).getHeight() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX()
            + (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
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
         - (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
         - CFG.PADDING;
   }

   @Override
   protected int getWidth() {
      return (int)((float)this.getTextWidth() * 0.7F)
         + (int)((float)ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
         + CFG.PADDING;
   }

   private final float getImageScale(int nImageHeight) {
      return (float)this.getHeight() / (float)nImageHeight < 1.0F ? (float)this.getHeight() / (float)nImageHeight : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_HAPPINESS_ACTIVE
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.COLOR_TEXT_HAPPINESS_HOVER
                     : CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, this.getCurrent(), 100, 1.0F)
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
