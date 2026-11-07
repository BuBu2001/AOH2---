/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_ArrowLeft;
import age.of.civilizations2.jakowski.lukasz.Button_ArrowRight;
import age.of.civilizations2.jakowski.lukasz.Button_CalendarDay;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Calendar
extends SliderMenu {
    protected Menu_Calendar() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempW = CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 2 / 3 * 7 + CFG.PADDING * 6;
        menuElements.add(new Button_ArrowLeft(0, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT * 2 / 3){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getMonthName(Game_Calendar.currentMonth - 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_ArrowRight(tempW - CFG.BUTTON_HEIGHT, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT * 2 / 3){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getMonthName(Game_Calendar.currentMonth + 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text(Game_Calendar.getMonthName(Game_Calendar.currentMonth), -1, CFG.BUTTON_HEIGHT, CFG.PADDING, tempW - CFG.BUTTON_HEIGHT * 2, CFG.BUTTON_HEIGHT * 2 / 3){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        int tX = 0;
        int tH = CFG.BUTTON_HEIGHT * 2 / 3 + CFG.PADDING * 2;
        for (int i = 0; i < Game_Calendar.getNumOfDaysInMonth(Game_Calendar.currentMonth); ++i) {
            menuElements.add(new Button_CalendarDay(i + 1, CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 2 / 3 * tX + CFG.PADDING * tX++, tH));
            if (tX != 7) continue;
            tH += CFG.PADDING + CFG.BUTTON_HEIGHT / 2;
            tX = 0;
        }
        menuElements.add(new Button_Transparent(0, 0, tempW, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), true));
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Date"), CFG.BUTTON_HEIGHT * 3 / 5, true, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, nPosX + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth() + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight(), true);
                oSB.setColor(new Color(0.05490196f, 0.07058824f, 0.14901961f, 0.775f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, (this.getHeight() - 2) * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, CFG.PADDING * 2, false, true);
                oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
                ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, ImageManager.getImage(Images.pix255_255_255).getHeight());
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5 + CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 8, tempW, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, menuElements, false, true);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), true, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, CFG.PADDING * 3);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void actionElement(int iID) {
        if (iID != this.getMenuElementsSize() - 1) {
            if (iID == 0) {
                Game_Calendar.minusMonth();
                CFG.menuManager.rebuildScenario_Age_Calendar();
                CFG.menuManager.updateScenario_Age_Date();
                ArrayList<String> tMess = new ArrayList<String>();
                ArrayList<Color> tColor = new ArrayList<Color>();
                tMess.add(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getName());
                tColor.add(Color.WHITE);
                tMess.add(Game_Calendar.getCurrentDate());
                tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess, tColor);
            } else if (iID == 1) {
                Game_Calendar.plusMonth();
                CFG.menuManager.rebuildScenario_Age_Calendar();
                CFG.menuManager.updateScenario_Age_Date();
                ArrayList<String> tMess = new ArrayList<String>();
                ArrayList<Color> tColor = new ArrayList<Color>();
                tMess.add(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getName());
                tColor.add(Color.WHITE);
                tMess.add(Game_Calendar.getCurrentDate());
                tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess, tColor);
            } else if (iID == 2) {
                CFG.toast.setInView(Game_Calendar.getCurrentDate());
                ArrayList<String> tMess = new ArrayList<String>();
                ArrayList<Color> tColor = new ArrayList<Color>();
                tMess.add(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getName());
                tColor.add(Color.WHITE);
                tMess.add(Game_Calendar.getCurrentDate());
                tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess, tColor);
            } else {
                Game_Calendar.currentDay = iID - 2;
                CFG.menuManager.updateScenario_Age_Date();
                ArrayList<String> tMess = new ArrayList<String>();
                ArrayList<Color> tColor = new ArrayList<Color>();
                tMess.add(CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getName());
                tColor.add(Color.WHITE);
                tMess.add(Game_Calendar.getCurrentDate());
                tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                CFG.toast.setInView(tMess, tColor);
            }
        }
    }
}

