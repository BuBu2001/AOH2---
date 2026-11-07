package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class TradeRequest_List implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iGold = 0;
   protected List<Integer> lProvinces = new ArrayList<>();
   protected int iDeclarWarOnCivID = -1;
   protected int iFormCoalitionAgainst = -1;
   protected boolean defensivePact = false;
   protected boolean nonAggressionPact = false;
   protected boolean proclaimIndependence = false;
   protected boolean militaryAccess = false;
}
