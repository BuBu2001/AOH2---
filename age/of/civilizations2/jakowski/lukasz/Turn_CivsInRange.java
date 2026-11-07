package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

public class Turn_CivsInRange extends Thread {
   protected static int DONE_CIVS = 1;

   @Override
   public void run() {
      Gdx.app.log("Turn_CivsInRange", "Turn_CivsInRange...");

      try {
         DONE_CIVS = 1;

         for(int i = 1; i < CFG.game.getCivsSize(); DONE_CIVS = i++) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
               CFG.oAI.getAI_Style(CFG.game.getCiv(i).getAI_Style()).diplomacyActions_BuildCivsInRange(i);
            }
         }
      } finally {
         DONE_CIVS = CFG.game.getCivsSize();
      }

      CFG.setRender_3(true);
   }
}
