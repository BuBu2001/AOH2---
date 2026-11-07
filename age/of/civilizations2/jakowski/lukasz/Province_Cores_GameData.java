package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Province_Cores_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Province_Cores_Provinces_GameData> lProvinces = new ArrayList<>();

   protected final int getProvincesSize() {
      return this.lProvinces.size();
   }

   protected final void addCore(int nProvinceID, int nCivID) {
      this.addCore(nProvinceID, nCivID, 100);
   }

   protected final void addCore(int nProvinceID, int nCivID, int nPerc) {
      if (nCivID != 0) {
         int i = 0;

         for(int iSize = this.lProvinces.size(); i < iSize; ++i) {
            if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
               this.lProvinces.get(i).addCore(nCivID, nPerc);
               return;
            }
         }

         this.lProvinces.add(new Province_Cores_Provinces_GameData(nProvinceID, nCivID, 100));
      }
   }

   protected final void removeCore(int nProvinceID, int nCivID) {
      int i = 0;

      for(int iSize = this.lProvinces.size(); i < iSize; ++i) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.get(i).removeCore(nCivID);
            return;
         }
      }
   }

   protected final void updatePercOfPopulation(int nProvinceID, int nCivID, int nPerc) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.get(i).updateCorePercOfPopulation(nCivID, nPerc);
            return;
         }
      }

      this.addCore(nProvinceID, nCivID, nPerc);

      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.get(i).updateCorePercOfPopulation(nCivID, nPerc);
            return;
         }
      }
   }

   protected final void updateAfterRemove(int nRemovedCivID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (CFG.game.getProvince(this.lProvinces.get(i).iProvinceID).getCivID() == 0) {
            this.lProvinces.remove(i--);
         } else {
            this.lProvinces.get(i).updateAfterRemove(nRemovedCivID);
            if (this.lProvinces.get(i).lCores.size() < 1) {
               this.lProvinces.remove(i--);
            }
         }
      }
   }

   protected final float getPercOfPop(int nProvinceID, int nCivID) {
      int i = 0;

      for(int iSize = this.lProvinces.size(); i < iSize; ++i) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            return this.lProvinces.get(i).getPercOfPop(nCivID);
         }
      }

      return 1.0F;
   }

   protected final void clearCoresData(int nProvinceID) {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.remove(i);
            return;
         }
      }
   }

   protected final void clearUselessData() {
      for(int i = 0; i < this.lProvinces.size(); ++i) {
         if (this.lProvinces.get(i).lCores.size() < 2) {
            this.lProvinces.remove(i--);
         }
      }
   }
}
