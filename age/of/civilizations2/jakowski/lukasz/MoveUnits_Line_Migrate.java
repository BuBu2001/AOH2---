package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MoveUnits_Line_Migrate extends MoveUnits_Line {
   protected static int MOVE_SRC_X2 = 0;
   protected static int MOVE_WIDTH2 = 0;

   protected MoveUnits_Line_Migrate(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
      MOVE_WIDTH2 = this.getImageID().getWidth();
   }

   @Override
   protected void updateColor(SpriteBatch oSB) {
      oSB.setColor(
         new Color(
            (float)(CFG.game.getCiv(CFG.game.getProvince(this.getFromProvinceID()).getCivID()).getR() / 255),
            (float)CFG.game.getCiv(CFG.game.getProvince(this.getFromProvinceID()).getCivID()).getG() / 255.0F,
            (float)CFG.game.getCiv(CFG.game.getProvince(this.getFromProvinceID()).getCivID()).getB() / 255.0F,
            1.0F
         )
      );
   }

   @Override
   protected boolean getFlipX() {
      return CFG.linesManager.migrateFlipX;
   }

   @Override
   protected int getMoveSrcX() {
      return MOVE_SRC_X2;
   }

   @Override
   protected Image getImageID() {
      return CFG.linesManager.migrateImage;
   }

   @Override
   protected void updateMovingLine() {
      this.fMovingPercentage += (float)(System.currentTimeMillis() - this.lMovingTime) / 350.0F * 0.9F;
   }
}
