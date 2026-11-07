package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Game_Colonize extends Button_Game {
   private long lTime = 0L;
   private float fAlphaMod = 0.0F;
   private boolean backAnimation = false;
   private long lTimeAnimation = System.currentTimeMillis();
   private int animationState = 0;
   protected static final int ANIMATION_T = 1000;
   private int iProvinceID = 0;
   protected static final float TEXT_MAIN_SCALE = 0.9F;
   protected static final float TEXT_TERRAIN_SCALE = 0.8F;
   private String sTerrain;
   private int iTerrainWidth;
   protected static float TEXT_COST_SCALE = 0.6F;
   private int iLeftWidth = 0;
   private int iRightWidth = 0;
   private int iRightIconWidth = 0;
   private String sGold;
   private int iGoldWidth;
   private Color colorGold;
   private String sMovement;
   private int iMovementWidth;
   private Color colorMovement;
   private String sDiplomacy;
   private int iDiplomacyWidth;
   private Color colorDiplomacy;

   protected Button_Game_Colonize(String sText, int nProvinceID, int iPosX, int iPosY, boolean isClickable) {
      super(sText, 0, iPosX, iPosY, isClickable);
      this.setWidth(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
      nProvinceID = Math.max(nProvinceID, 0);

      try {
         this.iProvinceID = nProvinceID;
         this.sTerrain = CFG.terrainTypesManager.getName(CFG.game.getProvince(nProvinceID).getTerrainTypeID());
         CFG.glyphLayout.setText(CFG.fontMain, this.sTerrain);
         this.iTerrainWidth = (int)(CFG.glyphLayout.width * 0.8F);
         this.iLeftWidth = (int)(
            Math.max((float)super.getTextWidth() * 0.9F, (float)this.iTerrainWidth) + (float)(CFG.PADDING * 7) + (float)CFG.CIV_FLAG_WIDTH
         );
         TEXT_COST_SCALE = 0.7F;

         while(
            TEXT_COST_SCALE > 0.25F
               && !((float)(this.getHeight() - CFG.PADDING * 2) >= (float)CFG.TEXT_HEIGHT * TEXT_COST_SCALE * 3.0F + (float)(CFG.PADDING * 2))
         ) {
            TEXT_COST_SCALE -= 0.01F;
         }

         int tempCostGold = DiplomacyManager.getColonizeCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         int tempCostMovement = DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         this.colorGold = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)tempCostGold
            ? CFG.COLOR_INGAME_GOLD
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         this.colorDiplomacy = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints()
               >= CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS
            ? CFG.COLOR_INGAME_DIPLOMACY_POINTS
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         this.colorMovement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= tempCostMovement
            ? CFG.COLOR_INGAME_MOVEMENT
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         this.sGold = CFG.getNumberWithSpaces("" + tempCostGold);
         CFG.glyphLayout.setText(CFG.fontMain, this.sGold);
         this.iGoldWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
         this.sMovement = "" + (float)tempCostMovement / 10.0F;
         CFG.glyphLayout.setText(CFG.fontMain, this.sMovement);
         this.iMovementWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
         this.sDiplomacy = "" + (float)CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS / 10.0F;
         CFG.glyphLayout.setText(CFG.fontMain, this.sDiplomacy);
         this.iDiplomacyWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
         this.iRightIconWidth = (int)Math.max(
            Math.max(
               (float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE),
               (float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)
            ),
            (float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)
         );
         this.iRightWidth = Math.max(Math.max(this.iGoldWidth, this.iMovementWidth), this.iDiplomacyWidth) + CFG.PADDING * 3 + this.iRightIconWidth;
         this.setClickable(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= (long)tempCostGold
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= tempCostMovement
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints()
                  >= CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS
         );
      } catch (IndexOutOfBoundsException var8) {
         CFG.exceptionStack(var8);
         this.setVisible(false);
         this.setClickable(false);
      }
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      if (this.lTime < System.currentTimeMillis() - 26L) {
         if (this.backAnimation) {
            this.fAlphaMod -= 0.02F;
            if (this.fAlphaMod < 0.0F) {
               this.backAnimation = false;
            }
         } else {
            this.fAlphaMod += 0.02F;
            if (this.fAlphaMod > 0.4F) {
               this.backAnimation = true;
            }
         }

         this.lTime = System.currentTimeMillis();
      }

      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F - this.fAlphaMod));
      CFG.setRender_3(true);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - 2
         );
      if (this.animationState >= 0) {
         if (this.animationState == 0) {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.625F : 0.525F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
               ++this.animationState;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.625F : 0.525F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
               this.animationState = 0;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
      }

      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.PADDING / 2
               - CFG.CIV_FLAG_HEIGHT
               - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight()
               + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.9F);
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.9F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.terrainTypesManager
         .getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID())
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING / 2
               - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()).getHeight()
               + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sTerrain,
         this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + CFG.CIV_FLAG_HEIGHT / 2 - (int)((float)this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         CFG.COLOR_BUTTON_GAME_TEXT
      );
      CFG.fontMain.getData().setScale(TEXT_COST_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.sMovement,
         this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iMovementWidth - CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0F) + iTranslateY,
         this.colorMovement
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sGold,
         this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iGoldWidth - CFG.PADDING + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0F)
            - (int)((float)this.getTextHeight() * TEXT_COST_SCALE)
            - CFG.PADDING
            + iTranslateY,
         this.colorGold
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sDiplomacy,
         this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iDiplomacyWidth - CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + (int)((float)this.getTextHeight() * TEXT_COST_SCALE / 2.0F) + CFG.PADDING + iTranslateY,
         this.colorDiplomacy
      );
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.PADDING
               - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2
               - (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2
               - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE) / 2.0F)
               - ImageManager.getImage(Images.top_gold).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)),
            (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE))
         );
      ImageManager.getImage(Images.top_movement_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(
                  (float)ImageManager.getImage(Images.top_movement_points).getHeight()
                     * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)
                     / 2.0F
               )
               - ImageManager.getImage(Images.top_movement_points).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)),
            (int)((float)ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE))
         );
      ImageManager.getImage(Images.top_diplomacy_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING
               + (int)((float)this.getTextHeight() * TEXT_COST_SCALE) / 2
               + (int)((float)this.getTextHeight() * TEXT_COST_SCALE)
               - (int)(
                  (float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)
               )
               - ImageManager.getImage(Images.top_diplomacy_points).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE)),
            (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points, TEXT_COST_SCALE))
         );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   protected int getWidth() {
      return Math.max(this.iLeftWidth + this.iRightWidth + CFG.PADDING * 4, super.getWidth());
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT)
               : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
         );
   }

   protected float getImageScale(int nImageID, float nTextScale) {
      return (float)CFG.TEXT_HEIGHT * nTextScale / (float)ImageManager.getImage(nImageID).getHeight();
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Colonize"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.getNumberWithSpaces("" + DiplomacyManager.getColonizeCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())),
            CFG.COLOR_INGAME_GOLD
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + (float)DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
            CFG.COLOR_INGAME_MOVEMENT
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + (float)CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS / 10.0F, CFG.COLOR_INGAME_DIPLOMACY_POINTS
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Terrain") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.terrainTypesManager.getName(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
