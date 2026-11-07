/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Descripted;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Games_Title;
import age.of.civilizations2.jakowski.lukasz.Slider;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.VicotryManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_VicotryConditions
extends SliderMenu {
    protected Menu_VicotryConditions() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempMenuWidth = Menu_Games_Title.getMenuWidth();
        int tY = 0;
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, tempMenuWidth, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void actionElement(int iID) {
                Menu_VicotryConditions.this.onBackPressed();
            }
        });
        menuElements.add(new Text(null, -1, 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT * 3 / 4){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        });
        menuElements.add(new Button_Menu_Descripted(CFG.langManager.get("AnnihilateAllOfYourEnemies"), CFG.langManager.get("Domination"), (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true, true));
        menuElements.add(new Slider("", CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempMenuWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, 2, 100, VicotryManager.VICTORY_CONTROL_PROVINCES_PERC){

            @Override
            protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2);
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected String getDrawText() {
                return super.getText() + ": " + this.getCurrent() + "%";
            }

            @Override
            protected Color getColorLEFT() {
                return CFG.COLOR_POPULATION[CFG.COLOR_POPULATION.length - 1];
            }

            @Override
            protected void actionElement(int iID) {
                VicotryManager.VICTORY_CONTROL_PROVINCES_PERC = this.getCurrent();
            }
        });
        menuElements.add(new Slider("", CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempMenuWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, 0, 100, VicotryManager.VICTORY_LIMIT_OF_TURNS / 10){

            @Override
            protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2);
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected String getDrawText() {
                return super.getText() + (this.getCurrent() == 0 ? CFG.langManager.get("NoThanks") : CFG.langManager.get("TurnsX", this.getCurrent() * 10));
            }

            @Override
            protected void actionElement(int iID) {
                VicotryManager.VICTORY_LIMIT_OF_TURNS = this.getCurrent() * 10;
            }
        });
        menuElements.add(new Slider("", CFG.PADDING, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, tempMenuWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, 0, 200, (int)(VicotryManager.VICTORY_TECHNOLOGY * 100.0f)){

            @Override
            protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f / 2.0f) + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2);
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected String getDrawText() {
                return super.getText() + (this.getCurrent() == 0 ? CFG.langManager.get("NoThanks") : "" + (float)this.getCurrent() / 100.0f);
            }

            @Override
            protected void actionElement(int iID) {
                VicotryManager.VICTORY_TECHNOLOGY = (float)this.getCurrent() / 100.0f;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        this.initMenuWithBackButton(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("VictoryConditions"));
        this.getMenuElement(3).setText(CFG.langManager.get("ControlProvinces"));
        this.getMenuElement(4).setText(CFG.langManager.get("TurnsLimit") + ": ");
        this.getMenuElement(5).setText(CFG.langManager.get("TechnologyLevel") + ": ");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        ImageManager.getImage(Images.patt2).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0f, 0);
        oSB.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        ImageManager.getImage(Images.gameLogo).draw(oSB, CFG.PADDING * 2 + iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
        oSB.setColor(1.0f, 1.0f, 1.0f, 0.85f);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, -ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, CFG.GAME_HEIGHT);
        oSB.setColor(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275f);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), CFG.GAME_HEIGHT);
        oSB.setColor(Color.WHITE);
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (this.getMenuElement(2).getIsInView()) {
            CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX, this.getMenuElement(2).getPosY() + this.getMenuElement(2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        this.getMenuElement(iID).actionElement(iID);
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(CFG.backToMenu);
        CFG.menuManager.setBackAnimation(true);
    }
}

