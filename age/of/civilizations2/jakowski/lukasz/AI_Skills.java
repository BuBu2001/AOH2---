package age.of.civilizations2.jakowski.lukasz;

class AI_Skills {
   protected int iPoints;
   protected int iPointsMax;

   protected AI_Skills(int iPoints, int iPointsMax) {
      this.iPoints = iPoints;
      this.iPointsMax = iPointsMax;
   }

   protected void addPoint_CivID(int nCivID) {
      SkillsManager.add_PopGrowth(nCivID);
      this.iPoints = CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH;
   }

   protected float getScore_Personality(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.TECH_POP;
   }

   protected float getScore(int nCivID) {
      return this.getScore_Personality(nCivID) * (1.0F - (float)this.iPoints / (float)this.iPointsMax);
   }
}
