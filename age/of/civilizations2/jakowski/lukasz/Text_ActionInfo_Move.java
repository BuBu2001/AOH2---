package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_ActionInfo_Move extends Text_ActionInfo {
   protected Text_ActionInfo_Move(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      iTranslateX -= (int)((float)this.getWidth() - (float)this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0F);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.9F));
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            (int)((float)this.getWidth() * 0.8F) + CFG.PADDING * 3,
            this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               + iTranslateY,
            (int)((float)this.getWidth() * 0.8F) + CFG.PADDING * 3,
            ImageManager.getImage(Images.civ_name_bg).getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75F));
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            (int)((float)this.getWidth() * 0.8F) + CFG.PADDING * 3,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            (int)((float)this.getWidth() * 0.8F) + CFG.PADDING * 3,
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + (int)(((float)this.getHeight() - (float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / 2.0F)
               - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight()
               + iTranslateY,
            (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
            (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + (int)(((float)this.getHeight() - (float)CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / 2.0F)
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
            (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
   }

   @Override
   protected int getWidth() {
      return super.getWidth() + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING * 2;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? new Color(0.56F, 0.56F, 0.56F, 1.0F) : (this.getClickable() ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }
}
