package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Slider_Defensive extends Slider_BG {
   private int iCivA = 0;

   protected Slider_Defensive(int iCivA, int iCivB, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super(CFG.game.getCiv(iCivA).getCivName() + ": ", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
      this.iCivA = iCivA;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawSliderBG_UpdateAnimation();
      ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth());
      oSB.setColor(
         new Color(
            CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getR(),
            CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getG(),
            CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_DEFENSIVE_PACT.getB(),
            0.68F
         )
      );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight());
      oSB.setColor(0.97F, 0.97F, 0.97F, 0.68F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + iTranslateX,
            this.getPosY() - 1 + iTranslateY,
            this.getWidth() - this.iCurrentPosX - this.iDifference_CurrentPosX,
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_SLIDER_BORDER.r, CFG.COLOR_SLIDER_BORDER.g, CFG.COLOR_SLIDER_BORDER.b, this.getClickable() ? 1.0F : 0.5F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeight());
      this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      oSB.setColor(Color.WHITE);
      this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }

   @Override
   protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivA)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.85F) / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() - CFG.game.getCiv(this.iCivA).getFlag().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.85F) / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawTextWithShadow(
         oSB,
         this.getDrawText(),
         this.getPosX() + this.getWidth() / 2 + CFG.CIV_FLAG_WIDTH / 2 - (int)((float)this.getTextWidth() * 0.85F) / 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.85F) / 2 + iTranslateY,
         new Color(0.945F, 0.945F, 0.945F, 1.0F)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfTurns"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
