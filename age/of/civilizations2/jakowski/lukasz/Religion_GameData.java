package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Religion_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName;
   private Color_GameData oColor;
   private String sIconName;

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

   protected String getIconName() {
      return this.sIconName;
   }

   protected void setIconName(String sIconName) {
      this.sIconName = sIconName;
   }
}
