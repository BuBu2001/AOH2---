package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

class Game_Cities {
   protected final List<City> loadCities() {
      List<City> nCities = new ArrayList<>();
      new Game_Cities.Config();

      try {
         Game_Cities.Config citiesData = this.readCities("cities.json");

         for(Object e : citiesData.cities) {
            Game_Cities.GameCity oCityData = (Game_Cities.GameCity)e;
            nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city));
         }
      } catch (GdxRuntimeException var10) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var10);
         }
      }

      try {
         Game_Cities.Config var11 = this.readCities("cities_1.json");

         for(Object e : var11.cities) {
            Game_Cities.GameCity oCityData = (Game_Cities.GameCity)e;
            nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city2));
         }
      } catch (GdxRuntimeException var9) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var9);
         }
      }

      try {
         Game_Cities.Config var12 = this.readCities("cities_2.json");

         for(Object e : var12.cities) {
            Game_Cities.GameCity oCityData = (Game_Cities.GameCity)e;
            nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city3));
         }
      } catch (GdxRuntimeException var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      }

      try {
         Game_Cities.Config var13 = this.readCities("cities_3.json");

         for(Object e : var13.cities) {
            Game_Cities.GameCity oCityData = (Game_Cities.GameCity)e;
            nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city4));
         }
      } catch (GdxRuntimeException var7) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var7);
         }
      }

      try {
         Game_Cities.Config var14 = this.readCities("cities_4.json");

         for(Object e : var14.cities) {
            Game_Cities.GameCity oCityData = (Game_Cities.GameCity)e;
            nCities.add(new City(oCityData.Name, oCityData.x, oCityData.y, Images.city5));
         }
      } catch (GdxRuntimeException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }

      return nCities;
   }

   private final Game_Cities.Config readCities(String nFileName) {
      Json json = new Json();
      json.setElementType(Game_Cities.Config.class, "cities", Game_Cities.GameCity.class);
      return json.fromJson(Game_Cities.Config.class, Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "cities/" + nFileName).reader("UTF8"));
   }

   protected static class Config {
      private ArrayList cities;
      private String name;
   }

   protected static class GameCity {
      protected String Name;
      protected int x;
      protected int y;
   }
}
