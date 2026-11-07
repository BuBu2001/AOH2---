package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_Economy_SliderDesc extends Text {
   protected static final float TEXT2_SCALE = 0.7F;
   protected static final float TEXT3_SCALE = 0.6F;
   private String sText2;
   private int iText2Width;
   private String sText_Progress;
   private int iText_ProgressWidth;
   private String sText_ProgressPerc;

   protected Text_Economy_SliderDesc(String sText, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
      this.sText2 = sText;
   }

   protected Text_Economy_SliderDesc(
      String sText, String sText_Progress, String sText_ProgressPerc, String sText2, int iPosX, int iPosY, int iWidth, int iHeight
   ) {
      super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
      this.sText2 = sText;
      this.sText_Progress = sText_Progress;
      this.sText_ProgressPerc = sText_ProgressPerc;
      CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
      this.iText2Width = (int)(CFG.glyphLayout.width * 0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sText_Progress);
      this.iText_ProgressWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.15F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 2 / 5,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() * 2 / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 2 / 5,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.research)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               - 1
               - ImageManager.getImage(Images.research).getHeight()
               + (this.getHeight() - (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.7F, Images.research))) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7F, Images.research)),
            (int)((float)ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.7F, Images.research))
         );
      ImageManager.getImage(Images.technology)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.7F, Images.technology))
               + iTranslateX,
            this.getPosY()
               - 1
               - ImageManager.getImage(Images.technology).getHeight()
               + (this.getHeight() - (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.7F, Images.technology))) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.7F, Images.technology)),
            (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.7F, Images.technology))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText2,
         this.getPosX()
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sText_Progress,
         this.getPosX()
            + this.iText2Width
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)((float)this.getTextWidth() * 0.7F)
            - (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.7F, Images.technology))
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText_ProgressPerc,
         this.getPosX()
            + this.iText2Width
            + this.iText_ProgressWidth
            + CFG.PADDING * 3
            + (int)((float)ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.7F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   private final float getImageScale(float fScale, int nImageID) {
      return (float)this.iTextHeight * fScale / (float)ImageManager.getImage(nImageID).getHeight();
   }
}
