package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Message implements Serializable {
   private static final long serialVersionUID = 0L;
   protected int iFromCivID = -1;
   protected int iValue = 1;
   protected int iValue2 = 1;
   protected int iNumOfTurnsLeft = 4;
   protected TradeRequest_GameData tradeRequest = null;
   protected Ultimatum_GameData ultimatum = null;
   protected String TAG = null;
   protected Message_Type messageType = Message_Type.JOIN_ALLIANCE;
   protected boolean requestsResponse = false;
   protected boolean willPauseTheGame = false;

   protected Message(int fromCivID, int iValue) {
      this.iFromCivID = fromCivID;
      this.iValue = iValue;
   }

   protected void onAction(int iMessageID) {
      CFG.menuManager.rebuildInGame_Message_Alliance(this.iFromCivID, iMessageID, this.iValue);
   }

   protected void onAccept(int iCivID) {
      DiplomacyManager.acceptAllianceProposal(iCivID, this.iFromCivID);
   }

   protected void onDecline(int iCivID) {
      DiplomacyManager.declineAllianceProposal(iCivID, this.iFromCivID);
   }

   protected int getImageID() {
      return Images.diplo_alliance;
   }

   protected int getBGImageID() {
      return Images.messages;
   }

   protected MenuElement_Hover_v2 getHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Alliance"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(this.getImageID(), CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivAWantsToJoinAlliance", CFG.game.getCiv(this.iFromCivID).getCivName())));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MessageWillExpireIn") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft) + " ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      return new MenuElement_Hover_v2(nElements);
   }

   protected final boolean updateNextTurn() {
      return --this.iNumOfTurnsLeft <= 0;
   }
}
