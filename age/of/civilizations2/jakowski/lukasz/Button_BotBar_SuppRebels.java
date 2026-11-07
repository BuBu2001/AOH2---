package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_BotBar_SuppRebels extends Button_BotBar {
   protected List<Integer> lCivs = new ArrayList<>();
   protected List<Integer> lCivsTurnsLeft = new ArrayList<>();
   protected List<List<Integer>> lSupportedByCivs = new ArrayList<>();
   protected int iCivsSize = 0;
   private int iProvinceID = 0;

   protected Button_BotBar_SuppRebels(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, boolean isClickable, boolean isVisible) {
      super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, isClickable, isVisible);
      this.iTextPositionX = CFG.PADDING * 2 + ImageManager.getImage(Images.bot_left).getWidth() / 2;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      for(int i = 0; i < this.iCivsSize; ++i) {
         if (this.lCivs.get(i) < 0) {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getTextPos()
                     + (int)(
                        (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                           * (
                              (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                                 / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                           )
                     )
                     + CFG.PADDING
                     + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i
                     + iTranslateX,
                  this.getPosY()
                     - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     + this.getHeight() / 2
                     - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                     + iTranslateY,
                  (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getTextPos()
                     + (int)(
                        (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                           * (
                              (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                                 / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                           )
                     )
                     + CFG.PADDING
                     + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i
                     + iTranslateX,
                  this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
                  (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
         } else {
            CFG.game
               .getCiv(this.lCivs.get(i))
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getTextPos()
                     + (int)(
                        (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                           * (
                              (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                                 / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                           )
                     )
                     + CFG.PADDING
                     + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i
                     + iTranslateX,
                  this.getPosY()
                     - CFG.game.getCiv(this.lCivs.get(i)).getFlag().getHeight()
                     + this.getHeight() / 2
                     - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                     + iTranslateY,
                  (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getTextPos()
                     + (int)(
                        (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                           * (
                              (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                                 / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                           )
                     )
                     + CFG.PADDING
                     + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * i
                     + iTranslateX,
                  this.getPosY() - CFG.CIV_FLAG_HEIGHT + this.getHeight() / 2 - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F) + iTranslateY,
                  (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
         }
      }

      ImageManager.getImage(Images.diplo_revolution)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(
                           (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                              * (
                                 (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                                    / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                              )
                        )
                  )
                  / 2
               - ImageManager.getImage(Images.diplo_revolution).getHeight()
               + iTranslateY,
            (int)(
               (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                  * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())
            ),
            (int)(
               (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                  * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())
            )
         );
      ImageManager.getImage(Images.ar_up)
         .draw(
            oSB,
            this.getPosX()
               + this.getTextPos()
               + (int)(
                  (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
                     * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())
               )
               - ImageManager.getImage(Images.ar_up).getWidth()
               + CFG.PADDING / 2
               + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(
                           (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                              * (
                                 (float)ImageManager.getImage(Images.victoryPoints).getHeight()
                                    / (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                              )
                        )
                  )
                  / 2
               + (int)(
                  (float)ImageManager.getImage(Images.diplo_revolution).getHeight()
                     * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())
               )
               - ImageManager.getImage(Images.ar_up).getHeight()
               + CFG.PADDING / 4
               + iTranslateY
         );
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }

   @Override
   protected void setCurrent(int iProvinceID) {
      this.iProvinceID = iProvinceID;
      this.lCivs.clear();
      this.lCivsTurnsLeft.clear();
      this.lSupportedByCivs.clear();
      this.iCivsSize = 0;

      for(int i = 0; i < CFG.game.getProvince(iProvinceID).saveProvinceData.iSupportRebelsSize; ++i) {
         boolean wasAdded = false;
         int tAddID = CFG.game
               .getPlayer(CFG.PLAYER_TURNID)
               .getMetCivilization(CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iRebelsCivID)
            ? CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iRebelsCivID
            : CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iRebelsCivID * -1;

         for(int j = this.lCivs.size() - 1; j >= 0; --j) {
            if (this.lCivs.get(j) == tAddID) {
               wasAdded = true;
               this.lCivsTurnsLeft
                  .set(j, Math.max(this.lCivsTurnsLeft.get(j), CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iTurnsLeft));
               this.lSupportedByCivs.get(j).add(CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iByCivID);
               break;
            }
         }

         if (!wasAdded) {
            this.lCivs.add(tAddID);
            this.lCivsTurnsLeft.add(CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iTurnsLeft);
            this.lSupportedByCivs.add(new ArrayList<>());
            this.lSupportedByCivs.get(this.lSupportedByCivs.size() - 1).add(CFG.game.getProvince(iProvinceID).saveProvinceData.lSupportRebels.get(i).iByCivID);
            if (this.lCivs.size() >= 4) {
               break;
            }
         }
      }

      this.iCivsSize = this.lCivs.size();
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(this.iMinWidth);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   @Override
   protected int getWidth() {
      return (int)(
            (float)ImageManager.getImage(Images.diplo_revolution).getWidth()
               * ((float)ImageManager.getImage(Images.victoryPoints).getHeight() / (float)ImageManager.getImage(Images.diplo_revolution).getHeight())
         )
         + CFG.PADDING * 2
         + 2
         + ((int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) + CFG.PADDING) * this.iCivsSize
         + CFG.PADDING
         + ImageManager.getImage(Images.bot_left).getWidth() / 2;
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.iProvinceID).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("SupportRebels"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();

         for(int i = 0; i < this.iCivsSize; ++i) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.lCivs.get(i)));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  this.lCivs.get(i) > 0 ? CFG.game.getCiv(this.lCivs.get(i)).getCivName() : CFG.langManager.get("Undiscovered"),
                  CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               )
            );

            for(int k = 0; k < this.lSupportedByCivs.get(i).size() && k < 10; ++k) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Flag(
                     !CFG.SPECTATOR_MODE && !CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.lSupportedByCivs.get(i).get(k))
                        ? -this.lSupportedByCivs.get(i).get(k)
                        : this.lSupportedByCivs.get(i).get(k),
                     k == 0 ? CFG.PADDING : 0,
                     0
                  )
               );
            }

            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  " " + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.lCivsTurnsLeft.get(i)), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", this.lCivsTurnsLeft.get(i)) + "]", CFG.COLOR_TEXT_RANK_HOVER)
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var5) {
         this.menuElementHover = null;
      }
   }
}
