package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class TradeZones_Manager {
   private List<TradeZone> lTradeZones = new ArrayList<>();
   private List<String> lTags = new ArrayList<>();
   private int iTradeZonesSize;

   public TradeZones_Manager() {
   }

   protected final void loadTradeZones_Age() {
      this.clearData();
   }

   protected final void loadTradeZones_All() {
      this.clearData();

      for(int i = 0; i < CFG.gameAges.getAgesSize(); ++i) {
         List<String> sFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "trade_zones/" + "zones/" + i + "/");

         for(int j = 0; j < sFiles.size(); ++j) {
            FileHandle fileData = Gdx.files
               .internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "trade_zones/" + "zones/" + i + "/" + (String)sFiles.get(j));

            try {
               TradeZone_GameData tempGameData = (TradeZone_GameData)CFG.deserialize(fileData.readBytes());
               this.lTradeZones
                  .add(
                     new TradeZone(
                        tempGameData.getName(),
                        tempGameData.getProvinces(),
                        tempGameData.getCenterOfTradeProvinceID(),
                        tempGameData.getAge(),
                        new Color(tempGameData.getColor().getR(), tempGameData.getColor().getG(), tempGameData.getColor().getB(), 0.65F)
                     )
                  );
               this.lTags.add(sFiles.get(j));
               this.iTradeZonesSize = this.lTradeZones.size();
            } catch (ClassNotFoundException var6) {
            } catch (IOException var7) {
            }
         }
      }
   }

   protected final int isInAnotherTradeZone(int nProvinceIDToCheck) {
      for(int i = 0; i < this.getTradeZonesSize(); ++i) {
         for(int j = 0; j < this.getTradeZone(i).getProvincesSize(); ++j) {
            if (this.getTradeZone(i).getProvince(j) == nProvinceIDToCheck) {
               return i;
            }
         }
      }

      return -1;
   }

   protected final void clearData() {
      this.lTradeZones.clear();
      this.lTags.clear();
      this.iTradeZonesSize = 0;
   }

   protected final void addTradeZone(int nCenterOfTrade) {
      this.lTradeZones.add(new TradeZone(nCenterOfTrade));
      this.iTradeZonesSize = this.lTradeZones.size();
      CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
      this.lTags.add(CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
   }

   protected final void removeTradeZone(int nTradeZoneID) {
      Gdx.files
         .local(
            "map/"
               + CFG.map.getFile_ActiveMap_Path()
               + "data/"
               + "trade_zones/"
               + "zones/"
               + this.getTradeZone(nTradeZoneID).getAge()
               + "/"
               + this.getTag(nTradeZoneID)
         )
         .delete();
      this.lTradeZones.remove(nTradeZoneID);
      this.iTradeZonesSize = this.lTradeZones.size();
   }

   protected final void saveTradeZone_GameData(int nTradeZoneID) {
      if (this.getTradeZone(nTradeZoneID).getAge() == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2) {
         OutputStream os = null;

         try {
            TradeZone_GameData tempGameData = new TradeZone_GameData(
               this.getTradeZone(nTradeZoneID).getName(),
               this.getTradeZone(nTradeZoneID).getProvinces(),
               this.getTradeZone(nTradeZoneID).getCenterOfTradeProvinceID_Real(),
               this.getTradeZone(nTradeZoneID).getAge(),
               new Color_GameData(
                  this.getTradeZone(nTradeZoneID).getColor().r, this.getTradeZone(nTradeZoneID).getColor().g, this.getTradeZone(nTradeZoneID).getColor().b
               )
            );
            FileHandle fileData = Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "data/"
                     + "trade_zones/"
                     + "zones/"
                     + this.getTradeZone(nTradeZoneID).getAge()
                     + "/"
                     + CFG.EDITOR_ACTIVE_GAMEDATA_TAG
               );
            fileData.writeBytes(CFG.serialize(tempGameData), false);
         } catch (IOException var13) {
         } finally {
            if (os != null) {
               try {
                  os.close();
               } catch (Exception var12) {
               }
            }
         }
      } else if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 > this.getTradeZone(nTradeZoneID).getAge()) {
      }
   }

   protected final TradeZone getTradeZone(int i) {
      return this.lTradeZones.get(i);
   }

   protected final String getTag(int i) {
      return this.lTags.get(i);
   }

   protected final int getTradeZonesSize() {
      return this.iTradeZonesSize;
   }
}
