/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Middle;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Right;
import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players_Special;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Calendar;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Leader_Edit_Data
extends SliderMenu {
    private String sName;
    private String sImage;
    private String sBorn;
    private String sWiki;

    protected Menu_Leader_Edit_Data() {
        int tempW = (int)((float)CFG.CIV_INFO_MENU_WIDTH * 1.25f);
        int tempElemH = CFG.BUTTON_HEIGHT;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_New_Game_Players_Special(CFG.leader_GameData.getLeaderOfCiv().getName(), CFG.PADDING * 2, CFG.PADDING + 2, tY, tempW - CFG.PADDING * 2 - 2, true){

            @Override
            protected String getTextToDraw() {
                return Menu_Leader_Edit_Data.this.sName + ": " + super.getText();
            }
        });
        menuElements.add(new Button_New_Game_Players_Special(CFG.leader_GameData.getLeaderOfCiv().getImage(), CFG.PADDING * 2, CFG.PADDING + 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - 2, true){

            @Override
            protected String getTextToDraw() {
                return Menu_Leader_Edit_Data.this.sImage + ": " + super.getText();
            }
        });
        menuElements.add(new Button_New_Game_Players_Special(CFG.leader_GameData.getLeaderOfCiv().getDay() + " " + Game_Calendar.getMonthName(CFG.leader_GameData.getLeaderOfCiv().getMonth()) + " " + CFG.gameAges.getYear(CFG.leader_GameData.getLeaderOfCiv().getYear()), CFG.PADDING * 2, CFG.PADDING + 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - 2, true){

            @Override
            protected String getTextToDraw() {
                return Menu_Leader_Edit_Data.this.sBorn + ": " + super.getText();
            }
        });
        menuElements.add(new Button_New_Game_Players_Special(CFG.leader_GameData.getLeaderOfCiv().getWiki(), CFG.PADDING * 2, CFG.PADDING + 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - 2, true){

            @Override
            protected String getTextToDraw() {
                return Menu_Leader_Edit_Data.this.sWiki + ": " + super.getText();
            }
        });
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle(null, -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f), tY, tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, CFG.PADDING + (int)((float)CFG.BUTTON_HEIGHT * 0.8f) + (tempW - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.8f) * 2), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.8f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_Leader_Edit_Data.this.getPosX() - 2 + iTranslateX, Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_Leader_Edit_Data.this.getWidth() + 2, this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.425f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_Leader_Edit_Data.this.getPosX() + iTranslateX, Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_Leader_Edit_Data.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_Leader_Edit_Data.this.getPosX() + iTranslateX, Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING, Menu_Leader_Edit_Data.this.getWidth(), CFG.PADDING, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_Leader_Edit_Data.this.getPosX() + iTranslateX, Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_Leader_Edit_Data.this.getWidth());
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.9f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_Leader_Edit_Data.this.getPosX() + iTranslateX, Menu_Leader_Edit_Data.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_Leader_Edit_Data.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 4, tempW, Math.min(((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 3), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("Name");
        this.sImage = CFG.langManager.get("ImageName");
        this.sBorn = CFG.langManager.get("Born");
        this.sWiki = CFG.langManager.get("Wiki");
        this.getTitle().setText(CFG.langManager.get("Leader"));
        this.getMenuElement(2).setText(Game_Calendar.currentDay + " " + Game_Calendar.getMonthName(Game_Calendar.currentMonth) + " " + CFG.gameAges.getYear(Game_Calendar.currentYear));
        this.getMenuElement(5).setText(CFG.langManager.get("PopulationGrowthModifier") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth * 100.0f) + "%");
        this.getMenuElement(8).setText(CFG.langManager.get("EconomyGrowthModifier") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth * 100.0f) + "%");
        this.getMenuElement(11).setText(CFG.langManager.get("IncomeTaxation") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation * 100.0f) + "%");
        this.getMenuElement(14).setText(CFG.langManager.get("IncomeProduction") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction * 100.0f) + "%");
        this.getMenuElement(17).setText(CFG.langManager.get("Administration") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration * 100.0f) + "%");
        this.getMenuElement(20).setText(CFG.langManager.get("Research") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_Research > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_Research * 100.0f) + "%");
        this.getMenuElement(23).setText(CFG.langManager.get("MilitaryUpkeep") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep * 100.0f) + "%");
        this.getMenuElement(26).setText(CFG.langManager.get("AttackBonus") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus * 100.0f) + "%");
        this.getMenuElement(29).setText(CFG.langManager.get("DefenseBonus") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus * 100.0f) + "%");
        this.getMenuElement(32).setText(CFG.langManager.get("MovementPoints") + ": " + (CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints > 0.0f ? "+" : "") + (int)(CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints * 100.0f) + "%");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), true, true);
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
                CFG.showKeyboard();
                return;
            }
            case 1: {
                CFG.showKeyboard();
                return;
            }
            case 2: {
                CFG.menuManager.saveLeader_Edit_Data();
                CFG.backToMenu = Menu.eGAME_LEADERS_EDIT;
                CFG.menuManager.setViewID(Menu.eSCENARIO_AGE);
                CFG.menuManager.updateSelecetScenarioAge_Slider();
                return;
            }
            case 3: {
                CFG.showKeyboard();
                return;
            }
            case 4: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 5: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 6: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_PopGrowth += 0.01f;
                this.updateLanguage();
                return;
            }
            case 7: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 8: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 9: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_EconomyGrowth += 0.01f;
                this.updateLanguage();
                return;
            }
            case 10: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 11: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 12: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeTaxation += 0.01f;
                this.updateLanguage();
                return;
            }
            case 13: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 14: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 15: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_IncomeProduction += 0.01f;
                this.updateLanguage();
                return;
            }
            case 16: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 17: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 18: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_Administration += 0.01f;
                this.updateLanguage();
                return;
            }
            case 19: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_Research -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 20: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 21: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_Research += 0.01f;
                this.updateLanguage();
                return;
            }
            case 22: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 23: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 24: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_MilitaryUpkeep += 0.01f;
                this.updateLanguage();
                return;
            }
            case 25: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 26: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 27: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_AttackBonus += 0.01f;
                this.updateLanguage();
                return;
            }
            case 28: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 29: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 30: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_DefenseBonus += 0.01f;
                this.updateLanguage();
                return;
            }
            case 31: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints -= 0.01f;
                this.updateLanguage();
                return;
            }
            case 32: {
                CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                return;
            }
            case 33: {
                CFG.leader_GameData.getLeaderOfCiv().fModifier_MovementPoints += 0.01f;
                this.updateLanguage();
                return;
            }
        }
    }
}

