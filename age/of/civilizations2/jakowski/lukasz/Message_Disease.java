package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Message_Disease extends Message {
   protected Message_Disease(int byCivID, int nProvinceID) {
      super(byCivID, nProvinceID);
      this.messageType = Message_Type.DISEASE;
      this.iNumOfTurnsLeft = 3;
   }

   @Override
   protected void onAction(int iMessageID) {
      if (this.iValue >= 0) {
         CFG.game.setActiveProvinceID(this.iValue);
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
      }

      if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DISEASES_MODE) {
         CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DISEASES_MODE);
      }

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
         CFG.toast.setInView(this.getPlagueName(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(4500);
      }
   }

   @Override
   protected void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         CFG.toast.setInView(this.getPlagueName(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(4500);
      }
   }

   protected final String getPlagueName() {
      try {
         return CFG.plagueManager.getPlague_InGame(CFG.game.getProvince(this.iValue).saveProvinceData.provincePlague.iPlagueID_InGame).getPlagueName();
      } catch (IndexOutOfBoundsException var2) {
      } catch (NullPointerException var3) {
      }

      return CFG.langManager.get("Plague");
   }

   protected final int getPlagueDeaths() {
      try {
         return CFG.plagueManager.getPlague_InGame(CFG.game.getProvince(this.iValue).saveProvinceData.provincePlague.iPlagueID_InGame).getDeaths();
      } catch (IndexOutOfBoundsException var2) {
      } catch (NullPointerException var3) {
      }

      return 0;
   }

   @Override
   protected int getImageID() {
      return Images.disease;
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
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Disease"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Name") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getPlagueName(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (this.getPlagueDeaths() > 0) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Deaths") + ": "));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + this.getPlagueDeaths()), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

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
