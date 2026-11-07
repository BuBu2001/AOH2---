package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Icon extends Button_Build {
   protected Button_Icon(int nImageID, int iPosX, int iPosY) {
      super("", nImageID, 0, 0, iPosX, iPosY, Button_Diplomacy.iDiploWidth, true, false, 0, 0.0F);
      this.setHeight(CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         ImageManager.getImage(this.iImageID)
            .draw(
               oSB,
               this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
            );
      } catch (IndexOutOfBoundsException var6) {
      }
   }
}
