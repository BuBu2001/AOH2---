package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_EditorFlag extends Button {
   private int nCivID = 0;

   protected Button_EditorFlag(int nCivID, int iPosX, int iPosY, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, ImageManager.getImage(Images.top_flag_frame).getWidth(), CFG.BUTTON_HEIGHT, isClickable, true, false, false, null);
      this.setCurrent(nCivID);
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (CFG.game.getCiv(this.nCivID).getCivTag().equals("ran")) {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.nCivID).getR() / 255.0F,
               (float)CFG.game.getCiv(this.nCivID).getG() / 255.0F,
               (float)CFG.game.getCiv(this.nCivID).getB() / 255.0F,
               1.0F
            )
         );
         CFG.game
            .getCiv(this.nCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + CFG.BUTTON_HEIGHT / 2
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                  + iTranslateY
                  - CFG.game.getCiv(this.nCivID).getFlag().getHeight(),
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight()
            );
         oSB.setColor(Color.WHITE);
      } else {
         CFG.game
            .getCiv(this.nCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + CFG.BUTTON_HEIGHT / 2
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                  + iTranslateY
                  - CFG.game.getCiv(this.nCivID).getFlag().getHeight(),
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight()
            );
      }

      if (this.getIsHovered()) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0375F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + CFG.BUTTON_HEIGHT / 2
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                  + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.425F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.gradient).getHeight()
                  + CFG.BUTTON_HEIGHT / 2
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                  + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight() / 5
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.gradient).getHeight()
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 5
                  + CFG.BUTTON_HEIGHT / 2
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                  + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight() / 5,
               false,
               true
            );
         oSB.setColor(Color.WHITE);
      }

      if (!isActive && this.nCivID != CFG.iCreateScenario_AssignProvinces_Civ) {
         ImageManager.getImage(Images.top_flag_frame)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY
            );
      } else {
         ImageManager.getImage(Images.top_flag_frame_h)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY
            );
      }
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.941F, 1.0F, 0.0F, 1.0F)
         : (this.getClickable() ? new Color(0.376F, 0.388F, 0.376F, 1.0F) : new Color(0.674F, 0.09F, 0.066F, 0.5F));
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.nCivID = nCurrent;
   }

   @Override
   protected int getCurrent() {
      return this.nCivID;
   }
}
