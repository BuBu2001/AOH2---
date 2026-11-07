package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Map_Regions {
   private List<String> lName;
   private List<Color> lColor;
   private int iRegionsSize;

   protected Map_Regions(String nTag) {
      this.loadRegions(nTag);
   }

   protected final void loadRegions(String nTag) {
      this.lName = new ArrayList<>();
      this.lColor = new ArrayList<>();

      try {
         FileHandle file = Gdx.files.internal("map/data/regions/packges/" + nTag);
         Package_RegionsData tempPackageRegionGameData = (Package_RegionsData)CFG.deserialize(file.readBytes());

         for(int i = 0; i < tempPackageRegionGameData.getRegionsTagsSize(); ++i) {
            try {
               FileHandle fileRegion = Gdx.files.internal("map/data/regions/packges_data/" + tempPackageRegionGameData.getRegionTag(i));
               Region_GameData tempregionGameData = (Region_GameData)CFG.deserialize(fileRegion.readBytes());
               this.lName.add(CFG.langManager.get(tempregionGameData.getName()));
               this.lColor.add(new Color(tempregionGameData.getR(), tempregionGameData.getG(), tempregionGameData.getB(), 0.45F));
            } catch (ClassNotFoundException var7) {
            } catch (IOException var8) {
            }
         }
      } catch (ClassNotFoundException var9) {
      } catch (IOException var10) {
      }

      this.iRegionsSize = this.lName.size();
   }

   protected final String getName(int i) {
      return this.lName.get(i);
   }

   protected final Color getColor(int i) {
      return this.lColor.get(i);
   }

   protected final int getRegionsSize() {
      return this.iRegionsSize;
   }
}
