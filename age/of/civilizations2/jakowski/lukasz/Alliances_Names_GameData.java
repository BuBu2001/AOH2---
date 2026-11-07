package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Alliances_Names_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sPackageName;
   private List<Alliances_Names_GameData_Bundle> lBundles = new ArrayList<>();

   protected Alliances_Names_GameData() {
      this.sPackageName = "";
   }

   protected final Alliances_Names_GameData_Bundle getBundle(int i) {
      return this.lBundles.get(i);
   }

   protected final void addBundle(String nWord) {
      this.lBundles.add(new Alliances_Names_GameData_Bundle(nWord));
   }

   protected final void removeBundle(int i) {
      this.lBundles.remove(i);
   }

   protected final int getSize() {
      return this.lBundles.size();
   }

   protected final String getPackageName() {
      return this.sPackageName;
   }

   protected final void setPackageName(String sPackageName) {
      this.sPackageName = sPackageName;
   }
}
