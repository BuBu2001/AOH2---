/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.Slide;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

class Menu_MapEditor_FormableCivs_Edit
extends SliderMenu {
    private Image lFlag = null;

    protected Menu_MapEditor_FormableCivs_Edit() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Text(null, -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, false){

            @Override
            protected boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.selectMode;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, false){

            @Override
            protected boolean getClickable() {
                return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.VIEW_SHOW_VALUES;
            }
        });
        menuElements.add(new Slide(CFG.PADDING + ImageManager.getImage(Images.slide_bg).getHeight() / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() / 2 - ImageManager.getImage(Images.slide_bg).getHeight() * 2, CFG.brushTool));
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
                    Menu_MapEditor_FormableCivs_Edit.this.lFlag.draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() - Menu_MapEditor_FormableCivs_Edit.this.lFlag.getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                }
                catch (NullPointerException ex) {
                    ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                }
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
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
            protected void buildElementHover() {
                if (CFG.formableCivs_GameData.getFormableCivTag() != null) {
                    try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                    catch (IndexOutOfBoundsException e) {
                        this.menuElementHover = null;
                    }
                    catch (NullPointerException ex) {
                        this.menuElementHover = null;
                    }
                } else {
                    this.menuElementHover = null;
                }
            }

            @Override
            protected boolean getClickable() {
                return CFG.formableCivs_GameData.getFormableCivTag() != null;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
        this.getMenuElement(3).setText(CFG.langManager.get("FormableCivilization") + ": " + (CFG.formableCivs_GameData.getFormableCivTag() == null ? CFG.langManager.get("None") : CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag())));
        this.getMenuElement(4).setText(CFG.langManager.get("Brush"));
        this.getMenuElement(5).setText(CFG.langManager.get("Select"));
        this.getMenuElement(6).setText(CFG.langManager.get("DeselectAll"));
        this.getMenuElement(7).setText(CFG.langManager.get("Undo"));
        this.getMenuElement(8).setText(CFG.langManager.get("Map"));
        this.getMenuElement(10).setText(CFG.langManager.get("SetCapital"));
        this.updateButtonWidth(5, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
        for (int i = 5; i < 9; ++i) {
            this.updateButtonWidth(i, CFG.PADDING, CFG.BUTTON_WIDTH);
        }
        this.updateButtonWidth(10, CFG.PADDING, CFG.BUTTON_WIDTH);
        this.updateButtonWidth(11, this.getMenuElement(10).getPosX() + this.getMenuElement(10).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
        int tempX = CFG.GAME_WIDTH - this.getMenuElement(5).getWidth() - CFG.PADDING;
        this.getMenuElement(5).setPosX(tempX);
        tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
        this.getMenuElement(4).setPosX(tempX);
        tempX = tempX - this.getMenuElement(6).getWidth() - CFG.PADDING;
        this.getMenuElement(6).setPosX(tempX);
        tempX = tempX - this.getMenuElement(7).getWidth() - CFG.PADDING;
        this.getMenuElement(7).setPosX(tempX);
        tempX = tempX - this.getMenuElement(8).getWidth() - CFG.PADDING;
        this.getMenuElement(8).setPosX(tempX);
        this.loadFlag();
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Top_Edge_R_Reflected(oSB, this.getMenuElement(8).getPosX() - CFG.PADDING + iTranslateX, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(8).getPosX() - CFG.PADDING), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, this.getMenuElement(10).getPosX() - CFG.PADDING + iTranslateX, this.getMenuPosY() + this.getMenuElement(10).getPosY() - CFG.PADDING + iTranslateY, this.getMenuElement(11).getPosX() + this.getMenuElement(11).getWidth() + CFG.PADDING, this.getMenuElement(10).getHeight() + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        try {
            this.lFlag.draw(oSB, this.getMenuElement(3).getPosX() + this.getMenuElement(3).getWidth() / 2 + this.getMenuElement(3).getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getMenuElement(3).getPosY() + this.getMenuElement(3).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - this.lFlag.getHeight() + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(3).getPosX() + this.getMenuElement(3).getWidth() / 2 + this.getMenuElement(3).getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getMenuElement(3).getPosY() + this.getMenuElement(3).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        }
        catch (NullPointerException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getMenuElement(3).getPosX() + this.getMenuElement(3).getWidth() / 2 + this.getMenuElement(3).getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getMenuElement(3).getPosY() + this.getMenuElement(3).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(3).getPosX() + this.getMenuElement(3).getWidth() / 2 + this.getMenuElement(3).getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getMenuElement(3).getPosY() + this.getMenuElement(3).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                CFG.brushTool = false;
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS);
                CFG.menuManager.setBackAnimation(true);
                return;
            }
            case 1: {
                if (CFG.formableCivs_GameData.getFormableCivTag() == null) {
                    CFG.toast.setInView("-- " + CFG.langManager.get("FormableCivilization") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(6000);
                    break;
                }
                if (CFG.formableCivs_GameData.getClaimantsSize() == 0) {
                    CFG.toast.setInView("-- " + CFG.langManager.get("Claimants") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(6000);
                    break;
                }
                if (CFG.game.getSelectedProvinces().getProvincesSize() == 0) {
                    CFG.toast.setInView("-- " + CFG.langManager.get("Provinces") + ": 0 --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(6000);
                    CFG.selectMode = true;
                    CFG.VIEW_SHOW_VALUES = false;
                    break;
                }
                if (!this.getIsCapitalOfFormableCivInSelectedProvinces()) {
                    CFG.toast.setInView("-- " + CFG.langManager.get("SetCapital") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(6000);
                    break;
                }
                this.saveFormableCiv();
                this.onBackPressed();
                CFG.brushTool = false;
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS);
                CFG.menuManager.setBackAnimation(true);
                break;
            }
            case 2: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
            case 3: {
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_SELECT_FORMABLE);
                break;
            }
            case 4: {
                CFG.brushTool = !CFG.brushTool;
                this.getMenuElement(9).setVisible(CFG.brushTool);
                break;
            }
            case 5: {
                CFG.selectMode = !CFG.selectMode;
                break;
            }
            case 6: {
                CFG.setDialogType(Dialog.DESELET_ALL_SELECTED_PROVINCES);
                break;
            }
            case 7: {
                CFG.game.getSelectedProvinces().popProvince();
                if (CFG.game.getSelectedProvinces().getProvincesSize() != 0) break;
                CFG.selectMode = true;
                break;
            }
            case 8: {
                CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
                break;
            }
            case 10: {
                if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                    CFG.game.getSelectedProvinces().addProvince(CFG.game.getActiveProvinceID());
                    CFG.formableCivs_GameData.setCapitalProvinceID(CFG.game.getActiveProvinceID());
                    CFG.toast.setInView(CFG.langManager.get("CapitalMoved"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                    break;
                }
                CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                break;
            }
            case 11: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.formableCivs_GameData.getFormableCivTag();
                CFG.setDialogType(Dialog.GO_TO_WIKI);
            }
        }
    }

    @Override
    protected void onBackPressed() {
        this.disposeFlag();
    }

    protected final boolean getIsCapitalOfFormableCivInSelectedProvinces() {
        if (CFG.formableCivs_GameData.getCapitalProvinceID() < 0) {
            return false;
        }
        for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); ++i) {
            if (CFG.game.getSelectedProvinces().getProvince(i) != CFG.formableCivs_GameData.getCapitalProvinceID()) continue;
            return true;
        }
        CFG.formableCivs_GameData.setCapitalProvinceID(-1);
        return false;
    }

    private final void saveFormableCiv() {
        OutputStream os = null;
        for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getSeaProvince()) continue;
            CFG.formableCivs_GameData.addProvince(CFG.game.getSelectedProvinces().getProvince(i));
        }
        try {
            FileHandle fileData = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + CFG.formableCivs_GameData.getFormableCivTag());
            fileData.writeBytes(CFG.serialize(CFG.formableCivs_GameData), false);
        }
        catch (IOException fileData) {
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                }
                catch (Exception fileData) {}
            }
        }
        try {
            FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations") : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
            String tempTags = file.readString();
            if (tempTags.indexOf(CFG.formableCivs_GameData.getFormableCivTag()) < 0) {
                FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
                fileSave.writeString(tempTags + CFG.formableCivs_GameData.getFormableCivTag() + ";", false);
            } else {
                String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                int iSize = tempTagsSplited.length;
                for (int i = 0; i < iSize; ++i) {
                    if (!tempTagsSplited[i].equals(CFG.formableCivs_GameData.getFormableCivTag())) continue;
                    tAdd = false;
                    break;
                }
                if (tAdd) {
                    FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.formableCivs_GameData.getFormableCivTag() + ";", false);
                }
            }
        }
        catch (GdxRuntimeException ex) {
            FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
            fileSave.writeString(CFG.formableCivs_GameData.getFormableCivTag() + ";", false);
        }
    }

    private final void loadFlag() {
        block10: {
            this.disposeFlag();
            if (CFG.formableCivs_GameData.getFormableCivTag() == null) {
                this.lFlag = null;
                return;
            }
            try {
                try {
                    this.lFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.formableCivs_GameData.getFormableCivTag() + ".png")), Texture.TextureFilter.Nearest);
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.lFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(CFG.formableCivs_GameData.getFormableCivTag()) + ".png")), Texture.TextureFilter.Nearest);
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.lFlag = new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.formableCivs_GameData.getFormableCivTag() + "/" + CFG.formableCivs_GameData.getFormableCivTag() + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            catch (GdxRuntimeException erq) {
                                this.lFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.formableCivs_GameData.getFormableCivTag() + "/" + CFG.formableCivs_GameData.getFormableCivTag() + "_FL.png")), Texture.TextureFilter.Nearest);
                            }
                            break block10;
                        }
                        this.lFlag = new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.formableCivs_GameData.getFormableCivTag() + "/" + CFG.formableCivs_GameData.getFormableCivTag() + "_FL.png")), Texture.TextureFilter.Nearest);
                    }
                }
            }
            catch (GdxRuntimeException e) {
                this.lFlag = null;
            }
        }
    }

    private final void disposeFlag() {
        if (this.lFlag != null) {
            this.lFlag.getTexture().dispose();
            this.lFlag = null;
        }
    }
}

