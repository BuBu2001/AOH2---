package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Report_Data {
   protected List<Integer> lAttackers_IDs = new ArrayList<>();
   protected List<Integer> lAttackers_Armies = new ArrayList<>();
   protected List<Integer> lAttackers_Armies_Lost = new ArrayList<>();
   protected List<Integer> lDefenders_IDs = new ArrayList<>();
   protected List<Integer> lDefenders_Armies = new ArrayList<>();
   protected List<Integer> lDefenders_ArmiesLost = new ArrayList<>();
   protected int iBattleOfProvinceID = 0;
   protected boolean attackersWon = true;
   protected float fWarScore;
   protected int iPopulationLosses = 0;
   protected int iEconomyLosses = 0;

   protected final int getAttackersArmy() {
      int tempOut = 0;

      for(int i = 0; i < this.lAttackers_Armies.size(); ++i) {
         tempOut += this.lAttackers_Armies.get(i);
      }

      return tempOut;
   }

   protected final int getAttackersArmy_Lost() {
      int tempOut = 0;

      for(int i = 0; i < this.lAttackers_Armies_Lost.size(); ++i) {
         tempOut += this.lAttackers_Armies_Lost.get(i);
      }

      return tempOut;
   }

   protected final int getDefendersArmy() {
      int tempOut = 0;

      for(int i = 0; i < this.lDefenders_Armies.size(); ++i) {
         tempOut += this.lDefenders_Armies.get(i);
      }

      return tempOut;
   }

   protected final int getDefendersArmy_Lost() {
      int tempOut = 0;

      for(int i = 0; i < this.lDefenders_ArmiesLost.size(); ++i) {
         tempOut += this.lDefenders_ArmiesLost.get(i);
      }

      return tempOut;
   }

   protected final void checkReport() {
      for(int i = this.lDefenders_IDs.size() - 1; i >= 0; --i) {
         for(int j = this.lAttackers_IDs.size() - 1; j >= 0; --j) {
            if (this.lDefenders_IDs.get(i).equals(this.lAttackers_IDs.get(j))) {
               this.lDefenders_IDs.remove(i);
               this.lDefenders_Armies.remove(i);
               this.lDefenders_ArmiesLost.remove(i);
               break;
            }
         }
      }
   }

   protected final int getTotalArmy() {
      int tempOut = 0;

      for(int i = 0; i < this.lAttackers_Armies.size(); ++i) {
         tempOut += this.lAttackers_Armies.get(i);
      }

      for(int i = 0; i < this.lDefenders_Armies.size(); ++i) {
         tempOut += this.lDefenders_Armies.get(i);
      }

      return tempOut;
   }

   protected final int getTotalArmy_Lost() {
      int tempOut = 0;

      for(int i = 0; i < this.lAttackers_Armies_Lost.size(); ++i) {
         tempOut += this.lAttackers_Armies_Lost.get(i);
      }

      for(int i = 0; i < this.lDefenders_ArmiesLost.size(); ++i) {
         tempOut += this.lDefenders_ArmiesLost.get(i);
      }

      return tempOut;
   }
}
