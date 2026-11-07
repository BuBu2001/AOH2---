package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class Button_Diplomacy_LiberityDesire extends Button {
   protected int iCivID = 0;
   private boolean row = false;
   protected Color textColor;
   private String sLiberty;
   private int iImageID = 0;

   protected Button_Diplomacy_LiberityDesire(
      int nCivID, int nLibertyDesire, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable
   ) {
      super.init("" + nLibertyDesire + "%", iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false);
      this.iCivID = nCivID;
      this.textColor = this.getOpinionColor(nLibertyDesire);
      this.sLiberty = CFG.langManager.get("LibertyDesire");
      this.iImageID = Images.diplo_vassal;
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

   private final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT * 0.8F / (float)nHeight;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);

      try {
         CFG.ideologiesManager
            .getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID())
            .getCrownImageScaled()
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((double)this.getTextWidth() * 0.6)
                  - (int)(
                     (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getWidth()
                        * this.getImageScale(
                           CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                        )
                  )
                  + iTranslateX,
               this.getPosY()
                  + 1
                  + this.getHeight() / 2
                  - (int)(
                        (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                           * this.getImageScale(
                              CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                           )
                     )
                     / 2
                  + iTranslateY
                  - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight(),
               (int)(
                  (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getWidth()
                     * this.getImageScale(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                     )
               ),
               (int)(
                  (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                     * this.getImageScale(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                     )
               )
            );
         Rectangle clipBounds = new Rectangle(
            (float)(this.getPosX() + iTranslateX),
            (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
            (float)(
               this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((double)this.getTextWidth() * 0.6)
                  - (int)(
                     (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getWidth()
                        * this.getImageScale(
                           CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID()).getCrownImageScaled().getHeight()
                        )
                  )
                  - CFG.PADDING
            ),
            (float)(-this.getHeight())
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
      } catch (IndexOutOfBoundsException var8) {
         ImageManager.getImage(this.iImageID)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((double)this.getTextWidth() * 0.6)
                  - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight()))
                  + iTranslateX,
               this.getPosY()
                  + 1
                  + this.getHeight() / 2
                  - (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(this.iImageID).getHeight(),
               (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight())),
               (int)((float)ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight()))
            );
         Rectangle clipBoundsx = new Rectangle(
            (float)(this.getPosX() + iTranslateX),
            (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
            (float)(
               this.getWidth()
                  - CFG.PADDING * 2
                  - (int)((double)this.getTextWidth() * 0.6)
                  - (int)((float)ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight()))
                  - CFG.PADDING
            ),
            (float)(-this.getHeight())
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBoundsx);
      }

      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sLiberty,
         this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }

      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + this.getWidth() - (int)((float)this.getTextWidth() * 0.6F) - CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         this.textColor
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      try {
         List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LibertyDesire") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.getText(), this.textColor));
         nData.add(new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID(), CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Lord") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID(), CFG.PADDING, 0));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Ideology(
               CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getIdeologyID(), CFG.PADDING, 0
            )
         );
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

   @Override
   protected void setCurrent(int nCurrent) {
      this.row = nCurrent == 1;
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }
}
