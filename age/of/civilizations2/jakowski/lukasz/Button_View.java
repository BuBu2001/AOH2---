package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_View extends Button {
   protected Button_View(String sText, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(sText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(isActive ? Images.top_view_right_h : Images.top_view_right)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getHeight() - ImageManager.getImage(Images.top_view_right).getHeight() * 2,
            this.getWidth(),
            ImageManager.getImage(Images.top_view_right).getHeight(),
            true
         );
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawText(
            oSB,
            this.getText(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.6F / 2.0F) : this.getTextPos()) + iTranslateX,
            this.getPosY()
               + ImageManager.getImage(Images.top_left2).getHeight()
               + (this.getHeight() - ImageManager.getImage(Images.top_left2).getHeight() - 2) / 2
               - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F)
               + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      } else {
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawText(
            oSB,
            this.getText(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.6F / 2.0F) : this.getTextPos()) + iTranslateX,
            this.getPosY()
               + ImageManager.getImage(Images.top_left2).getHeight()
               + (this.getHeight() - ImageManager.getImage(Images.top_left2).getHeight() - 2) / 2
               - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F)
               + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      }
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_TOP_VIEWS_ACTIVE
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_TOP_VIEWS_HOVER : CFG.COLOR_TEXT_TOP_VIEWS) : CFG.COLOR_TEXT_TOP_VIEWS_NOT_CLICKABLE);
   }
}
