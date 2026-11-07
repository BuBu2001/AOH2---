package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_FlagAction_Taxes extends Slider {
   protected static final Color bgColor = new Color(0.0F, 0.0F, 0.0F, 0.3F);
   protected static final float TEXT_SCALE = 0.7F;
   protected static final float TEXT2_SCALE = 0.85F;
   private String sTextLeft;
   private String sHappiness;
   private int iHappinessWidth = 0;
   private int iImageID;
   private String sText2;
   private int iText2Width = 0;
   private Color tColor;
   private String sText3;
   private int iText3Width = 0;
   private String sText4;
   private int iText4Width = 0;

   protected Slider_FlagAction_Taxes(String sText, String nTextLeft, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
      this.setText(sText);
      this.sText2 = " " + CFG.langManager.get("PerTurn");
      CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
      this.iText2Width = (int)(CFG.glyphLayout.width * 0.7F);
      this.sTextLeft = nTextLeft;
      this.sText3 = " " + CFG.langManager.get("Happiness") + ": ";
      this.sText4 = "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%";
      CFG.glyphLayout.setText(CFG.fontMain, this.sText3);
      this.iText3Width = (int)(CFG.glyphLayout.width * 0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sText4);
      this.iText4Width = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG_UpdateAnimation();
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
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
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 3 / 5,
            false,
            false
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
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getSliderHeight()
         );
      oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getSliderHeight()
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getSliderHeight()
         );
      oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a * 0.92F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.iCurrentPosX + this.iDifference_CurrentPosX,
            this.getSliderHeight()
         );

      for(int i = 1; i < 10; ++i) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.04F));
         ImageManager.getImage(Images.line_32_vertical)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() / 10 * i + iTranslateX,
               this.getPosY()
                  + this.getHeight()
                  - CFG.PADDING
                  - this.getSliderHeight()
                  - ImageManager.getImage(Images.line_32_vertical).getHeight()
                  + iTranslateY,
               1,
               this.getSliderHeight()
            );
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING + 1 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      if (isActive) {
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.045F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               + 1
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - this.getSliderHeight()
               - 1
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            1,
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.9F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX()
               + (int)(
                  (float)this.getWidth()
                     * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION
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
                     * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION
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
                     * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION
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
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sTextLeft,
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.8F) + iTranslateY,
         this.getColor(isActive)
      );
      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 3
               - this.iHappinessWidth
               - this.iText2Width
               - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85F, this.iImageID))
               + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING * 2
               - this.getSliderHeight()
               - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85F, this.iImageID))
               - ImageManager.getImage(this.iImageID).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85F, this.iImageID)),
            (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85F, this.iImageID))
         );
      CFG.fontMain.getData().setScale(0.7F);
      if (this.getIsHovered() || isActive) {
         oSB.setColor(
            new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.85F)
         );
         ImageManager.getImage(Images.line_32_vertical)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 4
                  - this.iHappinessWidth
                  - this.iText2Width
                  - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85F, this.iImageID))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight()
                  - CFG.PADDING * 2
                  - this.getSliderHeight()
                  - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85F, this.iImageID))
                  - ImageManager.getImage(Images.line_32_vertical).getHeight()
                  + iTranslateY,
               1,
               (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(0.85F, this.iImageID))
            );
         oSB.setColor(Color.WHITE);
         CFG.drawTextWithShadow(
            oSB,
            this.sText4,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 5
               - this.iText4Width
               - this.iHappinessWidth
               - this.iText2Width
               - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85F, this.iImageID))
               + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
         CFG.drawTextWithShadow(
            oSB,
            this.sText3,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 5
               - this.iText4Width
               - this.iText3Width
               - this.iHappinessWidth
               - this.iText2Width
               - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(0.85F, this.iImageID))
               + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
            CFG.COLOR_TEXT_OPTIONS_NS
         );
      }

      CFG.drawTextWithShadow(
         oSB,
         this.sHappiness,
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iHappinessWidth - this.iText2Width + iTranslateX,
         this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
         this.tColor
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sText2,
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iText2Width + iTranslateX,
         this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColorLEFT() {
      return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75F);
   }

   protected Color getColor(boolean isActive) {
      return isActive ? new Color(0.71F, 0.71F, 0.71F, 1.0F) : (this.getIsHovered() ? new Color(0.82F, 0.82F, 0.82F, 1.0F) : Color.WHITE);
   }

   @Override
   protected String getDrawText() {
      return this.getText();
   }

   protected int getSliderHeight() {
      return CFG.PADDING * 3;
   }

   private final float getImageScale(float fScale, int nImageID) {
      return (float)this.getTextHeight() * fScale / (float)ImageManager.getImage(nImageID).getHeight();
   }

   @Override
   protected final void setText(String sText) {
      this.sHappiness = sText.substring(0, sText.length() > 7 ? 7 : sText.length());
      CFG.glyphLayout.setText(CFG.fontMain, this.sHappiness);
      this.iHappinessWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void setMax(int iMax) {
      this.iImageID = iMax == 0 ? Images.happiness : (iMax == 1 ? Images.happiness1 : Images.happiness2);
      this.tColor = iMax == 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
   }
}
