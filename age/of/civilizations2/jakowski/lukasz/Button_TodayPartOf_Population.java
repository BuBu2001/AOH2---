package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_TodayPartOf_Population extends Button {
   private boolean row = true;
   private String sPerc;
   private int iPercWidth = 0;
   private String sPopulation;
   private int iPopulationWidth = 0;

   protected Button_TodayPartOf_Population(String nPop, String nPerc, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(CFG.langManager.get("Population") + " ", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.sPopulation = nPop + " ";
      this.sPerc = "" + nPerc + "%";
      CFG.glyphLayout.setText(CFG.fontMain, "[" + this.sPerc + "]");
      this.iPercWidth = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, this.sPopulation);
      this.iPopulationWidth = (int)CFG.glyphLayout.width;
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (!isActive && !this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F));
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65F));
      }

      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() * 4 / 5,
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      oSB.setColor(Color.WHITE);

      try {
         Menu_InGame_FormableCiv_Provinces.lFlag
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - Menu_InGame_FormableCiv_Provinces.lFlag.getHeight() + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (NullPointerException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sPopulation,
         this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + (int)((float)this.getTextWidth() * 0.8F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_POPULATION
      );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         "[" + this.sPerc + "]",
         this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + (int)((float)this.getTextWidth() * 0.8F + (float)this.iPopulationWidth * 0.8F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

         try {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag()), CFG.COLOR_TEXT_NUM_OF_PROVINCES
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         } catch (NullPointerException var4) {
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sPopulation, CFG.COLOR_TEXT_POPULATION));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WorldsPopulation") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sPerc, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var5) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
         : (
            this.getClickable()
               ? (this.getIsHovered() ? new Color(0.68F, 0.68F, 0.68F, 1.0F) : new Color(0.82F, 0.82F, 0.82F, 1.0F))
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 0;
   }

   @Override
   protected int getWidth() {
      return Math.max(
         super.getWidth(),
         CFG.PADDING * 3
            + CFG.CIV_FLAG_WIDTH
            + (int)((float)this.getTextWidth() * 0.8F + (float)this.iPopulationWidth * 0.8F + (float)this.iPercWidth * 0.7F)
            + CFG.PADDING * 2
      );
   }
}
