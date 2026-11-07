/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CurrentWars;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_FlagAction_Bot_Right_Right;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text_Outliner;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Outliner
extends SliderMenu {
    protected final float FONT_SCALE = 0.7f;
    protected static final int ANIMATION_TIME = 135;
    protected static long lTime = 0L;
    protected static boolean hideAnimation = true;

    protected Menu_InGame_Outliner() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH / 2;
        int tElementH = CFG.isAndroid() ? Math.max(CFG.BUTTON_HEIGHT / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4) : CFG.TEXT_HEIGHT + CFG.PADDING * 3;
        int tPosY = 0;
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_VictoryConditions()) {
                    CFG.menuManager.setVisibleInGame_VictoryConditions(false);
                } else {
                    CFG.menuManager.rebuildInGame_VictoryConditions();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_Wars()) {
                    CFG.menuManager.setVisibleInGame_Wars(false);
                } else {
                    CFG.menuManager.rebuildInGame_Wars();
                }
            }

            @Override
            protected void buildElementHover() {
                if (CFG.isDesktop()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F6", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_MilitaryAlliances()) {
                    CFG.menuManager.setVisibleInGame_MilitaryAlliances(false);
                } else {
                    CFG.menuManager.rebuildInGame_MilitaryAlliances();
                }
            }

            @Override
            protected void buildElementHover() {
                if (CFG.isDesktop()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F7", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_Rank()) {
                    CFG.menuManager.setVisibleInGame_Rank(false);
                } else {
                    CFG.menuManager.rebuildInGame_Rank();
                }
            }

            @Override
            protected void buildElementHover() {
                if (CFG.isDesktop()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F9", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_Wonders()) {
                    CFG.menuManager.setVisibleInGame_Wonders(false);
                } else {
                    CFG.menuManager.rebuildInGame_Wonders();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_WorldPopulation()) {
                    CFG.menuManager.setVisibleInGame_WorldPopulation(false);
                } else {
                    CFG.menuManager.rebuildInGame_WorldPopulation();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_HRE()) {
                    CFG.menuManager.setVisibleInGame_HRE(false);
                } else {
                    CFG.menuManager.rebuildInGame_HRE();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_ConquredProvinces()) {
                    CFG.menuManager.setVisibleInGame_ConquredProvinces(false);
                } else {
                    CFG.menuManager.rebuildInGame_ConqueredProvinces();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_BuildingsConstructed()) {
                    CFG.menuManager.setVisibleInGame_BuildingsConstructed(false);
                } else {
                    CFG.menuManager.rebuildInGame_BuildingsConstrcuted();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_Army()) {
                    CFG.menuManager.setVisibleInGame_Army(false);
                } else {
                    CFG.menuManager.rebuildInGame_Army();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_RecruitedArmy()) {
                    CFG.menuManager.setVisibleInGame_RecruitedArmy(false);
                } else {
                    CFG.menuManager.rebuildInGame_RecruitedArmy();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                    CFG.menuManager.setVisibleInGame_CensusOfProvince(false);
                } else if (CFG.game.getActiveProvinceID() >= 0) {
                    CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (CFG.menuManager.getVisibleInGame_History()) {
                    CFG.menuManager.setVisibleInGame_History(false);
                } else {
                    CFG.menuManager.rebuildInGame_History();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                CFG.game.resetChooseProvinceData_Immediately();
                CFG.game.resetRegroupArmyData();
                CFG.menuManager.setViewID(Menu.eTIMELINE);
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 0 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
                    CFG.menuManager.setVisible_Menu_InGame_Graph(false);
                } else {
                    Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 0;
                    CFG.menuManager.rebuildInGame_Graph();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
                    CFG.menuManager.setVisible_Menu_InGame_Graph(false);
                } else {
                    Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 1;
                    CFG.menuManager.rebuildInGame_Graph();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 2 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
                    CFG.menuManager.setVisible_Menu_InGame_Graph(false);
                } else {
                    Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 2;
                    CFG.menuManager.rebuildInGame_Graph();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void actionElement(int iID) {
                if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
                    CFG.menuManager.setVisible_Menu_InGame_Graph(false);
                } else {
                    Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 3;
                    CFG.menuManager.rebuildInGame_Graph();
                }
            }
        });
        menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY += tElementH, tMenuWidth - 2, tElementH){

            @Override
            protected void buildElementHover() {
                if (CFG.isDesktop()) {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("F12", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                } else {
                    this.menuElementHover = null;
                }
            }

            @Override
            protected void actionElement(int iID) {
                CFG.menuManager.setVisibleInGame_Playlist(!CFG.menuManager.getVisibleInGame_Playlist());
            }
        });
        tPosY += tElementH;
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, this.getHeight(), true, false);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, this.getHeight(), true, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 2 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(Images.stats).draw(oSB, nPosX + (int)((float)nWidth - ((float)this.getTextWidth() * 0.7f + (float)ImageManager.getImage(Images.stats).getWidth() + (float)CFG.PADDING)) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (this.getHeight() - ImageManager.getImage(Images.stats).getHeight()) / 2);
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - ((float)this.getTextWidth() * 0.7f + (float)ImageManager.getImage(Images.stats).getWidth() + (float)CFG.PADDING)) / 2 + CFG.PADDING + ImageManager.getImage(Images.stats).getWidth() + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.7f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tMenuWidth, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2, tMenuWidth, Math.min(tElementH * (CFG.isAndroid() ? 4 : 5), ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight()), menuElements, false, true);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
        }
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Stats"));
        int tID = 0;
        this.getMenuElement(tID++).setText(CFG.langManager.get("VictoryConditions"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Wars"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Alliances"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Ranking"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Wonders"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Population"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("HolyRomanEmpire"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("ConqueredProvinces"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("ConstructedBuildings"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Army"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("RecruitedArmy"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Demography"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("History"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Timeline"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Provinces"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Population"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("TechnologyLevel"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("RankScore"));
        this.getMenuElement(tID++).setText(CFG.langManager.get("Audio"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 135L >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX += (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0f))) : (iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0f)));
            CFG.setRender_3(true);
        } else if (hideAnimation) {
            super.setVisible(false);
            CFG.menuManager.getMenu_InGame_CurrentWars().setPosY(ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2);
            CFG.menuManager.getMenu_InGame_CurrentWars_Info().setPosY(CFG.menuManager.getMenu_InGame_CurrentWars().getPosY() - 1 + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight());
            Menu_InGame_CurrentWars.lTime = System.currentTimeMillis();
            return;
        }
        super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX + CFG.PADDING, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.getCloseButtonImage(sliderMenuIsActive).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX, this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5, ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5);
    }

    @Override
    protected void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
            this.setHideAnimation(false);
            CFG.menuManager.getMenu_InGame_CurrentWars().setPosY(this.getPosY() + this.getHeight());
            CFG.menuManager.getMenu_InGame_CurrentWars_Info().setPosY(CFG.menuManager.getMenu_InGame_CurrentWars().getPosY() - 1 + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight());
        } else {
            this.setHideAnimation(true);
        }
    }

    protected final void setHideAnimation(boolean hideAnimation) {
        if (hideAnimation != Menu_InGame_Outliner.hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 135L ? System.currentTimeMillis() - (135L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        Menu_InGame_Outliner.hideAnimation = hideAnimation;
    }
}

