package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;

class LinesManager {
   protected String highlightTAG = null;
   protected Image highlightImage = null;
   protected boolean highlightFlipX;
   protected boolean highlightMovable;
   protected String moveLandTAG = null;
   protected Image moveLandImage = null;
   protected boolean moveLandFlipX;
   protected boolean moveLandMovable;
   protected String migrateTAG = null;
   protected Image migrateImage = null;
   protected boolean migrateFlipX;
   protected boolean migrateMovable;

   protected LinesManager() {
      this.highlightTAG = CFG.settingsManager.sHighlightLine;
      this.moveLandTAG = CFG.settingsManager.sMoveLine;
      this.migrateTAG = "1";
      this.loadHighlight();
      this.loadMoveLand();
      this.loadMigrate();
   }

   protected final String loadNext(String sCurrent, boolean right) {
      FileHandle tempFileT = Gdx.files.internal("game/lines/Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");

      for(int i = 0; i < tagsSPLITED.length; ++i) {
         if (tagsSPLITED[i].equals(sCurrent)) {
            if (right) {
               if (i + 1 < tagsSPLITED.length) {
                  return tagsSPLITED[i + 1];
               }

               return tagsSPLITED[0];
            }

            if (i - 1 >= 0) {
               return tagsSPLITED[i - 1];
            }

            return tagsSPLITED[tagsSPLITED.length - 1];
         }
      }

      return "default";
   }

   protected final void loadHighlight() {
      if (this.highlightImage != null) {
         this.highlightImage.getTexture().dispose();
         this.highlightImage = null;
      }

      FileHandle tGameData = Gdx.files.internal("game/lines/" + this.highlightTAG);

      try {
         CFG.editorLine_GameData = (Line_GameData)CFG.deserialize(tGameData.readBytes());
         this.highlightImage = new Image(
            new Texture(Gdx.files.internal("game/lines/" + CFG.editorLine_GameData.getImageName() + ".png"), Pixmap.Format.RGBA8888, true),
            Texture.TextureFilter.Linear,
            CFG.editorLine_GameData.getRapeatImage() ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
         );
         this.highlightFlipX = CFG.editorLine_GameData.getFlipX();
         this.highlightMovable = CFG.editorLine_GameData.getMovable();
         CFG.editorLine_GameData = null;
         return;
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      } catch (GdxRuntimeException var5) {
      }

      this.highlightImage = new Image(
         new Texture(Gdx.files.internal("UI/pix"), Pixmap.Format.RGBA8888, true),
         Texture.TextureFilter.Linear,
         CFG.editorLine_GameData.getRapeatImage() ? Texture.TextureWrap.Repeat : Texture.TextureWrap.Repeat
      );
   }

   protected final void loadMigrate() {
      if (this.migrateImage != null) {
         this.migrateImage.getTexture().dispose();
         this.migrateImage = null;
      }

      FileHandle tGameData = Gdx.files.internal("game/lines/" + this.migrateTAG);

      try {
         CFG.editorLine_GameData = (Line_GameData)CFG.deserialize(tGameData.readBytes());
         this.migrateImage = new Image(
            new Texture(Gdx.files.internal("game/lines/" + CFG.editorLine_GameData.getImageName() + ".png"), Pixmap.Format.RGBA8888, true),
            Texture.TextureFilter.Linear,
            CFG.editorLine_GameData.getRapeatImage() ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
         );
         this.migrateFlipX = CFG.editorLine_GameData.getFlipX();
         this.migrateMovable = CFG.editorLine_GameData.getMovable();
         CFG.editorLine_GameData = null;
         return;
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      } catch (GdxRuntimeException var5) {
      }

      this.migrateImage = new Image(
         new Texture(Gdx.files.internal("UI/pix"), Pixmap.Format.RGBA8888, true),
         Texture.TextureFilter.Linear,
         CFG.editorLine_GameData.getRapeatImage() ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
      );
   }

   protected final void loadMoveLand() {
      if (this.moveLandImage != null) {
         this.moveLandImage.getTexture().dispose();
         this.moveLandImage = null;
      }

      FileHandle tGameData = Gdx.files.internal("game/lines/" + this.moveLandTAG);

      try {
         CFG.editorLine_GameData = (Line_GameData)CFG.deserialize(tGameData.readBytes());
         this.moveLandImage = new Image(
            new Texture(Gdx.files.internal("game/lines/" + CFG.editorLine_GameData.getImageName() + ".png"), Pixmap.Format.RGBA8888, true),
            Texture.TextureFilter.Linear,
            CFG.editorLine_GameData.getRapeatImage() ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
         );
         this.moveLandFlipX = CFG.editorLine_GameData.getFlipX();
         this.moveLandMovable = CFG.editorLine_GameData.getMovable();
         CFG.editorLine_GameData = null;
         return;
      } catch (ClassNotFoundException var3) {
      } catch (IOException var4) {
      } catch (GdxRuntimeException var5) {
      }

      this.moveLandImage = new Image(
         new Texture(Gdx.files.internal("UI/pix"), Pixmap.Format.RGBA8888, true),
         Texture.TextureFilter.Linear,
         CFG.editorLine_GameData.getRapeatImage() ? Texture.TextureWrap.Repeat : Texture.TextureWrap.ClampToEdge
      );
   }
}
