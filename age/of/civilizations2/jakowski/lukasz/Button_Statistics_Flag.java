package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Statistics_Flag extends Button_Statistics {
   protected int iCivID;

   protected Button_Statistics_Flag(int iCivID, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
      this.iCivID = iCivID;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               0.85F
            )
         );
      } catch (IndexOutOfBoundsException var7) {
         oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY,
            2,
            CFG.CIV_FLAG_HEIGHT
         );
      oSB.setColor(Color.WHITE);

      try {
         if (this.iCivID >= 0) {
            CFG.game
               .getCiv(this.iCivID)
               .getFlag()
               .draw(
                  oSB,
                  2 + this.getPosX() + this.getTextPos() + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         } else {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  2 + this.getPosX() + this.getTextPos() + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         }
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               2 + this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, 2 + this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      super.drawText(oSB, 2 + iTranslateX + CFG.CIV_FLAG_WIDTH + CFG.PADDING, iTranslateY, isActive);
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }
}
