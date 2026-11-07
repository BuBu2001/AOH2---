/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_Circle;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivInfo;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_CivInfo;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Happiness;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Icon;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Ideology;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Stability;
import age.of.civilizations2.jakowski.lukasz.ViewsManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CivInfo_Stats
extends SliderMenu {
    protected Menu_InGame_CivInfo_Stats() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
            }
        });
        menuElements.add(new Text_LeftSide_Icon("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3, Images.population){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_POPULATION_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION_HOVER : CFG.COLOR_TEXT_POPULATION) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING)){

            @Override
            protected void buildElementHover() {
                try {
                    this.menuElementHover = CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID()) ? CFG.game.getHover_CapitalCity(CFG.getActiveCivInfo()) : null) : CFG.game.getHover_CapitalCity(CFG.getActiveCivInfo());
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Text_LeftSide_Icon("3", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING), Images.city){

            @Override
            protected void buildElementHover() {
                try {
                    this.menuElementHover = CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID()) ? CFG.game.getHover_CapitalCity(CFG.getActiveCivInfo()) : null) : CFG.game.getHover_CapitalCity(CFG.getActiveCivInfo());
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2){

            @Override
            protected void buildElementHover() {
                try {
                    this.menuElementHover = CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(Menu_InGame_CivInfo_Stats.this.getMenuElement(5).getCurrent()) ? CFG.game.getHover_LargestCity(Menu_InGame_CivInfo_Stats.this.getMenuElement(5).getCurrent()) : null) : CFG.game.getHover_LargestCity(Menu_InGame_CivInfo_Stats.this.getMenuElement(5).getCurrent());
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new Text_LeftSide("3", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2){
            int iCurrent;

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
                try {
                    this.menuElementHover = CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(Menu_InGame_CivInfo_Stats.this.getMenuElement(5).getCurrent()) ? CFG.game.getHover_LargestCity(Menu_InGame_CivInfo_Stats.this.getMenuElement(5).getCurrent()) : null) : CFG.game.getHover_LargestCity(Menu_InGame_CivInfo_Stats.this.getMenuElement(5).getCurrent());
                }
                catch (IndexOutOfBoundsException ex) {
                    this.menuElementHover = null;
                }
            }
        });
        ArrayList<Integer> lData = new ArrayList<Integer>();
        ArrayList<Integer> lCivs = new ArrayList<Integer>();
        lData.add(18);
        lCivs.add(1);
        lData.add(7);
        lCivs.add(2);
        menuElements.add(new Graph_Circle(CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING, lData, lCivs, null){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_TechnologyLevel(CFG.getActiveCivInfo());
            }
        });
        menuElements.add(new Text_LeftSide_Icon("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3, Images.technology){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_TechnologyLevel(CFG.getActiveCivInfo());
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 4 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3));
        menuElements.add(new Text_LeftSide_Icon("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 4 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3, Images.economy){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_ECONOMY_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_ECONOMY_HOVER : CFG.COLOR_TEXT_ECONOMY) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_LeftSide_Happiness("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, ((MenuElement)menuElements.get(6)).getPosY() + ((MenuElement)menuElements.get(6)).getHeight() - CFG.TEXT_HEIGHT * 2 - CFG.PADDING){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Text_LeftSide_Ideology("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, ((MenuElement)menuElements.get(6)).getPosY() + ((MenuElement)menuElements.get(6)).getHeight() - CFG.TEXT_HEIGHT){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.ideologiesManager.getIdeologyHover(CFG.getActiveCivInfo());
            }
        });
        menuElements.add(new Text_LeftSide_Stability("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, ((MenuElement)menuElements.get(6)).getPosY() + ((MenuElement)menuElements.get(6)).getHeight() - CFG.TEXT_HEIGHT * 3 - CFG.PADDING * 2){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Stability") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Transparent(0, 0, CFG.CIV_INFO_MENU_WIDTH, ((MenuElement)menuElements.get(menuElements.size() - 4)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 4)).getHeight() + CFG.PADDING, true));
        int tempPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3;
        this.initMenu(null, 0 + AoCGame.LEFT, tempPosY, CFG.CIV_INFO_MENU_WIDTH, Math.min(CFG.GAME_HEIGHT - tempPosY - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT - CFG.PADDING, ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2), menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Population"));
        this.getMenuElement(2).setText(CFG.langManager.get("Capital"));
        this.getMenuElement(4).setText(CFG.langManager.get("LargestCity"));
        this.getMenuElement(7).setText(CFG.langManager.get("TechnologyLevel"));
        this.getMenuElement(9).setText(CFG.langManager.get("Economy"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_CivInfo.lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = Menu_InGame_CivInfo.hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (Menu_InGame_CivInfo.hideAnimation) {
            super.setVisible(false);
            return;
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2, true, true);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        CFG.drawRect_InfoBox_Left(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(1).getPosX() - this.getMenuElement(0).getPosX() + this.getMenuElement(1).getWidth() + CFG.PADDING * 2, this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2);
        try {
            if (CFG.activeCivLeader != null) {
                float fScale = ((float)(this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2) - 2.0f) / (float)CFG.activeCivLeader.getHeight();
                CFG.activeCivLeader.draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - CFG.activeCivLeader.getHeight() + 1 + iTranslateY, (int)((float)CFG.activeCivLeader.getWidth() * fScale), this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.575f));
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY, (int)((float)CFG.activeCivLeader.getWidth() * fScale), CFG.PADDING);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + 1 + (this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2) - CFG.PADDING + iTranslateY, (int)((float)CFG.activeCivLeader.getWidth() * fScale), CFG.PADDING, false, true);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, CFG.PADDING, this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + (int)((float)CFG.activeCivLeader.getWidth() * fScale) - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, CFG.PADDING, this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2, true, false);
                oSB.setColor(new Color(CFG.COLOR_TEXT_NUM_OF_PROVINCES.r, CFG.COLOR_TEXT_NUM_OF_PROVINCES.g, CFG.COLOR_TEXT_NUM_OF_PROVINCES.b, 0.175f));
                CFG.drawRect(oSB, this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, (int)((float)CFG.activeCivLeader.getWidth() * fScale) - 1, this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        CFG.drawRect_InfoBox_Left(oSB, this.getMenuElement(7).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(7).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(8).getPosX() - this.getMenuElement(7).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING * 2, this.getMenuElement(9).getPosY() + this.getMenuElement(9).getHeight() - this.getMenuElement(7).getPosY() + CFG.PADDING * 2);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight() + 2, true, true);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 0: 
            case 1: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_POPULATION_OF_CIV_MODE);
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_POPULATION_OF_CIV_MODE) break;
                CFG.toast.setInView(CFG.langManager.get("Population"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 2: 
            case 3: {
                try {
                    if (CFG.FOG_OF_WAR == 2) {
                        if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID())) break;
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                        break;
                    }
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
                break;
            }
            case 4: 
            case 5: {
                try {
                    int nLargestCity = CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(0);
                    for (int i = 1; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces(); ++i) {
                        if (CFG.game.getProvince(nLargestCity).getPopulationData().getPopulation() >= CFG.game.getProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i)).getPopulationData().getPopulation()) continue;
                        nLargestCity = CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i);
                    }
                    if (CFG.FOG_OF_WAR == 2) {
                        if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(nLargestCity)) break;
                        CFG.map.getMapCoordinates().centerToProvinceID(nLargestCity);
                        CFG.game.setActiveProvinceID(nLargestCity);
                        break;
                    }
                    CFG.map.getMapCoordinates().centerToProvinceID(nLargestCity);
                    CFG.game.setActiveProvinceID(nLargestCity);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
                break;
            }
            case 6: {
                if (CFG.activeCivLeader == null) break;
                this.getMenuElement(iID).setVisible(false);
                this.getMenuElement((int)iID).menuElementHover = null;
                break;
            }
            case 7: 
            case 8: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_TECHNOLOGY_MODE, false);
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_TECHNOLOGY_MODE) break;
                CFG.toast.setInView(CFG.langManager.get("TechnologyLevel"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 9: 
            case 10: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_ECONOMY_MODE);
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_ECONOMY_MODE) break;
                CFG.toast.setInView(CFG.langManager.get("Economy"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 11: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_HAPPINESS_MODE);
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_HAPPINESS_MODE) break;
                CFG.toast.setInView(CFG.langManager.get("Happiness"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 12: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_IDEOLOGIES_MODE);
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_IDEOLOGIES_MODE) break;
                CFG.toast.setInView(CFG.langManager.get("Governments"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                break;
            }
            case 13: {
                CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_PROVINCE_STABILITY_MODE);
                if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_PROVINCE_STABILITY_MODE) break;
                CFG.toast.setInView(CFG.langManager.get("ProvinceStability"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
        }
    }
}

