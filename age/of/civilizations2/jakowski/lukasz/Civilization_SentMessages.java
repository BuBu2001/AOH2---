package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civilization_SentMessages implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iToCivID;
   protected Message_Type messageType = Message_Type.GIFT;
   protected int iSentInTurnID;

   protected Civilization_SentMessages(int iToCivID, Message_Type messageType) {
      this.iToCivID = iToCivID;
      this.messageType = messageType;
      this.iSentInTurnID = Game_Calendar.TURN_ID;
   }
}
