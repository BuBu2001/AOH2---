package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Commands {
   protected static final int CONSOLE_LIMIT = 650;
   protected static List<String> sConsole = new ArrayList<>();
   protected static List<Point_XY> lShit = new ArrayList<>();
   protected static long lShitTime = 0L;

   protected static final void addMessage(String nMess) {
      sConsole.add(nMess);
      if (sConsole.size() > 650) {
         sConsole.remove(0);
      }
   }

   protected static final void execute(String nCommand) {
      if (nCommand.length() != 0) {
         addMessage("");
         addMessage("#" + nCommand);
         String[] tempCommand = nCommand.toLowerCase().split(" ");

         try {
            label745:
            if (tempCommand.length > 0) {
               if (tempCommand[0].equals("console")) {
                  CFG.menuManager.setVisible_InGame_FlagAction_Console(!CFG.menuManager.getVisible_InGame_FlagAction_Console());
                  if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                     CFG.toast.setInView("Hello");
                  }

                  return;
               }

               if (tempCommand[0].equals("info")) {
                  addMessage("FramesPerSecond: " + Gdx.graphics.getFramesPerSecond());
                  addMessage("Width: " + Gdx.graphics.getWidth());
                  addMessage("Height: " + Gdx.graphics.getHeight());
                  addMessage("PpiX: " + Gdx.graphics.getPpiX());
                  addMessage("PpiY: " + Gdx.graphics.getPpiY());
                  addMessage("Density: " + Gdx.graphics.getDensity());
                  addMessage("XHDPI: " + CFG.XHDPI);
                  addMessage("XXHDPI: " + CFG.XXHDPI);
                  addMessage("XXXHDPI: " + CFG.XXXHDPI);
                  addMessage("XXXXHDPI: " + CFG.XXXXHDPI);
                  return;
               }

               if (tempCommand[0].equals("debug")) {
                  CFG.DEBUG_MODE = !CFG.DEBUG_MODE;
                  addMessage(
                     CFG.langManager
                        .get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled")))
                  );
                  CFG.toast
                     .setInView(
                        CFG.langManager
                           .get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled")))
                     );
                  return;
               }

               if (tempCommand[0].equals("neutral")) {
                  for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                     if (CFG.game.getProvince(i).getWasteland() < 0 && CFG.game.getProvince(i).getCivID() == 0 && !CFG.game.getProvince(i).getSeaProvince()) {
                        CFG.game.setActiveProvinceID(i);
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                        break;
                     }
                  }

                  return;
               }

               if (tempCommand[0].equals("center")) {
                  if (tempCommand.length > 1) {
                     try {
                        int tempID = Integer.parseInt(tempCommand[1]);
                        if (tempID < CFG.game.getProvincesSize()) {
                           CFG.map.getMapCoordinates().centerToProvinceID(tempID);
                           CFG.game.setActiveProvinceID(tempID);
                           CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                        } else {
                           IllegalCommand();
                        }

                        return;
                     } catch (IllegalArgumentException var8) {
                        IllegalCommand();
                     } catch (IndexOutOfBoundsException var9) {
                        IllegalCommand();
                     }
                  } else {
                     CFG.map.getMapScroll().stopScrollingTheMap();
                     CFG.map.getMapScale().setCurrentScale(Map_Scale.MINSCALE);
                     CFG.map
                        .getMapCoordinates()
                        .setNewPosX(-((int)((float)(CFG.map.getMapBG().getWidth() / 2) - (float)CFG.GAME_WIDTH / Map_Scale.MINSCALE / 2.0F)));
                     CFG.map
                        .getMapCoordinates()
                        .setNewPosY(-((int)((float)(CFG.map.getMapBG().getHeight() / 2) - (float)CFG.GAME_HEIGHT / Map_Scale.MINSCALE / 2.0F)));
                  }

                  return;
               }

               if (tempCommand[0].equals("centerciv")) {
                  if (tempCommand.length > 1) {
                     try {
                        int tempID = Integer.parseInt(tempCommand[1]);
                        if (tempID < CFG.game.getCivsSize() && tempID > 0) {
                           CFG.map.getMapCoordinates().centerToCivilizationBox(tempID, true);
                           CFG.toast.setInView(CFG.game.getCiv(tempID).getCivName());
                        }
                     } catch (IllegalArgumentException var11) {
                        for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
                           if (tempCommand[1].equals(CFG.game.getCiv(i).getCivName()) || tempCommand[1].equals(CFG.game.getCiv(i).getCivTag())) {
                              CFG.map.getMapCoordinates().centerToCivilizationBox(i, true);
                              CFG.toast.setInView(CFG.game.getCiv(i).getCivName());
                              return;
                           }
                        }

                        IllegalCommand();
                     } catch (IndexOutOfBoundsException var12) {
                        IllegalCommand();
                     }
                  } else {
                     IllegalCommand();
                  }

                  return;
               }

               if (tempCommand[0].equals("scale")) {
                  if (tempCommand.length > 1) {
                     try {
                        tempCommand[1] = tempCommand[1].replace(',', '.');
                        float tempS = Float.parseFloat(tempCommand[1]);
                        CFG.map.getMapScale().setCurrentScale(tempS);
                        return;
                     } catch (IllegalArgumentException var10) {
                        IllegalCommand();
                     }
                  } else {
                     CFG.map.getMapScale().setCurrentScale(1.0F);
                  }

                  return;
               }

               if (!tempCommand[0].equals("close") && !tempCommand[0].equals("bye")) {
                  if (tempCommand[0].equals("fps")) {
                     AoCGame.drawFPS = !AoCGame.drawFPS;
                     return;
                  }

                  if (!tempCommand[0].equals("hi") && !tempCommand[0].equals("hello")) {
                     if (!tempCommand[0].equals("spin") && !tempCommand[0].equals("iss") && !tempCommand[0].equals("wheee") && !tempCommand[0].equals("whee")) {
                        if (!tempCommand[0].equals("help") && !tempCommand[0].equals("commands")) {
                           if (!tempCommand[0].equals("party")
                              && !tempCommand[0].equals("fuck")
                              && !tempCommand[0].equals("fuk")
                              && !tempCommand[0].equals("flags")) {
                              if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                 break label745;
                              }

                              if (tempCommand[0].equals("clear")) {
                                 sConsole.clear();
                                 lShit.clear();
                                 return;
                              }

                              if (!tempCommand[0].equals("Drew Durnil")
                                 && !tempCommand[0].equals("drew durnil")
                                 && !tempCommand[0].equals("drewdurnil")
                                 && !tempCommand[0].equals("drew")
                                 && !tempCommand[0].equals("Drew")
                                 && !tempCommand[0].equals("Durnil")
                                 && !tempCommand[0].equals("durnil")
                                 && !tempCommand[0].equals("observe")
                                 && !tempCommand[0].equals("noob")
                                 && !tempCommand[0].equals("Spectator")
                                 && !tempCommand[0].equals("spectator")) {
                                 if (!tempCommand[0].equals("civs") && !tempCommand[0].equals("tags")) {
                                    if (tempCommand[0].equals("civ")) {
                                       if (CFG.game.getActiveProvinceID() >= 0
                                          && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                                          addMessage(
                                             "CIV ID: "
                                                + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                + ", TAG: "
                                                + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag()
                                                + ", "
                                                + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                          );
                                       } else {
                                          IllegalCommand();
                                          CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                          addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                          addMessage("");
                                       }

                                       return;
                                    }

                                    if (tempCommand[0].equals("province")) {
                                       if (CFG.game.getActiveProvinceID() >= 0
                                          && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                                          addMessage(
                                             "PROVINCE ID: "
                                                + CFG.game.getActiveProvinceID()
                                                + ", CIV TAG"
                                                + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag()
                                          );
                                          addMessage(
                                             "POPULATION: "
                                                + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation()
                                                + ", ECONOMY"
                                                + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy()
                                          );
                                       } else {
                                          IllegalCommand();
                                          CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                          addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                          addMessage("");
                                       }

                                       return;
                                    }

                                    if (tempCommand[0].equals("showids")) {
                                       CFG.game.buildDrawArmy_ShowIDs();
                                       CFG.toast.setInView("showarmy");
                                       CFG.toast.setTimeInView(4500);
                                       addMessage(CFG.langManager.get("Disable") + ": showarmy");
                                       return;
                                    }

                                    if (tempCommand[0].equals("showarmy")) {
                                       CFG.game.buildDrawArmy();
                                       return;
                                    }

                                    if (!CFG.SPECTATOR_MODE && tempCommand[0].equals("addplayer")) {
                                       if (CFG.game.getActiveProvinceID() >= 0
                                          && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                                          && !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getControlledByPlayer()) {
                                          if (CFG.SPECTATOR_MODE) {
                                             CFG.SPECTATOR_MODE = false;
                                             if (CFG.game.getPlayersSize() == 1) {
                                                CFG.game.removePlayer(0);
                                             }
                                          }

                                          CFG.game.addPlayer(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                          CFG.gameAction.buildFogOfWar(CFG.game.getPlayersSize() - 1);
                                          if (CFG.FOG_OF_WAR == 2) {
                                             CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).buildMetProvincesAndCivs();
                                          }

                                          CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).loadPlayersFlag();
                                          addMessage(
                                             CFG.langManager.get("Added")
                                                + ": "
                                                + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                          );
                                          return;
                                       }

                                       IllegalCommand();
                                       CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                       addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                       addMessage("");
                                       break label745;
                                    }

                                    if (tempCommand[0].equals("addciv")) {
                                       if (tempCommand.length <= 1) {
                                          IllegalCommand();
                                       } else if (CFG.game.getActiveProvinceID() >= 0
                                          && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                          && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                                          for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
                                             if (CFG.game.getCiv(i).getCivTag().equals(tempCommand[1])) {
                                                IllegalCommand();
                                                addMessage(CFG.game.getCiv(i).getCivName() + ": IS IN THE GAME");
                                                addMessage("");
                                                return;
                                             }
                                          }

                                          CFG.game
                                             .getProvince(CFG.game.getActiveProvinceID())
                                             .updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(0), 0);
                                          CFG.game.createScenarioAddCivilization(tempCommand[1], CFG.game.getActiveProvinceID(), false, true, true);
                                          if (CFG.FOG_OF_WAR == 2) {
                                             for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                                                CFG.game.getPlayer(i).addMetCivilization(true);
                                             }
                                          }

                                          int tempPop = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation();
                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().clearData();
                                          CFG.game
                                             .getProvince(CFG.game.getActiveProvinceID())
                                             .getPopulationData()
                                             .setPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), tempPop);
                                          CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setMoney(100L);
                                          CFG.gameAction.updateCivsMovementPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                          CFG.gameAction.updateCivsDiplomacyPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                          CFG.gameAction.buildRank_Score(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                          int tActiveProvince = CFG.game.getActiveProvinceID();
                                          CFG.game.setActiveProvinceID(-1);
                                          CFG.game.setActiveProvinceID(tActiveProvince);
                                          addMessage(
                                             CFG.langManager.get("Added")
                                                + ": "
                                                + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                          );
                                       } else {
                                          IllegalCommand();
                                          CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                          addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                          addMessage("");
                                       }

                                       return;
                                    }

                                    if (tempCommand[0].equals("technology")) {
                                       if (CFG.game.getActiveProvinceID() < 0
                                          || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() >= 0
                                          || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                                          IllegalCommand();
                                          CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                          addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                          addMessage("");
                                       } else if (tempCommand.length > 1) {
                                          try {
                                             int tempTech = Integer.parseInt(tempCommand[1]);
                                             if (tempTech > 100) {
                                                tempTech = 100;
                                             } else if (tempTech < 1) {
                                                tempTech = 1;
                                             }

                                             CFG.game
                                                .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                                .setTechnologyLevel((float)tempTech / 100.0F);
                                             addMessage(
                                                cheatMess()
                                                   + CFG.langManager.get("Technology")
                                                   + ": "
                                                   + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getTechnologyLevel()
                                                   + ", "
                                                   + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                             );
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Technology"));
                                          } catch (IllegalArgumentException var7) {
                                             IllegalCommand();
                                          }
                                       } else {
                                          IllegalCommand();
                                       }

                                       return;
                                    }

                                    if (tempCommand[0].equals("population")) {
                                       if (CFG.game.getActiveProvinceID() >= 0
                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                          && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                                          CFG.game
                                             .getProvince(CFG.game.getActiveProvinceID())
                                             .getPopulationData()
                                             .setPopulationOfCivID(
                                                CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                                                750
                                                   + CFG.game
                                                      .getProvince(CFG.game.getActiveProvinceID())
                                                      .getPopulationData()
                                                      .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                             );
                                          addMessage(cheatMess() + CFG.langManager.get("Population") + ": +" + 750);
                                          addMessage("");
                                          int tActiveProvince = CFG.game.getActiveProvinceID();
                                          CFG.game.setActiveProvinceID(-1);
                                          CFG.game.setActiveProvinceID(tActiveProvince);
                                          CFG.toast.setInView(cheatMess() + CFG.langManager.get("Population"));
                                          if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                                             CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                                          }
                                       } else {
                                          IllegalCommand();
                                          CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                          addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                          addMessage("");
                                       }

                                       return;
                                    }

                                    if (!tempCommand[0].equals("armyset") && !tempCommand[0].equals("setarmy")) {
                                       if (tempCommand[0].equals("noliberity")) {
                                          CFG.NO_LIBERITY = !CFG.NO_LIBERITY;
                                          addMessage(
                                             cheatMess()
                                                + CFG.langManager.get("Liberation")
                                                + ": "
                                                + (CFG.NO_LIBERITY ? CFG.langManager.get("Disabled") : CFG.langManager.get("Enabled"))
                                          );
                                          addMessage("");
                                          CFG.toast
                                             .setInView(
                                                cheatMess()
                                                   + CFG.langManager.get("Liberation")
                                                   + ": "
                                                   + (CFG.NO_LIBERITY ? CFG.langManager.get("Disabled") : CFG.langManager.get("Enabled"))
                                             );
                                          return;
                                       }

                                       if (tempCommand[0].equals("id")) {
                                          if (CFG.game.getActiveProvinceID() >= 0) {
                                             addMessage(cheatMess() + CFG.langManager.get("Province") + ": " + CFG.game.getActiveProvinceID());
                                             addMessage(
                                                cheatMess()
                                                   + CFG.langManager.get("Civilization")
                                                   + ": "
                                                   + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                                   + ": "
                                                   + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivID()
                                             );
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("War"));
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("war")) {
                                          int civA = Integer.parseInt(tempCommand[1]);
                                          int civB = Integer.parseInt(tempCommand[2]);
                                          if (civA >= 0
                                             && civB >= 0
                                             && CFG.game.getCiv(civA).getNumOfProvinces() > 0
                                             && CFG.game.getCiv(civB).getNumOfProvinces() > 0) {
                                             CFG.game.declareWar(civA, civB, true);
                                             addMessage(
                                                cheatMess()
                                                   + CFG.langManager.get("War")
                                                   + ": "
                                                   + CFG.game.getCiv(civA).getCivName()
                                                   + " -> "
                                                   + CFG.game.getCiv(civB).getCivName()
                                             );
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("War"));
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("Error")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("peace")) {
                                          int civA = Integer.parseInt(tempCommand[1]);
                                          int civB = Integer.parseInt(tempCommand[2]);
                                          if (civA >= 0 && civB >= 0 && CFG.game.getCivsAtWar(civA, civB)) {
                                             CFG.game
                                                .getCiv(civB)
                                                .civGameData
                                                .civilization_Diplomacy_GameData
                                                .messageBox
                                                .addMessage(new Message_WeCanSignPeace(civA));
                                             addMessage(
                                                cheatMess()
                                                   + CFG.langManager.get("Added")
                                                   + ": "
                                                   + CFG.game.getCiv(civA).getCivName()
                                                   + " -> "
                                                   + CFG.game.getCiv(civB).getCivName()
                                             );
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Added"));
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("Error")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("buildport")) {
                                          if (CFG.game.getActiveProvinceID() >= 0
                                             && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                             && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= 0) {
                                             CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(1);
                                             addMessage(cheatMess() + "Port built");
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Port built"));
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("buildfort")) {
                                          if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                                             CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(1);
                                             CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateDrawArmy();
                                             addMessage(cheatMess() + "Fort built");
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Fort built"));
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("buildtower")) {
                                          if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                                             CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(1);
                                             CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateDrawArmy();
                                             addMessage(cheatMess() + "Tower built");
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Tower built"));
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("economy")) {
                                          if (CFG.game.getActiveProvinceID() >= 0
                                             && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                             && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                                             CFG.game
                                                .getProvince(CFG.game.getActiveProvinceID())
                                                .setEconomy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() + 600);
                                             addMessage(cheatMess() + CFG.langManager.get("Economy") + ": +" + 600);
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Economy"));
                                             if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                                                CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                                             }
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("army")) {
                                          if (CFG.game.getActiveProvinceID() >= 0) {
                                             CFG.game
                                                .getProvince(CFG.game.getActiveProvinceID())
                                                .updateArmy(
                                                   CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID),
                                                   CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(CFG.activeCivilizationArmyID) + 300
                                                );
                                             addMessage(cheatMess() + CFG.langManager.get("Army") + ": +" + 300);
                                             addMessage("");
                                             int tActiveProvince = CFG.game.getActiveProvinceID();
                                             CFG.game.setActiveProvinceID(-1);
                                             CFG.game.setActiveProvinceID(tActiveProvince);
                                             CFG.toast.setInView(cheatMess() + CFG.langManager.get("Army"));
                                             if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                                                CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                                             }
                                          } else {
                                             IllegalCommand();
                                             CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                             addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                             addMessage("");
                                          }

                                          return;
                                       }

                                       if (tempCommand[0].equals("money")) {
                                          CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() + 450L);
                                          addMessage(cheatMess() + CFG.langManager.get("Money") + ": +" + 450);
                                          addMessage("");
                                          CFG.toast.setInView(cheatMess() + CFG.langManager.get("Money"));
                                          CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                          return;
                                       }

                                       if (tempCommand[0].equals("movement")) {
                                          CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .setMovePoints(
                                                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
                                                   + CFG.ideologiesManager
                                                         .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                                         .COST_OF_MOVE
                                                      / 2
                                             );
                                          addMessage(
                                             cheatMess()
                                                + CFG.langManager.get("MovementPoints")
                                                + ": +"
                                                + CFG.ideologiesManager
                                                      .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                                      .COST_OF_MOVE
                                                   / 2
                                          );
                                          addMessage("");
                                          CFG.toast.setInView(cheatMess() + CFG.langManager.get("movement"));
                                          CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                          return;
                                       }

                                       if (tempCommand[0].equals("diplomacy")) {
                                          CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .setDiplomacyPoints(
                                                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints()
                                                   + CFG.ideologiesManager
                                                         .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                                         .COST_OF_MOVE
                                                      * 3
                                                      / 4
                                             );
                                          addMessage(
                                             cheatMess()
                                                + CFG.langManager.get("DiplomacyPoints")
                                                + ": +"
                                                + (float)(
                                                      CFG.ideologiesManager
                                                            .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                                            .COST_OF_MOVE
                                                         * 3
                                                         / 4
                                                   )
                                                   / 10.0F
                                          );
                                          addMessage("");
                                          CFG.toast.setInView(cheatMess() + CFG.langManager.get("diplomacy"));
                                          CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                          return;
                                       }

                                       if (tempCommand[0].equals("reloadprovince")) {
                                          try {
                                             int tempID = Integer.parseInt(tempCommand[1]);
                                             if (tempID < CFG.game.getProvincesSize()) {
                                                Editor_NeighboringProvinces.updateProvince(tempID);
                                                CFG.game.setActiveProvinceID(tempID);
                                                CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                                             } else {
                                                IllegalCommand();
                                             }

                                             return;
                                          } catch (IllegalArgumentException var5) {
                                             IllegalCommand();
                                          } catch (IndexOutOfBoundsException var6) {
                                             IllegalCommand();
                                          }

                                          return;
                                       }
                                       break label745;
                                    }

                                    int tArmy = Integer.parseInt(tempCommand[1]);
                                    if (tArmy >= 0
                                       && CFG.game.getActiveProvinceID() >= 0
                                       && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                       && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                                       CFG.game
                                          .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                          .setNumOfUnits(
                                             CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits()
                                                - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(0)
                                          );
                                       CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(tArmy);
                                       CFG.game
                                          .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                          .setNumOfUnits(
                                             CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() + tArmy
                                          );
                                       addMessage(cheatMess() + CFG.langManager.get("Army") + ": " + tArmy);
                                       addMessage("");
                                       int tActiveProvince = CFG.game.getActiveProvinceID();
                                       CFG.game.setActiveProvinceID(-1);
                                       CFG.game.setActiveProvinceID(tActiveProvince);
                                       CFG.toast.setInView(cheatMess() + CFG.langManager.get("Army"));
                                    } else {
                                       IllegalCommand();
                                       CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                       addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                       addMessage("");
                                    }

                                    return;
                                 }

                                 for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
                                    addMessage("CIV ID: " + i + ", TAG: " + CFG.game.getCiv(i).getCivTag() + ", " + CFG.game.getCiv(i).getCivName());
                                 }

                                 return;
                              }

                              CFG.toast.setInView("Games -> New Game -> Options -> Spectactor Mode");
                              CFG.toast.setTimeInView(4500);
                              addMessage("Games -> New Game -> Options -> Spectator Mode");
                              return;
                           }

                           if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                              CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                           }

                           Random oR = new Random();
                           lShit.clear();

                           for(int i = 0; i < CFG.GAME_WIDTH + CFG.GAME_HEIGHT; ++i) {
                              lShit.add(new Point_XY(oR.nextInt(CFG.GAME_WIDTH), oR.nextInt(CFG.GAME_HEIGHT)));
                           }

                           lShitTime = System.currentTimeMillis();
                           CFG.toast.setInView(CFG.langManager.get("clear"));
                           CFG.menuManager.getKeyboard().setVisible(false);
                           return;
                        }

                        if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                           CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                        }

                        CFG.toast.setInView(CFG.langManager.get("Help"));
                        addMessage("#" + CFG.sVERSION + ": " + "1.01415_ELA");
                        addMessage("");
                        addMessage("console");
                        addMessage("close");
                        addMessage("civ");
                        addMessage("civs");
                        addMessage("province");
                        addMessage("center X");
                        addMessage("centerciv X");
                        addMessage("scale X");
                        return;
                     }

                     CFG.map.getMapScroll().setScrollPos(125000, 10);
                     CFG.map.getMapScroll().setScrollPos(10, 10);
                     CFG.menuManager.getKeyboard().setVisible(false);
                     CFG.menuManager.setVisible_InGame_FlagAction(false);
                     CFG.map.getMapScroll().startScrollingTheMap();
                     CFG.toast.setInView(CFG.langManager.get("Wheee") + "!");
                     addMessage(CFG.langManager.get("Wheee") + "!");
                     return;
                  }

                  if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                     CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                  }

                  CFG.toast.setInView(CFG.langManager.get("Hello") + "!");
                  addMessage(CFG.langManager.get("Hello") + "!");
                  return;
               }

               if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                  CFG.menuManager.setVisible_InGame_FlagAction_Console(false);
               }

               CFG.menuManager.getKeyboard().setVisible(false);
               return;
            }
         } catch (IndexOutOfBoundsException var13) {
            CFG.exceptionStack(var13);
         } catch (NumberFormatException var14) {
            CFG.exceptionStack(var14);
         } catch (IllegalArgumentException var15) {
            CFG.exceptionStack(var15);
         }

         IllegalCommand();
      }
   }

   private static final String cheatMess() {
      return "[" + CFG.langManager.get("Cheat") + "] ";
   }

   private static final void IllegalCommand() {
      addMessage("# -- " + CFG.langManager.get("UnknownCommand"));
      CFG.toast.setInView("# -- " + CFG.langManager.get("UnknownCommand"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
      addMessage("");
   }
}
