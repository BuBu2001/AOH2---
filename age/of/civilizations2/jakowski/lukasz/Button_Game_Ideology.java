package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_Game_Ideology extends Button_Game {
   private int iIdeologyID;
   private int iIdeologyTextWidth = 0;

   protected Button_Game_Ideology(String sText, int nIdeologyID, int iTextPositionX, int iPosX, int iPosY, int nWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
      this.iIdeologyID = nIdeologyID;
      CFG.fontMain.getData().setScale(0.8F);
      CFG.glyphLayout.setText(CFG.fontMain, CFG.ideologiesManager.getIdeology(this.iIdeologyID).getName());
      this.iIdeologyTextWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX()
               + (this.getWidth() - (CFG.PADDING + CFG.CIV_FLAG_WIDTH + super.getTextWidth())) / 2
               + CFG.PADDING
               + CFG.CIV_FLAG_WIDTH
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - this.iTextHeight + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX()
               + (this.getWidth() - (CFG.PADDING + CFG.CIV_FLAG_WIDTH + super.getTextWidth())) / 2
               + CFG.PADDING
               + CFG.CIV_FLAG_WIDTH
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - this.iTextHeight + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         CFG.ideologiesManager.getIdeology(this.iIdeologyID).getName(),
         this.getPosX() + (this.getWidth() - this.iIdeologyTextWidth) / 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING + (CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT) / 2 + iTranslateY,
         this.getClickable()
            ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED
            : new Color(CFG.COLOR_BUTTON_GAME_TEXT_HOVERED.r, CFG.COLOR_BUTTON_GAME_TEXT_HOVERED.g, CFG.COLOR_BUTTON_GAME_TEXT_HOVERED.b, 0.65F)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Government") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.ideologiesManager.getIdeology(this.getCurrent()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(this.iIdeologyID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected int getCurrent() {
      return this.iIdeologyID;
   }

   @Override
   protected int getTextWidth() {
      return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH > this.iIdeologyTextWidth
         ? super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH
         : this.iIdeologyTextWidth;
   }

   @Override
   protected int getTextPos() {
      return super.getTextWidth();
   }
}
