package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Rank extends Button {
   private float fTEXT_SCALE = 1.0F;

   protected Button_Rank(String sText, int iPosX, int iPosY) {
      super.init(
         sText,
         0,
         iPosX,
         iPosY,
         ImageManager.getImage(Images.top_circle).getWidth(),
         ImageManager.getImage(Images.top_circle).getHeight(),
         true,
         true,
         false,
         false,
         null
      );
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.top_circle).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontArmy.getData().setScale(this.fTEXT_SCALE);
      CFG.drawArmyText(
         oSB,
         this.getText(),
         this.getPosX() + (this.getWidth() - this.getTextWidth()) / 2 + iTranslateX,
         this.getPosY() + (this.getHeight() - this.getTextHeight()) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontArmy.getData().setScale(1.0F);
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         int nPlayersCivRankPosition = Integer.parseInt(sText);
         if (nPlayersCivRankPosition < 10) {
            nPlayersCivRankPosition = 99;
         }

         this.fTEXT_SCALE = 1.0F;

         for(int i = 0; i < 70; ++i) {
            CFG.glyphLayout.setText(CFG.fontArmy, "" + nPlayersCivRankPosition);
            if ((float)((int)CFG.glyphLayout.width) <= (float)ImageManager.getImage(Images.top_circle).getWidth() - 10.0F * CFG.GUI_SCALE) {
               break;
            }

            this.fTEXT_SCALE -= 0.01F;
            CFG.fontArmy.getData().setScale(this.fTEXT_SCALE);
         }

         if (Integer.parseInt(sText) < 10) {
            CFG.glyphLayout.setText(CFG.fontArmy, "" + sText);
         }

         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
      } catch (IndexOutOfBoundsException var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      } catch (NullPointerException var9) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var9);
         }
      } finally {
         CFG.fontArmy.getData().setScale(1.0F);
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : new Color(0.92F, 0.94F, 0.92F, 1.0F))
               : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
         );
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK3;
   }
}
