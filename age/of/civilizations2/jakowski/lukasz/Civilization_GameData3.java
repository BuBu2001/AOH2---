package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civilization_GameData3 implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sCivTag;
   private int iR;
   private int iG;
   private int iB;
   protected Civilization_ServiceRibbon_GameData sr_GameData = new Civilization_ServiceRibbon_GameData();

   protected Civilization_GameData3() {
      this.sCivTag = "";
   }

   protected Civilization_GameData3(String sCivTag, int iR, int iG, int iB) {
      this.sCivTag = sCivTag;
      this.iR = iR;
      this.iG = iG;
      this.iB = iB;
   }

   protected final void setCivTag(String nCivTag) {
      this.sCivTag = nCivTag;
   }

   protected final String getCivTag() {
      return this.sCivTag;
   }

   protected final void setR(int nR) {
      this.iR = nR;
   }

   protected final int getR() {
      return this.iR;
   }

   protected final void setG(int nG) {
      this.iG = nG;
   }

   protected final int getG() {
      return this.iG;
   }

   protected final void setB(int nB) {
      this.iB = nB;
   }

   protected final int getB() {
      return this.iB;
   }
}
