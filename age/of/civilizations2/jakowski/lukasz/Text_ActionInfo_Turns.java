package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_ActionInfo_Turns extends Text_ActionInfo {
   protected Text_ActionInfo_Turns(String sText, int iPosX, int iPosY) {
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
      ImageManager.getImage(Images.time)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + (int)(((float)this.getHeight() - (float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale()) / 2.0F)
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale()),
            (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale())
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale()) + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(Images.time).getHeight();
   }

   @Override
   protected int getWidth() {
      return super.getWidth() + (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale()) + CFG.PADDING * 2;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getClickable() ? Color.WHITE : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }
}
