package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_ArrowLeft extends Button {
   protected Button_ArrowLeft(int iPosX, int iPosY, int iWidth, int iHeight) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, true, true, false, false, null);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
         ImageManager.getImage(Images.btn_up)
            .draw(
               oSB,
               this.getPosX() + ImageManager.getImage(Images.btn_up).getHeight() / 2 + this.getWidth() / 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - ImageManager.getImage(Images.btn_up).getWidth() / 2
                  - ImageManager.getImage(Images.btn_up).getHeight()
                  + iTranslateY,
               ImageManager.getImage(Images.btn_up).getWidth(),
               ImageManager.getImage(Images.btn_up).getHeight(),
               270.0F
            );
         oSB.setColor(Color.WHITE);
      } else {
         ImageManager.getImage(Images.btn_up)
            .draw(
               oSB,
               this.getPosX() + ImageManager.getImage(Images.btn_up).getHeight() / 2 + this.getWidth() / 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - ImageManager.getImage(Images.btn_up).getWidth() / 2
                  - ImageManager.getImage(Images.btn_up).getHeight()
                  + iTranslateY,
               ImageManager.getImage(Images.btn_up).getWidth(),
               ImageManager.getImage(Images.btn_up).getHeight(),
               270.0F
            );
      }
   }
}
