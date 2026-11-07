package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_PeaceTreaty extends Button {
   private static final float TEXT_SCALE_DATE = 0.7F;
   private static final float TEXT_SCALE_WARSCORE = 0.8F;
   private String sDate = "";
   private int iDateWidth;

   protected Button_PeaceTreaty(String nText, int nWarID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkbox) {
      super.init(nText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, true, checkbox, null);
      int tempScore = CFG.game.getWar(nWarID).getWarScore_PeaceTreaty();
      this.sDate = ""
         + (
            tempScore == 0
               ? CFG.langManager.get("Balanced")
               : (
                  tempScore < 0
                     ? CFG.langManager.get("XInFavorOfAggressors", Math.abs(tempScore) + "%")
                     : CFG.langManager.get("XInFavorOfDefenders", Math.abs(tempScore) + "%")
               )
         );
      CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
      this.iDateWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return new Button.Checkbox() {
         @Override
         public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
         }
      };
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      if (isActive) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.675F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8F));
      } else {
         oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
      }

      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.2F));
      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      if (this.getCheckboxState()) {
         oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.175F));
      } else {
         oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.175F));
      }

      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.525F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 3
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() / 3 + iTranslateY,
            this.getWidth(),
            this.getHeight() / 3,
            false,
            true
         );
      oSB.setColor(CFG.COLOR_FLAG_FRAME);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.75F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         int tX = (int)((float)(this.getWidth() / 2) - Math.max((float)this.iDateWidth, (float)this.getTextWidth() * 0.8F) / 2.0F - (float)(CFG.PADDING * 2));

         for(int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            CFG.game
               .getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID)
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + tX - CFG.CIV_FLAG_WIDTH * (i + 1) - CFG.PADDING * i + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getFlag().getHeight()
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX() + tX - CFG.CIV_FLAG_WIDTH * (i + 1) - CFG.PADDING * i + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         }

         tX = (int)((float)(this.getWidth() / 2) + Math.max((float)this.iDateWidth, (float)this.getTextWidth() * 0.8F) / 2.0F + (float)(CFG.PADDING * 2));

         for(int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
            CFG.game
               .getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID)
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + tX + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getFlag().getHeight()
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX() + tX + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         }
      } catch (IndexOutOfBoundsException var7) {
      }

      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sDate,
         this.getPosX() + (this.getWidth() - this.iDateWidth) / 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.8F) / 2.0F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.8F) - CFG.PADDING / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.475F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + (this.getWidth() - this.iDateWidth) / 2 - CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING
               + (int)((float)this.iTextHeight * 0.8F)
               + iTranslateY
               - ImageManager.getImage(Images.line_32_off1).getHeight(),
            this.iDateWidth + CFG.PADDING * 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.525F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + (this.getWidth() - this.iDateWidth) / 2 - CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING
               + (int)((float)this.iTextHeight * 0.8F)
               + 1
               + iTranslateY
               - ImageManager.getImage(Images.line_32_off1).getHeight(),
            this.iDateWidth + CFG.PADDING * 2,
            1
         );
      oSB.setColor(Color.WHITE);
   }

   private final float getImageScale(int nImageID) {
      return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7F / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7F / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

         for(int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++i) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getCivName()
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, 0, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();

         for(int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); ++i) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(i).iCivID).getCivName()
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var4) {
      } catch (NullPointerException var5) {
      }
   }
}
