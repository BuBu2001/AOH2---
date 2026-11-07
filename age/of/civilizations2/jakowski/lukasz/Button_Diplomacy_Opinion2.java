package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Diplomacy_Opinion2 extends Button_Diplomacy_Opinion {
   private String sText2;
   private int iText2Width = 0;
   protected Color textColor2;

   protected Button_Diplomacy_Opinion2(
      int nCivID, int nOpinion, int nOpinion2, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable
   ) {
      super(nCivID, nOpinion, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.sText2 = "" + nOpinion2;
      CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
      this.iText2Width = (int)CFG.glyphLayout.width;
      this.textColor2 = this.getOpinionColor(nOpinion2);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         CFG.game.getCiv(this.iCivID).getCivName(),
         this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + this.getWidth() - (int)((float)this.getTextWidth() * 0.6F) - CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.textColor
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations, 0, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, 0, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (float)((int)(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), this.iCivID) * 10.0F)) / 10.0F,
               CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), this.iCivID) > 0.0F
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : (
                     CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), this.iCivID) == 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE
                  )
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations, 0, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo(), 0, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (float)((int)(CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.getActiveCivInfo()) * 10.0F)) / 10.0F,
               CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.getActiveCivInfo()) > 0.0F
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : (
                     CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.getActiveCivInfo()) == 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE
                  )
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
      }
   }
}
