package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class PlagueProvince_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iPlagueID_InGame = 0;
   protected int iSinceTurnID = 0;
   protected int iDeaths = 0;
   protected float iDurationTurnsLeft = 0.0F;

   protected PlagueProvince_GameData(int iPlagueID_InGame, int iSinceTurnID, float iDurationTurnsLeft, int iDeaths) {
      this.iPlagueID_InGame = iPlagueID_InGame;
      this.iSinceTurnID = iSinceTurnID;
      this.iDurationTurnsLeft = iDurationTurnsLeft;
      this.iDeaths = iDeaths;
   }
}
