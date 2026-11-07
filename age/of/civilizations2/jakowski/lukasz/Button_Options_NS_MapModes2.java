package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Options_NS_MapModes2 extends Button_Options_NS_MapModes {
   protected Button_Options_NS_MapModes2(int iCurrent, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(iCurrent, sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   protected Button_Options_NS_MapModes2(
      int iCurrent, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(iCurrent, sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
            this.getWidth(),
            this.getHeight() - CFG.PADDING
         );
      oSB.setColor(
         new Color(
            CFG.COLOR_INFO_BOX_GRADIENT.r,
            CFG.COLOR_INFO_BOX_GRADIENT.g,
            CFG.COLOR_INFO_BOX_GRADIENT.b,
            isActive ? 0.225F : (this.getIsHovered() ? 0.175F : 0.225F)
         )
      );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth() / 2,
            this.getHeight() - CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX + this.getWidth() - this.getWidth() / 2,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth() / 2,
            this.getHeight() - CFG.PADDING,
            true,
            false
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.45F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            (this.getHeight() - CFG.PADDING) / 5
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.375F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + CFG.PADDING / 2
               + iTranslateY
               + (this.getHeight() - CFG.PADDING)
               - (this.getHeight() - CFG.PADDING) / 5
               - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            (this.getHeight() - CFG.PADDING) / 5,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, !isActive && !this.getIsHovered() ? 0.225F : 0.325F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY + (this.getHeight() - CFG.PADDING) - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING / 2 + iTranslateY + (this.getHeight() - CFG.PADDING) - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      CFG.drawRect(
         oSB, this.getPosX() + iTranslateX - 1, this.getPosY() + CFG.PADDING / 2 + iTranslateY - 2, this.getWidth() + 2, this.getHeight() - CFG.PADDING + 2
      );
      oSB.setColor(Color.WHITE);
   }
}
