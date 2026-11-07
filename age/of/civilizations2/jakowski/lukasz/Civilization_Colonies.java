package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civilization_Colonies implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iProvinceID;
   protected int iTurnID;

   protected Civilization_Colonies(int nProvinceID) {
      this.iProvinceID = nProvinceID;
   }
}
