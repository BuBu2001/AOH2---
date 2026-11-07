package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_FlagAction_GraphModes extends Button_Menu {
   private int iModeID = 0;

   protected Button_FlagAction_GraphModes(String sText, int nModeID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, -1, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.iModeID = nModeID;
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.125F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      if (!isActive && !this.getIsHovered() && this.iModeID != Menu_InGame_GraphManager.iActiveGraphID) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.85F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.6F) / 2.0F) + iTranslateX,
               this.getPosY()
                  + (int)(((float)this.getHeight() + (float)this.getTextHeight() * 0.6F) / 2.0F)
                  + CFG.PADDING
                  + iTranslateY
                  - ImageManager.getImage(Images.line_32_off1).getHeight(),
               (int)((float)this.getTextWidth() * 0.6F),
               1
            );
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - 1 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.6F);
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.6F) / 2.0F) + iTranslateX,
            this.getPosY() + (int)(((float)this.getHeight() - (float)this.getTextHeight() * 0.6F) / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * 0.6F) / 2.0F) + iTranslateX,
            this.getPosY() + (int)(((float)this.getHeight() - (float)this.getTextHeight() * 0.6F) / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED
                     : (Menu_InGame_GraphManager.iActiveGraphID == this.iModeID ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : CFG.COLOR_BUTTON_GAME_TEXT)
               )
               : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
         );
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GraphMode") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   protected int getCurrent() {
      return this.iModeID;
   }
}
