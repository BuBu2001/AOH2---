package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class SliderMenuTitle {
   private String sText;
   private int iTextWidth = -1;
   private int iTextHeight = -1;
   private int iHeight;
   private boolean moveable = false;
   private boolean resizable = false;

   protected SliderMenuTitle(String sText, int iHeight, boolean moveable, boolean resizable) {
      this.setText(sText);
      this.iHeight = iHeight;
      this.moveable = moveable;
      this.resizable = resizable;
   }

   protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.title_edge)
         .draw2(
            oSB,
            nPosX + nWidth - ImageManager.getImage(Images.title_edge).getWidth() + iTranslateX,
            nPosY - ImageManager.getImage(Images.title_edge).getHeight() - this.iHeight,
            ImageManager.getImage(Images.title_edge).getWidth(),
            this.iHeight,
            true,
            true
         );
      ImageManager.getImage(Images.title_edge)
         .draw2(
            oSB,
            nPosX + iTranslateX,
            nPosY - ImageManager.getImage(Images.title_edge).getHeight() - this.iHeight,
            nWidth - ImageManager.getImage(Images.title_edge).getWidth(),
            this.iHeight,
            false,
            true
         );
      this.drawText(oSB, iTranslateX, nPosX, nPosY, nWidth, sliderMenuIsActive);
   }

   protected void drawText(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
      CFG.drawText(
         oSB,
         this.sText,
         nPosX + nWidth / 2 - this.iTextWidth / 2 + iTranslateX,
         nPosY - this.iHeight + this.iHeight / 2 - this.iTextHeight / 2,
         new Color(0.92941177F, 0.99607843F, 1.0F, 1.0F)
      );
   }

   protected final String getText() {
      return this.sText;
   }

   protected final void setText(String sText) {
      this.sText = sText;
      this.setTextWidth(-1);
      if (sText != null && this.getTextWidth() < 0) {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.setTextWidth((int)CFG.glyphLayout.width);
         this.setTextHeight((int)CFG.glyphLayout.height);
      }
   }

   protected final int getHeight() {
      return this.iHeight;
   }

   protected final boolean getMoveable() {
      return this.moveable;
   }

   protected final int getTextWidth() {
      return this.iTextWidth;
   }

   protected final int getTextHeight() {
      return this.iTextHeight;
   }

   protected final void setTextWidth(int iTextWidth) {
      this.iTextWidth = iTextWidth;
   }

   protected final void setTextHeight(int iTextHeight) {
      this.iTextHeight = iTextHeight;
   }

   protected final boolean getResizable() {
      return this.resizable;
   }
}
