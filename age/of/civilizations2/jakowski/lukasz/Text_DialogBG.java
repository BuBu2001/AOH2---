package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_DialogBG extends Text {
   protected Text_DialogBG(String sText, int iPosX, int iPosY) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setText(sText);
      this.textPosition = new Text.TextPosition() {
         @Override
         public int getTextPosition() {
            return 0;
         }
      };
   }

   protected Text_DialogBG(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.iTextPositionX = iTextPositionX;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setText(sText);
      this.updateTextPosition();
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }
}
