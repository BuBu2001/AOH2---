package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_FlagAction_Goods extends Slider_FlagAction {
   protected Slider_FlagAction_Goods(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
      super.draw(oSB, iTranslateX, iTranslateY);
   }

   @Override
   protected void setCurrent(int nCurrent) {
      super.setCurrent(nCurrent);
      int tempSpend = (int)CFG.game_NextTurnUpdate
         .getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).iBudget);
      this.drawSpendings = tempSpend != 0;
      this.sSpendings = CFG.getNumberWithSpaces("" + tempSpend);
      CFG.glyphLayout.setText(CFG.fontMain, this.sSpendings);
      this.iSpendingsWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if ((float)this.getCurrent()
         < CFG.ideologiesManager
               .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
               .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            * 100.0F) {
         oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE.b, 0.275F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + this.getHeight()
                  - CFG.PADDING
                  - this.getSliderHeight()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + iTranslateY,
               (int)(
                  (float)this.getWidth()
                     * CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               ),
               this.getSliderHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  + this.getHeight()
                  - CFG.PADDING
                  - this.getSliderHeight()
                  - ImageManager.getImage(Images.slider_gradient).getHeight()
                  + iTranslateY,
               (int)(
                  (float)this.getWidth()
                     * CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               ),
               this.getSliderHeight()
            );
         oSB.setColor(Color.WHITE);
      }

      super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.9F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX()
               + (int)(
                  (float)this.getWidth()
                     * CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               )
               + iTranslateX,
            this.getPosY()
               + 1
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            1,
            this.getSliderHeight() - 1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.375F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX()
               - 1
               + (int)(
                  (float)this.getWidth()
                     * CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               )
               + iTranslateX,
            this.getPosY()
               + 1
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            1,
            this.getSliderHeight() - 1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX()
               + 1
               + (int)(
                  (float)this.getWidth()
                     * CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               )
               + iTranslateX,
            this.getPosY()
               + 1
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            1,
            this.getSliderHeight() - 1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected int getSliderHeight() {
      return (int)((float)CFG.PADDING * 2.5F);
   }
}
