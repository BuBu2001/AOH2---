package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class PalletOfCivsColors_Civ_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private Color_GameData oColor;

   protected PalletOfCivsColors_Civ_GameData(Color_GameData oColor) {
      this.setColor(oColor);
   }

   protected final Color_GameData getColor() {
      return this.oColor;
   }

   protected final void setColor(Color_GameData oColor) {
      this.oColor = oColor;
   }
}
