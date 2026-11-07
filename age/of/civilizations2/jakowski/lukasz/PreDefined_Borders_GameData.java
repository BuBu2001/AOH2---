package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class PreDefined_Borders_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<PreDefined_Borders_Data_GameData> lData = new ArrayList<>();

   protected PreDefined_Borders_GameData() {
   }

   protected final int getDataSize() {
      return this.lData.size();
   }

   protected final PreDefined_Borders_Data_GameData getData(int i) {
      return this.lData.get(i);
   }

   protected final void removeData(int i) {
      this.lData.remove(i);
   }

   protected final void addData(PreDefined_Borders_Data_GameData nData) {
      this.lData.add(nData);
   }
}
