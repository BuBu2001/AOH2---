/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConfigINI;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import java.util.ArrayList;
import java.util.List;

class Menu_Resolution
extends SliderMenu {
    private List<Point_XY> lResolution = new ArrayList<Point_XY>();

    protected Menu_Resolution() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        this.lResolution.clear();
        this.lResolution.add(new Point_XY(3840, 2160));
        this.lResolution.add(new Point_XY(3440, 1440));
        this.lResolution.add(new Point_XY(2560, 2048));
        this.lResolution.add(new Point_XY(2560, 1920));
        this.lResolution.add(new Point_XY(2560, 1600));
        this.lResolution.add(new Point_XY(2560, 1440));
        this.lResolution.add(new Point_XY(2560, 1080));
        this.lResolution.add(new Point_XY(2048, 1536));
        this.lResolution.add(new Point_XY(2048, 1152));
        this.lResolution.add(new Point_XY(1920, 1440));
        this.lResolution.add(new Point_XY(1920, 1200));
        this.lResolution.add(new Point_XY(1920, 1080));
        this.lResolution.add(new Point_XY(1856, 1392));
        this.lResolution.add(new Point_XY(1792, 1344));
        this.lResolution.add(new Point_XY(1680, 1050));
        this.lResolution.add(new Point_XY(1600, 1200));
        this.lResolution.add(new Point_XY(1600, 900));
        this.lResolution.add(new Point_XY(1536, 864));
        this.lResolution.add(new Point_XY(1440, 900));
        this.lResolution.add(new Point_XY(1400, 1050));
        this.lResolution.add(new Point_XY(1366, 768));
        this.lResolution.add(new Point_XY(1360, 768));
        this.lResolution.add(new Point_XY(1280, 1024));
        this.lResolution.add(new Point_XY(1280, 960));
        this.lResolution.add(new Point_XY(1280, 800));
        this.lResolution.add(new Point_XY(1280, 768));
        this.lResolution.add(new Point_XY(1280, 720));
        this.lResolution.add(new Point_XY(1024, 768));
        this.lResolution.add(new Point_XY(1024, 600));
        this.lResolution.add(new Point_XY(800, 600));
        for (int i = 0; i < this.lResolution.size(); ++i) {
            if (CFG.GAME_WIDTH == this.lResolution.get(i).getPosX() && CFG.GAME_HEIGHT == this.lResolution.get(i).getPosY()) {
                menuElements.add(new Button_Menu("" + this.lResolution.get(i).getPosX() + "x" + this.lResolution.get(i).getPosY(), (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true));
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                continue;
            }
            menuElements.add(new Button_Menu("" + this.lResolution.get(i).getPosX() + "x" + this.lResolution.get(i).getPosY(), (int)(50.0f * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Max"));
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == 0) {
            ConfigINI.iWidth = -1;
            ConfigINI.iHeight = -1;
        } else {
            ConfigINI.iWidth = this.lResolution.get(iID - 1).getPosX();
            ConfigINI.iHeight = this.lResolution.get(iID - 1).getPosY();
        }
        ConfigINI.saveConfig();
        CFG.menuManager.setViewID(Menu.eSETTINGS_GRAPHICS);
        CFG.menuManager.setBackAnimation(true);
        CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
        CFG.toast.setTimeInView(6000);
    }
}

