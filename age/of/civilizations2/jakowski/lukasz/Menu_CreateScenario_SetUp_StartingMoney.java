/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_CreateScenario;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Slider;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_SetUp_StartingMoney
extends Menu_CreateScenario {
    private String sTopText;
    private int iStepWidth;

    protected Menu_CreateScenario_SetUp_StartingMoney() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Button_Game("-", -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Slider(CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT, -100000, 100000, 0){

            @Override
            protected String getDrawText() {
                return CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCivName() + this.getText() + this.getCurrent();
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(0.15686275f, 0.50980395f, 0.26666668f, 1.0f);
            }

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + CFG.CIV_FLAG_WIDTH + CFG.PADDING;
            }

            @Override
            protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (CFG.iCreateScenario_AssignProvinces_Civ >= 0) {
                    oSB.setColor(Color.WHITE);
                    CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                }
                CFG.drawTextWithShadow(oSB, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
            }
        });
        menuElements.add(new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.VIEW_SHOW_VALUES;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.getMenuElement(2).setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sTopText = CFG.langManager.get("StartingMoney");
        CFG.glyphLayout.setText(CFG.fontMain, this.sTopText);
        this.iStepWidth = (int)CFG.glyphLayout.width;
        this.getMenuElement(4).setText(": ");
        super.updateLanguage();
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
        this.getMenuElement(6).setText(CFG.langManager.get("ShowValues"));
        this.getMenuElement(7).setText(CFG.langManager.get("Reset"));
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(6).getWidth() - CFG.PADDING;
        this.getMenuElement(6).setPosX(tempX);
        tempX = tempX - this.getMenuElement(7).getWidth() - CFG.PADDING;
        this.getMenuElement(7).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawTextWithShadow(oSB, this.sTopText, CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX, CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY, Color.WHITE);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(7).getPosX() - CFG.PADDING + iTranslateX, this.getMenuElement(6).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(7).getPosX() - CFG.PADDING), this.getMenuElement(6).getHeight() + CFG.PADDING * 2);
        CFG.drawEditorTitle_Bot_Edge_LR(oSB, iTranslateX, this.getMenuElement(3).getPosY() - CFG.PADDING, CFG.GAME_WIDTH, this.getMenuElement(3).getHeight() + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 1: {
                this.onBackPressed();
                return;
            }
            case 3: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                    CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setMoney(this.getMenuElement(iID + 1).getCurrent());
                    CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID()).getArmy_Obj(0).updateArmyWidth((int)(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney()));
                }
                return;
            }
            case 4: {
                if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                    CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setMoney(this.getMenuElement(iID).getCurrent());
                    CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID()).getArmy_Obj(0).updateArmyWidth((int)(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney()));
                }
                return;
            }
            case 5: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                    CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setMoney(this.getMenuElement(iID - 1).getCurrent());
                    CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID()).getArmy_Obj(0).updateArmyWidth((int)(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney()));
                }
                return;
            }
            case 6: {
                boolean bl = CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
                if (CFG.VIEW_SHOW_VALUES) {
                    for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                        if (!CFG.game.getProvince(i).getIsCapital()) continue;
                        CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth((int)(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getMoney()));
                    }
                }
                return;
            }
            case 7: {
                if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                    CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
                    this.getMenuElement(4).setCurrent(CFG.game.getGameScenarios().getScenario_StartingMoney());
                    CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID()).getArmy_Obj(0).updateArmyWidth((int)(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney() == -999999L ? (long)CFG.game.getGameScenarios().getScenario_StartingMoney() : CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getMoney()));
                }
                return;
            }
        }
        super.actionElement(iID);
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
        if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
            CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
        }
        CFG.game.setActiveProvinceID(-1);
        Game_Render_Province.updateDrawProvinces();
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (!CFG.game.getProvince(i).getIsCapital()) continue;
            CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
        }
    }
}

