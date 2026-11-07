package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class Editor_MapRegions extends Editor {
   protected static int iActiveRegionID = 1;
   protected static List<UndoContinent> lUndo;

   protected Editor_MapRegions() {
      lUndo = new ArrayList<>();
   }

   private static final void addUndo(int nProvinceID) {
      if (nProvinceID >= 0) {
         if (lUndo.size() > 0) {
            if (lUndo.get(lUndo.size() - 1).iProvinceID != nProvinceID && iActiveRegionID != CFG.game.getProvince(nProvinceID).getRegion()) {
               if (lUndo.size() > 50) {
                  lUndo.remove(0);
                  lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getRegion()));
               } else {
                  lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getRegion()));
               }
            }
         } else if (iActiveRegionID != CFG.game.getProvince(nProvinceID).getRegion()) {
            lUndo.add(new UndoContinent(nProvinceID, CFG.game.getProvince(nProvinceID).getRegion()));
         }
      }
   }

   protected static void popUndo() {
      if (lUndo.size() > 0) {
         CFG.game.setActiveProvinceID(lUndo.get(lUndo.size() - 1).iProvinceID);
         iActiveRegionID = lUndo.get(lUndo.size() - 1).iContinentID;
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

         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setRegion(iActiveRegionID);
         CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
      }
   }

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21)) {
         --iActiveRegionID;
         if (iActiveRegionID < 0) {
            iActiveRegionID = CFG.map.getMapRegions().getRegionsSize() - 1;
         }
      } else if (Gdx.input.isKeyPressed(22)) {
         ++iActiveRegionID;
         if (iActiveRegionID > CFG.map.getMapRegions().getRegionsSize() - 1) {
            iActiveRegionID = 0;
         }
      }

      if (Gdx.input.isKeyPressed(62) && CFG.game.getActiveProvinceID() >= 0) {
         actionSave(true);
      }
   }

   @Override
   public String toString() {
      return "REGION: " + CFG.map.getMapRegions().getName(iActiveRegionID);
   }
}
