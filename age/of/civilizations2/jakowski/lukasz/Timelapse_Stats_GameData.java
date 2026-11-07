package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Timelapse_Stats_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<List<Integer>> lProvinces = new ArrayList<>();
   protected List<List<Integer>> lPopulation = new ArrayList<>();
   protected List<List<Integer>> lRank = new ArrayList<>();
   protected List<List<Integer>> lTechnologyLevel = new ArrayList<>();
   protected List<List<Integer>> lPlayers_Income = new ArrayList<>();
   protected List<List<Integer>> lPlayers_MilitarySpendings = new ArrayList<>();
   protected List<List<Integer>> lPlayers_Balance = new ArrayList<>();
   protected List<List<HistoryLog>> lHistory = new ArrayList<>();

   protected final void addProvinces(List<Integer> tData) {
      this.lProvinces.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_PROVINCES > 0 && this.lProvinces.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_PROVINCES) {
         this.lProvinces.remove(0);
      }
   }

   protected final void addPopulation(List<Integer> tData) {
      this.lPopulation.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_POPULATION > 0 && this.lPopulation.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_POPULATION) {
         this.lPopulation.remove(0);
      }
   }

   protected final void addRank(List<Integer> tData) {
      this.lRank.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_RANK > 0 && this.lRank.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_RANK) {
         this.lRank.remove(0);
      }
   }

   protected final void addTechLevel(List<Integer> tData) {
      this.lTechnologyLevel.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_TECH_LEVEL > 0 && this.lTechnologyLevel.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_TECH_LEVEL) {
         this.lTechnologyLevel.remove(0);
      }
   }
}
