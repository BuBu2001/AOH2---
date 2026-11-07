package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_Scrollable_CNG_Options extends Text_Scrollable {
   protected Text_Scrollable_CNG_Options(String sText, int iPosX, int iPosY, int iWidth, Color textColor) {
      super(sText, iPosX, iPosY, iWidth, textColor);
   }

   protected Text_Scrollable_CNG_Options(String sText, int iPosX, int iPosY, int iWidth, Color textColor, float nTextScale) {
      super(sText, iPosX, iPosY, iWidth, textColor, nTextScale);
   }

   protected Text_Scrollable_CNG_Options(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Color textColor, float nTextScale) {
      super(sText, iPosX, iPosY, iWidth, iHeight, textColor, nTextScale);
   }

   @Override
   protected void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (isActive) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
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
      super.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }
}
