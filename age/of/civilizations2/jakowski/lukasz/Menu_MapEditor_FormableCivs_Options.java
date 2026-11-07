/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic_Wiki;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.FormableCivs_GameData;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_MapEditor_FormableCivs_Options
extends SliderMenu {
    private List<String> lCivsTags = null;
    private List<Image> lFlags = new ArrayList<Image>();
    private List<Integer> lLoadedFlags_TagsIDs = new ArrayList<Integer>();

    protected Menu_MapEditor_FormableCivs_Options() {
        this.lCivsTags = new ArrayList<String>();
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        try {
            FileHandle tempFileT = CFG.readLocalFiles() ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations") : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            ArrayList<String> lTempNames = new ArrayList<String>();
            ArrayList<String> lTempTags = new ArrayList<String>();
            if (CFG.sSearch != null && CFG.sSearch.length() > 0) {
                int iSize = tagsSPLITED.length;
                for (int i = 0; i < iSize; ++i) {
                    if (CFG.langManager.getCiv(tagsSPLITED[i]).toLowerCase().indexOf(CFG.sSearch.toLowerCase()) < 0) continue;
                    lTempNames.add(CFG.langManager.getCiv(tagsSPLITED[i]));
                    lTempTags.add(tagsSPLITED[i]);
                }
                int nPosY = 0;
                while (lTempNames.size() > 0) {
                    int toAddID = 0;
                    for (int i = 1; i < lTempNames.size(); ++i) {
                        if (!CFG.compareAlphabetic_TwoString((String)lTempNames.get(toAddID), (String)lTempNames.get(i))) continue;
                        toAddID = i;
                    }
                    menuElements.add(new Button_Menu(CFG.langManager.getCiv((String)lTempTags.get(toAddID)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT, true));
                    menuElements.add(new Button_Menu_Classic_Wiki(CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
                    this.lCivsTags.add((String)lTempTags.get(toAddID));
                    lTempNames.remove(toAddID);
                    lTempTags.remove(toAddID);
                    ++nPosY;
                }
            } else if (CFG.chosen_AlphabetCharachter == null) {
                int iSize = tagsSPLITED.length;
                for (int i = 0; i < iSize; ++i) {
                    lTempNames.add(CFG.langManager.getCiv(tagsSPLITED[i]));
                    lTempTags.add(tagsSPLITED[i]);
                }
                int nPosY = 0;
                while (lTempNames.size() > 0) {
                    int toAddID = 0;
                    for (int i = 1; i < lTempNames.size(); ++i) {
                        if (!CFG.compareAlphabetic_TwoString((String)lTempNames.get(toAddID), (String)lTempNames.get(i))) continue;
                        toAddID = i;
                    }
                    menuElements.add(new Button_Menu(CFG.langManager.getCiv((String)lTempTags.get(toAddID)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT, true));
                    menuElements.add(new Button_Menu_Classic_Wiki(CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
                    this.lCivsTags.add((String)lTempTags.get(toAddID));
                    lTempNames.remove(toAddID);
                    lTempTags.remove(toAddID);
                    ++nPosY;
                }
            } else {
                int iSize = tagsSPLITED.length;
                for (int i = 0; i < iSize; ++i) {
                    if (CFG.langManager.getCiv(tagsSPLITED[i]).charAt(0) != CFG.chosen_AlphabetCharachter.charAt(0)) continue;
                    lTempNames.add(CFG.langManager.getCiv(tagsSPLITED[i]));
                    lTempTags.add(tagsSPLITED[i]);
                }
                int nPosY = 0;
                while (lTempNames.size() > 0) {
                    int toAddID = 0;
                    for (int i = 1; i < lTempNames.size(); ++i) {
                        if (!CFG.compareAlphabetic_TwoString((String)lTempNames.get(toAddID), (String)lTempNames.get(i))) continue;
                        toAddID = i;
                    }
                    menuElements.add(new Button_Menu(CFG.langManager.getCiv((String)lTempTags.get(toAddID)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT, true));
                    menuElements.add(new Button_Menu_Classic_Wiki(CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
                    this.lCivsTags.add((String)lTempTags.get(toAddID));
                    lTempNames.remove(toAddID);
                    lTempTags.remove(toAddID);
                    ++nPosY;
                }
            }
        }
        catch (GdxRuntimeException gdxRuntimeException) {
            // empty catch block
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING - (CFG.BUTTON_HEIGHT + CFG.PADDING), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("AddNewFormableCivilization"));
    }

    @Override
    protected void updateMenuElements_IsInView() {
        super.updateMenuElements_IsInView();
        for (int i = 1; i < this.getMenuElementsSize(); i += 2) {
            int tempTagID = this.getIsLoaded(this.lCivsTags.get((i - 1) / 2));
            if (this.getMenuElement(i).getIsInView()) {
                if (tempTagID >= 0) continue;
                this.loadFlag((i - 1) / 2);
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
            try {
                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
            }
            catch (GdxRuntimeException e) {
                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")), Texture.TextureFilter.Nearest));
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
        int tempRandomButton = 1;
        try {
            for (int i = tempRandomButton; i < this.getMenuElementsSize(); i += 2) {
                if (!this.getMenuElement(i).getIsInView()) continue;
                this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() - this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).getHeight() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
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
                CFG.game.setActiveProvinceID(-1);
                CFG.game.getSelectedProvinces().clearSelectedProvinces();
                CFG.selectMode = true;
                CFG.brushTool = false;
                CFG.VIEW_SHOW_VALUES = false;
                CFG.formableCivs_GameData = new FormableCivs_GameData();
                CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_EDIT);
                return;
            }
        }
        if (iID % 2 == 1) {
            CFG.game.setActiveProvinceID(-1);
            CFG.game.getSelectedProvinces().clearSelectedProvinces();
            CFG.selectMode = true;
            CFG.brushTool = false;
            CFG.VIEW_SHOW_VALUES = false;
            try {
                FileHandle file;
                try {
                    file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + this.lCivsTags.get((iID - 1) / 2));
                    CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                catch (GdxRuntimeException ex) {
                    file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + this.lCivsTags.get((iID - 1) / 2));
                    CFG.formableCivs_GameData = (FormableCivs_GameData)CFG.deserialize(file.readBytes());
                }
                for (int i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); ++i) {
                    CFG.game.getSelectedProvinces().addProvince(CFG.formableCivs_GameData.getProvinceID(i));
                }
                CFG.map.getMapCoordinates().centerToProvinceID(CFG.formableCivs_GameData.getCapitalProvinceID());
                CFG.formableCivs_GameData.clearProvinces();
            }
            catch (ClassNotFoundException i) {
            }
            catch (IOException i) {
                // empty catch block
            }
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_EDIT);
        } else {
            String tempCivTag = this.lCivsTags.get((iID - 1) / 2);
            try {
                FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + tempCivTag);
                String sLine = readFile.readString();
                Gdx.net.openURI("https://en.wikipedia.org/wiki/" + sLine);
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("game/civilizations_informations/" + tempCivTag);
                fileSave.writeString("" + this.getMenuElement(iID - 1).getText().substring(this.getMenuElement(iID - 1).getText().indexOf(45) + 2, this.getMenuElement(iID - 1).getText().length()).replace(' ', '_'), false);
                Gdx.net.openURI("https://en.wikipedia.org/wiki/" + this.getMenuElement(iID - 1).getText().substring(this.getMenuElement(iID - 1).getText().indexOf(45) + 2, this.getMenuElement(iID - 1).getText().length()).replace(' ', '_'));
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
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

