package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class Message_Ultimatum extends Message {
   protected Message_Ultimatum(int fromCivID, Ultimatum_GameData nUltimatum, int nUnits) {
      super(fromCivID, nUnits);
      nUltimatum.numOfUntis = nUnits;
      this.ultimatum = nUltimatum;
      this.requestsResponse = true;
      this.willPauseTheGame = true;
      this.messageType = Message_Type.ULTIMATUM;
   }

   @Override
   protected void onAction(int iMessageID) {
      CFG.menuManager.rebuildInGame_Message_Ultimatum(this.iFromCivID, iMessageID, this.iValue, this.ultimatum);
   }

   @Override
   protected void onAccept(int iCivID) {
      DiplomacyManager.acceptUltimatum(this.iFromCivID, iCivID, this.ultimatum);
   }

   @Override
   protected void onDecline(int iCivID) {
      DiplomacyManager.refuseUltimatum(this.iFromCivID, iCivID, this.ultimatum);
   }

   @Override
   protected int getBGImageID() {
      return Images.messages_r;
   }

   @Override
   protected int getImageID() {
      return Images.diplo_rivals;
   }

   @Override
   protected MenuElement_Hover_v2 getHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).getCivName() + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Ultimatum"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (this.ultimatum.demandAnexation) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandsAnnexationOfOurTerritory"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      if (this.ultimatum.demandVasalization) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandVassalizationOfUs"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      if (this.ultimatum.demandMilitaryAccess) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandMilitaryAccess"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      if (this.ultimatum.demandLiberation.size() > 0) {
         for(int i = 0; i < this.ultimatum.demandLiberation.size(); ++i) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandLiberationOfVassal") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.ultimatum.demandLiberation.get(i)).getCivName()));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.ultimatum.demandLiberation.get(i), CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }
      }

      if (this.ultimatum.demandProvinces.size() > 0) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandsOurProvinces"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      for(int i = 0; i < this.ultimatum.demandProvinces.size(); ++i) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(this.ultimatum.demandProvinces.get(i)).getName()));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.langManager.get("XUnitsAreReadyToAttackIfWeRefuseTheirOffer", this.iValue), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
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
