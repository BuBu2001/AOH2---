package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class LeaderOfCiv_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sTag = "";
   private String sName = "";
   private String sImage = "";
   private String sWiki = "";
   private int Year = 3;
   private int Month = 2;
   private int Day = 1;
   protected float fModifier_PopGrowth = 0.0F;
   protected float fModifier_EconomyGrowth = 0.0F;
   protected float fModifier_IncomeTaxation = 0.0F;
   protected float fModifier_IncomeProduction = 0.0F;
   protected float fModifier_Administration = 0.0F;
   protected float fModifier_Research = 0.0F;
   protected float fModifier_MilitaryUpkeep = 0.0F;
   protected float fModifier_AttackBonus = 0.0F;
   protected float fModifier_DefenseBonus = 0.0F;
   protected float fModifier_MovementPoints = 0.0F;

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final String getImage() {
      return this.sImage;
   }

   protected final void setImage(String sImage) {
      this.sImage = sImage;
   }

   protected final String getWiki() {
      return this.sWiki;
   }

   protected final void setWiki(String sWiki) {
      this.sWiki = sWiki;
   }

   protected final int getMonth() {
      return this.Month;
   }

   protected final void setMonth(int month) {
      this.Month = month;
   }

   protected final int getYear() {
      return this.Year;
   }

   protected final void setYear(int year) {
      this.Year = year;
   }

   protected final int getDay() {
      return this.Day;
   }

   protected final void setDay(int day) {
      this.Day = day;
   }

   protected final String getTag() {
      return this.sTag;
   }

   protected final void setTag(String sTag) {
      this.sTag = sTag;
   }
}
