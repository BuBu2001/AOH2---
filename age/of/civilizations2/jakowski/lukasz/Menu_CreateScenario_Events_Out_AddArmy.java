/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Event_SelectCivAction;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_BG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Events_Out_AddArmy
extends SliderMenu {
    protected Menu_CreateScenario_Events_Out_AddArmy() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Slider_BG(0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, 0, 100000, CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getValue()));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        try {
            this.getMenuElement(1).setText(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID() >= 0 ? CFG.langManager.get("Civilization") + ": " + CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID()).getCivName() : CFG.langManager.get("SelectCivilization"));
        }
        catch (IndexOutOfBoundsException ex) {
            this.getMenuElement(1).setText(CFG.langManager.get("SelectCivilization"));
        }
        this.getMenuElement(2).setText(CFG.langManager.get("SelectProvinces") + ": " + CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvinces().size());
        this.getMenuElement(3).setText(CFG.langManager.get("Army") + ": ");
        this.getTitle().setText(CFG.langManager.get("AddArmy"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        int tempButtonID = 1;
        try {
            CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID()).getFlag().draw(oSB, this.getMenuElement(tempButtonID).getPosX() + this.getMenuElement(tempButtonID).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, -CFG.game.getCiv(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getCivID()).getFlag().getHeight() + this.getMenuElement(tempButtonID).getPosY() + this.getMenuPosY() + this.getMenuElement(tempButtonID).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getMenuElement(tempButtonID).getPosX() + this.getMenuElement(tempButtonID).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(tempButtonID).getPosY() + this.getMenuPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getMenuElement(tempButtonID).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(tempButtonID).getPosX() + this.getMenuElement(tempButtonID).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(tempButtonID).getPosY() + this.getMenuPosY() + this.getMenuElement(tempButtonID).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
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
                CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTCIV_ADDARMY;
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
                break;
            }
            case 2: {
                CFG.game.getSelectedProvinces().clearSelectedProvinces();
                for (int i = 0; i < CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvinces().size(); ++i) {
                    CFG.game.getSelectedProvinces().addProvince(CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).getProvinces().get(i));
                }
                CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTPROVICNES_ADDARMY;
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_PROVINCES);
                break;
            }
            case 3: {
                CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).setValue(this.getMenuElement(iID).getCurrent());
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
        CFG.menuManager.setBackAnimation(true);
    }
}

