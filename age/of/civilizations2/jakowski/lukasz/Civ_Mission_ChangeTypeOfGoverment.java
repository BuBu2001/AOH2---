package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civ_Mission_ChangeTypeOfGoverment implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iToIdeologyID;
   protected int iCost;

   protected Civ_Mission_ChangeTypeOfGoverment(int iToIdeologyID, int nCivID) {
      this.iToIdeologyID = iToIdeologyID;
      this.iCost = Ideologies_Manager.getChangeGovernmentCost(nCivID);
      this.action(nCivID);
   }

   protected final boolean action(int nCivID) {
      if (CFG.game.getCiv(nCivID).getMoney() >= (long)this.iCost) {
         if (!CFG.ideologiesManager.canBeAdded(nCivID, this.iToIdeologyID)) {
            return true;
         } else if (DiplomacyManager.changeGovernmentType(nCivID, this.iToIdeologyID)) {
            return true;
         } else {
            this.iCost = Ideologies_Manager.getChangeGovernmentCost(nCivID);
            return false;
         }
      } else {
         if (DiplomacyManager.canTakeMoreLoans(nCivID)
            && CFG.game.getCiv(nCivID).getMoney() + (long)DiplomacyManager.takeLoan_MaxValue(nCivID) >= (long)this.iCost) {
            DiplomacyManager.takeLoan(nCivID, (int)((long)this.iCost - CFG.game.getCiv(nCivID).getMoney()), 5);
            if (!CFG.ideologiesManager.canBeAdded(nCivID, this.iToIdeologyID)) {
               return true;
            }

            if (DiplomacyManager.changeGovernmentType(nCivID, this.iToIdeologyID)) {
               return true;
            }
         }

         return false;
      }
   }
}
