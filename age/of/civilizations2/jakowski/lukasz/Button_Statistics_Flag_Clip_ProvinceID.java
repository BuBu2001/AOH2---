package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

class Button_Statistics_Flag_Clip_ProvinceID extends Button_Statistics_Flag {
   private int iProvinceID;

   protected Button_Statistics_Flag_Clip_ProvinceID(int iProvinceID, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(iProvinceID, sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
      this.iProvinceID = iProvinceID;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + iTranslateX), (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY), (float)this.getWidth(), (float)(-this.getHeight())
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);

      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getR() / 255.0F,
               (float)CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getG() / 255.0F,
               (float)CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getB() / 255.0F,
               0.85F
            )
         );
      } catch (IndexOutOfBoundsException var9) {
         oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
               + iTranslateY,
            2,
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
         );
      oSB.setColor(Color.WHITE);

      try {
         CFG.game
            .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
            .getFlag()
            .draw(
               oSB,
               2 + this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                  - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
            );
      } catch (IndexOutOfBoundsException var8) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               2 + this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            2 + this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX()
            + this.textPosition.getTextPosition()
            + 2
            + CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale())
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }
}
