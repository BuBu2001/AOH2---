package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_5 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Save_Player_GameData> lPlayers = new ArrayList<>();

   protected final void buildData() {
      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         this.lPlayers.add(CFG.game.getPlayer(i).savePlayer);
      }
   }
}
