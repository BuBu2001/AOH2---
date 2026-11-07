/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Descripted;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Remove;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.City;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu_Editor_Cities
extends SliderMenu {
    private List<String> lTags;

    protected Menu_Editor_Cities() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Descripted(CFG.map.getMapAuthor(CFG.map.getActiveMapID()), CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        this.lTags = new ArrayList<String>();
        try {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "cities/" + "Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            for (int i = 0; i < tagsSPLITED.length; ++i) {
                this.lTags.add(tagsSPLITED[i]);
            }
        }
        catch (GdxRuntimeException tempFileT) {
            // empty catch block
        }
        for (int i = 0; i < this.lTags.size(); ++i) {
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "cities/" + this.lTags.get(i));
            try {
                City nData = (City)CFG.deserialize(fileData.readBytes());
                menuElements.add(new Button_Menu(nData.getCityName(), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true));
                menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT * (i + 2) + CFG.PADDING * (i + 3), CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true){

                    @Override
                    protected void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete")));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    }
                });
                continue;
            }
            catch (ClassNotFoundException classNotFoundException) {
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(2).setText(CFG.langManager.get("CreateaCity"));
        this.getTitle().setText(CFG.langManager.get("CitiesEditor"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        int iSize = this.lTags.size() * 2;
        for (int i = 0; i < iSize; i += 2) {
            if (!this.getMenuElement(i + 3).getIsInView()) continue;
            CFG.fontMain.getData().setScale(0.7f);
            CFG.drawText(oSB, this.lTags.get(i / 2), this.getMenuElement(i + 3).getTextPos() + this.getMenuElement(i + 3).getTextWidth() + CFG.PADDING + iTranslateX, this.getMenuElement(i + 3).getPosY() + this.getMenuElement(i + 3).getHeight() / 2 + CFG.TEXT_HEIGHT / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + this.getMenuPosY() + iTranslateY, CFG.COLOR_BUTTON_EXTRA_DESCRIPTION);
            CFG.fontMain.getData().setScale(1.0f);
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
                CFG.backToMenu = Menu.eEDITOR_CITIES;
                CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
                return;
            }
            case 2: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.editorCity = new City("", -1, -1, Images.city3);
                CFG.game.setActiveProvinceID(-1);
                CFG.menuManager.setViewID(Menu.eCREATE_CITY);
                CFG.updateKeyboard_Actions();
                return;
            }
        }
        if ((iID - 3) % 2 == 0) {
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lTags.get((iID - 3) / 2);
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "cities/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            try {
                CFG.editorCity = (City)CFG.deserialize(fileData.readBytes());
                CFG.editorCity.setCityLevel(CFG.getEditorCityLevel(CFG.editorCity.getCityLevel()));
                CFG.game.setProvinceID(CFG.map.getMapCoordinates().getPosX() + CFG.editorCity.getPosX() * CFG.map.getMapBG().getMapScale(), CFG.map.getMapCoordinates().getPosY() + CFG.editorCity.getPosY() * CFG.map.getMapBG().getMapScale());
                if (CFG.game.getActiveProvinceID() >= 0) {
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                }
            }
            catch (ClassNotFoundException classNotFoundException) {
            }
            catch (IOException iOException) {
                // empty catch block
            }
            CFG.menuManager.setViewID(Menu.eCREATE_CITY);
            CFG.updateKeyboard_Actions();
            CFG.menuManager.getCreateCity_UpdateSaveButton();
        } else {
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lTags.get((iID - 3) / 2);
            CFG.setDialogType(Dialog.REMOVE_CITY);
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eEDITOR);
        CFG.menuManager.setBackAnimation(true);
    }
}

