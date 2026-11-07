package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Achievement_Data {
   private String sText;
   private int iTextWidth = -1;
   private String sTextNum;
   private int iTextNumWidth = -1;
   private int iCivID;
   private int iSROverID = 0;
   protected static final float FONT_SCALE = 0.8F;
   private long lTime;
   private int TIME_IN_VIEW = 4500;
   private int TIME_IN_VIEW_HIDE_ANIMATION = 500;
   private int iSRID = 0;
   private int iLevelID = 0;
   private List<Color> lColors;

   protected Achievement_Data(int nCivID, String nTagID, String nText, String nTextNum, int nLevelID) {
      this.iCivID = nCivID;
      this.sText = nText;
      this.sTextNum = nTextNum;
      this.iLevelID = nLevelID;
      CFG.glyphLayout.setText(CFG.fontMain, this.sText);
      this.iTextWidth = (int)(CFG.glyphLayout.width * 0.8F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextNum);
      this.iTextNumWidth = (int)(CFG.glyphLayout.width * 0.8F);
      this.lColors = new ArrayList<>();
      Civilization_GameData3 tempSR = null;
      String tempTag = nTagID;
      this.iSROverID = nTagID.charAt(0) % CFG.serviceRibbon_Manager.getSROverlayImagesSize();
      if (nTagID.indexOf(";") > 0) {
         String[] tData = nTagID.split(";");
         tempTag = tData[0];
      }

      try {
         try {
            FileHandle fileSR = Gdx.files.internal("game/civilizations/" + tempTag);
            tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
         } catch (GdxRuntimeException var18) {
            try {
               FileHandle fileSRx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempTag));
               tempSR = (Civilization_GameData3)CFG.deserialize(fileSRx.readBytes());
            } catch (GdxRuntimeException var17) {
               try {
                  FileHandle fileSRxx = Gdx.files.local("game/civilizations_editor/" + tempTag + "/" + tempTag);
                  tempSR = (Civilization_GameData3)CFG.deserialize(fileSRxx.readBytes());
               } catch (GdxRuntimeException var16) {
                  try {
                     FileHandle fileSRxxx = Gdx.files.internal("game/civilizations_editor/" + tempTag + "/" + tempTag);
                     tempSR = (Civilization_GameData3)CFG.deserialize(fileSRxxx.readBytes());
                  } catch (GdxRuntimeException var15) {
                     try {
                        FileHandle fileSRxxxx = Gdx.files
                           .local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSRxxxx.readBytes());
                     } catch (GdxRuntimeException var14) {
                        FileHandle fileSRxxxxx = Gdx.files
                           .internal(
                              "game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag)
                           );
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSRxxxxx.readBytes());
                     }
                  }
               }
            }
         }
      } catch (ClassNotFoundException var19) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var19);
         }
      } catch (IOException var20) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var20);
         }
      }

      if (tempSR.sr_GameData != null) {
         this.iSRID = CFG.serviceRibbon_Manager.getSRID(tempSR.sr_GameData.getSRTAG());

         for(int i = 0; i < tempSR.sr_GameData.getColors().size(); ++i) {
            this.lColors
               .add(new Color(tempSR.sr_GameData.getColor(i).getR(), tempSR.sr_GameData.getColor(i).getG(), tempSR.sr_GameData.getColor(i).getB(), 1.0F));
         }

         this.lTime = System.currentTimeMillis();
      }
   }

   private final int getPosX() {
      return CFG.GAME_WIDTH / 2 - this.getWidth() / 2;
   }

   private final int getPosY() {
      return CFG.BUTTON_HEIGHT * 3 / 4;
   }

   private final int getWidth() {
      return Math.max(
         this.iTextWidth + this.iTextNumWidth + CFG.PADDING * 10,
         CFG.SERVICE_RIBBON_WIDTH * (this.iLevelID + 1) + CFG.PADDING * this.iLevelID + CFG.PADDING * 10
      );
   }

   private final int getHeight() {
      return CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 2;
   }

   protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
      float tAlpha = this.getAlpha();
      oSB.setColor(
         new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, CFG.COLOR_GRADIENT_DARK_BLUE.a * tAlpha)
      );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 1.0F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - 2 + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - 1 + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.SERVICE_RIBBON_HEIGHT
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - 1 + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iTextNumWidth) / 2 + iTranslateX,
         this.getPosY() + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 4 + iTranslateY,
         new Color(
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL.a * tAlpha
         )
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sTextNum,
         this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iTextNumWidth) / 2 + this.iTextWidth + iTranslateX,
         this.getPosY() + CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 4 + iTranslateY,
         new Color(
            CFG.COLOR_TEXT_NUM_OF_PROVINCES.r,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES.g,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES.b,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES.a * tAlpha
         )
      );
      CFG.fontMain.getData().setScale(1.0F);

      for(int i = 0; i < this.iLevelID + 1; ++i) {
         CFG.serviceRibbon_Manager
            .drawSRLevel(
               oSB,
               this.getPosX()
                  + this.getWidth() / 2
                  - (CFG.SERVICE_RIBBON_WIDTH * (this.iLevelID + 1) + CFG.PADDING * this.iLevelID) / 2
                  + CFG.SERVICE_RIBBON_WIDTH * i
                  + CFG.PADDING * i
                  + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 + iTranslateY,
               i,
               0,
               this.iSROverID,
               this.iSRID,
               this.lColors
            );
      }

      CFG.setRender_3(true);
   }

   private final float getAlpha() {
      return System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION
         ? Math.max(
            0.0F,
            1.0F
               - (float)(System.currentTimeMillis() - (this.lTime + (long)this.TIME_IN_VIEW - (long)this.TIME_IN_VIEW_HIDE_ANIMATION))
                  / (float)this.TIME_IN_VIEW_HIDE_ANIMATION
         )
         : 1.0F;
   }

   protected final boolean canBeDisposed() {
      return System.currentTimeMillis() > this.lTime + (long)this.TIME_IN_VIEW;
   }
}
