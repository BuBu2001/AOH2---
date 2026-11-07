package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_FlagActionSliderStyle_War extends Button_FlagActionSliderStyle {
   private long lTime = 0L;
   private float fAlphaMod = 0.0F;
   private boolean backAnimation = false;

   protected Button_FlagActionSliderStyle_War(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
   }

   protected Button_FlagActionSliderStyle_War(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.getIsHovered()) {
         if (this.lTime < System.currentTimeMillis() - 30L) {
            if (this.backAnimation) {
               this.fAlphaMod -= 0.02F;
               if (this.fAlphaMod < 0.0F) {
                  this.backAnimation = false;
               }
            } else {
               this.fAlphaMod += 0.02F;
               if (this.fAlphaMod > 0.4F) {
                  this.backAnimation = true;
               }
            }

            this.lTime = System.currentTimeMillis();
         }

         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F - this.fAlphaMod));
         CFG.setRender_3(true);
      } else {
         this.backAnimation = false;
         this.fAlphaMod = 0.0F;
         this.lTime = System.currentTimeMillis();
      }

      ImageManager.getImage(Images.diplo_war)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth() / 2
               - (int)(((float)this.getTextWidth() * 0.8F + (float)ImageManager.getImage(Images.diplo_war).getWidth() + (float)CFG.PADDING) / 2.0F)
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_war).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX()
            + (
               this.getTextPos() < 0
                  ? this.getWidth() / 2
                     - (int)(((float)this.getTextWidth() * 0.8F + (float)ImageManager.getImage(Images.diplo_war).getWidth() + (float)CFG.PADDING) / 2.0F)
                     + ImageManager.getImage(Images.diplo_war).getWidth()
                     + CFG.PADDING
                  : this.getTextPos()
            )
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
