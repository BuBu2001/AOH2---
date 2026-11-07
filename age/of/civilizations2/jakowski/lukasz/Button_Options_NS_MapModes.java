package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Options_NS_MapModes extends Button_Options_NS {
   protected static final float FONT_SCALE = 0.7F;
   private int iCurrent = 0;

   protected Button_Options_NS_MapModes(int iCurrent, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.iCurrent = iCurrent;
   }

   protected Button_Options_NS_MapModes(
      int iCurrent, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
      this.iCurrent = iCurrent;
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
            this.getWidth(),
            this.getHeight() - CFG.PADDING
         );
      oSB.setColor(
         new Color(
            CFG.COLOR_INFO_BOX_GRADIENT.r,
            CFG.COLOR_INFO_BOX_GRADIENT.g,
            CFG.COLOR_INFO_BOX_GRADIENT.b,
            isActive ? 0.225F : (this.getIsHovered() ? 0.175F : 0.4F)
         )
      );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth() / 2,
            this.getHeight() - CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX + this.getWidth() - this.getWidth() / 2,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth() / 2,
            this.getHeight() - CFG.PADDING,
            true,
            false
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            (this.getHeight() - CFG.PADDING) / 5
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + CFG.PADDING / 2
               + iTranslateY
               + (this.getHeight() - CFG.PADDING)
               - (this.getHeight() - CFG.PADDING) / 5
               - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            (this.getHeight() - CFG.PADDING) / 5,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, !isActive && !this.getIsHovered() ? 0.3F : 0.4F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY + (this.getHeight() - CFG.PADDING) - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY + (this.getHeight() - CFG.PADDING) - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, !isActive && !this.getIsHovered() ? 0.225F : 0.325F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.line_32_off1).getHeight(),
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY + (this.getHeight() - CFG.PADDING) - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      CFG.drawRect(
         oSB, this.getPosX() + iTranslateX - 1, this.getPosY() + CFG.PADDING / 2 + iTranslateY - 2, this.getWidth() + 2, this.getHeight() - CFG.PADDING + 2
      );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_Options_NS_MapModes.this.getCheckboxState()) {
                  oSB.setColor(CFG.COLOR_TEXT_CHECKBOX_TRUE);
               } else {
                  oSB.setColor(CFG.COLOR_TEXT_CHECKBOX_FALSE);
               }
   
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Options_NS_MapModes.this.getPosX() + iTranslateX,
                     Button_Options_NS_MapModes.this.getPosY()
                        + CFG.PADDING / 2
                        + (Button_Options_NS_MapModes.this.getHeight() - CFG.PADDING) / 4
                        + iTranslateY
                        - ImageManager.getImage(Images.gradient).getHeight(),
                     Button_Options_NS_MapModes.this.getWidth(),
                     (Button_Options_NS_MapModes.this.getHeight() - CFG.PADDING) * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Options_NS_MapModes.this.getPosX() + iTranslateX,
                     Button_Options_NS_MapModes.this.getPosY()
                        + CFG.PADDING / 2
                        + iTranslateY
                        + (Button_Options_NS_MapModes.this.getHeight() - CFG.PADDING)
                        - (Button_Options_NS_MapModes.this.getHeight() - CFG.PADDING) / 5
                        - ImageManager.getImage(Images.gradient).getHeight(),
                     Button_Options_NS_MapModes.this.getWidth(),
                     (Button_Options_NS_MapModes.this.getHeight() - CFG.PADDING) / 5,
                     false,
                     true
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Options_NS_MapModes.this.getPosX() + iTranslateX,
                     Button_Options_NS_MapModes.this.getPosY() + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Button_Options_NS_MapModes.this.getWidth() / 4,
                     Button_Options_NS_MapModes.this.getHeight(),
                     false,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Options_NS_MapModes.this.getPosX()
                        + Button_Options_NS_MapModes.this.getWidth()
                        - Button_Options_NS_MapModes.this.getWidth() / 4
                        + iTranslateX,
                     Button_Options_NS_MapModes.this.getPosY() + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Button_Options_NS_MapModes.this.getWidth() / 4,
                     Button_Options_NS_MapModes.this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
            }
         }
         : new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
         };
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.7F);
      if (this.getTextPos() < 0) {
         if (isActive) {
            CFG.drawText(
               oSB,
               this.getTextToDraw(),
               this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.7F / 2.0F) + iTranslateX,
               this.getPosY() + CFG.PADDING / 2 + (this.getHeight() - CFG.PADDING) / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
               this.getColor(isActive)
            );
         } else {
            CFG.drawTextWithShadow(
               oSB,
               this.getTextToDraw(),
               this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.7F / 2.0F) + iTranslateX,
               this.getPosY() + CFG.PADDING / 2 + (this.getHeight() - CFG.PADDING) / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
               this.getColor(isActive)
            );
         }
      } else if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + (this.getHeight() - CFG.PADDING) / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + (this.getHeight() - CFG.PADDING) / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected boolean getCheckboxState() {
      return this.getCurrent() == CFG.viewsManager.getActiveViewID();
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.iCurrent = nCurrent;
   }

   @Override
   protected int getCurrent() {
      return this.iCurrent;
   }

   @Override
   protected final Color getColor(boolean isActive) {
      return !isActive && !this.getCheckboxState()
         ? (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_TOP_VIEWS_HOVER : CFG.COLOR_TEXT_TOP_VIEWS) : CFG.COLOR_TEXT_TOP_VIEWS_NOT_CLICKABLE)
         : CFG.COLOR_TEXT_TOP_VIEWS_ACTIVE;
   }
}
