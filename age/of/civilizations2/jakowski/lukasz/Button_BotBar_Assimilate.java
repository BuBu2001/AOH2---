package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_BotBar_Assimilate extends Button_BotBar {
   protected Button_BotBar_Assimilate(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, boolean isClickable, boolean isVisible) {
      super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, isClickable, isVisible);
      this.iTextPositionX = CFG.PADDING * 2 + ImageManager.getImage(Images.bot_left).getWidth() / 2;
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.bot_left_red)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.bot_left_red).getHeight() + iTranslateY,
            this.getWidth() + ImageManager.getImage(Images.bot_left_red).getWidth() / 2,
            this.getHeight(),
            true,
            true
         );
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.diplo_popstability)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() + (this.getHeight() - ImageManager.getImage(Images.diplo_popstability).getHeight()) / 2 + iTranslateY
         );
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.getTextPos() + CFG.PADDING + ImageManager.getImage(Images.diplo_popstability).getWidth() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(1);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   @Override
   protected int getWidth() {
      return this.iTextWidth
         + CFG.PADDING * 2
         + 2
         + ImageManager.getImage(Images.diplo_popstability).getWidth()
         + CFG.PADDING * 2
         + ImageManager.getImage(Images.bot_left).getWidth() / 2;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_HAPPINESS_MAX
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_HAPPINESS_HOVER : CFG.COLOR_TEXT_HAPPINESS_ACTIVE)
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }
}
