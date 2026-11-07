/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CreateAVassal
extends SliderMenu {
    protected Menu_InGame_CreateAVassal() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true){

            @Override
            protected boolean getClickable() {
                try {
                    return CFG.createVassal_Data.sCivTag != null && CFG.createVassal_Data.iCapitalProvinceID >= 0 && CFG.game.getSelectedProvinces().getProvincesSize() > 0;
                }
                catch (NullPointerException ex) {
                    return false;
                }
                catch (IndexOutOfBoundsException ex) {
                    return false;
                }
            }

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (!this.getClickable()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
                }
                try {
                    CFG.createVassal_Data.getFlagOfCiv().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() - CFG.createVassal_Data.getFlagOfCiv().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                }
                catch (NullPointerException ex) {
                    ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                }
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true, false){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.selectMode;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.VIEW_SHOW_VALUES;
            }
        });
        menuElements.add(new Slide(CFG.GAME_WIDTH - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() * 2 - ImageManager.getImage(Images.slide_bg).getHeight() / 2, CFG.GAME_HEIGHT - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() / 2 - ImageManager.getImage(Images.slide_bg).getHeight() * 2, CFG.brushTool));
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH, true){

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (!this.getClickable()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
                }
                try {
                    CFG.createVassal_Data.getFlagOfCiv().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() - CFG.createVassal_Data.getFlagOfCiv().getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                }
                catch (NullPointerException ex) {
                    ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                }
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
            }

            @Override
            protected Color getColor(boolean isActive) {
                try {
                    if (CFG.createVassal_Data.iCapitalProvinceID == -1) {
                        return isActive ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
                    }
                    return super.getColor(isActive);
                }
                catch (NullPointerException ex) {
                    return super.getColor(isActive);
                }
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                }
                ImageManager.getImage(Images.wikipedia).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.wikipedia).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.wikipedia).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected boolean getClickable() {
                try {
                    return CFG.createVassal_Data.sCivTag != null;
                }
                catch (NullPointerException ex) {
                    return false;
                }
            }
        });
        menuElements.add(new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.6f);
                CFG.drawText(oSB, this.sText, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.6f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected int getPosX() {
                return 0;
            }

            @Override
            protected int getWidth() {
                return (int)((float)this.getTextWidth() * 0.6f) + CFG.PADDING * 2 + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("ReleaseAVassal"));
        this.getMenuElement(2).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(3).setText(CFG.langManager.get("Select"));
        this.getMenuElement(4).setText(CFG.langManager.get("DeselectAll"));
        this.getMenuElement(5).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(6).setText(CFG.langManager.get("Map"));
        this.getMenuElement(8).setText(CFG.langManager.get("SetCapital"));
        this.getMenuElement(10).setText(CFG.langManager.get("MapModes"));
        this.updateButtonWidth(5, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        for (int i = 3; i < 7; ++i) {
            this.updateButtonWidth(i, CFG.PADDING, CFG.BUTTON_WIDTH);
        }
        this.updateButtonWidth(0, CFG.PADDING, CFG.BUTTON_WIDTH);
        this.updateButtonWidth(8, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
        this.updateButtonWidth(1, this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
        this.updateButtonWidth(9, this.getMenuElement(1).getPosX() + this.getMenuElement(1).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(3).getWidth() - CFG.PADDING;
        this.getMenuElement(3).setPosX(tempX);
        tempX = tempX - this.getMenuElement(2).getWidth() - CFG.PADDING;
        this.getMenuElement(2).setPosX(tempX);
        tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
        this.getMenuElement(5).setPosX(tempX);
        tempX = tempX - this.getMenuElement(6).getWidth() - CFG.PADDING;
        this.getMenuElement(6).setPosX(tempX);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.top_left2_sha).draw2(oSB, this.getMenuElement(10).getPosX() + iTranslateX, this.getMenuElement(10).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY, this.getMenuElement(10).getWidth(), ImageManager.getImage(Images.top_left2_sha).getHeight(), true, false);
        ImageManager.getImage(Images.top_left2).draw2(oSB, this.getMenuElement(10).getPosX() + iTranslateX, this.getMenuElement(10).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY, this.getMenuElement(10).getWidth(), ImageManager.getImage(Images.top_left2).getHeight(), true, false);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(6).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(9).getPosX() + this.getMenuElement(9).getWidth() + CFG.PADDING, this.getMenuElement(0).getHeight() + CFG.PADDING * 2);
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
                CFG.setDialogType(Dialog.RELEASE_A_VASSAL);
                return;
            }
            case 2: {
                CFG.brushTool = !CFG.brushTool;
                break;
            }
            case 3: {
                CFG.selectMode = !CFG.selectMode;
                break;
            }
            case 4: {
                CFG.setDialogType(Dialog.DESELET_ALL_SELECTED_PROVINCES_CREATE_A_VASSAL);
                break;
            }
            case 5: {
                CFG.game.getSelectedProvinces().popProvince();
                if (CFG.game.getSelectedProvinces().getProvincesSize() == 0) {
                    CFG.selectMode = true;
                }
                boolean resetCapital = true;
                for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); ++i) {
                    if (CFG.createVassal_Data.iCapitalProvinceID != CFG.game.getSelectedProvinces().getProvince(i)) continue;
                    resetCapital = false;
                    break;
                }
                if (resetCapital) {
                    CFG.createVassal_Data.iCapitalProvinceID = -1;
                }
                CFG.updateCreateAVassal_CivInfo();
                break;
            }
            case 6: {
                CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
                CFG.map.getMapBG().updateWorldMap_Shaders();
                break;
            }
            case 8: {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getSelectedProvinces().canBeReleasedAsVassal(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getActiveProvinceID())) {
                    CFG.game.getSelectedProvinces().addProvince(CFG.game.getActiveProvinceID());
                    CFG.createVassal_Data.iCapitalProvinceID = CFG.game.getActiveProvinceID();
                    CFG.updateCreateAVassal_CivInfo();
                    CFG.toast.setInView(CFG.langManager.get("CapitalMoved"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                    break;
                }
                CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                break;
            }
            case 9: {
                if (CFG.createVassal_Data.sCivTag == null) break;
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.createVassal_Data.sCivTag;
                CFG.setDialogType(Dialog.GO_TO_WIKI);
                break;
            }
            case 10: {
                CFG.menuManager.getInGame_CreateAVassal_MapModes().setVisible(!CFG.menuManager.getInGame_CreateAVassal_MapModes().getVisible());
                if (!CFG.menuManager.getInGame_CreateAVassal_MapModes().getVisible()) {
                    CFG.viewsManager.disableAllViews();
                }
                if (CFG.menuManager.getInGame_CreateAVassal_MapModes().getPosX() >= 0) break;
                CFG.menuManager.getInGame_CreateAVassal_MapModes().setPosX_Force(CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2);
                CFG.menuManager.getInGame_CreateAVassal_MapModes().setPosY(CFG.menuManager.getInGame_CreateAVassal_MapModes().getTitle().getHeight() + CFG.BUTTON_WIDTH + CFG.PADDING * 3);
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.brushTool = false;
        CFG.menuManager.setViewID(Menu.eINGAME);
        Game_Render_Province.updateDrawProvinces();
        CFG.map.getMapBG().updateWorldMap_Shaders();
        CFG.createVassal_Data.dispose();
        CFG.createVassal_Data = null;
        CFG.viewsManager.setActiveViewID(CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
    }
}

