package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class PeaceTreaty_GameData_MessageData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected String PEACE_TREATY_TAG;
   protected PeaceTreaty_GameData peaceTreaty_GameData;

   protected PeaceTreaty_GameData_MessageData(PeaceTreaty_GameData peaceTreaty_GameData) {
      this.peaceTreaty_GameData = peaceTreaty_GameData;
      this.PEACE_TREATY_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
   }
}
