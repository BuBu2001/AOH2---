package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

class Editor_Wasteland extends Editor {
   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21) || Gdx.input.isKeyPressed(22)) {
         CFG.bSetWasteland_AvailableProvinces = !CFG.bSetWasteland_AvailableProvinces;
      }

      if (Gdx.input.isKeyPressed(62) && CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
         CFG.game.setWasteland(CFG.game.getActiveProvinceID(), CFG.bSetWasteland_AvailableProvinces);
      }

      if (Gdx.input.isKeyPressed(67)) {
         for(int i = 1; i < CFG.game.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(i).getSeaProvince()) {
               CFG.game.getProvince(i).setWasteland(0);
            }
         }
      }

      if (Gdx.input.isKeyPressed(66)) {
         actionSave();
      }
   }

   protected static final void actionSave() {
      FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "wasteland_maps/" + "temp");
      fileSave.writeString("" + (CFG.game.getProvince(0).getWasteland() >= 0 ? '1' : '0') + ";", false);

      for(int i = 1; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            fileSave.writeString("" + (CFG.game.getProvince(i).getWasteland() >= 0 ? '1' : '0') + ";", true);
         }
      }
   }

   @Override
   public String toString() {
      return "WASTELAND: " + CFG.bSetWasteland_AvailableProvinces;
   }
}
