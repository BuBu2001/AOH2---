package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Alliance extends Button {
   private int iAllianceID = 0;

   protected Button_Alliance(int iAllianceID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.iAllianceID = iAllianceID;
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
      } else {
         oSB.setColor(Color.WHITE);
      }

      ImageManager.getImage(Images.btn_add)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.btn_add).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight()
         );
      ImageManager.getImage(Images.btn_add)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.btn_add).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_add).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight(),
            true
         );
      ImageManager.getImage(Images.btn_add)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight() * 2 + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth(),
            ImageManager.getImage(Images.btn_add).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.btn_add)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_add).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.btn_add).getHeight() + iTranslateY,
            true,
            true
         );
      if (CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() * CFG.CIV_FLAG_WIDTH
            + (CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() + 1) * CFG.PADDING
         < this.getWidth()) {
         iTranslateX += (
                  this.getWidth()
                     - CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() * CFG.CIV_FLAG_WIDTH
                     - (CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() - 1) * CFG.PADDING
               )
               / 2
            - CFG.PADDING;
      }

      oSB.setColor(
         new Color(
            CFG.game.getAlliance(this.iAllianceID).getColorOfAlliance().getR(),
            CFG.game.getAlliance(this.iAllianceID).getColorOfAlliance().getG(),
            CFG.game.getAlliance(this.iAllianceID).getColorOfAlliance().getB(),
            1.0F
         )
      );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + CFG.CIV_COLOR_WIDTH - 1 + iTranslateY,
            CFG.PADDING,
            CFG.CIV_COLOR_WIDTH,
            true,
            false
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + CFG.CIV_COLOR_WIDTH - 1 + iTranslateY,
            CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() * CFG.CIV_FLAG_WIDTH
               + (CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() - 1) * CFG.PADDING
               - CFG.PADDING * 2,
            CFG.CIV_COLOR_WIDTH
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX()
               + CFG.PADDING
               + CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() * CFG.CIV_FLAG_WIDTH
               + (CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize() - 1) * CFG.PADDING
               - CFG.PADDING
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + CFG.CIV_COLOR_WIDTH - 1 + iTranslateY,
            CFG.PADDING,
            CFG.CIV_COLOR_WIDTH
         );
      oSB.setColor(Color.WHITE);

      for(int i = 0; i < CFG.game.getAlliance(this.iAllianceID).getCivilizationsSize(); ++i) {
         CFG.game
            .getCiv(CFG.game.getAlliance(this.iAllianceID).getCivilization(i))
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * (i + 1) + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  + this.getTextHeight() / 2
                  + CFG.PADDING * 2
                  - CFG.game.getCiv(CFG.game.getAlliance(this.iAllianceID).getCivilization(i)).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * (i + 1) + iTranslateX,
               this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING * 2 + iTranslateY
            );
      }
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - this.getTextWidth() / 2 : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - this.getTextWidth() / 2 : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
            this.getColor(isActive)
         );
      }
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? new Color(0.82F, 0.82F, 0.82F, 1.0F) : new Color(0.7F, 0.7F, 0.7F, 1.0F))
               : new Color(0.764F, 0.764F, 0.764F, 0.6F)
         );
   }

   @Override
   public void setText(String sText) {
      super.setText(sText);
      if (this.getTextWidth() > this.getWidth() - CFG.PADDING * 2) {
         this.setWidth(this.getTextWidth() + CFG.PADDING * 2);
      }
   }

   @Override
   protected int getCurrent() {
      return this.iAllianceID;
   }
}
