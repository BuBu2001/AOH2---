package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class Editor_ShiftArmy extends Editor {
   @Override
   protected void keyDown(int keycode) {
      if (CFG.game.getActiveProvinceID() >= 0) {
         if (Gdx.input.isKeyPressed(21)) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setShiftArmyX(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftX() - 1);
         }

         if (Gdx.input.isKeyPressed(22)) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setShiftArmyX(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftX() + 1);
         }

         if (Gdx.input.isKeyPressed(19)) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setShiftArmyY(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftY() - 1);
         }

         if (Gdx.input.isKeyPressed(20)) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setShiftArmyY(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftY() + 1);
         }

         saveArmyPosition();
      }
   }

   protected static final void saveArmyPosition() {
      CFG.game.saveProvince_Info_GameData_SHIFTXY(CFG.game.getActiveProvinceID());
   }

   @Override
   public String toString() {
      return "SHIFT ARMY: " + CFG.game.getActiveProvinceID();
   }
}
