package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_NS_Wonder extends Button {
   protected static final float FONTSIZE = 0.7F;
   protected static final float FONTSIZE2 = 0.65F;
   protected static final float TEXT_COST_SCALE = 0.7F;
   protected static final float TEXT_MOVEMENT_COST_SCALE = 0.7F;
   protected boolean row = false;
   private int iProvinceID = 0;
   private String sDeathsTEXT;
   private int iDeathsTEXTWidth;
   protected Color oColor;
   protected int iImageID;
   protected Color textColor;
   protected int iWonderID;

   protected Button_NS_Wonder(Color nColor, int nProvinceID, int nWonderID, int iPosX, int iPosY, int iWidth) {
      super.init(
         CFG.game.getProvince(nProvinceID).getWonder(nWonderID).sName,
         0,
         iPosX,
         iPosY,
         iWidth,
         (int)((float)CFG.BUTTON_HEIGHT * 3.5F / 5.0F),
         true,
         true,
         false,
         false
      );
      this.iProvinceID = nProvinceID;
      this.oColor = nColor;
      this.iWonderID = nWonderID;
      this.sDeathsTEXT = CFG.game.getProvince(nProvinceID).getName().length() > 0
         ? CFG.game.getProvince(nProvinceID).getName()
         : CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCivName();
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).sName, CFG.COLOR_TEXT_NUM_OF_PROVINCES)
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Wonder(this.iProvinceID, this.iWonderID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.1F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            Button_Diplomacy.iDiploWidth,
            this.getHeight()
         );
      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, 0.575F));
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

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         CFG.game
            .getProvince(this.iProvinceID)
            .getWonder(this.iWonderID)
            .nImage
            .draw(
               oSB,
               this.getPosX()
                  + Button_Diplomacy.iDiploWidth / 2
                  - (int)((float)(CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getWidth() / 2) * CFG.GUI_SCALE)
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight() * CFG.GUI_SCALE / 2.0F)
                  - CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight()
                  + iTranslateY,
               (int)((float)CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getWidth() * CFG.GUI_SCALE),
               (int)((float)CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight() * CFG.GUI_SCALE)
            );
         float fScale = (float)CFG.TEXT_HEIGHT * 0.65F / (float)ImageManager.getImage(Images.flag_rect).getHeight();
         if (CFG.game.getProvince(this.iProvinceID).getCivID() >= 0) {
            CFG.game
               .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     + CFG.PADDING / 2
                     - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight()
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale)
               );
         } else {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale)
               );
         }

         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + iTranslateX,
               this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * fScale)
            );
         oSB.setColor(Color.WHITE);
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
            this.getPosX()
               + CFG.PADDING * 2
               + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * fScale)
               + Button_Diplomacy.iDiploWidth
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
            CFG.COLOR_TEXT_OPTIONS_NS_HOVER
         );
         CFG.fontMain.getData().setScale(1.0F);
      } catch (IndexOutOfBoundsException var6) {
      } catch (NullPointerException var7) {
      }
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
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 1;
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }
}
