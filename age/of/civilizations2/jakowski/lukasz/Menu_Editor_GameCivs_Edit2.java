/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

class Menu_Editor_GameCivs_Edit2
extends SliderMenu {
    private final String sCivTAG = "Civilization TAG";
    private int iSRID = 0;

    protected Menu_Editor_GameCivs_Edit2() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_Line("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return "Civilization TAG: " + super.getTextToDraw();
            }
        });
        menuElements.add(new Button_Menu("", -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f, 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + Menu_Editor_GameCivs_Edit2.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getPosY() + Menu_Editor_GameCivs_Edit2.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, this.getTextWidth() - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + this.getTextWidth() - CFG.PADDING + iTranslateX, this.getPosY() + Menu_Editor_GameCivs_Edit2.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new Button_Menu("<<", -1, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("", -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_ReflectedBG(">>", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        this.iSRID = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
        int tempSRColorsSize = CFG.serviceRibbon_Manager.getSR(CFG.editorCivilization_GameData.sr_GameData.getSRTAG()).getSize();
        for (i = 0; i < tempSRColorsSize; ++i) {
            menuElements.add(new Button_Menu(CFG.langManager.get("ServiceRibbon") + " - " + CFG.langManager.get("Color") + ": " + (i + 1), -1, 0, CFG.BUTTON_HEIGHT * (3 + i) + CFG.PADDING * (4 + i), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){
                int iCurrent;

                @Override
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                    oSB.setColor(CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getR(), CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getG(), CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getB(), 1.0f);
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + Menu_Editor_GameCivs_Edit2.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                    ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + CFG.PADDING + iTranslateX, this.getPosY() + Menu_Editor_GameCivs_Edit2.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, this.getTextWidth() - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + this.getTextWidth() - CFG.PADDING + iTranslateX, this.getPosY() + Menu_Editor_GameCivs_Edit2.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                    oSB.setColor(Color.WHITE);
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
        }
        for (i = CFG.editorCivilization_GameData.sr_GameData.getColors().size(); i < tempSRColorsSize; ++i) {
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
        menuElements.add(new Button_Menu_LR_Line(null, -1, CFG.GAME_WIDTH / 2, CFG.PADDING, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.getMenuElement(this.getMenuElementsSize() - 1).setPosY(this.getMenuElement(0).getPosY());
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.editorCivilization_GameData.getCivTag());
        this.getMenuElement(2).setText(CFG.langManager.get("CivilizationColor"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Save"));
        this.getTitle().setText("Age of Civilizations II - " + CFG.langManager.get("GameCivilizations"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        ArrayList<Color> tempColors = new ArrayList<Color>();
        for (int i = 0; i < CFG.editorCivilization_GameData.sr_GameData.getColors().size(); ++i) {
            tempColors.add(new Color(CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getR(), CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getG(), CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getB(), 1.0f));
        }
        int tempWidth = CFG.SERVICE_RIBBON_WIDTH * 6 + CFG.PADDING * 5;
        for (int j = 0; j < 6; ++j) {
            CFG.serviceRibbon_Manager.drawSRLevel(oSB, CFG.GAME_WIDTH / 2 - tempWidth / 2 + (CFG.SERVICE_RIBBON_WIDTH + CFG.PADDING) * j + iTranslateX, this.getMenuElement(4).getPosY() + this.getMenuElement(4).getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 + this.getMenuPosY(), j, 0, 0, this.iSRID, tempColors);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                break;
            }
            case 1: {
                CFG.showKeyboard();
                break;
            }
            case 2: {
                if (CFG.menuManager.getColorPicker().getVisible() && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == -1) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                    break;
                }
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = -1;
                CFG.menuManager.getColorPicker().setActiveRGBColor((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f);
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_GAME_COLOR);
                break;
            }
            case 3: {
                int tempSRID = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
                if (--tempSRID <= 0) {
                    tempSRID = CFG.serviceRibbon_Manager.getSRSize() - 1;
                }
                CFG.editorCivilization_GameData.setCivTag(this.getMenuElement(1).getText());
                CFG.editorCivilization_GameData.sr_GameData.setSRTAG(CFG.serviceRibbon_Manager.getTag(tempSRID));
                CFG.menuManager.setViewIDWithoutAnimation(Menu.eEDITOR_GAME_CIVS_EDIT);
                break;
            }
            case 4: {
                CFG.toast.setInView("ID: " + CFG.serviceRibbon_Manager.getTag(this.iSRID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                break;
            }
            case 5: {
                int tempSRID2 = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
                if (++tempSRID2 >= CFG.serviceRibbon_Manager.getSRSize()) {
                    tempSRID2 = 0;
                }
                CFG.editorCivilization_GameData.setCivTag(this.getMenuElement(1).getText());
                CFG.editorCivilization_GameData.sr_GameData.setSRTAG(CFG.serviceRibbon_Manager.getTag(tempSRID2));
                CFG.menuManager.setViewIDWithoutAnimation(Menu.eEDITOR_GAME_CIVS_EDIT);
                break;
            }
            default: {
                if (iID == this.getMenuElementsSize() - 1) {
                    if (this.getMenuElement(1).getText().length() == 0) {
                        CFG.showKeyboard(1);
                        CFG.toast.setInView("Civilization TAG");
                        break;
                    }
                    CFG.editorCivilization_GameData.setCivTag(this.getMenuElement(1).getText());
                    this.saveData();
                    break;
                }
                if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == iID - 6 && CFG.menuManager.getColorPicker().getVisible()) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = -1;
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                    break;
                }
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = iID - 6;
                CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getR(), CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getG(), CFG.editorCivilization_GameData.sr_GameData.getColor(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getB());
                CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_GAME_COLOR_SR);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void saveData() {
        block30: {
            FileHandle file;
            OutputStream os = null;
            try {
                while (CFG.serviceRibbon_Manager.getSR(this.iSRID).getSize() > CFG.editorCivilization_GameData.sr_GameData.getColors().size()) {
                    CFG.editorCivilization_GameData.sr_GameData.getColors().remove(CFG.editorCivilization_GameData.sr_GameData.getColors().size() - 1);
                }
                file = Gdx.files.local("game/civilizations/" + CFG.editorCivilization_GameData.getCivTag());
                file.writeBytes(CFG.serialize(CFG.editorCivilization_GameData), false);
            }
            catch (GdxRuntimeException ex) {
                CFG.toast.setInView("----- " + CFG.langManager.get("Error") + " -----", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                CFG.toast.setTimeInView(6000);
            }
            catch (IOException ex) {
                if (CFG.LOGS) {
                    CFG.exceptionStack(ex);
                }
            }
            catch (NullPointerException ex) {
                CFG.toast.setInView("----- " + CFG.langManager.get("Error") + " -----", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                CFG.toast.setTimeInView(6000);
                return;
            }
            finally {
                if (os != null) {
                    try {
                        os.close();
                    }
                    catch (Exception ex) {}
                }
            }
            try {
                file = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
                String tempTags = file.readString();
                if (tempTags.indexOf(CFG.editorCivilization_GameData.getCivTag()) < 0) {
                    FileHandle fileSave = Gdx.files.local("game/civilizations/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.editorCivilization_GameData.getCivTag() + ";", false);
                    break block30;
                }
                String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                int iSize = tempTagsSplited.length;
                for (int i = 0; i < iSize; ++i) {
                    if (!tempTagsSplited[i].equals(CFG.editorCivilization_GameData.getCivTag())) continue;
                    tAdd = false;
                    break;
                }
                if (tAdd) {
                    FileHandle fileSave = Gdx.files.local("game/civilizations/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.editorCivilization_GameData.getCivTag() + ";", false);
                    break block30;
                }
                this.onBackPressed();
                return;
            }
            catch (GdxRuntimeException ex) {
                FileHandle fileSave = Gdx.files.local("game/civilizations/Age_of_Civilizations");
                fileSave.writeString(CFG.editorCivilization_GameData.getCivTag() + ";", false);
            }
        }
        try {
            FileHandle readFile = Gdx.files.internal("game/civilizations_informations/" + CFG.editorCivilization_GameData.getCivTag());
            readFile.readString();
        }
        catch (GdxRuntimeException ex) {
            FileHandle fileSave2 = Gdx.files.local("game/civilizations_informations/" + CFG.editorCivilization_GameData.getCivTag());
            fileSave2.writeString("" + CFG.langManager.get(this.getMenuElement(1).getText()).substring(CFG.langManager.get(this.getMenuElement(1).getText()).indexOf(45) + 2, CFG.langManager.get(this.getMenuElement(1).getText()).length()).replace(' ', '_'), false);
            Gdx.net.openURI("https://en.wikipedia.org/wiki/" + CFG.langManager.get(CFG.editorCivilization_GameData.getCivTag()));
        }
        this.onBackPressed();
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eEDITOR_GAME_CIVS);
        CFG.menuManager.setBackAnimation(true);
        CFG.menuManager.getColorPicker().setVisible(false, null);
        Game_Render_Province.updateDrawProvinces();
    }
}

