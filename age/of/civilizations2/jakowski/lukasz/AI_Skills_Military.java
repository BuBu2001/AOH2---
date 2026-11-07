package age.of.civilizations2.jakowski.lukasz;

class AI_Skills_Military extends AI_Skills {
   protected AI_Skills_Military(int iPoints, int iPointsMax) {
      super(iPoints, iPointsMax);
   }

   @Override
   protected void addPoint_CivID(int nCivID) {
      SkillsManager.add_MilitaryUpkeep(nCivID);
      this.iPoints = CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP;
   }

   @Override
   protected float getScore_Personality(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.TECH_MILITARY_UPKEEP;
   }
}
