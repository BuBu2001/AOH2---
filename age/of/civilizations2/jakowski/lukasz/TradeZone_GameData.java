package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class TradeZone_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName;
   private List<Integer> lProvinces;
   private int iCenterOfTradeProvinceID;
   private int iAgeID;
   private Color_GameData oColor;

   protected TradeZone_GameData() {
      this.lProvinces = new ArrayList<>();
      this.sName = "";
   }

   protected TradeZone_GameData(String sName, List<Integer> lProvinces, int iCenterOfTradeProvinceID, int iAgeID, Color_GameData oColor) {
      this.lProvinces = lProvinces;
      this.iCenterOfTradeProvinceID = iCenterOfTradeProvinceID;
      this.sName = sName;
      this.iAgeID = iAgeID;
      this.oColor = oColor;
   }

   protected final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   protected final int getCenterOfTradeProvinceID() {
      return this.iCenterOfTradeProvinceID;
   }

   protected final int getProvincesSize() {
      return this.lProvinces.size();
   }

   protected final int getAge() {
      return this.iAgeID;
   }

   protected final void setAge(int iAgeID) {
      this.iAgeID = iAgeID;
   }

   protected final Color_GameData getColor() {
      return this.oColor;
   }

   protected final void setColor(Color_GameData oColor) {
      this.oColor = oColor;
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final List<Integer> getProvinces() {
      return this.lProvinces;
   }
}
