package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

class Game_Mountains {
   protected final List<Mountain> loadMountains() {
      List<Mountain> nMountains = new ArrayList<>();
      new Game_Mountains.Config();

      try {
         Game_Mountains.Config mountainsData = this.readMountains("mountains.json");

         for(Object e : mountainsData.mountains) {
            Game_Mountains.GameCity oMountainData = (Game_Mountains.GameCity)e;
            nMountains.add(new Mountain(oMountainData.Name, oMountainData.Elevation, oMountainData.x, oMountainData.y));
         }
      } catch (GdxRuntimeException var6) {
      }

      return nMountains;
   }

   private final Game_Mountains.Config readMountains(String nFileName) {
      FileHandle handle = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "cities/" + nFileName);
      String fileContent = handle.readString();
      Json json = new Json();
      json.setElementType(Game_Mountains.Config.class, "mountains", Game_Mountains.GameCity.class);
      return json.fromJson(Game_Mountains.Config.class, fileContent);
   }

   protected static class Config {
      private ArrayList mountains;
      private String name;
   }

   protected static class GameCity {
      protected String Name;
      protected int Elevation;
      protected int x;
      protected int y;
   }
}
