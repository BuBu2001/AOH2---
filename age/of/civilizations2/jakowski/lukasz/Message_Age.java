package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Message_Age extends Message {
   protected Message_Age(int fromCivID) {
      super(fromCivID, 0);
      this.messageType = Message_Type.NEW_AGE;
      this.iNumOfTurnsLeft = 4;
   }

   @Override
   protected void onAction(int iMessageID) {
      CFG.toast.setInView("" + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getCivilization_Diplomacy_GameData()
         .messageBox
         .getMessage(iMessageID)
         .onDecline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(iMessageID);
      CFG.menuManager.rebuildInGame_Messages();
   }

   @Override
   protected void onAccept(int iCivID) {
   }

   @Override
   protected void onDecline(int iCivID) {
   }

   @Override
   protected int getImageID() {
      return Images.time;
   }

   @Override
   protected int getBGImageID() {
      return Images.messages;
   }

   @Override
   protected MenuElement_Hover_v2 getHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WelcomeInTheNewAge"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName() + " "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "[" + CFG.gameAges.getYear(CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getBeginningYear()), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + CFG.gameAges.getYear(CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getEndYear()) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
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
      if (CFG.game.getCiv(this.iFromCivID).civGameData.leaderData != null) {
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).civGameData.leaderData.getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      return new MenuElement_Hover_v2(nElements);
   }
}
