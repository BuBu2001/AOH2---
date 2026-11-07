package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Scenario_GameData_Diplomacy2 implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Scenario_GameData_Diplomacy_AlliancesData> lAlliances;
   private List<Scenario_GameData_Diplomacy_Data> lRelations;
   private List<Scenario_GameData_Diplomacy_Data> lPacts;
   private List<Scenario_GameData_Diplomacy_VassalsData> lVassals;
   private List<Scenario_GameData_Diplomacy_Data> lMilitaryAccess;
   private List<Scenario_GameData_Diplomacy_Data> lDefensivePacts;
   private List<Scenario_GameData_Diplomacy_Data> lGuarantee;
   private List<Scenario_GameData_Diplomacy_Data> lTruces;

   protected final void buildData() {
      this.lAlliances = new ArrayList<>();
      this.lRelations = new ArrayList<>();
      this.lPacts = new ArrayList<>();
      this.lVassals = new ArrayList<>();
      this.lMilitaryAccess = new ArrayList<>();
      this.lDefensivePacts = new ArrayList<>();
      this.lGuarantee = new ArrayList<>();
      this.lTruces = new ArrayList<>();

      for(int i = 1; i < CFG.game.getAlliancesSize(); ++i) {
         this.lAlliances
            .add(
               new Scenario_GameData_Diplomacy_AlliancesData(
                  CFG.game.getAlliance(i).getAllianceName(),
                  new Color_GameData(
                     CFG.game.getAlliance(i).getColorOfAlliance().getR(),
                     CFG.game.getAlliance(i).getColorOfAlliance().getG(),
                     CFG.game.getAlliance(i).getColorOfAlliance().getB()
                  )
               )
            );

         for(int j = 0; j < CFG.game.getAlliance(i).getCivilizationsSize(); ++j) {
            this.lAlliances.get(i - 1).addCiv(CFG.game.getAlliance(i).getCivilization(j));
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         for(int j = 1; j < CFG.game.getCivsSize(); ++j) {
            if (i != j) {
               if (CFG.game.getCivRelation_OfCivB(i, j) != 0.0F) {
                  this.lRelations.add(new Scenario_GameData_Diplomacy_Data(i, j, (int)CFG.game.getCivRelation_OfCivB(i, j)));
               }

               if (CFG.game.getGuarantee(i, j) > 0) {
                  this.lGuarantee.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getGuarantee(i, j)));
               }

               if (CFG.game.getMilitaryAccess(i, j) > 0) {
                  this.lMilitaryAccess.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getMilitaryAccess(i, j)));
               }
            }
         }

         if (CFG.game.getCiv(i).getCivID() != CFG.game.getCiv(i).getPuppetOfCivID()) {
            this.lVassals.add(new Scenario_GameData_Diplomacy_VassalsData(CFG.game.getCiv(i).getCivID(), CFG.game.getCiv(i).getPuppetOfCivID()));
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
         for(int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
            if (CFG.game.getCivNonAggressionPact(i, j) > 0) {
               this.lPacts.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getCivNonAggressionPact(i, j)));
            }

            if (CFG.game.getDefensivePact(i, j) > 0) {
               this.lDefensivePacts.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getDefensivePact(i, j)));
            }

            if (CFG.game.getCivTruce(i, j) > 0) {
               this.lTruces.add(new Scenario_GameData_Diplomacy_Data(i, j, CFG.game.getCivTruce(i, j)));
            }
         }
      }
   }

   protected final List<Scenario_GameData_Diplomacy_AlliancesData> getAlliances() {
      return this.lAlliances;
   }

   protected final List<Scenario_GameData_Diplomacy_Data> getRelations() {
      return this.lRelations;
   }

   protected final List<Scenario_GameData_Diplomacy_Data> getPacts() {
      return this.lPacts;
   }

   protected final List<Scenario_GameData_Diplomacy_Data> getTruces() {
      return this.lTruces;
   }

   protected final List<Scenario_GameData_Diplomacy_VassalsData> getVassals() {
      return this.lVassals;
   }

   protected final List<Scenario_GameData_Diplomacy_Data> getGuarantee() {
      return this.lGuarantee;
   }

   protected final List<Scenario_GameData_Diplomacy_Data> getMilitaryAccess() {
      return this.lMilitaryAccess;
   }

   protected final List<Scenario_GameData_Diplomacy_Data> getDefensivePacts() {
      return this.lDefensivePacts;
   }
}
