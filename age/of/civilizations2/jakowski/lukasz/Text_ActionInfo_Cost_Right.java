package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_ActionInfo_Cost_Right extends Text_ActionInfo {
   protected Text_ActionInfo_Cost_Right(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      iTranslateX += (int)((float)this.getWidth() - (float)this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0F);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.9F));
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(),
            false,
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
            this.getWidth(),
            ImageManager.getImage(Images.civ_name_bg).getHeight(),
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75F));
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 3 + (int)((float)this.iTextWidth * 0.8F) + iTranslateX,
            this.getPosY()
               + (int)(
                     (float)this.getHeight()
                        - (float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())
                  )
                  / 2
               - ImageManager.getImage(Images.top_gold).getHeight(),
            (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())),
            (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight()))
         );
   }

   private final float getImageScale(int nImageHeight) {
      return (float)CFG.TEXT_HEIGHT * 0.8F / (float)nImageHeight < 1.0F ? (float)CFG.TEXT_HEIGHT * 0.8F / (float)nImageHeight : 1.0F;
   }

   @Override
   protected int getPosX() {
      return CFG.GAME_WIDTH - this.getWidth();
   }

   @Override
   protected int getWidth() {
      return (int)((float)this.iTextWidth * 0.8F)
         + CFG.PADDING * 4
         + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight()));
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_GOLD;
   }
}
