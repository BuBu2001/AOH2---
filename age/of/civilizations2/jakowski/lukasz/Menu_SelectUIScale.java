/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ConfigINI;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_SelectUIScale
extends SliderMenu {
    private List<Image> lButtons = new ArrayList<Image>();

    protected Menu_SelectUIScale() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.lButtons.add(new Image(new Texture(Gdx.files.internal("UI/H/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(Gdx.files.internal("UI/XH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(Gdx.files.internal("UI/XXH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(Gdx.files.internal("UI/XXXH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        this.lButtons.add(new Image(new Texture(Gdx.files.internal("UI/XXXXH/buttons/menu.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear));
        int tY = CFG.PADDING;
        menuElements.add(new Button_Menu(null, -1, 0, tY, CFG.GAME_WIDTH, this.lButtons.get(0).getHeight(), true, CFG.getUIScale() == 0){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(0)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            }
        });
        menuElements.add(new Button_Menu(null, -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, this.lButtons.get(1).getHeight(), true, CFG.getUIScale() == 1){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(1)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            }
        });
        menuElements.add(new Button_Menu(null, -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, this.lButtons.get(2).getHeight(), true, CFG.getUIScale() == 2){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(2)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            }
        });
        menuElements.add(new Button_Menu(null, -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, this.lButtons.get(3).getHeight(), true, CFG.getUIScale() == 3){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(3)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            }
        });
        menuElements.add(new Button_Menu(null, -1, 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, this.lButtons.get(4).getHeight(), true, CFG.getUIScale() == 4){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ((Image)Menu_SelectUIScale.this.lButtons.get(4)).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("1"));
        this.getMenuElement(1).setText(CFG.langManager.get("2"));
        this.getMenuElement(2).setText(CFG.langManager.get("3"));
        this.getMenuElement(3).setText(CFG.langManager.get("4"));
        this.getMenuElement(4).setText(CFG.langManager.get("5"));
    }

    @Override
    protected final void actionElement(int iID) {
        ConfigINI.iUIScale = iID + 1;
        ConfigINI.saveConfig();
        switch (iID) {
            case 0: {
                CFG.settingsManager.FONT_MAIN_SIZE = 18;
                break;
            }
            case 1: {
                CFG.settingsManager.FONT_MAIN_SIZE = 24;
                break;
            }
            case 2: {
                CFG.settingsManager.FONT_MAIN_SIZE = 32;
                break;
            }
            case 3: {
                CFG.settingsManager.FONT_MAIN_SIZE = 36;
                break;
            }
            case 4: {
                CFG.settingsManager.FONT_MAIN_SIZE = 42;
            }
        }
        CFG.saveSettings();
        CFG.menuManager.setViewID(Menu.eSETTINGS);
        CFG.menuManager.setBackAnimation(true);
        CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
        CFG.toast.setTimeInView(6000);
    }

    @Override
    protected void setVisible(boolean visible) {
        if (!visible) {
            try {
                for (int i = 0; i < this.lButtons.size(); ++i) {
                    this.lButtons.get(i).getTexture().dispose();
                }
                this.lButtons.clear();
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
        }
        super.setVisible(visible);
    }
}

