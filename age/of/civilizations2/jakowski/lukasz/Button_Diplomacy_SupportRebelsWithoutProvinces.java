package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Diplomacy_SupportRebelsWithoutProvinces extends Button_Diplomacy_SupportRebels {
   protected Button_Diplomacy_SupportRebelsWithoutProvinces(
      int i, int iCivA, int iPopulation, int iRevolutionaryRisk, int nProvinces, int iPosX, int iPosY, int iWidth
   ) {
      super(i, iCivA, iPopulation, iRevolutionaryRisk, nProvinces, iPosX, iPosY, iWidth);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivA).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivA).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivA).getB() / 255.0F,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var6) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            2,
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivA)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - CFG.game.getCiv(this.iCivA).getFlag().getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.diplo_revolution)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 3
               - this.iRevolutionaryRiskWidth
               - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * this.getImageScale(Images.diplo_revolution)) / 2
               - ImageManager.getImage(Images.diplo_revolution).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution)),
            (int)((float)ImageManager.getImage(Images.diplo_revolution).getHeight() * this.getImageScale(Images.diplo_revolution))
         );
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 5
               - this.iRevolutionaryRiskWidth
               - this.iPopulationWidth
               - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution))
               - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population)) / 2
               - ImageManager.getImage(Images.population).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)),
            (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + CFG.PADDING * 3
            + 2
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.iRevolutionaryRisk + "%",
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iRevolutionaryRiskWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7F / 2.0F) + iTranslateY,
         CFG.getColorStep(CFG.COLOR_TEXT_REVOLUTION_MIN, CFG.COLOR_TEXT_REVOLUTION_MAX, this.iRevolutionaryRisk, 100, 1.0F)
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.sPopulation,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 4
            - this.iRevolutionaryRiskWidth
            - this.iPopulationWidth
            - (int)((float)ImageManager.getImage(Images.diplo_revolution).getWidth() * this.getImageScale(Images.diplo_revolution))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_POPULATION
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }
}
