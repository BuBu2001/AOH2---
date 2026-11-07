package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_ProvinceInfo_War {
   protected int iProvinceID;
   protected float iValue;
   protected boolean ownFrontProvince = false;
   protected List<Integer> lProvinces_Enemy = new ArrayList<>();

   protected AI_ProvinceInfo_War(int iProvinceID, int iValue, boolean ownFrontProvince) {
      this.iProvinceID = iProvinceID;
      this.iValue = (float)iValue;
      this.ownFrontProvince = ownFrontProvince;
   }

   protected final void buildEnemyProvinces(int nCivID) {
      for(int i = 0; i < CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID())) {
            this.lProvinces_Enemy.add(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i));
         }
      }
   }

   protected final boolean enemyProvinceBordersWithOurTrueProvince(int nCivID) {
      for(int i = 0; i < this.lProvinces_Enemy.size(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(this.lProvinces_Enemy.get(i)).getNeighboringProvincesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Enemy.get(i)).getNeighboringProvinces(j)).getCivID() == nCivID) {
               return true;
            }
         }
      }

      return false;
   }

   protected final boolean isOccupied() {
      return CFG.game.getProvince(this.iProvinceID).isOccupied();
   }

   protected final int getArmy(int nCivID) {
      return CFG.game.getProvince(this.iProvinceID).getArmyCivID(nCivID);
   }

   protected final int getRecruitableArmy(int nCivID) {
      return CFG.gameAction.getRecruitableArmy(this.iProvinceID, nCivID);
   }
}
