package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.Random;

public class Editor_Colors extends Editor {
   private int iActivePaletteID = 1;
   private Civilization_Color lastColor = new Civilization_Color();
   private int iActiveColorID;

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(20) && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
         Random oR = new Random();
         FileHandle file = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
         String sFile = file.readString();
         String[] sRes = sFile.split(";");

         boolean isAvailable;
         do {
            this.iActiveColorID = oR.nextInt(Pallet_Manager.NUM_OF_COLORS);
            isAvailable = true;

            for(int i = 0; i < sRes.length; ++i) {
               FileHandle fileCheckColor = null;

               try {
                  fileCheckColor = Gdx.files.internal("game/civilizations_colors/" + this.iActivePaletteID + "/" + sRes[i]);
                  String sColorID = fileCheckColor.readString();
                  if (Integer.parseInt(sColorID) == this.iActiveColorID) {
                     isAvailable = false;
                     break;
                  }
               } catch (GdxRuntimeException var13) {
               }
            }
         } while(!isAvailable);

         this.lastColor.iR = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getR();
         this.lastColor.iG = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getG();
         this.lastColor.iB = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getB();
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setR(CFG.oR.nextInt(256));
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setG(CFG.oR.nextInt(256));
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setB(CFG.oR.nextInt(256));
      }

      if (Gdx.input.isKeyPressed(67) && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setR(this.lastColor.iR);
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setG(this.lastColor.iG);
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setB(this.lastColor.iB);
      }

      if (Gdx.input.isKeyPressed(21)) {
         --this.iActivePaletteID;
         if (this.iActivePaletteID < 1) {
            this.iActivePaletteID = 1;
         }
      }

      if (Gdx.input.isKeyPressed(22)) {
         ++this.iActivePaletteID;
      }

      if (Gdx.input.isKeyPressed(62) && CFG.game.getActiveProvinceID() >= 0) {
      }

      if (Gdx.input.isKeyPressed(19) && CFG.game.getActiveProvinceID() >= 0) {
         if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
            try {
               FileHandle fileCiv = Gdx.files
                  .internal("game/civilizations/" + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag());
               Civilization_GameData3 tempCivGameData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
               CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setR(tempCivGameData.getR());
               CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setG(tempCivGameData.getG());
               CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setB(tempCivGameData.getB());
            } catch (ClassNotFoundException var11) {
            } catch (IOException var12) {
            }
         } else {
            CFG.palletManager.loadCivilizationsPaletteOfColors(this.iActivePaletteID);
         }
      }

      if (Gdx.input.isKeyPressed(66) && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
         FileHandle fileSave = Gdx.files
            .local(
               "game/civilizations_colors/"
                  + this.iActivePaletteID
                  + "/"
                  + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag()
            );
         fileSave.writeString("" + this.iActiveColorID, false);
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setR(CFG.oR.nextInt(256));
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setG(CFG.oR.nextInt(256));
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setB(CFG.oR.nextInt(256));
      }
   }

   @Override
   public String toString() {
      return "ACTIVE PALETTEID: " + this.iActivePaletteID;
   }
}
