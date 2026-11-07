package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Statistics_Flag_HREPrince extends Button_Statistics_Flag {
   protected static final float TEXT_COST_SCALE = 0.7F;
   protected String sPopulation;
   protected int iPopulationWidth;

   protected Button_Statistics_Flag_HREPrince(int iCivID, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(iCivID, sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
      if (iCivID >= 0) {
         this.sPopulation = CFG.getNumberWithSpaces("" + CFG.game.getCiv(iCivID).countPopulation());
      } else {
         this.sPopulation = "---";
      }

      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawText(oSB, iTranslateX, iTranslateY, isActive);
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.7F))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population, 0.7F)) / 2
               - ImageManager.getImage(Images.population).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.7F)),
            (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population, 0.7F))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sPopulation,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population, 0.7F))
            - this.iPopulationWidth
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
         !isActive && !this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION : CFG.COLOR_TEXT_POPULATION_HOVER
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   protected float getImageScale(int nImageID, float nTextScale) {
      return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Prince") + ":", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName()));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
      }
   }
}
