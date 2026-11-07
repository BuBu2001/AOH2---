package age.of.civilizations2.jakowski.lukasz;

class Space extends MenuElement {
   protected Space(int iPosX, int iPosY, int iWidth, int iHeight) {
      this.typeOfElement = MenuElement.TypeOfElement.SPACE;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setClickable(false);
   }
}
