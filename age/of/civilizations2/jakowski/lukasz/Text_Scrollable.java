package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

class Text_Scrollable extends Text {
   private Color textColor;
   private int iScrollPosX = 0;
   private boolean scrollRight = true;
   private long lTime;
   private float fTextScale = 1.0F;
   private boolean center = false;
   private Text_Scrollable.DrawText drawText;

   protected Text_Scrollable(String sText, int iPosX, int iPosY, int iWidth, Color textColor) {
      this.init(sText, iPosX, iPosY, iWidth, 0, textColor, 1.0F, 0);
   }

   protected Text_Scrollable(String sText, int iPosX, int iPosY, int iWidth, Color textColor, float nTextScale) {
      this.init(sText, iPosX, iPosY, iWidth, 0, textColor, nTextScale, 0);
   }

   protected Text_Scrollable(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Color textColor, float nTextScale) {
      this.init(sText, iPosX, iPosY, iWidth, iHeight, textColor, nTextScale, 0);
   }

   protected Text_Scrollable(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Color textColor, float nTextScale, int iTextPos) {
      this.init(sText, iPosX, iPosY, iWidth, iHeight, textColor, nTextScale, iTextPos);
   }

   private final void init(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Color textColor, float nTextScale, int iTextPos) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.iScrollPosX = iTextPos;
      this.fTextScale = nTextScale;
      this.center = iTextPos < 0;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      if (iHeight > 0) {
         this.setHeight(iHeight);
      }

      this.setText(sText);
      this.textColor = textColor;
      this.updateTextPosition();
      if (this.fTextScale != 1.0F) {
         this.drawText = new Text_Scrollable.DrawText() {
            @Override
            public void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(Text_Scrollable.this.fTextScale);
               CFG.drawTextWithShadow(
                  oSB,
                  Text_Scrollable.this.getText(),
                  Text_Scrollable.this.getPosX()
                     + (isActive && !Text_Scrollable.this.center ? Text_Scrollable.this.iScrollPosX : Text_Scrollable.this.textPosition.getTextPosition())
                     + iTranslateX,
                  Text_Scrollable.this.getPosY() + Text_Scrollable.this.getHeight() / 2 - Text_Scrollable.this.iTextHeight / 2 + iTranslateY,
                  Text_Scrollable.this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         };
      } else {
         this.drawText = new Text_Scrollable.DrawText() {
            @Override
            public void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.drawTextWithShadow(
                  oSB,
                  Text_Scrollable.this.getText(),
                  Text_Scrollable.this.getPosX()
                     + (isActive && !Text_Scrollable.this.center ? Text_Scrollable.this.iScrollPosX : Text_Scrollable.this.textPosition.getTextPosition())
                     + iTranslateX,
                  Text_Scrollable.this.getPosY() + Text_Scrollable.this.getHeight() / 2 - Text_Scrollable.this.iTextHeight / 2 + iTranslateY,
                  Text_Scrollable.this.getColor(isActive)
               );
            }
         };
      }
   }

   protected void draw_StartClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
         (float)this.getWidth(),
         (float)(-this.getHeight() - CFG.PADDING * 2)
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
   }

   protected void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawText.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }

   protected void draw_EndClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.draw_StartClip(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      this.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      this.draw_EndClip(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : (this.getClickable() ? this.textColor : new Color(0.78F, 0.78F, 0.78F, 0.7F)));
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.fontMain.getData().setScale(this.fTextScale);
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         CFG.fontMain.getData().setScale(1.0F);
         this.updateTextPosition();
         if (this.getHeight() < this.iTextHeight) {
            this.setHeight(this.iTextHeight);
         }
      } catch (NullPointerException var3) {
      }
   }

   @Override
   protected final void updateTextPosition() {
      if (this.getTextWidth() > this.getWidth() + CFG.PADDING) {
         this.textPosition = new Text.TextPosition() {
            @Override
            public int getTextPosition() {
               if (Text_Scrollable.this.lTime + 35L <= System.currentTimeMillis()) {
                  if (Text_Scrollable.this.scrollRight) {
                     --Text_Scrollable.this.iScrollPosX;
                     if (Text_Scrollable.this.getWidth() - Text_Scrollable.this.iScrollPosX >= Text_Scrollable.this.getTextWidth() + CFG.PADDING) {
                        Text_Scrollable.this.scrollRight = !Text_Scrollable.this.scrollRight;
                     }
                  } else {
                     ++Text_Scrollable.this.iScrollPosX;
                     if (Text_Scrollable.this.iScrollPosX == CFG.PADDING) {
                        Text_Scrollable.this.scrollRight = !Text_Scrollable.this.scrollRight;
                     }
                  }

                  Text_Scrollable.this.lTime = System.currentTimeMillis();
                  CFG.setRender_3(true);
               }

               return Text_Scrollable.this.iScrollPosX;
            }
         };
      } else if (this.center) {
         this.textPosition = new Text.TextPosition() {
            @Override
            public int getTextPosition() {
               return Text_Scrollable.this.getWidth() / 2 - Text_Scrollable.this.getTextWidth() / 2;
            }
         };
      } else {
         this.textPosition = new Text.TextPosition() {
            @Override
            public int getTextPosition() {
               return 0;
            }
         };
      }

      this.iScrollPosX = 0;
      this.scrollRight = true;
   }

   @Override
   protected int getCurrent() {
      return this.iScrollPosX;
   }

   private interface DrawText {
      void draw_Element(SpriteBatch var1, int var2, int var3, boolean var4, boolean var5);
   }
}
