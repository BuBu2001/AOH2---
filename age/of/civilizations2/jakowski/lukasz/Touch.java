package age.of.civilizations2.jakowski.lukasz;

class Touch {
   private static int mousePosX = 0;
   private static int mousePosY = 0;

   protected static final void setMousePosXY(int nMousePosX, int nMousePosY) {
      if (CFG.menuManager.isSomethingHovered()) {
         CFG.setRender_3(true);
      }

      mousePosX = nMousePosX;
      mousePosY = nMousePosY;
   }

   protected static final int getMousePosX() {
      return mousePosX;
   }

   protected static final int getMousePosY() {
      return mousePosY;
   }

   protected final void actionDown(int nPosX, int nPosY, int nPointer) {
      if (nPointer == 0) {
         CFG.menuManager.resetMobileHover();
         if (!CFG.menuManager.actionDown(nPosX, nPosY)) {
            CFG.map.getMapTouchManager().actionDown(nPosX, nPosY);
         }
      }
   }

   protected final void actionMove(int nPosX, int nPosY, int nPosX2, int nPosY2) {
      CFG.map.getMapTouchManager().actionMove(nPosX, nPosY, nPosX2, nPosY2);
   }

   protected final void actionMove(int nPosX, int nPosY, int nPointer) {
      if (nPointer == 0 && !CFG.map.getMapScale().getScaleMode() && !CFG.menuManager.actionMove(nPosX, nPosY)) {
         CFG.map.getMapTouchManager().actionMove(nPosX, nPosY);
      }
   }

   protected final void actionUp(int nPosX, int nPosY, int nPointer) {
      if (nPointer == 0) {
         if (!CFG.menuManager.actionUp(nPosX, nPosY)) {
            CFG.map.getMapTouchManager().actionUp(nPosX, nPosY);
         }

         resetAllModes();
      } else if (CFG.map.getMapScale().getScaleMode()) {
         resetAllModes();
      }
   }

   protected final void actionMove_Hover(int nPosX, int nPosY) {
      if (CFG.menuManager.getFromViewID() < 0) {
         CFG.menuManager.actionMove_Hover(nPosX, nPosY);
         CFG.menuManager.updateHoveredMenuElement_Hover(nPosX, nPosY);
         CFG.menuManager.updateHoveredProvince_Hover(nPosX, nPosY);
         if (CFG.menuManager.get_MenuElementHover_IsInView()) {
            CFG.setRender_3(true);
         }
      }
   }

   protected static final void resetAllModes() {
      CFG.menuManager.setActiveSliderMenuID(-1);
      CFG.menuManager.setActiveMenuElementID(-1);
      CFG.menuManager.setSliderMenuMode(false);
      CFG.menuManager.setSliderMenuTitleMode(false);
      CFG.menuManager.setSliderMenuResizeMode(false);
      CFG.menuManager.setSliderMode(false);
      CFG.menuManager.setSlideMapMode(false);
      CFG.menuManager.setTextSliderMode(false);
      CFG.menuManager.setSliderMenuCloseMode(false);
      CFG.menuManager.setKeyboardMode(false);
      CFG.menuManager.setFlagEditorMode(false);
      CFG.menuManager.setGraphMode(false);
      CFG.menuManager.setGraphButtonMode(false);
      CFG.menuManager.setGraphButtonModeX(false);
      CFG.menuManager.setGraphButtonMode2(false);
      CFG.menuManager.setColorPickerMode(false);
      CFG.map.getMapTouchManager().setActionMap(false);
      CFG.map.getMapScale().resetScaleInfo();
      CFG.map.getMapScroll().resetScrollInfo();
   }
}
