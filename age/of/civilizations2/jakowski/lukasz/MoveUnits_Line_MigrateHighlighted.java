package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MoveUnits_Line_MigrateHighlighted extends MoveUnits_Line_Migrate {
   protected MoveUnits_Line_MigrateHighlighted(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
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
}
