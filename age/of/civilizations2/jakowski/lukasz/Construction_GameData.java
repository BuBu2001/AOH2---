package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Construction_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected ConstructionType constructionType = ConstructionType.PORT;
   protected int iProvinceID;
   protected int iNumOfTurnsLeft;

   protected Construction_GameData(int iProvinceID, int iNumOfTurnsLeft) {
      this.iProvinceID = iProvinceID;
      this.iNumOfTurnsLeft = iNumOfTurnsLeft;
   }

   protected void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildPort(this.iProvinceID, nCivID);
      }
   }
}
