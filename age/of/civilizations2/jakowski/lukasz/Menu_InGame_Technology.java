/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Diplomacy;
import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button_Icon;
import age.of.civilizations2.jakowski.lukasz.Button_Technology;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot;
import age.of.civilizations2.jakowski.lukasz.SkillsManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear_Adm;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear_Tech;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_Technology
extends SliderMenu {
    protected int iCivID;
    protected long lTime = 0L;

    protected Menu_InGame_Technology() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = CFG.PADDING;
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("TechnologyPoints"), CFG.BUTTON_HEIGHT * 3 / 5, true, true), CFG.GAME_WIDTH / 2 - tempWidth * 3 / 8, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
        this.updateLanguage();
    }

    protected Menu_InGame_Technology(int nCivID) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
        int tY = 0;
        this.iCivID = nCivID;
        menuElements.add(new Button_Technology(nCivID, 2, tY, CFG.BUTTON_WIDTH * 2){

            @Override
            protected int getWidth() {
                return Menu_InGame_Technology.this.getElementW() * 2;
            }
        });
        menuElements.add(new Button_Icon(Images.population_growth, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Tech(0.75f, CFG.langManager.get("PopulationGrowthModifier"), Button_Diplomacy.iDiploWidth + CFG.PADDING, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 25, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_POP_GROWTH){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_POPULATION.r, CFG.COLOR_TEXT_POPULATION.g, CFG.COLOR_TEXT_POPULATION.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 6, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_PopGrowth(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_POP_GROWTH);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.economy, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Tech(0.75f, CFG.langManager.get("EconomyGrowthModifier"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 25, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ECONOMY_GROWTH){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_ECONOMY.r, CFG.COLOR_TEXT_ECONOMY.g, CFG.COLOR_TEXT_ECONOMY.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 6, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_EcoGrowth(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_ECONOMY_GROWTH);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.top_gold, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Tech(0.2f, CFG.langManager.get("IncomeTaxation"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 25, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_INCOME_TAXATION){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.2%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_IncomeTaxation(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_INCOME_TAXATION);
                Menu_InGame_Technology.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE && CFG.menuManager.getVisible_InGame_View_Stats()) {
                    CFG.menuManager.setVisible_InGame_ViewIncome(true);
                }
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.development, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Tech(0.25f, CFG.langManager.get("IncomeProduction"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 25, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_INCOME_PRODUCTION){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_INGAME_GOLD_ACTIVE.r, CFG.COLOR_INGAME_GOLD_ACTIVE.g, CFG.COLOR_INGAME_GOLD_ACTIVE.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.25%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_IncomeProduction(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_INCOME_PRODUCTION);
                Menu_InGame_Technology.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE && CFG.menuManager.getVisible_InGame_View_Stats()) {
                    CFG.menuManager.setVisible_InGame_ViewIncome(true);
                }
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.top_gold2, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Adm(0.3f, CFG.langManager.get("Administration"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 20, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_ADMINISTRATION){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Administration") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.3%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_Administration(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_ADMINISTRATION);
                Menu_InGame_Technology.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
                if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE && CFG.menuManager.getVisible_InGame_View_Stats()) {
                    CFG.menuManager.setVisible_InGame_ViewIncome(true);
                }
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.diplo_army, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Adm(0.35f, CFG.langManager.get("MilitaryUpkeep"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 30, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_MILITARY_UPKEEP){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_ARMY_TEXT.r, CFG.COLOR_ARMY_TEXT.g, CFG.COLOR_ARMY_TEXT.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.35%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_MilitaryUpkeep(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_MILITARY_UPKEEP);
                Menu_InGame_Technology.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.provinces, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Adm(1.0f, CFG.langManager.get("ColonizationCost"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 15, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_COLONIZATION){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.r, CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.g, CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationCost") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_Colonization(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_COLONIZATION);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Button_Icon(Images.research, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING));
        menuElements.add(new Slider_FlagAction_Clear_Tech(0.75f, CFG.langManager.get("Research"), CFG.PADDING + Button_Diplomacy.iDiploWidth, tY, tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6, 0, 30, CFG.game.getCiv((int)this.iCivID).civGameData.skills.POINTS_RESEARCH){

            @Override
            protected int getWidth() {
                return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_TEXT_RESEARCH.r, CFG.COLOR_TEXT_RESEARCH.g, CFG.COLOR_TEXT_RESEARCH.b, 0.65f);
            }

            @Override
            protected void actionElement(int iID) {
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4, true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void actionElement(int iID) {
                SkillsManager.add_Research(Menu_InGame_Technology.this.iCivID);
                Menu_InGame_Technology.this.getMenuElement(0).setMin(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
                Menu_InGame_Technology.this.getMenuElement(iID - 1).setCurrent(CFG.game.getCiv((int)Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_RESEARCH);
                Menu_InGame_Technology.this.rebuildBudgetView();
                Menu_InGame.updateOverBudget();
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Close"), -1, CFG.PADDING, tY += CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            protected int getWidth() {
                return Menu_InGame_Technology.this.getElementW() * 2 - CFG.PADDING * 2;
            }
        });
        int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("TechnologyPoints"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true, false);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0f, (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0f, 0.375f));
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
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth / 2 + CFG.PADDING + (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(), (int)(((float)(nWidth - CFG.PADDING * 6) - (float)this.getTextWidth() * 0.8f) / 2.0f), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING + tempMenuPosY > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6) : ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, true, true);
        this.updateLanguage();
        this.lTime = System.currentTimeMillis();
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(this.getMenuElement(i).getCurrent());
        }
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + 200L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() - 2, CFG.GAME_HEIGHT - this.getPosY(), this.getWidth() + 4, -((int)((float)(this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - this.lTime) / 200.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            CFG.setRender_3(true);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4, this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, this.getHeight() / 4);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - 4, 1);
            oSB.setColor(Color.WHITE);
            this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            oSB.setColor(Color.WHITE);
            this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            this.setVisible(false);
            return;
        }
        this.getMenuElement(iID).actionElement(iID);
    }

    protected final int getW() {
        return this.getWidth() - 4;
    }

    protected final int getElementW() {
        return this.getW() / 2;
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                this.getMenuElement(i).setVisible(false);
            }
        }
    }

    protected void rebuildBudgetView() {
        if (CFG.menuManager.getVisible_InGame_Budget()) {
            CFG.menuManager.setVisible_InGame_Budget(true);
            Menu_InGame_FlagAction_Bot.lTime = 1L;
        } else if (CFG.menuManager.getVisible_InGame_FlagAction()) {
            CFG.menuManager.rebuildInGame_FlagActionLeft();
        } else {
            CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        }
        CFG.menuManager.setOrderOfTechPoints();
    }
}

