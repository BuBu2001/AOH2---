package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Save_GameData_11 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected Events_GameData eventsGameData;

   protected final void buildData() {
      this.eventsGameData = CFG.eventsManager.eventsGD;
   }
}
