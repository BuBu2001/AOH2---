package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_6 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Alliance> lAlliances = new ArrayList<>();

   protected final void buildData() {
      for(int i = 0; i < CFG.game.getAlliancesSize(); ++i) {
         this.lAlliances.add(CFG.game.getAlliance(i));
      }
   }
}
