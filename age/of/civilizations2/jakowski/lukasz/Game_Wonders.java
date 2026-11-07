package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

class Game_Wonders {
   protected final List<age.of.civilizations2.jakowski.lukasz.Wonder> loadWonders() {
      List<age.of.civilizations2.jakowski.lukasz.Wonder> nMountains = new ArrayList<>();
      new Game_Wonders.Config();

      try {
         Game_Wonders.Config mountainsData = this.readWonders();

         for(Object e : mountainsData.wonders) {
            Game_Wonders.Wonder oMountainData = (Game_Wonders.Wonder)e;
            nMountains.add(
               new age.of.civilizations2.jakowski.lukasz.Wonder(
                  oMountainData.Name,
                  oMountainData.Image,
                  oMountainData.x,
                  oMountainData.y,
                  oMountainData.SinceYear,
                  oMountainData.UntilYear,
                  oMountainData.Wiki
               )
            );
         }
      } catch (GdxRuntimeException var6) {
      }

      return nMountains;
   }

   private final Game_Wonders.Config readWonders() {
      FileHandle handle = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "wonders/" + "wonders.json");
      String fileContent = handle.readString();
      Json json = new Json();
      json.setElementType(Game_Wonders.Config.class, "wonders", Game_Wonders.Wonder.class);
      return json.fromJson(Game_Wonders.Config.class, fileContent);
   }

   protected static class Config {
      private ArrayList wonders;
      private String name;
   }

   protected static class Wonder {
      protected String Name;
      protected int x;
      protected int y;
      protected int SinceYear;
      protected int UntilYear;
      protected String Image;
      protected String Wiki;
   }
}
