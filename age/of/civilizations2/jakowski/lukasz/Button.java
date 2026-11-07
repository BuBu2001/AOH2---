package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button extends MenuElement {
   protected Button.TypeOfButton typeOfButton;
   private Button.Checkbox oCheckbox;
   protected String sText = null;
   protected int iTextWidth = -1;
   protected int iTextHeight;
   protected int iTextPositionX;
   protected Button.TextPosition textPosition;
   protected boolean checkbox = false;
   private boolean checkboxState = false;

   protected final void init(
      String sText,
      int iTextPositionX,
      int iPosX,
      int iPosY,
      int iWidth,
      int iHeight,
      boolean isClickable,
      boolean isVisible,
      boolean checkbox,
      boolean checkboxState
   ) {
      this.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, isVisible, checkbox, checkboxState, null);
   }

   protected final void init(
      String sText,
      int nTextPositionX,
      int iPosX,
      int iPosY,
      int iWidth,
      int iHeight,
      boolean isClickable,
      boolean isVisible,
      boolean checkbox,
      boolean checkboxState,
      Button.TypeOfButton typeOfButton
   ) {
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setText(sText);
      this.iTextPositionX = nTextPositionX;
      if (nTextPositionX < 0) {
         this.textPosition = new Button.TextPosition() {
            @Override
            public int getTextPosition() {
               return Button.this.getWidth() / 2 - Button.this.getTextWidth() / 2;
            }
         };
      } else {
         this.textPosition = new Button.TextPosition() {
            @Override
            public int getTextPosition() {
               return Button.this.iTextPositionX;
            }
         };
      }

      this.checkbox = checkbox;
      this.checkboxState = checkboxState;
      this.oCheckbox = this.buildCheckbox();
      this.setClickable(isClickable);
      this.setVisible(isVisible);
      this.typeOfButton = typeOfButton;
   }

   @Override
   protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.getClickable()) {
         this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      } else {
         oSB.setColor(1.0F, 1.0F, 1.0F, 0.45F);
         this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
         oSB.setColor(Color.WHITE);
      }

      this.oCheckbox.drawCheckBox(oSB, iTranslateX, iTranslateY, scrollableY);
      this.drawText(oSB, iTranslateX, iTranslateY, isActive);
   }

   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button.this.getCheckboxState()) {
                  oSB.setColor(new Color(0.55F, 0.8F, 0.0F, 0.25F));
               } else {
                  oSB.setColor(new Color(0.8F, 0.137F, 0.0F, 0.25F));
               }
   
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button.this.getPosX() + iTranslateX,
                     Button.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
                     Button.this.getWidth() / 4,
                     Button.this.getHeight() - 2,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button.this.getPosX() + iTranslateX,
                     Button.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                     Button.this.getWidth(),
                     Button.this.getHeight() / 4,
                     false,
                     false
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button.this.getPosX() + iTranslateX,
                     Button.this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + Button.this.getHeight()
                        - 1
                        + iTranslateY
                        - Button.this.getHeight() / 4,
                     Button.this.getWidth(),
                     Button.this.getHeight() / 4,
                     false,
                     true
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

   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.textPosition.getTextPosition() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.textPosition.getTextPosition() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
            this.getColor(isActive)
         );
      }
   }

   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (this.getClickable() ? new Color(0.38F, 0.38F, 0.38F, 1.0F) : new Color(0.49F, 0.49F, 0.49F, 0.5F));
   }

   @Override
   protected String getText() {
      return this.sText;
   }

   @Override
   protected String getTextToDraw() {
      return this.sText;
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         if (sText != null) {
            CFG.glyphLayout.setText(CFG.fontMain, this.getText());
            this.iTextWidth = (int)CFG.glyphLayout.width;
            this.iTextHeight = (int)CFG.glyphLayout.height;
         } else {
            this.iTextWidth = this.iTextHeight = 0;
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (IllegalArgumentException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }
   }

   protected final void setCheckbox(boolean checkbox) {
      this.checkbox = checkbox;
   }

   @Override
   protected boolean getCheckboxState() {
      return this.checkboxState;
   }

   @Override
   protected final void setCheckboxState(boolean checkboxState) {
      this.checkboxState = checkboxState;
   }

   @Override
   protected void setTypeOfButton(Button.TypeOfButton typeOfButton) {
      this.typeOfButton = typeOfButton;
   }

   @Override
   protected int getTextWidth() {
      return this.iTextWidth;
   }

   @Override
   protected int getTextPos() {
      return this.iTextPositionX;
   }

   @Override
   protected int getTextHeight() {
      return this.iTextHeight;
   }

   interface Checkbox {
      void drawCheckBox(SpriteBatch var1, int var2, int var3, boolean var4);
   }

   interface TextPosition {
      int getTextPosition();
   }

   protected static enum TypeOfButton {
      KEYBOARD,
      KEYBOARD_NUM,
      KEYBOARD_ACTIVE,
      KEYBOARD_SAVE,
      KEYBOARD_OPTIONS;
   }
}
