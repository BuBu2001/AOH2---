package age.of.civilizations2.jakowski.lukasz;

class BuildingsManager {
   protected static Buildings ACTIVE_BUILDING = Buildings.FORT;
   protected static int iBuildInProvinceID = 0;
   protected static final int BONUS_CAPITAL_DEFENSE = 15;
   protected static final int BONUS_CAPITAL_ATTACK_FROM_CAPITAL = 10;
   protected static final int DESTROY_MOVEMENT_COST = 4;
   private static final String[] FORT_NAMES = new String[]{"", "Castle", "Fortress"};
   private static final float[] FORT_BUILD_COST = new float[]{0.0F, 0.05F, 0.0865F};
   private static final int[] FORT_BUILD_MOVEMENT_COST = new int[]{0, 12, 14};
   private static final int[] FORT_DEFENSE_BONUS = new int[]{0, 10, 20};
   private static final int[] FORT_MAINTENANCE_COST = new int[]{0, 60, 125};
   private static final float[] FORT_TECH_LEVEL = new float[]{0.0F, 0.25F, 0.5F};
   private static final int[] FORT_CONSTRUCTION = new int[]{0, 2, 3};
   private static final String[] TOWER_NAMES = new String[]{"", "WatchTower"};
   private static final float[] TOWER_BUILD_COST = new float[]{0.0F, 0.0425F};
   private static final int[] TOWER_BUILD_MOVEMENT_COST = new int[]{0, 16};
   private static final int[] TOWER_DEFENSE_BONUS = new int[]{0, 4};
   private static final int[] TOWER_MAINTENANCE_COST = new int[]{0, 35};
   private static final float[] TOWER_TECHNOLOGY_LEVEL = new float[]{0.0F, 0.2F};
   private static final int[] TOWER_CONSTRUCTION = new int[]{0, 1};
   private static final String[] PORT_NAMES = new String[]{"", "Port"};
   private static final float[] PORT_BUILD_COST = new float[]{0.0F, 0.0685F};
   private static final int[] PORT_BUILD_MOVEMENT_COST = new int[]{0, 16};
   private static final int[] PORT_MAINTENANCE_COST = new int[]{0, 35};
   private static final float[] PORT_TECHNOLOGY_LEVEL = new float[]{0.0F, 0.25F};
   private static final float[] PORT_INCOME_PRODUCTION = new float[]{0.0F, 0.02F};
   private static final int[] PORT_CONSTRUCTION = new int[]{0, 1};
   private static final String[] FARM_NAMES = new String[]{"", "Farm", "Farm", "Farm", "Farm", "Farm"};
   private static final float[] FARM_BUILD_COST = new float[]{0.0F, 0.0625F, 0.0765F, 0.1115F, 0.165F, 0.215F};
   private static final int[] FARM_BUILD_MOVEMENT_COST = new int[]{0, 14, 16, 18, 24, 26};
   private static final float[] FARM_GROWTH_RATE_BONUS = new float[]{0.0F, 0.05F, 0.1F, 0.15F, 0.2F, 0.25F};
   private static final int[] FARM_MAINTENANCE_COST = new int[]{0, 35, 50, 55, 65, 75};
   private static final float[] FARM_TECHNOLOGY_LEVEL = new float[]{0.0F, 0.15F, 0.3F, 0.4F, 0.55F, 0.7F};
   private static final int[] FARM_CONSTRUCTION = new int[]{0, 1, 2, 3, 4, 5};
   private static final String[] LIBRARY_NAMES = new String[]{"", "Library", "University", "ResearchLab"};
   private static final float[] LIBRARY_BUILD_COST = new float[]{0.0F, 0.08F, 0.1425F, 0.2125F};
   private static final int[] LIBRARY_BUILD_MOVEMENT_COST = new int[]{0, 10, 16, 20};
   private static final int[] LIBRARY_RESEARCH_PER_POPULATION = new int[]{0, 725, 425, 225};
   private static final float[] LIBRARY_TECH_LEVEL = new float[]{0.0F, 0.25F, 0.5F, 0.85F};
   private static final int[] LIBRARY_CONSTRUCTION = new int[]{0, 2, 3, 4};
   private static final String[] ARMOURY_NAMES = new String[]{"", "Armoury"};
   private static final float[] ARMOURY_BUILD_COST = new float[]{0.0F, 0.095F};
   private static final int[] ARMOURY_BUILD_MOVEMENT_COST = new int[]{0, 28};
   private static final float[] ARMOURY_TECH_LEVEL = new float[]{0.0F, 0.4F};
   private static final int[] ARMOURY_CONSTRUCTION = new int[]{0, 4};
   private static final String[] WORKSHOP_NAMES = new String[]{"", "Workshop", "Workshop", "Workshop"};
   private static final float[] WORKSHOP_BUILD_COST = new float[]{0.0F, 0.06F, 0.1F, 0.15F};
   private static final int[] WORKSHOP_BUILD_MOVEMENT_COST = new int[]{0, 18, 24, 30};
   private static final float[] WORKSHOP_INCOME_PRODUCTION = new float[]{0.0F, 0.05F, 0.1F, 0.15F};
   private static final int[] WORKSHOP_MAINTENANCE_COST = new int[]{0, 35, 50, 70};
   private static final float[] WORKSHOP_TECHNOLOGY_LEVEL = new float[]{0.0F, 0.4F, 0.65F, 0.8F};
   private static final int[] WORKSHOP_CONSTRUCTION = new int[]{0, 2, 3, 3};
   private static final String[] SUPPLY_NAMES = new String[]{"", "SupplyCamp"};
   private static final float[] SUPPLY_BUILD_COST = new float[]{0.0F, 0.01675F};
   private static final int[] SUPPLY_BUILD_MOVEMENT_COST = new int[]{0, 14};
   private static final float[] SUPPLY_TECH_LEVEL = new float[]{0.0F, 0.3F};
   private static final int[] SUPPLY_CONSTRUCTION = new int[]{0, 3};
   private static final float[] SUPPLY_BONUS = new float[]{0.0F, 0.2F};

