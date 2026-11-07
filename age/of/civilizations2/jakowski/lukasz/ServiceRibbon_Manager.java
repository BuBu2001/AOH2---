package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class ServiceRibbon_Manager {
   private List<ServiceRibbon_GameData> lSR;
   private List<String> lTags;
   private int iSRSize;
   private List<List<Image>> lSRImages = null;
   private int iSRImagesSize;
   private List<Image> lSROverlayImages = null;
   private int iSROverlayImagesSize;

   protected final Statistics_Civ_GameData loadStatistics_Civ(String nTag) {
      try {
         return (Statistics_Civ_GameData)CFG.deserialize(Gdx.files.local("saves/stats/civ/" + nTag).readBytes());
      } catch (GdxRuntimeException var3) {
      } catch (ClassNotFoundException var4) {
      } catch (IOException var5) {
      }

      return new Statistics_Civ_GameData(nTag);
   }

   protected final void saveStatistics_Civ(Statistics_Civ_GameData statistics_Civ_GameData) {
      if (statistics_Civ_GameData.sTag.length() != 0) {
         OutputStream os = null;

         try {
            FileHandle fileData = Gdx.files.local("saves/stats/civ/" + statistics_Civ_GameData.sTag);
            fileData.writeBytes(CFG.serialize(statistics_Civ_GameData), false);

            try {
               FileHandle file = Gdx.files.local("saves/stats/civ/Age_of_Civilizations");
               String tempTags = file.readString();
               String[] tData = tempTags.split(";");
               boolean tAdd = true;

               for(int i = 0; i < tData.length; ++i) {
                  if (tData[i].equals(statistics_Civ_GameData.sTag)) {
                     tAdd = false;
                     break;
                  }
               }

               if (tAdd) {
                  FileHandle fileSave = Gdx.files.local("saves/stats/civ/Age_of_Civilizations");
                  fileSave.writeString(tempTags + statistics_Civ_GameData.sTag + ";", false);
               }
            } catch (GdxRuntimeException var18) {
               FileHandle fileSave = Gdx.files.local("saves/stats/civ/Age_of_Civilizations");
               fileSave.writeString(statistics_Civ_GameData.sTag + ";", false);
            }
         } catch (IOException var19) {
         } finally {
            if (os != null) {
               try {
                  os.close();
               } catch (Exception var17) {
               }
            }
         }
      }
   }

   protected boolean check_RequestTurns(int nNumOfTurns) {
      return nNumOfTurns == this.getRequestTurns(this.getRequestTurns_Level(nNumOfTurns));
   }

   protected int getRequestTurns(int iLevel) {
      switch(iLevel) {
         case 0:
            return 50;
         case 1:
            return 250;
         case 2:
            return 500;
         case 3:
            return 1000;
         case 4:
            return 2000;
         default:
            return 0;
      }
   }

   protected int getRequestTurns_Level(int nNumOfTurns) {
      if (nNumOfTurns >= this.getRequestTurns(4)) {
         return 5;
      } else if (nNumOfTurns >= this.getRequestTurns(3)) {
         return 4;
      } else if (nNumOfTurns >= this.getRequestTurns(2)) {
         return 3;
      } else if (nNumOfTurns >= this.getRequestTurns(1)) {
         return 2;
      } else {
         return nNumOfTurns >= this.getRequestTurns(0) ? 1 : 0;
      }
   }

   protected int getRequestRecruitedArmy(int iLevel) {
      switch(iLevel) {
         case 0:
            return 5000;
         case 1:
            return 100000;
         case 2:
            return 250000;
         case 3:
            return 500000;
         case 4:
            return 1000000;
         default:
            return 0;
      }
   }

   protected int getRequestRecruitedArmy_Level(int nNumOfTurns) {
      if (nNumOfTurns >= this.getRequestRecruitedArmy(4)) {
         return 5;
      } else if (nNumOfTurns >= this.getRequestRecruitedArmy(3)) {
         return 4;
      } else if (nNumOfTurns >= this.getRequestRecruitedArmy(2)) {
         return 3;
      } else if (nNumOfTurns >= this.getRequestRecruitedArmy(1)) {
         return 2;
      } else {
         return nNumOfTurns >= this.getRequestRecruitedArmy(0) ? 1 : 0;
      }
   }

   protected boolean check_Request_ConquredProvinces(int nNum) {
      return nNum == this.getRequestProvinces(this.getRequestProvinces_Level(nNum - 1));
   }

   protected int getRequestProvinces(int i) {
      switch(i) {
         case 0:
            return 10;
         case 1:
            return 50;
         case 2:
            return 75;
         case 3:
            return 125;
         case 4:
            return 200;
         default:
            return 0;
      }
   }

   protected int getRequestProvinces_Level(int nNum) {
      if (nNum >= this.getRequestProvinces(4)) {
         return 5;
      } else if (nNum >= this.getRequestProvinces(3)) {
         return 4;
      } else if (nNum >= this.getRequestProvinces(2)) {
         return 3;
      } else if (nNum >= this.getRequestProvinces(1)) {
         return 2;
      } else {
         return nNum >= this.getRequestProvinces(0) ? 1 : 0;
      }
   }

   protected ServiceRibbon_Manager() {
      this.loadSR();
      this.loadSRImages();
   }

   protected final void loadSR() {
      try {
         this.lSR = new ArrayList<>();
         this.lTags = new ArrayList<>();
         FileHandle tempFileT = Gdx.files.internal("game/service_ribbons/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");

         for(int i = 0; i < tagsSPLITED.length; ++i) {
            try {
               this.lSR.add((ServiceRibbon_GameData)CFG.deserialize(Gdx.files.internal("game/service_ribbons/" + tagsSPLITED[i]).readBytes()));
               this.lTags.add(tagsSPLITED[i]);
            } catch (ClassNotFoundException var6) {
            } catch (IOException var7) {
            }
         }

         this.iSRSize = this.lSR.size();
      } catch (GdxRuntimeException var8) {
      }
   }

   protected final void drawSR(SpriteBatch oSB, int nPosX, int nPosY, int nID, List<Color> nColors) {
      for(int i = 0; i < this.lSR.get(nID).getSize(); ++i) {
         this.drawSROverlay(oSB, nPosX, nPosY, this.lSR.get(nID).getServiceRibbon_Overlay(i), nColors.get(i), 1);
      }

      oSB.setColor(Color.WHITE);
   }

   protected final void drawSR(SpriteBatch oSB, int nPosX, int nPosY, ServiceRibbon_GameData nSR, List<Color> nColors, int nExtraScale) {
      for(int i = 0; i < nSR.getSize(); ++i) {
         this.drawSROverlay(oSB, nPosX, nPosY, nSR.getServiceRibbon_Overlay(i), nColors.get(i), nExtraScale);
      }

      oSB.setColor(Color.WHITE);
   }

   protected final void drawSROverlay(SpriteBatch oSB, int nPosX, int nPosY, ServiceRibbon_Overlay_GameData nSROverlay, Color nColor, int nExtraScale) {
      oSB.setColor(nColor);
      if (nSROverlay.getReflected()) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nPosX + (int)((float)nSROverlay.getPosX() * CFG.GUI_SCALE * (float)nExtraScale),
               nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               (int)((float)nSROverlay.getWidth() * CFG.GUI_SCALE * (float)nExtraScale),
               CFG.SERVICE_RIBBON_HEIGHT * nExtraScale
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nPosX
                  + CFG.SERVICE_RIBBON_WIDTH * nExtraScale
                  - (int)((float)nSROverlay.getPosX() * CFG.GUI_SCALE * (float)nExtraScale)
                  - (int)((float)nSROverlay.getWidth() * CFG.GUI_SCALE * (float)nExtraScale),
               nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               (int)((float)nSROverlay.getWidth() * CFG.GUI_SCALE * (float)nExtraScale),
               CFG.SERVICE_RIBBON_HEIGHT * nExtraScale
            );
      } else {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nPosX + (int)((float)nSROverlay.getPosX() * CFG.GUI_SCALE * (float)nExtraScale),
               nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               (int)((float)nSROverlay.getWidth() * CFG.GUI_SCALE * (float)nExtraScale),
               CFG.SERVICE_RIBBON_HEIGHT * nExtraScale
            );
      }
   }

   protected final void drawSROver(SpriteBatch oSB, int nPosX, int nPosY, int nExtraScale) {
      this.lSROverlayImages
         .get(0)
         .draw(oSB, nPosX, nPosY - this.lSROverlayImages.get(0).getHeight(), CFG.SERVICE_RIBBON_WIDTH * nExtraScale, CFG.SERVICE_RIBBON_HEIGHT * nExtraScale);
   }

   protected final void drawSRLevel(SpriteBatch oSB, int nPosX, int nPosY, int iSRLevel, int iSRStyle, int iSROverStyle, int nID, List<Color> nColors) {
      this.drawSR(oSB, nPosX, nPosY, nID, nColors);
      this.lSROverlayImages
         .get(iSROverStyle)
         .draw(oSB, nPosX, nPosY - this.lSROverlayImages.get(iSROverStyle).getHeight(), CFG.SERVICE_RIBBON_WIDTH, CFG.SERVICE_RIBBON_HEIGHT);
      if (iSRLevel > 0) {
         this.lSRImages
            .get(iSRStyle)
            .get(iSRLevel - 1)
            .draw(oSB, nPosX, nPosY - this.lSRImages.get(iSRStyle).get(iSRLevel - 1).getHeight(), CFG.SERVICE_RIBBON_WIDTH, CFG.SERVICE_RIBBON_HEIGHT);
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), CFG.SERVICE_RIBBON_WIDTH, CFG.SERVICE_RIBBON_HEIGHT / 4);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.gradient).getHeight() + CFG.SERVICE_RIBBON_HEIGHT - CFG.SERVICE_RIBBON_HEIGHT / 4,
            CFG.SERVICE_RIBBON_WIDTH,
            CFG.SERVICE_RIBBON_HEIGHT / 4,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      CFG.drawRect(oSB, nPosX, nPosY - 1, CFG.SERVICE_RIBBON_WIDTH, CFG.SERVICE_RIBBON_HEIGHT);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      CFG.drawRect(oSB, nPosX - 1, nPosY - 2, CFG.SERVICE_RIBBON_WIDTH + 2, CFG.SERVICE_RIBBON_HEIGHT + 2);
      oSB.setColor(Color.WHITE);
   }

   protected final void saveData() {
      OutputStream os = null;

      try {
         FileHandle fileData = Gdx.files.local("game/service_ribbons/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
         fileData.writeBytes(CFG.serialize(CFG.editorServiceRibbon_GameData), false);

         try {
            FileHandle file = Gdx.files.internal("game/service_ribbons/Age_of_Civilizations");
            String tempTags = file.readString();
            if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
               FileHandle fileSave = Gdx.files.local("game/service_ribbons/Age_of_Civilizations");
               fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            }
         } catch (GdxRuntimeException var15) {
            FileHandle fileSave = Gdx.files.local("game/service_ribbons/Age_of_Civilizations");
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

   private final void loadSRImages() {
      if (this.lSRImages != null) {
         for(int i = 0; i < this.lSRImages.size(); ++i) {
            int j = 0;

            while(j < this.lSRImages.get(i).size()) {
               this.lSRImages.get(i).get(j).getTexture().dispose();
               this.lSRImages.get(i).remove(j);
            }
         }

         this.lSRImages.clear();
      }

      if (this.lSROverlayImages != null) {
         int i = 0;

         while(i < this.lSROverlayImages.size()) {
            this.lSROverlayImages.get(i).getTexture().dispose();
            this.lSROverlayImages.remove(i);
         }
      }

      this.lSRImages = new ArrayList<>();
      this.lSROverlayImages = new ArrayList<>();
      FileHandle tempFileT = Gdx.files.internal("UI/" + CFG.getRescouresPath() + "sr/" + "Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");

      for(int i = 0; i < tagsSPLITED.length; ++i) {
         try {
            List<Image> tempSRImages = new ArrayList<>();

            for(int j = 1; j < 6; ++j) {
               tempSRImages.add(
                  new Image(new Texture("UI/" + CFG.getRescouresPath() + "sr/" + tagsSPLITED[i] + "/" + j + ".png"), Texture.TextureFilter.Linear)
               );
            }

            this.lSRImages.add(tempSRImages);
         } catch (GdxRuntimeException var10) {
         }
      }

      FileHandle tempFileT2 = Gdx.files.internal("UI/" + CFG.getRescouresPath() + "sr_over/" + "Age_of_Civilizations");
      String tempT2 = tempFileT2.readString();
      String[] tagsSPLITED2 = tempT2.split(";");

      for(int i = 0; i < tagsSPLITED2.length; ++i) {
         try {
            this.lSROverlayImages
               .add(new Image(new Texture("UI/" + CFG.getRescouresPath() + "sr_over/" + tagsSPLITED2[i] + ".png"), Texture.TextureFilter.Linear));
         } catch (GdxRuntimeException var9) {
         }
      }

      this.iSRImagesSize = this.lSRImages.size();
      this.iSROverlayImagesSize = this.lSROverlayImages.size();
   }

   protected final ServiceRibbon_GameData getSR(int i) {
      return this.lSR.get(i);
   }

   protected final int getSRID(String nTag) {
      for(int i = 0; i < this.iSRSize; ++i) {
         if (this.lTags.get(i).equals(nTag)) {
            return i;
         }
      }

      return 0;
   }

   protected final ServiceRibbon_GameData getSR(String nTag) {
      for(int i = 0; i < this.iSRSize; ++i) {
         if (this.lTags.get(i).equals(nTag)) {
            return this.lSR.get(i);
         }
      }

      return this.lSR.get(0);
   }

   protected final String getTag(int i) {
      return this.lTags.get(i);
   }

   protected final int getSRSize() {
      return this.iSRSize;
   }

   protected final int getSRImagesSize() {
      return this.iSRImagesSize;
   }

   protected final int getSROverlayImagesSize() {
      return this.iSROverlayImagesSize;
   }
}
