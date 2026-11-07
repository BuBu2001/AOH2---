/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_Move;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_MovementCost_Right;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_MovementCost_Right_Free;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_Turns;
import java.util.ArrayList;

class Menu_InGame_ActionInfo_RegroupArmy_Move
extends SliderMenu {
    protected Menu_InGame_ActionInfo_RegroupArmy_Move() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_ActionInfo_Move("", 0 + AoCGame.LEFT, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2));
        try {
            if (CFG.game.currentRegroupArmy.getRouteSize() > 0 && CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0))) {
                menuElements.add(new Text_ActionInfo_MovementCost_Right_Free("-0.0", 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2){

                    @Override
                    protected void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("0.0", CFG.COLOR_TEXT_FREE_MOVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                });
            } else {
                menuElements.add(new Text_ActionInfo_MovementCost_Right("-" + (float)CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2){

                    @Override
                    protected void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                });
            }
        }
        catch (NullPointerException ex) {
            menuElements.add(new Text_ActionInfo_MovementCost_Right("-" + (float)CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
        }
        menuElements.add(new Text_ActionInfo_Turns("", 0 + AoCGame.LEFT, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2 - CFG.PADDING - CFG.BUTTON_HEIGHT - CFG.PADDING * 2));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, false, false);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                break;
            }
            case 1: {
                CFG.toast.setInView(CFG.langManager.get("MovementPoints") + ": " + this.getMenuElement(1).getText(), CFG.COLOR_INGAME_MOVEMENT_ZERO);
            }
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_Recruit();
    }
}

