package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_CNG_Scenario extends Button_Menu {
   private int iScenarioID = 0;
   private String sScenarioName;
   private String sScenarioDate;

   protected Button_CNG_Scenario(int iScenarioID, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super("", iTextPositionX, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 5, isClickable);
      this.iScenarioID = iScenarioID;
      this.sScenarioName = CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(iScenarioID));
      this.sScenarioDate = ""
         + CFG.game.getGameScenarios().getScenarioDay(iScenarioID)
         + " "
         + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(iScenarioID))
         + " "
         + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(iScenarioID))
         + " ["
         + CFG.game.getGameScenarios().getNumOfCivs(iScenarioID)
         + " "
         + CFG.langManager.get("Civilizations")
         + "]";
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.4F));
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.2F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.175F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - 2
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 4 + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4,
            false,
            true
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.35F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.75F));
      ImageManager.getImage(Images.line_32)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32).getHeight() * 2 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawText(
         oSB,
         this.sScenarioName,
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85F) / 2 + iTranslateY,
         this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME
      );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawText(
         oSB,
         this.sScenarioDate,
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + CFG.PADDING * 3 + CFG.TEXT_HEIGHT + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.65F) / 2 + iTranslateY,
         CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected int getCurrent() {
      return this.iScenarioID;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.66F, 0.658F, 0.665F, 1.0F)
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_MENU_TEXT_HOVERED : CFG.COLOR_BUTTON_MENU_TEXT)
               : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
         );
   }
}
