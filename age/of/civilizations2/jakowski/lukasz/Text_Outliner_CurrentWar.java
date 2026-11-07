package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Text_Outliner_CurrentWar extends Text {
   protected static final float FONT_SCALE = 0.7F;
   private boolean row = false;
   private int iWarID;
   private int iCivID;
   private Color colorWarScore;
   private boolean sentPeaceTreaty = false;

   protected Text_Outliner_CurrentWar(int nWarID, int iPosX, int iPosY, int iWidth) {
      super("", CFG.PADDING * 2, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4));
      this.iWarID = nWarID;
      this.iCivID = CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         ? CFG.game.getWar(nWarID).getDefenderID(0).getCivID()
         : CFG.game.getWar(nWarID).getAggressorID(0).getCivID();

      try {
         for(int i = 0; i < CFG.game.lPeaceTreaties.size(); ++i) {
            if (CFG.game.getWar(this.iWarID).WAR_TAG.equals(CFG.game.lPeaceTreaties.get(i).peaceTreaty_GameData.WAR_TAG)) {
               this.sentPeaceTreaty = true;
               break;
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      } catch (NullPointerException var8) {
         CFG.exceptionStack(var8);
      }

      int tempBest = 0;
      if (CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         for(int i = 1; i < CFG.game.getWar(nWarID).getDefendersSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(i).getCivID()).getRankScore()
               > CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(tempBest).getCivID()).getRankScore()) {
               tempBest = i;
            }
         }

         this.iCivID = CFG.game.getWar(nWarID).getDefenderID(tempBest).getCivID();
      } else {
         for(int i = 1; i < CFG.game.getWar(nWarID).getAggressorsSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(i).getCivID()).getRankScore()
               > CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(tempBest).getCivID()).getRankScore()) {
               tempBest = i;
            }
         }

         this.iCivID = CFG.game.getWar(nWarID).getAggressorID(tempBest).getCivID();
      }

      int tempWarScore = CFG.game.getWar(nWarID).getWarScore();
      if (CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         tempWarScore *= -1;
      }

      this.setText((tempWarScore > 0 ? "+" : "") + tempWarScore + "%");
      if (Math.abs(tempWarScore) >= 100) {
         this.colorWarScore = CFG.COLOR_TEXT_NUM_OF_PROVINCES;
      } else if (tempWarScore > 0) {
         this.colorWarScore = CFG.COLOR_TEXT_MODIFIER_POSITIVE;
      } else if (tempWarScore == 0) {
         this.colorWarScore = CFG.COLOR_TEXT_MODIFIER_NEUTRAL2;
      } else {
         this.colorWarScore = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      }

      if (this.getWidth() > iWidth) {
         this.setWidth(iWidth);
      }
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

      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.325F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.CIV_FLAG_HEIGHT / 2
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + CFG.CIV_FLAG_HEIGHT
               - 1
               + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
            1,
            true,
            false
         );
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.775F));
      } else {
         oSB.setColor(Color.WHITE);
      }

      ImageManager.getImage(Images.diplo_war)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_war).getHeight() / 2 + iTranslateY
         );
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.7F);
      if (this.sentPeaceTreaty && !this.getIsHovered() && !isActive) {
         ImageManager.getImage(Images.diplo_truce)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - ImageManager.getImage(Images.diplo_war).getWidth() / 2
                  - CFG.PADDING
                  - CFG.CIV_FLAG_WIDTH
                  - CFG.PADDING
                  - CFG.PADDING / 2
                  - ImageManager.getImage(Images.diplo_truce).getWidth()
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_truce).getHeight() / 2 + iTranslateY
            );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.sText,
            this.getPosX()
               + this.getWidth()
               - ImageManager.getImage(Images.diplo_war).getWidth() / 2
               - CFG.PADDING
               - (int)((float)this.getTextWidth() * 0.7F)
               - CFG.CIV_FLAG_WIDTH
               - CFG.PADDING
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)((float)this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
            isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : this.colorWarScore)
         );
      }

      CFG.fontMain.getData().setScale(1.0F);
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

      for(int i = 0; i < CFG.game.getWar(this.iWarID).getAggressorsSize() && i < 5; ++i) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID(), 0, 0));
      }

      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));

      for(int i = 0; i < CFG.game.getWar(this.iWarID).getDefendersSize() && i < 5; ++i) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getDefenderID(i).getCivID(), 0, 0));
      }

      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      int tempScore = CFG.game.getWar(this.iWarID).getWarScore();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            ""
               + (
                  tempScore == 0
                     ? CFG.langManager.get("Balanced")
                     : (
                        tempScore < 0
                           ? (
                              CFG.game.getWar(this.iWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 ? CFG.langManager.get("XInFavourOfYourSide", Math.abs(tempScore) + "%")
                                 : CFG.langManager.get("XInFavourOfEnemySide", Math.abs(tempScore) + "%")
                           )
                           : (
                              CFG.game.getWar(this.iWarID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 ? CFG.langManager.get("XInFavourOfYourSide", Math.abs(tempScore) + "%")
                                 : CFG.langManager.get("XInFavourOfEnemySide", Math.abs(tempScore) + "%")
                           )
                     )
               ),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      int casualties = CFG.game.getWar(this.iWarID).getCasualties_Aggressors() + CFG.game.getWar(this.iWarID).getCasualties_Defenders();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + CFG.getNumber_SHORT(casualties), casualties > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
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
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         if (this.getHeight() < this.iTextHeight) {
            this.setHeight(this.iTextHeight);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   @Override
   protected void actionElement(int iID) {
      if (CFG.menuManager.getVisibleInGame_WarDetails() && Menu_InGame_WarDetails.WAR_ID == this.iWarID) {
         CFG.menuManager.setVisibleInGame_WarDetails(false);
      } else {
         Menu_InGame_WarDetails.WAR_ID = this.iWarID;
         CFG.menuManager.rebuildInGame_WarDetails();
      }
   }
}
