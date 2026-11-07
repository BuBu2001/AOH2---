package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Drag_Civilization {
   private int iCivID = 0;
   private int iPosX;
   private int iPosY;
   private boolean visible = false;

   protected final void draw(SpriteBatch oSB, int iTranslateX) {
      if (this.visible) {
         int tFlagW = (int)((float)CFG.game.getCiv(this.iCivID).getCivNameHeight() * 100.0F / (float)CFG.CIV_FLAG_HEIGHT * (float)CFG.CIV_FLAG_WIDTH / 100.0F);
         int tFlagH = (int)((float)(CFG.CIV_FLAG_HEIGHT * CFG.game.getCiv(this.iCivID).getCivNameHeight()) * 100.0F / (float)CFG.CIV_FLAG_HEIGHT / 100.0F);
         oSB.setColor(new Color(0.015686275F, 0.015686275F, 0.015686275F, 1.0F));
         CFG.game
            .drawCivNameBG(
               oSB,
               this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 - CFG.CIV_COLOR_WIDTH - tFlagW / 2 - CFG.CIV_NAME_BG_EXTRA_WIDTH + iTranslateX,
               this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT,
               CFG.game.getCiv(this.iCivID).getCivNameWidth() + CFG.CIV_NAME_BG_EXTRA_WIDTH * 2 + tFlagW + CFG.CIV_COLOR_WIDTH,
               CFG.game.getCiv(this.iCivID).getCivNameHeight() + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2
            );
         CFG.drawText(
            oSB,
            CFG.game.getCiv(this.iCivID).getCivName(),
            this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 + tFlagW / 2 + iTranslateX,
            this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2,
            new Color(0.9843137F, 0.9843137F, 0.9843137F, 1.0F)
         );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH + iTranslateX,
               this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
               tFlagW,
               tFlagH
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.iPosX - CFG.game.getCiv(this.iCivID).getCivNameWidth() / 2 - tFlagW / 2 - CFG.CIV_COLOR_WIDTH + iTranslateX,
               this.iPosY - CFG.game.getCiv(this.iCivID).getCivNameHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight(),
               tFlagW,
               tFlagH
            );
      }
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final void setCivID(int iCivID) {
      this.iCivID = iCivID;
   }

   protected final int getPosX() {
      return this.iPosX;
   }

   protected final void setPosX(int iPosX) {
      this.iPosX = iPosX;
      CFG.setRender_3(true);
   }

   protected final int getPosY() {
      return this.iPosY;
   }

   protected final void setPosY(int iPosY) {
      this.iPosY = iPosY;
   }

   protected final boolean getVisible() {
      return this.visible;
   }

   protected final void setVisible(boolean visible) {
      this.visible = visible;
   }
}
