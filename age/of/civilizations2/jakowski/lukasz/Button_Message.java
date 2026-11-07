package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Message extends Button {
   protected static final float BUTTON_PERC_HEIGHT = 0.6F;
   private long lTime = 0L;
   private float fAlphaMod = 0.0F;
   private boolean backAnimation = false;
   private int iMessageID = 0;
   private int iFromCivID = 0;
   private int iImageID;
   private int iBGImageID;
   private long lTimeAnimation = 0L;
   private int animationState = 0;
   protected static final int ANIMATION_T = 750;

   protected Button_Message(int iPosX, int iPosY, int iMessageID, int iFromCivID, int iImageID, int iBGImageID) {
      super.init(
         "",
         -1,
         iPosX,
         iPosY,
         Button_Diplomacy.iDiploWidth + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 4,
         (int)((float)CFG.BUTTON_HEIGHT * 0.6F),
         true,
         true,
         false,
         false
      );
      this.iMessageID = iMessageID;
      this.iFromCivID = iFromCivID;
      this.iImageID = iImageID;
      this.iBGImageID = iBGImageID;
      this.lTimeAnimation = System.currentTimeMillis();
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
      ImageManager.getImage(this.iBGImageID)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(this.iBGImageID).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth(),
            this.getHeight() - ImageManager.getImage(this.iBGImageID).getHeight(),
            false,
            false
         );
      ImageManager.getImage(this.iBGImageID)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(this.iBGImageID).getHeight() + iTranslateY,
            ImageManager.getImage(this.iBGImageID).getWidth(),
            this.getHeight() - ImageManager.getImage(this.iBGImageID).getHeight(),
            true,
            false
         );
      ImageManager.getImage(this.iBGImageID)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - ImageManager.getImage(this.iBGImageID).getHeight()
               - ImageManager.getImage(this.iBGImageID).getHeight()
               + iTranslateY,
            this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth(),
            ImageManager.getImage(this.iBGImageID).getHeight(),
            false,
            true
         );
      ImageManager.getImage(this.iBGImageID)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(this.iBGImageID).getWidth() + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - ImageManager.getImage(this.iBGImageID).getHeight()
               - ImageManager.getImage(this.iBGImageID).getHeight()
               + iTranslateY,
            ImageManager.getImage(this.iBGImageID).getWidth(),
            ImageManager.getImage(this.iBGImageID).getHeight(),
            true,
            true
         );
      if (this.getIsHovered() || isActive) {
         if (this.iBGImageID == Images.messages_r) {
            oSB.setColor(new Color(0.3137255F, 0.13725491F, 0.047058824F, 0.475F - this.fAlphaMod));
         } else if (this.iBGImageID == Images.messages_g) {
            oSB.setColor(new Color(0.13333334F, 0.23529412F, 0.02745098F, 0.475F - this.fAlphaMod));
         } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.475F - this.fAlphaMod));
         }

         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() - 4
            );
      }

      if (this.animationState >= 0) {
         if (this.animationState == 0) {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
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
            if (this.lTimeAnimation < System.currentTimeMillis() - 750L) {
               ++this.animationState;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
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
            if (this.lTimeAnimation < System.currentTimeMillis() - 750L) {
               this.animationState = 0;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iFromCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.flag_rect).getWidth() - CFG.PADDING * 3 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - ImageManager.getImage(Images.flag_rect).getHeight() / 2
               - CFG.game.getCiv(this.iFromCivID).getFlag().getHeight()
               + iTranslateY,
            ImageManager.getImage(Images.flag_rect).getWidth(),
            ImageManager.getImage(Images.flag_rect).getHeight()
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.flag_rect).getWidth() - CFG.PADDING * 3 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY
         );
      if (this.getIsHovered()) {
         if (this.lTime < System.currentTimeMillis() - 30L) {
            if (this.backAnimation) {
               this.fAlphaMod -= 0.02F;
               if (this.fAlphaMod < 0.0F) {
                  this.backAnimation = false;
               }
            } else {
               this.fAlphaMod += 0.02F;
               if (this.fAlphaMod >= 0.5F) {
                  this.backAnimation = true;
               }
            }

            this.lTime = System.currentTimeMillis();
         }

         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F - this.fAlphaMod));
         CFG.setRender_3(true);
      } else {
         this.backAnimation = false;
         this.fAlphaMod = 0.0F;
         this.lTime = System.currentTimeMillis();
      }

      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX()
               + CFG.PADDING * 3
               + (Button_Diplomacy.iDiploWidth - CFG.PADDING * 4) / 2
               - ImageManager.getImage(this.iImageID).getWidth() / 2
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return this.getClickable()
         ? (isActive ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO))
         : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F);
   }

   @Override
   protected int getCurrent() {
      return this.iMessageID;
   }

   @Override
   protected void buildElementHover() {
      try {
         this.menuElementHover = CFG.game
            .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .getMessage(this.iMessageID)
            .getHover();
      } catch (IndexOutOfBoundsException var2) {
         this.menuElementHover = null;
      }
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
