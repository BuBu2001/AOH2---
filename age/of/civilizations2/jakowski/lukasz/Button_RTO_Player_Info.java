package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_RTO_Player_Info extends Button_RTO_Player {
   protected Button_RTO_Player_Info(int nID, int nCivID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(nID, nCivID, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(oSB, this.getTextPos() + this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getTextPos() + this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() / 2 + iTranslateY
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, this.getTextPos() + this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getTextPos() + this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - CFG.TEXT_HEIGHT / 2 + iTranslateY,
         CFG.COLOR_TEXT_CIV_NAME
      );
   }
}
