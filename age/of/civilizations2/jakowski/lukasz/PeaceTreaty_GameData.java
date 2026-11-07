package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iWarID;
   protected String WAR_TAG;
   protected int TRUCE_LENGTH = 45;
   protected List<PeaceTreaty_Civs> lCivsData_Defenders = new ArrayList<>();
   protected List<PeaceTreaty_Civs> lCivsData_Aggressors = new ArrayList<>();
   protected List<PeaceTreaty_Demands> lCivsDemands_Defenders = new ArrayList<>();
   protected List<PeaceTreaty_Demands> lCivsDemands_Aggressors = new ArrayList<>();
}
