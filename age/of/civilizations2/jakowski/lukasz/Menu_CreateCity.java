/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowDown;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowLeft;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowRight;
import age.of.civilizations2.jakowski.lukasz.Button_Game_ArrowUp;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Slider;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateCity
extends SliderMenu {
    private String sName;
    private int iNameWidth;

    protected Menu_CreateCity() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Menu("", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive || this.getIsHovered() ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : (this.getClickable() ? new Color(1.0f, 1.0f, 1.0f, 1.0f) : new Color(0.84f, 0.84f, 0.84f, 0.7f));
            }

            @Override
            protected String getTextToDraw() {
                return Menu_CreateCity.this.sName + ": " + super.getText();
            }

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + Menu_CreateCity.this.iNameWidth;
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, false){

            @Override
            protected final Color getColor(boolean isActive) {
                return isActive ? new Color(0.75f, 0.8f, 0.03f, 1.0f) : (this.getClickable() ? new Color(0.941f, 1.0f, 0.0f, 1.0f) : new Color(0.674f, 0.09f, 0.066f, 0.5f));
            }
        });
        menuElements.add(new Button_Game("-", -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 5, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
        menuElements.add(new Slider("", CFG.BUTTON_WIDTH * 4 + CFG.PADDING * 6, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 5 - CFG.PADDING * 8, CFG.BUTTON_HEIGHT, 0, 4, 2){

            @Override
            protected void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                oSB.setColor(Color.WHITE);
                ImageManager.getImage(CFG.editorCity.getCityLevel()).draw(oSB, this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(CFG.editorCity.getCityLevel()).getHeight() / 2);
                super.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            protected String getDrawText() {
                return this.getText();
            }
        });
        menuElements.add(new Button_Game_ArrowLeft(CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false));
        menuElements.add(new Button_Game_ArrowDown(CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false));
        menuElements.add(new Button_Game_ArrowRight(CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, false));
        menuElements.add(new Button_Game_ArrowUp(CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 2, false));
        menuElements.add(new Button_Transparent(0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("CityName");
        CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
        this.iNameWidth = (int)CFG.glyphLayout.width;
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(2).setText(CFG.langManager.get("Save"));
        this.getMenuElement(5).setText(CFG.langManager.get("CityLevel") + ": " + CFG.getCityLevelName(this.getMenuElement(5).getCurrent()));
        if (CFG.editorCity != null) {
            if (CFG.editorCity.getCityName() != null) {
                this.getMenuElement(1).setText(CFG.editorCity.getCityName());
            }
            this.getMenuElement(5).setCurrent(CFG.getEditorCityLevel_Ref(CFG.editorCity.getCityLevel()));
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3 - 1 - ImageManager.getImage(Images.new_game_top_edge).getHeight(), CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4 + 1, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3 + 1, true, false);
        ImageManager.getImage(Images.new_game_top_edge_line_horizontal).draw2(oSB, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4 + 1 + iTranslateX, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - 1 - ImageManager.getImage(Images.new_game_top_edge_line_horizontal).getHeight(), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4 + 1), CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1, true, false);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.showKeyboard();
                return;
            }
            case 2: {
                if (CFG.editorCity.getCityName() != null && CFG.editorCity.getCityName().length() > 0) {
                    CFG.game.saveCity();
                    this.onBackPressed();
                }
                return;
            }
            case 3: {
                this.getMenuElement(5).setCurrent(this.getMenuElement(5).getCurrent() - 1);
                this.updateEditorCityLevel(this.getMenuElement(5).getCurrent());
                this.getMenuElement(5).setText(CFG.langManager.get("CityLevel") + ": " + CFG.getCityLevelName(this.getMenuElement(5).getCurrent()));
                return;
            }
            case 4: {
                this.getMenuElement(5).setCurrent(this.getMenuElement(5).getCurrent() + 1);
                this.updateEditorCityLevel(this.getMenuElement(5).getCurrent());
                this.getMenuElement(5).setText(CFG.langManager.get("CityLevel") + ": " + CFG.getCityLevelName(this.getMenuElement(5).getCurrent()));
                return;
            }
            case 5: {
                this.updateEditorCityLevel(this.getMenuElement(5).getCurrent());
                this.getMenuElement(5).setText(CFG.langManager.get("CityLevel") + ": " + CFG.getCityLevelName(this.getMenuElement(5).getCurrent()));
                return;
            }
            case 6: {
                this.updatePosX(-1);
                return;
            }
            case 7: {
                this.updatePosY(1);
                return;
            }
            case 8: {
                this.updatePosX(1);
                return;
            }
            case 9: {
                this.updatePosY(-1);
                return;
            }
        }
    }

    private final void updatePosX(int nDiff) {
        if (CFG.editorCity.getPosX() >= 0) {
            CFG.editorCity.setPosX(CFG.editorCity.getPosX() + nDiff);
            if (CFG.editorCity.getPosX() > CFG.map.getMapBG().getWidth() / CFG.map.getMapBG().getMapScale()) {
                CFG.editorCity.setPosX(CFG.editorCity.getPosX() % (CFG.map.getMapBG().getWidth() / CFG.map.getMapBG().getMapScale()));
            }
            this.updateActiveProvince();
        }
    }

    private final void updatePosY(int nDiff) {
        if (CFG.editorCity.getPosY() >= 0) {
            CFG.editorCity.setPosY(CFG.editorCity.getPosY() + nDiff);
            if (CFG.editorCity.getPosY() > CFG.map.getMapBG().getHeight() / CFG.map.getMapBG().getMapScale()) {
                CFG.editorCity.setPosY(CFG.map.getMapBG().getHeight() / CFG.map.getMapBG().getMapScale());
            } else if (CFG.editorCity.getPosY() < 0) {
                CFG.editorCity.setPosY(0);
            }
            this.updateActiveProvince();
        }
    }

    private final void updateActiveProvince() {
        CFG.game.setProvinceID(CFG.map.getMapCoordinates().getPosX() + CFG.editorCity.getPosX() * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + CFG.editorCity.getPosY() * CFG.map.getMapBG().getMapScale());
        CFG.menuManager.getCreateCity_UpdateSaveButton();
        if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
        }
    }

    private final void updateEditorCityLevel(int nLevel) {
        CFG.editorCity.setCityLevel(CFG.getEditorCityLevel(nLevel));
    }

    @Override
    protected void onBackPressed() {
        CFG.game.setActiveProvinceID(-1);
        CFG.menuManager.setViewID(Menu.eEDITOR_CITIES);
        CFG.menuManager.setBackAnimation(true);
        CFG.updateKeyboard_Actions();
    }
}

