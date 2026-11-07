package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Ultimatum_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected boolean demandAnexation = false;
   protected boolean demandVasalization = false;
   protected boolean demandMilitaryAccess = false;
   protected List<Integer> demandProvinces = new ArrayList<>();
   protected List<Integer> demandLiberation = new ArrayList<>();
   protected int numOfUntis = 0;

   protected boolean isLiberationDemanded(int nCivID) {
      for(int i = 0; i < this.demandLiberation.size(); ++i) {
         if (this.demandLiberation.get(i) == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected void updateLiberationDemand(int nCivID) {
      for(int i = 0; i < this.demandLiberation.size(); ++i) {
         if (this.demandLiberation.get(i) == nCivID) {
            this.demandLiberation.remove(i);
            return;
         }
      }

      this.demandLiberation.add(nCivID);
   }

   protected final boolean canBeSend() {
      return this.demandAnexation
         || this.demandVasalization
         || this.demandMilitaryAccess
         || this.demandProvinces.size() > 0
         || this.demandLiberation.size() > 0;
   }
}
