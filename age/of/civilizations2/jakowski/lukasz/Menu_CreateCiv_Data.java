/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Clear;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Menu_CreateCiv_Data
extends SliderMenu {
    private int iSRID = 0;

    protected Menu_CreateCiv_Data() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tempH = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4;
        int tPosY = CFG.PADDING;
        this.iSRID = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
        int tempSRColorsSize = CFG.serviceRibbon_Manager.getSR(CFG.editorCivilization_GameData.sr_GameData.getSRTAG()).getSize();
        for (int i = CFG.editorCivilization_GameData.sr_GameData.getColors().size(); i < tempSRColorsSize; ++i) {
            if (i == 0) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.9843137f, 0.015686275f, 0.0f));
                continue;
            }
            if (i == 1) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(1.0f, 1.0f, 1.0f));
                continue;
            }
            if (i == 2) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.15294118f, 0.3019608f, 0.60784316f));
                continue;
            }
            if (i == 3) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.08627451f, 0.14901961f, 0.4509804f));
                continue;
            }
            Color tempColor = CFG.getRandomColor();
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(tempColor.r, tempColor.g, tempColor.b));
        }
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, true));
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f, 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_CreateCiv_Data.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateCiv_Data.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateCiv_Data.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PickColor"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Button_NewGameStyle_Left("<<", -1, CFG.PADDING, tPosY += CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 3, (tempW - CFG.PADDING * 2) / 2 + 1, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right(">>", -1, tempW - (tempW - CFG.PADDING * 2) / 2 - CFG.PADDING, tPosY, (tempW - CFG.PADDING * 2) / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        for (int i = 0; i < tempSRColorsSize; ++i) {
            menuElements.add(new Button_NewGameStyle_Clear(CFG.langManager.get("ServiceRibbon") + " - " + CFG.langManager.get("Color") + ": " + (i + 1), -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){
                int iCurrent;

                @Override
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                    oSB.setColor(CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getR(), CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getG(), CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getB(), 1.0f);
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_CreateCiv_Data.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                    ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateCiv_Data.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateCiv_Data.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                    oSB.setColor(Color.WHITE);
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }
            });
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
        }
        menuElements.add(new Button_NewGameStyle_Left(null, -1, CFG.PADDING, tPosY, (tempW - CFG.PADDING * 2) / 2 + 1, true));
        menuElements.add(new Button_NewGameStyle_Right(null, -1, tempW - (tempW - CFG.PADDING * 2) / 2 - CFG.PADDING, tPosY, (tempW - CFG.PADDING * 2) / 2, true));
        this.initMenu(null, 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT / 2 + (ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4), tempW, Math.min((tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING) + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT / 2 + (ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4) + CFG.PADDING)), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("CustomizeFlag"));
        this.getMenuElement(1).setText(CFG.langManager.get("CivilizationColor"));
        this.getMenuElement(this.getMenuElementsSize() - 2).setText(CFG.langManager.get("Back"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Save"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight(), this.getWidth() + 2, this.getHeight(), true, false);
        oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth() * 3 / 4, this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        ArrayList<Color> tempColors = new ArrayList<Color>();
        for (int i = 0; i < CFG.editorCivilization_GameData.sr_GameData.getColors().size(); ++i) {
            tempColors.add(new Color(CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getR(), CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getG(), CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getB(), 1.0f));
        }
        int tempWidth = CFG.SERVICE_RIBBON_WIDTH * 6 + CFG.PADDING * 5;
        for (int j = 0; j < 6; ++j) {
            CFG.serviceRibbon_Manager.drawSRLevel(oSB, AoCGame.LEFT + this.getWidth() / 2 - tempWidth / 2 + (CFG.SERVICE_RIBBON_WIDTH + CFG.PADDING) * j + iTranslateX, this.getMenuElement(2).getPosY() - CFG.PADDING * 2 - CFG.SERVICE_RIBBON_HEIGHT + this.getMenuPosY(), j, 0, 0, this.iSRID, tempColors);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 2) {
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.menuManager.setViewID(CFG.backToMenu);
            CFG.map.getMapBG().updateWorldMap_Shaders();
            Game_Render_Province.updateDrawProvinces();
            return;
        }
        if (iID == this.getMenuElementsSize() - 1) {
            if (CFG.menuManager.getCreateCiv_Name().getText().length() == 0) {
                CFG.toast.setInView("-- " + CFG.langManager.get("CivilizationName") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                CFG.toast.setTimeInView(3000);
            } else if (CFG.editorCivilization_GameData.getR() == 0 && CFG.editorCivilization_GameData.getG() == 0 && CFG.editorCivilization_GameData.getB() == 0) {
                CFG.toast.setInView("-- " + CFG.langManager.get("CivilizationColor") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                CFG.toast.setTimeInView(3000);
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = -1;
                CFG.menuManager.getColorPicker().setActiveRGBColor((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f);
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_GAME_COLOR);
            } else {
                CFG.menuManager.getColorPicker().setVisible(false, null);
                this.saveData();
            }
            return;
        }
        switch (iID) {
            case 0: {
                CFG.menuManager.getColorPicker().setVisible(false, null);
                CFG.menuManager.setVisibleCreateCiv_Data(false);
                CFG.menuManager.rebuildCreateCiv_Flag();
                return;
            }
            case 1: {
                if (CFG.menuManager.getColorPicker().getVisible() && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == -1) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = -1;
                    CFG.menuManager.getColorPicker().setActiveRGBColor((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f);
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_GAME_COLOR);
                }
                return;
            }
            case 2: {
                int tempSRID = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
                if (--tempSRID <= 0) {
                    tempSRID = CFG.serviceRibbon_Manager.getSRSize() - 1;
                }
                CFG.editorCivilization_GameData.sr_GameData.setSRTAG(CFG.serviceRibbon_Manager.getTag(tempSRID));
                CFG.menuManager.rebuildCreateCiv_Data();
                return;
            }
            case 3: {
                int tempSRID2 = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
                if (++tempSRID2 >= CFG.serviceRibbon_Manager.getSRSize()) {
                    tempSRID2 = 0;
                }
                CFG.editorCivilization_GameData.sr_GameData.setSRTAG(CFG.serviceRibbon_Manager.getTag(tempSRID2));
                CFG.menuManager.rebuildCreateCiv_Data();
                return;
            }
        }
        if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == iID - 4 && CFG.menuManager.getColorPicker().getVisible()) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = -1;
            CFG.menuManager.getColorPicker().setVisible(false, null);
        } else {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = iID - 4;
            CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getR(), CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getG(), CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getB());
            CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_GAME_COLOR_SR);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void saveData() {
        FileHandle file;
        OutputStream os = null;
        try {
            CFG.editorCivilization_GameData.setCivTag(CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            while (CFG.serviceRibbon_Manager.getSR(this.iSRID).getSize() > CFG.editorCivilization_GameData.sr_GameData.getColors().size()) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().remove(CFG.editorCivilization_GameData.sr_GameData.getColors().size() - 1);
            }
            file = Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
            file.writeBytes(CFG.serialize(CFG.editorCivilization_GameData), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                }
                catch (Exception ex) {}
            }
        }
        os = null;
        try {
            file = Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_FD");
            file.writeBytes(CFG.serialize(CFG.flagManager.flagEdit), false);
        }
        catch (IOException ex) {
            if (CFG.LOGS) {
                CFG.exceptionStack(ex);
            }
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                }
                catch (Exception ex) {}
            }
        }
        os = null;
        FileHandle fileSaveName = Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_NM");
        fileSaveName.writeString(CFG.menuManager.getCreateCiv_Name().getText(), false);
        try {
            FileHandle file2 = CFG.readLocalFiles() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            String tempTags = file2.readString();
            if (tempTags.indexOf(CFG.EDITOR_ACTIVE_GAMEDATA_TAG) < 0) {
                FileHandle fileSave = Gdx.files.local("game/civilizations_editor/Age_of_Civilizations");
                fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
            } else {
                String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                int iSize = tempTagsSplited.length;
                for (int i = 0; i < iSize; ++i) {
                    if (!tempTagsSplited[i].equals(CFG.EDITOR_ACTIVE_GAMEDATA_TAG)) continue;
                    tAdd = false;
                    break;
                }
                if (tAdd) {
                    FileHandle fileSave = Gdx.files.local("game/civilizations_editor/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
                }
            }
        }
        catch (GdxRuntimeException ex) {
            FileHandle fileSave = Gdx.files.local("game/civilizations_editor/Age_of_Civilizations");
            fileSave.writeString(CFG.EDITOR_ACTIVE_GAMEDATA_TAG + ";", false);
        }
        CFG.menuManager.setViewID(Menu.eGENERATE_FLAG);
    }
}

