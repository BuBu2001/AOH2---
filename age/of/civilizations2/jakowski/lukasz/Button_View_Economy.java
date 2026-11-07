package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_View_Economy extends Button {
   protected static final float FONT_SIZE = 0.65F;
   protected static final float FONT_SIZE2 = 0.6F;
   private boolean row = false;
   private int iProvinceID = 0;
   private String sPopulation;
   private int iPopulationWidth = 0;
   private String sPopulationPerc;
   private int iPopulationPercWidth = 0;
   protected boolean isFestivalOrganized = false;
   protected String sLevel = "";
   protected int iLevelWidth = 0;

   protected Button_View_Economy(int iRow, String sText, int nProvinceID, int totalPop, int iPosX, int iPosY, int iWidth, boolean isFestivalOrganized) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nProvinceID;
      this.sPopulation = "" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).getEconomy());
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.sPopulationPerc = "" + (float)((int)((float)CFG.game.getProvince(this.iProvinceID).getEconomy() / (float)totalPop * 10000.0F)) / 100.0F + "%";
      CFG.glyphLayout.setText(CFG.fontMain, this.sPopulationPerc);
      this.iPopulationPercWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.isFestivalOrganized = isFestivalOrganized;
      if (CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() > 0) {
         this.sLevel = "" + CFG.game.getProvince(nProvinceID).getLevelOfWorkshop();
         CFG.glyphLayout.setText(CFG.fontMain, this.sLevel);
         this.iLevelWidth = (int)(CFG.glyphLayout.width * 0.6F);
      }
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

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop() > 0 && this.isFestivalOrganized) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.525F));
         ImageManager.getImage(Images.patt)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      } else if (this.isFestivalOrganized) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.525F));
         ImageManager.getImage(Images.patt)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
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

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop() > 0 && this.isFestivalOrganized) {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.b_workshop)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iPopulationPercWidth
                  - (int)(
                     (float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                  )
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(
                        (float)ImageManager.getImage(Images.b_workshop).getHeight()
                           * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                     )
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_workshop).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_workshop).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight()))
            );
         ImageManager.getImage(Images.economy)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - CFG.PADDING
                  - (int)(
                     (float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                  )
                  - this.iPopulationPercWidth
                  - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.economy).getHeight(),
               (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight())),
               (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
            );
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawTextWithShadow(
            oSB,
            this.sLevel,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - CFG.PADDING
               - (int)((float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight()))
               - this.iPopulationPercWidth
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
               - CFG.PADDING
               - this.iLevelWidth
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
         CFG.fontMain.getData().setScale(1.0F);
      } else {
         oSB.setColor(Color.WHITE);
         if (this.isFestivalOrganized) {
            ImageManager.getImage(Images.economy)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - this.iPopulationPercWidth
                     - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
                        / 2
                     + iTranslateY
                     - ImageManager.getImage(Images.economy).getHeight(),
                  (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight())),
                  (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale2(ImageManager.getImage(Images.economy).getHeight()))
               );
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop() > 0) {
            ImageManager.getImage(Images.b_workshop)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - this.iPopulationPercWidth
                     - (int)(
                        (float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                     )
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - (int)(
                           (float)ImageManager.getImage(Images.b_workshop).getHeight()
                              * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                        )
                        / 2
                     + iTranslateY
                     - ImageManager.getImage(Images.b_workshop).getHeight(),
                  (int)((float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())),
                  (int)(
                     (float)ImageManager.getImage(Images.b_workshop).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                  )
               );
            CFG.fontMain.getData().setScale(0.6F);
            CFG.drawTextWithShadow(
               oSB,
               this.sLevel,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iPopulationPercWidth
                  - (int)(
                     (float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight())
                  )
                  - CFG.PADDING
                  - this.iLevelWidth
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
               CFG.COLOR_TEXT_NUM_OF_PROVINCES
            );
            CFG.fontMain.getData().setScale(1.0F);
         }
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
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
            + CFG.PADDING * 2
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawText(
         oSB,
         "" + this.sPopulation,
         this.getPosX()
            + CFG.PADDING * 2
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + (int)((float)this.getTextWidth() * 0.65F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_ECONOMY
      );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sPopulationPerc,
         this.getPosX() + this.getWidth() - CFG.PADDING - this.iPopulationPercWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   private final float getImageScale2(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / (float)nHeight;
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
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.game.getProvince(this.iProvinceID).getName().length() > 0
               ? CFG.game.getProvince(this.iProvinceID).getName()
               : CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getCivName(),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).getEconomy()), CFG.COLOR_TEXT_ECONOMY)
      );
      if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(this.iProvinceID).getCivID())) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, CFG.PADDING));
         if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Economy > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Economy),
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
         } else if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Economy < 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Economy), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
         } else {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Economy, CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
      } else {
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
      }

      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Development") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + (float)((int)(CFG.game.getProvince(this.iProvinceID).getDevelopmentLevel() * 100.0F)) / 100.0F, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (this.isFestivalOrganized && CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).isInvestOrganized_TurnsLeft(this.iProvinceID) > 0) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Invest"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "+"
                  + CFG.getNumberWithSpaces(
                     "" + CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).isInvestOrganized_EconomyLeft(this.iProvinceID)
                  ),
               CFG.COLOR_TEXT_ECONOMY
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               Game_Calendar.getDate_ByTurnID(
                  Game_Calendar.TURN_ID - 4 + CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).isInvestOrganized_TurnsLeft(this.iProvinceID)
               )
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               Game_Calendar.getDate_ByTurnID(
                  Game_Calendar.TURN_ID + CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).isInvestOrganized_TurnsLeft(this.iProvinceID)
               )
            )
         );
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               " ["
                  + CFG.langManager
                     .get("TurnsX", CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).isInvestOrganized_TurnsLeft(this.iProvinceID))
                  + "]",
               CFG.COLOR_TEXT_MODIFIER_NEUTRAL
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
