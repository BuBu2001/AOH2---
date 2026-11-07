package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;

public class Editor_ShiftPort extends Editor {
   private int iDiff = 1;

   @Override
   protected void keyDown(int keycode) {
      if (CFG.game.getActiveProvinceID() >= 0) {
         int tempX = 0;
         int tempY = 0;
         FileHandle fileProvinceData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "provinces/" + CFG.game.getActiveProvinceID());

         try {
            Province_GameData2 tData = (Province_GameData2)CFG.deserialize(fileProvinceData.readBytes());
            tempX = tData.iPort_ShiftX;
            tempY = tData.iPort_ShiftY;
            if (Gdx.input.isKeyPressed(51)) {
               ++this.iDiff;
            }

            if (Gdx.input.isKeyPressed(45)) {
               --this.iDiff;
               if (this.iDiff < 1) {
                  this.iDiff = 1;
               }
            }

            if (Gdx.input.isKeyPressed(21)) {
               tempX -= this.iDiff;
            }

            if (Gdx.input.isKeyPressed(22)) {
               tempX += this.iDiff;
            }

            if (Gdx.input.isKeyPressed(19)) {
               tempY -= this.iDiff;
            }

            if (Gdx.input.isKeyPressed(20)) {
               tempY += this.iDiff;
            }

            tData.iPort_ShiftX = tempX;
            tData.iPort_ShiftY = tempY;
            OutputStream osProvince = null;

            try {
               FileHandle fileProvince = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "provinces/" + CFG.game.getActiveProvinceID());
               fileProvince.writeBytes(CFG.serialize(tData), false);
            } catch (IOException var19) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var19);
               }
            } finally {
               if (osProvince != null) {
                  try {
                     osProvince.close();
                  } catch (Exception var18) {
                     if (CFG.LOGS) {
                        CFG.exceptionStack(var18);
                     }
                  }
               }
            }

            savePortPosition(tempX, tempY);
         } catch (ClassNotFoundException var21) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var21);
            }
         } catch (IOException var22) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var22);
            }
         } catch (GdxRuntimeException var23) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var23);
            }
         }
      }
   }

   protected static final void savePortPosition(int tempX, int tempY) {
      CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateProvincePort(tempX, tempY);
   }

   @Override
   public String toString() {
      return "SHIFT PORT: " + CFG.game.getActiveProvinceID() + "\nSHIFT: " + this.iDiff + " Q--, W++";
   }
}
