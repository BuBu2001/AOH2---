package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_2 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Save_Civ_GameData> lCivsData = new ArrayList<>();

   protected final void buildData() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.lCivsData.add(CFG.game.getCiv(i).civGameData);
      }
   }
}
