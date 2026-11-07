package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Events_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Event_GameData> lEvents = new ArrayList<>();
   protected int iEventsSize = 0;
}
