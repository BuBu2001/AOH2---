/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Options_NS;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_TitleStyle;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Options
extends SliderMenu {
    protected static final int ANIMATION_TIME = 225;
    private long lTime = 0L;
    protected static final float SCALE_CHANGE = 0.175f;

    protected Menu_InGame_Options() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_TitleStyle(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 - CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2));
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 + CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("JustOneMoreTurnIPromise"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 + CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SaveYourProgress") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 + CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){

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
        });
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 + CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){});
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 + CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true){

            @Override
            protected boolean getClickable() {
                return !CFG.SPECTATOR_MODE;
            }
        });
        menuElements.add(new Button_Options_NS(null, -1, CFG.GAME_WIDTH / 2 - CFG.CIV_INFO_MENU_WIDTH / 2 + CFG.PADDING, 0, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT * 3 / 5, true));
        int tempElementHeight = (menuElements.size() + 1) * CFG.PADDING + CFG.PADDING;
        for (int i = 0; i < menuElements.size(); ++i) {
            tempElementHeight += ((MenuElement)menuElements.get(i)).getHeight();
        }
        int tempY = CFG.PADDING;
        ((MenuElement)menuElements.get(0)).setPosY(CFG.GAME_HEIGHT * 2 / 5 - tempElementHeight / 2 + tempY);
        tempY += ((MenuElement)menuElements.get(0)).getHeight() + CFG.PADDING * 2;
        for (int i = 1; i < menuElements.size(); ++i) {
            if (i == 2) {
                tempY += CFG.PADDING;
            }
            ((MenuElement)menuElements.get(i)).setPosY(CFG.GAME_HEIGHT * 2 / 5 - tempElementHeight / 2 + tempY);
            tempY += ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING;
        }
        menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, true));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
        super.setVisible(false);
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Menu"));
        this.getMenuElement(1).setText(CFG.langManager.get("ReturnToGame"));
        this.getMenuElement(2).setText(CFG.langManager.get("SaveTheGame"));
        this.getMenuElement(3).setText(CFG.langManager.get("Audio"));
        this.getMenuElement(4).setText(CFG.langManager.get("GameOptions"));
        this.getMenuElement(5).setText(CFG.langManager.get("Surrender"));
        this.getMenuElement(6).setText(CFG.langManager.get("ExitToMainMenu"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
        ImageManager.getImage(Images.patt2).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt2).getHeight(), this.getWidth(), this.getHeight());
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f * ((float)(System.currentTimeMillis() - this.lTime) / 225.0f)));
        } else {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45f));
        }
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.CIV_INFO_MENU_WIDTH / 2, this.getHeight());
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - CFG.CIV_INFO_MENU_WIDTH / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.CIV_INFO_MENU_WIDTH / 2, this.getHeight(), true, false);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), CFG.CIV_INFO_MENU_WIDTH / 2);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.CIV_INFO_MENU_WIDTH / 2 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), CFG.CIV_INFO_MENU_WIDTH / 2, false, true);
        oSB.setColor(Color.WHITE);
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            iTranslateY = iTranslateY - this.getHeight() * 4 / 5 + (int)((float)(this.getHeight() * 4 / 5) * ((float)(System.currentTimeMillis() - this.lTime) / 225.0f));
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getMenuElement(0).getPosX() + iTranslateX, -ImageManager.getImage(Images.new_game_top_edge).getHeight() + this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() + this.getMenuPosY() + iTranslateY, this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getMenuElement(this.getMenuElementsSize() - 2).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 2).getHeight() + CFG.PADDING * 2 - (this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight()), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, -ImageManager.getImage(Images.new_game_top_edge).getHeight() + this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() + this.getMenuPosY() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getMenuElement(this.getMenuElementsSize() - 2).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 2).getHeight() + CFG.PADDING * 2 - (this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight()), true, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    protected static final void clickBack() {
        Map_Scale.SCALE_ANIMATION_TIME = 125;
        int tX = Touch.getMousePosX();
        int tY = Touch.getMousePosY();
        Touch.setMousePosXY(CFG.GAME_WIDTH / 2, CFG.GAME_HEIGHT / 2);
        CFG.map.getMapScale().setNewCurrentScaleByButton2(0.175f);
        CFG.menuManager.setVisible_InGame_Options(false);
        Touch.setMousePosXY(tX, tY);
        CFG.game.checkProvinceActionMenu();
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 1: {
                this.onBackPressed();
                break;
            }
            case 2: {
                CFG.setDialogType(Dialog.SAVE_THE_GAME_OPTIONS);
                break;
            }
            case 3: {
                this.onBackPressed();
                CFG.menuManager.setVisibleInGame_Playlist(!CFG.menuManager.getVisibleInGame_Playlist());
                break;
            }
            case 4: {
                this.onBackPressed();
                CFG.goToMenu2 = Menu.eINGAME;
                CFG.menuManager.setViewID(Menu.eSETTINGS);
                break;
            }
            case 5: {
                CFG.setDialogType(Dialog.SURRENDER);
                break;
            }
            case 6: {
                CFG.setDialogType(Dialog.PAUSE_GAME);
            }
        }
    }

    @Override
    protected final void onBackPressed() {
        Menu_InGame_Options.clickBack();
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        this.lTime = System.currentTimeMillis();
    }
}

