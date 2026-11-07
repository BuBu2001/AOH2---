/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Descripted;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ScenarioLoad;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_Games_Title;
import age.of.civilizations2.jakowski.lukasz.RTS;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Start_The_Game_Data;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.Iterator;

class Menu_LoadGame
extends SliderMenu {
    protected Menu_LoadGame() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempMenuWidth = Menu_Games_Title.getMenuWidth();
        int tY = 0;
        menuElements.add(new Text(null, -1, 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT * 3 / 4){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        });
        menuElements.add(new Button_Menu_Descripted(CFG.map.getMapAuthor(CFG.map.getActiveMapID()), CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()), (int)(50.0f * CFG.GUI_SCALE), 0, tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapName_Just(CFG.map.getActiveMapID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LandProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countLandProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SeaProvinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countSeaProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        try {
            FileHandle file2 = CFG.readLocalFiles() ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations") : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + "Age_of_Civilizations");
            String tempTags = file2.readString();
            String[] tSplted = tempTags.split(";");
            for (int i = tSplted.length - 1; i >= 0; --i) {
                FileHandle file = Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + tSplted[i] + "/" + tSplted[i] + ".json");
                String fileContent = file.readString();
                Json json = new Json();
                json.setElementType(SaveManager.ConfigSaveInfo.class, "Data_Save_Info", SaveManager.Data_Save_Info.class);
                SaveManager.ConfigSaveInfo data = json.fromJson(SaveManager.ConfigSaveInfo.class, fileContent);
                Iterator iterator = data.Data_Save_Info.iterator();
                if (!iterator.hasNext()) continue;
                Object e = iterator.next();
                SaveManager.Data_Save_Info tempData = (SaveManager.Data_Save_Info)e;
                menuElements.add(new Button_Menu_ScenarioLoad(i, tempData.PLAYER_TAG, tempData.Civs, tempData.GameDate + " - " + CFG.langManager.get("Turn") + ": " + tempData.Turn, 0, tY, tempMenuWidth - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true));
                menuElements.add(new Button_Menu_Remove(tempMenuWidth - CFG.BUTTON_WIDTH, tY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true){

                    @Override
                    protected void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                });
                tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }
        }
        catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
        }
        this.initMenu(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("LoadGame"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX, this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == 0) {
            return;
        }
        if (iID == 1) {
            CFG.backToMenu = Menu.eLOADGAME;
            CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
            return;
        }
        if ((iID - 2) % 2 == 1) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = (this.getMenuElementsSize() - 2) / 2 - 1 - (iID - 2) / 2;
            CFG.setDialogType(Dialog.DELETE_SAVEDGAME);
        } else {
            CFG.gameNewGame.loadGame((this.getMenuElementsSize() - 2) / 2 - 1 - (iID - 2) / 2);
            RTS.reset();
            CFG.game.disableDrawCivlizationsRegions_Players();
            CFG.viewsManager.disableAllViews();
            if (CFG.map.getMapScale().getCurrentScale() < Map_Scale.STANDARD_SCALE) {
                CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
            }
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.langManager.get("SavedGame");
            CFG.startTheGameData = new Start_The_Game_Data(false);
            CFG.menuManager.setViewIDWithoutAnimation(Menu.eSTART_THE_GAME);
        }
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_LoadGame();
    }
}

