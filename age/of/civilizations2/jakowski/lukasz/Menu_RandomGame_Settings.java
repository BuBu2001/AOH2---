/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_FlagActionSliderStyle;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Slider_FlagAction_Clear;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;

class Menu_RandomGame_Settings
extends SliderMenu {
    protected static final int ANIMATION_TIME = 175;
    protected static long lTime = 0L;

    protected Menu_RandomGame_Settings() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.GAME_WIDTH * 0.8f);
        int tempHeight = CFG.BUTTON_HEIGHT + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 3 / 4 * 4;
        menuElements.add(new Button_FlagActionSliderStyle("-", -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, true));
        menuElements.add(new Slider_FlagAction_Clear(CFG.langManager.get("Civilizations"), CFG.PADDING * 3 + CFG.BUTTON_WIDTH, CFG.PADDING, tempWidth - CFG.PADDING * 4 - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, Menu_RandomGame_Settings.getCivMax(), CFG.randomGameManager.getCivilizationsSize()){

            @Override
            protected int getWidth() {
                return Menu_RandomGame_Settings.this.getW() - this.getPosX() * 2;
            }

            @Override
            protected int getPosX() {
                return CFG.PADDING * 3 + CFG.BUTTON_WIDTH;
            }
        });
        menuElements.add(new Button_FlagActionSliderStyle("+", -1, tempWidth - CFG.PADDING - CFG.BUTTON_WIDTH, CFG.PADDING, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, true){

            @Override
            protected int getPosX() {
                return Menu_RandomGame_Settings.this.getW() - CFG.PADDING - this.getWidth();
            }
        });
        menuElements.add(new Slider_FlagAction_Clear(CFG.langManager.get("StartingPopulation"), CFG.PADDING * 2, CFG.PADDING * 2 + (CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5), tempWidth - CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 1, 2000, CFG.game.getGameScenarios().getScenario_StartingPopulation() / 100){

            @Override
            protected String getDrawText() {
                return "" + this.getCurrent() * 100;
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_POPULATION[CFG.COLOR_POPULATION.length - 1].r, CFG.COLOR_POPULATION[CFG.COLOR_POPULATION.length - 1].g, CFG.COLOR_POPULATION[CFG.COLOR_POPULATION.length - 1].b, 0.65f);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                CFG.game.getGameScenarios().setScenario_StartingPopulation(nCurrent * 100);
                super.setCurrent(nCurrent);
            }

            @Override
            protected int getWidth() {
                return Menu_RandomGame_Settings.this.getW() - this.getPosX() * 2;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear(CFG.langManager.get("StartingEconomy"), CFG.PADDING * 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5) * 2, tempWidth - CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 1, 1000, CFG.game.getGameScenarios().getScenario_StartingEconomy() / 100){

            @Override
            protected String getDrawText() {
                return "" + this.getCurrent() * 100;
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_ECONOMY[CFG.COLOR_ECONOMY.length - 1].r, CFG.COLOR_ECONOMY[CFG.COLOR_ECONOMY.length - 1].g, CFG.COLOR_ECONOMY[CFG.COLOR_ECONOMY.length - 1].b, 0.65f);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                CFG.game.getGameScenarios().setScenario_StartingEconomy(nCurrent * 100);
                super.setCurrent(nCurrent);
            }

            @Override
            protected int getWidth() {
                return Menu_RandomGame_Settings.this.getW() - this.getPosX() * 2;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear(CFG.langManager.get("StartingArmyInCapitals"), CFG.PADDING * 2, CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5) * 3, tempWidth - CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, 200, CFG.game.getGameScenarios().getScenario_StartingArmyInCapitals() / 25){

            @Override
            protected String getDrawText() {
                return "" + this.getCurrent() * 25;
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65f);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                CFG.game.getGameScenarios().setScenario_StartingArmyInCapitals(nCurrent * 25);
                super.setCurrent(nCurrent);
            }

            @Override
            protected int getSliderHeight() {
                return CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_RandomGame_Settings.this.getW() - this.getPosX() * 2;
            }
        });
        menuElements.add(new Slider_FlagAction_Clear(CFG.langManager.get("NeutralArmy"), CFG.PADDING * 2, CFG.PADDING * 5 + (CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5) * 4, tempWidth - CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5, 0, 200, CFG.randomGameManager.getNeutralArmy() / 25){

            @Override
            protected String getDrawText() {
                return "" + this.getCurrent() * 25;
            }

            @Override
            protected Color getColorLEFT() {
                return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.r, CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.g, CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.b, 0.65f);
            }

            @Override
            protected void setCurrent(int nCurrent) {
                CFG.randomGameManager.setNeutralArmy(nCurrent * 25);
                super.setCurrent(nCurrent);
            }

            @Override
            protected int getSliderHeight() {
                return CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
                return Menu_RandomGame_Settings.this.getW() - this.getPosX() * 2;
            }
        });
        if (tempHeight > ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING) {
            tempHeight = ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Settings"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + iTranslateX, nPosY - ImageManager.getImage(Images.dialog_title).getHeight() - this.getHeight(), nWidth - ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX, nPosY - ImageManager.getImage(Images.dialog_title).getHeight() - this.getHeight(), ImageManager.getImage(Images.dialog_title).getWidth(), this.getHeight(), true);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, (this.getHeight() - 2) * 2 / 3, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, ImageManager.getImage(Images.pix255_255_255).getHeight());
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.85f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight() * 2, nWidth - 4, 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), CFG.COLOR_TEXT_OPTIONS_LEFT_NS);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, (CFG.GAME_WIDTH - (int)((float)CFG.GAME_WIDTH * 0.8f)) / 2, (CFG.GAME_HEIGHT * 4 / 5 - tempHeight - CFG.BUTTON_HEIGHT * 3 / 5) / 2 > 0 ? (CFG.GAME_HEIGHT * 4 / 5 - tempHeight - CFG.BUTTON_HEIGHT * 3 / 5) / 2 : CFG.PADDING, tempWidth, tempHeight, menuElements, false, true);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            int tOld = this.getMenuElement(i).getCurrent();
            this.getMenuElement(i).setCurrent(-1);
            this.getMenuElement(i).setCurrent(tOld);
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 175L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX(), CFG.GAME_HEIGHT - this.getPosY(), this.getWidth(), -((int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - lTime) / 175.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, CFG.PADDING);
            oSB.setColor(Color.WHITE);
            super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
            CFG.setRender_3(true);
            super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        } else {
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
            ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
            ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, CFG.PADDING);
            oSB.setColor(Color.WHITE);
            super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    protected int getW() {
        return this.getWidth();
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                CFG.randomGameManager.setCivilizationsSize(this.getMenuElement(iID + 1).getCurrent());
                break;
            }
            case 1: {
                CFG.randomGameManager.setCivilizationsSize(this.getMenuElement(iID).getCurrent());
                break;
            }
            case 2: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                CFG.randomGameManager.setCivilizationsSize(this.getMenuElement(iID - 1).getCurrent());
                break;
            }
            case 3: {
                CFG.game.getGameScenarios().setScenario_StartingPopulation(this.getMenuElement(iID).getCurrent() * 100);
                break;
            }
            case 4: {
                CFG.game.getGameScenarios().setScenario_StartingEconomy(this.getMenuElement(iID).getCurrent() * 100);
                break;
            }
            case 5: {
                CFG.game.getGameScenarios().setScenario_StartingArmyInCapitals(this.getMenuElement(iID).getCurrent() * 25);
                break;
            }
            case 6: {
                CFG.randomGameManager.setNeutralArmy(this.getMenuElement(iID).getCurrent() * 25);
            }
        }
    }

    protected static final int getCivMax() {
        FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        String[] tagsSPLITED_ED = new String[]{};
        try {
            FileHandle tempFileT_ED = null;
            tempFileT_ED = CFG.isAndroid() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            String tempT_ED = tempFileT_ED.readString();
            tagsSPLITED_ED = tempT_ED.split(";");
        }
        catch (GdxRuntimeException tempFileT_ED) {
            // empty catch block
        }
        int nNumOfPlayableProvinces = 0;
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) continue;
            ++nNumOfPlayableProvinces;
        }
        return Math.min(nNumOfPlayableProvinces, tagsSPLITED.length + tagsSPLITED_ED.length);
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = System.currentTimeMillis();
    }
}

