package age.of.civilizations2.jakowski.lukasz;

class AI_FrontLine_War {
   protected int i;
   protected int iFrontArmy = 0;
   protected int iNeighboringProvincesLostScore;

   protected AI_FrontLine_War(int nCivID, int i) {
      this.i = i;
      this.iFrontArmy = this.getFrontLineArmy(nCivID);
      this.iNeighboringProvincesLostScore = this.getNeighboringProvincesLostScore(nCivID);
   }

   protected final int getWithCivID(int nCivID) {
      return CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).iWithCivID;
   }

   protected final int getEnemyRating(int nCivID, int withCivID) {
      return (int)((float)CFG.game.getCiv(withCivID).getNumOfUnits() * 1.125F);
   }

   protected final int getFrontScore(int nCivID) {
      return 0
         + this.iNeighboringProvincesLostScore
         + (CFG.game.getCiv(nCivID).getNumOfUnits() < this.getEnemyRating(nCivID, this.getWithCivID(nCivID)) ? 20 : 0);
   }

   protected final int getFrontArmy(int nCivID) {
      return CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).getFrontLineArmy(nCivID);
   }

   protected boolean canRecruitArmy_FrontLine(int nCivID) {
      for(int j = CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.size() - 1; j >= 0; --j) {
         if (!CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(j)).isOccupied()) {
            return true;
         }
      }

      return false;
   }

   protected final int getImportance_OurRegion(int nCivID) {
      try {
         return CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getPotentialRegion();
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
         return 0;
      }
   }

   protected final int getImportance_Region_NumOfProvinces(int nCivID) {
      try {
         for(int j = CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvincesSize(); j >= 0; --j) {
            if (CFG.game
                  .getProvince(CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvinces(j))
                  .getCivID()
               == this.getWithCivID(nCivID)) {
               return CFG.game
                  .getProvince(CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvinces(j))
                  .getRegion_NumOfProvinces();
            }
         }

         return CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getRegion_NumOfProvinces();
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
         return 0;
      }
   }

   protected final int getEnemyRegion_Potential(int nCivID) {
      try {
         for(int j = CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvincesSize(); j >= 0; --j) {
            if (CFG.game
                  .getProvince(CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvinces(j))
                  .getCivID()
               == this.getWithCivID(nCivID)) {
               return CFG.game
                  .getProvince(CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvinces(j))
                  .getPotentialRegion();
            }
         }

         return 1;
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
         return 1;
      }
   }

   protected final int getEnemyRegion_NumOfProvinces(int nCivID) {
      try {
         for(int j = CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvincesSize(); j >= 0; --j) {
            if (CFG.game
                  .getProvince(CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvinces(j))
                  .getCivID()
               == this.getWithCivID(nCivID)) {
               return CFG.game
                  .getProvince(CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(0)).getNeighboringProvinces(j))
                  .getRegion_NumOfProvinces();
            }
         }

         return 1;
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
         return 1;
      }
   }

   protected final int getEnemyRegion_NumOfRegions(int nCivID) {
      try {
         return CFG.game.getCiv(this.getWithCivID(nCivID)).getCivRegionsSize();
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
         return 1;
      }
   }

   protected final int getNeighboringProvincesLostScore(int nCivID) {
      int out = 0;

      for(int j = CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.size() - 1; j >= 0; --j) {
         if (CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).lProvinces.get(j)).getNeighbooringProvinceOfCivWasLost() > 0) {
            ++out;
         }
      }

      return out;
   }

   protected final int getFrontLineArmy(int nCivID) {
      return CFG.oAI.lFrontLines.get(nCivID - 1).get(this.i).getFrontLineArmy(nCivID);
   }
}
