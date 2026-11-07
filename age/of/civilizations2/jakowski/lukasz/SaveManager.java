package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

class SaveManager {
   protected static boolean saveRequest = false;
   protected static String saveTag;
   protected static boolean gameCanBeContinued = false;
   protected static int iTurnsSinceLastSave = 0;
   protected static boolean gameSaved = false;
   protected static boolean forceShowNextPlayerTurnView = false;

   protected static final void newGame() {
      saveTag = null;
      iTurnsSinceLastSave = 0;
   }

   protected static final void trySaveGame() {
      ++iTurnsSinceLastSave;
      if (gameWillBeSavedInThisTurn()) {
         forceShowNextPlayerTurnView = true;
         saveGame();
      } else {
         forceShowNextPlayerTurnView = false;
      }
   }

   protected static boolean gameWillBeSavedInNextTurn() {
      return CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE > 0 && CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE <= iTurnsSinceLastSave + 1 || saveRequest;
   }

   protected static boolean gameWillBeSavedInThisTurn() {
      return CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE > 0 && CFG.settingsManager.TURNS_BETWEEN_AUTOSAVE <= iTurnsSinceLastSave || saveRequest;
   }

   protected static final void saveGame() {
      try {
         Gdx.app.log("AoC", "saveGame: BEGIN");
         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         boolean firstSaveOfTheGame = false;
         if (!CFG.SPECTATOR_MODE) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               if (CFG.game.getPlayer(i).getCivID() > 0) {
                  CFG.serviceRibbon_Manager.saveStatistics_Civ(CFG.game.getPlayer(i).statistics_Civ_GameData);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         if (saveTag == null) {
            saveTag = "" + System.currentTimeMillis() + CFG.extraRandomTag();
            firstSaveOfTheGame = true;
         }

         try {
            FileHandle file;
            if (CFG.readLocalFiles()) {
               file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            } else {
               file = Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            }

            String tempTags = file.readString();
            if (tempTags.indexOf(saveTag) < 0) {
               FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
               fileSave.writeString(tempTags + saveTag + ";", false);
            }
         } catch (GdxRuntimeException var1191) {
            FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            fileSave.writeString(saveTag + ";", false);
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);

         try {
            if (CFG.isDesktop()) {
               FileHandle fileReadData = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag);
               if (fileReadData.exists()) {
                  fileReadData.copyTo(Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_BACKUP"));
               }

               FileHandle fileReadData2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + ".json");
               if (fileReadData2.exists()) {
                  fileReadData2.copyTo(Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + ".json_BACKUP"));
               }
            }
         } catch (NullPointerException var1189) {
            CFG.exceptionStack(var1189);
         } catch (GdxRuntimeException var1190) {
            CFG.exceptionStack(var1190);
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os_1 = null;

         try {
            Save_GameData_1 nSaveData = new Save_GameData_1();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_1");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1216 = null;
         } catch (IOException var1188) {
            CFG.exceptionStack(var1188);
         } finally {
            if (os_1 != null) {
               try {
                  os_1.close();
               } catch (Exception var1171) {
                  CFG.exceptionStack(var1171);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1212 = null;
         OutputStream os_2 = null;

         try {
            Save_GameData_2 nSaveData = new Save_GameData_2();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_2X");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1221 = null;
         } catch (IOException var1187) {
            CFG.exceptionStack(var1187);
         } finally {
            if (os_2 != null) {
               try {
                  os_2.close();
               } catch (Exception var1170) {
                  CFG.exceptionStack(var1170);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1218 = null;
         OutputStream os_3 = null;

         try {
            Save_GameData_3 nSaveData = new Save_GameData_3();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_3");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1225 = null;
         } catch (IOException var1186) {
            CFG.exceptionStack(var1186);
         } finally {
            if (os_3 != null) {
               try {
                  os_3.close();
               } catch (Exception var1169) {
                  CFG.exceptionStack(var1169);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1223 = null;
         OutputStream os_4 = null;

         try {
            Save_GameData_4 nSaveData = new Save_GameData_4();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_4");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1229 = null;
         } catch (IOException var1185) {
            CFG.exceptionStack(var1185);
         } finally {
            if (os_4 != null) {
               try {
                  os_4.close();
               } catch (Exception var1168) {
                  CFG.exceptionStack(var1168);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1227 = null;
         OutputStream os_5 = null;

         try {
            Save_GameData_5 nSaveData = new Save_GameData_5();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_5");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1233 = null;
         } catch (IOException var1184) {
            CFG.exceptionStack(var1184);
         } finally {
            if (os_5 != null) {
               try {
                  os_5.close();
               } catch (Exception var1167) {
                  CFG.exceptionStack(var1167);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1231 = null;
         OutputStream os_6 = null;

         try {
            Save_GameData_6 nSaveData = new Save_GameData_6();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_6");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1237 = null;
         } catch (IOException var1183) {
            CFG.exceptionStack(var1183);
         } finally {
            if (os_6 != null) {
               try {
                  os_6.close();
               } catch (Exception var1166) {
                  CFG.exceptionStack(var1166);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1235 = null;
         OutputStream os_7 = null;

         try {
            Save_GameData_7 nSaveData = new Save_GameData_7();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_7");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1241 = null;
         } catch (IOException var1182) {
            CFG.exceptionStack(var1182);
         } finally {
            if (os_7 != null) {
               try {
                  os_7.close();
               } catch (Exception var1165) {
                  CFG.exceptionStack(var1165);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1239 = null;
         OutputStream os_8 = null;

         try {
            Save_GameData_8 nSaveData = new Save_GameData_8();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_8");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1245 = null;
         } catch (IOException var1181) {
            CFG.exceptionStack(var1181);
         } finally {
            if (os_8 != null) {
               try {
                  os_8.close();
               } catch (Exception var1164) {
                  CFG.exceptionStack(var1164);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1243 = null;
         OutputStream os_9 = null;

         try {
            Save_GameData_9 nSaveData = new Save_GameData_9();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_9");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1249 = null;
         } catch (IOException var1180) {
            CFG.exceptionStack(var1180);
         } finally {
            if (os_9 != null) {
               try {
                  os_9.close();
               } catch (Exception var1163) {
                  CFG.exceptionStack(var1163);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1247 = null;
         OutputStream os_10 = null;

         try {
            Save_GameData_10 nSaveData = new Save_GameData_10();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_10");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1253 = null;
         } catch (IOException var1179) {
            CFG.exceptionStack(var1179);
         } finally {
            if (os_10 != null) {
               try {
                  os_10.close();
               } catch (Exception var1162) {
                  CFG.exceptionStack(var1162);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream var1251 = null;
         OutputStream os_11 = null;

         try {
            Save_GameData_11 nSaveData = new Save_GameData_11();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_11");
            file.writeBytes(CFG.serialize(nSaveData), false);
            Object var1257 = null;
         } catch (IOException var1178) {
            CFG.exceptionStack(var1178);
         } finally {
            if (os_11 != null) {
               try {
                  os_11.close();
               } catch (Exception var1161) {
                  CFG.exceptionStack(var1161);
               }
            }
         }

         OutputStream var1255 = null;
         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os8 = null;

         try {
            Save_GameData2 nSaveData2 = new Save_GameData2();
            nSaveData2.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_2");
            file.writeBytes(CFG.serialize(nSaveData2), false);
         } catch (IOException var1177) {
            CFG.exceptionStack(var1177);
         } finally {
            if (os8 != null) {
               try {
                  os8.close();
               } catch (Exception var1160) {
                  CFG.exceptionStack(var1160);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         saveSave_Info();
         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         if (firstSaveOfTheGame) {
            OutputStream os3 = null;

            try {
               FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + saveTag + "_O");
               file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseOwnersGameData), false);
            } catch (IOException var1176) {
               CFG.exceptionStack(var1176);
            } finally {
               if (os3 != null) {
                  try {
                     os3.close();
                  } catch (Exception var1159) {
                     CFG.exceptionStack(var1159);
                  }
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os2 = null;

         try {
            FileHandle file2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + saveTag + "_T");
            file2.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseGameData), false);
         } catch (IOException var1175) {
            CFG.exceptionStack(var1175);
         } finally {
            if (os2 != null) {
               try {
                  os2.close();
               } catch (Exception var1158) {
                  CFG.exceptionStack(var1158);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os3 = null;

         try {
            FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + saveTag + "_S");
            file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseStatsGD), false);
         } catch (IOException var1174) {
            CFG.exceptionStack(var1174);
         } finally {
            if (os3 != null) {
               try {
                  os3.close();
               } catch (Exception var1157) {
                  CFG.exceptionStack(var1157);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         int turnSavesID = 0;

         try {
            FileHandle fileReadData3 = null;
            if (CFG.readLocalFiles()) {
               fileReadData3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            } else {
               fileReadData3 = Gdx.files
                  .internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            }

            String tRead = fileReadData3.readString();
            turnSavesID = Integer.parseInt(tRead) + 1;
            FileHandle fileSave = Gdx.files
               .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            fileSave.writeString("" + turnSavesID, false);
         } catch (GdxRuntimeException var1173) {
            FileHandle fileSave = Gdx.files
               .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            fileSave.writeString("" + turnSavesID, false);
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os4 = null;

         try {
            FileHandle file4 = Gdx.files
               .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + saveTag + "_C" + "_" + turnSavesID);
            file4.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseTurnChanges), false);
            CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.clear();
         } catch (IOException var1172) {
            CFG.exceptionStack(var1172);
         } finally {
            if (os4 != null) {
               try {
                  os4.close();
               } catch (Exception var1156) {
                  CFG.exceptionStack(var1156);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         saveRequest = false;
         iTurnsSinceLastSave = 0;
         gameSaved = true;
         Gdx.app.log("AoC", "saveGame: END");
         CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
         CFG.toast.setTimeInView(3000);
         if (CFG.isDesktop() && AoCGame.steamGame != null) {
            AoCGame.steamGame.uploadScore_OnSave();
         }
      } catch (OutOfMemoryError var1208) {
         CFG.toast.setInView(CFG.langManager.get("Device is out of RAM memory - Game not saved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(3000);
      }
   }

   protected static final void saveGame_OLD() {
      try {
         Gdx.app.log("AoC", "saveGame: BEGIN");
         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         boolean firstSaveOfTheGame = false;
         if (!CFG.SPECTATOR_MODE) {
            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               if (CFG.game.getPlayer(i).getCivID() > 0) {
                  CFG.serviceRibbon_Manager.saveStatistics_Civ(CFG.game.getPlayer(i).statistics_Civ_GameData);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         if (saveTag == null) {
            saveTag = "" + System.currentTimeMillis() + CFG.extraRandomTag();
            firstSaveOfTheGame = true;
         }

         try {
            FileHandle file;
            if (CFG.readLocalFiles()) {
               file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            } else {
               file = Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            }

            String tempTags = file.readString();
            if (tempTags.indexOf(saveTag) < 0) {
               FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
               fileSave.writeString(tempTags + saveTag + ";", false);
            }
         } catch (GdxRuntimeException var197) {
            FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            fileSave.writeString(saveTag + ";", false);
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os = null;

         try {
            Save_GameData nSaveData = new Save_GameData();
            nSaveData.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag);
            file.writeBytes(CFG.serialize(nSaveData), false);
         } catch (IOException var196) {
            CFG.exceptionStack(var196);
         } finally {
            if (os != null) {
               try {
                  os.close();
               } catch (Exception var189) {
                  CFG.exceptionStack(var189);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os8 = null;

         try {
            Save_GameData2 nSaveData2 = new Save_GameData2();
            nSaveData2.buildData();
            FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + "_2");
            file.writeBytes(CFG.serialize(nSaveData2), false);
         } catch (IOException var195) {
            CFG.exceptionStack(var195);
         } finally {
            if (os8 != null) {
               try {
                  os8.close();
               } catch (Exception var188) {
                  CFG.exceptionStack(var188);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         saveSave_Info();
         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         if (firstSaveOfTheGame) {
            OutputStream os3 = null;

            try {
               FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + saveTag + "_O");
               file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseOwnersGameData), false);
            } catch (IOException var194) {
               CFG.exceptionStack(var194);
            } finally {
               if (os3 != null) {
                  try {
                     os3.close();
                  } catch (Exception var187) {
                     CFG.exceptionStack(var187);
                  }
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os2 = null;

         try {
            FileHandle file2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + saveTag + "_T");
            file2.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseGameData), false);
         } catch (IOException var193) {
            CFG.exceptionStack(var193);
         } finally {
            if (os2 != null) {
               try {
                  os2.close();
               } catch (Exception var186) {
                  CFG.exceptionStack(var186);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os3 = null;

         try {
            FileHandle file3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + saveTag + "_S");
            file3.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseStatsGD), false);
         } catch (IOException var192) {
            CFG.exceptionStack(var192);
         } finally {
            if (os3 != null) {
               try {
                  os3.close();
               } catch (Exception var185) {
                  CFG.exceptionStack(var185);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "...", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         int turnSavesID = 0;

         try {
            FileHandle fileReadData3 = null;
            if (CFG.readLocalFiles()) {
               fileReadData3 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            } else {
               fileReadData3 = Gdx.files
                  .internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            }

            String tRead = fileReadData3.readString();
            turnSavesID = Integer.parseInt(tRead) + 1;
            FileHandle fileSave = Gdx.files
               .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            fileSave.writeString("" + turnSavesID, false);
         } catch (GdxRuntimeException var191) {
            FileHandle fileSave = Gdx.files
               .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            fileSave.writeString("" + turnSavesID, false);
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + ".", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         OutputStream os4 = null;

         try {
            FileHandle file4 = Gdx.files
               .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + "TS/" + "TURN/" + saveTag + "_C" + "_" + turnSavesID);
            file4.writeBytes(CFG.serialize(CFG.timelapseManager.timelapseTurnChanges), false);
            CFG.timelapseManager.timelapseTurnChanges.lTurnChanges.clear();
         } catch (IOException var190) {
            CFG.exceptionStack(var190);
         } finally {
            if (os4 != null) {
               try {
                  os4.close();
               } catch (Exception var184) {
                  CFG.exceptionStack(var184);
               }
            }
         }

         CFG.toast.setInView(CFG.langManager.get("Saving") + "..", CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(6000);
         saveRequest = false;
         iTurnsSinceLastSave = 0;
         gameSaved = true;
         Gdx.app.log("AoC", "saveGame: END");
         CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
         CFG.toast.setTimeInView(3000);
         if (CFG.isDesktop() && AoCGame.steamGame != null) {
            AoCGame.steamGame.uploadScore_OnSave();
         }
      } catch (OutOfMemoryError var204) {
         CFG.toast.setInView(CFG.langManager.get("Device is out of RAM memory - Game not saved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(3000);
      }
   }

   private static final void saveSave_Info() {
      SaveManager.ConfigSaveInfo configData = new SaveManager.ConfigSaveInfo();
      configData.Age_of_Civilizations = "Data";
      new ArrayList();
      ArrayList dataList = new ArrayList();
      int tNumOfCivs = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            ++tNumOfCivs;
         }
      }

      SaveManager.Data_Save_Info nDataTag = new SaveManager.Data_Save_Info();
      nDataTag.Civs = tNumOfCivs;
      nDataTag.GameDate = Game_Calendar.getCurrentDate();
      nDataTag.Turn = Game_Calendar.TURN_ID;
      nDataTag.PLAYER_TAG = CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCivTag();
      dataList.add(nDataTag);
      configData.Data_Save_Info = dataList;
      Json jsonSave = new Json();
      jsonSave.setOutputType(JsonWriter.OutputType.json);
      jsonSave.setElementType(SaveManager.ConfigSaveInfo.class, "Data_Save_Info", SaveManager.Data_Save_Info.class);
      FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + saveTag + "/" + saveTag + ".json");
      fileSave.writeString(jsonSave.prettyPrint(configData), false);
   }

   protected static final void deleteSavedGame(int i) {
      FileHandle file2;
      if (CFG.readLocalFiles()) {
         file2 = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
      } else {
         file2 = Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
      }

      String tempTags = file2.readString();
      String[] tSplted = tempTags.split(";");
      if (i < tSplted.length) {
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + tSplted[i]).delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + tSplted[i] + ".json").delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + tSplted[i] + "_S").delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + tSplted[i] + "_O").delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + tSplted[i] + "_T").delete();
         int turnSavesID = 0;

         try {
            FileHandle fileReadData3 = null;
            if (CFG.readLocalFiles()) {
               fileReadData3 = Gdx.files
                  .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            } else {
               fileReadData3 = Gdx.files
                  .internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + "TURN/" + "Age_of_Civilizations");
            }

            String tRead = fileReadData3.readString();
            turnSavesID = Integer.parseInt(tRead) + 1;

            for(int j = 0; j < turnSavesID; ++j) {
               try {
                  Gdx.files
                     .local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + "TURN/" + tSplted[i] + "_C" + "_" + j)
                     .delete();
               } catch (GdxRuntimeException var9) {
               }
            }
         } catch (GdxRuntimeException var10) {
         }

         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + "TURN/" + "Age_of_Civilizations").delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/" + "TURN/").delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + "TS/").delete();
         Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i]).delete();
         String tempTagsNew = "";

         for(int j = 0; j < tSplted.length; ++j) {
            if (i != j) {
               tempTagsNew = tempTagsNew + tSplted[j] + ";";
            }
         }

         if (tempTagsNew.length() > 0) {
            FileHandle fileSave = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            fileSave.writeString(tempTagsNew, false);
         } else {
            Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations").delete();
         }
      }
   }

   protected static class ConfigSaveInfo {
      protected String Age_of_Civilizations;
      protected ArrayList Data_Save_Info;
   }

   protected static class Data_Save_Info {
      protected String PLAYER_TAG;
      protected String GameDate;
      protected int Turn;
      protected int Civs;
   }
}
