/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_TechnologyLevels_Civs
extends SliderMenu {
    private List<Integer> lCivs;

    protected Menu_CreateScenario_TechnologyLevels_Civs() {
        int i;
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.lCivs = new ArrayList<Integer>();
        ArrayList<Integer> tempCivs = new ArrayList<Integer>();
        for (int i2 = 1; i2 < CFG.game.getCivsSize(); ++i2) {
            tempCivs.add(i2);
        }
        while (tempCivs.size() > 0) {
            int tBest = 0;
            for (i = 1; i < tempCivs.size(); ++i) {
                if (!(CFG.game.getCiv((Integer)tempCivs.get(i)).getTechnologyLevel() > CFG.game.getCiv((Integer)tempCivs.get(tBest)).getTechnologyLevel())) continue;
                tBest = i;
            }
            this.lCivs.add((Integer)tempCivs.get(tBest));
            tempCivs.remove(tBest);
        }
        int tY = CFG.PADDING;
        for (i = 0; i < this.lCivs.size(); ++i) {
            menuElements.add(new Text(CFG.game.getCiv(this.lCivs.get(i)).getCivName(), -1, 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 2){

                @Override
                protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                    CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                    CFG.fontMain.getData().setScale(0.6f);
                    CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.6f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                    CFG.fontMain.getData().setScale(1.0f);
                }
            });
            menuElements.add(new Slider_FlagAction_Clear(CFG.game.getCiv(this.lCivs.get(i)).getCivName(), CFG.PADDING * 2, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 5, 100, (int)(CFG.game.getCiv(this.lCivs.get(i)).getTechnologyLevel() * 100.0f)){

                @Override
                protected String getDrawText() {
                    return "" + (float)this.getCurrent() / 100.0f;
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() - 2 + iTranslateX, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth() + 2, this.getHeight(), false, false);
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() + iTranslateX, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() + iTranslateX, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() + iTranslateX, Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min(tempElemH * menuElements.size(), CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("TechnologyLevel"));
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
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
        if (iID % 2 == 1) {
            CFG.game.getCiv(this.lCivs.get(iID / 2)).setTechnologyLevel((float)this.getMenuElement(iID).getCurrent() / 100.0f);
        } else if (CFG.game.getCiv(this.lCivs.get(iID / 2)).getCapitalProvinceID() >= 0) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.lCivs.get(iID / 2)).getCapitalProvinceID());
        }
    }
}

