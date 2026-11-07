package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Save_CivDiploInfo_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int id = 0;
   protected int iValue;

   protected Save_CivDiploInfo_GameData(int id, int iValue) {
      this.id = id;
      this.iValue = iValue;
   }
}
