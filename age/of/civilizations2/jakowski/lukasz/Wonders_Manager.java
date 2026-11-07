package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Wonders_Manager {
   protected List<Wonder> lWonders = null;
   protected List<Integer> wonders_Provinces = new ArrayList<>();

   protected final void buildWondersProvinceID() {
      this.wonders_Provinces.clear();
      int i = 0;

      for(int iSize = this.lWonders.size(); i < iSize; ++i) {
         for(int j = 0; j < CFG.game.getProvincesSize(); ++j) {
            if (CFG.game.getProvince(j).getMinX() <= this.lWonders.get(i).iPosX * CFG.map.getMapBG().getMapScale()
               && CFG.game.getProvince(j).getMaxX() >= this.lWonders.get(i).iPosX * CFG.map.getMapBG().getMapScale()
               && CFG.game.getProvince(j).getMinY() <= this.lWonders.get(i).iPosY * CFG.map.getMapBG().getMapScale()
               && CFG.game.getProvince(j).getMaxY() >= this.lWonders.get(i).iPosY * CFG.map.getMapBG().getMapScale()
               && CFG.game
                  .pathContains(
                     j, this.lWonders.get(i).iPosX * CFG.map.getMapBG().getMapScale(), this.lWonders.get(i).iPosY * CFG.map.getMapBG().getMapScale()
                  )) {
               CFG.game.getProvince(j).addWonder(this.lWonders.get(i));
               this.wonders_Provinces.add(j);
               break;
            }
         }
      }

      this.lWonders.clear();
      this.lWonders = null;
   }
}
