package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class TerrainTypesManager {
   private List<String> lNames;
   private List<String> lTerrainTags;
   private List<Color> lColors;
   private List<Image> lTerrainIcons;
   private List<Float> lDefense;
   private List<Float> lMilitaryUpkeep;
   private List<Float> lPopulationGrowth;
   private List<Float> lEconomyGrowth;
   private List<Float> lBuildCost;
   private List<Float> lMovementCost;
   private List<Float> lBaseDevelopment;
   private List<Integer> lBaseProvinceValue;
   private int iTerrainTypesSize;

   protected TerrainTypesManager() {
      this.loadTerrainTypes();
   }

   protected final void loadTerrainTypes() {
      if (this.lTerrainIcons != null) {
         int i = 0;

         while(i < this.lTerrainIcons.size()) {
            this.lTerrainIcons.get(i).getTexture().dispose();
            this.lTerrainIcons.remove(i);
         }
      }

      this.lNames = new ArrayList<>();
      this.lTerrainTags = new ArrayList<>();
      this.lTerrainIcons = new ArrayList<>();
      this.lColors = new ArrayList<>();
      this.lDefense = new ArrayList<>();
      this.lMilitaryUpkeep = new ArrayList<>();
      this.lPopulationGrowth = new ArrayList<>();
      this.lEconomyGrowth = new ArrayList<>();
      this.lBuildCost = new ArrayList<>();
      this.lMovementCost = new ArrayList<>();
      this.lBaseDevelopment = new ArrayList<>();
      this.lBaseProvinceValue = new ArrayList<>();

      try {
         FileHandle tempFileT = Gdx.files.internal("game/terrain_types/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         this.iTerrainTypesSize = tagsSPLITED.length;
         this.addSea();

         for(int i = 0; i < this.iTerrainTypesSize; ++i) {
            FileHandle fileData = Gdx.files.internal("game/terrain_types/" + tagsSPLITED[i]);

            try {
               Terrain_GameData3 tempData = (Terrain_GameData3)CFG.deserialize(fileData.readBytes());
               this.lNames.add(CFG.langManager.get(tempData.getName()));
               this.lTerrainTags.add(tagsSPLITED[i]);
               this.lColors.add(new Color(tempData.getColor().getR(), tempData.getColor().getG(), tempData.getColor().getB(), 0.55F));
               this.lDefense.add(tempData.getDefensiveModifier());
               this.lMilitaryUpkeep.add(tempData.getMilitaryUpkeepModifier());
               this.lPopulationGrowth.add(tempData.getPopulationGrowthModifier());
               this.lEconomyGrowth.add(tempData.getEconomyGrowthModifier());
               this.lBuildCost.add(tempData.getBuildCostModifier());
               this.lMovementCost.add(tempData.getMovementCost());
               this.lBaseDevelopment.add(tempData.getBaseDevelopmentLevel());
               this.lBaseProvinceValue.add(tempData.getBaseProvinceValue());

               try {
                  this.lTerrainIcons
                     .add(
                        new Image(
                           new Texture(
                              Gdx.files.internal("UI/" + CFG.getRescouresPath() + "terrain/" + tempData.getIconName() + ".png"), Pixmap.Format.RGBA8888, true
                           ),
                           Texture.TextureFilter.Linear
                        )
                     );
               } catch (GdxRuntimeException var8) {
                  this.lTerrainIcons
                     .add(
                        new Image(
                           new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "terrain/" + "notfound.png"), Pixmap.Format.RGBA8888, true),
                           Texture.TextureFilter.Linear
                        )
                     );
               }
            } catch (ClassNotFoundException var9) {
            } catch (IOException var10) {
            }
         }

         Object var13 = null;
      } catch (GdxRuntimeException var11) {
      }

      this.iTerrainTypesSize = this.lNames.size();
   }

   private final void addSea() {
      this.lNames.add(CFG.langManager.get("Sea"));
      this.lTerrainTags.add("");
      this.lColors.add(new Color(1.0F, 1.0F, 1.0F, 0.0F));
      this.lDefense.add(0.0F);
      this.lMilitaryUpkeep.add(0.08F);
      this.lPopulationGrowth.add(0.0F);
      this.lEconomyGrowth.add(0.0F);
      this.lBuildCost.add(0.0F);
      this.lMovementCost.add(0.0F);
      this.lBaseDevelopment.add(0.0F);
      this.lBaseProvinceValue.add(0);

      try {
         this.lTerrainIcons
            .add(
               new Image(
                  new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "terrain/" + "sea.png"), Pixmap.Format.RGBA8888, true),
                  Texture.TextureFilter.Linear
               )
            );
      } catch (GdxRuntimeException var2) {
         this.lTerrainIcons
            .add(
               new Image(
                  new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "terrain/" + "notfound.png"), Pixmap.Format.RGBA8888, true),
                  Texture.TextureFilter.Linear
               )
            );
      }
   }

   protected final int getTerrainTypeID(String sTag) {
      for(int i = 1; i < this.iTerrainTypesSize; ++i) {
         if (this.getTag(i).equals(sTag)) {
            return i;
         }
      }

      return 1;
   }

   protected final void saveTerrainData() {
      OutputStream os = null;

      try {
         FileHandle fileData = Gdx.files.local("game/terrain_types/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
         fileData.writeBytes(CFG.serialize(CFG.editorTerrain_Data2), false);

         try {
            FileHandle file = Gdx.files.internal("game/terrain_types/Age_of_Civilizations");
            String tempTags = file.readString();
            if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
               FileHandle fileSave = Gdx.files.local("game/terrain_types/Age_of_Civilizations");
               fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            }
         } catch (GdxRuntimeException var15) {
            FileHandle fileSave = Gdx.files.local("game/terrain_types/Age_of_Civilizations");
            fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
         }
      } catch (IOException var16) {
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (Exception var14) {
            }
         }
      }
   }

   protected final String getName(int i) {
      return this.lNames.get(i);
   }

   protected final String getTag(int i) {
      return this.lTerrainTags.get(i);
   }

   protected final Color getColor(int i) {
      return this.lColors.get(i);
   }

   protected final Image getIcon(int i) {
      return this.lTerrainIcons.get(i);
   }

   protected final int getTerrainsSize() {
      return this.iTerrainTypesSize;
   }

   protected final float getDefense(int i) {
      return this.lDefense.get(i);
   }

   protected final float getMilitaryUpkeep(int i) {
      return this.lMilitaryUpkeep.get(i);
   }

   protected final float getPopulationGrowth(int i) {
      return this.lPopulationGrowth.get(i);
   }

   protected final float getEconomyGrowth(int i) {
      return this.lEconomyGrowth.get(i);
   }

   protected final float getBuildCost(int i) {
      return this.lBuildCost.get(i);
   }

   protected final float getMovementCost(int i) {
      return this.lMovementCost.get(i);
   }

   protected final float getBaseDevelopmentModifier(int i) {
      return this.lBaseDevelopment.get(i);
   }

   protected final int getBaseProvinceValue(int i) {
      return this.lBaseProvinceValue.get(i);
   }
}
