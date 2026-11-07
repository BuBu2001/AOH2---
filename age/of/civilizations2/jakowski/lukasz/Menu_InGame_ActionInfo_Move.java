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
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_ArmyBonus;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_Move;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_MovementCost_Right;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_MovementCost_Right_Free;
import age.of.civilizations2.jakowski.lukasz.Text_ActionInfo_Right_ArmyBonues;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ActionInfo_Move
extends SliderMenu {
    protected Menu_InGame_ActionInfo_Move() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_ActionInfo_Move("", 0 + AoCGame.LEFT, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2));
        if (CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getActiveProvinceID(), CFG.chosenProvinceID)) {
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
            menuElements.add(new Text_ActionInfo_MovementCost_Right("-" + (float)CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
        }
        int tRes = CFG.gameAction.moveArmyModifiers_Attackers(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        if (tRes != 0) {
            menuElements.add(new Text_ActionInfo_ArmyBonus(CFG.langManager.get("Attackers") + ": ", "" + (tRes > 0 ? "+" : "") + tRes + "%", 0 + AoCGame.LEFT, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.PADDING){

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList();
                        ArrayList nData = new ArrayList();
                        nElements = (ArrayList<MenuElement_Hover_v2_Element2>) CFG.gameAction.getMoveArmyModifiers_Attackers_Hover(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        this.menuElementHover = null;
                    }
                }

                @Override
                protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElementHover != null) {
                        this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                    }
                }
            });
        }
        if ((tRes = CFG.gameAction.moveArmyModifiers_Defenders(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID)) != 0) {
            menuElements.add(new Text_ActionInfo_Right_ArmyBonues(CFG.langManager.get("Defenders") + ": ", "" + (tRes > 0 ? "+" : "") + tRes + "%", 0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.PADDING){
                boolean isPositive;
                {
                    this.isPositive = true;
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList();
                        ArrayList nData = new ArrayList();
                        nElements = (ArrayList<MenuElement_Hover_v2_Element2>) CFG.gameAction.getMoveArmyModifiers_Defenders_Hover(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID);
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        this.menuElementHover = null;
                    }
                }

                @Override
                protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    if (this.menuElementHover != null) {
                        this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                    }
                }

                @Override
                protected Color getColorValue() {
                    return this.isPositive ? super.getColorValue() : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.isPositive = nCurrent >= 0;
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(tRes);
        }
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

