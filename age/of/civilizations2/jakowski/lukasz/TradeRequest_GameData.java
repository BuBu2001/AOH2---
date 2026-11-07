package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class TradeRequest_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iCivLEFT = 0;
   protected int iCivRIGHT = 0;
   protected TradeRequest_List listLEFT = new TradeRequest_List();
   protected TradeRequest_List listRight = new TradeRequest_List();

   protected final boolean canBeSend() {
      return this.listLEFT.iGold > 0
         || this.listRight.iGold > 0
         || this.listLEFT.lProvinces.size() > 0
         || this.listRight.lProvinces.size() > 0
         || this.listLEFT.iDeclarWarOnCivID > 0
         || this.listRight.iDeclarWarOnCivID > 0
         || this.listLEFT.iFormCoalitionAgainst > 0
         || this.listRight.iFormCoalitionAgainst > 0
         || this.listLEFT.defensivePact
         || this.listRight.defensivePact
         || this.listLEFT.nonAggressionPact
         || this.listRight.nonAggressionPact
         || this.listLEFT.proclaimIndependence
         || this.listRight.proclaimIndependence
         || this.listLEFT.militaryAccess
         || this.listRight.militaryAccess;
   }
}
