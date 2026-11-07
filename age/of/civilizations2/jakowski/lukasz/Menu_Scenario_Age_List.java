/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Age;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.Random;

class Menu_Scenario_Age_List
extends SliderMenu {
    protected Menu_Scenario_Age_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        for (int i = 0; i < CFG.gameAges.getAgesSize(); ++i) {
            menuElements.add(new Button_Menu_Age(CFG.gameAges.getYear(CFG.gameAges.getAge(i).getBeginningYear()) + " - " + CFG.gameAges.getYear(CFG.gameAges.getAge(i).getEndYear()), -1, 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_ReflectedBG(CFG.gameAges.getAge(i).getName(), CFG.PADDING * 5, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 5, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 5), menuElements);
        this.updateLanguage();
    }

    @Override
    protected boolean getMenuElementIsActive(boolean sliderMenuIsActive, int i) {
        return super.getMenuElementIsActive(sliderMenuIsActive, i) || i % 2 == 0 && i / 2 == CFG.CREATE_SCENARIO_AGE;
    }

    @Override
    protected final void actionElement(int iID) {
        if (CFG.CREATE_SCENARIO_AGE != iID / 2) {
            CFG.CREATE_SCENARIO_AGE = iID / 2;
            Game_Calendar.currentYear = Math.max(Math.abs(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getBeginningYear()), Math.abs(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getEndYear())) - Math.min(Math.abs(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getBeginningYear()), Math.abs(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getEndYear()));
            Random oR = new Random();
            Game_Calendar.currentYear = CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getBeginningYear() + oR.nextInt(Game_Calendar.currentYear);
            ArrayList<String> tMess = new ArrayList<String>();
            ArrayList<Color> tColor = new ArrayList<Color>();
            tMess.add(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getName());
            tColor.add(Color.WHITE);
            tMess.add(CFG.gameAges.getYear(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getBeginningYear()) + " - " + CFG.gameAges.getYear(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getEndYear()));
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
            CFG.menuManager.updateSelecetScenarioAge_Slider();
        }
    }
}

