package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Leader_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private LeaderOfCiv_GameData leaderOfCiv = new LeaderOfCiv_GameData();
   private List<String> sCivs = new ArrayList<>();

   protected final void addCiv(String nTag) {
      if (this.sCivs == null || !this.sCivs.equals(nTag)) {
         for(int i = 0; i < this.sCivs.size(); ++i) {
            if (this.sCivs.get(i).equals(nTag)) {
               return;
            }
         }

         this.sCivs.add(nTag);
      }
   }

   protected final void removeCiv(int i) {
      this.sCivs.remove(i);
   }

   protected final String getCiv(int i) {
      return this.sCivs.get(i);
   }

   protected final int getCivsSize() {
      return this.sCivs.size();
   }

   protected final LeaderOfCiv_GameData getLeaderOfCiv() {
      return this.leaderOfCiv;
   }

   protected final void setLeaderOfCiv(LeaderOfCiv_GameData leaderOfCiv) {
      this.leaderOfCiv = leaderOfCiv;
   }
}
