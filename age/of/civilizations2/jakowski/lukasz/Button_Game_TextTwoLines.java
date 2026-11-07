package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Game_TextTwoLines extends Button_Game {
   private String sTextBot;
   private int iTextBotWidth = 0;

   protected Button_Game_TextTwoLines(String sText, String sTextBot, int iTextPositionX, int iPosX, int iPosY, int nWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
      this.sTextBot = sTextBot;
      CFG.fontMain.getData().setScale(0.8F);
      CFG.glyphLayout.setText(CFG.fontMain, sTextBot);
      this.iTextBotWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getWidth() - super.getTextWidth()) / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - this.iTextHeight + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getWidth() - super.getTextWidth()) / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - this.iTextHeight + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sTextBot,
         this.getPosX() + (this.getWidth() - this.iTextBotWidth) / 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT) / 2 + iTranslateY,
         new Color(0.46F, 0.46F, 0.46F, 1.0F)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getTextWidth() {
      return super.getTextWidth() > this.iTextBotWidth ? super.getTextWidth() : this.iTextBotWidth;
   }

   @Override
   protected int getTextPos() {
      return super.getTextWidth();
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : CFG.COLOR_BUTTON_GAME_TEXT)
               : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
         );
   }
}
