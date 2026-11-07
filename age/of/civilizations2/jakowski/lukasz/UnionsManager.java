package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;

class UnionsManager {
   protected Union_GameData createUnion_Data;
   protected Unions_GameData unions;

   protected UnionsManager() {
      try {
         try {
            FileHandle file = Gdx.files.local("game/unions/data");

            try {
               this.unions = (Unions_GameData)CFG.deserialize(file.readBytes());
            } catch (ClassNotFoundException var6) {
            } catch (IOException var7) {
            }
         } catch (GdxRuntimeException var8) {
            FileHandle file = Gdx.files.internal("game/unions/data");

            try {
               this.unions = (Unions_GameData)CFG.deserialize(file.readBytes());
            } catch (ClassNotFoundException var4) {
            } catch (IOException var5) {
            }
         }
      } catch (GdxRuntimeException var9) {
         this.unions = new Unions_GameData();
      }
   }

   protected final void saveUnions() {
      if (this.unions.lUnions.size() > 0) {
         OutputStream osData = null;

         try {
            FileHandle fileWasteland = Gdx.files.local("game/unions/data");
            fileWasteland.writeBytes(CFG.serialize(this.unions), false);
         } catch (IOException var11) {
         } finally {
            if (osData != null) {
               try {
                  osData.close();
               } catch (Exception var10) {
               }
            }
         }
      }
   }

   protected final String getUnionTag(String nTag) {
      String[] tData = nTag.split(";");

      for(int i = 0; i < tData.length; ++i) {
         tData[i] = CFG.ideologiesManager.getRealTag(tData[i]);
      }

      for(int i = 0; i < this.unions.lUnions.size(); ++i) {
         for(int j = 0; j < this.unions.lUnions.get(i).lCivsTags.size(); ++j) {
            boolean found = false;

            for(int k = 0; k < tData.length; ++k) {
               if (tData[k].equals(this.unions.lUnions.get(i).lCivsTags.get(j))) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               break;
            }

            if (j == this.unions.lUnions.get(i).lCivsTags.size() - 1 && tData.length == this.unions.lUnions.get(i).lCivsTags.size()) {
               for(int k = 0; k < CFG.game.getCivsSize(); ++k) {
                  if (this.unions.lUnions.get(i).lCreateCivTag.equals(CFG.game.getCiv(k).getCivTag())) {
                     return "";
                  }
               }

               return this.unions.lUnions.get(i).lCreateCivTag;
            }
         }
      }

      return "";
   }
}
