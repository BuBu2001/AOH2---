package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

class Button_PeaceTreaty_Demands_Province2 extends Button_PeaceTreaty_Demands_Province {
   protected Button_PeaceTreaty_Demands_Province2(int nProvinceID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(nProvinceID, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(Color.WHITE);

      try {
         if (CFG.peaceTreatyData.drawProvinceOwners.get(this.iProvinceID).isTaken > 0
            && CFG.peaceTreatyData.drawProvinceOwners.get(this.iProvinceID).iCivID > 0) {
            CFG.game
               .getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(this.iProvinceID).iCivID)
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX()
                     + MAX_WDITH_LEFT / 2
                     - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale2(Images.flag_rect)) / 2
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale2(Images.flag_rect)) / 2
                     - CFG.game.getCiv(CFG.peaceTreatyData.drawProvinceOwners.get(this.iProvinceID).iCivID).getFlag().getHeight()
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale2(Images.flag_rect)),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale2(Images.flag_rect))
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + MAX_WDITH_LEFT / 2
                     - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale2(Images.flag_rect)) / 2
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale2(Images.flag_rect)) / 2
                     - ImageManager.getImage(Images.flag_rect).getHeight()
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale2(Images.flag_rect)),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale2(Images.flag_rect))
               );
         } else {
            ImageManager.getImage(this.iImageID)
               .draw(
                  oSB,
                  this.getPosX() + MAX_WDITH_LEFT / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
                  this.getPosY() + 1 + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
               );
         }
      } catch (IndexOutOfBoundsException var8) {
      } catch (NullPointerException var9) {
      }

      ImageManager.getImage(Images.victoryPoints)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(
                  (float)ImageManager.getImage(Images.victoryPoints).getWidth() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())
               )
               + iTranslateX,
            this.getPosY()
               + 1
               + this.getHeight() / 2
               - (int)(
                     (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                        * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())
                  )
                  / 2
               + iTranslateY
               - ImageManager.getImage(Images.victoryPoints).getHeight(),
            (int)((float)ImageManager.getImage(Images.victoryPoints).getWidth() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())),
            (int)((float)ImageManager.getImage(Images.victoryPoints).getHeight() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight()))
         );
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
         (float)(
            this.getWidth()
               - CFG.PADDING * 3
               - this.iValueWidth
               - (int)(
                  (float)ImageManager.getImage(Images.victoryPoints).getWidth() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())
               )
         ),
         (float)(-this.getHeight())
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + MAX_WDITH_LEFT + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }

      CFG.drawText(
         oSB,
         this.sValue,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iValueWidth
            - (int)(
               (float)ImageManager.getImage(Images.victoryPoints).getWidth() * this.getImageScale(ImageManager.getImage(Images.victoryPoints).getHeight())
            )
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
