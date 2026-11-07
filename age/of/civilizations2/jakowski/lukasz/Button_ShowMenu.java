package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_ShowMenu extends Button {
   protected Button_ShowMenu(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - ImageManager.getImage(Images.new_game_top_edge).getHeight()
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_top_edge).getHeight() * 2 + iTranslateY,
            this.getWidth(),
            ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            false,
            true
         );
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
      }

      ImageManager.getImage(Images.btn_show)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_show).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_show).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }
}
