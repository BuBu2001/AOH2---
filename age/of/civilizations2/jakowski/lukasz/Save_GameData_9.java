package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_9 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Plague_GameData> lPlagues_INGAME = new ArrayList<>();

   protected final void buildData() {
      for(int i = 0; i < CFG.plagueManager.lPlagues_INGAME.size(); ++i) {
         this.lPlagues_INGAME.add(CFG.plagueManager.lPlagues_INGAME.get(i));
      }
   }
}
