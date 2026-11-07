package age.of.civilizations2.jakowski.lukasz;

class AI_ProvinceValue {
   protected int iProvinceID;
   protected float iValue;

   protected AI_ProvinceValue(int iProvinceID) {
      this.iProvinceID = iProvinceID;
      this.iValue = 0.0F;
   }

   protected AI_ProvinceValue(int iProvinceID, int iValue) {
      this.iProvinceID = iProvinceID;
      this.iValue = (float)iValue;
   }
}
