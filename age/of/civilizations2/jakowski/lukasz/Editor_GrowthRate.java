package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class Editor_GrowthRate extends Editor {
   protected static float currentGrowthRate = 1.0F;
   protected static List<UndoGrowthRate> lUndo;

   private static final void addUndo(int nProvinceID) {
      if (nProvinceID >= 0) {
         if (lUndo.size() > 0) {
            if (lUndo.get(lUndo.size() - 1).iProvinceID != nProvinceID && currentGrowthRate != CFG.game.getProvince(nProvinceID).getGrowthRate_Population()) {
               if (lUndo.size() > 50) {
                  lUndo.remove(0);
                  lUndo.add(new UndoGrowthRate(nProvinceID, CFG.game.getProvince(nProvinceID).getGrowthRate_Population()));
               } else {
                  lUndo.add(new UndoGrowthRate(nProvinceID, CFG.game.getProvince(nProvinceID).getGrowthRate_Population()));
               }
            }
         } else if (currentGrowthRate != CFG.game.getProvince(nProvinceID).getGrowthRate_Population()) {
            lUndo.add(new UndoGrowthRate(nProvinceID, CFG.game.getProvince(nProvinceID).getGrowthRate_Population()));
         }
      }
   }

   protected static void popUndo() {
      if (lUndo.size() > 0) {
         CFG.game.setActiveProvinceID(lUndo.get(lUndo.size() - 1).iProvinceID);
         currentGrowthRate = lUndo.get(lUndo.size() - 1).fGrowthRate;
         actionSave(false);
         if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         }

         lUndo.remove(lUndo.size() - 1);
      }
   }

   public Editor_GrowthRate() {
      lUndo = new ArrayList<>();
   }

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21)) {
         int tempInt = (int)(currentGrowthRate * 100.0F) - 1;
         currentGrowthRate = (float)tempInt / 100.0F;
         if (currentGrowthRate < 0.02F) {
            currentGrowthRate = 0.02F;
         }
      }

      if (Gdx.input.isKeyPressed(22)) {
         int tempInt = (int)(currentGrowthRate * 100.0F) + 1;
         currentGrowthRate = (float)tempInt / 100.0F;
         if (currentGrowthRate > 1.0F) {
            currentGrowthRate = 1.0F;
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

         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setGrowthRate_Population(currentGrowthRate);
         if (CFG.VIEW_SHOW_VALUES) {
            CFG.game
               .getProvince(CFG.game.getActiveProvinceID())
               .getArmy_Obj(0)
               .updateArmyWidth("" + (int)(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getGrowthRate_Population() * 100.0F) + "%");
         }

         CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
      }
   }

   @Override
   public String toString() {
      return "GROWTH RATE: " + (int)(currentGrowthRate * 100.0F) + "%";
   }
}
