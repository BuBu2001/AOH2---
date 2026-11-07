package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class AI_Style {
   protected static final int MIN_ARMY_TO_ATTACK = 10;
   protected String TAG;
   protected float PERSONALITY_MIN_MILITARY_SPENDINGS_DEFAULT = 0.16F;
   protected int PERSONALITY_MIN_MILITARY_SPENDINGS_RANDOM = 20;
   protected float PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_DEFAULT = 0.6F;
   protected int PERSONALITY_MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY_RANDOM = 35;
   protected int PERSONALITY_MIN_HAPPINESS_DEFAULT = 69;
   protected int PERSONALITY_MIN_HAPPINESS_RANDOM = 24;
   protected float PERSONALITY_FORGIVNESS_DEFAULT = 1.0F;
   protected int PERSONALITY_FORGIVNESS_RANDOM = 50;
   protected int USE_OF_BUDGET_FOR_SPENDINGS = 35;
   protected int USE_OF_BUDGET_FOR_SPENDINGS_RANDOM = 65;
   protected int PERSONALITY_GOODS_RANDOM = 100;
   protected int PERSONALITY_INVESTMENTS_RANDOM = 100;
   protected int PERSONALITY_RESEARCH_RANDOM = 100;
   protected int PERSONALITY_PLUNDER_MIN = 0;
   protected int PERSONALITY_PLUNDER_RANDOM = 45;
   protected int PERSONALITY_PLUNDER_LOCK = 78;
   protected float PERSONALITY_MIN_AGGRESION_DEFAULT = 0.2475F;
   protected int PERSONALITY_MIN_AGGRESION_RANDOM = 4825;
   protected boolean armyOverBudget = false;
   protected int RIVAL_MIN_TURNS = 34;
   protected int MIN_TURNS_TO_ABANDON_USELESS_PROVINCE = 25;

   protected AI_Style() {
      this.TAG = "DEFAULT";
   }

   protected float getMinMilitarySpendings(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS;
   }

   protected void turnOrders(int nCivID) {
      Gdx.app.log(this.TAG, "BEGIN, " + CFG.game.getCiv(nCivID).getCivName());
      this.armyOverBudget = false;
      this.relocateLostCapital(nCivID);
      this.changeTypeOfIdeology(nCivID);
      this.checkPeaceTreaties(nCivID);
      if (CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize > 0) {
         CFG.game.getCiv(nCivID).civGameData.civPlans.checkWarPreparations(nCivID);
      }

      try {
         if (CFG.game.getCiv(nCivID).isAtWar()) {
            this.defendFromSeaInvasion(nCivID);
            this.moveAtWar(nCivID);
            this.armyOverBudget = true;
         }

         if (CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar()) {
            this.prepareForWar2(nCivID);
         }
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      } catch (NullPointerException var7) {
         CFG.exceptionStack(var7);
      } catch (ArithmeticException var8) {
         CFG.exceptionStack(var8);
      }

      CFG.oAI.expandNeutral.expandToNeutralProvinces(nCivID);
      Gdx.app
         .log(
            "AoC",
            "MILITARY UPKEEP CHECK: "
               + (
                  0.96F
                     - CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
                     - CFG.game.getCiv(nCivID).getSpendings_Goods()
                     - CFG.game.getCiv(nCivID).getSpendings_Investments()
               )
         );
      if (this.getMinMilitarySpendings(nCivID) + 0.025F < CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC) {
         Gdx.app.log("AoC", "MILITARY UPKEEP CHECK: we need to disband");
         this.armyOverBudget_Disband(nCivID);
         this.armyOverBudget = true;
      }

      if (CFG.game.getCiv(nCivID).getHappiness() < CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_CRISIS) {
         this.happinessCrisis(nCivID);
      } else if (!CFG.game.getCiv(nCivID).isAtWar()) {
         if (CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() > 0
            && CFG.game.getCiv(nCivID).getTaxationLevel() <= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
            && CFG.game.getCiv(nCivID).getSpendings_Goods() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
            )
          {
            this.hostFestivals(nCivID, CFG.game.getCiv(nCivID).getNumOfProvinces());
         }
      } else if (CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() > 0
         && CFG.game.getCiv(nCivID).getTaxationLevel() <= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
         && CFG.game.getCiv(nCivID).getSpendings_Goods() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)) {
         this.hostFestivals(nCivID, 1 + CFG.oR.nextInt(3));
      }

      if (CFG.game.getCiv(nCivID).lProvincesWithLowStability.size() > 0) {
         this.assimilateProvinces(nCivID);
      }

      if ((!this.armyOverBudget || CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0)
         && CFG.game.getCiv(nCivID).getMoney() > 0L
         && this.getMinMilitarySpendings(nCivID) > CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC + 0.0275F) {
         this.recruitMilitary_MinSpendings(nCivID);
      }

      if (!this.armyOverBudget) {
         this.colonizeProvinces(nCivID);
      }

      if (!CFG.game.getCiv(nCivID).isAtWar() && !CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar()) {
         this.regroupArmy_AtPeace(nCivID);
      }

      this.regroupArmyAfterRecruitment(nCivID);
      if (CFG.game.getCiv(nCivID).lProvincesWithHighRevRisk.size() > 0) {
         this.prepareArmyForRevolution(nCivID);
      }

      if (CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar()) {
         this.prepareForWar_MoveReadyArmies(nCivID);

         for(int i = CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize - 1; i >= 0; --i) {
            if (CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(i).iNumOfTurnsLeft-- <= 0) {
               int tOnCivID = CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(i).onCivID;
               CFG.game.declareWar(nCivID, CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(i).onCivID, false);

               for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
                  if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.PREAPARE_FOR_WAR
                     && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_ID == tOnCivID) {
                     CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                  }
               }

               try {
                  CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.remove(i);
                  CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize = CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.size();
               } catch (IndexOutOfBoundsException var5) {
               }
            }
         }
      }

      if (CFG.game.getCiv(nCivID).getMovePoints() >= 10 && CFG.game.getCiv(nCivID).getMoney() > 0L) {
         this.buildBuildings(nCivID);
      }

      Gdx.app.log(this.TAG, "AI -> turnOrders -> END, " + CFG.game.getCiv(nCivID).getCivName());
      CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn = 0;
   }

   protected final void turnOrdersEssential(int nCivID) {
      this.respondToEvents(nCivID);
      this.updateSentMessages(nCivID);
      this.respondToMessages(nCivID);
      this.diplomacyActions(nCivID);
      this.manageBudget(nCivID);
      this.updateLiberityDesire(nCivID);
   }

   protected void diplomacyActions(int nCivID) {
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
         Gdx.app.log("AoC", "diplomacyActions - > " + CFG.game.getCiv(nCivID).getCivName());
         this.diplomacyActions_BuildCivsInRange(nCivID);
         if (!CFG.game.getCiv(nCivID).isAtWar() && !CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar()) {
            this.diplomacyActions_RivalCiv(nCivID);
            this.diplomacyActions_FormCiv(nCivID);
            this.diplomacyActions_CircledVassals(nCivID);
         }

         this.diplomacyActions_FriendCiv(nCivID);
         if (!CFG.game.getCiv(nCivID).isAtWar() && !CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar()) {
            this.diplomacyActions_DeclareWar(nCivID);
         }

         this.diplomacyActions_Ally(nCivID);
         Gdx.app.log("AoC", "diplomacyActions - > END");
      }
   }

   protected final void diplomacyActions_FormCiv(int nCivID) {
      if (Game_Calendar.TURN_ID >= CFG.game.getCiv(nCivID).civGameData.checkFormCiv_TurnID) {
         if (CFG.game.getCiv(nCivID).getTagsCanFormSize() > 0) {
            for(int i = 0; i < CFG.game.getCiv(nCivID).getTagsCanFormSize(); ++i) {
               if (CFG.canFormACiv(nCivID, CFG.game.getCiv(nCivID).getTagsCanForm(i), true)) {
                  CFG.loadFormableCiv_GameData(CFG.game.getCiv(nCivID).getTagsCanForm(i));
                  CFG.formCiv(nCivID);
               }
            }

            CFG.game.getCiv(nCivID).civGameData.checkFormCiv_TurnID = Game_Calendar.TURN_ID + 40 + CFG.oR.nextInt(60);
         }

         CFG.game.getCiv(nCivID).civGameData.checkFormCiv_TurnID = Game_Calendar.TURN_ID + 500;
      }
   }

   protected final void diplomacyActions_CircledVassals(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.circledVassals_TurnID <= Game_Calendar.TURN_ID) {
         if (CFG.game.getCiv(nCivID).civGameData.iVassalsSize > 0) {
            for(int z = 0; z < CFG.game.getCiv(nCivID).civGameData.iVassalsSize; ++z) {
               if (CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID - 1).size() == 0
                  && CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID).getSeaAccess() <= 0) {
                  if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID) > -10.0F) {
                     DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID, 10);
                     DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID, 10);
                     DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID, 10);
                     DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID, 10);
                     DiplomacyManager.decreaseRelation(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID, 10);
                  }

                  Ultimatum_GameData nUltimatum = new Ultimatum_GameData();
                  nUltimatum.demandAnexation = true;
                  DiplomacyManager.sendUltimatum(
                     CFG.game.getCiv(nCivID).civGameData.lVassals.get(z).iCivID, nCivID, nUltimatum, CFG.game.getCiv(nCivID).getNumOfUnits()
                  );
               }
            }
         }

         CFG.game.getCiv(nCivID).civGameData.circledVassals_TurnID = Game_Calendar.TURN_ID + 30 + CFG.oR.nextInt(25);
      }
   }

   protected final void diplomacyActions_DeclareWar(int nCivID) {
      Gdx.app
         .log(
            "AoC",
            "diplomacyActions_DeclareWar - > nCivID: "
               + CFG.game.getCiv(nCivID).getCivName()
               + ", declareWar_CheckNextTurnID: "
               + CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID
         );
      if (CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID <= Game_Calendar.TURN_ID) {
         if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID
               + (int)((float)(12 + CFG.oR.nextInt(35) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 30) / Game_Calendar.AI_AGGRESSIVNESS);
         } else if ((
                  CFG.game.getCiv(nCivID).getPuppetOfCivID() == nCivID
                     ? CFG.game.getCiv(nCivID).civGameData.civPersonality.AGGRESSION
                     : CFG.game.getCiv(nCivID).civGameData.civPersonality.AGGRESSION / 8.0F
               )
               * Game_Calendar.AI_AGGRESSIVNESS
            >= (float)CFG.oR.nextInt(10000) / 100.0F) {
            List<Integer> possibleCivs = new ArrayList<>();
            if (CFG.game.getCiv(nCivID).lProvincesWithLowStability.size() > 0) {
               CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID
                  + 8
                  + (int)(
                     (float)(
                           CFG.game.getCiv(nCivID).lProvincesWithLowStability.size() * 2
                              + CFG.oR.nextInt(14)
                              + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 25
                        )
                        / Game_Calendar.AI_AGGRESSIVNESS
                  );
               return;
            }

            if (CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() > 0) {
               CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID
                  + 8
                  + (int)(
                     (float)(
                           CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() * 2
                              + CFG.oR.nextInt(14)
                              + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 25
                        )
                        / Game_Calendar.AI_AGGRESSIVNESS
                  );
               return;
            }

            for(int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
               boolean wasAdded = false;

               for(int o = possibleCivs.size() - 1; o >= 0; --o) {
                  if (possibleCivs.get(o) == CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID) {
                     wasAdded = true;
                  }
               }

               if (!wasAdded) {
                  possibleCivs.add(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID);
               }
            }

            for(int i = 0; i < CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size(); ++i) {
               for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
                  for(int k = 0;
                     k
                        < CFG.game
                           .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                           .getNeighboringProvincesSize();
                     ++k
                  ) {
                     if (!CFG.game
                        .getProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                              .getNeighboringProvinces(k)
                        )
                        .getSeaProvince()) {
                        if (CFG.game
                              .getProvince(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                              .getCivID()
                           > 0) {
                           boolean wasAdded = false;

                           for(int o = possibleCivs.size() - 1; o >= 0; --o) {
                              if (possibleCivs.get(o)
                                 == CFG.game
                                    .getProvince(
                                       CFG.game
                                          .getProvince(
                                             CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)
                                          )
                                          .getNeighboringProvinces(k)
                                    )
                                    .getCivID()) {
                                 wasAdded = true;
                              }
                           }

                           if (!wasAdded) {
                              possibleCivs.add(
                                 CFG.game
                                    .getProvince(
                                       CFG.game
                                          .getProvince(
                                             CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)
                                          )
                                          .getNeighboringProvinces(k)
                                    )
                                    .getCivID()
                              );
                           }
                        }
                     } else {
                        for(int z = 0;
                           z
                              < CFG.game
                                 .getProvince(
                                    CFG.game
                                       .getProvince(
                                          CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)
                                       )
                                       .getNeighboringProvinces(k)
                                 )
                                 .getNeighboringProvincesSize();
                           ++z
                        ) {
                           if (CFG.game
                                 .getProvince(
                                    CFG.game
                                       .getProvince(
                                          CFG.game
                                             .getProvince(
                                                CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)
                                             )
                                             .getNeighboringProvinces(k)
                                       )
                                       .getNeighboringProvinces(z)
                                 )
                                 .getCivID()
                              > 0) {
                              boolean wasAdded = false;

                              for(int o = possibleCivs.size() - 1; o >= 0; --o) {
                                 if (possibleCivs.get(o)
                                    == CFG.game
                                       .getProvince(
                                          CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i))
                                                         .getNeighboringSeaProvinces(j)
                                                   )
                                                   .getNeighboringProvinces(k)
                                             )
                                             .getNeighboringProvinces(z)
                                       )
                                       .getCivID()) {
                                    wasAdded = true;
                                 }
                              }

                              if (!wasAdded) {
                                 possibleCivs.add(
                                    CFG.game
                                       .getProvince(
                                          CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i))
                                                         .getNeighboringSeaProvinces(j)
                                                   )
                                                   .getNeighboringProvinces(k)
                                             )
                                             .getNeighboringProvinces(z)
                                       )
                                       .getCivID()
                                 );
                              }
                           }
                        }
                     }
                  }
               }
            }

            if ((possibleCivs.size() == 0 || CFG.oR.nextInt(100) < 6)
               && CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces_Size() > 0
               && CFG.game.getCiv(nCivID).getNumOfProvinces() > 4) {
               for(int i = CFG.game.getCiv(nCivID).civGameData.civsInRange.size() - 1; i >= 0; --i) {
                  possibleCivs.add(CFG.game.getCiv(nCivID).civGameData.civsInRange.get(i).iCivID);
               }
            }

            if (possibleCivs.size() < 0) {
               for(int i = 0; i < CFG.game.getCiv(nCivID).civGameData.civRivalsSize; ++i) {
                  boolean wasAdded = false;

                  for(int o = possibleCivs.size() - 1; o >= 0; --o) {
                     if (possibleCivs.get(o) == CFG.game.getCiv(nCivID).civGameData.civRivals.get(i).iCivID) {
                        wasAdded = true;
                     }
                  }

                  if (!wasAdded) {
                     possibleCivs.add(CFG.game.getCiv(nCivID).civGameData.civRivals.get(i).iCivID);
                  }
               }
            }

            for(int i = possibleCivs.size() - 1; i >= 0; --i) {
               if (CFG.game.getCiv(possibleCivs.get(i)).getPuppetOfCivID() != possibleCivs.get(i)) {
                  possibleCivs.remove(i);
               } else if (CFG.game.getCiv(nCivID).isFriendlyCiv(possibleCivs.get(i)) >= 0) {
                  possibleCivs.remove(i);
               } else if (CFG.game.isAlly(nCivID, possibleCivs.get(i))) {
                  possibleCivs.remove(i);
               } else if (CFG.game.getGuarantee(nCivID, possibleCivs.get(i)) > 0 || CFG.game.getGuarantee(possibleCivs.get(i), nCivID) > 0) {
                  possibleCivs.remove(i);
               } else if (CFG.game.getCivNonAggressionPact(nCivID, possibleCivs.get(i)) > 0) {
                  possibleCivs.remove(i);
               } else if (CFG.game.getCivTruce(nCivID, possibleCivs.get(i)) > 0) {
                  possibleCivs.remove(i);
               } else if (CFG.game.getCivRelation_OfCivB(nCivID, possibleCivs.get(i))
                  > (CFG.oAI.NUM_OF_CIVS_IN_THE_GAME < 10 ? 10.0F : Math.max(-50.0F, -50.0F / Game_Calendar.AI_AGGRESSIVNESS))) {
                  possibleCivs.remove(i);
               }
            }

            if (possibleCivs.size() > 0) {
               boolean done = false;
               if (CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.size() > 0
                  && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED < 0) {
                  List<Integer> tribalPossible = new ArrayList<>();

                  for(int z = 0; z < possibleCivs.size(); ++z) {
                     if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(possibleCivs.get(z)).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                        tribalPossible.add(possibleCivs.get(z));
                     }
                  }

                  if (tribalPossible.size() > 0) {
                     CFG.game.declareWar(nCivID, tribalPossible.get(CFG.oR.nextInt(tribalPossible.size())), false);
                     done = true;
                  }
               }

               if (!done) {
                  List<Float> lScores = new ArrayList<>();
                  float modifier_Relation = 1.275F + (float)CFG.oR.nextInt(975) / 1000.0F;
                  float modifier_Budget = 0.125F + (float)CFG.oR.nextInt(1755) / 1000.0F;
                  float modifier_CivsSize = 0.625F;

                  for(int z = 0; z < possibleCivs.size(); ++z) {
                     lScores.add(this.diplomacyActions_DeclareWar_Score(nCivID, possibleCivs.get(z), modifier_Budget, modifier_CivsSize, modifier_Relation));
                  }

                  int tBest = 0;

                  for(int z = 1; z < possibleCivs.size(); ++z) {
                     if (lScores.get(tBest) < lScores.get(z)) {
                        tBest = z;
                     }
                  }

                  int ownBudget = this.diplomacyActions_DeclareWar_Budgets(nCivID, false);
                  int theirBudget = this.diplomacyActions_DeclareWar_Budgets(possibleCivs.get(tBest), true);
                  if ((float)ownBudget > (float)theirBudget * 0.695F) {
                     int turns = 3 + CFG.oR.nextInt(4);
                     CFG.game.getCiv(nCivID).civGameData.civPlans.addNewWarPreparations(nCivID, nCivID, possibleCivs.get(tBest), turns);
                     List<Integer> toCall = DiplomacyManager.callToArmsListOfCivs(nCivID, possibleCivs.get(tBest));

                     for(int z = 0; z < toCall.size(); ++z) {
                        DiplomacyManager.sendPrepareForWar(toCall.get(z), nCivID, possibleCivs.get(tBest), turns, nCivID);
                     }
                  } else {
                     List<Integer> possibleToJoin = new ArrayList<>();

                     for(int a = 0; a < CFG.game.getCiv(possibleCivs.get(tBest)).getHatedCivs_BySize(); ++a) {
                        if (!CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(possibleCivs.get(tBest)).getHatedCiv_By(a))
                           && CFG.game.getCiv(CFG.game.getCiv(possibleCivs.get(tBest)).getHatedCiv_By(a)).getNumOfProvinces() > 0) {
                           possibleToJoin.add(CFG.game.getCiv(possibleCivs.get(tBest)).getHatedCiv_By(a));
                        }
                     }

                     for(int z = 0; z < possibleToJoin.size(); ++z) {
                        ownBudget += this.diplomacyActions_DeclareWar_Budgets(possibleToJoin.get(z), false);
                     }

                     if (!((float)ownBudget > (float)theirBudget * 0.605F)) {
                        DiplomacyManager.sendNonAggressionProposal(possibleCivs.get(tBest), nCivID, 40);
                     } else {
                        for(int z = 0; z < possibleToJoin.size(); ++z) {
                           if (possibleCivs.get(tBest) != possibleToJoin.get(z)) {
                              TradeRequest_GameData tradeRequest = new TradeRequest_GameData();
                              tradeRequest.iCivLEFT = nCivID;
                              tradeRequest.iCivRIGHT = possibleToJoin.get(z);
                              tradeRequest.listRight.iFormCoalitionAgainst = possibleCivs.get(tBest);
                              tradeRequest.listLEFT.iGold = 10 + CFG.oR.nextInt(50);
                              DiplomacyManager.sendTradeRequest(possibleToJoin.get(z), nCivID, tradeRequest);
                           }
                        }

                        int turns = 3 + CFG.oR.nextInt(4);
                        CFG.game.getCiv(nCivID).civGameData.civPlans.addNewWarPreparations(nCivID, nCivID, possibleCivs.get(tBest), turns);
                        List<Integer> toCall = DiplomacyManager.callToArmsListOfCivs(nCivID, possibleCivs.get(tBest));

                        for(int z = 0; z < toCall.size(); ++z) {
                           DiplomacyManager.sendPrepareForWar(toCall.get(z), nCivID, possibleCivs.get(tBest), turns, nCivID);
                        }
                     }
                  }
               }

               CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID
                  + (int)(
                     (float)(
                           10
                              + CFG.game.getCiv(possibleCivs.get(CFG.oR.nextInt(possibleCivs.size()))).getNumOfProvinces()
                              + CFG.oR.nextInt(25)
                              + CFG.oR.nextInt((int)((float)CFG.oAI.NUM_OF_CIVS_IN_THE_GAME * 1.025F) + 1) / 25
                        )
                        / Game_Calendar.AI_AGGRESSIVNESS
                  );
            } else {
               CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID
                  + (int)((float)(10 + CFG.oR.nextInt(14) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS);
            }
         } else {
            CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Game_Calendar.TURN_ID
               + (int)((float)(8 + CFG.oR.nextInt(14) + CFG.oR.nextInt(CFG.oAI.NUM_OF_CIVS_IN_THE_GAME + 1) / 30) / Game_Calendar.AI_AGGRESSIVNESS);
         }
      }
   }

   protected final float diplomacyActions_DeclareWar_Score(int nCivID, int onCivID, float modifier_Budget, float modifier_CivsSize, float modifier_Relation) {
      return modifier_Budget * (1.0F - Math.min((float)CFG.game.getCiv(onCivID).iBudget / (float)CFG.game.getCiv(nCivID).iBudget, 0.95F))
         + modifier_Relation
            * (1.0F + CFG.game.getCiv(onCivID).civGameData.civAggresionLevel / 4.0F)
            * (1.0F - Math.min(CFG.game.getCivRelation_OfCivB(nCivID, onCivID) + 100.0F, 200.0F) / 200.0F);
   }

   protected final int diplomacyActions_DeclareWar_Budgets(int nCivID, boolean defensivePacts) {
      int out = CFG.game.getCiv(nCivID).iBudget;
      if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
         out += CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).iBudget;
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).civGameData.iVassalsSize; ++i) {
         out += CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID).iBudget;
      }

      if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
         for(int i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++i) {
            if (nCivID != CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i)
               && nCivID != CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i)).getPuppetOfCivID()
               && CFG.game.getCiv(nCivID).getPuppetOfCivID() != CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i)) {
               out += CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(i)).iBudget;
            }
         }
      }

      try {
         if (defensivePacts) {
            for(int i = 1; i < nCivID; ++i) {
               if (CFG.game.getDefensivePact(nCivID, i) > 0) {
                  if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                     out += CFG.game.getCiv(i).iBudget;
                  }
               } else if (CFG.game.getGuarantee(i, nCivID) > 0 && CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                  out += CFG.game.getCiv(i).iBudget;
               }
            }

            for(int i = nCivID; i < CFG.game.getCivsSize(); ++i) {
               if (CFG.game.getDefensivePact(nCivID, i) > 0) {
                  if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                     out += CFG.game.getCiv(i).iBudget;
                  }
               } else if (CFG.game.getGuarantee(nCivID, i) > 0 && CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                  out += CFG.game.getCiv(i).iBudget;
               }
            }
         }
      } catch (IndexOutOfBoundsException var5) {
      }

      return out;
   }

   protected final void diplomacyActions_BuildCivsInRange(int nCivID) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && Game_Calendar.TURN_ID >= CFG.game.getCiv(nCivID).civGameData.nextBuildCivsInRange_TurnID) {
         CFG.game.getCiv(nCivID).civGameData.civsInRange.clear();
         CFG.game.getCiv(nCivID).civGameData.civsInRange = this.diplomacyActions_CivsInRange(nCivID);
         if (CFG.game.getCiv(nCivID).civGameData.civsInRange.size() > 0) {
            CFG.game.getCiv(nCivID).civGameData.nextBuildCivsInRange_TurnID = 55 + CFG.oR.nextInt(45);
         } else {
            CFG.game.getCiv(nCivID).civGameData.nextBuildCivsInRange_TurnID = 65 + CFG.oR.nextInt(Math.max(75, CFG.game.getCivsSize() / 4));
         }
      } else {
         CFG.game.getCiv(nCivID).civGameData.nextBuildCivsInRange_TurnID = 24 + CFG.oR.nextInt(26);
      }
   }

   protected final void diplomacyActions_Ally(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.allianceCheck_TurnID <= Game_Calendar.TURN_ID) {
         if (CFG.game.getCiv(nCivID).getNumOfProvinces() < 4
            && CFG.game.getCiv(nCivID).getPuppetOfCivID() == nCivID
            && CFG.game.getCiv(nCivID).getAllianceID() == 0
            && CFG.oR.nextInt(100) < 12
            && CFG.game.getCiv(nCivID).getFriendlyCivsSize() > 0) {
            int tRandCiv = CFG.oR.nextInt(CFG.game.getCiv(nCivID).getFriendlyCivsSize());
            if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getFriendlyCiv(tRandCiv).iCivID).getAllianceID() == 0) {
               DiplomacyManager.sendAllianceProposal(CFG.game.getCiv(nCivID).getFriendlyCiv(tRandCiv).iCivID, nCivID);
            }
         }

         CFG.game.getCiv(nCivID).civGameData.allianceCheck_TurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(35);
      }
   }

   protected final void diplomacyActions_FriendCiv(int nCivID) {
      this.diplomacyActions_InfluencedCiv_Update(nCivID);
      if (Game_Calendar.TURN_ID >= CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID) {
         int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces())
            - CFG.game.getCiv(nCivID).civGameData.civsInfluencedSize;
         if (numOfCivsToAdd > 0) {
            if (CFG.gameAction.getUpdateCivsDiplomacyPoints(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) <= 3) {
               CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
            } else if (CFG.game.getCiv(nCivID).civGameData.civsInRange.size() > 0) {
               List<AI_CivsInRange> possibleCivs = new ArrayList<>();

               for(int i = CFG.game.getCiv(nCivID).civGameData.civsInRange.size() - 1; i >= 0; --i) {
                  possibleCivs.add(CFG.game.getCiv(nCivID).civGameData.civsInRange.get(i));
               }

               Gdx.app.log("AoC", "diplomacyActions_FriendCiv -> " + CFG.game.getCiv(nCivID).getCivName() + ", possibleCivs.size: " + possibleCivs.size());

               for(int i = possibleCivs.size() - 1; i >= 0; --i) {
                  if (diplomacyActions_RivalCiv_IsRival(nCivID, possibleCivs.get(i).iCivID)) {
                     possibleCivs.remove(i);
                  } else if (this.diplomacyActions_IsInfluenced(nCivID, possibleCivs.get(i).iCivID)) {
                     possibleCivs.remove(i);
                  } else if (this.diplomacyActions_IsInfluenced(possibleCivs.get(i).iCivID, nCivID) && CFG.oR.nextInt(100) < 88) {
                     possibleCivs.remove(i);
                  } else if (CFG.game.getCivRelation_OfCivB(nCivID, possibleCivs.get(i).iCivID) > 55.0F
                     || CFG.game.getCivRelation_OfCivB(possibleCivs.get(i).iCivID, nCivID) > 55.0F) {
                     possibleCivs.remove(i);
                  }
               }

               if (possibleCivs.size() <= 0) {
                  CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
               } else {
                  List<Float> lScores = new ArrayList<>();
                  List<Integer> tempD = new ArrayList<>();
                  float modifier_Budget = 1.85F + (float)CFG.oR.nextInt(475) / 1000.0F;
                  float modifier_CivsSize = 0.625F + (float)CFG.oR.nextInt(325) / 1000.0F;
                  float modifier_Range = 0.2825F
                     + 0.1475F * ((float)CFG.game.getCiv(nCivID).getRankPosition() / (float)CFG.game.getCivsSize())
                     + (float)CFG.oR.nextInt(65) / 1000.0F;
                  int civBudget = (int)((float)CFG.game.getCiv(nCivID).iBudget * (0.625F + (float)CFG.oR.nextInt(725) / 100.0F));
                  int i = 0;

                  for(int iSize = possibleCivs.size(); i < iSize; ++i) {
                     lScores.add(this.diplomacyActions_FriendlyCiv_Score(civBudget, nCivID, possibleCivs.get(i), modifier_Budget, modifier_CivsSize));
                     tempD.add(i);
                  }

                  float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);

                  for(int ix = possibleCivs.size() - 1; ix >= 0; --ix) {
                     lScores.set(
                        ix,
                        (float)CFG.oR.nextInt(45) / 100.0F
                           + lScores.get(ix)
                              * (
                                 1.0F
                                    - modifier_Range
                                       * possibleCivs.get(ix).fRange
                                       / ((tempDis + tempDis * 0.2675F) * CFG.game.getCiv(nCivID).getTechnologyLevel())
                                    + 0.115F * (Math.min(CFG.game.getCivRelation_OfCivB(nCivID, possibleCivs.get(ix).iCivID), 0.0F) / 100.0F)
                              )
                     );
                  }

                  List<Integer> sortedIDs = new ArrayList<>();

                  while(tempD.size() > 0 && sortedIDs.size() < numOfCivsToAdd) {
                     int tBest = 0;

                     for(int ix = tempD.size() - 1; ix > 0; --ix) {
                        if (lScores.get(tempD.get(ix)) > lScores.get(tempD.get(tBest))) {
                           tBest = ix;
                        }
                     }

                     sortedIDs.add(tempD.get(tBest));
                     tempD.remove(tBest);
                  }

                  int maxCivsInThisTurn = Math.min(1 + CFG.oR.nextInt(2), numOfCivsToAdd);

                  for(int ix = 0; ix < possibleCivs.size() && ix < maxCivsInThisTurn; ++ix) {
                     CFG.game
                        .getCiv(nCivID)
                        .getCivilization_Diplomacy_GameData()
                        .addImproveRelations(nCivID, possibleCivs.get(sortedIDs.get(ix)).iCivID, 4 + CFG.oR.nextInt(35));
                     CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civsInfluenced
                        .add(
                           new AI_Influence(
                              possibleCivs.get(sortedIDs.get(ix)).iCivID,
                              32 + CFG.oR.nextInt(40),
                              Game_Calendar.TURN_ID + this.RIVAL_MIN_TURNS * 3 / 4 + CFG.oR.nextInt(40)
                           )
                        );
                     --numOfCivsToAdd;
                  }

                  CFG.game.getCiv(nCivID).civGameData.civsInfluencedSize = CFG.game.getCiv(nCivID).civGameData.civsInfluenced.size();
                  if (numOfCivsToAdd <= 0) {
                     CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
                  } else if (possibleCivs.size() - maxCivsInThisTurn
                     > Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces())
                        - CFG.game.getCiv(nCivID).civGameData.civsInfluencedSize) {
                     CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 5 + CFG.oR.nextInt(12);
                  }
               }

               CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
            } else {
               CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
            }
         } else {
            CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(24);
         }
      }
   }

   protected final float diplomacyActions_FriendlyCiv_Score(int civBudget, int nCivID, AI_CivsInRange withCiv, float modifier_Budget, float modifier_CivsSize) {
      return modifier_Budget
            * (float)Math.min(civBudget, CFG.game.getCiv(withCiv.iCivID).iBudget)
            / (float)Math.max(civBudget, CFG.game.getCiv(withCiv.iCivID).iBudget)
         + modifier_CivsSize
            * (float)Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces())
            / (float)Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces())
            * (this.isRivalOfMyRival(nCivID, withCiv.iCivID) ? 1.0625F : 1.0F)
            * (CFG.game.getCivRelation_OfCivB(nCivID, withCiv.iCivID) > 40.0F ? 0.625F : 1.0F);
   }

   protected final boolean isRivalOfMyRival(int nCivID, int withCiv) {
      for(int i = 0; i < CFG.game.getCiv(nCivID).civGameData.civRivalsSize; ++i) {
         if (diplomacyActions_RivalCiv_IsRival(CFG.game.getCiv(nCivID).civGameData.civRivals.get(i).iCivID, withCiv)) {
            return true;
         }
      }

      return false;
   }

   protected final void diplomacyActions_RivalCiv(int nCivID) {
      this.diplomacyActions_RivalCiv_Update(nCivID);
      if (Game_Calendar.TURN_ID >= CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID) {
         int numOfCivsToAdd = Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces())
            - CFG.game.getCiv(nCivID).civGameData.civRivalsSize;
         if (numOfCivsToAdd > 0) {
            if (CFG.game.getCiv(nCivID).civGameData.civsInRange.size() > 0) {
               List<AI_CivsInRange> possibleCivs = new ArrayList<>();

               for(int i = CFG.game.getCiv(nCivID).civGameData.civsInRange.size() - 1; i >= 0; --i) {
                  possibleCivs.add(CFG.game.getCiv(nCivID).civGameData.civsInRange.get(i));
               }

               Gdx.app.log("AoC", "diplomacyActions_RivalCiv -> " + CFG.game.getCiv(nCivID).getCivName() + ", possibleCivs.size: " + possibleCivs.size());

               for(int i = possibleCivs.size() - 1; i >= 0; --i) {
                  if (diplomacyActions_RivalCiv_IsRival(nCivID, possibleCivs.get(i).iCivID)) {
                     possibleCivs.remove(i);
                  } else if (this.diplomacyActions_IsInfluenced(nCivID, possibleCivs.get(i).iCivID)) {
                     possibleCivs.remove(i);
                  }
               }

               if (possibleCivs.size() > 0) {
                  List<Float> lScores = new ArrayList<>();
                  List<Integer> tempD = new ArrayList<>();
                  float modifier_Budget = 1.85F + (float)CFG.oR.nextInt(475) / 1000.0F;
                  float modifier_CivsSize = 0.625F + (float)CFG.oR.nextInt(525) / 1000.0F;
                  float modifier_Range = 0.2825F
                     + 0.1475F * ((float)CFG.game.getCiv(nCivID).getRankPosition() / (float)CFG.game.getCivsSize())
                     + (float)CFG.oR.nextInt(65) / 1000.0F;
                  int civBudget = 0;
                  if (CFG.game.getCiv(nCivID).getNumOfProvinces() >= 5 && CFG.game.getCiv(nCivID).iLeague <= 6) {
                     civBudget = (int)((float)CFG.game.getCiv(nCivID).iBudget * (0.775F + (float)CFG.oR.nextInt(625) / 100.0F));
                  } else {
                     civBudget = (int)((float)CFG.game.getCiv(nCivID).iBudget * (0.925F + (float)CFG.oR.nextInt(15) / 100.0F));
                  }

                  int i = 0;

                  for(int iSize = possibleCivs.size(); i < iSize; ++i) {
                     lScores.add(this.diplomacyActions_RivalCiv_Score(civBudget, nCivID, possibleCivs.get(i), modifier_Budget, modifier_CivsSize));
                     tempD.add(i);
                  }

                  float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);

                  for(int ix = possibleCivs.size() - 1; ix >= 0; --ix) {
                     lScores.set(
                        ix,
                        lScores.get(ix)
                           * (
                              1.0F
                                 + (
                                       -modifier_Range
                                          + (0.25F + (float)CFG.oR.nextInt(350) / 100.0F)
                                             * CFG.game.getCiv(possibleCivs.get(ix).iCivID).civGameData.civAggresionLevel
                                    )
                                    * possibleCivs.get(ix).fRange
                                    / ((tempDis + tempDis * 0.2675F) * CFG.game.getCiv(nCivID).getTechnologyLevel())
                                 + 0.115F * (Math.min(CFG.game.getCivRelation_OfCivB(nCivID, possibleCivs.get(ix).iCivID), 0.0F) / 100.0F)
                           )
                     );
                  }

                  List<Integer> sortedIDs = new ArrayList<>();

                  while(tempD.size() > 0 && sortedIDs.size() < numOfCivsToAdd) {
                     int tBest = 0;

                     for(int ix = tempD.size() - 1; ix > 0; --ix) {
                        if (lScores.get(tempD.get(ix)) > lScores.get(tempD.get(tBest))) {
                           tBest = ix;
                        }
                     }

                     sortedIDs.add(tempD.get(tBest));
                     tempD.remove(tBest);
                  }

                  int maxCivsInThisTurn = Math.min(1 + CFG.oR.nextInt(2), numOfCivsToAdd);

                  for(int ix = 0; ix < possibleCivs.size() && ix < maxCivsInThisTurn; ++ix) {
                     int turns = 15 + CFG.oR.nextInt(35);
                     DiplomacyManager.decreaseRelation(nCivID, possibleCivs.get(sortedIDs.get(ix)).iCivID, turns);
                     CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civRivals
                        .add(new AI_Rival(possibleCivs.get(sortedIDs.get(ix)).iCivID, Game_Calendar.TURN_ID + turns * 2 + CFG.oR.nextInt(20)));
                     --numOfCivsToAdd;
                  }

                  CFG.game.getCiv(nCivID).civGameData.civRivalsSize = CFG.game.getCiv(nCivID).civGameData.civRivals.size();
                  if (numOfCivsToAdd <= 0) {
                     CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID
                        + (int)((float)this.RIVAL_MIN_TURNS / 10.0F + (float)CFG.oR.nextInt(45) / Game_Calendar.AI_AGGRESSIVNESS);
                  } else if (possibleCivs.size() - maxCivsInThisTurn
                     > Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getNumOfProvinces()) - CFG.game.getCiv(nCivID).civGameData.civRivalsSize) {
                     CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID
                        + 5
                        + (int)((float)CFG.oR.nextInt(12) / Game_Calendar.AI_AGGRESSIVNESS);
                  }
               } else {
                  CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID
                     + (int)(((float)this.RIVAL_MIN_TURNS / 10.0F + (float)CFG.oR.nextInt(60)) / Game_Calendar.AI_AGGRESSIVNESS);
               }
            } else {
               CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID = CFG.game.getCiv(nCivID).civGameData.nextBuildCivsInRange_TurnID + 1;
            }
         } else {
            CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID = CFG.game.getCiv(nCivID).civGameData.nextBuildCivsInRange_TurnID
               + 5
               + CFG.oR.nextInt(25);
         }
      }
   }

   protected static final boolean diplomacyActions_RivalCiv_IsRival(int nCivID, int nRivalID) {
      for(int z = 0; z < CFG.game.getCiv(nCivID).civGameData.civRivalsSize; ++z) {
         if (CFG.game.getCiv(nCivID).civGameData.civRivals.get(z).iCivID == nRivalID) {
            return true;
         }
      }

      return false;
   }

   protected final boolean diplomacyActions_IsInfluenced(int nCivID, int nInfluenced) {
      for(int z = 0; z < CFG.game.getCiv(nCivID).civGameData.civsInfluencedSize; ++z) {
         if (CFG.game.getCiv(nCivID).civGameData.civsInfluenced.get(z).iCivID == nInfluenced) {
            return true;
         }
      }

      return false;
   }

   protected final void diplomacyActions_RivalCiv_Update(int nCivID) {
      for(int z = CFG.game.getCiv(nCivID).civGameData.civRivalsSize - 1; z >= 0; --z) {
         if (CFG.game.getCiv(nCivID).civGameData.civRivals.get(z).iUntilTurnID <= Game_Calendar.TURN_ID) {
            CFG.game.getCiv(nCivID).civGameData.civRivals.remove(z);
            CFG.game.getCiv(nCivID).civGameData.civRivalsSize = CFG.game.getCiv(nCivID).civGameData.civRivals.size();
            CFG.game.getCiv(nCivID).civGameData.holdLookingForEnemy_UntilTurnID = Game_Calendar.TURN_ID + CFG.oR.nextInt(2);
         }
      }
   }

   protected final void diplomacyActions_InfluencedCiv_Update(int nCivID) {
      for(int z = CFG.game.getCiv(nCivID).civGameData.civsInfluencedSize - 1; z >= 0; --z) {
         if (CFG.game.getCiv(nCivID).civGameData.civsInfluenced.get(z).iUntilTurnID <= Game_Calendar.TURN_ID) {
            CFG.game.getCiv(nCivID).civGameData.civsInfluenced.remove(z);
            CFG.game.getCiv(nCivID).civGameData.civsInfluencedSize = CFG.game.getCiv(nCivID).civGameData.civsInfluenced.size();
            CFG.game.getCiv(nCivID).civGameData.holdLookingForFriends_UntilTurnID = Game_Calendar.TURN_ID + CFG.oR.nextInt(2);
         }
      }
   }

   protected final float diplomacyActions_RivalCiv_Score(int civBudget, int nCivID, AI_CivsInRange withCiv, float modifier_Budget, float modifier_CivsSize) {
      return modifier_Budget
            * (float)Math.min(civBudget, CFG.game.getCiv(withCiv.iCivID).iBudget)
            / (float)Math.max(civBudget, CFG.game.getCiv(withCiv.iCivID).iBudget)
         + modifier_CivsSize
            * (float)Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces())
            / (float)Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces(), CFG.game.getCiv(withCiv.iCivID).getNumOfProvinces());
   }

   protected final List<AI_CivsInRange> diplomacyActions_CivsInRange(int nCivID) {
      List<AI_CivsInRange> possibleCivs = new ArrayList<>();
      float tDistanceBetweenCivs = 1.0F;
      float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);

      for(int i = 1; i < nCivID; ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(i).getCapitalProvinceID() > 0) {
            tDistanceBetweenCivs = CFG.game_NextTurnUpdate
               .getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), CFG.game.getCiv(i).getCapitalProvinceID());
            if (tDistanceBetweenCivs * 0.9F < (tempDis + tempDis * 0.2675F) * CFG.game.getCiv(nCivID).getTechnologyLevel()) {
               possibleCivs.add(new AI_CivsInRange(i, tDistanceBetweenCivs));
            }
         }
      }

      for(int i = nCivID + 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(i).getCapitalProvinceID() > 0) {
            tDistanceBetweenCivs = CFG.game_NextTurnUpdate
               .getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), CFG.game.getCiv(i).getCapitalProvinceID());
            if (tDistanceBetweenCivs * 0.9F < (tempDis + tempDis * 0.2675F) * CFG.game.getCiv(nCivID).getTechnologyLevel()) {
               possibleCivs.add(new AI_CivsInRange(i, tDistanceBetweenCivs));
            }
         }
      }

      return possibleCivs;
   }

   protected final void colonizeProvinces(int nCivID) {
      if (Game_Calendar.getColonizationOfWastelandIsEnabled() || Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
         boolean isColonizing = false;

         for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
            if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.COLONIZE_PROVINCE) {
               isColonizing = true;
               if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).canMakeAction(nCivID, 0)) {
                  if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
                     CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                     CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(
                        Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(24), CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID
                     );
                  } else if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).iObsolate <= 0) {
                     CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
                     CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(
                        Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(4), CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID
                     );
                  }
               }
            }
         }

         if (isColonizing) {
            return;
         }

         CFG.game.getCiv(nCivID).civGameData.iLockTreasury = 1;
         if (CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID > Game_Calendar.TURN_ID) {
            return;
         }

         if (!Game_Calendar.getCanColonize_TechLevel(nCivID)
            && CFG.game.getCiv(nCivID).getTechnologyLevel() / Game_Calendar.COLONIZATION_TECH_LEVEL
               < 1.0F
                  - 0.35F
                     * Math.min(
                        (float)CFG.oAI.iNumOfColonizedProvinces
                           / Math.min(
                              (float)(6 + Math.min((CFG.game.getCiv(nCivID).getRankPosition() - 1) * 3, 22)), (float)CFG.game.getProvincesSize() * 0.01F
                           ),
                        1.0F
                     )) {
            CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(
               Game_Calendar.TURN_ID + 12 + CFG.oR.nextInt(15), CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID
            );
            return;
         }

         if (CFG.game.getCiv(nCivID).iBudget < 1) {
            CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(
               Game_Calendar.TURN_ID + 5 + CFG.oR.nextInt(10), CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID
            );
            return;
         }

         if (DiplomacyManager.getColonizeCost_AI(nCivID) / CFG.game.getCiv(nCivID).iBudget > 22) {
            CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(
               Game_Calendar.TURN_ID + 8 + CFG.oR.nextInt(12), CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID
            );
            return;
         }

         if ((float)CFG.game.getCiv(nCivID).getRankPosition() < Math.max((float)CFG.game.getCivsSize() * 0.35F, 11.0F)) {
            try {
               if (CFG.game.getCiv(nCivID).isAtWar()) {
                  CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID = Math.max(
                     Game_Calendar.TURN_ID + 8 + CFG.oR.nextInt(14), CFG.game.getCiv(nCivID).civGameData.iLockColonization_UntilTurnID
                  );
                  return;
               }

               int numOfProvincesAbleToColonize = 0;
               if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
                  numOfProvincesAbleToColonize += CFG.oAI.lWastelandProvincesWithSeaAccess.size();
                  numOfProvincesAbleToColonize += CFG.game.getCiv(nCivID).lBordersWithWastelandProvincesID.size();
               }

               if (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
                  numOfProvincesAbleToColonize += CFG.oAI.lNeutralProvincesWithSeaAccess.size();
                  numOfProvincesAbleToColonize += CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size();
               }

               if (numOfProvincesAbleToColonize > 0) {
                  boolean tryFoundNewColony = true;
                  if (CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.size() > 0) {
                     tryFoundNewColony = !this.colonizeProvinces_ExtendColony(nCivID);
                  }

                  if (tryFoundNewColony) {
                     this.colonizeProvinces_FoundNewColony(nCivID);
                  }
               }
            } catch (IndexOutOfBoundsException var5) {
               CFG.exceptionStack(var5);
            }
         }
      }
   }

   protected final void colonizeProvinces_FoundNewColony(int nCivID) {
      List<AI_ProvinceValue> possibleProvinces = new ArrayList<>();
      List<Boolean> haveAccessToBasins = new ArrayList<>();

      for(int i = 0; i < CFG.map.iNumOfBasins; ++i) {
         haveAccessToBasins.add(false);
      }

      for(int i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
         if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).isOccupied()) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
               haveAccessToBasins.set(
                  CFG.game
                     .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                     .getBasinID(),
                  true
               );
            }
         }
      }

      for(int i = CFG.oAI.lNeutralProvincesWithSeaAccess.size() - 1; i >= 0; --i) {
         boolean canColonizeThisProvince = false;

         for(int j = 0; j < CFG.game.getProvince(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)).getNeighboringSeaProvincesSize(); ++j) {
            if (haveAccessToBasins.get(
               CFG.game.getProvince(CFG.game.getProvince(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)).getNeighboringSeaProvinces(j)).getBasinID()
            )) {
               canColonizeThisProvince = true;
               break;
            }
         }

         if (canColonizeThisProvince) {
            possibleProvinces.add(new AI_ProvinceValue(CFG.oAI.lNeutralProvincesWithSeaAccess.get(i)));
         }
      }

      if (possibleProvinces.size() == 0 && Game_Calendar.getColonizationOfWastelandIsEnabled()) {
         for(int i = CFG.oAI.lWastelandProvincesWithSeaAccess.size() - 1; i >= 0; --i) {
            boolean canColonizeThisProvince = false;

            for(int j = 0; j < CFG.game.getProvince(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)).getNeighboringSeaProvincesSize(); ++j) {
               if (haveAccessToBasins.get(
                  CFG.game.getProvince(CFG.game.getProvince(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)).getNeighboringSeaProvinces(j)).getBasinID()
               )) {
                  canColonizeThisProvince = true;
                  break;
               }
            }

            if (canColonizeThisProvince) {
               possibleProvinces.add(new AI_ProvinceValue(CFG.oAI.lWastelandProvincesWithSeaAccess.get(i)));
            }
         }
      }

      if (possibleProvinces.size() > 0) {
         int colonizeProvinceID = possibleProvinces.get(CFG.oR.nextInt(possibleProvinces.size())).iProvinceID;
         if (CFG.game.getProvince(colonizeProvinceID).getCivID() == 0) {
            CFG.game
               .getCiv(nCivID)
               .civGameData
               .civPlans
               .addNewArmyMission(colonizeProvinceID, new CivArmyMission_ColonizeProvince(nCivID, colonizeProvinceID));
            CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Math.max(
               CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID,
               Game_Calendar.TURN_ID
                  + (int)((float)(8 + CFG.oR.nextInt(25) + CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS)
            );
         }
      }
   }

   protected final boolean colonizeProvinces_ExtendColony(int nCivID) {
      try {
         List<AI_ProvinceValue> possibleProvinces = new ArrayList<>();

         for(int i = CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; --i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i)).getCivID() == 0) {
               possibleProvinces.add(
                  new AI_ProvinceValue(
                     CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i),
                     this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i))
                  )
               );
            }
         }

         for(int i = CFG.game.getCiv(nCivID).lBordersWithWastelandProvincesID.size() - 1; i >= 0; --i) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lBordersWithWastelandProvincesID.get(i)).getCivID() == 0) {
               possibleProvinces.add(
                  new AI_ProvinceValue(
                     CFG.game.getCiv(nCivID).lBordersWithWastelandProvincesID.get(i),
                     this.colonizeProvinces_ExtendColony_Score(nCivID, CFG.game.getCiv(nCivID).lBordersWithWastelandProvincesID.get(i))
                  )
               );
            }
         }

         for(int i = CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.size() - 1; i >= 0; --i) {
            for(int j = 0;
               j < CFG.game.getProvince(CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.get(i).iProvinceID).getNeighboringProvincesSize();
               ++j
            ) {
               if (CFG.game
                        .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.get(i).iProvinceID).getNeighboringProvinces(j))
                        .getCivID()
                     == 0
                  || CFG.game
                        .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.get(i).iProvinceID).getNeighboringProvinces(j))
                        .getWasteland()
                     >= 0) {
                  possibleProvinces.add(
                     new AI_ProvinceValue(
                        CFG.game.getProvince(CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.get(i).iProvinceID).getNeighboringProvinces(j),
                        this.colonizeProvinces_ExtendColony_Score(
                           nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.get(i).iProvinceID).getNeighboringProvinces(j)
                        )
                     )
                  );
               }
            }
         }

         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); ++j) {
               for(int k = 0;
                  k
                     < CFG.game
                        .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j))
                        .getNeighboringProvincesSize();
                  ++k
               ) {
                  if (!CFG.game
                        .getProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j))
                              .getNeighboringProvinces(k)
                        )
                        .getSeaProvince()
                     && CFG.game
                           .getProvince(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j))
                                 .getNeighboringProvinces(k)
                           )
                           .getCivID()
                        == 0) {
                     possibleProvinces.add(
                        new AI_ProvinceValue(
                           CFG.game
                              .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j))
                              .getNeighboringProvinces(k),
                           (int)(
                              (float)this.colonizeProvinces_ExtendColony_Score(
                                    nCivID,
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j))
                                       .getNeighboringProvinces(k)
                                 )
                                 * (
                                    CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j))
                                                   .getNeighboringProvinces(k)
                                             )
                                             .getNeighboringProvincesSize()
                                          == 0
                                       ? 1.0F
                                       : 0.625F
                                 )
                           )
                        )
                     );
                  }
               }
            }
         }

         if (possibleProvinces.size() > 0) {
            for(int i = possibleProvinces.size() - 1; i >= 0; --i) {
               for(int j = 0; j < CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvincesSize(); ++j) {
                  for(int k = 0;
                     k
                        < CFG.game
                           .getProvince(CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvinces(j))
                           .getNeighboringProvincesSize();
                     ++k
                  ) {
                     if (!CFG.game
                           .getProvince(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvinces(j))
                                 .getNeighboringProvinces(k)
                           )
                           .getSeaProvince()
                        && CFG.game
                              .getProvince(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                              .getNeighboringProvincesSize()
                           == 0
                        && CFG.game
                              .getProvince(
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                              .getCivID()
                           == 0) {
                        possibleProvinces.add(
                           new AI_ProvinceValue(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvinces(j))
                                 .getNeighboringProvinces(k),
                              this.colonizeProvinces_ExtendColony_Score(
                                 nCivID,
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(possibleProvinces.get(i).iProvinceID).getNeighboringSeaProvinces(j))
                                    .getNeighboringProvinces(k)
                              )
                           )
                        );
                     }
                  }
               }
            }

            int colonizeProvinceID = 0;

            for(int i = possibleProvinces.size() - 1; i > 0; --i) {
               if (possibleProvinces.get(colonizeProvinceID).iValue < possibleProvinces.get(i).iValue) {
                  colonizeProvinceID = i;
               } else if (possibleProvinces.get(colonizeProvinceID).iValue == possibleProvinces.get(i).iValue && CFG.oR.nextInt(100) < 50) {
                  colonizeProvinceID = i;
               }
            }

            if (CFG.game.getProvince(possibleProvinces.get(colonizeProvinceID).iProvinceID).getCivID() == 0) {
               if (CFG.gameAction.canColonizieWasteland_BorderOrArmy(possibleProvinces.get(colonizeProvinceID).iProvinceID, nCivID)) {
                  CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .addNewArmyMission(
                        possibleProvinces.get(colonizeProvinceID).iProvinceID,
                        new CivArmyMission_ColonizeProvince_Just(nCivID, possibleProvinces.get(colonizeProvinceID).iProvinceID)
                     );
                  CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Math.max(
                     CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID,
                     Game_Calendar.TURN_ID
                        + (int)((float)(8 + CFG.oR.nextInt(25) + CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS)
                  );
               } else {
                  CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .addNewArmyMission(
                        possibleProvinces.get(colonizeProvinceID).iProvinceID,
                        new CivArmyMission_ColonizeProvince(nCivID, possibleProvinces.get(colonizeProvinceID).iProvinceID)
                     );
                  CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID = Math.max(
                     CFG.game.getCiv(nCivID).civGameData.declareWar_CheckNextTurnID,
                     Game_Calendar.TURN_ID
                        + (int)((float)(8 + CFG.oR.nextInt(25) + CFG.oR.nextInt(CFG.game.getCivsSize() + 1) / 20) / Game_Calendar.AI_AGGRESSIVNESS)
                  );
               }

               return true;
            }
         }
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      } catch (IllegalArgumentException var7) {
         CFG.exceptionStack(var7);
      }

      return false;
   }

   protected final int colonizeProvinces_ExtendColony_Score(int nCivID, int nProvinceID) {
      float out = 1.0F;
      if (CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize() > 0) {
         int ownProvinces = 0;

         for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
               ++ownProvinces;
               out += 0.725F;
            }
         }

         out += CFG.game.getCiv(nCivID).civGameData.civPersonality.COLONIZATION_OWN_PROVINCES
            * (float)(ownProvinces / Math.max(CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(), 1));
      }

      out += CFG.game.getCiv(nCivID).civGameData.civPersonality.COLONIZATION_DISTANCE
         * (1.0F - CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nProvinceID));
      if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
         out += CFG.game.getCiv(nCivID).civGameData.civPersonality.COLONIZATION_SEA;
      }

      out += CFG.game.getCiv(nCivID).civGameData.civPersonality.COLONIZATION_GROWTH_RATE * CFG.game.getProvince(nProvinceID).getGrowthRate_Population();
      return (int)out;
   }

   protected final void checkPeaceTreaties(int nCivID) {
      if (CFG.game.getCiv(nCivID).isAtWar()) {
         Gdx.app.log("AoC", "checkPeaceTreaties -> " + CFG.game.getCiv(nCivID).getCivName());
         if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) {
         }
      }
   }

   protected final void recruitMilitary_MinSpendings(int nCivID) {
      try {
         Gdx.app
            .log(
               "AoC",
               "recruitMilitary_MinSpendings -> "
                  + CFG.game.getCiv(nCivID).getCivName()
                  + " -> MIN_MILITARY: "
                  + this.getMinMilitarySpendings(nCivID)
                  + " -> PERC"
                  + CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
            );
         int nUpkeepLeft = (int)(
            (float)CFG.game.getCiv(nCivID).iBudget * this.getMinMilitarySpendings(nCivID)
               - (float)CFG.game.getCiv(nCivID).iBudget * CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
         );
         Gdx.app.log("AoC", "recruitMilitary_MinSpendings -> " + CFG.game.getCiv(nCivID).getCivName() + " -> nUpkeepLeft: " + nUpkeepLeft);
         if (nUpkeepLeft > 0 && CFG.oAI.lFrontLines.get(nCivID - 1).size() > 0) {
            int tMaxDL = 1;
            float tMaxPotential = 1.0F;
            List<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<>();

            for(int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
               for(int j = CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.size() - 1; j >= 0; --j) {
                  boolean wasAdded = false;

                  for(int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                     if (tempFrontProvinces.get(k).iProvinceID == CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j)) {
                        wasAdded = true;
                        break;
                     }
                  }

                  if (!wasAdded) {
                     tempFrontProvinces.add(
                        new AI_ProvinceInfo(
                           CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j),
                           this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j), nCivID),
                           CFG.gameAction.getRecruitableArmy(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j))
                        )
                     );
                  }
               }
            }

            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringSeaProvincesSize() > 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
               boolean aldAdded = false;

               for(int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                  if (tempFrontProvinces.get(k).iProvinceID == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                     aldAdded = true;
                     break;
                  }
               }

               if (!aldAdded) {
                  tempFrontProvinces.add(
                     new AI_ProvinceInfo(
                        CFG.game.getCiv(nCivID).getCapitalProvinceID(),
                        this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID),
                        CFG.gameAction.getRecruitableArmy(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                     )
                  );
               }
            }

            if (tempFrontProvinces.size() > 0) {
               int tMaxArmy = 1;
               float tMaxRegion_NumOfProvinces = 1.0F;
               float tMaxRegion_Potential = 1.0F;
               List<Integer> tMovingArmy = new ArrayList<>();
               int i = 0;
               int iSize = tempFrontProvinces.size();

               for(int tempMovingArmy = 0; i < iSize; ++i) {
                  if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                     tMaxPotential = tempFrontProvinces.get(i).iValue;
                  }

                  if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                     tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy();
                  }

                  if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                     tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces();
                  }

                  if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                     tMaxRegion_Potential = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion();
                  }

                  tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
                  tMovingArmy.add(tempMovingArmy);
                  if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                     tMaxArmy = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
                  }
               }

               i = (int)(
                  (float)nUpkeepLeft
                     / (CFG.game_NextTurnUpdate.getMilitaryUpkeep_WithoutDefensivePosition(tempFrontProvinces.get(0).iProvinceID, 1000, nCivID) / 1000.0F)
               );
               iSize = 0;

               for(int iSizex = tempFrontProvinces.size(); iSize < iSizex; ++iSize) {
                  tempFrontProvinces.get(iSize).iValue = this.getValue_PositionOfArmy(
                     nCivID,
                     tempFrontProvinces,
                     iSize,
                     tMovingArmy.get(iSize),
                     tMaxPotential,
                     tMaxRegion_Potential,
                     tMaxDL,
                     tMaxArmy,
                     i,
                     tMaxRegion_NumOfProvinces
                  );
               }

               List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

               while(tempFrontProvinces.size() > 0) {
                  int tBest = 0;
                  int ix = 1;

                  for(int iSizex = tempFrontProvinces.size(); ix < iSizex; ++ix) {
                     if (tempFrontProvinces.get(tBest).iValue < tempFrontProvinces.get(ix).iValue) {
                        tBest = ix;
                     }
                  }

                  sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
                  tempFrontProvinces.remove(tBest);
               }

               int iNumOfMaxRecruitments = Math.max(
                  1,
                  Math.min(
                     (
                           CFG.game.getCiv(nCivID).getMovePoints()
                              - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE
                        )
                        / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT,
                     CFG.game.getCiv(nCivID).getNumOfProvinces()
                  )
               );
               List<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<>();
               float totalValues = 0.0F;

               for(int ix = 0; ix < iNumOfMaxRecruitments && ix < sortedFrontProvinces.size(); ++ix) {
                  tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(ix));
                  totalValues += sortedFrontProvinces.get(ix).iValue;
               }

               int tempMoneyPre = (int)CFG.game.getCiv(nCivID).getMoney();

               for(int ix = 0; ix < tRecruitArmiesForProvinces.size(); ++ix) {
                  int tArmyToRecruit_PRE = (int)(
                     (float)Math.min(i, tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(ix).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5))
                        * tRecruitArmiesForProvinces.get(ix).iValue
                        / totalValues
                  );
                  Gdx.app
                     .log(
                        "AoC",
                        "recruitMilitary_MinSpendings -> RECRUIT FOR: "
                           + tRecruitArmiesForProvinces.get(ix).iProvinceID
                           + " -> "
                           + CFG.game.getProvince(tRecruitArmiesForProvinces.get(ix).iProvinceID).getName()
                           + ", tArmyToRecruit_PRE: "
                           + tArmyToRecruit_PRE
                     );
                  boolean notEnoughRecruits = false;
                  if (tRecruitArmiesForProvinces.get(ix).iRecruitable < tArmyToRecruit_PRE) {
                     notEnoughRecruits = true;
                  }

                  if (!CFG.game.getProvince(tRecruitArmiesForProvinces.get(ix).iProvinceID).isOccupied()
                     && !notEnoughRecruits
                     && CFG.oR.nextInt(100) >= CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_RECRUIT_FROM_FAR_AWAY_CHANCE) {
                     int tArmyToRecruit = (int)(
                        (float)Math.min(
                              i,
                              Math.min(
                                 CFG.gameAction.getRecruitableArmy(tRecruitArmiesForProvinces.get(ix).iProvinceID),
                                 tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(ix).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                              )
                           )
                           * tRecruitArmiesForProvinces.get(ix).iValue
                           / totalValues
                     );
                     CFG.game.getCiv(nCivID).recruitArmy_AI(tRecruitArmiesForProvinces.get(ix).iProvinceID, tArmyToRecruit);
                  } else {
                     List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI
                        .getAllNeighboringProvincesInRange_Recruit(
                           tRecruitArmiesForProvinces.get(ix).iProvinceID, nCivID, 3, true, false, new ArrayList<>(), new ArrayList<>()
                        );
                     if (listOfPossibleProvincesToRecruit.size() > 0) {
                        int tempRand = 0;
                        if (notEnoughRecruits) {
                           int tBest = 0;
                           int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);

                           for(int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                              if (tBestArmy < CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID)) {
                                 tBest = k;
                                 tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID);
                              }
                           }

                           tempRand = tBest;
                        } else {
                           tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                        }

                        int tArmyToRecruit = (int)(
                           (float)Math.min(
                                 i,
                                 Math.min(
                                    CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID),
                                    tempMoneyPre
                                       / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                                 )
                              )
                              * tRecruitArmiesForProvinces.get(ix).iValue
                              / totalValues
                        );
                        CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, tArmyToRecruit);
                        int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID);
                        if (tempArmy > 0) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment(
                                    nCivID,
                                    listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID,
                                    tRecruitArmiesForProvinces.get(ix).iProvinceID,
                                    tempArmy
                                 )
                              );
                        }
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var24) {
         CFG.exceptionStack(var24);
      }
   }

   protected final void regroupArmy_AtPeace(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID <= Game_Calendar.TURN_ID) {
            List<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<>();
            List<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<>();
            List<AI_RegoupArmyData> armiesAtSea = new ArrayList<>();
            List<AI_RegoupArmyData> rest = new ArrayList<>();
            int numOfUnitsToRegoup = 0;

            for(int i = 0; i < CFG.game.getCiv(nCivID).iArmiesPositionSize; ++i) {
               int tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i));
               if (tArmyToRegroup > 0) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getSeaProvince()) {
                     armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getCivID() != nCivID) {
                     armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getDangerLevel() == 0) {
                     armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                     numOfUnitsToRegoup += tArmyToRegroup;
                  } else {
                     rest.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  }
               }
            }

            for(int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
               int tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
               if (tArmyToRegroup > 0) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getSeaProvince()) {
                     armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() != nCivID) {
                     armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getDangerLevel() == 0) {
                     armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                     numOfUnitsToRegoup += tArmyToRegroup;
                  } else {
                     rest.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  }
               }
            }

            Gdx.app.log("AoC", "regroupArmy_AtPeace -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesWithoutDanger.size: " + armiesWithoutDanger.size());
            Gdx.app
               .log(
                  "AoC",
                  "regroupArmy_AtPeace -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesInAnotherTerritory.size: " + armiesInAnotherTerritory.size()
               );
            Gdx.app.log("AoC", "regroupArmy_AtPeace -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesAtSea.size: " + armiesAtSea.size());
            if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces()) {
               armiesWithoutDanger.clear();
            }

            while(armiesWithoutDanger.size() > 0 || armiesAtSea.size() > 0 || armiesInAnotherTerritory.size() > 0) {
               int highestArmyID = -1;
               int highestArmy_Num = 0;
               int highestArmy_ListID = -1;

               for(int i = armiesWithoutDanger.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesWithoutDanger.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesWithoutDanger.get(i).iArmy;
                     highestArmy_ListID = 0;
                  }
               }

               for(int i = armiesAtSea.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesAtSea.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesAtSea.get(i).iArmy;
                     highestArmy_ListID = 1;
                  }
               }

               for(int i = armiesInAnotherTerritory.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesInAnotherTerritory.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesInAnotherTerritory.get(i).iArmy;
                     highestArmy_ListID = 2;
                  }
               }

               if (Game_Calendar.TURN_ID >= CFG.game.getCiv(nCivID).civGameData.nextArmyRestREgroupment_TurnID) {
                  for(int i = rest.size() - 1; i >= 0; --i) {
                     if (highestArmyID < 0 || highestArmy_Num < rest.get(i).iArmy) {
                        highestArmyID = i;
                        highestArmy_Num = rest.get(i).iArmy;
                        highestArmy_ListID = 3;
                     }
                  }
               }

               if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                  switch(highestArmy_ListID) {
                     case 0:
                        Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 0");
                        this.regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(nCivID, armiesWithoutDanger.get(highestArmyID), false);
                        armiesWithoutDanger.remove(highestArmyID);
                        break;
                     case 1:
                        Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 1");
                        this.regroupArmy_AtPeace_AtSea(nCivID, armiesAtSea.get(highestArmyID));
                        armiesAtSea.remove(highestArmyID);
                        break;
                     case 2:
                        Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 2");
                        this.regroupArmy_AtPeace_InAnotherTerritory(nCivID, armiesInAnotherTerritory.get(highestArmyID));
                        armiesInAnotherTerritory.remove(highestArmyID);
                        break;
                     case 3:
                        Gdx.app.log("AoC", "regroupArmy_AtPeace -> ACTION: 3");
                        this.regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(nCivID, rest.get(highestArmyID), true);
                        rest.remove(highestArmyID);
                        CFG.game.getCiv(nCivID).civGameData.nextArmyRestREgroupment_TurnID = Math.max(
                           CFG.game.getCiv(nCivID).civGameData.nextArmyRestREgroupment_TurnID, Game_Calendar.TURN_ID + 3 + CFG.oR.nextInt(9)
                        );
                  }
               }

               if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  || CFG.game.getCiv(nCivID).getMovePoints()
                     < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) {
                  break;
               }
            }

            if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces() || armiesAtSea.size() != 0) {
               CFG.game.getCiv(nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID = Game_Calendar.TURN_ID + 4 + CFG.oR.nextInt(4);
            }
         }
      } catch (IndexOutOfBoundsException var11) {
         CFG.exceptionStack(var11);
      } catch (StackOverflowError var12) {
         CFG.exceptionStack(var12);
      }
   }

   protected final boolean regroupArmy_AtPeace_AtSea(int nCivID, AI_RegoupArmyData nArmy) {
      List<AI_ProvinceInfo> possibleMoveTo = new ArrayList<>();

      for(int i = 0; i < CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
            possibleMoveTo.add(
               new AI_ProvinceInfo(
                  CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i),
                  this.getPotential_BasedOnNeighboringProvs(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i), nCivID),
                  1
               )
            );
         }
      }

      Gdx.app
         .log(
            "AoC",
            "regroupArmy_AtPeace_AtSea -> "
               + CFG.game.getCiv(nCivID).getCivName()
               + ", Province: "
               + nArmy.iProvinceID
               + ", NAME: "
               + CFG.game.getProvince(nArmy.iProvinceID).getName()
               + ", nArmy: "
               + nArmy.iArmy
               + ", possibleMoveTo.size: "
               + possibleMoveTo.size()
         );
      if (possibleMoveTo.size() > 0) {
         int tMaxArmy = 1;
         float tMaxPotential = 1.0F;
         float tMaxRegion_NumOfProvinces = 1.0F;
         float tMaxRegion_Potential = 1.0F;
         int tMaxDL = 1;
         List<Integer> tMovingArmy = new ArrayList<>();
         int i = 0;
         int iSize = possibleMoveTo.size();

         for(int tempMovingArmy = 0; i < iSize; ++i) {
            if (possibleMoveTo.get(i).iValue > tMaxPotential) {
               tMaxPotential = possibleMoveTo.get(i).iValue;
            }

            if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
               tMaxDL = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy();
            }

            if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
               tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces();
            }

            if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
               tMaxRegion_Potential = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion();
            }

            tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, possibleMoveTo.get(i).iProvinceID);
            tMovingArmy.add(tempMovingArmy);
            if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
               tMaxArmy = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
            }
         }

         i = 0;

         for(int iSizex = possibleMoveTo.size(); i < iSizex; ++i) {
            possibleMoveTo.get(i).iValue = this.getValue_PositionOfArmy(
               nCivID, possibleMoveTo, i, tMovingArmy.get(i), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, (float)nArmy.iArmy
            );
         }

         List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

         while(possibleMoveTo.size() > 0) {
            iSize = 0;
            int ix = 1;

            for(int iSizex = possibleMoveTo.size(); ix < iSizex; ++ix) {
               if (possibleMoveTo.get(iSize).iValue < possibleMoveTo.get(ix).iValue) {
                  iSize = ix;
               }
            }

            sortedFrontProvinces.add(possibleMoveTo.get(iSize));
            possibleMoveTo.remove(iSize);
         }

         float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01F);
         int iNumOfMaxMovements = 1;
         if (CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
            iNumOfMaxMovements = 1;
         } else {
            iNumOfMaxMovements = Math.max(
               1,
               Math.min(
                  (CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE)
                     / (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 2),
                  1 + CFG.oR.nextInt(3)
               )
            );
            if (percOfArmyToRegroup > 0.54F) {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 4);
            } else if (percOfArmyToRegroup > 0.34F) {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 3);
            } else if (percOfArmyToRegroup > 0.14F) {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 2);
            } else {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 1);
            }
         }

         Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> 000 -> iNumOfMaxMovements: " + iNumOfMaxMovements);
         List<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<>();
         float totalValues = 0.0F;

         for(int ix = 0; ix < iNumOfMaxMovements && ix < sortedFrontProvinces.size(); ++ix) {
            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(ix));
            totalValues += sortedFrontProvinces.get(ix).iValue;
         }

         Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> 000 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());

         for(int ix = 0; ix < tRecruitArmiesForProvinces.size(); ++ix) {
            int tArmyToRecruit_PRE = (int)Math.ceil((double)((float)nArmy.iArmy * tRecruitArmiesForProvinces.get(ix).iValue / totalValues));
            Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea -> 000 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
            if (tArmyToRecruit_PRE <= 0) {
               break;
            }

            RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID);
            if (tryRegroupArmy.getRouteSize() > 0) {
               if (tryRegroupArmy.getRouteSize() == 1) {
                  if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) {
                  }
               } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) {
                  tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                  tryRegroupArmy.removeRoute(0);
                  tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                  CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
               }
            }
         }

         return true;
      } else {
         for(int i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
               if (CFG.game
                     .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                     .getBasinID()
                  == CFG.game.getProvince(nArmy.iProvinceID).getBasinID()) {
                  possibleMoveTo.add(
                     new AI_ProvinceInfo(
                        CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i),
                        this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), nCivID),
                        1
                     )
                  );
                  break;
               }
            }
         }

         Gdx.app.log("AoC", "regroupArmy_AtPeace_AtSea ->  111, possibleMoveTo.size: " + possibleMoveTo.size());
         if (possibleMoveTo.size() > 0) {
            int tMaxArmy = 1;
            float tMaxPotential = 1.0F;
            float tMaxRegion_NumOfProvinces = 1.0F;
            float tMaxRegion_Potential = 1.0F;
            int tMaxDL = 1;
            List<Integer> tMovingArmy = new ArrayList<>();
            int i = 0;
            int iSize = possibleMoveTo.size();

            for(int tempMovingArmy = 0; i < iSize; ++i) {
               if (possibleMoveTo.get(i).iValue > tMaxPotential) {
                  tMaxPotential = possibleMoveTo.get(i).iValue;
               }

               if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                  tMaxDL = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy();
               }

               if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                  tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces();
               }

               if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                  tMaxRegion_Potential = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion();
               }

               tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, possibleMoveTo.get(i).iProvinceID);
               tMovingArmy.add(tempMovingArmy);
               if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                  tMaxArmy = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
               }
            }

            i = 0;

            for(int iSizex = possibleMoveTo.size(); i < iSizex; ++i) {
               possibleMoveTo.get(i).iValue = this.getValue_PositionOfArmy(
                  nCivID, possibleMoveTo, i, tMovingArmy.get(i), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, (float)nArmy.iArmy
               );
            }

            List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();
            if (possibleMoveTo.size() > 0) {
               iSize = 0;
               int ix = 1;

               for(int iSizex = possibleMoveTo.size(); ix < iSizex; ++ix) {
                  if (possibleMoveTo.get(iSize).iValue < possibleMoveTo.get(ix).iValue) {
                     iSize = ix;
                  }
               }

               sortedFrontProvinces.add(possibleMoveTo.get(iSize));
               possibleMoveTo.remove(iSize);
            }

            RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, sortedFrontProvinces.get(0).iProvinceID);
            if (tryRegroupArmy.getRouteSize() > 0) {
               if (tryRegroupArmy.getRouteSize() == 1) {
                  if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, sortedFrontProvinces.get(0).iProvinceID, nArmy.iArmy, nCivID, true, false)) {
                  }
               } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                  tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                  tryRegroupArmy.removeRoute(0);
                  tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                  CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
               }
            }

            return true;
         } else {
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
               RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(
                  nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID()
               );
               if (tryRegroupArmy.getRouteSize() > 0) {
                  if (tryRegroupArmy.getRouteSize() == 1) {
                     if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                     }
                  } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                     tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                     tryRegroupArmy.removeRoute(0);
                     tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                     CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                  }
               } else {
                  CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
               }
            } else {
               CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }

            return true;
         }
      }
   }

   protected final boolean regroupArmy_AtPeace_InAnotherTerritory(int nCivID, AI_RegoupArmyData nArmy) {
      try {
         float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01F);
         List<AI_NeighProvinces> listOfPossibleProvinces = CFG.oAI
            .getAllNeighboringProvincesInRange_OnlyOwn_Clear(
               nArmy.iProvinceID,
               nCivID,
               CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.game.getCiv(nCivID).getNumOfProvinces() / 15,
               false,
               false,
               new ArrayList<>(),
               new ArrayList<>()
            );
         Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory - > listOfPossibleProvinces.size: " + listOfPossibleProvinces.size());
         if (listOfPossibleProvinces.size() > 0) {
            int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints()
               / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
            if (percOfArmyToRegroup > 0.275F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 2);
            } else {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 1);
            }

            Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory -> 000");
            Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory -> 1111");
            List<Integer> tSortedIDs = new ArrayList<>();
            List<Integer> tData = new ArrayList<>();

            for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
               tData.add(i);
            }

            while(tData.size() > 0) {
               int tBest = 0;

               for(int i = tData.size() - 1; i > 0; --i) {
                  if (CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(tBest)).iProvinceID).getPotential()
                     < CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(i)).iProvinceID).getPotential()) {
                     tBest = i;
                  }
               }

               tSortedIDs.add(tData.get(tBest));
               tData.remove(tBest);
            }

            int nDangerTotal = 0;

            for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size(); ++i) {
               nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getPotential();
            }

            int tIDOfFisrttSuccesfulMovement = -1;
            Gdx.app.log("AoC", "regroupArmy_AtPeace_InAnotherTerritory -> 222, nNumOfPossibleMovements: " + nNumOfPossibleMovements);

            for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size() && nArmy.iArmy > 0; ++i) {
               RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(
                  nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID
               );
               if (tryRegroupArmy.getRouteSize() > 0) {
                  int tArmyToMove = i != nNumOfPossibleMovements && i != tSortedIDs.size() - 1
                     ? (int)Math.ceil(
                        (double)(
                           (float)nArmy.iArmy
                              * ((float)CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getPotential() / (float)nDangerTotal)
                        )
                     )
                     : nArmy.iArmy;
                  nArmy.iArmy -= tArmyToMove;
                  if (tArmyToMove <= 0) {
                     break;
                  }

                  if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) {
                     if (tryRegroupArmy.getRouteSize() > 1) {
                        CFG.game
                           .getCiv(nCivID)
                           .civGameData
                           .civPlans
                           .lArmiesMissions
                           .add(
                              new CivArmyMission_RegroupAfterRecruitment(
                                 nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID, tArmyToMove
                              )
                           );
                     }

                     tIDOfFisrttSuccesfulMovement = i;
                  }
               } else if (tIDOfFisrttSuccesfulMovement >= 0) {
                  tryRegroupArmy = new RegroupArmy_Data_AtPeace(
                     nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(tIDOfFisrttSuccesfulMovement)).iProvinceID
                  );
                  if (tryRegroupArmy.getRouteSize() > 0
                     && CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                     if (tryRegroupArmy.getRouteSize() > 1) {
                        CFG.game
                           .getCiv(nCivID)
                           .civGameData
                           .civPlans
                           .lArmiesMissions
                           .add(
                              new CivArmyMission_RegroupAfterRecruitment(
                                 nCivID,
                                 tryRegroupArmy.getRoute(0),
                                 listOfPossibleProvinces.get(tSortedIDs.get(tIDOfFisrttSuccesfulMovement)).iProvinceID,
                                 nArmy.iArmy
                              )
                           );
                     }

                     return true;
                  }
               }
            }

            if (tIDOfFisrttSuccesfulMovement >= 0) {
               return true;
            }
         } else if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
            && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
            RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
            if (tryRegroupArmy.getRouteSize() > 0) {
               if (tryRegroupArmy.getRouteSize() == 1) {
                  if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                  }
               } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                  tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                  tryRegroupArmy.removeRoute(0);
                  tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                  CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
               }
            } else {
               CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }
         } else {
            CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
         }
      } catch (IndexOutOfBoundsException var13) {
         CFG.exceptionStack(var13);
      } catch (StackOverflowError var14) {
         CFG.exceptionStack(var14);
      }

      return false;
   }

   protected final boolean regroupArmy_AtPeace_InOwnTerritory_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy, boolean rebuildLine) {
      try {
         float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01F);

         try {
            if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
               Gdx.app.log("AoC", "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> " + nArmy.iArmy);
               int tMaxDL = 1;
               float tMaxPotential = 1.0F;
               List<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<>();

               for(int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
                  try {
                     if (CFG.game.getProvince(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(0)).getCivRegionID()
                        == CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) {
                        for(int j = CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.size() - 1; j >= 0; --j) {
                           boolean wasAdded = false;

                           for(int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                              if (tempFrontProvinces.get(k).iProvinceID == CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j)) {
                                 wasAdded = true;
                                 break;
                              }
                           }

                           if (!wasAdded) {
                              tempFrontProvinces.add(
                                 new AI_ProvinceInfo(
                                    CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j),
                                    this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(j), nCivID),
                                    1
                                 )
                              );
                           }
                        }
                     }
                  } catch (IndexOutOfBoundsException var19) {
                  }
               }

               Gdx.app.log("AoC", "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
               if (tempFrontProvinces.size() > 0) {
                  int tMaxArmy = 1;
                  float tMaxRegion_NumOfProvinces = 1.0F;
                  float tMaxRegion_Potential = 1.0F;
                  List<Integer> tMovingArmy = new ArrayList<>();
                  int i = 0;
                  int iSize = tempFrontProvinces.size();

                  for(int tempMovingArmy = 0; i < iSize; ++i) {
                     if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                        tMaxPotential = tempFrontProvinces.get(i).iValue;
                     }

                     if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                        tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy();
                     }

                     if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                        tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces();
                     }

                     if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                        tMaxRegion_Potential = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion();
                     }

                     tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
                     tMovingArmy.add(tempMovingArmy);
                     if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                        tMaxArmy = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
                     }
                  }

                  i = 0;

                  for(int iSizex = tempFrontProvinces.size(); i < iSizex; ++i) {
                     tempFrontProvinces.get(i).iValue = this.getValue_PositionOfArmy(
                        nCivID,
                        tempFrontProvinces,
                        i,
                        tMovingArmy.get(i),
                        tMaxPotential,
                        tMaxRegion_Potential,
                        tMaxDL,
                        tMaxArmy,
                        nArmy.iArmy,
                        tMaxRegion_NumOfProvinces
                     );
                  }

                  List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

                  while(tempFrontProvinces.size() > 0) {
                     iSize = 0;
                     int ix = 1;

                     for(int iSizex = tempFrontProvinces.size(); ix < iSizex; ++ix) {
                        if (tempFrontProvinces.get(iSize).iValue < tempFrontProvinces.get(ix).iValue) {
                           iSize = ix;
                        }
                     }

                     sortedFrontProvinces.add(tempFrontProvinces.get(iSize));
                     tempFrontProvinces.remove(iSize);
                  }

                  iSize = 1;
                  if (rebuildLine) {
                     iSize = Math.max(
                        1,
                        Math.min(
                           (CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE)
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE,
                           Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3))
                        )
                     );
                  } else if (CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                     iSize = 1;
                  } else {
                     iSize = Math.max(
                        1,
                        Math.min(
                           (CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE)
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE,
                           Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3))
                        )
                     );
                  }

                  Gdx.app.log("AoC", "iNumOfMaxMovements: " + iSize);
                  List<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<>();
                  float totalValues = 0.0F;

                  for(int ix = 0; ix < iSize && ix < sortedFrontProvinces.size(); ++ix) {
                     tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(ix));
                     totalValues += sortedFrontProvinces.get(ix).iValue;
                  }

                  Gdx.app
                     .log(
                        "AoC",
                        "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size()
                     );

                  for(int ix = 0; ix < tRecruitArmiesForProvinces.size(); ++ix) {
                     int tArmyToRecruit_PRE = (int)Math.ceil((double)((float)nArmy.iArmy * tRecruitArmiesForProvinces.get(ix).iValue / totalValues));
                     Gdx.app
                        .log(
                           "AoC",
                           "regroupArmy_AtPeace_InOwnTerritory_WithoutDanger -> 111 -> nArmy.iArmy: "
                              + nArmy.iArmy
                              + ", tArmyToRecruit_PRE: "
                              + tArmyToRecruit_PRE
                        );
                     if (tArmyToRecruit_PRE <= 0) {
                        break;
                     }

                     RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(
                        nCivID, nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID
                     );
                     if (tryRegroupArmy.getRouteSize() > 0) {
                        if (tryRegroupArmy.getRouteSize() == 1) {
                           if (!CFG.gameAction
                              .moveArmy(nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) {
                           }
                        } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) {
                           tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                           tryRegroupArmy.removeRoute(0);
                           tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                           CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                     }
                  }

                  return true;
               }
            }
         } catch (NullPointerException var20) {
         }

         List<AI_NeighProvinces> listOfPossibleProvinces = CFG.oAI
            .getAllNeighboringProvincesInRange_OnlyOwn_Clear(
               nArmy.iProvinceID,
               nCivID,
               Math.max(CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES, CFG.game.getCiv(nCivID).getNumOfProvinces() / 10),
               false,
               false,
               new ArrayList<>(),
               new ArrayList<>()
            );
         if (listOfPossibleProvinces.size() > 0) {
            int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints()
               / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
            if (percOfArmyToRegroup > 0.375F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 4);
            } else if (percOfArmyToRegroup > 0.25F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 3);
            } else if (percOfArmyToRegroup > 0.1F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 2);
            } else {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 1);
            }

            boolean provincesWithDanger = false;

            for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
               if (CFG.game.getProvince(listOfPossibleProvinces.get(i).iProvinceID).getDangerLevel() > 0) {
                  provincesWithDanger = true;
                  break;
               }
            }

            if (provincesWithDanger) {
               List<Integer> tSortedIDs = new ArrayList<>();
               List<Integer> tData = new ArrayList<>();

               for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                  tData.add(i);
               }

               while(tData.size() > 0) {
                  int tBest = 0;

                  for(int i = tData.size() - 1; i > 0; --i) {
                     if (CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(tBest)).iProvinceID).getDangerLevel_WithArmy()
                        < CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(i)).iProvinceID).getDangerLevel_WithArmy()) {
                        tBest = i;
                     }
                  }

                  tSortedIDs.add(tData.get(tBest));
                  tData.remove(tBest);
               }

               int nDangerTotal = 0;

               for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size(); ++i) {
                  nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getDangerLevel_WithArmy();
               }

               int tIDOfFisrttSuccesfulMovement = -1;

               for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size() && nArmy.iArmy > 0; ++i) {
                  RegroupArmy_Data_AtPeace tryRegroupArmy = new RegroupArmy_Data_AtPeace(
                     nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID
                  );
                  if (tryRegroupArmy.getRouteSize() > 0) {
                     int tArmyToMove = i != nNumOfPossibleMovements && i != tSortedIDs.size() - 1
                        ? (int)Math.ceil(
                           (double)(
                              (float)nArmy.iArmy
                                 * (
                                    (float)CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getDangerLevel_WithArmy()
                                       / (float)nDangerTotal
                                 )
                           )
                        )
                        : nArmy.iArmy;
                     nArmy.iArmy -= tArmyToMove;
                     if (tArmyToMove <= 0) {
                        break;
                     }

                     if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) {
                        if (tryRegroupArmy.getRouteSize() > 1) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment(
                                    nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID, tArmyToMove
                                 )
                              );
                        }

                        tIDOfFisrttSuccesfulMovement = i;
                     }
                  } else if (tIDOfFisrttSuccesfulMovement >= 0) {
                     tryRegroupArmy = new RegroupArmy_Data_AtPeace(
                        nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(tIDOfFisrttSuccesfulMovement)).iProvinceID
                     );
                     if (tryRegroupArmy.getRouteSize() > 0
                        && CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                        if (tryRegroupArmy.getRouteSize() > 1) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment(
                                    nCivID,
                                    tryRegroupArmy.getRoute(0),
                                    listOfPossibleProvinces.get(tSortedIDs.get(tIDOfFisrttSuccesfulMovement)).iProvinceID,
                                    nArmy.iArmy
                                 )
                              );
                        }

                        return true;
                     }
                  }
               }

               if (tIDOfFisrttSuccesfulMovement >= 0) {
                  return true;
               }
            } else {
               Gdx.app.log("AoC", "DISBAND -> 11111111 -> " + nArmy.iArmy);
               if (CFG.game.getProvince(nArmy.iProvinceID).getCivID() != nCivID) {
                  CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
               } else if (!CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).isKeyRegion) {
                  CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
               }
            }
         }
      } catch (IndexOutOfBoundsException var21) {
         CFG.exceptionStack(var21);
      } catch (StackOverflowError var22) {
         CFG.exceptionStack(var22);
      }

      return false;
   }

   protected final int getRegroupArmy_NumOfUnits(int nCivID, int nProvinceID) {
      int out = CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID);

      for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).iProvinceID == nProvinceID) {
            out -= CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).iArmy;
         }
      }

      return out;
   }

   protected final float getValue_PositionOfArmy(
      int nCivID,
      List<AI_ProvinceInfo> tempFrontProvinces,
      int i,
      int tMovingArmy,
      float tMaxPotential,
      float tMaxRegion_Potential,
      int tMaxDL,
      int tMaxArmy,
      int numOfUnitsToRecruit_MAX,
      float tMaxRegion_NumOfProvinces
   ) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_POTENTIAL * (tempFrontProvinces.get(i).iValue / tMaxPotential)
         + CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_DANGER
            * ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL)
            * (
               1.0F
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS
                     * (
                        1.0F
                           - (float)(CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tMovingArmy)
                              / (
                                 (float)tMaxArmy
                                    + (float)numOfUnitsToRecruit_MAX * CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS_RECRUITMENT
                              )
                     )
            )
            * (
               1.0F
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_REGION_NUM_OF_PROVINCES
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_REGION_NUM_OF_PROVINCES
                     * (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces()
                     / tMaxRegion_NumOfProvinces
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_REGION_POTENTIAL
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_REGION_POTENTIAL
                     * (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion()
                     / tMaxRegion_Potential
            );
   }

   protected final void regroupArmyAfterRecruitment(int nCivID) {
      for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.REGRUOP_AFTER_RECRUIT
            && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).canMakeAction(nCivID, 0)
            && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
            CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
         }
      }
   }

   protected final void defendFromSeaInvasion(int nCivID) {
      List<Integer> provincesToDefend = new ArrayList<>();
      List<Integer> toArmies = new ArrayList<>();

      for(int i = CFG.game.getCiv(nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
         for(int j = 0; j < CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getMoveUnitsSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvinceID()).getCivID() == nCivID
               && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getFromProvinceID()).getSeaProvince()
               && !CFG.game.getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvinceID()).isOccupied()) {
               provincesToDefend.add(CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getToProvinceID());
               toArmies.add(CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getMoveUnits(j).getNumOfUnits());
            }
         }
      }

      while(provincesToDefend.size() > 0) {
         int tBest = 0;

         for(int j = 1; j < provincesToDefend.size(); ++j) {
            if (CFG.game.getProvince(provincesToDefend.get(tBest)).getPotential() < CFG.game.getProvince(provincesToDefend.get(j)).getPotential()) {
               tBest = j;
            }
         }

         if (CFG.game.getProvince(provincesToDefend.get(tBest)).getArmyCivID(nCivID) < toArmies.get(tBest)) {
            int requiredArmy = toArmies.get(tBest) - CFG.game.getProvince(provincesToDefend.get(tBest)).getArmyCivID(nCivID);
            requiredArmy = (int)Math.ceil((double)((float)requiredArmy * (1.025F + (float)CFG.oR.nextInt(85) / 1000.0F)));
            if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT) {
               if (CFG.game.getCiv(nCivID).getMoney() < (long)(5 * requiredArmy)
                  && CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT + 6
                  )
                {
                  int toTake = (int)((long)(5 * requiredArmy) - CFG.game.getCiv(nCivID).getMoney());
                  if (CFG.game.getCiv(nCivID).getMoney() + (long)toTake > 5L) {
                     DiplomacyManager.takeLoan(nCivID, toTake, 5);
                  }
               }

               if (CFG.game.getCiv(nCivID).getMoney() > 5L && CFG.game.getCiv(nCivID).recruitArmy_AI(provincesToDefend.get(tBest), requiredArmy)) {
               }
            }
         }

         provincesToDefend.remove(tBest);
         toArmies.remove(tBest);
         if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE) {
            break;
         }
      }
   }

   protected final void moveAtWar(int nCivID) {
      Gdx.app.log("AoC", "moveAtWar -> " + CFG.game.getCiv(nCivID).getCivName());

      try {
         boolean haveOwnFront = false;
         List<AI_ProvinceInfo_War> tempFrontProvinces = new ArrayList<>();

         for(int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
            if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID)) {
               haveOwnFront = true;

               for(int k = CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.size() - 1; k >= 0; --k) {
                  boolean add = true;

                  for(int o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                     if (tempFrontProvinces.get(o).iProvinceID == CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(k)) {
                        add = false;
                        break;
                     }
                  }

                  if (add) {
                     tempFrontProvinces.add(
                        new AI_ProvinceInfo_War(
                           CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(k),
                           this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(k), nCivID),
                           true
                        )
                     );
                  }
               }
            }
         }

         for(int o = 0; o < CFG.game.getCiv(nCivID).civGameData.iVassalsSize; ++o) {
            for(int i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).size() - 1; i >= 0; --i) {
               if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).iWithCivID)) {
                  for(int k = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.size() - 1;
                     k >= 0;
                     --k
                  ) {
                     boolean add = true;

                     for(int u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                        if (tempFrontProvinces.get(u).iProvinceID
                           == CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.get(k)) {
                           add = false;
                           break;
                        }
                     }

                     if (add) {
                        tempFrontProvinces.add(
                           new AI_ProvinceInfo_War(
                              CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.get(k),
                              this.getPotential_BasedOnNeighboringProvs(
                                 CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.get(k),
                                 CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID
                              ),
                              false
                           )
                        );
                     }
                  }
               }
            }
         }

         if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
            for(int i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).size() - 1; i >= 0; --i) {
               if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).iWithCivID)) {
                  for(int k = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.size() - 1; k >= 0; --k) {
                     boolean add = true;

                     for(int o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                        if (tempFrontProvinces.get(o).iProvinceID
                           == CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.get(k)) {
                           add = false;
                           break;
                        }
                     }

                     if (add) {
                        tempFrontProvinces.add(
                           new AI_ProvinceInfo_War(
                              CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.get(k),
                              this.getPotential_BasedOnNeighboringProvs(
                                 CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.get(k),
                                 CFG.game.getCiv(nCivID).getPuppetOfCivID()
                              ),
                              false
                           )
                        );
                     }
                  }
               }
            }
         }

         if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
            for(int o = 0; o < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++o) {
               if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) != nCivID) {
                  for(int i = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).size() - 1;
                     i >= 0;
                     --i
                  ) {
                     if (CFG.game
                        .getCivsAtWar(
                           nCivID,
                           CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i).iWithCivID
                        )) {
                        for(int k = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                 .lProvinces
                                 .size()
                              - 1;
                           k >= 0;
                           --k
                        ) {
                           boolean add = true;

                           for(int u = tempFrontProvinces.size() - 1; u >= 0; --u) {
                              if (tempFrontProvinces.get(u).iProvinceID
                                 == CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                    .lProvinces
                                    .get(k)) {
                                 add = false;
                                 break;
                              }
                           }

                           if (add) {
                              tempFrontProvinces.add(
                                 new AI_ProvinceInfo_War(
                                    CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                       .lProvinces
                                       .get(k),
                                    this.getPotential_BasedOnNeighboringProvs(
                                       CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                          .lProvinces
                                          .get(k),
                                       CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o)
                                    ),
                                    false
                                 )
                              );
                           }
                        }
                     }
                  }
               }
            }
         }

         Gdx.app.log("AoC", "CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size(): " + CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size());

         for(int e = CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.size() - 1; e >= 0; --e) {
            Gdx.app
               .log(
                  "AoC",
                  "CFG.game.getCiv(nCivID).lOpt_MilitirayAccess"
                     + e
                     + ": "
                     + CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)
                     + " "
                     + CFG.game.getCiv(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).getCivName()
               );

            for(int i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).size() - 1; i >= 0; --i) {
               if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).get(i).iWithCivID)) {
                  for(int k = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).get(i).lProvinces.size() - 1; k >= 0; --k) {
                     boolean add = true;

                     for(int o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                        if (tempFrontProvinces.get(o).iProvinceID
                           == CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).get(i).lProvinces.get(k)) {
                           add = false;
                           break;
                        }
                     }

                     if (add) {
                        tempFrontProvinces.add(
                           new AI_ProvinceInfo_War(
                              CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).get(i).lProvinces.get(k),
                              this.getPotential_BasedOnNeighboringProvs(
                                 CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e)).get(i).lProvinces.get(k),
                                 CFG.game.getCiv(nCivID).lOpt_MilitirayAccess.get(e) + 1
                              ),
                              false
                           )
                        );
                     }
                  }
               }
            }
         }

         Gdx.app.log("AoC", "moveAtWar -> tempFrontProvinces.size: " + tempFrontProvinces.size());
         if (tempFrontProvinces.size() > 0) {
            int tMaxDL = 1;
            float tMaxPotential = 1.0F;
            List<Integer> tMovingArmy_toFrontProvince = new ArrayList<>();
            int tMaxArmy = 1;
            float tMaxRegion_NumOfProvinces = 1.0F;
            float tMaxRegion_Potential = 1.0F;
            List<Integer> lFrontIDsWithArmies = new ArrayList<>();
            int i = tempFrontProvinces.size() - 1;

            for(int tempMovingArmy = 0; i >= 0; --i) {
               if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                  tMaxPotential = tempFrontProvinces.get(i).iValue;
               }

               if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                  tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy();
               }

               if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                  tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces();
               }

               if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                  tMaxRegion_Potential = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion();
               }

               tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
               tMovingArmy_toFrontProvince.add(tempMovingArmy);
               if (CFG.game.getProvinceArmy(tempFrontProvinces.get(i).iProvinceID) + tempMovingArmy > tMaxArmy) {
                  tMaxArmy = CFG.game.getProvinceArmy(tempFrontProvinces.get(i).iProvinceID) + tempMovingArmy;
               }
            }

            for(int ix = tempFrontProvinces.size() - 1; ix >= 0; --ix) {
               tempFrontProvinces.get(ix).iValue = (
                        CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_POTENTIAL * (tempFrontProvinces.get(ix).iValue / tMaxPotential)
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_DANGER
                              * ((float)CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL)
                           + 1.0F
                           - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES
                              * (float)CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getRegion_NumOfProvinces()
                              / tMaxRegion_NumOfProvinces
                           - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL
                              * (float)CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getPotentialRegion()
                              / tMaxRegion_Potential
                     )
                     * (
                        1.0F
                           - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_ATTACK_DISTANCE
                              * CFG.game_NextTurnUpdate
                                 .getDistanceFromAToB_PercOfMax(
                                    CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID), tempFrontProvinces.get(ix).iProvinceID
                                 )
                     )
                  + 1.0F
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS
                     * (
                        1.0F
                           - (float)(CFG.game.getProvinceArmy(tempFrontProvinces.get(ix).iProvinceID) + tMovingArmy_toFrontProvince.get(ix)) / (float)tMaxArmy
                     )
                     * (
                        CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getNeighbooringProvinceOfCivWasLost() > 0
                           ? 0.55F + (float)CFG.oR.nextInt(30) / 100.0F
                           : 1.0F
                     );
            }

            List<AI_ProvinceInfo_War> sortedFrontProvinces = new ArrayList<>();

            for(int tID = 0; tempFrontProvinces.size() > 0; ++tID) {
               int tBest = 0;
               int ix = 1;

               for(int iSize = tempFrontProvinces.size(); ix < iSize; ++ix) {
                  if (tempFrontProvinces.get(tBest).iValue < tempFrontProvinces.get(ix).iValue) {
                     tBest = ix;
                  } else if (tempFrontProvinces.get(tBest).iValue == tempFrontProvinces.get(ix).iValue && CFG.oR.nextInt(100) < 50) {
                     tBest = ix;
                  }
               }

               if (CFG.game.getProvince(tempFrontProvinces.get(tBest).iProvinceID).getArmyCivID(nCivID) > 0) {
                  lFrontIDsWithArmies.add(tID);
               }

               sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
               tempFrontProvinces.remove(tBest);
            }

            this.moveAtWar_Regroup(nCivID, sortedFrontProvinces, lFrontIDsWithArmies);
            Gdx.app.log("AoC", "moveAtWar -> BEFORE RECRUIT MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
            if (CFG.game.getCiv(nCivID).getMoney() > 5L && CFG.game.getCiv(nCivID).iBudget > 0) {
               boolean canRecruitAndMove = (float)lFrontIDsWithArmies.size()
                     * 1.75F
                     * (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  <= (float)(
                     CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
                  );
               if (!canRecruitAndMove
                  && !(
                     (float)(CFG.game.getCiv(nCivID).getMoney() / 5L)
                           * (
                              CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn < 0
                                 ? 0.16F + 0.03F * (float)CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn
                                 : (CFG.game.getCiv(nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn ? 0.6F : 0.75F)
                           )
                        > (float)CFG.game.getCiv(nCivID).getNumOfUnits()
                  )
                  && CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn >= -3
                  && CFG.game.getCiv(nCivID).getNumOfProvinces() >= 3
                  && CFG.oR.nextInt(100) >= 6) {
                  Gdx.app.log("AoC", "moveAtWar -> BEFORE RECRUIT 000: false");
               } else {
                  Gdx.app.log("AoC", "moveAtWar -> BEFORE RECRUIT 000: true");
                  this.moveAtWar_Recruit(nCivID, sortedFrontProvinces, lFrontIDsWithArmies, false);
               }

               CFG.game.getCiv(nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = false;
            }

            Gdx.app.log("AoC", "moveAtWar -> AFTER RECRUIT -> ATTACK MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
            int numOfPossibleMoves = CFG.game.getCiv(nCivID).getMovePoints()
               / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
            if (numOfPossibleMoves > 0) {
               List<Float> lScores = new ArrayList<>();
               float score_MaxArmy = 1.0F;
               float score_MaxPotenialProvinces = 1.0F;

               for(int ix = lFrontIDsWithArmies.size() - 1; ix >= 0; --ix) {
                  if (CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getArmyCivID(nCivID) < 10) {
                     lFrontIDsWithArmies.remove(ix);
                  }
               }

               for(int ix = lFrontIDsWithArmies.size() - 1; ix >= 0; --ix) {
                  if (score_MaxArmy < (float)CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getArmyCivID(nCivID)) {
                     score_MaxArmy = (float)CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getArmyCivID(nCivID);
                  }

                  if (score_MaxPotenialProvinces
                     < (float)CFG.game
                        .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID)
                        .getPotentialModified_WAR_MoveFrom(nCivID)) {
                     score_MaxPotenialProvinces = (float)CFG.game
                        .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID)
                        .getPotentialModified_WAR_MoveFrom(nCivID);
                  }
               }

               for(int ix = 0; ix < lFrontIDsWithArmies.size(); ++ix) {
                  lScores.add(
                     CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_ARMY
                           * (float)CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getArmyCivID(nCivID)
                           / score_MaxArmy
                        + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_POTENTIAL
                           * (float)CFG.game
                              .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID)
                              .getPotentialModified_WAR_MoveFrom(nCivID)
                           / score_MaxPotenialProvinces
                        + (
                           CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getWasConquered() > 0
                              ? CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_ATTACK_SCORE_WAS_CONQUERED
                              : 0.0F
                        )
                        + (
                           CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getIsNotSuppliedForXTurns() > 0
                              ? 0.275F
                                 + 2.5F
                                    * (float)CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(ix)).iProvinceID).getArmyCivID(nCivID)
                                    / score_MaxArmy
                              : 0.0F
                        )
                  );
               }

               List<Integer> tSorted = new ArrayList<>();
               List<Integer> tempIDs = new ArrayList<>();

               for(int ix = lFrontIDsWithArmies.size() - 1; ix >= 0; --ix) {
                  tempIDs.add(ix);
               }

               while(tempIDs.size() > 0) {
                  int tBest = 0;

                  for(int ix = tempIDs.size() - 1; ix > 0; --ix) {
                     if (lScores.get(tempIDs.get(tBest)) < lScores.get(tempIDs.get(ix))) {
                        tBest = ix;
                     }
                  }

                  tSorted.add(tempIDs.get(tBest));
                  tempIDs.remove(tBest);
               }

               Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
               Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tSorted.size(): " + tSorted.size());
               int ix = 0;

               for(int iSize = tSorted.size(); ix < iSize; ++ix) {
                  lScores.clear();
                  tempIDs.clear();
                  if (CFG.oR.nextInt(100) < 65) {
                     for(int j = 0;
                        j < CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getNeighboringProvincesSize();
                        ++j
                     ) {
                        if (CFG.game
                              .getCivsAtWar(
                                 nCivID,
                                 CFG.game
                                    .getProvince(
                                       CFG.game
                                          .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                          .getNeighboringProvinces(j)
                                    )
                                    .getCivID()
                              )
                           && !CFG.game
                              .getCiv(nCivID)
                              .isMovingUnitsToProvinceID(
                                 CFG.game
                                    .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                    .getNeighboringProvinces(j)
                              )) {
                           tempIDs.add(
                              CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getNeighboringProvinces(j)
                           );
                           lScores.add(
                              this.moveAtWar_AttackTo_Score(
                                 nCivID,
                                 CFG.game
                                    .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                    .getNeighboringProvinces(j)
                              )
                           );
                        }
                     }
                  }

                  if (tempIDs.size() == 0) {
                     for(int j = 0;
                        j < CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getNeighboringProvincesSize();
                        ++j
                     ) {
                        if (CFG.game
                           .getCivsAtWar(
                              nCivID,
                              CFG.game
                                 .getProvince(
                                    CFG.game
                                       .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                       .getNeighboringProvinces(j)
                                 )
                                 .getCivID()
                           )) {
                           tempIDs.add(
                              CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getNeighboringProvinces(j)
                           );
                           lScores.add(
                              this.moveAtWar_AttackTo_Score(
                                    nCivID,
                                    CFG.game
                                       .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                       .getNeighboringProvinces(j)
                                 )
                                 * (
                                    CFG.game
                                          .getCiv(nCivID)
                                          .isMovingUnitsToProvinceID(
                                             CFG.game
                                                .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                                .getNeighboringProvinces(j)
                                          )
                                       ? 0.625F
                                       : 1.0F
                                 )
                           );
                        }
                     }
                  }

                  Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tempIDs.size(): " + tempIDs.size());
                  if (this.plunderProvince(nCivID, sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)) {
                     int maxArmy = this.getRegroupArmy_NumOfUnits(nCivID, sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID);
                     maxArmy = (int)Math.max(
                        DiplomacyManager.plunderEfficiency_RequiredMAX(nCivID, sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID),
                        (float)maxArmy
                     );
                     DiplomacyManager.plunderProvince(nCivID, sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID, maxArmy);
                  }

                  if (tempIDs.size() > 0) {
                     if (tempIDs.size() > 1
                        && CFG.game.getCiv(nCivID).getMovePoints()
                           >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 2) {
                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 0000");
                        int tArmyToMove = this.getRegroupArmy_NumOfUnits(
                           nCivID, sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID
                        );
                        if (tArmyToMove > 0) {
                           Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. tArmyToMove: " + tArmyToMove);
                           int numOfMoves = CFG.game.getCiv(nCivID).getMovePoints()
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
                           Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. numOfMoves: " + numOfMoves);
                           List<Integer> sortedMoveTo = new ArrayList<>();
                           List<Integer> tData = new ArrayList<>();

                           for(int o = lScores.size() - 1; o >= 0; --o) {
                              tData.add(o);
                           }

                           while(tData.size() > 0) {
                              int tBest = 0;

                              for(int o = tData.size() - 1; o > 0; --o) {
                                 if (lScores.get(tData.get(tBest)) < lScores.get(tData.get(o))) {
                                    tBest = o;
                                 }
                              }

                              sortedMoveTo.add(tData.get(tBest));
                              tData.remove(tBest);
                           }

                           numOfMoves = Math.min(numOfMoves, tempIDs.size());
                           Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. numOfMoves2: " + numOfMoves);
                           float totalScore = 0.0F;

                           for(int o = 0; o < sortedMoveTo.size(); ++o) {
                              totalScore += lScores.get(sortedMoveTo.get(o));
                           }

                           List<Boolean> checkJoinProvinces = new ArrayList<>();

                           for(int o = 0; o < numOfMoves; ++o) {
                              Gdx.app
                                 .log(
                                    "AoC",
                                    "moveAtWar -> START ATTACKING.. 000 -> ATTACK ID: "
                                       + o
                                       + ", lScores.get(o): "
                                       + lScores.get(o)
                                       + ", totalScore: "
                                       + totalScore
                                 );
                              int armyToMove_PRE = (int)Math.ceil((double)((float)tArmyToMove * lScores.get(o) / totalScore));
                              if (CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getWasAttacked() > 0
                                 || CFG.game
                                          .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                          .getLevelOfWatchTower()
                                       > 0
                                    && CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getLevelOfFort() <= 0) {
                                 Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 000");
                                 int enemyArmy = (int)(
                                    (float)(
                                          CFG.game.getProvinceArmy(tempIDs.get(sortedMoveTo.get(o)))
                                             + this.getEnemyArmy_ExtraMovedArmy(tempIDs.get(sortedMoveTo.get(o)))
                                       )
                                       * 1.05F
                                 );
                                 if (armyToMove_PRE < enemyArmy) {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 111");
                                    if (CFG.game
                                          .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                          .getArmyCivID(nCivID)
                                       > enemyArmy) {
                                       Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 222");
                                       armyToMove_PRE = (int)Math.min(
                                          (float)CFG.game
                                             .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                             .getArmyCivID(nCivID),
                                          (float)CFG.game.getProvinceArmy(tempIDs.get(sortedMoveTo.get(o))) * (1.04F + (float)CFG.oR.nextInt(20) / 100.0F)
                                       );
                                       tArmyToMove -= armyToMove_PRE;
                                       totalScore = Math.max(1.0F, totalScore - lScores.get(o));
                                    } else {
                                       Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 333");
                                       if (enemyArmy
                                          < CFG.game.getProvinceArmy(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)) {
                                          Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 444");
                                       } else {
                                          Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 555");
                                          int armyJoinProvinces = 0;

                                          for(int m = 0; m < CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvincesSize(); ++m) {
                                             if (CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m)
                                                != sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID) {
                                                armyJoinProvinces += CFG.game
                                                   .getProvince(CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m))
                                                   .getArmyCivID(nCivID);
                                             }
                                          }

                                          if (enemyArmy >= armyToMove_PRE + armyJoinProvinces) {
                                             Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 777, CONTINUE");
                                             checkJoinProvinces.add(false);
                                             continue;
                                          }

                                          Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 666");
                                       }
                                    }
                                 } else {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 888");
                                 }
                              } else {
                                 Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> CHECK 999");
                              }

                              checkJoinProvinces.add(true);
                              if (!CFG.gameAction
                                 .moveArmy(
                                    sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID,
                                    tempIDs.get(sortedMoveTo.get(o)),
                                    armyToMove_PRE,
                                    nCivID,
                                    true,
                                    false
                                 )) {
                                 Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 000 -> ATTACK ID: " + o + ", break");
                                 break;
                              }

                              Gdx.app
                                 .log(
                                    "AoC",
                                    "moveAtWar -> START ATTACKING.. 000 FROM: "
                                       + sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID
                                       + " -> "
                                       + CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getName()
                                       + " TO: "
                                       + tempIDs.get(sortedMoveTo.get(o))
                                       + " -> "
                                       + CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getName()
                                 );
                           }

                           if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE
                              <= CFG.game.getCiv(nCivID).getMovePoints()) {
                              for(int k = 0; k < checkJoinProvinces.size(); ++k) {
                                 if (checkJoinProvinces.get(k)) {
                                    for(int o = 0; o < numOfMoves; ++o) {
                                       for(int m = 0; m < CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvincesSize(); ++m) {
                                          if (CFG.game
                                                   .getProvince(CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m))
                                                   .getArmyCivID(nCivID)
                                                > 0
                                             && (
                                                CFG.game
                                                         .getProvince(CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m))
                                                         .getCivID()
                                                      != nCivID
                                                   || this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(
                                                         nCivID, CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m)
                                                      )
                                                      <= 1
                                             )) {
                                             if (CFG.gameAction
                                                .moveArmy(
                                                   CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m),
                                                   tempIDs.get(sortedMoveTo.get(o)),
                                                   CFG.game
                                                      .getProvince(CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m))
                                                      .getArmyCivID(nCivID),
                                                   nCivID,
                                                   true,
                                                   false
                                                )) {
                                                Gdx.app
                                                   .log(
                                                      "AoC",
                                                      "moveAtWar -> START ATTACKING.. 000 JOIN: "
                                                         + CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m)
                                                         + " -> "
                                                         + CFG.game
                                                            .getProvince(CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getNeighboringProvinces(m))
                                                            .getName()
                                                         + " TO: "
                                                         + tempIDs.get(sortedMoveTo.get(o))
                                                         + " -> "
                                                         + CFG.game.getProvince(tempIDs.get(sortedMoveTo.get(o))).getName()
                                                   );
                                             } else if (CFG.game.getCiv(nCivID).getMovePoints()
                                                < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE) {
                                                break;
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     } else {
                        int tBestMoveTo = 0;

                        for(int o = lScores.size() - 1; o > 0; --o) {
                           if (lScores.get(tBestMoveTo) < lScores.get(o)) {
                              tBestMoveTo = o;
                           }
                        }

                        Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111");
                        Gdx.app
                           .log(
                              "AoC",
                              "moveAtWar -> START ATTACKING.. 1111 FROM: "
                                 + sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID
                                 + " -> "
                                 + CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getName()
                                 + ", TO: "
                                 + tempIDs.get(tBestMoveTo)
                                 + " -> "
                                 + CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getName()
                                 + ", ARMY: "
                                 + CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getArmyCivID(nCivID)
                           );
                        float totalScore = 0.0F;

                        for(int k = tempIDs.size() - 1; k >= 0; --k) {
                           if (!CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(tempIDs.get(k))) {
                              totalScore += lScores.get(k);
                           }
                        }

                        int armyToMove_PRE;
                        if (totalScore > 0.0F && CFG.oR.nextInt(100) < 90) {
                           armyToMove_PRE = (int)Math.ceil(
                              (double)(
                                 (float)CFG.game
                                       .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                       .getArmyCivID(nCivID)
                                    * lScores.get(tBestMoveTo)
                                    / totalScore
                              )
                           );
                        } else {
                           armyToMove_PRE = CFG.game
                              .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                              .getArmyCivID(nCivID);
                        }

                        if (CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getWasAttacked() > 0
                           || CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getLevelOfWatchTower() > 0
                              && CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getLevelOfFort() <= 0) {
                           Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 000");
                           int enemyArmy = (int)(
                              (float)(CFG.game.getProvinceArmy(tempIDs.get(tBestMoveTo)) + this.getEnemyArmy_ExtraMovedArmy(tempIDs.get(tBestMoveTo))) * 1.05F
                           );
                           if (armyToMove_PRE >= enemyArmy) {
                              Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 888");
                           } else {
                              Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 111");
                              if (CFG.game.getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID).getArmyCivID(nCivID)
                                 > enemyArmy) {
                                 Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 222");
                                 armyToMove_PRE = (int)Math.min(
                                    (float)CFG.game
                                       .getProvince(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)
                                       .getArmyCivID(nCivID),
                                    (float)CFG.game.getProvinceArmy(tempIDs.get(tBestMoveTo)) * (1.04F + (float)CFG.oR.nextInt(20) / 100.0F)
                                 );
                              } else {
                                 Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 333");
                                 if (enemyArmy < CFG.game.getProvinceArmy(sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID)) {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 444");
                                 } else {
                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 555");
                                    int armyJoinProvinces = 0;

                                    for(int m = 0; m < CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvincesSize(); ++m) {
                                       if (CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)
                                          != sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID) {
                                          armyJoinProvinces += CFG.game
                                             .getProvince(CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m))
                                             .getArmyCivID(nCivID);
                                       }
                                    }

                                    if (enemyArmy >= armyToMove_PRE + armyJoinProvinces) {
                                       Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 777, CONTINUE");
                                       continue;
                                    }

                                    Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 666");
                                 }
                              }
                           }
                        } else {
                           Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 111 -> CHECK 999");
                        }

                        if (CFG.gameAction
                           .moveArmy(
                              sortedFrontProvinces.get(lFrontIDsWithArmies.get(tSorted.get(ix))).iProvinceID,
                              tempIDs.get(tBestMoveTo),
                              armyToMove_PRE,
                              nCivID,
                              true,
                              false
                           )) {
                           Gdx.app.log("AoC", "moveAtWar -> START ATTACKING.. 1111 MOVED");

                           for(int m = 0; m < CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvincesSize(); ++m) {
                              if (CFG.game.getProvince(CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)).getArmyCivID(nCivID) > 0
                                 && (
                                    CFG.game.getProvince(CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)).getCivID() != nCivID
                                       || this.moveAtWar_NumOfNotCoveredNeighEnemyProvinces(
                                             nCivID, CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)
                                          )
                                          <= 1
                                 )) {
                                 if (CFG.gameAction
                                    .moveArmy(
                                       CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m),
                                       tempIDs.get(tBestMoveTo),
                                       CFG.game.getProvince(CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)).getArmyCivID(nCivID),
                                       nCivID,
                                       true,
                                       false
                                    )) {
                                    Gdx.app
                                       .log(
                                          "AoC",
                                          "moveAtWar -> START ATTACKING.. 222 FROM: "
                                             + CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)
                                             + " -> "
                                             + CFG.game.getProvince(CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getNeighboringProvinces(m)).getName()
                                             + " TO: "
                                             + tempIDs.get(tBestMoveTo)
                                             + " -> "
                                             + CFG.game.getProvince(tempIDs.get(tBestMoveTo)).getName()
                                       );
                                 } else if (CFG.game.getCiv(nCivID).getMovePoints()
                                    < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE) {
                                    break;
                                 }
                              }
                           }
                        } else if (CFG.game.getCiv(nCivID).getMovePoints()
                           < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE) {
                           break;
                        }
                     }
                  }

                  Gdx.app.log("AoC", "moveAtWar -> START ATTACKING END");
               }
            }
         } else if (CFG.game.getCiv(nCivID).civGameData.iNextCheckMilitaryAccessTurnID <= Game_Calendar.TURN_ID && CFG.oR.nextInt(100) < 72) {
            List<Integer> askForAccess = new ArrayList<>();

            for(int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
               if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID)) {
                  for(int j = CFG.oAI.lFrontLines.get(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID - 1).size() - 1; j >= 0; --j) {
                     if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID - 1).get(j).iWithCivID)
                        && (
                           CFG.game.getCiv(nCivID).iBudget
                                 > CFG.game.getCiv(CFG.oAI.lFrontLines.get(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID - 1).get(j).iWithCivID).iBudget
                              || CFG.oR.nextInt(100) < 6
                        )) {
                        boolean wasAdded = false;

                        for(int z = askForAccess.size() - 1; z >= 0; --z) {
                           if (askForAccess.get(z) == CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID) {
                              wasAdded = true;
                              break;
                           }
                        }

                        if (!wasAdded) {
                           askForAccess.add(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID);
                        }
                     }
                  }
               }
            }

            if (askForAccess.size() > 0) {
               int tRand;
               for(; askForAccess.size() > 0 && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10; askForAccess.remove(tRand)) {
                  tRand = CFG.oR.nextInt(askForAccess.size());
                  if (CFG.game.getMilitaryAccess(nCivID, askForAccess.get(tRand)) <= 10
                     && !CFG.game.getCiv(nCivID).messageWasSent(askForAccess.get(tRand), Message_Type.MILITARY_ACCESS_ASK)) {
                     DiplomacyManager.sendMilitaryAccess_AskProposal(askForAccess.get(tRand), nCivID, 40);
                  }
               }

               CFG.game.getCiv(nCivID).civGameData.iNextCheckMilitaryAccessTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(20);
            }
         } else if (CFG.game.getCiv(nCivID).civGameData.iNextCheckMilitaryAccessSeaTurnID <= Game_Calendar.TURN_ID) {
            List<Integer> askForAccess2 = new ArrayList<>();

            for(int i = CFG.game.getCiv(nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
               if (CFG.game.getCiv(nCivID).iBudget > CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).iBudget
                  && CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getSeaAccess() == 0) {
                  for(int z = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i) - 1).size() - 1; z >= 0; --z) {
                     if (!CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i) - 1).get(z).iWithCivID)
                        && CFG.game.getCiv(CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i) - 1).get(z).iWithCivID).getSeaAccess() > 0) {
                        boolean wasAdded = false;

                        for(int o = askForAccess2.size() - 1; o >= 0; --o) {
                           if (askForAccess2.get(o) == CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i) - 1).get(z).iWithCivID) {
                              wasAdded = true;
                              break;
                           }
                        }

                        if (!wasAdded) {
                           askForAccess2.add(CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i) - 1).get(z).iWithCivID);
                        }
                     }
                  }
               }
            }

            int tRand;
            if (askForAccess2.size() > 0) {
               for(; askForAccess2.size() > 0 && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10; askForAccess2.remove(tRand)) {
                  tRand = CFG.oR.nextInt(askForAccess2.size());
                  if (CFG.game.getMilitaryAccess(nCivID, askForAccess2.get(tRand)) <= 10
                     && !CFG.game.getCiv(nCivID).messageWasSent(askForAccess2.get(tRand), Message_Type.MILITARY_ACCESS_ASK)) {
                     DiplomacyManager.sendMilitaryAccess_AskProposal(askForAccess2.get(tRand), nCivID, 40);
                  }
               }
            }

            CFG.game.getCiv(nCivID).civGameData.iNextCheckMilitaryAccessSeaTurnID = Game_Calendar.TURN_ID + 6 + CFG.oR.nextInt(20);
         }

         this.moveAtWar_AtSea(nCivID);
      } catch (IndexOutOfBoundsException var32) {
         Gdx.app.log("AoC", "moveAtWar -> ERRRORR");
         CFG.exceptionStack(var32);
      } catch (ArithmeticException var33) {
         Gdx.app.log("AoC", "moveAtWar -> ERRRORR");
         CFG.exceptionStack(var33);
      }

      Gdx.app.log("AoC", "moveAtWar -> END, movementPoints:" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
   }

   protected final boolean plunderProvince(int nCivID, int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).isOccupied()
         && (float)CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID) < (float)CFG.game.getCiv(nCivID).getNumOfUnits() * 0.235F
         && CFG.game.getCiv(nCivID).civGameData.iPlunder_LastTurnID <= Game_Calendar.TURN_ID) {
         int possibleArmy = this.getRegroupArmy_NumOfUnits(nCivID, nProvinceID);
         if ((float)possibleArmy / DiplomacyManager.plunderEfficiency_RequiredMAX(nCivID, nProvinceID) > 0.45F
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_PLUNDER <= CFG.game.getCiv(nCivID).getMovePoints()) {
            if (CFG.game.getCiv(nCivID).civGameData.civPersonality.PLUNDER_CHANCE > (float)CFG.oR.nextInt(1000) / 1000.0F) {
               if (CFG.oR.nextInt(100) < this.PERSONALITY_PLUNDER_LOCK) {
                  CFG.game.getCiv(nCivID).civGameData.iPlunder_LastTurnID = Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(4);
               } else {
                  CFG.game.getCiv(nCivID).civGameData.iPlunder_LastTurnID = Game_Calendar.TURN_ID + 2 + CFG.oR.nextInt(4);
               }

               return true;
            }

            return true;
         }
      }

      return false;
   }

   protected final int getEnemyArmy_ExtraMovedArmy(int nProvinceID) {
      int out = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); ++i) {
         for(int j = 0; j < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getMoveUnitsSize(); ++j) {
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getMoveUnits(j).getFromProvinceID() == nProvinceID) {
               out += CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getMoveUnits(j).getNumOfUnits();
            }
         }
      }

      return out;
   }

   protected final int moveAtWar_AtSea_RunMissions(int nCivID) {
      int outActiveMissions = 0;

      for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.NAVAL_INVASION
            && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).canMakeAction(nCivID, 0)) {
            if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
               CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
            } else if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).iObsolate <= 0) {
               CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
            } else {
               ++outActiveMissions;
            }
         }
      }

      return outActiveMissions;
   }

   protected final void moveAtWar_AtSea(int nCivID) {
      if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE) {
         Gdx.app.log("AoC", "moveAtWar -> BY SEA");
         int numOfCurrentInvasions = this.moveAtWar_AtSea_RunMissions(nCivID);
         Gdx.app.log("AoC", "moveAtWar -> BY SEA -> numOfCurrentInvasions: " + numOfCurrentInvasions);
         if (Game_Calendar.TURN_ID <= CFG.game.getCiv(nCivID).civGameData.iNextPossibleNavalInvastionTurnID) {
            return;
         }

         if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0 && numOfCurrentInvasions > 0) {
            return;
         }

         if ((float)numOfCurrentInvasions >= Math.max(1.0F, (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / 10.0F)) {
            return;
         }

         if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) {
            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> NO NEIGHBORING ENEMIES, LOOK FOR SEA PROVINCES");
            boolean canBuildPort = BuildingsManager.canBuildPort(CFG.game.getCiv(nCivID).getProvinceID(0));
            if (CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces_Size() == 0 && !canBuildPort) {
               Gdx.app.log("AoC", "moveAtWar -> BY SEA -> CANT GO TO THE SEA..");
               return;
            }

            if (CFG.game.getCiv(nCivID).getSeaAccess() > 0) {
               List<Integer> civsAtWarWithSeaAccessToo = new ArrayList<>();

               for(int i = CFG.game.getCiv(nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
                  if (CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).getSeaAccess() > 0) {
                     civsAtWarWithSeaAccessToo.add(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i));
                  }
               }

               if (civsAtWarWithSeaAccessToo.size() > 0) {
                  List<Boolean> haveAccessToBasins = new ArrayList<>();
                  List<List<Integer>> possibleProvinceMoveTo_OwnProvinces = new ArrayList<>();
                  List<List<Integer>> possibleProvinceMoveTo = new ArrayList<>();

                  for(int i = 0; i < CFG.map.iNumOfBasins; ++i) {
                     haveAccessToBasins.add(false);
                     possibleProvinceMoveTo_OwnProvinces.add(new ArrayList<>());
                     possibleProvinceMoveTo.add(new ArrayList<>());
                  }

                  if (!canBuildPort) {
                     for(int i = CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces_Size() - 1; i >= 0; --i) {
                        for(int j = 0;
                           j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces().get(i)).getNeighboringSeaProvincesSize();
                           ++j
                        ) {
                           haveAccessToBasins.set(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_PortProvinces().get(i)).getNeighboringSeaProvinces(j))
                                 .getBasinID(),
                              true
                           );
                        }
                     }
                  } else {
                     for(int i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
                        for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
                           haveAccessToBasins.set(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                                 .getBasinID(),
                              true
                           );
                        }
                     }
                  }

                  int possibleMoveTo_OwnProvinces = 0;
                  int possibleMoveTo = 0;

                  for(int i = civsAtWarWithSeaAccessToo.size() - 1; i >= 0; --i) {
                     for(int j = CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces_Size() - 1; j >= 0; --j) {
                        for(int k = 0;
                           k
                              < CFG.game
                                 .getProvince(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j))
                                 .getNeighboringSeaProvincesSize();
                           ++k
                        ) {
                           if (haveAccessToBasins.get(
                              CFG.game
                                 .getProvince(
                                    CFG.game
                                       .getProvince(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j))
                                       .getNeighboringSeaProvinces(k)
                                 )
                                 .getBasinID()
                           )) {
                              if (CFG.game
                                    .getProvince(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j))
                                    .getTrueOwnerOfProvince()
                                 == nCivID) {
                                 possibleProvinceMoveTo_OwnProvinces.get(
                                       CFG.game
                                          .getProvince(
                                             CFG.game
                                                .getProvince(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j))
                                                .getNeighboringSeaProvinces(k)
                                          )
                                          .getBasinID()
                                    )
                                    .add(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j));
                                 ++possibleMoveTo_OwnProvinces;
                              } else {
                                 possibleProvinceMoveTo.get(
                                       CFG.game
                                          .getProvince(
                                             CFG.game
                                                .getProvince(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j))
                                                .getNeighboringSeaProvinces(k)
                                          )
                                          .getBasinID()
                                    )
                                    .add(CFG.game.getCiv(civsAtWarWithSeaAccessToo.get(i)).getSeaAccess_Provinces().get(j));
                                 ++possibleMoveTo;
                              }
                           }
                        }
                     }
                  }

                  Gdx.app.log("AoC", "moveAtWar -> BY SEA -> possibleMoveTo_OwnProvinces: " + possibleMoveTo_OwnProvinces);
                  Gdx.app.log("AoC", "moveAtWar -> BY SEA -> possibleMoveTo: " + possibleMoveTo);
                  if (possibleMoveTo + possibleMoveTo_OwnProvinces == 0) {
                     return;
                  }

                  int iBestProvinceID_MoveTo = -1;
                  float fBestProvinceID_MoveTo_Score = -1.0F;
                  if (possibleMoveTo > 0 || possibleMoveTo_OwnProvinces > 0) {
                     float tempScore = 0.0F;

                     for(int i = possibleProvinceMoveTo_OwnProvinces.size() - 1; i >= 0; --i) {
                        for(int j = possibleProvinceMoveTo_OwnProvinces.get(i).size() - 1; j >= 0; --j) {
                           tempScore = this.moveAtWar_AtSea_ToProvinceID_Score(nCivID, possibleProvinceMoveTo_OwnProvinces.get(i).get(j), true);
                           if (tempScore > fBestProvinceID_MoveTo_Score) {
                              iBestProvinceID_MoveTo = possibleProvinceMoveTo_OwnProvinces.get(i).get(j);
                              fBestProvinceID_MoveTo_Score = tempScore;
                           }
                        }
                     }

                     for(int i = possibleProvinceMoveTo.size() - 1; i >= 0; --i) {
                        for(int j = possibleProvinceMoveTo.get(i).size() - 1; j >= 0; --j) {
                           tempScore = this.moveAtWar_AtSea_ToProvinceID_Score(nCivID, possibleProvinceMoveTo.get(i).get(j), false);
                           if (tempScore > fBestProvinceID_MoveTo_Score) {
                              iBestProvinceID_MoveTo = possibleProvinceMoveTo.get(i).get(j);
                              fBestProvinceID_MoveTo_Score = tempScore;
                           }
                        }
                     }

                     if (iBestProvinceID_MoveTo >= 0) {
                        if (!CFG.game.isAlly(nCivID, CFG.game.getProvince(iBestProvinceID_MoveTo).getCivID())
                           && CFG.game.getCiv(nCivID).getRankPosition()
                              > CFG.game.getCiv(CFG.game.getProvince(iBestProvinceID_MoveTo).getCivID()).getRankPosition()
                           && CFG.oR.nextInt(100) < 62) {
                           CFG.game.getCiv(nCivID).civGameData.iNextPossibleNavalInvastionTurnID = Game_Calendar.TURN_ID + 3 + CFG.oR.nextInt(4);
                           return;
                        }

                        this.moveAtWar_AtSea_ToProvinceID(nCivID, iBestProvinceID_MoveTo);
                     } else {
                        Gdx.app.log("AoC", "moveAtWar -> BY SEA -> iBestProvinceID_MoveTo: " + iBestProvinceID_MoveTo);
                     }
                  }
               }
            }
         }

         Gdx.app.log("AoC", "moveAtWar -> BY SEA -> MP: " + CFG.game.getCiv(nCivID).getMovePoints());
         Gdx.app.log("AoC", "moveAtWar -> BY SEA END");
      }
   }

   protected final void moveAtWar_AtSea_ToProvinceID(int nCivID, int iBestProvinceID_MoveTo) {
      Gdx.app
         .log(
            "AoC",
            "moveAtWar -> BY SEA -> iBestProvinceID_MoveTo: " + iBestProvinceID_MoveTo + ", NAME: " + CFG.game.getProvince(iBestProvinceID_MoveTo).getName()
         );
      List<Boolean> haveAccessToBasins = new ArrayList<>();

      for(int i = 0; i < CFG.map.iNumOfBasins; ++i) {
         haveAccessToBasins.add(false);
      }

      for(int i = 0; i < CFG.game.getProvince(iBestProvinceID_MoveTo).getNeighboringSeaProvincesSize(); ++i) {
         haveAccessToBasins.set(CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveTo).getNeighboringSeaProvinces(i)).getBasinID(), true);
      }

      int iBestProvinceID_MoveFrom = -1;
      float fBestProvinceID_MoveTo_Score = -1.0F;
      int tempPossibleToRecruit = (int)(
         CFG.game.getCiv(nCivID).getMoney() > 5L && CFG.game.getCiv(nCivID).iBudget > 0 ? (float)(CFG.game.getCiv(nCivID).getMoney() / 5L) * 0.8F : 0.0F
      );

      for(int i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
            if (haveAccessToBasins.get(
               CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j)).getBasinID()
            )) {
               if (!CFG.game
                  .getCiv(nCivID)
                  .getCivRegion(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getCivRegionID())
                  .checkRegionBordersWithEnemy(nCivID)) {
                  float tempScore = this.moveAtWar_AtSea_FromProvinceID_Score(
                     nCivID, CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), iBestProvinceID_MoveTo, false, tempPossibleToRecruit
                  );
                  if (tempScore > fBestProvinceID_MoveTo_Score) {
                     iBestProvinceID_MoveFrom = CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i);
                     fBestProvinceID_MoveTo_Score = tempScore;
                  }
               }
               break;
            }
         }
      }

      if (iBestProvinceID_MoveFrom >= 0) {
         Gdx.app
            .log(
               "AoC",
               "moveAtWar -> BY SEA -> iBestProvinceID_MoveFrom: "
                  + iBestProvinceID_MoveFrom
                  + ", NAME: "
                  + CFG.game.getProvince(iBestProvinceID_MoveFrom).getName()
                  + " PORT: "
                  + CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort()
            );
         if (CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort() <= 0) {
            Gdx.app.log("AoC", "moveAtWar -> BY SEA -> TRY FIND NEIGHBORING PROVINCE WITH PORT");
            boolean newFound = false;

            for(int i = 0; i < CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvincesSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getCivID() == nCivID
                  && CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getLevelOfPort() > 0) {
                  iBestProvinceID_MoveFrom = CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i);
                  newFound = true;
                  Gdx.app
                     .log(
                        "AoC",
                        "moveAtWar -> BY SEA -> UPDATE 00: iBestProvinceID_MoveFrom: "
                           + iBestProvinceID_MoveFrom
                           + ", NAME: "
                           + CFG.game.getProvince(iBestProvinceID_MoveFrom).getName()
                           + " PORT: "
                           + CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort()
                     );
                  break;
               }
            }

            if (!newFound) {
               for(int i = 0; i < CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvincesSize(); ++i) {
                  if (CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getCivID() == nCivID
                     && CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getLevelOfPort() > 0) {
                     for(int j = 0;
                        j < CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getNeighboringProvincesSize();
                        ++j
                     ) {
                        if (CFG.game
                                 .getProvince(
                                    CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getNeighboringProvinces(j)
                                 )
                                 .getCivID()
                              == nCivID
                           && CFG.game
                                 .getProvince(
                                    CFG.game.getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i)).getNeighboringProvinces(j)
                                 )
                                 .getLevelOfPort()
                              > 0) {
                           iBestProvinceID_MoveFrom = CFG.game
                              .getProvince(CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvinces(i))
                              .getNeighboringProvinces(j);
                           i = CFG.game.getProvince(iBestProvinceID_MoveFrom).getNeighboringProvincesSize();
                           Gdx.app
                              .log(
                                 "AoC",
                                 "moveAtWar -> BY SEA -> UPDATE 00: iBestProvinceID_MoveFrom: "
                                    + iBestProvinceID_MoveFrom
                                    + ", NAME: "
                                    + CFG.game.getProvince(iBestProvinceID_MoveFrom).getName()
                                    + " PORT: "
                                    + CFG.game.getProvince(iBestProvinceID_MoveFrom).getLevelOfPort()
                              );
                           break;
                        }
                     }
                  }
               }
            }
         }

         CFG.game
            .getCiv(nCivID)
            .civGameData
            .civPlans
            .lArmiesMissions
            .add(new CivArmyMission_NavalInvasion(nCivID, iBestProvinceID_MoveFrom, iBestProvinceID_MoveTo));
         CFG.game
            .getCiv(nCivID)
            .civGameData
            .civPlans
            .lArmiesMissions
            .get(CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1)
            .action(nCivID);
      } else {
         Gdx.app.log("AoC", "moveAtWar -> BY SEA -> iBestProvinceID_MoveFrom: " + iBestProvinceID_MoveFrom);
      }
   }

   protected final float moveAtWar_AtSea_ToProvinceID_Score(int nCivID, int nProvince, boolean ownProvince) {
      return (
            (float)CFG.game.getProvince(nProvince).getPotential()
               + (float)(
                     CFG.game.getProvince(nProvince).getPotentialRegion()
                        * CFG.game
                           .getCiv(CFG.game.getProvince(nProvince).getCivID())
                           .getCivRegion(CFG.game.getProvince(nProvince).getCivRegionID())
                           .getProvincesSize()
                  )
                  / (float)CFG.game.getCiv(CFG.game.getProvince(nProvince).getCivID()).getNumOfProvinces()
         )
         * (ownProvince ? 1.625F : (CFG.game.getProvince(nProvince).isOccupied() ? 0.725F : 1.0F))
         * (CFG.game.getProvince(nProvince).getIsCapital() ? (CFG.game.getProvince(nProvince).getCivID() != nCivID ? 0.725F : 1.45F) : 1.0F)
         * (
            1.0F
               - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_ATTACK_NAVAL_DISTANCE
                  * CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID), nProvince)
         );
   }

   protected final float moveAtWar_AtSea_FromProvinceID_Score(int nCivID, int nProvince, int toProvinceID, boolean ownProvince, int tempPossibleToRecruit) {
      return (float)CFG.game.getProvince(nProvince).getPotential()
         * (
            0.375F
               + 0.625F
                  * (float)(
                     (CFG.game.getProvince(nProvince).getArmyCivID(nCivID) + (CFG.game.getProvince(nProvince).isOccupied() ? 0 : tempPossibleToRecruit))
                        / Math.max(1, CFG.game.getCiv(nCivID).getNumOfUnits() + tempPossibleToRecruit)
                  )
         )
         * (CFG.game.getProvince(nProvince).getLevelOfPort() > 0 ? 1.5F : 1.0F)
         * (1.0F - CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvince, toProvinceID) / 2.0F);
   }

   protected final int moveAtWar_NumOfNotCoveredNeighEnemyProvinces(int nCivID, int nProvinceID) {
      int out = 0;

      for(int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
         if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getCivID())
            && !CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j))) {
            ++out;
         }
      }

      return out;
   }

   protected final float moveAtWar_AttackTo_Score(int nCivID, int toProvinceID) {
      return (float)CFG.game.getProvince(toProvinceID).getPotentialModified_WAR_MoveTo(nCivID);
   }

   protected final void prepareForWar_Regroup(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies) {
      try {
         if (CFG.game.getCiv(nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID <= Game_Calendar.TURN_ID) {
            List<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<>();
            List<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<>();
            List<AI_RegoupArmyData> armiesAtSea = new ArrayList<>();

            for(int i = 0; i < CFG.game.getCiv(nCivID).iArmiesPositionSize; ++i) {
               int tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i));
               if (tArmyToRegroup > 0 && !CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i))) {
                  armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
               }
            }

            for(int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
               int tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
               if (tArmyToRegroup > 0 && !CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i))) {
                  boolean addN = true;

                  for(int z = armiesWithoutDanger.size() - 1; z >= 0; --z) {
                     if (armiesWithoutDanger.get(z).iProvinceID == CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)) {
                        addN = false;
                     }
                  }

                  if (addN) {
                     armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                  }
               }
            }

            Gdx.app
               .log("AoC", "prepareForWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesWithoutDanger.size: " + armiesWithoutDanger.size());
            Gdx.app
               .log(
                  "AoC",
                  "prepareForWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesInAnotherTerritory.size: " + armiesInAnotherTerritory.size()
               );
            Gdx.app.log("AoC", "prepareForWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesAtSea.size: " + armiesAtSea.size());
            if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces()) {
               armiesWithoutDanger.clear();
            }

            while(armiesWithoutDanger.size() > 0 || armiesInAnotherTerritory.size() > 0) {
               int highestArmyID = -1;
               int highestArmy_Num = 0;
               int highestArmy_ListID = -1;

               for(int i = armiesWithoutDanger.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesWithoutDanger.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesWithoutDanger.get(i).iArmy;
                     highestArmy_ListID = 0;
                  }
               }

               for(int i = armiesInAnotherTerritory.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesInAnotherTerritory.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesInAnotherTerritory.get(i).iArmy;
                     highestArmy_ListID = 2;
                  }
               }

               if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                  switch(highestArmy_ListID) {
                     case 0:
                        Gdx.app.log("AoC", "prepareForWar_Regroup -> ACTION: 0");
                        this.regroupArmy_PrepareForWar_WithoutDanger(nCivID, armiesWithoutDanger.get(highestArmyID));
                        armiesWithoutDanger.remove(highestArmyID);
                        break;
                     case 2:
                        Gdx.app.log("AoC", "prepareForWar_Regroup -> ACTION: 2");
                        this.regroupArmy_PrepareForWar_WithoutDanger(nCivID, armiesInAnotherTerritory.get(highestArmyID));
                        armiesInAnotherTerritory.remove(highestArmyID);
                  }
               }

               if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  || CFG.game.getCiv(nCivID).getMovePoints()
                     < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) {
                  return;
               }
            }

            CFG.game.getCiv(nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID = 0;
         }
      } catch (IndexOutOfBoundsException var11) {
         CFG.exceptionStack(var11);
      } catch (StackOverflowError var12) {
         CFG.exceptionStack(var12);
      }
   }

   protected final boolean moveAtWar_BordersWithEnemyCheck(int nCivID, int nProvinceID) {
      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID())) {
            return true;
         }
      }

      return false;
   }

   protected final void moveAtWar_Regroup(int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies) {
      try {
         if (CFG.game.getCiv(nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID <= Game_Calendar.TURN_ID) {
            List<AI_RegoupArmyData> armiesWithoutDanger = new ArrayList<>();
            List<AI_RegoupArmyData> armiesInAnotherTerritory = new ArrayList<>();
            List<AI_RegoupArmyData> armiesAtSea = new ArrayList<>();

            for(int i = 0; i < CFG.game.getCiv(nCivID).iArmiesPositionSize; ++i) {
               int tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i));
               if (tArmyToRegroup > 0) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getSeaProvince()) {
                     armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getCivID() != nCivID
                     && !CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getBordersWithEnemy()
                     && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i))) {
                     armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  } else if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).lArmiesPosition.get(i)).getBordersWithEnemy()
                     && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i))) {
                     armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).lArmiesPosition.get(i), tArmyToRegroup));
                  }
               }
            }

            for(int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
               int tArmyToRegroup = this.getRegroupArmy_NumOfUnits(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
               if (tArmyToRegroup > 0) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getSeaProvince()) {
                     armiesAtSea.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() != nCivID
                     && !CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getBordersWithEnemy()
                     && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i))) {
                     armiesInAnotherTerritory.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                  } else if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getDangerLevel() == 0
                     && !CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getBordersWithEnemy()
                     && !this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i))) {
                     armiesWithoutDanger.add(new AI_RegoupArmyData(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), tArmyToRegroup));
                  }
               }
            }

            Gdx.app.log("AoC", "moveAtWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesWithoutDanger.size: " + armiesWithoutDanger.size());
            Gdx.app
               .log(
                  "AoC",
                  "moveAtWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesInAnotherTerritory.size: " + armiesInAnotherTerritory.size()
               );
            Gdx.app.log("AoC", "moveAtWar_Regroup -> " + CFG.game.getCiv(nCivID).getCivName() + ", armiesAtSea.size: " + armiesAtSea.size());
            if (armiesWithoutDanger.size() == CFG.game.getCiv(nCivID).getNumOfProvinces()) {
               armiesWithoutDanger.clear();
            }

            while(armiesAtSea.size() > 0) {
               int highestArmyID = -1;
               int highestArmy_Num = 0;
               int highestArmy_ListID = -1;

               for(int i = armiesAtSea.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesAtSea.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesAtSea.get(i).iArmy;
                     highestArmy_ListID = 1;
                  }
               }

               if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                  switch(highestArmy_ListID) {
                     case 1:
                        Gdx.app.log("AoC", "moveAtWar_Regroup -> ACTION: 1");
                        this.regroupArmy_AtWar_AtSea(nCivID, armiesAtSea.get(highestArmyID));
                        armiesAtSea.remove(highestArmyID);
                  }
               }

               if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  || CFG.game.getCiv(nCivID).getMovePoints()
                     < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) {
                  return;
               }
            }

            while(armiesWithoutDanger.size() > 0 || armiesInAnotherTerritory.size() > 0) {
               int highestArmyID = -1;
               int highestArmy_Num = 0;
               int highestArmy_ListID = -1;

               for(int i = armiesWithoutDanger.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesWithoutDanger.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesWithoutDanger.get(i).iArmy;
                     highestArmy_ListID = 0;
                  }
               }

               for(int i = armiesInAnotherTerritory.size() - 1; i >= 0; --i) {
                  if (highestArmyID < 0 || highestArmy_Num < armiesInAnotherTerritory.get(i).iArmy) {
                     highestArmyID = i;
                     highestArmy_Num = armiesInAnotherTerritory.get(i).iArmy;
                     highestArmy_ListID = 2;
                  }
               }

               if (highestArmyID >= 0 && highestArmy_ListID >= 0 && highestArmy_Num > 0) {
                  switch(highestArmy_ListID) {
                     case 0:
                        Gdx.app.log("AoC", "moveAtWar_Regroup -> ACTION: 0");
                        this.regroupArmy_AtWar_WithoutDanger(nCivID, armiesWithoutDanger.get(highestArmyID));
                        armiesWithoutDanger.remove(highestArmyID);
                        break;
                     case 2:
                        Gdx.app.log("AoC", "moveAtWar_Regroup -> ACTION: 2");
                        this.regroupArmy_AtWar_WithoutDanger(nCivID, armiesInAnotherTerritory.get(highestArmyID));
                        armiesInAnotherTerritory.remove(highestArmyID);
                  }
               }

               if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  || CFG.game.getCiv(nCivID).getMovePoints()
                     < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE) {
                  return;
               }
            }

            CFG.game.getCiv(nCivID).civGameData.iRegroupArmyAtPeace_CheckTurnID = 0;
         }
      } catch (IndexOutOfBoundsException var11) {
         CFG.exceptionStack(var11);
      } catch (StackOverflowError var12) {
         CFG.exceptionStack(var12);
      }
   }

   protected final boolean regroupArmy_AtWar_AtSea(int nCivID, AI_RegoupArmyData nArmy) {
      List<AI_ProvinceInfo> possibleMoveTo = new ArrayList<>();

      for(int i = 0; i < CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID
            || CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i)).getCivID())) {
            possibleMoveTo.add(
               new AI_ProvinceInfo(
                  CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i),
                  this.getPotential_BasedOnNeighboringProvs(CFG.game.getProvince(nArmy.iProvinceID).getNeighboringProvinces(i), nCivID),
                  1
               )
            );
         }
      }

      Gdx.app
         .log(
            "AoC",
            "regroupArmy_AtWar_AtSea -> "
               + CFG.game.getCiv(nCivID).getCivName()
               + ", Province: "
               + nArmy.iProvinceID
               + ", NAME: "
               + CFG.game.getProvince(nArmy.iProvinceID).getName()
               + ", nArmy: "
               + nArmy.iArmy
               + ", possibleMoveTo.size: "
               + possibleMoveTo.size()
         );
      if (possibleMoveTo.size() > 0) {
         int tMaxArmy = 1;
         float tMaxPotential = 1.0F;
         float tMaxRegion_NumOfProvinces = 1.0F;
         float tMaxRegion_Potential = 1.0F;
         int tMaxDL = 1;
         List<Integer> tMovingArmy = new ArrayList<>();
         int i = 0;
         int iSize = possibleMoveTo.size();

         for(int tempMovingArmy = 0; i < iSize; ++i) {
            if (possibleMoveTo.get(i).iValue > tMaxPotential) {
               tMaxPotential = possibleMoveTo.get(i).iValue;
            }

            if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
               tMaxDL = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy();
            }

            if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
               tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces();
            }

            if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
               tMaxRegion_Potential = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion();
            }

            tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, possibleMoveTo.get(i).iProvinceID);
            tMovingArmy.add(tempMovingArmy);
            if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
               tMaxArmy = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
            }
         }

         i = 0;

         for(int iSizex = possibleMoveTo.size(); i < iSizex; ++i) {
            possibleMoveTo.get(i).iValue = this.getValue_PositionOfArmy(
               nCivID, possibleMoveTo, i, tMovingArmy.get(i), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, (float)nArmy.iArmy
            );
         }

         List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

         while(possibleMoveTo.size() > 0) {
            iSize = 0;
            int ix = 1;

            for(int iSizex = possibleMoveTo.size(); ix < iSizex; ++ix) {
               if (possibleMoveTo.get(iSize).iValue < possibleMoveTo.get(ix).iValue) {
                  iSize = ix;
               }
            }

            sortedFrontProvinces.add(possibleMoveTo.get(iSize));
            possibleMoveTo.remove(iSize);
         }

         float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01F);
         int iNumOfMaxMovements = 1;
         if (CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
            iNumOfMaxMovements = 1;
         } else {
            iNumOfMaxMovements = Math.max(
               1,
               Math.min(
                  (CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE)
                     / (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 2),
                  1 + CFG.oR.nextInt(3)
               )
            );
            if (percOfArmyToRegroup > 0.34F) {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 4);
            } else if (percOfArmyToRegroup > 0.24F) {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 3);
            } else if (percOfArmyToRegroup > 0.1F) {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 2);
            } else {
               iNumOfMaxMovements = Math.min(iNumOfMaxMovements, 1);
            }
         }

         Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> 000 -> iNumOfMaxMovements: " + iNumOfMaxMovements);
         List<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<>();
         float totalValues = 0.0F;

         for(int ix = 0; ix < iNumOfMaxMovements && ix < sortedFrontProvinces.size(); ++ix) {
            tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(ix));
            totalValues += sortedFrontProvinces.get(ix).iValue;
         }

         Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> 000 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());

         for(int ix = 0; ix < tRecruitArmiesForProvinces.size(); ++ix) {
            int tArmyToRecruit_PRE = (int)Math.ceil((double)((float)nArmy.iArmy * tRecruitArmiesForProvinces.get(ix).iValue / totalValues));
            Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea -> 000 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
            if (tArmyToRecruit_PRE <= 0) {
               break;
            }

            RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID);
            if (tryRegroupArmy.getRouteSize() > 0) {
               if (tryRegroupArmy.getRouteSize() == 1) {
                  if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) {
                  }
               } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) {
                  tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                  tryRegroupArmy.removeRoute(0);
                  tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                  CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
               }
            }
         }

         return true;
      } else {
         for(int i = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; i >= 0; --i) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvincesSize(); ++j) {
               if (CFG.game
                     .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i)).getNeighboringSeaProvinces(j))
                     .getBasinID()
                  == CFG.game.getProvince(nArmy.iProvinceID).getBasinID()) {
                  possibleMoveTo.add(
                     new AI_ProvinceInfo(
                        CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i),
                        this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(i), nCivID),
                        1
                     )
                  );
                  break;
               }
            }
         }

         Gdx.app.log("AoC", "regroupArmy_AtWar_AtSea ->  111, possibleMoveTo.size: " + possibleMoveTo.size());
         if (possibleMoveTo.size() > 0) {
            int tMaxArmy = 1;
            float tMaxPotential = 1.0F;
            float tMaxRegion_NumOfProvinces = 1.0F;
            float tMaxRegion_Potential = 1.0F;
            int tMaxDL = 1;
            List<Integer> tMovingArmy = new ArrayList<>();
            int i = 0;
            int iSize = possibleMoveTo.size();

            for(int tempMovingArmy = 0; i < iSize; ++i) {
               if (possibleMoveTo.get(i).iValue > tMaxPotential) {
                  tMaxPotential = possibleMoveTo.get(i).iValue;
               }

               if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                  tMaxDL = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getDangerLevel_WithArmy();
               }

               if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                  tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getRegion_NumOfProvinces();
               }

               if ((float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                  tMaxRegion_Potential = (float)CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getPotentialRegion();
               }

               tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, possibleMoveTo.get(i).iProvinceID);
               tMovingArmy.add(tempMovingArmy);
               if (CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                  tMaxArmy = CFG.game.getProvince(possibleMoveTo.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
               }
            }

            i = 0;

            for(int iSizex = possibleMoveTo.size(); i < iSizex; ++i) {
               possibleMoveTo.get(i).iValue = this.getValue_PositionOfArmy(
                  nCivID, possibleMoveTo, i, tMovingArmy.get(i), tMaxPotential, tMaxRegion_Potential, tMaxDL, tMaxArmy, nArmy.iArmy, (float)nArmy.iArmy
               );
            }

            List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();
            if (possibleMoveTo.size() > 0) {
               iSize = 0;
               int ix = 1;

               for(int iSizex = possibleMoveTo.size(); ix < iSizex; ++ix) {
                  if (possibleMoveTo.get(iSize).iValue < possibleMoveTo.get(ix).iValue) {
                     iSize = ix;
                  }
               }

               sortedFrontProvinces.add(possibleMoveTo.get(iSize));
               possibleMoveTo.remove(iSize);
            }

            RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, sortedFrontProvinces.get(0).iProvinceID);
            if (tryRegroupArmy.getRouteSize() > 0) {
               if (tryRegroupArmy.getRouteSize() == 1) {
                  if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, sortedFrontProvinces.get(0).iProvinceID, nArmy.iArmy, nCivID, true, false)) {
                  }
               } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                  tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                  tryRegroupArmy.removeRoute(0);
                  tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                  CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
               }
            }

            return true;
         } else {
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID) {
               RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
               if (tryRegroupArmy.getRouteSize() > 0) {
                  if (tryRegroupArmy.getRouteSize() == 1) {
                     if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                     }
                  } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                     tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                     tryRegroupArmy.removeRoute(0);
                     tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                     CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                  }
               } else {
                  CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
               }
            } else {
               CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
            }

            return true;
         }
      }
   }

   protected final boolean regroupArmy_PrepareForWar_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy) {
      try {
         float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01F);

         try {
            if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
               Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> " + nArmy.iArmy);
               int tMaxDL = 1;
               float tMaxPotential = 1.0F;
               List<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<>();

               for(int i = CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).size() - 1; i >= 0; --i) {
                  for(int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                     if (CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).iWithCivID
                        == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
                        try {
                           if (CFG.game
                                 .getProvince(CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.get(0))
                                 .getCivRegionID()
                              == CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) {
                              for(int j = CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.size() - 1;
                                 j >= 0;
                                 --j
                              ) {
                                 boolean wasAdded = false;

                                 for(int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                    if (tempFrontProvinces.get(k).iProvinceID
                                       == CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.get(j)) {
                                       wasAdded = true;
                                       break;
                                    }
                                 }

                                 if (!wasAdded) {
                                    tempFrontProvinces.add(
                                       new AI_ProvinceInfo(
                                          CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.get(j), 1, 1
                                       )
                                    );
                                 }
                              }
                           }
                        } catch (IndexOutOfBoundsException var16) {
                        }
                     }
                  }
               }

               Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
               if (tempFrontProvinces.size() > 0) {
                  int tMaxArmy = 1;
                  List<Integer> tMovingArmy = new ArrayList<>();
                  int i = 0;
                  int iSize = tempFrontProvinces.size();

                  for(int tempMovingArmy = 0; i < iSize; ++i) {
                     if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                        tMaxPotential = tempFrontProvinces.get(i).iValue;
                     }

                     if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                        tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy();
                     }

                     tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
                     tMovingArmy.add(tempMovingArmy);
                     if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                        tMaxArmy = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
                     }
                  }

                  i = 0;

                  for(int iSizex = tempFrontProvinces.size(); i < iSizex; ++i) {
                     tempFrontProvinces.get(i).iValue = (
                                 1.0F
                                    - (float)(CFG.game.getProvinceArmy(tempFrontProvinces.get(i).iProvinceID) + tMovingArmy.get(i)) / (float)tMaxArmy
                                    + 0.2F * ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel() / (float)tMaxDL)
                                    + 0.2F
                                       * (
                                          (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID)
                                             / tMaxPotential
                                       )
                                    + 0.2F * (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getNeighbooringProvinceOfCivWasLost()
                              )
                              * (float)tempFrontProvinces.get(i).iRecruitable
                           == 0.0F
                        ? 0.725F
                        : 1.0F;
                  }

                  List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

                  while(tempFrontProvinces.size() > 0) {
                     iSize = 0;
                     int ix = 1;

                     for(int iSizex = tempFrontProvinces.size(); ix < iSizex; ++ix) {
                        if (tempFrontProvinces.get(iSize).iValue < tempFrontProvinces.get(ix).iValue) {
                           iSize = ix;
                        }
                     }

                     sortedFrontProvinces.add(tempFrontProvinces.get(iSize));
                     tempFrontProvinces.remove(iSize);
                  }

                  iSize = 1;
                  if (CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                     iSize = 1;
                  } else {
                     iSize = Math.max(
                        1,
                        Math.min(
                           (CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE)
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE,
                           Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3))
                        )
                     );
                     if (percOfArmyToRegroup > 0.4F) {
                        iSize = Math.min(iSize, 4);
                     } else if (percOfArmyToRegroup > 0.3F) {
                        iSize = Math.min(iSize, 3);
                     } else if (percOfArmyToRegroup > 0.2F) {
                        iSize = Math.min(iSize, 2);
                     } else {
                        iSize = Math.min(iSize, 1);
                     }
                  }

                  Gdx.app.log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> iNumOfMaxMovements: " + iSize);
                  List<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<>();
                  float totalValues = 0.0F;

                  for(int ix = 0; ix < iSize && ix < sortedFrontProvinces.size(); ++ix) {
                     tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(ix));
                     totalValues += sortedFrontProvinces.get(ix).iValue;
                  }

                  Gdx.app
                     .log("AoC", "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());

                  for(int ix = 0; ix < tRecruitArmiesForProvinces.size(); ++ix) {
                     int tArmyToRecruit_PRE = (int)Math.ceil((double)((float)nArmy.iArmy * tRecruitArmiesForProvinces.get(ix).iValue / totalValues));
                     Gdx.app
                        .log(
                           "AoC",
                           "regroupArmy_PrepareForWar_WithoutDanger -> 111 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE
                        );
                     if (tArmyToRecruit_PRE <= 0) {
                        break;
                     }

                     RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID);
                     if (tryRegroupArmy.getRouteSize() > 0) {
                        if (tryRegroupArmy.getRouteSize() == 1) {
                           if (!CFG.gameAction
                              .moveArmy(nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) {
                           }
                        } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) {
                           tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                           tryRegroupArmy.removeRoute(0);
                           tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                           CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                     }
                  }

                  return true;
               }
            }
         } catch (NullPointerException var17) {
         }

         List<AI_NeighProvinces> listOfPossibleProvinces = this.getAllNeighboringProvincesInRange_RegroupPrepareForWAr(
            nArmy.iProvinceID,
            nCivID,
            CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.game.getCiv(nCivID).getNumOfProvinces() / 15,
            new ArrayList<>(),
            new ArrayList<>()
         );
         if (listOfPossibleProvinces.size() > 0) {
            int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints()
               / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
            if (percOfArmyToRegroup > 0.54F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 4);
            } else if (percOfArmyToRegroup > 0.35F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 3);
            } else if (percOfArmyToRegroup > 0.25F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 2);
            } else {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 1);
            }

            boolean provincesWithDanger = false;

            for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
               if (CFG.game.getProvince(listOfPossibleProvinces.get(i).iProvinceID).getDangerLevel() > 0) {
                  provincesWithDanger = true;
                  break;
               }
            }

            if (!provincesWithDanger) {
               Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NONE -> 11111111 -> " + nArmy.iArmy);
            } else {
               List<Integer> tSortedIDs = new ArrayList<>();
               List<Integer> tData = new ArrayList<>();

               for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                  tData.add(i);
               }

               while(tData.size() > 0) {
                  int tBest = 0;

                  for(int i = tData.size() - 1; i > 0; --i) {
                     if (CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(tBest)).iProvinceID).getDangerLevel_WithArmy()
                        < CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(i)).iProvinceID).getDangerLevel_WithArmy()) {
                        tBest = i;
                     }
                  }

                  tSortedIDs.add(tData.get(tBest));
                  tData.remove(tBest);
               }

               int nDangerTotal = 0;

               for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size(); ++i) {
                  nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getDangerLevel_WithArmy();
               }

               int tIDOfFisrttSuccesfulMovement = -1;

               for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size() && nArmy.iArmy > 0; ++i) {
                  RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID);
                  if (tryRegroupArmy.getRouteSize() > 0) {
                     int tArmyToMove = i != nNumOfPossibleMovements && i != tSortedIDs.size() - 1
                        ? (int)Math.ceil(
                           (double)(
                              (float)nArmy.iArmy
                                 * (
                                    (float)CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getDangerLevel_WithArmy()
                                       / (float)nDangerTotal
                                 )
                           )
                        )
                        : nArmy.iArmy;
                     nArmy.iArmy -= tArmyToMove;
                     if (tArmyToMove <= 0) {
                        break;
                     }

                     if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) {
                        if (tryRegroupArmy.getRouteSize() > 1) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment(
                                    nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID, tArmyToMove
                                 )
                              );
                        }

                        tIDOfFisrttSuccesfulMovement = i;
                     }
                  } else if (tIDOfFisrttSuccesfulMovement >= 0) {
                     tryRegroupArmy = new RegroupArmy_Data(
                        nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(tIDOfFisrttSuccesfulMovement)).iProvinceID
                     );
                     if (tryRegroupArmy.getRouteSize() > 0
                        && CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                        if (tryRegroupArmy.getRouteSize() > 1) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment(
                                    nCivID,
                                    tryRegroupArmy.getRoute(0),
                                    listOfPossibleProvinces.get(tSortedIDs.get(tIDOfFisrttSuccesfulMovement)).iProvinceID,
                                    nArmy.iArmy
                                 )
                              );
                        }

                        return true;
                     }
                  }
               }

               if (tIDOfFisrttSuccesfulMovement >= 0) {
                  return true;
               }
            }
         }

         Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NONE -> 222222 -> " + nArmy.iArmy);
      } catch (IndexOutOfBoundsException var18) {
         CFG.exceptionStack(var18);
      } catch (StackOverflowError var19) {
         CFG.exceptionStack(var19);
      }

      return false;
   }

   protected final boolean regroupArmy_AtWar_WithoutDanger(int nCivID, AI_RegoupArmyData nArmy) {
      try {
         float percOfArmyToRegroup = Math.max((float)nArmy.iArmy / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.01F);

         try {
            if (CFG.game.getCiv(nCivID).getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()).getProvincesSize() > 1) {
               Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> " + nArmy.iArmy);
               int tMaxDL = 1;
               float tMaxPotential = 1.0F;
               List<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<>();

               for(int i = CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).size() - 1; i >= 0; --i) {
                  if (CFG.game.getCivsAtWar(nCivID, CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).iWithCivID)) {
                     try {
                        if (CFG.game
                              .getProvince(CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.get(0))
                              .getCivRegionID()
                           == CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID()) {
                           for(int j = CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.size() - 1;
                              j >= 0;
                              --j
                           ) {
                              boolean wasAdded = false;

                              for(int k = tempFrontProvinces.size() - 1; k >= 0; --k) {
                                 if (tempFrontProvinces.get(k).iProvinceID
                                    == CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.get(j)) {
                                    wasAdded = true;
                                    break;
                                 }
                              }

                              if (!wasAdded) {
                                 tempFrontProvinces.add(
                                    new AI_ProvinceInfo(
                                       CFG.oAI.lFrontLines.get(CFG.game.getProvince(nArmy.iProvinceID).getCivID() - 1).get(i).lProvinces.get(j), 1, 1
                                    )
                                 );
                              }
                           }
                        }
                     } catch (IndexOutOfBoundsException var17) {
                     }
                  }
               }

               Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
               if (tempFrontProvinces.size() > 0) {
                  int tMaxArmy = 1;
                  List<Integer> tMovingArmy = new ArrayList<>();
                  int i = 0;
                  int iSize = tempFrontProvinces.size();

                  for(int tempMovingArmy = 0; i < iSize; ++i) {
                     if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                        tMaxPotential = tempFrontProvinces.get(i).iValue;
                     }

                     if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                        tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy();
                     }

                     tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
                     tMovingArmy.add(tempMovingArmy);
                     if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                        tMaxArmy = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
                     }
                  }

                  i = 0;

                  for(int iSizex = tempFrontProvinces.size(); i < iSizex; ++i) {
                     tempFrontProvinces.get(i).iValue = (
                                 1.0F
                                    - (float)(CFG.game.getProvinceArmy(tempFrontProvinces.get(i).iProvinceID) + tMovingArmy.get(i)) / (float)tMaxArmy
                                    + 0.2F * ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel() / (float)tMaxDL)
                                    + 0.2F
                                       * (
                                          (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialModified_WAR_MoveFrom(nCivID)
                                             / tMaxPotential
                                       )
                                    + 0.2F * (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getNeighbooringProvinceOfCivWasLost()
                              )
                              * (float)tempFrontProvinces.get(i).iRecruitable
                           == 0.0F
                        ? 0.725F
                        : 1.0F;
                  }

                  List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

                  while(tempFrontProvinces.size() > 0) {
                     iSize = 0;
                     int ix = 1;

                     for(int iSizex = tempFrontProvinces.size(); ix < iSizex; ++ix) {
                        if (tempFrontProvinces.get(iSize).iValue < tempFrontProvinces.get(ix).iValue) {
                           iSize = ix;
                        }
                     }

                     sortedFrontProvinces.add(tempFrontProvinces.get(iSize));
                     tempFrontProvinces.remove(iSize);
                  }

                  iSize = 1;
                  if (CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_DISBAND_IF_LESS_THAN_PERC > percOfArmyToRegroup) {
                     iSize = 1;
                  } else {
                     iSize = Math.max(
                        1,
                        Math.min(
                           (CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE)
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE,
                           Math.min(CFG.game.getCiv(nCivID).getNumOfProvinces(), 2 + CFG.oR.nextInt(3))
                        )
                     );
                     if (percOfArmyToRegroup > 0.34F) {
                        iSize = Math.min(iSize, 4);
                     } else if (percOfArmyToRegroup > 0.24F) {
                        iSize = Math.min(iSize, 3);
                     } else if (percOfArmyToRegroup > 0.1F) {
                        iSize = Math.min(iSize, 2);
                     } else {
                        iSize = Math.min(iSize, 1);
                     }
                  }

                  Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> iNumOfMaxMovements: " + iSize);
                  List<AI_ProvinceInfo> tRecruitArmiesForProvinces = new ArrayList<>();
                  float totalValues = 0.0F;

                  for(int ix = 0; ix < iSize && ix < sortedFrontProvinces.size(); ++ix) {
                     tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(ix));
                     totalValues += sortedFrontProvinces.get(ix).iValue;
                  }

                  Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> tRecruitArmiesForProvinces.size: " + tRecruitArmiesForProvinces.size());

                  for(int ix = 0; ix < tRecruitArmiesForProvinces.size(); ++ix) {
                     int tempArmyInThisMove = nArmy.iArmy;
                     if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0
                        && CFG.game.getProvince(nArmy.iProvinceID).getCivID() == nCivID
                        && CFG.game.getProvince(tRecruitArmiesForProvinces.get(ix).iProvinceID).getCivID() != nCivID) {
                        tempArmyInThisMove = (int)Math.ceil((double)((float)tempArmyInThisMove * (0.72F + (float)CFG.oR.nextInt(12) / 100.0F)));
                     }

                     int tArmyToRecruit_PRE = (int)Math.ceil((double)((float)tempArmyInThisMove * tRecruitArmiesForProvinces.get(ix).iValue / totalValues));
                     Gdx.app
                        .log("AoC", "regroupArmy_AtWar_WithoutDanger -> 111 -> nArmy.iArmy: " + nArmy.iArmy + ", tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
                     if (tArmyToRecruit_PRE <= 0) {
                        break;
                     }

                     RegroupArmy_Data_ToTheFront_Double tryRegroupArmy = new RegroupArmy_Data_ToTheFront_Double(
                        nCivID, nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID
                     );
                     if (tryRegroupArmy.getRouteSize() > 0) {
                        if (tryRegroupArmy.getRouteSize() == 1) {
                           if (!CFG.gameAction
                              .moveArmy(nArmy.iProvinceID, tRecruitArmiesForProvinces.get(ix).iProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) {
                           }
                        } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) {
                           tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                           tryRegroupArmy.removeRoute(0);
                           tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                           CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                        }
                     }
                  }

                  return true;
               }
            }
         } catch (NullPointerException var18) {
         }

         List<AI_NeighProvinces> listOfPossibleProvinces = this.getAllNeighboringProvincesInRange_RegroupAtWar(
            nArmy.iProvinceID,
            nCivID,
            CFG.game.getCiv(nCivID).civGameData.civPersonality.REGROUP_AT_PEACE_MAX_PROVINCES + CFG.game.getCiv(nCivID).getNumOfProvinces() / 15,
            new ArrayList<>(),
            new ArrayList<>()
         );
         Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> listOfPossibleProvinces.size: " + listOfPossibleProvinces.size());
         if (listOfPossibleProvinces.size() > 0) {
            int nNumOfPossibleMovements = CFG.game.getCiv(nCivID).getMovePoints()
               / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
            if (percOfArmyToRegroup > 0.54F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 4);
            } else if (percOfArmyToRegroup > 0.34F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 3);
            } else if (percOfArmyToRegroup > 0.19F) {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 2);
            } else {
               nNumOfPossibleMovements = Math.min(nNumOfPossibleMovements, 1);
            }

            boolean provincesWithDanger = false;

            for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
               if (CFG.game.getProvince(listOfPossibleProvinces.get(i).iProvinceID).getDangerLevel() > 0) {
                  provincesWithDanger = true;
                  break;
               }
            }

            if (!provincesWithDanger) {
               Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NOTHING 0000 -> " + nArmy.iArmy);
            } else {
               List<Integer> tSortedIDs = new ArrayList<>();
               List<Integer> tData = new ArrayList<>();

               for(int i = listOfPossibleProvinces.size() - 1; i >= 0; --i) {
                  tData.add(i);
               }

               while(tData.size() > 0) {
                  int tBest = 0;

                  for(int i = tData.size() - 1; i > 0; --i) {
                     if (CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(tBest)).iProvinceID).getDangerLevel_WithArmy()
                        < CFG.game.getProvince(listOfPossibleProvinces.get(tData.get(i)).iProvinceID).getDangerLevel_WithArmy()) {
                        tBest = i;
                     }
                  }

                  tSortedIDs.add(tData.get(tBest));
                  tData.remove(tBest);
               }

               int nDangerTotal = 0;

               for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size(); ++i) {
                  nDangerTotal += CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getDangerLevel_WithArmy();
               }

               int tIDOfFisrttSuccesfulMovement = -1;

               for(int i = 0; i < nNumOfPossibleMovements && i < tSortedIDs.size() && nArmy.iArmy > 0; ++i) {
                  RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(
                     nCivID, nArmy.iProvinceID, listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID
                  );
                  if (tryRegroupArmy.getRouteSize() > 0) {
                     int tempArmyInThisMove = nArmy.iArmy;
                     if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0
                        && CFG.game.getProvince(nArmy.iProvinceID).getCivID() == nCivID
                        && CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getCivID() != nCivID) {
                        tempArmyInThisMove = (int)Math.ceil((double)((float)tempArmyInThisMove * (0.72F + (float)CFG.oR.nextInt(12) / 100.0F)));
                     }

                     int tArmyToMove = i != nNumOfPossibleMovements && i != tSortedIDs.size() - 1
                        ? (int)Math.ceil(
                           (double)(
                              (float)tempArmyInThisMove
                                 * (
                                    (float)CFG.game.getProvince(listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID).getDangerLevel_WithArmy()
                                       / (float)nDangerTotal
                                 )
                           )
                        )
                        : tempArmyInThisMove;
                     nArmy.iArmy -= tArmyToMove;
                     if (tArmyToMove <= 0) {
                        break;
                     }

                     if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToMove, nCivID, true, false)) {
                        if (tryRegroupArmy.getRouteSize() > 1) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment_War_Double(
                                    nCivID, tryRegroupArmy.getRoute(0), listOfPossibleProvinces.get(tSortedIDs.get(i)).iProvinceID, tArmyToMove
                                 )
                              );
                        }

                        tIDOfFisrttSuccesfulMovement = i;
                     }
                  }
               }

               if (tIDOfFisrttSuccesfulMovement >= 0) {
                  return true;
               }
            }
         }

         if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
            && CFG.game.getCiv(nCivID).getCapitalProvinceID() != nArmy.iProvinceID
            && (
               CFG.game.getCiv(nCivID).getBordersWithEnemy() > 0
                  || CFG.game.getProvince(nArmy.iProvinceID).getNeighboringSeaProvincesSize() <= 0
                  || CFG.oR.nextInt(100) >= 80
            )
            && CFG.oR.nextInt(100) < 15) {
            Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> CAPITAL -> " + nArmy.iArmy);
            if (percOfArmyToRegroup < 0.01F) {
               CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
               Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> DISBAND -> 11111111 -> " + nArmy.iArmy);
            } else {
               RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(nCivID, nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID());
               if (tryRegroupArmy.getRouteSize() > 0) {
                  if (tryRegroupArmy.getRouteSize() == 1) {
                     if (!CFG.gameAction.moveArmy(nArmy.iProvinceID, CFG.game.getCiv(nCivID).getCapitalProvinceID(), nArmy.iArmy, nCivID, true, false)) {
                     }
                  } else if (CFG.gameAction.moveArmy(nArmy.iProvinceID, tryRegroupArmy.getRoute(0), nArmy.iArmy, nCivID, true, false)) {
                     tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                     tryRegroupArmy.removeRoute(0);
                     tryRegroupArmy.setNumOfUnits(nArmy.iArmy);
                     CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                  }
               } else if (!CFG.game
                  .getCiv(CFG.game.getProvince(nArmy.iProvinceID).getCivID())
                  .getCivRegion(CFG.game.getProvince(nArmy.iProvinceID).getCivRegionID())
                  .isKeyRegion) {
                  CFG.gameAction.disbandArmy(nArmy.iProvinceID, nArmy.iArmy, nCivID);
                  Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> DISBAND -> 222222 -> " + nArmy.iArmy);
               }
            }
         }

         Gdx.app.log("AoC", "regroupArmy_AtWar_WithoutDanger -> NOTHING 1111 -> " + nArmy.iArmy);
      } catch (IndexOutOfBoundsException var19) {
         CFG.exceptionStack(var19);
      } catch (StackOverflowError var20) {
         CFG.exceptionStack(var20);
      } catch (NullPointerException var21) {
         CFG.exceptionStack(var21);
      }

      return false;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RegroupAtWar(
      int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())
                     || CFG.game
                           .getMilitaryAccess(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())
                        > 0) {
                     if (CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getBordersWithEnemy()) {
                        boolean bordersWithOurEnemy = false;

                        for(int z = 0;
                           z < CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getNeighboringProvincesSize();
                           ++z
                        ) {
                           if (CFG.game
                              .getCivsAtWar(
                                 nCivID,
                                 CFG.game
                                    .getProvince(
                                       CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getNeighboringProvinces(z)
                                    )
                                    .getCivID()
                              )) {
                              bordersWithOurEnemy = true;
                              break;
                           }
                        }

                        if (bordersWithOurEnemy) {
                           out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                           if (iFirstFoundRange < 0) {
                              iFirstFoundRange = nIteration_Distance;
                           }
                        }
                     } else if (this.moveAtWar_BordersWithEnemyCheck(nCivID, CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i))) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                           iFirstFoundRange = nIteration_Distance;
                        }
                     }

                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 2 < nIteration_Distance) {
            break;
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var14 = null;
      return out;
   }

   protected final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RegroupPrepareForWAr(
      int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was
   ) {
      List<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      List<Integer> currProvinces = new ArrayList<>();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
         currProvinces.clear();
         ++nIteration_Distance;

         for(int a = recentlyAdded.size() - 1; a >= 0; --a) {
            boolean wasntAdded = true;

            for(int j = currProvinces.size() - 1; j >= 0; --j) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for(int a = currProvinces.size() - 1; a >= 0; --a) {
            for(int i = 0; i < CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvincesSize(); ++i) {
               if (!CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())
                     || CFG.game
                           .getMilitaryAccess(nCivID, CFG.game.getProvince(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i)).getCivID())
                        > 0) {
                     if (CFG.oAI.prepareForWar_BordersWithEnemy(nCivID, CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i))) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                           iFirstFoundRange = nIteration_Distance;
                        }
                     }

                     recentlyAdded.add(CFG.game.getProvince(currProvinces.get(a)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 2 < nIteration_Distance) {
            break;
         }
      }

      for(int j = was.size() - 1; j >= 0; --j) {
         CFG.game.getProvince(was.get(j)).was = false;
      }

      recentlyAdded.clear();
      recentlyAdded = null;
      was.clear();
      List<Integer> var13 = null;
      return out;
   }

   protected final void prepareForWar_Recruit(
      int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies, boolean forSeaInvasion
   ) {
      if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT) {
         Gdx.app.log("AoC", "RECRUIT NO MOVEMNETS POINTS 0000");
      } else if (lFrontIDsWithArmies.size() * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
            > CFG.game.getCiv(nCivID).getMovePoints()
         && Math.max((float)(CFG.game.getCiv(nCivID).getMoney() / 5L) / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.001F) < 0.048F
         && CFG.oR.nextInt(100) < 85) {
         Gdx.app.log("AoC", "RECRUIT, IT IS NOT WORTH TO RECRUIT IN THIS TURN");
      } else {
         int nUpkeepLeft = (int)(
            (float)CFG.game.getCiv(nCivID).iBudget
                  * (
                     0.8F
                        - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                        - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                  )
               - (float)CFG.game.getCiv(nCivID).iBudget * CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
         );
         if (nUpkeepLeft < 0) {
            Gdx.app.log("AoC", "RECRUIT, nUpkeepLeft: " + nUpkeepLeft);
         } else {
            if (!forSeaInvasion
               && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringSeaProvincesSize() > 0) {
               boolean aldAdded = false;

               for(int k = sortedFrontProvinces.size() - 1; k >= 0; --k) {
                  if (sortedFrontProvinces.get(k).iProvinceID == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                     aldAdded = true;
                     break;
                  }
               }

               if (!aldAdded) {
                  sortedFrontProvinces.add(
                     new AI_ProvinceInfo_War(
                        CFG.game.getCiv(nCivID).getCapitalProvinceID(),
                        this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID),
                        true
                     )
                  );
               }
            }

            int numOfUnitsToRecruit_MAX = (int)(
               (float)nUpkeepLeft
                  / (CFG.game_NextTurnUpdate.getMilitaryUpkeep_WithoutDefensivePosition(sortedFrontProvinces.get(0).iProvinceID, 1000, nCivID) / 1000.0F)
            );
            int iNumOfMaxRecruitments = Math.max(
               1,
               Math.min(
                  (
                        CFG.game.getCiv(nCivID).getMovePoints()
                           - (
                              lFrontIDsWithArmies.size() > 0
                                 ? CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * lFrontIDsWithArmies.size()
                                 : CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE
                           )
                     )
                     / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT,
                  CFG.game.getCiv(nCivID).getNumOfProvinces()
               )
            );
            Gdx.app.log("AoC", "prepareForWar_Recruit -> " + CFG.game.getCiv(nCivID).getCivName());
            Gdx.app.log("AoC", "prepareForWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
            if (lFrontIDsWithArmies.size() > 1
               && iNumOfMaxRecruitments > 1
               && Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX)
                  <= (long)sortedFrontProvinces.get(0).getRecruitableArmy(nCivID)
               && (float)Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX)
                  < (float)CFG.game.getCiv(nCivID).getNumOfUnits() * 0.35F
               && CFG.oR.nextInt(100) < 95) {
               iNumOfMaxRecruitments = 1;
            }

            Gdx.app.log("AoC", "prepareForWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
            Gdx.app.log("AoC", "prepareForWar_Recruit -> numOfUnitsToRecruit_MAX: " + numOfUnitsToRecruit_MAX);
            List<AI_ProvinceInfo_War> tRecruitArmiesForProvinces = new ArrayList<>();
            float totalValues = 0.0F;

            for(int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
               tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i));
               totalValues += sortedFrontProvinces.get(i).iValue;
            }

            int tempMoneyPre = (int)CFG.game.getCiv(nCivID).getMoney();
            boolean armyRecruited = false;

            for(int i = 0; i < tRecruitArmiesForProvinces.size(); ++i) {
               int tArmyToRecruit_PRE = (int)(
                  (float)Math.min(
                        numOfUnitsToRecruit_MAX,
                        tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                     )
                     * tRecruitArmiesForProvinces.get(i).iValue
                     / totalValues
               );
               Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MAX: " + CFG.game.getCiv(nCivID).getMoney() / 5L);
               Gdx.app.log("AoC", "prepareForWar_Recruit -> tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
               boolean notEnoughRecruits = false;
               if (tRecruitArmiesForProvinces.get(i).getRecruitableArmy(nCivID) < tArmyToRecruit_PRE) {
                  notEnoughRecruits = true;
               }

               if (!CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).isOccupied()
                  && CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getCivID() == nCivID
                  && !notEnoughRecruits) {
                  Gdx.app
                     .log(
                        "AoC",
                        "moveAtWar_Recruit -> RECRUIT MODE 1111 -> PROV: "
                           + tRecruitArmiesForProvinces.get(i).iProvinceID
                           + " -> "
                           + CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getName()
                     );
                  int tArmyToRecruit = (int)(
                     (float)Math.min(
                           numOfUnitsToRecruit_MAX,
                           Math.min(
                              numOfUnitsToRecruit_MAX,
                              tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                           )
                        )
                        * tRecruitArmiesForProvinces.get(i).iValue
                        / totalValues
                  );
                  Gdx.app
                     .log(
                        "AoC",
                        "moveAtWar_Recruit -> RECRUIT MODE 1111 -> TO RECR ARMY: "
                           + tArmyToRecruit
                           + ", RECRITABLE MAX: "
                           + CFG.gameAction.getRecruitableArmy(tRecruitArmiesForProvinces.get(i).iProvinceID, nCivID)
                     );
                  if (CFG.game.getCiv(nCivID).recruitArmy_AI(tRecruitArmiesForProvinces.get(i).iProvinceID, tArmyToRecruit)) {
                     armyRecruited = true;
                  }
               } else {
                  List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI
                     .getAllNeighboringProvincesInRange_RecruitAtWAr(
                        tRecruitArmiesForProvinces.get(i).iProvinceID,
                        nCivID,
                        Math.max(10, CFG.game.getCiv(nCivID).getNumOfProvinces() / 8),
                        true,
                        false,
                        new ArrayList<>(),
                        new ArrayList<>()
                     );
                  if (listOfPossibleProvincesToRecruit.size() > 0) {
                     Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MODE 000");
                     int tempRand = 0;
                     if (!notEnoughRecruits && CFG.oR.nextInt(100) >= 90) {
                        tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                     } else {
                        int tBest = 0;
                        int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);

                        for(int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                           if (tBestArmy < CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID)) {
                              tBest = k;
                              tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID);
                           }
                        }

                        tempRand = tBest;
                     }

                     if ((float)CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, nCivID)
                        < (float)tRecruitArmiesForProvinces.get(i).getRecruitableArmy(nCivID) * 1.2F) {
                        Gdx.app.log("AoC", "prepareForWar_Recruit -> RECRUIT MODE 000A -> ARMY CAN'T BE RECRUITED FROM NEIGH PROVINCES -> SEND BY SEA!");
                        int tArmyToRecruit = (int)(
                           (float)Math.min(
                                 numOfUnitsToRecruit_MAX,
                                 Math.min(
                                    numOfUnitsToRecruit_MAX,
                                    tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                                 )
                              )
                              * tRecruitArmiesForProvinces.get(i).iValue
                              / totalValues
                        );
                        Gdx.app
                           .log(
                              "AoC",
                              "prepareForWar_Recruit -> RECRUIT MODE 000A -> TO RECR ARMY: "
                                 + tArmyToRecruit
                                 + ", RECRITABLE MAX: "
                                 + CFG.gameAction.getRecruitableArmy(tRecruitArmiesForProvinces.get(i).iProvinceID, nCivID)
                           );
                        if (CFG.game.getCiv(nCivID).recruitArmy_AI(tRecruitArmiesForProvinces.get(i).iProvinceID, tArmyToRecruit)) {
                           armyRecruited = true;
                        }
                     } else {
                        int tArmyToRecruit = (int)(
                           (float)Math.min(
                                 numOfUnitsToRecruit_MAX,
                                 Math.min(
                                    numOfUnitsToRecruit_MAX,
                                    tempMoneyPre
                                       / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                                 )
                              )
                              * tRecruitArmiesForProvinces.get(i).iValue
                              / totalValues
                        );
                        Gdx.app
                           .log(
                              "AoC",
                              "prepareForWar_Recruit -> RECRUIT MODE 0000B -> TO RECR ARMY: "
                                 + tArmyToRecruit
                                 + ", RECRITABLE MAX: "
                                 + CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, nCivID)
                           );
                        if (CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, tArmyToRecruit)) {
                           armyRecruited = true;
                        }

                        int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID);
                        if (tempArmy > 0) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment(
                                    nCivID,
                                    listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID,
                                    tRecruitArmiesForProvinces.get(i).iProvinceID,
                                    tempArmy
                                 )
                              );
                        }
                     }
                  }
               }
            }

            if (armyRecruited && CFG.game.getCiv(nCivID).getMoney() < 25L) {
               CFG.game.getCiv(nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = true;
            }
         }
      }
   }

   protected final void moveAtWar_Recruit(
      int nCivID, List<AI_ProvinceInfo_War> sortedFrontProvinces, List<Integer> lFrontIDsWithArmies, boolean forSeaInvasion
   ) {
      if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT) {
         Gdx.app.log("AoC", "RECRUIT NO MOVEMNETS POINTS 0000");
      } else if (lFrontIDsWithArmies.size() * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
            > CFG.game.getCiv(nCivID).getMovePoints()
         && Math.max((float)(CFG.game.getCiv(nCivID).getMoney() / 5L) / (float)CFG.game.getCiv(nCivID).getNumOfUnits(), 0.001F) < 0.048F
         && CFG.oR.nextInt(100) < 85) {
         Gdx.app.log("AoC", "RECRUIT, IT IS NOT WORTH TO RECRUIT IN THIS TURN");
      } else {
         int nUpkeepLeft = (int)(
            (float)CFG.game.getCiv(nCivID).iBudget
                  * (
                     (
                           forSeaInvasion
                              ? CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY
                              : (
                                 CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0
                                    ? CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_NOT_BORDERING_WITH_ENEMY
                                    : CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_RECRUIT_AT_WAR
                              )
                        )
                        - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                        - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                  )
               - (float)CFG.game.getCiv(nCivID).iBudget * CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
         );
         if (nUpkeepLeft < 0) {
            Gdx.app.log("AoC", "RECRUIT, nUpkeepLeft: " + nUpkeepLeft);
         } else {
            if (!forSeaInvasion
               && CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getNeighboringSeaProvincesSize() > 0) {
               boolean aldAdded = false;

               for(int k = sortedFrontProvinces.size() - 1; k >= 0; --k) {
                  if (sortedFrontProvinces.get(k).iProvinceID == CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                     aldAdded = true;
                     break;
                  }
               }

               if (!aldAdded) {
                  sortedFrontProvinces.add(
                     new AI_ProvinceInfo_War(
                        CFG.game.getCiv(nCivID).getCapitalProvinceID(),
                        this.getPotential_BasedOnNeighboringProvs(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nCivID),
                        true
                     )
                  );
               }
            }

            int numOfUnitsToRecruit_MAX = (int)(
               (float)nUpkeepLeft
                  / (CFG.game_NextTurnUpdate.getMilitaryUpkeep_WithoutDefensivePosition(sortedFrontProvinces.get(0).iProvinceID, 1000, nCivID) / 1000.0F)
            );
            int iNumOfMaxRecruitments = Math.max(
               1,
               Math.min(
                  (
                        CFG.game.getCiv(nCivID).getMovePoints()
                           - (
                              lFrontIDsWithArmies.size() > 0
                                 ? CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * lFrontIDsWithArmies.size()
                                 : CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE
                           )
                     )
                     / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT,
                  CFG.game.getCiv(nCivID).getNumOfProvinces()
               )
            );
            Gdx.app.log("AoC", "moveAtWar_Recruit -> " + CFG.game.getCiv(nCivID).getCivName());
            Gdx.app.log("AoC", "moveAtWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
            if (lFrontIDsWithArmies.size() > 1
               && iNumOfMaxRecruitments > 1
               && Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX)
                  <= (long)sortedFrontProvinces.get(0).getRecruitableArmy(nCivID)
               && (float)Math.min(CFG.game.getCiv(nCivID).getMoney() / 5L, (long)numOfUnitsToRecruit_MAX)
                  < (float)CFG.game.getCiv(nCivID).getNumOfUnits() * 0.35F
               && CFG.oR.nextInt(100) < 95) {
               iNumOfMaxRecruitments = 1;
            }

            Gdx.app.log("AoC", "moveAtWar_Recruit -> iNumOfMaxRecruitments: " + iNumOfMaxRecruitments);
            Gdx.app.log("AoC", "moveAtWar_Recruit -> numOfUnitsToRecruit_MAX: " + numOfUnitsToRecruit_MAX);
            List<AI_ProvinceInfo_War> tRecruitArmiesForProvinces = new ArrayList<>();
            float totalValues = 0.0F;

            for(int i = 0; i < iNumOfMaxRecruitments && i < sortedFrontProvinces.size(); ++i) {
               tRecruitArmiesForProvinces.add(sortedFrontProvinces.get(i));
               totalValues += sortedFrontProvinces.get(i).iValue;
            }

            int tempMoneyPre = (int)CFG.game.getCiv(nCivID).getMoney();
            boolean armyRecruited = false;

            for(int i = 0; i < tRecruitArmiesForProvinces.size(); ++i) {
               int tArmyToRecruit_PRE = (int)(
                  (float)Math.min(
                        numOfUnitsToRecruit_MAX,
                        tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                     )
                     * tRecruitArmiesForProvinces.get(i).iValue
                     / totalValues
               );
               Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MAX: " + CFG.game.getCiv(nCivID).getMoney() / 5L);
               Gdx.app.log("AoC", "moveAtWar_Recruit -> tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
               boolean notEnoughRecruits = false;
               if (tRecruitArmiesForProvinces.get(i).getRecruitableArmy(nCivID) < tArmyToRecruit_PRE) {
                  notEnoughRecruits = true;
               }

               if (!CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).isOccupied()
                  && CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getCivID() == nCivID
                  && !notEnoughRecruits) {
                  Gdx.app
                     .log(
                        "AoC",
                        "moveAtWar_Recruit -> RECRUIT MODE 1111 -> PROV: "
                           + tRecruitArmiesForProvinces.get(i).iProvinceID
                           + " -> "
                           + CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getName()
                     );
                  int tArmyToRecruit = (int)(
                     (float)Math.min(
                           numOfUnitsToRecruit_MAX,
                           Math.min(
                              numOfUnitsToRecruit_MAX,
                              tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                           )
                        )
                        * tRecruitArmiesForProvinces.get(i).iValue
                        / totalValues
                  );
                  Gdx.app
                     .log(
                        "AoC",
                        "moveAtWar_Recruit -> RECRUIT MODE 1111 -> TO RECR ARMY: "
                           + tArmyToRecruit
                           + ", RECRITABLE MAX: "
                           + CFG.gameAction.getRecruitableArmy(tRecruitArmiesForProvinces.get(i).iProvinceID, nCivID)
                     );
                  if (CFG.game.getCiv(nCivID).recruitArmy_AI(tRecruitArmiesForProvinces.get(i).iProvinceID, tArmyToRecruit)) {
                     armyRecruited = true;
                  }
               } else {
                  List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI
                     .getAllNeighboringProvincesInRange_RecruitAtWAr(
                        tRecruitArmiesForProvinces.get(i).iProvinceID,
                        nCivID,
                        Math.max(10, CFG.game.getCiv(nCivID).getNumOfProvinces() / 8),
                        true,
                        false,
                        new ArrayList<>(),
                        new ArrayList<>()
                     );
                  if (listOfPossibleProvincesToRecruit.size() > 0) {
                     Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000");
                     int tempRand = 0;
                     if (!notEnoughRecruits && CFG.oR.nextInt(100) >= 90) {
                        tempRand = CFG.oR.nextInt(listOfPossibleProvincesToRecruit.size());
                     } else {
                        int tBest = 0;
                        int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);

                        for(int k = 1; k < listOfPossibleProvincesToRecruit.size(); ++k) {
                           if (tBestArmy < CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID)) {
                              tBest = k;
                              tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(k).iProvinceID);
                           }
                        }

                        tempRand = tBest;
                     }

                     if ((float)CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, nCivID)
                        < (float)tRecruitArmiesForProvinces.get(i).getRecruitableArmy(nCivID) * 1.2F) {
                        Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000A -> ARMY CAN'T BE RECRUITED FROM NEIGH PROVINCES -> SEND BY SEA!");
                        int tArmyToRecruit = (int)(
                           (float)Math.min(
                                 numOfUnitsToRecruit_MAX,
                                 Math.min(
                                    numOfUnitsToRecruit_MAX,
                                    tempMoneyPre / (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                                 )
                              )
                              * tRecruitArmiesForProvinces.get(i).iValue
                              / totalValues
                        );
                        Gdx.app
                           .log(
                              "AoC",
                              "moveAtWar_Recruit -> RECRUIT MODE 000A -> TO RECR ARMY: "
                                 + tArmyToRecruit
                                 + ", RECRITABLE MAX: "
                                 + CFG.gameAction.getRecruitableArmy(tRecruitArmiesForProvinces.get(i).iProvinceID, nCivID)
                           );
                        if (CFG.game.getCiv(nCivID).recruitArmy_AI(tRecruitArmiesForProvinces.get(i).iProvinceID, tArmyToRecruit)) {
                           armyRecruited = true;
                        }
                     } else {
                        int tArmyToRecruit = (int)(
                           (float)Math.min(
                                 numOfUnitsToRecruit_MAX,
                                 Math.min(
                                    numOfUnitsToRecruit_MAX,
                                    tempMoneyPre
                                       / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                                 )
                              )
                              * tRecruitArmiesForProvinces.get(i).iValue
                              / totalValues
                        );
                        Gdx.app
                           .log(
                              "AoC",
                              "moveAtWar_Recruit -> RECRUIT MODE 0000B -> TO RECR ARMY: "
                                 + tArmyToRecruit
                                 + ", RECRITABLE MAX: "
                                 + CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, nCivID)
                           );
                        if (CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID, tArmyToRecruit)) {
                           armyRecruited = true;
                        }

                        int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID);
                        if (tempArmy > 0) {
                           CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .lArmiesMissions
                              .add(
                                 new CivArmyMission_RegroupAfterRecruitment_War(
                                    nCivID,
                                    listOfPossibleProvincesToRecruit.get(tempRand).iProvinceID,
                                    tRecruitArmiesForProvinces.get(i).iProvinceID,
                                    tempArmy
                                 )
                              );
                        }
                     }
                  } else {
                     Gdx.app.log("AoC", "moveAtWar_Recruit -> RECRUIT MODE 000 -> ARMY CAN'T BE RECRUITED FROM NEIGH PROVINCES -> SEND BY SEA!");
                     if (!forSeaInvasion) {
                        boolean addMission = true;

                        for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
                           if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.NAVAL_INVASION
                              && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).toProvinceID
                                 == tRecruitArmiesForProvinces.get(i).iProvinceID) {
                              addMission = false;
                              break;
                           }
                        }

                        if (addMission) {
                           int tMoveTo = tRecruitArmiesForProvinces.get(i).iProvinceID;
                           if (CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringSeaProvincesSize() == 0) {
                              boolean provinceUpdated = false;

                              for(int z = 0; z < CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringSeaProvincesSize(); ++z) {
                                 if (CFG.game
                                          .getProvince(CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringProvinces(z))
                                          .getLevelOfPort()
                                       >= 0
                                    && (
                                       CFG.game
                                                .getProvince(CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringProvinces(z))
                                                .getCivID()
                                             == nCivID
                                          || CFG.game
                                             .getCivsAtWar(
                                                nCivID,
                                                CFG.game
                                                   .getProvince(CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringProvinces(z))
                                                   .getCivID()
                                             )
                                    )) {
                                    if (provinceUpdated) {
                                       if (CFG.oR.nextInt(100) < 50) {
                                          tMoveTo = CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringProvinces(z);
                                       }
                                    } else {
                                       tMoveTo = CFG.game.getProvince(tRecruitArmiesForProvinces.get(i).iProvinceID).getNeighboringProvinces(z);
                                    }
                                 }
                              }
                           }

                           this.moveAtWar_AtSea_ToProvinceID(nCivID, tMoveTo);
                        }
                     }
                  }
               }
            }

            if (armyRecruited && CFG.game.getCiv(nCivID).getMoney() < 25L) {
               CFG.game.getCiv(nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = true;
            }
         }
      }
   }

   protected void buildStartingBuildings(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getTower_TechLevel(1) * 1.04F) {
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfWatchTower(1);
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(1) * 0.96F) {
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setLevelOfFort(1);
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getPort_TechLevel(1) * 1.1F) {
               this.buildStartingBuildings_Port(nCivID);
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   protected final void buildStartingBuildings_Port(int nCivID) {
      int buildPortInProvinceID = -1;
      if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getLevelOfPort() >= 0) {
         buildPortInProvinceID = CFG.game.getCiv(nCivID).getCapitalProvinceID();
      } else {
         for(int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).getLevelOfPort() == 0) {
               if (buildPortInProvinceID < 0) {
                  buildPortInProvinceID = CFG.game.getCiv(nCivID).getProvinceID(j);
               } else if (CFG.game.getProvince(buildPortInProvinceID).getPopulationData().getPopulation()
                  < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).getPopulationData().getPopulation()) {
                  buildPortInProvinceID = CFG.game.getCiv(nCivID).getProvinceID(j);
               }
            }
         }
      }

      if (buildPortInProvinceID >= 0 && CFG.game.getProvince(buildPortInProvinceID).getLevelOfPort() >= 0) {
         CFG.game.getProvince(buildPortInProvinceID).setLevelOfPort(1);
      }
   }

   protected static final long getMoney_MinReserve_LockTreasury(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment != null
         ? (long)Math.max(CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment.iCost, CFG.game.getCiv(nCivID).civGameData.iLockTreasury)
         : (long)CFG.game.getCiv(nCivID).civGameData.iLockTreasury;
   }

   protected static final long getMoney_MinReserve(int nCivID) {
      return (long)Math.max(
         (float)getMoney_MinReserve_LockTreasury(nCivID),
         (float)CFG.game.getCiv(nCivID).iBudget * CFG.game.getCiv(nCivID).civGameData.civPersonality.TREASURY_RESERVE
      );
   }

   protected void manageBudget(int nCivID) {
      CFG.game
         .getCiv(nCivID)
         .setSpendings_Goods(
            Math.max(
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID) + 0.01F,
               CFG.game.getCiv(nCivID).getSpendings_Goods()
            )
         );
      CFG.game
         .getCiv(nCivID)
         .setSpendings_Investments(
            Math.max(
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS + 0.01F,
               CFG.game.getCiv(nCivID).getSpendings_Investments()
            )
         );
      if (!CFG.game.getCiv(nCivID).isAtWar() && !CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar()) {
         float reserveModifier = 1.0F;
         if (CFG.game.getCiv(nCivID).getMoney() < getMoney_MinReserve_LockTreasury(nCivID)) {
            reserveModifier = 0.275F;
            if (CFG.game.getCiv(nCivID).getMoney() > 0L) {
               reserveModifier += 0.225F * ((float)CFG.game.getCiv(nCivID).getMoney() / (float)getMoney_MinReserve_LockTreasury(nCivID));
            }
         } else {
            if (CFG.game.getCiv(nCivID).getMoney() < getMoney_MinReserve(nCivID)) {
               if (CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0F > 0.0F) {
                  reserveModifier = 1.0F + CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0F;
                  CFG.game.getCiv(nCivID).civGameData.civPersonality.TREASURY_RESERVE = Math.max(
                     2.25F, CFG.game.getCiv(nCivID).civGameData.civPersonality.TREASURY_RESERVE - 0.75F
                  );
               } else {
                  reserveModifier = CFG.game.getCiv(nCivID).civGameData.civPersonality.TREASURY_RESERVE_MODIFIER
                     + (1.0F - CFG.game.getCiv(nCivID).civGameData.civPersonality.TREASURY_RESERVE_MODIFIER)
                        * (float)CFG.game.getCiv(nCivID).getMoney()
                        / ((float)CFG.game.getCiv(nCivID).iBudget * CFG.game.getCiv(nCivID).civGameData.civPersonality.TREASURY_RESERVE);
               }
            } else if (CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0F > 0.0F) {
               reserveModifier = 1.0F + CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0F;
            }

            if (CFG.game.getCiv(nCivID).lProvincesWithLowStability.size() > 0) {
               int tAssimilateCost = DiplomacyManager.assimilateCost(CFG.game.getCiv(nCivID).lProvincesWithLowStability.get(0), 25)
                  * CFG.game.getCiv(nCivID).lProvincesWithLowStability.size();
               reserveModifier = Math.min(reserveModifier, 0.1F + 0.9F * (float)CFG.game.getCiv(nCivID).getMoney() / (float)tAssimilateCost);
            }
         }

         float fHappinessLeft = 0.7F;
         float happinessDiff;
         if (CFG.game.getCiv(nCivID).getHappiness() - CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV < 0) {
            if (CFG.game.getCiv(nCivID).getHappiness() < 20) {
               fHappinessLeft = (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0F / 16.0F;
               happinessDiff = 0.0F;
            } else if (CFG.game.getCiv(nCivID).getHappiness() < 40) {
               fHappinessLeft = (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0F / 4.0F;
               happinessDiff = 0.0F;
            } else if (CFG.game.getCiv(nCivID).getHappiness() < 55) {
               fHappinessLeft = (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0F / 2.0F;
               happinessDiff = 0.0F;
            } else {
               happinessDiff = (1.0F - fHappinessLeft)
                  * ((float)CFG.game.getCiv(nCivID).getHappiness() / (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV);
            }
         } else {
            happinessDiff = 1.0F - fHappinessLeft;
            if (CFG.game.getCiv(nCivID).getHappiness() > 80
               && (float)CFG.game.getCiv(nCivID).getHappiness()
                  > CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL * 115.0F) {
               happinessDiff = 1.0F - fHappinessLeft + (float)CFG.oR.nextInt(1450) / 1000.0F;
            }
         }

         CFG.game
            .getCiv(nCivID)
            .setTaxationLevel(
               (
                     CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * fHappinessLeft
                        + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * happinessDiff
                  )
                  * CFG.game.getCiv(nCivID).civGameData.civPersonality.TAXATION_LEVEL
            );
         this.updateMilitarySpendings(nCivID);
         float nSpendingsLeft = 0.97F
            - CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
            - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
            - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS;
         if (nSpendingsLeft > 0.0F) {
            if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
               nSpendingsLeft *= 0.04F + (float)CFG.oR.nextInt(46) / 100.0F;
               float tTotal = CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Goods(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                     )
                  );
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Investments(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                     )
                  );
               CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
            } else {
               float extraDevelopment = 1.0F;
               if (CFG.game.getCiv(nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel()
                  < CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY) {
                  extraDevelopment = 1.0F
                     + (
                           CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY
                              - CFG.game.getCiv(nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel()
                        )
                        / CFG.game.getCiv(nCivID).getTechnologyLevel();
               }

               float tTotal = CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET * extraDevelopment
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Goods(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS
                              * reserveModifier,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                     )
                  );
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Investments(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                              * extraDevelopment
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS
                              * reserveModifier,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                     )
                  );
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Research(
                     CFG.game.getCiv(nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET
                        / tTotal
                        * nSpendingsLeft
                        * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS
                        * reserveModifier
                  );
            }
         } else {
            CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
         }
      } else {
         if (CFG.game.getCiv(nCivID).isAtWarWithCivs.size() <= 0) {
            CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR = CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS;
         } else {
            int iBudgetOfEnemies = 0;

            for(int i = CFG.game.getCiv(nCivID).isAtWarWithCivs.size() - 1; i >= 0; --i) {
               iBudgetOfEnemies = (int)(
                  (float)iBudgetOfEnemies
                     + Math.max(
                        1.0F,
                        (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).isAtWarWithCivs.get(i)).iBudget
                           * CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR_MODIFIER
                     )
               );
            }

            CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR = Math.max(
               Math.min(2.0F, (float)iBudgetOfEnemies / (float)CFG.game.getCiv(nCivID).iBudget),
               CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS
            );
         }

         float fHappinessLeft = 0.7F;
         float happinessDiff;
         if (CFG.game.getCiv(nCivID).getHappiness() - CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV < 0) {
            if (CFG.game.getCiv(nCivID).getHappiness() < 20) {
               fHappinessLeft = (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0F / 16.0F;
               happinessDiff = 0.0F;
            } else if (CFG.game.getCiv(nCivID).getHappiness() < 40) {
               fHappinessLeft = (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0F / 4.0F;
               happinessDiff = 0.0F;
            } else if (CFG.game.getCiv(nCivID).getHappiness() < 55) {
               fHappinessLeft = (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV / 100.0F / 2.0F;
               happinessDiff = 0.0F;
            } else {
               happinessDiff = (1.0F - fHappinessLeft)
                  * ((float)CFG.game.getCiv(nCivID).getHappiness() / (float)CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_HAPPINESS_FOR_CIV);
            }
         } else {
            happinessDiff = 1.0F - fHappinessLeft;
            if (CFG.game.getCiv(nCivID).getHappiness() > 80
               && (float)CFG.game.getCiv(nCivID).getHappiness()
                  > CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL * 115.0F) {
               happinessDiff = 1.0F - fHappinessLeft + (float)CFG.oR.nextInt(1450) / 1000.0F;
            }
         }

         CFG.game
            .getCiv(nCivID)
            .setTaxationLevel(
               (
                     CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * fHappinessLeft
                        + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION * happinessDiff
                  )
                  * CFG.game.getCiv(nCivID).civGameData.civPersonality.TAXATION_LEVEL
            );
         this.updateMilitarySpendings(nCivID);
         float reserveModifier = 0.675F;
         float nSpendingsLeft = 0.97F
            - CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
            - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
            - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS;
         if (CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR > CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC) {
            reserveModifier = reserveModifier
               - 0.4F
               - 0.375F
                  * (
                     1.0F
                        - CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
                           / Math.min(1.0F, CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_MILITARY_SPENDINGS_WAR)
                  );
         } else {
            int i = CFG.game.getCiv(nCivID).isAtWarWithCivs.size() - 1;

            while(i >= 0) {
               --i;
            }

            reserveModifier = 0.25F;
         }

         if (CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0F > 0.0F) {
            reserveModifier = 1.0F + CFG.game_NextTurnUpdate.getInflationPerc(nCivID) * 100.0F;
         }

         if (nSpendingsLeft > 0.0F) {
            if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
               nSpendingsLeft *= 0.04F + (float)CFG.oR.nextInt(46) / 100.0F;
               float tTotal = CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Goods(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                     )
                  );
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Investments(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                     )
                  );
               CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
            } else {
               float extraDevelopment = 1.0F;
               if (CFG.game.getCiv(nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel()
                  < CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_DIFFERENCE_IN_DEVELOPMENT_TO_TECHNOLOGY) {
                  extraDevelopment = 1.0F - CFG.game.getCiv(nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel();
               }

               float tTotal = CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                  + (
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                     )
                     * extraDevelopment
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET;
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Goods(
                     Math.max(
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                           + CFG.game.getCiv(nCivID).civGameData.civPersonality.GOODS_EXTRA_PERC_OF_BUDGET
                              / tTotal
                              * nSpendingsLeft
                              * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS
                              * reserveModifier,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
                     )
                  );
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Investments(
                     Math.max(
                        (
                              CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                                 + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                                 + (
                                       CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                                          + CFG.game.getCiv(nCivID).civGameData.civPersonality.INVESTMENTS_EXTRA_PERC_OF_BUDGET
                                    )
                                    * extraDevelopment
                           )
                           / tTotal
                           * nSpendingsLeft
                           * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS
                           * reserveModifier,
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS
                     )
                  );
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Research(
                     CFG.game.getCiv(nCivID).civGameData.civPersonality.RESEARCH_PERC_OF_BUDGET
                        / tTotal
                        * nSpendingsLeft
                        * CFG.game.getCiv(nCivID).civGameData.civPersonality.USE_OF_BUDGET_FOR_SPENDINGS
                        * reserveModifier
                  );
            }
         } else {
            CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
         }
      }
   }

   protected final void manageVassalsTribute(int nCivID) {
      try {
         for(int i = 0; i < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); ++i) {
            CFG.game
               .getCiv(nCivID)
               .civGameData
               .lVassals
               .get(i)
               .setTribute(
                  (int)(
                     20.0F
                        * (
                           CFG.game.getCiv(nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC
                              - (
                                 CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID) > 0.0F
                                    ? CFG.game.getCiv(nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC
                                       * CFG.game.getCiv(nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC_FRIENDLY
                                       * CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID)
                                       / 100.0F
                                    : 0.0F
                              )
                              + (float)CFG.oR.nextInt((int)(CFG.game.getCiv(nCivID).civGameData.civPersonality.VASSALS_TRIBUTE_PERC_RAND * 100.0F)) / 100.0F
                        )
                  )
               );
         }
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      }
   }

   protected final void happinessCrisis(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() > 0
            && CFG.game.getCiv(nCivID).getMovePoints() >= 8
            && (float)CFG.game.getCiv(nCivID).getMoney()
               >= 0.5F * (float)DiplomacyManager.festivalCost(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(0))) {
            List<Assimilate_Data> tempProvincesScore = new ArrayList<>();
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
               CFG.game.getCiv(nCivID).getCapitalProvinceID();
            } else {
               CFG.game.getCiv(nCivID).getProvinceID(0);
            }

            for(int i = CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() - 1; i >= 0; --i) {
               if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getHappiness() < Game_Action.RISE_REVOLT_RISK_HAPPINESS) {
                  tempProvincesScore.add(
                     new Assimilate_Data(
                        CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i),
                        (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getPopulationData().getPopulation()
                           * (1.0F - CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getHappiness() / 4.0F)
                     )
                  );
               } else {
                  tempProvincesScore.add(
                     new Assimilate_Data(
                        CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i),
                        (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getPopulationData().getPopulation()
                           * (1.0F - CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getHappiness())
                     )
                  );
               }
            }

            List<Assimilate_Data> tempSorted = new ArrayList<>();

            while(tempProvincesScore.size() > 0) {
               int tBest = 0;

               for(int i = tBest + 1; i < tempProvincesScore.size(); ++i) {
                  if (tempProvincesScore.get(i).fScore > tempProvincesScore.get(tBest).fScore) {
                     tBest = i;
                  }
               }

               tempSorted.add(tempProvincesScore.get(tBest));
               tempProvincesScore.remove(tBest);
            }

            while(
               CFG.game.getCiv(nCivID).getMovePoints() >= 8 && tempSorted.size() != 0 && DiplomacyManager.addFestival(nCivID, tempSorted.get(0).iProvinceID)
            ) {
               tempSorted.remove(0);
            }

            tempSorted.clear();
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      } catch (NullPointerException var8) {
         CFG.exceptionStack(var8);
      }
   }

   protected void updateMilitarySpendings(int nCivID) {
      CFG.game.getCiv(nCivID).iMilitaryUpkeep_Total = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(nCivID);
      if (CFG.game.getCiv(nCivID).iBudget <= 0 && CFG.game.getCiv(nCivID).getNumOfUnits() > 0) {
         CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC = 100.0F;
      } else {
         CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC = Math.max(
            0.0F, (float)CFG.game.getCiv(nCivID).iMilitaryUpkeep_Total / (float)CFG.game.getCiv(nCivID).iBudget
         );
      }
   }

   protected long build_GetMoney(int nCivID) {
      return CFG.game.getCiv(nCivID).getMoney() < getMoney_MinReserve(nCivID) ? 0L : CFG.game.getCiv(nCivID).getMoney() - getMoney_MinReserve(nCivID);
   }

   protected void buildBuildings(int nCivID) {
      if (this.build_GetMoney(nCivID) > 0L) {
         List<AI_Build> buildingsScore = new ArrayList<>();
         List<AI_Build_Option> buildingsOptions = new ArrayList<>();

         try {
            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFarm_TechLevel(1)
               && CFG.game.getCiv(nCivID).iNumOf_Farms_ProvincesPossibleToBuild * BuildingsManager.getWorkshop_MaxLevel_CanBuild(nCivID)
                  > CFG.game.getCiv(nCivID).iNumOf_Farms) {
               buildingsOptions.add(new AI_Build_Option());
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getWorkshop_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getWorkshop_MaxLevel_CanBuild(nCivID)
                  > CFG.game.getCiv(nCivID).iNumOf_Workshops) {
               buildingsOptions.add(new AI_Build_Option_Workshop());
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getLibrary_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getLibrary_MaxLevel_CanBuild(nCivID)
                  > CFG.game.getCiv(nCivID).iNumOf_Libraries) {
               buildingsOptions.add(new AI_Build_Option_Library());
            }

            if (CFG.game.getCiv(nCivID).getSeaAccess() > 0
               && CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getPort_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv(nCivID).iNumOf_Ports) {
               buildingsOptions.add(new AI_Build_Option_Port());
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getArmoury_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv(nCivID).iNumOf_Armories) {
               buildingsOptions.add(new AI_Build_Option_Armoury());
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getSupply_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() > CFG.game.getCiv(nCivID).iNumOf_SuppliesCamp) {
               buildingsOptions.add(new AI_Build_Option_Supplies());
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getFort_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getFort_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv(nCivID).iNumOf_Forts) {
               buildingsOptions.add(new AI_Build_Option_Fort());
            }

            if (CFG.game.getCiv(nCivID).getTechnologyLevel() >= BuildingsManager.getTower_TechLevel(1)
               && CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getTower_MaxLevel_CanBuild(nCivID) > CFG.game.getCiv(nCivID).iNumOf_Towers) {
               buildingsOptions.add(new AI_Build_Option_Tower());
            }

            buildingsOptions.add(new AI_Build_Option_Invest());
            if (CFG.game.getCiv(nCivID).fAverageDevelopment / CFG.game.getCiv(nCivID).getTechnologyLevel() < 0.9F) {
               buildingsOptions.add(new AI_Build_Option_Invest_Development());
            }

            if (buildingsOptions.size() > 0) {
               int tBestScore = 0;

               for(int i = tBestScore + 1; i < buildingsOptions.size(); ++i) {
                  if (buildingsOptions.get(i).getScore(nCivID) > buildingsOptions.get(tBestScore).getScore(nCivID)) {
                     tBestScore = i;
                  }
               }

               buildingsScore.add(buildingsOptions.get(tBestScore).getData(nCivID));
               if (buildingsScore.get(0).build(nCivID, 0, false)) {
                  CFG.game.getCiv(nCivID).buildCivPersonality_Buildings();
               }
            }
         } catch (IndexOutOfBoundsException var6) {
            CFG.exceptionStack(var6);
         } catch (NullPointerException var7) {
            CFG.exceptionStack(var7);
         }

         buildingsOptions.clear();
         buildingsOptions = null;
         buildingsScore.clear();
         buildingsScore = null;
      }
   }

   protected final void prepareArmyForRevolution(int nCivID) {
   }

   protected final void assimilateProvinces(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 6
            && (float)CFG.game.getCiv(nCivID).getMoney()
               >= 1.225F * (float)DiplomacyManager.assimilateCost(CFG.game.getCiv(nCivID).lProvincesWithLowStability.get(0), 10)) {
            List<Assimilate_Data> tempAssimilateProvinces = new ArrayList<>();
            int tempCapital = CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               ? CFG.game.getCiv(nCivID).getCapitalProvinceID()
               : CFG.game.getCiv(nCivID).getProvinceID(0);

            for(int i = CFG.game.getCiv(nCivID).lProvincesWithLowStability.size() - 1; i >= 0; --i) {
               tempAssimilateProvinces.add(
                  new Assimilate_Data(
                     CFG.game.getCiv(nCivID).lProvincesWithLowStability.get(i),
                     CFG.game.getCiv(nCivID).civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE
                           * Math.min(
                              (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowStability.get(i)).getPopulationData().getPopulation()
                                 / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation(),
                              1.0F
                           )
                        + CFG.game.getCiv(nCivID).civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE
                           * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(tempCapital, CFG.game.getCiv(nCivID).lProvincesWithLowStability.get(i))
                        + CFG.game.getCiv(nCivID).civGameData.civPersonality.ASSIMILATE_PERC_LOW_STABILITY_SCORE
                           * (1.0F - CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowStability.get(i)).getProvinceStability())
                  )
               );
            }

            List<Assimilate_Data> tempSortedAssimilate = new ArrayList<>();

            while(tempAssimilateProvinces.size() > 0) {
               int tBest = 0;

               for(int i = tBest + 1; i < tempAssimilateProvinces.size(); ++i) {
                  if (tempAssimilateProvinces.get(i).fScore > tempAssimilateProvinces.get(tBest).fScore) {
                     tBest = i;
                  }
               }

               tempSortedAssimilate.add(tempAssimilateProvinces.get(tBest));
               tempAssimilateProvinces.remove(tBest);
            }

            while(
               CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 6
                  && tempSortedAssimilate.size() != 0
                  && (float)CFG.game.getCiv(nCivID).getMoney() >= 1.225F * (float)DiplomacyManager.assimilateCost(tempSortedAssimilate.get(0).iProvinceID, 10)
                  && DiplomacyManager.addAssimilate(
                     nCivID,
                     tempSortedAssimilate.get(0).iProvinceID,
                     (int)Math.min(
                        Math.min(
                           (100.0F - CFG.game.getProvince(tempSortedAssimilate.get(0).iProvinceID).getProvinceStability() * 100.0F) / 1.724F,
                           (float)(CFG.game.getCiv(nCivID).getMoney() / (long)DiplomacyManager.assimilateCost(tempSortedAssimilate.get(0).iProvinceID, 1))
                        ),
                        50.0F
                     )
                  )
            ) {
               tempSortedAssimilate.remove(0);
            }

            tempSortedAssimilate.clear();
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      } catch (NullPointerException var8) {
         CFG.exceptionStack(var8);
      }
   }

   protected final void hostFestivals(int nCivID, int iLimit) {
      try {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 8
            && (float)CFG.game.getCiv(nCivID).getMoney()
               >= 1.05F * (float)DiplomacyManager.festivalCost(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(0))) {
            List<Assimilate_Data> tempProvincesScore = new ArrayList<>();
            int tempCapital = CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               ? CFG.game.getCiv(nCivID).getCapitalProvinceID()
               : CFG.game.getCiv(nCivID).getProvinceID(0);

            for(int i = CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() - 1; i >= 0; --i) {
               tempProvincesScore.add(
                  new Assimilate_Data(
                     CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i),
                     (
                           CFG.game.getCiv(nCivID).civGameData.civPersonality.ASSIMILATE_PERC_POPULATION_SCORE
                                 * Math.min(
                                    (float)CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getPopulationData().getPopulation()
                                       / (float)CFG.game.getGameScenarios().getScenario_StartingPopulation(),
                                    1.0F
                                 )
                              + CFG.game.getCiv(nCivID).civGameData.civPersonality.ASSIMILATE_PERC_DISTANCE_SCORE
                                 * CFG.game_NextTurnUpdate
                                    .getDistanceFromCapital_PercOfMax(tempCapital, CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i))
                        )
                        * CFG.game.getProvince(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(i)).getProvinceStability()
                  )
               );
            }

            List<Assimilate_Data> tempSorted = new ArrayList<>();

            while(tempProvincesScore.size() > 0) {
               int tBest = 0;

               for(int i = tBest + 1; i < tempProvincesScore.size(); ++i) {
                  if (tempProvincesScore.get(i).fScore > tempProvincesScore.get(tBest).fScore) {
                     tBest = i;
                  }
               }

               tempSorted.add(tempProvincesScore.get(tBest));
               tempProvincesScore.remove(tBest);
            }

            while(
               CFG.game.getCiv(nCivID).getMovePoints() >= 8
                  && tempSorted.size() != 0
                  && (float)CFG.game.getCiv(nCivID).getMoney() >= 1.125F * (float)DiplomacyManager.festivalCost(tempSorted.get(0).iProvinceID)
                  && DiplomacyManager.addFestival(nCivID, tempSorted.get(0).iProvinceID)
            ) {
               tempSorted.remove(0);
               if (iLimit-- <= 0) {
                  return;
               }
            }

            tempSorted.clear();
         }
      } catch (IndexOutOfBoundsException var8) {
         CFG.exceptionStack(var8);
      } catch (NullPointerException var9) {
         CFG.exceptionStack(var9);
      }
   }

   protected final void changeTypeOfIdeology(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment != null) {
         if (CFG.game.getCiv(nCivID).isAtWar()) {
            CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment = null;
         } else if (CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment.action(nCivID)) {
            CFG.game.getCiv(nCivID).civGameData.changeTypeOfGoverment = null;
         }
      }
   }

   protected final void relocateLostCapital(int nCivID) {
      try {
         if (CFG.game.getCiv(nCivID).getCapitalProvinceID() == CFG.game.getCiv(nCivID).getCoreCapitalProvinceID()
            || CFG.game.getCiv(nCivID).getCoreCapitalProvinceID() < 0
            || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCoreCapitalProvinceID()).getCivID() != nCivID
            || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCoreCapitalProvinceID()).isOccupied()
            || CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID) {
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0
               || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() != nCivID
                  && (
                     !CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied()
                        || !CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID())
                  )) {
               int bestProvinceID = CFG.game.getCiv(nCivID).getProvinceID(0);
               int bestScore = this.relocateLostCapital_ProvinceScore(nCivID, CFG.game.getCiv(nCivID).getProvinceID(0));

               for(int j = 1; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++j) {
                  int tempScore = this.relocateLostCapital_ProvinceScore(nCivID, CFG.game.getCiv(nCivID).getProvinceID(j));
                  if (bestScore < tempScore) {
                     bestScore = tempScore;
                     bestProvinceID = CFG.game.getCiv(nCivID).getProvinceID(j);
                  }
               }

               if (!CFG.game.getProvince(bestProvinceID).isOccupied()) {
                  CFG.gameAction.moveCapital(nCivID, bestProvinceID);
               }
            }
         } else if ((float)CFG.game.getCiv(nCivID).getMoney() > (float)CFG.gameAction.moveCapital_Cost(nCivID) * 4.76124F) {
            CFG.gameAction.moveCapital(nCivID, CFG.game.getCiv(nCivID).getCoreCapitalProvinceID());
         }
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      } catch (NullPointerException var7) {
         CFG.exceptionStack(var7);
      }
   }

   protected final int relocateLostCapital_ProvinceScore(int nCivID, int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).isOccupied()
         ? -1
         : (int)(
            (float)CFG.game.getProvince(nCivID).getPopulationData().getPopulationOfCivID(nCivID)
               + (float)CFG.game.getProvince(nCivID).getPopulationData().getPopulation() / 15.0F
               + (float)CFG.game.getProvince(nCivID).getEconomy() / 6.0F
         );
   }

   protected final void respondToEvents(int nCivID) {
      CFG.game.getCiv(nCivID).runNextEvent();
   }

   protected final void respondToMessages(int nCivID) {
      try {
         for(int i = CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1; i >= 0; --i) {
            switch(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).messageType) {
               case PEACE_TREATY_LIST_OF_DEMANDS:
                  Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> " + CFG.game.getCiv(nCivID).getCivName());

                  try {
                     if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                        break;
                     }

                     int peaceID = CFG.game
                        .getPeaceTreaty_GameDataID(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).TAG);
                     if (peaceID >= 0) {
                        PeaceTreaty_Data tempData = new PeaceTreaty_Data(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData);
                        int warID = CFG.game
                           .getWarID(
                              tempData.peaceTreatyGameData.lCivsData_Defenders.get(0).iCivID, tempData.peaceTreatyGameData.lCivsData_Aggressors.get(0).iCivID
                           );
                        boolean canEnd = CFG.game.getCiv(nCivID).getNumOfProvinces() == 0;
                        if (!canEnd) {
                           try {
                              canEnd = Game_Calendar.TURN_ID > CFG.game.getWar(warID).getWarTurnID() + 39;
                           } catch (IndexOutOfBoundsException var12) {
                              CFG.exceptionStack(var12);
                           }
                        }

                        Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 000");
                        if (canEnd) {
                           Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 111");
                           DiplomacyManager.acceptPeaceTreaty(
                              nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).TAG
                           );
                        } else if (CFG.game.lPeaceTreaties.size() > 0) {
                           Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 2222");
                           int powerLeft = 0;
                           int powerRight = 0;
                           boolean canEnd_V2 = false;
                           Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 3333");

                           try {
                              for(int o = 0; o < tempData.peaceTreatyGameData.lCivsData_Defenders.size(); ++o) {
                                 if (CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get(o).iCivID).getNumOfProvinces() > 0) {
                                    powerLeft = (int)(
                                       (long)powerLeft
                                          + Math.max(CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get(o).iCivID).getMoney(), 0L) / 5L
                                          + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get(o).iCivID).getNumOfUnits()
                                          + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Defenders.get(o).iCivID).getNumOfProvinces()
                                    );
                                 } else {
                                    canEnd_V2 = true;
                                 }
                              }

                              for(int o = 0; o < tempData.peaceTreatyGameData.lCivsData_Aggressors.size(); ++o) {
                                 if (CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get(o).iCivID).getNumOfProvinces() > 0) {
                                    powerRight = (int)(
                                       (long)powerRight
                                          + Math.max(CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get(o).iCivID).getMoney(), 0L) / 5L
                                          + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get(o).iCivID).getNumOfUnits()
                                          + (long)CFG.game.getCiv(tempData.peaceTreatyGameData.lCivsData_Aggressors.get(o).iCivID).getNumOfProvinces()
                                    );
                                 } else {
                                    canEnd_V2 = true;
                                 }
                              }
                           } catch (IndexOutOfBoundsException var13) {
                              canEnd_V2 = true;
                           }

                           Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 4444");
                           if (canEnd_V2) {
                              Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 5555");
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                           } else if (CFG.game.getWar(warID).getIsDefender(nCivID)) {
                              Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 666");
                              if ((float)powerLeft > (float)powerRight * 0.475F) {
                                 Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 7777");
                                 CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                              } else {
                                 Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 8888");
                                 CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                              }
                           } else {
                              Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 999");
                              if ((float)powerRight > (float)powerLeft * 0.475F) {
                                 Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 10");
                                 CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                              } else {
                                 Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 11");
                                 CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                              }
                           }
                        } else {
                           Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> 12");
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                     }

                     Gdx.app.log("AoC", "respondToMessages -> PEACE_TREATY_LIST_OF_DEMANDS -> END");
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  } catch (IndexOutOfBoundsException var14) {
                     DiplomacyManager.acceptPeaceTreaty(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).TAG);
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                     CFG.exceptionStack(var14);
                  }
                  break;
               case WE_CAN_SIGN_PEACE:
               case WE_CAN_SIGN_PEACE_STATUS_QUO:
                  Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> " + CFG.game.getCiv(nCivID).getCivName() + " -> ");
                  int nWarID = CFG.game.getWarID(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID);
                  boolean playerTakesPartInPeaceTreaty = false;
                  if (nWarID >= 0) {
                     if (CFG.game
                        .getPeaceTreaty_GameData_AlreadySent(
                           nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                        )) {
                        break;
                     }

                     List<Boolean> lDefenders = new ArrayList<>();
                     List<Boolean> lAggressors = new ArrayList<>();
                     if (CFG.game.getWar(nWarID).getIsAggressor(nCivID)) {
                        for(int o = 0; o < CFG.game.getWar(nWarID).getAggressorsSize(); ++o) {
                           lAggressors.add(true);
                           if (CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o).getCivID()).getControlledByPlayer()) {
                              boolean playerOccupiedProvincesInThisPeace = false;

                              for(int z = 0; z < CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o).getCivID()).getNumOfProvinces(); ++z) {
                                 if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o).getCivID()).getProvinceID(z)).isOccupied()
                                    )
                                  {
                                    for(int p = 0; p < CFG.game.getWar(nWarID).getDefendersSize(); ++p) {
                                       if (CFG.game.getWar(nWarID).getDefenderID(p).getCivID()
                                          == CFG.game
                                             .getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o).getCivID()).getProvinceID(z))
                                             .getTrueOwnerOfProvince()) {
                                          playerOccupiedProvincesInThisPeace = true;
                                          z = CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o).getCivID()).getNumOfProvinces();
                                          break;
                                       }
                                    }
                                 }
                              }

                              if (playerOccupiedProvincesInThisPeace) {
                                 playerTakesPartInPeaceTreaty = true;
                              }
                           }
                        }

                        for(int o = 0; o < CFG.game.getWar(nWarID).getDefendersSize(); ++o) {
                           lDefenders.add(
                              CFG.game.getWar(nWarID).getDefenderID(o).getCivID()
                                    == CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                 || CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o).getCivID()).getNumOfProvinces() == 0
                           );
                        }
                     } else {
                        for(int o = 0; o < CFG.game.getWar(nWarID).getAggressorsSize(); ++o) {
                           lAggressors.add(
                              CFG.game.getWar(nWarID).getAggressorID(o).getCivID()
                                    == CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                 || CFG.game.getCiv(CFG.game.getWar(nWarID).getAggressorID(o).getCivID()).getNumOfProvinces() == 0
                           );
                        }

                        for(int o = 0; o < CFG.game.getWar(nWarID).getDefendersSize(); ++o) {
                           lDefenders.add(true);
                           if (CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o).getCivID()).getControlledByPlayer()) {
                              playerTakesPartInPeaceTreaty = true;
                              boolean playerOccupiedProvincesInThisPeace = false;

                              for(int z = 0; z < CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o).getCivID()).getNumOfProvinces(); ++z) {
                                 if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o).getCivID()).getProvinceID(z)).isOccupied()) {
                                    for(int p = 0; p < CFG.game.getWar(nWarID).getAggressorsSize(); ++p) {
                                       if (CFG.game.getWar(nWarID).getAggressorID(p).getCivID()
                                          == CFG.game
                                             .getProvince(CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o).getCivID()).getProvinceID(z))
                                             .getTrueOwnerOfProvince()) {
                                          playerOccupiedProvincesInThisPeace = true;
                                          z = CFG.game.getCiv(CFG.game.getWar(nWarID).getDefenderID(o).getCivID()).getNumOfProvinces();
                                          break;
                                       }
                                    }
                                 }
                              }

                              if (playerOccupiedProvincesInThisPeace) {
                                 playerTakesPartInPeaceTreaty = true;
                              }
                           }
                        }
                     }

                     if (playerTakesPartInPeaceTreaty) {
                        Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> playerTakesPartInPeaceTreaty: " + playerTakesPartInPeaceTreaty);
                        break;
                     }

                     Menu_PeaceTreaty.WAR_ID = nWarID;
                     CFG.peaceTreatyData = new PeaceTreaty_Data(
                        Menu_PeaceTreaty.WAR_ID, lDefenders, lAggressors, CFG.game.getWar(nWarID).getIsAggressor(nCivID)
                     );
                     Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> 000");
                     CFG.peaceTreatyData.AI_UseVictoryPoints();
                     Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> 111");
                     DiplomacyManager.sendPeaceTreaty(
                        CFG.game.getWar(CFG.peaceTreatyData.peaceTreatyGameData.iWarID).getIsAggressor(nCivID),
                        nCivID,
                        CFG.peaceTreatyData.peaceTreatyGameData
                     );
                  }

                  Gdx.app.log("AoC", "respondToMessages -> WE_CAN_SIGN_PEACE -> END");
                  break;
               case TECHNOLOGY_POINTS:
                  this.useTechnologyPoints(nCivID);
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case JOIN_ALLIANCE:
                  if (CFG.game
                     .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     .getCivilization_Diplomacy_GameData()
                     .isEmassyClosed(nCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (DiplomacyManager.getAllianceProposal_Positive(
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID
                           )
                           + DiplomacyManager.getAllianceProposal_Negative(
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID
                           )
                        <= 0
                     && (
                        !(
                              CFG.game
                                    .getCivRelation_OfCivB(
                                       nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                    )
                                 > 0.0F
                           )
                           || CFG.oR.nextInt(1000) <= 985
                     )) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case NONAGGRESSIONPACT:
                  if (CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game
                        .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     > -35.0F) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case PREPARE_FOR_WAR:
                  if (CFG.game
                     .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     .getCivilization_Diplomacy_GameData()
                     .isEmassyClosed(nCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case DEFENSIVEPACT:
                  if (CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game
                        .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     > 10.0F) {
                     boolean sameRivals = false;

                     for(int a = 0; a < CFG.game.getCiv(nCivID).getHatedCivsSize(); ++a) {
                        for(int b = 0;
                           b
                              < CFG.game
                                 .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                 .getHatedCivsSize();
                           ++b
                        ) {
                           if (CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .isHatedCiv(CFG.game.getCiv(nCivID).getHatedCiv(a).iCivID)) {
                              sameRivals = true;
                              break;
                           }
                        }

                        if (sameRivals) {
                           break;
                        }
                     }

                     if (sameRivals) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case DEFENSIVEPACT_EXPIRED:
                  if (!CFG.game.getCiv(nCivID).isAtWar()
                     && !CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).isAtWar()
                     && CFG.game
                           .getCiv(nCivID)
                           .isFriendlyCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                        > 0
                     && CFG.oR.nextInt(100) < 9) {
                     DiplomacyManager.sendDefensivePactProposal(
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID, 40
                     );
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case NONAGGRESSIONPACT_EXPIRED:
                  if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     && CFG.game
                           .getCiv(nCivID)
                           .isFriendlyCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                        > 0
                     && CFG.oR.nextInt(100) < 9) {
                     DiplomacyManager.sendNonAggressionProposal(
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID, 40
                     );
                  }
                  break;
               case GIFT:
                  float nPercOfBudget = (float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iValue
                     / (float)CFG.game.getCiv(nCivID).iBudget;
                  if (CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else if (!CFG.game
                        .getCiv(nCivID)
                        .isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     && !CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).isHatedCiv(nCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     if (nPercOfBudget > 0.21F + (float)CFG.oR.nextInt(100) / 1000.0F) {
                        CFG.game
                           .getCiv(nCivID)
                           .getCivilization_Diplomacy_GameData()
                           .addImproveRelations(
                              nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, 3 + CFG.oR.nextInt(6)
                           );
                     }

                     if ((
                           CFG.game
                                    .getCiv(nCivID)
                                    .isFriendlyCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                 >= 0
                              || CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .isFriendlyCiv(nCivID)
                                 >= 0
                        )
                        && CFG.game.getCiv(nCivID).getNumOfProvinces() > 3
                        && CFG.game.getCiv(nCivID).getNumOfProvinces()
                           > CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .getNumOfProvinces()
                        && CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .getNumOfProvinces()
                           < 5) {
                        boolean alreadyGuaratneed = false;

                        for(int z = 1; z < CFG.game.getCivsSize(); ++z) {
                           if (CFG.game.getGuarantee(z, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID) > 0) {
                              alreadyGuaratneed = true;
                              break;
                           }
                        }

                        if (!alreadyGuaratneed) {
                           DiplomacyManager.sendGuaranteeIndependence_AskProposal(
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID, 40
                           );
                        }
                     }
                  } else if (nPercOfBudget > 0.785F + (float)CFG.oR.nextInt(100) / 1000.0F) {
                     CFG.game
                        .getCiv(nCivID)
                        .getCivilization_Diplomacy_GameData()
                        .addImproveRelations(
                           nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, 5 + CFG.oR.nextInt(10)
                        );
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else if (nPercOfBudget < 0.32F + (float)CFG.oR.nextInt(85) / 1000.0F) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case GUARANTEE_ASK:
                  if (CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game
                     .getCiv(nCivID)
                     .isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if ((float)CFG.game.getCiv(nCivID).iBudget
                     > (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget
                        * 0.785F) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case MILITARY_ACCESS_ASK:
                  if (CFG.game
                     .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     .getCivilization_Diplomacy_GameData()
                     .isEmassyClosed(nCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else {
                     float nScore = -7.785F;
                     if (!CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civPlans
                        .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        float minDistance = 1.0F;

                        for(int k = 0; k < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++k) {
                           for(int j = 0;
                              j
                                 < CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .getNumOfProvinces();
                              ++j
                           ) {
                              minDistance = Math.min(
                                 minDistance,
                                 CFG.game_NextTurnUpdate
                                    .getDistanceFromAToB_PercOfMax(
                                       CFG.game.getCiv(nCivID).getProvinceID(k),
                                       CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .getProvinceID(j)
                                    )
                              );
                           }
                        }

                        nScore -= CFG.game.getCiv(nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DISTANCE_SCORE * minDistance;
                        nScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RELATION_SCORE
                           * CFG.game
                              .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                           / 100.0F;
                        nScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_SCORE
                           * (float)(
                              CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).getRankScore()
                                 / CFG.game.getCivsSize()
                           );
                        nScore -= CFG.game.getCiv(nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_RANK_OWN_SCORE
                           * (float)(CFG.game.getCiv(nCivID).getRankScore() / CFG.game.getCivsSize());
                        if (CFG.game
                              .getMilitaryAccess(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                           > 0) {
                           nScore += 3.75F;
                        }

                        if (CFG.game
                              .getMilitaryAccess(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID)
                           > 0) {
                           nScore += 4.25F;
                        }

                        if (CFG.game.getGuarantee(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID)
                           > 0) {
                           nScore += 12.75F;
                        }

                        if (CFG.game.getCiv(nCivID).getIsPartOfHolyRomanEmpire()
                           || CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .getIsPartOfHolyRomanEmpire()) {
                           if (CFG.game.getCiv(nCivID).getIsPartOfHolyRomanEmpire()
                              && CFG.game
                                 .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                 .getIsPartOfHolyRomanEmpire()) {
                              if (CFG.game
                                    .getCivRelation_OfCivB(
                                       nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                    )
                                 > -15.0F) {
                                 nScore += 12.85F;
                                 if (CFG.holyRomanEmpire_Manager
                                    .getHRE()
                                    .getIsEmperor(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                                    nScore += 250.0F;
                                 }
                              }
                           } else if (!CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .getIsPartOfHolyRomanEmpire()) {
                              nScore -= 4.25F;
                           }
                        }

                        if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
                           for(int j = 0; j < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++j) {
                              if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(j) != nCivID
                                 && CFG.game
                                       .getCivRelation_OfCivB(
                                          CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(j),
                                          CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                       )
                                    < 0.0F) {
                                 nScore -= 7.475F
                                    * CFG.game
                                       .getCivRelation_OfCivB(
                                          CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(j),
                                          CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                       )
                                    / 100.0F;
                              }

                              if (CFG.game
                                 .getCivsAtWar(
                                    CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(j),
                                    CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                                 )) {
                                 nScore = -1500.0F;
                              }
                           }
                        }

                        if (CFG.game.getCiv(nCivID).isAtWar()) {
                           for(int j = 0; j < CFG.game.getWarsSize(); ++j) {
                              if (CFG.game.getWar(j).getIsDefender(nCivID)
                                 && CFG.game
                                    .getWar(j)
                                    .getIsDefender(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                                 nScore += 1000.0F;
                                 break;
                              }

                              if (CFG.game.getWar(j).getIsAggressor(nCivID)
                                 && CFG.game
                                    .getWar(j)
                                    .getIsAggressor(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                                 nScore += 1000.0F;
                                 break;
                              }
                           }
                        }

                        try {
                           if (CFG.game
                                 .getCiv(nCivID)
                                 .getDefensivePact(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              > 0) {
                              nScore += CFG.game.getCiv(nCivID).civGameData.civPersonality.RESPONSE_MILITARY_ACCESS_DEFENSIVE_PACT_SCORE;
                           }
                        } catch (IndexOutOfBoundsException var11) {
                        }

                        if (nScore > 0.0F) {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                     }
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case MILITARY_ACCESS_ASK_DENIED:
                  if (CFG.game.getMilitaryAccess(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                        <= 0
                     && !CFG.game
                        .getCiv(nCivID)
                        .messageWasSent(
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, Message_Type.TRADE_REQUEST
                        )) {
                     TradeRequest_GameData tradeRequest = new TradeRequest_GameData();
                     tradeRequest.iCivLEFT = nCivID;
                     tradeRequest.iCivRIGHT = CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID;
                     tradeRequest.listRight.militaryAccess = true;
                     tradeRequest.listLEFT.iGold = (int)(
                        Math.min(
                                 (float)CFG.game.getCiv(nCivID).iBudget,
                                 (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget
                                    * 1.375F
                              )
                              * 0.425F
                           + (float)CFG.oR.nextInt(325) / 1000.0F
                     );
                     DiplomacyManager.sendTradeRequest(
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID, tradeRequest
                     );
                  }
                  break;
               case MILITARY_ACCESS_GIVE:
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case WAR_DECLARED_ON_ALLY:
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case TRADE_REQUEST:
                  if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.iFormCoalitionAgainst > 0
                     || CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
                     if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.lProvinces.size()
                        > 0) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if (CFG.game.getCiv(nCivID).isAtWar()) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if (!CFG.game
                           .getCiv(nCivID)
                           .isHatedCiv(
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iFormCoalitionAgainst
                           )
                        && !CFG.game
                           .getCiv(nCivID)
                           .isHatedCiv(
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.iFormCoalitionAgainst
                           )) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if (CFG.game
                           .getCiv(nCivID)
                           .isFriendlyCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                        >= 0) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else if (CFG.oR.nextInt(100) < 10) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.militaryAccess
                     && !CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.proclaimIndependence
                     && !CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.nonAggressionPact
                     && !CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.defensivePact
                     && CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.iGold <= 0
                     && CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.lProvinces.size() == 0) {
                     boolean warAgainstFriendlyCiv = false;

                     for(int z = 0;
                        z
                           < CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .isAtWarWithCivs
                              .size();
                        ++z
                     ) {
                        if (CFG.game
                              .getCiv(nCivID)
                              .isFriendlyCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .isAtWarWithCivs
                                    .get(z)
                              )
                           >= 0) {
                           warAgainstFriendlyCiv = true;
                           break;
                        }
                     }

                     if (warAgainstFriendlyCiv) {
                        if (CFG.game
                           .getCiv(nCivID)
                           .isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                           if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold
                              > (float)CFG.game.getCiv(nCivID).iBudget * 8.35F) {
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                           } else {
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                           }
                        }
                     } else if (CFG.game
                        .getCiv(nCivID)
                        .isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold
                           > (float)CFG.game.getCiv(nCivID).iBudget * 2.35F) {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                     } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold > 0) {
                        if (CFG.game.getCiv(nCivID).iBudget > 0) {
                           if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold
                              > (float)CFG.game.getCiv(nCivID).iBudget * 0.175F) {
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                           } else {
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                           }
                        } else {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        }
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.lProvinces.size()
                     > 0) {
                     if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.lProvinces.size()
                           / (float)CFG.game.getCiv(nCivID).getNumOfProvinces()
                        > 0.25F) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else {
                        boolean haveACore = false;

                        for(int z = 0;
                           z < CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.lProvinces.size();
                           ++z
                        ) {
                           if (CFG.game
                              .getProvince(
                                 CFG.game
                                    .getCiv(nCivID)
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessage(i)
                                    .tradeRequest
                                    .listRight
                                    .lProvinces
                                    .get(z)
                              )
                              .getCore()
                              .getHaveACore(nCivID)) {
                              haveACore = true;
                              break;
                           }
                        }

                        if (!haveACore) {
                           int totalCost = 0;

                           for(int z = 0;
                              z
                                 < CFG.game
                                    .getCiv(nCivID)
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessage(i)
                                    .tradeRequest
                                    .listRight
                                    .lProvinces
                                    .size();
                              ++z
                           ) {
                              totalCost = (int)(
                                 (float)totalCost
                                    + CFG.game
                                       .getProvince(
                                          CFG.game
                                             .getCiv(nCivID)
                                             .getCivilization_Diplomacy_GameData()
                                             .messageBox
                                             .getMessage(i)
                                             .tradeRequest
                                             .listRight
                                             .lProvinces
                                             .get(z)
                                       )
                                       .iIncome_Taxation
                                    + CFG.game
                                          .getProvince(
                                             CFG.game
                                                .getCiv(nCivID)
                                                .getCivilization_Diplomacy_GameData()
                                                .messageBox
                                                .getMessage(i)
                                                .tradeRequest
                                                .listRight
                                                .lProvinces
                                                .get(z)
                                          )
                                          .iIncome_Production
                                       * 2.0F
                              );
                           }

                           totalCost *= 225 + CFG.oR.nextInt(45);
                           if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold > totalCost) {
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                           } else {
                              CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                           }
                        } else {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.defensivePact
                     || CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.defensivePact) {
                     if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if ((float)CFG.oR.nextInt(100)
                        < CFG.game
                           .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.proclaimIndependence
                     || CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.proclaimIndependence) {
                     if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if ((float)CFG.oR.nextInt(100)
                        < CFG.game
                           .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.nonAggressionPact
                     || CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.nonAggressionPact) {
                     if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if ((float)CFG.oR.nextInt(100)
                        < CFG.game
                           .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.militaryAccess
                     || CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.militaryAccess) {
                     if (CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     } else if ((float)CFG.oR.nextInt(100)
                        < CFG.game
                           .getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.iGold <= 0
                     && CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold <= 0) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listRight.iGold
                     > CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).tradeRequest.listLEFT.iGold) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case ULTIMATUM:
                  if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.demandAnexation) {
                     if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.numOfUntis
                        < CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).iBudget / 5, 0)) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        DiplomacyManager.declarationOfIndependeceByVassal(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID);
                     } else if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.numOfUntis * 0.42F
                        > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).iBudget / 5, 0))) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.demandProvinces.size() > 0) {
                     if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.numOfUntis * 0.0465F
                        > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).iBudget / 5, 0))) {
                        DiplomacyManager.decreaseRelation(
                           nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, 50
                        );
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        int totalBudget = CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget;
                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).getAllianceID()
                           > 0) {
                           for(int a = 0;
                              a
                                 < CFG.game
                                    .getAlliance(
                                       CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .getAllianceID()
                                    )
                                    .getCivilizationsSize();
                              ++a
                           ) {
                              if (CFG.game
                                    .getAlliance(
                                       CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .getAllianceID()
                                    )
                                    .getCivilization(a)
                                 != CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID) {
                                 totalBudget += CFG.game
                                    .getCiv(
                                       CFG.game
                                          .getAlliance(
                                             CFG.game
                                                .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                                .getAllianceID()
                                          )
                                          .getCivilization(a)
                                    )
                                    .iBudget;
                              }
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).civGameData.iVassalsSize
                           > 0) {
                           for(int a = 0;
                              a
                                 < CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .civGameData
                                    .iVassalsSize;
                              ++a
                           ) {
                              totalBudget += CFG.game
                                 .getCiv(
                                    CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .civGameData
                                          .lVassals
                                          .get(a)
                                       .iCivID
                                 )
                                 .iBudget;
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).getCivID()
                           != CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .getPuppetOfCivID()) {
                           totalBudget += CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .getPuppetOfCivID()
                              )
                              .iBudget;
                        }

                        if ((float)CFG.game.getCiv(nCivID).iBudget * 0.9165F > (float)totalBudget) {
                           CFG.game
                              .declareWar(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, false);
                        }
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.demandMilitaryAccess) {
                     if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.numOfUntis * 0.875F
                        > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).iBudget / 5, 0))) {
                        DiplomacyManager.decreaseRelation(
                           nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, 15
                        );
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.demandVasalization) {
                     if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.numOfUntis * 0.22F
                        > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).iBudget / 5, 0))) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        int totalBudget = CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget;
                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).getAllianceID()
                           > 0) {
                           for(int a = 0;
                              a
                                 < CFG.game
                                    .getAlliance(
                                       CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .getAllianceID()
                                    )
                                    .getCivilizationsSize();
                              ++a
                           ) {
                              if (CFG.game
                                    .getAlliance(
                                       CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .getAllianceID()
                                    )
                                    .getCivilization(a)
                                 != CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID) {
                                 totalBudget += CFG.game
                                    .getCiv(
                                       CFG.game
                                          .getAlliance(
                                             CFG.game
                                                .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                                .getAllianceID()
                                          )
                                          .getCivilization(a)
                                    )
                                    .iBudget;
                              }
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).civGameData.iVassalsSize
                           > 0) {
                           for(int a = 0;
                              a
                                 < CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .civGameData
                                    .iVassalsSize;
                              ++a
                           ) {
                              totalBudget += CFG.game
                                 .getCiv(
                                    CFG.game
                                          .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                          .civGameData
                                          .lVassals
                                          .get(a)
                                       .iCivID
                                 )
                                 .iBudget;
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).getCivID()
                           != CFG.game
                              .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                              .getPuppetOfCivID()) {
                           totalBudget += CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .getPuppetOfCivID()
                              )
                              .iBudget;
                        }

                        if ((float)CFG.game.getCiv(nCivID).iBudget * 0.865F > (float)totalBudget) {
                           CFG.game
                              .declareWar(nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, false);
                        }
                     }
                  } else if (CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.demandLiberation.size() > 0) {
                     if ((float)CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).ultimatum.numOfUntis * 0.36F
                        > (float)(CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).iBudget / 5, 0))) {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case UNION:
                  if (CFG.game
                     .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     .getCivilization_Diplomacy_GameData()
                     .isEmassyClosed(nCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .isPreparingForTheWar(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game.getCiv(nCivID).iLeague < 2
                     && CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iLeague < 2) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game.isAlly(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID, nCivID)) {
                     if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).civGameData.numOfUnions
                           == 0
                        && CFG.game.getCiv(nCivID).civGameData.numOfUnions == 0) {
                        boolean sameRivals = false;

                        for(int a = 0; a < CFG.game.getCiv(nCivID).getHatedCivsSize(); ++a) {
                           for(int b = 0;
                              b
                                 < CFG.game
                                    .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                    .getHatedCivsSize();
                              ++b
                           ) {
                              if (CFG.game
                                 .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                                 .isHatedCiv(CFG.game.getCiv(nCivID).getHatedCiv(a).iCivID)) {
                                 sameRivals = true;
                                 break;
                              }
                           }

                           if (sameRivals) {
                              break;
                           }
                        }

                        if (sameRivals) {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                     } else if (CFG.game.getCiv(nCivID).getNumOfProvinces() < 3) {
                        if (CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).civGameData.numOfUnions
                              < 3
                           && CFG.game.getCiv(nCivID).civGameData.numOfUnions < 3) {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                        } else {
                           CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                        }
                     } else {
                        CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                     }
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case OFFERVASALIZATION:
                  if (CFG.game
                     .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                     .getCivilization_Diplomacy_GameData()
                     .isEmassyClosed(nCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game
                        .getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)
                        .getPuppetOfCivID()
                     == nCivID) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  } else if (CFG.game.getCiv(nCivID).iBudget
                        > CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget * 15
                     && !CFG.game.getCiv(nCivID).isHatedCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID)) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case TRANSFER_CONTROL:
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case HIGH_INFLATION:
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case DECLARATION_OF_INDEPENDENCE:
                  if ((float)CFG.game.getCiv(nCivID).iBudget
                     > (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget
                        * 0.25F) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case WAR:
                  if (CFG.ideologiesManager
                     .getIdeology(
                        CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).getIdeologyID()
                     )
                     .REVOLUTIONARY) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                     break;
                  }

                  List<Integer> callToArms = DiplomacyManager.callToArmsListOfCivs(
                     nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                  );

                  for(int j = 0; j < callToArms.size(); ++j) {
                     DiplomacyManager.sendCallToArms(
                        callToArms.get(j), nCivID, CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID
                     );
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case RELATIONS_INSULT:
                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
                  break;
               case DECLARATION_OF_INDEPENDENCE_BYVASSAl:
                  if ((float)CFG.game.getCiv(nCivID).iBudget
                     > (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iFromCivID).iBudget
                        * 0.25F) {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAccept(nCivID);
                  } else {
                     CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onDecline(nCivID);
                  }

                  CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
            }
         }
      } catch (IndexOutOfBoundsException var15) {
         CFG.exceptionStack(var15);
      } catch (NullPointerException var16) {
         CFG.exceptionStack(var16);
      }
   }

   protected final void updateSentMessages(int nCivID) {
      try {
         int i = CFG.game.getCiv(nCivID).getSentMessagesSize() - 1;

         while(i >= 0) {
            switch(CFG.game.getCiv(nCivID).getSentMessage(i).messageType) {
               default:
                  if (Game_Calendar.TURN_ID - CFG.game.getCiv(nCivID).getSentMessage(i).iSentInTurnID
                     > CFG.game.getCiv(nCivID).getCivPersonality().SENT_MESSAGES_DEFAULT_TURNS) {
                     CFG.game.getCiv(nCivID).removeSentMessage(i);
                  }
               case GIFT:
                  --i;
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      } catch (NullPointerException var4) {
         CFG.exceptionStack(var4);
      }
   }

   protected final int getPrepareForWar_TurnsLeft(int nCivID, int onCivID) {
      for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(j).onCivID == onCivID) {
            return CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(j).iNumOfTurnsLeft;
         }
      }

      return -1;
   }

   protected final int getPrepareForWar_TurnsLeft_BasedOnNeighboors(int nCivID, int nProvinceID) {
      int out = 8;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() > 0
            && CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) {
            for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
               if (CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(j).onCivID
                  == CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID()) {
                  out = Math.max(CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(j).iNumOfTurnsLeft, out);
               }
            }
         }
      }

      return out;
   }

   protected final void prepareForWar_MoveReadyArmies(int nCivID) {
      for(int i = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; i >= 0; --i) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).MISSION_TYPE == CivArmyMission_Type.PREAPARE_FOR_WAR) {
            int tempTurnsLeft;
            if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).MISSION_ID < 0) {
               tempTurnsLeft = 0;
            } else {
               tempTurnsLeft = this.getPrepareForWar_TurnsLeft(nCivID, CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).MISSION_ID);
            }

            if (tempTurnsLeft < 0) {
               CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(i);
            } else if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).canMakeAction(nCivID, tempTurnsLeft)
               && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).action(nCivID)) {
               CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(i);
            }
         }
      }
   }

   protected final void prepareForWar2(int nCivID) {
      Gdx.app.log("AoC", "moveAtWar -> " + CFG.game.getCiv(nCivID).getCivName());

      try {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize == 0) {
            return;
         }

         boolean haveOwnFront = false;
         List<AI_ProvinceInfo_War> tempFrontProvinces = new ArrayList<>();
         new ArrayList();

         for(int i = CFG.oAI.lFrontLines.get(nCivID - 1).size() - 1; i >= 0; --i) {
            for(int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
               if (CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
                  haveOwnFront = true;

                  for(int k = CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.size() - 1; k >= 0; --k) {
                     boolean add = true;

                     for(int o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                        if (tempFrontProvinces.get(o).iProvinceID == CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(k)) {
                           add = false;
                           break;
                        }
                     }

                     if (add) {
                        tempFrontProvinces.add(
                           new AI_ProvinceInfo_War(
                              CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(k),
                              this.getPotential_BasedOnNeighboringProvs(CFG.oAI.lFrontLines.get(nCivID - 1).get(i).lProvinces.get(k), nCivID),
                              true
                           )
                        );
                     }
                  }
               }
            }
         }

         for(int o = 0; o < CFG.game.getCiv(nCivID).civGameData.iVassalsSize; ++o) {
            for(int i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).size() - 1; i >= 0; --i) {
               for(int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                  if (CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).iWithCivID
                     == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
                     for(int k = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.size() - 1;
                        k >= 0;
                        --k
                     ) {
                        boolean add = true;

                        for(int z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                           if (tempFrontProvinces.get(z).iProvinceID
                              == CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.get(k)) {
                              add = false;
                              break;
                           }
                        }

                        if (add) {
                           tempFrontProvinces.add(
                              new AI_ProvinceInfo_War(
                                 CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.get(k),
                                 this.getPotential_BasedOnNeighboringProvs(
                                    CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID - 1).get(i).lProvinces.get(k),
                                    CFG.game.getCiv(nCivID).civGameData.lVassals.get(o).iCivID
                                 ),
                                 false
                              )
                           );
                        }
                     }
                  }
               }
            }
         }

         if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
            for(int i = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).size() - 1; i >= 0; --i) {
               for(int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                  if (CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).iWithCivID
                     == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
                     for(int k = CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.size() - 1; k >= 0; --k) {
                        boolean add = true;

                        for(int o = tempFrontProvinces.size() - 1; o >= 0; --o) {
                           if (tempFrontProvinces.get(o).iProvinceID
                              == CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.get(k)) {
                              add = false;
                              break;
                           }
                        }

                        if (add) {
                           tempFrontProvinces.add(
                              new AI_ProvinceInfo_War(
                                 CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.get(k),
                                 this.getPotential_BasedOnNeighboringProvs(
                                    CFG.oAI.lFrontLines.get(CFG.game.getCiv(nCivID).getPuppetOfCivID() - 1).get(i).lProvinces.get(k),
                                    CFG.game.getCiv(nCivID).getPuppetOfCivID()
                                 ),
                                 false
                              )
                           );
                        }
                     }
                  }
               }
            }
         }

         if (CFG.game.getCiv(nCivID).getAllianceID() > 0) {
            for(int o = 0; o < CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize(); ++o) {
               if (CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) != nCivID) {
                  for(int i = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).size() - 1;
                     i >= 0;
                     --i
                  ) {
                     for(int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++u) {
                        if (CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i).iWithCivID
                           == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
                           for(int k = CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                    .lProvinces
                                    .size()
                                 - 1;
                              k >= 0;
                              --k
                           ) {
                              boolean add = true;

                              for(int z = tempFrontProvinces.size() - 1; z >= 0; --z) {
                                 if (tempFrontProvinces.get(z).iProvinceID
                                    == CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                       .lProvinces
                                       .get(k)) {
                                    add = false;
                                    break;
                                 }
                              }

                              if (add) {
                                 tempFrontProvinces.add(
                                    new AI_ProvinceInfo_War(
                                       CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                          .lProvinces
                                          .get(k),
                                       this.getPotential_BasedOnNeighboringProvs(
                                          CFG.oAI.lFrontLines.get(CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o) - 1).get(i)
                                             .lProvinces
                                             .get(k),
                                          CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilization(o)
                                       ),
                                       false
                                    )
                                 );
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         Gdx.app.log("AoC", "prepareForWar2 -> tempFrontProvinces.size: " + tempFrontProvinces.size());
         if (tempFrontProvinces.size() > 0) {
            int tMaxDL = 1;
            float tMaxPotential = 1.0F;
            List<Integer> tMovingArmy_toFrontProvince = new ArrayList<>();
            int tMaxArmy = 1;
            float tMaxRegion_NumOfProvinces = 1.0F;
            float tMaxRegion_Potential = 1.0F;
            List<Integer> lFrontIDsWithArmies = new ArrayList<>();
            int i = tempFrontProvinces.size() - 1;

            for(int tempMovingArmy = 0; i >= 0; --i) {
               if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                  tMaxPotential = tempFrontProvinces.get(i).iValue;
               }

               if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy() > tMaxDL) {
                  tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel_WithArmy();
               }

               if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces() > tMaxRegion_NumOfProvinces) {
                  tMaxRegion_NumOfProvinces = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getRegion_NumOfProvinces();
               }

               if ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion() > tMaxRegion_Potential) {
                  tMaxRegion_Potential = (float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getPotentialRegion();
               }

               tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
               tMovingArmy_toFrontProvince.add(tempMovingArmy);
               if (CFG.game.getProvinceArmy(tempFrontProvinces.get(i).iProvinceID) + tempMovingArmy > tMaxArmy) {
                  tMaxArmy = CFG.game.getProvinceArmy(tempFrontProvinces.get(i).iProvinceID) + tempMovingArmy;
               }
            }

            for(int ix = tempFrontProvinces.size() - 1; ix >= 0; --ix) {
               tempFrontProvinces.get(ix).iValue = CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_POTENTIAL
                     * (tempFrontProvinces.get(ix).iValue / tMaxPotential)
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_DANGER
                     * ((float)CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getDangerLevel_WithArmy() / (float)tMaxDL)
                  + 1.0F
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_NUM_OF_UNITS
                     * (
                        1.0F
                           - (float)(CFG.game.getProvinceArmy(tempFrontProvinces.get(ix).iProvinceID) + tMovingArmy_toFrontProvince.get(ix)) / (float)tMaxArmy
                     )
                  + 1.0F
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_NUM_OF_PROVINCES
                     * (float)CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getRegion_NumOfProvinces()
                     / tMaxRegion_NumOfProvinces
                  - CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL
                  + CFG.game.getCiv(nCivID).civGameData.civPersonality.WAR_REGION_POTENTIAL
                     * (float)CFG.game.getProvince(tempFrontProvinces.get(ix).iProvinceID).getPotentialRegion()
                     / tMaxRegion_Potential;
            }

            List<AI_ProvinceInfo_War> sortedFrontProvinces = new ArrayList<>();

            for(int tID = 0; tempFrontProvinces.size() > 0; ++tID) {
               int tBest = 0;
               int ix = 1;

               for(int iSize = tempFrontProvinces.size(); ix < iSize; ++ix) {
                  if (tempFrontProvinces.get(tBest).iValue < tempFrontProvinces.get(ix).iValue) {
                     tBest = ix;
                  } else if (tempFrontProvinces.get(tBest).iValue == tempFrontProvinces.get(ix).iValue && CFG.oR.nextInt(100) < 50) {
                     tBest = ix;
                  }
               }

               if (CFG.game.getProvince(tempFrontProvinces.get(tBest).iProvinceID).getArmyCivID(nCivID) > 0) {
                  lFrontIDsWithArmies.add(tID);
               }

               sortedFrontProvinces.add(tempFrontProvinces.get(tBest));
               tempFrontProvinces.remove(tBest);
            }

            this.prepareForWar_Regroup(nCivID, sortedFrontProvinces, lFrontIDsWithArmies);
            Gdx.app.log("AoC", "prepareForWar2 -> BEFORE RECRUIT MP: " + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
            if (CFG.game.getCiv(nCivID).getMoney() > 5L && CFG.game.getCiv(nCivID).iBudget > 0) {
               boolean canRecruitAndMove = (float)lFrontIDsWithArmies.size()
                     * 1.75F
                     * (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
                  <= (float)(
                     CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
                  );
               if (!canRecruitAndMove
                  && !(
                     (float)(CFG.game.getCiv(nCivID).getMoney() / 5L)
                           * (
                              CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn < 0
                                 ? 0.16F + 0.03F * (float)CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn
                                 : (CFG.game.getCiv(nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn ? 0.6F : 0.75F)
                           )
                        > (float)CFG.game.getCiv(nCivID).getNumOfUnits()
                  )
                  && CFG.game.getCiv(nCivID).civGameData.moveAtWar_ProvincesLostAndConquered_LastTurn >= -3
                  && CFG.game.getCiv(nCivID).getNumOfProvinces() >= 3) {
                  Gdx.app.log("AoC", "prepareForWar2 -> BEFORE RECRUIT 000: false");
               } else {
                  Gdx.app.log("AoC", "prepareForWar2 -> BEFORE RECRUIT 000: true");
                  this.prepareForWar_Recruit(nCivID, sortedFrontProvinces, lFrontIDsWithArmies, false);
               }

               CFG.game.getCiv(nCivID).civGameData.moveAtWar_ArmyFullyRecruitedLastTurn = false;
            }
         }

         this.moveAtWar_AtSea(nCivID);
      } catch (IndexOutOfBoundsException var17) {
         Gdx.app.log("AoC", "prepareForWar2 -> ERRRORR");
         CFG.exceptionStack(var17);
      } catch (ArithmeticException var18) {
         Gdx.app.log("AoC", "prepareForWar2 -> ERRRORR");
         CFG.exceptionStack(var18);
      }

      Gdx.app.log("AoC", "prepareForWar2 -> END, movementPoints:" + (float)CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
   }

   protected final void prepareForWar(int nCivID, float fMovemnetPointsToUse) {
      if (CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize > 0) {
         List<Integer> tempFrontlinesIDs = new ArrayList<>();

         for(int i = 0; i < CFG.oAI.lFrontLines.get(nCivID - 1).size(); ++i) {
            for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
               if (CFG.oAI.lFrontLines.get(nCivID - 1).get(i).iWithCivID == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(j).onCivID) {
                  tempFrontlinesIDs.add(i);
                  break;
               }
            }
         }

         if (tempFrontlinesIDs.size() > 0) {
            List<AI_ProvinceInfo> tempFrontProvinces = new ArrayList<>();
            int tMaxDL = 1;
            float tMaxPotential = 1.0F;
            List<Integer> tempWithCivs = new ArrayList<>();

            for(int j = 0; j < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; ++j) {
               tempWithCivs.add(CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(j).onCivID);
            }

            for(int i = 0; i < tempFrontlinesIDs.size(); ++i) {
               int j = 0;

               for(int jSize = CFG.oAI.lFrontLines.get(nCivID - 1).get(tempFrontlinesIDs.get(i)).lProvinces.size(); j < jSize; ++j) {
                  boolean wasAdded = false;

                  for(int k = 0; k < tempFrontProvinces.size(); ++k) {
                     if (tempFrontProvinces.get(k).iProvinceID == CFG.oAI.lFrontLines.get(nCivID - 1).get(tempFrontlinesIDs.get(i)).lProvinces.get(j)) {
                        wasAdded = true;
                        break;
                     }
                  }

                  if (!wasAdded) {
                     tempFrontProvinces.add(
                        new AI_ProvinceInfo(
                           CFG.oAI.lFrontLines.get(nCivID - 1).get(tempFrontlinesIDs.get(i)).lProvinces.get(j),
                           this.getPotential_BasedOnNeighboringProvs(
                              CFG.oAI.lFrontLines.get(nCivID - 1).get(tempFrontlinesIDs.get(i)).lProvinces.get(j), nCivID, tempWithCivs
                           ),
                           CFG.gameAction.getRecruitableArmy(CFG.oAI.lFrontLines.get(nCivID - 1).get(tempFrontlinesIDs.get(i)).lProvinces.get(j))
                        )
                     );
                  }
               }
            }

            tempWithCivs.clear();
            tempWithCivs = null;
            if (tempFrontProvinces.size() > 0) {
               int tMaxArmy = 1;
               List<Integer> tMovingArmy = new ArrayList<>();
               int i = 0;
               int iSize = tempFrontProvinces.size();

               for(int tempMovingArmy = 0; i < iSize; ++i) {
                  if (tempFrontProvinces.get(i).iValue > tMaxPotential) {
                     tMaxPotential = tempFrontProvinces.get(i).iValue;
                  }

                  if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel() > tMaxDL) {
                     tMaxDL = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel();
                  }

                  tempMovingArmy += this.getMovingArmyToProvinceID(nCivID, tempFrontProvinces.get(i).iProvinceID);
                  tMovingArmy.add(tempMovingArmy);
                  if (CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy > tMaxArmy) {
                     tMaxArmy = CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tempMovingArmy;
                  }
               }

               i = 0;

               for(int iSizex = tempFrontProvinces.size(); i < iSizex; ++i) {
                  tempFrontProvinces.get(i).iValue = CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_POTENTIAL
                        * (tempFrontProvinces.get(i).iValue / tMaxPotential)
                     + CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_DANGER
                        * ((float)CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getDangerLevel() / (float)tMaxDL)
                        * (
                           1.0F
                              - CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS
                              + CFG.game.getCiv(nCivID).civGameData.civPersonality.VALUABLE_NUM_OF_UNITS
                                 * (
                                    1.0F
                                       - (float)(CFG.game.getProvince(tempFrontProvinces.get(i).iProvinceID).getArmy(0) + tMovingArmy.get(i))
                                          / (float)tMaxArmy
                                 )
                        );
               }

               List<AI_ProvinceInfo> sortedFrontProvinces = new ArrayList<>();

               while(tempFrontProvinces.size() > 0) {
                  iSize = 0;
                  int ix = 1;

                  for(int iSizex = tempFrontProvinces.size(); ix < iSizex; ++ix) {
                     if (tempFrontProvinces.get(iSize).iValue < tempFrontProvinces.get(ix).iValue) {
                        iSize = ix;
                     }
                  }

                  sortedFrontProvinces.add(tempFrontProvinces.get(iSize));
                  tempFrontProvinces.remove(iSize);
               }

               List<Integer> lArmiesToRegoup = new ArrayList<>();

               for(int ix = 0; ix < CFG.game.getCiv(nCivID).iArmiesPositionSize; ++ix) {
                  if (!CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission(CFG.game.getCiv(nCivID).lArmiesPosition.get(ix))) {
                     lArmiesToRegoup.add(CFG.game.getCiv(nCivID).lArmiesPosition.get(ix));
                  }
               }

               List<AI_NeighProvinces> ab = CFG.oAI
                  .getAllNeighboringProvincesInRange_Recruit(
                     sortedFrontProvinces.get(0).iProvinceID, nCivID, 3, true, false, new ArrayList<>(), new ArrayList<>()
                  );
               if (ab.size() > 0) {
                  int tempRand = CFG.oR.nextInt(ab.size());
                  CFG.game.getCiv(nCivID).recruitArmy_AI(ab.get(tempRand).iProvinceID, CFG.gameAction.getRecruitableArmy(ab.get(tempRand).iProvinceID));
                  int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(ab.get(tempRand).iProvinceID);
                  if (tempArmy > 0) {
                     CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civPlans
                        .lArmiesMissions
                        .add(
                           new CivArmyMission_RegroupAfterRecruitment(nCivID, ab.get(tempRand).iProvinceID, sortedFrontProvinces.get(0).iProvinceID, tempArmy)
                        );
                  }
               }
            }
         }
      }
   }

   protected final int getMovingArmyToProvinceID(int nCivID, int nProvinceID) {
      int out = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size(); ++i) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).toProvinceID == nProvinceID) {
            out += CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(i).iArmy;
         }
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getRegroupArmySize(); ++i) {
         if (CFG.game.getCiv(nCivID).getRegroupArmy(i).getToProvinceID() == nProvinceID) {
            out += CFG.game.getCiv(nCivID).getRegroupArmy(i).getNumOfUnits();
         }
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
         if (CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID() == nProvinceID) {
            out += CFG.game.getCiv(nCivID).getMoveUnits(i).getNumOfUnits();
         }
      }

      return out;
   }

   protected final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID) {
      int out = CFG.game.getProvince(nProvinceID).getPotential();
      int tSize = 1;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() != nCivID) {
            out += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getPotentialModified(nCivID);
            ++tSize;
         }
      }

      return out / tSize;
   }

   protected final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID, int withCivID) {
      int out = CFG.game.getProvince(nProvinceID).getPotential();
      int tSize = 1;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == withCivID) {
            out += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getPotentialModified(nCivID);
            ++tSize;
         }
      }

      return out / tSize;
   }

   protected final int getPotential_BasedOnNeighboringProvs(int nProvinceID, int nCivID, List<Integer> withCivID) {
      int out = CFG.game.getProvince(nProvinceID).getPotential();
      int tSize = 1;
      int jSize = withCivID.size();

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         for(int j = 0; j < jSize; ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == withCivID.get(j)) {
               out += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getPotentialModified(nCivID);
               ++tSize;
            }
         }
      }

      return out / tSize;
   }

   protected boolean canMove(int nCivID) {
      return CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
   }

   protected boolean canMoveAndRecruit(int nCivID) {
      return CFG.game.getCiv(nCivID).getMovePoints()
         >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE
            + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT;
   }

   protected boolean canMoveArmyToProvinceID(int nProvinceID, int nCivID) {
      return CFG.game.getProvince(nProvinceID).getCivID() == nCivID
         || CFG.game.getCivsAreAllied(nCivID, CFG.game.getProvince(nProvinceID).getCivID())
         || CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID
         || CFG.game.getCiv(nCivID).getPuppetOfCivID() == CFG.game.getProvince(nProvinceID).getCivID()
         || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(nProvinceID).getCivID()) > 0;
   }

   protected boolean alliesAtWar(int nCivID) {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (i != nCivID && CFG.game.isAlly(nCivID, i) && CFG.game.getCiv(i).isAtWar()) {
            return true;
         }
      }

      return false;
   }

   protected boolean canRecruit(int nCivID, int nProvinceID) {
      return CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
         && CFG.game.getCiv(nCivID).getMoney() >= (long)CFG.getCostOfRecruitArmyMoney(nProvinceID);
   }

   protected final int getRecruitableArmy(int nProvinceID, int nCivID) {
      return Math.min(
         CFG.gameAction.getRecruitableArmy(nProvinceID, nCivID), (int)(CFG.game.getCiv(nCivID).getMoney() / (long)CFG.getCostOfRecruitArmyMoney(nProvinceID))
      );
   }

   protected final boolean doHaveAVisionInProvince(int nProvinceID, int nCivID) {
      if (CFG.FOG_OF_WAR == 0) {
         return true;
      } else {
         if (CFG.game.getProvince(nProvinceID).getLevelOfFort() == 0) {
            for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getLevelOfWatchTower() > 0
                  && CFG.game.isAlly(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(), nCivID)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   protected final int getEnemyArmyInNeighbooringProvinces_ArmyOnlyAtWar(int nProvinceID, int nCivID) {
      int nOut = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() > 0) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); ++j) {
               if (CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j), nCivID)) {
                  nOut += CFG.game
                     .getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i))
                     .getArmyCivID(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j));
               }
            }
         }
      }

      return nOut;
   }

   protected final int getEnemyArmyInNeighbooringProvinces_Total(int nProvinceID, int nCivID) {
      int nOut = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() > 0) {
            for(int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); ++j) {
               if (CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j), nCivID)) {
                  nOut += CFG.game.getProvinceArmy(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i));
                  break;
               }
            }
         }
      }

      return nOut;
   }

   protected final int getEnemyArmyInNeighbooringSeaProvinces_Total(int nProvinceID, int nCivID) {
      int nOut = 0;

      for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
         for(int j = 1; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivsSize(); ++j) {
            if (CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getCivID(j), nCivID)) {
               nOut += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getArmy(j);
            }
         }
      }

      return nOut;
   }

   protected final boolean isUncivilzed(int nCivID) {
      return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0;
   }

   protected final boolean canCivlize(int nCivID) {
      return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL <= CFG.game.getCiv(nCivID).getTechnologyLevel();
   }

   protected final boolean civilize(int nCivID) {
      if (this.isUncivilzed(nCivID) && this.canCivlize(nCivID)) {
         if (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && this.tryToExpandBeforeCivilize(nCivID) && CFG.oR.nextInt(100) > 2) {
            return false;
         }

         if (DiplomacyManager.civilizeCiv(nCivID)) {
            return true;
         }
      }

      return false;
   }

   protected final boolean tryToExpandBeforeCivilize(int nCivID) {
      if (CFG.game.getCiv(nCivID).getBordersWithEnemy() > 0) {
         return false;
      } else {
         if (CFG.game.getCiv(nCivID).getMoney() + (long)CFG.game.getCiv(nCivID).iBudget > -1000L
            && CFG.game.getCiv(nCivID).getNumOfNeighboringNeutralProvinces() > 0) {
            for(int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; --k) {
               if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.EXPAND_NETURAL_PROVINCE) {
                  if (!CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
                     return true;
                  }

                  CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).onRemove();
                  CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
               }
            }

            if (CFG.game.getCiv(nCivID).getNumOfProvinces() < 6 + nCivID % 2) {
               int minArmy = -1;

               for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                  for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); ++j) {
                     if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() == 0) {
                        if (minArmy < 0) {
                           minArmy = CFG.game
                              .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j))
                              .getArmy(0);
                        } else {
                           minArmy = Math.min(
                              minArmy,
                              CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j)).getArmy(0)
                           );
                        }
                     }
                  }
               }

               if (minArmy < 0) {
                  return false;
               }

               minArmy = (int)((long)minArmy - ((long)CFG.game.getCiv(nCivID).getNumOfUnits() + Math.max(CFG.game.getCiv(nCivID).getMoney(), 0L) / 5L));
               if (minArmy <= 0) {
                  CFG.oAI.expandToNeutralProvinces_Out(nCivID, false);
                  return true;
               }

               int willTakeNumOfTurns = (int)Math.ceil((double)((float)minArmy / (float)(CFG.game.getCiv(nCivID).iBudget / 5)));
               if (willTakeNumOfTurns < 50) {
                  CFG.oAI.expandToNeutralProvinces_Out(nCivID, false);
                  return true;
               }
            }
         }

         return false;
      }
   }

   protected final void checkBalanceOfProvinces_Tribal(int nCivID) {
      try {
         List<Integer> lProvincesWithDeficit = new ArrayList<>();
         List<Integer> lProvincesWithDeficit_ALL = new ArrayList<>();
         int totalBalanceOnMinus = 0;
         int totalBalancePositive = 0;

         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()) {
               if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn() < 0) {
                  if (CFG.game.getCiv(nCivID).getProvinceID(i) != CFG.game.getCiv(nCivID).getCapitalProvinceID()) {
                     totalBalanceOnMinus += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn();
                     lProvincesWithDeficit_ALL.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                     if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus
                        >= this.MIN_TURNS_TO_ABANDON_USELESS_PROVINCE) {
                        lProvincesWithDeficit.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                     }
                  } else {
                     totalBalanceOnMinus += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn();
                  }
               } else {
                  totalBalancePositive += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn();
               }
            }
         }

         if (lProvincesWithDeficit_ALL.size() > 0 && (float)totalBalancePositive * 0.65F < (float)Math.abs(totalBalanceOnMinus)) {
            float fAverage = 0.0F;

            for(int i = lProvincesWithDeficit_ALL.size() - 1; i >= 0; --i) {
               fAverage += (float)CFG.game.getProvince(lProvincesWithDeficit_ALL.get(i)).getBalance_LastTurn();
            }

            fAverage /= (float)lProvincesWithDeficit_ALL.size();
            List<Integer> lProvincesToDoSomething = new ArrayList<>();

            for(int i = lProvincesWithDeficit_ALL.size() - 1; i >= 0; --i) {
               if (fAverage * 0.375F > (float)CFG.game.getProvince(lProvincesWithDeficit_ALL.get(i)).getBalance_LastTurn()) {
                  lProvincesToDoSomething.add(lProvincesWithDeficit_ALL.get(i));
               }
            }

            this.abandonOrReleaseAsVassalProvinces(nCivID, lProvincesToDoSomething, true);
         }
      } catch (IndexOutOfBoundsException var9) {
         CFG.exceptionStack(var9);
      }
   }

   protected final boolean abandonOrReleaseAsVassalProvinces(int nCivID, List<Integer> tProvinces, boolean canAbandon) {
      Gdx.app.log("AoC", "abandonOrReleaseAsVassalProvinces -> " + CFG.game.getCiv(nCivID).getCivName());
      List<AI_ReleaseVassal> lCivsToRelease = new ArrayList<>();

      for(int i = tProvinces.size() - 1; i >= 0; --i) {
         for(int j = 0; j < CFG.game.getProvince(tProvinces.get(i)).getCore().getCivsSize(); ++j) {
            if (CFG.game.getCiv(CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j)).getNumOfProvinces() == 0) {
               boolean addCiv = true;

               for(int k = lCivsToRelease.size() - 1; k >= 0; --k) {
                  if (lCivsToRelease.get(k).iCivID == CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j)) {
                     addCiv = false;
                     lCivsToRelease.get(k).addProvince(tProvinces.get(i));
                     break;
                  }
               }

               if (addCiv) {
                  lCivsToRelease.add(new AI_ReleaseVassal(CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j), tProvinces.get(i)));
               }
            }
         }
      }

      if (lCivsToRelease.size() > 0) {
         int nNewVassalID = this.abandonOrReleaseAsVassalProvinces_ReleaseVassal(lCivsToRelease, tProvinces, nCivID);
         if (nNewVassalID >= 0) {
            for(int i = tProvinces.size() - 1; i >= 0; --i) {
               if (CFG.game.getCiv(nNewVassalID).controlsProvince(tProvinces.get(i))) {
                  tProvinces.remove(i);
               }
            }

            return this.abandonOrReleaseAsVassalProvinces(nCivID, tProvinces, canAbandon);
         }
      }

      lCivsToRelease.clear();

      for(int i = tProvinces.size() - 1; i >= 0; --i) {
         for(int j = 0; j < CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID() > 0
               && CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID() != nCivID) {
               boolean addCiv = true;

               for(int k = lCivsToRelease.size() - 1; k >= 0; --k) {
                  if (lCivsToRelease.get(k).iCivID == CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID()) {
                     addCiv = false;
                     lCivsToRelease.get(k).addProvince(tProvinces.get(i));
                     break;
                  }
               }

               if (addCiv) {
                  lCivsToRelease.add(
                     new AI_ReleaseVassal(
                        CFG.game.getProvince(CFG.game.getProvince(tProvinces.get(i)).getNeighboringProvinces(j)).getCivID(), tProvinces.get(i)
                     )
                  );
               }
            }
         }

         for(int j = 0; j < CFG.game.getProvince(tProvinces.get(i)).getCore().getCivsSize(); ++j) {
            if (CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j) != nCivID) {
               boolean addCiv = true;

               for(int k = lCivsToRelease.size() - 1; k >= 0; --k) {
                  if (lCivsToRelease.get(k).iCivID == CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j)) {
                     addCiv = false;
                     if (!lCivsToRelease.get(k).haveProvince(tProvinces.get(i))) {
                        lCivsToRelease.get(k).addProvince(tProvinces.get(i));
                     }
                     break;
                  }
               }

               if (addCiv) {
                  lCivsToRelease.add(new AI_ReleaseVassal(CFG.game.getProvince(tProvinces.get(i)).getCore().getCivID(j), tProvinces.get(i)));
               }
            }
         }
      }

      List<AI_ReleaseVassal> lAllies = new ArrayList<>();

      for(int i = lCivsToRelease.size() - 1; i >= 0; --i) {
         if (CFG.game.isAlly(nCivID, lCivsToRelease.get(i).iCivID)) {
            lAllies.add(lCivsToRelease.get(i));
         }
      }

      for(int j = lAllies.size() - 1; j >= 0; --j) {
         for(int i = CFG.game.getCiv(nCivID).getSentMessagesSize() - 1; i >= 0; --i) {
            if (CFG.game.getCiv(nCivID).getSentMessage(i).messageType == Message_Type.TRADE_REQUEST_GIVE_PROVINCES
               && CFG.game.getCiv(nCivID).getSentMessage(i).iToCivID == lAllies.get(j).iCivID) {
               lAllies.remove(j);
            }
         }
      }

      while(lAllies.size() > 0) {
         int tBest = 0;

         for(int i = lAllies.size() - 1; i > 0; --i) {
            if (lAllies.get(tBest).lProvinces.size() < lAllies.get(i).lProvinces.size() || CFG.oR.nextInt(100) < 10) {
               tBest = i;
            }
         }

         TradeRequest_GameData nTD = new TradeRequest_GameData();

         for(int i = lAllies.get(tBest).lProvinces.size() - 1; i >= 0; --i) {
            nTD.listLEFT.lProvinces.add(lAllies.get(tBest).lProvinces.get(i));
         }

         boolean messageSent = DiplomacyManager.sendTradeRequest(lAllies.get(tBest).iCivID, nCivID, nTD);
         if (!messageSent) {
            break;
         }

         CFG.game
            .getCiv(nCivID)
            .civGameData
            .lSentMessages
            .add(new Civilization_SentMessages(lAllies.get(tBest).iCivID, Message_Type.TRADE_REQUEST_GIVE_PROVINCES));
         CFG.game
            .getCiv(lAllies.get(tBest).iCivID)
            .civGameData
            .lSentMessages
            .add(new Civilization_SentMessages(nCivID, Message_Type.TRADE_REQUEST_GIVE_PROVINCES));

         for(int j = nTD.listLEFT.lProvinces.size() - 1; j >= 0; --j) {
            Gdx.app
               .log("AoC", "abandonOrReleaseAsVassalProvinces -> nTD.listLEFT.lProvinces: " + CFG.game.getProvince(nTD.listLEFT.lProvinces.get(j)).getName());

            for(int i = tProvinces.size() - 1; i >= 0; --i) {
               if (tProvinces.get(i).equals(nTD.listLEFT.lProvinces.get(j))) {
                  tProvinces.remove(i);
                  break;
               }
            }
         }

         for(int i = lAllies.size() - 1; i >= 0; --i) {
            if (i != tBest) {
               for(int j = lAllies.get(tBest).lProvinces.size() - 1; j >= 0; --j) {
                  lAllies.get(i).removeProvinceID(lAllies.get(tBest).lProvinces.get(j));
               }
            }
         }

         lAllies.remove(tBest);

         for(int i = lAllies.size() - 1; i >= 0; --i) {
            if (lAllies.get(i).lProvinces.size() == 0) {
               lAllies.remove(i);
            }
         }
      }

      for(int i = tProvinces.size() - 1; i >= 0; --i) {
         CFG.gameAction.abadonProvince(tProvinces.get(i), nCivID);
      }

      return true;
   }

   protected final int abandonOrReleaseAsVassalProvinces_ReleaseVassal(List<AI_ReleaseVassal> lCivsToRelease, List<Integer> tProvinces, int nCivID) {
      int tBest = 0;

      for(int i = lCivsToRelease.size() - 1; i > 0; --i) {
         if (lCivsToRelease.get(tBest).lProvinces.size() < lCivsToRelease.get(i).lProvinces.size()) {
            tBest = i;
         } else if (lCivsToRelease.get(tBest).lProvinces.size() == lCivsToRelease.get(i).lProvinces.size() && CFG.oR.nextInt(100) < 50) {
            tBest = i;
         }
      }

      for(int i = tProvinces.size() - 1; i >= 0; --i) {
         CFG.game.getProvince(tProvinces.get(i)).was = true;
      }

      for(int i = lCivsToRelease.get(tBest).lProvinces.size() - 1; i >= 0; --i) {
         CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).was = false;
      }

      for(int i = 0; i < lCivsToRelease.get(tBest).lProvinces.size(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvincesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvinces(j)).was) {
               boolean canBeAdded = true;

               for(int o = lCivsToRelease.size() - 1; o >= 0; --o) {
                  if (CFG.game
                     .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvinces(j))
                     .getCore()
                     .getHaveACore(lCivsToRelease.get(o).iCivID)) {
                     canBeAdded = false;
                     break;
                  }
               }

               for(int m = 0;
                  m
                     < CFG.game
                        .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvinces(j))
                        .getNeighboringProvincesSize();
                  ++m
               ) {
                  for(int u = lCivsToRelease.size() - 1; u >= 0; --u) {
                     if (u != tBest
                        && lCivsToRelease.get(u)
                           .haveProvince(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvinces(j))
                                 .getNeighboringProvinces(m)
                           )) {
                        canBeAdded = false;
                        break;
                     }
                  }
               }

               if (canBeAdded) {
                  lCivsToRelease.get(tBest).addProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvinces(j));
                  CFG.game.getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringProvinces(j)).was = false;
               }
            }
         }

         for(int j = 0; j < CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvincesSize(); ++j) {
            for(int k = 0;
               k
                  < CFG.game
                     .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                     .getNeighboringProvincesSize();
               ++k
            ) {
               if (!CFG.game
                     .getProvince(
                        CFG.game
                           .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                           .getNeighboringProvinces(k)
                     )
                     .getSeaProvince()
                  && CFG.game
                     .getProvince(
                        CFG.game
                           .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                           .getNeighboringProvinces(k)
                     )
                     .was) {
                  boolean canBeAdded = true;

                  for(int o = lCivsToRelease.size() - 1; o >= 0; --o) {
                     if (CFG.game
                        .getProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                              .getNeighboringProvinces(k)
                        )
                        .getCore()
                        .getHaveACore(lCivsToRelease.get(o).iCivID)) {
                        canBeAdded = false;
                        break;
                     }
                  }

                  for(int m = 0;
                     m
                        < CFG.game
                           .getProvince(
                              CFG.game
                                 .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                                 .getNeighboringProvinces(k)
                           )
                           .getNeighboringProvincesSize();
                     ++m
                  ) {
                     for(int u = lCivsToRelease.size() - 1; u >= 0; --u) {
                        if (u != tBest
                           && lCivsToRelease.get(u)
                              .haveProvince(
                                 CFG.game
                                    .getProvince(
                                       CFG.game
                                          .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                                          .getNeighboringProvinces(k)
                                    )
                                    .getNeighboringProvinces(m)
                              )) {
                           canBeAdded = false;
                           break;
                        }
                     }
                  }

                  if (canBeAdded) {
                     lCivsToRelease.get(tBest)
                        .addProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                              .getNeighboringProvinces(k)
                        );
                     CFG.game
                        .getProvince(
                           CFG.game
                              .getProvince(CFG.game.getProvince(lCivsToRelease.get(tBest).lProvinces.get(i)).getNeighboringSeaProvinces(j))
                              .getNeighboringProvinces(k)
                        )
                        .was = false;
                  }
               }
            }
         }
      }

      this.clearWas(tProvinces);
      return CFG.game.releaseAVasssal(CFG.game.getCiv(lCivsToRelease.get(tBest).iCivID).getCivTag(), lCivsToRelease.get(tBest).lProvinces, -1, nCivID, true);
   }

   protected final void clearWas(List<Integer> was) {
      for(int i = was.size() - 1; i >= 0; --i) {
         CFG.game.getProvince(was.get(i)).was = false;
      }
   }

   protected final void clearWas() {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         CFG.game.getProvince(i).was = false;
      }
   }

   protected final float armyOverBudget_Disband_AtWar(int nCivID) {
      return 0.9F
         - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getMin_Goods(nCivID)
         - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MIN_INVESTMENTS;
   }

   protected void armyOverBudget_Disband(int nCivID) {
      if (CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_DISBAND) {
         boolean atWar = false;
         if ((CFG.game.getCiv(nCivID).isAtWar() || CFG.game.getCiv(nCivID).civGameData.civPlans.isPreparingForTheWar())
            && CFG.game.getCiv(nCivID).iBudget > 0
            && CFG.game.getCiv(nCivID).getMoney() + (long)(CFG.game.getCiv(nCivID).iBudget * 3) > 0L) {
            Gdx.app.log("AoC", "armyOverBudget_Disband -> 000, DONT DISBAND ARMY, WE NEEED IT!!!1");
            atWar = true;
            return;
         }

         Gdx.app.log("AoC", "armyOverBudget_Disband -> 000");
         List<AI_ArmyUpkeep> armyUpkeep = new ArrayList<>();
         int spendingsOnArmy = (int)(
            (float)CFG.game.getCiv(nCivID).iBudget * (atWar ? this.armyOverBudget_Disband_AtWar(nCivID) : this.getMinMilitarySpendings(nCivID))
         );
         int budgetForArmyisOver = (int)Math.abs(
            (float)CFG.game.getCiv(nCivID).iBudget * (atWar ? this.armyOverBudget_Disband_AtWar(nCivID) : this.getMinMilitarySpendings(nCivID))
               - (float)CFG.game.getCiv(nCivID).iBudget * CFG.game.getCiv(nCivID).iMilitaryUpkeep_PERC
         );
         Gdx.app.log("AoC", "armyOverBudget_Disband -> CFG.game.getCiv(nCivID).iMilitaryUpkeep_Total: " + CFG.game.getCiv(nCivID).iMilitaryUpkeep_Total);
         Gdx.app.log("AoC", "armyOverBudget_Disband -> spendingsOnArmy: " + spendingsOnArmy);
         Gdx.app.log("AoC", "armyOverBudget_Disband -> budgetForArmyisOver: " + budgetForArmyisOver);
         if (CFG.game.getCiv(nCivID).iMilitaryUpkeep_Total > spendingsOnArmy) {
            Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> AA00");
            Gdx.app
               .log(
                  "AoC",
                  "armyOverBudget_Disband -> DISBAND OVER BUDGET -> CFG.game.getCiv(nCivID).iArmiesPositionSize: "
                     + CFG.game.getCiv(nCivID).iArmiesPositionSize
               );
            Gdx.app
               .log(
                  "AoC",
                  "armyOverBudget_Disband -> DISBAND OVER BUDGET -> CFG.game.getCiv(nCivID).lArmiesPosition.size(): "
                     + CFG.game.getCiv(nCivID).lArmiesPosition.size()
               );

            for(int i = 0; i < CFG.game.getCiv(nCivID).iArmiesPositionSize; ++i) {
               armyUpkeep.add(new AI_ArmyUpkeep(nCivID, CFG.game.getCiv(nCivID).lArmiesPosition.get(i)));
            }

            Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> armyUpkeep.SIZE: " + armyUpkeep.size());
            List<AI_ArmyUpkeep> armiesOver = new ArrayList<>();

            for(int i = armyUpkeep.size() - 1; i >= 0; --i) {
               Gdx.app
                  .log(
                     "AoC",
                     "armyOverBudget_Disband -> DISBAND OVER BUDGET -> armyUpkeep.get(i).iCost: "
                        + armyUpkeep.get(i).iCost
                        + ", budgetForArmyisOver: "
                        + budgetForArmyisOver
                  );
               if (armyUpkeep.get(i).iCost >= budgetForArmyisOver) {
                  armiesOver.add(armyUpkeep.get(i));
               }
            }

            Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> armiesOver.SIZE: " + armiesOver.size());
            if (armiesOver.size() > 0) {
               Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> AA11");
               int tBestID = 0;

               for(int i = tBestID + 1; i < armiesOver.size(); ++i) {
                  if (CFG.game.getProvince(armiesOver.get(tBestID).iProvinceID).getDangerLevel()
                     > CFG.game.getProvince(armiesOver.get(i).iProvinceID).getDangerLevel()) {
                     tBestID = i;
                  }
               }

               float costPerUnit = CFG.game_NextTurnUpdate.getMilitaryUpkeep(armiesOver.get(tBestID).iProvinceID, 1000, nCivID) / 1000.0F * 1.05F;
               int maxDisbandArmy = CFG.game.getProvince(armiesOver.get(tBestID).iProvinceID).getArmyCivID(nCivID);
               Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> maxDisbandArmy: " + maxDisbandArmy);
               if (maxDisbandArmy > 0) {
                  Gdx.app
                     .log(
                        "AoC",
                        "armyOverBudget_Disband -> DISBAND OVER BUDGET -> 33333 -> "
                           + (int)Math.min(Math.ceil((double)((float)budgetForArmyisOver / costPerUnit)), (double)maxDisbandArmy)
                     );
                  CFG.gameAction
                     .disbandArmy(
                        armiesOver.get(tBestID).iProvinceID,
                        (int)Math.min(Math.ceil((double)((float)budgetForArmyisOver / costPerUnit)), (double)maxDisbandArmy),
                        nCivID
                     );
               } else {
                  Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> 44444");
               }
            } else {
               Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND OVER BUDGET -> BB0");
               armiesOver.clear();

               for(int i = armyUpkeep.size() - 1; i >= 0; --i) {
                  if (CFG.game.getProvince(armyUpkeep.get(i).iProvinceID).getDangerLevel() == 0) {
                     armiesOver.add(armyUpkeep.get(i));
                  }
               }

               if (armiesOver.size() > 0) {
                  int tTotalCost = 0;

                  for(int i = armiesOver.size() - 1; i >= 0; --i) {
                     tTotalCost += armiesOver.get(i).iCost;
                  }

                  if (tTotalCost >= budgetForArmyisOver) {
                     while(
                        CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_DISBAND
                           && armiesOver.size() > 0
                     ) {
                        if (budgetForArmyisOver <= 0) {
                           return;
                        }

                        int tBest = 0;

                        for(int i = armiesOver.size() - 1; i > 0; --i) {
                           if (armiesOver.get(tBest).iCost < armiesOver.get(i).iCost) {
                              tBest = i;
                           }
                        }

                        float costPerUnit = CFG.game_NextTurnUpdate.getMilitaryUpkeep(armiesOver.get(tBest).iProvinceID, 1000, nCivID) / 1000.0F * 1.05F;
                        Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND -> 44444");
                        CFG.gameAction
                           .disbandArmy(armiesOver.get(tBest).iProvinceID, (int)Math.ceil((double)((float)budgetForArmyisOver / costPerUnit)), nCivID);
                        budgetForArmyisOver -= armiesOver.get(tBest).iCost;
                        armiesOver.remove(tBest);
                     }
                  }
               }

               while(
                  CFG.game.getCiv(nCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_DISBAND
                     && armyUpkeep.size() > 0
               ) {
                  if (budgetForArmyisOver <= 0) {
                     return;
                  }

                  int tBest = 0;

                  for(int i = armyUpkeep.size() - 1; i > 0; --i) {
                     if (armyUpkeep.get(tBest).iCost < armyUpkeep.get(i).iCost) {
                        tBest = i;
                     }
                  }

                  float costPerUnit = CFG.game_NextTurnUpdate.getMilitaryUpkeep(armyUpkeep.get(tBest).iProvinceID, 1000, nCivID) / 1000.0F * 1.05F;
                  Gdx.app.log("AoC", "armyOverBudget_Disband -> DISBAND -> 55555");
                  CFG.gameAction.disbandArmy(armyUpkeep.get(tBest).iProvinceID, (int)Math.ceil((double)((float)budgetForArmyisOver / costPerUnit)), nCivID);
                  budgetForArmyisOver -= armyUpkeep.get(tBest).iCost;
                  armyUpkeep.remove(tBest);
               }
            }
         }
      } else {
         Gdx.app.log("AoC", "armyOverBudget_Disband -> NO MOVEMENT POITNS");
      }
   }

   protected final void useTechnologyPoints(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         List<AI_Skills> nSkills = new ArrayList<>();
         nSkills.add(new AI_Skills(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH, 25));
         nSkills.add(new AI_Skills_Eco(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH, 25));
         nSkills.add(new AI_Skills_Taxation(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION, 25));
         nSkills.add(new AI_Skills_Production(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION, 25));
         nSkills.add(new AI_Skills_Administration(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION, 20));
         nSkills.add(new AI_Skills_Military(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP, 30));
         nSkills.add(new AI_Skills_Research(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH, 30));
         int pointsToUse = CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID);
         int nSkillsSize = nSkills.size();
         if (CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.size() > 0
            && CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION < 15
            && CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION < CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.size()) {
            SkillsManager.add_Colonization(nCivID);
            --pointsToUse;
         }

         while(pointsToUse-- > 0) {
            int tBestID = 0;

            for(int i = tBestID + 1; i < nSkillsSize; ++i) {
               if (nSkills.get(tBestID).getScore(nCivID) < nSkills.get(i).getScore(nCivID)) {
                  tBestID = i;
               }
            }

            nSkills.get(tBestID).addPoint_CivID(nCivID);
         }
      }
   }

   protected final void updateLiberityDesire(int nCivID) {
      if (CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID) {
         boolean updateLiberity = true;

         try {
            if ((float)CFG.game.getCiv(nCivID).getNumOfProvinces()
               > (float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getNumOfProvinces() * 0.85F) {
               CFG.game
                  .getCiv(nCivID)
                  .setVassalLiberityDesire(
                     CFG.game.getCiv(nCivID).getVassalLiberityDesire()
                        + (0.425F + (float)CFG.oR.nextInt(825) / 1000.0F)
                           * (float)(
                              CFG.game.getCiv(nCivID).getNumOfProvinces() / CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getNumOfProvinces()
                           )
                           * 0.85F
                  );
               updateLiberity = false;
            }
         } catch (ArithmeticException var6) {
         }

         try {
            if ((float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID)
               > 20.0F * CFG.game.getCiv(nCivID).civGameData.civPersonality.LIBERITY_ACCEPTABLE_TRIBUTE) {
               CFG.game
                  .getCiv(nCivID)
                  .setVassalLiberityDesire(
                     CFG.game.getCiv(nCivID).getVassalLiberityDesire()
                        + CFG.game.getCiv(nCivID).getVassalLiberityDesire() * 0.01F
                        + (0.325F + (float)CFG.oR.nextInt(100) / 100.0F)
                           * (float)(CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID) / 20)
                  );
               updateLiberity = false;
            } else if ((float)CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID)
               < 20.0F * CFG.game.getCiv(nCivID).civGameData.civPersonality.LIBERITY_ACCEPTABLE_TRIBUTE * 0.5F) {
               CFG.game
                  .getCiv(nCivID)
                  .setVassalLiberityDesire(
                     CFG.game.getCiv(nCivID).getVassalLiberityDesire()
                        - (0.075F + (float)CFG.oR.nextInt(125) / 100.0F)
                           * (float)(CFG.game.getCiv(CFG.game.getCiv(nCivID).getPuppetOfCivID()).getVassal_Tribute(nCivID) / 20)
                  );
            }
         } catch (ArithmeticException var5) {
         }

         try {
            if (CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getPuppetOfCivID()) < -10.0F) {
               CFG.game
                  .getCiv(nCivID)
                  .setVassalLiberityDesire(
                     CFG.game.getCiv(nCivID).getVassalLiberityDesire()
                        + 3.425F * Math.abs(CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getCiv(nCivID).getPuppetOfCivID()) / 100.0F)
                  );
               updateLiberity = false;
            }
         } catch (ArithmeticException var4) {
         }

         if (updateLiberity) {
            CFG.game
               .getCiv(nCivID)
               .setVassalLiberityDesire(CFG.game.getCiv(nCivID).getVassalLiberityDesire() - CFG.game.getCiv(nCivID).getVassalLiberityDesire() * 0.01F);
         }

         if (CFG.game.getCiv(nCivID).getVassalLiberityDesire() > CFG.game.getCiv(nCivID).civGameData.civPersonality.LIBERITY_DECLARATION) {
            DiplomacyManager.declarationOfIndependeceByVassal(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID);
         }
      }
   }
}
