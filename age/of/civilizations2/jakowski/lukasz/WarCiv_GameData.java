package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class WarCiv_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private int iCivID = 0;
   private int iCasualties = 0;
   private int iCivilianDeaths = 0;
   private int iEconomicLosses = 0;
   private int iConqueredProvinces = 0;

   protected WarCiv_GameData(int nCivID) {
      this.iCivID = nCivID;
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final void setCivID(int iCivID) {
      this.iCivID = iCivID;
   }

   protected final int getCasualties() {
      return this.iCasualties;
   }

   protected final void addCasualties(int nCasualties) {
      this.iCasualties += nCasualties;
   }

   protected final int getCivilianDeaths() {
      return this.iCivilianDeaths;
   }

   protected final void addCivilianDeaths(int nCivilianDeaths) {
      this.iCivilianDeaths += nCivilianDeaths;
   }

   protected final int getEconomicLosses() {
      return this.iEconomicLosses;
   }

   protected final void addEconomicLosses(int nEconomicLosses) {
      this.iEconomicLosses += nEconomicLosses;
   }

   protected final int getConqueredProvinces() {
      return this.iConqueredProvinces;
   }

   protected final void setConqueredProvinces(int iConqueredProvinces) {
      this.iConqueredProvinces = iConqueredProvinces;
   }

   protected final void addConqueredProvinces() {
      ++this.iConqueredProvinces;
   }
}
