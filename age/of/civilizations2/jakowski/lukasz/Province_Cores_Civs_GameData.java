package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Province_Cores_Civs_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID;
   protected float fPercPop;

   protected Province_Cores_Civs_GameData(int nCivID, int nPerc) {
      this.iCivID = nCivID;
      this.fPercPop = (float)nPerc / 100.0F;
   }

   protected final void setPerc(float nPerc) {
      this.fPercPop = nPerc;
      if (this.fPercPop < 0.01F) {
         this.fPercPop = 0.01F;
      } else if (this.fPercPop > 1.0F) {
         this.fPercPop = 1.0F;
      }
   }
}
