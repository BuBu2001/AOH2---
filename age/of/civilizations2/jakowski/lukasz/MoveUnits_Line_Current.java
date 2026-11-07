package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MoveUnits_Line_Current extends MoveUnits_Line {
   protected MoveUnits_Line_Current(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
      MOVE_WIDTH = this.getImageID().getWidth();
   }

   @Override
   protected void updateColor(SpriteBatch oSB) {
      try {
         oSB.setColor(
            (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID)).getR() / 255.0F,
            (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID)).getG() / 255.0F,
            (float)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID)).getB() / 255.0F,
            1.0F
         );
      } catch (IndexOutOfBoundsException var3) {
         oSB.setColor(Color.WHITE);
      }
   }
}
