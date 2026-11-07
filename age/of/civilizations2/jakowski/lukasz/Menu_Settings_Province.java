/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Clear;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Middle;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Game_Render;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InitGame;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SettingsManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear;
import age.of.civilizations2.jakowski.lukasz.Text_BudgetTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Settings_Province
extends SliderMenu {
    private String sScale;

    protected Menu_Settings_Province() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = CFG.PADDING;
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + CFG.BUTTON_HEIGHT, tPosY, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2), tPosY, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 25, 255, CFG.settingsManager.PROVINCE_ALPHA));
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 100, 400, (int)(CFG.settingsManager.STOP_SCALING_ARMY * 100.0f)){

            @Override
            protected String getDrawText() {
                return "" + (float)this.getCurrent() / 100.0f;
            }
        });
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 2, true, CFG.settingsManager.ENABLE_INNER_BORDERS){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.ENABLE_INNER_BORDERS;
            }
        });
        menuElements.add(new Text_BudgetTitle("", -1, 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 100, CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 10, 200, (int)(CFG.settingsManager.CITIES_FONT_SCALE * 100.0f)){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Text_BudgetTitle("", -1, 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2, true, CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME){

            @Override
            protected boolean getCheckboxState() {
                return CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME;
            }
        });
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + CFG.BUTTON_HEIGHT, tPosY, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2), tPosY, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 100, (int)(CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT * 100.0f)){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){
            int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsManager.civNamesFontColor.getR(), CFG.settingsManager.civNamesFontColor.getG(), CFG.settingsManager.civNamesFontColor.getB(), 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 100, (int)(CFG.settingsManager.civNamesFontColor_ALPHA * 100.0f)){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){
            int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsManager.civNamesFontColorBorder.getR(), CFG.settingsManager.civNamesFontColorBorder.getG(), CFG.settingsManager.civNamesFontColorBorder.getB(), 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 100, (int)(CFG.settingsManager.civNamesFontColorBorder_ALPHA * 100.0f)){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, (int)((float)CFG.settingsManager.FONT_BORDER_SIZE * 0.4f), CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "px";
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 5000, CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "ms";
            }
        });
        menuElements.add(new Text_BudgetTitle("", -1, 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){
            int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsManager.civNamesFontColor.getR(), CFG.settingsManager.civNamesFontColor.getG(), CFG.settingsManager.civNamesFontColor.getB(), 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 255, (int)(CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0f)){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "";
            }
        });
        menuElements.add(new Text_BudgetTitle("", -1, 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_NewGameStyle_Clear("", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){
            int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.settingsManager.civNamesFontColorBorder.getR(), CFG.settingsManager.civNamesFontColorBorder.getG(), CFG.settingsManager.civNamesFontColorBorder.getB(), 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 0, 255, (int)(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * 255.0f)){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "";
            }
        });
        menuElements.add(new Button_NewGameStyle_Left("<<", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle("", -1, CFG.PADDING + CFG.BUTTON_HEIGHT, tPosY, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(Color.WHITE);
                CFG.linesManager.moveLandImage.draw2(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.linesManager.moveLandImage.getHeight() / 2 - CFG.linesManager.moveLandImage.getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 4, CFG.linesManager.moveLandImage.getHeight());
            }
        });
        menuElements.add(new Button_NewGameStyle_Right(">>", -1, CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2), tPosY, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("<<", -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle("", -1, CFG.PADDING + CFG.BUTTON_HEIGHT, tPosY, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(Color.WHITE);
                CFG.linesManager.highlightImage.draw2(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.linesManager.highlightImage.getHeight() / 2 - CFG.linesManager.highlightImage.getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 4, CFG.linesManager.highlightImage.getHeight());
            }
        });
        menuElements.add(new Button_NewGameStyle_Right(">>", -1, CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2), tPosY, CFG.BUTTON_HEIGHT, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Text_BudgetTitle("", -1, 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 25, 255, CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA){

            @Override
            protected String getDrawText() {
                return "" + (int)((float)this.getCurrent() / 255.0f * 100.0f) + "%";
            }
        });
        menuElements.add(new Slider_FlagAction_Clear("", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 4, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), 1, 100, (int)(CFG.settingsManager.OCCUPIED_STRIPES_SIZE * 10.0f)){

            @Override
            protected String getDrawText() {
                return "" + (float)this.getCurrent() / 10.0f;
            }
        });
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4, this.getHeight());
                oSB.setColor(new Color(0.003921569f, 0.32941177f, 0.50980395f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.003921569f, 0.32941177f, 0.50980395f, 0.375f));
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
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min(tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 4)), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(2).setText(CFG.langManager.get("FontSizeofArmy") + ": " + CFG.settingsManager.FONT_ARMY_SIZE);
        this.getMenuElement(4).setText(CFG.langManager.get("ProvinceAlpha"));
        this.getMenuElement(5).setText(CFG.langManager.get("Scale"));
        this.getMenuElement(6).setText(CFG.langManager.get("InnerBorders"));
        this.getMenuElement(7).setText(CFG.langManager.get("Cities"));
        this.getMenuElement(8).setText(CFG.langManager.get("NumberOfCities"));
        this.getMenuElement(9).setText(CFG.langManager.get("ScaleOfCitiesNames"));
        this.getMenuElement(10).setText(CFG.langManager.get("CivilizationsNames"));
        this.getMenuElement(11).setText(CFG.langManager.get("NamesOfCivilizationsOverProvinces"));
        this.getMenuElement(13).setText(CFG.langManager.get("FontSize") + ": " + CFG.settingsManager.FONT_BORDER_SIZE);
        this.getMenuElement(15).setText(CFG.langManager.get("MinScaleofCivilizationsNames"));
        this.getMenuElement(16).setText(CFG.langManager.get("Color"));
        this.getMenuElement(17).setText(CFG.langManager.get("Alpha"));
        this.getMenuElement(18).setText(CFG.langManager.get("BorderColor"));
        this.getMenuElement(19).setText(CFG.langManager.get("Alpha"));
        this.getMenuElement(20).setText(CFG.langManager.get("Width"));
        this.getMenuElement(21).setText(CFG.langManager.get("AnimationTime"));
        this.getMenuElement(22).setText(CFG.langManager.get("Wasteland"));
        this.getMenuElement(23).setText(CFG.langManager.get("Color"));
        this.getMenuElement(24).setText(CFG.langManager.get("Alpha"));
        this.getMenuElement(25).setText(CFG.langManager.get("Fogofwar"));
        this.getMenuElement(26).setText(CFG.langManager.get("Color"));
        this.getMenuElement(27).setText(CFG.langManager.get("Alpha"));
        this.getMenuElement(34).setText(CFG.langManager.get("OccupiedProvinces"));
        this.getMenuElement(35).setText(CFG.langManager.get("Alpha"));
        this.getMenuElement(36).setText(CFG.langManager.get("Scale"));
        this.getMenuElement(37).setText(CFG.langManager.get("Defaults"));
        this.getMenuElement(4).setCurrent(CFG.settingsManager.PROVINCE_ALPHA);
        this.getMenuElement(5).setCurrent((int)(CFG.settingsManager.STOP_SCALING_ARMY * 100.0f));
        this.getMenuElement(7).setCurrent(CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP);
        this.getMenuElement(9).setCurrent((int)(CFG.settingsManager.CITIES_FONT_SCALE * 100.0f));
        this.getMenuElement(15).setCurrent((int)(CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT * 100.0f));
        this.getMenuElement(17).setCurrent((int)(CFG.settingsManager.civNamesFontColor_ALPHA * 100.0f));
        this.getMenuElement(19).setCurrent((int)(CFG.settingsManager.civNamesFontColorBorder_ALPHA * 100.0f));
        this.getMenuElement(20).setCurrent(CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER);
        this.getMenuElement(21).setCurrent(CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
        this.getMenuElement(24).setCurrent((int)(CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0f));
        this.getMenuElement(27).setCurrent((int)(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * 255.0f));
        this.getMenuElement(35).setCurrent(CFG.settingsManager.PROVINCE_ALPHA);
        this.getMenuElement(36).setCurrent((int)(CFG.settingsManager.OCCUPIED_STRIPES_SIZE * 10.0f));
        this.getTitle().setText(CFG.langManager.get("ProvinceSettings"));
        this.sScale = CFG.langManager.get("Scale") + ": ";
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT * 3 / 4);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), false, true);
        CFG.fontBorder.getData().setScale(1.0f);
        CFG.drawTextBorder(oSB, "Age of Civilizations II", CFG.PADDING * 2 + iTranslateX, CFG.PADDING * 2, Color.WHITE);
        CFG.drawTextWithShadow(oSB, this.sScale + CFG.map.getMapScale().getCurrentScale(), CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING - CFG.TEXT_HEIGHT, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    protected final void updateArmyWidth() {
        int j;
        int i;
        for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
            for (j = 0; j < CFG.game.getProvince(i).getCivsSize(); ++j) {
                CFG.game.getProvince(i).getArmy_Obj(j).updateArmyWidth_Just(i);
            }
        }
        for (i = 1; i < CFG.game.getCivsSize(); ++i) {
            for (j = 0; j < CFG.game.getCiv(i).getRecruitArmySize(); ++j) {
                CFG.game.getCiv(i).getRecruitArmy(j).setArmy(CFG.game.getCiv(i).getRecruitArmy(j).getArmy());
            }
            for (j = 0; j < CFG.game.getCiv(i).getMoveUnitsPlunderSize(); ++j) {
                CFG.game.getCiv(i).getMoveUnits_Plunder(j).setNumOfUnits(CFG.game.getCiv(i).getMoveUnits_Plunder(j).getNumOfUnits());
            }
        }
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                --CFG.settingsManager.FONT_ARMY_SIZE;
                if (CFG.settingsManager.FONT_ARMY_SIZE < 12) {
                    CFG.settingsManager.FONT_ARMY_SIZE = 12;
                }
                CFG.loadFontArmy();
                if (SaveManager.gameCanBeContinued) {
                    this.updateArmyWidth();
                } else {
                    for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                        CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
                    }
                }
                Menu_InitGame.loadArmyBGImages();
                this.updateLanguage();
                break;
            }
            case 2: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 3: {
                ++CFG.settingsManager.FONT_ARMY_SIZE;
                if (CFG.settingsManager.FONT_ARMY_SIZE > 128) {
                    CFG.settingsManager.FONT_ARMY_SIZE = 128;
                }
                CFG.loadFontArmy();
                if (SaveManager.gameCanBeContinued) {
                    this.updateArmyWidth();
                } else {
                    for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                        CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
                    }
                }
                Menu_InitGame.loadArmyBGImages();
                this.updateLanguage();
                break;
            }
            case 4: {
                CFG.settingsManager.PROVINCE_ALPHA = this.getMenuElement(iID).getCurrent();
                break;
            }
            case 5: {
                CFG.settingsManager.STOP_SCALING_ARMY = (float)this.getMenuElement(iID).getCurrent() / 100.0f;
                break;
            }
            case 6: {
                CFG.settingsManager.ENABLE_INNER_BORDERS = !CFG.settingsManager.ENABLE_INNER_BORDERS;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                break;
            }
            case 8: {
                CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP = this.getMenuElement(iID).getCurrent();
                for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                    CFG.game_NextTurnUpdate.updateCities(i);
                }
                break;
            }
            case 9: {
                CFG.settingsManager.CITIES_FONT_SCALE = (float)this.getMenuElement(iID).getCurrent() / 100.0f;
                for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    for (int j = 0; j < CFG.game.getProvince(i).getCitiesSize(); ++j) {
                        CFG.game.getProvince(i).getCity(j).updateCityNameWidth();
                    }
                }
                break;
            }
            case 11: {
                CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME = !CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME;
                Game_Render.updateRenderer_CivNames();
                break;
            }
            case 12: {
                --CFG.settingsManager.FONT_BORDER_SIZE;
                if (CFG.settingsManager.FONT_BORDER_SIZE < 8) {
                    CFG.settingsManager.FONT_BORDER_SIZE = 8;
                }
                CFG.loadFontBorder();
                for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                    for (int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
                        CFG.game.getCiv(i).getCivRegion(j).buildScaleOfText();
                    }
                }
                this.updateLanguage();
                break;
            }
            case 14: {
                ++CFG.settingsManager.FONT_BORDER_SIZE;
                if (CFG.settingsManager.FONT_BORDER_SIZE > 256) {
                    CFG.settingsManager.FONT_BORDER_SIZE = 256;
                }
                CFG.loadFontBorder();
                for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                    for (int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); ++j) {
                        CFG.game.getCiv(i).getCivRegion(j).buildScaleOfText();
                    }
                }
                this.updateLanguage();
                break;
            }
            case 15: {
                CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT = (float)this.getMenuElement(iID).getCurrent() / 100.0f;
                break;
            }
            case 16: {
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.settingsManager.civNamesFontColor.getR(), CFG.settingsManager.civNamesFontColor.getG(), CFG.settingsManager.civNamesFontColor.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CIV_NAMES_OVER_PROVINCES);
                break;
            }
            case 17: {
                CFG.settingsManager.civNamesFontColor_ALPHA = (float)this.getMenuElement(iID).getCurrent() / 100.0f;
                CFG.loadFontBorder();
                break;
            }
            case 18: {
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.settingsManager.civNamesFontColorBorder.getR(), CFG.settingsManager.civNamesFontColorBorder.getG(), CFG.settingsManager.civNamesFontColorBorder.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CIV_NAMES_OVER_PROVINCES_BORDER);
                break;
            }
            case 19: {
                CFG.settingsManager.civNamesFontColorBorder_ALPHA = (float)this.getMenuElement(iID).getCurrent() / 100.0f;
                CFG.loadFontBorder();
                break;
            }
            case 20: {
                CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER = this.getMenuElement(iID).getCurrent();
                CFG.loadFontBorder();
                break;
            }
            case 21: {
                CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL = this.getMenuElement(iID).getCurrent();
                break;
            }
            case 23: {
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getR(), CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getG(), CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PROVINCE_SETTINGS_WASTELAND_COLOR);
                break;
            }
            case 24: {
                CFG.settingsManager.PROVINCE_ALPHA_WASTELAND = (float)this.getMenuElement(iID).getCurrent() / 255.0f;
                break;
            }
            case 26: {
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(), CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PROVINCE_SETTINGS_DISCOVERY_COLOR);
                break;
            }
            case 27: {
                CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA = (float)this.getMenuElement(iID).getCurrent() / 255.0f;
                break;
            }
            case 28: {
                CFG.linesManager.moveLandTAG = CFG.settingsManager.sMoveLine = CFG.linesManager.loadNext(CFG.linesManager.moveLandTAG, false);
                CFG.linesManager.loadMoveLand();
                break;
            }
            case 29: {
                break;
            }
            case 30: {
                CFG.linesManager.moveLandTAG = CFG.settingsManager.sMoveLine = CFG.linesManager.loadNext(CFG.linesManager.moveLandTAG, true);
                CFG.linesManager.loadMoveLand();
                break;
            }
            case 31: {
                CFG.linesManager.highlightTAG = CFG.settingsManager.sHighlightLine = CFG.linesManager.loadNext(CFG.linesManager.highlightTAG, false);
                CFG.linesManager.loadHighlight();
                break;
            }
            case 32: {
                break;
            }
            case 33: {
                CFG.linesManager.highlightTAG = CFG.settingsManager.sHighlightLine = CFG.linesManager.loadNext(CFG.linesManager.highlightTAG, true);
                CFG.linesManager.loadHighlight();
                break;
            }
            case 35: {
                CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA = this.getMenuElement(iID).getCurrent();
                break;
            }
            case 36: {
                CFG.settingsManager.OCCUPIED_STRIPES_SIZE = (float)this.getMenuElement(iID).getCurrent() / 10.0f;
                break;
            }
            case 37: {
                int i;
                SettingsManager tempS = new SettingsManager();
                CFG.settingsManager.PROVINCE_ALPHA = tempS.PROVINCE_ALPHA;
                CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME = tempS.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME;
                CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA = tempS.OCCUPIED_PROVINCE_ALPHA;
                CFG.settingsManager.OCCUPIED_STRIPES_SIZE = tempS.OCCUPIED_STRIPES_SIZE;
                CFG.settingsManager.FONT_ARMY_SIZE = tempS.FONT_ARMY_SIZE;
                AoCGame.updateArmyFontSize();
                CFG.loadFontArmy();
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
                }
                CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP = tempS.PERCETANGE_OF_CITIES_ON_MAP;
                CFG.settingsManager.STOP_SCALING_ARMY = tempS.STOP_SCALING_ARMY;
                for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                    CFG.game_NextTurnUpdate.updateCities(i);
                }
                CFG.settingsManager.updateCitiesFontScale();
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    for (int j = 0; j < CFG.game.getProvince(i).getCitiesSize(); ++j) {
                        CFG.game.getProvince(i).getCity(j).updateCityNameWidth();
                    }
                }
                CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER = tempS.FONT_BORDER_WIDTH_OF_BORDER;
                CFG.settingsManager.ENABLE_INNER_BORDERS = tempS.ENABLE_INNER_BORDERS;
                for (i = 0; i < CFG.game.getProvincesSize(); ++i) {
                    CFG.game.getProvince(i).updateProvinceBorder();
                }
                CFG.settingsManager.civNamesFontColor.setR(tempS.civNamesFontColor.getR());
                CFG.settingsManager.civNamesFontColor.setG(tempS.civNamesFontColor.getG());
                CFG.settingsManager.civNamesFontColor.setB(tempS.civNamesFontColor.getB());
                CFG.settingsManager.civNamesFontColor_ALPHA = tempS.civNamesFontColor_ALPHA;
                CFG.settingsManager.civNamesFontColorBorder.setR(tempS.civNamesFontColorBorder.getR());
                CFG.settingsManager.civNamesFontColorBorder.setG(tempS.civNamesFontColorBorder.getG());
                CFG.settingsManager.civNamesFontColorBorder.setB(tempS.civNamesFontColorBorder.getB());
                CFG.settingsManager.civNamesFontColorBorder_ALPHA = tempS.civNamesFontColorBorder_ALPHA;
                CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT = tempS.CIV_NAMES_MIN_SCALE_OF_FONT;
                CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL = tempS.CIVILIZATIONS_NAMES_INTERVAL;
                CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setR(tempS.COLOR_PROVINCE_BG_WASTELAND.getR());
                CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setG(tempS.COLOR_PROVINCE_BG_WASTELAND.getG());
                CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setB(tempS.COLOR_PROVINCE_BG_WASTELAND.getB());
                CFG.settingsManager.PROVINCE_ALPHA_WASTELAND = tempS.PROVINCE_ALPHA_WASTELAND;
                CFG.settingsManager.COLOR_PROVINCE_DISCOVERY = tempS.COLOR_PROVINCE_DISCOVERY;
                CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA = tempS.COLOR_PROVINCE_DISCOVERY_ALPHA;
                CFG.settingsManager.sMoveLine = tempS.sMoveLine;
                CFG.linesManager.loadMoveLand();
                CFG.settingsManager.sHighlightLine = tempS.sHighlightLine;
                CFG.linesManager.loadHighlight();
                CFG.loadFontBorder();
                Game_Render.updateRenderer_CivNames();
                this.updateLanguage();
            }
        }
        CFG.saveSettings();
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        CFG.menuManager.setViewID(Menu.eSETTINGS);
        CFG.menuManager.setBackAnimation(true);
        this.updateArmyWidth();
    }
}

