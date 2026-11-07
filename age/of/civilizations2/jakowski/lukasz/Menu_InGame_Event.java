/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players_Anim;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.TextSlider;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_InGame_Event
extends SliderMenu {
    protected static int EVENT_ID = 0;
    private Image lPicture = null;
    protected static final float DATE_FONT_SCALE = 0.65f;
    private String sEventDate = "";
    private int iEventDateWidth = 0;

    protected Menu_InGame_Event(int tInit) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)(512.0f * CFG.GUI_SCALE) + CFG.PADDING * 2;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
    }

    protected Menu_InGame_Event() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)(512.0f * CFG.GUI_SCALE) + CFG.PADDING * 2;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        int tY = CFG.PADDING;
        menuElements.add(new Button_Transparent(CFG.PADDING, tY, tempWidth - CFG.PADDING * 2, (int)(96.0f * CFG.GUI_SCALE), true));
        menuElements.add(new TextSlider(CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), tempWidth - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 2, 0.8f){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT);
            }

            @Override
            protected void drawBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
                oSB.setColor(Color.WHITE);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), this.getHeight() * 3 / 5, false, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.275f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), false, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 4, this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3f));
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, false);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.7f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 4, 1);
                oSB.setColor(Color.BLACK);
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight());
                oSB.setColor(Color.WHITE);
            }
        });
        ((MenuElement)menuElements.get(1)).addText(CFG.langManager.get(CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEvent_PopUp().sText), CFG.PADDING);
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        for (int i = 0; i < CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).lDecisions.size(); ++i) {
            menuElements.add(new Button_New_Game_Players_Anim(CFG.langManager.get(CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).lDecisions.get((int)i).sTitle), -1, CFG.PADDING, tY, tempWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2, true){
                int iCurrent;
                {
                    this.iCurrent = 0;
                }

                @Override
                protected Color getColor(boolean isActive) {
                    return isActive ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS) : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE);
                }

                @Override
                protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getIsHovered() ? 1.0f : 0.8f));
                    super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                    oSB.setColor(Color.WHITE);
                }

                @Override
                protected int getCurrent() {
                    return this.iCurrent;
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    for (int i = 0; i < CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).lDecisions.get((int)this.getCurrent()).lOutcomes.size(); ++i) {
                        List<MenuElement_Hover_v2_Element2> tempElements = CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).lDecisions.get((int)this.getCurrent()).lOutcomes.get(i).getHoverText();
                        for (int j = 0; j < tempElements.size(); ++j) {
                            nElements.add(tempElements.get(j));
                        }
                        tempElements.clear();
                        tempElements = null;
                    }
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
        this.initMenu(new SliderMenuTitle("", CFG.BUTTON_HEIGHT * 3 / 5, true, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color(0.06666667f, 0.3764706f, 0.7529412f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.06666667f, 0.3764706f, 0.7529412f, 0.375f));
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
                CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().draw(oSB, nPosX + (int)((float)nWidth - ((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.flag_rect).getWidth() + (float)CFG.PADDING)) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + (int)((float)nWidth - ((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.flag_rect).getWidth() + (float)CFG.PADDING)) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - ((float)this.getTextWidth() * 0.8f + (float)ImageManager.getImage(Images.flag_rect).getWidth() + (float)CFG.PADDING)) / 2 + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, false);
        this.updateLanguage();
        try {
            this.lPicture = CFG.eventsManager.getEvent(EVENT_ID).getEventPicture().length() == 0 ? new Image(new Texture(Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.game.getGameScenarios().sActiveScenarioTag + "/" + "events/" + "default.png")), Texture.TextureFilter.Linear) : new Image(new Texture(Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.game.getGameScenarios().sActiveScenarioTag + "/" + "events/" + CFG.eventsManager.getEvent(EVENT_ID).getEventPicture())), Texture.TextureFilter.Linear);
        }
        catch (GdxRuntimeException ex) {
            try {
                this.lPicture = new Image(new Texture(Gdx.files.internal("UI/events/" + CFG.eventsManager.getEvent(EVENT_ID).getEventPicture())), Texture.TextureFilter.Linear);
            }
            catch (GdxRuntimeException exr) {
                try {
                    this.lPicture = new Image(new Texture(Gdx.files.internal("UI/events/default.png")), Texture.TextureFilter.Linear);
                }
                catch (GdxRuntimeException exre) {
                    this.lPicture = null;
                }
            }
        }
    }

    @Override
    protected void updateLanguage() {
        try {
            this.getTitle().setText(CFG.langManager.get(CFG.eventsManager.getEvent(EVENT_ID).getEventName()));
        }
        catch (NullPointerException ex) {
            try {
                this.getTitle().setText(CFG.langManager.get("Event"));
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
        }
        if (CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEventDate_Until().iEventYear == 9999999) {
            if (CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEventDate_Since().iEventYear == 9999999) {
                this.sEventDate = Game_Calendar.getCurrentDate();
            } else {
                CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEventDate_Since().iEventDay;
                CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEventDate_Since().iEventMonth;
                CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEventDate_Since().iEventYear;
                this.sEventDate = Game_Calendar.getCurrentDate_CreateEvent();
            }
        } else {
            this.sEventDate = Game_Calendar.getCurrentDate();
        }
        CFG.glyphLayout.setText(CFG.fontMain, this.sEventDate);
        this.iEventDateWidth = (int)(CFG.glyphLayout.width * 0.65f);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        try {
            if (this.getMenuElement(0).getIsHovered()) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
            }
            this.lPicture.draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() / 2 - (int)((float)this.lPicture.getWidth() * CFG.GUI_SCALE) / 2 + iTranslateX, this.getPosY() + this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() / 2 - (int)((float)this.lPicture.getHeight() * CFG.GUI_SCALE) / 2 - this.lPicture.getHeight() + iTranslateY, (int)((float)this.lPicture.getWidth() * CFG.GUI_SCALE), (int)((float)this.lPicture.getHeight() * CFG.GUI_SCALE));
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getMenuElement(0).getWidth(), (int)((float)CFG.TEXT_HEIGHT * 0.65f) + CFG.PADDING * 2);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getMenuElement(0).getWidth() / 3, (int)((float)CFG.TEXT_HEIGHT * 0.65f) + CFG.PADDING * 2);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - this.getMenuElement(0).getWidth() / 3 + iTranslateX, this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getMenuElement(0).getWidth() / 3, (int)((float)CFG.TEXT_HEIGHT * 0.65f) + CFG.PADDING * 2, true, false);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX, this.getPosY() + (int)((float)CFG.TEXT_HEIGHT * 0.65f) + CFG.PADDING * 2 - 1 + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getMenuElement(0).getWidth() / 3, 1);
            ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - this.getMenuElement(0).getWidth() / 3 + iTranslateX, this.getPosY() + (int)((float)CFG.TEXT_HEIGHT * 0.65f) + CFG.PADDING * 2 - 1 + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getMenuElement(0).getWidth() / 3, 1, true, false);
            oSB.setColor(Color.WHITE);
            CFG.fontMain.getData().setScale(0.65f);
            if (CFG.eventsManager.getEvent(EVENT_ID).getCivID() > 0 && CFG.eventsManager.getEvent(EVENT_ID).getCivID() < CFG.game.getCivsSize() && CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).getCapitalProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).getCapitalProvinceID()).getCitiesSize() > 0) {
                CFG.drawText(oSB, CFG.game.getProvince(CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).getCapitalProvinceID()).getCity(0).getCityName(), this.getPosX() + this.getMenuElement(0).getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getMenuElement(0).getPosY() + CFG.PADDING + iTranslateY, new Color(1.0f, 1.0f, 1.0f, 0.8f));
            }
            CFG.drawText(oSB, this.sEventDate, this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - CFG.PADDING - this.iEventDateWidth + iTranslateX, this.getPosY() + this.getMenuElement(0).getPosY() + CFG.PADDING + iTranslateY, new Color(1.0f, 1.0f, 1.0f, 0.8f));
            CFG.fontMain.getData().setScale(1.0f);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.toast.setInView(this.getTitle().getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                return;
            }
            case 1: {
                CFG.toast.setInView(CFG.langManager.get(CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).getEvent_PopUp().sText), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                return;
            }
        }
        CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
        iID -= 2;
        try {
            if (CFG.eventsManager.getEvent(EVENT_ID).getCivID() >= 0) {
                CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).addEvent_DecisionTaken(CFG.eventsManager.getEvent(EVENT_ID).getEventTag() + "_" + iID);
            }
            CFG.eventsManager.getEvent((int)Menu_InGame_Event.EVENT_ID).lDecisions.get(iID).executeDecision();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        CFG.menuManager.setVisibleInGame_Event(false);
        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).runNextEvent();
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible && this.lPicture != null) {
            this.lPicture.getTexture().dispose();
            this.lPicture = null;
        }
    }
}

