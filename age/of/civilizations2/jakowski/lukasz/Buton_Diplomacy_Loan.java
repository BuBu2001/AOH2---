package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Buton_Diplomacy_Loan extends Button_Statistics {
   private static final float FONT_SCALE = 0.7F;
   private static final float FONT_SCALE2 = 0.6F;
   private int iCivA;
   private String sTurnLeft;
   private int iTurnLeftWidth;
   private String sTurnLeftDate;
   private int iTurnLeftDateWidth;
   private int iMoneyPerTurn;

   protected Buton_Diplomacy_Loan(int i, int iCivA, int iMoneyPerTurn, int iMoney, int iTurnsLeft, int iPosX, int iPosY, int iWidth) {
      super("" + iMoney, 0, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6F)), false);
      this.row = i % 2 == 0;
      this.iCivA = iCivA;
      this.iMoneyPerTurn = iMoneyPerTurn;
      this.sTurnLeft = CFG.langManager.get("TurnsX", iTurnsLeft) + " ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTurnLeft);
      this.iTurnLeftWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sTurnLeftDate = "[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + iTurnsLeft) + "]";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTurnLeftDate);
      this.iTurnLeftDateWidth = (int)(CFG.glyphLayout.width * 0.6F);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.15F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 6,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 6 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 6,
               this.getHeight(),
               true,
               false
            );
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.05F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 6,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 6 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 6,
               this.getHeight(),
               true,
               false
            );
      }

      if (isActive || this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, isActive ? 0.345F : 0.265F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() - 2
            );
      }

      if (this.row) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.625F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth()
            );
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.375F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth()
            );
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivA).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivA).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivA).getB() / 255.0F,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var6) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            2,
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivA)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - CFG.game.getCiv(this.iCivA).getFlag().getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      CFG.fontMain.getData().setScale(0.7F);
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX()
               + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
               + CFG.PADDING * 4
               + 2
               + (int)((float)this.getTextWidth() * 0.7F)
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold)) / 2
               - ImageManager.getImage(Images.top_gold).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)),
            (int)((float)ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold))
         );
      ImageManager.getImage(Images.time)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time))
               - CFG.PADDING * 2
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time)) / 2
               - ImageManager.getImage(Images.time).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time)),
            (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + CFG.PADDING * 3
            + 2
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7F / 2.0F) + iTranslateY,
         isActive ? CFG.COLOR_INGAME_GOLD_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_GOLD_HOVER : CFG.COLOR_INGAME_GOLD)
      );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         this.sTurnLeft,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time))
            - this.iTurnLeftWidth
            - this.iTurnLeftDateWidth
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sTurnLeftDate,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time))
            - this.iTurnLeftDateWidth
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.6F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Buton_Diplomacy_Loan.this.getCheckboxState()) {
                  oSB.setColor(new Color(0.55F, 0.8F, 0.0F, 0.2F));
               } else {
                  oSB.setColor(new Color(0.8F, 0.137F, 0.0F, 0.15F));
               }
   
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Buton_Diplomacy_Loan.this.getPosX() + iTranslateX,
                     Buton_Diplomacy_Loan.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + iTranslateY,
                     Buton_Diplomacy_Loan.this.getWidth(),
                     Buton_Diplomacy_Loan.this.getHeight() - 2,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Buton_Diplomacy_Loan.this.getPosX() + iTranslateX,
                     Buton_Diplomacy_Loan.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                     Buton_Diplomacy_Loan.this.getWidth(),
                     Buton_Diplomacy_Loan.this.getHeight() / 4,
                     false,
                     false
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Buton_Diplomacy_Loan.this.getPosX() + iTranslateX,
                     Buton_Diplomacy_Loan.this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + Buton_Diplomacy_Loan.this.getHeight()
                        - 1
                        + iTranslateY
                        - Buton_Diplomacy_Loan.this.getHeight() / 4,
                     Buton_Diplomacy_Loan.this.getWidth(),
                     Buton_Diplomacy_Loan.this.getHeight() / 4,
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

   private final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GoldPerTurn") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iMoneyPerTurn, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Total") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.getText(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
