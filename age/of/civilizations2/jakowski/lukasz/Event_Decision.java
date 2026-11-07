package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Decision implements Serializable {
   private static final long serialVersionUID = 0L;
   protected String sTitle = "";
   protected List<Event_Outcome> lOutcomes = new ArrayList<>();
   protected int iAIChance = 100;

   protected final void executeDecision() {
      for(int i = 0; i < this.lOutcomes.size(); ++i) {
         this.lOutcomes.get(i).outcomeAction();
      }
   }
}
