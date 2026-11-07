package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_ActionInfo_MovementCost_Right extends Text {
   protected Text_ActionInfo_MovementCost_Right(String sText, int iPosX, int iPosY) {
      super(sText, CFG.PADDING, iPosX, iPosY, CFG.TEXT_HEIGHT + CFG.PADDING * 2);
      this.setWidth((int)((float)this.getWidth() * 0.8F) + CFG.PADDING * 3 + CFG.PADDING + ImageManager.getImage(Images.top_movement_points).getWidth());
      this.setPosX(CFG.GAME_WIDTH - this.getWidth());
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
      ImageManager.getImage(Images.top_movement_points)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - ImageManager.getImage(Images.top_movement_points).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() / 2
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX()
            - ImageManager.getImage(Images.top_movement_points).getWidth()
            - CFG.PADDING
            + this.getWidth()
            - (int)((float)this.getTextWidth() * 0.8F)
            - CFG.PADDING
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_INGAME_MOVEMENT_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_MOVEMENT_HOVER : CFG.COLOR_INGAME_MOVEMENT);
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_MOVE_ARMY;
   }
}
