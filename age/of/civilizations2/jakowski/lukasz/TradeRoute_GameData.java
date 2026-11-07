package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class TradeRoute_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sFromTagID;
   private String sToTagID;
   private int iAgeFoundID;

   protected TradeRoute_GameData() {
   }

   protected final String getFromTagID() {
      return this.sFromTagID;
   }

   protected final void setFromTagID(String sFromTagID) {
      this.sFromTagID = sFromTagID;
   }

   protected final String getToTagID() {
      return this.sToTagID;
   }

   protected final void setToTagID(String sToTagID) {
      this.sToTagID = sToTagID;
   }

   protected final int getAgeFoundID() {
      return this.iAgeFoundID;
   }

   protected final void setAgeFoundID(int iAgeFoundID) {
      this.iAgeFoundID = iAgeFoundID;
   }
}
