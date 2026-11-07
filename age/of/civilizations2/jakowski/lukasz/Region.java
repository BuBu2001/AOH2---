package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Region {
   private List<Integer> lProvinces;
   private int iProvincesSize = 0;
   private int iMinX;
   private int iMaxX;
   private int iMinY;
   private int iMaxY;
   private boolean belowZero = false;

   protected Region() {
      this.lProvinces = new ArrayList<>();
   }

   protected final void addProvince(int nProvinceID) {
      this.lProvinces.add(nProvinceID);
   }

   protected final void removeProvince(int i) {
      this.lProvinces.remove(i);
      this.iProvincesSize = this.lProvinces.size();
   }

   protected final void buildRegionBounds() {
      if (this.lProvinces.size() > 0) {
         this.iMinX = CFG.game.getProvince(this.lProvinces.get(0)).getMinX();
         this.iMaxX = CFG.game.getProvince(this.lProvinces.get(0)).getMaxX();
         this.iMinY = CFG.game.getProvince(this.lProvinces.get(0)).getMinY();
         this.iMaxY = CFG.game.getProvince(this.lProvinces.get(0)).getMaxY();
         this.iProvincesSize = this.lProvinces.size();

         for(int i = 1; i < this.iProvincesSize; ++i) {
            if (CFG.game.getProvince(this.lProvinces.get(i)).getMinX() < this.iMinX) {
               this.iMinX = CFG.game.getProvince(this.lProvinces.get(i)).getMinX();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxX() > this.iMaxX) {
               this.iMaxX = CFG.game.getProvince(this.lProvinces.get(i)).getMaxX();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMinY() < this.iMinY) {
               this.iMinY = CFG.game.getProvince(this.lProvinces.get(i)).getMinY();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxY() > this.iMaxY) {
               this.iMaxY = CFG.game.getProvince(this.lProvinces.get(i)).getMaxY();
            }
         }

         this.belowZero = this.iMinX < 0;
      }
   }

   protected final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   protected final int getProvincesSize() {
      return this.iProvincesSize;
   }

   protected final int getProvincesSize2() {
      return this.lProvinces.size();
   }

   protected final int getMinX() {
      return this.iMinX;
   }

   protected final int getMaxX() {
      return this.iMaxX;
   }

   protected final int getMinY() {
      return this.iMinY;
   }

   protected final int getMaxY() {
      return this.iMaxY;
   }

   protected final boolean getBelowZero() {
      return this.belowZero;
   }
}
