package age.of.civilizations2.jakowski.lukasz;

class Plagues_GameData {
   private String Name;
   protected int BeginningYear;
   protected int EndYear;
   protected float OUTBREAK_CHANCE;
   protected int OUTBREAK_PROVINCES;
   protected int OUTBREAK_PROVINCES_EXTRA;
   protected float OUTBREAK_SCORE_POPULATION;
   protected float OUTBREAK_SCORE_ECONOMY;
   protected float OUTBREAK_SCORE_DEVELOPMENT_LOW;
   protected float OUTBREAK_SCORE_DEVELOPMENT;
   protected float OUTBREAK_SCORE_HAPPINESS_LOW;
   protected float OUTBREAK_SCORE_HAPPINESS;
   protected int DURATION_TURNS_MIN;
   protected int DURATION_TURNS_EXTRA;
   protected float DEATH_RATE_MIN;
   protected float DEATH_RATE_EXTRA;
   protected float EXPANSION_MODIFIER;
   protected float EXPANSION_MODIFIER_EXTRA;
   protected float fR;
   protected float fG;
   protected float fB;

   protected Plagues_GameData(
      String Name,
      int BeginningYear,
      int EndYear,
      int DURATION_TURNS_MIN,
      int DURATION_TURNS_EXTRA,
      float DEATH_RATE_MIN,
      float DEATH_RATE_EXTRA,
      float EXPANSION_MODIFIER,
      float EXPANSION_MODIFIER_EXTRA,
      int R,
      int G,
      int B,
      float OUTBREAK_CHANCE,
      int OUTBREAK_PROVINCES,
      int OUTBREAK_PROVINCES_EXTRA,
      float OUTBREAK_SCORE_POPULATION,
      float OUTBREAK_SCORE_ECONOMY,
      float OUTBREAK_SCORE_DEVELOPMENT,
      float OUTBREAK_SCORE_HAPPINESS,
      float OUTBREAK_SCORE_DEVELOPMENT_LOW,
      float OUTBREAK_SCORE_HAPPINESS_LOW
   ) {
      this.Name = Name;
      if (EndYear < BeginningYear) {
         int tData = BeginningYear;
         BeginningYear = EndYear;
         EndYear = tData;
      }

      this.BeginningYear = BeginningYear;
      this.EndYear = EndYear;
      this.DURATION_TURNS_MIN = Math.max(DURATION_TURNS_MIN, 1);
      this.DURATION_TURNS_EXTRA = Math.max(DURATION_TURNS_EXTRA, 0);
      this.DEATH_RATE_MIN = Math.max(DEATH_RATE_MIN, 0.01F);
      this.DEATH_RATE_EXTRA = Math.max(DEATH_RATE_EXTRA, 0.0F);
      this.EXPANSION_MODIFIER = Math.max(EXPANSION_MODIFIER, 0.01F);
      this.EXPANSION_MODIFIER_EXTRA = Math.max(EXPANSION_MODIFIER_EXTRA, 0.0F);
      this.fR = (float)R / 255.0F;
      this.fG = (float)G / 255.0F;
      this.fB = (float)B / 255.0F;
      this.OUTBREAK_CHANCE = Math.max(OUTBREAK_CHANCE, 0.0F);
      this.OUTBREAK_PROVINCES = Math.min(Math.max(OUTBREAK_PROVINCES, 1), 10);
      this.OUTBREAK_PROVINCES_EXTRA = Math.max(OUTBREAK_PROVINCES_EXTRA, 0);
      this.OUTBREAK_SCORE_POPULATION = Math.min(Math.max(OUTBREAK_SCORE_POPULATION, -2.0F), 2.0F);
      this.OUTBREAK_SCORE_ECONOMY = Math.min(Math.max(OUTBREAK_SCORE_ECONOMY, -2.0F), 2.0F);
      this.OUTBREAK_SCORE_DEVELOPMENT = Math.min(Math.max(OUTBREAK_SCORE_DEVELOPMENT, -2.0F), 2.0F);
      this.OUTBREAK_SCORE_HAPPINESS = Math.min(Math.max(OUTBREAK_SCORE_HAPPINESS, -2.0F), 2.0F);
      this.OUTBREAK_SCORE_DEVELOPMENT_LOW = Math.min(Math.max(OUTBREAK_SCORE_DEVELOPMENT_LOW, -2.0F), 2.0F);
      this.OUTBREAK_SCORE_HAPPINESS_LOW = Math.min(Math.max(OUTBREAK_SCORE_HAPPINESS_LOW, -2.0F), 2.0F);
   }

   protected final String getName() {
      return CFG.langManager.get(this.Name);
   }

   protected final String getName_Real() {
      return this.Name;
   }

   protected final void setName(String Name) {
      this.Name = Name;
   }
}
