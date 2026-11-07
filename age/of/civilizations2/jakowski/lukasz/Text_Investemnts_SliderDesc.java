package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_Investemnts_SliderDesc extends Text {
   protected static final float TEXT2_SCALE = 0.65F;
   private String sText2;

   protected Text_Investemnts_SliderDesc(String sText, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
      this.sText2 = sText2;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.15F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 2 / 5,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() * 2 / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 2 / 5,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.economy)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.economy).getHeight()
               + (this.getHeight() - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(0.65F, Images.economy))) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(0.65F, Images.economy)),
            (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(0.65F, Images.economy))
         );
      ImageManager.getImage(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments()
                  >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).MIN_INVESTMENTS
               ? Images.development
               : Images.development_down
         )
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.development).getWidth() * this.getImageScale(0.65F, Images.development))
               + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.development).getHeight()
               + (this.getHeight() - (int)((float)ImageManager.getImage(Images.development).getHeight() * this.getImageScale(0.65F, Images.development))) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.development).getWidth() * this.getImageScale(0.65F, Images.development)),
            (int)((float)ImageManager.getImage(Images.development).getHeight() * this.getImageScale(0.65F, Images.development))
         );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText2,
         this.getPosX()
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(0.65F, Images.economy))
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.development).getWidth() * this.getImageScale(0.65F, Images.development))
            - (int)((float)this.getTextWidth() * 0.65F)
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   private final float getImageScale(float fScale, int nImageID) {
      return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
   }
}
