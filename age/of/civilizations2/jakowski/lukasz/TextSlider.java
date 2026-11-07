package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class TextSlider extends MenuElement {
   private List<TextSlider_Line> lLine = new ArrayList<>();
   private int iSliderPosY;
   private int iHeightOfSlider;
   private boolean moveable = false;
   private int extraPosY = CFG.PADDING + CFG.PADDING / 2;
   private float FONT_SCALE = 1.0F;
   private int iMaxHeight = 0;
   private boolean scrollModeY = false;
   private int iScrollPosY = -1;
   private int iScrollPosY2 = -1;
   private float fScrollNewMenuPosY = 0.0F;

   protected TextSlider(int nPosX, int nPosY, int nWidth, int nHeight) {
      this.init(nPosX, nPosY, nWidth, nHeight, nHeight, 1.0F);
   }

   protected TextSlider(int nPosX, int nPosY, int nWidth, int nHeight, float nFONT_SCALE) {
      this.init(nPosX, nPosY, nWidth, nHeight, nHeight, nFONT_SCALE);
   }

   protected TextSlider(int nPosX, int nPosY, int nWidth, int nMinHeight, int nMaxHeight, float nFONT_SCALE) {
      this.init(nPosX, nPosY, nWidth, nMinHeight, nMaxHeight, nFONT_SCALE);
   }

   private final void init(int nPosX, int nPosY, int nWidth, int nHeight, int nMaxHeight, float nFONT_SCALE) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT_SLIDER;
      this.FONT_SCALE = nFONT_SCALE;
      this.iMaxHeight = nMaxHeight;
      this.setPosX(nPosX);
      this.setPosY(nPosY);
      this.setWidth(nWidth);
      this.setHeight(nHeight);
      this.updateMoveable();
      this.updateSlider(this.iSliderPosY);
   }

   @Override
   protected final void addText(String sText, int extraHeight) {
      this.lLine.add(new TextSlider_Line(sText, this.getWidth() - CFG.PADDING * 2, extraHeight, TextSlider_Line.Align.LEFT, this.FONT_SCALE));
      this.updateMoveable();
   }

   protected void drawBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight()
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.main_menu_edge).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight(),
            true
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth(),
            ImageManager.getImage(Images.main_menu_edge).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY,
            true,
            true
         );
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - CFG.PADDING - iTranslateY),
         (float)this.getWidth(),
         (float)(-this.getHeight() + CFG.PADDING)
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      oSB.setColor(new Color(0.8627451F, 0.9019608F, 0.8627451F, 1.0F));
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      int i = 0;
      int tY = 0;

      for(int iSize = this.lLine.size(); i < iSize; ++i) {
         this.lLine
            .get(i)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY() + this.extraPosY + tY + this.iSliderPosY + iTranslateY,
               this.getWidth(),
               this.getColor(isActive)
            );
         tY += this.lLine.get(i).getHeight();
      }

      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var10) {
      }

      if (this.scrollModeY) {
         if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
            this.updateSlider(this.iSliderPosY + (int)this.fScrollNewMenuPosY);
            this.fScrollNewMenuPosY *= 0.97F;
         } else {
            this.scrollModeY = false;
         }

         CFG.setRender_3(true);
      }

      this.drawScrollPos(oSB, iTranslateX, iTranslateY, isActive);
   }

   protected final void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         if (this.moveable && sliderMenuIsActive) {
            oSB.setColor(new Color(0.22F, 0.22F, 0.3F, 1.0F));
            ImageManager.getImage(Images.scroll_posiotion)
               .draw2(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + 1 + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.scroll_posiotion).getHeight() + iTranslateY,
                  ImageManager.getImage(Images.scroll_posiotion).getWidth(),
                  this.getHeight() - ImageManager.getImage(Images.scroll_posiotion).getHeight()
               );
            ImageManager.getImage(Images.scroll_posiotion)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + 1 + iTranslateX,
                  this.getPosY() + this.getHeight() - ImageManager.getImage(Images.scroll_posiotion).getHeight() + iTranslateY,
                  false,
                  true
               );
            if (CFG.menuManager.getSliderMenuMode()) {
               oSB.setColor(new Color(0.0F, 0.0F, 0.08F, 1.0F));
            } else {
               oSB.setColor(new Color(0.098F, 0.098F, 0.16F, 1.0F));
            }

            ImageManager.getImage(Images.scroll_posiotion_active)
               .draw2(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1,
                  this.getPosY() - this.iSliderPosY - ImageManager.getImage(Images.scroll_posiotion_active).getHeight() + iTranslateY,
                  CFG.PADDING * 2 - 2,
                  this.getHeight() * 100 / this.iHeightOfSlider * this.getHeight() / 100 - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
               );
            ImageManager.getImage(Images.scroll_posiotion_active)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1,
                  this.getPosY()
                     - this.iSliderPosY
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
                     + this.getHeight() * 100 / this.iHeightOfSlider * this.getHeight() / 100
                     + iTranslateY,
                  false,
                  true
               );
            oSB.setColor(Color.WHITE);
         }
      } catch (ArithmeticException var6) {
         oSB.setColor(Color.WHITE);
      }
   }

   @Override
   protected void updateSlider(int nSliderPosY) {
      Gdx.app.log("AoC", "--------------------");
      Gdx.app.log("AoC", "nSliderPosY: " + nSliderPosY);
      Gdx.app.log("AoC", "getHeight(): " + this.getHeight());
      Gdx.app.log("AoC", "iHeightOfSlider: " + this.iHeightOfSlider);
      if (nSliderPosY > 0) {
         this.iSliderPosY = 0;
         CFG.menuManager.setUpdateSliderMenuPosY(true);
         this.scrollModeY = false;
      } else if (nSliderPosY < this.getHeight() - this.iHeightOfSlider) {
         this.iSliderPosY = this.getHeight() - this.iHeightOfSlider;
         CFG.menuManager.setUpdateSliderMenuPosY(true);
         this.scrollModeY = false;
      } else {
         this.iSliderPosY = nSliderPosY;
      }
   }

   protected final void updateMoveable() {
      this.iHeightOfSlider = this.extraPosY + CFG.PADDING * 3;
      int i = 0;

      for(int iSize = this.lLine.size(); i < iSize; ++i) {
         this.iHeightOfSlider += this.lLine.get(i).getHeight();
      }

      this.moveable = this.getHeight() < this.iHeightOfSlider;
      this.scrollModeY = false;
      this.iSliderPosY = 0;
   }

   @Override
   protected final void scrollTheMenu() {
      if (this.moveable && this.iScrollPosY > 0 && this.iScrollPosY2 > 0 && (float)Math.abs(this.iScrollPosY - this.iScrollPosY2) > 3.0F * CFG.DENSITY) {
         this.fScrollNewMenuPosY = (float)(this.iScrollPosY - this.iScrollPosY2) * 1.25F;
         this.scrollModeY = true;
      }
   }

   @Override
   protected boolean getMoveable() {
      return this.moveable;
   }

   @Override
   protected int getCurrent() {
      return this.iSliderPosY;
   }

   @Override
   protected final void setScrollPosY(int iScrollPosY) {
      this.iScrollPosY2 = this.iScrollPosY;
      this.iScrollPosY = iScrollPosY;
   }

   @Override
   protected int getHeight() {
      return this.iHeightOfSlider > super.getHeight() ? (this.iHeightOfSlider > this.iMaxHeight ? this.iMaxHeight : this.iHeightOfSlider) : super.getHeight();
   }

   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_BUTTON_GAME_TEXT : Color.WHITE;
   }
}
