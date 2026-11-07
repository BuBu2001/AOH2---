package age.of.civilizations2.jakowski.lukasz;

class AI_ProvinceInfo {
   protected int iProvinceID;
   protected float iValue;
   protected int iRecruitable;

   protected AI_ProvinceInfo(int iProvinceID, int iValue, int iRecruitable) {
      this.iProvinceID = iProvinceID;
      this.iValue = (float)iValue;
      this.iRecruitable = iRecruitable;
   }
}
