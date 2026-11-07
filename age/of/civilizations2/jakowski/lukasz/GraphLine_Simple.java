package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class GraphLine_Simple extends GraphLine {
   private int iHeight;

   protected GraphLine_Simple(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      super(fromPosX, fromPosY, toPosX, toPosY);
      this.setWidth(toPosX - fromPosX);
      this.iHeight = toPosY - fromPosY;
   }

   @Override
   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int i) {
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX - i, nPosY + this.getPosY(), this.getWidth());
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX - i + this.getWidth(), nPosY + this.getPosY(), 1, this.iHeight);
   }
}
