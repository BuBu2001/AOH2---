package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_TitleStyle extends Text {
   protected Text_TitleStyle(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getHeight()
         );
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() + iTranslateY,
            ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(this.getColor_BG().r, this.getColor_BG().g, this.getColor_BG().b, 0.165F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
            this.getWidth() - 4,
            this.getHeight() - 2 + iTranslateY,
            false,
            true
         );
      oSB.setColor(new Color(this.getColor_BG().r, this.getColor_BG().g, this.getColor_BG().b, 0.375F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth() - 4,
            this.getHeight() * 2 / 3 + iTranslateY,
            false,
            true
         );
      oSB.setColor(new Color(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.45F)));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() + this.getHeight() - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 4,
            (this.getHeight() - 2) * 2 / 3,
            false,
            true
         );
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, this.getWidth() - 4);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.85F / 2.0F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.85F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_CIV_NAME : (this.getClickable() ? Color.WHITE : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }

   protected Color getColor_BG() {
      return new Color(0.38039216F, 0.43137255F, 0.6627451F, 1.0F);
   }
}
