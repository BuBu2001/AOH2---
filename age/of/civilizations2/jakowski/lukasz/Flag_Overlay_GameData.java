package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Flag_Overlay_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iOverlayID = 0;
   protected Color_GameData oColor = new Color_GameData(1.0F, 1.0F, 1.0F);
   protected int iPosX = 0;
   protected int iPosY = 0;
   protected int iWidth = 0;
   protected int iHeight = 0;

   protected Flag_Overlay_GameData(int iOverlayID) {
      this.iOverlayID = iOverlayID;
   }
}
