package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Skills_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int POINTS_POP_GROWTH = 0;
   protected int POINTS_ECONOMY_GROWTH = 0;
   protected int POINTS_INCOME_TAXATION = 0;
   protected int POINTS_INCOME_PRODUCTION = 0;
   protected int POINTS_ADMINISTRATION = 0;
   protected int POINTS_MILITARY_UPKEEP = 0;
   protected int POINTS_RESEARCH = 0;
   protected int POINTS_COLONIZATION = 0;

   protected final int getPointsLeft(int nCivID) {
      return CFG.game.getCiv(nCivID).getTechnologyLevel_INT()
         - this.POINTS_POP_GROWTH
         - this.POINTS_ECONOMY_GROWTH
         - this.POINTS_INCOME_TAXATION
         - this.POINTS_INCOME_PRODUCTION
         - this.POINTS_ADMINISTRATION
         - this.POINTS_MILITARY_UPKEEP
         - this.POINTS_RESEARCH
         - this.POINTS_COLONIZATION;
   }
}
