package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_NewGameBoxStyle_RIGHT_Remove extends Button {
   protected Button_Game_NewGameBoxStyle_RIGHT_Remove(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box_hover).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               true,
               false
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight() * 2 + iTranslateY,
               this.getWidth(),
               ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               true,
               true
            );
         oSB.setColor(1.0F, 1.0F, 1.0F, 0.45F);
         ImageManager.getImage(Images.btn_remove)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_remove).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_remove).getHeight() / 2 + iTranslateY,
               true
            );
      } else {
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight(),
               true,
               false
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight() * 2 + iTranslateY,
               this.getWidth(),
               ImageManager.getImage(Images.new_game_box).getHeight(),
               true,
               true
            );
         ImageManager.getImage(Images.btn_remove)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_remove).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_remove).getHeight() / 2 + iTranslateY,
               true
            );
      }

      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 2 + iTranslateY, 1, this.getHeight() - 6);
      oSB.setColor(Color.WHITE);
   }
}
