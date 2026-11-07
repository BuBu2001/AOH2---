package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class SliderMenu {
   private List<MenuElement> menuElements = new ArrayList<>();
   private int iMenuElementsSize;
   private int iPosX;
   private int iPosY;
   private int iWidth;
   private int iHeight;
   private boolean visible = true;
   private boolean closeable = false;
   private SliderMenuTitle sliderMenuTitle = null;
   private int iMenuPosX;
   private int iNewMenuPositionX;
   private int iMaxSliderPositionX;
   private boolean scrollableX = false;
   private int iMenuPosY;
   private int iNewMenuPositionY;
   protected int iMaxSliderPositionY;
   private boolean scrollableY = false;
   private boolean scrollModeY = false;
   private int iScrollPosY = -1;
   private int iScrollPosY2 = -1;
   private float fScrollNewMenuPosY = 0.0F;
   private boolean scrollModeX = false;
   private int iScrollPosX = -1;
   private int iScrollPosX2 = -1;
   private float fScrollNewMenuPosX = 0.0F;

   protected final void initMenu(SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, false, false);
   }

   protected final void initMenu(
      SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements, boolean visible, boolean closeable
   ) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, false, closeable);
   }

   protected final void initMenuWithBackButton(SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, true, false);
   }

   protected final void initMenuWithBackButton(
      SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements, boolean closeable
   ) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, true, closeable);
   }

   protected final void initMenuWithBackButton(
      SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements, boolean visible, boolean closeable
   ) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, true, closeable);
   }

   protected final void initMenu(
      SliderMenuTitle sliderMenuTitle,
      int iPosX,
      int iPosY,
      int iWidth,
      int iHeight,
      List<MenuElement> menuElements,
      boolean visible,
      boolean backButton,
      boolean closeable
   ) {
      this.iPosX = this.iMenuPosX = this.iNewMenuPositionX = iPosX;
      this.iPosY = this.iMenuPosY = this.iNewMenuPositionY = iPosY;
      this.iWidth = iWidth;
      this.iHeight = iHeight;
      this.closeable = closeable;
      this.visible = visible;
      this.sliderMenuTitle = sliderMenuTitle;
      this.iMenuElementsSize = menuElements.size();
      if (backButton) {
         int tempMaxY = 0;

         for(int i = 0; i < this.iMenuElementsSize; ++i) {
            if (menuElements.get(i).getPosY() + menuElements.get(i).getHeight() > tempMaxY) {
               tempMaxY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight();
            }
         }

         menuElements.get(0).setPosY(tempMaxY + CFG.PADDING);
         if (tempMaxY > iHeight - CFG.PADDING - menuElements.get(0).getHeight()) {
            menuElements.get(0).setPosY(tempMaxY + CFG.PADDING);
         } else {
            menuElements.get(0).setPosY(iHeight - menuElements.get(0).getHeight());
         }
      }

      this.menuElements = menuElements;
      this.updateScrollable();
      this.updateMenuElements_IsInView();
   }

   protected final void updateScrollable() {
      this.iMaxSliderPositionY = this.iMaxSliderPositionX = 0;

      for(int i = 0; i < this.iMenuElementsSize; ++i) {
         if (this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() > this.iMaxSliderPositionY) {
            this.iMaxSliderPositionY = this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight();
         }

         if (this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() > this.iMaxSliderPositionX) {
            this.iMaxSliderPositionX = this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth();
         }
      }

      this.scrollableX = this.iMaxSliderPositionX > this.getWidth();
      this.scrollableY = this.iMaxSliderPositionY > this.iHeight;
      if (this.scrollableY) {
         this.updateMenuPosY(this.iPosY);
      }

      if (this.scrollableX) {
         this.updateMenuPosX(this.iPosX);
      }
   }

   protected void updateLanguage() {
   }

   protected void update() {
      if (this.scrollModeY) {
         if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
            this.updateMenuPosY(this.iMenuPosY + (int)this.fScrollNewMenuPosY);
            this.fScrollNewMenuPosY *= 0.97F;
         } else {
            this.scrollModeY = false;
         }

         CFG.setRender_3(true);
      }

      if (this.scrollModeX) {
         if (Math.abs(this.fScrollNewMenuPosX) > 1.0F) {
            this.updateMenuPosX(this.iMenuPosX + (int)this.fScrollNewMenuPosX);
            this.fScrollNewMenuPosX *= 0.97F;
         } else {
            this.scrollModeX = false;
         }

         CFG.setRender_3(true);
      }

      if (this.scrollableX && this.iNewMenuPositionX != this.iMenuPosX) {
         this.iMenuPosX = this.iNewMenuPositionX;
         this.updateMenuElements_IsInView();
         CFG.setRender_3(true);
      }

      if (this.iNewMenuPositionY != this.iMenuPosY) {
         this.iMenuPosY = this.iNewMenuPositionY;
         this.updateMenuElements_IsInView();
         CFG.setRender_3(true);
      }
   }

   protected void extraAction() {
   }

   protected void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
      this.draw(oSB, iTranslateX, 0, sliderMenuIsActive);
   }

   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   protected void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.drawBackgroundMode(oSB, sliderMenuIsActive);
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + iTranslateX), (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY), (float)this.getWidth(), (float)(-this.getHeight())
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
   }

   protected final void drawMenu(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.drawMenuElements(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   protected final void endClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var6) {
      }

      this.drawTitle(oSB, iTranslateX, iTranslateY, sliderMenuIsActive, this.getPosY());
      if (this.getCloseable()) {
         this.drawCloseButton(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   protected final void drawHover(SpriteBatch oSB, int iTranslateX, int iTranslateY, int nMenuElementID) {
      try {
         this.getMenuElement(nMenuElementID)
            .drawMenuElementHover2(
               oSB,
               this.getMenuPosX() + iTranslateX,
               this.getMenuPosY() + iTranslateY,
               this.getMenuElementIsActive(true, CFG.menuManager.getActiveMenuElementID())
            );
      } catch (IndexOutOfBoundsException var6) {
      }
   }

   protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         if (this.scrollableY && this.getHeight() < this.iMaxSliderPositionY) {
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
                  this.getPosY()
                     + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100)
                        * (this.getPosY() - this.getMenuPosY())
                        / (this.iMaxSliderPositionY - this.getHeight())
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
                     + iTranslateY,
                  CFG.PADDING * 2 - 2,
                  this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
               );
            ImageManager.getImage(Images.scroll_posiotion_active)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1,
                  this.getPosY()
                     + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100)
                        * (this.getPosY() - this.getMenuPosY())
                        / (this.iMaxSliderPositionY - this.getHeight())
                     + this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
                     + iTranslateY,
                  false,
                  true
               );
            oSB.setColor(Color.WHITE);
         }
      } catch (ArithmeticException var6) {
      }
   }

   protected final void drawMenuElements(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      for(int i = this.iMenuElementsSize - 1; i >= 0; --i) {
         if (this.menuElements.get(i).getVisible() && this.menuElements.get(i).getIsInView()) {
            this.menuElements
               .get(i)
               .draw(
                  oSB,
                  this.getMenuPosX() + iTranslateX,
                  this.getMenuPosY() + iTranslateY,
                  this.getMenuElementIsActive(sliderMenuIsActive, i),
                  this.scrollableY
               );
         }
      }
   }

   protected void updateMenuElements_IsInView() {
      for(int i = 0; i < this.iMenuElementsSize; ++i) {
         this.menuElements.get(i).setIsInView(this.getMenuElementIsInView(i));
      }
   }

   protected void updateMenuElements_IsInView_X() {
      for(int i = 0; i < this.iMenuElementsSize; ++i) {
         this.menuElements.get(i).setIsInView(this.getMenuElementIsInView_X(i));
      }
   }

   protected void updateMenuElements_IsInView_XY() {
      for(int i = 0; i < this.iMenuElementsSize; ++i) {
         this.menuElements.get(i).setIsInView(this.getMenuElementIsInView(i) && this.getMenuElementIsInView_X(i));
      }
   }

   private final boolean getMenuElementIsInView(int i) {
      return this.menuElements.get(i).getPosY() + this.getMenuPosY() > this.getPosY()
            && this.menuElements.get(i).getPosY() + this.getMenuPosY() < this.getPosY() + this.getHeight()
         || this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() > this.getPosY()
            && this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() < this.getPosY() + this.getHeight();
   }

   private final boolean getMenuElementIsInView_X(int i) {
      return this.menuElements.get(i).getPosX() + this.getMenuPosX() >= this.getPosX()
            && this.menuElements.get(i).getPosX() + this.getMenuPosX() <= this.getPosX() + this.getWidth()
         || this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX() >= this.getPosX()
            && this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX() <= this.getPosX() + this.getWidth();
   }

   protected boolean getMenuElementIsActive(boolean sliderMenuIsActive, int i) {
      return sliderMenuIsActive ? i == CFG.menuManager.getActiveMenuElementID() : false;
   }

   protected void drawTitle(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive, int nPosY) {
      if (this.sliderMenuTitle != null) {
         this.sliderMenuTitle.draw(oSB, iTranslateX, this.getPosX(), nPosY + iTranslateY, this.getWidth(), sliderMenuIsActive);
      }

      if (sliderMenuIsActive) {
         if (CFG.menuManager.getSliderMenuResizeMode()) {
            this.drawMenuBorder(oSB);
            this.drawMenuResizeRect(oSB);
         } else if (CFG.menuManager.getSliderMenuTitleMode()) {
            this.drawMenuBorder(oSB);
         }
      }
   }

   protected final void drawMenuBorder(SpriteBatch oSB) {
      oSB.setColor(0.196F, 0.196F, 0.196F, 1.0F);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY(), 1, this.getHeight());
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() - 1, this.getPosY(), 1, this.getHeight());
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY(), this.getWidth(), -1);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - 1, this.getWidth(), -1);
      oSB.setColor(Color.WHITE);
   }

   protected final void drawMenuResizeRect(SpriteBatch oSB) {
      oSB.setColor(0.196F, 0.196F, 0.196F, 0.95F);
      if (CFG.menuManager.getSliderMenuResizeLEFT()) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - 1 - CFG.PADDING * 6, CFG.PADDING * 6, CFG.PADDING * 6);
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.pickerEdge)
            .draw(
               oSB,
               this.getPosX(),
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pickerEdge).getHeight() * 2,
               ImageManager.getImage(Images.pickerEdge).getWidth(),
               ImageManager.getImage(Images.pickerEdge).getHeight(),
               true,
               false
            );
      } else {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - 1 - CFG.PADDING * 6,
               this.getPosY() + this.getHeight() - 1 - CFG.PADDING * 6,
               CFG.PADDING * 6,
               CFG.PADDING * 6
            );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.pickerEdge)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.pickerEdge).getWidth(),
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pickerEdge).getHeight() * 2,
               ImageManager.getImage(Images.pickerEdge).getWidth(),
               ImageManager.getImage(Images.pickerEdge).getHeight(),
               false,
               false
            );
      }

      oSB.setColor(Color.WHITE);
   }

   protected final void drawBackgroundMode(SpriteBatch oSB, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive && (CFG.menuManager.getSliderMenuResizeMode() || CFG.menuManager.getSliderMenuTitleMode())) {
         oSB.setColor(new Color(0.1F, 0.1F, 0.1F, 0.5F));
         ImageManager.getImage(Images.patt).draw2(oSB, 0, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
         oSB.setColor(Color.WHITE);
      }
   }

   protected void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() + iTranslateX,
            this.getPosY() - this.sliderMenuTitle.getHeight() + iTranslateY
         );
   }

   protected final Image getCloseButtonImage(boolean sliderMenuIsActive) {
      return CFG.menuManager.getSliderMenuCloseMode() && sliderMenuIsActive
         ? ImageManager.getImage(Images.btnh_close)
         : ImageManager.getImage(Images.btn_close);
   }

   protected void actionElement(int nMenuElementID) {
   }

   protected void onBackPressed() {
   }

   protected void onMenuPressed() {
   }

   protected void actionClose() {
      this.setVisible(false);
   }

   protected void onHovered() {
   }

   protected final void updateMenuPosX(int nMenuPosX) {
      try {
         if (nMenuPosX > this.getPosX()) {
            this.iNewMenuPositionX = this.getPosX();
            CFG.menuManager.setUpdateSliderMenuPosX(true);
         } else if (nMenuPosX < this.getWidth() + this.getPosX() - this.iMaxSliderPositionX) {
            this.iNewMenuPositionX = this.getWidth() + this.getPosX() - this.iMaxSliderPositionX;
            CFG.menuManager.setUpdateSliderMenuPosX(true);
         } else {
            this.iNewMenuPositionX = nMenuPosX;
         }
      } catch (NullPointerException var3) {
      }
   }

   protected final void updateMenuPosY(int nMenuPosY) {
      try {
         if (nMenuPosY > this.getPosY()) {
            this.iNewMenuPositionY = this.getPosY();
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
         } else if (nMenuPosY < this.getHeight() + this.getPosY() - this.iMaxSliderPositionY) {
            this.iNewMenuPositionY = this.getHeight() + this.getPosY() - this.iMaxSliderPositionY;
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
         } else {
            this.iNewMenuPositionY = nMenuPosY;
         }
      } catch (NullPointerException var3) {
      }
   }

   protected final void scrollTheMenu() {
      if (this.scrollableY && this.iScrollPosY > 0 && this.iScrollPosY2 > 0 && (float)Math.abs(this.iScrollPosY - this.iScrollPosY2) > 3.0F * CFG.DENSITY) {
         this.fScrollNewMenuPosY = (float)(this.iScrollPosY - this.iScrollPosY2) * 1.45F;
         this.scrollModeY = true;
      }

      if (this.scrollableX && this.iScrollPosX > 0 && this.iScrollPosX2 > 0 && Math.abs(this.iScrollPosX - this.iScrollPosX2) > 3) {
         this.fScrollNewMenuPosX = (float)(this.iScrollPosX - this.iScrollPosX2) * 1.45F;
         this.scrollModeX = true;
      }

      this.resetScrollINFO();
   }

   private final void resetScrollINFO() {
      this.iScrollPosY = this.iScrollPosY2 = this.iScrollPosX = this.iScrollPosX2 = -1;
   }

   protected final void stopScrolling() {
      this.resetScrollINFO();
      this.scrollModeY = this.scrollModeX = false;
   }

   protected final void updatedButtonsWidth(int iStartPosX, int iMinWidth) {
      for(int i = 0; i < this.getMenuElementsSize(); ++i) {
         iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + CFG.PADDING;
      }

      this.updateScrollable();
   }

   protected final void updatedButtonsWidth_Padding(int iStartPosX, int iMinWidth, int iPadding) {
      for(int i = 0; i < this.getMenuElementsSize(); ++i) {
         iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + iPadding;
      }

      this.updateScrollable();
   }

   protected final void updatedButtonsWidthFromToID(int iStartButtonID, int iEndButtonID, int iStartPosX, int iMinWidth) {
      for(int i = iStartButtonID; i < iEndButtonID; ++i) {
         iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + CFG.PADDING;
      }

      this.updateScrollable();
   }

   protected final int updateButtonWidth(int iButtonID, int iStartPosX, int iMinWidth) {
      if (this.getMenuElement(iButtonID).getTextWidth() + CFG.PADDING * 4 > iMinWidth) {
         this.getMenuElement(iButtonID).setWidth(this.getMenuElement(iButtonID).getTextWidth() + CFG.PADDING * 4);
      } else {
         this.getMenuElement(iButtonID).setWidth(iMinWidth);
      }

      this.getMenuElement(iButtonID).setPosX(iStartPosX);
      this.updateScrollable();
      return this.getMenuElement(iButtonID).getWidth();
   }

   protected final int getMenuElementsSize() {
      return this.iMenuElementsSize;
   }

   protected final MenuElement getMenuElement(int iID) {
      return this.menuElements.get(iID);
   }

   protected final void setMenuElement(int iID, MenuElement nMenuElement) {
      this.menuElements.set(iID, null);
      this.menuElements.set(iID, nMenuElement);
   }

   protected int getPosX() {
      return this.iPosX;
   }

   protected void setPosX(int iPosX) {
      this.iPosX = iPosX;
      this.iMenuPosX = iPosX;
      this.updateMenuPosX(this.iMenuPosX);
   }

   protected final void setPosX_Force(int iPosX) {
      this.iPosX = iPosX;
      this.iMenuPosX = iPosX;
      this.iNewMenuPositionX = iPosX;
      CFG.menuManager.setUpdateSliderMenuPosX(false);
   }

   protected int getPosY() {
      return this.iPosY;
   }

   protected void setPosY(int iPosY) {
      this.iPosY = iPosY;
      this.iMenuPosY = iPosY;
      this.updateMenuPosY(this.iMenuPosY);
   }

   protected int getWidth() {
      return this.iWidth;
   }

   protected boolean setWidth(int iWidth) {
      if (iWidth < CFG.GAME_WIDTH) {
         if (iWidth < this.getMinWidth()) {
            this.iWidth = this.getMinWidth();
            return false;
         } else {
            this.iWidth = iWidth;
            return true;
         }
      } else {
         this.iWidth = CFG.GAME_WIDTH;
         return true;
      }
   }

   protected final int getMinWidth() {
      try {
         return CFG.PADDING * 2;
      } catch (NullPointerException var2) {
         return CFG.PADDING * 2;
      }
   }

   protected int getHeight() {
      return this.iHeight;
   }

   protected void setHeight(int iHeight) {
      this.iHeight = iHeight;
      if (iHeight < this.getMinHeight()) {
         this.iHeight = this.getMinHeight();
      }

      if (iHeight + this.getPosY() + (this.sliderMenuTitle != null ? this.sliderMenuTitle.getHeight() : 0) >= CFG.GAME_HEIGHT) {
         this.iHeight = CFG.GAME_HEIGHT - (this.getPosY() + (this.sliderMenuTitle != null ? this.sliderMenuTitle.getHeight() : 0));
      }

      this.updateScrollable();
   }

   protected final int getMinHeight() {
      return CFG.PADDING + CFG.BUTTON_HEIGHT;
   }

   protected final SliderMenuTitle getTitle() {
      return this.sliderMenuTitle;
   }

   protected final boolean getScrollableY() {
      return this.scrollableY;
   }

   protected final void setMenuPosY(int iMenuPosY) {
      this.updateMenuPosY(iMenuPosY);
   }

   protected int getMenuPosY() {
      return this.iMenuPosY;
   }

   protected final int getNewMenuPosY() {
      return this.iNewMenuPositionY;
   }

   protected final int getNewMenuPosX() {
      return this.iNewMenuPositionX;
   }

   protected final boolean getScrollableX() {
      return this.scrollableX;
   }

   protected final void setMenuPosX(int iMenuPosX) {
      this.updateMenuPosX(iMenuPosX);
   }

   protected int getMenuPosX() {
      return this.iMenuPosX;
   }

   protected boolean getVisible() {
      return this.visible;
   }

   protected void setVisible(boolean visible) {
      this.visible = visible;
   }

   protected final boolean getCloseable() {
      return this.closeable;
   }

   protected final boolean getMoveable() {
      return this.sliderMenuTitle == null ? false : this.sliderMenuTitle.getMoveable();
   }

   protected final boolean getResizable() {
      return this.sliderMenuTitle == null ? false : this.sliderMenuTitle.getResizable();
   }

   protected final void setScrollPosY(int iScrollPosY) {
      this.iScrollPosY2 = this.iScrollPosY;
      this.iScrollPosY = iScrollPosY;
   }

   protected final int getScrollPosY() {
      return this.iScrollPosY;
   }

   protected final void setScrollPosX(int iScrollPosX) {
      this.iScrollPosX2 = this.iScrollPosX;
      this.iScrollPosX = iScrollPosX;
   }

   protected final boolean getScrollModeY() {
      return this.scrollModeY;
   }
}
