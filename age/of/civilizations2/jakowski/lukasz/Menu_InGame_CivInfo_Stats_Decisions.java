/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Action;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Action_Goverment;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Action_Tech;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_Civilize;
import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy_FormCivilization;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.CreateVassal_Data;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Ideologies_Manager;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology_Vassal;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivInfo;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CivInfo_Stats_Decisions
extends SliderMenu {
    protected Menu_InGame_CivInfo_Stats_Decisions() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tY = CFG.PADDING;
        for (i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanFormSize(); ++i) {
            menuElements.add(new Button_Diplomacy_FormCivilization(CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanForm(i), 0, tY, tempW - 2, true, CFG.canFormACiv(CFG.getActiveCivInfo(), CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanForm(i), true)){

                @Override
                protected void actionElement(int iID) {
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
                    CFG.viewsManager.disableAllViews();
                    CFG.game.resetChooseProvinceData();
                    CFG.game.resetRegroupArmyData();
                    CFG.game.setActiveProvinceID(-1);
                    CFG.game.resetChooseProvinceData_Immediately();
                    CFG.gameAction.hideAllProvinceActionViews();
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                    CFG.loadFormableCiv_GameData(CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanForm(iID));
                    CFG.menuManager.setViewID(Menu.eINGAME_FORMABLE_CIV_PROVINCES);
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        if (CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            menuElements.add(new Button_Diplomacy_Civilize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0, tY, tempW - 2, true, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() >= CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).CIVILIZE_TECH_LEVEL){

                @Override
                protected void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_Civilize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_IDEOLOGIES_MODE);
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        menuElements.add(new Button_Diplomacy_Action_Tech(Images.technology, CFG.langManager.get("Technology"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_Technology()) {
                    CFG.menuManager.setVisibleInGame_Technology(false);
                } else {
                    CFG.menuManager.rebuildInGame_Technology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                }
            }

            @Override
            protected void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + pointsLeft, pointsLeft > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
            menuElements.add(new Button_Diplomacy_Action(Images.diplo_alliance, CFG.langManager.get("LeaveAlliance"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

                @Override
                protected void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_LeaveAllinace(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        menuElements.add(new Button_Diplomacy_Action(Images.diplo_vassal, CFG.langManager.get("Vassals"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_Tribute()) {
                    CFG.menuManager.setVisibleInGame_Tribute(false);
                } else {
                    CFG.menuManager.rebuildInGame_Tribute();
                }
            }

            @Override
            protected void buildElementHover() {
                int i;
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassals"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                for (i = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() - 1; i > 0; --i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getCiv(i).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(i).getCivName() + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getIncome_Vassals(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                for (i = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() + 1; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getCiv(i).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(i).getCivName() + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getIncome_Vassals(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (nElements.size() <= 1) {
                    nElements.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NoVassals"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
            menuElements.add(new Button_Diplomacy_Action(Images.diplo_vassal, CFG.langManager.get("DeclarationOfIndependence"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, CFG.game.getCivTruce(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()) == 0){

                @Override
                protected void actionElement(int iID) {
                    CFG.menuManager.rebuildInGame_DeclarationOfIndependence(CFG.getActiveCivInfo());
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        if (CFG.game.getCivTruce(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()) > 0) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WeHaveATruceUntil") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + CFG.game.getCivTruce(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()))));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", CFG.game.getCivTruce(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID())) + "]", CFG.COLOR_TEXT_OPTIONS_NS_HOVER));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        } else {
                            this.menuElementHover = null;
                        }
                    }
                    catch (IndexOutOfBoundsException ex) {
                        this.menuElementHover = null;
                    }
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        menuElements.add(new Button_Diplomacy_Action(Images.top_diplomacy_points, CFG.langManager.get("ReleaseAVassal"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

            @Override
            protected void actionElement(int iID) {
                CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                CFG.viewsManager.disableAllViews();
                CFG.game.resetChooseProvinceData();
                CFG.game.resetRegroupArmyData();
                CFG.game.setActiveProvinceID(-1);
                CFG.game.resetChooseProvinceData_Immediately();
                CFG.gameAction.hideAllProvinceActionViews();
                CFG.game.getSelectedProvinces().clearSelectedProvinces();
                CFG.createVassal_Data = new CreateVassal_Data();
                CFG.selectMode = true;
                CFG.brushTool = false;
                CFG.VIEW_SHOW_VALUES = false;
                CFG.menuManager.setViewID(Menu.eINGAME_CREATE_VASSAL);
                Game_Render_Province.updateDrawProvinces();
                CFG.map.getMapBG().updateWorldMap_Shaders();
            }
        });
        menuElements.add(new Button_Diplomacy_Action(Images.editor_city, CFG.langManager.get("MoveCapital"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, CFG.gameAction.moveCapital_CanMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

            @Override
            protected void buildElementHover() {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (!CFG.gameAction.moveCapital_CanMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityHasRecentlyBeenMoved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.city, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Math.abs(Game_Calendar.TURN_ID - (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalMoved_LastTurnID() + 50)))));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.langManager.get("TurnsX", Math.abs(Game_Calendar.TURN_ID - (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalMoved_LastTurnID() + 50))) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    } else if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoveCapitalTo") + ": "));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCity(0).getCityName() : CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                                nData.clear();
                            }
                        } else {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectProvince"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        } else {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.gameAction.moveCapital_Cost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_INGAME_GOLD));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                    } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OccupiedProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectProvince"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            }

            @Override
            protected boolean getClickable() {
                return super.getClickable() && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getActiveProvinceID() != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() && (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() < 0 || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || !CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar());
            }

            @Override
            protected void actionElement(int iID) {
                CFG.menuManager.rebuildInGame_MoveCapital(CFG.game.getActiveProvinceID());
            }
        });
        menuElements.add(new Button_Diplomacy_Action_Goverment(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.langManager.get("ChangeTypeOfGovernment"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

            @Override
            protected void actionElement(int iID) {
                CFG.menuManager.rebuildInGame_ChangeGovernment();
            }

            @Override
            protected void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeTypeOfGovernment"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), CFG.COLOR_INGAME_GOLD));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("2.2", CFG.COLOR_INGAME_MOVEMENT));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Button_Diplomacy_Action(Images.diplo_loan, CFG.langManager.get("TakeLoan"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

            @Override
            protected void actionElement(int iID) {
                CFG.menuManager.rebuildInGame_TakeLoan(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TakeLoan"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Diplomacy_Action(Images.diplo_loan2, CFG.langManager.get("RepayLoans"), 0, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true){

            @Override
            protected void actionElement(int iID) {
                CFG.menuManager.rebuildInGame_Loans(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }

            @Override
            protected void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    int pointsLeft = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.skills.getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Loans") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan2, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        this.initMenu(new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, Menu_InGame_CivInfo_Stats_Decisions.this.getPosX() + iTranslateX, Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(), Menu_InGame_CivInfo_Stats_Decisions.this.getWidth(), this.getHeight(), true, false);
                CFG.drawRect_InfoBox_Left_Title(oSB, Menu_InGame_CivInfo_Stats_Decisions.this.getPosX() + iTranslateX, Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight(), Menu_InGame_CivInfo_Stats_Decisions.this.getWidth() - 2, this.getHeight());
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawTextWithShadow(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) / 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f) / 2, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0 + AoCGame.LEFT, ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f) + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, tempW, (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * 6, menuElements, false, false);
        this.updateLanguage();
        for (i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
        }
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Decisions"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_CivInfo.lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = Menu_InGame_CivInfo.hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (Menu_InGame_CivInfo.hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2, true, false);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + 1, this.getWidth() - 2, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + this.getHeight(), this.getWidth() - 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + 2 + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() + 2, true, false);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
    }

    @Override
    protected void actionElement(int iID) {
        if (CFG.SPECTATOR_MODE || CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS) {
            return;
        }
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
        }
    }

    @Override
    protected void actionClose() {
        super.setVisible(false);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setVisible(false);
        }
    }

    @Override
    protected void setPosY(int iPosY) {
        super.setPosY(iPosY);
        this.setHeight(this.iMaxSliderPositionY);
        if (this.getPosY() + this.getHeight() > CFG.GAME_HEIGHT) {
            this.setHeight(Math.max(CFG.GAME_HEIGHT - this.getPosY(), CFG.BUTTON_HEIGHT / 2));
        }
        int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        this.setHeight(Math.min(this.getHeight(), tempElemH * 6));
        this.updateMenuElements_IsInView();
    }
}

