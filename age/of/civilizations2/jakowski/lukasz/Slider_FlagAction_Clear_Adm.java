package age.of.civilizations2.jakowski.lukasz;

public class Slider_FlagAction_Clear_Adm extends Slider_FlagAction_Clear_Tech {
   protected Slider_FlagAction_Clear_Adm(float fModifier, String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super(fModifier, sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   protected String getTextLeft() {
      return this.getCurrent() > 0 ? "-" + (float)((int)((float)this.getCurrent() * this.fModifier * 100.0F)) / 100.0F + "%" : "";
   }
}
