package age.of.civilizations2.jakowski.lukasz;

class AI_Skills_Eco extends AI_Skills {
   protected AI_Skills_Eco(int iPoints, int iPointsMax) {
      super(iPoints, iPointsMax);
   }

   @Override
   protected void addPoint_CivID(int nCivID) {
      SkillsManager.add_EcoGrowth(nCivID);
      this.iPoints = CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH;
   }

   @Override
   protected float getScore_Personality(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.TECH_ECO;
   }
}
