package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HolyRomanEmpire_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Integer> lProvinces = new ArrayList<>();
   private int iProvincesSize = 0;
   private int iEmperorID = -1;
   private int iEmperorAuthority = 0;
   private List<Integer> lElectors = new ArrayList<>();
   private int iElectorsSize = 0;
   private List<Integer> lPrinces = new ArrayList<>();
   private int iPrincesSize = 0;
   protected List<Integer> lVotesFor = new ArrayList<>();
   private int iNextElectionsIn = 30;

   protected final void updateHRE_AfterRemoveCivilization(int nCivID) {
      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (this.getPrince(i) == nCivID) {
            this.removePrince(nCivID);
            --i;
         } else if (this.getPrince(i) > nCivID) {
            this.lPrinces.set(i, this.lPrinces.get(i) - 1);
         }
      }
   }

   protected final int getProvinces(int i) {
      return this.lProvinces.get(i);
   }

   protected final boolean addProvince(int nProvinceID) {
      for(int i = 0; i < this.getProvincesSize(); ++i) {
         if (this.getProvinces(i) == nProvinceID) {
            return false;
         }
      }

      this.lProvinces.add(nProvinceID);
      this.iProvincesSize = this.lProvinces.size();
      CFG.game.getProvince(nProvinceID).setIsPartOfHolyRomanEmpire(true);
      return true;
   }

   protected final boolean removeProvince(int nProvinceID) {
      for(int i = 0; i < this.getProvincesSize(); ++i) {
         if (this.getProvinces(i) == nProvinceID) {
            CFG.game.getProvince(nProvinceID).setIsPartOfHolyRomanEmpire(false);
            this.lProvinces.remove(i);
            this.iProvincesSize = this.lProvinces.size();
            return true;
         }
      }

      return false;
   }

   protected final int getProvincesSize() {
      return this.iProvincesSize;
   }

   protected final boolean getIsImperialProvince(int nProvinceID) {
      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (this.getProvinces(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final int getPrince(int i) {
      return this.lPrinces.get(i);
   }

   protected final void addPrince(int nCivID) {
      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (this.getPrince(i) == nCivID) {
            return;
         }
      }

      this.lPrinces.add(nCivID);
      this.iPrincesSize = this.lPrinces.size();
      CFG.game.getCiv(nCivID).setIsPartOfHolyRomanEmpire(true);
   }

   protected final void removePrinceID(int nID) {
      this.removePrince(this.getPrince(nID));
   }

   protected final void removePrince(int nCivID) {
      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (this.getPrince(i) == nCivID) {
            CFG.game.getCiv(nCivID).setIsPartOfHolyRomanEmpire(false);
            this.removeElector(nCivID);
            this.lPrinces.remove(i);
            this.iPrincesSize = this.lPrinces.size();

            for(int j = 0; j < this.getElectorsSize(); ++j) {
               if (this.lElectors.get(j) > i) {
                  this.lElectors.set(j, this.lElectors.get(j) - 1);
               }
            }

            if (this.iEmperorID == i) {
               if (this.getElectorsSize() > 0) {
                  this.iEmperorID = this.getElector(0);
               } else {
                  this.iEmperorID = -1;
               }
            } else if (this.iEmperorID > i) {
               --this.iEmperorID;
            }

            return;
         }
      }
   }

   protected final int getPrincesSize() {
      return this.iPrincesSize;
   }

   protected final int getPrincesSize_True() {
      int out = 0;

      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (CFG.game.getCiv(this.getPrince(i)).getNumOfProvinces() > 0) {
            ++out;
         }
      }

      return out;
   }

   protected final boolean getIsPrince(int nCivID) {
      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (this.getPrince(i) == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final int getEmperor() {
      return this.lPrinces.get(this.iEmperorID);
   }

   protected final void setEmperor(int nCivID) {
      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (this.getPrince(i) == nCivID) {
            this.iEmperorID = i;
            this.removeElector(nCivID);
            return;
         }
      }
   }

   protected final void setEmperorID(int nID) {
      if (this.iEmperorID != nID && nID < this.getPrincesSize()) {
         this.iEmperorID = nID;
      } else {
         this.iEmperorID = -1;
      }
   }

   protected final int getEmperorAuthority() {
      return this.iEmperorAuthority;
   }

   protected final void setEmperorAuthority(int iEmperorAuthority) {
      this.iEmperorAuthority = iEmperorAuthority;
   }

   protected boolean getIsEmperor(int nCivID) {
      if (this.iEmperorID >= 0) {
         return this.getPrince(this.iEmperorID) == nCivID;
      } else {
         return false;
      }
   }

   protected final int getElector(int i) {
      return this.lElectors.get(i);
   }

   protected final void addElector(int nCivID) {
      if (this.getElectorsSize() < 7) {
         for(int i = 0; i < this.getPrincesSize(); ++i) {
            if (this.getPrince(i) == nCivID) {
               this.lElectors.add(i);
               this.iElectorsSize = this.lElectors.size();
               this.buildVotesFor();
               return;
            }
         }
      }
   }

   protected final void removeElector(int nCivID) {
      for(int i = 0; i < this.getElectorsSize(); ++i) {
         if (this.getPrince(this.lElectors.get(i)) == nCivID) {
            this.lElectors.remove(i);
            this.iElectorsSize = this.lElectors.size();
            this.buildVotesFor();
            return;
         }
      }
   }

   protected final int getElectorsSize() {
      return this.iElectorsSize;
   }

   protected final boolean getIsElector(int nCivID) {
      for(int i = 0; i < this.getElectorsSize(); ++i) {
         if (this.getPrince(this.getElector(i)) == nCivID) {
            return true;
         }
      }

      return false;
   }

   protected final void setElectorID(int nID) {
      if (nID < this.getPrincesSize()) {
         if (this.getIsElector(this.getPrince(nID))) {
            this.removeElector(this.getPrince(nID));
         } else {
            this.addElector(this.getPrince(nID));
         }
      }
   }

   protected final void addStrongestPrinceAsElector() {
      List<Integer> tPossibleElectors = new ArrayList<>();

      for(int i = 0; i < this.getPrincesSize(); ++i) {
         if (CFG.game.getCiv(this.getPrince(i)).getNumOfProvinces() > 0 && !this.getIsElector(this.getPrince(i)) && !this.getIsEmperor(this.getPrince(i))) {
            tPossibleElectors.add(this.getPrince(i));
         }
      }

      if (tPossibleElectors.size() > 0) {
         int tBest = 0;

         for(int i = 0; i < tPossibleElectors.size(); ++i) {
            if (CFG.game.getCiv(tPossibleElectors.get(i)).countPopulation() > CFG.game.getCiv(tPossibleElectors.get(tBest)).countPopulation()) {
               tBest = i;
            }
         }

         this.addElector(tPossibleElectors.get(tBest));
      }
   }

   protected final void buildVotesFor() {
      if (this.lVotesFor == null) {
         this.lVotesFor = new ArrayList<>();
      }

      if (this.lVotesFor.size() == 0) {
         for(int i = 0; i < this.getElectorsSize(); ++i) {
            this.lVotesFor.add(this.getPrince(this.getElector(i)));
         }
      } else {
         List<Integer> oldVotes = new ArrayList<>();

         for(int i = 0; i < this.lVotesFor.size(); ++i) {
            oldVotes.add(this.lVotesFor.get(i));
         }

         this.lVotesFor.clear();

         for(int i = 0; i < this.getElectorsSize(); ++i) {
            if (CFG.game.getCiv(this.getPrince(this.getElector(i))).getControlledByPlayer()) {
               try {
                  this.lVotesFor.add(oldVotes.get(i));
               } catch (IndexOutOfBoundsException var4) {
                  this.lVotesFor.add(this.getPrince(this.getElector(i)));
               }
            } else {
               this.lVotesFor.add(this.getPrince(this.getElector(i)));
            }
         }
      }
   }

   protected final int getNextElectionsIn() {
      return this.iNextElectionsIn;
   }

   protected final void setNextElectionsIn(int iNextElectionsIn) {
      this.iNextElectionsIn = iNextElectionsIn;
   }

   protected final void randomNextElections() {
      this.iNextElectionsIn = 32 + CFG.oR.nextInt(60);
   }
}
