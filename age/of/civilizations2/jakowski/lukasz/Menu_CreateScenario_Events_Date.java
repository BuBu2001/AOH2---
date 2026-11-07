/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Slider_Age;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Events_Date
extends SliderMenu {
    protected Menu_CreateScenario_Events_Date() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        menuElements.add(new Text(null, -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? new Color(0.98f, 0.98f, 0.98f, 1.0f) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate_CreateEvent()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game("-", -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 4, true));
        menuElements.add(new Slider_Age(null, CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 4, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT, -350, 720, 125){

            @Override
            protected String getDrawText() {
                return this.getText() + CFG.gameAges.getYear(this.getCurrent());
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getYear(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getBeginningYear())));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getYear(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getEndYear())));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void updateSlider(int nX) {
                super.updateSlider(nX);
                this.menuElementHover = null;
            }
        });
        menuElements.add(new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 4, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 5, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Save"));
        this.getMenuElement(1).setText(CFG.eventsManager.iCreateEvent_Day + " " + Game_Calendar.getMonthName(CFG.eventsManager.iCreateEvent_Month));
        this.getMenuElement(2).setText(CFG.langManager.get("NoDate"));
        this.getMenuElement(4).setText(CFG.langManager.get("Year") + ": ");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawBG_WithGradient(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getMenuPosY() + this.getMenuElement(3).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, this.getMenuElement(3).getHeight() + CFG.PADDING * 2);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (CFG.eventsManager.setSinceDate) {
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventDay = CFG.eventsManager.iCreateEvent_Day;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventMonth = CFG.eventsManager.iCreateEvent_Month;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventYear = CFG.eventsManager.iCreateEvent_Year;
                } else {
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventDay = CFG.eventsManager.iCreateEvent_Day;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventMonth = CFG.eventsManager.iCreateEvent_Month;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventYear = CFG.eventsManager.iCreateEvent_Year;
                }
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.menuManager.setCreateScenario_Events_Calendar_Visible(!CFG.menuManager.getCreateScenario_Events_Calendar_Visible());
                return;
            }
            case 2: {
                if (CFG.eventsManager.setSinceDate) {
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventDay = 1;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventMonth = 1;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventYear = 9999999;
                } else {
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventDay = 1;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventMonth = 1;
                    CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventYear = 9999999;
                }
                this.onBackPressed();
                return;
            }
            case 3: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                CFG.eventsManager.iCreateEvent_Year = this.getMenuElement(iID + 1).getCurrent();
                ArrayList<String> tMess3 = new ArrayList<String>();
                ArrayList<Color> tColor3 = new ArrayList<Color>();
                tMess3.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
                tColor3.add(Color.WHITE);
                tMess3.add(Game_Calendar.getCurrentDate_CreateEvent());
                tColor3.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess3, tColor3);
                return;
            }
            case 4: {
                CFG.eventsManager.iCreateEvent_Year = this.getMenuElement(iID).getCurrent();
                ArrayList<String> tMess2 = new ArrayList<String>();
                ArrayList<Color> tColor2 = new ArrayList<Color>();
                tMess2.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
                tColor2.add(Color.WHITE);
                tMess2.add(Game_Calendar.getCurrentDate_CreateEvent());
                tColor2.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess2, tColor2);
                return;
            }
            case 5: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                CFG.eventsManager.iCreateEvent_Year = this.getMenuElement(iID - 1).getCurrent();
                ArrayList<String> tMess = new ArrayList<String>();
                ArrayList<Color> tColor = new ArrayList<Color>();
                tMess.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
                tColor.add(Color.WHITE);
                tMess.add(Game_Calendar.getCurrentDate_CreateEvent());
                tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess, tColor);
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS);
        CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
        CFG.menuManager.setBackAnimation(true);
    }
}

