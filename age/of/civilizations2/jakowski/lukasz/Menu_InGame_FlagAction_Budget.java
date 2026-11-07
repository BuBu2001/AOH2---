/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology_Vassal;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Goods;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Investments;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Military;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Taxes;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.TechnologyManager;
import age.of.civilizations2.jakowski.lukasz.Text_BudgetTitle;
import age.of.civilizations2.jakowski.lukasz.Text_EconomyTitle;
import age.of.civilizations2.jakowski.lukasz.Text_Economy_Balance;
import age.of.civilizations2.jakowski.lukasz.Text_Economy_SliderDesc_Research;
import age.of.civilizations2.jakowski.lukasz.Text_Economy_Total;
import age.of.civilizations2.jakowski.lukasz.Text_Economy_Value;
import age.of.civilizations2.jakowski.lukasz.Text_Investemnts_SliderDesc;
import age.of.civilizations2.jakowski.lukasz.Text_Investemnts_SliderDescGoods;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_FlagAction_Budget
extends SliderMenu {
    protected static final int MAX_SHOW = 9;

    protected static final MenuElement_Hover getIncomeTaxation() {
        int i;
        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
        ArrayList<Integer> tempIDs = new ArrayList<Integer>();
        ArrayList<Integer> tempIncome = new ArrayList<Integer>();
        for (int i2 = 0; i2 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); ++i2) {
            tempIDs.add(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2));
            tempIncome.add((int)CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)));
        }
        ArrayList sortedIDs = new ArrayList();
        ArrayList sortedIncome = new ArrayList();
        while (tempIDs.size() > 0) {
            int tBest = 0;
            for (i = 1; i < tempIDs.size(); ++i) {
                if ((Integer)tempIncome.get(tBest) >= (Integer)tempIncome.get(i)) continue;
                tBest = i;
            }
            sortedIDs.add(tempIDs.get(tBest));
            sortedIncome.add(tempIncome.get(tBest));
            tempIDs.remove(tBest);
            tempIncome.remove(tBest);
        }
        for (int i3 = 0; i3 < 9 && i3 < sortedIDs.size(); ++i3) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince((Integer)sortedIDs.get(i3)).getName() + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + sortedIncome.get(i3), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        if (sortedIDs.size() > 9) {
            int rest = 0;
            for (i = 9; i < sortedIDs.size(); ++i) {
                rest += ((Integer)sortedIncome.get(i)).intValue();
            }
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AndTheRest") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + rest, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
        }
        return new MenuElement_Hover_v2(nElements);
    }

    protected Menu_InGame_FlagAction_Budget() {
        boolean tempHeight = false;
        int tempWidth = 0;
        int tY = 0;
        tempWidth = CFG.isAndroid() && !CFG.LANDSCAPE ? CFG.GAME_WIDTH - CFG.PADDING * 4 : CFG.GAME_WIDTH / 2 - CFG.PADDING * 2;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        int tempBalance = 0;
        menuElements.add(new Text_EconomyTitle(CFG.langManager.get("Income"), -1, CFG.PADDING * 2, CFG.PADDING * 2, (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4));
        int tempValue = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iIncomeTaxation;
        menuElements.add(new Text_Economy_Value("" + tempValue, CFG.langManager.get("Taxation"), CFG.PADDING * 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation()), CFG.COLOR_TEXT_POPULATION));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%", CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness(), 100, 1.0f)));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempValue = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iIncomeProduction;
        menuElements.add(new Text_Economy_Value("" + tempValue, CFG.langManager.get("Production"), CFG.PADDING * 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countEconomy()), CFG.COLOR_TEXT_ECONOMY));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageDevelopment") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + (int)(CFG.game.countAvarageDevelopmentLevel_Float(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempValue = (int)CFG.game_NextTurnUpdate.getIncome_FromVassalsOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + (int)CFG.game_NextTurnUpdate.getIncome_Debuff_IsVassal(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + (int)CFG.game_NextTurnUpdate.getIncome_Buff_WarReparations(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + (int)CFG.game_NextTurnUpdate.getIncome_Debuff_WarReparations(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        menuElements.add(new Text_Economy_Value("" + tempValue, CFG.langManager.get("Others"), CFG.PADDING * 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

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
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getVassal_Tribute(i) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                for (i = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() + 1; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || CFG.game.getCiv(i).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(i).getCivName() + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getIncome_Vassals(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getVassal_Tribute(i) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
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
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID() != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Lord") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + (int)CFG.game_NextTurnUpdate.getIncome_Vassals(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()).getVassal_Tribute(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarReparationsGetsSize() > 0 || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarReparationsPaysSize() > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarReparations"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarReparationsGetsSize(); ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsGets((int)i).iFromCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsGets((int)i).iFromCivID).getCivName() + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getWarReparationsMoney(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsGets((int)i).iFromCivID), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.langManager.get("TurnsX", CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsGets((int)i).iTurnsLeft), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarReparationsPaysSize(); ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsPays((int)i).iFromCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsPays((int)i).iFromCivID).getCivName() + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + (int)CFG.game_NextTurnUpdate.getWarReparationsMoney(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.langManager.get("TurnsX", CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getWarReparationsPays((int)i).iTurnsLeft), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempBalance = tempValue = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        menuElements.add(new Text_Economy_Total("" + CFG.getNumberWithSpaces("" + tempValue), CFG.langManager.get("TotalIncome"), CFG.PADDING * 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3));
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Text_EconomyTitle(CFG.langManager.get("Expenses"), -1, CFG.PADDING * 2 + (tempWidth - CFG.PADDING * 4) / 2, CFG.PADDING * 2, (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4));
        tY = ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        tempValue = -CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iAdministrationCosts - (int)CFG.game_NextTurnUpdate.getInterestCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) - (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoans_GoldTotalPerTurn();
        menuElements.add(new Text_Economy_Value("" + Math.abs(tempValue), CFG.langManager.get("Administration"), CFG.PADDING * 2 + (tempWidth - CFG.PADDING * 4) / 2, tY, (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AdministrationCost") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iAdministrationCosts, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Inflation") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + (float)((int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0f)) / 100.0f + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getInterestCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), (int)CFG.game_NextTurnUpdate.getInterestCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                for (int i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize(); ++i) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Loan") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getLoan((int)i).iGoldPerTurn, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + CFG.langManager.get("TurnsX", CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getLoan((int)i).iTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempValue = -((int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        menuElements.add(new Text_Economy_Value("" + Math.abs(tempValue), CFG.langManager.get("Military"), CFG.PADDING * 2 + (tempWidth - CFG.PADDING * 4) / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                int nUpkeep = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + nUpkeep, nUpkeep == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)((float)nUpkeep / (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits() * 100.0f)) / 100.0f, CFG.COLOR_INGAME_GOLD));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerUnit")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0f)) / 100.0f + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BudgetSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game_NextTurnUpdate.getMilitarySpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempValue = -((int)CFG.game_NextTurnUpdate.getInvestments_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget)) - (int)CFG.game_NextTurnUpdate.getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget);
        menuElements.add(new Text_Economy_Value("" + Math.abs(tempValue), CFG.langManager.get("Spendings"), CFG.PADDING * 2 + (tempWidth - CFG.PADDING * 4) / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Goods") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget), (int)CFG.game_NextTurnUpdate.getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget), (int)CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Investments") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game_NextTurnUpdate.getInvestmentsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget), (int)CFG.game_NextTurnUpdate.getInvestmentsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempValue = -((int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        tempBalance += tempValue;
        menuElements.add(new Text_Economy_Total("" + CFG.getNumberWithSpaces("" + Math.abs(tempValue)), CFG.langManager.get("TotalExpenses"), CFG.PADDING * 2 + (tempWidth - CFG.PADDING * 4) / 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), (tempWidth - CFG.PADDING * 4) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3));
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tempValue = tempBalance;
        menuElements.add(new Text_Economy_Balance("" + CFG.getNumberWithSpaces("" + tempValue), CFG.langManager.get("Balance"), CFG.PADDING * 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempWidth - CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_GOLD;
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tempValue);
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        for (int i = 0; i < menuElements.size(); ++i) {
            ((MenuElement)menuElements.get(i)).setCurrent(i % 2);
        }
        float tFValue = CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        menuElements.add(new Slider_FlagAction_Taxes("" + (tFValue > 0.0f ? "+" : "") + tFValue, CFG.langManager.get("Taxes"), CFG.PADDING * 3, tY += CFG.PADDING * 2, tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, 100, (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTaxationLevel() * 100.0f)){

            @Override
            protected String getDrawText() {
                return this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return CFG.getColorStep(new Color(0.023529412f, 0.3254902f, 0.40392157f, 0.65f), new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65f), this.getCurrent(), 100, 0.65f);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_GOLD;
            }

            @Override
            protected boolean getClickable() {
                return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMax(tFValue >= 0.0f ? 0 : (tFValue <= -0.8f ? 2 : 1));
        menuElements.add(new Text_BudgetTitle(CFG.langManager.get("BudgetSpendings"), -1, 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempWidth - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Budget") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.langManager.get("TotalIncome") + " ", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("- " + CFG.langManager.get("AdministrationCost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Budget") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitarySpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game_NextTurnUpdate.getMilitarySpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GoodsSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Goods() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ResearchSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Research() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("InvestmentsSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Slider_FlagAction_Goods(CFG.langManager.get("Goods"), CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, 100, (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Goods() * 100.0f)){

            @Override
            protected String getDrawText() {
                return this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return this.getCurrent() < (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 100.0f) ? CFG.getColorStep(new Color(0.54901963f, 0.078431375f, 0.078431375f, 0.65f), new Color(0.7058824f, 0.078431375f, 0.078431375f, 0.65f), (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 100.0f) - this.getCurrent(), (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 100.0f), 0.65f) : CFG.getColorStep(new Color(0.019607844f, 0.39215687f, 0.1764706f, 0.65f), new Color(0.039215688f, 0.5686275f, 0.29411766f, 0.65f), this.getCurrent(), 100, 0.65f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return this.getCurrent() >= (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 100.0f) ? super.getColor(isActive) : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            }

            @Override
            protected boolean getClickable() {
                return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
        });
        menuElements.add(new Text_Investemnts_SliderDescGoods(CFG.langManager.get("AverageGrowthRate") + ": " + CFG.game.countAvarageGrowthRate(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + "%", CFG.langManager.get("Population") + ": ", CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation()), CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods2")));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods3", "" + (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 100.0f))));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("hGoods4")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BudgetSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Goods() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Spendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget)), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Slider_FlagAction(CFG.langManager.get("Research"), CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, 100, (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Research() * 100.0f), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= -500L){

            @Override
            protected String getDrawText() {
                return this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return CFG.getColorStep(new Color(0.07058824f, 0.18431373f, 0.3882353f, 0.65f), new Color(0.105882354f, 0.27450982f, 0.57254905f, 0.65f), this.getCurrent(), 100, 0.65f);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_TECHNOLOGY;
            }

            @Override
            protected boolean getClickable() {
                return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
        });
        menuElements.add(new Text_Economy_SliderDesc_Research(CFG.langManager.get("Progress") + ": ", "" + (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress(), " / " + CFG.getNumberWithSpaces("" + TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) + " ", "[" + CFG.getPercentage_Max100((int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress(), TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), 4) + "%]", CFG.langManager.get("TechnologyLevel") + ": " + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech1"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech2")));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f)) / 100.0f, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ResearchProgress") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress() + " / " + TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BudgetSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Research() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Spendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget)), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_TECHNOLOGY;
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setMin((int)(CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) * (1.0f + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getModifier_Research())));
        menuElements.add(new Slider_FlagAction_Investments(CFG.langManager.get("Investments"), CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, 100, (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments() * 100.0f)){

            @Override
            protected String getDrawText() {
                return this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return this.getCurrent() < (int)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).MIN_INVESTMENTS * 100.0f) ? CFG.getColorStep(new Color(0.54901963f, 0.078431375f, 0.078431375f, 0.65f), new Color(0.7058824f, 0.078431375f, 0.078431375f, 0.65f), (int)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).MIN_INVESTMENTS * 100.0f) - this.getCurrent(), (int)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).MIN_INVESTMENTS * 100.0f), 0.65f) : CFG.getColorStep(new Color(0.105882354f, 0.16078432f, 0.2901961f, 0.65f), new Color(0.20392157f, 0.2784314f, 0.45490196f, 0.65f), this.getCurrent(), 100, 0.65f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return this.getCurrent() >= (int)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).MIN_INVESTMENTS * 100.0f) ? super.getColor(isActive) : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
            }

            @Override
            protected boolean getClickable() {
                return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
        });
        menuElements.add(new Text_Investemnts_SliderDesc(CFG.langManager.get("AverageDevelopment") + ": " + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + " [" + (int)(CFG.game.countAvarageDevelopmentLevel_Float(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f) + "%]", CFG.langManager.get("Economy") + ": " + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countEconomy()), CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildYourEconomicPowerBySpendingGoldOnInvestments"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DevelopmentLevelAndEconomyWillBeIncreased")));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageDevelopment") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + (int)(CFG.game.countAvarageDevelopmentLevel_Float(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0f) + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, 0, 0));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech4"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech5"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BudgetSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments() * 100.0f) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Spendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getInvestmentsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget)), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Slider_FlagAction_Military(CFG.langManager.get("Military"), CFG.PADDING * 3, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempWidth - CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, 0, 100, CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget < 0 && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits() > 0 ? 100 : CFG.game_NextTurnUpdate.getMilitarySpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget)){

            @Override
            protected String getDrawText() {
                return this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(0.39215687f, 0.078431375f, 0.078431375f, 0.65f);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BudgetSpendings") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game_NextTurnUpdate.getMilitarySpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setClickable(false);
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        Menu_InGame_FlagAction_Bot.lTime = System.currentTimeMillis();
        menuElements.add(new Button_Transparent(0, 0, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, true));
        this.initMenu(new SliderMenuTitle(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.BUTTON_HEIGHT * 3 / 5, true, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1, true, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(Color.WHITE);
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.PADDING * 2 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 5, tempWidth, Math.min(((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + 1, CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 5) - CFG.PADDING * 2), menuElements, false, true);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_FlagAction_Bot.lTime + 225L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_FlagAction_Bot.lTime) / 225.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.25f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING - 2, true, false);
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 2, CFG.BUTTON_HEIGHT / 4);
            oSB.setColor(Color.WHITE);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            CFG.setRender_3(true);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.25f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING - 2, true, false);
            oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 2, CFG.BUTTON_HEIGHT / 4);
            oSB.setColor(Color.WHITE);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    private final void updateIncomeAndExpenses() {
        int tempBalance = 0;
        int tempValue = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iIncomeTaxation;
        this.getMenuElement(1).setText("" + tempValue);
        this.getMenuElement(1).setMax(tempValue);
        tempValue = CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iIncomeProduction;
        this.getMenuElement(2).setText("" + tempValue);
        this.getMenuElement(2).setMax(tempValue);
        tempValue = (int)CFG.game_NextTurnUpdate.getIncome_FromVassalsOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + (int)CFG.game_NextTurnUpdate.getIncome_Debuff_IsVassal(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + (int)CFG.game_NextTurnUpdate.getIncome_Debuff_WarReparations(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) + (int)CFG.game_NextTurnUpdate.getIncome_Buff_WarReparations(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        this.getMenuElement(3).setText("" + tempValue);
        this.getMenuElement(3).setMax(tempValue);
        tempBalance = tempValue = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        this.getMenuElement(4).setText("" + CFG.getNumberWithSpaces("" + Math.abs(tempValue)));
        this.getMenuElement(4).setMax(tempValue);
        tempValue = -CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iAdministrationCosts - (int)CFG.game_NextTurnUpdate.getInterestCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) - (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoans_GoldTotalPerTurn();
        this.getMenuElement(6).setText("" + Math.abs(tempValue));
        this.getMenuElement(6).setMax(tempValue);
        tempValue = -((int)CFG.game_NextTurnUpdate.getInvestments_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget)) - (int)CFG.game_NextTurnUpdate.getGoodsSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget);
        this.getMenuElement(8).setText("" + Math.abs(tempValue));
        this.getMenuElement(8).setMax(tempValue);
        tempValue = -((int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
        this.getMenuElement(9).setText("" + CFG.getNumberWithSpaces("" + Math.abs(tempValue)));
        this.getMenuElement(9).setMax(tempValue);
        this.getMenuElement(10).setText("" + CFG.getNumberWithSpaces("" + (tempBalance += tempValue)));
        this.getMenuElement(10).setMax(tempBalance);
        float tFValue = CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        if ((double)tFValue < 0.001 && (double)tFValue > -0.001) {
            tFValue = 0.0f;
        }
        Gdx.app.log("AoC", "" + tFValue);
        this.getMenuElement(11).setText("" + (tFValue > 0.0f ? "+" : "") + tFValue);
        this.getMenuElement(11).setMax(tFValue >= 0.0f ? 0 : (tFValue <= -0.8f ? 2 : 1));
        this.getMenuElement(19).setCurrent(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget < 0 && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits() > 0 ? 100 : CFG.game_NextTurnUpdate.getMilitarySpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget));
        this.getMenuElement(13).setCurrent((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Goods() * 100.0f));
        this.getMenuElement(15).setCurrent((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Research() * 100.0f));
        this.getMenuElement(16).setMin((int)(CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) * (1.0f + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getModifier_Research())));
        this.getMenuElement(17).setCurrent((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments() * 100.0f));
        Menu_InGame.updateOverBudget();
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                break;
            }
            case 3: {
                if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.lVassals.size() <= 0) break;
                CFG.menuManager.rebuildInGame_Tribute();
                CFG.toast.setInView(CFG.langManager.get("Vassals"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                break;
            }
            case 6: {
                CFG.toast.setInView(CFG.langManager.get("hAdministrationCost"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setTimeInView(6000);
                break;
            }
            case 11: {
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setTaxationLevel((float)this.getMenuElement(iID).getCurrent() / 100.0f);
                CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.game_NextTurnUpdate.updateSpendingsOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget);
                this.updateIncomeAndExpenses();
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_INCOME_MODE || !CFG.menuManager.getVisible_InGame_View_Stats()) break;
                CFG.menuManager.setVisible_InGame_ViewIncome(true);
                break;
            }
            case 12: {
                CFG.toast.setInView(CFG.langManager.get("Budget") + ": " + CFG.getNumberWithSpaces("" + CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setTimeInView(6000);
                break;
            }
            case 13: {
                if (this.getMenuElement(13).getCurrent() + this.getMenuElement(15).getCurrent() + this.getMenuElement(17).getCurrent() + this.getMenuElement(19).getCurrent() > 200) {
                    if (this.getMenuElement(15).getCurrent() + this.getMenuElement(17).getCurrent() > 0) {
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setSpendings_Goods((float)this.getMenuElement(13).getCurrent() / 100.0f);
                        CFG.game_NextTurnUpdate.updateSpendingsOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget);
                        this.getMenuElement(13).setCurrent((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Goods() * 100.0f));
                        this.getMenuElement(15).setCurrent((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Research() * 100.0f));
                        this.getMenuElement(17).setCurrent((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSpendings_Investments() * 100.0f));
                        if (this.getMenuElement(13).getCurrent() + this.getMenuElement(15).getCurrent() + this.getMenuElement(17).getCurrent() + this.getMenuElement(19).getCurrent() > 200) {
                            this.getMenuElement(13).setCurrent(200 - this.getMenuElement(19).getCurrent() - this.getMenuElement(15).getCurrent() - this.getMenuElement(17).getCurrent());
                        }
                    } else {
                        this.getMenuElement(13).setCurrent(200 - this.getMenuElement(19).getCurrent() - this.getMenuElement(15).getCurrent() - this.getMenuElement(17).getCurrent());
                    }
                }
                this.updateResearchAndIvestments();
                this.getMenuElement(13).setCurrent(this.getMenuElement(13).getCurrent());
                this.getMenuElement(17).setCurrent(this.getMenuElement(17).getCurrent());
                this.getMenuElement(16).setMin((int)(CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) * (1.0f + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getModifier_Research())));
                break;
            }
            case 15: {
                if (this.getMenuElement(15).getCurrent() + this.getMenuElement(19).getCurrent() + this.getMenuElement(13).getCurrent() > 200) {
                    this.getMenuElement(15).setCurrent(200 - this.getMenuElement(19).getCurrent() - this.getMenuElement(13).getCurrent());
                }
                if (this.getMenuElement(15).getCurrent() + this.getMenuElement(17).getCurrent() + this.getMenuElement(19).getCurrent() + this.getMenuElement(13).getCurrent() > 200) {
                    this.getMenuElement(17).setCurrent(200 - this.getMenuElement(15).getCurrent() - this.getMenuElement(19).getCurrent() - this.getMenuElement(13).getCurrent());
                }
                this.updateResearchAndIvestments();
                this.getMenuElement(17).setCurrent(this.getMenuElement(17).getCurrent());
                this.getMenuElement(16).setMin((int)(CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) * (1.0f + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getModifier_Research())));
                break;
            }
            case 17: {
                if (this.getMenuElement(17).getCurrent() + this.getMenuElement(19).getCurrent() + this.getMenuElement(13).getCurrent() > 200) {
                    this.getMenuElement(17).setCurrent(200 - this.getMenuElement(19).getCurrent() - this.getMenuElement(13).getCurrent());
                }
                if (this.getMenuElement(15).getCurrent() + this.getMenuElement(17).getCurrent() + this.getMenuElement(19).getCurrent() + this.getMenuElement(13).getCurrent() > 200) {
                    this.getMenuElement(15).setCurrent(200 - this.getMenuElement(17).getCurrent() - this.getMenuElement(19).getCurrent() - this.getMenuElement(13).getCurrent());
                }
                this.updateResearchAndIvestments();
                this.getMenuElement(17).setCurrent(this.getMenuElement(17).getCurrent());
                this.getMenuElement(16).setMin((int)(CFG.game_NextTurnUpdate.getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget) * (1.0f + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getModifier_Research())));
            }
        }
    }

    private final void updateResearchAndIvestments() {
        if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget <= 0) {
            this.getMenuElement(13).setCurrent(0);
            this.getMenuElement(15).setCurrent(0);
            this.getMenuElement(16).setMin(0);
            this.getMenuElement(17).setCurrent(0);
        }
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setSpendings_Goods((float)this.getMenuElement(13).getCurrent() / 100.0f);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setSpendings_Research((float)this.getMenuElement(15).getCurrent() / 100.0f);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setSpendings_Investments((float)this.getMenuElement(17).getCurrent() / 100.0f);
        this.updateIncomeAndExpenses();
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }
}

