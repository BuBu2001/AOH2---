package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Message_LeftAlliance extends Message {
   protected Message_LeftAlliance(int byCivID, int allianceID) {
      super(byCivID, allianceID);
      this.messageType = Message_Type.LEFT_ALLIANCE;
      this.iNumOfTurnsLeft = 2;
   }

   @Override
   protected void onAction(int iMessageID) {
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
         try {
            CFG.toast
               .setInView(
                  CFG.game.getCiv(this.iFromCivID).getCivName()
                     + " "
                     + CFG.langManager.get("CivAIsNoLongerAMemberOf")
                     + " "
                     + CFG.game.getAlliance(this.iValue).getAllianceName(),
                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               );
            CFG.toast.setTimeInView(3000);
         } catch (IndexOutOfBoundsException var3) {
         }
      }
   }

   @Override
   protected void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         try {
            CFG.toast
               .setInView(
                  CFG.game.getCiv(this.iFromCivID).getCivName()
                     + " "
                     + CFG.langManager.get("CivAIsNoLongerAMemberOf")
                     + " "
                     + CFG.game.getAlliance(this.iValue).getAllianceName(),
                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               );
            CFG.toast.setTimeInView(3000);
         } catch (IndexOutOfBoundsException var3) {
         }
      }
   }

   @Override
   protected int getImageID() {
      return Images.diplo_alliance;
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
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).getCivName() + " "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivAIsNoLongerAMemberOf"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.game.getAlliance(this.iValue).getAllianceName()));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_alliance, CFG.PADDING, 0));
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
