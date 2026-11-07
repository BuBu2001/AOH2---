package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class FormableCivs_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sFormableCivTag = null;
   private List<String> sClaimants = new ArrayList<>();
   private List<Integer> lProvinces = new ArrayList<>();
   private int iCapitalProvinceID = -1;

   protected final void addClaimant(String nTag) {
      if (this.sFormableCivTag == null || !this.sFormableCivTag.equals(nTag)) {
         for(int i = 0; i < this.sClaimants.size(); ++i) {
            if (this.sClaimants.get(i).equals(nTag)) {
               return;
            }
         }

         this.sClaimants.add(nTag);
      }
   }

   protected final void removeClaimant(int i) {
      this.sClaimants.remove(i);
   }

   protected final String getClaimant(int i) {
      return this.sClaimants.get(i);
   }

   protected final int getClaimantsSize() {
      return this.sClaimants.size();
   }

   protected final void addProvince(int nProvince) {
      this.lProvinces.add(nProvince);
   }

   protected final int getProvinceID(int i) {
      return this.lProvinces.get(i);
   }

   protected final int getProvincesSize() {
      return this.lProvinces.size();
   }

   protected final void clearProvinces() {
      this.lProvinces.clear();
   }

   protected final void setCapitalProvinceID(int iCapitalProvinceID) {
      this.iCapitalProvinceID = iCapitalProvinceID;
   }

   protected final int getCapitalProvinceID() {
      return this.iCapitalProvinceID;
   }

   protected final String getFormableCivTag() {
      return this.sFormableCivTag;
   }

   protected final void setFormableCivTag(String sFormableCivTag) {
      this.sFormableCivTag = sFormableCivTag;
   }
}
