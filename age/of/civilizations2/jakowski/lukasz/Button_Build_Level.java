package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Build_Level extends Button_Build {
   protected static final float FONT_SIZE_LEVEL = 0.65F;
   private String sLevelID;
   private String sLevel;
   private int iLevelWidth = 0;

   protected Button_Build_Level(
      String sText,
      int nImageID,
      String nLevelID,
      int nCost,
      int nMovementCost,
      int iPosX,
      int iPosY,
      int iWidth,
      boolean isClickable,
      boolean isBuildMax,
      int inConstruction,
      float fTech
   ) {
      super(sText, nImageID, nCost, nMovementCost, iPosX, iPosY, iWidth, isClickable, isBuildMax, inConstruction, fTech);
      this.sLevelID = nLevelID;
      this.sLevel = CFG.langManager.get("Level") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sLevel);
      this.iLevelWidth = (int)(CFG.glyphLayout.width * 0.65F);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.getIsHovered()) {
         if (this.lTime < System.currentTimeMillis() - 30L) {
            if (this.backAnimation) {
               this.fAlphaMod -= 0.02F;
               if (this.fAlphaMod < 0.0F) {
                  this.backAnimation = false;
               }
            } else {
               this.fAlphaMod += 0.02F;
               if (this.fAlphaMod > 0.4F) {
                  this.backAnimation = true;
               }
            }

            this.lTime = System.currentTimeMillis();
         }

         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F - this.fAlphaMod));
         CFG.setRender_3(true);
      } else {
         this.backAnimation = false;
         this.fAlphaMod = 0.0F;
         this.lTime = System.currentTimeMillis();
      }

      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
      if (!this.inConstruction) {
         if (this.iTechWidth > 0) {
            ImageManager.getImage(Images.technology)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - ImageManager.getImage(Images.technology).getHeight()
                     - (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6F)) / 2
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F)),
                  (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6F))
               );
            CFG.fontMain.getData().setScale(0.6F);
            CFG.drawTextWithShadow(
               oSB,
               this.sTech,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iTechWidth
                  - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F))
                  - CFG.PADDING
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F) / 2 + iTranslateY,
               CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
         } else if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
            if (this.sCost.length() > 0) {
               ImageManager.getImage(Images.top_gold)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth()
                        - CFG.PADDING * 2
                        - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                        + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6F))
                        - ImageManager.getImage(Images.top_gold).getHeight()
                        - CFG.PADDING / 2
                        + iTranslateY,
                     (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F)),
                     (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6F))
                  );
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sCost,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - Math.max(
                        (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                        (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                     )
                     - CFG.PADDING
                     - this.iCostWidth
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)((float)this.getTextHeight() * 0.6F) + iTranslateY,
                  this.canBuild_MoneyCost ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               );
            }

            if (this.sMovementCost.length() > 0) {
               ImageManager.getImage(Images.top_movement_points)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth()
                        - CFG.PADDING * 2
                        - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() + CFG.PADDING / 2 + iTranslateY,
                     (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                     (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F))
                  );
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sMovementCost,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - this.iMovementCostWidth
                     - Math.max(
                        (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                        (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                     )
                     - CFG.PADDING
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
                  this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               );
            }
         } else if (this.sMovementCost.length() > 0) {
            ImageManager.getImage(Images.top_movement_points)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - ImageManager.getImage(Images.top_movement_points).getHeight()
                     - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F)) / 2
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                  (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F))
               );
            CFG.fontMain.getData().setScale(0.6F);
            CFG.drawTextWithShadow(
               oSB,
               this.sMovementCost,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iMovementCostWidth
                  - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                  - CFG.PADDING
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F) / 2 + iTranslateY,
               this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
         } else if (this.getCheckboxState()) {
            ImageManager.getImage(Images.icon_check_true)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)((float)ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true, 0.6F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - ImageManager.getImage(Images.icon_check_true).getHeight()
                     - (int)((float)ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true, 0.6F)) / 2
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true, 0.6F)),
                  (int)((float)ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true, 0.6F))
               );
         }
      } else {
         ImageManager.getImage(Images.time)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - ImageManager.getImage(Images.time).getHeight()
                  - (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time, 0.6F)) / 2
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F)),
               (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time, 0.6F))
            );
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawTextWithShadow(
            oSB,
            this.sConstruction,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - this.iConstructionWidth
               - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F))
               - CFG.PADDING
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F) / 2 + iTranslateY,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         );
      }

      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)((float)this.getTextHeight() * 0.7F + (float)this.getTextHeight() * 0.65F + (float)CFG.PADDING) / 2
            + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.sLevel,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)((float)this.getTextHeight() * 0.7F + (float)this.getTextHeight() * 0.65F + (float)CFG.PADDING) / 2
            + CFG.PADDING
            + (int)((float)this.getTextHeight() * 0.7F)
            + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sLevelID,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iLevelWidth + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)((float)this.getTextHeight() * 0.7F + (float)this.getTextHeight() * 0.65F + (float)CFG.PADDING) / 2
            + CFG.PADDING
            + (int)((float)this.getTextHeight() * 0.7F)
            + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
