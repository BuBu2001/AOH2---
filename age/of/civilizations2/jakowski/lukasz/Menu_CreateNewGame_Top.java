/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_CreateNewGameScenario;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Game_Scenarios;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateNewGame_Top
extends SliderMenu {
    protected static final float SCALE_OF_CLICK_TEXT = 0.6f;
    private String sClickOnTheMap;
    private int iClickOnTheMapWidth;
    protected static int iBGWidth;
    protected static float fMovePercentage;
    private long lTime;

    protected Menu_CreateNewGame_Top() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text(null, CFG.GAME_WIDTH / 2, CFG.PADDING){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.9f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.9f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.9f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void setWidth(int iWidth) {
                if (iWidth < CFG.BUTTON_WIDTH) {
                    iWidth = CFG.BUTTON_WIDTH;
                }
                super.setWidth(iWidth);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(CFG.game.getScenarioID())).getName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioAuthor(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text(null, CFG.GAME_WIDTH / 2, CFG.PADDING){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.6f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.63f, 0.63f, 0.63f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.6f, 0.6f, 0.6f, 1.0f) : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(CFG.game.getScenarioID())).getName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioAuthor(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawText(oSB, this.sText, this.getPosX() + this.getWidth() - (int)((float)this.getTextWidth() * 0.6f) - CFG.PADDING * 2 + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }

            @Override
            protected int getWidth() {
                return (int)((float)this.getTextWidth() * 0.6f) + CFG.PADDING * 2 + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.dice).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + (this.getHeight() - ImageManager.getImage(Images.dice).getHeight()) / 2 + iTranslateY);
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawText(oSB, this.sText, this.getPosX() + CFG.PADDING * 3 + ImageManager.getImage(Images.dice).getWidth() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : CFG.COLOR_TEXT_MODIFIER_NEUTRAL) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected int getPosX() {
                return 0;
            }

            @Override
            protected int getWidth() {
                return (int)((float)this.getTextWidth() * 0.6f) + CFG.PADDING + ImageManager.getImage(Images.dice).getWidth() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_left2).getWidth();
            }
        });
        menuElements.add(new Button_CreateNewGameScenario("<<", -1, 0, CFG.PADDING * 2, ImageManager.getImage(Images.new_game_top).getHeight() - CFG.PADDING){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                int nScenarioID = CFG.game.getScenarioID() + 1;
                CFG.game.getGameScenarios();
                if (nScenarioID > Game_Scenarios.SCENARIOS_SIZE - 1) {
                    nScenarioID = 0;
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(nScenarioID)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioDay(nScenarioID) + " " + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(nScenarioID)) + " " + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(nScenarioID)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(nScenarioID)).getName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getNumOfCivs(nScenarioID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioAuthor(nScenarioID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_CreateNewGameScenario(">>", -1, 0, CFG.PADDING * 2, ImageManager.getImage(Images.new_game_top).getHeight() - CFG.PADDING){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                int nScenarioID = CFG.game.getScenarioID() - 1;
                if (nScenarioID < 0) {
                    CFG.game.getGameScenarios();
                    nScenarioID = Game_Scenarios.SCENARIOS_SIZE - 1;
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(nScenarioID)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioDay(nScenarioID) + " " + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(nScenarioID)) + " " + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(nScenarioID)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(nScenarioID)).getName()));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getNumOfCivs(nScenarioID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getGameScenarios().getScenarioAuthor(nScenarioID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sClickOnTheMap = CFG.langManager.get("ClickOnTheMap");
        CFG.fontMain.getData().setScale(0.6f);
        CFG.glyphLayout.setText(CFG.fontMain, this.sClickOnTheMap);
        this.iClickOnTheMapWidth = (int)CFG.glyphLayout.width;
        CFG.fontMain.getData().setScale(1.0f);
        int tNumOfCivs = 0;
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
            ++tNumOfCivs;
        }
        this.getMenuElement(0).setText(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())));
        this.getMenuElement(1).setText("" + tNumOfCivs + " " + CFG.langManager.get("Civilizations") + " | " + CFG.langManager.get("Year") + ": " + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(CFG.game.getScenarioID())));
        this.getMenuElement(0).setPosY(CFG.PADDING + ImageManager.getImage(Images.new_game_top).getHeight() / 2 - ((int)((float)this.getMenuElement(0).getTextHeight() * 0.9f + (float)this.getMenuElement(1).getTextHeight() * 0.6f) + CFG.PADDING * 3) / 2);
        this.getMenuElement(0).setHeight((int)((float)this.getMenuElement(0).getTextHeight() * 0.9f) + CFG.PADDING * 2);
        this.getMenuElement(1).setPosY(CFG.PADDING + ImageManager.getImage(Images.new_game_top).getHeight() / 2 - ((int)((float)this.getMenuElement(0).getTextHeight() * 0.9f + (float)this.getMenuElement(1).getTextHeight() * 0.6f) + CFG.PADDING * 3) / 2 + this.getMenuElement(0).getHeight());
        this.getMenuElement(1).setHeight((int)((float)this.getMenuElement(1).getTextHeight() * 0.6f + (float)CFG.PADDING));
        iBGWidth = this.getMenuElement(0).getTextWidth() > this.iClickOnTheMapWidth - CFG.PADDING * 4 ? this.getMenuElement(0).getTextWidth() + CFG.PADDING * 6 : this.iClickOnTheMapWidth + CFG.PADDING * 4;
        iBGWidth = (int)((float)this.getMenuElement(1).getTextWidth() * 0.6f > (float)iBGWidth ? (float)this.getMenuElement(1).getTextWidth() * 0.6f + (float)(CFG.PADDING * 6) : (float)iBGWidth);
        this.getMenuElement(0).setWidth(iBGWidth);
        this.getMenuElement(1).setWidth(iBGWidth);
        this.getMenuElement(0).setPosX(CFG.GAME_WIDTH / 2 - this.getMenuElement(0).getWidth() / 2);
        this.getMenuElement(1).setPosX(CFG.GAME_WIDTH / 2 - this.getMenuElement(1).getWidth() / 2);
        this.lTime = System.currentTimeMillis();
        fMovePercentage = 5.0f;
        this.getMenuElement(2).setText(CFG.langManager.get("MapModes"));
        this.getMenuElement(3).setText(CFG.langManager.get("RandomCivilization"));
        this.getMenuElement(4).setPosX(this.getMenuElement(0).getPosX() - this.getMenuElement(4).getWidth() - CFG.PADDING * 2 - CFG.PADDING / 2);
        this.getMenuElement(5).setPosX(this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + CFG.PADDING / 2);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((fMovePercentage += (float)(System.currentTimeMillis() - this.lTime) / 300.0f * 95.0f) > 100.0f) {
            fMovePercentage = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        this.lTime = System.currentTimeMillis();
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(2).getPosX() + iTranslateX, this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + (iTranslateY -= (int)((float)this.getHeight() * (100.0f - fMovePercentage) / 100.0f)), this.getMenuElement(2).getWidth(), ImageManager.getImage(Images.top_left2_sha).getHeight(), false, false);
        ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(2).getPosX() + iTranslateX, this.getMenuElement(2).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, this.getMenuElement(2).getWidth(), ImageManager.getImage(Images.top_left2).getHeight(), false, false);
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(3).getPosX() + iTranslateX, this.getMenuElement(3).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, this.getMenuElement(3).getWidth(), ImageManager.getImage(Images.top_left2_sha).getHeight(), true, false);
        ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(3).getPosX() + iTranslateX, this.getMenuElement(3).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, this.getMenuElement(3).getWidth(), ImageManager.getImage(Images.top_left2).getHeight(), true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight(), CFG.GAME_WIDTH, CFG.PADDING * 3);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top).draw(oSB, CFG.GAME_WIDTH / 2 - iBGWidth / 2 - CFG.PADDING * 2 + iTranslateX, CFG.PADDING + iTranslateY, iBGWidth + CFG.PADDING * 4 - ImageManager.getImage(Images.new_game_top).getWidth());
        ImageManager.getImage(Images.new_game_top).draw(oSB, CFG.GAME_WIDTH / 2 + iBGWidth / 2 + CFG.PADDING * 2 - ImageManager.getImage(Images.new_game_top).getWidth() + iTranslateX, CFG.PADDING + iTranslateY, true);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, this.getMenuElement(0).getIsHovered() || this.getMenuElement(1).getIsHovered() ? 0.375f : 0.3f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, CFG.GAME_WIDTH / 2 - iBGWidth / 2 - CFG.PADDING * 2 + iTranslateX, CFG.PADDING + 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, iBGWidth + CFG.PADDING * 4, ImageManager.getImage(Images.new_game_top).getHeight() - 4);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.fontMain.getData().setScale(0.6f);
        CFG.drawText(oSB, this.sClickOnTheMap, CFG.GAME_WIDTH / 2 - this.iClickOnTheMapWidth / 2 + iTranslateX, CFG.PADDING * 2 + ImageManager.getImage(Images.new_game_top).getHeight() + iTranslateY, new Color(1.0f, 1.0f, 1.0f, 0.4f));
        CFG.fontMain.getData().setScale(1.0f);
    }

    protected static final void clickChooseScenario() {
        CFG.game.disableDrawCivlizationsRegions_Players();
        CFG.menuManager.setViewID(Menu.eCHOOSE_SCENARIO);
        CFG.backToMenu = Menu.eCREATE_NEW_GAME;
        CFG.goToMenu = Menu.eCREATE_NEW_GAME;
        if (CFG.menuManager.getColorPicker().getVisible()) {
            CFG.menuManager.getColorPicker().setVisible(false, null);
        }
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: 
            case 1: {
                Menu_CreateNewGame_Top.clickChooseScenario();
                break;
            }
            case 2: {
                CFG.menuManager.getCreateNewGame_MapModes().setVisible(!CFG.menuManager.getCreateNewGame_MapModes().getVisible());
                if (CFG.menuManager.getCreateNewGame_MapModes().getPosX() >= 0) break;
                CFG.menuManager.getCreateNewGame_MapModes().setPosX_Force(CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - CFG.menuManager.getCreateNewGame_MapModes().getWidth());
                CFG.menuManager.getCreateNewGame_MapModes().setPosY(CFG.menuManager.getCreateNewGame_MapModes().getTitle().getHeight() + ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f));
                break;
            }
            case 3: {
                CFG.game.disableDrawCivilizationRegions_Active();
                ArrayList<Integer> tempCivs = new ArrayList<Integer>();
                for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0) continue;
                    tempCivs.add(i);
                }
                int tempR = CFG.oR.nextInt(tempCivs.size());
                if (CFG.game.getCiv((Integer)tempCivs.get(tempR)).getCapitalProvinceID() >= 0) {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv((Integer)tempCivs.get(tempR)).getCapitalProvinceID());
                } else {
                    CFG.game.setActiveProvinceID(CFG.game.getCiv((Integer)tempCivs.get(tempR)).getProvinceID(0));
                }
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                CFG.updateActiveCivInfo_CreateNewGame();
                if (CFG.game.getPlayersSize() == 1 && CFG.getActiveCivInfo() > 0) {
                    CFG.game.getPlayer(0).setCivID(CFG.getActiveCivInfo());
                    CFG.menuManager.rebuildCivilizations_Info_Players();
                }
                CFG.game.enableDrawCivlizationsRegions_Players();
                CFG.game.enableDrawCivilizationRegions_ActiveProvince();
                tempCivs.clear();
                tempCivs = null;
                break;
            }
            case 4: {
                int nScenarioID = CFG.game.getScenarioID() + 1;
                CFG.game.getGameScenarios();
                if (nScenarioID > Game_Scenarios.SCENARIOS_SIZE - 1) {
                    nScenarioID = 0;
                }
                CFG.game.setActiveProvinceID(-1);
                CFG.viewsManager.disableAllViews();
                CFG.game.setScenarioID(nScenarioID);
                CFG.game.loadScenario(false);
                CFG.game.initPlayers();
                CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
                CFG.menuManager.rebuildCivilizations_Info_Players();
                break;
            }
            case 5: {
                int nScenarioID = CFG.game.getScenarioID() - 1;
                if (nScenarioID < 0) {
                    CFG.game.getGameScenarios();
                    nScenarioID = Game_Scenarios.SCENARIOS_SIZE - 1;
                }
                CFG.game.setActiveProvinceID(-1);
                CFG.viewsManager.disableAllViews();
                CFG.game.setScenarioID(nScenarioID);
                CFG.game.loadScenario(false);
                CFG.game.initPlayers();
                CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
                CFG.menuManager.rebuildCivilizations_Info_Players();
                break;
            }
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        this.lTime = System.currentTimeMillis();
        fMovePercentage = 5.0f;
        super.setVisible(visible);
    }
}

