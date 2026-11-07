package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_New_Game_Players_Report extends Button_New_Game_Players {
   private int iCivID;

   protected Button_New_Game_Players_Report(int nCivID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
      this.iCivID = nCivID;
   }

   protected Button_New_Game_Players_Report(int nCivID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.iCivID = nCivID;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.iCivID < 0) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth() / 2
                  - (int)(((float)this.getTextWidth() * 0.8F + (float)CFG.PADDING + (float)CFG.CIV_FLAG_WIDTH) / 2.0F)
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } else {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth() / 2
                  - (int)(((float)this.getTextWidth() * 0.8F + (float)CFG.PADDING + (float)CFG.CIV_FLAG_WIDTH) / 2.0F)
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth() / 2
               - (int)(((float)this.getTextWidth() * 0.8F + (float)CFG.PADDING + (float)CFG.CIV_FLAG_WIDTH) / 2.0F)
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         oSB,
         this.getTextToDraw(),
         this.getPosX()
            + this.getWidth() / 2
            - (int)(((float)this.getTextWidth() * 0.8F + (float)CFG.PADDING + (float)CFG.CIV_FLAG_WIDTH) / 2.0F)
            + CFG.CIV_FLAG_WIDTH
            + CFG.PADDING
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }
}
