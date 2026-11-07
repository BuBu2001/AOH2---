package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Map {
   private int iActiveMapID = 0;
   private List<String> sMAP_TAGS;
   private List<String> sMAP_LANG_KEY;
   private List<Integer> iMAP_NUM_OF_PROVINCES;
   private List<Integer> iMAP_SCALE;
   private List<Integer> iMAP_DEFAULT_SCALE;
   private List<String> sMAP_BACKGROUND_NAME;
   private List<String> sMAP_CONTINENTS_PACKAGE_TAG;
   private List<String> sMAP_REGIONS_PACKAGE_TAG;
   private List<String> sMAP_AUTHOR;
   private List<Boolean> MAP_WORLD_MAP;
   private List<String> MAP_SCENARIO;
   private List<String> sMAP_WIKI;
   private List<Image> iMAP_ICON;
   private Map_BG mapBG = null;
   private Map_Coordinates mapCoordinates = null;
   private Map_TouchManager mapTouchManager = null;
   private Map_Scroll mapScroll = null;
   private Map_Scale mapScale = null;
   private Map_Continents mapContinents = null;
   private Map_Regions mapRegions = null;
   protected int iNumOfBasins = 0;
   protected static boolean GAME_CRASHED_LOADED_MIN_SCALE = false;

   protected Map() {
      new Map.Config();
      Json json = new Json();
      json.setElementType(Map.Config.class, "Map", Map.Maps.class);
      Map.Config data = json.fromJson(Map.Config.class, Gdx.files.internal("map/Age_of_Civilizations.json").reader("UTF8"));
      this.sMAP_TAGS = new ArrayList<>();

      for(Object e : data.Map) {
         Map.Maps tempMapFolder = (Map.Maps)e;
         this.sMAP_TAGS.add(tempMapFolder.Folder);
      }

      this.sMAP_LANG_KEY = new ArrayList<>();
      this.iMAP_NUM_OF_PROVINCES = new ArrayList<>();
      this.iMAP_SCALE = new ArrayList<>();
      this.iMAP_DEFAULT_SCALE = new ArrayList<>();
      this.sMAP_BACKGROUND_NAME = new ArrayList<>();
      this.sMAP_AUTHOR = new ArrayList<>();
      this.sMAP_WIKI = new ArrayList<>();
      this.sMAP_CONTINENTS_PACKAGE_TAG = new ArrayList<>();
      this.sMAP_REGIONS_PACKAGE_TAG = new ArrayList<>();
      this.iMAP_ICON = new ArrayList<>();
      this.MAP_WORLD_MAP = new ArrayList<>();
      this.MAP_SCENARIO = new ArrayList<>();
      int i = 0;

      for(int iSize = this.sMAP_TAGS.size(); i < iSize; ++i) {
         new Map.Config();
         json.setElementType(Map.Config.class, "Map", Map.MapInformations.class);
         data = json.fromJson(Map.Config.class, Gdx.files.internal("map/" + (String)this.sMAP_TAGS.get(i) + "/" + "config" + ".json").reader("UTF8"));
         Iterator var6 = data.Map.iterator();
         if (var6.hasNext()) {
            Object e = var6.next();
            Map.MapInformations tempMapFolder = (Map.MapInformations)e;
            this.sMAP_LANG_KEY.add(tempMapFolder.MapName);
            this.sMAP_AUTHOR.add(tempMapFolder.Author);
            this.sMAP_BACKGROUND_NAME.add(tempMapFolder.BackgroundName);
            this.sMAP_CONTINENTS_PACKAGE_TAG.add(tempMapFolder.ContinentsPackage);
            this.sMAP_REGIONS_PACKAGE_TAG.add(tempMapFolder.RegionsPackage);
            this.iMAP_NUM_OF_PROVINCES.add(tempMapFolder.NumberOfProvinces);
            this.iMAP_SCALE
               .add(
                  tempMapFolder.MapScale
                     + (
                        CFG.isDesktop()
                           ? (CFG.XHDPI ? 1 : 0)
                           : (CFG.isAndroid() ? (!CFG.XXXXHDPI && !CFG.XXXHDPI && !CFG.XXHDPI ? (CFG.XHDPI ? 1 : 0) : 2) : 0)
                     )
               );
            this.iMAP_DEFAULT_SCALE.add(tempMapFolder.MapScale);
            this.MAP_WORLD_MAP.add(tempMapFolder.WorldMap);
            this.MAP_SCENARIO.add(tempMapFolder.Scenario);
            this.sMAP_WIKI.add(tempMapFolder.Wiki);
         }

         this.iMAP_ICON.add(new Image(new Texture(Gdx.files.internal("map/" + (String)this.sMAP_TAGS.get(i) + "/" + "ico.png"))));
      }

      this.mapBG = new Map_BG();
      this.mapCoordinates = new Map_Coordinates();
      this.mapTouchManager = new Map_TouchManager();
      this.mapScroll = new Map_Scroll();
      this.mapScale = new Map_Scale();
   }

   protected final void loadSettings_ActiveMap() {
      try {
         try {
            FileHandle file = Gdx.files.local("settings_map");
            SaveActiveMap_GameData tempActiveMapData = (SaveActiveMap_GameData)CFG.deserialize(file.readBytes());
            if (tempActiveMapData.iActiveMapID >= 0 && tempActiveMapData.iActiveMapID < this.getNumOfMaps()) {
               CFG.map.setMapScale(tempActiveMapData.iActiveMapID, tempActiveMapData.iActiveMapScale);
               CFG.map.setActiveMapID(tempActiveMapData.iActiveMapID);
            }
         } catch (GdxRuntimeException var8) {
            FileHandle file = Gdx.files.internal("settings_map");
            SaveActiveMap_GameData tempActiveMapData = (SaveActiveMap_GameData)CFG.deserialize(file.readBytes());
            if (tempActiveMapData.iActiveMapID >= 0 && tempActiveMapData.iActiveMapID < this.getNumOfMaps()) {
               CFG.map.setMapScale(tempActiveMapData.iActiveMapID, tempActiveMapData.iActiveMapScale);
               CFG.map.setActiveMapID(tempActiveMapData.iActiveMapID);
            }
         }
      } catch (GdxRuntimeException var9) {
      } catch (ClassNotFoundException var10) {
      } catch (IOException var11) {
      }

      try {
         try {
            FileHandle file = Gdx.files.local("status");
            SaveActiveMap_Status_GameData tempActiveMapData = (SaveActiveMap_Status_GameData)CFG.deserialize(file.readBytes());
            this.load_MinScale();
         } catch (GdxRuntimeException var4) {
            FileHandle filex = Gdx.files.internal("status");
            SaveActiveMap_Status_GameData tempActiveMapDatax = (SaveActiveMap_Status_GameData)CFG.deserialize(filex.readBytes());
            this.load_MinScale();
         }
      } catch (GdxRuntimeException var5) {
      } catch (ClassNotFoundException var6) {
      } catch (IOException var7) {
      }
   }

   protected final void load_MinScale() {
      FileHandle tempFileT = Gdx.files
         .internal("map/" + CFG.map.getFile_Map_Path(this.getActiveMapID()) + "data/" + "scales/" + "provinces/" + "Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");
      CFG.map.setMapScale(this.getActiveMapID(), Integer.parseInt(tagsSPLITED[0]));
      this.load_DeleteStatusFile();
      GAME_CRASHED_LOADED_MIN_SCALE = true;
   }

   protected final void load_DeleteStatusFile() {
      try {
         Gdx.files.local("status").delete();
      } catch (GdxRuntimeException var4) {
         try {
            Gdx.files.internal("status").delete();
         } catch (GdxRuntimeException var3) {
         }
      }
   }

   protected final void initMapContinents() {
      this.mapContinents = new Map_Continents(this.sMAP_CONTINENTS_PACKAGE_TAG.get(this.getActiveMapID()));
   }

   protected final void initMapRegions() {
      this.mapRegions = new Map_Regions(this.sMAP_REGIONS_PACKAGE_TAG.get(this.getActiveMapID()));
   }

   protected final void update() {
      this.mapScale.update();
      this.mapScroll.update();
      this.mapCoordinates.update();
   }

   protected final void drawMap(SpriteBatch oSB) {
      if (this.mapBG.requestToDisposeMinimap) {
         this.mapBG.disposeMinimapOfCivilizations_Real();
      }

      this.mapBG.drawMinimapTexture_Generate(oSB);
      this.mapBG.drawMap(oSB, this.mapCoordinates.getPosX(), this.mapCoordinates.getPosY());
      this.mapBG.drawMapBorder(oSB, this.mapCoordinates.getPosX(), this.mapCoordinates.getPosY());
   }

   protected final String getFile_ActiveMap_Path() {
      return (String)this.sMAP_TAGS.get(this.iActiveMapID) + "/";
   }

   protected final String getFile_Map_Path(int nMapID) {
      return (String)this.sMAP_TAGS.get(nMapID) + "/";
   }

   protected final String getMapName_Just(int i) {
      return CFG.langManager.get(this.getMapLangKey(i));
   }

   protected final String getMapName(int i) {
      return CFG.langManager.get(this.getMapLangKey(i)) + " | " + this.getMapNumOfProvinces(i) + " " + CFG.langManager.get("Provinces");
   }

   protected final void updateWorldMap() {
      this.mapBG.updateWorldMap();
      this.mapCoordinates.updateWorldMap();
   }

   protected final Map_BG getMapBG() {
      return this.mapBG;
   }

   protected final Map_Coordinates getMapCoordinates() {
      return this.mapCoordinates;
   }

   protected final Map_TouchManager getMapTouchManager() {
      return this.mapTouchManager;
   }

   protected final Map_Scroll getMapScroll() {
      return this.mapScroll;
   }

   protected final Map_Scale getMapScale() {
      return this.mapScale;
   }

   protected final Map_Continents getMapContinents() {
      return this.mapContinents;
   }

   protected final Map_Regions getMapRegions() {
      return this.mapRegions;
   }

   protected final int getActiveMapID() {
      return this.iActiveMapID;
   }

   protected final void setActiveMapID(int iActiveMapID) {
      if (this.iActiveMapID != iActiveMapID) {
         this.iActiveMapID = iActiveMapID;
         this.updateWorldMap();
      }
   }

   protected final String getMapLangKey(int i) {
      return this.sMAP_LANG_KEY.get(i);
   }

   protected final int getMapNumOfProvinces(int i) {
      return this.iMAP_NUM_OF_PROVINCES.get(i);
   }

   protected final int getNumOfMaps() {
      return this.sMAP_TAGS.size();
   }

   protected final Image getIcon(int i) {
      return this.iMAP_ICON.get(i);
   }

   protected final String getMapBackgroundName(int i) {
      return this.sMAP_BACKGROUND_NAME.get(i);
   }

   protected final String getMapAuthor(int i) {
      return this.sMAP_AUTHOR.get(i);
   }

   protected final String getMapWiki(int i) {
      return this.sMAP_WIKI.get(i);
   }

   protected final int setMapScale(int i, int nMapScale) {
      return this.iMAP_SCALE.set(i, nMapScale);
   }

   protected final int getMapScale(int i) {
      return this.iMAP_SCALE.get(i);
   }

   protected final int getMapDefaultScale(int i) {
      return this.iMAP_DEFAULT_SCALE.get(i);
   }

   protected final String getMapContinentsPackageTag(int i) {
      return this.sMAP_CONTINENTS_PACKAGE_TAG.get(i);
   }

   protected final String getMapRegionsPackageTag(int i) {
      return this.sMAP_REGIONS_PACKAGE_TAG.get(i);
   }

   protected final boolean getMapWorldMap(int i) {
      return this.MAP_WORLD_MAP.get(i);
   }

   protected final String getMapDefaultScenario(int i) {
      return this.MAP_SCENARIO.get(i);
   }

   protected static class Config {
      private String Age_of_Civilizations;
      private ArrayList Map;

      protected void setMapData(ArrayList nMap) {
         this.Map = nMap;
      }
   }

   protected static class MapInformations {
      private String MapName;
      private String Author;
      private String BackgroundName;
      private String ContinentsPackage;
      private String RegionsPackage;
      private int NumberOfProvinces;
      private int MapScale;
      private boolean WorldMap;
      private String Scenario;
      private String Wiki;
   }

   protected static class Maps {
      private String Folder;
   }

   protected static class Mapsrr {
      private String Folder;
   }
}
