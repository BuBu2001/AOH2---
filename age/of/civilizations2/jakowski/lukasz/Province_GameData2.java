package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Province_GameData2 implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Short> lPointsX;
   private List<Short> lPointsY;
   private List<Province_Border_GameData> lProvinceBorder;
   private int iLevelOfPort;
   private List<Short> lNeighboringProvinces;
   private List<Short> lNeighboringSeaProvinces;
   protected Province_Info_GameData3 provinceInfo = new Province_Info_GameData3();
   protected int iPort_ShiftX = 0;
   protected int iPort_ShiftY = 0;

   protected Province_GameData2() {
      this.lPointsX = new ArrayList<>();
      this.lPointsY = new ArrayList<>();
      this.lProvinceBorder = new ArrayList<>();
      this.lNeighboringProvinces = new ArrayList<>();
      this.lNeighboringSeaProvinces = new ArrayList<>();
   }

   protected Province_GameData2(
      int iLevelOfPort,
      List<Short> lPointsX,
      List<Short> lPointsY,
      List<Province_Border_GameData> lProvinceBorder,
      List<Short> lNeighboringProvinces,
      List<Short> lNeighboringSeaProvinces
   ) {
      this.iLevelOfPort = iLevelOfPort;
      this.lPointsX = lPointsX;
      this.lPointsY = lPointsY;
      this.lProvinceBorder = lProvinceBorder;
      this.lNeighboringProvinces = lNeighboringProvinces;
      this.lNeighboringSeaProvinces = lNeighboringSeaProvinces;
   }

   protected final List<Short> getPointsX() {
      return this.lPointsX;
   }

   protected final List<Short> getPointsY() {
      return this.lPointsY;
   }

   protected final int getLevelOfPort() {
      return this.iLevelOfPort;
   }

   protected final List<Province_Border_GameData> getProvinceBorder() {
      return this.lProvinceBorder;
   }

   protected final List<Short> getNeighboringProvinces() {
      return this.lNeighboringProvinces;
   }

   protected final List<Short> getNeighboringSeaProvinces() {
      return this.lNeighboringSeaProvinces;
   }
}
