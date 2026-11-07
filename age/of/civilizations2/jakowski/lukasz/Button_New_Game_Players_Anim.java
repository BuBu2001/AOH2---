package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_New_Game_Players_Anim extends Button_New_Game_Players {
   protected static long lTimeAnimation = 0L;
   protected static int animationState = 0;
   protected static final int ANIMATION_T = 750;

   protected Button_New_Game_Players_Anim(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
   }

   protected Button_New_Game_Players_Anim(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box_hover).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight()
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box_hover).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               true
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight() * 2 + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_box_hover)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box_hover).getWidth() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box_hover).getHeight() * 2 + iTranslateY,
               ImageManager.getImage(Images.new_game_box_hover).getWidth(),
               ImageManager.getImage(Images.new_game_box_hover).getHeight(),
               true,
               true
            );
      } else {
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight()
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_box).getWidth(),
               this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight(),
               true
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight() * 2 + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth(),
               ImageManager.getImage(Images.new_game_box).getHeight(),
               false,
               true
            );
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.new_game_box).getHeight() * 2 + iTranslateY,
               ImageManager.getImage(Images.new_game_box).getWidth(),
               ImageManager.getImage(Images.new_game_box).getHeight(),
               true,
               true
            );
      }

      oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.215F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - 4
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.325F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      if (this.getClickable() && this.getIsHovered() && animationState >= 0) {
         if (animationState == 0) {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (lTimeAnimation < System.currentTimeMillis() - 750L) {
               ++animationState;
               lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (lTimeAnimation < System.currentTimeMillis() - 750L) {
               animationState = 0;
               lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
         oSB.setColor(Color.WHITE);
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void setIsHovered(boolean isHovered) {
      super.setIsHovered(isHovered);
      lTimeAnimation = System.currentTimeMillis();
      animationState = 0;
   }
}
