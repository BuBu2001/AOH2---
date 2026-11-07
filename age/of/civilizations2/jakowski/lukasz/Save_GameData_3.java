package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_3 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Save_CivDiplo_GameData> lCivsDiploData = new ArrayList<>();

   protected final void buildData() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.lCivsDiploData.add(new Save_CivDiplo_GameData(i));
      }
   }
}
