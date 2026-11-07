package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class Game_Scenarios {
   protected static int SCENARIOS_SIZE;
   private List<String> lScenarios_TagsList = new ArrayList<>();
   private List<Boolean> isInternal = new ArrayList<>();
   private List<String> lScenarios_Names = new ArrayList<>();
   private List<Integer> lScenarios_CivNum = new ArrayList<>();
   private List<String> lScenarios_Authors = new ArrayList<>();
   private List<Integer> lScenarios_Age = new ArrayList<>();
   private List<Integer> lScenarios_Year = new ArrayList<>();
   private List<Integer> lScenarios_Month = new ArrayList<>();
   private List<Integer> lScenarios_Day = new ArrayList<>();
   private List<String> lScenarios_Wikis = new ArrayList<>();
   private int iScenario_StartingArmyInCapitals = 750;
   private int iScenario_NeutralArmy = 150;
   private int iScenario_StartingPopulation = 65000;
   private int iScenario_StartingEconomy = 32000;
   private int iScenario_StartingMoney = 4500;
   private float iScenario_PopulationGrowthRate_Modifier = 0.0F;
   private float iScenario_EconomyGrowthRate_Modifier = 0.0F;
   private float iScenario_DiseasesDeathRate_Modifier = 0.0F;
   private String sScenario_ActivePallet_TAG = null;
   protected String sActiveScenarioTag = "";
   protected static final float PERC_OF_POPULATION_REQUIRED_TO_GET_A_CORE = 0.18F;

   protected final void loadGame_Scenarios(boolean initMap) {
      if (SCENARIOS_SIZE > 0 || this.lScenarios_TagsList.size() > 0) {
         this.disposeScenarios();
      }

      String defaultScenario = null;
      String[] tagsSPLITED = null;
      if (CFG.isDesktop()) {
         List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/");
         int i = 0;

         for(int iSize = tempFiles.size(); i < iSize; ++i) {
            if (tempFiles.get(i).equals("Age_of_Civilizations")) {
               tempFiles.remove(i);
               break;
            }
         }

         tagsSPLITED = new String[tempFiles.size()];
         i = 0;

         for(int iSize = tempFiles.size(); i < iSize; ++i) {
            tagsSPLITED[i] = tempFiles.get(i);
         }
      } else {
         FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + "Age_of_Civilizations");
         String tempT = tempFileT.readString();
         tagsSPLITED = tempT.split(";");
      }

      List<String> tempScenarios_TagsList = new ArrayList<>();
      List<Boolean> tempIsInternal = new ArrayList<>();
      List<String> tempScenarios_Names = new ArrayList<>();
      List<Integer> tempScenarios_CivNum = new ArrayList<>();
      List<String> tempScenarios_Authors = new ArrayList<>();
      List<Integer> tempScenarios_Age = new ArrayList<>();
      List<Integer> tempScenarios_Year = new ArrayList<>();
      List<Integer> tempScenarios_Month = new ArrayList<>();
      List<Integer> tempScenarios_Day = new ArrayList<>();
      List<String> tempScenarios_Wikis = new ArrayList<>();
      int i = 0;

      for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
         tempScenarios_TagsList.add(tagsSPLITED[i]);
         tempIsInternal.add(true);
      }

      for(int ix = 0; ix < tempScenarios_TagsList.size(); ++ix) {
         try {
            new CFG.ConfigScenarioInfo();
            Json json = new Json();
            json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
            CFG.ConfigScenarioInfo var40 = json.fromJson(
               CFG.ConfigScenarioInfo.class,
               Gdx.files
                  .internal(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "scenarios/"
                        + (String)tempScenarios_TagsList.get(ix)
                        + "/"
                        + (String)tempScenarios_TagsList.get(ix)
                        + "_INFO"
                        + ".json"
                  )
                  .reader("UTF8")
            );
            Iterator nStart = var40.Data_Scenario_Info.iterator();
            if (nStart.hasNext()) {
               Object e = nStart.next();
               CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
               tempScenarios_CivNum.add(tempData.Civs);
               tempScenarios_Names.add(tempData.Name);
               tempScenarios_Authors.add(tempData.Author);
               tempScenarios_Wikis.add(tempData.Wiki);
               tempScenarios_Age.add(tempData.Age);
               tempScenarios_Year.add(tempData.Year);
               tempScenarios_Month.add(tempData.Month);
               tempScenarios_Day.add(tempData.Day);
            }
         } catch (GdxRuntimeException var27) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var27);
            }

            tempScenarios_CivNum.add(0);
            tempScenarios_Names.add("ERROR");
            tempScenarios_Authors.add("ERROR");
            tempScenarios_Wikis.add("");
            tempScenarios_Age.add(0);
            tempScenarios_Year.add(0);
            tempScenarios_Month.add(0);
            tempScenarios_Day.add(0);
         }
      }

      if (CFG.readLocalFiles()) {
         try {
            FileHandle tempFileT2 = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + "Age_of_Civilizations");
            String tempT2 = tempFileT2.readString();
            String[] tagsSPLITED2 = tempT2.split(";");
            int nStart = tempScenarios_TagsList.size();
            int ix = 0;

            for(int iSize = tagsSPLITED2.length; ix < iSize; ++ix) {
               tempScenarios_TagsList.add(tagsSPLITED2[ix]);
               tempIsInternal.add(false);
            }

            for(int ixx = nStart; ixx < tempScenarios_TagsList.size(); ++ixx) {
               FileHandle file = Gdx.files
                  .local(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "scenarios/"
                        + (String)tempScenarios_TagsList.get(ixx)
                        + "/"
                        + (String)tempScenarios_TagsList.get(ixx)
                        + "_INFO"
                        + ".json"
                  );
               String fileContent = file.readString();
               Json json = new Json();
               json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
               new CFG.ConfigScenarioInfo();
               CFG.ConfigScenarioInfo data = json.fromJson(CFG.ConfigScenarioInfo.class, fileContent);
               Iterator var23 = data.Data_Scenario_Info.iterator();
               if (var23.hasNext()) {
                  Object e = var23.next();
                  CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
                  tempScenarios_CivNum.add(tempData.Civs);
                  tempScenarios_Names.add(tempData.Name);
                  tempScenarios_Authors.add(tempData.Author);
                  tempScenarios_Wikis.add(tempData.Wiki);
                  tempScenarios_Age.add(tempData.Age);
                  tempScenarios_Year.add(tempData.Year);
                  tempScenarios_Month.add(tempData.Month);
                  tempScenarios_Day.add(tempData.Day);
               }
            }
         } catch (GdxRuntimeException var26) {
         }
      }

      if (CFG.game.getScenarioID() == -1) {
         defaultScenario = tempScenarios_TagsList.get(0);
         CFG.game.setScenarioID(0);
      }

      while(tempScenarios_TagsList.size() > 0) {
         i = 0;

         for(int ix = 1; ix < tempScenarios_TagsList.size(); ++ix) {
            if (tempScenarios_Year.get(i) < tempScenarios_Year.get(ix)) {
               i = ix;
            }
         }

         this.lScenarios_TagsList.add(tempScenarios_TagsList.get(i));
         tempScenarios_TagsList.remove(i);
         this.isInternal.add(tempIsInternal.get(i));
         tempIsInternal.remove(i);
         this.lScenarios_CivNum.add(tempScenarios_CivNum.get(i));
         tempScenarios_CivNum.remove(i);
         this.lScenarios_Names.add(tempScenarios_Names.get(i));
         tempScenarios_Names.remove(i);
         this.lScenarios_Authors.add(tempScenarios_Authors.get(i));
         tempScenarios_Authors.remove(i);
         this.lScenarios_Wikis.add(tempScenarios_Wikis.get(i));
         tempScenarios_Wikis.remove(i);
         this.lScenarios_Age.add(tempScenarios_Age.get(i));
         tempScenarios_Age.remove(i);
         this.lScenarios_Year.add(tempScenarios_Year.get(i));
         tempScenarios_Year.remove(i);
         this.lScenarios_Month.add(tempScenarios_Month.get(i));
         tempScenarios_Month.remove(i);
         this.lScenarios_Day.add(tempScenarios_Day.get(i));
         tempScenarios_Day.remove(i);
      }

      if (defaultScenario != null) {
         for(int ix = 0; ix < this.lScenarios_TagsList.size(); ++ix) {
            if (defaultScenario.equals(this.lScenarios_TagsList.get(ix))) {
               CFG.game.setScenarioID(ix);
               break;
            }
         }
      }

      SCENARIOS_SIZE = this.lScenarios_TagsList.size();
      if (initMap) {
         CFG.game.updateDaultScenarioID_ForMap();
      }
   }

   protected final void disposeScenarios() {
      this.lScenarios_TagsList.clear();
      this.lScenarios_TagsList = new ArrayList<>();
      this.lScenarios_Names.clear();
      this.lScenarios_Names = new ArrayList<>();
      this.lScenarios_CivNum.clear();
      this.lScenarios_CivNum = new ArrayList<>();
      this.lScenarios_Authors.clear();
      this.lScenarios_Authors = new ArrayList<>();
      this.lScenarios_Wikis.clear();
      this.lScenarios_Wikis = new ArrayList<>();
      this.lScenarios_Age.clear();
      this.lScenarios_Age = new ArrayList<>();
      this.lScenarios_Year.clear();
      this.lScenarios_Year = new ArrayList<>();
      this.lScenarios_Month.clear();
      this.lScenarios_Month = new ArrayList<>();
      this.lScenarios_Day.clear();
      this.lScenarios_Day = new ArrayList<>();
      this.isInternal.clear();
      this.isInternal = new ArrayList<>();
      SCENARIOS_SIZE = 0;
   }

   protected final List<Civilization> loadCivilizations_RandomGame() {
      Random oR = new Random();
      List<Civilization> lCivs = new ArrayList<>();
      lCivs.add(CFG.game.getNeutralCivilization());
      lCivs.get(0).setCivID(0);
      List<String> lRandomGameCivsTags = new ArrayList<>();
      if (CFG.RANDOM_PLACMENT) {
         FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         String[] tagsSPLITED_ED = new String[0];

         try {
            FileHandle tempFileT_ED = null;
            if (CFG.isAndroid()) {
               tempFileT_ED = Gdx.files.local("game/civilizations_editor/Age_of_Civilizations");
            } else {
               tempFileT_ED = Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            }

            String tempT_ED = tempFileT_ED.readString();
            tagsSPLITED_ED = tempT_ED.split(";");
         } catch (GdxRuntimeException var49) {
         }

         List<String> nCivsTags = new ArrayList<>();
         int i = 0;

         for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED[i])) {
               nCivsTags.add(tagsSPLITED[i]);
            }
         }

         i = 0;

         for(int iSize = tagsSPLITED_ED.length; i < iSize; ++i) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[i])) {
               nCivsTags.add(tagsSPLITED[i]);
            }
         }

         for(int ix = 0; ix < CFG.randomGameManager.getPlayersSize(); ++ix) {
            if (CFG.randomGameManager.getPlayer(ix).getTag() != null) {
               lRandomGameCivsTags.add(CFG.randomGameManager.getPlayer(ix).getTag());
            } else {
               int nR = oR.nextInt(nCivsTags.size());
               lRandomGameCivsTags.add(nCivsTags.get(nR));
               nCivsTags.remove(nR);
            }
         }

         try {
            i = 0;

            for(int nR = 0; i < CFG.randomGameManager.getCivilizationsSize(); ++i) {
               nR = oR.nextInt(nCivsTags.size());
               lRandomGameCivsTags.add(nCivsTags.get(nR));
               nCivsTags.remove(nR);
            }
         } catch (IllegalArgumentException var59) {
         }

         try {
            String tempTag = null;
            boolean add = true;
            int ix = 0;

            for(int iSize = lRandomGameCivsTags.size(); ix < iSize; ++ix) {
               int nRandIdeology = oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
               int nNumOfTries = 0;

               while(
                  (
                        CFG.ideologiesManager.getIdeology(nRandIdeology).REVOLUTIONARY
                           || CFG.ideologiesManager.getIdeology(nRandIdeology).CAN_BECOME_CIVILIZED >= 0
                     )
                     && nNumOfTries++ < 8
               ) {
                  nRandIdeology = oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
               }

               add = true;
               String var74 = CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix)) + CFG.ideologiesManager.getIdeology(nRandIdeology).getExtraTag();

               for(int j = ix + 1; j < iSize; ++j) {
                  if (var74.equals(lRandomGameCivsTags.get(j))) {
                     add = false;
                     break;
                  }
               }

               if (add) {
                  for(int j = ix - 1; j >= 0; --j) {
                     if (var74.equals(lRandomGameCivsTags.get(j))) {
                        add = false;
                        break;
                     }
                  }

                  if (add) {
                     lRandomGameCivsTags.set(ix, var74);
                  }
               }

               Civilization_GameData3 tempCivData;
               try {
                  try {
                     FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + (String)lRandomGameCivsTags.get(ix));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var47) {
                     FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix)));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                     int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lRandomGameCivsTags.get(ix));
                     Color tempColor = CFG.getColorMixed(
                        new Color((float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F),
                        new Color(
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     tempCivData.setR((int)(tempColor.r * 255.0F));
                     tempCivData.setG((int)(tempColor.g * 255.0F));
                     tempCivData.setB((int)(tempColor.b * 255.0F));
                  }
               } catch (GdxRuntimeException var48) {
                  try {
                     FileHandle fileCiv = Gdx.files.local("game/civilizations/" + (String)lRandomGameCivsTags.get(ix));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var46) {
                     try {
                        FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix)));
                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lRandomGameCivsTags.get(ix));
                        Color tempColor = CFG.getColorMixed(
                           new Color((float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F),
                           new Color(
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                              0.225F
                           )
                        );
                        tempCivData.setR((int)(tempColor.r * 255.0F));
                        tempCivData.setG((int)(tempColor.g * 255.0F));
                        tempCivData.setB((int)(tempColor.b * 255.0F));
                     } catch (GdxRuntimeException var45) {
                        try {
                           if (CFG.isAndroid()) {
                              try {
                                 FileHandle fileCivxx = Gdx.files
                                    .local(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix))
                                    );
                                 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                              } catch (GdxRuntimeException var43) {
                                 FileHandle fileCivxxx = Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix))
                                    );
                                 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                              }
                           } else {
                              FileHandle fileCivxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(ix))
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                           }
                        } catch (GdxRuntimeException var44) {
                           Color tempC = CFG.getRandomColor();
                           tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F));
                        }
                     }
                  }
               }

               int tCapital = 0;
               if (ix < CFG.randomGameManager.getPlayersSize() && CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID() >= 0) {
                  tCapital = CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID();
               } else {
                  tCapital = -1;
               }

               lCivs.add(new Civilization(lRandomGameCivsTags.get(ix), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, ix + 1));
               lCivs.get(ix + 1).setCivID(ix + 1);
               lCivs.get(ix + 1).setTechnologyLevel((float)(20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0F);
               lCivs.get(ix + 1).setHappiness(68 + oR.nextInt(16));
               if (tCapital >= 0) {
                  CFG.game.getProvince(lCivs.get(ix + 1).getCapitalProvinceID()).setCivID_LoadScenario(ix + 1);
               }

               lCivs.get(ix + 1).setMoney((long)CFG.game.getGameScenarios().getScenario_StartingMoney());
            }
         } catch (ClassNotFoundException var57) {
         } catch (IOException var58) {
         }
      } else {
         FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         String[] tagsSPLITED_ED = new String[0];

         try {
            FileHandle tempFileT_ED = null;
            if (CFG.isAndroid()) {
               tempFileT_ED = Gdx.files.local("game/civilizations_editor/Age_of_Civilizations");
            } else {
               tempFileT_ED = Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            }

            String tempT_ED = tempFileT_ED.readString();
            tagsSPLITED_ED = tempT_ED.split(";");
         } catch (GdxRuntimeException var42) {
         }

         List<String> nCivsTags = new ArrayList<>();
         List<Game_Scenarios.RandomGame_AoCMode> civsToAdd = new ArrayList<>();
         int i = 0;

         for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED[i])) {
               nCivsTags.add(tagsSPLITED[i]);
            }
         }

         i = 0;

         for(int iSize = tagsSPLITED_ED.length; i < iSize; ++i) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[i])) {
               nCivsTags.add(tagsSPLITED[i]);
            }
         }

         for(int ix = 0; ix < CFG.randomGameManager.getPlayersSize(); ++ix) {
            if (CFG.randomGameManager.getPlayer(ix).getTag() != null) {
               civsToAdd.add(
                  new Game_Scenarios.RandomGame_AoCMode(
                     CFG.randomGameManager.getPlayer(ix).getTag(), CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID()
                  )
               );
            } else if (CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID() >= 0) {
               int nR = oR.nextInt(nCivsTags.size());
               civsToAdd.add(new Game_Scenarios.RandomGame_AoCMode(nCivsTags.get(nR), CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID()));
               nCivsTags.remove(nR);
            }
         }

         for(int o = 0; o < civsToAdd.size(); ++o) {
            try {
               Civilization_GameData3 tempCivData;
               try {
                  try {
                     FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + civsToAdd.get(o).sTag);
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var38) {
                     FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                     int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(civsToAdd.get(o).sTag);
                     Color tempColor = CFG.getColorMixed(
                        new Color((float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F),
                        new Color(
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     tempCivData.setR((int)(tempColor.r * 255.0F));
                     tempCivData.setG((int)(tempColor.g * 255.0F));
                     tempCivData.setB((int)(tempColor.b * 255.0F));
                  }
               } catch (GdxRuntimeException var39) {
                  try {
                     FileHandle fileCiv = Gdx.files.local("game/civilizations/" + civsToAdd.get(o).sTag);
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var37) {
                     try {
                        FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag));
                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(civsToAdd.get(o).sTag);
                        Color tempColor = CFG.getColorMixed(
                           new Color((float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F),
                           new Color(
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                              0.225F
                           )
                        );
                        tempCivData.setR((int)(tempColor.r * 255.0F));
                        tempCivData.setG((int)(tempColor.g * 255.0F));
                        tempCivData.setB((int)(tempColor.b * 255.0F));
                     } catch (GdxRuntimeException var36) {
                        try {
                           if (CFG.isAndroid()) {
                              try {
                                 FileHandle fileCivxx = Gdx.files
                                    .local(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                    );
                                 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                              } catch (GdxRuntimeException var34) {
                                 FileHandle fileCivxxx = Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                    );
                                 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                              }
                           } else {
                              FileHandle fileCivxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                           }
                        } catch (GdxRuntimeException var35) {
                           Color tempC = CFG.getRandomColor();
                           tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F));
                        }
                     }
                  }
               }

               int tCapital = civsToAdd.get(o).iCapitalID;
               lCivs.add(new Civilization(civsToAdd.get(o).sTag, tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, o + 1));
               lCivs.get(o + 1).setCivID(o + 1);
               lCivs.get(o + 1).setTechnologyLevel((float)(20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0F);
               lCivs.get(o + 1).setHappiness(68 + oR.nextInt(16));
               if (tCapital >= 0) {
                  CFG.game.getProvince(lCivs.get(o + 1).getCapitalProvinceID()).setCivID_LoadScenario(o + 1);
               }

               lCivs.get(o + 1).setMoney((long)CFG.game.getGameScenarios().getScenario_StartingMoney());
            } catch (ClassNotFoundException var40) {
            } catch (IOException var41) {
            }
         }

         List<Integer> lPossibleCapitals = new ArrayList<>();

         for(int ix = 0; ix < CFG.game.getProvincesSize(); ++ix) {
            if (!CFG.game.getProvince(ix).getSeaProvince()) {
               CFG.game.getProvince(ix).setIsCapital(false);
            }
         }

         for(int ix = 0; ix < CFG.randomGameManager.getPlayersSize(); ++ix) {
            if (CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID()).setIsCapital(true);
            }
         }

         for(int ix = 0; ix < CFG.game.getProvincesSize(); ++ix) {
            if (!CFG.game.getProvince(ix).getSeaProvince() && CFG.game.getProvince(ix).getWasteland() < 0 && !CFG.game.getProvince(ix).getIsCapital()) {
               try {
                  if (Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + ix).exists()) {
                     lPossibleCapitals.add(ix);
                  }
               } catch (GdxRuntimeException var33) {
               }
            }
         }

         try {
            int extraToAddForPlayers = civsToAdd.size() - CFG.randomGameManager.getPlayersSize();
            int ix = 0;

            for(int nR = 0; ix < CFG.randomGameManager.getCivilizationsSize() + extraToAddForPlayers && lPossibleCapitals.size() > 0; ++ix) {
               try {
                  int tempCapitalID = 0;
                  int iNumOfItterations = 0;

                  while(true) {
                     int tRandID = CFG.oR.nextInt(lPossibleCapitals.size());
                     tempCapitalID = lPossibleCapitals.get(tRandID);
                     ++iNumOfItterations;
                     if (!CFG.game.getProvince(tempCapitalID).getIsCapital()) {
                        boolean found = true;

                        for(int o = 0; o < CFG.game.getProvince(tempCapitalID).getNeighboringProvincesSize(); ++o) {
                           if (CFG.game.getProvince(CFG.game.getProvince(tempCapitalID).getNeighboringProvinces(o)).getIsCapital_Just()) {
                              found = false;
                              break;
                           }
                        }

                        if (found || iNumOfItterations > 18) {
                           found = false;
                           List<String> lPossibleCapitals_Tags = new ArrayList<>();

                           try {
                              FileHandle file = Gdx.files
                                 .internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + lPossibleCapitals.get(tRandID));
                              String sOwners = file.readString();
                              String[] sRes = sOwners.split(";");

                              for(int j = 0; j < sRes.length; j += 2) {
                                 int nIdeology = CFG.ideologiesManager.getIdeologyID(sRes[j]);
                                 if (CFG.ideologiesManager.getIdeology(nIdeology).CAN_BECOME_CIVILIZED >= 0) {
                                    lPossibleCapitals_Tags.add(CFG.ideologiesManager.getRealTag(sRes[j]));
                                 } else {
                                    lPossibleCapitals_Tags.add(sRes[j]);
                                 }
                              }

                              for(int j = lPossibleCapitals_Tags.size() - 1; j >= 0; --j) {
                                 for(int k = civsToAdd.size() - 1; k >= 0; --k) {
                                    if (civsToAdd.get(k).sTag.equals(lPossibleCapitals_Tags.get(j))) {
                                       lPossibleCapitals_Tags.remove(j);
                                       break;
                                    }
                                 }
                              }

                              if (lPossibleCapitals_Tags.size() == 0) {
                                 lPossibleCapitals.remove(tRandID);
                                 continue;
                              }

                              found = true;
                           } catch (GdxRuntimeException var52) {
                              lPossibleCapitals.remove(tRandID);
                              continue;
                           }

                           if (!found) {
                              break;
                           }

                           try {
                              int nTagIDToAdd = CFG.oR.nextInt(lPossibleCapitals_Tags.size());

                              Civilization_GameData3 tempCivData;
                              try {
                                 try {
                                    FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + (String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                 } catch (GdxRuntimeException var31) {
                                    FileHandle fileCivx = Gdx.files
                                       .internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                    tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                                    int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    Color tempColor = CFG.getColorMixed(
                                       new Color(
                                          (float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F
                                       ),
                                       new Color(
                                          CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                                          CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                                          CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                                          0.225F
                                       )
                                    );
                                    tempCivData.setR((int)(tempColor.r * 255.0F));
                                    tempCivData.setG((int)(tempColor.g * 255.0F));
                                    tempCivData.setB((int)(tempColor.b * 255.0F));
                                 }
                              } catch (GdxRuntimeException var32) {
                                 try {
                                    FileHandle fileCiv = Gdx.files.local("game/civilizations/" + (String)lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                 } catch (GdxRuntimeException var30) {
                                    try {
                                       FileHandle fileCivx = Gdx.files
                                          .local("game/civilizations/" + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                       tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                                       int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lPossibleCapitals_Tags.get(nTagIDToAdd));
                                       Color tempColor = CFG.getColorMixed(
                                          new Color(
                                             (float)tempCivData.getR() / 255.0F,
                                             (float)tempCivData.getG() / 255.0F,
                                             (float)tempCivData.getB() / 255.0F,
                                             0.775F
                                          ),
                                          new Color(
                                             CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                                             CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                                             CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                                             0.225F
                                          )
                                       );
                                       tempCivData.setR((int)(tempColor.r * 255.0F));
                                       tempCivData.setG((int)(tempColor.g * 255.0F));
                                       tempCivData.setB((int)(tempColor.b * 255.0F));
                                    } catch (GdxRuntimeException var29) {
                                       try {
                                          if (CFG.isAndroid()) {
                                             try {
                                                FileHandle fileCivxx = Gdx.files
                                                   .local(
                                                      "game/civilizations_editor/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                         + "/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                   );
                                                tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                                             } catch (GdxRuntimeException var27) {
                                                FileHandle fileCivxxx = Gdx.files
                                                   .internal(
                                                      "game/civilizations_editor/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                         + "/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                   );
                                                tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                                             }
                                          } else {
                                             FileHandle fileCivxx = Gdx.files
                                                .internal(
                                                   "game/civilizations_editor/"
                                                      + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                      + "/"
                                                      + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                );
                                             tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                                          }
                                       } catch (GdxRuntimeException var28) {
                                          Color tempC = CFG.getRandomColor();
                                          tempCivData = new Civilization_GameData3(
                                             "ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F)
                                          );
                                       }
                                    }
                                 }
                              }

                              int tCapital = lPossibleCapitals.get(tRandID);
                              civsToAdd.add(new Game_Scenarios.RandomGame_AoCMode(lPossibleCapitals_Tags.get(nTagIDToAdd), tCapital));
                              int tCivID = lCivs.size();
                              lCivs.add(
                                 new Civilization(
                                    lPossibleCapitals_Tags.get(nTagIDToAdd), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, tCivID
                                 )
                              );
                              lCivs.get(tCivID).setCivID(tCivID);
                              lCivs.get(tCivID).setTechnologyLevel((float)(20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0F);
                              lCivs.get(tCivID).setHappiness(68 + oR.nextInt(16));
                              if (tCapital >= 0) {
                                 CFG.game.getProvince(lCivs.get(tCivID).getCapitalProvinceID()).setCivID_LoadScenario(tCivID);
                                 CFG.game.getProvince(tCapital).setIsCapital(true);
                              }

                              lCivs.get(tCivID).setMoney((long)CFG.game.getGameScenarios().getScenario_StartingMoney());
                              lPossibleCapitals.remove(tRandID);
                              break;
                           } catch (ClassNotFoundException var50) {
                              lPossibleCapitals.remove(tRandID);
                           } catch (IOException var51) {
                              lPossibleCapitals.remove(tRandID);
                           }
                        }
                     } else {
                        lPossibleCapitals.remove(tRandID);
                     }
                  }
               } catch (StackOverflowError var53) {
                  CFG.exceptionStack(var53);
               }
            }
         } catch (IllegalArgumentException var54) {
            CFG.exceptionStack(var54);
         } catch (IndexOutOfBoundsException var55) {
            CFG.exceptionStack(var55);
         } catch (NullPointerException var56) {
            CFG.exceptionStack(var56);
         }
      }

      return lCivs;
   }

   protected final List<Civilization> loadCivilizationsLoadGame(List<Save_Civ_GameData> nCivsData) {
      Game_Calendar.updateAge(false);
      List<Civilization> lCivs = new ArrayList<>();
      lCivs.add(CFG.game.getNeutralCivilization());
      lCivs.get(0).setCivID(0);

      for(int i = 0; i < nCivsData.size(); ++i) {
         lCivs.add(new Civilization(nCivsData.get(i), i + 1));
      }

      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      return lCivs;
   }

   protected final List<Civilization> loadCivilizations(boolean nEditor) {
      CFG.FILL_THE_MAP = true;
      Game_Calendar.CURRENT_AGEID = this.getScenarioAge(CFG.game.getScenarioID());
      List<Civilization> lCivs = new ArrayList<>();
      lCivs.add(CFG.game.getNeutralCivilization());
      lCivs.get(0).setCivID(0);
      FileHandle file;
      FileHandle fileProvince;
      if (this.isInternal.get(CFG.game.getScenarioID())) {
         file = Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
            );
         fileProvince = Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_PD"
            );
      } else {
         file = Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
            );
         fileProvince = Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_PD"
            );
      }

      try {
         Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
         this.setScenario_StartingArmyInCapitals(tempScenarioGameData.getStartingArmyInCapitals());
         this.setScenario_NeutralArmy(tempScenarioGameData.getNeutralArmy());
         this.setScenario_StartingPopulation(tempScenarioGameData.getStartingPopulation());
         this.setScenario_StartingEconomy(tempScenarioGameData.getStartingEconomy());
         this.setScenario_StartingMoney(tempScenarioGameData.getStartingMoney());
         this.setScenario_PopulationGrowthRate_Modifier(tempScenarioGameData.getPopulationGrowthRate_Modifier());
         this.setScenario_EconomyGrowthRate_Modifier(tempScenarioGameData.getEconomyGrowthRate_Modifier());
         this.setScenario_DiseasesDeathRate_Modifier(tempScenarioGameData.getDiseasesDeathRate_Modifier());
         this.setScenario_ActivePallet_TAG(tempScenarioGameData.getActivePalletOfColors_TAG());
         Game_Calendar.ENABLE_COLONIZATION = tempScenarioGameData.getColonization();
         Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = tempScenarioGameData.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
         Game_Calendar.COLONIZATION_TECH_LEVEL = tempScenarioGameData.COLONIZATION_TECH_LEVEL;

         for(int i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
            Civilization_GameData3 tempCivData;
            try {
               try {
                  FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + tempScenarioGameData.getCivTag(i));
                  tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
               } catch (GdxRuntimeException var22) {
                  FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                  tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                  int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(tempScenarioGameData.getCivTag(i));
                  Color tempColor = CFG.getColorMixed(
                     new Color((float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F),
                     new Color(
                        CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                        CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                        CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                        0.225F
                     )
                  );
                  tempCivData.setR((int)(tempColor.r * 255.0F));
                  tempCivData.setG((int)(tempColor.g * 255.0F));
                  tempCivData.setB((int)(tempColor.b * 255.0F));
               }
            } catch (GdxRuntimeException var23) {
               try {
                  FileHandle fileCiv = Gdx.files.local("game/civilizations/" + tempScenarioGameData.getCivTag(i));
                  tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
               } catch (GdxRuntimeException var21) {
                  try {
                     FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                     int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(tempScenarioGameData.getCivTag(i));
                     Color tempColor = CFG.getColorMixed(
                        new Color((float)tempCivData.getR() / 255.0F, (float)tempCivData.getG() / 255.0F, (float)tempCivData.getB() / 255.0F, 0.775F),
                        new Color(
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     tempCivData.setR((int)(tempColor.r * 255.0F));
                     tempCivData.setG((int)(tempColor.g * 255.0F));
                     tempCivData.setB((int)(tempColor.b * 255.0F));
                  } catch (GdxRuntimeException var20) {
                     try {
                        if (CFG.isAndroid()) {
                           try {
                              FileHandle fileCivxx = Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                           } catch (GdxRuntimeException var18) {
                              FileHandle fileCivxxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                           }
                        } else {
                           FileHandle fileCivxx = Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                              );
                           tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                        }
                     } catch (GdxRuntimeException var19) {
                        Color tempC = CFG.getRandomColor();
                        tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F));
                     }
                  }
               }
            }

            lCivs.add(
               new Civilization(
                  tempScenarioGameData.getCivTag(i), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tempScenarioGameData.getCivCapital(i), i + 1
               )
            );
            lCivs.get(i + 1).setCivID(i + 1);
            lCivs.get(i + 1).setTechnologyLevel(tempScenarioGameData.getTechnologyLevel(i));
            lCivs.get(i + 1).setHappiness(tempScenarioGameData.getHappiness(i));
            if (nEditor) {
               lCivs.get(i + 1)
                  .setMoney((long)(-999999 == tempScenarioGameData.getStartingMoneyCiv(i) ? -999999 : tempScenarioGameData.getStartingMoneyCiv(i)));
            } else {
               lCivs.get(i + 1)
                  .setMoney(
                     (long)(
                        -999999 == tempScenarioGameData.getStartingMoneyCiv(i)
                           ? (
                              CFG.ideologiesManager.getIdeology(lCivs.get(i + 1).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
                                 ? tempScenarioGameData.getStartingMoney() / 10
                                 : tempScenarioGameData.getStartingMoney()
                           )
                           : tempScenarioGameData.getStartingMoneyCiv(i)
                     )
                  );
            }

            if (lCivs.get(i + 1).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(lCivs.get(i + 1).getCapitalProvinceID()).setCivID_LoadScenario(i + 1);
            }
         }

         CFG.initCreateScenario_TechnologyLevelsByContinents_Civ();

         for(int i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
            CFG.addCreateScenario_TechnologyLevelsByContinents_Civ(tempScenarioGameData.getTechnologyByContinents(i));
         }

         Scenario_GameData var27 = null;
         Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
         if (scenario_GameData_Province.getProvinceOwners() != null) {
            try {
               int i = 0;

               for(int iSize = scenario_GameData_Province.getProvinceOwners().size(); i < iSize; ++i) {
                  CFG.game.getProvince(i).setCivID_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i));
               }
            } catch (IndexOutOfBoundsException var24) {
            }
         }

         scenario_GameData_Province = null;
      } catch (ClassNotFoundException var25) {
      } catch (IOException var26) {
      }

      if (!nEditor) {
         boolean foundRandomCivilization = false;
         int i = 1;

         for(int iSize = lCivs.size(); i < iSize; ++i) {
            if (lCivs.get(i).getCivTag().equals("ran")) {
               foundRandomCivilization = true;
               break;
            }
         }

         if (foundRandomCivilization) {
            FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            Random oR = new Random();
            int ix = 1;

            for(int iSize = lCivs.size(); ix < iSize; ++ix) {
               if (lCivs.get(ix).getCivTag().equals("ran")) {
                  int tempTagID;
                  do {
                     tempTagID = oR.nextInt(tagsSPLITED.length);
                  } while(tagsSPLITED[tempTagID].equals("ran") || CFG.isInTheGame(tagsSPLITED[tempTagID]));

                  FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + tagsSPLITED[tempTagID]);

                  try {
                     Civilization_GameData3 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                     lCivs.get(ix).setCivTag(tempCivData.getCivTag());
                     lCivs.get(ix).setCivName(CFG.langManager.getCiv(tempCivData.getCivTag()));
                     lCivs.get(ix).setR(tempCivData.getR());
                     lCivs.get(ix).setG(tempCivData.getG());
                     lCivs.get(ix).setB(tempCivData.getB());
                     lCivs.get(ix).disposeFlag();
                     lCivs.get(ix).loadFlag();
                     tempCivData = null;
                  } catch (ClassNotFoundException var16) {
                  } catch (IOException var17) {
                  }
               }
            }
         }
      }

      CFG.map.getMapBG().disposeMinimapOfCivilizations();

      try {
         this.sActiveScenarioTag = this.getScenarioTag(CFG.game.getScenarioID());
      } catch (IndexOutOfBoundsException var15) {
         this.sActiveScenarioTag = "";
      }

      return lCivs;
   }

   protected final void loadProvincesData(boolean nEditor) {
      FileHandle file;
      if (this.isInternal.get(CFG.game.getScenarioID())) {
         file = Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_W"
            );
      } else {
         file = Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_W"
            );
      }

      try {
         Scenario_WastelandProvinces_GameData scenario_WastelandProvinces_GameData = (Scenario_WastelandProvinces_GameData)CFG.deserialize(file.readBytes());
         int i = 0;

         for(int iSize = scenario_WastelandProvinces_GameData.getWastelandProvincesSize(); i < iSize; ++i) {
            CFG.game.getProvince(scenario_WastelandProvinces_GameData.getWastelandProvinceID(i)).setWasteland(0);
         }

         scenario_WastelandProvinces_GameData = null;
      } catch (ClassNotFoundException var6) {
      } catch (IOException var7) {
      }

      this.buildProvincePopulationAndEconomy(true, nEditor);
   }

   protected final void loadEventsData() {
      try {
         FileHandle file;
         if (this.isInternal.get(CFG.game.getScenarioID())) {
            file = Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + "events/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_E"
               );
         } else {
            file = Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + "events/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_E"
               );
         }

         try {
            CFG.eventsManager.eventsGD = (Events_GameData)CFG.deserialize(file.readBytes());
         } catch (ClassNotFoundException var3) {
         } catch (IOException var4) {
         }
      } catch (GdxRuntimeException var5) {
         CFG.eventsManager.eventsGD = new Events_GameData();
      }
   }

   protected final void loadCoresData() {
      try {
         FileHandle file;
         if (this.isInternal.get(CFG.game.getScenarioID())) {
            file = Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               );
         } else {
            file = Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               );
         }

         try {
            CFG.province_Cores_GameData = (Province_Cores_GameData)CFG.deserialize(file.readBytes());
         } catch (ClassNotFoundException var3) {
         } catch (IOException var4) {
         }
      } catch (GdxRuntimeException var5) {
         CFG.province_Cores_GameData = new Province_Cores_GameData();
      }
   }

   protected final void loadCoresData_Editor() {
      try {
         FileHandle file;
         if (this.isInternal.get(CFG.game.getScenarioID())) {
            file = Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               );
         } else {
            file = Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               );
         }

         try {
            CFG.province_Cores_GameData = (Province_Cores_GameData)CFG.deserialize(file.readBytes());

            for(int i = 0; i < CFG.province_Cores_GameData.getProvincesSize(); ++i) {
               CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID).buildProvinceCore();

               for(int j = 0; j < CFG.province_Cores_GameData.lProvinces.get(i).lCores.size(); ++j) {
                  CFG.game
                     .getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID)
                     .getCore()
                     .addNewCore(CFG.province_Cores_GameData.lProvinces.get(i).lCores.get(j).iCivID, Game_Calendar.TURN_ID);
               }
            }
         } catch (ClassNotFoundException var4) {
         } catch (IOException var5) {
         }
      } catch (GdxRuntimeException var6) {
         CFG.province_Cores_GameData = new Province_Cores_GameData();
      }
   }

   protected final void buildDiplomacy() {
      CFG.game.buildAlliances();
      CFG.game.buildWars();

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).buildDiplomacy(true);
      }
   }

   protected final void loadDiplomacyData(boolean editor) {
      this.buildDiplomacy();
      FileHandle file;
      if (this.isInternal.get(CFG.game.getScenarioID())) {
         file = Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_D"
            );
      } else {
         file = Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_D"
            );
      }

      try {
         Scenario_GameData_Diplomacy2 scenario_GameData_Diplomacy = (Scenario_GameData_Diplomacy2)CFG.deserialize(file.readBytes());

         for(int i = 0; i < scenario_GameData_Diplomacy.getVassals().size(); ++i) {
            if (scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID() < CFG.game.getCivsSize()) {
               CFG.game
                  .getCiv(scenario_GameData_Diplomacy.getVassals().get(i).getCivID())
                  .setPuppetOfCivID(scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID());
            }
         }

         for(int i = 0; i < scenario_GameData_Diplomacy.getAlliances().size(); ++i) {
            Gdx.app.log("AoC", scenario_GameData_Diplomacy.getAlliances().get(i).getName());
            CFG.game.addAlliance(scenario_GameData_Diplomacy.getAlliances().get(i).getName());

            for(int j = 0; j < scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().size(); ++j) {
               CFG.game.getAlliance(i + 1).addCivilization(scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().get(j));
               CFG.game.getCiv(scenario_GameData_Diplomacy.getAlliances().get(i).getCivs().get(j)).setAllianceID(i + 1);
            }

            CFG.game
               .getAlliance(i + 1)
               .setColorOfAlliance(
                  new Color_GameData(
                     scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getR(),
                     scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getG(),
                     scenario_GameData_Diplomacy.getAlliances().get(i).getColor().getB()
                  )
               );
         }

         if (editor) {
            for(int i = 0; i < scenario_GameData_Diplomacy.getRelations().size(); ++i) {
               CFG.game
                  .setCivRelation_OfCivB(
                     scenario_GameData_Diplomacy.getRelations().get(i).getCivA(),
                     scenario_GameData_Diplomacy.getRelations().get(i).getCivB(),
                     (float)scenario_GameData_Diplomacy.getRelations().get(i).getValue()
                  );
            }
         } else {
            for(int i = 0; i < scenario_GameData_Diplomacy.getRelations().size(); ++i) {
               CFG.game
                  .setCivRelation_OfCivB(
                     scenario_GameData_Diplomacy.getRelations().get(i).getCivA(),
                     scenario_GameData_Diplomacy.getRelations().get(i).getCivB(),
                     (float)scenario_GameData_Diplomacy.getRelations().get(i).getValue()
                  );
            }

            for(int i = 1; i < CFG.game.getCivsSize() - 1; ++i) {
               for(int j = i + 1; j < CFG.game.getCivsSize(); ++j) {
                  if ((int)CFG.game.getCivRelation_OfCivB(i, j) == 0) {
                     CFG.game.setCivRelation_OfCivB(i, j, (float)(CFG.oR.nextInt(20) - 10));
                  }

                  if ((int)CFG.game.getCivRelation_OfCivB(j, i) == 0) {
                     CFG.game.setCivRelation_OfCivB(j, i, (float)(CFG.oR.nextInt(20) - 10));
                  }
               }
            }
         }

         for(int i = 0; i < scenario_GameData_Diplomacy.getMilitaryAccess().size(); ++i) {
            CFG.game
               .setMilitaryAccess(
                  scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getCivA(),
                  scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getCivB(),
                  scenario_GameData_Diplomacy.getMilitaryAccess().get(i).getValue()
               );
         }

         for(int i = 0; i < scenario_GameData_Diplomacy.getGuarantee().size(); ++i) {
            CFG.game
               .setGuarantee(
                  scenario_GameData_Diplomacy.getGuarantee().get(i).getCivA(),
                  scenario_GameData_Diplomacy.getGuarantee().get(i).getCivB(),
                  scenario_GameData_Diplomacy.getGuarantee().get(i).getValue()
               );
         }

         for(int i = 0; i < scenario_GameData_Diplomacy.getPacts().size(); ++i) {
            CFG.game
               .setCivNonAggressionPact(
                  scenario_GameData_Diplomacy.getPacts().get(i).getCivA(),
                  scenario_GameData_Diplomacy.getPacts().get(i).getCivB(),
                  scenario_GameData_Diplomacy.getPacts().get(i).getValue()
               );
         }

         for(int i = 0; i < scenario_GameData_Diplomacy.getDefensivePacts().size(); ++i) {
            CFG.game
               .setDefensivePact(
                  scenario_GameData_Diplomacy.getDefensivePacts().get(i).getCivA(),
                  scenario_GameData_Diplomacy.getDefensivePacts().get(i).getCivB(),
                  scenario_GameData_Diplomacy.getDefensivePacts().get(i).getValue()
               );
         }

         for(int i = 0; i < scenario_GameData_Diplomacy.getTruces().size(); ++i) {
            CFG.game
               .setCivTruce(
                  scenario_GameData_Diplomacy.getTruces().get(i).getCivA(),
                  scenario_GameData_Diplomacy.getTruces().get(i).getCivB(),
                  scenario_GameData_Diplomacy.getTruces().get(i).getValue()
               );
         }

         scenario_GameData_Diplomacy = null;
      } catch (ClassNotFoundException var6) {
         CFG.toast.setInView("Error - Diplomacy Data");
      } catch (IOException var7) {
      } catch (GdxRuntimeException var8) {
      } catch (NullPointerException var9) {
      }

      DiplomacyManager.buildFriendlyCivs();
   }

   protected final void loadArmiesData() {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).resetArmies_NewGame(0);
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
            if (CFG.game.getProvince(i).getCivID() == 0) {
               CFG.game.getProvince(i).updateArmy(this.getScenario_NeutralArmy());
            } else if (CFG.game.getProvince(i).getIsCapital()) {
               if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                  CFG.game.getProvince(i).updateArmy(this.getScenario_StartingArmyInCapitals() / 10);
               } else {
                  CFG.game.getProvince(i).updateArmy(this.getScenario_StartingArmyInCapitals());
               }
            }
         }
      }

      try {
         FileHandle file;
         if (this.isInternal.get(CFG.game.getScenarioID())) {
            file = Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_A"
               );
         } else {
            file = Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_A"
               );
         }

         try {
            Scenario_GameData_Armies scenario_GameData_Armies = (Scenario_GameData_Armies)CFG.deserialize(file.readBytes());
            int i = 0;

            for(int iSize = scenario_GameData_Armies.lArmies.size(); i < iSize; ++i) {
               try {
                  if (CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getWasteland() < 0
                     && (
                        CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()
                              == scenario_GameData_Armies.lArmies.get(i).getCivID()
                           || CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getSeaProvince()
                           || CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(i).getCivID()).getAllianceID() > 0
                              && CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(i).getCivID()).getAllianceID() > 0
                                 == CFG.game.getCiv(CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()).getAllianceID()
                                    > 0
                           || CFG.game.getCiv(CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()).getPuppetOfCivID()
                              == scenario_GameData_Armies.lArmies.get(i).getCivID()
                           || CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()
                              == CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(i).getCivID()).getPuppetOfCivID()
                           || CFG.game
                                 .getMilitaryAccess(
                                    scenario_GameData_Armies.lArmies.get(i).getCivID(),
                                    CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID()).getCivID()
                                 )
                              > 0
                     )) {
                     CFG.game
                        .getProvince(scenario_GameData_Armies.lArmies.get(i).getProvinceID())
                        .updateArmy(scenario_GameData_Armies.lArmies.get(i).getCivID(), scenario_GameData_Armies.lArmies.get(i).getArmy());
                  }
               } catch (IndexOutOfBoundsException var6) {
               } catch (NullPointerException var7) {
               }
            }

            scenario_GameData_Armies = null;
         } catch (ClassNotFoundException var8) {
         } catch (IOException var9) {
         } catch (GdxRuntimeException var10) {
         }
      } catch (GdxRuntimeException var11) {
      }
   }

   protected final void buildProvincePopulationAndEconomy(boolean loadCoresData, boolean nEditor) {
      Random oR = new Random();
      CFG.game.getCiv(0).setTechnologyLevel(0.1F);

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            CFG.game.getProvince(i).getPopulationData().clearData();
            CFG.game.getProvince(i).setEconomy(0);
            CFG.game.getProvince(i).iIncome_Taxation = 1.0F;
            CFG.game.getProvince(i).iIncome_Production = 1.0F;
            CFG.game.getProvince(i).iAdministrationCost = 0.0F;
            CFG.game.getProvince(i).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
         }

         CFG.game.getProvince(i).setIsPartOfHolyRomanEmpire(false);
         CFG.game.getProvince(i).saveProvinceData.resetData();
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).buildProvinceCore();
      }

      if (loadCoresData) {
         CFG.game.getGameScenarios().loadCoresData();

         for(int i = 0; i < CFG.province_Cores_GameData.getProvincesSize(); ++i) {
            try {
               if (!CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID).getSeaProvince()
                  && CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID).getWasteland() < 0
                  && CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID).getCivID() > 0) {
                  for(int j = 0; j < CFG.province_Cores_GameData.lProvinces.get(i).lCores.size(); ++j) {
                     CFG.game
                        .getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID)
                        .getCore()
                        .addNewCore(CFG.province_Cores_GameData.lProvinces.get(i).lCores.get(j).iCivID, 1);
                  }
               }
            } catch (IndexOutOfBoundsException var8) {
            }
         }
      }

      if (CFG.province_Cores_GameData == null) {
         CFG.province_Cores_GameData = new Province_Cores_GameData();
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            float tDevelopment = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTechnologyLevel();
            tDevelopment = tDevelopment
                  * (1.0F - CFG.gameAges.getAge_StartingDevelopment(Game_Calendar.CURRENT_AGEID))
                  * (CFG.game.getProvince(i).getIsCapital() ? 0.7646841F : 0.5746985F)
               + tDevelopment * CFG.gameAges.getAge_StartingDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(i).getGrowthRate_Population();
            if (CFG.game.getProvince(i).getCivID() > 0) {
               tDevelopment = tDevelopment
                  * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                     CFG.game.getProvince(i).getCivID() - 1, CFG.game.getProvince(i).getRegion()
                  )
                  / 100.0F;
            }

            tDevelopment *= 0.875F
               + (float)CFG.oR.nextInt(2000) / 10000.0F
               + CFG.terrainTypesManager.getBaseDevelopmentModifier(CFG.game.getProvince(i).getTerrainTypeID());
            CFG.game.getProvince(i).setDevelopmentLevel(tDevelopment);
            if (CFG.game.getProvince(i).getCivID() == 0) {
               CFG.game
                  .getProvince(i)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(i).getCivID(),
                     (int)(
                           (float)this.getScenario_StartingPopulation()
                              * 0.18275F
                              * CFG.game.getProvince(i).getGrowthRate_Population()
                              * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                        )
                        + oR.nextInt(
                              1
                                 + (int)Math.ceil(
                                    (double)(
                                       (float)this.getScenario_StartingPopulation()
                                          * ((float)oR.nextInt(25) / 100.0F)
                                          * CFG.game.getProvince(i).getGrowthRate_Population()
                                          * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                                    )
                                 )
                           )
                           / 4
                  );
               CFG.game
                  .getProvince(i)
                  .setEconomy(
                     (int)(
                           (float)this.getScenario_StartingEconomy()
                              * (0.05275F + (float)CFG.game.getProvince(i).getNeighboringSeaProvincesSize() * 0.0015F)
                              * CFG.game.getProvince(i).getGrowthRate_Population()
                              * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                        )
                        + oR.nextInt(
                           1
                              + (int)Math.ceil(
                                 (double)(
                                    (float)this.getScenario_StartingEconomy()
                                       * ((float)oR.nextInt(10) / 100.0F)
                                       * CFG.game.getProvince(i).getGrowthRate_Population()
                                       * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                                       * CFG.game.getProvince(i).getDevelopmentLevel()
                                 )
                              )
                        )
                  );
               CFG.game.getProvince(i).setHappiness(0.48F + (float)oR.nextInt(2400) / 10000.0F);
            } else {
               if (CFG.game.getProvince(i).getCore().getCivsSize() < 1) {
                  CFG.game
                     .getProvince(i)
                     .getPopulationData()
                     .setPopulationOfCivID(
                        CFG.game.getProvince(i).getCivID(),
                        (int)(
                           (float)(
                                 (int)(
                                       (float)this.getScenario_StartingPopulation()
                                          * (0.85F + (CFG.game.getProvince(i).getIsCapital() ? 0.05F : 0.0F))
                                          * CFG.game.getProvince(i).getGrowthRate_Population()
                                          * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                                    )
                                    + oR.nextInt(
                                       1
                                          + (int)Math.ceil(
                                             (double)(
                                                (float)this.getScenario_StartingPopulation()
                                                   * 0.15F
                                                   * CFG.game.getProvince(i).getGrowthRate_Population()
                                                   * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                                             )
                                          )
                                    )
                              )
                              * (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED
                                       >= 0
                                    ? (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID() == i ? 0.4F : 0.275F)
                                    : 1.0F
                              )
                              * (
                                 0.725F
                                    + 0.275F
                                       * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                                          CFG.game.getProvince(i).getCivID() - 1, CFG.game.getProvince(i).getRegion()
                                       )
                                       / 100.0F
                              )
                        )
                     );
               } else {
                  int tempPop = (int)(
                     (float)(
                           (int)(
                                 (float)this.getScenario_StartingPopulation()
                                    * (0.85F + (CFG.game.getProvince(i).getIsCapital() ? 0.0725F : 0.0F))
                                    * (
                                       CFG.game.getProvince(i).getIsCapital()
                                          ? Math.max(0.2675F, CFG.game.getProvince(i).getGrowthRate_Population())
                                          : CFG.game.getProvince(i).getGrowthRate_Population()
                                    )
                                    * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                              )
                              + oR.nextInt(
                                 1
                                    + (int)Math.ceil(
                                       (double)(
                                          (float)this.getScenario_StartingPopulation()
                                             * 0.15F
                                             * CFG.game.getProvince(i).getGrowthRate_Population()
                                             * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                                       )
                                    )
                              )
                        )
                        * (
                           CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
                              ? (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID() == i ? 0.4F : 0.275F)
                              : 1.0F
                        )
                        * (
                           0.725F
                              + 0.275F
                                 * (float)CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                                    CFG.game.getProvince(i).getCivID() - 1, CFG.game.getProvince(i).getRegion()
                                 )
                                 / 100.0F
                        )
                  );
                  CFG.game.getProvince(i).getPopulationData().clearData();

                  for(int j = 0; j < CFG.game.getProvince(i).getCore().getCivsSize(); ++j) {
                     CFG.game
                        .getProvince(i)
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(i).getCore().getCivID(j),
                           (int)((float)tempPop * CFG.province_Cores_GameData.getPercOfPop(i, CFG.game.getProvince(i).getCore().getCivID(j)))
                        );
                  }

                  for(int j = 0; j < CFG.game.getProvince(i).getCore().getCivsSize() && j < 1; ++j) {
                     if (CFG.province_Cores_GameData.getPercOfPop(i, CFG.game.getProvince(i).getCore().getCivID(j)) < 0.18F) {
                        CFG.game.getProvince(i).getCore().removeCore(CFG.game.getProvince(i).getCore().getCivID(j));
                     }
                  }
               }

               CFG.game
                  .getProvince(i)
                  .setEconomy(
                     (int)(
                        (float)(
                              (int)(
                                 (float)this.getScenario_StartingEconomy()
                                    * (
                                       CFG.game.getProvince(i).getDevelopmentLevel() * 1.064498F
                                          + (float)CFG.game.getProvince(i).getNeighboringSeaProvincesSize() * 0.035F
                                    )
                                    * CFG.game.getProvince(i).getGrowthRate_Population()
                                    * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                              )
                           )
                           + (float)oR.nextInt(
                                 1
                                    + Math.max(
                                       (int)Math.ceil(
                                          (double)(
                                             (float)this.getScenario_StartingEconomy()
                                                * (1.0F - CFG.game.getProvince(i).getDevelopmentLevel())
                                                * CFG.game.getProvince(i).getGrowthRate_Population()
                                                * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(i).getTerrainTypeID()))
                                                * CFG.game.getProvince(i).getDevelopmentLevel()
                                          )
                                       ),
                                       0
                                    )
                              )
                              * (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED
                                       >= 0
                                    ? (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCapitalProvinceID() == i ? 0.95F : 0.725F)
                                    : 1.0F
                              )
                     )
                  );
               CFG.game.getProvince(i).setHappiness((float)(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getHappiness() + oR.nextInt(12) - 6) / 100.0F);
            }

            for(int j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); ++j) {
               if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() > 0
                  && CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() != CFG.game.getProvince(i).getCivID()) {
                  CFG.game
                     .getProvince(i)
                     .getPopulationData()
                     .setPopulationOfCivID(
                        CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID(),
                        (int)((float)CFG.game.getProvince(i).getPopulationData().getPopulation() * (0.00874F + (float)CFG.oR.nextInt(345) / 10000.0F))
                     );
               }
            }
         }
      }

      if (!nEditor) {
         for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0
               && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
               && CFG.game.getCiv(i).getCapitalProvinceID() >= 0) {
               for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvincesSize(); ++j) {
                  if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j)).getWasteland() < 0
                     && (
                        CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j)).getCivID() == 0
                           || CFG.ideologiesManager
                                 .getIdeology(
                                    CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j))
                                             .getCivID()
                                       )
                                       .getIdeologyID()
                                 )
                                 .CAN_BECOME_CIVILIZED
                              >= 0
                     )) {
                     CFG.game
                        .getProvince(CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getNeighboringProvinces(j))
                        .getCore()
                        .addNewCore(i, 1);
                  }
               }

               int tRan = CFG.oR.nextInt(10);

               for(int a = 0; a < tRan; ++a) {
                  CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).getCore().increaseOwnership(i, CFG.game.getCiv(i).getCapitalProvinceID());
               }
            }
         }
      }

      CFG.province_Cores_GameData = null;
      CFG.game_NextTurnUpdate.updateCities();
   }

   protected final void disableFillTheMap() {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getIsCapital()) {
            CFG.game.getProvince(i).setCivID_LoadScenario(0);
            CFG.game.getProvince(i).setCivRegionID(-1);
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).clearProvinces_FillTheMap(CFG.game.getCiv(i).getNumOfProvinces() > 0);
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
            CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(false, i);
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getProvince(CFG.game.getCiv(i).getCapitalProvinceID()).updateProvinceBorder();
      }

      CFG.game.buildCivilizationsRegions();
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
   }

   protected final void enableFillTheMap() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).clearProvinces_FillTheMap(false);
      }

      FileHandle file = Gdx.files
         .internal(
            "map/"
               + CFG.map.getFile_ActiveMap_Path()
               + "scenarios/"
               + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
               + "/"
               + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
         );
      FileHandle fileProvince = Gdx.files
         .internal(
            "map/"
               + CFG.map.getFile_ActiveMap_Path()
               + "scenarios/"
               + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
               + "/"
               + (String)this.lScenarios_TagsList.get(CFG.game.getScenarioID())
               + "_PD"
         );

      try {
         Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());

         for(int i = 0; i < tempScenarioGameData.getCivSize(); ++i) {
            CFG.game.getCiv(i + 1).setCapitalProvinceID(tempScenarioGameData.getCivCapital(i));
         }

         Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
         if (scenario_GameData_Province.getProvinceOwners() != null) {
            int i = 0;

            for(int iSize = scenario_GameData_Province.getProvinceOwners().size(); i < iSize; ++i) {
               CFG.game.getProvince(i).setCivID_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i));
               CFG.game.getCiv(scenario_GameData_Province.getProvinceOwners().get(i)).addProvince_Just(i);
            }
         }

         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            for(int j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
               CFG.game
                  .getProvince(i)
                  .getProvinceBordersLandByLand()
                  .get(j)
                  .setIsCivilizationBorder(
                     CFG.game.getProvince(i).getCivID()
                        != CFG.game.getProvince(CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(),
                     i
                  );
            }
         }

         CFG.game.buildCivilizationsRegions();
      } catch (ClassNotFoundException var7) {
      } catch (IOException var8) {
      } catch (GdxRuntimeException var9) {
      }

      CFG.map.getMapBG().disposeMinimapOfCivilizations();
   }

   protected final void editScenario(int iID) {
      Game_Calendar.TURN_ID = 1;
      CFG.game.setScenarioID(iID);
      CFG.game.loadScenario(true);
      CFG.game.getGameScenarios().loadCoresData_Editor();
      CFG.CREATE_SCENARIO_GAME_DATA_TAG = this.lScenarios_TagsList.get(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_NAME = this.getScenarioName(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_AUTHOR = this.getScenarioAuthor(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_AGE = this.getScenarioAge(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_WIKI = this.getScenarioWiki(CFG.game.getScenarioID());
      Game_Calendar.currentYear = this.getScenarioYear(CFG.game.getScenarioID());
      Game_Calendar.currentMonth = this.getScenarioMonth(CFG.game.getScenarioID());
      Game_Calendar.currentDay = this.getScenarioDay(CFG.game.getScenarioID());
   }

   protected final int getNumOfCivs(int i) {
      return this.lScenarios_CivNum.get(i);
   }

   protected final void setNumOfCivs(int i, int nNumCivs) {
      try {
         this.lScenarios_CivNum.set(i, nNumCivs);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final String getScenarioName(int i) {
      return this.lScenarios_Names.get(i);
   }

   protected final void setScenarioName(int i, String nName) {
      try {
         this.lScenarios_Names.set(i, nName);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final String getScenarioWiki(int i) {
      return this.lScenarios_Wikis.get(i);
   }

   protected final String getScenarioAuthor(int i) {
      return this.lScenarios_Authors.get(i);
   }

   protected final void setScenarioAuthor(int i, String nAuthor) {
      try {
         this.lScenarios_Authors.set(i, nAuthor);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final String getScenarioTag(int i) {
      return this.lScenarios_TagsList.get(i);
   }

   protected final int getScenarioAge(int i) {
      return this.lScenarios_Age.get(i);
   }

   protected final void setScenarioAge(int i, int nAge) {
      try {
         this.lScenarios_Age.set(i, nAge);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final int getScenarioYear(int i) {
      return this.lScenarios_Year.get(i);
   }

   protected final int getScenarioMonth(int i) {
      return this.lScenarios_Month.get(i);
   }

   protected final int getScenarioDay(int i) {
      return this.lScenarios_Day.get(i);
   }

   protected final void setScenarioDay(int i, int nDay) {
      try {
         this.lScenarios_Day.set(i, nDay);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final void setScenarioMonth(int i, int nMonth) {
      try {
         this.lScenarios_Month.set(i, nMonth);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final void setScenarioYear(int i, int nYear) {
      try {
         this.lScenarios_Year.set(i, nYear);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected final int getScenario_StartingArmyInCapitals() {
      return this.iScenario_StartingArmyInCapitals;
   }

   protected final void setScenario_StartingArmyInCapitals(int iScenario_StartingArmyInCapitals) {
      this.iScenario_StartingArmyInCapitals = iScenario_StartingArmyInCapitals;
   }

   protected final float getScenario_PopulationGrowthRate_Modifier() {
      return this.iScenario_PopulationGrowthRate_Modifier;
   }

   protected final void setScenario_PopulationGrowthRate_Modifier(float iScenario_PopulationGrowthRate_Modifier) {
      this.iScenario_PopulationGrowthRate_Modifier = iScenario_PopulationGrowthRate_Modifier;
   }

   protected final float getScenario_EconomyGrowthRate_Modifier() {
      return this.iScenario_EconomyGrowthRate_Modifier;
   }

   protected final void setScenario_EconomyGrowthRate_Modifier(float iScenario_EconomyGrowthRate_Modifier) {
      this.iScenario_EconomyGrowthRate_Modifier = iScenario_EconomyGrowthRate_Modifier;
   }

   protected final float getScenario_DiseasesDeathRate_Modifier() {
      return this.iScenario_DiseasesDeathRate_Modifier;
   }

   protected final void setScenario_DiseasesDeathRate_Modifier(float iScenario_DiseasesDeathRate_Modifier) {
      this.iScenario_DiseasesDeathRate_Modifier = iScenario_DiseasesDeathRate_Modifier;
   }

   protected final int getScenario_NeutralArmy() {
      return this.iScenario_NeutralArmy;
   }

   protected final void setScenario_NeutralArmy(int iScenario_NeutralArmy) {
      this.iScenario_NeutralArmy = iScenario_NeutralArmy;
   }

   protected final int getScenario_StartingPopulation() {
      return this.iScenario_StartingPopulation;
   }

   protected final void setScenario_StartingPopulation(int iScenario_StartingPopulation) {
      this.iScenario_StartingPopulation = iScenario_StartingPopulation;
   }

   protected final int getScenario_StartingEconomy() {
      return this.iScenario_StartingEconomy;
   }

   protected final void setScenario_StartingEconomy(int iScenario_StartingEconomy) {
      this.iScenario_StartingEconomy = iScenario_StartingEconomy;
   }

   protected final int getScenario_StartingMoney() {
      return this.iScenario_StartingMoney;
   }

   protected final void setScenario_StartingMoney(int iScenario_StartingMoney) {
      this.iScenario_StartingMoney = iScenario_StartingMoney;
   }

   protected final String getScenario_ActivePallet_TAG() {
      return this.sScenario_ActivePallet_TAG;
   }

   public void setScenario_ActivePallet_TAG(String sScenario_ActivePallet_TAG) {
      this.sScenario_ActivePallet_TAG = sScenario_ActivePallet_TAG;
   }

   protected final boolean getScenarioIsInternal(int i) {
      return this.isInternal.get(i);
   }

   class RandomGame_AoCMode {
      protected String sTag;
      protected int iCapitalID = -1;

      protected RandomGame_AoCMode(String sTag) {
         this.sTag = sTag;
         this.iCapitalID = -1;
      }

      protected RandomGame_AoCMode(String sTag, int iCapitalID) {
         this.sTag = sTag;
         this.iCapitalID = iCapitalID;
      }
   }
}
