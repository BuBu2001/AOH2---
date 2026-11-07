package age.of.civilizations2.jakowski.lukasz;

class Start_The_Game_Data {
   private long lTime;
   private float fProvincesAlpha;
   private float fCapitalsAlpha;
   private float fWastelandAlpha;
   private float fStaightLinePercentage;
   private float fDashedLinePercentage;
   private boolean ready = false;
   private boolean reverse = false;
   private final int TIME_PROVINCE_ALPHA = 3250;
   private final int TIME_CAPITALS_PROVINCE_ALPHA = 1500;
   private final int TIME_STRAIGHT_LINE = 1250;
   private final int TIME_DASHED_LINE = 3500;
   protected final float TIMER_MODFIER_END_GAME = 1.3F;

   protected Start_The_Game_Data(boolean reverse) {
      if (reverse) {
         this.lTime = System.currentTimeMillis();
         this.fProvincesAlpha = (float)CFG.settingsManager.PROVINCE_ALPHA;
         this.fCapitalsAlpha = (float)CFG.settingsManager.PROVINCE_ALPHA;
         this.fWastelandAlpha = CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F;
         this.fStaightLinePercentage = 100.0F;
         this.fDashedLinePercentage = 100.0F;
      } else {
         this.lTime = System.currentTimeMillis();
         this.fProvincesAlpha = 0.0F;
         this.fCapitalsAlpha = 0.0F;
         this.fWastelandAlpha = 0.0F;
         this.fStaightLinePercentage = 2.0F;
         this.fDashedLinePercentage = 2.0F;
      }

      this.reverse = reverse;
   }

   protected final void updateData() {
      if (this.reverse) {
         this.fProvincesAlpha -= Math.abs((float)(System.currentTimeMillis() - this.lTime) / 4225.0F * (float)CFG.settingsManager.PROVINCE_ALPHA);
         this.fCapitalsAlpha = this.fProvincesAlpha;
         if (this.fProvincesAlpha < 0.0F) {
            this.fProvincesAlpha = 0.0F;
            this.fCapitalsAlpha = 0.0F;
         }

         this.fWastelandAlpha -= Math.abs((float)(System.currentTimeMillis() - this.lTime) / 4225.0F * CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F);
         if (this.fWastelandAlpha < 0.0F) {
            this.fWastelandAlpha = 0.0F;
         }

         this.fStaightLinePercentage -= (float)(System.currentTimeMillis() - this.lTime) / 4550.0F * 98.0F;
         if (this.fStaightLinePercentage < 0.0F) {
            this.fStaightLinePercentage = 0.0F;
            this.ready = true;
         }

         this.fDashedLinePercentage -= (float)(System.currentTimeMillis() - this.lTime) / 1625.0F * 98.0F;
         if (this.fDashedLinePercentage < 0.0F) {
            this.fDashedLinePercentage = 0.0F;
         }

         this.lTime = System.currentTimeMillis();
      } else {
         this.fProvincesAlpha += Math.abs((float)(System.currentTimeMillis() - this.lTime) / 3250.0F * (float)CFG.settingsManager.PROVINCE_ALPHA);
         if (this.fProvincesAlpha > (float)CFG.settingsManager.PROVINCE_ALPHA) {
            this.fProvincesAlpha = (float)CFG.settingsManager.PROVINCE_ALPHA;
         }

         this.fWastelandAlpha += Math.abs((float)(System.currentTimeMillis() - this.lTime) / 3250.0F * CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F);
         if (this.fWastelandAlpha > CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F) {
            this.fWastelandAlpha = CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F;
         }

         this.fCapitalsAlpha += Math.abs((float)(System.currentTimeMillis() - this.lTime) / 1500.0F * (float)CFG.settingsManager.PROVINCE_ALPHA);
         if (this.fCapitalsAlpha > (float)CFG.settingsManager.PROVINCE_ALPHA) {
            this.fCapitalsAlpha = (float)CFG.settingsManager.PROVINCE_ALPHA;
         }

         this.fStaightLinePercentage += (float)(System.currentTimeMillis() - this.lTime) / 1250.0F * 98.0F;
         if (this.fStaightLinePercentage > 100.0F) {
            this.fStaightLinePercentage = 100.0F;
         }

         this.fDashedLinePercentage += (float)(System.currentTimeMillis() - this.lTime) / 3500.0F * 98.0F;
         if (this.fDashedLinePercentage > 100.0F) {
            this.fDashedLinePercentage = 100.0F;
            this.ready = true;
            Menu_StartTheGame.done();
         }

         this.lTime = System.currentTimeMillis();
      }
   }

   protected final int getProvincesAlpha() {
      return (int)Math.abs(this.fProvincesAlpha);
   }

   protected final int getCapitalsAlpha() {
      return (int)Math.abs(this.fCapitalsAlpha);
   }

   protected final int getWastelandAlpha() {
      return (int)Math.abs(this.fWastelandAlpha);
   }

   protected final float getStraightLinePercentage() {
      return this.fStaightLinePercentage / 100.0F;
   }

   protected final float getDashedLinePercentage() {
      return this.fDashedLinePercentage / 100.0F;
   }

   protected final boolean getIsDone() {
      return this.ready;
   }
}
