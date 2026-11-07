package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Build {
   protected List<List<Integer>> lProvincesToBuild = new ArrayList<>();
   protected int iProvincesToBuild_NumOfElements = 0;
   protected int iMaxDangerLevel = 0;

   protected AI_Build(int nCivID, long nMoney) {
   }

   protected boolean build(int nCivID, int iteration, boolean out) {
      return false;
   }

   protected int getNumOfAlreadyBuilt(int nCivID) {
      return 0;
   }

   protected long getMoney(int nCivID) {
      return CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)
         ? 0L
         : CFG.game.getCiv(nCivID).getMoney() - AI_Style.getMoney_MinReserve(nCivID);
   }
}
