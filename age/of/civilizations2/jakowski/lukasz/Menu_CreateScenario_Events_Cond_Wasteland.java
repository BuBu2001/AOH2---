/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_SelectCivAction;
import age.of.civilizations2.jakowski.lukasz.Event_Type;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Events_Cond_Wasteland
extends SliderMenu {
    protected Menu_CreateScenario_Events_Cond_Wasteland() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH / 3, CFG.BUTTON_HEIGHT, true){

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get((int)CFG.eventsManager.iCreateEvent_EditConditionID).conditionType == Event_Type.AND ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, CFG.GAME_WIDTH / 3, tY, CFG.GAME_WIDTH / 3, CFG.BUTTON_HEIGHT, true){

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get((int)CFG.eventsManager.iCreateEvent_EditConditionID).conditionType == Event_Type.NOT ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Menu_LR_Line(null, -1, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 3, tY, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 3 * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get((int)CFG.eventsManager.iCreateEvent_EditConditionID).conditionType == Event_Type.OR ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : super.getColor(isActive);
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getValue() == 0){

            @Override
            protected boolean getCheckboxState() {
                return CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getValue() == 0;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(1).setText(CFG.langManager.get("AND"));
        this.getMenuElement(2).setText(CFG.langManager.get("NOT"));
        this.getMenuElement(3).setText(CFG.langManager.get("OR"));
        this.getMenuElement(4).setText(CFG.langManager.get("SelectProvinces") + ": " + CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvinces().size());
        this.getMenuElement(5).setText(CFG.langManager.get("IsWasteland"));
        this.getTitle().setText(CFG.langManager.get("IsWasteland"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get((int)CFG.eventsManager.iCreateEvent_EditConditionID).conditionType = Event_Type.AND;
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                break;
            }
            case 2: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get((int)CFG.eventsManager.iCreateEvent_EditConditionID).conditionType = Event_Type.NOT;
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                break;
            }
            case 3: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get((int)CFG.eventsManager.iCreateEvent_EditConditionID).conditionType = Event_Type.OR;
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                break;
            }
            case 4: {
                CFG.game.getSelectedProvinces().clearSelectedProvinces();
                for (int i = 0; i < CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvinces().size(); ++i) {
                    CFG.game.getSelectedProvinces().addProvince(CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvinces().get(i));
                }
                CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.SELECT_PROVINCES_WASTELAND;
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_PROVINCES);
                break;
            }
            case 5: {
                CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).setValue(CFG.eventsManager.lCreateScenario_Event.getTrigger((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(CFG.eventsManager.iCreateEvent_EditConditionID).getValue() == 0 ? 1 : 0);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
        CFG.menuManager.setBackAnimation(true);
    }
}

