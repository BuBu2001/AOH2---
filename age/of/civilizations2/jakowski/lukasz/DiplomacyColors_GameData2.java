package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class DiplomacyColors_GameData2 implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName = "";
   protected Color_GameData COLOR_DIPLOMACY_OWN_PROVINCES;
   protected Color_GameData COLOR_DIPLOMACY_AT_WAR;
   protected Color_GameData COLOR_DIPLOMACY_ALLIANCE;
   protected Color_GameData COLOR_DIPLOMACY_PACT;
   protected Color_GameData COLOR_DIPLOMACY_PACT_MAX;
   protected Color_GameData COLOR_DIPLOMACY_VASSAL;
   protected Color_GameData COLOR_DIPLOMACY_INDEPENDENCE;
   protected Color_GameData COLOR_DIPLOMACY_NEUTRAL;
   protected Color_GameData[] COLOR_DIPLOMACY_NEGATIVE;
   protected Color_GameData[] COLOR_DIPLOMACY_POSITIVE;
   protected Color_GameData COLOR_DIPLOMACY_MILITARY_ACCESS;
   protected Color_GameData COLOR_DIPLOMACY_DEFENSIVE_PACT;

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }
}
