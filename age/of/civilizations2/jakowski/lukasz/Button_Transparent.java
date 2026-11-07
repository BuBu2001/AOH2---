package age.of.civilizations2.jakowski.lukasz;

class Button_Transparent extends Button {
   protected Button_Transparent(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON_TRANSPARENT;
   }

   protected Button_Transparent(int iTextPos, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", iTextPos, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON_TRANSPARENT;
   }
}
