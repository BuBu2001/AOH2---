/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_NewGame_Players
extends SliderMenu {
    protected Menu_NewGame_Players() {
        this.initMenu();
    }

    private void initMenu() {
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, this.buildMenuElements());
        this.updateLanguage();
    }

    protected List<MenuElement> buildMenuElements() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, CFG.game.getPlayersSize() < CFG.game.getAvailableCivilizations() && CFG.game.getPlayersSize() < CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID())));
        menuElements.add(new Button_Flag(CFG.game.getPlayer(0).getCivID(), 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.CIV_FLAG_WIDTH + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 4, CFG.BUTTON_HEIGHT, Button_Flag.ButtonFlagType.FLAG_COLOR));
        menuElements.add(new Button_Menu_Classic("P1 | " + CFG.langManager.get("Civilization") + ": " + (CFG.game.getPlayer(0).getCivID() < 0 ? CFG.langManager.get("RandomCivilization") : CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).getCivName()), CFG.PADDING * 2, CFG.PADDING * 4 + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.CIV_FLAG_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.PADDING * 4 - (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) - CFG.CIV_FLAG_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i = 1; i < CFG.game.getPlayersSize(); ++i) {
            menuElements.add(new Button_Flag(CFG.game.getPlayer(i).getCivID(), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.CIV_FLAG_WIDTH + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 4, CFG.BUTTON_HEIGHT, Button_Flag.ButtonFlagType.FLAG_COLOR));
            menuElements.add(new Button_Menu_Classic("P" + (i + 1) + " | " + CFG.langManager.get("Civilization") + ": " + (CFG.game.getPlayer(i).getCivID() < 0 ? CFG.langManager.get("RandomCivilization") : CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCivName()), CFG.PADDING * 2, CFG.PADDING * 4 + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.CIV_FLAG_WIDTH, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH / 2 - CFG.PADDING * 4 - (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) - CFG.CIV_FLAG_WIDTH, CFG.BUTTON_HEIGHT, true));
            menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        }
        return menuElements;
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("AddPlayer"));
        this.getTitle().setText(CFG.langManager.get("Players"));
    }

    @Override
    protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.game.addPlayer();
                CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).getCivID(), 0);
                this.initMenu();
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                CFG.menuManager.setViewID(Menu.eSELECT_CIVILIZATION);
                CFG.iSelectCivilizationPlayerID = 0;
                if (CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID() > 0) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID()).getCapitalProvinceID());
                } else {
                    CFG.game.setActiveProvinceID(-1);
                }
                return;
            }
        }
        switch (iID % 3) {
            case 0: {
                if (CFG.game.getPlayer((iID - 6) / 3 + 1).getCivID() > 0) {
                    CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer((iID - 6) / 3 + 1).getCivID());
                }
                CFG.game.removePlayer((iID - 6) / 3 + 1);
                this.initMenu();
                return;
            }
            case 1: {
                CFG.iSelectCivilizationPlayerID = (iID - 4) / 3 + 1;
                return;
            }
            case 2: {
                CFG.menuManager.setViewID(Menu.eSELECT_CIVILIZATION);
                CFG.iSelectCivilizationPlayerID = (iID - 5) / 3 + 1;
                if (CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID() > 0) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID()).getCapitalProvinceID());
                } else {
                    CFG.game.setActiveProvinceID(-1);
                }
                return;
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
        CFG.menuManager.setBackAnimation(true);
    }
}

