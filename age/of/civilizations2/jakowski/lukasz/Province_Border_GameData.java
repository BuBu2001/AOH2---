package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.List;

class Province_Border_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private int withProvinceID;
   private List<Short> lPointsX;
   private List<Short> lPointsY;

   protected Province_Border_GameData(int withProvinceID, List<Short> nPointsX, List<Short> nPointsY) {
      this.withProvinceID = withProvinceID;
      this.lPointsX = nPointsX;
      this.lPointsY = nPointsY;
   }

   protected final int getWithProvinceID() {
      return this.withProvinceID;
   }

   protected final List<Short> getPointsX() {
      return this.lPointsX;
   }

   protected final List<Short> getPointsY() {
      return this.lPointsY;
   }
}
