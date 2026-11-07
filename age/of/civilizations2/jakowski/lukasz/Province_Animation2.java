package age.of.civilizations2.jakowski.lukasz;

class Province_Animation2 {
   protected final int START_PROVINCE_ALPHA = 60;
   protected final int START_PROVINCE_BORDER_ALPHA = 255;
   protected final int TIME_UPDATE = 60;
   protected long lTime = 0L;
   protected int fAlpha = 60;
   protected int iColorStepID;
   protected int iStepID = 0;
   protected boolean backAnimation = false;
   protected long lTimeBorder = 0L;
   protected int iStepIDBorder = 0;
   protected int iBorderAlpha = 255;
   protected boolean backAnimationBorder = false;

   protected final void update() {
      if (this.lTime < System.currentTimeMillis() - 60L) {
         ++this.iStepID;
         if (this.backAnimation) {
            this.fAlpha = (int)((float)this.fAlpha + 2.75F);
            this.iBorderAlpha += 6;
            --this.iColorStepID;
         } else {
            this.fAlpha = (int)((float)this.fAlpha - 2.75F);
            this.iBorderAlpha -= 6;
            ++this.iColorStepID;
         }

         this.lTime = System.currentTimeMillis();
         if (this.iStepID == 20) {
            this.iStepID = 0;
            this.backAnimation = !this.backAnimation;
            this.backAnimationBorder = !this.backAnimationBorder;
            this.lTime += this.backAnimation ? 450L : 600L;
         }

         CFG.setRender_3(true);
      }
   }

   protected final void resetAnimationData() {
      this.lTime = 0L;
      this.fAlpha = 60;
      this.iStepID = 0;
      this.backAnimation = false;
      this.iColorStepID = 0;
      this.lTimeBorder = System.currentTimeMillis() + 200L;
      this.iStepIDBorder = 0;
      this.iBorderAlpha = 255;
      this.backAnimationBorder = false;
   }

   protected final int getAlpha() {
      return this.fAlpha;
   }

   protected final int getBorderAlpha() {
      return this.iBorderAlpha;
   }

   protected final boolean getBackAnimation() {
      return this.backAnimation;
   }

   protected final int getStepID() {
      return this.iStepID;
   }

   protected final int getColorStepID() {
      return this.iColorStepID;
   }
}
