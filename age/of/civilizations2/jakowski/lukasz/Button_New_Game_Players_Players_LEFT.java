package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

class Button_New_Game_Players_Players_LEFT extends Button_New_Game_Players {
   private int iPlayerID = 0;

   protected Button_New_Game_Players_Players_LEFT(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable, int iPlayerID) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
      this.iPlayerID = iPlayerID;
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
               this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight()
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight() * 2 + iTranslateY,
               this.getWidth(),
               ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               false,
               true
            );
      } else {
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight()
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight() * 2 + iTranslateY,
               this.getWidth(),
               ImageManager.getImage(Images.new_game_box).getHeight(),
               false,
               true
            );
      }

      oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.215F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - 4,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         try {
            CFG.game
               .getCiv(CFG.game.getPlayer(this.iPlayerID).getCivID())
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.game.getPlayer(this.iPlayerID).getCivID()).getFlag().getHeight()
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         } catch (IndexOutOfBoundsException var9) {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         }

         ImageManager.getImage(Images.flag_rect)
            .draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
         Rectangle clipBounds = new Rectangle(
            (float)(this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 3 + iTranslateX),
            (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
            (float)(this.getWidth() - (CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4)),
            (float)(-this.getHeight())
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         CFG.fontMain.getData().setScale(0.8F);

         try {
            CFG.drawText(
               oSB,
               CFG.game.getPlayer(this.iPlayerID).getCivID() < 0
                  ? CFG.RANDOM_CIVILIZATION
                  : CFG.game.getCiv(CFG.game.getPlayer(this.iPlayerID).getCivID()).getCivName(),
               this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 3 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8F / 2.0F) + iTranslateY,
               this.getColor(isActive)
            );
         } catch (IndexOutOfBoundsException var8) {
         }

         CFG.fontMain.getData().setScale(1.0F);

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var7) {
         }
      } catch (IndexOutOfBoundsException var10) {
      } catch (NullPointerException var11) {
      }
   }

   @Override
   protected int getCurrent() {
      return this.iPlayerID;
   }
}
