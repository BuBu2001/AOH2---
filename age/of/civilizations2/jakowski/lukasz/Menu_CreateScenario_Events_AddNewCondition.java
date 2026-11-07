/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Allies;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_AtWar;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_CivExist;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_ControlledByPlayer;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_ControlsProvinces;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_DecisionTaken;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_DefensivePact;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Development;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Development_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Economy;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Economy_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_EventChance;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Farm;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Fort;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Happiness;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Happiness_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_HaveArmy;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_HaveCore;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Ideology;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Independence;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_IsAVassal;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_IsAVassalOfCiv;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_IsAtWar;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_IsCapital;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_IsPartOfHRE;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_MilitaryAccess;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Neutral;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NonAggression;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfAllies;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfAllies_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfNeighbors;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfNeighbors_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfUnits;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfUnits_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfVassals;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfVassals_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfWars;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumOfWars_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumberOfProvinces;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_NumberOfProvinces_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_OccupyProvinces;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Population;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Population_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Port;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Relation;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Relation_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Technology;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Technology_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Treasury;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Treasury_Low;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_Wasteland;
import age.of.civilizations2.jakowski.lukasz.Event_Conditions_WatchTower;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import java.util.ArrayList;

class Menu_CreateScenario_Events_AddNewCondition
extends SliderMenu {
    protected Menu_CreateScenario_Events_AddNewCondition() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        for (int i = 0; i < 52; ++i) {
            menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("EventChance"));
        this.getMenuElement(2).setText(CFG.langManager.get("DecisionTaken"));
        this.getMenuElement(3).setText(CFG.langManager.get("CivilizationExist"));
        this.getMenuElement(4).setText(CFG.langManager.get("ControlsProvinces"));
        this.getMenuElement(5).setText(CFG.langManager.get("OccupiedProvinces"));
        this.getMenuElement(6).setText(CFG.langManager.get("HaveArmy"));
        this.getMenuElement(7).setText(CFG.langManager.get("HaveACore"));
        this.getMenuElement(8).setText(CFG.langManager.get("IsCapital"));
        this.getMenuElement(9).setText(CFG.langManager.get("NumberOfProvinces") + " >=");
        this.getMenuElement(10).setText(CFG.langManager.get("NumberOfProvinces") + " <");
        this.getMenuElement(11).setText(CFG.langManager.get("NumberOfUnits") + " >=");
        this.getMenuElement(12).setText(CFG.langManager.get("NumberOfUnits") + " <");
        this.getMenuElement(13).setText(CFG.langManager.get("NumberOfVassals") + " >=");
        this.getMenuElement(14).setText(CFG.langManager.get("NumberOfVassals") + " <");
        this.getMenuElement(15).setText(CFG.langManager.get("NumberOfAllies") + " >=");
        this.getMenuElement(16).setText(CFG.langManager.get("NumberOfAllies") + " <");
        this.getMenuElement(17).setText(CFG.langManager.get("NumberOfWars") + " >=");
        this.getMenuElement(18).setText(CFG.langManager.get("NumberOfWars") + " <");
        this.getMenuElement(19).setText(CFG.langManager.get("NumberOfNeighbors") + " >=");
        this.getMenuElement(20).setText(CFG.langManager.get("NumberOfNeighbors") + " <");
        this.getMenuElement(21).setText(CFG.langManager.get("Population") + " >=");
        this.getMenuElement(22).setText(CFG.langManager.get("Population") + " <");
        this.getMenuElement(23).setText(CFG.langManager.get("Economy") + " >=");
        this.getMenuElement(24).setText(CFG.langManager.get("Economy") + " <");
        this.getMenuElement(25).setText(CFG.langManager.get("Relation") + " >=");
        this.getMenuElement(26).setText(CFG.langManager.get("Relation") + " <");
        this.getMenuElement(27).setText(CFG.langManager.get("IaAtWar"));
        this.getMenuElement(28).setText(CFG.langManager.get("AtWar"));
        this.getMenuElement(29).setText(CFG.langManager.get("Allies"));
        this.getMenuElement(30).setText(CFG.langManager.get("NonAggressionPact"));
        this.getMenuElement(31).setText(CFG.langManager.get("DefensivePact"));
        this.getMenuElement(32).setText(CFG.langManager.get("GuaranteeIndependence"));
        this.getMenuElement(33).setText(CFG.langManager.get("MilitaryAccess"));
        this.getMenuElement(34).setText(CFG.langManager.get("IsAVassal"));
        this.getMenuElement(35).setText(CFG.langManager.get("IsAVassalOfCiv"));
        this.getMenuElement(36).setText(CFG.langManager.get("IsPartOfHRE"));
        this.getMenuElement(37).setText(CFG.langManager.get("Government"));
        this.getMenuElement(38).setText(CFG.langManager.get("TechnologyLevel") + " >=");
        this.getMenuElement(39).setText(CFG.langManager.get("TechnologyLevel") + " <");
        this.getMenuElement(40).setText(CFG.langManager.get("DevelopmentLevel") + " >=");
        this.getMenuElement(41).setText(CFG.langManager.get("DevelopmentLevel") + " <");
        this.getMenuElement(42).setText(CFG.langManager.get("Happiness") + " >=");
        this.getMenuElement(43).setText(CFG.langManager.get("Happiness") + " <");
        this.getMenuElement(44).setText(CFG.langManager.get("Treasury") + " >=");
        this.getMenuElement(45).setText(CFG.langManager.get("Treasury") + " <");
        this.getMenuElement(46).setText(CFG.langManager.get("CivIsControlledByAPlayer"));
        this.getMenuElement(47).setText(CFG.langManager.get("IsWasteland"));
        this.getMenuElement(48).setText(CFG.langManager.get("NeutralProvince"));
        this.getMenuElement(49).setText(CFG.langManager.get("Fort"));
        this.getMenuElement(50).setText(CFG.langManager.get("Port"));
        this.getMenuElement(51).setText(CFG.langManager.get("WatchTower"));
        this.getMenuElement(52).setText(CFG.langManager.get("Farm"));
        this.getTitle().setText(CFG.langManager.get("AddNewCondition"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_EventChance());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 2: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_DecisionTaken());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 3: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_CivExist());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 4: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_ControlsProvinces());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 5: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_OccupyProvinces());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 6: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_HaveArmy());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 7: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_HaveCore());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 8: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_IsCapital());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 9: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumberOfProvinces());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 10: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumberOfProvinces_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 11: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfUnits());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 12: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfUnits_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 13: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfVassals());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 14: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfVassals_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 15: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfAllies());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 16: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfAllies_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 17: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfWars());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 18: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfWars_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 19: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfNeighbors());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 20: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NumOfNeighbors_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 21: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Population());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 22: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Population_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 23: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Economy());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 24: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Economy_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 25: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Relation());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 26: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Relation_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 27: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_IsAtWar());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 28: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_AtWar());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 29: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Allies());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 30: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_NonAggression());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 31: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_DefensivePact());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 32: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Independence());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 33: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_MilitaryAccess());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 34: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_IsAVassal());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 35: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_IsAVassalOfCiv());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 36: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_IsPartOfHRE());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 37: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Ideology());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 38: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Technology());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 39: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Technology_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 40: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Development());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 41: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Development_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 42: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Happiness());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 43: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Happiness_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 44: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Treasury());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 45: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Treasury_Low());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 46: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_ControlledByPlayer());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 47: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Wasteland());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 48: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Neutral());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 49: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Fort());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 50: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Port());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 51: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_WatchTower());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
                break;
            }
            case 52: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.add(new Event_Conditions_Farm());
                CFG.eventsManager.iCreateEvent_EditConditionID = CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size() - 1;
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).editViewID();
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
        CFG.menuManager.setBackAnimation(true);
    }
}

