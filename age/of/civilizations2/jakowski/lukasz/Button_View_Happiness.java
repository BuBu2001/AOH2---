package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_View_Happiness extends Button {
   protected static final float FONT_SIZE = 0.65F;
   protected static final float FONT_SIZE2 = 0.6F;
   private boolean row = false;
   private int iProvinceID = 0;
   private String sPopulationPerc;
   private int iPopulationPercWidth = 0;
   private int iLargestNationality = 0;
   protected Color cColorStability;
   protected boolean isFestivalOrganized = false;

   protected Button_View_Happiness(int iRow, String sText, int nProvinceID, int iPosX, int iPosY, int iWidth, boolean isFestivalOrganized) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nProvinceID;
      this.isFestivalOrganized = isFestivalOrganized;

      for(int i = 1; i < CFG.game.getProvince(this.iProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         if (CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(this.iLargestNationality)
            < CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(i)) {
            this.iLargestNationality = i;
         }
      }

      this.iLargestNationality = CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.iLargestNationality);
      this.sPopulationPerc = "" + (float)((int)(CFG.game.getProvince(this.iProvinceID).getHappiness() * 10000.0F)) / 100.0F + "%";
      CFG.glyphLayout.setText(CFG.fontMain, this.sPopulationPerc);
      this.iPopulationPercWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.cColorStability = CFG.getColorStep(
         CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, (int)(CFG.game.getProvince(this.iProvinceID).getHappiness() * 100.0F), 100, 1.0F
      );
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

      if (this.isFestivalOrganized) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.525F));
         ImageManager.getImage(Images.patt)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.diplo_festival)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iPopulationPercWidth
                  - (int)(
                     (float)ImageManager.getImage(Images.diplo_festival).getWidth()
                        * this.getImageScale2(ImageManager.getImage(Images.diplo_festival).getHeight())
                  )
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(
                        (float)ImageManager.getImage(Images.diplo_festival).getHeight()
                           * this.getImageScale2(ImageManager.getImage(Images.diplo_festival).getHeight())
                     )
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.diplo_festival).getHeight(),
               (int)(
                  (float)ImageManager.getImage(Images.diplo_festival).getWidth()
                     * this.getImageScale2(ImageManager.getImage(Images.diplo_festival).getHeight())
               ),
               (int)(
                  (float)ImageManager.getImage(Images.diplo_festival).getHeight()
                     * this.getImageScale2(ImageManager.getImage(Images.diplo_festival).getHeight())
               )
            );
      }

      if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.825F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      }

      oSB.setColor(Color.WHITE);
   }

   private final float getImageScale2(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / (float)nHeight;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iLargestNationality)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
                  / 2
               - CFG.game.getCiv(this.iLargestNationality).getFlag().getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
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
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         this.sPopulationPerc,
         this.getPosX() + this.getWidth() - CFG.PADDING - this.iPopulationPercWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.cColorStability
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                     : (this.isFestivalOrganized ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_OPTIONS_NS)
               )
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

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (int)(CFG.game.getProvince(this.iProvinceID).getHappiness() * 100.0F) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
            )
         );
         if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(this.iProvinceID).getCivID())) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, CFG.PADDING));
            if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Happiness > 0.0F) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+"
                        + String.format("%.4f", CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Happiness * 100.0F).replace(',', '.')
                        + "%",
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
            } else if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Happiness < 0.0F) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + String.format("%.4f", CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Happiness * 100.0F).replace(',', '.') + "%",
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
            } else {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
         } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, 0));
         }

         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
      }
   }
}
