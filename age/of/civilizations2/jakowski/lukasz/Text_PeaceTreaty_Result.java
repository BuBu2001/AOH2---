package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Text_PeaceTreaty_Result extends Text {
   protected static final float FONT_SCALE = 0.8F;
   private boolean row = false;
   private int iCivID;
   private Color colorWarScore;
   protected boolean accepted = false;

   protected Text_PeaceTreaty_Result(int nCivID, boolean accepted, int iPosX, int iPosY, int iWidth) {
      super("", CFG.PADDING * 2, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4));
      this.iCivID = nCivID;
      this.accepted = accepted;
      this.colorWarScore = CFG.COLOR_TEXT_MODIFIER_NEUTRAL;
      if (this.getWidth() > iWidth) {
         this.setWidth(iWidth);
      }
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.row) {
         if (!isActive && !this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F));
         } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8F));
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
         this.drawActive(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               false,
               false
            );
         oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
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
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
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
         oSB.setColor(Color.WHITE);
      } else {
         if (!isActive && !this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.75F));
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
         this.drawActive(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
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
      }

      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.775F));
      } else {
         oSB.setColor(Color.WHITE);
      }

      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      if (this.accepted) {
         ImageManager.getImage(Images.icon_check_true)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + (int)((float)this.getTextWidth() * 0.8F) + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(
                        (float)ImageManager.getImage(Images.icon_check_true).getHeight()
                           * this.getImageScale(ImageManager.getImage(Images.icon_check_true).getHeight())
                     )
                     / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.icon_check_true).getHeight(),
               (int)(
                  (float)ImageManager.getImage(Images.icon_check_true).getWidth()
                     * this.getImageScale(ImageManager.getImage(Images.icon_check_true).getHeight())
               ),
               (int)(
                  (float)ImageManager.getImage(Images.icon_check_true).getHeight()
                     * this.getImageScale(ImageManager.getImage(Images.icon_check_true).getHeight())
               )
            );
      } else {
         ImageManager.getImage(Images.time)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + (int)((float)this.getTextWidth() * 0.8F) + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.time).getHeight(),
               (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(ImageManager.getImage(Images.time).getHeight())),
               (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(ImageManager.getImage(Images.time).getHeight()))
            );
      }

      oSB.setColor(Color.WHITE);
   }

   protected void drawActive(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.accepted) {
         oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.825F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() * 3 / 4,
               this.getHeight(),
               false,
               false
            );
      }
   }

   private final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT * 1.0F / (float)nHeight;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(this.iCivID).getIdeologyID(), CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (this.accepted) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PeaceTreaty") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Accepted"), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_true, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      } else {
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PeaceTreaty") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WaitingForResponse"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 0;
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         if (this.getHeight() < this.iTextHeight) {
            this.setHeight(this.iTextHeight);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }

   @Override
   protected void actionElement(int iID) {
      if (this.accepted) {
         CFG.toast.setInView(CFG.game.getCiv(this.iCivID).getCivName() + ": " + CFG.langManager.get("Accepted"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
      } else {
         CFG.toast.setInView(CFG.game.getCiv(this.iCivID).getCivName() + ": " + CFG.langManager.get("WaitingForResponse"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
      }
   }
}
