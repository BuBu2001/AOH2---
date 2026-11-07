package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Game_Accept extends Button_Game {
   private long lTimeAnimation = 0L;
   private int animationState = 0;
   private final int ANIMATION_T = 750;

   protected Button_Game_Accept(int iPosX, int iPosY, boolean isClickable) {
      super("", 0, iPosX, iPosY, isClickable);
   }

   @Override
   protected final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      if (isActive) {
         ImageManager.getImage(Images.btn_v_active)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_v_active).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_v_active).getHeight() / 2 + iTranslateY
            );
      } else {
         if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
         }

         ImageManager.getImage(Images.btn_v)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_v).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_v).getHeight() / 2 + iTranslateY
            );
         oSB.setColor(Color.WHITE);
      }

      if (this.animationState >= 0) {
         if (this.animationState == 0) {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.675F : 0.575F));
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
            if (this.lTimeAnimation < System.currentTimeMillis() - 750L) {
               ++this.animationState;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, this.getIsHovered() ? 0.675F : 0.575F));
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
            if (this.lTimeAnimation < System.currentTimeMillis() - 750L) {
               this.animationState = 0;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
         oSB.setColor(Color.WHITE);
      }
   }
}
