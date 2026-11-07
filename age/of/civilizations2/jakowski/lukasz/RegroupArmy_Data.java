package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class RegroupArmy_Data implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iFromProvinceID;
   protected List<Integer> lRoute = new ArrayList<>();
   protected int iRouteSize = 0;
   protected int iNumOfUnits = 0;
   protected int iObsolate = 10;

   protected RegroupArmy_Data(int nCivID, int fromProvinceID, int toProvinceID) {
      this.buildRoute(nCivID, fromProvinceID, toProvinceID);
   }

   protected boolean continueMovingArmy(int nCivID) {
      return true;
   }

   protected boolean buildRoute(int nCivID, int fromProvinceID, int toProvinceID) {
      this.lRoute.clear();
      this.iFromProvinceID = fromProvinceID;
      if (fromProvinceID < 0 || toProvinceID < 0 || CFG.game.getProvince(toProvinceID).getWasteland() >= 0) {
         return false;
      } else if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()
         && CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize() == 0
         && CFG.game.getProvince(fromProvinceID).getLevelOfPort() <= 0) {
         return false;
      } else {
         List<Integer> was = new ArrayList<>();
         was.add(fromProvinceID);
         CFG.game.getProvince(fromProvinceID).was = true;
         List<Integer> in = new ArrayList<>();
         List<List<Integer>> inPath = new ArrayList<>();

         for(int i = 0; i < CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++i) {
            if (canBeUsedInPath(
               nCivID, CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i), isFriendlyProvince(nCivID, toProvinceID), toProvinceID
            )) {
               in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
               List<Integer> tP = new ArrayList<>();
               tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
               inPath.add(tP);
               was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID()).was = true;
            }
         }

         if (!CFG.game.getProvince(fromProvinceID).getSeaProvince() && CFG.game.getProvince(fromProvinceID).getLevelOfPort() > 0) {
            for(int i = 0; i < CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++i) {
               in.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
               List<Integer> tP = new ArrayList<>();
               tP.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
               inPath.add(tP);
               was.add(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID()).was = true;
            }
         }

         this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID);
         return true;
      }
   }

   protected boolean buildPath(int nCivID, List<Integer> was, List<Integer> in, List<List<Integer>> inPath, int from, int lookingFor) {
      List<Integer> nIN = new ArrayList<>();
      List<List<Integer>> nINPath = new ArrayList<>();

      for(int i = 0; i < in.size(); ++i) {
         if (CFG.game.getProvince(in.get(i)).getProvinceID() == lookingFor) {
            this.setPath(from, lookingFor, inPath.get(i), lookingFor);
            this.clearWas(was);
            return true;
         }
      }

      for(int i = 0; i < in.size(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
            if (canBeUsedInPath(
                  nCivID,
                  CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(),
                  isFriendlyProvince(nCivID, lookingFor),
                  lookingFor
               )
               && !CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).was) {
               if (CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                  this.setPath(from, lookingFor, inPath.get(i), lookingFor);
                  this.clearWas(was);
                  return true;
               }

               nIN.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
               List<Integer> tPL = new ArrayList<>();

               for(int u = 0; u < inPath.get(i).size(); ++u) {
                  tPL.add(inPath.get(i).get(u));
               }

               tPL.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
               nINPath.add(tPL);
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).was = true;
               was.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
            }
         }

         if (!CFG.game.getProvince(in.get(i)).getSeaProvince() && CFG.game.getProvince(in.get(i)).getLevelOfPort() > 0) {
            for(int j = 0; j < CFG.game.getProvince(in.get(i)).getNeighboringSeaProvincesSize(); ++j) {
               if (!CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).was) {
                  if (CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                     this.setPath(from, lookingFor, inPath.get(i), lookingFor);
                     this.clearWas(was);
                     return true;
                  }

                  nIN.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                  List<Integer> tPL = new ArrayList<>();

                  for(int u = 0; u < inPath.get(i).size(); ++u) {
                     tPL.add(inPath.get(i).get(u));
                  }

                  tPL.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                  nINPath.add(tPL);
                  CFG.game.getProvince(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).was = true;
                  was.add(CFG.game.getProvince(CFG.game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
               }
            }
         }
      }

      try {
         return this.buildPath(nCivID, was, nIN, nINPath, from, lookingFor);
      } catch (StackOverflowError var13) {
         this.clearWas(was);
         return false;
      }
   }

   protected final void clearWas(List<Integer> was) {
      for(int i = was.size() - 1; i >= 0; --i) {
         CFG.game.getProvince(was.get(i)).was = false;
      }
   }

   protected final void setPath(int p1, int p2, List<Integer> lPath, int toProvinceID) {
      for(int i = 0; i < lPath.size(); ++i) {
         this.lRoute.add(lPath.get(i));
      }

      if (toProvinceID != this.lRoute.get(this.lRoute.size() - 1)) {
         this.lRoute.add(toProvinceID);
      }

      this.iRouteSize = this.lRoute.size();
      this.iObsolate = Math.max(10, (int)((float)this.iRouteSize * 1.5F + 1.0F));
   }

   protected static final boolean isFriendlyProvince(int nCivID, int toProvinceID) {
      return CFG.game.getProvince(toProvinceID).getCivID() == nCivID
         || CFG.game.getProvince(toProvinceID).getSeaProvince()
         || CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID()
         || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(toProvinceID).getCivID()
         || CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() == nCivID
         || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(toProvinceID).getCivID()) > 0;
   }

   protected static boolean canBeUsedInPath(int nCivID, int nProvinceID, boolean moveToFriendlyProvince, int toProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getWasteland() >= 0) {
         return false;
      } else if (nCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
         && CFG.FOG_OF_WAR == 2
         && !CFG.game.getProvince(nProvinceID).getSeaProvince()
         && nProvinceID != toProvinceID
         && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID)) {
         return false;
      } else {
         return CFG.game.getProvince(nProvinceID).getCivID() == nCivID
            || CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID
            || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(nProvinceID).getCivID()
            || !moveToFriendlyProvince
               && CFG.game.getProvince(nProvinceID).getCivID() == 0
               && !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES
               && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nProvinceID))
            || CFG.game.getProvince(nProvinceID).getSeaProvince()
            || CFG.game.getCiv(nCivID).getAllianceID() > 0
               && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID()
            || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) > 0
            || !moveToFriendlyProvince && (int)CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) == -100;
      }
   }

   protected final int getFromProvinceID() {
      return this.iFromProvinceID;
   }

   protected final void setFromProvinceID(int iFromProvinceID) {
      this.iFromProvinceID = iFromProvinceID;
   }

   protected final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   protected final void setNumOfUnits(int iNumOfUnits) {
      this.iNumOfUnits = iNumOfUnits;
   }

   protected final int getRouteSize() {
      return this.iRouteSize;
   }

   protected final int getRoute(int i) {
      return this.lRoute.get(i);
   }

   protected final void removeRoute(int i) {
      this.lRoute.remove(i);
      this.iRouteSize = this.lRoute.size();
   }

   protected final int getToProvinceID() {
      return this.lRoute.get(this.getRouteSize() - 1);
   }

   protected final int getObsolate() {
      return this.iObsolate;
   }

   protected final void updateObsolate() {
      --this.iObsolate;
   }
}
