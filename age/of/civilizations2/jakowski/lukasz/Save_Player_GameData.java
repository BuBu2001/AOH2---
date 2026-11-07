package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.List;

class Save_Player_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivID = -1;
   protected List<Boolean> metProvince;
   protected List<Boolean> metCivilization;
   protected boolean lostNextTurn = false;
}
