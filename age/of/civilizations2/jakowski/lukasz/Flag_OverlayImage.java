package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.io.Serializable;

public class Flag_OverlayImage implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iOverlayID = 0;
   protected Image imageOverlay;

   protected Flag_OverlayImage(int iOverlayID) {
      this.iOverlayID = iOverlayID;
      this.imageOverlay = new Image(
         new Texture(Gdx.files.internal("game/flags_editor/overlays/" + CFG.flagManager.lOverlays.get(iOverlayID).sName + ".png")),
         Texture.TextureFilter.Linear
      );
   }
}
