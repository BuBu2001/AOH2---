package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_RandomGame_Players_Elector extends Button_New_Game_Players {
   private int iCivID;

   protected Button_RandomGame_Players_Elector(int nCivID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
      this.iCivID = nCivID;
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.new_game_box_line_hover)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_box_line_hover).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - ImageManager.getImage(Images.new_game_box_line_hover).getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.new_game_box_line_hover)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_line_hover).getHeight() * 2 + iTranslateY,
            this.getWidth(),
            ImageManager.getImage(Images.new_game_box_line_hover).getHeight(),
            true,
            true
         );
      if (!CFG.holyRomanEmpire_Manager.getHRE().getIsElector(this.iCivID) && !CFG.holyRomanEmpire_Manager.getHRE().getIsEmperor(this.iCivID)) {
         if (isActive) {
            oSB.setColor(1.0F, 1.0F, 1.0F, 0.275F);
         } else if (this.getIsHovered()) {
            oSB.setColor(1.0F, 1.0F, 1.0F, 0.35F);
         } else {
            oSB.setColor(1.0F, 1.0F, 1.0F, 0.45F);
         }
      } else if (isActive) {
         oSB.setColor(1.0F, 1.0F, 1.0F, 0.75F);
      } else if (this.getIsHovered()) {
         oSB.setColor(1.0F, 1.0F, 1.0F, 0.85F);
      } else {
         oSB.setColor(1.0F, 1.0F, 1.0F, 1.0F);
      }

      ImageManager.getImage(Images.hre_icon)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.hre_icon).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.hre_icon).getHeight() / 2 + iTranslateY,
            true
         );
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 2 + iTranslateY, 1, this.getHeight() - 6);
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }
}
