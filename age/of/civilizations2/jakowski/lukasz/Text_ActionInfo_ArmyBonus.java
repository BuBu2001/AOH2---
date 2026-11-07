package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_ActionInfo_ArmyBonus extends Text_ActionInfo {
   private String sValue;
   private int iValueWidth = 0;

   protected Text_ActionInfo_ArmyBonus(String sText, String sValue, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
      this.sValue = sValue;
      CFG.glyphLayout.setText(CFG.fontMain, sValue);
      this.iValueWidth = (int)(CFG.glyphLayout.width * 0.8F);
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
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + this.textPosition.getTextPosition() + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sValue,
         this.getPosX() + this.textPosition.getTextPosition() + (int)((float)this.getTextWidth() * 0.8F) + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_POSITIVE
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getWidth() {
      return super.getWidth() + this.iValueWidth + CFG.PADDING;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME);
   }
}
