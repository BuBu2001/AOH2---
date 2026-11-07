package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Graph_Vertical_Data_Value_Population extends Graph_Vertical_Data_Value {
   protected Graph_Vertical_Data_Value_Population(int iValue, int iColorDataID) {
      super(iValue, iColorDataID);
   }

   @Override
   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, Color nColor) {
      oSB.setColor(this.getColor(0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - nHeight - this.iHeight, nWidth, this.iHeight);
      oSB.setColor(this.getColor(0.7F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY - nHeight - this.iHeight - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.iHeight, false, true);
      oSB.setColor(this.getColor(0.2F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX, nPosY - nHeight - this.iHeight - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 3, this.iHeight, false, false);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + nWidth - nWidth / 3,
            nPosY - nHeight - this.iHeight - ImageManager.getImage(Images.slider_gradient).getHeight(),
            nWidth / 3,
            this.iHeight,
            true,
            false
         );
   }

   @Override
   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int nAnimationHeight, Color nColor) {
      oSB.setColor(this.getColor(0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - nHeight - nAnimationHeight, nWidth, nAnimationHeight);
      oSB.setColor(this.getColor(0.7F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY - nHeight - nAnimationHeight - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nAnimationHeight, false, true);
      oSB.setColor(this.getColor(0.2F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX,
            nPosY - nHeight - nAnimationHeight - ImageManager.getImage(Images.slider_gradient).getHeight(),
            nWidth / 3,
            nAnimationHeight,
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + nWidth - nWidth / 3,
            nPosY - nHeight - nAnimationHeight - ImageManager.getImage(Images.slider_gradient).getHeight(),
            nWidth / 3,
            nAnimationHeight,
            true,
            false
         );
   }

   protected final Color getColor(float fAlpha) {
      try {
         return new Color(
            (float)CFG.game.getCiv(this.iColorDataID).getR() / 255.0F,
            (float)CFG.game.getCiv(this.iColorDataID).getG() / 255.0F,
            (float)CFG.game.getCiv(this.iColorDataID).getB() / 255.0F,
            fAlpha
         );
      } catch (IndexOutOfBoundsException var3) {
         return new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, fAlpha);
      }
   }
}
