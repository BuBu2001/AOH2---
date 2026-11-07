package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Province_SupportRebels implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iRebelsCivID;
   protected int iByCivID;
   protected int iTurnsLeft;

   protected Province_SupportRebels(int iByCivID, int iRebelsCivID, int iTurnsLeft) {
      this.iByCivID = iByCivID;
      this.iRebelsCivID = iRebelsCivID;
      this.iTurnsLeft = iTurnsLeft;
   }
}
