package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Stats_Image extends Button_Stats {
   private int iImageID = 0;

   protected Button_Stats_Image(
      int iImageID, String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, int iHeight, boolean isClickable, boolean isVisible
   ) {
      super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, iHeight, isClickable, isVisible);
      this.iImageID = iImageID;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - ImageManager.getImage(this.iImageID).getHeight()
               - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale() / 2.0F)
               + iTranslateY,
            (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale()),
            (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale())
         );
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.getTextPos() + CFG.PADDING + (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale()) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(this.iMinWidth);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
         if (super.getWidth() < this.iTextWidth + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale())) {
            this.setWidth(this.iTextWidth + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale()));
         }
      } catch (NullPointerException var3) {
      } catch (IllegalArgumentException var4) {
      }
   }

   private final float getImageScale() {
      return 1.0F;
   }
}
