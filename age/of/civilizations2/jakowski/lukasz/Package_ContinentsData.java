package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Package_ContinentsData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sPackageName;
   private List<String> lContinentsTags = new ArrayList<>();

   protected Package_ContinentsData() {
      this.sPackageName = "";
      this.lContinentsTags = new ArrayList<>();
   }

   protected final String getPackageName() {
      return this.sPackageName;
   }

   protected final void setPackageName(String sPackageName) {
      this.sPackageName = sPackageName;
   }

   protected final String getContinentTag(int i) {
      return this.lContinentsTags.get(i);
   }

   protected final int getContinentsTagsSize() {
      return this.lContinentsTags.size();
   }

   protected final void addContinentTag(String sTag) {
      this.lContinentsTags.add(sTag);
   }

   protected final void removeContinentTag(int i) {
      this.lContinentsTags.remove(i);
   }
}
