package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Province_Population_Nationalities implements Serializable {
   private static final long serialVersionUID = 0L;
   private int iCivID;
   private int iPopulation;

   public Province_Population_Nationalities(int iCivID, int iPopulation) {
      this.iCivID = iCivID;
      this.iPopulation = iPopulation;
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final int getPopulation() {
      return this.iPopulation;
   }

   protected final void setPopulaton(int iPopulation) {
      this.iPopulation = iPopulation;
   }
}
