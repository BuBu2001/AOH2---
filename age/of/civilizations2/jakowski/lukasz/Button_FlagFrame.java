package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_FlagFrame extends Button {
   protected Button_FlagFrame(int iPosX, int iPosY, boolean isClickable) {
      super.init(
         "",
         0,
         iPosX,
         iPosY,
         ImageManager.getImage(Images.top_flag_frame).getWidth(),
         ImageManager.getImage(Images.top_flag_frame).getHeight(),
         isClickable,
         true,
         false,
         false,
         null
      );
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      this.getFlag()
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - this.getFlag().getHeight() + iTranslateY,
            ImageManager.getImage(Images.top_flag_frame).getWidth(),
            ImageManager.getImage(Images.top_flag_frame).getHeight()
         );
      if (this.getIsHovered()) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0375F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.425F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight() / 5
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + this.getHeight()
                  - ImageManager.getImage(Images.top_flag_frame).getHeight() / 5
                  - ImageManager.getImage(Images.gradient).getHeight()
                  + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight() / 5,
               false,
               true
            );
         oSB.setColor(Color.WHITE);
      }

      if (isActive) {
         ImageManager.getImage(Images.top_flag_frame_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
      } else {
         ImageManager.getImage(Images.top_flag_frame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
      }

      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(CFG.getActiveCivInfo()).getR() / 255.0F,
               (float)CFG.game.getCiv(CFG.getActiveCivInfo()).getG() / 255.0F,
               (float)CFG.game.getCiv(CFG.getActiveCivInfo()).getB() / 255.0F,
               0.725F
            )
         );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + iTranslateX,
               this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               (int)((float)CFG.PADDING * 1.25F),
               ImageManager.getImage(Images.top_flag_frame).getHeight() - 2
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + iTranslateX,
               this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               (int)((float)CFG.PADDING * 1.25F),
               1
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + iTranslateX,
               this.getPosY()
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  - 2
                  - ImageManager.getImage(Images.slider_gradient).getHeight()
                  + iTranslateY,
               (int)((float)CFG.PADDING * 1.25F),
               1
            );
         oSB.setColor(CFG.COLOR_FLAG_FRAME);
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               (int)((float)CFG.PADDING * 1.25F),
               1
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + iTranslateX,
               this.getPosY()
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  - 1
                  - ImageManager.getImage(Images.slider_gradient).getHeight()
                  + iTranslateY,
               (int)((float)CFG.PADDING * 1.25F),
               1
            );
      } catch (IndexOutOfBoundsException var6) {
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.941F, 1.0F, 0.0F, 1.0F)
         : (this.getClickable() ? new Color(0.376F, 0.388F, 0.376F, 1.0F) : new Color(0.674F, 0.09F, 0.066F, 0.5F));
   }

   protected Image getFlag() {
      return CFG.getActiveCivFlag();
   }
}
