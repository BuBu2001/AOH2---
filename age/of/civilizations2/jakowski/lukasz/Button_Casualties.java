package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_Casualties extends Button {
   private boolean row = true;
   private String sPopulation;
   private int iPopulationWidth = 0;
   private Color oColorCasualtiesTotal;

   protected Button_Casualties(int WAR_ID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(CFG.langManager.get("Casualties") + ": ", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      int tempCas = CFG.game.getWar(WAR_ID).getCasualties_Aggressors() + CFG.game.getWar(WAR_ID).getCasualties_Defenders();
      this.oColorCasualtiesTotal = tempCas == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      this.sPopulation = "" + CFG.getNumber_SHORT(tempCas);
      CFG.glyphLayout.setText(CFG.fontMain, this.sPopulation);
      this.iPopulationWidth = (int)CFG.glyphLayout.width;
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (!isActive && !this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F));
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
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() * 4 / 5,
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
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
      ImageManager.getImage(Images.difficulty_hell)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 3 + (int)((float)this.getTextWidth() * 0.8F) + (int)((float)this.iPopulationWidth * 0.8F) + iTranslateX,
            this.getPosY()
               + 1
               + this.getHeight() / 2
               - (int)(
                     (float)ImageManager.getImage(Images.difficulty_hell).getHeight()
                        * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())
                  )
                  / 2
               + iTranslateY
               - ImageManager.getImage(Images.difficulty_hell).getHeight(),
            (int)(
               (float)ImageManager.getImage(Images.difficulty_hell).getWidth() * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())
            ),
            (int)(
               (float)ImageManager.getImage(Images.difficulty_hell).getHeight()
                  * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())
            )
         );
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sPopulation,
         this.getPosX() + CFG.PADDING * 2 + (int)((float)this.getTextWidth() * 0.8F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         this.oColorCasualtiesTotal
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         int tempCas = CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getCasualties_Aggressors()
            + CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getCasualties_Defenders();
         this.oColorCasualtiesTotal = tempCas == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempCas), this.oColorCasualtiesTotal));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         if (tempCas > 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            boolean added = false;

            for(int i = 0; i < CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorsSize(); ++i) {
               tempCas = CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorID(i).getCasualties()
                  + CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorID(i).getCivilianDeaths();
               if (tempCas > 0) {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getAggressorID(i).getCivID())
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempCas), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  added = true;
               }
            }

            boolean added2 = false;

            for(int i = 0; i < CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefendersSize(); ++i) {
               tempCas = CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefenderID(i).getCasualties()
                  + CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefenderID(i).getCivilianDeaths();
               if (tempCas > 0) {
                  if (added && !added2) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     added2 = true;
                  }

                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getDefenderID(i).getCivID())
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempCas), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }
            }
         }

         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var7) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
         : (
            this.getClickable()
               ? (this.getIsHovered() ? new Color(0.68F, 0.68F, 0.68F, 1.0F) : new Color(0.82F, 0.82F, 0.82F, 1.0F))
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }

   private final float getImageScale(int nImageID) {
      return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.8F / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.8F / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 0;
   }

   @Override
   protected int getWidth() {
      return Math.max(
         super.getWidth(),
         CFG.PADDING * 4
            + (int)(
               (float)this.getTextWidth() * 0.8F
                  + (float)this.iPopulationWidth * 0.8F
                  + (float)(
                     (int)(
                        (float)ImageManager.getImage(Images.difficulty_hell).getWidth()
                           * this.getImageScale(ImageManager.getImage(Images.difficulty_hell).getHeight())
                     )
                  )
            )
            + CFG.PADDING * 2
      );
   }
}
