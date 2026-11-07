package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class Button_Diplomacy_Migrate2 extends Button {
   private boolean row = true;
   private int iCivID = 0;
   private int toProvinceID = 0;
   private String sToProvinceID;
   private String sTextCostGold;
   private String sTextCostDiplomacy;
   private int iTextCostGoldWidth;
   private int iTextCostDiplomacyWidth;

   protected Button_Diplomacy_Migrate2(int fromProvinceID, int toProvinceID, int iCivID, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super.init(
         CFG.langManager.get("MigrateTo") + ": ",
         0,
         iPosX,
         iPosY,
         iWidth,
         Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING),
         isClickable,
         true,
         false,
         false
      );
      this.iCivID = iCivID;
      this.toProvinceID = toProvinceID;
      this.sTextCostGold = "" + (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_MOVE / 10.0F;
      this.sTextCostDiplomacy = "+" + (int)((CFG.game.getProvince(fromProvinceID).getIsCapital() ? 0.1F : 0.05F) * 100.0F) + "%";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextCostGold);
      this.iTextCostGoldWidth = (int)(CFG.glyphLayout.width * 0.6F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextCostDiplomacy);
      this.iTextCostDiplomacyWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sToProvinceID = ""
         + (CFG.game.getProvince(toProvinceID).getName().length() > 0 ? CFG.game.getProvince(toProvinceID).getName() : CFG.langManager.get("NewLand"));
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
      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
               ImageManager.getImage(Images.flag_rect).getWidth(),
               ImageManager.getImage(Images.flag_rect).getHeight()
            );
      } catch (NullPointerException var9) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               ImageManager.getImage(Images.flag_rect).getWidth(),
               ImageManager.getImage(Images.flag_rect).getHeight()
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
            ImageManager.getImage(Images.flag_rect).getWidth(),
            ImageManager.getImage(Images.flag_rect).getHeight()
         );
      ImageManager.getImage(Images.top_movement_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING / 2
               + this.getHeight() / 2
               - this.getHeight() / 4
               - (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points) / 2.0F)
               - ImageManager.getImage(Images.top_movement_points).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)),
            (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points))
         );
      ImageManager.getImage(Images.research)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(Images.research))
               + iTranslateX,
            this.getPosY()
               - CFG.PADDING / 2
               + this.getHeight() / 2
               + this.getHeight() / 4
               - (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(Images.research) / 2.0F)
               - ImageManager.getImage(Images.research).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(Images.research)),
            (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(Images.research))
         );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sTextCostGold,
         this.getPosX()
            + this.getWidth()
            - this.iTextCostGoldWidth
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points))
            + iTranslateX,
         this.getPosY() + CFG.PADDING / 2 + this.getHeight() / 2 - this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
               >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).COST_OF_MOVE
            ? CFG.COLOR_INGAME_MOVEMENT
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.drawText(
         oSB,
         this.sTextCostDiplomacy,
         this.getPosX()
            + this.getWidth()
            - this.iTextCostDiplomacyWidth
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(Images.research))
            + iTranslateX,
         this.getPosY() - CFG.PADDING / 2 + this.getHeight() / 2 + this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         Color.WHITE
      );
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
         (float)(this.getWidth() - this.getRightWidth() - Button_Diplomacy.iDiploWidth),
         (float)(-this.getHeight())
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );

      try {
         CFG.drawText(
            oSB,
            this.sToProvinceID,
            this.getPosX() + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7F) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
      } catch (IndexOutOfBoundsException var8) {
      }

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   protected final int getRightWidth() {
      return Math.max(
         this.iTextCostGoldWidth
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)),
         this.iTextCostDiplomacyWidth
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.research))
      );
   }

   private final float getImageScale(int nImageID) {
      return (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.6F / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.6F / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
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

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, 0, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText()));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sToProvinceID, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + this.sTextCostGold, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sTextCostDiplomacy, CFG.COLOR_TEXT_RESEARCH));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void setMax(int nCurrent) {
      this.row = nCurrent == 1;
   }

   @Override
   protected void setVisible(boolean isVisible) {
      super.setVisible(isVisible);
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
