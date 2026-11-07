package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Civilization_Region_Active {
   private int iCivID;
   private List<Integer> lActiveRegions;
   private List<Integer> lRegionStyle;

   protected Civilization_Region_Active(int iCivID, int iRegionID, int iRegionStyle) {
      this.iCivID = iCivID;
      this.lActiveRegions = new ArrayList<>();
      this.lActiveRegions.add(iRegionID);
      this.lRegionStyle = new ArrayList<>();
      this.lRegionStyle.add(iRegionStyle);
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final boolean isActive_RegionID(int nCivRegionID) {
      for(int i = 0; i < this.lActiveRegions.size(); ++i) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            return true;
         }
      }

      return false;
   }

   protected final void addRegion(int nCivRegionID, int nRegionStyle) {
      for(int i = 0; i < this.lActiveRegions.size(); ++i) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            return;
         }
      }

      this.lActiveRegions.add(nCivRegionID);
      this.lRegionStyle.add(nRegionStyle);
   }

   protected final void removeRegion(int nCivRegionID) {
      for(int i = 0; i < this.lActiveRegions.size(); ++i) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            this.lActiveRegions.remove(i);
            this.lRegionStyle.remove(i);
            return;
         }
      }
   }

   protected final int getActiveRegionsSize() {
      return this.lActiveRegions.size();
   }

   protected final int getRegionStyleID(int nCivRegionID) {
      for(int i = 0; i < this.lActiveRegions.size(); ++i) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            return this.lRegionStyle.get(i);
         }
      }

      return this.lRegionStyle.get(0);
   }
}
