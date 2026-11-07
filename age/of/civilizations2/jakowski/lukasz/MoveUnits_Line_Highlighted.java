package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MoveUnits_Line_Highlighted extends MoveUnits_Line {
   protected static int MOVE_SRC_X2 = 0;
   protected static int MOVE_WIDTH2 = 0;

   protected MoveUnits_Line_Highlighted(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
      MOVE_WIDTH2 = this.getImageID().getWidth();
   }

   @Override
   protected void updateColor(SpriteBatch oSB) {
      oSB.setColor(
         new Color(
            1.0F,
            1.0F,
            1.0F,
            (float)(
                  (CFG.game.getProvince(this.getFromProvinceID()).getSeaProvince() ? 45 : 75)
                     + (
                        CFG.game.getProvinceAnimation_Active_Data().getBackAnimation()
                           ? 30 - CFG.game.getProvinceAnimation_Active_Data().getStepID()
                           : CFG.game.getProvinceAnimation_Active_Data().getStepID()
                     )
               )
               / 255.0F
         )
      );
   }

   @Override
   protected boolean getFlipX() {
      return CFG.linesManager.highlightFlipX;
   }

   @Override
   protected int getMoveSrcX() {
      return MOVE_SRC_X2;
   }

   @Override
   protected Image getImageID() {
      return CFG.linesManager.highlightImage;
   }

   @Override
   protected void updateMovingLine() {
      this.fMovingPercentage += (float)(System.currentTimeMillis() - this.lMovingTime) / 350.0F * 0.9F;
   }
}
