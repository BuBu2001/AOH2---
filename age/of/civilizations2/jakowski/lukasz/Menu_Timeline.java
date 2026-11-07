/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Speed;
import age.of.civilizations2.jakowski.lukasz.Button_Speed_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
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
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.TimelapseManager;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Timeline
extends SliderMenu {
    protected Menu_Timeline() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text(null, 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (TimelapseManager.PAUSE) {
                    CFG.fontMain.getData().setScale(0.8f);
                    CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + 1 + iTranslateY, this.getColor(isActive));
                    CFG.fontMain.getData().setScale(1.0f);
                }
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
                return Menu_Timeline.this.getMenuElement(1).getPosX();
            }

            @Override
            protected int getWidth() {
                return Menu_Timeline.this.getMenuElement(1).getWidth();
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
                return Menu_Timeline.this.getMenuElement(1).getPosX() - this.getWidth();
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
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(Game_Calendar.getDate_ByTurnID(CFG.timelapseManager.iTimelineTurnID + 1));
        this.getMenuElement(2).setText(CFG.langManager.get("Turn") + ": " + (CFG.timelapseManager.iTimelineTurnID + 1));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (!TimelapseManager.PAUSE) {
            CFG.timelapseManager.updateTime();
        }
        if (TimelapseManager.PAUSE) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.TEXT_HEIGHT);
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.TEXT_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.TEXT_HEIGHT, false, true);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
            ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left2_sha).getHeight(), true, true);
            if (this.getMenuElement(0).getIsHovered()) {
                ImageManager.getImage(Images.top_left3).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left3).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left3).getHeight(), true, true);
            } else {
                ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(), ImageManager.getImage(Images.top_left2).getHeight(), true, true);
            }
            oSB.setColor(Color.WHITE);
        } else {
            CFG.setRender_3(true);
        }
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(3).getPosX() - ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 - CFG.PADDING + iTranslateX, -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - this.getMenuElement(3).getPosX()), ImageManager.getImage(Images.top_left2_sha).getHeight());
        ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(3).getPosX() - ImageManager.getImage(Images.top_left2).getWidth() / 2 - CFG.PADDING + iTranslateX, -ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - this.getMenuElement(3).getPosX()), ImageManager.getImage(Images.top_left2).getHeight());
        Menu_Timeline.draw_Time(oSB, this.getMenuElement(1).getPosX() + iTranslateX, 0, this.getMenuElement(1).getWidth(), ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING);
        int tSpeedWidth = (this.getMenuElement(1).getWidth() - CFG.PADDING * 5) / 6;
        int tX = (this.getMenuElement(1).getWidth() - tSpeedWidth * 6 - CFG.PADDING * 5) / 2;
        for (int i = 0; i < TimelapseManager.SPEED; ++i) {
            Menu_InGame.draw_Speed(oSB, tX + this.getMenuElement(1).getPosX() + (tSpeedWidth + CFG.PADDING) * i + iTranslateX, ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING, tSpeedWidth, CFG.PADDING);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: 
            case 2: {
                CFG.timelapseManager.pauseUnpause();
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
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eINGAME);
        CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
        CFG.timelapseManager.clearTimeline();
    }
}