   protected static final int getFort_MaxLevel() {
      return FORT_NAMES.length - 1;
   }

   protected static final int getFort_MaxLevel_CanBuild(int nCivID) {
      for(int i = 0; i < FORT_TECH_LEVEL.length; ++i) {
         if (FORT_TECH_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel()) {
            return i - 1;
         }
      }

      return getFort_MaxLevel();
   }

   protected static final String getFort_Name(int nLevel) {
      try {
         return FORT_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return FORT_NAMES[FORT_NAMES.length - 1];
      }
   }

   protected static final int getFort_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFort() > 0) {
               iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFort();
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (FORT_BUILD_COST[nLevel] + 0.004721F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.0275F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getFort_BuildMovementCost(int nLevel) {
      try {
         return FORT_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getFort_DefenseBonus(int nLevel) {
      try {
         return FORT_DEFENSE_BONUS[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getFort_MaitenanceCost(int nLevel) {
      try {
         return FORT_MAINTENANCE_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getFort_TechLevel(int nLevel) {
      try {
         return FORT_TECH_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final int getFort_Construction(int nLevel) {
      try {
         return FORT_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean canBuildFort(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfFort() < getFort_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getFort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getFort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1);
   }

   protected static final boolean constructFort(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfFort() >= getFort_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getFort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getFort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getFort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getFort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getFort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(new Construction_GameData_Fort(nProvinceID, getFort_Construction(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1)));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildFort(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFort() < getFort_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfFort(CFG.game.getProvince(nProvinceID).getLevelOfFort() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;

         for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() > 0) {
               CFG.game.getProvince(nProvinceID).updateFogOfWar(i);
            }
         }

         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Fort(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean destroyFort(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFort() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfFort(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroyTower(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfWatchTower(0);
            if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
               CFG.game.getProvince(nProvinceID).updateFogOfWar(CFG.game.getPlayerID_ByCivID(nCivID));
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroyPort(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfPort() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfPort(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroyFarm(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFarm() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfFarm(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroyWorkshop(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfWorkshop(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroyLibrary(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfLibrary(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroyArmoury(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfArmoury() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfArmoury(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final boolean destroySupply(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfSupply() > 0) {
         if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
            CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
            CFG.game.getProvince(nProvinceID).setLevelOfSupply(0);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected static final int getTower_MaxLevel() {
      return TOWER_NAMES.length - 1;
   }

   protected static final int getTower_MaxLevel_CanBuild(int nCivID) {
      for(int i = 0; i < TOWER_TECHNOLOGY_LEVEL.length; ++i) {
         if (TOWER_TECHNOLOGY_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel()) {
            return i - 1;
         }
      }

      return getTower_MaxLevel();
   }

   protected static final String getTower_Name(int nLevel) {
      try {
         return TOWER_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return TOWER_NAMES[TOWER_NAMES.length - 1];
      }
   }

   protected static final int getTower_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfWatchTower() > 0) {
               ++iNumOfBuildigns;
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (TOWER_BUILD_COST[nLevel] + 0.005314F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.01F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getTower_BuildMovementCost(int nLevel) {
      try {
         return TOWER_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getTower_MaitenanceCost(int nLevel) {
      try {
         return TOWER_MAINTENANCE_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getTower_TechLevel(int nLevel) {
      try {
         return TOWER_TECHNOLOGY_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final int getTower_DefenseBonus(int nLevel) {
      try {
         return TOWER_DEFENSE_BONUS[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getTower_Construction(int nLevel) {
      try {
         return TOWER_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean canBuildTower(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() < getTower_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getTower_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getTower_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1);
   }

   protected static final boolean constructTower(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() >= getTower_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getTower_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getTower_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getTower_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getTower_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getTower_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(
               new Construction_GameData_Tower(nProvinceID, getTower_Construction(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1))
            );
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildTower(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() < getTower_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfWatchTower(CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;

         for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() > 0) {
               for(int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++j) {
                  CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).updateFogOfWar(i);
               }
            }
         }

         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Tower(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final int getPort_MaxLevel() {
      return PORT_NAMES.length - 1;
   }

   protected static final String getPort_Name(int nLevel) {
      try {
         return PORT_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return PORT_NAMES[PORT_NAMES.length - 1];
      }
   }

   protected static final int getPort_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfPort() > 0) {
               ++iNumOfBuildigns;
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (PORT_BUILD_COST[nLevel] + 0.00325F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.015F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getPort_BuildMovementCost(int nLevel) {
      try {
         return PORT_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getPort_MaitenanceCost(int nLevel) {
      try {
         return PORT_MAINTENANCE_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getPort_Construction(int nLevel) {
      try {
         return PORT_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getPort_TechLevel(int nLevel) {
      try {
         return PORT_TECHNOLOGY_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final float getPort_IncomeProduction(int nLevel) {
      try {
         return PORT_INCOME_PRODUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         return 0.0F;
      }
   }

   protected static final boolean canBuildPort(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfPort() < getPort_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getPort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getPort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1)
         && CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0;
   }

   protected static final boolean constructPort(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getLevelOfPort() < 0
         || CFG.game.getProvince(nProvinceID).getLevelOfPort() >= getPort_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getPort_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getPort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getPort_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(new Construction_GameData(nProvinceID, getPort_Construction(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1)));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildPort(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getLevelOfPort() >= 0 && CFG.game.getProvince(nProvinceID).getLevelOfPort() < getPort_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfPort(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Port(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final int getFarm_MaxLevel() {
      return FARM_NAMES.length - 1;
   }

   protected static final int getFarm_MaxLevel_CanBuild(int nCivID) {
      for(int i = 0; i < FARM_TECHNOLOGY_LEVEL.length; ++i) {
         if (FARM_TECHNOLOGY_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel()) {
            return i - 1;
         }
      }

      return getFarm_MaxLevel();
   }

   protected static final String getFarm_Name(int nLevel) {
      try {
         return FARM_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return FARM_NAMES[FARM_NAMES.length - 1];
      }
   }

   protected static final int getFarm_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFarm() > 0) {
               iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfFarm();
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (FARM_BUILD_COST[nLevel] + 0.00215F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.015F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getFarm_Construction(int nLevel) {
      try {
         return FARM_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getFarm_BuildMovementCost(int nLevel) {
      try {
         return FARM_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getFarm_TechLevel(int nLevel) {
      try {
         return FARM_TECHNOLOGY_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final float getFarm_GrowthRateBonus(int nLevel) {
      try {
         return FARM_GROWTH_RATE_BONUS[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return FARM_GROWTH_RATE_BONUS[FARM_GROWTH_RATE_BONUS.length - 1];
      }
   }

   protected static final int getFarm_MaitenanceCost(int nLevel) {
      try {
         return FARM_MAINTENANCE_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean constructFarm(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfFarm() >= getFarm_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getFarm_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getFarm_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getFarm_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getFarm_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getFarm_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(new Construction_GameData_Farm(nProvinceID, getFarm_Construction(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1)));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildFarm(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfFarm() < getFarm_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfFarm(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Farm(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean canBuildFarm_Terrain(int nProvinceID) {
      return CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(nProvinceID).getTerrainTypeID()) >= 0.0F;
   }

   protected static final boolean canBuildFarm(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfFarm() < getFarm_MaxLevel()
         && canBuildFarm_Terrain(nProvinceID)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getFarm_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getFarm_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfFarm() + 1);
   }

   protected static final int getLibrary_MaxLevel() {
      return LIBRARY_NAMES.length - 1;
   }

   protected static final int getLibrary_MaxLevel_CanBuild(int nCivID) {
      for(int i = 0; i < LIBRARY_TECH_LEVEL.length; ++i) {
         if (LIBRARY_TECH_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel()) {
            return i - 1;
         }
      }

      return getLibrary_MaxLevel();
   }

   protected static final String getLibrary_Name(int nLevel) {
      try {
         return LIBRARY_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return LIBRARY_NAMES[LIBRARY_NAMES.length - 1];
      }
   }

   protected static final int getLibrary_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfLibrary() > 0) {
               iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfLibrary();
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (LIBRARY_BUILD_COST[nLevel] + 0.00425F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.135F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getLibrary_BuildMovementCost(int nLevel) {
      try {
         return LIBRARY_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getLibrary_ResearchPerPopulation(int nLevel) {
      try {
         return LIBRARY_RESEARCH_PER_POPULATION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getLibrary_TechLevel(int nLevel) {
      try {
         return LIBRARY_TECH_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final int getLibrary_Construction(int nLevel) {
      try {
         return LIBRARY_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean constructLibrary(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfLibrary() >= getLibrary_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getLibrary_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getLibrary_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getLibrary_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getLibrary_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getLibrary_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(
               new Construction_GameData_Library(nProvinceID, getLibrary_Construction(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1))
            );
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildLibrary(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfLibrary() < getLibrary_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfLibrary(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Library(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean canBuildLibrary(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfLibrary() < getLibrary_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getLibrary_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getLibrary_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfLibrary() + 1);
   }

   protected static final int getArmoury_MaxLevel() {
      return ARMOURY_NAMES.length - 1;
   }

   protected static final String getArmoury_Name(int nLevel) {
      try {
         return ARMOURY_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return ARMOURY_NAMES[ARMOURY_NAMES.length - 1];
      }
   }

   protected static final int getArmoury_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfArmouries = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfArmoury() > 0) {
               ++iNumOfArmouries;
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (ARMOURY_BUILD_COST[nLevel] + 0.0235F * (float)iNumOfArmouries)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.3F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getArmoury_BuildMovementCost(int nLevel) {
      try {
         return ARMOURY_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getArmoury_TechLevel(int nLevel) {
      try {
         return ARMOURY_TECH_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final int getArmoury_Construction(int nLevel) {
      try {
         return ARMOURY_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean constructArmoury(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfArmoury() >= getArmoury_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getArmoury_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getArmoury_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getArmoury_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getArmoury_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getArmoury_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(
               new Construction_GameData_Armoury(nProvinceID, getArmoury_Construction(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1))
            );
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildArmoury(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfArmoury() < getArmoury_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfArmoury(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Armoury(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean canBuildArmoury(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfArmoury() < getArmoury_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getArmoury_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getArmoury_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfArmoury() + 1);
   }

   protected static final int getWorkshop_MaxLevel() {
      return WORKSHOP_NAMES.length - 1;
   }

   protected static final int getWorkshop_MaxLevel_CanBuild(int nCivID) {
      for(int i = 0; i < WORKSHOP_TECHNOLOGY_LEVEL.length; ++i) {
         if (WORKSHOP_TECHNOLOGY_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel()) {
            return i - 1;
         }
      }

      return getWorkshop_MaxLevel();
   }

   protected static final String getWorkshop_Name(int nLevel) {
      try {
         return WORKSHOP_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return WORKSHOP_NAMES[WORKSHOP_NAMES.length - 1];
      }
   }

   protected static final int getWorkshop_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfWorkshop() > 0) {
               iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfWorkshop();
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (WORKSHOP_BUILD_COST[nLevel] + 0.002675F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.025F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final int getWorkshop_Construction(int nLevel) {
      try {
         return WORKSHOP_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final int getWorkshop_BuildMovementCost(int nLevel) {
      try {
         return WORKSHOP_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getWorkshop_TechLevel(int nLevel) {
      try {
         return WORKSHOP_TECHNOLOGY_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final float getWorkshop_IncomeProduction(int nLevel) {
      try {
         return WORKSHOP_INCOME_PRODUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return WORKSHOP_INCOME_PRODUCTION[WORKSHOP_INCOME_PRODUCTION.length - 1];
      }
   }

   protected static final int getWorkshop_MaitenanceCost(int nLevel) {
      try {
         return WORKSHOP_MAINTENANCE_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean constructWorkshop(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() >= getWorkshop_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getWorkshop_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getWorkshop_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getWorkshop_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getWorkshop_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(
               CFG.game.getCiv(nCivID).getMoney() - (long)getWorkshop_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1, nProvinceID)
            );
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(
               new Construction_GameData_Workshop(nProvinceID, getWorkshop_Construction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1))
            );
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean buildWorkshop(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() < getWorkshop_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfWorkshop(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Workshop(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean canBuildWorkshop(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() < getWorkshop_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getWorkshop_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getWorkshop_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() + 1);
   }

   protected static final int getSupply_MaxLevel() {
      return SUPPLY_NAMES.length - 1;
   }

   protected static final String getSupply_Name(int nLevel) {
      try {
         return SUPPLY_NAMES[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return SUPPLY_NAMES[SUPPLY_NAMES.length - 1];
      }
   }

   protected static final int getSupply_BuildCost(int nLevel, int nProvinceID) {
      try {
         int iNumOfBuildigns = 0;

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfSupply() > 0) {
               ++iNumOfBuildigns;
            }
         }

         return (int)(
            (
                  (float)CFG.game.getGameScenarios().getScenario_StartingPopulation() * (SUPPLY_BUILD_COST[nLevel] + 0.0115F * (float)iNumOfBuildigns)
                     + (float)CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.3F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   protected static final float getSupply_Bonus(int nLevel) {
      try {
         return SUPPLY_BONUS[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return SUPPLY_BONUS[SUPPLY_BONUS.length - 1];
      }
   }

   protected static final int getSupply_BuildMovementCost(int nLevel) {
      try {
         return SUPPLY_BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final float getSupply_TechLevel(int nLevel) {
      try {
         return SUPPLY_TECH_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0.0F;
      }
   }

   protected static final int getSupply_Construction(int nLevel) {
      try {
         return SUPPLY_CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         return 0;
      }
   }

   protected static final boolean constructSupply(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfSupply() >= getSupply_MaxLevel()
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getSupply_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getSupply_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1)
         && CFG.game.getCiv(nCivID).getMoney() >= (long)getSupply_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1, nProvinceID)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getSupply_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1));
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)getSupply_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1, nProvinceID));
         CFG.game
            .getCiv(nCivID)
            .addNewConstruction(
               new Construction_GameData_Supply(nProvinceID, getSupply_Construction(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1))
            );
         return true;
      } else {
         return false;
      }
   }

   protected static final boolean canBuildSupply(int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getLevelOfSupply() < getSupply_MaxLevel()
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getSupply_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getSupply_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1);
   }

   protected static final boolean buildSupply(int nProvinceID, int nCivID) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfSupply() < getSupply_MaxLevel()) {
         CFG.game.getProvince(nProvinceID).setLevelOfSupply(CFG.game.getProvince(nProvinceID).getLevelOfSupply() + 1);
         ++CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed;
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Supply(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }
}
