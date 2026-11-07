package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Button_Diplomacy_FormCivilization extends Button {
   private Image lFlag = null;
   private boolean row = false;
   private String sCivTag;
   private String sTextCostGold;
   private String sTextCostDiplomacy;
   private int iTextCostGoldWidth;
   private int iTextCostDiplomacyWidth;

   protected Button_Diplomacy_FormCivilization(String nTag, int iPosX, int iPosY, int iWidth, boolean isClickable, boolean nCheckbox) {
      super.init(
         CFG.langManager.get("FormX", CFG.langManager.getCiv(nTag)),
         0,
         iPosX,
         iPosY,
         iWidth,
         Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING),
         isClickable,
         true,
         true,
         nCheckbox
      );
      this.sCivTag = nTag;
      this.sTextCostGold = "1000";
      this.sTextCostDiplomacy = "2.4";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextCostGold);
      this.iTextCostGoldWidth = (int)(CFG.glyphLayout.width * 0.6F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextCostDiplomacy);
      this.iTextCostDiplomacyWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.loadFlag(this.sCivTag);
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
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_Diplomacy_FormCivilization.this.getCheckboxState()) {
                  oSB.setColor(new Color(0.55F, 0.8F, 0.0F, 0.3F));
               } else {
                  oSB.setColor(new Color(0.8F, 0.137F, 0.0F, 0.3F));
               }
   
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_FormCivilization.this.getPosX() + iTranslateX,
                     Button_Diplomacy_FormCivilization.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
                     Button_Diplomacy_FormCivilization.this.getWidth() / 6,
                     Button_Diplomacy_FormCivilization.this.getHeight() - 2,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_FormCivilization.this.getPosX() + iTranslateX,
                     Button_Diplomacy_FormCivilization.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
                     Button_Diplomacy_FormCivilization.this.getWidth() / 10,
                     Button_Diplomacy_FormCivilization.this.getHeight() - 2,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_FormCivilization.this.getPosX() + iTranslateX,
                     Button_Diplomacy_FormCivilization.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                     Button_Diplomacy_FormCivilization.this.getWidth(),
                     CFG.PADDING,
                     false,
                     false
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Button_Diplomacy_FormCivilization.this.getPosX() + iTranslateX,
                     Button_Diplomacy_FormCivilization.this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + Button_Diplomacy_FormCivilization.this.getHeight()
                        - 1
                        + iTranslateY
                        - CFG.PADDING,
                     Button_Diplomacy_FormCivilization.this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
            }
         }
         : new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
         };
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         this.lFlag
            .draw(
               oSB,
               this.getPosX() + (Button_Diplomacy.iDiploWidth - ImageManager.getImage(Images.flag_rect).getWidth()) / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - this.lFlag.getHeight() + iTranslateY,
               ImageManager.getImage(Images.flag_rect).getWidth(),
               ImageManager.getImage(Images.flag_rect).getHeight()
            );
      } catch (NullPointerException var8) {
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
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING / 2
               + this.getHeight() / 2
               - this.getHeight() / 4
               - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold) / 2.0F)
               - ImageManager.getImage(Images.top_gold).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)),
            (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold))
         );
      ImageManager.getImage(Images.top_diplomacy_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
               + iTranslateX,
            this.getPosY()
               - CFG.PADDING / 2
               + this.getHeight() / 2
               + this.getHeight() / 4
               - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points) / 2.0F)
               - ImageManager.getImage(Images.top_diplomacy_points).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)),
            (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points))
         );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sTextCostGold,
         this.getPosX()
            + this.getWidth()
            - this.iTextCostGoldWidth
            - CFG.PADDING * 2
            - (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold))
            + iTranslateX,
         this.getPosY() + CFG.PADDING / 2 + this.getHeight() / 2 - this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= 1000L ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.drawText(
         oSB,
         this.sTextCostDiplomacy,
         this.getPosX()
            + this.getWidth()
            - this.iTextCostDiplomacyWidth
            - CFG.PADDING * 2
            - (int)((float)ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
            + iTranslateX,
         this.getPosY() - CFG.PADDING / 2 + this.getHeight() / 2 + this.getHeight() / 4 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24
            ? CFG.COLOR_INGAME_DIPLOMACY_POINTS
            : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
         (float)(this.getWidth() - this.getRightWidth() - Button_Diplomacy.iDiploWidth),
         (float)(-this.getHeight())
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   protected final int getRightWidth() {
      return Math.max(
         this.iTextCostGoldWidth + CFG.PADDING * 3 + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)),
         this.iTextCostDiplomacyWidth
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_diplomacy_points))
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
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(this.getCheckboxState() ? Images.icon_check_true : Images.icon_check_false));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Image(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= 1000L ? Images.icon_check_true : Images.icon_check_false
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "1000",
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= 1000L
                  ? CFG.COLOR_INGAME_GOLD
                  : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Image(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24 ? Images.icon_check_true : Images.icon_check_false
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "2.4",
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24
                  ? CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Image(
               CFG.game.isAtPeace(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID())
                  ? Images.icon_check_true
                  : Images.icon_check_false
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AtPeace")));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Image(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID()
                     == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                  ? Images.icon_check_true
                  : Images.icon_check_false
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IsNotAVassal")));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(CFG.doesNotExists_FormableCiv(this.sCivTag) ? Images.icon_check_true : Images.icon_check_false));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("XDoesNotExist", CFG.langManager.getCiv(this.sCivTag))));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected void setMax(int nCurrent) {
      this.row = nCurrent == 1;
   }

   private final void loadFlag(String nTag) {
      this.disposeFlag();

      try {
         try {
            this.lFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + nTag + ".png")), Texture.TextureFilter.Nearest);
         } catch (GdxRuntimeException var7) {
            try {
               this.lFlag = new Image(
                  new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Nearest
               );
            } catch (GdxRuntimeException var6) {
               if (CFG.isAndroid()) {
                  try {
                     this.lFlag = new Image(
                        new Texture(Gdx.files.local("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest
                     );
                  } catch (GdxRuntimeException var5) {
                     this.lFlag = new Image(
                        new Texture(Gdx.files.internal("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest
                     );
                  }
               } else {
                  this.lFlag = new Image(
                     new Texture(Gdx.files.internal("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest
                  );
               }
            }
         }
      } catch (GdxRuntimeException var8) {
         this.lFlag = null;
      } catch (OutOfMemoryError var9) {
         this.lFlag = null;
      }
   }

   @Override
   protected void setVisible(boolean isVisible) {
      super.setVisible(isVisible);
      this.disposeFlag();
   }

   private final void disposeFlag() {
      if (this.lFlag != null) {
         this.lFlag.getTexture().dispose();
         this.lFlag = null;
      }
   }
}
