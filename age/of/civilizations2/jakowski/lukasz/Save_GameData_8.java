package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_8 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<PeaceTreaty_GameData_MessageData> lPeaceTreaties = new ArrayList<>();

   protected final void buildData() {
      for(int i = 0; i < CFG.game.lPeaceTreaties.size(); ++i) {
         this.lPeaceTreaties.add(CFG.game.lPeaceTreaties.get(i));
      }
   }
}
