package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class PalletOfCivsColors_Data {
   private List<PalletOfCivsColors_Civ_GameData> lData = new ArrayList<>();
   private List<String> lCivsTags = new ArrayList<>();
   private int iDataSize = 0;
   private String UPDATE_KEY = null;

   protected final void setCivColor(String nTag, Color_GameData nColor) {
      for(int i = 0; i < this.iDataSize; ++i) {
         if (this.lCivsTags.get(i).equals(nTag)) {
            this.lData.get(i).setColor(nColor);
            return;
         }
      }

      this.lData.add(new PalletOfCivsColors_Civ_GameData(nColor));
      this.lCivsTags.add(nTag);
      this.iDataSize = this.lData.size();
   }

   protected final void readData(boolean isInternal) {
      this.lData = new ArrayList<>();
      this.lCivsTags = new ArrayList<>();
      this.iDataSize = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         FileHandle file = null;

         try {
            if (isInternal) {
               file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
            } else {
               file = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());
            }

            try {
               PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
               CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0F));
               CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0F));
               CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0F));
            } catch (ClassNotFoundException var5) {
               CFG.palletManager.loadCivilizationStandardColor(0);
            } catch (IOException var6) {
               CFG.palletManager.loadCivilizationStandardColor(0);
            }
         } catch (GdxRuntimeException var7) {
            CFG.palletManager.loadCivilizationStandardColor(0);
         }
      }
   }

   protected final void saveData() {
      for(int i = 0; i < this.iDataSize; ++i) {
         OutputStream outputSteam = null;

         try {
            try {
               FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + CFG.game.getCiv(i + 1).getCivTag());

               try {
                  Civilization_GameData3 temp = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  if (temp.getR() == (int)(this.lData.get(i).getColor().getR() * 255.0F)
                        && temp.getG() == (int)(this.lData.get(i).getColor().getG() * 255.0F)
                        && temp.getB() == (int)(this.lData.get(i).getColor().getB() * 255.0F)
                     || (int)(this.lData.get(i).getColor().getR() * 255.0F) == 0
                        && (int)(this.lData.get(i).getColor().getG() * 255.0F) == 1
                        && (int)(this.lData.get(i).getColor().getB() * 255.0F) == 2) {
                     continue;
                  }
               } catch (ClassNotFoundException var29) {
                  continue;
               }
            } catch (GdxRuntimeException var30) {
               try {
                  FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(i + 1).getCivTag()));

                  try {
                     Civilization_GameData3 temp = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                     if (temp.getR() == (int)(this.lData.get(i).getColor().getR() * 255.0F)
                           && temp.getG() == (int)(this.lData.get(i).getColor().getG() * 255.0F)
                           && temp.getB() == (int)(this.lData.get(i).getColor().getB() * 255.0F)
                        || (int)(this.lData.get(i).getColor().getR() * 255.0F) == 0
                           && (int)(this.lData.get(i).getColor().getG() * 255.0F) == 1
                           && (int)(this.lData.get(i).getColor().getB() * 255.0F) == 2) {
                        continue;
                     }
                  } catch (ClassNotFoundException var27) {
                     continue;
                  }
               } catch (GdxRuntimeException var28) {
               }
            }

            FileHandle fileData = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + (String)this.lCivsTags.get(i));
            fileData.writeBytes(CFG.serialize(this.lData.get(i)), false);

            try {
               FileHandle file;
               if (CFG.readLocalFiles()) {
                  file = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + "Age_of_Civilizations");
               } else {
                  file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + "Age_of_Civilizations");
               }

               String tempTags = file.readString();
               if (tempTags.indexOf(this.lCivsTags.get(i)) < 0) {
                  FileHandle fileSave = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + "Age_of_Civilizations");
                  fileSave.writeString(tempTags + (String)this.lCivsTags.get(i) + ";", false);
               }
            } catch (GdxRuntimeException var26) {
               FileHandle fileSave = Gdx.files.local("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + "Age_of_Civilizations");
               fileSave.writeString((String)this.lCivsTags.get(i) + ";", false);
            }
         } catch (IOException var31) {
         } finally {
            if (outputSteam != null) {
               try {
                  outputSteam.close();
               } catch (Exception var24) {
               }
            }
         }
      }

      try {
         FileHandle file;
         if (CFG.readLocalFiles()) {
            file = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
         } else {
            file = Gdx.files.internal("game/pallets_of_civs_colors/Age_of_Civilizations");
         }

         String tempTags = file.readString();
         if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
            FileHandle fileSave = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
            fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
         }
      } catch (GdxRuntimeException var25) {
         FileHandle fileSave = Gdx.files.local("game/pallets_of_civs_colors/Age_of_Civilizations");
         fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
      }
   }

   protected final int getDataSize() {
      return this.iDataSize;
   }

   protected final String getUPDATE_KEY() {
      return this.UPDATE_KEY;
   }

   protected final void setUPDATE_KEY(String nUPDATE_KEY) {
      this.UPDATE_KEY = nUPDATE_KEY;
   }
}
