/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Color;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Flag_Clip;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Flag_Clip_ProvinceID;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Title;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_ContinentPopulation
extends SliderMenu {
    protected static int iSort = 1;
    protected static int CONTINENT_ID = 0;

    protected Menu_InGame_ContinentPopulation(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
    }

    protected Menu_InGame_ContinentPopulation() {
        int i;
        int tAdd;
        int i2;
        int j;
        int i3;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
        int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 4 : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Civilization"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight){

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Population"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getElementW();
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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Provinces"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getElementW();
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("LargestCity"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getW() - Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2 - 2;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return iSort == 5 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
        });
        int tPosY = CFG.PADDING + tElemHeight;
        ArrayList<Integer> tPopulation = new ArrayList<Integer>();
        ArrayList<Integer> tCivilizations = new ArrayList<Integer>();
        ArrayList<Integer> tProvinces = new ArrayList<Integer>();
        ArrayList<Integer> tLargestCity = new ArrayList<Integer>();
        for (i3 = 0; i3 < CFG.game.getProvincesSize(); ++i3) {
            if (CFG.game.getProvince(i3).getContinent() != CONTINENT_ID || CFG.game.getProvince(i3).getWasteland() >= 0 || CFG.game.getProvince(i3).getSeaProvince()) continue;
            boolean tAdd2 = true;
            for (j = 0; j < tCivilizations.size(); ++j) {
                if (((Integer)tCivilizations.get(j)).intValue() != CFG.game.getProvince(i3).getCivID()) continue;
                tAdd2 = false;
                break;
            }
            if (!tAdd2) continue;
            tPopulation.add(0);
            tCivilizations.add(CFG.game.getProvince(i3).getCivID());
            tProvinces.add(0);
            tLargestCity.add(i3);
        }
        for (i3 = 0; i3 < CFG.game.getProvincesSize(); ++i3) {
            if (CFG.game.getProvince(i3).getWasteland() >= 0 || CFG.game.getProvince(i3).getSeaProvince() || CFG.game.getProvince(i3).getContinent() != CONTINENT_ID) continue;
            int tCivID = 0;
            for (j = 0; j < tCivilizations.size(); ++j) {
                if (((Integer)tCivilizations.get(j)).intValue() != CFG.game.getProvince(i3).getCivID()) continue;
                tCivID = j;
            }
            tPopulation.set(tCivID, (Integer)tPopulation.get(tCivID) + CFG.game.getProvince(i3).getPopulationData().getPopulation());
            tProvinces.set(tCivID, (Integer)tProvinces.get(tCivID) + 1);
            if (CFG.game.getProvince((Integer)tLargestCity.get(tCivID)).getPopulationData().getPopulation() >= CFG.game.getProvince(i3).getPopulationData().getPopulation()) continue;
            tLargestCity.set(tCivID, i3);
        }
        int tTotalPop = 0;
        boolean tCivsTotal = false;
        int tProvincesTotal = 0;
        int tLargestCityTotal = -1;
        for (int i4 = 0; i4 < tCivilizations.size(); ++i4) {
            tTotalPop += ((Integer)tPopulation.get(i4)).intValue();
            tProvincesTotal += ((Integer)tProvinces.get(i4)).intValue();
            if (tLargestCityTotal < 0) {
                if ((Integer)tLargestCity.get(i4) < 0) continue;
                tLargestCityTotal = (Integer)tLargestCity.get(i4);
                continue;
            }
            if ((Integer)tLargestCity.get(i4) < 0 || CFG.game.getProvince(tLargestCityTotal).getPopulationData().getPopulation() >= CFG.game.getProvince((Integer)tLargestCity.get(i4)).getPopulationData().getPopulation()) continue;
            tLargestCityTotal = (Integer)tLargestCity.get(i4);
        }
        menuElements.add(new Button_Statistics_Color(new Color(CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).r, CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).g, CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).b, 0.95f), "" + CFG.map.getMapContinents().getName(CONTINENT_ID), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2){

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 2;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
            }
        });
        menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + tTotalPop), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

            @Override
            protected int getPosX() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getElementW();
            }
        });
        menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + tProvincesTotal), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

            @Override
            protected int getPosX() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getElementW();
            }
        });
        menuElements.add(new Button_Statistics_Flag_Clip_ProvinceID(CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(tLargestCityTotal) ? tLargestCityTotal : -1) : tLargestCityTotal, CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(tLargestCityTotal) ? (CFG.game.getProvince(tLargestCityTotal).getCitiesSize() > 0 ? CFG.game.getProvince(tLargestCityTotal).getCity(0).getCityName() : (CFG.game.getProvince(tLargestCityTotal).getName().length() > 0 ? CFG.game.getProvince(tLargestCityTotal).getName() : CFG.langManager.get("NoData"))) : CFG.langManager.get("Undiscovered")) : (CFG.game.getProvince(tLargestCityTotal).getCitiesSize() > 0 ? CFG.game.getProvince(tLargestCityTotal).getCity(0).getCityName() : (CFG.game.getProvince(tLargestCityTotal).getName().length() > 0 ? CFG.game.getProvince(tLargestCityTotal).getName() : CFG.langManager.get("NoData"))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

            @Override
            protected int getPosX() {
                return Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_ContinentPopulation.this.getW() - Menu_InGame_ContinentPopulation.this.getElementW() * 4;
            }

            @Override
            protected void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    if (CFG.FOG_OF_WAR == 2) {
                        if (this.getCurrent() < 0) {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        } else {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                    } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
            }
        });
        tPosY += tElemHeight2;
        ArrayList tSorted = new ArrayList();
        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
        for (i2 = 0; i2 < tCivilizations.size(); ++i2) {
            tempIDs.add(i2);
        }
        if (iSort == 0) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if (!CFG.compareAlphabetic_TwoString(CFG.game.getCiv((Integer)tCivilizations.get((Integer)tempIDs.get(tAdd))).getCivName(), CFG.game.getCiv((Integer)tCivilizations.get((Integer)tempIDs.get(i))).getCivName())) continue;
                    tAdd = i;
                }
                tSorted.add(tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (iSort == 1) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if ((Integer)tPopulation.get((Integer)tempIDs.get(tAdd)) >= (Integer)tPopulation.get((Integer)tempIDs.get(i))) continue;
                    tAdd = i;
                }
                tSorted.add(tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (iSort == 2) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if ((Integer)tProvinces.get((Integer)tempIDs.get(tAdd)) >= (Integer)tProvinces.get((Integer)tempIDs.get(i))) continue;
                    tAdd = i;
                }
                tSorted.add(tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (iSort == 3) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if (CFG.game.getProvince((Integer)tLargestCity.get((Integer)tempIDs.get(tAdd))).getPopulationData().getPopulation() >= CFG.game.getProvince((Integer)tLargestCity.get((Integer)tempIDs.get(i))).getPopulationData().getPopulation()) continue;
                    tAdd = i;
                }
                tSorted.add(tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        }
        for (i2 = 0; i2 < tSorted.size(); ++i2) {
            menuElements.add(new Button_Statistics_Flag_Clip(CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tCivilizations.get((Integer)tSorted.get(i2))) ? (Integer)tCivilizations.get((Integer)tSorted.get(i2)) : -1, CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tCivilizations.get((Integer)tSorted.get(i2))) ? CFG.game.getCiv((Integer)tCivilizations.get((Integer)tSorted.get(i2))).getCivName() : CFG.langManager.get("Undiscovered"), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2){

                @Override
                protected int getWidth() {
                    return Menu_InGame_ContinentPopulation.this.getElementW() * 2;
                }

                @Override
                protected Color getColor(boolean isActive) {
                    return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + tPopulation.get((Integer)tSorted.get(i2))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_ContinentPopulation.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + tProvinces.get((Integer)tSorted.get(i2))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_ContinentPopulation.this.getElementW() * 3 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_ContinentPopulation.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics_Flag_Clip_ProvinceID(CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))) ? (Integer)tLargestCity.get((Integer)tSorted.get(i2)) : -1) : (Integer)tLargestCity.get((Integer)tSorted.get(i2)), CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))) ? (CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getCitiesSize() > 0 ? CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getCity(0).getCityName() : (CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getName().length() > 0 ? CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getName() : CFG.langManager.get("NoData"))) : CFG.langManager.get("Undiscovered")) : (CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getCitiesSize() > 0 ? CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getCity(0).getCityName() : (CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getName().length() > 0 ? CFG.game.getProvince((Integer)tLargestCity.get((Integer)tSorted.get(i2))).getName() : CFG.langManager.get("NoData"))), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_ContinentPopulation.this.getW() - Menu_InGame_ContinentPopulation.this.getElementW() * 4;
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        if (CFG.FOG_OF_WAR == 2) {
                            if (this.getCurrent() < 0) {
                                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                                nData.clear();
                            } else {
                                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                                nData.clear();
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
                                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                                nData.clear();
                            }
                        } else {
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                        }
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
            });
            tPosY += tElemHeight2;
        }
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle(CFG.map.getMapContinents().getName(CONTINENT_ID), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).r, CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).g, CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).b, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).r, CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).g, CFG.map.getMapContinents().getColor((int)Menu_InGame_ContinentPopulation.CONTINENT_ID).b, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(Images.population).draw(oSB, nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 - CFG.PADDING - ImageManager.getImage(Images.population).getWidth() + iTranslateX, 2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.population).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, tElemHeight2 * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        for (i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i / 4 % 2);
        }
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), true, true);
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
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_ContinentPopulation();
                }
                return;
            }
            case 1: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_ContinentPopulation();
                }
                return;
            }
            case 2: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_ContinentPopulation();
                }
                return;
            }
            case 3: {
                if (iSort != iID) {
                    iSort = iID;
                    CFG.menuManager.rebuildInGame_ContinentPopulation();
                }
                return;
            }
        }
        if (iID % 4 == 0) {
            if (iID / 4 == 1) {
                CFG.menuManager.rebuildInGame_WorldPopulation();
            } else if (this.getMenuElement(iID).getCurrent() > 0) {
                if (!CFG.menuManager.getVisible_InGame_CivInfo()) {
                    CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
                    CFG.setActiveCivInfo(this.getMenuElement(iID).getCurrent());
                    CFG.updateActiveCivInfo_InGame();
                    CFG.toast.setInView(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                } else {
                    CFG.setActiveCivInfo(this.getMenuElement(iID).getCurrent());
                    CFG.updateActiveCivInfo_InGame();
                    CFG.toast.setInView(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                }
            }
        } else if (iID % 4 == 3) {
            try {
                if (this.getMenuElement(iID).getCurrent() >= 0 && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getMenuElement(iID).getCurrent()))) {
                    CFG.game.setActiveProvinceID(this.getMenuElement(iID).getCurrent());
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                // empty catch block
            }
        }
    }

    protected final int getW() {
        return this.getWidth() - CFG.PADDING * 4;
    }

    protected final int getElementW() {
        return this.getW() / 5;
    }
}

