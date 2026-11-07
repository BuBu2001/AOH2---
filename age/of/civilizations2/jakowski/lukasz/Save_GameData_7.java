package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_7 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<War_GameData> lWars = new ArrayList<>();

   protected final void buildData() {
      for(int i = 0; i < CFG.game.getWarsSize(); ++i) {
         this.lWars.add(CFG.game.getWar(i));
      }
   }
}
