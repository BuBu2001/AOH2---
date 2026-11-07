package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class CivPlans implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<AI_WarPreparations> warPreparations = new ArrayList<>();
   protected int iWarPreparationsSize = 0;
   protected List<CivArmyMission> lArmiesMissions = new ArrayList<>();

   protected final void addNewWarPreparations(int iLeaderCivID, int iCivID, int onCivID, int numOfTurns) {
      if (!CFG.game.getCivsAtWar(iCivID, onCivID)) {
         if (this.isPreparingForTheWar(onCivID)) {
            this.updatePreparationsTime(onCivID, numOfTurns);
            return;
         }

         Gdx.app.log("AoC", "Plans: addNewWarPreparations: " + CFG.game.getCiv(iCivID).getCivName() + " -> " + CFG.game.getCiv(onCivID).getCivName());
         this.warPreparations.add(new AI_WarPreparations(iLeaderCivID, onCivID, true, numOfTurns));
         this.iWarPreparationsSize = this.warPreparations.size();
      }
   }

   protected final boolean isPreparingForTheWar() {
      return this.warPreparations.size() > 0;
   }

   protected final boolean checkWarPreparations(int nCivID) {
      for(int i = 0; i < CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.size(); ++i) {
         if (CFG.game.getCivsAtWar(nCivID, CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(i).onCivID)) {
            CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.remove(i--);
            this.iWarPreparationsSize = this.warPreparations.size();
         }
      }

      return this.warPreparations.size() > 0;
   }

   protected final boolean isPreparingForTheWar(int onCivID) {
      for(int i = 0; i < this.warPreparations.size(); ++i) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean isPreparingForTheWar(int iWarLeaderID, int onCivID) {
      for(int i = 0; i < this.warPreparations.size(); ++i) {
         if (this.warPreparations.get(i).onCivID == onCivID && this.warPreparations.get(i).iLeaderCivID == iWarLeaderID) {
            return true;
         }
      }

      return false;
   }

   protected final void updatePreparationsTime(int onCivID, int numOfTurns) {
      for(int i = 0; i < this.warPreparations.size(); ++i) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            this.warPreparations.get(i).iNumOfTurnsLeft = numOfTurns;
            return;
         }
      }
   }

   protected final int getPreparationsTime(int onCivID) {
      for(int i = 0; i < this.warPreparations.size(); ++i) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            return this.warPreparations.get(i).iNumOfTurnsLeft;
         }
      }

      return 0;
   }

   protected final int getPreparations_LeaderCivID(int onCivID) {
      for(int i = 0; i < this.warPreparations.size(); ++i) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            return this.warPreparations.get(i).iLeaderCivID;
         }
      }

      return 0;
   }

   protected final boolean addNewArmyMission(int nProvinceID, CivArmyMission nMission) {
      for(int i = this.lArmiesMissions.size() - 1; i >= 0; --i) {
         if (this.lArmiesMissions.get(i).iProvinceID == nProvinceID) {
            return false;
         }
      }

      this.lArmiesMissions.add(nMission);
      return true;
   }

   protected final void checkArmyMissions(int nCivID) {
      for(int i = 0; i < this.lArmiesMissions.size(); ++i) {
         if (CFG.game.getProvince(this.lArmiesMissions.get(i).iProvinceID).getArmyCivID(nCivID) <= 0) {
            this.lArmiesMissions.remove(i--);
         }
      }
   }

   protected final void removeMission(int nProvinceID) {
      for(int i = 0; i < this.lArmiesMissions.size(); ++i) {
         if (this.lArmiesMissions.get(nProvinceID).iProvinceID == nProvinceID) {
            this.lArmiesMissions.remove(i--);
            return;
         }
      }
   }

   protected final boolean haveMission(int nProvinceID) {
      for(int i = 0; i < this.lArmiesMissions.size(); ++i) {
         if (this.lArmiesMissions.get(i).iProvinceID == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final int haveMission_Army(int nProvinceID) {
      int out = 0;

      for(int i = 0; i < this.lArmiesMissions.size(); ++i) {
         if (this.lArmiesMissions.get(i).iProvinceID == nProvinceID) {
            out += this.lArmiesMissions.get(i).iArmy;
         }
      }

      return out;
   }

   protected final int haveMission_Army_ToProvinceID(int nProvinceID) {
      for(int i = 0; i < this.lArmiesMissions.size(); ++i) {
         if (this.lArmiesMissions.get(i).toProvinceID == nProvinceID) {
            return this.lArmiesMissions.get(i).iArmy;
         }
      }

      return 0;
   }

   protected final void updateObsolateMissions() {
      for(int i = 0; i < this.lArmiesMissions.size(); ++i) {
         if (this.lArmiesMissions.get(i).iObsolate-- <= 0) {
            this.lArmiesMissions.remove(i--);
         }
      }
   }
}
