package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

class TradeZone {
   private String sName;
   private List<Integer> lProvinces;
   private int iCenterOfTradeProvinceID;
   private int iProvincesSize;
   private int iAgeID;
   private Color cColor;

   protected TradeZone(int nCenterOfTrade) {
      this.lProvinces = new ArrayList<>();
      this.lProvinces.add(nCenterOfTrade);
      this.iCenterOfTradeProvinceID = 0;
      this.iProvincesSize = this.lProvinces.size();
      Color tempColor = CFG.getRandomColor();
      this.cColor = new Color(tempColor.r, tempColor.g, tempColor.b, 0.65F);
   }

   protected TradeZone(String sName, List<Integer> lProvinces, int iCenterOfTradeProvinceID, int iAgeID, Color cColor) {
      this.lProvinces = lProvinces;
      this.iCenterOfTradeProvinceID = iCenterOfTradeProvinceID;
      this.iAgeID = iAgeID;
      this.loadName(sName);
      this.cColor = cColor;
      this.iProvincesSize = lProvinces.size();
   }

   private final void loadName(String sName) {
      if (sName != null && sName.length() != 0) {
         this.sName = sName;
      } else {
         sName = "???";
      }
   }

   protected final void addProvinceID(int nProvinceID) {
   }

   private final boolean isConnected(int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
         for(int i = 0; i < this.iProvincesSize; ++i) {
            if (CFG.game.getProvince(this.getProvince(i)).getNeighboringSeaProvincesSize() > 0) {
               return true;
            }
         }
      }

      for(int i = 0; i < this.iProvincesSize; ++i) {
         for(int j = 0; j < CFG.game.getProvince(this.getProvince(i)).getNeighboringProvincesSize(); ++j) {
            if (CFG.game.getProvince(this.getProvince(i)).getNeighboringProvinces(j) == nProvinceID) {
               return true;
            }
         }
      }

      return false;
   }

   protected final void removeProvinceID(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            if (i != this.iCenterOfTradeProvinceID) {
               this.lProvinces.remove(i);
               if (this.iCenterOfTradeProvinceID > i) {
                  --this.iCenterOfTradeProvinceID;
               }
            }
            break;
         }
      }

      this.iProvincesSize = this.lProvinces.size();
   }

   protected final List<Integer> getProvinces() {
      return this.lProvinces;
   }

   protected final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   protected final int getCenterOfTradeProvinceID_Real() {
      return this.iCenterOfTradeProvinceID;
   }

   protected final int getCenterOfTradeProvinceID() {
      return this.lProvinces.get(this.iCenterOfTradeProvinceID);
   }

   protected final void setCenterOfTrade(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (nProvinceID == this.lProvinces.get(i)) {
            this.iCenterOfTradeProvinceID = i;
            return;
         }
      }
   }

   protected final int getProvincesSize() {
      return this.iProvincesSize;
   }

   protected final int getAge() {
      return this.iAgeID;
   }

   protected final void setAgeID(int iAgeID) {
      this.iAgeID = iAgeID;
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final Color getColor() {
      return this.cColor;
   }

   protected final void setColor(Color cColor) {
      this.cColor = cColor;
   }
}
