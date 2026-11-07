package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

class Message_Bulit_Port extends Message {
   protected Message_Bulit_Port(int fromCivID, int iValue) {
      super(fromCivID, iValue);
      this.messageType = Message_Type.BULIT_PORT;
      this.iNumOfTurnsLeft = 1;
   }

   @Override
   protected void onAction(int iMessageID) {
      CFG.game.setActiveProvinceID(this.iValue);
      CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
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
         List<String> lMess = new ArrayList<>();
         List<Color> lColors = new ArrayList<>();
         lMess.add(
            (CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() + ": " : "")
               + CFG.langManager.get(BuildingsManager.getPort_Name(CFG.game.getProvince(this.iValue).getLevelOfPort()))
         );
         lColors.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(lMess, lColors);
         CFG.toast.setTimeInView(6000);
      }
   }

   @Override
   protected void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         List<String> lMess = new ArrayList<>();
         List<Color> lColors = new ArrayList<>();
         lMess.add(
            (CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() + ": " : "")
               + CFG.langManager.get(BuildingsManager.getPort_Name(CFG.game.getProvince(this.iValue).getLevelOfPort()))
         );
         lColors.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(lMess, lColors);
         CFG.toast.setTimeInView(6000);
      }
   }

   @Override
   protected int getImageID() {
      return Images.b_port;
   }

   @Override
   protected int getBGImageID() {
      return Images.messages_g;
   }

   @Override
   protected MenuElement_Hover_v2 getHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildingBuiltIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() : CFG.langManager.get("Province")
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.langManager.get(BuildingsManager.getPort_Name(CFG.game.getProvince(this.iValue).getLevelOfPort())), CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsYourArmyGoToTheSea"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "+" + (int)(BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(this.iValue).getLevelOfPort()) * 100.0F) + "%",
            CFG.COLOR_TEXT_MODIFIER_POSITIVE
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
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
