package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class Editor_TerrainType extends Editor {
   protected static int currentTerrainTypeID = 1;
   protected static List<UndoTerrain> lUndo;

   private static final void addUndo(int nProvinceID) {
      if (nProvinceID >= 0) {
         if (lUndo.size() > 0) {
            if (lUndo.get(lUndo.size() - 1).iProvinceID != nProvinceID && currentTerrainTypeID != CFG.game.getProvince(nProvinceID).getTerrainTypeID()) {
               if (lUndo.size() > 50) {
                  lUndo.remove(0);
                  lUndo.add(new UndoTerrain(nProvinceID, CFG.game.getProvince(nProvinceID).getTerrainTypeID()));
               } else {
                  lUndo.add(new UndoTerrain(nProvinceID, CFG.game.getProvince(nProvinceID).getTerrainTypeID()));
               }
            }
         } else if (currentTerrainTypeID != CFG.game.getProvince(nProvinceID).getTerrainTypeID()) {
            lUndo.add(new UndoTerrain(nProvinceID, CFG.game.getProvince(nProvinceID).getTerrainTypeID()));
         }
      }
   }

   protected static void popUndo() {
      if (lUndo.size() > 0) {
         CFG.game.setActiveProvinceID(lUndo.get(lUndo.size() - 1).iProvinceID);
         currentTerrainTypeID = lUndo.get(lUndo.size() - 1).iTerrainID;
         actionSave(false);
         if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         }

         lUndo.remove(lUndo.size() - 1);
      }
   }

   public Editor_TerrainType() {
      lUndo = new ArrayList<>();
   }

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21)) {
         --currentTerrainTypeID;
         if (currentTerrainTypeID < 1) {
            currentTerrainTypeID = CFG.terrainTypesManager.getTerrainsSize() - 1;
         }
      }

      if (Gdx.input.isKeyPressed(22)) {
         ++currentTerrainTypeID;
         if (currentTerrainTypeID > CFG.terrainTypesManager.getTerrainsSize() - 1) {
            currentTerrainTypeID = 1;
         }
      }

      if (Gdx.input.isKeyPressed(62)) {
         actionSave(true);
      }
   }

   protected static final void actionSave(boolean addUndo) {
      if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
         if (addUndo) {
            addUndo(CFG.game.getActiveProvinceID());
         }

         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setTerrainTypeID(currentTerrainTypeID);
         CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
      }
   }

   @Override
   public String toString() {
      return "TERRAIN: " + CFG.terrainTypesManager.getName(currentTerrainTypeID);
   }
}
