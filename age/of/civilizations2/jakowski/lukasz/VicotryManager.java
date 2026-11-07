package age.of.civilizations2.jakowski.lukasz;

class VicotryManager {
   protected static int VICTORY_CONTROL_PROVINCES_PERC = 100;
   protected static int VICTORY_LIMIT_OF_TURNS = 0;
   protected static float VICTORY_TECHNOLOGY = 0.0F;
   protected static int domination_NumOfCivsInGame = 1;
   protected static int controlProvinces_NumOfProvinces = 1;

   protected static float getDefault_VcitoryTechnology() {
      return 0.0F;
   }

   protected static final void checkVictoryConditions() {
      updateVictoryConditions();
      if (VICTORY_TECHNOLOGY > 0.0F) {
         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (VICTORY_TECHNOLOGY <= CFG.game.getCiv(i).getTechnologyLevel()) {
               VICTORY_TECHNOLOGY = CFG.game.getCiv(i).getTechnologyLevel() + 0.01F;
               if (CFG.game.getCiv(i).getTechnologyLevel() >= 1.0F) {
                  VICTORY_TECHNOLOGY = 0.0F;
                  break;
               }
            }
         }
      }

      if (VICTORY_CONTROL_PROVINCES_PERC < 100) {
         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (controlProvinces_GetCivScore(i) >= (float)VICTORY_CONTROL_PROVINCES_PERC) {
               VICTORY_CONTROL_PROVINCES_PERC = (int)Math.ceil((double)controlProvinces_GetCivScore(i)) + 1;
            }
         }
      }
   }

   protected static final void updateVictoryConditions() {
      domination_UpdateNumOfCivs();
      controlProvinces_UpdateNumOfProvinces();
   }

   protected static final void domination_UpdateNumOfCivs() {
      domination_NumOfCivsInGame = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            ++domination_NumOfCivsInGame;
         }
      }

      domination_NumOfCivsInGame = Math.max(domination_NumOfCivsInGame, 1);
   }

   protected static final int domination_CivScore(int nCivID) {
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() <= 0) {
         return CFG.game.getCiv(nCivID).getNumOfProvinces();
      } else {
         int out = 1;

         for(int i = CFG.game.getCiv(nCivID).civGameData.lVassals.size() - 1; i >= 0; --i) {
            if (CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID).getNumOfProvinces() > 0) {
               ++out;
            }
         }

         return out;
      }
   }

   protected static final void controlProvinces_UpdateNumOfProvinces() {
      controlProvinces_NumOfProvinces = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         controlProvinces_NumOfProvinces += CFG.game.getCiv(i).getNumOfProvinces();
      }

      controlProvinces_NumOfProvinces = Math.max(controlProvinces_NumOfProvinces, 1);
   }

   protected static final float controlProvinces_GetCivScore(int nCivID) {
      return (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / (float)controlProvinces_NumOfProvinces * 100.0F;
   }

   protected static int turnsLimit_TurnsLeft() {
      return VICTORY_LIMIT_OF_TURNS - Game_Calendar.TURN_ID;
   }

   protected static int technology_BestCiv() {
      int iBest = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0
            && (CFG.game.getCiv(iBest).getTechnologyLevel() < CFG.game.getCiv(i).getTechnologyLevel() || iBest == 0)) {
            iBest = i;
         }
      }

      return iBest;
   }
}
