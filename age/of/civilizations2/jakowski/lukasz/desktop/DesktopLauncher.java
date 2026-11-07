package age.of.civilizations2.jakowski.lukasz.desktop;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DesktopLauncher {
   public static void main(String[] arg) {
      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
      config.title = "Age of Civilizations II";
      config.addIcon("ic_32x32.png", Files.FileType.Internal);
      config.resizable = false;
      int tWidth = -1;
      int tHeight = -1;
      boolean tFullscreen = true;
      int tSamples = -1;
      boolean tVSync = false;
      FileReader fr = null;
      String sLine = "";
      System.out.println(System.getProperty("file.encoding"));

      try {
         fr = new FileReader("config.ini");
         BufferedReader bfr = new BufferedReader(fr);

         while((sLine = bfr.readLine()) != null) {
            String[] tempR = sLine.replace(";", "").split("=");

            try {
               if (tempR[0].equals("FULLSCREEN")) {
                  tFullscreen = Boolean.parseBoolean(tempR[1]);
               } else if (tempR[0].equals("WIDTH")) {
                  tWidth = Integer.parseInt(tempR[1]);
               } else if (tempR[0].equals("HEIGHT")) {
                  tHeight = Integer.parseInt(tempR[1]);
               } else if (tempR[0].equals("ANTIALIASING")) {
                  tSamples = Integer.parseInt(tempR[1]);
               } else if (tempR[0].equals("VSYNC")) {
                  tVSync = Boolean.parseBoolean(tempR[1]);
               }
            } catch (IndexOutOfBoundsException var13) {
               tWidth = -1;
               tHeight = -1;
               tFullscreen = true;
               tSamples = -1;
               tVSync = false;
               break;
            } catch (IllegalArgumentException var14) {
               tWidth = -1;
               tHeight = -1;
               tFullscreen = true;
               tSamples = -1;
               tVSync = false;
               break;
            }
         }

         fr.close();
      } catch (IOException var15) {
         tWidth = -1;
         tHeight = -1;
         tFullscreen = true;
         tSamples = -1;
         tVSync = false;
      }

      if (tSamples != -1) {
         config.samples = tSamples;
      }

      config.vSyncEnabled = tVSync;
      if (tWidth <= 0 && tHeight <= 0) {
         config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
         config.fullscreen = tFullscreen;
      } else {
         config.width = tWidth;
         config.height = tHeight;
         config.fullscreen = tFullscreen;
      }

      try {
         SteamAPI.loadLibraries();
         if (!SteamAPI.init()) {
         }
      } catch (SteamException var12) {
      }

      new LwjglApplication(new AoCGame(new DesktopLinkHandler()), config);
   }
}
