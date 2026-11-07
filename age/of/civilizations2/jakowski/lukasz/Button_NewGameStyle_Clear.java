package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_NewGameStyle_Clear extends Button {
   protected Button_NewGameStyle_Clear(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, (int)((float)CFG.BUTTON_HEIGHT * 0.65F), isClickable, true, false, false);
   }

   protected Button_NewGameStyle_Clear(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box_hover).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight()
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box_hover).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               true
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight() * 2 + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight() * 2 + iTranslateY,
               ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               true,
               true
            );
      } else {
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight()
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_box).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight(),
               true
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight() * 2 + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth(),
               ImageManager.getImage(Images.new_game_box).getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight() * 2 + iTranslateY,
               ImageManager.getImage(Images.new_game_box).getWidth(),
               ImageManager.getImage(Images.new_game_box).getHeight(),
               true,
               true
            );
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.fontMain.getData().setScale(0.8F);
         CFG.drawText(
            oSB,
            this.getText(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8F / 2.0F) : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      } else {
         CFG.fontMain.getData().setScale(0.8F);
         CFG.drawText(
            oSB,
            this.getText(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8F / 2.0F) : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return CFG.getColor_CivInfo_Text(isActive, this.getIsHovered());
   }
}
