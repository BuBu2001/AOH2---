package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_FlagActionStats extends Text {
   protected static final float TEXT_SCALE = 0.7F;
   private String s2;
   private int iS2Width = 0;
   private Color oColor2;

   protected Text_FlagActionStats(String sText, int iPosX, int iPosY) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setHeight(CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 1);
      this.s2 = "";
      this.oColor2 = Color.WHITE;
      this.setText(sText);
      this.textPosition = new Text.TextPosition() {
         @Override
         public int getTextPosition() {
            return 0;
         }
      };
   }

   protected Text_FlagActionStats(String sText, String s2, Color oColor2, int iPosX, int iPosY) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setHeight(CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 1);
      this.s2 = s2;
      this.oColor2 = oColor2;
      this.setText(sText);
      this.textPosition = new Text.TextPosition() {
         @Override
         public int getTextPosition() {
            return 0;
         }
      };
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (isActive || this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.375F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               CFG.PADDING
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               CFG.PADDING,
               false,
               true
            );
         oSB.setColor(Color.WHITE);
      }

      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.sText,
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + (this.getHeight() - this.iTextHeight) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawText(
         oSB,
         this.s2,
         this.getPosX() + this.getTextPos() + this.iTextWidth + iTranslateX,
         this.getPosY() + (this.getHeight() - this.iTextHeight) / 2 + iTranslateY,
         this.oColor2
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, this.s2);
         this.iS2Width = (int)(CFG.glyphLayout.width * 0.7F);
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * 0.7F);
         this.iTextHeight = (int)(CFG.glyphLayout.height * 0.7F);
         if (super.getWidth() < this.iTextWidth) {
            this.setWidth(this.iTextWidth);
         }

         if (this.getHeight() < this.iTextHeight) {
            this.setHeight(this.iTextHeight);
         }
      } catch (NullPointerException var3) {
      }
   }

   @Override
   protected int getWidth() {
      return super.getWidth() + this.iS2Width;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
         );
   }
}
