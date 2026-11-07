/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Statistics;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Alliance_Clip;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Clip;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Flag_Clip;
import age.of.civilizations2.jakowski.lukasz.Button_Statistics_Title;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_InGame_MilitaryAlliances
extends SliderMenu {
    protected static int sortBy = 1;
    private List<Integer> lSorted = new ArrayList<Integer>();

    protected Menu_InGame_MilitaryAlliances() {
        int i;
        int tAdd;
        int i2;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2 + CFG.CIV_INFO_MENU_WIDTH / 2;
        int tempHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 3 / 4;
        int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 4 : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Name"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight){

            @Override
            protected int getWidth() {
                return Menu_InGame_MilitaryAlliances.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
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
            protected Color getColor(boolean isActive) {
                return sortBy == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Members"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_MilitaryAlliances.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            protected Color getColor(boolean isActive) {
                return sortBy == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Provinces"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_MilitaryAlliances.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            protected Color getColor(boolean isActive) {
                return sortBy == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Population"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_MilitaryAlliances.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            protected Color getColor(boolean isActive) {
                return sortBy == 3 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Headquarters"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_MilitaryAlliances.this.getElementW() * 5 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            protected Color getColor(boolean isActive) {
                return sortBy == 4 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Formation"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight){

            @Override
            protected int getPosX() {
                return Menu_InGame_MilitaryAlliances.this.getElementW() * 6 + CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_MilitaryAlliances.this.getW() - Menu_InGame_MilitaryAlliances.this.getElementW() * 6 + CFG.PADDING * 2 - 2;
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
            protected Color getColor(boolean isActive) {
                return sortBy == 5 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
        });
        int tPosY = CFG.PADDING + tElemHeight;
        ArrayList<Integer> tProvinces = new ArrayList<Integer>();
        ArrayList<Integer> tPopulation = new ArrayList<Integer>();
        for (int i3 = 1; i3 < CFG.game.getAlliancesSize(); ++i3) {
            tProvinces.add(CFG.game.getAlliance(i3).countProvinces());
            tPopulation.add(CFG.game.getAlliance(i3).countPopulation());
        }
        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
        for (i2 = 1; i2 < CFG.game.getAlliancesSize(); ++i2) {
            tempIDs.add(i2);
        }
        if (sortBy == 0) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if (!CFG.compareAlphabetic_TwoString(CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getAllianceName(), CFG.game.getAlliance((Integer)tempIDs.get(i)).getAllianceName())) continue;
                    tAdd = i;
                }
                this.lSorted.add((Integer)tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (sortBy == 1) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if (CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getCivilizationsSize() >= CFG.game.getAlliance((Integer)tempIDs.get(i)).getCivilizationsSize()) continue;
                    tAdd = i;
                }
                this.lSorted.add((Integer)tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (sortBy == 2) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if ((Integer)tProvinces.get((Integer)tempIDs.get(tAdd) - 1) >= (Integer)tProvinces.get((Integer)tempIDs.get(i) - 1)) continue;
                    tAdd = i;
                }
                this.lSorted.add((Integer)tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (sortBy == 3) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if ((Integer)tPopulation.get((Integer)tempIDs.get(tAdd) - 1) >= (Integer)tPopulation.get((Integer)tempIDs.get(i) - 1)) continue;
                    tAdd = i;
                }
                this.lSorted.add((Integer)tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (sortBy == 4) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if (!CFG.compareAlphabetic_TwoString(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID()).getCity(0).getCityName() : (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID()).getName().length() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID()).getName() : CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getCivilization(0)).getCivName()), CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(i)).getCivilization(0)).getCapitalProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(i)).getCivilization(0)).getCapitalProvinceID()).getCity(0).getCityName() : (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(i)).getCivilization(0)).getCapitalProvinceID()).getName().length() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(i)).getCivilization(0)).getCapitalProvinceID()).getName() : CFG.game.getCiv(CFG.game.getAlliance((Integer)tempIDs.get(i)).getCivilization(0)).getCivName()))) continue;
                    tAdd = i;
                }
                this.lSorted.add((Integer)tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        } else if (sortBy == 5) {
            while (tempIDs.size() > 0) {
                tAdd = 0;
                for (i = 1; i < tempIDs.size(); ++i) {
                    if (CFG.game.getAlliance((Integer)tempIDs.get(tAdd)).getFormationTurnID() >= CFG.game.getAlliance((Integer)tempIDs.get(i)).getFormationTurnID()) continue;
                    tAdd = i;
                }
                this.lSorted.add((Integer)tempIDs.get(tAdd));
                tempIDs.remove(tAdd);
            }
        }
        for (i2 = 0; i2 < this.lSorted.size(); ++i2) {
            menuElements.add(new Button_Statistics_Alliance_Clip(CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(this.lSorted.get(i2)) ? this.lSorted.get(i2) : -1, CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(this.lSorted.get(i2)) ? CFG.game.getAlliance(this.lSorted.get(i2)).getAllianceName() : CFG.langManager.get("Undiscovered")) : CFG.game.getAlliance(this.lSorted.get(i2)).getAllianceName(), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2){

                @Override
                protected int getWidth() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW() * 2;
                }

                @Override
                protected Color getColor(boolean isActive) {
                    return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                }

                @Override
                protected int getSFX() {
                    return SoundsManager.SOUND_CLICK2;
                }
            });
            menuElements.add(new Button_Statistics("" + CFG.game.getAlliance(this.lSorted.get(i2)).getCivilizationsSize(), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW() * 2 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + tProvinces.get(this.lSorted.get(i2) - 1)), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW() * 3 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics(CFG.getNumberWithSpaces("" + tPopulation.get(this.lSorted.get(i2) - 1)), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW() * 4 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW();
                }
            });
            menuElements.add(new Button_Statistics_Flag_Clip(CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)) ? CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0) : -1) : CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0), CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()) ? (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getCity(0).getCityName() : (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getName().length() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getName() : CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCivName())) : CFG.langManager.get("Undiscovered")) : (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getCitiesSize() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getCity(0).getCityName() : (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getName().length() > 0 ? CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCapitalProvinceID()).getName() : CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(i2)).getCivilization(0)).getCivName())), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW() * 5 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW();
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
            });
            menuElements.add(new Button_Statistics_Clip("" + Game_Calendar.getDate_ByTurnID(CFG.game.getAlliance(this.lSorted.get(i2)).getFormationTurnID()), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, tPosY, CFG.BUTTON_WIDTH, tElemHeight2){

                @Override
                protected int getPosX() {
                    return Menu_InGame_MilitaryAlliances.this.getElementW() * 6 + CFG.PADDING * 2;
                }

                @Override
                protected int getWidth() {
                    return Menu_InGame_MilitaryAlliances.this.getW() - Menu_InGame_MilitaryAlliances.this.getElementW() * 6;
                }

                @Override
                protected void buildElementHover() {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Formation") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                }
            });
            tPosY += tElemHeight2;
        }
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("MilitaryAlliances"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.003921569f, 0.12941177f, 0.4117647f, 0.225f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.003921569f, 0.12941177f, 0.4117647f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(Images.diplo_alliance).draw(oSB, nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 - CFG.PADDING - ImageManager.getImage(Images.diplo_alliance).getWidth() + iTranslateX, 2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_alliance).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5, tempWidth, tempHeight, menuElements, false, true);
        this.updateLanguage();
        for (i2 = 0; i2 < this.getMenuElementsSize(); ++i2) {
            this.getMenuElement(i2).setCurrent(i2 / 6 % 2);
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
                if (sortBy != iID) {
                    sortBy = iID;
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
                return;
            }
            case 1: {
                if (sortBy != iID) {
                    sortBy = iID;
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
                return;
            }
            case 2: {
                if (sortBy != iID) {
                    sortBy = iID;
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
                return;
            }
            case 3: {
                if (sortBy != iID) {
                    sortBy = iID;
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
                return;
            }
            case 4: {
                if (sortBy != iID) {
                    sortBy = iID;
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
                return;
            }
            case 5: {
                if (sortBy != iID) {
                    sortBy = iID;
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
                return;
            }
        }
        if (iID % 6 == 0 || iID % 6 == 1) {
            CFG.menuManager.rebuildInGame_Alliance(this.lSorted.get(iID / 6 - 1));
        } else if (iID % 6 == 4 && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(iID / 6 - 1)).getCivilization(0)).getCapitalProvinceID()))) {
            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(iID / 6 - 1)).getCivilization(0)).getCapitalProvinceID());
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
        }
    }

    protected final int getW() {
        return this.getWidth() - CFG.PADDING * 4;
    }

    protected final int getElementW() {
        return this.getW() / 7;
    }
}

