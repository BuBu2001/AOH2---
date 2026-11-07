package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flag_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iDivisionID;
   protected List<Color_GameData> lDivisionColors = new ArrayList<>();
   protected List<Flag_Overlay_GameData> lOverlays = new ArrayList<>();
}
