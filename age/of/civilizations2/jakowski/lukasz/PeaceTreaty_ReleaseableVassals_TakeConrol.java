package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class PeaceTreaty_ReleaseableVassals_TakeConrol implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iFromCivID;
   protected int iVassalCivID;

   protected PeaceTreaty_ReleaseableVassals_TakeConrol(int iFromCivID, int iVassalCivID) {
      this.iFromCivID = iFromCivID;
      this.iVassalCivID = iVassalCivID;
   }
}
