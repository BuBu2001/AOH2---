/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_PlayAsVassal;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Graph_Circle;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_CivInfo;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Happiness;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Icon;
import age.of.civilizations2.jakowski.lukasz.Text_LeftSide_Ideology;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CreateAVassal_Info_Stats
extends SliderMenu {
    protected Menu_InGame_CreateAVassal_Info_Stats() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv_CreateAVassal();
            }
        });
        menuElements.add(new Text_LeftSide_Icon("0", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3, Images.population){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv_CreateAVassal();
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_POPULATION_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION_HOVER : CFG.COLOR_TEXT_POPULATION) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING)){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_CapitalCity_ByProvinceID(CFG.createVassal_Data.iCapitalProvinceID);
            }
        });
        menuElements.add(new Text_LeftSide("-", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING)){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_CapitalCity_ByProvinceID(CFG.createVassal_Data.iCapitalProvinceID);
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_LargestCity(Menu_InGame_CreateAVassal_Info_Stats.this.getMenuElement(5).getCurrent());
            }
        });
        menuElements.add(new Text_LeftSide("-", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2){
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
                this.menuElementHover = CFG.game.getHover_LargestCity(this.getCurrent());
            }
        });
        ArrayList<Integer> lData = new ArrayList<Integer>();
        ArrayList<Integer> lCivs = new ArrayList<Integer>();
        lData.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
        lCivs.add(1);
        menuElements.add(new Graph_Circle(CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING, lData, lCivs, null){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_PopulationOfCiv_CreateAVassal();
            }
        });
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3){});
        menuElements.add(new Text_LeftSide_Icon("" + (float)((int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 0.72f * 100.0f)) / 100.0f, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3, Images.technology){});
        menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 4 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3));
        menuElements.add(new Text_LeftSide_Icon("0", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 4 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3, Images.economy){

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
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(0);
        menuElements.add(new Text_LeftSide_Ideology("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 3 - 2, ((MenuElement)menuElements.get(6)).getPosY() + ((MenuElement)menuElements.get(6)).getHeight() - CFG.TEXT_HEIGHT){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Government") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID());
        menuElements.add(new Button_PlayAsVassal("", CFG.PADDING, CFG.PADDING * 2, CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 5 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true, CFG.createVassal_Data != null ? CFG.createVassal_Data.playAsVassal : false));
        menuElements.add(new Button_Transparent(0, 0, CFG.CIV_INFO_MENU_WIDTH, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight(), true));
        int tempPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT + CFG.PADDING * 3;
        this.initMenu(null, 0 + AoCGame.LEFT, tempPosY, CFG.CIV_INFO_MENU_WIDTH, Math.min(CFG.GAME_HEIGHT - tempPosY - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT - CFG.PADDING, ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2), menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Population"));
        this.getMenuElement(2).setText(CFG.langManager.get("Capital"));
        this.getMenuElement(4).setText(CFG.langManager.get("LargestCity"));
        this.getMenuElement(7).setText(CFG.langManager.get("TechnologyLevel"));
        this.getMenuElement(9).setText(CFG.langManager.get("Economy"));
        this.getMenuElement(13).setText(CFG.langManager.get("PlayAsAReleasedVassal"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight() + 2, true, true);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        CFG.drawRect_InfoBox_Left(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(1).getPosX() - this.getMenuElement(0).getPosX() + this.getMenuElement(1).getWidth() + CFG.PADDING * 2, this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2);
        CFG.drawRect_InfoBox_Left(oSB, this.getMenuElement(7).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(7).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(8).getPosX() - this.getMenuElement(7).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING * 2, this.getMenuElement(9).getPosY() + this.getMenuElement(9).getHeight() - this.getMenuElement(7).getPosY() + CFG.PADDING * 2);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + 1, this.getWidth() - 2, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 + this.getHeight(), this.getWidth() - 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + 2 + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
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
        CFG.menuManager.setOrderOfMenu_InGame_CreateAVassal_Info();
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            case 13: {
                try {
                    this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
                    CFG.createVassal_Data.playAsVassal = this.getMenuElement(iID).getCheckboxState();
                }
                catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
                return;
            }
        }
    }
}

