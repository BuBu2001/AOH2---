package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Save_GameData2 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected float AI_AGGRESSIVNESS;

   protected final void buildData() {
      this.AI_AGGRESSIVNESS = Game_Calendar.AI_AGGRESSIVNESS;
   }
}
