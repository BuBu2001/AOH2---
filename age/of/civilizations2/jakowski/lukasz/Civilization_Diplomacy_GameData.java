package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Civilization_Diplomacy_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Civilization_Diplomacy_ImproveRelations_GameData> lImproveRelations = new ArrayList<>();
   private List<Civilization_ClosedEmbassy> lEmabassyClosed = new ArrayList<>();
   private int iEmbassyClosedSize = 0;
   protected MessageBox_GameData messageBox = new MessageBox_GameData();

   protected final void addImproveRelations(int iCivID, int iWithCivID, int iNumOfTurns) {
      if (!CFG.game.getCiv(iWithCivID).getCivilization_Diplomacy_GameData().isEmassyClosed(iCivID)) {
         for(int i = 0; i < this.lImproveRelations.size(); ++i) {
            if (this.lImproveRelations.get(i).iWithCivID == iWithCivID) {
               this.lImproveRelations.get(i).iNumOfTurns = iNumOfTurns;
               return;
            }
         }

         if (CFG.game.getCiv(iCivID).getDiplomacyPoints() >= 5) {
            CFG.game.getCiv(iCivID).setDiplomacyPoints(CFG.game.getCiv(iCivID).getDiplomacyPoints() - 5);
            this.lImproveRelations.add(new Civilization_Diplomacy_ImproveRelations_GameData(iWithCivID, iNumOfTurns, iCivID));
         }
      }
   }

   protected final void runImproveRelations(int iCivID) {
      for(int i = 0; i < this.lImproveRelations.size(); ++i) {
         if (this.lImproveRelations.get(i).action(iCivID)) {
            CFG.game
               .getCiv(iCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_Relations_Increase_Ended(this.lImproveRelations.get(i).iWithCivID));
            this.lImproveRelations.remove(i--);
         }
      }
   }

   protected final int getImproveRelationsSize() {
      return this.lImproveRelations.size();
   }

   protected final Civilization_Diplomacy_ImproveRelations_GameData getImproveRelation(int i) {
      return this.lImproveRelations.get(i);
   }

   protected final void removeImproveRelations(int iCivID, int i) {
      try {
         CFG.game.getCiv(iCivID).setDiplomacyPoints(CFG.game.getCiv(iCivID).getDiplomacyPoints() + 5);
         this.lImproveRelations.remove(i);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final void removeImproveRelations_WithCivID(int iCivID, int withCivID) {
      try {
         for(int i = 0; i < this.lImproveRelations.size(); ++i) {
            if (this.lImproveRelations.get(i).iWithCivID == withCivID) {
               CFG.game.getCiv(iCivID).setDiplomacyPoints(CFG.game.getCiv(iCivID).getDiplomacyPoints() + 5);
               this.lImproveRelations.remove(i);
               return;
            }
         }
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final void addEmbeassyClosed(Civilization_ClosedEmbassy nData) {
      for(int i = 0; i < this.iEmbassyClosedSize; ++i) {
         if (this.lEmabassyClosed.get(i).iCivID == nData.iCivID) {
            this.lEmabassyClosed.get(i).iNumOfTurns = Math.max(this.lEmabassyClosed.get(i).iNumOfTurns, nData.iNumOfTurns);
            return;
         }
      }

      this.lEmabassyClosed.add(nData);
      this.iEmbassyClosedSize = this.lEmabassyClosed.size();
   }

   protected final void clearEmbassyClosed() {
      this.lEmabassyClosed.clear();
      this.iEmbassyClosedSize = 0;
   }

   protected final void updateEmbassyClosed() {
      for(int i = this.iEmbassyClosedSize - 1; i >= 0; --i) {
         if (--this.lEmabassyClosed.get(i).iNumOfTurns <= 0) {
            this.lEmabassyClosed.remove(i);
            this.iEmbassyClosedSize = this.lEmabassyClosed.size();
         }
      }
   }

   protected final void removeEmbassyClosedWithCivID(int nCivID) {
      for(int i = 0; i < this.iEmbassyClosedSize; ++i) {
         if (this.lEmabassyClosed.get(i).iCivID == nCivID) {
            this.lEmabassyClosed.remove(i);
            this.iEmbassyClosedSize = this.lEmabassyClosed.size();
            return;
         }
      }
   }

   protected final boolean isEmassyClosed(int nCivID) {
      for(int i = 0; i < this.iEmbassyClosedSize; ++i) {
         if (this.lEmabassyClosed.get(i).iCivID == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final int isEmbassyClosed_Turns(int nCivID) {
      for(int i = 0; i < this.iEmbassyClosedSize; ++i) {
         if (this.lEmabassyClosed.get(i).iCivID == nCivID) {
            return this.lEmabassyClosed.get(i).iNumOfTurns;
         }
      }

      return 0;
   }

   protected int getEmbassyClosedSize() {
      return this.iEmbassyClosedSize;
   }

   protected final Civilization_ClosedEmbassy getEmbassyClosed(int i) {
      return this.lEmabassyClosed.get(i);
   }
}
