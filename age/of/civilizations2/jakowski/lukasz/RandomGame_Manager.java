package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class RandomGame_Manager {
   private int iCivilizationsSize = 0;
   private int iNeutralArmy = 0;
   private List<RandomGame_Player> lPlayers = null;

   protected RandomGame_Manager() {
      this.lPlayers = new ArrayList<>();
      this.lPlayers.add(new RandomGame_Player(null, -1));
   }

   protected final void addPlayer() {
      this.lPlayers.add(new RandomGame_Player(null, -1));
   }

   protected final void removePlayer(int i) {
      try {
         this.lPlayers.remove(i);
      } catch (IndexOutOfBoundsException var3) {
      }
   }

   protected final RandomGame_Player getPlayer(int i) {
      return this.lPlayers.get(i);
   }

   protected final int getPlayersSize() {
      return this.lPlayers.size();
   }

   protected final boolean isTagTaken(String nTag) {
      for(int i = 0; i < this.getPlayersSize(); ++i) {
         if (this.lPlayers.get(i).getTag() != null && nTag.equals(this.lPlayers.get(i).getTag())) {
            return true;
         }
      }

      return false;
   }

   protected final void checkCapitals() {
      for(int i = 0; i < this.getPlayersSize(); ++i) {
         if (this.getPlayer(i).getCapitalProvinceID() >= 0) {
            try {
               if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) {
                  this.getPlayer(i).setCapitalProvinceID(-1);
               }
            } catch (IndexOutOfBoundsException var3) {
               this.getPlayer(i).setCapitalProvinceID(-1);
            }
         }
      }
   }

   protected final int getCivilizationsSize() {
      return this.iCivilizationsSize;
   }

   protected final void setCivilizationsSize(int iCivilizationsSize) {
      this.iCivilizationsSize = iCivilizationsSize;
   }

   protected final int getNeutralArmy() {
      return this.iNeutralArmy;
   }

   protected final void setNeutralArmy(int iNeutralArmy) {
      this.iNeutralArmy = iNeutralArmy;
   }
}
