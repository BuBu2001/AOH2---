/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
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
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_TechnologyLevels_Continents
extends SliderMenu {
    private List<Integer> lContinents = this.getContinentsOfCiv();

    protected Menu_CreateScenario_TechnologyLevels_Continents() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
            this.lContinents = this.getContinentsOfCiv();
            for (int i = 0; i < this.lContinents.size(); ++i) {
                menuElements.add(new Slider_FlagAction_Clear(CFG.map.getMapRegions().getName(this.lContinents.get(i)), CFG.PADDING * 2, CFG.PADDING + tempElemH * i, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 5, 150, CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(CFG.iCreateScenario_AssignProvinces_Civ - 1, this.lContinents.get(i))){

                    @Override
                    protected String getDrawText() {
                        return "" + (float)((int)(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getTechnologyLevel() * (float)this.getCurrent())) / 100.0f;
                    }

                    @Override
                    protected void buildElementHover() {
                        try {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.iCreateScenario_AssignProvinces_Civ));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DevelopmentLevelIn", this.getText()) + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        }
                        catch (IndexOutOfBoundsException ex) {
                            this.menuElementHover = null;
                        }
                    }
                });
            }
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() - 2 + iTranslateX, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth() + 2, this.getHeight(), false, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() + iTranslateX, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() + iTranslateX, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() + iTranslateX, Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2);
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min(tempElemH * menuElements.size(), CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Development"));
    }

    private final List<Integer> getContinentsOfCiv() {
        ArrayList<Integer> tempContinents = new ArrayList<Integer>();
        for (int i = 0; i < CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getNumOfProvinces(); ++i) {
            boolean addN = true;
            for (int j = 0; j < tempContinents.size(); ++j) {
                if (((Integer)tempContinents.get(j)).intValue() != CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getProvinceID(i)).getRegion()) continue;
                addN = false;
                break;
            }
            if (!addN) continue;
            tempContinents.add(CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getProvinceID(i)).getRegion());
        }
        return tempContinents;
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
        CFG.setCreateScenario_TechnologyLevelsByContinents_Continent(CFG.iCreateScenario_AssignProvinces_Civ - 1, this.lContinents.get(iID), this.getMenuElement(iID).getCurrent());
    }
}

