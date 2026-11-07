package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Keyboard extends Button {
   protected Button_Keyboard(String sText, int iPosX, int iPosY, int iWidth, int iHeight, Button.TypeOfButton typeOfButton, boolean isClickable) {
      super.init(sText, -1, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, typeOfButton);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      switch(this.typeOfButton) {
         case KEYBOARD:
            if (isActive) {
               CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            } else {
               CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            }
            break;
         case KEYBOARD_NUM:
            if (isActive) {
               CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            } else {
               CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            }
            break;
         case KEYBOARD_ACTIVE:
            if (isActive) {
               CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            } else {
               CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            }
            break;
         case KEYBOARD_SAVE:
            if (isActive) {
               CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            } else {
               CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            }
            break;
         case KEYBOARD_OPTIONS:
            if (isActive) {
               CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            } else {
               CFG.drawRect_NewGameBox_EDGE(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            }
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.3882353F, 0.35686275F, 0.32156864F, 1.0F)
         : (this.getClickable() ? new Color(0.74509805F, 0.73333335F, 0.7176471F, 1.0F) : new Color(0.49F, 0.49F, 0.49F, 0.5F));
   }
}
