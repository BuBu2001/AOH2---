package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class TutorialBox {
   private String sText;
   private int iTextWidth;

   protected TutorialBox(String sText) {
      this.sText = sText;
      CFG.glyphLayout.setText(CFG.fontMain, sText);
      this.iTextWidth = (int)CFG.glyphLayout.width;
   }

   protected void draw(SpriteBatch oSB) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
            this.getWidth() - CFG.PADDING * 2,
            this.getHeight()
         );
      oSB.setColor(CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
      ImageManager.getImage(Images.line_32)
         .draw2(
            oSB, this.getPosX() + CFG.PADDING, this.getPosY() + 1 - ImageManager.getImage(Images.line_32).getHeight(), this.getWidth() - CFG.PADDING * 2, 1
         );
      ImageManager.getImage(Images.line_32)
         .draw2(
            oSB,
            this.getPosX() + CFG.PADDING,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32).getHeight(),
            this.getWidth() - CFG.PADDING * 2,
            1
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, this.getHeight(), true, false);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
            CFG.PADDING,
            this.getHeight()
         );
      CFG.drawText(
         oSB, this.sText, this.getPosX() + this.getWidth() / 2 - this.iTextWidth / 2, this.getPosY() + this.getHeight() / 2 - CFG.TEXT_HEIGHT / 2, Color.BLACK
      );
   }

   protected int getPosX() {
      return 0;
   }

   protected int getPosY() {
      return 0;
   }

   protected int getWidth() {
      return CFG.PADDING * 4 + this.iTextWidth;
   }

   protected int getHeight() {
      return CFG.PADDING * 2 + CFG.TEXT_HEIGHT;
   }
}
