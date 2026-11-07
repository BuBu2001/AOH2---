package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_RTO2 extends Button_RTO {
   protected Button_RTO2(int nID, int nCivID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(nID, nCivID, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (!isActive && !this.getIsHovered()) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.05F));
      } else {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.1F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.45F));
      ImageManager.getImage(Images.line_32)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }
}
