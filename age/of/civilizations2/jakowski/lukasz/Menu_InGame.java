/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button_Rank;
import age.of.civilizations2.jakowski.lukasz.Button_Speed;
import age.of.civilizations2.jakowski.lukasz.Button_Speed_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.DiplomacyManager;
import age.of.civilizations2.jakowski.lukasz.Game_Action;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Color;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_GraphManager;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_ProvinceInfo;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.Touch;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame
extends SliderMenu {
    protected static float fTurnScale = 0.8f;
    protected static boolean BUDGET_OVER = false;
    protected static int iTopBalance = 0;
    protected static final int TIME_REQUIRED_TO_CONTINUE = 6;
    protected static long TIME_CONTINUE;
    protected static final float FONT_SIZE_BALANCE = 0.85f;

    protected static final void updateOverBudget() {
        iTopBalance = CFG.game_NextTurnUpdate.getBalance(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        BUDGET_OVER = iTopBalance < 0;
        CFG.menuManager.getInGame().getMenuElement(1).setCurrent(iTopBalance);
    }

    protected Menu_InGame() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.XXXXHDPI) {
            CFG.topBox.iFlagX = 10;
            CFG.topBox.iFlagY = 11;
            CFG.topBox.iCircleShift = -36;
        } else if (CFG.XXXHDPI) {
            CFG.topBox.iFlagX = 14;
            CFG.topBox.iFlagY = 12;
            CFG.topBox.iCircleShift = -24;
        } else if (CFG.XXHDPI) {
            CFG.topBox.iFlagX = 14;
            CFG.topBox.iFlagY = 12;
            CFG.topBox.iCircleShift = -24;
        } else if (CFG.XHDPI) {
            CFG.topBox.iFlagX = 9;
            CFG.topBox.iFlagY = 9;
            CFG.topBox.iCircleShift = -22;
        } else {
            CFG.topBox.iFlagX = 7;
            CFG.topBox.iFlagY = 7;
            CFG.topBox.iCircleShift = -18;
            CFG.topBox.leftExtraViewPadding = 10;
        }
        menuElements.add(new Minimap(0, 0){

            @Override
            protected int getPosY() {
                return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight();
            }
        });
        menuElements.add(new Text("0", 0, CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING, 0, ImageManager.getImage(Images.top_left2).getHeight()){
            String sBalance;
            int iBalanceWidth;
            Color cBalance;
            {
                this.sBalance = "";
                this.iBalanceWidth = 0;
                this.cBalance = Color.WHITE;
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() < 0L) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
                    ImageManager.getImage(Images.top_gold2).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold2).getHeight() / 2 + iTranslateY);
                    oSB.setColor(Color.WHITE);
                } else {
                    ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2 + iTranslateY);
                }
                if (BUDGET_OVER) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
                    ImageManager.getImage(Images.top_gold2).draw(oSB, this.getPosX() + this.getTextWidth() + ImageManager.getImage(Images.top_gold).getWidth() + this.textPosition.getTextPosition() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold2).getHeight() / 2 + iTranslateY);
                    oSB.setColor(Color.WHITE);
                    ImageManager.getImage(Images.ar_down).draw(oSB, this.getPosX() + this.getTextWidth() + ImageManager.getImage(Images.top_gold).getWidth() + this.textPosition.getTextPosition() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_gold2).getWidth() - ImageManager.getImage(Images.ar_down).getWidth() + CFG.PADDING / 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold2).getHeight() / 2 + ImageManager.getImage(Images.top_gold2).getHeight() - ImageManager.getImage(Images.ar_down).getHeight() + CFG.PADDING / 4 + iTranslateY);
                } else {
                    ImageManager.getImage(Images.top_gold).draw(oSB, this.getPosX() + this.getTextWidth() + ImageManager.getImage(Images.top_gold).getWidth() + this.textPosition.getTextPosition() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2 + iTranslateY);
                    ImageManager.getImage(Images.ar_up).draw(oSB, this.getPosX() + this.getTextWidth() + ImageManager.getImage(Images.top_gold).getWidth() + this.textPosition.getTextPosition() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_gold).getWidth() - ImageManager.getImage(Images.ar_up).getWidth() + CFG.PADDING / 4 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2 + ImageManager.getImage(Images.top_gold).getHeight() - ImageManager.getImage(Images.ar_up).getHeight() + CFG.PADDING / 4 + iTranslateY);
                }
                super.draw(oSB, iTranslateX += ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY);
                CFG.fontMain.getData().setScale(0.85f);
                CFG.drawTextWithShadow(oSB, this.sBalance, this.getPosX() + this.textPosition.getTextPosition() + CFG.PADDING + ImageManager.getImage(Images.top_gold).getWidth() + this.getTextWidth() + CFG.PADDING + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)CFG.TEXT_HEIGHT * 0.85f) / 2.0f) + iTranslateY, this.cBalance);
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                try {
                    this.sBalance = (nCurrent > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + nCurrent);
                    this.cBalance = nCurrent > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (nCurrent == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER);
                    CFG.glyphLayout.setText(CFG.fontMain, this.sBalance);
                    this.iBalanceWidth = (int)(CFG.glyphLayout.width * 0.85f);
                }
                catch (NullPointerException ex) {
                    this.sBalance = "";
                    this.iBalanceWidth = 0;
                    CFG.exceptionStack(ex);
                }
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING + this.iBalanceWidth + CFG.PADDING;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= 0L ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : (isActive ? CFG.COLOR_INGAME_GOLD_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_GOLD_HOVER : CFG.COLOR_INGAME_GOLD));
            }

            @Override
            protected void buildElementHover() {
                int tempValue;
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Treasury") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()), CFG.COLOR_INGAME_GOLD));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if ((int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Inflation") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())), (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("[" + (float)((int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0f)) / 100.0f + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                int tempBalance = tempValue = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Income") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempValue), tempValue > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                tempValue = (int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Expenses") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tempValue), tempValue > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Balance") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(((tempBalance -= tempValue) > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + tempBalance), tempBalance > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (tempBalance < 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
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
                return CFG.menuManager.getVisible_InGame_Budget() ? SoundsManager.SOUND_CLICK2 : SoundsManager.SOUND_GOLD;
            }
        });
        menuElements.add(new Text("", 0, 0));
        menuElements.add(new Text("", 0, 0));
        menuElements.add(new Text("", 0, 0));
        menuElements.add(new Text("", 0, 0));
        menuElements.add(new Button_Rank("1", CFG.topBox.iFlagX + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.topBox.iCircleShift, CFG.topBox.iFlagY + ImageManager.getImage(Images.top_flag_frame).getHeight() + ImageManager.getImage(Images.top_civ_color).getHeight() + CFG.topBox.iCircleShift){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition() + "/" + CFG.game.getCivsSize(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.rank, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F9", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left).getHeight());
                }
            }
        });
        menuElements.add(new Button_Flag_JustFrame(CFG.topBox.iFlagX, CFG.topBox.iFlagY, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.menuManager.getVisible_InGame_FlagAction() ? CFG.langManager.get("CloseCivilizationView") : CFG.langManager.get("OpenCivilizationView"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition() + "/" + CFG.game.getCivsSize(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.rank, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F1", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left).getHeight());
                }
            }
        });
        menuElements.add(new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected int getPosX() {
                return Menu_InGame.this.getMenuElement(10).getVisible() ? Menu_InGame.this.getMenuElement(10).getPosX() + Menu_InGame.this.getMenuElement(10).getWidth() : Menu_InGame.this.getMenuElement(3).getPosX() + Menu_InGame.this.getMenuElement(3).getWidth();
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawText(oSB, this.sText, this.getPosX() + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected int getWidth() {
                return (int)((float)this.getTextWidth() * 0.6f) + CFG.PADDING * 2 + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            protected int getSFX() {
                return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            protected void buildElementHover() {
                if (CFG.isDesktop()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F3", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        menuElements.add(new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected int getPosX() {
                return Menu_InGame.this.getMenuElement(8).getPosX() + Menu_InGame.this.getMenuElement(8).getWidth();
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawText(oSB, this.sText, this.getPosX() + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected int getWidth() {
                return (int)((float)this.getTextWidth() * 0.6f) + CFG.PADDING * 2 + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }

            @Override
            protected void buildElementHover() {
                if (CFG.isDesktop()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F4", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        menuElements.add(new Text("", 0, CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Player") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.PLAYER_TURNID + 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setVisible(false);
        menuElements.add(new Text("", 0, 0));
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

            @Override
            protected boolean getVisible() {
                return RTS.isEnabled();
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.setMenuElement(2, new Text("3.2", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.top_movement_points).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() / 2 + iTranslateY);
                super.draw(oSB, iTranslateX += ImageManager.getImage(Images.top_movement_points).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected int getPosX() {
                return CFG.menuManager.getInGame().getMenuElement(1).getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getWidth() + CFG.PADDING;
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + ImageManager.getImage(Images.top_movement_points).getWidth() + CFG.PADDING;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() < CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : (isActive ? CFG.COLOR_INGAME_MOVEMENT_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_MOVEMENT_HOVER : CFG.COLOR_INGAME_MOVEMENT));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() / 10.0f, CFG.COLOR_INGAME_MOVEMENT));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + (float)CFG.gameAction.getMovementPoints_BaseValue(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilizationSize") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.gameAction.getMovementPoints_FromCivSize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+") + (float)CFG.gameAction.getMovementPoints_FromCivSize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.gameAction.getMovementPoints_FromCivSize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.gameAction.getMovementPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+") + (float)CFG.gameAction.getMovementPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.gameAction.getMovementPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
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
                return SoundsManager.SOUND_MOVE_ARMY;
            }
        });
        this.setMenuElement(3, new Text("1.4", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.top_diplomacy_points).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_diplomacy_points).getHeight() / 2 + iTranslateY);
                super.draw(oSB, iTranslateX += ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected int getPosX() {
                try {
                    return CFG.menuManager.getInGame().getMenuElement(2).getPosX() + CFG.menuManager.getInGame().getMenuElement(2).getWidth() + CFG.PADDING;
                }
                catch (NullPointerException ex) {
                    return 0;
                }
            }

            @Override
            protected int getWidth() {
                return super.getWidth() + ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING;
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_INGAME_DIPLOMACY_POINTS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_DIPLOMACY_POINTS_HOVER : CFG.COLOR_INGAME_DIPLOMACY_POINTS);
            }

            @Override
            protected void buildElementHover() {
                int i;
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() / 10.0f, CFG.COLOR_INGAME_DIPLOMACY_POINTS));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                int perTurn = CFG.gameAction.getUpdateCivsDiplomacyPoints(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnIncrease") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + (float)Math.max(perTurn, 0) / 10.0f, perTurn > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + (float)CFG.gameAction.getDiplomacyPoints_BaseValue(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Rank") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.gameAction.getDiplomacyPoints_FromRank(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+") + (float)CFG.gameAction.getDiplomacyPoints_FromRank(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.gameAction.getDiplomacyPoints_FromRank(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.gameAction.getDiplomacyPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+") + (float)CFG.gameAction.getDiplomacyPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.gameAction.getDiplomacyPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enemies") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHatedCivsSize(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHatedCivsSize() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(" / "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.oAI.MIN_NUM_OF_RIVALS, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) <= 0 ? "" : "+") + (float)CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0f, CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) < 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_POSITIVE)));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (DiplomacyManager.getCostOfCurrentDiplomaticActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize() > 1) {
                    for (int j = 0; j < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize(); ++j) {
                        if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j) == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                        nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getR() / 255.0f, CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getG() / 255, (float)CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)).getB() / 255.0f, 1.0f)));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)));
                        break;
                    }
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Alliance")));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_alliance, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCivsSize(); ++i) {
                    if (CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv((int)i).iCivID).getNumOfProvinces() <= 0) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv((int)i).iCivID).getR() / 255.0f, CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv((int)i).iCivID).getG() / 255, (float)CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv((int)i).iCivID).getB() / 255.0f, 1.0f)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv((int)i).iCivID));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FriendlyCivilizations")));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_heart, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.3", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.iVassalsSize > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 1.0f)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassals")));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_vassal, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + (float)(1 * CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).civGameData.iVassalsSize) / 10.0f, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                    if (CFG.game.getCiv(i).getNumOfProvinces() <= 0 || i == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) continue;
                    if (CFG.game.getCivNonAggressionPact(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(i).getR() / 255.0f, CFG.game.getCiv(i).getG() / 255, (float)CFG.game.getCiv(i).getB() / 255.0f, 1.0f)));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NonAggressionPact")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_non_aggression, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.2", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.game.getGuarantee(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(i).getR() / 255.0f, CFG.game.getCiv(i).getG() / 255, (float)CFG.game.getCiv(i).getB() / 255.0f, 1.0f)));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GuaranteeIndependence")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_guarantee_gives, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.1", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.game.getDefensivePact(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(i).getR() / 255.0f, CFG.game.getCiv(i).getG() / 255, (float)CFG.game.getCiv(i).getB() / 255.0f, 1.0f)));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefensivePact")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_defensive_pact, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.3", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.game.getMilitaryAccess(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i) <= 0) continue;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(i).getR() / 255.0f, CFG.game.getCiv(i).getG() / 255, (float)CFG.game.getCiv(i).getB() / 255.0f, 1.0f)));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryAccess")));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_access_has, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.1", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelationsSize() > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WeAreImprovingOurRelationsWith") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelationsSize(); ++i) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Color(new Color((float)CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID).getR() / 255.0f, CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID).getG() / 255, (float)CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID).getB() / 255.0f, 1.0f)));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID).getCivName()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" +" + (float)((int)(DiplomacyManager.getImproveRelation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation((int)i).iWithCivID) * 100.0f)) / 100.0f, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations_inc, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.5", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerTurn")));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getSFX() {
                return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        this.setMenuElement(4, new Text(null, 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()){

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
                return CFG.GAME_WIDTH + (RTS.isEnabled() ? -ImageManager.getImage(Images.top_left2).getHeight() - CFG.PADDING - Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING * 4) : -((int)((float)this.getTextWidth() * 0.8f)) - CFG.PADDING);
            }

            @Override
            protected int getWidth() {
                return RTS.isEnabled() ? Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)((float)this.getTextWidth() * 0.8f) + CFG.PADDING * 4) : (int)((float)this.getTextWidth() * 0.8f);
            }

            @Override
            protected int getHeight() {
                return (int)((float)CFG.TEXT_HEIGHT * 0.8f) + CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (RTS.isEnabled()) {
                    if (RTS.PAUSE) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (Game_Calendar.TURN_ID != 1) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
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
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("Turn") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + Game_Calendar.TURN_ID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (Game_Calendar.TURN_ID != 1) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("F8", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
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
        for (int i = 0; i < 60 && !((float)CFG.TEXT_HEIGHT * fTurnScale <= (float)tempTurnH); ++i) {
            fTurnScale -= 0.01f;
        }
        this.setMenuElement(5, new Text(null, 0, 0, CFG.PADDING * 2 + (int)((float)CFG.TEXT_HEIGHT * 0.8f), ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(fTurnScale);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + (RTS.isEnabled() ? (int)(((float)this.getWidth() - (float)this.getTextWidth() * fTurnScale) / 2.0f) : (int)((float)this.getWidth() - (float)this.getTextWidth() * fTurnScale)) + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_RANK_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected int getPosX() {
                return Menu_InGame.this.getMenuElement(4).getPosX();
            }

            @Override
            protected int getWidth() {
                return Menu_InGame.this.getMenuElement(4).getWidth();
            }

            @Override
            protected int getHeight() {
                return (int)((float)CFG.TEXT_HEIGHT * fTurnScale);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                if (RTS.isEnabled()) {
                    if (RTS.PAUSE) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (Game_Calendar.TURN_ID != 1) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
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
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    if (Game_Calendar.TURN_ID != 1) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("F8", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
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
        this.setMenuElement(11, new Button_Speed("-", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight() - 2, true){

            @Override
            protected int getPosX() {
                return Menu_InGame.this.getMenuElement(4).getPosX() - this.getWidth();
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
            protected boolean getVisible() {
                return RTS.isEnabled();
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
                }
            }
        });
        this.updateLanguage();
        this.updateMenuElements_IsInView();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(4).setText(Game_Calendar.getCurrentDate());
        this.getMenuElement(5).setText(CFG.langManager.get("Turn") + ": " + Game_Calendar.TURN_ID);
        this.getMenuElement(8).setText(CFG.langManager.get("Diplomacy"));
        this.getMenuElement(9).setText(CFG.langManager.get("MapModes"));
    }

    protected static final void draw_Time(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), (int)((float)nWidth * RTS.getTimePerc()), nHeight, 0, RTS.SOURCE);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), (int)((float)nWidth * RTS.getTimePerc()), nHeight, 0, RTS.SOURCE);
        if (!RTS.PAUSE) {
            --RTS.SOURCE;
        }
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING);
        oSB.setColor(Color.WHITE);
    }

    protected static final void draw_Speed(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY + nHeight - nHeight / 2 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 2, false, true);
        ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 2, false, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, nHeight);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - CFG.PADDING, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, nHeight, true, false);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOADING_NEXT_TURN && TIME_CONTINUE > 0L) {
            CFG.setRender_3(true);
            if (TIME_CONTINUE < System.currentTimeMillis() - 6L) {
                TIME_CONTINUE = -1L;
                Menu_InGame_ProvinceInfo.clickEndTurn();
            }
        }
        int nElemWidthID = this.getMenuElement(10).getVisible() ? 10 : 3;
        ImageManager.getImage(Images.top_left).draw2(oSB, iTranslateX, iTranslateY - ImageManager.getImage(Images.top_left).getHeight(), this.getMenuElement(nElemWidthID).getPosX() + this.getMenuElement(nElemWidthID).getWidth(), ImageManager.getImage(Images.top_left).getHeight());
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(nElemWidthID).getPosX() + this.getMenuElement(nElemWidthID).getWidth() + iTranslateX, -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, this.getMenuElement(8).getWidth() + this.getMenuElement(9).getWidth() + ImageManager.getImage(Images.top_left2_sha).getWidth() - CFG.PADDING, ImageManager.getImage(Images.top_left2_sha).getHeight(), true, false);
        ImageManager.getImage(this.getMenuElement(9).getIsHovered() ? Images.top_left2 : Images.top_left3).draw2(oSB, this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + iTranslateX, -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY, this.getMenuElement(9).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(), ImageManager.getImage(Images.top_left3).getHeight(), true, false);
        ImageManager.getImage(this.getMenuElement(8).getIsHovered() ? Images.top_left3 : Images.top_left2).draw2(oSB, this.getMenuElement(nElemWidthID).getPosX() + this.getMenuElement(nElemWidthID).getWidth() + iTranslateX, -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY, this.getMenuElement(8).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(), ImageManager.getImage(Images.top_left3).getHeight(), true, false);
        ImageManager.getImage(Images.top_left2).draw(oSB, this.getMenuElement(nElemWidthID).getPosX() + this.getMenuElement(nElemWidthID).getWidth() + iTranslateX, iTranslateY, true, false);
        ImageManager.getImage(Images.top_left_extra).draw(oSB, iTranslateX, ImageManager.getImage(Images.top_left).getHeight() + iTranslateY);
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX()) - ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 - CFG.PADDING + iTranslateX, -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())), ImageManager.getImage(Images.top_left2_sha).getHeight());
        ImageManager.getImage(Images.top_left2).draw2(oSB, (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX()) - ImageManager.getImage(Images.top_left2).getWidth() / 2 - CFG.PADDING + iTranslateX, -ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())), ImageManager.getImage(Images.top_left2).getHeight());
        if (RTS.isEnabled()) {
            Menu_InGame.draw_Time(oSB, this.getMenuElement(4).getPosX() + iTranslateX, 0, this.getMenuElement(4).getWidth(), ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING);
            int tSpeedWidth = (this.getMenuElement(4).getWidth() - CFG.PADDING * 5) / 6;
            int tX = (this.getMenuElement(4).getWidth() - tSpeedWidth * 6 - CFG.PADDING * 5) / 2;
            for (int i = 0; i < RTS.SPEED; ++i) {
                Menu_InGame.draw_Speed(oSB, tX + this.getMenuElement(4).getPosX() + (tSpeedWidth + CFG.PADDING) * i + iTranslateX, ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING, tSpeedWidth, CFG.PADDING);
            }
        }
        CFG.game.getPlayer(CFG.PLAYER_TURNID).getFlag().draw(oSB, CFG.topBox.iFlagX + iTranslateX, CFG.topBox.iFlagY - CFG.game.getPlayer(CFG.PLAYER_TURNID).getFlag().getHeight() + this.getMenuPosY() + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
        oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 1.0f));
        ImageManager.getImage(Images.top_civ_color_shader).draw(oSB, CFG.topBox.iFlagX + iTranslateX, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.topBox.iFlagY + iTranslateY);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.top_civ_color).draw(oSB, CFG.topBox.iFlagX + iTranslateX, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.topBox.iFlagY + iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_BG_GAME_MENU_SHADOW);
        if (Menu_InGame_ProvinceInfo.iMaxWidth >= 0) {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.map.getMapBG().getMinimapWidth() + Menu_InGame_ProvinceInfo.iMaxWidth + 1);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY, CFG.map.getMapBG().getMinimapWidth() + Menu_InGame_ProvinceInfo.iMaxWidth + 1, 1);
        } else {
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.GAME_WIDTH);
            ImageManager.getImage(Images.line_32_off1).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY, CFG.GAME_WIDTH, 1);
        }
        oSB.setColor(Color.WHITE);
    }

    protected static final void clickFlagAction() {
        if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            if (!CFG.menuManager.getVisible_InGame_FlagAction()) {
                CFG.game_NextTurnUpdate.updateSpendingsOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).iBudget);
                if (RTS.isEnabled() && !RTS.PAUSE) {
                    RTS.updateTimePast_AfterAction(0.4f);
                }
                if (CFG.menuManager.getVisible_InGame_CivInfo()) {
                    CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
                }
                int reloadGraph = Menu_InGame_GraphManager.iActiveGraphID;
                Menu_InGame_GraphManager.iActiveGraphID = -1;
                Menu_InGame_GraphManager.setActiveGraphID(reloadGraph);
            }
            CFG.menuManager.setVisible_InGame_FlagAction(!CFG.menuManager.getVisible_InGame_FlagAction());
            if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                CFG.gameAction.hideAllViews();
                if (CFG.chooseProvinceMode) {
                    CFG.game.resetChooseProvinceData();
                }
                if (CFG.regroupArmyMode) {
                    CFG.game.resetRegroupArmyData();
                }
            } else {
                if (CFG.viewsManager.getActiveViewID() >= 0) {
                    CFG.viewsManager.getActiveView().enableViewAction();
                }
                CFG.game.checkProvinceActionMenu();
            }
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 1: {
                if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                    int tBalance = CFG.game_NextTurnUpdate.getBalance(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    CFG.toast.setInView(CFG.langManager.get("Balance") + ": " + (tBalance > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + tBalance), tBalance > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (tBalance == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                    CFG.toast.setTimeInView(1500);
                    CFG.menuManager.setVisible_InGame_Budget(!CFG.menuManager.getVisible_InGame_Budget());
                    CFG.menuManager.resetHoverActive();
                    break;
                }
                int tBalance = CFG.game_NextTurnUpdate.getBalance(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                CFG.toast.setInView(CFG.langManager.get("Balance") + ": " + (tBalance > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + tBalance), tBalance > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (tBalance == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                CFG.toast.setTimeInView(1500);
                break;
            }
            case 2: {
                CFG.toast.setInView(CFG.langManager.get("MovementPoints") + ": " + this.getMenuElement(iID).getText(), CFG.COLOR_INGAME_MOVEMENT);
                break;
            }
            case 4: 
            case 5: {
                if (RTS.isEnabled()) {
                    if (!RTS.PAUSE) {
                        RTS.updateTimePast_AfterAction(0.75f);
                    }
                    RTS.pauseUnpause();
                    break;
                }
                if (CFG.menuManager.getVisibleInGame_History()) {
                    CFG.menuManager.setVisibleInGame_History(false);
                } else {
                    CFG.menuManager.rebuildInGame_History();
                }
                ArrayList<String> tempMess = new ArrayList<String>();
                ArrayList<Color> tempColor = new ArrayList<Color>();
                tempMess.add(CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName());
                tempColor.add(CFG.COLOR_TEXT_RANK);
                tempMess.add(Game_Calendar.getCurrentDate());
                tempColor.add(CFG.COLOR_TEXT_CIV_NAME);
                CFG.toast.setInView(tempMess, tempColor);
                break;
            }
            case 6: {
                if (CFG.menuManager.getVisibleInGame_Rank()) {
                    CFG.menuManager.setVisibleInGame_Rank(false);
                    break;
                }
                CFG.menuManager.rebuildInGame_Rank();
                break;
            }
            case 7: {
                Menu_InGame.clickFlagAction();
                if (CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS) break;
                this.getMenuElement(iID).buildElementHover();
                break;
            }
            case 3: 
            case 8: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                    if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                        CFG.menuManager.setVisible_InGame_FlagAction(false);
                    }
                    if (CFG.menuManager.getInGame_Budget().getVisible()) {
                        CFG.menuManager.getInGame_Budget().setVisible(false);
                    }
                    CFG.menuManager.setVisible_InGame_CivInfo(true);
                    CFG.viewsManager.getActiveView().updateActiveCivInfo_ExtraAction(CFG.getActiveCivInfo());
                    break;
                }
                CFG.menuManager.setVisible_InGame_CivInfo(false);
                break;
            }
            case 9: {
                CFG.menuManager.setVisible_InGame_MapModes(!CFG.menuManager.getInGame_MapModes().getVisible());
                if (CFG.menuManager.getInGame_MapModes().getPosX() >= 0) break;
                if (CFG.isAndroid()) {
                    CFG.glyphLayout.setText(CFG.fontMain, "+100% ");
                    int tempMaxTextW = (int)(CFG.glyphLayout.width * 0.7f);
                    int tMenuWidth = ImageManager.getImage(Images.diplo_war).getWidth() / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING + tempMaxTextW + CFG.PADDING;
                    CFG.menuManager.getInGame_MapModes().setPosX_Force(CFG.GAME_WIDTH - CFG.menuManager.getInGame_MapModes().getWidth() - CFG.PADDING - tMenuWidth);
                    CFG.menuManager.getInGame_MapModes().setPosY(CFG.menuManager.getInGame_MapModes().getTitle().getHeight() + this.getMenuElement(iID).getPosY() + this.getMenuElement(iID).getHeight() + CFG.PADDING);
                    if (CFG.menuManager.getInGame_MapModes().getPosX() + CFG.menuManager.getInGame_MapModes().getWidth() <= CFG.GAME_WIDTH - CFG.PADDING) break;
                    CFG.menuManager.getInGame_MapModes().setPosX_Force(CFG.GAME_WIDTH - CFG.PADDING - CFG.menuManager.getInGame_MapModes().getWidth());
                    break;
                }
                CFG.menuManager.getInGame_MapModes().setPosX_Force(this.getMenuElement(iID).getPosX() + this.getMenuElement(iID).getWidth() / 2 - CFG.menuManager.getInGame_MapModes().getWidth() / 2);
                CFG.menuManager.getInGame_MapModes().setPosY(CFG.menuManager.getInGame_MapModes().getTitle().getHeight() + this.getMenuElement(iID).getPosY() + this.getMenuElement(iID).getHeight() + CFG.PADDING);
                if (CFG.menuManager.getInGame_MapModes().getPosX() + CFG.menuManager.getInGame_MapModes().getWidth() <= CFG.GAME_WIDTH - CFG.PADDING) break;
                CFG.menuManager.getInGame_MapModes().setPosX_Force(CFG.GAME_WIDTH - CFG.PADDING - CFG.menuManager.getInGame_MapModes().getWidth());
                break;
            }
            case 10: {
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                break;
            }
            case 11: {
                RTS.updateSpeed(-1);
                break;
            }
            case 12: {
                RTS.updateSpeed(1);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setVisible_InGame_Options(true);
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame();
    }
}

