package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Text_Outliner_DiploInfo_Disease extends Text {
   protected static final float FONT_SCALE = 0.7F;
   private boolean row = false;
   private int iCivLeft;
   protected int iProvinceID;
   protected int iTurnID;
   protected String sAllianceName;

   protected Text_Outliner_DiploInfo_Disease(int iCivLeft, int iProvinceID, String sAllianceName, int iTurnID, int iPosX, int iPosY, int nWidth) {
      super("", CFG.PADDING * 2, iPosX, iPosY, nWidth, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2);
      this.iProvinceID = CFG.FOG_OF_WAR >= 2 && iProvinceID >= 0 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(iProvinceID) ? -1 : iProvinceID;
      this.iCivLeft = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(iCivLeft) ? iCivLeft : -iCivLeft;
      this.sAllianceName = sAllianceName;
      this.iTurnID = iTurnID;
   }

   protected final int getImage() {
      return Images.disease;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.row) {
         if (!isActive && !this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F));
         } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.8F));
         }

         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         oSB.setColor(Color.WHITE);
      } else {
         if (!isActive && !this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.75F));
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
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
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
               true,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         oSB.setColor(Color.WHITE);
      }

      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.775F));
      } else {
         oSB.setColor(Color.WHITE);
      }

      ImageManager.getImage(this.getImage())
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(this.getImage()).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.getImage()).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
      if (this.iCivLeft >= 0) {
         CFG.game
            .getCiv(this.iCivLeft)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivLeft).getFlag().getHeight() + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } else {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Disease") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sAllianceName, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OutbreakOfDisease") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            this.iProvinceID >= 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.langManager.get("Undiscovered"), CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iProvinceID >= 0 ? CFG.game.getProvince(this.iProvinceID).getCivID() : -1, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getDate_ByTurnID(this.iTurnID) + " "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.langManager.get("Turn") + ": " + this.iTurnID + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 0;
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   protected void actionElement(int iID) {
      if (this.iProvinceID >= 0) {
         if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DISEASES_MODE) {
            CFG.viewsManager.disableAllViews();
         } else {
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DISEASES_MODE);
            CFG.game.setActiveProvinceID(this.iProvinceID);
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
               CFG.toast
                  .setInView(
                     CFG.langManager.get("OutbreakOfDisease") + ": " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(),
                     CFG.COLOR_TEXT_NUM_OF_PROVINCES
                  );
            }
         }
      } else if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DISEASES_MODE) {
         CFG.viewsManager.disableAllViews();
      } else {
         CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DISEASES_MODE);
         CFG.toast.setInView("Diseases", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
      }
   }
}
