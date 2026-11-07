package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_View_Unrest extends Button {
   protected static final float FONT_SIZE = 0.7F;
   protected static final float FONT_SIZE2 = 0.7F;
   private boolean row = false;
   private int iProvinceID = 0;
   private String sPopulation;
   private int iPopulationWidth = 0;

   protected Button_View_Unrest(int iRow, String sText, int nProvinceID, int iPosX, int iPosY, int iWidth) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nProvinceID;
      this.sPopulation = "" + (int)(CFG.game.getProvince(this.iProvinceID).getRevolutionaryRisk() * 100.0F) + "%";
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275F));
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
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
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
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

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
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      }

      if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.825F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
                  / 2
               - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
                  / 2
               + iTranslateY
               - ImageManager.getImage(Images.flag_rect).getHeight(),
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX()
            + CFG.PADDING * 2
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.sPopulation,
         this.getPosX()
            + CFG.PADDING * 2
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + (int)((float)this.getTextWidth() * 0.7F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         CFG.getColorStep(
            CFG.COLOR_TEXT_REVOLUTION_MIN,
            CFG.COLOR_TEXT_REVOLUTION_MAX,
            (int)(CFG.game.getProvince(this.iProvinceID).getRevolutionaryRisk() * 100.0F),
            100,
            1.0F
         )
      );
      ImageManager.getImage(Images.diplo_revolution)
         .draw(
            oSB,
            this.getPosX()
               + CFG.PADDING * 3
               + this.iPopulationWidth
               + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
               + (int)((float)this.getTextWidth() * 0.7F)
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(
                     (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                        * this.getImageScale(ImageManager.getImage(Images.diplo_revolution).getHeight())
                  )
                  / 2
               + iTranslateY
               - ImageManager.getImage(Images.diplo_revolution).getHeight(),
            (int)(
               (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                  * this.getImageScale(ImageManager.getImage(Images.diplo_revolution).getHeight())
            ),
            (int)(
               (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                  * this.getImageScale(ImageManager.getImage(Images.diplo_revolution).getHeight())
            )
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6F)
         );
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }

   private final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / (float)nHeight;
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.game.getProvince(this.iProvinceID).getName().length() > 0
               ? CFG.game.getProvince(this.iProvinceID).getName()
               : CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getCivName(),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + (int)(CFG.game.getProvince(this.iProvinceID).getRevolutionaryRisk() * 100.0F) + "%", CFG.COLOR_TEXT_ECONOMY
         )
      );
      if (!CFG.game.showTurnChangesInformation(CFG.game.getProvince(this.iProvinceID).getCivID())
         && !CFG.game.getProvince(this.iProvinceID).civSupportsRebels(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
      } else {
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, CFG.PADDING));
         if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_RevRisk > 0.0F) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + String.format("%.2f", CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_RevRisk * 100.0F).replace(',', '.') + "%",
                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
         } else if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_RevRisk < 0.0F) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "" + String.format("%.2f", CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_RevRisk * 100.0F).replace(',', '.') + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
         } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
      }

      if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.iSupportRebelsSize > 0) {
         List<Integer> lCivs = new ArrayList<>();
         List<Integer> lCivsTurnsLeft = new ArrayList<>();
         List<List<Integer>> lSupportedByCivs = new ArrayList<>();
         int iCivsSize = 0;

         for(int i = 0; i < CFG.game.getProvince(this.iProvinceID).saveProvinceData.iSupportRebelsSize; ++i) {
            boolean wasAdded = false;
            int tAddID = CFG.game
                  .getPlayer(CFG.PLAYER_TURNID)
                  .getMetCivilization(CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iRebelsCivID)
               ? CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iRebelsCivID
               : CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iRebelsCivID * -1;

            for(int j = lCivs.size() - 1; j >= 0; --j) {
               if (lCivs.get(j) == tAddID) {
                  wasAdded = true;
                  lCivsTurnsLeft.set(
                     j, Math.max(lCivsTurnsLeft.get(j), CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iTurnsLeft)
                  );
                  lSupportedByCivs.get(j).add(CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iByCivID);
                  break;
               }
            }

            if (!wasAdded) {
               lCivs.add(tAddID);
               lCivsTurnsLeft.add(CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iTurnsLeft);
               lSupportedByCivs.add(new ArrayList<>());
               lSupportedByCivs.get(lSupportedByCivs.size() - 1).add(CFG.game.getProvince(this.iProvinceID).saveProvinceData.lSupportRebels.get(i).iByCivID);
               if (lCivs.size() >= 4) {
                  break;
               }
            }
         }

         iCivsSize = lCivs.size();
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("SupportRebels"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();

         for(int i = 0; i < iCivsSize; ++i) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(lCivs.get(i)));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  lCivs.get(i) > 0 ? CFG.game.getCiv(lCivs.get(i)).getCivName() : CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               )
            );

            for(int k = 0; k < lSupportedByCivs.get(i).size() && k < 10; ++k) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Flag(
                     !CFG.SPECTATOR_MODE && !CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), lSupportedByCivs.get(i).get(k))
                        ? -lSupportedByCivs.get(i).get(k)
                        : lSupportedByCivs.get(i).get(k),
                     k == 0 ? CFG.PADDING : 0,
                     0
                  )
               );
            }

            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  " " + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + lCivsTurnsLeft.get(i)), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", lCivsTurnsLeft.get(i)) + "]", CFG.COLOR_TEXT_RANK_HOVER));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }
      }

      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
