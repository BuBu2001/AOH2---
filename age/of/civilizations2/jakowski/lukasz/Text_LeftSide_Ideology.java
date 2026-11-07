package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_LeftSide_Ideology extends Text_LeftSide {
   private int iCurrent = 0;

   protected Text_LeftSide_Ideology(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.iCurrent < 0) {
         ImageManager.getImage(Images.hre_crown_scaled)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + (
                        this.getHeight()
                           - (int)(
                              (float)ImageManager.getImage(Images.hre_crown_scaled).getHeight()
                                 * this.getImageScale(ImageManager.getImage(Images.hre_crown_scaled).getHeight())
                                 * 6.0F
                                 / 5.0F
                           )
                     )
                     / 2
                  - ImageManager.getImage(Images.hre_crown_scaled).getHeight()
                  + iTranslateY,
               (int)(
                  (float)ImageManager.getImage(Images.hre_crown_scaled).getWidth()
                     * this.getImageScale(ImageManager.getImage(Images.hre_crown_scaled).getHeight())
               ),
               (int)(
                  (float)ImageManager.getImage(Images.hre_crown_scaled).getHeight()
                     * this.getImageScale(ImageManager.getImage(Images.hre_crown_scaled).getHeight())
               )
            );
         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.sText,
            this.getPosX()
               + (int)(
                  (float)ImageManager.getImage(Images.hre_crown_scaled).getWidth()
                     * this.getImageScale(ImageManager.getImage(Images.hre_crown_scaled).getHeight())
               )
               + CFG.PADDING
               + iTranslateX,
            this.getPosY() + (int)((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2 + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      } else {
         CFG.ideologiesManager
            .getIdeology(this.iCurrent)
            .getCrownImageScaled()
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + (
                        this.getHeight()
                           - (int)(
                              (float)CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight()
                                 * this.getImageScale(CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight())
                           )
                     )
                     / 2
                  - CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight()
                  + iTranslateY,
               (int)(
                  (float)CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getWidth()
                     * this.getImageScale(CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight())
               ),
               (int)(
                  (float)CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight()
                     * this.getImageScale(CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight())
               )
            );
         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.sText,
            this.getPosX()
               + (int)(
                  (float)CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getWidth()
                     * this.getImageScale(CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight())
               )
               + CFG.PADDING
               + iTranslateX,
            this.getPosY() + (int)((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2 + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      }
   }

   @Override
   protected int getPosX() {
      return this.iCurrent < 0
         ? super.getPosX()
            - (int)(
               (float)ImageManager.getImage(Images.hre_crown_scaled).getWidth()
                  * this.getImageScale(ImageManager.getImage(Images.hre_crown_scaled).getHeight())
            )
            - CFG.PADDING
         : super.getPosX()
            - (int)(
               (float)CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getWidth()
                  * this.getImageScale(CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight())
            )
            - CFG.PADDING;
   }

   @Override
   protected int getWidth() {
      return this.iCurrent < 0
         ? (int)((float)this.getTextWidth() * 0.7F)
            + (int)(
               (float)ImageManager.getImage(Images.hre_crown_scaled).getWidth()
                  * this.getImageScale(ImageManager.getImage(Images.hre_crown_scaled).getHeight())
            )
            + CFG.PADDING
         : (int)((float)this.getTextWidth() * 0.7F)
            + (int)(
               (float)CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getWidth()
                  * this.getImageScale(CFG.ideologiesManager.getIdeology(this.iCurrent).getCrownImageScaled().getHeight())
            )
            + CFG.PADDING;
   }

   private final float getImageScale(int nImageHeight) {
      return (float)this.getHeight() / (float)nImageHeight < 1.0F ? (float)this.getHeight() / (float)nImageHeight : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                     : (
                        this.iCurrent < 0
                           ? HolyRomanEmpire_Manager.oColorHRE
                           : new Color(
                              CFG.ideologiesManager.getIdeology(this.iCurrent).getColor().r,
                              CFG.ideologiesManager.getIdeology(this.iCurrent).getColor().g,
                              CFG.ideologiesManager.getIdeology(this.iCurrent).getColor().b,
                              1.0F
                           )
                     )
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
   }
}
