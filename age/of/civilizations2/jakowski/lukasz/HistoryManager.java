package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class HistoryManager {
   protected static List<String> lHistoryDates = new ArrayList<>();
   protected static List<Integer> lHistoryDatesWidth = new ArrayList<>();
   protected static int HISTORY_LIMIT = 200;

   protected HistoryManager() {
      CFG.timelapseManager.timelapseStatsGD.lHistory = new ArrayList<>();
      this.addNewTurn();
      HistoryLog.ICON_WIDTH = (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_war));
      if (HistoryLog.ICON_WIDTH < (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_truce))) {
         HistoryLog.ICON_WIDTH = (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_truce));
      }

      if (HistoryLog.ICON_WIDTH < (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_alliance))) {
         HistoryLog.ICON_WIDTH = (int)((float)ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_alliance));
      }

      for(int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
         if (HistoryLog.ICON_WIDTH
            < (int)((float)CFG.ideologiesManager.getIdeology(i).getiCrownVassalImage().getWidth() * HistoryLog.getImageScale_CrownVassal(i))) {
            HistoryLog.ICON_WIDTH = (int)(
               (float)CFG.ideologiesManager.getIdeology(i).getiCrownVassalImage().getWidth() * HistoryLog.getImageScale_CrownVassal(i)
            );
         }
      }

      HistoryLog.ICON_WIDTH += CFG.PADDING * 3;
      HISTORY_LIMIT = CFG.isDesktop() ? 200 : 50;
   }

   protected final void updateLanguage() {
      for(int i = 0; i < CFG.timelapseManager.timelapseStatsGD.lHistory.size(); ++i) {
         for(int j = 0; j < CFG.timelapseManager.timelapseStatsGD.lHistory.get(j).size(); ++j) {
            CFG.timelapseManager.timelapseStatsGD.lHistory.get(i).get(j).updateLanguage();
         }
      }
   }

   protected static final void buildHistoryDates() {
      clearHistoryDates();

      for(int i = 1; i < Game_Calendar.TURN_ID; ++i) {
         lHistoryDates.add(Game_Calendar.getDate_ByTurnID(i));
      }

      lHistoryDates.add(Game_Calendar.getCurrentDate());
      int i = 0;

      for(int iSize = lHistoryDates.size(); i < iSize; ++i) {
         CFG.glyphLayout.setText(CFG.fontMain, (String)lHistoryDates.get(i) + ": ");
         lHistoryDatesWidth.add((int)(CFG.glyphLayout.width * 0.7F));
      }
   }

   protected static final void clearHistoryDates() {
      lHistoryDates.clear();
      lHistoryDatesWidth.clear();
   }

   protected final boolean haveHistory() {
      for(int i = 0; i < CFG.timelapseManager.timelapseStatsGD.lHistory.size(); ++i) {
         if (CFG.timelapseManager.timelapseStatsGD.lHistory.get(i).size() > 0) {
            return true;
         }
      }

      return false;
   }

   protected final void addNewTurn() {
      List<HistoryLog> turnHistory = new ArrayList<>();
      CFG.timelapseManager.timelapseStatsGD.lHistory.add(turnHistory);
      if (CFG.timelapseManager.timelapseStatsGD.lHistory.size() > HISTORY_LIMIT) {
         CFG.timelapseManager.timelapseStatsGD.lHistory.remove(0);
      }
   }

   protected final void addHistoryLog(HistoryLog tHL) {
      try {
         CFG.timelapseManager.timelapseStatsGD.lHistory.get(CFG.timelapseManager.timelapseStatsGD.lHistory.size() - 1).add(tHL);
         if (CFG.menuManager.getVisibleInGame_History()) {
            CFG.menuManager.rebuildInGame_History();
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   protected final void addHistory(int iTurnID, HistoryLog nHistory) {
      CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).add(nHistory);
   }

   protected final HistoryLog getHistory(int iTurnID, int i) {
      return CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).get(i);
   }

   protected final void clearHistory() {
      CFG.timelapseManager.timelapseStatsGD.lHistory.clear();
   }

   protected final int getHistorySize() {
      return CFG.timelapseManager.timelapseStatsGD.lHistory.size();
   }

   protected final int getHistoryTurnSize(int iTurnID) {
      return CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).size();
   }
}
