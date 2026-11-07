package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_Army extends Text {
   protected Text_Army() {
   }

   protected Text_Army(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   protected Text_Army(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight) {
      super(sText, iTextPositionX, iPosX, iPosY, iHeight);
   }

   protected Text_Army(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.drawArmyText_WithShadow(
         oSB,
         this.sText,
         this.getPosX() + this.textPosition.getTextPosition() + iTranslateX,
         this.getPosY() + (this.getHeight() - this.iTextHeight) / 2 + iTranslateY,
         this.getColor(isActive)
      );
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.glyphLayout.setText(CFG.fontArmy, sText);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         if (super.getWidth() < this.iTextWidth) {
            this.setWidth(this.iTextWidth);
         }

         if (this.getHeight() < this.iTextHeight) {
            this.setHeight(this.iTextHeight);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }
}
