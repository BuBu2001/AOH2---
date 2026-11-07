package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Statistics_Civ_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected String sTag = "";
   private int iGamesWon = 0;
   private int iConqueredProvinces = 0;
   private int iTurns = 0;
   private int iRecruitedArmy = 0;
   private int iLargestArmy = 0;
   private int iLargestPopulation = 0;
   private int iBiggestEconomy = 0;
   private int iBuiltForts = 0;
   private int iBuiltTowers = 0;
   private int iBuiltPorts = 0;
   private int iBuiltLibraries = 0;
   private int iBuiltSupplies = 0;
   private int iBuiltArmories = 0;
   private int iBuiltFarms = 0;
   private int iBuiltWorkshops = 0;

   protected Statistics_Civ_GameData(String nTag) {
      this.sTag = nTag;
   }

   protected final int getConqueredProvinces() {
      return this.iConqueredProvinces;
   }

   protected final void setConqueredProvinces(int iConqueredProvinces) {
      this.iConqueredProvinces = iConqueredProvinces;
   }

   protected final int getTurns() {
      return this.iTurns;
   }

   protected final void setTurns(int iTurns) {
      this.iTurns = iTurns;
   }

   protected final int getRecruitedArmy() {
      return this.iRecruitedArmy;
   }

   protected final void setRecruitedArmy(int iRecruitedArmy) {
      this.iRecruitedArmy = iRecruitedArmy;
   }

   protected final int getGamesWon() {
      return this.iGamesWon;
   }

   protected final void setGamesWon(int iGamesWon) {
      this.iGamesWon = iGamesWon;
   }

   protected final int getBiggestEconomy() {
      return this.iBiggestEconomy;
   }

   protected final void setBiggestEconomy(int iBiggestEconomy) {
      this.iBiggestEconomy = iBiggestEconomy;
   }

   protected final int getLargestPopulation() {
      return this.iLargestPopulation;
   }

   protected final void setLargestPopulation(int iLargestPopulation) {
      this.iLargestPopulation = iLargestPopulation;
   }

   protected final int getLargestArmy() {
      return this.iLargestArmy;
   }

   protected final void setLargestArmy(int iLargestArmy) {
      this.iLargestArmy = iLargestArmy;
   }

   protected final int getiBuiltArmories() {
      return this.iBuiltArmories;
   }

   protected final void setiBuiltArmories(int iBuiltArmories) {
      this.iBuiltArmories = iBuiltArmories;
   }

   protected final int getiBuiltFarms() {
      return this.iBuiltFarms;
   }

   protected final void setiBuiltFarms(int iBuiltFarms) {
      this.iBuiltFarms = iBuiltFarms;
   }

   protected final int getiBuiltWorkshops() {
      return this.iBuiltWorkshops;
   }

   protected final void setiBuiltWorkshops(int iBuiltWorkshops) {
      this.iBuiltWorkshops = iBuiltWorkshops;
   }

   protected final int getiBuiltSupplies() {
      return this.iBuiltSupplies;
   }

   protected final void setiBuiltSupplies(int iBuiltSupplies) {
      this.iBuiltSupplies = iBuiltSupplies;
   }

   protected final int getiBuiltPorts() {
      return this.iBuiltPorts;
   }

   protected final void setiBuiltPorts(int iBuiltPorts) {
      this.iBuiltPorts = iBuiltPorts;
   }

   protected final int getiBuiltTowers() {
      return this.iBuiltTowers;
   }

   protected final void setiBuiltTowers(int iBuiltTowers) {
      this.iBuiltTowers = iBuiltTowers;
   }

   protected final int getiBuiltForts() {
      return this.iBuiltForts;
   }

   protected final void setiBuiltForts(int iBuiltForts) {
      this.iBuiltForts = iBuiltForts;
   }

   protected final int getiBuiltLibraries() {
      return this.iBuiltLibraries;
   }

   protected final void setiBuiltLibraries(int iBuiltLibraries) {
      this.iBuiltLibraries = iBuiltLibraries;
   }
}
