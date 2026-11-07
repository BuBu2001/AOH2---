package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class Game_NextTurnUpdate {
   protected static int INFLATION_PEAK_VALUE = 100;
   protected static final float INFLATION_STARTS_AT = 0.235F;
   protected static final float INFLATION_MODIFIER = 18.12746F;
   protected static float LEAGUE_BUDGET = 1.0F;
   protected static final float TAXES_INFLUENCE_POP = 0.3F;
   protected static final float TAXES_INFLUENCE_PRODUCTION = 0.175F;
   protected static final int PERCENTAGE_OF_INCOME_FOR_LORD_DEFAULT = 9;
   protected static final int PERCENTAGE_OF_INCOME_FOR_LORD_MAX = 20;
   protected static final int PERCENTAGE_OF_INCOME_FOR_LORD_MIN = 0;
   protected static final float PERCENTAGE_OF_INCOME_FOR_WAR_REPARATIONS = 0.08F;
   protected static final float EMPLOYEMENT_PER_ECONOMY = 1.025F;
   protected static final float EMPLOYEMENT_PER_ECONOMY_OLD = 1.775F;
   protected static final float DEFENSIVE_POSITION_MILITARY_UPKEEP_PER_TUR = 0.008F;
   protected static final int BUDGET_MAX = 200;

   protected final void updatePlayableProvinces() {
      CFG.oAI.PLAYABLE_PROVINCES = 0;

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
            ++CFG.oAI.PLAYABLE_PROVINCES;
         }
      }

      CFG.oAI.NUM_OF_CIVS_IN_THE_GAME = 0;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            ++CFG.oAI.NUM_OF_CIVS_IN_THE_GAME;
         }
      }

      CFG.oAI.NUM_OF_CIVS_IN_THE_GAME = Math.max(1, CFG.oAI.NUM_OF_CIVS_IN_THE_GAME);
      CFG.oAI.updateMinRivals();
   }

   protected final void updateInflationPeakValue() {
      INFLATION_PEAK_VALUE = 1;

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            INFLATION_PEAK_VALUE = Math.max(INFLATION_PEAK_VALUE, CFG.game.getCiv(i).iIncomeTaxation + CFG.game.getCiv(i).iIncomeProduction);
            LEAGUE_BUDGET = (float)(
               (int)Math.max(
                  LEAGUE_BUDGET, (float)(CFG.game.getCiv(i).iIncomeTaxation + CFG.game.getCiv(i).iIncomeProduction - CFG.game.getCiv(i).iAdministrationCosts)
               )
            );
         }
      }

      LEAGUE_BUDGET = (float)((int)(LEAGUE_BUDGET * 0.9F));

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            CFG.game.getCiv(i).iLeague = Math.min(
               (int)(
                  (float)Math.max(CFG.game.getCiv(i).iIncomeTaxation + CFG.game.getCiv(i).iIncomeProduction - CFG.game.getCiv(i).iAdministrationCosts, 0)
                     / LEAGUE_BUDGET
                     * 10.0F
               ),
               10
            );
         }
      }

      for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
         if (this.getInflationPerc(CFG.game.getPlayer(i).getCivID()) > 0.0049F) {
            CFG.game
               .getCiv(CFG.game.getPlayer(i).getCivID())
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_HighInflation(CFG.game.getPlayer(i).getCivID(), 0));
         }
      }
   }

   protected final void updateCivs_Money() {
      Gdx.app.log("AoC", "updateCivs_Money 0000");

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.getBalance_UpdateBudget_Prepare(i);
      }

      Gdx.app.log("AoC", "updateCivs_Money 11111");

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            CFG.game.getCiv(i).setMoney(CFG.game.getCiv(i).getMoney() + (long)this.getBalance(i));
            CFG.game.getCiv(i).updateLoansNextTurn();
         }
      }

      Gdx.app.log("AoC", "updateCivs_Money END");
   }

   protected final void updateProvinceStability() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         CFG.game.getCiv(i).lProvincesWithLowStability.clear();
         CFG.game.getCiv(i).fStability = 0.0F;
      }

      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
            CFG.game.getProvince(i).updateProvinceStability();
            if (CFG.game.getProvince(i).getCivID() > 0) {
               Civilization var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
               var10000.fStability += CFG.game.getProvince(i).getProvinceStability();
            }
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         for(int j = CFG.game.getCiv(i).lProvincesWithLowStability.size() - 1; j >= 0; --j) {
            if (CFG.game.getCiv(i).isAssimilateOrganized(CFG.game.getCiv(i).lProvincesWithLowStability.get(j))) {
               CFG.game.getCiv(i).lProvincesWithLowStability.remove(j);
            }
         }

         CFG.game.getCiv(i).setStability(CFG.game.getCiv(i).fStability / (float)CFG.game.getCiv(i).getNumOfProvinces());
      }
   }

   protected final int getBalance(int nCivID) {
      return (int)(this.getIncome(nCivID) - this.getExpenses(nCivID));
   }

   protected final int getAdministration_Capital(int nCivID) {
      return CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0
         ? (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0 ? CFG.game.getCiv(nCivID).getProvinceID(0) : 0)
         : CFG.game.getCiv(nCivID).getCapitalProvinceID();
   }

   protected final void getBalance_UpdateBudget_Prepare(int nCivID) {
      CFG.game.getCiv(nCivID).iIncomeTaxation = 0;
      CFG.game.getCiv(nCivID).iIncomeProduction = 0;
      CFG.game.getCiv(nCivID).iAdministrationCosts = 0;
      int nCapital = this.getAdministration_Capital(nCivID);
      float incomeModifer = this.taxIncome_Modifier(nCivID);

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Taxation = this.getProvinceIncome_Taxation(
            CFG.game.getCiv(nCivID).getProvinceID(i), nCivID, incomeModifer
         );
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Production = this.getProvinceIncome_Production(
            CFG.game.getCiv(nCivID).getProvinceID(i), nCivID, incomeModifer
         );
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iAdministrationCost = Math.min(
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Taxation
               + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Production,
            this.getProvinceAdministration(CFG.game.getCiv(nCivID).getProvinceID(i), nCapital)
         );
         Civilization var10000 = CFG.game.getCiv(nCivID);
         var10000.iIncomeTaxation = (int)((float)var10000.iIncomeTaxation + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Taxation);
         var10000 = CFG.game.getCiv(nCivID);
         var10000.iIncomeProduction = (int)(
            (float)var10000.iIncomeProduction + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Production
         );
         var10000 = CFG.game.getCiv(nCivID);
         var10000.iAdministrationCosts = (int)(
            (float)var10000.iAdministrationCosts + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iAdministrationCost
         );
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn() < 0) {
            ++CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus;
         } else {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
         }
      }

      CFG.game.getCiv(nCivID).iBudget = (int)(this.getIncome(nCivID) - (float)CFG.game.getCiv(nCivID).iAdministrationCosts);
   }

   protected final float getHappinessChange_ByTaxation(int nCivID) {
      return 0.042F
         + (
               (
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
                           + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
                              * CFG.game.getCiv(nCivID).getTechnologyLevel()
                              / 21.73F
                     )
                     * 100.0F
                  - CFG.game.getCiv(nCivID).getTaxationLevel() * 100.0F
            )
            * (
               CFG.game.getCiv(nCivID).getTaxationLevel() > CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
                  ? 1.45F
                  : 1.0F
            )
            * 0.02675F;
   }

   protected final float getHappinessChange_ByTaxation_Occupied(int nCivID) {
      return 0.034F
         + (
               (
                        CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
                           + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
                              * CFG.game.getCiv(nCivID).getTechnologyLevel()
                              / 21.73F
                     )
                     * 100.0F
                  - CFG.game.getCiv(nCivID).getTaxationLevel() * 100.0F
            )
            * (
               CFG.game.getCiv(nCivID).getTaxationLevel() > CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).ACCEPTABLE_TAXATION
                  ? 1.45F
                  : 1.0F
            )
            * 0.02675F;
   }

   protected float taxIncome_Modifier(int nCivID) {
      if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
         switch(CFG.DIFFICULTY) {
            case 0:
               return 1.25F;
            case 1:
            default:
               return 1.025F;
            case 2:
               return 1.0F;
            case 3:
               return 0.975F;
            case 4:
               return 0.95F;
         }
      } else {
         switch(CFG.DIFFICULTY) {
            case 0:
               return 0.925F;
            case 1:
            default:
               return 1.0F;
            case 2:
               return 1.025F;
            case 3:
               return 1.04F;
            case 4:
               return 1.065F;
         }
      }
   }

   protected final int getMilitarySpendings(int nCivID, int iBudget) {
      return Math.max(0, (int)(this.getMilitaryUpkeep_Total(nCivID) / (float)iBudget * 100.0F));
   }

   protected final float getIncome(int nCivID) {
      float tempTotal = 0.0F;
      tempTotal += (float)CFG.game.getCiv(nCivID).iIncomeTaxation;
      tempTotal += (float)CFG.game.getCiv(nCivID).iIncomeProduction;
      tempTotal += this.getIncome_FromVassalsOfCiv(nCivID);
      tempTotal += this.getIncome_Debuff_IsVassal(nCivID);
      tempTotal += this.getIncome_Buff_WarReparations(nCivID);
      tempTotal += this.getIncome_Debuff_WarReparations(nCivID);
      return (float)((int)tempTotal);
   }

   protected final float getIncome_TaxesLevel(int nCivID) {
      return this.getIncome_TaxesLevel_Taxation(nCivID) + this.getIncome_TaxesLevel_Production(nCivID);
   }

   protected final float getIncome_TaxesLevel_Taxation(int nCivID) {
      float tempTotal = 0.0F;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         tempTotal += this.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return tempTotal;
   }

   protected final float getIncome_TaxesLevel_Production(int nCivID) {
      float tempTotal = 0.0F;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         tempTotal += this.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return tempTotal;
   }

   protected final float getIncome_Debuff_IsVassal(int nCivID) {
      return CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID ? -this.getIncome_Vassals(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID) : 0.0F;
   }

   protected final float getIncome_FromVassalsOfCiv(int nCivID) {
      float tempTotal = 0.0F;

      for(int i = CFG.game.getCiv(nCivID).civGameData.lVassals.size() - 1; i >= 0; --i) {
         tempTotal += this.getIncome_Vassals(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID);
      }

      return tempTotal;
   }

   protected final float getIncome_Debuff_WarReparations(int nCivID) {
      float tempTotal = 0.0F;

      for(int i = CFG.game.getCiv(nCivID).getWarReparationsPaysSize() - 1; i >= 0; --i) {
         tempTotal -= this.getWarReparationsMoney(nCivID);
      }

      return tempTotal;
   }

   protected final float getIncome_Buff_WarReparations(int nCivID) {
      float tempTotal = 0.0F;

      for(int i = CFG.game.getCiv(nCivID).getWarReparationsGetsSize() - 1; i >= 0; --i) {
         tempTotal += this.getWarReparationsMoney(CFG.game.getCiv(nCivID).getWarReparationsGets(i).iFromCivID);
      }

      return tempTotal;
   }

   protected final float getIncome_Vassals(int nForCivID, int nIsVassal) {
      return CFG.game.getCiv(nIsVassal).getPuppetOfCivID() == nForCivID ? this.getVassalizationMoney(nIsVassal) : 0.0F;
   }

   protected final float getVassalizationMoney(int nVassalID) {
      return (float)CFG.game.getCiv(nVassalID).iIncomeTaxation
         * ((float)CFG.game.getCiv(CFG.game.getCiv(nVassalID).getPuppetOfCivID()).getVassal_Tribute(nVassalID) / 100.0F);
   }

   protected final float getWarReparationsMoney(int nCivID) {
      return (float)CFG.game.getCiv(nCivID).iIncomeTaxation * 0.08F;
   }

   protected final float getProvinceIncomeAndExpenses_Total(int nProvinceID) {
      return this.getProvinceIncome_Taxation(nProvinceID)
         + this.getProvinceIncome_Production(nProvinceID)
         - (
            CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID() >= 0
               ? this.getProvinceAdministration(nProvinceID, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID())
               : 0.0F
         );
   }

   protected final float getProvinceIncome_Taxation(int nProvinceID) {
      return this.getProvinceIncome_Taxation(
         nProvinceID, CFG.game.getProvince(nProvinceID).getCivID(), this.taxIncome_Modifier(CFG.game.getProvince(nProvinceID).getCivID())
      );
   }

   protected final float getProvinceIncome_Taxation(int nProvinceID, int nCivID, float incomeModifer) {
      return CFG.game.getProvince(nProvinceID).isOccupied()
         ? this.getProvinceAdministration(nProvinceID, CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID))
         : (float)(
               Math.pow(
                     (double)(
                        (float)this.getProvince_EmploymentPopulation(nProvinceID)
                           * (
                              CFG.gameAges.getAge_IncomeTaxation_Base(Game_Calendar.CURRENT_AGEID)
                                 + CFG.gameAges.getAge_IncomeTaxation_PerTechnology(Game_Calendar.CURRENT_AGEID)
                                    * CFG.game.getCiv(nCivID).getTechnologyLevel()
                                    * 21.923813F
                           )
                     ),
                     0.8386
                  )
                  + Math.pow(
                     (double)(
                        (float)this.getProvince_UnemploymentPopulation(nProvinceID)
                           * (
                              CFG.gameAges.getAge_IncomeTaxation_Base(Game_Calendar.CURRENT_AGEID)
                                 + CFG.gameAges.getAge_IncomeTaxation_PerTechnology(Game_Calendar.CURRENT_AGEID)
                                    * CFG.game.getCiv(nCivID).getTechnologyLevel()
                                    * 21.923813F
                           )
                     ),
                     0.7936
                  )
            )
            * CFG.gameAges.getAge_TreasuryModifier(Game_Calendar.CURRENT_AGEID)
            * (0.675F + 0.325F * CFG.game.getProvince(nProvinceID).getProvinceStability())
            * (
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).INCOME_TAXATION
                  + CFG.game.getCiv(nCivID).getModifier_IncomeTaxation()
                  + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.1F : 0.0F)
                  + -0.16584F
                  + 0.3674786F * CFG.game.getProvince(nProvinceID).getHappiness()
            )
            * (0.7F + 0.3F * CFG.game.getCiv(nCivID).getTaxationLevel())
            * incomeModifer
            * Game_Calendar.GAME_SPEED;
   }

   protected final int getProvince_EmploymentPopulation(int nProvinceID) {
      return (int)Math.min(
         (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation(),
         (float)CFG.game.getProvince(nProvinceID).getEconomy()
            * (
               1.775F
                  + 0.1725F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                  + 0.0925F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            )
      );
   }

   protected final int getProvince_UnemploymentPopulation(int nProvinceID) {
      return Math.max(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() - this.getProvince_EmploymentPopulation(nProvinceID), 0);
   }

   protected final int getEmploymentPopulation(int nCivID) {
      int out = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         out += this.getProvince_EmploymentPopulation(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return out;
   }

   protected final int getUnemploymentPopulation(int nCivID) {
      int out = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         out += this.getProvince_UnemploymentPopulation(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return out;
   }

   protected final float getProvinceIncome_Production(int nProvinceID) {
      return this.getProvinceIncome_Production(
         nProvinceID, CFG.game.getProvince(nProvinceID).getCivID(), this.taxIncome_Modifier(CFG.game.getProvince(nProvinceID).getCivID())
      );
   }

   protected final float getProvinceIncome_Production(int nProvinceID, int nCivID, float incomeModifer) {
      return CFG.game.getProvince(nProvinceID).isOccupied()
         ? (float)(
               (int)Math.min(
                  (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                     * (
                        1.025F
                           + 0.1725F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                           + 0.0425F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                     ),
                  (float)CFG.game.getProvince(nProvinceID).getEconomy()
               )
            )
            * (
               CFG.gameAges.getAge_IncomeProduction_Base(Game_Calendar.CURRENT_AGEID)
                  + CFG.gameAges.getAge_IncomeProduction_PerDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (
               0.0685F
                  + 0.575F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                  + 0.8625F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (0.425F + 0.575F * CFG.game.getProvince(nProvinceID).getProvinceStability())
            * CFG.gameAges.getAge_TreasuryModifier_Production(Game_Calendar.CURRENT_AGEID)
            * (
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).INCOME_PRODUCTION
                  + BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfPort())
                  + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_IncomeProduction()
                  + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2F : 0.0F)
                  + BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop())
            )
            * (0.825F + 0.175F * CFG.game.getCiv(nCivID).getTaxationLevel())
            * incomeModifer
            * Game_Calendar.GAME_SPEED
            * 0.1F
         : (float)(
               (int)Math.min(
                  (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                     * (
                        1.025F
                           + 0.1725F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                           + 0.0425F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                     ),
                  (float)CFG.game.getProvince(nProvinceID).getEconomy()
               )
            )
            * (
               CFG.gameAges.getAge_IncomeProduction_Base(Game_Calendar.CURRENT_AGEID)
                  + CFG.gameAges.getAge_IncomeProduction_PerDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (
               0.0685F
                  + 0.575F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                  + 0.8625F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (0.425F + 0.575F * CFG.game.getProvince(nProvinceID).getProvinceStability())
            * CFG.gameAges.getAge_TreasuryModifier_Production(Game_Calendar.CURRENT_AGEID)
            * (
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).INCOME_PRODUCTION
                  + BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfPort())
                  + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_IncomeProduction()
                  + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2F : 0.0F)
                  + BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop())
            )
            * (0.825F + 0.175F * CFG.game.getCiv(nCivID).getTaxationLevel())
            * incomeModifer
            * Game_Calendar.GAME_SPEED;
   }

   protected final float getExpenses(int nCivID) {
      float tempTotal = 0.0F;
      tempTotal += (float)CFG.game.getCiv(nCivID).iAdministrationCosts;
      tempTotal += this.getMilitaryUpkeep_Total(nCivID);
      tempTotal += this.getInvestments_Total(nCivID, CFG.game.getCiv(nCivID).iBudget);
      tempTotal += this.getGoodsSpendings(nCivID, CFG.game.getCiv(nCivID).iBudget);
      tempTotal += this.getInterestCost(nCivID);
      tempTotal += this.getInflation(nCivID);
      tempTotal += (float)CFG.game.getCiv(nCivID).getLoans_GoldTotalPerTurn();
      return (float)((int)Math.ceil((double)tempTotal));
   }

   protected final float getExpenses_Budget(int nCivID) {
      float tempTotal = 0.0F;
      tempTotal += (float)CFG.game.getCiv(nCivID).iAdministrationCosts;
      return (float)((int)Math.ceil((double)tempTotal));
   }

   protected final float getInflation(int nCivID) {
      if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
         return 0.0F;
      } else {
         try {
            return (float)CFG.game.getCiv(nCivID).getMoney()
                     / (
                        (
                              (float)INFLATION_PEAK_VALUE * 1.1275F
                                 + (float)(CFG.game.getCiv(nCivID).iIncomeTaxation + CFG.game.getCiv(nCivID).iIncomeProduction) * 0.4F
                           )
                           * 18.12746F
                     )
                  > 0.235F
               ? 1.0F
                  + (float)CFG.game.getCiv(nCivID).getMoney()
                     * ((float)CFG.game.getCiv(nCivID).getMoney() / ((float)INFLATION_PEAK_VALUE * 18.12746F) - 0.235F)
                     * 0.0679248F
               : 0.0F;
         } catch (ArithmeticException var3) {
            return 0.0F;
         }
      }
   }

   protected final float getInflationPerc(int nCivID) {
      return Math.max(this.getInflation(nCivID) / (float)CFG.game.getCiv(nCivID).getMoney(), 0.0F);
   }

   protected final float getInterestCost(int nCivID) {
      return CFG.game.getCiv(nCivID).getMoney() < 0L
         ? Math.min((float)Math.abs(CFG.game.getCiv(nCivID).getMoney()) * 0.01274F, Math.abs((float)CFG.game.getCiv(nCivID).iBudget * 0.075F))
         : 0.0F;
   }

   protected final float getAdministrationCost_Update(int nCivID) {
      float tempTotal = 0.0F;

      try {
         for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
            tempTotal += this.getProvinceAdministration(CFG.game.getCiv(nCivID).getProvinceID(i), CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID));
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }

      return tempTotal;
   }

   protected final float getProvinceAdministration(int nProvinceID, int nCapital) {
      return (float)Math.pow(
            (double)(
               (float)CFG.game.getProvince(nProvinceID).getEconomy()
                     * Math.min(
                        1.0F,
                        (float)CFG.game.getProvince(nProvinceID).getEconomy() / (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                     )
                     * 0.003248F
                  + (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                     * (0.0024F + 7.25E-4F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
            ),
            0.93478
         )
         * (
            1.0F
               + (
                     this.getDistanceFromCapital_PercOfMax(nCapital, nProvinceID)
                           / (1.5275F + CFG.game.getProvince(nProvinceID).getProvinceStability() / 8.0F)
                           * CFG.gameAges.getAge_AdministrationCost_Distance(Game_Calendar.CURRENT_AGEID)
                        + 0.13468F
                        - 0.13468F * CFG.game.getProvince(nProvinceID).getHappiness()
                  )
                  * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST_DISTANCE
         )
         * (
            0.9325F
               + 0.0715F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTaxationLevel()
               + 0.0325F * (1.0F - CFG.game.getProvince(nProvinceID).getProvinceStability())
         )
         * CFG.gameAges.getAge_TreasuryModifier_Administration(Game_Calendar.CURRENT_AGEID)
         * (
            CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST
               + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_Administation()
         )
         * (
            nProvinceID == nCapital
               ? CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST_CAPITAL
               : 1.0F
         )
         * Game_Calendar.GAME_SPEED;
   }

   protected final float getDistanceFromCapital(int nCapital, int toProvinceID) {
      try {
         return CFG.map.getMapWorldMap(CFG.map.getActiveMapID())
            ? Math.min(
               Math.min(
                  (float)Math.sqrt(
                     Math.pow(
                           (double)(
                              CFG.game.getProvince(toProvinceID).getCenterX_Real()
                                 + CFG.map.getMapBG().getWidth_Real()
                                 - CFG.game.getProvince(nCapital).getCenterX_Real()
                           ),
                           2.0
                        )
                        + Math.pow((double)(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real()), 2.0)
                  ),
                  (float)Math.sqrt(
                     Math.pow(
                           (double)(
                              CFG.game.getProvince(toProvinceID).getCenterX_Real()
                                 - (CFG.game.getProvince(nCapital).getCenterX_Real() + CFG.map.getMapBG().getWidth_Real())
                           ),
                           2.0
                        )
                        + Math.pow((double)(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real()), 2.0)
                  )
               ),
               (float)Math.sqrt(
                  Math.pow((double)(CFG.game.getProvince(toProvinceID).getCenterX_Real() - CFG.game.getProvince(nCapital).getCenterX_Real()), 2.0)
                     + Math.pow((double)(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real()), 2.0)
               )
            )
            : (float)Math.sqrt(
               Math.pow((double)(CFG.game.getProvince(toProvinceID).getCenterX_Real() - CFG.game.getProvince(nCapital).getCenterX_Real()), 2.0)
                  + Math.pow((double)(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real()), 2.0)
            );
      } catch (IndexOutOfBoundsException var4) {
         return (float)CFG.map.getMapBG().getMaxDistance();
      }
   }

   protected final float getDistanceFromCapital_PercOfMax(int nCapital, int toProvinceID) {
      return this.getDistanceFromCapital(nCapital, toProvinceID) / (float)CFG.map.getMapBG().getMaxDistance();
   }

   protected final float getDistanceFromAToB_PercOfMax(int nProvinceA, int nProvinceB) {
      return this.getDistanceFromCapital(nProvinceA, nProvinceB) / (float)CFG.map.getMapBG().getMaxDistance();
   }

   protected final float getMilitaryUpkeep_Total(int nCivID) {
      float tempTotal = 0.0F;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID);
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); ++i) {
         tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i), nCivID);
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); ++i) {
         tempTotal += this.getMilitaryUpkeep(
            CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits(i).getNumOfUnits(), nCivID
         );
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsPlunderSize(); ++i) {
         tempTotal += this.getMilitaryUpkeep(
            CFG.game.getCiv(nCivID).getMoveUnits_Plunder(i).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits_Plunder(i).getNumOfUnits(), nCivID
         );
      }

      return (float)((int)Math.ceil((double)tempTotal));
   }

   protected final float getMilitaryUpkeep(int nProvinceID, int nCivID) {
      return this.getMilitaryUpkeep(nProvinceID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID), nCivID);
   }

   protected final float getMilitaryUpkeep_WithAllRecruitmentsInProcess(int nProvinceID, int nArmy, int nCivID) {
      int out = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); ++i) {
         if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() != nProvinceID) {
            out = (int)(
               (float)out
                  + this.getMilitaryUpkeep(
                     CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID(), CFG.game.getCiv(nCivID).getRecruitArmy(i).getArmy(), nCivID
                  )
            );
         }
      }

      return (float)out + this.getMilitaryUpkeep(nProvinceID, nArmy, nCivID);
   }

   protected final float getMilitaryUpkeep_WithAllRecruitmentsInProcess_Disband(int nProvinceID, int nArmy, int nCivID) {
      int out = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); ++i) {
         if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() != nProvinceID) {
            out = (int)(
               (float)out
                  + this.getMilitaryUpkeep(
                     CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID(), CFG.game.getCiv(nCivID).getRecruitArmy(i).getArmy(), nCivID
                  )
            );
         }
      }

      return (float)out - this.getMilitaryUpkeep(nProvinceID, nArmy, nCivID);
   }

   protected final float getMilitaryUpkeep(int nProvinceID, int nArmy, int nCivID) {
      return (float)Math.pow(
            (double)((float)nArmy * CFG.gameAges.getAge_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID)),
            (double)(1.03F - 0.1275F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - 0.10479F * CFG.game.getCiv(nCivID).getTechnologyLevel())
         )
         * (1.0F + CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MILITARY_UPKEEP
         * CFG.gameAges.getAge_TreasuryModifier_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID)
         * (
            1.0F
               + (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / (float)CFG.game.getProvincesSize() * 0.425F
               + CFG.game.getCiv(nCivID).getWarWeariness()
               + CFG.game.getCiv(nCivID).getModifier_MilitaryUpkeep()
               - BuildingsManager.getSupply_Bonus(CFG.game.getProvince(nProvinceID).getLevelOfSupply())
         )
         * Game_Calendar.GAME_SPEED
         * (1.0F - this.getMilitaryUpkeep_DefensivePosition(nProvinceID));
   }

   protected final float getMilitaryUpkeep_WithoutDefensivePosition(int nProvinceID, int nArmy, int nCivID) {
      return (float)Math.pow(
            (double)((float)nArmy * CFG.gameAges.getAge_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID)),
            (double)(1.03F - 0.1275F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - 0.10479F * CFG.game.getCiv(nCivID).getTechnologyLevel())
         )
         * (1.0F + CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MILITARY_UPKEEP
         * CFG.gameAges.getAge_TreasuryModifier_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID)
         * (
            1.0F
               + (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / (float)CFG.game.getProvincesSize() * 0.425F
               + CFG.game.getCiv(nCivID).getWarWeariness()
               + CFG.game.getCiv(nCivID).getModifier_MilitaryUpkeep()
               - BuildingsManager.getSupply_Bonus(CFG.game.getProvince(nProvinceID).getLevelOfSupply())
         )
         * Game_Calendar.GAME_SPEED;
   }

   protected final float getMilitaryUpkeep_DefensivePosition(int nProvinceID) {
      return 0.008F * (float)CFG.game.getProvince(nProvinceID).getDefensivePosition();
   }

   protected final float getInvestments_Total(int nCivID, int iBudget) {
      return this.getResearchSpendings(nCivID, iBudget) + this.getInvestmentsSpendings(nCivID, iBudget);
   }

   protected final float getResearchSpendings(int nCivID, int iBudget) {
      return (float)iBudget * CFG.game.getCiv(nCivID).getSpendings_Research();
   }

   protected final float getGoodsSpendings(int nCivID, int iBudget) {
      return (float)iBudget * CFG.game.getCiv(nCivID).getSpendings_Goods();
   }

   protected final float getInvestmentsSpendings(int nCivID, int iBudget) {
      return (float)iBudget * CFG.game.getCiv(nCivID).getSpendings_Investments();
   }

   protected final void updateSpendingsOfCiv(int nCivID, int iBudget) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
         if (CFG.game.getCiv(nCivID).getMoney() < -500L) {
            CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
         }

         if (iBudget <= 0) {
            CFG.game.getCiv(nCivID).setSpendings_Goods(0.0F);
            CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
            CFG.game.getCiv(nCivID).setSpendings_Investments(0.0F);
         }

         int tempMilitary = this.getMilitarySpendings(nCivID, iBudget);
         if (tempMilitary + (int)(CFG.game.getCiv(nCivID).getSpendings_Goods() * 100.0F) > 200) {
            CFG.game.getCiv(nCivID).setSpendings_Goods((float)(200 - tempMilitary) / 100.0F);
         }

         tempMilitary += (int)(CFG.game.getCiv(nCivID).getSpendings_Goods() * 100.0F);
         if (tempMilitary
               + (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F)
               + (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F)
            > 200) {
            if (tempMilitary > 200) {
               CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
               CFG.game.getCiv(nCivID).setSpendings_Investments(0.0F);
               return;
            }

            int overBudget = (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F)
               + (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F)
               + tempMilitary
               - 200;
            int tempBef = (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F);
            CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv(nCivID).getSpendings_Research() - (float)overBudget / 2.0F / 100.0F);
            overBudget -= (int)((float)tempBef - CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F);
            if ((float)overBudget < CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F) {
               CFG.game.getCiv(nCivID).setSpendings_Investments(CFG.game.getCiv(nCivID).getSpendings_Investments() - (float)overBudget / 100.0F);
            } else {
               overBudget -= (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F);
               CFG.game.getCiv(nCivID).setSpendings_Investments(0.0F);
               CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv(nCivID).getSpendings_Research() - (float)overBudget / 100.0F);
            }
         }
      }
   }

   protected final void updateCities() {
      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.updateCities(i);
      }
   }

   protected final void updateCities(int nCivID) {
      int tempNumOfCities = (int)Math.ceil(
         (double)((float)(CFG.game.getCiv(nCivID).getNumOfProvinces() * CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP) / 100.0F)
      );
      int tMaxPopulation = 1;
      List<Integer> tempProvinces = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         tempProvinces.add(CFG.game.getCiv(nCivID).getProvinceID(i));
         CFG.game
            .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
            .setDrawCities(
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort() > 0
                  || CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfArmoury() > 0
            );
         if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()
            && tMaxPopulation < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation()) {
            tMaxPopulation = CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation();
         }
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCitiesSize(); ++j) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCity(j).getCityLevel() != CFG.getEditorCityLevel(0)) {
               CFG.game
                  .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
                  .getCity(j)
                  .setCityLevel(
                     this.getLevelOfCity(tMaxPopulation, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation(), j)
                  );
            }
         }
      }

      for(int j = 0; j < tempNumOfCities; ++j) {
         int largestProvinceID = 0;
         int largestPopulation = CFG.game.getProvince(tempProvinces.get(largestProvinceID)).getPopulationData().getPopulation();
         int i = 1;

         for(int iSize = tempProvinces.size(); i < iSize; ++i) {
            if (largestPopulation < CFG.game.getProvince(tempProvinces.get(i)).getPopulationData().getPopulation()) {
               largestProvinceID = i;
               largestPopulation = CFG.game.getProvince(tempProvinces.get(i)).getPopulationData().getPopulation();
            }
         }

         CFG.game.getProvince(tempProvinces.get(largestProvinceID)).setDrawCities(true);
         tempProvinces.remove(largestProvinceID);
      }

      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
      }

      tempProvinces.clear();
   }

   protected int getLevelOfCity(int nMaxPopulation, int nPopulation, int nCityID) {
      float nScore = (float)nPopulation / (float)nMaxPopulation;
      int out = 4;
      byte var6;
      if (nScore >= 0.765F) {
         var6 = 1;
      } else if (nScore >= 0.575F) {
         var6 = 2;
      } else if (nScore >= 0.325F) {
         var6 = 3;
      } else {
         var6 = 4;
      }

      return CFG.getEditorCityLevel(var6);
   }

   protected final void buildLevelsOfCities() {
      for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(i).getCitiesSize(); ++j) {
            CFG.game.getProvince(i).getCity(j).setCityLevel(CFG.getEditorCityLevel(4));
         }
      }

      for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
         this.buildLevelsOfCities(i);
      }
   }

   protected final void buildLevelsOfCities(int nCivID) {
      int tMaxPop = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         if (tMaxPop < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation()) {
            tMaxPop = CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation();
         }
      }

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCitiesSize(); ++j) {
            CFG.game
               .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
               .getCity(j)
               .setCityLevel(
                  CFG.getCityLevel_Population(
                     (float)tMaxPop, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation(), j
                  )
               );
         }
      }

      try {
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      } catch (NullPointerException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }
}
