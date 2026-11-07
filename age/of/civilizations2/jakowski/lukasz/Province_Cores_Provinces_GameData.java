package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Province_Cores_Provinces_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iProvinceID;
   protected List<Province_Cores_Civs_GameData> lCores = new ArrayList<>();

   protected Province_Cores_Provinces_GameData(int nProvinceID, int nCivID, int nPerc) {
      this.iProvinceID = nProvinceID;
      this.addCore(nCivID, nPerc);
   }

   protected final void addCore(int nCivID, int nPerc) {
      for(int i = 0; i < this.lCores.size(); ++i) {
         if (this.lCores.get(i).iCivID == nCivID) {
            return;
         }
      }

      this.lCores.add(new Province_Cores_Civs_GameData(nCivID, Math.min(nPerc, 100 - this.lCores.size())));
      this.updateCorePercOfPopulation(nCivID, nPerc);
   }

   protected final void updateCorePercOfPopulation(int nCivID, int nPerc) {
      if (this.lCores.size() > 1) {
         for(int i = 0; i < this.lCores.size(); ++i) {
            if (this.lCores.get(i).iCivID == nCivID) {
               this.lCores.get(i).setPerc((float)nPerc / 100.0F);
               break;
            }
         }

         float tempPercAll = 0.0F;

         for(int i = 0; i < this.lCores.size(); ++i) {
            tempPercAll += this.lCores.get(i).fPercPop;
         }

         if (tempPercAll > 1.0F) {
            float tempTotal = 0.0F;
            float tempCivTotal = 0.0F;

            for(int i = 0; i < this.lCores.size(); ++i) {
               if (this.lCores.get(i).iCivID != nCivID) {
                  tempTotal += this.lCores.get(i).fPercPop;
               } else {
                  this.lCores.get(i).setPerc((float)Math.min(nPerc, 100 - this.lCores.size()) / 100.0F);
                  tempCivTotal = this.lCores.get(i).fPercPop;
               }
            }

            float tDiff = 1.0F - Math.min(1.0F, tempCivTotal);

            for(int i = 0; i < this.lCores.size(); ++i) {
               if (this.lCores.get(i).iCivID != nCivID) {
                  this.lCores.get(i).setPerc(tDiff * this.lCores.get(i).fPercPop / tempTotal);
               }
            }

            tempTotal = 0.0F;

            for(int i = 0; i < this.lCores.size(); ++i) {
               tempTotal += this.lCores.get(i).fPercPop;
            }

            tempTotal = 1.0F - tempTotal;
            if (tempTotal > 0.0F) {
               this.lCores.get(this.lCores.size() - 1).setPerc(this.lCores.get(this.lCores.size() - 1).fPercPop + tempTotal);
            }
         }
      } else if (this.lCores.size() > 0) {
         if (nPerc > 100) {
            this.lCores.get(0).setPerc(1.0F);
         } else if (nPerc < 1) {
            this.lCores.get(0).setPerc(0.01F);
         } else {
            this.lCores.get(0).setPerc((float)nPerc / 100.0F);
         }
      }
   }

   protected final void removeCore(int nCivID) {
      for(int i = 0; i < this.lCores.size(); ++i) {
         if (this.lCores.get(i).iCivID == nCivID) {
            this.lCores.remove(i);
            return;
         }
      }
   }

   protected final float getPercOfPop(int nCivID) {
      for(int i = 0; i < this.lCores.size(); ++i) {
         if (this.lCores.get(i).iCivID == nCivID) {
            return this.lCores.get(i).fPercPop;
         }
      }

      return 0.0F;
   }

   protected final void updateAfterRemove(int nRemovedCivID) {
      for(int i = 0; i < this.lCores.size(); ++i) {
         if (this.lCores.get(i).iCivID > nRemovedCivID) {
            --this.lCores.get(i).iCivID;
         } else if (this.lCores.get(i).iCivID == nRemovedCivID) {
            this.lCores.remove(i--);
         }
      }
   }
}
