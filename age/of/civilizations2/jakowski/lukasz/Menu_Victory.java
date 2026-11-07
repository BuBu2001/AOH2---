/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Speed;
import age.of.civilizations2.jakowski.lukasz.Button_Speed_Right;
import age.of.civilizations2.jakowski.lukasz.Button_VictoryStats;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.TimelapseManager;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Victory
extends SliderMenu {
    protected static boolean VICTORIOUS = false;
    protected static final int ANIMATION_TIME = 1000;
    private long lTime = 0L;
    protected static final int ANIMATION_TIME_TOP = 725;
    private long lTimeTOP = 0L;
    protected static final int ANIMATION_TIME_TOP2 = 2750;
    private long lTimeTOP2 = 0L;
    protected boolean backAnimation = false;
    protected boolean hideTop = false;
    private String sTopText;
    private int iTopTextWidth = 0;
    protected final float FONT_DATE_SCALE = 0.7f;
    private String sTopTextDate;
    private int iTopTextDateWidth = 0;
    protected Color topColorBG;

    protected Menu_Victory(boolean VICTORIOUS) {
        Menu_Victory.VICTORIOUS = VICTORIOUS;
        this.sTopText = VICTORIOUS ? CFG.langManager.get("Victory") : CFG.langManager.get("Defeat");
        CFG.glyphLayout.setText(CFG.fontMain, this.sTopText);
        this.iTopTextWidth = (int)CFG.glyphLayout.width;
        this.sTopTextDate = Game_Calendar.getDate_ByTurnID(1) + " - " + Game_Calendar.getCurrentDate();
        CFG.glyphLayout.setText(CFG.fontMain, this.sTopTextDate);
        this.iTopTextDateWidth = (int)(CFG.glyphLayout.width * 0.7f);
        this.topColorBG = VICTORIOUS ? Color.WHITE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
        this.backAnimation = false;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text(null, 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + 1 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }

            @Override
            protected int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight();
            }

            @Override
            protected int getWidth() {
                return Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING * 4);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Text("Date", 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected int getPosX() {
                return CFG.GAME_WIDTH - ImageManager.getImage(Images.top_left2).getHeight() - CFG.PADDING - Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING * 4);
            }

            @Override
            protected int getWidth() {
                return Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING * 4);
            }

            @Override
            protected int getHeight() {
                return (int)((float)CFG.TEXT_HEIGHT * 0.8f) + CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (TimelapseManager.PAUSE) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("ENTER", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        int tempTurnH = ImageManager.getImage(Images.top_left2).getHeight() - CFG.PADDING * 3 - (int)((float)CFG.TEXT_HEIGHT * 0.8f);
        for (int i = 0; i < 60 && !((float)CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale <= (float)tempTurnH); ++i) {
            Menu_InGame.fTurnScale -= 0.01f;
        }
        menuElements.add(new Text("Turn", 0, 0, CFG.PADDING * 2 + (int)((float)CFG.TEXT_HEIGHT * 0.8f), ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(Menu_InGame.fTurnScale);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + (int)(((float)this.getWidth() - (float)this.getTextWidth() * Menu_InGame.fTurnScale) / 2.0f) + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_RANK_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected int getPosX() {
                return Menu_Victory.this.getMenuElement(1).getPosX();
            }

            @Override
            protected int getWidth() {
                return Menu_Victory.this.getMenuElement(1).getWidth();
            }

            @Override
            protected int getHeight() {
                return (int)((float)CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (TimelapseManager.PAUSE) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("ENTER", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        menuElements.add(new Button_Speed("-", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight() - 2, true){

            @Override
            protected int getPosX() {
                return Menu_Victory.this.getMenuElement(1).getPosX() - this.getWidth();
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DecreaseSpeed"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("-", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        menuElements.add(new Button_Speed_Right("+", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight() - 2, true){

            @Override
            protected int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncreaseSpeed"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("+", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        menuElements.add(new Button_VictoryStats(CFG.langManager.get("Income") + ": ", CFG.getNumberWithSpaces("75148"), CFG.COLOR_INGAME_GOLD, Images.top_gold, 0, CFG.GAME_HEIGHT - ((MenuElement)menuElements.get(0)).getHeight() - CFG.PADDING * 2 - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), CFG.CIV_INFO_MENU_WIDTH * 3 / 4, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), true));
        menuElements.add(new Button_VictoryStats(CFG.langManager.get("Provinces") + ": ", "4", CFG.COLOR_TEXT_NUM_OF_PROVINCES, Images.provinces, 0, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), CFG.CIV_INFO_MENU_WIDTH * 3 / 4, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.lTime = System.currentTimeMillis();
        this.lTimeTOP = System.currentTimeMillis();
        this.lTimeTOP2 = 0L;
        TimelapseManager.SPEED = 6;
        CFG.timelapseManager.pauseUnpause();
        CFG.map.getMapCoordinates().centerToCivilizationBox_Timeline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
        this.updateLanguage();
        this.getMenuElement(5).setCurrent(0);
        this.getMenuElement(6).setCurrent(1);
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(1).setText(Game_Calendar.getDate_ByTurnID(CFG.timelapseManager.iTimelineTurnID + 1));
        this.getMenuElement(2).setText(CFG.langManager.get("Turn") + ": " + (CFG.timelapseManager.iTimelineTurnID + 1));
        this.getMenuElement(0).setText(CFG.langManager.get("Continue"));
        this.updateTurnData();
    }

    protected void updateTurnData() {
        this.getMenuElement(5).setText(CFG.getNumberWithSpaces("" + CFG.timelapseManager.getPlayerIncome(CFG.PLAYER_TURNID, CFG.timelapseManager.iTimelineTurnID)));
        this.getMenuElement(6).setText(CFG.getNumberWithSpaces("" + CFG.timelapseManager.getNumOfProvinces(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.setRender_3(true);
        if (!TimelapseManager.PAUSE) {
            CFG.timelapseManager.updateTime();
            this.updateTurnData();
        }
        if (this.lTime + 1000L >= System.currentTimeMillis()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.05f + 0.325f * ((float)(System.currentTimeMillis() - this.lTime) / 1000.0f)));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.375f));
        }
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getTopHeight());
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTopHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getTopHeight() / 4, false, true);
        if (this.lTime + 1000L >= System.currentTimeMillis()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.05f + 0.075f * ((float)(System.currentTimeMillis() - this.lTime) / 1000.0f)));
            CFG.setRender_3(true);
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.125f));
        }
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getTopHeight() / 2, this.getHeight());
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getTopHeight() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getTopHeight() / 4, this.getHeight(), true, false);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.125f));
        ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING - ImageManager.getImage(Images.gameLogo).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gameLogo).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(3).getPosX() - ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 - CFG.PADDING + iTranslateX, -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - this.getMenuElement(3).getPosX()), ImageManager.getImage(Images.top_left2_sha).getHeight());
        ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(3).getPosX() - ImageManager.getImage(Images.top_left2).getWidth() / 2 - CFG.PADDING + iTranslateX, -ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - this.getMenuElement(3).getPosX()), ImageManager.getImage(Images.top_left2).getHeight());
        Menu_Victory.draw_Time(oSB, this.getMenuElement(1).getPosX() + iTranslateX, 0, this.getMenuElement(1).getWidth(), ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING);
        int tSpeedWidth = (this.getMenuElement(1).getWidth() - CFG.PADDING * 5) / 6;
        int tX = (this.getMenuElement(1).getWidth() - tSpeedWidth * 6 - CFG.PADDING * 5) / 2;
        for (int i = 0; i < TimelapseManager.SPEED; ++i) {
            Menu_InGame.draw_Speed(oSB, tX + this.getMenuElement(1).getPosX() + (tSpeedWidth + CFG.PADDING) * i + iTranslateX, ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING, tSpeedWidth, CFG.PADDING);
        }
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left2_sha).getHeight(), true, true);
        if (this.getMenuElement(0).getIsHovered()) {
            ImageManager.getImage(Images.top_left3).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left3).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left3).getHeight(), true, true);
        } else {
            ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left2).getHeight(), true, true);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        int extraY = 0;
        if (this.backAnimation && this.lTimeTOP2 + 2750L >= System.currentTimeMillis()) {
            this.lTimeTOP = System.currentTimeMillis();
        } else if (this.lTimeTOP + 725L >= System.currentTimeMillis()) {
            extraY = this.backAnimation ? (int)((float)(-this.getVictoryPosMax()) * ((float)(System.currentTimeMillis() - this.lTimeTOP) / 725.0f)) : (int)((float)(-this.getVictoryPosMax()) + (float)this.getVictoryPosMax() * ((float)(System.currentTimeMillis() - this.lTimeTOP) / 725.0f));
        } else if (!this.backAnimation) {
            this.backAnimation = true;
            this.lTimeTOP = System.currentTimeMillis();
            this.lTimeTOP2 = System.currentTimeMillis();
        } else {
            this.hideTop = true;
        }
        if (!this.hideTop) {
            this.drawVictory(oSB, this.getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT / 4 + extraY + iTranslateY, sliderMenuIsActive, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
        }
    }

    protected int getVictoryPosMax() {
        return CFG.BUTTON_HEIGHT / 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 6 + CFG.PADDING * 2 + 0;
    }

    protected static final void draw_Time(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), (int)((float)nWidth * CFG.timelapseManager.getTimePerc()), nHeight, 0, TimelapseManager.SOURCE);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), (int)((float)nWidth * CFG.timelapseManager.getTimePerc()), nHeight, 0, TimelapseManager.SOURCE);
        if (!TimelapseManager.PAUSE) {
            --TimelapseManager.SOURCE;
        }
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING);
        oSB.setColor(Color.WHITE);
    }

    protected final void drawVictory(SpriteBatch oSB, int nX, int nY, boolean sliderMenuIsActive, int titleH) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), titleH);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.625f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY + titleH - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY + titleH - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        CFG.drawTextWithShadow(oSB, this.sTopText, nX + this.getWidth() / 2 - this.iTopTextWidth / 2, nY + titleH / 2 - CFG.TEXT_HEIGHT / 2, this.topColorBG);
        CFG.fontMain.getData().setScale(0.7f);
        CFG.drawText(oSB, this.sTopTextDate, nX + this.getWidth() / 2 - this.iTopTextDateWidth / 2, nY + titleH + CFG.PADDING + CFG.PADDING / 2, new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.425f));
        CFG.fontMain.getData().setScale(1.0f);
    }

    protected int getTopHeight() {
        return CFG.BUTTON_HEIGHT + CFG.PADDING * 4;
    }

    protected static final void clickBack() {
        RTS.resetTime();
        RTS.PAUSE = true;
        CFG.menuManager.setViewIDWithoutAnimation(Menu.eINGAME);
        CFG.map.getMapScroll().stopScrollingTheMap();
        CFG.map.getMapBG().updateWorldMap_Shaders();
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                if (!TimelapseManager.PAUSE) {
                    CFG.timelapseManager.pauseUnpause();
                }
                CFG.setDialogType(Dialog.CONTINUE_AFTER_END_GAME);
                break;
            }
            case 1: 
            case 2: {
                CFG.timelapseManager.pauseUnpause();
                CFG.map.getMapCoordinates().centerToCivilizationBox_Timeline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
                return;
            }
            case 3: {
                CFG.timelapseManager.updateSpeed(-1);
                return;
            }
            case 4: {
                CFG.timelapseManager.updateSpeed(1);
                return;
            }
        }
    }

    @Override
    protected final void onBackPressed() {
    }

    @Override
    protected void onMenuPressed() {
    }
}

