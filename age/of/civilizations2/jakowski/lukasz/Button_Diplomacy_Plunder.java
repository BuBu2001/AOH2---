package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Diplomacy_Plunder extends Button_Statistics {
   private static final float FONT_SCALE = 0.7F;
   private String sPopulation;
   private int iPopulation = 0;
   private int iPopulationWidth = 0;
   private String sIncome;
   private int iIncome = 0;
   private int iIncomeWidth = 0;
   private int iIncomeWidth2 = 0;
   private String sDiploCost;
   private int iDiploCostWidth = 0;
   private String sEfficiency;
   private int iEfficiencyWidth = 0;
   private String sEfficiencyPerc;
   private int iEfficiencyPercWidth = 0;

   protected Button_Diplomacy_Plunder(String nDiploCost, int nPopulation, int nIncome, int iPosX, int iPosY, int iWidth) {
      super(CFG.langManager.get("Population") + ": ", 0, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING);
      this.sDiploCost = nDiploCost;
      CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
      this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.iPopulation = nPopulation;
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.sIncome = CFG.langManager.get("ProvinceIncome") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sIncome);
      this.iIncomeWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.iIncome = nIncome;
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iIncome);
      this.iIncomeWidth2 = (int)(CFG.glyphLayout.width * 0.7F);
      this.sEfficiency = CFG.langManager.get("Efficiency") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sEfficiency);
      this.iEfficiencyWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.setCurrent(0);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() * 3 / 5,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.iPopulation,
         this.getPosX() + CFG.PADDING * 2 + (int)((float)this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_POPULATION
      );
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 3 + (int)((float)this.getTextWidth() * 0.7F) + this.iPopulationWidth + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population))) / 2
               - ImageManager.getImage(Images.population).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)),
            (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sIncome,
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + CFG.PADDING * 3 + CFG.TEXT_HEIGHT + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.iIncome,
         this.getPosX() + CFG.PADDING * 2 + this.iIncomeWidth + iTranslateX,
         this.getPosY() + CFG.PADDING * 3 + CFG.TEXT_HEIGHT + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_INGAME_GOLD
      );
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 3 + this.iIncomeWidth + this.iIncomeWidth2 + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 3
               + CFG.TEXT_HEIGHT
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold))) / 2
               - ImageManager.getImage(Images.top_gold).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)),
            (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sEfficiencyPerc,
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iEfficiencyPercWidth + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sEfficiency,
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iEfficiencyPercWidth - this.iEfficiencyWidth + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         Color.WHITE
      );
      ImageManager.getImage(Images.top_movement_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + CFG.TEXT_HEIGHT
               + CFG.PADDING
               + (
                     CFG.TEXT_HEIGHT
                        - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points))
                  )
                  / 2
               - ImageManager.getImage(Images.top_movement_points).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)),
            (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sDiploCost,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iDiploCostWidth
            - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points))
            + iTranslateX,
         this.getPosY()
            + CFG.PADDING * 2
            + CFG.TEXT_HEIGHT
            + CFG.PADDING
            + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F)
            + iTranslateY,
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
               >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_PLUNDER
            ? CFG.COLOR_INGAME_MOVEMENT
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }

   private final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
   }

   @Override
   protected void buildElementHover() {
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.sEfficiencyPerc = "" + nCurrent + "%";
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sEfficiencyPerc);
      this.iEfficiencyPercWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }
}
