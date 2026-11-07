package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_Demands implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected int iVictoryPointsLeft;
   protected boolean peaceTreatyAccepted = false;
   protected List<Integer> lDemands = new ArrayList<>();
   protected int iTotalNumOfVicotryPoints = 0;
   protected List<Integer> lWarReparationsFromCivsID = new ArrayList<>();
   protected int iPaysWarReparationsToCivID = -1;
   protected List<Integer> lWillVassalizeCivsID = new ArrayList<>();
   protected int iWillBecomeVassalOfCivID = -1;
   protected List<PeaceTreaty_ReleaseableVassals> lReleasableCivs = new ArrayList<>();
   protected List<PeaceTreaty_ReleaseableVassals_TakeConrol> lReleasableCivs_TakeControl = new ArrayList<>();

   protected PeaceTreaty_Demands(int iCivID, int iVictoryPointsLeft) {
      this.iCivID = iCivID;
      this.iVictoryPointsLeft = iVictoryPointsLeft;
   }

   protected final void addDemandOnProvince(int nProvinceID) {
      for(int i = 0; i < this.lDemands.size(); ++i) {
         if (this.lDemands.get(i) == nProvinceID) {
            return;
         }
      }

      this.lDemands.add(nProvinceID);
   }

   protected final void removeDemandOnProvince(int nProvinceID) {
      for(int i = 0; i < this.lDemands.size(); ++i) {
         if (this.lDemands.get(i) == nProvinceID) {
            this.lDemands.remove(i);
            break;
         }
      }
   }

   protected final void removeWarReparationsFromCivID(int nID) {
      for(int i = 0; i < this.lWarReparationsFromCivsID.size(); ++i) {
         if (this.lWarReparationsFromCivsID.get(i) == nID) {
            this.lWarReparationsFromCivsID.remove(i);
            return;
         }
      }
   }

   protected final void addWarReparationsFromCivID(int nID) {
      for(int i = 0; i < this.lWarReparationsFromCivsID.size(); ++i) {
         if (this.lWarReparationsFromCivsID.get(i) == nID) {
            return;
         }
      }

      this.lWarReparationsFromCivsID.add(nID);
   }

   protected final void removeWillVassalizeCivID(int nID) {
      for(int i = 0; i < this.lWillVassalizeCivsID.size(); ++i) {
         if (this.lWillVassalizeCivsID.get(i) == nID) {
            this.lWillVassalizeCivsID.remove(i);
            return;
         }
      }
   }

   protected final void addWillVassalizeCivID(int nID) {
      for(int i = 0; i < this.lWillVassalizeCivsID.size(); ++i) {
         if (this.lWarReparationsFromCivsID.get(i) == nID) {
            return;
         }
      }

      this.lWillVassalizeCivsID.add(nID);
   }

   protected final void removeReleaseVassal_TakeControl(int nFromCivID, int nVassalID) {
      for(int i = 0; i < this.lReleasableCivs_TakeControl.size(); ++i) {
         if (this.lReleasableCivs_TakeControl.get(i).iFromCivID == nFromCivID && this.lReleasableCivs_TakeControl.get(i).iVassalCivID == nVassalID) {
            this.lReleasableCivs_TakeControl.remove(i);
            return;
         }
      }
   }

   protected final void addReleaseVassal_TakeControl(int nFromCivID, int nVassalID) {
      for(int i = 0; i < this.lReleasableCivs_TakeControl.size(); ++i) {
         if (this.lReleasableCivs_TakeControl.get(i).iFromCivID == nFromCivID && this.lReleasableCivs_TakeControl.get(i).iVassalCivID == nVassalID) {
            return;
         }
      }

      this.lReleasableCivs_TakeControl.add(new PeaceTreaty_ReleaseableVassals_TakeConrol(nFromCivID, nVassalID));
   }
}
