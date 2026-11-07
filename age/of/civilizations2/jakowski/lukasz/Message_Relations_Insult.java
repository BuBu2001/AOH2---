package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Message_Relations_Insult extends Message {
   protected Message_Relations_Insult(int byCivID) {
      super(byCivID, 0);
      this.messageType = Message_Type.RELATIONS_INSULT;
      this.iNumOfTurnsLeft = 3;
   }

   @Override
   protected void onAction(int iMessageID) {
      CFG.toast
         .setInView(
            CFG.langManager.get("DiplomaticRelationsAreSuspended") + ": " + CFG.game.getCiv(this.iFromCivID).getCivName(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
         );
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
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         CFG.toast
            .setInView(
               CFG.langManager.get("DiplomaticRelationsAreSuspended") + ": " + CFG.game.getCiv(this.iFromCivID).getCivName(),
               CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
      }
   }

   @Override
   protected void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         CFG.toast
            .setInView(
               CFG.langManager.get("DiplomaticRelationsAreSuspended") + ": " + CFG.game.getCiv(this.iFromCivID).getCivName(),
               CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
      }
   }

   @Override
   protected int getImageID() {
      return Images.diplo_relations_dec;
   }

   @Override
   protected int getBGImageID() {
      return Images.messages_r;
   }

   @Override
   protected MenuElement_Hover_v2 getHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AnInsultFrom") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).getCivName()));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomaticRelationsAreSuspended"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            " - "
               + Game_Calendar.getDate_ByTurnID(
                  Game_Calendar.TURN_ID
                     + CFG.game
                        .getCiv(this.iFromCivID)
                        .getCivilization_Diplomacy_GameData()
                        .isEmbassyClosed_Turns(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               ),
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            " ["
               + CFG.langManager
                  .get(
                     "TurnsX",
                     CFG.game
                        .getCiv(this.iFromCivID)
                        .getCivilization_Diplomacy_GameData()
                        .isEmbassyClosed_Turns(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  )
               + "]",
            CFG.COLOR_TEXT_OPTIONS_NS_HOVER
         )
      );
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
