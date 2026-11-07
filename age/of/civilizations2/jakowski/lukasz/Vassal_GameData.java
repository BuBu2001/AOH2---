package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Vassal_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected int iTribute;

   protected Vassal_GameData(int iCivID) {
      this.iCivID = iCivID;
      this.setTribute(9);
   }

   protected final void setTribute(int iTribute) {
      if (iTribute > 20) {
         iTribute = 20;
      } else if (iTribute < 0) {
         iTribute = 0;
      }

      this.iTribute = iTribute;
   }
}
