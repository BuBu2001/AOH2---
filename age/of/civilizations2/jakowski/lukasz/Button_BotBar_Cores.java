package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_BotBar_Cores extends Button_BotBar {
   private int iProvinceID = 0;

   protected Button_BotBar_Cores(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, boolean isClickable, boolean isVisible) {
      super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, isClickable, isVisible);
      this.iTextPositionX = CFG.PADDING * 2 + ImageManager.getImage(Images.bot_left).getWidth() / 2;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (CFG.game.getProvince(this.iProvinceID).getCore().getCivsSize() == 0) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + this.getTextPos() + this.getTextWidth() + CFG.PADDING + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + this.getHeight() / 2
                  - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                  + iTranslateY,
               (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
               (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + this.getTextPos() + this.getTextWidth() + CFG.PADDING + iTranslateX,
               this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
               (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
               (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
            );
      } else {
         for(int i = 0; i < Math.min(3, CFG.game.getProvince(this.iProvinceID).getCore().getCivsSize()); ++i) {
            CFG.game
               .getCiv(CFG.game.getProvince(this.iProvinceID).getCore().getCivID(i))
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getTextPos()
                     + this.getTextWidth()
                     + CFG.PADDING
                     + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i
                     + iTranslateX,
                  this.getPosY()
                     - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCore().getCivID(i)).getFlag().getHeight()
                     + this.getHeight() / 2
                     - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                     + iTranslateY,
                  (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getTextPos()
                     + this.getTextWidth()
                     + CFG.PADDING
                     + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i
                     + iTranslateX,
                  this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
                  (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
         }
      }

      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }

   @Override
   protected void setCurrent(int iProvinceID) {
      this.iProvinceID = iProvinceID;
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(this.iMinWidth);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   @Override
   protected int getWidth() {
      return this.iTextWidth
         + CFG.PADDING * 2
         + 2
         + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING)
            * (
               CFG.game.getProvince(this.iProvinceID).getCore().getCivsSize() > 1
                  ? Math.min(3, CFG.game.getProvince(this.iProvinceID).getCore().getCivsSize())
                  : 1
            )
         + CFG.PADDING
         + ImageManager.getImage(Images.bot_left).getWidth() / 2;
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
   }
}
