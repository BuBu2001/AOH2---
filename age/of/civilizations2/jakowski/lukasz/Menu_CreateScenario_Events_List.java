/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_NewGameBoxStyle_RIGHT_Remove;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle;
import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players_CivID_LEFT;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Event_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateScenario_Events_List
extends SliderMenu {
    protected Menu_CreateScenario_Events_List() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosY = CFG.PADDING;
        menuElements.add(new Button_NewGameStyle(CFG.langManager.get("AddNewEvent"), -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        for (int i = 0; i < CFG.eventsManager.getEventsSize(); ++i) {
            CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.getEvent((int)i).getEventDate_Since().iEventDay;
            CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.getEvent((int)i).getEventDate_Since().iEventMonth;
            CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.getEvent((int)i).getEventDate_Since().iEventYear;
            menuElements.add(new Button_New_Game_Players_CivID_LEFT(CFG.eventsManager.getEvent(i).getCivID(), CFG.eventsManager.getEvent(i).getEventName() + (CFG.eventsManager.getEvent(i).getCivID() > 0 && CFG.eventsManager.getEvent(i).getCivID() < CFG.game.getCivsSize() ? ", " + CFG.game.getCiv(CFG.eventsManager.getEvent(i).getCivID()).getCivName() : "") + ", " + (CFG.eventsManager.iCreateEvent_Year == 9999999 ? CFG.langManager.get("NoDate") : Game_Calendar.getCurrentDate_CreateEvent()), CFG.PADDING * 2, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){
                int iCurrent;
                {
                    this.iCurrent = 0;
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
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Title") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.eventsManager.getEvent(this.getCurrent()).getEventName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    try {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecipientCiv") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.eventsManager.getEvent(this.getCurrent()).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.eventsManager.getEvent(this.getCurrent()).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        // empty catch block
                    }
                    CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.getEvent((int)this.getCurrent()).getEventDate_Since().iEventDay;
                    CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.getEvent((int)this.getCurrent()).getEventDate_Since().iEventMonth;
                    CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.getEvent((int)this.getCurrent()).getEventDate_Since().iEventYear;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Since") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.eventsManager.iCreateEvent_Year == 9999999 ? CFG.langManager.get("NoDate") : Game_Calendar.getCurrentDate_CreateEvent(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.getEvent((int)this.getCurrent()).getEventDate_Until().iEventDay;
                    CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.getEvent((int)this.getCurrent()).getEventDate_Until().iEventMonth;
                    CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.getEvent((int)this.getCurrent()).getEventDate_Until().iEventYear;
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Until") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.eventsManager.iCreateEvent_Year == 9999999 ? CFG.langManager.get("NoDate") : Game_Calendar.getCurrentDate_CreateEvent(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
            menuElements.add(new Button_Game_NewGameBoxStyle_RIGHT_Remove(tempW - CFG.PADDING - (int)((float)CFG.BUTTON_HEIGHT * 0.6f), tPosY, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
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
                ImageManager.getImage(Images.time).draw(oSB, nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 - CFG.PADDING - ImageManager.getImage(Images.time).getWidth() + iTranslateX, 2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.time).getHeight() / 2);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, CFG.BUTTON_HEIGHT + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5, tempW, Math.min(tPosY, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5) - CFG.PADDING * 2), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Events"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), false, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.eventsManager.addEvent(new Event_GameData());
                CFG.eventsManager.iCreateEvent_EditEventID = CFG.eventsManager.getEventsSize() - 1;
                CFG.eventsManager.lCreateScenario_Event = CFG.eventsManager.getEvent(CFG.eventsManager.iCreateEvent_EditEventID);
                CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
                return;
            }
        }
        if (--iID % 2 == 0) {
            CFG.eventsManager.iCreateEvent_EditEventID = iID / 2;
            CFG.eventsManager.lCreateScenario_Event = CFG.eventsManager.getEvent(CFG.eventsManager.iCreateEvent_EditEventID);
            CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
        } else {
            CFG.eventsManager.iCreateEvent_EditEventID = iID / 2;
            CFG.setDialogType(Dialog.CREATE_SCENARIO_REMOVE_EVENT);
        }
    }
}

