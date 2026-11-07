package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_NS_Opinion extends Button {
   protected long lTime = 0L;
   protected float fAlphaMod = 0.0F;
   protected boolean backAnimation = false;
   protected static final float FONTSIZE = 0.7F;
   protected static final float FONTSIZE2 = 0.65F;
   protected static final float TEXT_COST_SCALE = 0.7F;
   protected static final float TEXT_MOVEMENT_COST_SCALE = 0.7F;
   protected int iImageID;
   protected boolean row = false;
   protected String sCost;
   protected int iCostWidth;
   protected boolean canBuild_MoneyCost;
   protected String sMovementCost;
   protected int iMovementCostWidth;
   protected boolean canBuild_Movement;
   private int iCivA;
   private int iCivB;
   private String sCivBName;
   private String sCurrentRelation;
   private int iCurrentRelationWidth = 0;
   private Color relationColor;

   protected Button_NS_Opinion(int iCivA, int iCivB, int nImageID, int nCost, int nDiploCost, int iPosX, int iPosY, int iWidth) {
      super.init(CFG.langManager.get("Opinion") + ": ", 0, iPosX, iPosY, iWidth, CFG.BUTTON_HEIGHT * 4 / 5, true, true, false, false);
      this.iCivA = iCivA;
      this.iCivB = iCivB;
      this.iImageID = nImageID;
      this.sCivBName = CFG.game.getCiv(iCivB).getCivName();
      this.sCurrentRelation = ""
         + (CFG.game.getCivRelation_OfCivB(iCivB, iCivA) > 0.0F ? "+" : "")
         + (float)((int)(CFG.game.getCivRelation_OfCivB(iCivB, iCivA) * 10.0F)) / 10.0F
         + " ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sCurrentRelation);
      this.iCurrentRelationWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.relationColor = CFG.game.getCivRelation_OfCivB(iCivB, iCivA) > 0.0F
         ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
         : (CFG.game.getCivRelation_OfCivB(iCivB, iCivA) == 0.0F ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
      this.canBuild_MoneyCost = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)nCost;
      if (nCost > 0) {
         this.sCost = "" + nCost;
      } else {
         this.sCost = "";
      }

      this.canBuild_Movement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= nDiploCost;
      if (nDiploCost > 0) {
         this.sMovementCost = "" + (float)nDiploCost / 10.0F;
      } else {
         this.sMovementCost = "";
      }

      CFG.fontMain.getData().setScale(0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sCost);
      this.iCostWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sMovementCost);
      this.iMovementCostWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_NS_Opinion.this.getCheckboxState()) {
                  oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.2F));
                  ImageManager.getImage(Images.patt_square)
                     .draw2(
                        oSB,
                        Button_NS_Opinion.this.getPosX() + iTranslateX,
                        Button_NS_Opinion.this.getPosY() - ImageManager.getImage(Images.patt_square).getHeight() + 1 + iTranslateY,
                        Button_Diplomacy.iDiploWidth,
                        Button_NS_Opinion.this.getHeight() - 2,
                        true,
                        false
                     );
                  oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        Button_NS_Opinion.this.getPosX() + iTranslateX,
                        Button_NS_Opinion.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                        Button_Diplomacy.iDiploWidth,
                        Button_NS_Opinion.this.getHeight() / 4,
                        false,
                        false
                     );
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        Button_NS_Opinion.this.getPosX() + iTranslateX,
                        Button_NS_Opinion.this.getPosY()
                           - ImageManager.getImage(Images.gradient).getHeight()
                           + Button_NS_Opinion.this.getHeight()
                           - 1
                           + iTranslateY
                           - Button_NS_Opinion.this.getHeight() / 4,
                        Button_Diplomacy.iDiploWidth,
                        Button_NS_Opinion.this.getHeight() / 4,
                        false,
                        true
                     );
                  oSB.setColor(Color.WHITE);
               }
            }
         }
         : new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
         };
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getHeight()
         );
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
      if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
         if (this.sCost.length() > 0) {
            ImageManager.getImage(Images.top_gold)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.7F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.7F))
                     - ImageManager.getImage(Images.top_gold).getHeight()
                     - CFG.PADDING / 2
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.7F)),
                  (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.7F))
               );
            CFG.fontMain.getData().setScale(0.7F);
            CFG.drawTextWithShadow(
               oSB,
               this.sCost,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - Math.max(
                     (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F)),
                     (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.7F))
                  )
                  - CFG.PADDING
                  - this.iCostWidth
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)((float)this.getTextHeight() * 0.7F) + iTranslateY,
               this.canBuild_MoneyCost ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
         }

         if (this.sMovementCost.length() > 0) {
            ImageManager.getImage(Images.top_diplomacy_points)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F))
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_diplomacy_points).getHeight() + CFG.PADDING / 2 + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F)),
                  (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, 0.7F))
               );
            CFG.fontMain.getData().setScale(0.7F);
            CFG.drawTextWithShadow(
               oSB,
               this.sMovementCost,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iMovementCostWidth
                  - Math.max(
                     (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F)),
                     (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.7F))
                  )
                  - CFG.PADDING
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
               this.canBuild_Movement ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
         }
      } else if (this.sMovementCost.length() > 0) {
         ImageManager.getImage(Images.top_diplomacy_points)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - ImageManager.getImage(Images.top_diplomacy_points).getHeight()
                  - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, 0.7F)) / 2
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F)),
               (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, 0.7F))
            );
         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.sMovementCost,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - this.iMovementCostWidth
               - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, 0.7F))
               - CFG.PADDING
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F) / 2 + iTranslateY,
            this.canBuild_Movement ? CFG.COLOR_INGAME_DIPLOMACY_POINTS : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
         );
      }

      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.diplo_relations)
         .draw(
            oSB,
            this.getPosX()
               + CFG.PADDING
               + Button_Diplomacy.iDiploWidth
               + this.iCurrentRelationWidth
               + CFG.PADDING * 3 / 4
               + (int)((float)this.getTextWidth() * 0.65F)
               + CFG.CIV_FLAG_WIDTH
               + CFG.PADDING
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING / 2
               + (int)((float)this.getTextHeight() * 0.65F) / 2
               - (int)((float)ImageManager.getImage(Images.diplo_relations).getHeight() * this.getImageScale(Images.diplo_relations, 0.65F)) / 2
               - ImageManager.getImage(Images.diplo_relations).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.diplo_relations).getWidth() * this.getImageScale(Images.diplo_relations, 0.65F)),
            (int)((float)ImageManager.getImage(Images.diplo_relations).getHeight() * this.getImageScale(Images.diplo_relations, 0.65F))
         );
      CFG.game
         .getCiv(this.iCivB)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivB).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sCivBName,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sCurrentRelation,
         this.getPosX()
            + CFG.PADDING
            + Button_Diplomacy.iDiploWidth
            + (int)((float)this.getTextWidth() * 0.65F)
            + CFG.CIV_FLAG_WIDTH
            + CFG.PADDING
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         this.relationColor
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   protected float getImageScale(int nImageID, float nTextScale) {
      return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
   }

   private final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT * 0.8F / (float)ImageManager.getImage(nImageID).getHeight();
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.525F)
         );
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivA, 0, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivA).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivB).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivB, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + this.sMovementCost, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 1;
   }
}
