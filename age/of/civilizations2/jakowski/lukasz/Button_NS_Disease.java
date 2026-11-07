package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_NS_Disease extends Button {
   protected long lTime = 0L;
   protected float fAlphaMod = 0.0F;
   protected boolean backAnimation = false;
   protected static final float FONTSIZE = 0.7F;
   protected static final float FONTSIZE2 = 0.65F;
   protected static final float TEXT_COST_SCALE = 0.7F;
   protected static final float TEXT_MOVEMENT_COST_SCALE = 0.7F;
   protected boolean row = false;
   private int iProvinceID = 0;
   private String sDeathsTEXT;
   private int iDeathsTEXTWidth;
   private String sDeaths;
   private int iDeathsWidth;
   private Color relationColor;
   protected Color oColor;
   protected String sProvinces;
   protected int iDiseaseID;

   protected Button_NS_Disease(
      int iDiseaseID, Color nColor, String sText, int nOutbreakProvinceID, int nDeaths, String sProvinces, int iPosX, int iPosY, int iWidth
   ) {
      super.init(sText, 0, iPosX, iPosY, iWidth, CFG.BUTTON_HEIGHT * 4 / 5, true, true, false, false);
      this.iProvinceID = nOutbreakProvinceID;
      this.oColor = nColor;
      this.iDiseaseID = iDiseaseID;
      this.sDeaths = CFG.getNumberWithSpaces("" + nDeaths);
      CFG.glyphLayout.setText(CFG.fontMain, this.sDeaths);
      this.iDeathsWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.sDeathsTEXT = CFG.langManager.get("Deaths") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sDeathsTEXT);
      this.iDeathsTEXTWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.relationColor = nDeaths > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2;
      this.sProvinces = sProvinces;
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_NS_Disease.this.getCheckboxState()) {
                  oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.2F));
                  ImageManager.getImage(Images.patt_square)
                     .draw2(
                        oSB,
                        Button_NS_Disease.this.getPosX() + iTranslateX,
                        Button_NS_Disease.this.getPosY() - ImageManager.getImage(Images.patt_square).getHeight() + 1 + iTranslateY,
                        Button_Diplomacy.iDiploWidth,
                        Button_NS_Disease.this.getHeight() - 2,
                        true,
                        false
                     );
                  oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        Button_NS_Disease.this.getPosX() + iTranslateX,
                        Button_NS_Disease.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                        Button_Diplomacy.iDiploWidth,
                        Button_NS_Disease.this.getHeight() / 4,
                        false,
                        false
                     );
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        Button_NS_Disease.this.getPosX() + iTranslateX,
                        Button_NS_Disease.this.getPosY()
                           - ImageManager.getImage(Images.gradient).getHeight()
                           + Button_NS_Disease.this.getHeight()
                           - 1
                           + iTranslateY
                           - Button_NS_Disease.this.getHeight() / 4,
                        Button_Diplomacy.iDiploWidth,
                        Button_NS_Disease.this.getHeight() / 4,
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
      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.125F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            Button_Diplomacy.iDiploWidth,
            this.getHeight()
         );
      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.625F));
      ImageManager.getImage(Images.patt_square)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.patt_square).getHeight() + iTranslateY,
            Button_Diplomacy.iDiploWidth,
            this.getHeight(),
            true,
            false
         );
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

      try {
         if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).saveProvinceData.provincePlague != null
            && CFG.game.getProvince(CFG.game.getActiveProvinceID()).saveProvinceData.provincePlague.iPlagueID_InGame == this.iDiseaseID) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.825F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth() - Button_Diplomacy.iDiploWidth,
                  this.getHeight()
               );
         }
      } catch (IndexOutOfBoundsException var6) {
      } catch (NullPointerException var7) {
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

      if (this.iProvinceID >= 0 && CFG.game.getProvince(this.iProvinceID).getCivID() > 0) {
         CFG.game
            .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } else {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY - ImageManager.getImage(Images.flag_rect).getHeight(),
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.disease)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iDeathsWidth + this.iDeathsTEXTWidth + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING / 2
               + (int)((float)this.getTextHeight() * 0.65F) / 2
               - (int)((float)ImageManager.getImage(Images.disease).getHeight() * this.getImageScale(Images.disease, 0.65F)) / 2
               - ImageManager.getImage(Images.disease).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.disease).getWidth() * this.getImageScale(Images.disease, 0.65F)),
            (int)((float)ImageManager.getImage(Images.disease).getHeight() * this.getImageScale(Images.disease, 0.65F))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.sDeathsTEXT,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sDeaths,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iDeathsTEXTWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         this.relationColor
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   protected float getImageScale(int nImageID, float nTextScale) {
      return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
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
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Name") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Deaths") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sDeaths, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OutbreakOfDisease") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.iProvinceID)
                  ? CFG.game.getProvince(this.iProvinceID).getName()
                  : CFG.langManager.get("Undiscovered"),
               CFG.COLOR_TEXT_NUM_OF_PROVINCES
            )
         );
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Flag(
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.iProvinceID) ? CFG.game.getProvince(this.iProvinceID).getCivID() : -1, CFG.PADDING, 0
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sProvinces, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
      } catch (NullPointerException var4) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 1;
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }
}
