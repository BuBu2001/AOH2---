/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle_Animated;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_CallAlly;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_CallAlly_Right;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Title;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Title_Right;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_WarDetails;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_WarDetails_Right;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_WarDetails_WarResult;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_War_Casualties;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_War_Casualties_Right;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_PeaceTreaty;
import age.of.civilizations2.jakowski.lukasz.PeaceTreaty_Data;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text_AlliesNotInWar;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_WarDetails
extends SliderMenu {
    protected static int WAR_ID = 0;
    protected static int iSort = 0;
    protected String sDefender;
    protected String sWarDate;
    protected int iWarDateWidth;
    protected static final float FONT_SCALE = 0.55f;

    protected Menu_InGame_WarDetails(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2;
        this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
    }

    protected Menu_InGame_WarDetails() {
        int i;
        int j;
        int i2;
        int i3;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
        if (WAR_ID >= CFG.game.getWarsSize()) {
            WAR_ID = 0;
        }
        this.sWarDate = Game_Calendar.getNumOfDates_ByTurnID(CFG.game.getWar(WAR_ID).getWarTurnID());
        CFG.glyphLayout.setText(CFG.fontMain, this.sWarDate);
        this.iWarDateWidth = (int)(CFG.glyphLayout.width * 0.55f);
        menuElements.add(new Button_Statistics_WarDetails_WarResult(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID(), WAR_ID, 2, 0, tempWidth - 4){

            @Override
            protected int getWidth() {
                return Menu_InGame_WarDetails.this.getW();
            }
        });
        int tY = ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Aggressors"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected int getWidth() {
                return Menu_InGame_WarDetails.this.getElementW() * 4;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                if (iSort != 0) {
                    iSort = 0;
                    CFG.menuManager.rebuildInGame_WarDetails();
                }
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Casualties"), -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected int getPosX() {
                return Menu_InGame_WarDetails.this.getElementW() * 4 + 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_WarDetails.this.getElementW() + 2;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                if (iSort != 1) {
                    iSort = 1;
                    CFG.menuManager.rebuildInGame_WarDetails();
                }
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Casualties"), -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected int getPosX() {
                return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_WarDetails.this.getElementW() + 2;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                if (iSort != 1) {
                    iSort = 1;
                    CFG.menuManager.rebuildInGame_WarDetails();
                }
            }
        });
        menuElements.add(new Button_Statistics_Title_Right(CFG.langManager.get("Defenders"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected int getPosX() {
                return Menu_InGame_WarDetails.this.getElementW() * 6 + 6;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                if (iSort != 0) {
                    iSort = 0;
                    CFG.menuManager.rebuildInGame_WarDetails();
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        for (i3 = 0; i3 < CFG.game.getWar(WAR_ID).getAggressorsSize(); ++i3) {
            menuElements.add(new Button_Statistics_War_Casualties(CFG.game.getWar(WAR_ID).getAggressorID(i3).getCasualties() + CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivilianDeaths(), -1, tY, CFG.BUTTON_WIDTH * 2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_WarDetails.this.getElementW() * 4 + 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_WarDetails.this.getElementW() + 2;
                }
            });
            menuElements.add(new Button_Statistics_WarDetails(CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivID(), CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivilianDeaths(), CFG.game.getWar(WAR_ID).getAggressorID(i3).getEconomicLosses(), CFG.game.getWar(WAR_ID).getParticipation_AggressorID(i3), CFG.game.getWar(WAR_ID).getProvinces_Aggressor_OwnTotal(i3), CFG.game.getWar(WAR_ID).getProvinces_Aggressor_Own(i3), 2, tY, CFG.BUTTON_WIDTH * 2, !CFG.SPECTATOR_MODE && CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

                @Override
                protected int getWidth() {
                    return Menu_InGame_WarDetails.this.getElementW() * 4;
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        tY = ((MenuElement)menuElements.get(1)).getPosY() + ((MenuElement)menuElements.get(1)).getHeight();
        for (i3 = 0; i3 < CFG.game.getWar(WAR_ID).getDefendersSize(); ++i3) {
            menuElements.add(new Button_Statistics_War_Casualties_Right(CFG.game.getWar(WAR_ID).getDefenderID(i3).getCasualties() + CFG.game.getWar(WAR_ID).getDefenderID(i3).getCivilianDeaths(), -1, tY, CFG.BUTTON_WIDTH * 2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_WarDetails.this.getElementW() + 2;
                }
            });
            menuElements.add(new Button_Statistics_WarDetails_Right(CFG.game.getWar(WAR_ID).getDefenderID(i3).getCivID(), CFG.game.getWar(WAR_ID).getDefenderID(i3).getCivilianDeaths(), CFG.game.getWar(WAR_ID).getDefenderID(i3).getEconomicLosses(), CFG.game.getWar(WAR_ID).getParticipation_DefenderID(i3), CFG.game.getWar(WAR_ID).getProvinces_Defender_OwnTotal(i3), CFG.game.getWar(WAR_ID).getProvinces_Defender_Own(i3), CFG.PADDING * 2, tY, CFG.BUTTON_WIDTH * 2, !CFG.SPECTATOR_MODE && CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

                @Override
                protected int getPosX() {
                    return Menu_InGame_WarDetails.this.getElementW() * 6 + 6;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4;
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        this.sDefender = CFG.FOG_OF_WAR == 2 ? (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()) ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getAllianceName() : CFG.langManager.get("Undiscovered")) : (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()) ? CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName() : CFG.langManager.get("Undiscovered"))) : (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0 ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getAllianceName() : CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName());
        int tempMaxY = 0;
        int iSize = menuElements.size();
        for (int i4 = 0; i4 < iSize; ++i4) {
            if (((MenuElement)menuElements.get(i4)).getPosY() + ((MenuElement)menuElements.get(i4)).getHeight() <= tempMaxY) continue;
            tempMaxY = ((MenuElement)menuElements.get(i4)).getPosY() + ((MenuElement)menuElements.get(i4)).getHeight();
        }
        boolean addAlliesNotInWar = false;
        if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0) {
            for (i2 = 0; i2 < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilizationsSize(); ++i2) {
                if (CFG.game.getWar(WAR_ID).getIsInAggressors(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i2))) continue;
                addAlliesNotInWar = true;
                break;
            }
        }
        if (!addAlliesNotInWar && CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0) {
            for (i2 = 0; i2 < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilizationsSize(); ++i2) {
                if (CFG.game.getWar(WAR_ID).getIsInDefenders(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(i2))) continue;
                addAlliesNotInWar = true;
                break;
            }
        }
        if (!addAlliesNotInWar) {
            block5: for (i2 = 0; i2 < CFG.game.getWar(WAR_ID).getAggressorsSize(); ++i2) {
                if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(i2).getCivID()).getNumOfProvinces() <= 0) continue;
                for (j = 1; j < CFG.game.getCivsSize(); ++j) {
                    if (j == CFG.game.getWar(WAR_ID).getAggressorID(i2).getCivID() || CFG.game.getCiv(j).getPuppetOfCivID() != CFG.game.getWar(WAR_ID).getAggressorID(i2).getCivID() && CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(i2).getCivID()).getPuppetOfCivID() != j || CFG.game.getWar(WAR_ID).getIsAggressor(j) || CFG.game.getCivsAreAllied(j, CFG.game.getWar(WAR_ID).getAggressorID(i2).getCivID())) continue;
                    addAlliesNotInWar = true;
                    continue block5;
                }
            }
        }
        if (!addAlliesNotInWar) {
            block7: for (i2 = 0; i2 < CFG.game.getWar(WAR_ID).getDefendersSize(); ++i2) {
                if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(i2).getCivID()).getNumOfProvinces() <= 0) continue;
                for (j = 1; j < CFG.game.getCivsSize(); ++j) {
                    if (j == CFG.game.getWar(WAR_ID).getDefenderID(i2).getCivID() || CFG.game.getCiv(j).getPuppetOfCivID() != CFG.game.getWar(WAR_ID).getDefenderID(i2).getCivID() && CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(i2).getCivID()).getPuppetOfCivID() != j || CFG.game.getWar(WAR_ID).getIsDefender(j) || CFG.game.getCivsAreAllied(j, CFG.game.getWar(WAR_ID).getDefenderID(i2).getCivID())) continue;
                    addAlliesNotInWar = true;
                    continue block7;
                }
            }
        }
        if (addAlliesNotInWar) {
            int j2;
            tY = tempMaxY + CFG.PADDING * 2;
            menuElements.add(new Text_AlliesNotInWar(CFG.langManager.get("AlliesNotInWar"), -1, CFG.PADDING, tY, tempWidth - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3){

                @Override
                protected int getPosX() {
                    return 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_WarDetails.this.getW();
                }
            });
            tempMaxY = tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
            int tempAdded = 0;
            if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0) {
                for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilizationsSize(); ++i) {
                    if (CFG.game.getWar(WAR_ID).getIsInAggressors(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i))) continue;
                    menuElements.add(new Button_Statistics_CallAlly(CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i)) ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i) : -1, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

                        @Override
                        protected int getWidth() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                        }

                        @Override
                        protected void actionElement(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.game.getWarsSize()) {
                                if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    CFG.menuManager.rebuildInGame_JoinAWar(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                                }
                            }
                        }
                    });
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(tempAdded++ % 2);
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) || CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i) == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            for (i = 0; i < CFG.game.getWar(WAR_ID).getAggressorsSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(i).getCivID()).getNumOfProvinces() <= 0) continue;
                for (j2 = 1; j2 < CFG.game.getCivsSize(); ++j2) {
                    if (j2 == CFG.game.getWar(WAR_ID).getAggressorID(i).getCivID() || CFG.game.getCiv(j2).getPuppetOfCivID() != CFG.game.getWar(WAR_ID).getAggressorID(i).getCivID() || CFG.game.getCivsAreAllied(j2, CFG.game.getWar(WAR_ID).getAggressorID(i).getCivID()) || CFG.game.getWar(WAR_ID).getIsInAggressors(j2)) continue;
                    menuElements.add(new Button_Statistics_CallAlly(CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(j2) ? j2 : -1, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

                        @Override
                        protected int getWidth() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                        }

                        @Override
                        protected void actionElement(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.game.getWarsSize()) {
                                if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    CFG.menuManager.rebuildInGame_JoinAWar(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID());
                                } else if (CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                                }
                            }
                        }
                    });
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(tempAdded++ % 2);
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) || j2 == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            tempAdded = 0;
            tY = tempMaxY;
            if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0) {
                for (i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilizationsSize(); ++i) {
                    if (CFG.game.getWar(WAR_ID).getIsInDefenders(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(i))) continue;
                    menuElements.add(new Button_Statistics_CallAlly_Right(CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(i)) ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(i) : -1, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

                        @Override
                        protected int getPosX() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                        }

                        @Override
                        protected int getWidth() {
                            return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4 + Menu_InGame_WarDetails.this.getElementW() + 2;
                        }

                        @Override
                        protected void actionElement(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.game.getWarsSize()) {
                                if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    CFG.menuManager.rebuildInGame_JoinAWar(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID(), CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID());
                                } else if (CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                                } else if (CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID());
                                }
                            }
                        }
                    });
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(tempAdded++ % 2);
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) || CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(i) == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
            for (i = 0; i < CFG.game.getWar(WAR_ID).getDefendersSize(); ++i) {
                if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(i).getCivID()).getNumOfProvinces() <= 0) continue;
                for (j2 = 1; j2 < CFG.game.getCivsSize(); ++j2) {
                    if (j2 == CFG.game.getWar(WAR_ID).getDefenderID(i).getCivID() || CFG.game.getCiv(j2).getPuppetOfCivID() != CFG.game.getWar(WAR_ID).getDefenderID(i).getCivID() || CFG.game.getCivsAreAllied(j2, CFG.game.getWar(WAR_ID).getDefenderID(i).getCivID()) || CFG.game.getWar(WAR_ID).getIsInDefenders(j2)) continue;
                    menuElements.add(new Button_Statistics_CallAlly_Right(CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(j2) ? j2 : -1, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())){

                        @Override
                        protected int getPosX() {
                            return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                        }

                        @Override
                        protected int getWidth() {
                            return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4 + Menu_InGame_WarDetails.this.getElementW() + 2;
                        }

                        @Override
                        protected void actionElement(int iID) {
                            if (WAR_ID >= 0 && WAR_ID < CFG.game.getWarsSize()) {
                                if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    CFG.menuManager.rebuildInGame_JoinAWar(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID(), CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID());
                                } else if (CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                                } else if (CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID());
                                }
                            }
                        }
                    });
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(tempAdded++ % 2);
                    ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) || j2 == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                }
            }
        }
        tempMaxY = 0;
        int iSize2 = menuElements.size();
        for (i2 = 0; i2 < iSize2; ++i2) {
            if (((MenuElement)menuElements.get(i2)).getPosY() + ((MenuElement)menuElements.get(i2)).getHeight() <= tempMaxY) continue;
            tempMaxY = ((MenuElement)menuElements.get(i2)).getPosY() + ((MenuElement)menuElements.get(i2)).getHeight();
        }
        if (CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) || CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            menuElements.add(new Button_FlagActionSliderStyle_Animated(CFG.langManager.get("PeaceNegotiations"), -1, 2, tempMaxY += CFG.PADDING, CFG.BUTTON_WIDTH, true){

                @Override
                protected int getPosX() {
                    return 2 + CFG.PADDING;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_WarDetails.this.getW() - CFG.PADDING * 2;
                }

                @Override
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    ImageManager.getImage(Images.diplo_truce).draw(oSB, this.getPosX() + this.getWidth() / 2 - CFG.PADDING - (int)((float)ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)) / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)ImageManager.getImage(Images.diplo_truce).getHeight() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)) / 2 - ImageManager.getImage(Images.diplo_truce).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)), (int)((float)ImageManager.getImage(Images.diplo_truce).getHeight() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)));
                    CFG.fontMain.getData().setScale(0.8f);
                    CFG.drawText(oSB, this.getText(), this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)(((float)this.getTextWidth() * 0.8f + (float)((int)((float)ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce))) + (float)CFG.PADDING) / 2.0f) + (int)((float)ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)) + CFG.PADDING : this.getTextPos()) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, this.getColor(isActive));
                    CFG.fontMain.getData().setScale(1.0f);
                }

                @Override
                protected void actionElement(int iID) {
                    if (CFG.SPECTATOR_MODE) {
                        return;
                    }
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    Menu_PeaceTreaty.WAR_ID = WAR_ID;
                    CFG.peaceTreatyData = new PeaceTreaty_Data(Menu_PeaceTreaty.WAR_ID, CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    CFG.game.resetChooseProvinceData_Immediately();
                    CFG.game.resetRegroupArmyData();
                    CFG.menuManager.setViewID(Menu.eINGAME_PEACE_TREATY);
                }

                @Override
                protected int getSFX() {
                    return SoundsManager.SOUND_CLICK2;
                }

                @Override
                protected void buildElementHover() {
                    int i;
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PeaceNegotiations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    for (i = 0; i < CFG.game.getWar(WAR_ID).getAggressorsSize() && i < 5; ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(WAR_ID).getAggressorID(i).getCivID(), i == 0 ? CFG.PADDING : 0, 0));
                    }
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
                    for (i = 0; i < CFG.game.getWar(WAR_ID).getDefendersSize() && i < 5; ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(WAR_ID).getDefenderID(i).getCivID(), i == 0 ? CFG.PADDING : 0, 0));
                    }
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            tempMaxY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        menuElements.add(new Button_Transparent(0, 0, tempWidth, tempMaxY, true){

            @Override
            protected int getPosX() {
                return 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_WarDetails.this.getW();
            }
        });
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2;
        this.initMenu(new SliderMenuTitle(CFG.FOG_OF_WAR == 2 ? (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()) ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getAllianceName() : CFG.langManager.get("Undiscovered")) : (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()) ? CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName() : CFG.langManager.get("Undiscovered"))) : (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0 ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getAllianceName() : CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName()), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.5411765f, 0.050980393f, 0.050980393f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.5411765f, 0.050980393f, 0.050980393f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(Images.diplo_rivals).draw(oSB, nPosX + nWidth / 2 - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX, 2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_rivals).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 - CFG.PADDING + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.7f) / 2, Color.WHITE);
                CFG.drawText(oSB, Menu_InGame_WarDetails.this.sDefender, nPosX + nWidth / 2 + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + CFG.PADDING + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.7f) / 2, Color.WHITE);
                ImageManager.getImage(Images.time).draw(oSB, nPosX + nWidth - CFG.PADDING - 2 - (int)((float)ImageManager.getImage(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)) + iTranslateX, nPosY - CFG.PADDING - (int)((float)ImageManager.getImage(Images.time).getHeight() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)) - ImageManager.getImage(Images.time).getHeight(), (int)((float)ImageManager.getImage(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)), (int)((float)ImageManager.getImage(Images.time).getHeight() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)));
                CFG.fontMain.getData().setScale(0.55f);
                CFG.drawText(oSB, Menu_InGame_WarDetails.this.sWarDate, nPosX + nWidth - Menu_InGame_WarDetails.this.iWarDateWidth - CFG.PADDING * 2 - (int)((float)ImageManager.getImage(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)) - 2 + iTranslateX, nPosY - CFG.PADDING - (int)((float)CFG.TEXT_HEIGHT * 0.55f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth * 3 / 4, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        for (i = 0; i < this.getMenuElementsSize() && i < CFG.game.getWar(WAR_ID).getAggressorsSize() * 2; ++i) {
            this.getMenuElement(i).setCurrent(i / 2 % 2);
        }
        for (i = 4 + CFG.game.getWar(WAR_ID).getAggressorsSize() * 2; i < this.getMenuElementsSize() && i < 4 + CFG.game.getWar(WAR_ID).getAggressorsSize() * 2 + CFG.game.getWar(WAR_ID).getDefendersSize(); ++i) {
            this.getMenuElement(i).setCurrent((i / 2 + (CFG.game.getWar(WAR_ID).getAggressorsSize() + 1) % 2) % 2);
        }
    }

    @Override
    protected void updateLanguage() {
    }

    private final float getImageScale3(int nImageID) {
        return (float)CFG.TEXT_HEIGHT * 1.0f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT * 1.0f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    private final float getImageScale2(int nImageID) {
        return (float)CFG.TEXT_HEIGHT * 0.55f / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT * 0.55f / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    private final void clickFlag(int iID) {
        try {
            CFG.toast.setInView(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            if (CFG.FOG_OF_WAR == 2) {
                if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(this.getMenuElement(iID).getCurrent()) && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID())) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                }
            } else {
                CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                CFG.game.disableDrawCivilizationRegions_Active();
                CFG.game.enableDrawCivilizationRegions_ActiveProvince();
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() - 1 + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getMenuElement(0).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getMenuElement(0).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.getCloseButtonImage(sliderMenuIsActive).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX, this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5, ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5);
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            return;
        }
        this.getMenuElement(iID).actionElement(iID);
    }

    protected final int getW() {
        return this.getWidth() - 4;
    }

    protected final int getElementW() {
        return this.getW() / 10;
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                this.getMenuElement(i).setVisible(false);
            }
        }
    }
}

