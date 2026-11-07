package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_View_Buildings extends Button {
   protected static final float FONT_SIZE = 0.65F;
   protected static final float FONT_SIZE2 = 0.6F;
   private boolean row = false;
   private int iProvinceID = 0;
   private String sPopulation;
   private int iPopulationWidth = 0;
   private int iLargestNationality = 0;
   protected boolean isAssimiliate = false;

   protected Button_View_Buildings(int iRow, String sText, int nProvinceID, int totalPop, int iPosX, int iPosY, int iWidth, boolean isAssimiliate) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nProvinceID;
      this.iLargestNationality = CFG.game.getProvince(this.iProvinceID).getCivID();
      this.sPopulation = "" + totalPop;
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.isAssimiliate = isAssimiliate;
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

      if (this.isAssimiliate) {
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

      oSB.setColor(Color.WHITE);
      int nX = 0;
      if (CFG.game.getProvince(this.iProvinceID).getLevelOfFort() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_fort).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_fort).getHeight()));
         ImageManager.getImage(Images.b_fort)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.b_fort).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_fort).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_fort).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_fort).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_fort).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_fort).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_fort).getHeight()))
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfWatchTower() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_tower).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_tower).getHeight()));
         ImageManager.getImage(Images.b_tower)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.b_tower).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_tower).getHeight()))
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_tower).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_tower).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_tower).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_tower).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_tower).getHeight()))
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfPort() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_port).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_port).getHeight()));
         ImageManager.getImage(Images.b_port)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.b_port).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_port).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_port).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_port).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_port).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_port).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_port).getHeight()))
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfFarm() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_farm).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_farm).getHeight()));
         ImageManager.getImage(Images.b_farm)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.b_farm).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_farm).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_farm).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_farm).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_farm).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_farm).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_farm).getHeight()))
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_workshop).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_workshop).getHeight()));
         ImageManager.getImage(Images.b_workshop)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
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
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfLibrary() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_library).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight()));
         ImageManager.getImage(Images.b_library)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(
                        (float)ImageManager.getImage(Images.b_library).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight())
                     )
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_library).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_library).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_library).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight()))
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfArmoury() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_armoury).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_armoury).getHeight()));
         ImageManager.getImage(Images.b_armoury)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(
                        (float)ImageManager.getImage(Images.b_armoury).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_armoury).getHeight())
                     )
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_armoury).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_armoury).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_armoury).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_armoury).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_armoury).getHeight()))
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfSupply() > 0) {
         nX -= CFG.PADDING
            + (int)((float)ImageManager.getImage(Images.b_supply).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_supply).getHeight()));
         ImageManager.getImage(Images.b_supply)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() + nX + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.b_supply).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_supply).getHeight()))
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_supply).getHeight(),
               (int)((float)ImageManager.getImage(Images.b_supply).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_supply).getHeight())),
               (int)((float)ImageManager.getImage(Images.b_supply).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_supply).getHeight()))
            );
      }

      oSB.setColor(Color.WHITE);
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
      CFG.drawText(
         oSB,
         "" + this.sPopulation,
         this.getPosX()
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + CFG.PADDING * 2
            + (int)((float)this.getTextWidth() * 0.65F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.65F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
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
         if (CFG.game.getProvince(this.iProvinceID).getLevelOfFort() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfFort())), CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfFort(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("HidesTheArmyFromTheSightOfViewOfWatchTower"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(this.iProvinceID).getLevelOfFort()) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfWatchTower() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getTower_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfWatchTower())),
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfWatchTower(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsToSeeTheArmyInNeighboringProvinces"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(this.iProvinceID).getLevelOfWatchTower()) + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfPort() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getPort_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfPort())), CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfPort(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsYourArmyGoToTheSea"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (int)(BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(this.iProvinceID).getLevelOfPort()) * 100.0F) + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfLibrary() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getLibrary_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfLibrary())),
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_library, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfLibrary(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("+1"), CFG.COLOR_TEXT_RESEARCH));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, CFG.PADDING));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager
                     .get(
                        "ResearchPerTurnForEveryXPeopleInProvince",
                        BuildingsManager.getLibrary_ResearchPerPopulation(CFG.game.getProvince(this.iProvinceID).getLevelOfLibrary())
                     ),
                  CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfFarm() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getFarm_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfFarm())), CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfFarm(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (int)(BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(this.iProvinceID).getLevelOfFarm()) * 100.0F) + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getWorkshop_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop())),
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_workshop, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (int)(BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop()) * 100.0F) + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfArmoury() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfArmoury())),
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_armoury, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfArmoury(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         if (CFG.game.getProvince(this.iProvinceID).getLevelOfSupply() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get(BuildingsManager.getSupply_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfSupply())),
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_supply, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getProvince(this.iProvinceID).getLevelOfSupply(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "-" + (int)(BuildingsManager.getSupply_Bonus(CFG.game.getProvince(this.iProvinceID).getLevelOfSupply()) * 100.0F) + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
      }
   }
}
