package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

class ConfigINI {
   protected static int iWidth = -1;
   protected static int iHeight = -1;
   protected static boolean fullscreen = true;
   protected static int iSamples = -1;
   protected static boolean vSync = false;
   protected static boolean landscape = true;
   protected static int iUIScale = -1;

   protected static final void readConfig() {
      try {
         FileHandle file = Gdx.files.local("config.ini");
         String tempTags = file.readString();
         String[] tSplited = tempTags.replace("\n", "").split(";");

         for(int i = 0; i < tSplited.length; ++i) {
            String[] tempR = tSplited[i].split("=");

            try {
               if (tempR[0].equals("FULLSCREEN")) {
                  fullscreen = Boolean.parseBoolean(tempR[1]);
               } else if (tempR[0].equals("WIDTH")) {
                  iWidth = Integer.parseInt(tempR[1]);
               } else if (tempR[0].equals("HEIGHT")) {
                  iHeight = Integer.parseInt(tempR[1]);
               } else if (tempR[0].equals("ANTIALIASING")) {
                  iSamples = Integer.parseInt(tempR[1]);
               } else if (tempR[0].equals("VSYNC")) {
                  vSync = Boolean.parseBoolean(tempR[1]);
               } else if (tempR[0].equals("LANDSCAPE")) {
                  landscape = Boolean.parseBoolean(tempR[1]);
               } else if (tempR[0].equals("UISCALE")) {
                  iUIScale = Integer.parseInt(tempR[1]);
               }
            } catch (IndexOutOfBoundsException var6) {
               iWidth = -1;
               iHeight = -1;
               fullscreen = true;
               iSamples = -1;
               vSync = false;
               landscape = true;
               iUIScale = -1;
               break;
            } catch (IllegalArgumentException var7) {
               iWidth = -1;
               iHeight = -1;
               fullscreen = true;
               iSamples = -1;
               vSync = false;
               landscape = true;
               iUIScale = -1;
               break;
            }
         }
      } catch (GdxRuntimeException var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      }
   }

   protected static final void saveConfig() {
      FileHandle fileSave = Gdx.files.local("config.ini");
      fileSave.writeString("FULLSCREEN=" + fullscreen + ";\n", false);
      fileSave.writeString("WIDTH=" + iWidth + ";\n", true);
      fileSave.writeString("HEIGHT=" + iHeight + ";\n", true);
      fileSave.writeString("ANTIALIASING=" + iSamples + ";\n", true);
      fileSave.writeString("VSYNC=" + vSync + ";\n", true);
      fileSave.writeString("LANDSCAPE=" + landscape + ";\n", true);
      fileSave.writeString("UISCALE=" + iUIScale + ";", true);
   }
}
