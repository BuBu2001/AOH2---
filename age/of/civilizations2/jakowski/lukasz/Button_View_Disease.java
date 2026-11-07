package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_View_Disease extends Button {
   protected static final float FONT_SIZE = 0.65F;
   protected static final float FONT_SIZE2 = 0.6F;
   private boolean row = false;
   private int iProvinceID = 0;
   private String sPopulationPerc;
   private int iPopulationPercWidth = 0;
   protected Color oColor;
   protected String sProvinces;

   protected Button_View_Disease(
      int iRow, Color nColor, String sText, int nOutbreakProvinceID, int nDeaths, String sProvinces, int iPosX, int iPosY, int iWidth
   ) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nOutbreakProvinceID;
      this.oColor = nColor;
      this.sProvinces = sProvinces;
      this.sPopulationPerc = CFG.getNumberWithSpaces("" + nDeaths);
      CFG.glyphLayout.setText(CFG.fontMain, this.sPopulationPerc);
      this.iPopulationPercWidth = (int)(CFG.glyphLayout.width * 0.6F);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275F));
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
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
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
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

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
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      }

      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() * 3 / 4,
            this.getHeight()
         );
      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.075F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING + CFG.PADDING / 2
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING / 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING + CFG.PADDING / 2,
            false,
            true
         );
      if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.225F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               false
            );
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.iProvinceID >= 0 && CFG.game.getProvince(this.iProvinceID).getCivID() > 0) {
         CFG.game
            .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
                     / 2
                  - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            );
      } else {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(
                        (float)ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           * this.getImageScale(ImageManager.getImage(Images.randomCivilizationFlag).getHeight())
                     )
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
               (int)(
                  (float)ImageManager.getImage(Images.randomCivilizationFlag).getWidth()
                     * this.getImageScale(ImageManager.getImage(Images.randomCivilizationFlag).getHeight())
               ),
               (int)(
                  (float)ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     * this.getImageScale(ImageManager.getImage(Images.randomCivilizationFlag).getHeight())
               )
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
                  / 2
               + iTranslateY
               - ImageManager.getImage(Images.flag_rect).getHeight(),
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX()
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + CFG.PADDING * 2
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      ImageManager.getImage(Images.disease)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - (int)((float)ImageManager.getImage(Images.disease).getWidth() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight()))
               - CFG.PADDING * 2
               - this.iPopulationPercWidth
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.disease).getHeight() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight())) / 2
               + iTranslateY
               - ImageManager.getImage(Images.disease).getHeight(),
            (int)((float)ImageManager.getImage(Images.disease).getWidth() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight())),
            (int)((float)ImageManager.getImage(Images.disease).getHeight() * this.getImageScale(ImageManager.getImage(Images.disease).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sPopulationPerc,
         this.getPosX() + this.getWidth() - CFG.PADDING - this.iPopulationPercWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6F)
         );
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }

   private final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / (float)nHeight;
   }

   private final float getImageScale2(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / (float)nHeight;
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         if (this.iProvinceID < 0) {
            this.menuElementHover = null;
            return;
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Name") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Deaths") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sPopulationPerc, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
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
}
