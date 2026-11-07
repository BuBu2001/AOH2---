package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Statistics_CallAlly_Right extends Button_Statistics_CallAlly {
   private String sCallAll;
   private int iCallAllyWidth;

   protected Button_Statistics_CallAlly_Right(int nCivID, int iPosX, int iPosY, int iWidth, boolean isDeclareWar) {
      super(nCivID, iPosX, iPosY, iWidth, isDeclareWar);
      this.callAlly(isDeclareWar);
   }

   protected Button_Statistics_CallAlly_Right(int nCivID, int iPosX, int iPosY, int iWidth, boolean checkbox, boolean isDeclareWar) {
      super(nCivID, iPosX, iPosY, iWidth, checkbox, isDeclareWar);
      this.callAlly(isDeclareWar);
   }

   private final void callAlly(boolean isDeclareWar) {
      if (isDeclareWar) {
         this.sCallAll = "";
         this.iCallAllyWidth = 0;
      } else {
         if (this.iCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
            this.sCallAll = CFG.langManager.get("JoinAWar");
         } else {
            this.sCallAll = CFG.langManager.get("CallAlly");
         }

         CFG.glyphLayout.setText(CFG.fontMain, this.sCallAll);
         this.iCallAllyWidth = (int)(CFG.glyphLayout.width * 0.6F);
      }
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.6F);
      if (this.getClickable() && this.iCallAllyWidth > 0) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.175F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.iCallAllyWidth + CFG.PADDING * 4,
               this.getHeight()
            );
         oSB.setColor(Color.WHITE);
         CFG.drawTextWithShadow(
            oSB,
            this.sCallAll,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.6F) / 2.0F) + iTranslateY,
            CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         );
      }

      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var7) {
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
            this.getPosX() + this.getWidth() - CFG.PADDING * 2 - 2 + iTranslateX,
            this.getPosY()
               + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            2,
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);

      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                  + iTranslateX,
               this.getPosY()
                  + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                  - CFG.game.getCiv(this.iCivID).getFlag().getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                  + iTranslateX,
               this.getPosY()
                  + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
               + iTranslateX,
            this.getPosY()
               + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - 2
            - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            - (int)((float)this.getTextWidth() * 0.6F)
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.6F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_Statistics_CallAlly_Right.this.getCheckboxState()) {
                  oSB.setColor(new Color(0.55F, 0.8F, 0.0F, 0.2F));
               } else {
                  oSB.setColor(new Color(0.8F, 0.137F, 0.0F, 0.15F));
               }
   
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Button_Statistics_CallAlly_Right.this.getPosX() + iTranslateX,
                     Button_Statistics_CallAlly_Right.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + iTranslateY,
                     Button_Statistics_CallAlly_Right.this.getWidth(),
                     Button_Statistics_CallAlly_Right.this.getHeight() - 2,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Statistics_CallAlly_Right.this.getPosX() + iTranslateX,
                     Button_Statistics_CallAlly_Right.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                     Button_Statistics_CallAlly_Right.this.getWidth(),
                     Button_Statistics_CallAlly_Right.this.getHeight() / 4,
                     false,
                     false
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Statistics_CallAlly_Right.this.getPosX() + iTranslateX,
                     Button_Statistics_CallAlly_Right.this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + Button_Statistics_CallAlly_Right.this.getHeight()
                        - 1
                        + iTranslateY
                        - Button_Statistics_CallAlly_Right.this.getHeight() / 4,
                     Button_Statistics_CallAlly_Right.this.getWidth(),
                     Button_Statistics_CallAlly_Right.this.getHeight() / 4,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
            }
         }
         : new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
         };
   }
}
