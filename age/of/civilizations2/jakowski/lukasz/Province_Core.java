package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Province_Core implements Serializable {
   private static final long serialVersionUID = 0L;
   protected static final int NUMBER_OF_TURNS_REQUIRED_TO_GAIN_CORE = 40;
   protected static final int NUMBER_OF_TURNS_REQUIRED_TO_GAIN_CORE_EXTRA_PER_CORE = 10;
   protected static final int NUMBER_OF_TURNS_REQUIRED_TO_GAIN_CORE_WITHOUT_ANY_CORE = 15;
   private List<Integer> lCivs;
   private List<Integer> lSinceTurnID;
   private int iCivsSize = 0;
   private List<Integer> lOwnership_Civs;
   private List<Integer> lOwnership_NumberOfTurns;
   private int iOwnership_CivsSize = 0;

   protected Province_Core() {
      this.lCivs = new ArrayList<>();
      this.lSinceTurnID = new ArrayList<>();
      this.iCivsSize = 0;
      this.lOwnership_Civs = new ArrayList<>();
      this.lOwnership_NumberOfTurns = new ArrayList<>();
      this.iOwnership_CivsSize = 0;
   }

   protected final void addNewCore(int nCivID, int nTurnID) {
      for(int i = 0; i < this.iCivsSize; ++i) {
         if (this.lCivs.get(i) == nCivID) {
            return;
         }
      }

      this.lCivs.add(nCivID);
      this.lSinceTurnID.add(nTurnID);
      this.iCivsSize = this.lCivs.size();
   }

   protected final void removeCore(int nCivID) {
      for(int i = 0; i < this.iCivsSize; ++i) {
         if (this.lCivs.get(i) == nCivID) {
            this.lCivs.remove(i);
            this.lSinceTurnID.remove(i);
            this.iCivsSize = this.lCivs.size();
            break;
         }
      }

      for(int i = 0; i < this.iOwnership_CivsSize; ++i) {
         if (this.lOwnership_Civs.get(i) == nCivID) {
            this.lOwnership_Civs.remove(i);
            this.lOwnership_NumberOfTurns.remove(i);
            this.iOwnership_CivsSize = this.lOwnership_Civs.size();
            break;
         }
      }
   }

   protected final void increaseOwnership(int nCivID, int nProvinceID) {
      for(int i = 0; i < this.iOwnership_CivsSize; ++i) {
         if (this.lOwnership_Civs.get(i) == nCivID) {
            this.lOwnership_NumberOfTurns.set(i, this.lOwnership_NumberOfTurns.get(i) + 1);
            if (this.lOwnership_NumberOfTurns.get(i) == this.getNumOfTurnsOwnershipToGetACore()) {
               this.addNewCore(nCivID, Game_Calendar.TURN_ID);
            }

            return;
         }
      }

      this.lOwnership_Civs.add(nCivID);
      this.lOwnership_NumberOfTurns.add(1);
      this.iOwnership_CivsSize = this.lOwnership_Civs.size();
   }

   protected final void clearData() {
      this.lCivs = new ArrayList<>();
      this.lSinceTurnID = new ArrayList<>();
      this.iCivsSize = 0;
      this.lOwnership_Civs = new ArrayList<>();
      this.lOwnership_NumberOfTurns = new ArrayList<>();
      this.iOwnership_CivsSize = 0;
   }

   protected final boolean getHaveACore(int nCivID) {
      for(int i = 0; i < this.getCivsSize(); ++i) {
         if (nCivID == this.getCivID(i)) {
            return true;
         }
      }

      return false;
   }

   protected final int getNumOfTurnsOwnershipToGetACore() {
      return this.iCivsSize == 0 ? 15 : 40 + 10 * this.getCivsSize();
   }

   protected final boolean getHaveOwnership(int nCivID) {
      for(int i = 0; i < this.getOwnership_CivsSize(); ++i) {
         if (nCivID == this.getOwnership_CivID(i)) {
            return true;
         }
      }

      return false;
   }

   protected final void resetOwnership(int nCivID) {
      for(int i = 0; i < this.getOwnership_CivsSize(); ++i) {
         if (nCivID == this.getOwnership_CivID(i)) {
            this.lOwnership_NumberOfTurns.set(i, 1);
            return;
         }
      }
   }

   protected final int getNumOfOwnership(int nCivID) {
      for(int i = 0; i < this.getOwnership_CivsSize(); ++i) {
         if (nCivID == this.getOwnership_CivID(i)) {
            return this.getOwnership_NumOfTurns(i);
         }
      }

      return 0;
   }

   private final boolean getIsLargestGroup(int nCivID, int nProvinceID) {
      int tempPop = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID);

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) > tempPop) {
            return false;
         }
      }

      return true;
   }

   protected final int getCivID(int i) {
      return this.lCivs.get(i);
   }

   protected final void setCivID_Editor(int i, int nCivID) {
      this.lCivs.set(i, nCivID);
   }

   protected final int getSinceTurnID(int i) {
      return this.lSinceTurnID.get(i);
   }

   protected final int getCivsSize() {
      return this.iCivsSize;
   }

   protected final int getOwnership_CivID(int i) {
      return this.lOwnership_Civs.get(i);
   }

   protected final int getOwnership_NumOfTurns(int i) {
      return this.lOwnership_NumberOfTurns.get(i);
   }

   protected final int getOwnership_CivsSize() {
      return this.iOwnership_CivsSize;
   }
}
