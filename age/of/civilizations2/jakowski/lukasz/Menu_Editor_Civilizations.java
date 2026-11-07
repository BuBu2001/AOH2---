/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_Editor_Civilizations
extends SliderMenu {
    private List<String> lCivsTags = null;
    private List<Image> lFlags = new ArrayList<Image>();
    private List<Integer> lLoadedFlags_TagsIDs = new ArrayList<Integer>();

    protected Menu_Editor_Civilizations() {
        this.lCivsTags = new ArrayList<String>();
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0 + AoCGame.LEFT, 0, CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0 + AoCGame.LEFT, CFG.PADDING, CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
        try {
            FileHandle tempFileT = null;
            tempFileT = CFG.readLocalFiles() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            int iSize = tagsSPLITED.length;
            for (int i = 0; i < iSize; ++i) {
                try {
                    FileHandle file;
                    if (CFG.readLocalFiles()) {
                        try {
                            file = Gdx.files.local("game/civilizations_editor/" + tagsSPLITED[i] + "/" + tagsSPLITED[i] + "_NM");
                            menuElements.add(new Button_Menu(file.readString(), (int)(50.0f * CFG.GUI_SCALE), 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
                        }
                        catch (GdxRuntimeException eq) {
                            FileHandle file2 = Gdx.files.internal("game/civilizations_editor/" + tagsSPLITED[i] + "/" + tagsSPLITED[i] + "_NM");
                            menuElements.add(new Button_Menu(file2.readString(), (int)(50.0f * CFG.GUI_SCALE), 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
                        }
                    } else {
                        file = Gdx.files.internal("game/civilizations_editor/" + tagsSPLITED[i] + "/" + tagsSPLITED[i] + "_NM");
                        menuElements.add(new Button_Menu(file.readString(), (int)(50.0f * CFG.GUI_SCALE), 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
                    }
                    this.lCivsTags.add(tagsSPLITED[i]);
                    continue;
                }
                catch (GdxRuntimeException e) {
                    menuElements.add(new Button_Menu(tagsSPLITED[i], (int)(50.0f * CFG.GUI_SCALE), 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH - AoCGame.LEFT, CFG.BUTTON_HEIGHT, true));
                    this.lCivsTags.add(tagsSPLITED[i]);
                }
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("CreateNewCivilization"));
        this.getTitle().setText(CFG.langManager.get("CivilizationEditor"));
    }

    @Override
    protected void updateMenuElements_IsInView() {
        int tempRandomButton;
        super.updateMenuElements_IsInView();
        for (int i = tempRandomButton = 2; i < this.getMenuElementsSize(); ++i) {
            int tempTagID = this.getIsLoaded(this.lCivsTags.get(i - tempRandomButton));
            if (this.getMenuElement(i).getIsInView()) {
                if (tempTagID >= 0) continue;
                this.loadFlag(i - tempRandomButton);
                continue;
            }
            if (tempTagID < 0) continue;
            this.lFlags.get(tempTagID).getTexture().dispose();
            this.lFlags.set(tempTagID, null);
            this.lFlags.remove(tempTagID);
            this.lLoadedFlags_TagsIDs.remove(tempTagID);
        }
    }

    private final int getIsLoaded(String nCivTag) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (!this.lCivsTags.get(this.lLoadedFlags_TagsIDs.get(i)).equals(nCivTag)) continue;
            return i;
        }
        return -1;
    }

    private final int getFlagID(int nCivTagID) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (this.lLoadedFlags_TagsIDs.get(i) != nCivTagID) continue;
            return i;
        }
        return 0;
    }

    private final void loadFlag(int nCivTagID) {
        try {
            if (CFG.readLocalFiles()) {
                try {
                    this.lFlags.add(new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")), Texture.TextureFilter.Nearest));
                }
                catch (GdxRuntimeException erq) {
                    this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")), Texture.TextureFilter.Nearest));
                }
            } else {
                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")), Texture.TextureFilter.Nearest));
            }
        }
        catch (GdxRuntimeException e) {
            this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
        }
        this.lLoadedFlags_TagsIDs.add(nCivTagID);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        int tempRandomButton = 2;
        try {
            for (int i = tempRandomButton; i < this.getMenuElementsSize(); ++i) {
                if (!this.getMenuElement(i).getIsInView()) continue;
                this.lFlags.get(this.getFlagID(i - tempRandomButton)).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() - this.lFlags.get(this.getFlagID(i - tempRandomButton)).getHeight() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.backToMenu = Menu.eEDITOR_CIVILIZATIONS;
                CFG.menuManager.getColorPicker().setPosX(CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4 + CFG.PADDING * 4);
                CFG.flagManager.loadData();
                CFG.flagManager.initFlagEdit();
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.editorCivilization_GameData = new Civilization_GameData3();
                CFG.menuManager.setViewID(Menu.eCREATE_CIVILIZATION);
                Game_Render_Province.updateDrawProvinces();
                return;
            }
        }
        CFG.backToMenu = Menu.eEDITOR_CIVILIZATIONS;
        CFG.menuManager.getColorPicker().setPosX(CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4 + CFG.PADDING * 4);
        CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lCivsTags.get(iID - 2);
        CFG.flagManager.loadData();
        CFG.flagManager.loadFlagEdit();
        CFG.menuManager.setViewID(Menu.eCREATE_CIVILIZATION);
        Game_Render_Province.updateDrawProvinces();
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eEDITOR);
        CFG.menuManager.setBackAnimation(true);
        this.disposeData();
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            this.disposeData();
        }
    }

    protected void disposeData() {
        for (int i = 0; i < this.lFlags.size(); ++i) {
            this.lFlags.get(i).getTexture().dispose();
        }
        this.lFlags.clear();
        this.lLoadedFlags_TagsIDs.clear();
        this.lCivsTags.clear();
    }
}

