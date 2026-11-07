package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_PLAY extends Button_Game {
   private long lTime = 0L;
   private float fAlphaMod = 0.0F;
   private boolean backAnimation = false;
   private long lTimeAnimation = 0L;
   private int animationState = 0;
   protected static final int ANIMATION_T = 1000;

   protected Button_Game_PLAY(String sText, int iTextPositionX, int iPosX, int iPosY, int nWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
      this.lTimeAnimation = System.currentTimeMillis();
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      if (this.lTime < System.currentTimeMillis() - 26L) {
         if (this.backAnimation) {
            this.fAlphaMod -= 0.02F;
            if (this.fAlphaMod < 0.0F) {
               this.backAnimation = false;
            }
         } else {
            this.fAlphaMod += 0.02F;
            if (this.fAlphaMod > 0.4F) {
               this.backAnimation = true;
            }
         }

         this.lTime = System.currentTimeMillis();
      }

      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F - this.fAlphaMod));
      CFG.setRender_3(true);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - 2
         );
      if (this.animationState >= 0) {
         if (this.animationState == 0) {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.625F : 0.525F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
               ++this.animationState;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.625F : 0.525F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
               this.animationState = 0;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
      }

      oSB.setColor(Color.WHITE);
   }
}
