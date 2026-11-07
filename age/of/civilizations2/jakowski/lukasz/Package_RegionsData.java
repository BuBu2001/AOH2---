package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Package_RegionsData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sPackageName;
   private List<String> lRegionsTags = new ArrayList<>();

   protected Package_RegionsData() {
      this.sPackageName = "";
      this.lRegionsTags = new ArrayList<>();
   }

   protected final String getPackageName() {
      return this.sPackageName;
   }

   protected final void setPackageName(String sPackageName) {
      this.sPackageName = sPackageName;
   }

   protected final String getRegionTag(int i) {
      return this.lRegionsTags.get(i);
   }

   protected final int getRegionsTagsSize() {
      return this.lRegionsTags.size();
   }

   protected final void addRegionTag(String sTag) {
      this.lRegionsTags.add(sTag);
   }

   protected final void removeRegionTag(int i) {
      this.lRegionsTags.remove(i);
   }
}
