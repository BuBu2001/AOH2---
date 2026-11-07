package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class History_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<List<HistoryLog>> lHistory = new ArrayList<>();
}
