package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Scenario_GameData_Diplomacy_AlliancesData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName;
   private Color_GameData oColor;
   private List<Integer> lCivs;

   protected Scenario_GameData_Diplomacy_AlliancesData(String sName, Color_GameData oColor) {
      this.setName(sName);
      this.setColor(oColor);
      this.lCivs = new ArrayList<>();
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final Color_GameData getColor() {
      return this.oColor;
   }

   protected final void setColor(Color_GameData oColor) {
      this.oColor = oColor;
   }

   protected final List<Integer> getCivs() {
      return this.lCivs;
   }

   protected final void addCiv(int nCivID) {
      this.lCivs.add(nCivID);
   }
}
