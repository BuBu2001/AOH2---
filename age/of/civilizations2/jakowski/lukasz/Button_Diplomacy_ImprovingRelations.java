package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Button_Diplomacy_ImprovingRelations extends Button {
   protected static final float FONT_SIZE = 0.6F;
   protected int iCivID = 0;
   private boolean row = false;
   private String sImprovingRelations;
   private int iImprovingRelationsWidth;
   private String sImprovingRelations2;

   protected Button_Diplomacy_ImprovingRelations(
      int nCivID, int nNumOfTurns, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable
   ) {
      super.init("", iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false);
      this.iCivID = nCivID;
      this.sImprovingRelations = CFG.langManager.get("ImprovingRelations") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sImprovingRelations);
      this.iImprovingRelationsWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sImprovingRelations2 = CFG.langManager.get("TurnsX", nNumOfTurns);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
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
         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
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
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         this.sImprovingRelations,
         this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sImprovingRelations2,
         this.getPosX() + this.iImprovingRelationsWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.fontMain.getData().setScale(1.0F);
      if (!this.getIsHovered() && !isActive) {
         ImageManager.getImage(Images.icon_check_true)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((float)ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale())
                  + iTranslateX,
               this.getPosY()
                  + (int)(((float)this.getHeight() - (float)ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale()) / 2.0F)
                  - ImageManager.getImage(Images.icon_check_true).getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale()),
               (int)((float)ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale())
            );
      } else {
         ImageManager.getImage(Images.icon_check_false)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((float)ImageManager.getImage(Images.icon_check_false).getWidth() * this.getImageScale())
                  + iTranslateX,
               this.getPosY()
                  + (int)(((float)this.getHeight() - (float)ImageManager.getImage(Images.icon_check_false).getHeight() * this.getImageScale()) / 2.0F)
                  - ImageManager.getImage(Images.icon_check_false).getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.icon_check_false).getWidth() * this.getImageScale()),
               (int)((float)ImageManager.getImage(Images.icon_check_false).getHeight() * this.getImageScale())
            );
      }
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WithdrawTheDiplomat"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_false, CFG.PADDING, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName()));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Color(
               new Color(
                  (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
                  (float)(CFG.game.getCiv(this.iCivID).getG() / 255),
                  (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
                  1.0F
               )
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (float)((int)(CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10.0F)) / 10.0F,
               this.getOpinionColor((int)CFG.game.getCivRelation_OfCivB(this.iCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()))
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Color(
               new Color(
                  (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
                  (float)(CFG.game.getCiv(this.iCivID).getG() / 255),
                  (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
                  1.0F
               )
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "+" + (float)((int)(DiplomacyManager.getImproveRelation(CFG.game.getActiveCivID(), this.iCivID) * 100.0F)) / 100.0F,
               CFG.COLOR_TEXT_MODIFIER_POSITIVE
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations_inc, CFG.PADDING, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.5", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, CFG.PADDING));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerTurn")));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
         );
   }

   protected final Color getOpinionColor(int nOpinion) {
      return nOpinion > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (nOpinion == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
   }

   private final float getImageScale() {
      return Math.min((float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(Images.icon_check_false).getHeight(), 1.0F);
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 1;
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }
}
