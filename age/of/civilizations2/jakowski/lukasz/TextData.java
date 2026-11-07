package age.of.civilizations2.jakowski.lukasz;

class TextData {
   private String sText;
   private int iWidth;

   protected TextData(String sText) {
      this.sText = sText;
      CFG.glyphLayout.setText(CFG.fontMain, sText);
      this.iWidth = (int)CFG.glyphLayout.width;
   }

   protected String getString() {
      return this.sText;
   }

   protected int getWidth() {
      return this.iWidth;
   }
}
