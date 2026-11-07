package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Province_Animation_MoveUnits2 {
   private long lTimeLine;
   private int iLineOffset = 0;
   protected int iLineOffsetInterval = 75;

   protected final void resetData() {
      this.lTimeLine = System.currentTimeMillis();
      this.iLineOffset = 0;
   }

   protected Province_Animation_MoveUnits2() {
      this.resetData();
   }

   protected final void update() {
      if (this.lTimeLine < System.currentTimeMillis() - (long)this.iLineOffsetInterval) {
         ++this.iLineOffset;
         this.lTimeLine = System.currentTimeMillis();
         CFG.setRender_3(true);
      }
   }

   protected void updateColor(SpriteBatch oSB, int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()) {
         oSB.setColor(
            new Color(
               CFG.getColorStep(255, 55, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 30),
               CFG.getColorStep(255, 55, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 30),
               CFG.getColorStep(255, 55, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 30),
               (float)CFG.game.getProvinceAnimation_Active_Data().getAlpha()
                  / (nProvinceID == CFG.chosenProvinceID ? 1.75F : 4.0F)
                  / 255.0F
                  * (CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F)
            )
         );
      } else {
         oSB.setColor(
            new Color(
               CFG.getColorStep(255, 55, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 30),
               CFG.getColorStep(255, 55, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 30),
               CFG.getColorStep(255, 55, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 30),
               (float)CFG.game.getProvinceAnimation_Active_Data().getAlpha()
                  / (nProvinceID == CFG.chosenProvinceID ? 1.0F : 2.0F)
                  / 255.0F
                  * (CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F)
            )
         );
      }
   }

   protected int getLineOffset() {
      return this.iLineOffset;
   }

   protected void setLineOffsetInterval(int iLineOffsetInterval) {
      this.iLineOffsetInterval = iLineOffsetInterval;
   }
}
