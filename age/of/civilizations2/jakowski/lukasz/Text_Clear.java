package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_Clear extends Text {
   private int iTurnID = 0;
   private int iLogID = 0;

   protected Text_Clear(int iTurnID, int iLogID, int iPosX, int iPosY, int iWidth, int iHeight) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.iTurnID = iTurnID;
      this.iLogID = iLogID;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setText("");
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
   }

   @Override
   protected int getCurrent() {
      return this.iLogID;
   }

   @Override
   protected int getTextPos() {
      return this.iTurnID;
   }
}
