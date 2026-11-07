package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Stats_FlagName extends Button_Stats {
   private int iCivID = 0;

   protected Button_Stats_FlagName(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, int iHeight, boolean isClickable, boolean isVisible) {
      super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, iHeight, isClickable, isVisible);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.iCivID < 0) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
               (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
               (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
            );
      } else {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
               (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
               (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
            (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
            (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
         );
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.getTextPos() + CFG.PADDING + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.iCivID = nCurrent;
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(this.iMinWidth);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
         if (super.getWidth() < this.iTextWidth + CFG.PADDING * 2 + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) {
            this.setWidth(this.iTextWidth + CFG.PADDING * 2 + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING);
         }
      } catch (NullPointerException var3) {
      } catch (IllegalArgumentException var4) {
      }
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
   }
}
