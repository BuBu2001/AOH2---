package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class Button_Diplomacy_Civilize2 extends Button {
   private boolean row = false;
   private int iCivID = 0;
   private String sTextCostGold;
   private String sTextCostDiplomacy;
   private int iTextCostGoldWidth;
   private int iTextCostDiplomacyWidth;

   protected Button_Diplomacy_Civilize2(int iCivID, int iPosX, int iPosY, int iWidth, boolean isClickable, boolean nCheckbox) {
      super.init(
         CFG.langManager.get("ChangeTypeOfGovernmentTo") + ": ",
         0,
         iPosX,
         iPosY,
         iWidth,
         Math.max(Math.max(CFG.TEXT_HEIGHT, CFG.CIV_FLAG_HEIGHT) + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6F)),
         isClickable,
         true,
         true,
         nCheckbox
      );
      this.iCivID = iCivID;
      this.sTextCostGold = ""
         + (float)((int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL * 100.0F)) / 100.0F;
      this.sTextCostDiplomacy = "1.0";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextCostGold);
      this.iTextCostGoldWidth = (int)(CFG.glyphLayout.width * 0.6F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextCostDiplomacy);
      this.iTextCostDiplomacyWidth = (int)(CFG.glyphLayout.width * 0.6F);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35F));
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
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45F));
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
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_Diplomacy_Civilize2.this.getCheckboxState()) {
                  oSB.setColor(new Color(0.55F, 0.8F, 0.0F, 0.3F));
               } else {
                  oSB.setColor(new Color(0.8F, 0.137F, 0.0F, 0.3F));
               }
   
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_Civilize2.this.getPosX() + iTranslateX,
                     Button_Diplomacy_Civilize2.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
                     Button_Diplomacy_Civilize2.this.getWidth() / 6,
                     Button_Diplomacy_Civilize2.this.getHeight() - 2,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_Civilize2.this.getPosX() + iTranslateX,
                     Button_Diplomacy_Civilize2.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
                     Button_Diplomacy_Civilize2.this.getWidth() / 10,
                     Button_Diplomacy_Civilize2.this.getHeight() - 2,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_Civilize2.this.getPosX() + iTranslateX,
                     Button_Diplomacy_Civilize2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                     Button_Diplomacy_Civilize2.this.getWidth(),
                     CFG.PADDING,
                     false,
                     false
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_Civilize2.this.getPosX() + iTranslateX,
                     Button_Diplomacy_Civilize2.this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + Button_Diplomacy_Civilize2.this.getHeight()
                        - 1
                        + iTranslateY
                        - CFG.PADDING,
                     Button_Diplomacy_Civilize2.this.getWidth(),
                     CFG.PADDING,
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

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
               ImageManager.getImage(Images.flag_rect).getWidth(),
               ImageManager.getImage(Images.flag_rect).getHeight()
            );
      } catch (NullPointerException var9) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               ImageManager.getImage(Images.flag_rect).getWidth(),
               ImageManager.getImage(Images.flag_rect).getHeight()
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
            ImageManager.getImage(Images.flag_rect).getWidth(),
            ImageManager.getImage(Images.flag_rect).getHeight()
         );
      ImageManager.getImage(Images.technology)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING / 2
               + this.getHeight() / 2
               - this.getHeight() / 4
               - (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology) / 2.0F)
               - ImageManager.getImage(Images.technology).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology)),
            (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology))
         );
      ImageManager.getImage(Images.top_diplomacy_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
               + iTranslateX,
            this.getPosY()
               - CFG.PADDING / 2
               + this.getHeight() / 2
               + this.getHeight() / 4
               - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points) / 2.0F)
               - ImageManager.getImage(Images.top_diplomacy_points).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)),
            (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points))
         );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sTextCostGold,
         this.getPosX()
            + this.getWidth()
            - this.iTextCostGoldWidth
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology))
            + iTranslateX,
         this.getPosY() + CFG.PADDING / 2 + this.getHeight() / 2 - this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         CFG.game.getCiv(this.iCivID).getTechnologyLevel()
               >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL
            ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.drawText(
         oSB,
         this.sTextCostDiplomacy,
         this.getPosX()
            + this.getWidth()
            - this.iTextCostDiplomacyWidth
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
            + iTranslateX,
         this.getPosY() - CFG.PADDING / 2 + this.getHeight() / 2 + this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         CFG.game.getCiv(this.iCivID).getDiplomacyPoints() >= 10 ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
         (float)(this.getWidth() - this.getRightWidth() - Button_Diplomacy.iDiploWidth),
         (float)(-this.getHeight())
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );

      try {
         CFG.drawText(
            oSB,
            CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED).getName(),
            this.getPosX() + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7F) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
            new Color(
               CFG.ideologiesManager
                  .getIdeology(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED)
                  .getColor()
                  .r,
               CFG.ideologiesManager
                  .getIdeology(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED)
                  .getColor()
                  .g,
               CFG.ideologiesManager
                  .getIdeology(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED)
                  .getColor()
                  .b,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var8) {
      }

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   protected final int getRightWidth() {
      return Math.max(
         this.iTextCostGoldWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology)),
         this.iTextCostDiplomacyWidth
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.top_diplomacy_points))
      );
   }

   private final float getImageScale(int nImageID) {
      return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.6F / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.6F / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
         );
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeTypeOfGovernmentTo") + "..?", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WhatIsAGovernmentAnyway")));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeTypeOfGovernmentTo") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.ideologiesManager
                  .getIdeology(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED)
                  .getName(),
               CFG.ideologiesManager
                  .getIdeology(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED)
                  .getColor()
            )
         );
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Ideology(
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CAN_BECOME_CIVILIZED, CFG.PADDING, 0
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RequiredTechnologyLevel") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (float)((int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL * 100.0F)) / 100.0F,
               CFG.COLOR_TEXT_TECHNOLOGY
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Image(
               CFG.game.getCiv(this.iCivID).getTechnologyLevel()
                     >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL
                  ? Images.icon_check_true
                  : Images.icon_check_false,
               CFG.PADDING,
               CFG.PADDING
            )
         );
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "[",
               CFG.game.getCiv(this.iCivID).getTechnologyLevel()
                     >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, 0, CFG.PADDING));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (float)((int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0F)) / 100.0F,
               CFG.game.getCiv(this.iCivID).getTechnologyLevel()
                     >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "]",
               CFG.game.getCiv(this.iCivID).getTechnologyLevel()
                     >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("1.0"));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Image(
               CFG.game.getCiv(this.iCivID).getDiplomacyPoints() >= 10 ? Images.icon_check_true : Images.icon_check_false, CFG.PADDING, 0
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected void setMax(int nCurrent) {
      this.row = nCurrent == 1;
   }

   @Override
   protected void setVisible(boolean isVisible) {
      super.setVisible(isVisible);
   }
}
