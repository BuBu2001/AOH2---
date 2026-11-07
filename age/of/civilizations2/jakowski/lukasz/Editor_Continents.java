package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class Editor_Continents extends Editor {
   protected static int iActiveContinentID = 1;
   protected static List<UndoContinent> lUndo;

   protected Editor_Continents() {
      lUndo = new ArrayList<>();
   }

   private static final void addUndo(int nProvinceID) {
      if (nProvinceID >= 0) {
         if (lUndo.size() > 0) {
            if (lUndo.get(lUndo.size() - 1).iProvinceID != nProvinceID && iActiveContinentID != CFG.game.getProvince(nProvinceID).getContinent()) {
               if (lUndo.size() > 50) {
                  lUndo.remove(0);
                  lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getContinent()));
               } else {
                  lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getContinent()));
               }
            }
         } else if (iActiveContinentID != CFG.game.getProvince(nProvinceID).getContinent()) {
            lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getContinent()));
         }
      }
   }

   protected static void popUndo() {
      if (lUndo.size() > 0) {
         CFG.game.setActiveProvinceID(lUndo.get(lUndo.size() - 1).iProvinceID);
         iActiveContinentID = lUndo.get(lUndo.size() - 1).iContinentID;
         actionSave(false);
         if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         }

         lUndo.remove(lUndo.size() - 1);
      }
   }

   protected static final void actionSave(boolean addUndo) {
      if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= -1) {
         if (addUndo) {
            addUndo(CFG.game.getActiveProvinceID());
         }

         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setContinent(iActiveContinentID);
         CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
      }
   }

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21)) {
         --iActiveContinentID;
         if (iActiveContinentID < 0) {
            iActiveContinentID = CFG.map.getMapContinents().getContinentsSize() - 1;
         }
      } else if (Gdx.input.isKeyPressed(22)) {
         ++iActiveContinentID;
         if (iActiveContinentID > CFG.map.getMapContinents().getContinentsSize() - 1) {
            iActiveContinentID = 0;
         }
      }

      if (Gdx.input.isKeyPressed(62) && CFG.game.getActiveProvinceID() >= 0) {
         actionSave(true);
      }
   }

   @Override
   public String toString() {
      return "CONTINENT: " + CFG.map.getMapContinents().getName(iActiveContinentID);
   }
}
