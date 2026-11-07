package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class CivBonus_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected CivBonus_Type BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_PROSPERITY;
   protected float fModifier_PopGrowth = 0.0F;
   protected float fModifier_EconomyGrowth = 0.0F;
   protected float fModifier_IncomeTaxation = 0.0F;
   protected float fModifier_IncomeProduction = 0.0F;
   protected float fModifier_Research = 0.0F;
   protected float fModifier_MilitaryUpkeep = 0.0F;
   protected float fModifier_AttackBonus = 0.0F;
   protected float fModifier_DefenseBonus = 0.0F;
   protected float fModifier_MovementPoints = 0.0F;
   protected int iTurnsLeft = 0;
}
