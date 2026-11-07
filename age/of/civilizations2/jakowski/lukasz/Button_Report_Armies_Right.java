package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Report_Armies_Right extends Button {
   private int iCivID;
   private int iArmy;
   private int iArmyWidth;
   private int iArmyLost;

   protected Button_Report_Armies_Right(int iPosX, int iPosY, int iWidth, int nCivID, int nArmy, int nArmyLost) {
      this.iCivID = nCivID;
      this.iArmy = nArmy;
      this.iArmyLost = nArmyLost;
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iArmy);
      this.iArmyWidth = (int)CFG.glyphLayout.width;
      super.init(
         "",
         0,
         iPosX,
         iPosY,
         iWidth,
         CFG.PADDING
            + ImageManager.getImage(Images.flag_rect).getHeight() / 2
            - (int)((float)CFG.TEXT_HEIGHT * 0.85F / 2.0F)
            + (int)((float)CFG.TEXT_HEIGHT * 0.85F) * 2
            + CFG.PADDING * 2,
         true,
         true,
         false,
         false,
         null
      );
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.425F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.245F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(
         new Color(
            (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
            (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
            (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
            0.175F
         )
      );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - (CFG.PADDING * 2 + ImageManager.getImage(Images.flag_rect).getWidth() + 2) + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2 + ImageManager.getImage(Images.flag_rect).getWidth() + 2,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 5
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 5,
            false,
            true
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, this.getIsHovered() ? 0.95F : 0.675F));
      CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      CFG.drawRect(oSB, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      CFG.drawRect(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - 2, this.getHeight() - 2);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(
         new Color(
            (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
            (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
            (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
            0.85F
         )
      );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - 2 + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            2,
            ImageManager.getImage(Images.flag_rect).getHeight(),
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - 2 - ImageManager.getImage(Images.flag_rect).getWidth() + iTranslateX,
            this.getPosY() + CFG.PADDING - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - 2 - ImageManager.getImage(Images.flag_rect).getWidth() + iTranslateX,
            this.getPosY() + CFG.PADDING + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawText(
         oSB,
         "" + this.iArmy,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING
            - 2
            - ImageManager.getImage(Images.flag_rect).getWidth()
            - CFG.PADDING
            - (int)((float)this.iArmyWidth * 0.85F)
            + iTranslateX,
         this.getPosY() + CFG.PADDING + ImageManager.getImage(Images.flag_rect).getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85F / 2.0F) + iTranslateY,
         Button_Report_Armies.COLOR_ARMY
      );
      CFG.drawText(
         oSB,
         this.iArmyLost > 0 ? "" + this.iArmyLost + "-" : "" + this.iArmyLost,
         this.getPosX() + CFG.PADDING + 2 + iTranslateX,
         this.getPosY() + CFG.PADDING + ImageManager.getImage(Images.flag_rect).getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85F / 2.0F) + iTranslateY,
         Button_Report_Armies.COLOR_ARMY_LOST
      );
      CFG.drawText(
         oSB,
         "" + (this.iArmy - this.iArmyLost),
         this.getPosX() + CFG.PADDING + 2 + iTranslateX,
         this.getPosY()
            + CFG.PADDING
            + ImageManager.getImage(Images.flag_rect).getHeight() / 2
            - (int)((float)CFG.TEXT_HEIGHT * 0.85F / 2.0F)
            + (int)((float)CFG.TEXT_HEIGHT * 0.85F)
            + CFG.PADDING
            + iTranslateY,
         this.iArmy - this.iArmyLost == 0 ? Button_Report_Armies.COLOR_ARMY_LOST : Button_Report_Armies.COLOR_ARMY
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName()));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Strength") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iArmy, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + this.iArmyLost, this.iArmyLost > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      if (this.iArmyLost > 0) {
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               " [" + (int)((float)this.iArmyLost / (float)this.iArmy * 100.0F) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
            )
         );
      }

      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
