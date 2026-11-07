package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

class Editor_LevelOfPort extends Editor {
   private int nLevelOfPort = -1;

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21)) {
         --this.nLevelOfPort;
         if (this.nLevelOfPort < -3) {
            this.nLevelOfPort = -3;
         }
      }

      if (Gdx.input.isKeyPressed(22)) {
         ++this.nLevelOfPort;
         if (this.nLevelOfPort > -1) {
            this.nLevelOfPort = -1;
         }
      }

      if (CFG.game.getActiveProvinceID() >= 0 && (Gdx.input.isKeyPressed(66) || Gdx.input.isKeyPressed(62))) {
         boolean reloadProvinceBG = false;
         if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= -1 && this.nLevelOfPort < -1) {
            reloadProvinceBG = true;
         } else if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() < -1 && this.nLevelOfPort >= -1) {
            reloadProvinceBG = true;
         }

         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(this.nLevelOfPort);
         if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() < -1) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setContinent(0);
         } else if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getContinent() == 0) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setContinent(1);
         }

         CFG.game.buildGameProvinceData(CFG.game.getActiveProvinceID());
         CFG.game.saveProvince_Info_GameData(CFG.game.getActiveProvinceID());
         CFG.game
            .getProvince(CFG.game.getActiveProvinceID())
            .getArmy_Obj(0)
            .updateArmyWidth(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort());
         if (reloadProvinceBG) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).loadProvinceBG();
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = 0;
         }
      }
   }

   @Override
   public String toString() {
      return "SET TO LEVEL: "
         + this.nLevelOfPort
         + " ["
         + (this.nLevelOfPort == -1 ? "LAND" : (this.nLevelOfPort == -2 ? "SEA" : "CLOSED SEA"))
         + "]\n\nENTER/SPACE -> SET LEVEL\nLEFT, RIGHT - > LEVEL\n\n-1 = LAND PROVINCE\n-2 = SEA PROVINCE\n-3 = CLOSED SEA, LAKES";
   }
}
