package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Terrain_GameData3 implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName = "";
   private String sIconName = "";
   private Color_GameData oColor;
   private float fMovementCost = 0.0F;
   private float fDefensiveModifier = 0.0F;
   private float fPopulationGrowthModifier;
   private float fEconomyGrowthModifier;
   private float fMilitaryUpkeepModifier = 0.0F;
   private float fBuildCostModifier;
   private float fBaseDevelopmentLevel;
   private int iBaseProvinceValue;

   protected Terrain_GameData3() {
      this.fPopulationGrowthModifier = 0.0F;
      this.fEconomyGrowthModifier = 0.0F;
      this.fBuildCostModifier = 0.0F;
      this.fBaseDevelopmentLevel = 0.0F;
      this.iBaseProvinceValue = 0;
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final String getIconName() {
      return this.sIconName;
   }

   protected final void setIconName(String sIconName) {
      this.sIconName = sIconName;
   }

   protected final Color_GameData getColor() {
      return this.oColor;
   }

   protected final void setColor(Color_GameData oColor) {
      this.oColor = oColor;
   }

   protected final float getDefensiveModifier() {
      return this.fDefensiveModifier;
   }

   protected final void setDefensiveModifier(float fDefensiveModifier) {
      this.fDefensiveModifier = fDefensiveModifier;
   }

   protected final float getPopulationGrowthModifier() {
      return this.fPopulationGrowthModifier;
   }

   protected final void setPopulationGrowthModifier(float fPopulationGrowthModifier) {
      this.fPopulationGrowthModifier = fPopulationGrowthModifier;
   }

   protected final float getEconomyGrowthModifier() {
      return this.fEconomyGrowthModifier;
   }

   protected final void setEconomyGrowthModifier(float fEconomyGrowthModifier) {
      this.fEconomyGrowthModifier = fEconomyGrowthModifier;
   }

   protected final float getBuildCostModifier() {
      return this.fBuildCostModifier;
   }

   protected final void setBuildCostModifier(float fBuildCostModifier) {
      this.fBuildCostModifier = fBuildCostModifier;
   }

   protected final float getMilitaryUpkeepModifier() {
      return this.fMilitaryUpkeepModifier;
   }

   protected final void setMilitaryUpkeepModifier(float fMilitaryUpkeepModifier) {
      this.fMilitaryUpkeepModifier = fMilitaryUpkeepModifier;
   }

   protected final float getMovementCost() {
      return this.fMovementCost;
   }

   protected final void setMovementCost(float fMovementCost) {
      this.fMovementCost = fMovementCost;
   }

   protected final float getBaseDevelopmentLevel() {
      return this.fBaseDevelopmentLevel;
   }

   protected final void setBaseDevelopmentLevel(float fBaseDevelopmentLevel) {
      this.fBaseDevelopmentLevel = fBaseDevelopmentLevel;
   }

   protected final int getBaseProvinceValue() {
      return this.iBaseProvinceValue;
   }

   protected final void setBaseProvinceValue(int iBaseProvinceValue) {
      this.iBaseProvinceValue = iBaseProvinceValue;
   }
}
