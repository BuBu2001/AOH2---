package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Save_CivDiplo_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Save_CivDiploInfo_GameData> lNonAggressionPacts = new ArrayList<>();
   protected List<Save_CivDiploInfo_GameData> lTruce = new ArrayList<>();
   protected List<Save_CivDiploInfo_GameData> lDefensivePact = new ArrayList<>();
   protected List<Save_CivDiploInfo_GameData> lGuarantee = new ArrayList<>();
   protected List<Save_CivDiploInfo_GameData> lMilitirayAccess = new ArrayList<>();

   protected Save_CivDiplo_GameData(int nCivID) {
      for(int i = 0; i < CFG.game.getCiv(nCivID).lOpt_NonAggressionPact.size(); ++i) {
         this.lNonAggressionPacts
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_NonAggressionPact.get(i),
                  CFG.game.getCiv(nCivID).getNonAggressionPact(CFG.game.getCiv(nCivID).lOpt_NonAggressionPact.get(i))
               )
            );
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).lOpt_Truce.size(); ++i) {
         this.lTruce
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_Truce.get(i), CFG.game.getCiv(nCivID).getTruce(CFG.game.getCiv(nCivID).lOpt_Truce.get(i))
               )
            );
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).lOpt_DefensivePact.size(); ++i) {
         this.lDefensivePact
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_DefensivePact.get(i),
                  CFG.game.getCiv(nCivID).getDefensivePact(CFG.game.getCiv(nCivID).lOpt_DefensivePact.get(i))
               )
            );
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).lOpt_Guarantee.size(); ++i) {
         this.lGuarantee
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_Guarantee.get(i), CFG.game.getCiv(nCivID).getGuarantee(CFG.game.getCiv(nCivID).lOpt_Guarantee.get(i))
               )
            );
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size(); ++i) {
         this.lMilitirayAccess
            .add(
               new Save_CivDiploInfo_GameData(
                  CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(i),
                  CFG.game.getCiv(nCivID).getMilitaryAccess(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(i))
               )
            );
      }
   }
}
