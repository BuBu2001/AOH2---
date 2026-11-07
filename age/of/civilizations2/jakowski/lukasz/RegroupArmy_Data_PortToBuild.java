package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class RegroupArmy_Data_PortToBuild extends RegroupArmy_Data {
   protected RegroupArmy_Data_PortToBuild(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
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

         if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()) {
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

   @Override
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

         if (!CFG.game.getProvince(in.get(i)).getSeaProvince()) {
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
}
