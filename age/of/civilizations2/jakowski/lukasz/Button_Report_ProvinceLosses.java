package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Report_ProvinceLosses extends Button {
   private int iPopulationLosses;
   private int iEconomyLosses;
   private int iEconomyLossesWidth;

   protected Button_Report_ProvinceLosses(int iPosX, int iPosY, int iWidth, int nPopulationLosses, int nEconomyLosses) {
      this.iPopulationLosses = nPopulationLosses;
      this.iEconomyLosses = nEconomyLosses;
      CFG.glyphLayout.setText(CFG.fontMain, "-" + this.iEconomyLosses);
      this.iEconomyLossesWidth = (int)CFG.glyphLayout.width;
      super.init("", 0, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 2, true, true, false, false, null);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.475F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, 0.425F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            this.getHeight(),
            false,
            false
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
      oSB.setColor(new Color(0.20392157F, 0.23921569F, 0.26666668F, this.getIsHovered() ? 0.95F : 0.745F));
      CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      CFG.drawRect(oSB, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      CFG.drawRect(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - 2, this.getHeight() - 2);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.population).getHeight() / 2 + iTranslateY
         );
      ImageManager.getImage(Images.economy)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - ImageManager.getImage(Images.economy).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.economy).getHeight() / 2 + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawText(
         oSB,
         "-" + this.iPopulationLosses,
         this.getPosX() + CFG.PADDING * 2 + ImageManager.getImage(Images.population).getWidth() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85F / 2.0F) + iTranslateY,
         Button_Report_Armies.COLOR_ARMY_LOST
      );
      CFG.drawText(
         oSB,
         "-" + this.iEconomyLosses,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 2
            - ImageManager.getImage(Images.economy).getWidth()
            - (int)((float)this.iEconomyLossesWidth * 0.85F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.85F / 2.0F) + iTranslateY,
         Button_Report_Armies.COLOR_ARMY_LOST
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilianDeaths") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iPopulationLosses, CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomicLosses") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iEconomyLosses, CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
